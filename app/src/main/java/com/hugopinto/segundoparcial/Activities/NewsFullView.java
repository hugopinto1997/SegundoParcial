package com.hugopinto.segundoparcial.Activities;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hugopinto.segundoparcial.APIs.News;
import com.hugopinto.segundoparcial.R;
import com.squareup.picasso.Picasso;

public class NewsFullView extends AppCompatActivity {

    ImageView imagenfull;
    TextView Newsname, newsdesc, datenews, bnews, gname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_full_view);
        getSupportActionBar().hide();

           imagenfull = findViewById(R.id.imagefullview);
            Newsname = findViewById(R.id.titlenews);
            gname = findViewById(R.id.gamwnews);
            bnews = findViewById(R.id.bodynews);
            datenews = findViewById(R.id.fechanews);
            newsdesc = findViewById(R.id.dnews);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        final News rnews = (News) bundle.getSerializable("key");

        if (!(rnews.getCoverImage() == null)) {
            Picasso.with(getApplicationContext()).load(rnews.getCoverImage()).error(R.drawable.ic_padwhite).into(imagenfull);
        } else {
            Picasso.with(getApplicationContext()).load(R.drawable.ic_padwhite).error(R.drawable.ic_padwhite).into(imagenfull);
        }
        Newsname.setText(rnews.getTitle());
        newsdesc.setText(rnews.getDescription());
        gname.setText(rnews.getGame());
        bnews.setText(rnews.getBody());
        datenews.setText(rnews.getCreated_date());

        imagenfull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(v.getContext(), imageact.class);
                Bundle caja = new Bundle();
                caja.putSerializable("key3", rnews);
                newIntent.putExtras(caja);
                v.getContext().startActivity(newIntent);
            }
        });


    }
}
