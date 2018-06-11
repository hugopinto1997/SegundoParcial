package com.hugopinto.segundoparcial.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.hugopinto.segundoparcial.APIs.News;
import com.hugopinto.segundoparcial.Activities.NewsFullView;
import com.hugopinto.segundoparcial.Classes.Game;
import com.hugopinto.segundoparcial.R;
import com.squareup.picasso.Picasso;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GamesViewHolder> {

    private ArrayList<News> news;
    public Context ctx;
    public Context context;





    public static class GamesViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView title,description;
        ImageView img;
        Context ctx;



        public GamesViewHolder(View itemView){
            super(itemView);
            card=itemView.findViewById(R.id.card_view);
            title=itemView.findViewById(R.id.newstitle);
            description=itemView.findViewById(R.id.newsdesc);
            img=itemView.findViewById(R.id.img);
            ctx= itemView.getContext();


        }

    }
    public GameAdapter(ArrayList<News> news, Context context) {
        this.news = news;
        this.context = context;

    }



    @Override
    public GamesViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return (new GamesViewHolder(v));
    }

    @Override
    public void onBindViewHolder(GamesViewHolder holder, final int position) {

        final News noticia = news.get(position);
        holder.title.setText(noticia.getTitle());
        holder.description.setText(noticia.getDescription());
        if (!(noticia.getCoverImage() == null)) {
            Picasso.with(holder.ctx).load(noticia.getCoverImage()).error(R.drawable.ic_gamepad).into(holder.img);
        } else {
            Picasso.with(holder.ctx).load(R.drawable.ic_gamepad).error(R.drawable.ic_gamepad).into(holder.img);
        }
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(context, NewsFullView.class);
                Bundle caja = new Bundle();
                caja.putSerializable("key", noticia);
                newIntent.putExtras(caja);
                context.startActivity(newIntent);
            }
        });
    }


    @Override
    public int getItemCount(){
        return news.size();
    }
}
