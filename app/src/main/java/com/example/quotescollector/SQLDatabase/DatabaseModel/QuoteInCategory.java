package com.example.quotescollector.SQLDatabase.DatabaseModel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(primaryKeys = {"quoteID", "categoryID"},
        foreignKeys = {
                @ForeignKey(entity = Quote.class,
                        parentColumns = "quoteID",
                        childColumns = "quoteID"),
                @ForeignKey(entity = Category.class,
                        parentColumns = "categoryID",
                        childColumns = "categoryID")

        })
public class QuoteInCategory {

    public int quoteID;
    public int categoryID;
}
