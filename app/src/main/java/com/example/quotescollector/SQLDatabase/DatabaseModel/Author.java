package com.example.quotescollector.SQLDatabase.DatabaseModel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Author {

    @PrimaryKey(autoGenerate = true)
    public int authorID;

    public String authorName;

    public Author(String authorName) {
        this.authorName = authorName;
    }
}
