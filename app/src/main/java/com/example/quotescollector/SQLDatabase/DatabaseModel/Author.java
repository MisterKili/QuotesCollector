package com.example.quotescollector.SQLDatabase.DatabaseModel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Author {

    @PrimaryKey(autoGenerate = true)
    public int authorID;

    public String authorName;

    public Author(String authorName) {
        this.authorName = authorName;
    }

    @NonNull
    @Override
    public String toString() {
        return authorName;
    }
}
