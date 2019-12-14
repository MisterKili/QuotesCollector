package com.example.quotescollector.View;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quotescollector.Model.QuoteFull;
import com.example.quotescollector.Model.SourceFull;
import com.example.quotescollector.R;
import com.example.quotescollector.SQLDatabase.Handler.QuotesDatabase;

public class QuoteDetailsActivity extends AppCompatActivity {


    QuoteFull quote;
    QuotesDatabase database;
    TextView tvQuote, tvDescr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_details);

        int quoteID;
        database = QuotesDatabase.getInstance(this);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                quoteID = extras.getInt("quoteID");

                quote = database.quotesDao().getQuoteFull(quoteID);
                SourceFull sourceFull = database.quotesDao().getSourceFullOne(quote.sourceID);
                quote.setSource(sourceFull);
            }
        } else {
            System.out.println("Jest cos zapisane");
        }

        tvQuote = findViewById(R.id.tvQuoteInDetails);
        tvDescr = findViewById(R.id.tvQuoteDscrInDetails);

        tvQuote.setText(quote.getQuoteFull());
        tvDescr.setText(quote.description);

        setCopyListener();
    }


    private void setCopyListener(){
        tvQuote.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("quote", quote.quote);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getApplicationContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }


}
