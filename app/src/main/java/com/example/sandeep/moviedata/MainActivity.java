package com.example.sandeep.moviedata;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MovieListAdapter mAdapter;
    MovieDataHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new MovieDataHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new MovieListAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();
    }

    private void prepareMovieData() {
        Cursor cursor = myDb.showMovie();
        if (cursor.getCount() == 0) {
            Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_LONG).show();
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            buffer.append(MovieDataContract.MovieTable.COLUMN_NAME_TITLE + " :" + cursor.getString(1) + "\n");
            buffer.append(MovieDataContract.MovieTable.COLUMN_NAME_YEAR + " :" + cursor.getString(2) + "\n");
            buffer.append(MovieDataContract.MovieTable.COLUMN_NAME_GENRE + " :" + cursor.getString(3) + "\n\n");

            Movie movie = new Movie(cursor.getString(1), cursor.getString(2), cursor.getString(3));
            movieList.add(movie);
        }

        mAdapter.notifyDataSetChanged();
    }
}
