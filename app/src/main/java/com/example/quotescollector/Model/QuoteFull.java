package com.example.quotescollector.Model;

import android.arch.persistence.room.Ignore;

import java.io.Serializable;

public class QuoteFull implements Serializable {

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

    public void setSource(SourceFull source) {
        this.source = source;
    }

    public String getQuoteWithQuotemarks(){
        return "\"" + quote + "\"";
    }

    public String getQuoteFull(){
        StringBuilder stringBuilder = new StringBuilder(getQuoteWithQuotemarks());
        stringBuilder.append("\n~ ");
        stringBuilder.append(authorName);
        stringBuilder.append(" (");
        stringBuilder.append(source.sourceTitle);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
