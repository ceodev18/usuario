package com.kelly.usuario.Interfaz;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kelly.usuario.Adapters.ScheduleAdapter;
import com.kelly.usuario.Adapters.TravelAdapter;
import com.kelly.usuario.Classes.Driver;
import com.kelly.usuario.Classes.Schedule;
import com.kelly.usuario.Classes.Travel;
import com.kelly.usuario.R;
import com.kelly.usuario.Utils.DividerItemDecoration;
import com.kelly.usuario.Utils.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class JoinTaxiActivity extends AppCompatActivity {
    private CircularProgressView progressView;
    private Context context=this;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    FloatingActionButton fab_update;
    private int counter=0;
    private ArrayList<Schedule>scheduleList;
    private RecyclerView rv_taxiFree;
    private String beginDestination,endDestination;
    private ScheduleAdapter scheduleAdapter;
    private TextView tv_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_taxi);
        setElements();
        setActions();
    }
    private void setElements(){
        fab_update= (FloatingActionButton) findViewById(R.id.fab_update);
        progressView = (CircularProgressView) findViewById(R.id.pb_loading);
        progressView.startAnimation();
        progressView.setColor(R.color.backgroundColor);

        scheduleList = new ArrayList<>();
        beginDestination=getIntent().getStringExtra("beginDestination").toString();
        endDestination=getIntent().getStringExtra("endDestination").toString();
        //settin recycler view
        rv_taxiFree=(RecyclerView) findViewById(R.id.rv_taxiFree);
        rv_taxiFree.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        rv_taxiFree.setLayoutManager(llm);
        //pushing data
        fillData();
        changeViews();
        //adding onClick event
        scheduleAdapter = new ScheduleAdapter(context,scheduleList);
        rv_taxiFree.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        rv_taxiFree.setAdapter(scheduleAdapter);
        tv_title=(TextView) findViewById(R.id.tv_tittle);
    }
    private void changeViews(){
        final Handler handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {
                progressView.stopAnimation();
                progressView.setVisibility(View.GONE);
                rv_taxiFree.setVisibility(View.VISIBLE);
                handler.postDelayed(this, 2000);
            }
        };

        handler.postDelayed(r, 2000);

    }
    private void setActions(){
        fab_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        tv_title.setText(beginDestination+" - "+endDestination);


        rv_taxiFree.addOnItemTouchListener(new RecyclerTouchListener(context, rv_taxiFree, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //open activity
                int slots =Integer.parseInt(scheduleList.get(position).getSlots());
                if( slots==4){
                    Toast.makeText(context, "No hay asientos disponibles", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(context,PeakPositionActivity.class);
                    intent.putExtra("order",scheduleList.get(position).getOrder());
                    intent.putExtra("cost",scheduleList.get(position).getCosto());
                    intent.putExtra("idDriver",scheduleList.get(position).getChoferID());
                    intent.putExtra("startTime",scheduleList.get(position).getTimeInit());
                    intent.putExtra("idTravel",scheduleList.get(position).getIdTravel());
                    intent.putExtra("slots",scheduleList.get(position).getSlots());
                    startActivity(intent);
                }

            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));


    }
    private void fillData(){
        //make consult to firebase
        FirebaseApp.initializeApp(context);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("viajes");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String idTravel = dataSnapshot.getKey().toString();
                Travel travel= dataSnapshot.getValue(Travel.class);
                if(travel.getBeginDestination().equals(beginDestination)&&
                        travel.getEndDestination().equals(endDestination)) {
                    counter++;
                    Schedule schedule = new Schedule(travel.getSchedule());
                    schedule.setOrder(counter+"");
                    schedule.setIdTravel(idTravel);
                    scheduleList.add(schedule);
                    scheduleAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}
