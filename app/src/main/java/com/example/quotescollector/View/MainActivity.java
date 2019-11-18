package com.example.quotescollector.View;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quotescollector.Model.OCR;
import com.example.quotescollector.Model.SourceFull;
import com.example.quotescollector.Model.tools.RequestPermissionsTool;
import com.example.quotescollector.Model.tools.RequestPermissionsToolImpl;
import com.example.quotescollector.R;
import com.example.quotescollector.SQLDatabase.DatabaseModel.Author;
import com.example.quotescollector.SQLDatabase.DatabaseModel.Quote;
import com.example.quotescollector.SQLDatabase.DatabaseModel.Source;
import com.example.quotescollector.SQLDatabase.DatabaseModel.SourceType;
import com.example.quotescollector.SQLDatabase.Handler.QuotesDatabase;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    QuotesDatabase database;


    TextView textView;
    ProgressBar progressBar;
    ImageView imageView;

    OCR ocr;
    String mCameraFileName;

    private RequestPermissionsTool requestTool; //for API >=23 only

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = QuotesDatabase.getInstance(this);

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
//
//        for(SourceFull sf : database.quotesDao().getAllSourceFull()){
//            Log.d("aaaa", sf.toString());
//        }


        //==========================================================
//        textView = (TextView) findViewById(R.id.textViewResult);
//        imagePhoto = (ImageView) findViewById(R.id.imageViewPhoto);
//        progressBar = findViewById(R.id.progressBar);
//        progressBar.setVisibility(View.INVISIBLE);
//
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            requestPermissions();
//        }
//
//        ocr = new OCR(this);
//
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            String sUri = extras.getString("imageUri");
//            if(sUri != null){
//                ocr.setUri(sUri);
//                imagePhoto.setImageURI(ocr.getUri());
//            }
//        }
        //==========================================================
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
        database.clearAllTables();
    }







    public void takePhotoAndOCR(View view) {

//        startCameraActivity();
    }


}
