package com.example.sandeep.moviedata;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by sandeep on 7/18/16.
 */
public class MovieDataHelper extends SQLiteOpenHelper {

    public MovieDataHelper(Context context) {
        super(context, MovieDataContract.DATABASE_NAME, null, MovieDataContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MovieDataContract.MovieTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MovieDataContract.MovieTable.DELETE_TABLE);
        onCreate(db);
    }

    public boolean insertMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MovieDataContract.MovieTable.COLUMN_NAME_TITLE, movie.getTitle());
        values.put(MovieDataContract.MovieTable.COLUMN_NAME_YEAR, movie.getYear());
        values.put(MovieDataContract.MovieTable.COLUMN_NAME_GENRE, movie.getGenre());
        db.insert(MovieDataContract.MovieTable.TABLE_NAME, null, values);
        db.close();
        return true;
    }

    public Cursor getMovie(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + MovieDataContract.MovieTable.TABLE_NAME +
                " WHERE " + MovieDataContract.MovieTable._ID + " = " + id, null);
        return cursor;
    }

    public Cursor showMovie() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + MovieDataContract.MovieTable.TABLE_NAME,
                null);
        return cursor;
    }


    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db,
                MovieDataContract.MovieTable.TABLE_NAME);
        return numRows;
    }

    public boolean updateData(Integer id, Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MovieDataContract.MovieTable.COLUMN_NAME_TITLE, movie.getTitle());
        values.put(MovieDataContract.MovieTable.COLUMN_NAME_YEAR, movie.getYear());
        values.put(MovieDataContract.MovieTable.COLUMN_NAME_GENRE, movie.getGenre());
        db.update(MovieDataContract.MovieTable.TABLE_NAME, values,
                MovieDataContract.MovieTable._ID + " = ? ",
                new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteData(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(MovieDataContract.MovieTable.TABLE_NAME,
                MovieDataContract.MovieTable._ID + " = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList<String> getAllData() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * From " + MovieDataContract.MovieTable.TABLE_NAME, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(MovieDataContract.MovieTable.COLUMN_NAME_TITLE)));
            res.moveToNext();
        }
        return array_list;
    }
}
