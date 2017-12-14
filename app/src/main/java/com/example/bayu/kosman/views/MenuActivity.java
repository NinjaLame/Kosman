package com.example.bayu.kosman.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bayu.kosman.R;
import com.example.bayu.kosman.Static;

import org.json.JSONException;
import org.json.JSONObject;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences mySharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mySharedPreferences = getSharedPreferences(Static.MY_PREFS, Static.prefMode);
        TextView temp = (TextView)findViewById(R.id.name);

        String userId = mySharedPreferences.getString("userId",null);
        temp.setText(userId);


        //headEmail.setText(email);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        TextView headName = (TextView)findViewById(R.id.nav_head_name);
        TextView headEmail = (TextView)findViewById(R.id.nav_head_email);
        String email = mySharedPreferences.getString("email",null);
        String name = mySharedPreferences.getString("name", null);
        headName.setText(name);
        headEmail.setText(email);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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

        if (id == R.id.nav_manage) {

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void buildingList(){

    }


    public void Owners(String userId) {
        final String[] email = new String[1];
        final String[] name = new String[1];
        final String[] phone = new String[1];
        final String[] bankNumber = new String[1];
        mySharedPreferences = getSharedPreferences(Static.MY_PREFS, Static.prefMode);

        final String access_token = mySharedPreferences.getString("access_token", null);
        //Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, password, Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Static.OWNER_URL+"/"+userId+"?access_token="+access_token,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                        JSONObject object;
                        if(response.contains("id")){
                            //Toast.makeText(MainActivity.this, "sukses", Toast.LENGTH_SHORT).show();

                            try {
                                object = new JSONObject(response);
                                name[0] = object.getString("name");
                                email[0] = object.getString("email");
                                phone[0] = object.getString("phone");
                                bankNumber[0] = object.getString("bankNumber");
                                //Toast.makeText(MainActivity.this, id, Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor editor;
                                SharedPreferences mySharedPreferences = getSharedPreferences(Static.MY_PREFS, Static.prefMode); ;
                                editor = mySharedPreferences.edit();
                                editor.putString("name", name[0]);
                                editor.putString("email", email[0]);
                                editor.putString("phone", phone[0]);
                                editor.putString("bankNumber", bankNumber[0]);
                                editor.commit();
                                Toast.makeText(MenuActivity.this, name[0], Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else{

                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MenuActivity.this, "The server unreachable", Toast.LENGTH_LONG).show();
            }
        });
        //Adding the string request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

}
