package com.hugopinto.segundoparcial.ROOM;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Tabla_News")
public class NewsEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "coverImage")
    private String coverImage;

    @ColumnInfo(name = "createdDate")
    private String createddDate;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "body")
    private String body;

    @ColumnInfo(name = "game")
    private String game;

    public NewsEntity(@NonNull String id, String title, String coverImage, String createddDate, String description, String body, String game) {
        this.id = id;
        this.title = title;
        this.coverImage = coverImage;
        this.createddDate = createddDate;
        this.description = description;
        this.body = body;
        this.game = game;
    }
}
