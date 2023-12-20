package com.example.oopjavafx;

import java.util.ArrayList;

public class Watch_Record {

    private final int userId;
    private Movie movie;
    private Integer rating; //Integer provide null value as default which is needed for no rating case
    private int day;
    private int month;
    private int year;

    public Watch_Record(int userId) {
        this.userId = userId;
    }

    public Watch_Record(int userId, Movie movie, int rating, int day, int month, int year) {
        this.userId = userId;
        this.movie = movie;
        this.rating = rating;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getUserId() {
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
        if (rating == null || rating >= 0 && rating < 5) {
            this.rating = rating;
        }
    }


    public void getDate() {
        System.out.println(day + "/" + month + "/" + year);
    }

    public void setDate(int d, int m, int y) {
        day = d;
        month = m;
        year = y;
    }

    void showThisWatchRecordDetails(Watch_Record watchRecord) {
        System.out.println("User ID: " + watchRecord.userId);
        System.out.println("Movie Name: " + watchRecord.movie);
        System.out.println("Date: " + day + "/" + month + "/" + year);
        System.out.println("Movie Rating : " + watchRecord.rating);
    }

    void getLastWatchRecord(ArrayList<Watch_Record> watchRecords) {
        if (!watchRecords.isEmpty()) {
            int size_w = watchRecords.size() - 1;
            System.out.println(watchRecords.get(size_w));
        } else {
            System.out.println("Watch Record is Empty!");
        }
    }

    void getAllWatchRecord(ArrayList<Watch_Record> watchRecords) {
        if (!watchRecords.isEmpty()) {

            for (Watch_Record wr : watchRecords) {
                showThisWatchRecordDetails(wr);
            }
        } else {
            System.out.println("Watch Record is Empty!");
        }
    }

    void deletelastRecord(ArrayList<Watch_Record> watchRecords) {
        if (!watchRecords.isEmpty()) {
            int size_w = watchRecords.size() - 1;
            watchRecords.remove(size_w);
        } else System.out.println("Records are Already Empty!");
    }

    void deleteAllRecords(ArrayList<Watch_Record> watchRecords) {
        if (!watchRecords.isEmpty()) {
            int size_w = watchRecords.size() - 1;
            for (int i = size_w - 1; i >= 0; i--) {
                deletelastRecord(watchRecords);
            }
        } else System.out.println("Records are Already Empty!");
    }
}
