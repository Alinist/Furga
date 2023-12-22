package com.example.oopjavafx;

import java.io.*;
import java.util.ArrayList;
/**
 * The `Actor` class represents a person who is an actor in the film industry. It extends the `Person` class and adds
 * additional properties such as a list of movies acted in, a list of awards received, and the number of awards.
 */
public class Actor extends Person{
    /**
     * The movies contributed by the actor.
     */
    public ArrayList < String > movies;
    /**
     * The awards won by the actor.
     */
    public ArrayList < String > awards;
    /**
     * The number of awards won by the actor.
     */
    public int number_of_awards;

    private static final String ACTORS_FILE_PATH = "actors.txt.txt";

    private static final String MOVIES_FILE_PATH = "movies.txt";

    /**
     * Constructs a new instance of the `Actor` class with the specified parameters.
     *
     * @param first_name             the first name of the actor
     * @param last_name              the last name of the actor
     * @param nationality            the nationality of the actor
     * @param gender                 the gender of the actor
     * @param age                    the age of the actor
     * @param movies                 the list of movies acted in by the actor
     * @param awards                 the list of awards received by the actor
     */
    public Actor(String first_name, String last_name, String nationality, String gender, int age,ArrayList < String > movies,
                 ArrayList < String > awards) {
        super(first_name, last_name, nationality,gender, age);
        this.movies = movies;
        this.awards = awards;
    }


    public void writeActorToFile(Actor actor) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACTORS_FILE_PATH, true))) {
            writer.write("Firstname: " + actor.getFirst_name());
            writer.newLine();
            writer.write("Lastname: " + actor.getLast_name());
            writer.newLine();
            writer.write("Nationality: " + actor.getNationality());
            writer.newLine();
            writer.write("Age: " + actor.getAge());
            writer.newLine();
            writer.write("Gender: " + actor.getGender());
            writer.newLine();
            writer.write("Movies: " + actor.getMovies());
            writer.newLine();
            writer.write("Awards: " + actor.getAwards());
            writer.newLine();
            writer.newLine(); // Add an extra newline to separate actor entries
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void extractActorInfo(String firstName, String lastName, String infoKey) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ACTORS_FILE_PATH))) {
            String line;
            boolean actorFound = false;
            boolean infoFound = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Firstname:") && line.substring(10).trim().equalsIgnoreCase(firstName)) {
                    // Check the next line for the last name
                    String nextLine = reader.readLine();
                    if (nextLine != null && nextLine.startsWith("Lastname:") && nextLine.substring(9).trim().equalsIgnoreCase(lastName)) {
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
    public void setNumber_of_awards(int number_of_awards) {
        this.number_of_awards = number_of_awards;
    }
    public int getNumber_of_awards() {
        return number_of_awards;
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