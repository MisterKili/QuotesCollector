package com.example.quotescollector.Model;

import android.arch.persistence.room.Ignore;

public class QuoteFull {

    public int quoteID;

    public String quote;
    public String description;

    public String authorName;

    public int sourceID;

    @Ignore
    public SourceFull source;

    public QuoteFull(int quoteID, String quote, String description, String authorName, int sourceID) {
        this.quoteID = quoteID;
        this.quote = quote;
        this.description = description;
        this.authorName = authorName;
        this.sourceID = sourceID;
    }


    public String getAuthor(){
        return authorName;
    }


//    public SourceFull get

//    public Quote getQuoteObj(){
//        Quote q = new Quote(quote, description, authorID, source.sourceID);
//        return q;
//    }

//    public Author getAuthorObj(){
//        Author a = new Author(authorName);
//        return a;
//    }

}
