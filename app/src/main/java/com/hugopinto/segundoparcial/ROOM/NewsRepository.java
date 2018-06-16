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
import com.hugopinto.segundoparcial.APIs.Category;
import com.hugopinto.segundoparcial.Activities.Login;

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
    private LiveData<List<player>> listaplayer2;
    private LiveData<List<player>> listaplayer3;
    private Application applicationc;



    private NewsDAO mNewsDAO;
    private NewsDAO mNewsDAO2;
    private NewsDAO mNewsDAO3;
    private NewsDAO mNewsDAO4;
    private PlayersDAO mPlayersDAO;
    //private CatDAO mCatDAO;

    private  SharedPreferences sp;





    public NewsRepository(Application application){
        NewsDB database = NewsDB.getAppDataBase(application);
        mNewsDAO= database.newsDAO();
        applicationc = application;
        mNewsDAO2= database.newsDAO();
        mNewsDAO3= database.newsDAO();
        mNewsDAO4= database.newsDAO();
        mPlayersDAO = database.playersDAO();
        //mCatDAO = database.catDAO();
        sp = application.getSharedPreferences("Preferencias",Context.MODE_PRIVATE);
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
        listaplayer = mPlayersDAO.getCSGOPlayers();
        listaplayer2 = mPlayersDAO.getLOLPlayers();
        listaplayer3 = mPlayersDAO.getOVERWATCHPlayers();


    }
    public void FillAllNews(){
        new FNews(TokenAccess,mNewsDAO, applicationc).execute();
    }
    public void FillAllNews2(){
        new FNews(TokenAccess,mNewsDAO2, applicationc).execute();
    }
    public void FillAllNews3(){
        new FNews(TokenAccess,mNewsDAO3, applicationc).execute();
    }
    public void FillAllNews4(){
        new FNews(TokenAccess,mNewsDAO4, applicationc).execute();
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
    public LiveData<List<player>> getLOLPlayers(){
        return listaplayer2;
    }
    public LiveData<List<player>> getCSGOPlayers(){
        return listaplayer;
    }
    public LiveData<List<player>> getOVERWATCHPlayers(){
        return listaplayer3;
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
                        Intent intent = new Intent(a,Login.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                        Toast.makeText(a, response.code()+""+"Sesion expirada", Toast.LENGTH_SHORT).show();
                        a.startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<News>> call, Throwable t) {
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
