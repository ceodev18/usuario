package com.kelly.usuario.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kelly.usuario.Interfaz.DataHolder;
import com.kelly.usuario.Interfaz.PersonalData;
import com.kelly.usuario.Interfaz.SplashActivity;
import com.kelly.usuario.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PerfilFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Button b_logout;
    private String mParam1;
    private String mParam2;
    private TextView tv_address_perfil,tv_name_perfil,tv_phone_perfil;
    private View view;
    private OnFragmentInteractionListener mListener;

    public PerfilFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
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
        view =inflater.inflate(R.layout.fragment_perfil, container, false);
        fillData();
        return view;
    }
    private void fillData(){

        b_logout=(Button) view.findViewById(R.id.b_logout);

        tv_name_perfil=(TextView) view.findViewById(R.id.tv_name_perfil);

        tv_address_perfil=(TextView) view.findViewById(R.id.tv_address_perfil);

        tv_phone_perfil= (TextView) view.findViewById(R.id.tv_phone_perfil);

        SharedPreferences sharedPreferencesManual = getContext().getSharedPreferences(PersonalData.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String name_user = sharedPreferencesManual.getString(PersonalData.USER_NAME, " ");
        String phone_user = sharedPreferencesManual.getString(PersonalData.USER_PHONE, " ");
        String address_user = sharedPreferencesManual.getString(PersonalData.USER_ADDRESS, " ");
        tv_name_perfil.setText(name_user);
        tv_phone_perfil.setText(phone_user);
        tv_address_perfil.setText(address_user);


        b_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences_log = getContext().getSharedPreferences(PersonalData.LOGGEDIN_SHARED_PREF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences_log.edit();
                editor.putString(PersonalData.LOGIN,"true");
                editor.commit();
                Intent intent = new Intent(getContext(), SplashActivity.class);
                startActivity(intent);
            }
        });
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
