package com.example.quotescollector.Model;


import com.example.quotescollector.SQLDatabase.DatabaseModel.Author;
import com.example.quotescollector.SQLDatabase.DatabaseModel.Quote;

public class QuoteFull {

    public int quoteID;

    public String quote;
    public String description;

    public String authorName;
//    public int authorID;


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

//    public Quote getQuoteObj(){
//        Quote q = new Quote(quote, description, authorID, source.sourceID);
//        return q;
//    }

//    public Author getAuthorObj(){
//        Author a = new Author(authorName);
//        return a;
//    }

}
