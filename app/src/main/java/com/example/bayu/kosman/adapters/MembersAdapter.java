package com.example.bayu.kosman.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bayu.kosman.R;
import com.example.bayu.kosman.views.MemberActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bayu on 27/12/17.
 */

public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.MyHolder> {
    Context context;
    JSONArray memberArray;
    public MembersAdapter(Context ctx,String result)throws JSONException{
        this.context = ctx;
        this.memberArray = new JSONArray(result);
        memberArray.getJSONObject(0).getJSONArray("members");
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater myInflater = LayoutInflater.from(context);
        View myOwnView = myInflater.inflate(R.layout.member_item,parent,false);
        return new MyHolder(myOwnView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        JSONObject jsonObject = memberArray.getJSONObject(position);
        String name = jsonObject.getString("name");
        holder.name.setText(name);
    }

    @Override
    public int getItemCount() {
        return memberArray.length();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        TextView name,phone;
        public LinearLayout linearLayout;
        public MyHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.member_name);
            phone = (TextView)itemView.findViewById(R.id.member_phone);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.item_member);
        }
    }
}
