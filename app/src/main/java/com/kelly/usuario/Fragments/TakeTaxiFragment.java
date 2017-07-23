package com.kelly.usuario.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kelly.usuario.Adapters.TravelAdapter;
import com.kelly.usuario.Classes.Travel;
import com.kelly.usuario.Interfaz.JoinTaxiActivity;
import com.kelly.usuario.R;
import com.kelly.usuario.Utils.DividerItemDecoration;
import com.kelly.usuario.Utils.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TakeTaxiFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TakeTaxiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TakeTaxiFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<Travel> listObject;
    private RecyclerView rv_takeTaxi;
    private View view;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TakeTaxiFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static TakeTaxiFragment newInstance(String param1, String param2) {
        TakeTaxiFragment fragment = new TakeTaxiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_take_taxi, container, false);
        setElements();
        setActions();
        return view;
    }
    private void setElements(){
        rv_takeTaxi = (RecyclerView) view.findViewById(R.id.rv_takeTaxi);
        rv_takeTaxi.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv_takeTaxi.setLayoutManager(llm);
        fillData();
        TravelAdapter travelAdapter = new TravelAdapter(getContext(),listObject);
        rv_takeTaxi.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rv_takeTaxi.setAdapter(travelAdapter);
    }
    private void fillData(){
        listObject = new ArrayList<>();
        listObject.add(new Travel("Acobamba","Huancavelica"));
        listObject.add(new Travel("Huancavelica","Acobamba"));
        listObject.add(new Travel("Acobamba","Huancayo"));
        listObject.add(new Travel("Huancayo","Acobamba"));
        listObject.add(new Travel("Acobamba","Huanta"));
        listObject.add(new Travel("Huanta","Acobamba"));
    }
    private void setActions(){
        rv_takeTaxi.addOnItemTouchListener(new RecyclerTouchListener(getContext(), rv_takeTaxi, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getContext(), JoinTaxiActivity.class);
                intent.putExtra("beginDestination",listObject.get(position).getBeginDestination());
                intent.putExtra("endDestination",listObject.get(position).getEndDestination());
                startActivity(intent);
            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
