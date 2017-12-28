package com.example.bayu.kosman.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bayu.kosman.R;
import com.example.bayu.kosman.api.Members;
import com.example.bayu.kosman.api.andreModel;
import com.example.bayu.kosman.models.BuildingItem;
import com.example.bayu.kosman.models.BuildingModel;

public class AddMemberActivity extends AppCompatActivity {
    String buildingId;
    Button addMember;
    EditText name,phone,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        Intent i = getIntent();
        name=findViewById(R.id.name_member);
        phone=findViewById(R.id.phone_member);
        email=findViewById(R.id.email_member);
        final BuildingModel andre = (BuildingModel) i.getSerializableExtra("data");
        Spinner spin = (Spinner)findViewById(R.id.building_spinner);
        ArrayAdapter<BuildingItem> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, andre.getBuildingList());
        spin.setAdapter(adapter);
        final Members members = new Members(this);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                buildingId= andre.getBuildingList().get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        addMember=(Button)findViewById(R.id.add_member);
        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                members.addMember(buildingId,name.getText().toString(),email.getText().toString(),phone.getText().toString());
            }
        });
    }

}
