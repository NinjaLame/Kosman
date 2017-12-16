package com.example.bayu.kosman.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bayu.kosman.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bayu on 03/12/17.
 */







public class BuildingsAdapter extends RecyclerView.Adapter<BuildingsAdapter.myOwnHolder> {


    JSONArray BuildingArray;

    Context context;

    public BuildingsAdapter(Context ctx, String result) throws JSONException {
        try {
            this.context = ctx;
            this.BuildingArray = new JSONArray(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public myOwnHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater myInflater = LayoutInflater.from(context);
        View myOwnView = myInflater.inflate(R.layout.buildings_row,parent,false);
        return new myOwnHolder(myOwnView);
    }

    @Override
    public void onBindViewHolder(myOwnHolder holder, int position) {
        try {
            JSONObject obj = BuildingArray.getJSONObject(position);
            String name = obj.getString("name");
            String address = obj.getString("address");
            holder.Bname.setText(name);
            holder.Baddress.setText(address);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return BuildingArray.length();
    }

    public class myOwnHolder extends RecyclerView.ViewHolder{
        TextView Bname,Baddress;
        public LinearLayout linearLayout;
        public myOwnHolder(View itemView) {
            super(itemView);
            Bname = (TextView)itemView.findViewById(R.id.building_name);
            Baddress = (TextView)itemView.findViewById(R.id.building_address);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.buildings_row);
        }
    }
}
