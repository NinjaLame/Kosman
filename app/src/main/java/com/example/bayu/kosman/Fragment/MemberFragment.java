package com.example.bayu.kosman.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.bayu.kosman.R;
import com.example.bayu.kosman.Static;
import com.example.bayu.kosman.api.Members;
import com.example.bayu.kosman.api.Owners;
import com.example.bayu.kosman.interfaces.VolleyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MemberFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MemberFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemberFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MemberFragment() {
        // Required empty public constructor
    }

    public static MemberFragment newInstance(String param1, String param2) {
        MemberFragment fragment = new MemberFragment();
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
        View view = inflater.inflate(R.layout.fragment_member, container, false);
        SharedPreferences mySharedPreferences = getContext().getSharedPreferences(Static.MY_PREFS, Static.prefMode);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.my_Recycler);
        Owners o = new Owners(getContext());
        String userId = mySharedPreferences.getString("userId",null);
        Members members = new Members(getContext());
        members.MemberList("1", new VolleyCallback() {

            @Override
            public void onSuccess(String result) throws JSONException {
//                try{
//
//
//
//                }catch (Exception e){
//
//                }
                JSONArray jsonArray = new JSONArray(result);
                JSONArray jsonArray1 = new JSONArray("[]");
                JSONArray jsonArray2 = new JSONArray("[]");
                    int i,j;
                    for(i=0;i<jsonArray.length();i++){
                        jsonArray1 = jsonArray.getJSONObject(i).getJSONArray("members");
                        String num = Integer.toString(jsonArray1.length());

                        for(j=0;j<jsonArray1.length();j++){
                            jsonArray2.put(jsonArray1.getJSONObject(j));

                        }
                    }
                Toast.makeText(getContext(), jsonArray2.toString(), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onErr(VolleyError result) throws JSONException {

            }
        });

        return view;
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
