package com.hugopinto.segundoparcial.ROOM;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.hugopinto.segundoparcial.APIs.News;

import java.util.List;

@Dao
public interface NewsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNews(NewsEntity... news);

    @Query("SELECT * FROM Tabla_News")
    LiveData<List<NewsEntity>> getAllNews();
}
