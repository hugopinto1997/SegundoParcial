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

    public NewsViewModel(@NonNull Application application){
        super(application);
        Repo = new NewsRepository(application);
        mlista = Repo.getAllNews();
    }

    public LiveData<List<News>> getAllNews(){
        return mlista;
    }

    public void fillnews(){
        Repo.FillAllNews();
    }



}
