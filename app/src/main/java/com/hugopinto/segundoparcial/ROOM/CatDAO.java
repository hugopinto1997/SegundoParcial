package com.hugopinto.segundoparcial.ROOM;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CatDAO {
    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(NewsListEntity...newsListEntities);

    @Query("SELECT * FROM CATEGORY")
    LiveData<List<NewsListEntity>> getCategories();*/
}
