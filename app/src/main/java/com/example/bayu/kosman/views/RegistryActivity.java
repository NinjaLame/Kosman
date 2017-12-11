package com.example.bayu.kosman.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewAnimator;

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

public class RegistryActivity extends AppCompatActivity {
    ViewAnimator regAnimator;
    EditText name,pass,email,phone;
    String sname,spass,semail,sphone;
    LinearLayout animator;
    boolean touchable=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        Toast.makeText(this, "Here Register", Toast.LENGTH_SHORT).show();
        regAnimator = (ViewAnimator)findViewById(R.id.registryAnimator);

        name=(EditText)findViewById(R.id.nameReg);
        pass=(EditText)findViewById(R.id.passwordReg);
        email=(EditText)findViewById(R.id.emailReg);
        phone=(EditText)findViewById(R.id.phoneReg);
        animator= (LinearLayout) findViewById(R.id.animator);

        animator.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()){
                    case 0:
                        touchable=true;
                        animator.setBackgroundColor(Color.rgb(255,220,91));

                        break;
                     case 1:
                        animator.setBackgroundColor(0);
                         break;

                }
                return true;
            }
        });
    }

    public void regBackBtn(View view) {
        regAnimator.setInAnimation(this,R.anim.back_slide_in);
        regAnimator.setOutAnimation(this,R.anim.back_slide_out);
       regAnimator.showPrevious();
    }

    public void regNextBtn(View view) {
        regAnimator.setInAnimation(this,R.anim.slide_in);
        regAnimator.setOutAnimation(this,R.anim.slide_out);
        regAnimator.showNext();
    }

    public void saveRegistry(View view) {


        sname = name.getText().toString() ;
        spass = pass.getText().toString();
        semail =email.getText().toString();
        sphone = phone.getText().toString();
        boolean isEmptyField = false;
        if(TextUtils.isEmpty(sname)){
            isEmptyField = true;
            name.setError("Field is Empty");
        }
        if(TextUtils.isEmpty(spass)){
            isEmptyField = true;
            pass.setError("Field is Empty");
        } if(TextUtils.isEmpty(semail)){
            isEmptyField = true;
            email.setError("Field is Empty");
        } if(TextUtils.isEmpty(sphone)){
            isEmptyField = true;
            phone.setError("Field is Empty");
        }

        if(!isEmptyField){
            Intent intent = new Intent(this,VerifRegActivity.class);
            intent.putExtra("name",sname);
            intent.putExtra("email",semail);
            intent.putExtra("password",spass);
            intent.putExtra("phone",sphone);
            register(name,email,pass,phone);
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }

    }
    public void register(EditText inp_nama, EditText inp_email, EditText inp_pass,EditText inp_phone) {
        final String email = inp_email.getText().toString().trim();
        final String password = inp_pass.getText().toString().trim();
        final String nama = inp_nama.getText().toString().trim();
        final String phone = inp_phone.getText().toString().trim();
        //Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, password, Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Static.REGIST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                        JSONObject object;
                        if (response.contains("name")) {
                            Toast.makeText(RegistryActivity.this, "sukses", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistryActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistryActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(LoginActivity.this, "The server unreachable", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Static.KEY_EMAIL, email);
                params.put(Static.KEY_PASSWORD, password);
                params.put(Static.KEY_NAME, nama);
                params.put(Static.KEY_PHONE, phone);

                //returning parameter
                return params;
            }
        };
        //Adding the string request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

}
