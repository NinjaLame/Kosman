package com.example.bayu.kosman;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewAnimator;

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

            startActivity(new Intent(this,MainActivity.class));
            finish();
        }

    }
}
