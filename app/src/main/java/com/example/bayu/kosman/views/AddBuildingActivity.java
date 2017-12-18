package com.example.bayu.kosman.views;

import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.bayu.kosman.R;
import com.example.bayu.kosman.Static;
import com.example.bayu.kosman.api.Owners;

public class AddBuildingActivity extends AppCompatActivity {
    SharedPreferences mySharedPreferences;
    EditText bName,bAddress,bCapacity;
    String ud;
    Owners owner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_building);
        mySharedPreferences = getSharedPreferences(Static.MY_PREFS, Static.prefMode);
        ud= mySharedPreferences.getString("userId","loading");
        bName = (EditText)findViewById(R.id.bd_name);
        bAddress= (EditText)findViewById(R.id.bd_address);
        bCapacity = (EditText)findViewById(R.id.bd_capacity);
        owner = new Owners(this);
    }

    public void add_building(View view) {
        String name,address,capacity;
        name = bName.getText().toString();
        address = bAddress.getText().toString();
        capacity = bCapacity.getText().toString();
        owner.AddBuilding(ud,name,address,capacity);
    }
}
