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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class WatchedMovieController implements Initializable {

    @FXML
    public HBox WatchedMovie;

    private Scene scene1;
    private Stage stage;
    private Parent root;

    @FXML
    private Button Rate;
    @FXML
    public Button Remove;

    @FXML
    private Button WatchNow;
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
    int index = 0;

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Remove.setOnMouseClicked(event -> {
            index = 0;
            for (int j = 0; j < Main.CurrentUser.Watched.size(); j++) {
                if (Main.CurrentUser.Watched.get(j).getMovie().getMovieTitle().equalsIgnoreCase(MovieName_ReleaseDate.getText().substring(0, MovieName_ReleaseDate.getText().length() - 7))) {
                    System.out.println("found");
                    index = j;
                    break;
                }
            }
            Main.CurrentUser.Watched.remove(Main.CurrentUser.Watched.get(index));
            Main.watchRecordData.remove(index);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WatchList.fxml"));
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ChangeScene(event);
            stage.setResizable(true);
        });

        Rate.setOnMouseClicked(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MovieScene.fxml"));
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            MovieSceneController movieSceneController = loader.getController();
            movieSceneController.MovieName.setText(MovieName_ReleaseDate.getText().substring(0,MovieName_ReleaseDate.getText().length() - 7));
            movieSceneController.movieTitle.setText(MovieName_ReleaseDate.getText().substring(0,MovieName_ReleaseDate.getText().length() - 7));
            movieSceneController.MoviePoster.setImage(moviePoster.getImage());
            for(int i = 0 ; i < Main.watchRecordData.size() ; i++) {
                if(Main.watchRecordData.get(i).getMovie().getMovieTitle().equalsIgnoreCase(MovieName_ReleaseDate.getText().substring(0,MovieName_ReleaseDate.getText().length() - 7)) && Main.watchRecordData.get(i).getUserId().equalsIgnoreCase(Main.CurrentUser.getUser_ID())) {
                    movieSceneController.yourRating.setText(String.valueOf(Main.watchRecordData.get(i).getRating()) + "/10");
                }
            }
            for(int i = 0 ; i < User.MovieList.size() ; i++) {
                if(User.MovieList.get(i).getMovieTitle().equalsIgnoreCase(MovieName_ReleaseDate.getText().substring(0,MovieName_ReleaseDate.getText().length() - 7))) {
                    for(int j = 0 ; j < Main.watchRecordData.size() ; j++) {
                        if(User.MovieList.get(i).getMovieTitle().equalsIgnoreCase(MovieName_ReleaseDate.getText().substring(0,MovieName_ReleaseDate.getText().length() - 7))) {
                            User.MovieList.get(i).Ratings.add((float) Main.watchRecordData.get(j).getRating());
                            Main.CurrentUser.Watched.get(i).getMovie().Ratings.set(i , Float.parseFloat(String.valueOf(Main.watchRecordData.get(j).getRating())));
                        }
                    }
                    imdbRating.setText(String.valueOf(User.MovieList.get(i).getImdb_score()));
                }
            }
            for(int i = 0 ; i < Main.watchRecordData.size() ; i++) {
                if(Main.watchRecordData.get(i).getMovie().getMovieTitle().equalsIgnoreCase(MovieName_ReleaseDate.getText().substring(0,MovieName_ReleaseDate.getText().length() - 7)) && Main.watchRecordData.get(i).getUserId().equalsIgnoreCase(Main.CurrentUser.getUser_ID())) {
                    movieSceneController.imdbRating.setText(String.valueOf(Main.watchRecordData.get(i).getMovie().getImdb_score() + "/10"));
                }
            }
            ChangeScene(event);
        });
    }

    public void ChangeScene(ActionEvent event) {
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();
    }
    public void ChangeScene(MouseEvent event) {
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();
    }

    @FXML
    void RateMovie(ActionEvent event) throws IOException {

    }

    @FXML
    void RemoveFromWatched(ActionEvent event) throws IOException {

    }

    @FXML
    void WatchMovie(ActionEvent event) {

    }

}
