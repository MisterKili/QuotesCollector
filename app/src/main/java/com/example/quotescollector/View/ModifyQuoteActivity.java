package com.example.quotescollector.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quotescollector.Model.QuoteFull;

public class ModifyQuoteActivity extends AddQuoteManActivity {


    QuoteFull quoteFull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int quoteID;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                quoteID = extras.getInt("quoteID");

                Toast.makeText(this, "ID: " + quoteID, Toast.LENGTH_SHORT).show();

                quoteFull = database.quotesDao().getQuoteFull(quoteID);
                etQuote.setText(quoteFull.quote);
                etDescription.setText(quoteFull.description);

            }
        } else {
            System.out.println("Jest cos zapisane");
        }

    }


    @Override
    public void doneClick(View view){

    }

}
