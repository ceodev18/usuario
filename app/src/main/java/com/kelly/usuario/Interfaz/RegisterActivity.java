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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kelly.usuario.Classes.User;
import com.kelly.usuario.R;

public class RegisterActivity extends AppCompatActivity {
    private Button b_register;
    private Context context=this;
    private DatabaseReference databaseReference;
    private EditText et_address,et_name,et_password,et_phone;
    private Window window=null;
    private FirebaseDatabase firebaseData;
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_register);
        setElements();
        setActions();
    }
    private void setElements(){
        b_register=(Button) findViewById(R.id.b_register);
        et_address=(EditText)findViewById(R.id.et_address);
        et_name=(EditText)findViewById(R.id.et_name);
        et_phone=(EditText)findViewById(R.id.et_phone);
        et_password=(EditText)findViewById(R.id.et_password);
    }
    private boolean verirfyContent(){
        if(et_address.getText().toString().length()==0|| et_name.getText().toString().length()==0 ||
                (et_phone.getText().toString().length()==0 || et_phone.getText().toString().length()<9)
                || (et_password.getText().toString().length()==0 ||et_password.getText().toString().length()<8))return false;
        return true;
    }
    private void setActions(){
        b_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verirfyContent()){
                    //enviar data a Firebase
                    FirebaseApp.initializeApp(context);
                    firebaseData=FirebaseDatabase.getInstance();
                    databaseReference = firebaseData.getReference("/usuarios");
                    User user =new User(et_address.getText().toString(),et_name.getText().toString(),et_phone.getText().toString());
                    databaseReference.child(et_phone.getText().toString()).setValue(user);
                    //saving personal data
                    savingPersonalData();
                    //PersonalData.nameUser=et_name.getText().toString();
                    Intent intent = new Intent(context,MenuActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(context, "Verifico los datos de registro.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void savingPersonalData(){
        SharedPreferences sharedPreferences_log = getSharedPreferences(PersonalData.LOGGEDIN_SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences_log.edit();
        editor.putString(PersonalData.LOGIN,"false");
        editor.commit();
        //saving personal data
        SharedPreferences sharedPreferencesManual = context.getSharedPreferences(PersonalData.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorDatos= sharedPreferencesManual.edit();
        String user_name=et_name.getText().toString();
        String user_phone=et_phone.getText().toString();
        String user_address=et_address.getText().toString();
        editorDatos.putString(PersonalData.USER_NAME,user_name);
        editorDatos.putString(PersonalData.USER_PHONE,user_phone);
        editorDatos.putString(PersonalData.USER_ADDRESS,user_address);
        editorDatos.commit();


    }
}
