package com.example.bayu.kosman.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.bayu.kosman.R;
import com.example.bayu.kosman.Fragment.dummy.DummyContent;
import com.example.bayu.kosman.Fragment.dummy.DummyContent.DummyItem;
import com.example.bayu.kosman.Static;
import com.example.bayu.kosman.adapters.BuildingsAdapter;
import com.example.bayu.kosman.api.Owners;
import com.example.bayu.kosman.interfaces.VolleyCallback;
import com.example.bayu.kosman.views.AddBuildingActivity;
import com.example.bayu.kosman.views.MenuActivity;

import org.json.JSONException;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class BuildingListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private SharedPreferences mySharedPreferences;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BuildingListFragment() {

    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static BuildingListFragment newInstance(int columnCount) {
        BuildingListFragment fragment = new BuildingListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_building, container, false);
        mySharedPreferences = getContext().getSharedPreferences(Static.MY_PREFS, Static.prefMode);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.my_Recycler);
        Owners o = new Owners(getContext());
        String userId = mySharedPreferences.getString("userId",null);
        o.buildingList(userId, new VolleyCallback() {
            @Override
            public void onSuccess(String result) throws JSONException {
                BuildingsAdapter b = new BuildingsAdapter(getContext(),result);
                recyclerView.setAdapter(b);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            }

            @Override
            public void onErr(VolleyError result) throws JSONException {

            }
        });
        // Set the adapter

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),AddBuildingActivity.class));
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
