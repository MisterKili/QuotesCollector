package com.example.quotescollector.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quotescollector.R;
import com.example.quotescollector.SQLDatabase.DatabaseModel.Author;
import com.example.quotescollector.SQLDatabase.DatabaseModel.Quote;
import com.example.quotescollector.SQLDatabase.Handler.QuotesDatabase;

public class AddQuoteManActivity extends AppCompatActivity {

    private EditText quote, author, description;
    private Spinner sourceSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quote_man);

        quote = findViewById(R.id.quoteInput);
        author = findViewById(R.id.authorInput);
        description = findViewById(R.id.descriptionInput);
        sourceSpinner = findViewById(R.id.sourceSpinner);
    }

    public void doneClick(View view) {
        String quoteS = quote.getText().toString();
        String authorS = author.getText().toString();
        String descriptionS = description.getText().toString();

        Author author = new Author(authorS);

        QuotesDatabase database = QuotesDatabase.getInstance(this);
        int authorID = (int) database.quotesDao().insertAuthor(author);
        Quote quote = new Quote(quoteS, descriptionS, authorID, 1);
        database.quotesDao().insertQuote(quote);

        Toast.makeText(this, "Quote added", Toast.LENGTH_LONG).show();
    }
}
