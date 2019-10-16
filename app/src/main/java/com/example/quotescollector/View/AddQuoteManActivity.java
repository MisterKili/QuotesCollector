package com.example.quotescollector.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quotescollector.Model.SourceFull;
import com.example.quotescollector.R;
import com.example.quotescollector.SQLDatabase.DatabaseModel.Author;
import com.example.quotescollector.SQLDatabase.DatabaseModel.Quote;
import com.example.quotescollector.SQLDatabase.DatabaseModel.Source;
import com.example.quotescollector.SQLDatabase.Handler.QuotesDatabase;

import java.util.List;

public class AddQuoteManActivity extends AppCompatActivity {

    QuotesDatabase database;

    private EditText etQuote, etDescription;
    private Spinner spinSource, spinAuthor;

    private List<Author> authorList;
    private List<SourceFull> sourceList;

    private Author author;
    private Quote quote;
    private SourceFull source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quote_man);

        database = QuotesDatabase.getInstance(this);

        etQuote = findViewById(R.id.quoteInput);
        etDescription = findViewById(R.id.descriptionInput);
        spinSource = findViewById(R.id.sourceSpinner);
        spinAuthor = findViewById(R.id.authorSpinner);

        spinAuthor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                author = authorList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinSource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                source = sourceList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        authorList = database.quotesDao().getAllAuthors();
        sourceList = database.quotesDao().getAllSourceFull();

        fillAuthorSpinner();
        fillSourceSpinner();
    }

    public void doneClick(View view) {
        String quoteS = etQuote.getText().toString();
        String descriptionS = etDescription.getText().toString();

        QuotesDatabase database = QuotesDatabase.getInstance(this);
//        int authorID = (int) database.quotesDao().insertAuthor(author);

        Quote quote = new Quote(quoteS, descriptionS, author.authorID, 1);
        database.quotesDao().insertQuote(quote);

        Toast.makeText(this, "Quote added", Toast.LENGTH_LONG).show();
    }

    private void fillAuthorSpinner() {
        if(authorList != null) {
            ArrayAdapter<Author> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, authorList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinAuthor.setAdapter(adapter);
        }
    }

    private void fillSourceSpinner() {
        if(sourceList != null) {
            ArrayAdapter<SourceFull> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, sourceList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinSource.setAdapter(adapter);
        }
    }

}
