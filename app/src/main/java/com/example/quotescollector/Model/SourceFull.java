package com.example.quotescollector.Model;


public class SourceFull {

    public String sourceTitle;
    public String sourceTypeName;


    public SourceFull(String sourceTitle, String sourceTypeName) {
        this.sourceTitle = sourceTitle;
        this.sourceTypeName = sourceTypeName;
    }

    @Override
    public String toString(){
        return sourceTitle + " " + sourceTypeName;
    }
}
