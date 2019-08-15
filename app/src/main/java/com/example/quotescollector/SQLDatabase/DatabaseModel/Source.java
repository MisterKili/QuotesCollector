package com.example.quotescollector.SQLDatabase.DatabaseModel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = SourceType.class, parentColumns = "sourceTypeID",
        childColumns = "sourceID"))
public class Source {

    @PrimaryKey
    public int sourceID;

    public String sourceTitle;
    public int sourceTypeID;
}
