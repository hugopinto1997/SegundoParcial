package com.hugopinto.segundoparcial.ROOM;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.hugopinto.segundoparcial.APIs.News;
import com.hugopinto.segundoparcial.APIs.player;



@Database(entities = {News.class, player.class}, version = 2)
public abstract class NewsDB extends RoomDatabase {

   private static NewsDB INSTANCE;
   public abstract NewsDAO newsDAO();
    public abstract PlayersDAO playersDAO();



    public static NewsDB getAppDataBase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NewsDB.class, "news_database").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }




}
