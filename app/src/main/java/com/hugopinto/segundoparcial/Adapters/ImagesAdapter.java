package com.hugopinto.segundoparcial.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hugopinto.segundoparcial.APIs.News;
import com.hugopinto.segundoparcial.APIs.player;
import com.hugopinto.segundoparcial.Activities.NewsFullView;
import com.hugopinto.segundoparcial.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder> {

    private ArrayList<News> news;
    public Context context;
    public Context ctx;

    public static class ImagesViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        ImageView img;
        Context ctx;



        public ImagesViewHolder(View itemView){
            super(itemView);
            card=itemView.findViewById(R.id.cardviewimage);
            img=itemView.findViewById(R.id.imggame);
            ctx= itemView.getContext();


        }

    }
    public ImagesAdapter(ArrayList<News> news, Context context) {
        this.news = news;
        this.context = context;

    }

    @Override
    public ImagesAdapter.ImagesViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewimg,parent,false);
        return (new ImagesAdapter.ImagesViewHolder(v));
    }

    @Override
    public void onBindViewHolder(ImagesAdapter.ImagesViewHolder holder, final int position) {

        final News noticia = news.get(position);
        if (!(noticia.getCoverImage() == null)) {
            Picasso.with(holder.ctx).load(noticia.getCoverImage()).error(R.drawable.ic_gamepad).into(holder.img);
        } else {
            Picasso.with(holder.ctx).load(R.drawable.ic_gamepad).error(R.drawable.ic_gamepad).into(holder.img);
        }
    }


    @Override
    public int getItemCount(){
        return news.size();
    }
}
