package com.example.oopjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class PersonController {

    @FXML
    private Label Age;

    @FXML
    private Label Gender;

    @FXML
    private Label MoviesParticipatedIn;

    @FXML
    private Label Nationality;

    @FXML
    private ImageView PersonPic;

    @FXML
    private Label fullName;

    private String movies = "";

    public void setDirectorData(Director director) {
        fullName.setText(director.getFirst_name() + " " + director.getLast_name());
        Age.setText(String.valueOf(director.getAge()));
        Gender.setText(director.getGender());
        Nationality.setText(director.getNationality() + " " + "Director");
        Image personPic = new Image(String.valueOf(getClass().getResource("Images/" + director.getFirst_name() + " " + director.getLast_name() + ".png")));
        PersonPic.setImage(personPic);
        for(int i = 0 ; i < User.MovieList.size() ; i++) {
            if(User.MovieList.get(i).getMovieDirector().equalsIgnoreCase(director.getFirst_name() + " " + director.getLast_name())) {
                if(i != User.MovieList.size() - 1) {
                    movies += User.MovieList.get(i).getMovieTitle() + " - ";
                } else {
                    movies += User.MovieList.get(i).getMovieTitle();
                }
            }
        }
        MoviesParticipatedIn.setText(movies);
    }
    public void setActorData(Actor actor) {
        fullName.setText(actor.getFirst_name() + " " + actor.getLast_name());
        Age.setText(String.valueOf(actor.getAge()));
        Gender.setText(actor.getGender());
        Nationality.setText(actor.getNationality() + " " + "Actor");
        Image personPic = new Image(String.valueOf(getClass().getResource("Images/" + actor.getFirst_name() + " " + actor.getLast_name() + ".png")));
        PersonPic.setImage(personPic);
        for(int i = 0 ; i < User.MovieList.size() ; i++) {
            for(int j = 0 ; j < User.MovieList.get(i).getCast().size() ; j++) {
                if(User.MovieList.get(i).getCast().get(j).getFirst_name().equals(actor.getFirst_name()) && User.MovieList.get(i).getCast().get(j).getLast_name().equals(actor.getLast_name())) {
                    if(i != User.MovieList.size() - 1) {
                        movies += User.MovieList.get(i).getMovieTitle() + " - ";
                    } else {
                        movies += User.MovieList.get(i).getMovieTitle();
                    }
                }
            }
        }
        MoviesParticipatedIn.setText(movies);
    }
}
