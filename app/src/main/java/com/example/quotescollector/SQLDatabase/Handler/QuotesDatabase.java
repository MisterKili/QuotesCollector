package com.example.quotescollector.SQLDatabase.Handler;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.quotescollector.SQLDatabase.DatabaseModel.Author;
import com.example.quotescollector.SQLDatabase.DatabaseModel.Category;
import com.example.quotescollector.SQLDatabase.DatabaseModel.Colour;
import com.example.quotescollector.SQLDatabase.DatabaseModel.Quote;
import com.example.quotescollector.SQLDatabase.DatabaseModel.QuoteInCategory;
import com.example.quotescollector.SQLDatabase.DatabaseModel.Source;
import com.example.quotescollector.SQLDatabase.DatabaseModel.SourceType;

@Database(entities = {Author.class, Category.class, Colour.class, Quote.class, QuoteInCategory.class,
        Source.class, SourceType.class}, exportSchema = false, version = 1)
public abstract class QuotesDatabase extends RoomDatabase {

    private static final String DB_NAME = "quotes_db";
    private static QuotesDatabase instance;

    public static synchronized QuotesDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), QuotesDatabase.class,
                    DB_NAME).allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract QuotesDao quotesDao();
}
