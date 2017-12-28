package com.example.bayu.kosman.api;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bayu.kosman.Static;
import com.example.bayu.kosman.interfaces.VolleyCallback;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bayu on 27/12/17.
 */

public class Members {
    public SharedPreferences mySharedPreferences;
    Context context;

    public Members(Context ctx){
        this.context = ctx;
        mySharedPreferences = context.getSharedPreferences(Static.MY_PREFS, Static.prefMode);
    }

    public void MemberList(String id, final VolleyCallback callback){
        final String access_token = mySharedPreferences.getString("access_token", null);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Static.OWNER_URL + "/" + id + "/buildings?filter[include]=members&&access_token=" + access_token,
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

            }
        });
        Volley.newRequestQueue(context).add(stringRequest);
    }
    public  void addMember(final String bid, final String name, final String email, final String phone){
        final String access_token = mySharedPreferences.getString("access_token", null);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Static.BUILDING_URL +"/"+ bid + "/members?access_token" + access_token,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Log","succ");
                        Toast.makeText(context, "Add Success", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Log","err");
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name",name);
                params.put("buildingId",bid);
                params.put("phone",phone);
                params.put("email",email);
                return params;
            }
        };
        Volley.newRequestQueue(context).add(stringRequest);
    }


}
