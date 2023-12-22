package com.example.oopjavafx;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.*;


public class HomePageController implements Initializable {
    private boolean menuOpened = false;

    private boolean BlockingPaneExists = false;

    private Scene scene1;
    private Stage stage;
    private Parent root;

    @FXML
    private VBox MovieListLayout;

    @FXML
    private Button ForYouButton;

    @FXML
    private Button Later;

    @FXML
    private Button Logout;

    @FXML
    private Button PopularButton;

    @FXML
    private Button TrendingButton;

    @FXML
    private ImageView menu;

    @FXML
    private AnchorPane pane1;

    @FXML
    private AnchorPane pane2;

    @FXML
    private TextField SearchBar;

    @FXML
    Label UserName;

    public void ChangeScene(ActionEvent event) {
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        User.MovieList = Main.moviesData;

        for(int i = 0 ; i < User.MovieList.size() ; i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("SearchedMovie.fxml"));

            try {
                HBox hbox = fxmlLoader.load();
                SearchedMovieController searchedMovieController = fxmlLoader.getController();
                searchedMovieController.setMovieData(User.MovieList.get(i));
                MovieListLayout.getChildren().add(hbox);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ForYouButton.setStyle("-fx-text-fill:  #4dde90");
        TrendingButton.setStyle("-fx-text-fill:  #ffffff");
        PopularButton.setStyle("-fx-text-fill:  #ffffff");

        pane1.setVisible(false);

        FadeTransition fadeTransition=new FadeTransition(Duration.seconds(0.25),pane1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(0.01),pane2);
        translateTransition.setByX(+600);
        translateTransition.play();

        menu.setOnMouseClicked(event -> {
            BlockingPaneExists = false;

            if(!menuOpened) {
                menuOpened = true;
                pane1.setVisible(true);
                FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.25), pane1);
                fadeTransition1.setFromValue(0);
                fadeTransition1.setToValue(0.15);
                fadeTransition1.play();

                TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.25), pane2);
                translateTransition1.setByX(-600);
                translateTransition1.play();
            }
        });

        pane1.setOnMouseClicked(event -> {
            menuOpened = false;
            if(!BlockingPaneExists) {
                BlockingPaneExists = true;
                FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.25), pane1);
                fadeTransition1.setFromValue(0.15);
                fadeTransition1.setToValue(0);
                fadeTransition1.play();

                fadeTransition1.setOnFinished(event1 -> {
                    pane1.setVisible(false);
                });
                TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.25), pane2);
                translateTransition1.setByX(+600);
                translateTransition1.play();
            }
        });

    }

    public static void MakeImageRounded(ImageView im, String path){

        Image myImage = new Image(HomePageController.class.getResourceAsStream(path));
        im.setImage(myImage);

        // set a clip to apply rounded border to the original image.
        Rectangle clip = new Rectangle(im.getFitWidth(), im.getFitHeight());
        clip.setArcHeight(50);
        clip.setArcWidth(50);
        im.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = im.snapshot(parameters, null);


        // store the rounded image in the imageView.
        im.setImage(image);
    }



    public void ForYou_Controller(ActionEvent e){
        ForYouButton.setStyle("-fx-text-fill:  #4dde90");
        TrendingButton.setStyle("-fx-text-fill:  #ffffff");
        PopularButton.setStyle("-fx-text-fill:  #ffffff");
    }

    public void Trending_Controller(ActionEvent e){
        TrendingButton.setStyle("-fx-text-fill:  #4dde90");
        ForYouButton.setStyle("-fx-text-fill:  #ffffff");
        PopularButton.setStyle("-fx-text-fill:  #ffffff");
    }

    public void Popular_Controller(ActionEvent e){
        PopularButton.setStyle("-fx-text-fill:  #4dde90");
        TrendingButton.setStyle("-fx-text-fill:  #ffffff");
        ForYouButton.setStyle("-fx-text-fill:  #ffffff");
    }

    public void Logout(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        ChangeScene(event);
        stage.setResizable(false);
        stage.setWidth(1045);
        stage.setHeight(645);
        stage.setY(180);
        stage.setX(437.5);
    }

    public void WatchedList(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WatchList.fxml"));
        root = loader.load();
        ChangeScene(event);
        stage.setResizable(true);
    }

    public void Later(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Later.fxml"));
        root = loader.load();
        ChangeScene(event);
        stage.setResizable(true);
    }
    public void Search(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Search.fxml"));
        root = loader.load();
        SearchController searchController = loader.getController();
        searchController.SearchBar.setText(SearchBar.getText());
        ChangeScene(event);
        stage.setResizable(true);
    }

    public void Subscription(ActionEvent event) throws IOException {
        Calendar calendar = Calendar.getInstance();
        FXMLLoader loader;
        if(Main.CurrentUser.subscription.CheckIfSubscriptionEnding(calendar)) {
            loader = new FXMLLoader(getClass().getResource("My_Subscription.fxml"));
        } else {
            loader = new FXMLLoader(getClass().getResource("Pricing_plan.fxml"));
        }
        root = loader.load();
        ChangeScene(event);
        stage.setResizable(true);
    }




}