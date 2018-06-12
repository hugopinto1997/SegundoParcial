package com.hugopinto.segundoparcial.ROOM;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.hugopinto.segundoparcial.APIs.GameNewsAPI;
import com.hugopinto.segundoparcial.APIs.News;

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

    private NewsDAO mNewsDAO;
    private NewsDAO mNewsDAO2;




    public NewsRepository(Application application){
        NewsDB database = NewsDB.getAppDataBase(application);
        mNewsDAO= database.newsDAO();
        mNewsDAO2= database.newsDAO();
        SharedPreferences sp = application.getSharedPreferences("Preferencias",Context.MODE_PRIVATE);
        TokenAccess = sp.getString("Token","");
        FillAllNews();
        FillAllNews2();
        lista = mNewsDAO.getAllNews();
        listacsgo = mNewsDAO2.getCSGONEWS();

    }
    public void FillAllNews(){
        new FNews(TokenAccess,mNewsDAO).execute();
    }
    public void FillAllNews2(){
        new FNews(TokenAccess,mNewsDAO2).execute();
    }


    public LiveData<List<News>> getAllNews(){
        return lista;
    }
    public LiveData<List<News>> getCSGONEWS(){
        return listacsgo;
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


}
