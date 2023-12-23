package com.example.oopjavafx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Movie {

    ////////////////////////////////////////////////// Fields////////////////////////////////////////////////////////////

    ArrayList<Float> Ratings = new ArrayList<Float>();
    private boolean Is_Watched = false; // status

    public int watchcount;

    private final String movieID; // can be seen by Director

    private String movieTitle;

    private String releaseDate;

    private int RunningTime; // in minutes

    public String[] Genres; // Amgad change from private to public for search reasons

    // public Director director = new Director(); // Director object

    // Defining the array of cast
    public ArrayList<Actor> Cast = new ArrayList<Actor>();

    String Language;

    public double imdb_score; // over all rating wala THE IMDB score for the actual site?

    public String country;

    public double budget;

    public double revenue;

    public int user_Rating; // user rating for that movie
    public String directorFile;
    public ArrayList<String> actorFiles = new ArrayList<>();

    private static final String MOVIES_FILE_PATH = "movies.txt";

    private static final String DIRECTORS_FILE_PATH = "directors.txt";

    private static final String ACTORS_FILE_PATH = "actors.txt";

    Director director;

    public ImageView poster;

    public Movie() {
        user_Rating = 0;
        Is_Watched = false;
        // this.movieID = UUID.randomUUID().toString();
        this.movieID = UUID.randomUUID().toString();
        this.movieTitle = null;
        this.releaseDate = null;
        this.RunningTime = 0;
        Genres = null;
        Language = null;
        imdb_score = 0;
        country = null;
        this.budget = 0;
        this.revenue = 0;
    }

    public Movie(String movieID) {
        this.movieID = movieID;
    }

    public Movie(boolean is_Watched, String movieID, String movieTitle, String releaseDate, int runningTime,
                 double imdb_score, String country, double budget, double revenue) {
        Is_Watched = is_Watched;
        this.movieID = movieID;
        this.movieTitle = movieTitle;
        this.releaseDate = releaseDate;
        RunningTime = runningTime;
        this.imdb_score = imdb_score;
        this.country = country;
        this.budget = budget;
        this.revenue = revenue;
    }

    public static Movie getMovieById(String mid) {
        for (Movie m : Main.moviesData) {
            if (m.getMovieID().equals(mid)) {
                return m;
            }
        }
        return null;
    }

    /////////////////////////////////////////////// Setters and
    /////////////////////////////////////////////// Getters//////////////////////////////////////////////////
    public void setCast(ArrayList<Actor> cast) {
        this.Cast = cast;
    }

    public ArrayList<Actor> getCast() {
        return this.Cast;
    }

    public String getMovieDirector() {
        String dFullName = director.first_name + " " + director.last_name;
        return dFullName;
    }

    public void setMovieDirector(Director d) {
        director = d;
    }

    // Other class methods

    // Other class methods

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;// .toLowerCase()
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setRunningTime(int runningTime) {
        RunningTime = runningTime;
    }

    public void setGenres(String... genres) {
        Genres = new String[genres.length];
        System.arraycopy(genres, 0, Genres, 0, genres.length);
    }

    public void setLanguage(String language) {
        this.Language = language;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public String getMovieID() {
        return movieID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getRunningTime() {
        return RunningTime;
    }

    public String[] getGenres() { ///// alinist's method
        return Genres;
    }

    public double getImdb_score() {
        imdb_score = 0;
        for (int i = 0; i < Ratings.size(); i++) {
            imdb_score += Ratings.get(i);
        }
        double result = imdb_score / Ratings.size();
        return Math.round(result * 10) / 10.0;
    }

    public double setImdb_score() {
        imdb_score = 0;
        for (int i = 0; i < Ratings.size(); i++) {
            imdb_score += Ratings.get(i);
        }
        double result = imdb_score / Ratings.size();
        return Math.round(result * 10) / 10.0;
    }

    public String getCountry() {
        return country;
    }

    public double getBudget() {
        return budget;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setIs_Watched(boolean is_Watched) {
        Is_Watched = is_Watched;
    }

    public boolean getIs_Watched() {
        return Is_Watched;
    }

    // public void DisplayMovieDetails() {
    // System.out.println( this.movieTitle + " Details are : ");
    // System.out.println();
    //     }

}