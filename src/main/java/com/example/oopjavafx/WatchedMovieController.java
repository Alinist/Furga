package com.example.oopjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class WatchedMovieController {

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

    public void ChangeScene(ActionEvent event) {
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();
    }

    @FXML
    void RateMovie(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MovieScene.fxml"));
        ChangeScene(event);
    }

    @FXML
    void RemoveFromWatched(ActionEvent event) throws IOException {

    }

    @FXML
    void WatchMovie(ActionEvent event) {

    }
}
