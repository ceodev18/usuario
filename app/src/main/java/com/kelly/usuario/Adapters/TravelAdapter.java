package com.kelly.usuario.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.kelly.usuario.Classes.Travel;
import com.kelly.usuario.R;

import java.util.List;

public class TravelAdapter  extends RecyclerView.Adapter<TravelAdapter.TravelObjectViewHolder>{
    List<Travel> travelList;
    private  Context context;
    public TravelAdapter(Context context, List<Travel> travelList){
        this.travelList=travelList;
        this.context=context;
    }

    @Override
    public TravelObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_travel,parent,false);
        TravelObjectViewHolder travelObjectViewHolder = new TravelObjectViewHolder(v);

        return travelObjectViewHolder;
    }

    @Override
    public void onBindViewHolder(TravelObjectViewHolder holder, int position) {
        holder.tv_from.setText(travelList.get(position).getBeginDestination());
        holder.tv_to.setText(travelList.get(position).getEndDestination());
    }


    @Override
    public int getItemCount() {
        return travelList.size();
    }
    public static class TravelObjectViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView tv_from,tv_to;
        TravelObjectViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cv_travel);
            tv_from = (TextView)itemView.findViewById(R.id.tv_from);
            tv_to = (TextView)itemView.findViewById(R.id.tv_to);
        }
    }
}
