package com.hugopinto.segundoparcial.ROOM;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.hugopinto.segundoparcial.APIs.GameNewsAPI;
import com.hugopinto.segundoparcial.APIs.News;
import com.hugopinto.segundoparcial.APIs.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsRepository {

    private String TokenAccess;
    private LiveData<List<News>> lista;
    private LiveData<List<News>> listacsgo;
    private LiveData<List<News>> listalol;
    private LiveData<List<News>> listaoverwatch;
    private LiveData<List<player>> listaplayer;


    private NewsDAO mNewsDAO;
    private NewsDAO mNewsDAO2;
    private NewsDAO mNewsDAO3;
    private NewsDAO mNewsDAO4;
    private PlayersDAO mPlayersDAO;





    public NewsRepository(Application application){
        NewsDB database = NewsDB.getAppDataBase(application);
        mNewsDAO= database.newsDAO();
        mNewsDAO2= database.newsDAO();
        mNewsDAO3= database.newsDAO();
        mNewsDAO4= database.newsDAO();
        mPlayersDAO = database.playersDAO();
        SharedPreferences sp = application.getSharedPreferences("Preferencias",Context.MODE_PRIVATE);
        TokenAccess = sp.getString("Token","");
        FillAllNews();
        FillAllNews2();
        FillAllNews3();
        FillAllNews4();
        FillAllPlayers();
        lista = mNewsDAO.getAllNews();
        listacsgo = mNewsDAO2.getCSGONEWS();
        listalol = mNewsDAO3.getLOLNEWS();
        listaoverwatch = mNewsDAO4.getOVERWATCHNEWS();
        listaplayer = mPlayersDAO.getAllPlayers();


    }
    public void FillAllNews(){
        new FNews(TokenAccess,mNewsDAO).execute();
    }
    public void FillAllNews2(){
        new FNews(TokenAccess,mNewsDAO2).execute();
    }
    public void FillAllNews3(){
        new FNews(TokenAccess,mNewsDAO3).execute();
    }
    public void FillAllNews4(){
        new FNews(TokenAccess,mNewsDAO4).execute();
    }
    public void FillAllPlayers(){
        new FPlayers(TokenAccess,mPlayersDAO).execute();
    }



    public LiveData<List<News>> getAllNews(){
        return lista;
    }
    public LiveData<List<News>> getCSGONEWS(){
        return listacsgo;
    }
    public LiveData<List<News>> getLOLNEWS(){
        return listalol;
    }
    public LiveData<List<News>> getOVERWATCHNEWS(){
        return listaoverwatch;
    }
    public LiveData<List<player>> getAllPlayers(){
        return listaplayer;
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

        public FNews(String token,NewsDAO nwsDAO){
            this.TokAcces= token;
            this.mnDAO= nwsDAO;
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
                        Collections.reverse(newarray);
                        new AsyncTaskI(mnDAO).execute(newarray);

                    }
                }

                @Override
                public void onFailure(Call<ArrayList<News>> call, Throwable t) {
                    System.out.println("on failure");
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

        public FPlayers(String token,PlayersDAO psDAO){
            this.TokAcces= token;
            this.plysDAO= psDAO;
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
                        Collections.reverse(newarray);
                        new AsyncTaskI2(plysDAO).execute(newarray);

                    }
                }

                @Override
                public void onFailure(Call<ArrayList<player>> call, Throwable t) {
                    System.out.println("on failure");
                }
            });
            return null;
        }
    }


}
