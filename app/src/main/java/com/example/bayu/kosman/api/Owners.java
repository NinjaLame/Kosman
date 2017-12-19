package com.example.bayu.kosman.api;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bayu.kosman.Static;
import com.example.bayu.kosman.interfaces.VolleyCallback;
import com.example.bayu.kosman.views.MainActivity;
import com.example.bayu.kosman.views.MenuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.support.v4.content.ContextCompat.startActivity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by bayu on 12/12/17.
 */

public class Owners  {

    public SharedPreferences mySharedPreferences;
    Context context;

    public Owners(Context ctx){
        this.context = ctx;
        mySharedPreferences = context.getSharedPreferences(Static.MY_PREFS, Static.prefMode);
    }


    public void buildingList(String userId, final VolleyCallback callback){
        final String access_token = mySharedPreferences.getString("access_token", null);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Static.OWNER_URL + "/" + userId+"/" + "buildings?access_token=" + access_token,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            callback.onSuccess(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    callback.onErr(error);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Volley.newRequestQueue(context).add(stringRequest);

    }


    public void OwnerData(String userId) {
        final String[] email = new String[1];
        final String[] name = new String[1];
        final String[] phone = new String[1];
        final String[] bankNumber = new String[1];


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
        Volley.newRequestQueue(context).add(stringRequest);
    }

    public void AddBuilding(String userId, final String bName, final String bAddress, final String bCapacity){
        final String access_token = mySharedPreferences.getString("access_token", null);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Static.OWNER_URL + "/" + userId + "/buildings?access_token=" + access_token,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put("name", bName);
                params.put("address", bAddress);
                params.put("capacity", bCapacity);

                //returning parameter
                return params;
            }
        };
        Volley.newRequestQueue(context).add(stringRequest);
    }
}
