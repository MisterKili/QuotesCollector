package com.example.quotescollector.Model;

import com.example.quotescollector.SQLDatabase.DatabaseModel.Author;

public class AuthorWithNumbOfQ extends Author {

    public int numberOfQuotes;

    public AuthorWithNumbOfQ(int authorID, String authorName, int numberOfQuotes){
        this.authorID = authorID;
        this.authorName = authorName;
        this.numberOfQuotes = numberOfQuotes;
    }

    @Override
    public String toString(){
        return authorName + " " + numberOfQuotes;
    }

}
