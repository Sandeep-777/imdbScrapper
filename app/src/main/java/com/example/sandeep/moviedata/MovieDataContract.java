package com.example.sandeep.moviedata;

import android.provider.BaseColumns;

/**
 * Created by sandeep on 7/18/16.
 */
public final class MovieDataContract {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "movieDataBase.db";
    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ", ";
    public static final String INTEGER_TYPE = " INTEGER";

    public MovieDataContract() {
    }

    public static abstract class MovieTable implements BaseColumns {
        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_NAME_YEAR = "year";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_GENRE = "genre";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_YEAR + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_GENRE + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

}
