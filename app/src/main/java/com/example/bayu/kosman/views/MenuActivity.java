package com.example.bayu.kosman.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.bayu.kosman.Fragment.BuildingFragment;
import com.example.bayu.kosman.Fragment.BuildingListFragment;
import com.example.bayu.kosman.Fragment.HomeFragment;
import com.example.bayu.kosman.Fragment.SettingFragment;
import com.example.bayu.kosman.Fragment.TestFragment;
import com.example.bayu.kosman.Fragment.dummy.DummyContent;
import com.example.bayu.kosman.R;
import com.example.bayu.kosman.Static;
import com.example.bayu.kosman.adapters.BuildingsAdapter;
import com.example.bayu.kosman.api.Owners;
import com.example.bayu.kosman.interfaces.VolleyCallback;

import org.json.JSONException;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
        ,TestFragment.OnFragmentInteractionListener
        ,BuildingFragment.OnFragmentInteractionListener
        ,BuildingListFragment.OnListFragmentInteractionListener
        ,HomeFragment.OnFragmentInteractionListener
        ,SettingFragment.OnFragmentInteractionListener
{

    private SharedPreferences mySharedPreferences;
    public String email ;
    public String name ;
    TextView headName;
    TextView headEmail;
    BuildingsAdapter Buildings;
    RecyclerView recyler;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mySharedPreferences = getSharedPreferences(Static.MY_PREFS, Static.prefMode);
        String ud = mySharedPreferences.getString("userId","loading");
        Owners owner = new Owners(this);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.myFrame,new BuildingListFragment())
                .commit();



    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        Log.i("Log","back pressed");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu, menu);
        for (int i = 0; i < menu.size(); i++)
            menu.getItem(i).setVisible(false);

        String userId = mySharedPreferences.getString("userId",null);


        headName = (TextView)findViewById(R.id.nav_head_name);
        email= mySharedPreferences.getString("email","Loading");
        name = mySharedPreferences.getString("name", "Loading");
        headEmail = (TextView)findViewById(R.id.nav_head_email);
        headEmail.setText(email);
        headName.setText(name);




        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.i("Log","On selected");
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.nav_building) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.myFrame,new BuildingListFragment())
                    .commit();
            //Toast.makeText(this, "Nav BUilding", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.nav_member){
//            Intent i= new Intent(MenuActivity.this,MemberActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//            startActivity(i);
            getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.myFrame,new TestFragment())
                    .commit();
        }
        else if (id == R.id.nav_logout) {
            SharedPreferences.Editor editor;
            editor = mySharedPreferences.edit();

            editor.clear();
            editor.commit();
            finish();
            Intent i = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
        else if(id == R.id.nav_manage){
            getSupportFragmentManager().beginTransaction()
                    .setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                    .replace(R.id.myFrame, new SettingFragment())
                    .commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
