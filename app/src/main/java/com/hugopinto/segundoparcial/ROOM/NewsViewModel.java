package com.hugopinto.segundoparcial.ROOM;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.hugopinto.segundoparcial.APIs.News;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {
    private NewsRepository Repo;
    private LiveData<List<News>> mlista;
    private LiveData<List<News>> mlista2;



    public NewsViewModel(@NonNull Application application){
        super(application);
        Repo = new NewsRepository(application);
        mlista = Repo.getAllNews();
        mlista2 = Repo.getCSGONEWS();


    }

    public LiveData<List<News>> getAllNews(){
        return mlista;
    }
    public LiveData<List<News>> getCSGONEWS(){
        return mlista2;
    }

    public void fillnews(){
        Repo.FillAllNews();
    }



}
