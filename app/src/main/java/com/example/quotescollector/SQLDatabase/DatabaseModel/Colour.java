package com.example.quotescollector.SQLDatabase.DatabaseModel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Colour {

    @PrimaryKey(autoGenerate = true)
    public int colourID;

    public String colour;

    public Colour(String colour) {
        this.colour = colour;
    }
}
