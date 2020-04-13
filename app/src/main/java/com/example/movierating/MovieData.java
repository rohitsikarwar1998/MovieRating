package com.example.movierating;

public class MovieData {
    private  String movieName;
    private  String rating;
    private  String imagePath;

    public MovieData(String movieName, String rating, String imagePath) {
        this.movieName = movieName;
        this.rating = rating;
        this.imagePath = imagePath;
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
