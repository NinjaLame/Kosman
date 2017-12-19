package com.example.bayu.kosman.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.bayu.kosman.R;
import com.example.bayu.kosman.Static;
import com.example.bayu.kosman.adapters.BuildingsAdapter;
import com.example.bayu.kosman.api.Owners;
import com.example.bayu.kosman.interfaces.VolleyCallback;
import com.example.bayu.kosman.views.MenuActivity;

import org.json.JSONException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BuildingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BuildingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuildingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SharedPreferences mySharedPreferences;
    BuildingsAdapter Buildings;
    RecyclerView recyler;
    LinearLayoutManager linearLayoutManager;
    private OnFragmentInteractionListener mListener;

    public BuildingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuildingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuildingFragment newInstance(String param1, String param2) {
        BuildingFragment fragment = new BuildingFragment();
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

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mySharedPreferences = this.getActivity().getSharedPreferences(Static.MY_PREFS, Static.prefMode);
        String ud = mySharedPreferences.getString("userId","loading");
        Owners owner = new Owners(getContext());
        final View view = inflater.inflate(R.layout.activity_menu, container, false);
        recyler =(RecyclerView)view.findViewById(R.id.my_Recycler);
        final FragmentActivity c = getActivity();
        Toast.makeText(c, R.id.my_Recycler, Toast.LENGTH_SHORT).show();
        linearLayoutManager = new LinearLayoutManager(c);
        //recyler.setLayoutManager(linearLayoutManager);
        owner.buildingList(ud, new VolleyCallback() {

            @Override
            public void onSuccess(String result) throws JSONException {

                Buildings = new BuildingsAdapter(getContext(),result);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        c.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                //recyler.setAdapter(Buildings);
                                Toast.makeText(c, "succ", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();

            }

            @Override
            public void onErr(VolleyError result) throws JSONException {
                Toast.makeText(c, "new succ", Toast.LENGTH_SHORT).show();
            }
        });


        return inflater.inflate(R.layout.fragment_building, container, false);
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
