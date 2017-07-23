package com.kelly.usuario.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kelly.usuario.Classes.Schedule;
import com.kelly.usuario.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KELLY on 16-Jul-17.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleAdapterViewHolder>{
    private Context context;
    private ArrayList<Schedule> scheduleList;
    public ScheduleAdapter(Context context,ArrayList<Schedule>scheduleList){
        this.context=context;
        this.scheduleList=scheduleList;
    }

    @Override
    public ScheduleAdapter.ScheduleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_schedule,parent,false);
        ScheduleAdapterViewHolder scheduleAdapterViewHolder = new ScheduleAdapterViewHolder(v);
        return scheduleAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(ScheduleAdapter.ScheduleAdapterViewHolder holder, int position) {
        int nroDisp;
        holder.tv_hours.setText(scheduleList.get(position).getTimeInit() +" - "+scheduleList.get(position).getTimeEnd());
        holder.tv_numberTaxi.setText("Taxi Nro. "+scheduleList.get(position).getOrder());
        nroDisp= Integer.parseInt(scheduleList.get(position).getSlots());
        nroDisp=4-nroDisp;
        if(nroDisp==0){
            holder.tv_numberFree.setText("Lleno");
            holder.tv_labelDisp.setText("");
        }else if(nroDisp>=0 && nroDisp<4){
            holder.tv_numberFree.setText(nroDisp+"");
        }else{
            holder.tv_numberFree.setText("Lleno");
            holder.tv_labelDisp.setText("");

        }


    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public static class ScheduleAdapterViewHolder extends RecyclerView.ViewHolder {
         CardView cv_schedule;
         TextView tv_hours,tv_labelDisp,tv_numberFree,tv_numberTaxi;
        public ScheduleAdapterViewHolder(View itemView) {
            super(itemView);
            cv_schedule= (CardView) itemView.findViewById(R.id.cv_schedule);
            tv_hours= (TextView) itemView.findViewById(R.id.tv_hours);
            tv_numberFree= (TextView) itemView.findViewById(R.id.tv_numberFree);
            tv_numberTaxi= (TextView) itemView.findViewById(R.id.tv_numberTaxi);
            tv_labelDisp= (TextView) itemView.findViewById(R.id.tv_labelDisp);
        }
    }
}
