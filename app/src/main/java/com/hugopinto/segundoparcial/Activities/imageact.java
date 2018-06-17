package com.hugopinto.segundoparcial.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;
import com.hugopinto.segundoparcial.APIs.News;
import com.hugopinto.segundoparcial.APIs.player;
import com.hugopinto.segundoparcial.R;
import com.squareup.picasso.Picasso;

public class imageact extends AppCompatActivity {

    PhotoView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageact);
        photo = findViewById(R.id.pv);


        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        final News rnews = (News) bundle.getSerializable("key3");

        if (!(rnews.getCoverImage() == null)) {
            Picasso.with(getApplicationContext()).load(rnews.getCoverImage()).error(R.drawable.ic_padwhite).into(photo);
        } else {
            Picasso.with(getApplicationContext()).load(R.drawable.ic_padwhite).error(R.drawable.ic_padwhite).into(photo);
        }

    }
}
