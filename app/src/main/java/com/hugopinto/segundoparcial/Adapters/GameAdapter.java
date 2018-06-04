package com.hugopinto.segundoparcial.Adapters;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

import com.hugopinto.segundoparcial.Classes.Game;
import com.hugopinto.segundoparcial.R;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GamesViewHolder> {

    private List<Game> games;


    public GameAdapter(List<Game> games) {
        this.games = games;
    }

    public static class GamesViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView title,description;
        ImageView img;
        Context cxt;




        public GamesViewHolder(View itemView){
            super(itemView);
            card=itemView.findViewById(R.id.card_view);
            title=itemView.findViewById(R.id.newstitle);
            description=itemView.findViewById(R.id.newsdesc);
            img=itemView.findViewById(R.id.img);



        }

    }



    @Override
    public GamesViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return (new GamesViewHolder(v));
    }

    @Override
    public void onBindViewHolder(GamesViewHolder holder, final int position){
        holder.title.setText(games.get(position).getGamename());
        holder.img.setImageResource(games.get(position).getGameimg());
        holder.description.setText(games.get(position).getGamedesc());
    }


    @Override
    public int getItemCount(){
        return games.size();
    }
}
