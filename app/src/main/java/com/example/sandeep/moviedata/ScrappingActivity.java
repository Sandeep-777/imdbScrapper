package com.example.sandeep.moviedata;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class ScrappingActivity extends AppCompatActivity {

    // URL Address
    String url = "http://www.imdb.com/chart/top";
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrapping);
    }

    public void onScrap(View view) {
        new Scrappy().execute();
    }

    private class Scrappy extends AsyncTask<Void, Void, Void> {
        String bitmap;
        MovieDataHelper myDb;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myDb = new MovieDataHelper(ScrappingActivity.this);
            mProgressDialog = new ProgressDialog(ScrappingActivity.this);
            mProgressDialog.setTitle("Scraping data from IMDB");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                // Connect to the web site
                Document document = Jsoup.connect(url).get();
                // Using Elements to get the class data
                for (Element row : document.select("table.chart.full-width tr")) {

                    final String title = row.select(".titleColumn a").text();
                    final String year = row.select(".titleColumn span").text();
                    final String rating = row.select(".imdbRating").text();

                    if (title != "null" && year != "" && rating != "") {
                        Movie movie = new Movie(title, year, rating);
                        myDb.insertMovie(movie);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mProgressDialog.dismiss();
            Intent intent = new Intent(ScrappingActivity.this, MainActivity.class);
            ScrappingActivity.this.startActivity(intent);
        }
    }
}
