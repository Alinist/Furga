package com.example.oopjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
