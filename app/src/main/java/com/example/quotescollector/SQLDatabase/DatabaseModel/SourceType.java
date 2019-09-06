package com.example.quotescollector.SQLDatabase.DatabaseModel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class SourceType {

    @PrimaryKey(autoGenerate = true)
    public int sourceTypeID;

    public String sourceTypeName;

    public SourceType(String sourceTypeName) {
        this.sourceTypeName = sourceTypeName;
    }

    @Override
    public String toString(){
        return sourceTypeID + " " + sourceTypeName;
    }
}
