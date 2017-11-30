package com.example.bayu.kosman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class VerifRegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verif_reg);
        Intent intent = getIntent();
        Toast.makeText(this, intent.getStringExtra("password"), Toast.LENGTH_SHORT).show();
    }
}
