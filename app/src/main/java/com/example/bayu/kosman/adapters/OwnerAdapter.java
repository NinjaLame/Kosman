package com.example.bayu.kosman.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by bayu on 03/12/17.
 */







public class OwnerAdapter extends RecyclerView.Adapter<OwnerAdapter.myOwnHolder> {




    public OwnerAdapter(){

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
