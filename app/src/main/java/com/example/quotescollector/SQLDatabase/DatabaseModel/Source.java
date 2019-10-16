package com.example.quotescollector.SQLDatabase.DatabaseModel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = SourceType.class, parentColumns = "sourceTypeID",
        childColumns = "sourceID"))
public class Source {

    @PrimaryKey(autoGenerate = true)
    public int sourceID;

    public String sourceTitle;
    public int sourceTypeID;

    public Source(String sourceTitle, int sourceTypeID) {
        this.sourceTitle = sourceTitle;
        this.sourceTypeID = sourceTypeID;
    }

    @Ignore
    public Source(int sourceID, String sourceTitle, int sourceTypeID) {
        this.sourceID = sourceID;
        this.sourceTitle = sourceTitle;
        this.sourceTypeID = sourceTypeID;
    }

    @Override
    public String toString(){
        return sourceID + " " + sourceTitle + " " + sourceTypeID;
    }
}