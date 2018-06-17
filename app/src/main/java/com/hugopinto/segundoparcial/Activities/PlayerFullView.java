package com.hugopinto.segundoparcial.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.hugopinto.segundoparcial.APIs.player;
import com.hugopinto.segundoparcial.R;
import com.squareup.picasso.Picasso;



public class PlayerFullView extends AppCompatActivity {


    ImageView imagenfull2;
    TextView Newsname2, newsdesc2, datenews2, bnews2, gname2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_full_view);
        getSupportActionBar().hide();
        imagenfull2 = findViewById(R.id.imagefullview2);
        Newsname2 = findViewById(R.id.playername);
        bnews2 = findViewById(R.id.playerbio);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        final player rnews = (player) bundle.getSerializable("key2");

        if (!(rnews.getAvatar() == null)) {
            Picasso.with(getApplicationContext()).load(rnews.getAvatar()).error(R.drawable.ic_padwhite).into(imagenfull2);
        } else {
            Picasso.with(getApplicationContext()).load(R.drawable.ic_padwhite).error(R.drawable.ic_padwhite).into(imagenfull2);
        }
        Newsname2.setText(rnews.getName());
        bnews2.setText(rnews.getBiografia());

    }
}
