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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

public class WatchListController implements Initializable{

    private boolean menuOpened = false;

    private boolean BlockingPaneExists = false;

    private Scene scene1;
    private Stage stage;
    private Parent root;

    ArrayList<Movie> movies = new ArrayList<>(Movies());

    public void ChangeScene(ActionEvent event) {
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();
    }

    @FXML
    private TextField SearchBar;

    @FXML
    private Button Furga;

    @FXML
    private Button Later;

    @FXML
    private Button Logout;

    @FXML
    VBox MovieListLayout;

    @FXML
    private ImageView menu;

    @FXML
    private AnchorPane pane1;

    @FXML
    private AnchorPane pane2;

    @FXML
    Label UserName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserName.setText(Main.CurrentUser.getFirst_name() + " " + Main.CurrentUser.getLast_name());
        for(int i = 0 ; i < Main.CurrentUser.Watched.size() ; i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("WatchedMovie.fxml"));

            try {
                HBox hbox = fxmlLoader.load();
                WatchedMovieController watchedMovieController = fxmlLoader.getController();
                watchedMovieController.setMovieData(Main.CurrentUser.Watched.get(i).getMovie());
                MovieListLayout.getChildren().add(hbox);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(Main.CurrentUser.Watched.size());
        System.out.println(Main.laterLists.size());

        pane1.setVisible(false);

        FadeTransition fadeTransition=new FadeTransition(Duration.seconds(0.25),pane1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(0.25),pane2);
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

    private ArrayList<Movie> Movies() {
        ArrayList<Movie> Movies = new ArrayList<>();
        Movie movie = new Movie();

        movie.setMovieTitle("avengers");
        Movies.add(movie);

        movie.setMovieTitle("spiderman");
        Movies.add(movie);

        return Movies;
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

    public void HomePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        root = loader.load();
        HomePageController homePageController = loader.getController();
        homePageController.UserName.setText(Main.CurrentUser.getFirst_name() + " " + Main.CurrentUser.getLast_name());
        ChangeScene(event);
    }

    public void Later(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Later.fxml"));
        ChangeScene(event);
        stage.setResizable(true);
    }

    public void Search(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Search.fxml"));
        root = loader.load();
        SearchController searchController = loader.getController();
        searchController.SearchBar.setText(SearchBar.getText());
        searchController.search(event);
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
