package com.hugopinto.segundoparcial.ROOM;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.hugopinto.segundoparcial.APIs.News;
import com.hugopinto.segundoparcial.APIs.player;

import java.util.List;

public class NewsViewModel extends AndroidViewModel{
    private NewsRepository Repo;
    private LiveData<List<News>> mlista;
    private LiveData<List<News>> mlista2;
    private LiveData<List<News>> mlista3;
    private String buscarpor;
    private LiveData<List<News>> mlista4;
    private LiveData<List<player>> mlistaplayers;
    private LiveData<List<player>> mlistaplayers2;
    private LiveData<List<player>> mlistaplayers3;
    private LiveData<List<String>> list4;
    private Application application;




    public NewsViewModel(@NonNull Application application, String buscarpor){
        super(application);
        this.application = application;
        this.buscarpor = buscarpor;
        Repo = new NewsRepository(application, buscarpor);
        mlista = Repo.getAllNews();
        mlista3 = Repo.getLOLNEWS();
        mlista4 = Repo.getOVERWATCHNEWS();
        mlistaplayers = Repo.getCSGOPlayers();
        mlistaplayers2 = Repo.getLOLPlayers();
        mlistaplayers3 = Repo.getOVERWATCHPlayers();
        list4 = Repo.getGames();



    }

    public LiveData<List<News>> getAllNews(){
        return mlista;
    }
    public LiveData<List<News>> getCSGONEWS(String game){
        return Repo.getCSGONEWS(game);
    }
    public LiveData<List<News>> getLOLNEWS(){
        return mlista3;
    }
    public LiveData<List<News>> getOVERWATCHNEWS(){
        return mlista4;
    }
    public LiveData<List<player>> getLOLPlayers(){
        return mlistaplayers2;
    }
    public LiveData<List<player>> getOVERWATCHPlayers(){
        return mlistaplayers3;
    }
    public LiveData<List<player>> getCSGOPlayers(){
        return mlistaplayers;
    }
    public LiveData<List<String>> getGames(){
        return list4;
    }
    public void fillnews(){
        Repo.FillAllNews();
    }



}
