package com.kelly.usuario.Interfaz;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kelly.usuario.R;

import java.util.HashMap;
import java.util.Map;

public class PeakPositionActivity extends AppCompatActivity {
    private AlertDialog dialog=null;
    private AlertDialog.Builder builder;
    private Button b_cancelar,b_getService,b_ok;
    private Context context=this;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseData;
    private String idDriver,idTravel;
    private TextView tv_cost,tv_order,tv_phone_peak,tv_starTime,
            tv_licensePlate,tv_color,tv_carType,tv_driverName;
    private View mView;
    private int state=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peak_position);
        setElements();
        setActions();
    }
    private void  setElements(){
        idDriver = getIntent().getStringExtra("idDriver");
        idTravel= getIntent().getStringExtra("idTravel");
        b_getService= (Button)findViewById(R.id.b_getService);

        tv_cost=(TextView)findViewById(R.id.tv_cost);
        tv_cost.setText("Costo: "+getIntent().getStringExtra("cost"));

        tv_order=(TextView)findViewById(R.id.tv_order);
        tv_order.setText("Auto Nro. "+getIntent().getStringExtra("order"));

        tv_phone_peak=(TextView)findViewById(R.id.tv_phone_peak);
        tv_phone_peak.setText(idDriver);

        tv_starTime=(TextView)findViewById(R.id.tv_starTime);
        tv_starTime.setText(getIntent().getStringExtra("startTime"));

        tv_licensePlate=(TextView)findViewById(R.id.tv_licensePlate);
        tv_color=(TextView)findViewById(R.id.tv_color);
        tv_carType=(TextView)findViewById(R.id.tv_carType);
        tv_driverName=(TextView)findViewById(R.id.tv_driverName);



        fillDataDriver(idDriver);
    }
    private void  setActions(){
        b_getService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });


    }
    private void showDialog(){
        mView = new View(context,null);
        mView= getLayoutInflater().inflate(R.layout.viewalertdialog, null);
        b_cancelar= (Button)mView.findViewById(R.id.b_cancelar_dialog);
        b_ok= (Button)mView.findViewById(R.id.b_ok_dialog);
        b_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();

            }
        });
        b_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //enviar data al firebase
                insertUserIntoSchedule();
                state=1;
                //updatevalues
                FirebaseApp.initializeApp(context);
                firebaseData=FirebaseDatabase.getInstance();
                //gettin data user
                databaseReference = firebaseData.getReference("/viajes/"+idTravel+"/schedule");
                Map<String,Object> taskMap = new HashMap<String,Object>();
                int slost = Integer.parseInt(getIntent().getStringExtra("slots"));
                slost++;
                taskMap.put("slots", slost+"");
                databaseReference.updateChildren(taskMap);
                dialog.cancel();
                Toast.makeText(context, "Reserva exitosa!", Toast.LENGTH_SHORT).show();

            }
        });
        builder = new AlertDialog.Builder(context, R.style.AlertDialogCustom);
        if(builder!=null){
            builder.setView(mView);

            dialog = builder.create();
            if(state==1){
                Toast.makeText(context, "Usted ya programó el servicio", Toast.LENGTH_SHORT).show();
            }else
                dialog.show();
        }

    }
    private void fillDataDriver(String id){
        //verificar datos e ingresar al menu
        FirebaseApp.initializeApp(context);
        firebaseData=FirebaseDatabase.getInstance();
        //gettin data user
        databaseReference = firebaseData.getReference("/conductores");

        databaseReference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tv_licensePlate.setText(dataSnapshot.child("licensePlate").getValue(String.class));
                tv_color.setText(dataSnapshot.child("color").getValue(String.class));
                tv_carType.setText(dataSnapshot.child("brandCar").getValue(String.class));
                tv_driverName.setText(dataSnapshot.child("name").getValue(String.class));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    private void insertUserIntoSchedule(){
        SharedPreferences sharedPreferencesManual =context.getSharedPreferences(PersonalData.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String phone_user = sharedPreferencesManual.getString(PersonalData.USER_PHONE, " ");
        String id = getIntent().getStringExtra("idTravel");
        FirebaseApp.initializeApp(context);
        firebaseData=FirebaseDatabase.getInstance();
        databaseReference = firebaseData.getReference("/viajes/"+id+"/users");
        databaseReference.child(phone_user).setValue("usuario");
    }

}
