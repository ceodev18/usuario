package com.kelly.usuario.Interfaz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kelly.usuario.R;

public class LoginActivity extends AppCompatActivity {
    private Button b_login;
    private Context context=this;
    private DatabaseReference databaseReference;
    private EditText et_password,et_phone;
    private FirebaseDatabase firebaseData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_login);
        setElements();
        setActions();
    }
    private void setElements(){
        b_login=(Button) findViewById(R.id.b_login);
        et_password=(EditText) findViewById(R.id.et_password);
        et_phone=(EditText) findViewById(R.id.et_phone);
    }
    private boolean verirfyContent(){
        if((et_phone.getText().length()==0 || et_phone.getText().length()<9)
                || (et_password.getText().length()==0 ||et_password.getText().length()<8))return false;
        return true;
    }
    private void setActions(){
        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verirfyContent()){
                    //verificar datos e ingresar al menu
                    FirebaseApp.initializeApp(context);
                    firebaseData=FirebaseDatabase.getInstance();
                    //gettin data user
                    databaseReference = firebaseData.getReference("/usuarios");

                    databaseReference.child(et_phone.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String password =  dataSnapshot.child("password").getValue(String.class);
                            String name =  dataSnapshot.child("name").getValue(String.class);
                            String address =  dataSnapshot.child("address").getValue(String.class);
                            // verifying password match
                            if (dataSnapshot.exists() && password.equals(et_password.getText().toString())) {
                                savingPersonalData(et_phone.getText().toString(),name,address);
                                Intent intent= new Intent(context, MenuActivity.class);
                                context.startActivity(intent);
                            }else {
                                // TODO: handle the case where the data does not yet exist
                                Toast.makeText(context, "DNI o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }else{
                    Toast.makeText(context,"Verifico los datos de registro.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void savingPersonalData(String phone,String name,String address){
        SharedPreferences sharedPreferences_log = getSharedPreferences(PersonalData.LOGGEDIN_SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences_log.edit();
        editor.putString(PersonalData.LOGIN,"false");
        editor.commit();
        //saving personal data
        DataHolder.getInstance().setName(name);
        DataHolder.getInstance().setPhone(phone);
        DataHolder.getInstance().setAddress(address);

        SharedPreferences sharedPreferencesManual = context.getSharedPreferences(PersonalData.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorDatos= sharedPreferencesManual.edit();
        editorDatos.putString(PersonalData.USER_NAME,name);
        editorDatos.putString(PersonalData.USER_PHONE,phone);
        editorDatos.putString(PersonalData.USER_ADDRESS,address);
        editorDatos.commit();
    }
}
