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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hugopinto.segundoparcial.APIs.News;
import com.hugopinto.segundoparcial.APIs.player;
import com.hugopinto.segundoparcial.Activities.NewsFullView;
import com.hugopinto.segundoparcial.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder> {

    private ArrayList<player> players;
    private Context ctx;
    private Context context;

    public static class PlayersViewHolder extends RecyclerView.ViewHolder {
        CardView cardp;
        TextView titlep,descriptionp;
        ImageView imgp;
        Context ctx;



        public PlayersViewHolder(View itemView){
            super(itemView);
            cardp=itemView.findViewById(R.id.cardviewplayer);
            titlep=itemView.findViewById(R.id.topname);
            descriptionp=itemView.findViewById(R.id.topdesc);
            imgp=itemView.findViewById(R.id.playeravatar);
            ctx= itemView.getContext();


        }

    }
    public PlayersAdapter(ArrayList<player> players, Context context) {
        this.players = players;
        this.context = context;

    }



    @Override
    public PlayersAdapter.PlayersViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_cardview,parent,false);
        return (new PlayersAdapter.PlayersViewHolder(v));
    }

    @Override
    public void onBindViewHolder(PlayersViewHolder holder, final int position) {

        final player jugador = players.get(position);
        holder.titlep.setText(jugador.getName());
        holder.descriptionp.setText(jugador.getBiografia());
        if (!(jugador.getAvatar() == null)) {
            Picasso.with(holder.ctx).load(jugador.getAvatar()).error(R.drawable.ic_gamepad).into(holder.imgp);
        } else {
            Picasso.with(holder.ctx).load(R.drawable.ic_gamepad).error(R.drawable.ic_gamepad).into(holder.imgp);
        }
        holder.cardp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),players.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount(){
        return players.size();
    }
}
