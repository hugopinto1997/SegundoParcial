package com.hugopinto.segundoparcial.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hugopinto.segundoparcial.APIs.News;
import com.hugopinto.segundoparcial.Fragments.GenericFragment;
import com.hugopinto.segundoparcial.Fragments.NewsFragment;
import com.hugopinto.segundoparcial.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public abstract class JuegosAdapter extends RecyclerView.Adapter<JuegosAdapter.JuegosViewHolder> {

    private ArrayList<String> news;
    public Context context;
    public Context ctx;

    public static class JuegosViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView text;
        Context ctx;



        public JuegosViewHolder(View itemView){
            super(itemView);
            card=itemView.findViewById(R.id.card_viewcat);
            text=itemView.findViewById(R.id.juegotitle);
            ctx= itemView.getContext();


        }

    }
    public JuegosAdapter(ArrayList<String> news, Context context) {
        this.news = news;
        this.context = context;

    }

    @Override
    public JuegosAdapter.JuegosViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewcat,parent,false);
        return (new JuegosAdapter.JuegosViewHolder(v));
    }

    @Override
    public void onBindViewHolder(JuegosAdapter.JuegosViewHolder holder, final int position) {

        final String noticia = news.get(position);
        if(noticia==null){
            holder.text.setText("Sin nombre");
        }else{
            holder.text.setText(noticia.toString());
        }
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                up(noticia);
            }
        });

    }
    public abstract void up (String nt);


    @Override
    public int getItemCount(){
        return news.size();
    }
}
