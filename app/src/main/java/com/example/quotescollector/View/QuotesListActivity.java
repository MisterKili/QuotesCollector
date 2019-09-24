package com.example.quotescollector.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.quotescollector.Model.QuoteFull;
import com.example.quotescollector.R;
import com.example.quotescollector.SQLDatabase.Handler.QuotesDatabase;

import java.util.List;

public class QuotesListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<QuoteFull> myDataset;
    QuotesDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_list);

        recyclerView = (RecyclerView) findViewById(R.id.quotesRV);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        database = QuotesDatabase.getInstance(this);
        myDataset = database.quotesDao().getAllQuotesFull();

        mAdapter = new QuotesListAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);

    }
}
