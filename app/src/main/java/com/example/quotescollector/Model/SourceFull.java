package com.example.quotescollector.Model;


public class SourceFull {

    public String sourceTitle;
    public String sourceTypeName;
    public int sourceID;

    public SourceFull(){}


//    public SourceFull(String sourceTitle, String sourceTypeName) {
//        this.sourceTitle = sourceTitle;
//        this.sourceTypeName = sourceTypeName;
//    }
    public SourceFull(String sourceTitle, String sourceTypeName, int sourceID) {
        this.sourceTitle = sourceTitle;
        this.sourceTypeName = sourceTypeName;
        this.sourceID = sourceID;
    }

//    @Override
//    public String toString(){
//        return sourceTitle + " (" + sourceTypeName + ")";
//    }

    @Override
    public String toString(){
        return sourceTitle + " (" + sourceTypeName + ")";
    }

}
