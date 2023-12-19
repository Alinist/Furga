package com.example.oopjavafx;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The `Director` class represents a person who is a director in the film industry. It extends the `Person` class and adds
 * additional properties such as a list of movies directed, a list of awards received, and the number of awards and directed movies.
 */
public class Director extends Person {
    /**
     * The movies directed by the director.
     */
    public ArrayList < String > movies;
    /**
     * The awards won by the director.
     */
    public ArrayList < String > awards;
    /**
     * The number of movies directed by director.
     */
    public int number_of_directed_movies;
    /**
     * The number of awards won by the director.
     */
    public int number_of_awards;

    private static final String DIRECTORS_FILE_PATH = "directors.txt";

    private static final String MOVIES_FILE_PATH = "movies.txt";
    /**
     * Constructs a new instance of the `Director` class with the specified parameters.
     *
     * @param first_name             the first name of the director
     * @param last_name              the last name of the director
     * @param nationality            the nationality of the director
     * @param gender                 the gender of the director
     * @param age                    the age of the director
     * @param movies                 the list of movies directed by the director
     * @param awards                 the list of awards received by the director
     */

    public Director(String first_name, String last_name, String nationality, String gender, int age,ArrayList < String > movies,
                    ArrayList < String > awards) {
        super(first_name, last_name, nationality, gender, age);
        this.movies = movies;
        this.awards = awards;
    }
    public void writeDirectorToFile(Director director) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DIRECTORS_FILE_PATH, true))) {
            writer.write("Firstname: " + director.getFirst_name());
            writer.newLine();
            writer.write("Lastname: " + director.getLast_name());
            writer.newLine();
            writer.write("Nationality: " + director.getNationality());
            writer.newLine();
            writer.write("Age: " + director.getAge());
            writer.newLine();
            writer.write("Gender: " + director.getGender());
            writer.newLine();
            writer.write("Movies: " + director.getMovies());
            writer.newLine();
            writer.write("Awards: " + director.getAwards());
            writer.newLine();
            writer.newLine(); // Add an extra newline to separate director entries
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
                    if (nextLine != null && nextLine.startsWith("Lastname:") && nextLine.substring(9).trim().equalsIgnoreCase(lastName)) {
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
    public int getNumber_of_directed_movies() {
        return movies.size();
    }
    public int getNumber_of_awards() {
        return awards.size();
    }
    public void addMovie(String movies) {
        this.movies.add(movies);
    }
    public ArrayList < String > getMovies() {
        return this.movies;
    }
    public void addAwards(String awards) {
        this.awards.add(awards);
    }
    public ArrayList < String > getAwards() {
        return this.awards;
    }
}