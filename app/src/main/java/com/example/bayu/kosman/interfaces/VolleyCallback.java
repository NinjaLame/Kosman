package com.example.bayu.kosman.interfaces;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Meda on 16/12/2017.
 */

public interface VolleyCallback {
    void onSuccess(String result) throws JSONException;
    void onErr(VolleyError result)throws JSONException;
}
