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

public class NewsViewModel extends AndroidViewModel {
    private NewsRepository Repo;
    private LiveData<List<News>> mlista;
    private LiveData<List<News>> mlista2;
    private LiveData<List<News>> mlista4;
    private LiveData<List<player>> mlistaplayers;
    private LiveData<List<String>> list4;
    private Application application;




    public NewsViewModel(@NonNull Application application){
        super(application);
        this.application = application;
        Repo = new NewsRepository(application);
        mlista = Repo.getAllNews();
        mlista2 = Repo.getNewsByCat();
        mlistaplayers = Repo.getPlayers();
        list4 = Repo.getGames();
    }

    public LiveData<List<News>> getAllNews(){
        return mlista;
    }
    public LiveData<List<News>> getNewsByCat(){
        return mlista2;
    }

    public LiveData<List<player>> getPlayers(){
        return mlistaplayers;
    }
    public LiveData<List<String>> getGames(){
        return list4;
    }
    public void fillnews(){
        Repo.FillAllNews();
    }


}
