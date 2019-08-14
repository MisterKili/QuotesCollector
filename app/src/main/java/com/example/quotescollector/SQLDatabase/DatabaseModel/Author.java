package com.example.quotescollector.SQLDatabase.DatabaseModel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Author {

    @PrimaryKey
    public int authorID;

    public String firstName;
    public String lastName;
}
