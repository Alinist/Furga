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

    public void writeMovieToFile(Movie movie) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MOVIES_FILE_PATH, true))) {
            writer.write("ID: " + movie.getMovieID());
            writer.newLine();
            writer.write("Title: " + movie.getMovieTitle());
            writer.newLine();
            writer.write("Released date: " + movie.getReleaseDate());
            writer.newLine();
            writer.write("Duration: " + movie.getRunningTime());
            writer.newLine();
            writer.write("IMDB score: " + movie.getImdb_score());
            writer.newLine();
            writer.write("Country: " + movie.getCountry());
            writer.newLine();
            writer.write("Budget: " + movie.getBudget());
            writer.newLine();
            writer.write("BoxOffice: " + movie.getRevenue());
            writer.newLine();
            writer.newLine(); // Add an extra newline to separate movie entries
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void extractMovieInfo(String movieTitle, String infoKey) {
        try (BufferedReader reader = new BufferedReader(new FileReader(MOVIES_FILE_PATH))) {
            String line;
            boolean movieFound = false;
            boolean infoFound = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Title:") && line.substring(7).trim().equalsIgnoreCase(movieTitle)) {
                    movieFound = true;
                }

                if (movieFound) {
                    if (line.startsWith(infoKey + ":")) {
                        String infoValue = line.substring(infoKey.length() + 1).trim();
                        System.out.println(infoValue);
                        infoFound = true;
                        break;
                    }
                }

                if (line.isEmpty()) {
                    movieFound = false;
                }
            }

            if (!infoFound) {
                System.out.println("Info not found");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void extractDirectorInfo(String firstName, String lastName, String infoKey) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DIRECTORS_FILE_PATH))) {
            String line;
            boolean directorFound = false;
            boolean infoFound = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Firstname:") && line.substring(10).trim().equalsIgnoreCase(firstName)) {
                    // Check the next line for the last name
                    String nextLine = reader.readLine();
                    if (nextLine != null && nextLine.startsWith("Lastname:")
                            && nextLine.substring(9).trim().equalsIgnoreCase(lastName)) {
                        directorFound = true;
                    }
                }

                if (directorFound) {
                    if (line.startsWith(infoKey + ":")) {
                        String infoValue = line.substring(infoKey.length() + 1).trim();
                        System.out.println(infoValue);
                        infoFound = true;
                        break;
                    }
                }

                if (line.isEmpty()) {
                    directorFound = false;
                }
            }

            if (!infoFound) {
                System.out.println("Info not found");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // Other class methods

    public void extractActorInfo(String firstName, String lastName, String infoKey) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ACTORS_FILE_PATH))) {
            String line;
            boolean actorFound = false;
            boolean infoFound = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Firstname:") && line.substring(10).trim().equalsIgnoreCase(firstName)) {
                    // Check the next line for the last name
                    String nextLine = reader.readLine();
                    if (nextLine != null && nextLine.startsWith("Lastname:")
                            && nextLine.substring(9).trim().equalsIgnoreCase(lastName)) {
                        actorFound = true;
                    }
                }

                if (actorFound) {
                    if (line.startsWith(infoKey + ":")) {
                        String infoValue = line.substring(infoKey.length() + 1).trim();
                        System.out.println(infoValue);
                        infoFound = true;
                        break;
                    }
                }

                if (line.isEmpty()) {
                    actorFound = false;
                }
            }

            if (!infoFound) {
                System.out.println("Info not found");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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