package com.example.sandeep.moviedata;

/**
 * Created by sandeep on 7/18/16.
 */
public class Movie {
    private String title, rating, year;

    public Movie() {
    }

    public Movie(String title, String year, String genre) {
        this.title = title;
        this.year = year;
        this.rating = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String genre) {
        this.rating = genre;
    }
}