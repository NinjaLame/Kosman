package com.example.bayu.kosman.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by bayu on 03/12/17.
 */







public class BuildingsAdapter extends RecyclerView.Adapter<BuildingsAdapter.myOwnHolder> {


    JSONArray BuildingArray;

    public BuildingsAdapter(String result) throws JSONException {
        try {
            this.BuildingArray = new JSONArray(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public myOwnHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(myOwnHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class myOwnHolder extends RecyclerView.ViewHolder{

        public myOwnHolder(View itemView) {
            super(itemView);
        }
    }
}
