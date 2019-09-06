package com.example.quotescollector.SQLDatabase.DatabaseModel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Colour.class, parentColumns = "colourID",
        childColumns = "colourID"),
        indices = @Index(value = "colourID"))
public class Category {

    @PrimaryKey(autoGenerate = true)
    public int categoryID;

    public String categoryName;
    public int colourID;

    public Category(String categoryName, int colourID) {
        this.categoryName = categoryName;
        this.colourID = colourID;
    }
}
