package com.example.quotescollector.SQLDatabase.DatabaseModel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Colour.class, parentColumns = "colourID",
        childColumns = "colourID"))
public class Category {

    @PrimaryKey
    public int categoryID;

    public String categoryName;
    public int colourID;
}
