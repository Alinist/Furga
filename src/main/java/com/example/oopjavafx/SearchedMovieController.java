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
import java.time.LocalDate;
import java.util.Calendar;
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
        imdbScore.setText(String.valueOf(movie.getImdb_score()) + "/10");
    }

    public void ChangeScene(ActionEvent event) {
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toWatched.setOnMouseClicked(event -> {
            if(Main.CurrentUser.Watched.size() == 0) {
                Watch_Record watchRecord;
                for (int j = 0; j < User.MovieList.size(); j++) {
                    if (User.MovieList.get(j).getMovieTitle().equalsIgnoreCase(Moviename_ReleaseDate.getText().substring(0, Moviename_ReleaseDate.getText().length() - 7))) {
                        System.out.println("condition3");
                        watchRecord = new Watch_Record(Main.CurrentUser.getUser_ID(), User.MovieList.get(j), 0, LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear());
                        Main.CurrentUser.Watched.add(watchRecord);
                        Main.watchRecordData.add(watchRecord);
                        System.out.println(Main.CurrentUser.Watched.get(0).getUserId());
                        System.out.println(Main.CurrentUser.Watched.size());
                        System.out.println(watchRecord.getMovie().getMovieTitle());
                        System.out.println(watchRecord.getRating());
                        System.out.println(watchRecord.getDate());
                    }
                }
            }
            else {
                for (int i = 0; i < Main.CurrentUser.Watched.size(); i++) {
                    if (Main.CurrentUser.Watched.get(i).getMovie().getMovieTitle().equalsIgnoreCase(Moviename_ReleaseDate.getText().substring(0, Moviename_ReleaseDate.getText().length() - 7))) {
                        System.out.println("condition1");
                        return;
                    }
                }
                Watch_Record watchRecord;
                for (int j = 0; j < User.MovieList.size(); j++) {
                    if (User.MovieList.get(j).getMovieTitle().equalsIgnoreCase(Moviename_ReleaseDate.getText().substring(0, Moviename_ReleaseDate.getText().length() - 7))) {
                        System.out.println("condition2");
                        watchRecord = new Watch_Record(Main.CurrentUser.getUser_ID(), User.MovieList.get(j), 0, LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear());
                        Main.CurrentUser.Watched.add(watchRecord);
                        Main.watchRecordData.add(watchRecord);
                        System.out.println(Main.CurrentUser.Watched.get(0).getUserId());
                        System.out.println(Main.CurrentUser.Watched.size());
                        System.out.println(watchRecord.getMovie().getMovieTitle());
                        System.out.println(watchRecord.getRating());
                        System.out.println(watchRecord.getDate());
                    }
                }
            }
        });
    }
    @FXML
    void WatchMovie(ActionEvent event) throws IOException {

    }
    @FXML
    void AddToLater(ActionEvent event) {

    }

    @FXML
    void AddToWatched(ActionEvent event) {
//        System.out.println();
    }

}
