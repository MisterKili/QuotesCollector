package com.example.quotescollector.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class OCR {

    private TessBaseAPI tessBaseApi;

    private static final String lang = "eng";
    String result = "empty";

    private static final String DATA_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
    private static final String TESSDATA = "tessdata";
    private final String TAG = "OCR";

    Uri image;

    Context context;

    TextExtractorTask extractor;


    public OCR(Context context){
        this.context = context;
    }


    public void setUri(String sUri){
        image = Uri.parse(sUri);
    }

    public void setUri(Uri uri){
        image = uri;
    }

    public Uri getUri() {
        return image;
    }

    public void doOCR(TextView textView, ProgressBar progressBar) {
        prepareTesseract();
        startOCR(textView, progressBar);
    }

    /**
     * Prepare directory on external storage
     *
     * @param path
     * @throws Exception
     */
    public void prepareDirectory(String path) {

        File dir = new File(path);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Log.e(TAG, "ERROR: Creation of directory " + path + " failed, check does Android Manifest have permission to write to external storage.");
            }
        } else {
            Log.i(TAG, "Created directory " + path);
        }
    }

    private void prepareTesseract() {
        try {
            prepareDirectory(DATA_PATH + TESSDATA);
        } catch (Exception e) {
            e.printStackTrace();
        }
        copyTessDataFiles(TESSDATA);
    }

    /**
     * Copy tessdata files (located on assets/tessdata) to destination directory
     *
     * @param path - name of directory with .traineddata files
     */
    private void copyTessDataFiles(String path) {
        try {
            String fileList[] = context.getAssets().list(path);

            System.out.println("Files from assets: " + fileList.length);

            for (String fileName : fileList) {

                // open file within the assets folder
                // if it is not already there copy it to the sdcard


                System.out.println(fileName);

                String pathToDataFile = DATA_PATH + path + "/" + fileName;
                if (!(new File(pathToDataFile)).exists()) {

                    InputStream in = context.getAssets().open(path + "/" + fileName);
                    OutputStream out = new FileOutputStream(pathToDataFile);

                    // Transfer bytes from in to out
                    byte[] buf = new byte[1024];
                    int len;

                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    in.close();
                    out.close();

                    Log.d(TAG, "Copied " + fileName + "to tessdata");
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "Unable to copy files to tessdata " + e.toString());
        }
    }

    private void startOCR(TextView textView, ProgressBar progressBar) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inSampleSize = 1; // 1 - means max size. 4 - means maxsize/4 size. Don't use value <4, because you need more memory in the heap to store your data.
//            Bitmap bitmap = BitmapFactory.decodeFile(imgUri.getPath(), options);
            Bitmap bitmap = BitmapFactory.decodeFile(image.getPath(), options);
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.screenshot3);


//            result = extractText(bitmap);

            extractor = new TextExtractorTask(bitmap, textView, progressBar);
            extractor.execute();

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    String extractText(Bitmap bitmap) {
        try {
            tessBaseApi = new TessBaseAPI();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            if (tessBaseApi == null) {
                Log.e(TAG, "TessBaseAPI is null. TessFactory not returning tess object.");
            }
        }

        tessBaseApi.init(DATA_PATH, lang);

//       //EXTRA SETTINGS
//        //For example if we only want to detect numbers
//        tessBaseApi.setVariable(TessBaseAPI.VAR_CHAR_WHITELIST, "1234567890");
//
        //blackList Example
//        tessBaseApi.setVariable(TessBaseAPI.VAR_CHAR_BLACKLIST, "!@#$%^&*()_+=-qwertyuiop[]}{POIU" +
//                "YTRWQasdASDfghFGHjklJKLl;L:'\"\\|~`xcvXCVbnmBNM,./<>?");

        Log.d(TAG, "Training file loaded");
        tessBaseApi.setImage(bitmap);
        String extractedText = "empty result";
        try {
            extractedText = tessBaseApi.getUTF8Text();
        } catch (Exception e) {
            Log.e(TAG, "Error in recognizing text.");
        }
        tessBaseApi.end();
        return extractedText;
    }

    public boolean isExtractorNull(){
        if (extractor == null)
            return true;
        else
            return false;
    }

    public void cancelTask(){
        if(extractor != null){
            extractor.cancel(true);
        }
    }


    final class TextExtractorTask extends AsyncTask<Void, Void, String> {

        Bitmap bitmap;
        TextView textView;
        ProgressBar progressBar;

        TextExtractorTask(Bitmap bitmap, TextView textView, ProgressBar progressBar){
            this.bitmap = bitmap;
            this.textView = textView;
            this.progressBar = progressBar;
        }

        @Override
        protected void onPreExecute() {
            textView.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... params) {
            System.out.println( "W doInBackgroud");
            result = extractText(bitmap);
            System.out.println(result);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            progressBar.setVisibility(View.INVISIBLE);
            textView.setText(result);
            textView.setVisibility(View.VISIBLE);
        }
    }
}
