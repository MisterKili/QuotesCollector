package com.example.quotescollector.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quotescollector.Model.SourceFull;
import com.example.quotescollector.R;
import com.example.quotescollector.SQLDatabase.DatabaseModel.Author;
import com.example.quotescollector.SQLDatabase.DatabaseModel.Quote;
import com.example.quotescollector.SQLDatabase.DatabaseModel.Source;
import com.example.quotescollector.SQLDatabase.DatabaseModel.SourceType;
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
    private int sourceID;
    private int sourceTypeID;

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

        fillAuthorSpinner();
        fillSourceSpinner();
    }

    public void doneClick(View view) {

        System.out.println("doneClick");
        System.out.println("author: "+author.authorName);
        SourceFull sf = database.quotesDao().getSourceFullOne(4);
        System.out.println("source: "+sf.sourceTitle + "  " + sf.sourceTypeName);

        String quoteS = etQuote.getText().toString();
        String descriptionS = etDescription.getText().toString();

        QuotesDatabase database = QuotesDatabase.getInstance(this);

        Quote quote = new Quote(quoteS, descriptionS, author.authorID, sourceID);
        database.quotesDao().insertQuote(quote);

        Toast.makeText(this, "Quote added", Toast.LENGTH_LONG).show();
    }

    private void fillAuthorSpinner() {
        authorList = database.quotesDao().getAllAuthors();
        if(authorList != null) {
            ArrayAdapter<Author> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, authorList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinAuthor.setAdapter(adapter);
        }
    }

    private void fillSourceSpinner() {
        sourceList = database.quotesDao().getAllSourceFull();
        if(sourceList != null) {
            ArrayAdapter<SourceFull> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, sourceList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinSource.setAdapter(adapter);
        }
    }

    //TODO refactoring
    public void addSource(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add source");

        final LinearLayout parentLL = new LinearLayout(this);
        parentLL.setOrientation(LinearLayout.VERTICAL);

        final EditText inputSourceName = new EditText(this);
        inputSourceName.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        inputSourceName.setInputType(InputType.TYPE_CLASS_TEXT);
        inputSourceName.setHint("Source name...");

        final LinearLayout childLL = new LinearLayout(this);
        childLL.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        childLL.setOrientation(LinearLayout.HORIZONTAL);

        final Spinner spinnerSourceTypes = new Spinner(this);
        spinnerSourceTypes.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        final List<SourceType>[] sourceTypes = new List[]{database.quotesDao().getAllSourceTypes()};
        ArrayAdapter<SourceType> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, sourceTypes[0]);
        spinnerSourceTypes.setAdapter(adapter);
        spinnerSourceTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SourceType sourceType = sourceTypes[0].get(i);
                sourceTypeID = sourceType.sourceTypeID;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        Button buttonAddSourceType = new Button(this);
        buttonAddSourceType.setText("+");
        buttonAddSourceType.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        buttonAddSourceType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {        //pokazanie EditText do SourceType

                final LinearLayout childLL2 = new LinearLayout(getApplicationContext());
                childLL2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                childLL2.setOrientation(LinearLayout.HORIZONTAL);

                final EditText inputSourceType = new EditText(getApplicationContext());
                inputSourceType.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                inputSourceType.setInputType(InputType.TYPE_CLASS_TEXT);
                inputSourceType.setHint("Source type...");

                Button buttonAddSourceType = new Button(getApplicationContext());
                buttonAddSourceType.setText("Add");
                buttonAddSourceType.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                buttonAddSourceType.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {        //git działa
                        String sourceTypeName = inputSourceType.getText().toString();
                        sourceTypeID = (int) database.quotesDao().insertSourceType(new SourceType(sourceTypeName));
                        inputSourceType.setText("");

                        sourceTypes[0] = database.quotesDao().getAllSourceTypes();
                        ArrayAdapter<SourceType> adapter = new ArrayAdapter<>(getApplicationContext(),
                                android.R.layout.simple_spinner_item, sourceTypes[0]);
                        spinnerSourceTypes.setAdapter(adapter);

                        parentLL.removeView(childLL2);
                    }
                });

                childLL2.addView(inputSourceType);
                childLL2.addView(buttonAddSourceType);

                parentLL.addView(childLL2);
            }
        });

        childLL.addView(spinnerSourceTypes);
        childLL.addView(buttonAddSourceType);

        parentLL.addView(inputSourceName);
        parentLL.addView(childLL);

        builder.setView(parentLL);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String sourceName = inputSourceName.getText().toString();
                sourceID = (int)database.quotesDao().insertSource(new Source(sourceName, sourceTypeID));
                fillSourceSpinner();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    public void addAuthor(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add author");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String authorName = input.getText().toString();
                database.quotesDao().insertAuthor(new Author(authorName));
                fillAuthorSpinner();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
