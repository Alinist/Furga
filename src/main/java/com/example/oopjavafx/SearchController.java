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
import javafx.scene.control.ComboBox;
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
import java.util.List;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    private boolean menuOpened = false;

    private boolean BlockingPaneExists = false;

    private Scene scene1;
    private Stage stage;
    private Parent root;

    public void ChangeScene(ActionEvent event) {
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();
    }

    private String[] sortbyItems = {"Any", "Rating", "Release Date"};

    private String[] genreItems = {"Any", "Action", "Comedy", "Adventure"};

    private String[] typeItems = {"Any", "Movie", "Director", "Actor"};

    @FXML
    private ComboBox<String> GenreBox;
    @FXML
    private ComboBox<String> SortByBox;

    @FXML
    private ComboBox<String> TypeBox;

    @FXML
    private VBox MovieListLayout;

    @FXML
    TextField SearchBar;


    @FXML
    private Button Furga;

    @FXML
    private Button Later;

    @FXML
    private Button Logout;

    @FXML
    private ImageView menu;

    @FXML
    private AnchorPane pane1;

    @FXML
    private AnchorPane pane2;

    @FXML
    private Label UserName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserName.setText(Main.CurrentUser.getFirst_name() + " " + Main.CurrentUser.getLast_name());

        SortByBox.getItems().addAll(sortbyItems);
        GenreBox.getItems().addAll(genreItems);
        TypeBox.getItems().addAll(typeItems);
        SortByBox.getSelectionModel().selectFirst();
        GenreBox.getSelectionModel().selectFirst();
        TypeBox.getSelectionModel().selectFirst();
        ArrayList<Actor> actors = new ArrayList<>(Actors());
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
        for(int i = 0 ; i < actors.size() ; i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Person.fxml"));

            try {
                HBox hbox = fxmlLoader.load();
                PersonController actorController = fxmlLoader.getController();
                MovieListLayout.getChildren().add(hbox);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        for(int i = 0 ; i < actors.size() ; i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Person.fxml"));

            try {
                HBox hbox = fxmlLoader.load();
                PersonController actorController = fxmlLoader.getController();
                MovieListLayout.getChildren().add(hbox);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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

        movie.setMovieTitle("avengers");
        Movies.add(movie);

        movie.setMovieTitle("spiderman");
        Movies.add(movie);

        movie.setMovieTitle("avengers");
        Movies.add(movie);

        movie.setMovieTitle("spiderman");
        Movies.add(movie);

        return Movies;
    }

    private ArrayList<Actor> Actors() {
        String[] movies = {"avengers", "spiderman"};
        String[] awards = {"best actor", "most beloved actor"};
        ArrayList<Actor> Actors = new ArrayList<>();
        Actor actor1 = new Actor("Ali","Ashraf", "Egyption", "Male", 19,new ArrayList<String>(List.of(movies)),new ArrayList<String>(List.of(movies)));
        Actors.add(actor1);
        return Actors;
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

    @FXML
    void WatchedList(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("WatchList.fxml"));
        ChangeScene(event);
    }

    public void Subscription(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Pricing_plan.fxml"));
        root = loader.load();
        ChangeScene(event);
        stage.setResizable(true);
    }
}
