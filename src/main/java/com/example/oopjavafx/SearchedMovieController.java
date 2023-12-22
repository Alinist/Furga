package com.example.oopjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchedMovieController implements Initializable {
    private Scene scene1;
    private Stage stage;
    private Parent root;

    @FXML
    private Button WatchNow;

    @FXML
    private Button toLater;

    @FXML
    private Button toWatched;

    @FXML
    private Label Moviename_ReleaseDate;
    @FXML
    private Label Actors;
    @FXML
    private Label Directors;
    @FXML
    private Label Genres;
    @FXML
    private Label Language;
    @FXML
    private Label RunningTime;
    @FXML
    private Label imdbScore;
    @FXML
    private ImageView MoviePoster;

    private String actors = "";
    private String genres = "";

    public void setMovieData(Movie movie) {
        Moviename_ReleaseDate.setText(movie.getMovieTitle() + " (" +movie.getReleaseDate() + ")");
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
        MoviePoster.setImage(movieposter);
        System.out.println(movie.getMovieTitle());
//        HomePageController.MakeImageRounded(MoviePoster,"Images/Harry Potter and the Chamber of Secrets.png");
    }

    public void ChangeScene(ActionEvent event) {
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    void WatchMovie(ActionEvent event) throws IOException {

    }
    @FXML
    void AddToLater(ActionEvent event) {

    }

    @FXML
    void AddToWatched(ActionEvent event) {

    }

}
