package com.example.quotescollector.SQLDatabase.DatabaseModel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys =
        {@ForeignKey(entity = Author.class, parentColumns = "authorID",
                childColumns = "authorID"),
        @ForeignKey(entity = Source.class, parentColumns = "sourceID", childColumns = "sourceID")},
        indices = {@Index(value = "authorID"), @Index(value = "sourceID")})
public class Quote {

    @PrimaryKey(autoGenerate = true)
    public int quoteID;

    public String quote;
    public String description;

    public int authorID;
    public int sourceID;

    public Quote(String quote, String description, int authorID, int sourceID) {
        this.quote = quote;
        this.description = description;
        this.authorID = authorID;
        this.sourceID = sourceID;
    }
}
