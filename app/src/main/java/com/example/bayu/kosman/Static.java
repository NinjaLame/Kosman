package com.example.bayu.kosman;

import android.app.Activity;

/**
 * Created by bayu on 08/12/17.
 */

public class Static {
    //URL to our login.php file, url bisa diganti sesuai dengan alamat server kita
    public static final String BASE_URL= "http://192.168.43.165:3001";
    public static final String LOGIN_URL = BASE_URL+"/api/Owners/login";
    public static final String REGIST_URL = BASE_URL+"/api/Owners";
    public static final String OWNER_URL= BASE_URL+"/api/Owners";
    public static final String BUILDING_URL = BASE_URL+"/api/Buildings";
    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_EMAIL = "email";
    public static String MY_PREFS = "MY_PREFS";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE = "phone";
    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";
    public static final int prefMode = Activity.MODE_PRIVATE;
}
