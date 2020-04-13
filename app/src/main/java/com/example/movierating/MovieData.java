package com.example.movierating;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieData {
    private  String movieName;
    private  String rating;
    private  String imagePath;
    private  int movieId;

    public MovieData(String movieName, String rating, String imagePath,int movieId) {
        this.movieName = movieName;
        this.rating = rating;
        this.imagePath = imagePath;
        this.movieId=movieId;
    }



    public int getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
