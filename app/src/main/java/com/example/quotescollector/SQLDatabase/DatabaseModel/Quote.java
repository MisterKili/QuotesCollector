package com.example.quotescollector.SQLDatabase.DatabaseModel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Quote {

    @PrimaryKey
    public int quoteID;

    public String quote;
    public String description;



}
