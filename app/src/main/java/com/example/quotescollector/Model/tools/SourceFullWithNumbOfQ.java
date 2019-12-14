package com.example.quotescollector.Model.tools;

import com.example.quotescollector.Model.SourceFull;

public class SourceFullWithNumbOfQ extends SourceFull {

    public int numberOfQuotes;

    public SourceFullWithNumbOfQ(String sourceTitle, String sourceTypeName, int sourceID, int numberOfQuotes){
        super(sourceTitle, sourceTypeName, sourceID);
        this.numberOfQuotes = numberOfQuotes;
    }

}
