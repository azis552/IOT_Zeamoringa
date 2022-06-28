package com.example.zeamoringa1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView ph_tangki1,vol_tangki1,ntu_tangki1,tds_tangki1,status_power;
    TextView ph_tangki2,vol_tangki2,ntu_tangki2,tds_tangki2;
    Switch sw_power;
    DatabaseReference ref_tangki1,ref_tangki2,ref_tandon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sw_power = (Switch) findViewById(R.id.switch_power);
        sw_power.setChecked(false);
        status_power = (TextView) findViewById(R.id.status_power);
        ref_tandon = FirebaseDatabase.getInstance().getReference();
        sw_power.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    status_power.setText("ON");

                    ref_tandon.child("Pompa1").setValue(1);
                    sensor();
                }else{
                    ref_tandon.child("Pompa1").setValue(0);
                    status_power.setText("OFF");
                    ph_tangki1.setText("OFF");
                    vol_tangki1.setText("OFF");
                    ntu_tangki1.setText("OFF");
                    tds_tangki1.setText("OFF");
                    ph_tangki2.setText("OFF");
                    vol_tangki2.setText("OFF");
                    ntu_tangki2.setText("OFF");
                    tds_tangki2.setText("OFF");
                }
            }
        });
    }
    public void sensor(){
        ph_tangki1 = (TextView) findViewById(R.id.status_ph_1);
        vol_tangki1 = (TextView) findViewById(R.id.status_volume_air_1);
        ntu_tangki1 = (TextView) findViewById(R.id.status_ntu_1);
        tds_tangki1 = (TextView) findViewById(R.id.status_tds_1);
        ph_tangki2 = (TextView) findViewById(R.id.status_ph_2);
        vol_tangki2 = (TextView) findViewById(R.id.status_volume_air_2);
        ntu_tangki2= (TextView) findViewById(R.id.status_ntu_2);
        tds_tangki2 = (TextView) findViewById(R.id.status_tds_2);
        ref_tangki1 = FirebaseDatabase.getInstance().getReference().child("Tandon 1");
        ref_tangki1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String ph_1 = snapshot.child("pH_1").getValue().toString();
                String vol_1 = snapshot.child("Volume_1").getValue().toString();
                String ntu_1 = snapshot.child("Turbidity_1").getValue().toString();
                String tds_1 = snapshot.child("TDS_1").getValue().toString();
                ph_tangki1.setText(ph_1);
                vol_tangki1.setText(vol_1+" %");
                ntu_tangki1.setText(ntu_1+" NTU");
                tds_tangki1.setText(tds_1+" PPM");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ref_tangki2 = FirebaseDatabase.getInstance().getReference().child("Tandon 2");
        ref_tangki2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String ph_2 = snapshot.child("pH_2").getValue().toString();
                String vol_2 = snapshot.child("Volume_2").getValue().toString();
                String ntu_2 = snapshot.child("Turbidity_2").getValue().toString();
                String tds_2 = snapshot.child("TDS_2").getValue().toString();
                ph_tangki2.setText(ph_2);
                vol_tangki2.setText(vol_2+" %");
                ntu_tangki2.setText(ntu_2+" NTU");
                tds_tangki2.setText(tds_2+" PPM");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

