package com.hugopinto.segundoparcial.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hugopinto.segundoparcial.R;

public class Settings extends AppCompatActivity {

    private SharedPreferences prefs;

    ImageView imgbtn;
    TextView txtvw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Settings");

        imgbtn=findViewById(R.id.logoutimg);
        txtvw = findViewById(R.id.logouttext);

        prefs = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        txtvw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });



    }

    private void logout(){
        prefs.edit().clear().apply();
        Intent intent = new Intent(this,Login.class);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
