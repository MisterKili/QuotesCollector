package com.example.quotescollector.View;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.quotescollector.Model.QuoteFull;
import com.example.quotescollector.Model.SourceFull;

public class ModifyQuoteActivity extends AddQuoteActivity {


    QuoteFull quoteFull;
    SourceFull sourceFull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int quoteID;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                quoteID = extras.getInt("quoteID");

                quoteFull = database.quotesDao().getQuoteFull(quoteID);
                sourceFull = database.quotesDao().getSourceFullOne(quoteFull.sourceID);

                etQuote.setText(quoteFull.quote);
                etDescription.setText(quoteFull.description);

                imagePhoto.setVisibility(View.GONE);

                spinAuthor.setSelection(getNumberOfSelectedAuthor());
                spinSource.setSelection(NumberOfSelectedSource());
            }
        } else {
            System.out.println("Jest cos zapisane");
        }
    }

    private int getNumberOfSelectedAuthor(){
        for(int i = 0; i < authorList.size(); i++){
            if (authorList.get(i).authorName.equals(quoteFull.authorName)){
                return i;
            }
        }
        Log.d("ModyfyQuote", "Author isn't in the list");
        return 0;
    }

    private int NumberOfSelectedSource(){
        for(int i = 0; i < sourceList.size(); i++){
            SourceFull s1 = sourceList.get(i);
            if (s1.sourceID == sourceFull.sourceID){
                return i;
            }
        }
        Log.d("ModyfyQuote", "Source isn't in the list");
        return 0;
    }


    @Override
    public void doneClick(View view){
        System.out.println("doneClick");
        System.out.println("author: "+author.authorName);

        System.out.println("source id: "+ sourceID);
        System.out.println("sourcefull id: "+ source.sourceID);

        SourceFull sf = database.quotesDao().getSourceFullOne(source.sourceID);
        System.out.println("source: "+sf.sourceTitle + "  " + sf.sourceTypeName);

        String quoteS = etQuote.getText().toString();
        System.out.println("quote: "+quoteS);
        String descriptionS = etDescription.getText().toString();

        if (descriptionS.isEmpty()){
            descriptionS = "brak opisu";
        }
        System.out.println("desc: " + descriptionS);

        database.quotesDao().updateQuote(quoteFull.quoteID, quoteS, descriptionS, author.authorID, source.sourceID);
        Toast.makeText(this, "Quote updated", Toast.LENGTH_LONG).show();
    }

}
