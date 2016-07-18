package com.example.sandeep.moviedata;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by sandeep on 7/18/16.
 */
public class AddMovieActivity extends AppCompatActivity {

    MovieDataHelper myDb;
    EditText et_title;
    EditText et_year;
    EditText et_genre;
    EditText et_id;
    Button bt_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        myDb = new MovieDataHelper(this);
        et_title = (EditText) findViewById(R.id.et_title);
        et_id = (EditText) findViewById(R.id.et_id);
        et_year = (EditText) findViewById(R.id.et_year);
        et_genre = (EditText) findViewById(R.id.et_genre);
        bt_add = (Button) findViewById(R.id.bt_add);
    }

    public void addData(View view) {
        Movie movie = new Movie(et_title.getText().toString(), et_year.getText().toString(), et_genre.getText().toString());
        boolean inserted = myDb.insertMovie(movie);
        if (inserted == true) {
            Toast.makeText(view.getContext(), "Movie added to database", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(view.getContext(), "Error", Toast.LENGTH_SHORT).show();
    }

    public void showData(View view) {
        Intent intent = new Intent(AddMovieActivity.this, MainActivity.class);

        Toast.makeText(view.getContext(), "Main Activity", Toast.LENGTH_SHORT).show();
        AddMovieActivity.this.startActivity(intent);
    }

    public void updateData(View view) {
        Movie movie = new Movie(et_title.getText().toString(), et_year.getText().toString(), et_genre.getText().toString());
        boolean updated = myDb.updateData(Integer.valueOf(et_id.getText().toString()), movie);
        if (updated == true) {
            Toast.makeText(view.getContext(), "Movie updated to database", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(view.getContext(), "Error", Toast.LENGTH_SHORT).show();
    }
    public void deleteData(View view) {
        int deleted = myDb.deleteData(Integer.valueOf(et_id.getText().toString()));
        if (deleted != 0) {
            Toast.makeText(view.getContext(), "Movie deleted from database", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(view.getContext(), "Error", Toast.LENGTH_SHORT).show();
    }

}
