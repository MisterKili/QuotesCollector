package com.example.quotescollector.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.quotescollector.Model.SourceFull;
import com.example.quotescollector.R;
import com.example.quotescollector.SQLDatabase.DatabaseModel.Author;
import com.example.quotescollector.SQLDatabase.DatabaseModel.Quote;
import com.example.quotescollector.SQLDatabase.DatabaseModel.Source;
import com.example.quotescollector.SQLDatabase.DatabaseModel.SourceType;
import com.example.quotescollector.SQLDatabase.Handler.QuotesDatabase;

public class MainActivity extends AppCompatActivity {

    QuotesDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = QuotesDatabase.getInstance(this);

        database.quotesDao().deleteAllQuotes();

        database.quotesDao().insertAuthor(new Author("Stephen King"));
        database.quotesDao().insertAuthor(new Author("Stephen Hawking"));
        database.quotesDao().insertAuthor(new Author("Richard Dawkins"));

        System.out.println(database.quotesDao().getAllAuthors());

        database.quotesDao().insertSourceType(new SourceType("book"));
        database.quotesDao().insertSourceType(new SourceType("movie"));
        database.quotesDao().insertSourceType(new SourceType("magazine"));
        database.quotesDao().insertSourceType(new SourceType("real life"));

        System.out.println(database.quotesDao().getAllSourceTypes());

        Source s = new Source("Krotka historia czasu", 1);
        System.out.println("Source created    sourceID = " + s.sourceID + " source name = " + s.sourceTitle);
        database.quotesDao().insertSource(s);
        System.out.println("Source inserted");
        database.quotesDao().insertSource(new Source("Dluga historia czasu", 1));
        database.quotesDao().insertSource(new Source("Srednia historia czasu", 2));

        database.quotesDao().insertQuote(new Quote("Byl sobie wielki wybuch", "jakis opis",
                2, 1));

        for(SourceFull sf : database.quotesDao().getAllSourceFull()){
            Log.d("aaaa", sf.toString());
        }


    }

    public void toList(View view) {
        Intent intent = new Intent(this, QuotesListActivity.class);
        startActivity(intent);
    }

    public void toAdd(View view) {
        Intent intent = new Intent(this, AddQuoteManActivity.class);
        startActivity(intent);
    }

    public void clearDatabase(View view){
        database.quotesDao().deleteAllQuotes();
    }
}
