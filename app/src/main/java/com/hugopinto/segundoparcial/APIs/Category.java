package com.hugopinto.segundoparcial.APIs;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "category")
public class Category {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "category")
    private String categoria;

    public Category(){
    }


    @NonNull
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(@NonNull String categoria) {
        this.categoria = categoria;
    }
}
