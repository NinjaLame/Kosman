package com.example.bayu.kosman.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bayu.kosman.R;
import com.example.bayu.kosman.Static;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class   MainActivity extends AppCompatActivity {

    private EditText inp_pass;
    private EditText inp_email;
    SharedPreferences mySharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mySharedPreferences = getSharedPreferences(Static.MY_PREFS, Static.prefMode);
        super.onCreate(savedInstanceState);
        String id = mySharedPreferences.getString("access_token", null);
        if(id==null){

            setContentView(R.layout.activity_main);
            inp_email = (EditText)findViewById(R.id.inp_email);
            inp_pass = (EditText)findViewById(R.id.inp_password);

        }
        else {
            mySharedPreferences = getSharedPreferences(Static.MY_PREFS, Static.prefMode);
            Intent i=new Intent(MainActivity.this,MenuActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        }

    }

    public void Registry(View view) {
        Intent inten = new Intent(this,RegistryActivity.class);
        startActivity(inten);
    }

    public void Login(View view) {

        loginVal(inp_email,inp_pass);
    }

    public void loginVal(EditText inp_email, EditText inp_pass) {
        final String email = inp_email.getText().toString().trim();
        final String password = inp_pass.getText().toString().trim();
        final String[] id = {""};
        final String[] userId = {""};
        //Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, password, Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Static.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                        JSONObject object;
                        if(response.contains("id")){
                            //Toast.makeText(MainActivity.this, "sukses", Toast.LENGTH_SHORT).show();

                            try {
                                object = new JSONObject(response);
                                id[0] = object.getString("id");
                                userId[0] = object.getString("userId");
                                //Toast.makeText(MainActivity.this, id, Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor editor;
                                SharedPreferences mySharedPreferences = getSharedPreferences(Static.MY_PREFS, Static.prefMode); ;
                                editor = mySharedPreferences.edit();
                                editor.putString("access_token", id[0]);
                                editor.putString("userId", userId[0]);
                                editor.commit();

                                Owners(userId[0]);

                                //Toast.makeText(MainActivity.this, mySharedPreferences.getString("access_token",null), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this,MenuActivity.class));

                                finish();
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
                //Toast.makeText(LoginActivity.this, "The server unreachable", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Static.KEY_EMAIL, email);
                params.put(Static.KEY_PASSWORD, password);

                //returning parameter
                return params;
            }
        };
        //Adding the string request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
        //Toast.makeText(this, id[0], Toast.LENGTH_SHORT).show();
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
                                editor = mySharedPreferences.edit();
                                editor.putString("name", name[0]);
                                editor.putString("email", email[0]);
                                editor.putString("phone", phone[0]);
                                editor.putString("bankNumber", bankNumber[0]);
                                editor.commit();

                                //Toast.makeText(MainActivity.this, name[0], Toast.LENGTH_SHORT).show();
                                //Toast.makeText(MenuActivity.this, name[0], Toast.LENGTH_SHORT).show();
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
                //Toast.makeText(MenuActivity.this, "The server unreachable", Toast.LENGTH_LONG).show();
            }
        });
        //Adding the string request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

}