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

import com.example.bayu.kosman.R;
import com.example.bayu.kosman.Static;

public class AddBuildingActivity extends AppCompatActivity {
    SharedPreferences mySharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mySharedPreferences = getSharedPreferences(Static.MY_PREFS, Static.prefMode);
        String ud = mySharedPreferences.getString("userId","loading");
    }
}
