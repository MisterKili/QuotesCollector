package com.example.quotescollector.SQLDatabase.DatabaseModel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys =
        {@ForeignKey(entity = Author.class, parentColumns = "authorID",
                childColumns = "authorID"),
        @ForeignKey(entity = Source.class, parentColumns = "sourceID", childColumns = "sourceID")})
public class Quote {

    @PrimaryKey
    public int quoteID;

    public String quote;
    public String description;

    public int authorID;
    public int sourceID;
}
