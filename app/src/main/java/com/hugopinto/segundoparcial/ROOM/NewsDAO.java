package com.hugopinto.segundoparcial.ROOM;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.content.Context;

import com.hugopinto.segundoparcial.APIs.News;

import java.util.List;

@Dao
public interface NewsDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNews(News... news);


    //@Query("SELECT * FROM Tabla_News WHERE game like '%csgo%' ")

    @Query("SELECT * FROM Tabla_News  ORDER BY createdDate DESC")
    LiveData<List<News>> getAllNews();

    @Query("SELECT DISTINCT Game FROM Tabla_News ORDER BY Game ASC ")
    LiveData<List<String>> getGame();

    @Query("SELECT * FROM Tabla_News WHERE game = :busc order by createdDate DESC")
    LiveData<List<News>> getNewsByCat(String busc);

}
