package com.example.bayu.kosman.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bayu.kosman.R;
import com.example.bayu.kosman.api.Owners;

public class BuildingDetailActivity extends AppCompatActivity {
    EditText bName,bAddress,bCapacity;
    Owners own;
    String id, name,address,capacity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_detail);
        Intent i = getIntent();
        name = i.getStringExtra("name").toString();
        address = i.getStringExtra("address").toString();
        capacity = i.getStringExtra("capacity").toString();
        id = i.getStringExtra("id").toString();
        bName = (EditText)findViewById(R.id.b_name);
        bAddress = (EditText)findViewById(R.id.b_address);
        bCapacity = (EditText)findViewById(R.id.b_capacity);
        own = new Owners(this);
        bName.setText(name);
        bAddress.setText(address);
        bCapacity.setText(capacity);
    }

    public void updateBuilding(View view) {
        name = bName.getText().toString();
        address = bAddress.getText().toString();
        capacity = bCapacity.getText().toString();
        own.UpdateBuilding(id,name,address,capacity);
    }
}
