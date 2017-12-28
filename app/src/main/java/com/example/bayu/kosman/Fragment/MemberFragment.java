package com.example.bayu.kosman.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.bayu.kosman.R;
import com.example.bayu.kosman.Static;
import com.example.bayu.kosman.adapters.MembersAdapter;
import com.example.bayu.kosman.api.Members;
import com.example.bayu.kosman.api.Owners;
import com.example.bayu.kosman.api.andreItem;
import com.example.bayu.kosman.api.andreModel;
import com.example.bayu.kosman.interfaces.VolleyCallback;
import com.example.bayu.kosman.models.BuildingItem;
import com.example.bayu.kosman.models.BuildingModel;
import com.example.bayu.kosman.views.AddBuildingActivity;
import com.example.bayu.kosman.views.AddMemberActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_member, container, false);
        SharedPreferences mySharedPreferences = getContext().getSharedPreferences(Static.MY_PREFS, Static.prefMode);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerMemeber);
        String userId = mySharedPreferences.getString("userId",null);
        Members members = new Members(getContext());
        List<andreItem> andrelist = new ArrayList<>();
        andrelist.add(new andreItem("1","Andre"));
        final andreModel andre = new andreModel(andrelist);

        final List<BuildingItem> buildingList = new ArrayList<>();

        members.MemberList(userId, new VolleyCallback() {

            @Override
            public void onSuccess(String result) throws JSONException {

                JSONArray jsonArray = new JSONArray(result);
                JSONArray jsonArray1 = new JSONArray("[]");
                JSONArray jsonArray2 = new JSONArray("[]");
                    int i,j;
                    for(i=0;i<jsonArray.length();i++){
                        jsonArray1 = jsonArray.getJSONObject(i).getJSONArray("members");
                        String num = Integer.toString(jsonArray1.length());
                        buildingList.add(new BuildingItem(jsonArray.getJSONObject(i).getString("id"),
                                jsonArray.getJSONObject(i).getString("name")));
                        for(j=0;j<jsonArray1.length();j++){
                            jsonArray2.put(jsonArray1.getJSONObject(j));

                        }
                    }
                MembersAdapter m = new MembersAdapter(getContext(),jsonArray2.toString());
                recyclerView.setAdapter(m);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabMember);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(),AddMemberActivity.class);
                        BuildingModel buildingModel = new BuildingModel(buildingList);
                        intent.putExtra("data",buildingModel);
                        startActivity(intent);
                    }
                });

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
