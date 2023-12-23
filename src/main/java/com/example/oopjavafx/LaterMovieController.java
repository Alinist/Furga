package com.example.oopjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class LaterMovieController implements Initializable {
    @FXML
    private Button Remove;

    @FXML
    private Button WatchNow;

    @FXML
    private Button toWatched;
    @FXML
    private Label Actors;
    @FXML
    private Label Directors;
    @FXML
    private Label Genres;
    @FXML
    private Label Language;
    @FXML
    private Label MovieName_ReleaseDate;
    @FXML
    private ImageView moviePoster;
    @FXML
    private Label imdbRating;
    @FXML
    private Label RunningTime;

    private String actors = "";
    private String genres = "";

    public void setMovieData(Movie movie) {
        MovieName_ReleaseDate.setText(movie.getMovieTitle() + " (" +movie.getReleaseDate() + ")");
        for (int i = 0 ; i < movie.getCast().size() ; i++) {
            if(i != movie.getCast().size()-1) {
                actors += movie.getCast().get(i).getFirst_name() + " " + movie.getCast().get(i).getLast_name() + ",";
            } else {
                actors += movie.getCast().get(i).getFirst_name() + " " + movie.getCast().get(i).getLast_name();
            }
        }
        Actors.setText(actors);
        Directors.setText(movie.getMovieDirector());
        for (int i = 0 ; i < movie.getGenres().length ; i++) {
            if(i != movie.getGenres().length-1) {
                genres += movie.getGenres()[i] + "/";
            }else {
                genres += movie.getGenres()[i];
            }
        }
        Genres.setText(genres);
        Language.setText(movie.Language);
        RunningTime.setText(String.valueOf((movie.getRunningTime()/60)) + "h " + String.valueOf(movie.getRunningTime()-60*(movie.getRunningTime()/60)) + "min");
        Image movieposter = new Image(String.valueOf(getClass().getResource("Images/" + movie.getMovieTitle() + ".png")));
        moviePoster.setImage(movieposter);
        imdbRating.setText(movie.getImdb_score() + "/10");
    }

    @FXML
    void AddToWatched(ActionEvent event) {

    }

    @FXML
    void RemoveFromLater(ActionEvent event) {

    }

    @FXML
    void WatchMovie(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
