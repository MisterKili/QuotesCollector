package com.example.quotescollector.SQLDatabase.DatabaseModel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class SourceType {

    @PrimaryKey
    public int sourceTypeID;

    public String sourceTypeName;
}
