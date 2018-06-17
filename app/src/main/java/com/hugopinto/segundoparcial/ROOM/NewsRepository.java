package com.hugopinto.segundoparcial.ROOM;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hugopinto.segundoparcial.APIs.GameNewsAPI;
import com.hugopinto.segundoparcial.APIs.News;
import com.hugopinto.segundoparcial.APIs.player;
import com.hugopinto.segundoparcial.Activities.Login;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsRepository {

    private String juegazo;
    private String TokenAccess;
    private LiveData<List<News>> lista;
    private LiveData<List<News>> listacsgo;
    private LiveData<List<String>> Game;
    private LiveData<List<player>> listaplayer;
    private Application applicationc;



    private NewsDAO mNewsDAO;
    private PlayersDAO mPlayersDAO;

    private  SharedPreferences sp,sp2;





    public NewsRepository(Application application){
        NewsDB database = NewsDB.getAppDataBase(application);
        mNewsDAO= database.newsDAO();
        mPlayersDAO = database.playersDAO();
        applicationc = application;
        sp = application.getSharedPreferences("Preferencias",Context.MODE_PRIVATE);
        TokenAccess = sp.getString("Token","");
        SharedPreferences jgs = application.getSharedPreferences("Juego", Context.MODE_PRIVATE);
        juegazo=  jgs.getString("Juegos","");
        FillAllNews();
        FillAllPlayers();
        lista = mNewsDAO.getAllNews();
        Game = mNewsDAO.getGame();
        listacsgo = mNewsDAO.getNewsByCat(juegazo);
        listaplayer = mPlayersDAO.getPlayers(juegazo);


    }
    public void FillAllNews(){
        new FNews(TokenAccess,mNewsDAO, applicationc).execute();
    }
    public void FillAllPlayers(){
        new FPlayers(TokenAccess,mPlayersDAO, applicationc).execute();
    }



    public LiveData<List<News>> getAllNews(){
        return lista;
    }
    public LiveData<List<News>> getNewsByCat(){
        return listacsgo;
    }

    public LiveData<List<player>> getPlayers(){
        return listaplayer;
    }
    public LiveData<List<String>> getGames() {
        return Game;
    }


    private static class AsyncTaskI extends AsyncTask<ArrayList<News>,Void,Void>{

        private NewsDAO newsDao;

        AsyncTaskI(NewsDAO newsDao){
            this.newsDao= newsDao;
        }

        @Override
        protected Void doInBackground(ArrayList<News>... arrayLists) {
            ArrayList<News> Not = arrayLists[0];
            for(int e=0; e<Not.size(); e++){
                News news = Not.get(e);
                newsDao.insertNews(news);
            }
            return null;
        }


    }

    private static class FNews extends AsyncTask<Void,Void,Void> {

        private String TokAcces;
        private NewsDAO mnDAO;
        private Application a;
        private SharedPreferences shared;

        public FNews(String token,NewsDAO nwsDAO,Application application){
            this.TokAcces= token;
            this.mnDAO= nwsDAO;
            this.a = application;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(GameNewsAPI.ENDPOINT).addConverterFactory(GsonConverterFactory.create(new Gson())).build();
            GameNewsAPI GNAPI = retrofit.create(GameNewsAPI.class);
            Call<ArrayList<News>> news = GNAPI.getNews("Beared " + TokAcces);
            news.enqueue(new Callback<ArrayList<News>>() {


                @Override
                public void onResponse(Call<ArrayList<News>> call, Response<ArrayList<News>> response) {
                    if(response.isSuccessful()){
                        ArrayList<News> newarray = (ArrayList<News>) response.body();
                        new AsyncTaskI(mnDAO).execute(newarray);
                    }else {
                        shared= a.getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
                        shared.edit().clear().apply();
                        Toast.makeText(a,response.code()+"", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(a,Login.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                        Toast.makeText(a, response.code()+""+"Sesion expirada", Toast.LENGTH_SHORT).show();
                        a.startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<News>> call, Throwable t) {
                    Toast.makeText(a,"No hay conexion a internet",Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        }
    }

    private static class AsyncTaskI2 extends AsyncTask<ArrayList<player>,Void,Void>{

        private PlayersDAO pDao;

        AsyncTaskI2(PlayersDAO pDao){
            this.pDao= pDao;
        }

        @Override
        protected Void doInBackground(ArrayList<player>... arrayLists) {
            ArrayList<player> Not = arrayLists[0];
            for(int e=0; e<Not.size(); e++){
                player p = Not.get(e);
                pDao.insertPlayer(p);
            }
            return null;
        }


    }
    private static class FPlayers extends AsyncTask<Void,Void,Void> {

        private String TokAcces;
        private PlayersDAO plysDAO;
        private static Application contexto;

        public FPlayers(String token,PlayersDAO psDAO, Application contexto){
            this.TokAcces= token;
            this.plysDAO= psDAO;
            this.contexto=contexto;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(GameNewsAPI.ENDPOINT).addConverterFactory(GsonConverterFactory.create(new Gson())).build();
            GameNewsAPI GNAPI = retrofit.create(GameNewsAPI.class);
            Call<ArrayList<player>> news = GNAPI.getPlayers("Beared " + TokAcces);
            news.enqueue(new Callback<ArrayList<player>>() {


                @Override
                public void onResponse(Call<ArrayList<player>> call, Response<ArrayList<player>> response) {
                    if(response.isSuccessful()){
                        ArrayList<player> newarray = (ArrayList<player>) response.body();
                        new AsyncTaskI2(plysDAO).execute(newarray);

                    }else {
                        Toast.makeText(contexto,response.code()+"", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<ArrayList<player>> call, Throwable t) {
                    Toast.makeText(contexto,"No hay conexion a internet",Toast.LENGTH_SHORT).show();

                }
            });
            return null;
        }
    }

    /*private static class AsyncTaskI3 extends AsyncTask<ArrayList<Category>,Void,Void>{

        private CatDAO cDao;

        AsyncTaskI3(CatDAO cDao){
            this.cDao= cDao;
        }

        @Override
        protected Void doInBackground(ArrayList<Category>... arrayLists) {
            ArrayList<Category> Not = arrayLists[0];
            for(int e=0; e<Not.size(); e++){
                Category p = Not.get(e);
                cDao.insertCategory(p);
            }
            return null;
        }


    }
    private static class FCats extends AsyncTask<Void,Void,Void> {

        private String TokAcces;
        private CatDAO ctsDAO;
        private static Application contexto;

        public FCats(String token,CatDAO csDAO){
            this.TokAcces= token;
            this.ctsDAO= csDAO;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(GameNewsAPI.ENDPOINT).addConverterFactory(GsonConverterFactory.create(new Gson())).build();
            GameNewsAPI GNAPI = retrofit.create(GameNewsAPI.class);
            Call<ArrayList<Category>> news = GNAPI.getCategoria("Beared " + TokAcces);
            news.enqueue(new Callback<ArrayList<Category>>() {


                @Override
                public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                    if(response.isSuccessful()){
                        ArrayList<Category> newarray = (ArrayList<Category>) response.body();
                        new AsyncTaskI2(plysDAO).execute(newarray);

                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                    System.out.println("on failure");
                }
            });
            return null;
        }
    }*/

}
