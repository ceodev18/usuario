package com.kelly.usuario.Interfaz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.kelly.usuario.MainActivity;
import com.kelly.usuario.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    // Set the duration of the splash screen
    private Button b_ingresar,b_registrar;
    private Context context=this;
    private  Window window=null;
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    /** Called when the activity is first created. */
    Thread splashTread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_splash);
        verifyLogin();
    }
    private void startAnimations(){
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.ll_splash);
        l.clearAnimation();
        l.startAnimation(anim);



        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();

        LinearLayout ll_contenSplash = (LinearLayout) findViewById(R.id.ll_contenSplash);
        ll_contenSplash.setVisibility(View.VISIBLE);
        b_ingresar=(Button) findViewById(R.id.b_ingresar);
        b_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        b_registrar=(Button) findViewById(R.id.b_registrar);
        b_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context,RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        ll_contenSplash.clearAnimation();
        ll_contenSplash.startAnimation(anim);
        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (true) {
                        sleep(100);
                        waited += 100;
                    }
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    //SplashAcitivity.this.finish();
                }

            }
        };
        splashTread.start();
    }
    private void verifyLogin(){
        SharedPreferences sharedPreferences_log = getSharedPreferences(PersonalData.LOGGEDIN_SHARED_PREF, Context.MODE_PRIVATE);
        String loggedIn = sharedPreferences_log.getString(PersonalData.LOGIN,"null");
        if(loggedIn.equals("false")){
            //make splash
            runSplash();
        }else{
            startAnimations();
        }

    }
    private void runSplash(){

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // Start the next activity
                Intent mainIntent = new Intent(context,MenuActivity.class);
                startActivity(mainIntent);

                // Close the activity so the user won't able to go back this
                // activity pressing Back button
                finish();
            }
        };
        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, Config.SPLASH_SCREEN_DELAY);
    }
}
