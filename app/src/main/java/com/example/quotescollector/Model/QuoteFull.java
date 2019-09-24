package com.example.quotescollector.Model;


public class QuoteFull {

    public int quoteID;

    public String quote;
    public String description;

    public String authorName;
//    public SourceFull source;

    public QuoteFull(int quoteID, String quote, String description, String authorName) {
        this.quoteID = quoteID;
        this.quote = quote;
        this.description = description;
        this.authorName = authorName;
    }


    public String getAuthor(){
        return authorName;
    }

}
