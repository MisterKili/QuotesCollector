package com.example.quotescollector.View;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.example.quotescollector.R;
import com.example.quotescollector.SQLDatabase.DatabaseModel.Author;
import com.example.quotescollector.SQLDatabase.DatabaseModel.Quote;
import com.example.quotescollector.SQLDatabase.DatabaseModel.Source;
import com.example.quotescollector.SQLDatabase.DatabaseModel.SourceType;
import com.example.quotescollector.SQLDatabase.Handler.QuotesDatabase;


public class MainActivity extends AppCompatActivity implements QuotesListFragment.OnFragmentInteractionListener,
    AuthorsListFragment.OnFragmentInteractionListener, SourceListFragment.OnFragmentInteractionListener{

    QuotesDatabase database;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Fragment currentFragment;
    QuotesListFragment quotesListFragment;
    AuthorsListFragment authorsListFragment;
    SourceListFragment sourceListFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                    = new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_quotes:
                            fragmentTransaction = fragmentManager.beginTransaction();
                            if(quotesListFragment == null) {
                                quotesListFragment = new QuotesListFragment();
                                fragmentTransaction.add(R.id.container, quotesListFragment);
                            }
                            if(currentFragment != null && currentFragment != quotesListFragment){
                                fragmentTransaction.detach(currentFragment);
                            }
                            currentFragment = quotesListFragment;
                            fragmentTransaction.attach(quotesListFragment);
                            fragmentTransaction.commit();
                            return true;
                        case R.id.navigation_authors:
                            fragmentTransaction = fragmentManager.beginTransaction();
                            if (authorsListFragment == null) {
                                authorsListFragment = new AuthorsListFragment();
                                fragmentTransaction.add(R.id.container, authorsListFragment);
                            }
                            if (currentFragment != null && currentFragment != authorsListFragment) {
                                fragmentTransaction.detach(currentFragment);
                            }
                            currentFragment = authorsListFragment;
                            fragmentTransaction.attach(authorsListFragment);
                            fragmentTransaction.commit();
                            return true;

                        case R.id.navigation_sources:
                            fragmentTransaction = fragmentManager.beginTransaction();
                            if (sourceListFragment == null) {
                                sourceListFragment = new SourceListFragment();
                                fragmentTransaction.add(R.id.container, sourceListFragment);
                            }
                            if (currentFragment != null && currentFragment != sourceListFragment) {
                                fragmentTransaction.detach(currentFragment);
                            }
                            currentFragment = sourceListFragment;
                            fragmentTransaction.attach(sourceListFragment);
                            fragmentTransaction.commit();
                            return true;
                    }
                    return false;
                }
            };;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = QuotesDatabase.getInstance(this);



        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

//        quotesListFragment = new QuotesListFragment();
//        fragmentTransaction.add(R.id.container, quotesListFragment);
//        currentFragment = quotesListFragment;
//        fragmentTransaction.attach(quotesListFragment);
//        fragmentTransaction.commit();

        findViewById(R.id.addButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.listButton).setVisibility(View.INVISIBLE);

//        database.quotesDao().insertAuthor(new Author("Stephen King"));
//        database.quotesDao().insertAuthor(new Author("Stephen Hawking"));
//        database.quotesDao().insertAuthor(new Author("Richard Dawkins"));
//
//        System.out.println(database.quotesDao().getAllAuthors());
//
//        database.quotesDao().insertSourceType(new SourceType("book"));
//        database.quotesDao().insertSourceType(new SourceType("movie"));
//        database.quotesDao().insertSourceType(new SourceType("magazine"));
//        database.quotesDao().insertSourceType(new SourceType("real life"));
//
//        System.out.println(database.quotesDao().getAllSourceTypes());
//
//        Source s = new Source("Krotka historia czasu", 1);
//        System.out.println("Source created    sourceID = " + s.sourceID + " source name = " + s.sourceTitle);
//        database.quotesDao().insertSource(s);
//        System.out.println("Source inserted");
//        database.quotesDao().insertSource(new Source("Dluga historia czasu", 1));
//        database.quotesDao().insertSource(new Source("Srednia historia czasu", 2));
//
//        database.quotesDao().insertQuote(new Quote("Byl sobie wielki wybuch", "jakis opis",
//                2, 1));

        FloatingActionButton floatingActionButton = findViewById(R.id.fabAddQuote);
        floatingActionButton.setBackgroundColor(Color.BLUE);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddQuoteManActivity.class);
                startActivity(intent);
            }
        });
    }


    public void toList(View view) {
        Intent intent = new Intent(this, QuotesListActivity.class);
        startActivity(intent);
    }

    public void toAdd(View view) {
        Intent intent = new Intent(this, AddQuoteManActivity.class);
        startActivity(intent);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
