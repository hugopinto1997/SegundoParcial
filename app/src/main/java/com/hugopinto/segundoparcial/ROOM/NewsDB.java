package com.hugopinto.segundoparcial.ROOM;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import static java.time.chrono.MinguoChronology.INSTANCE;


@Database(entities = {NewsEntity.class}, version = 1)
public abstract class NewsDB {

   private static NewsDB INSTANCE;
   public abstract NewsDAO NewsDAO();

    static NewsDB getDatabase(final Context context) {

                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NewsDB.class, "news_database")
                            .allowMainThreadQueries()
                            .build();
                }
        return INSTANCE;

    }

}
