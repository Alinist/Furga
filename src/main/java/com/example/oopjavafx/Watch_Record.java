package com.example.oopjavafx;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Watch_Record {

    private final String userId;
    private Movie movie;
    private Integer rating = 0; // Integer provide null value as default which is needed for no rating case
    private LocalDate date;

    public Watch_Record(String userId) {
        this.userId = userId;
    }

    public Watch_Record(String userId, Movie movie, int rating, int day, int month, int year) {
        this.userId = userId;
        this.movie = movie;
        this.rating = rating;
        LocalDate date = LocalDate.of(year, month, day);
        setDate(date);
    }

    public Watch_Record() {
        userId = UUID.randomUUID().toString();
    }


    public String getUserId() {
        return userId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        if (rating >= 1 && rating <= 10) {
            this.rating = rating;
        } else {
            this.rating = 0;
        }
    }

    public String getDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateString = date.format(formatter);
        return dateString;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}