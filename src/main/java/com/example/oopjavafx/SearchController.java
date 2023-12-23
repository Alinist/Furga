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
import java.util.Calendar;
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

    private String[] genreItems = {"Any", "Action", "Comedy", "Adventure" , "Fantasy" , "Horror"};

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

    public void setMovies (String genre) {
        ArrayList<Movie> SearchedMovies = User.SearchMovieByName(SearchBar.getText());
        ArrayList<Movie> SearchedGenreMovies = new ArrayList<>();
        if(!genre.equalsIgnoreCase("any")) {
            for(int i = 0 ; i < SearchedMovies.size() ; i++) {
                for(int j = 0 ; j < SearchedMovies.get(i).getGenres().length ; j++) {
                    if(SearchedMovies.get(i).getGenres()[j].equalsIgnoreCase(genre)) {
                        SearchedGenreMovies.add(SearchedMovies.get(i));
                    }
                }
//                if(SortByBox.getValue().equalsIgnoreCase("rating")) {
//                    SearchedGenreMovies = User.GetTopRatedMovies();
//                }
            }
            for(int i = 0 ; i < SearchedGenreMovies.size() ; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("SearchedMovie.fxml"));

                try {
                    HBox hbox = fxmlLoader.load();
                    SearchedMovieController searchedMovieController = fxmlLoader.getController();
                    searchedMovieController.setMovieData(SearchedGenreMovies.get(i));
                    MovieListLayout.getChildren().add(hbox);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            if(SortByBox.getValue().equalsIgnoreCase("rating")) {
                SearchedMovies = User.GetTopRatedMovies();
            } else {
                SearchedMovies = User.SearchMovieByName(SearchBar.getText());
            }
            for (int i = 0; i < SearchedMovies.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("SearchedMovie.fxml"));

                try {
                    HBox hbox = fxmlLoader.load();
                    SearchedMovieController searchedMovieController = fxmlLoader.getController();
                    searchedMovieController.setMovieData(SearchedMovies.get(i));
                    MovieListLayout.getChildren().add(hbox);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void setActors () {
        ArrayList<Actor> SearchedActors = User.SearchActorByName(SearchBar.getText());
        for(int i = 0 ; i < SearchedActors.size() ; i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Person.fxml"));

            try {
                HBox hbox = fxmlLoader.load();
                PersonController actorController = fxmlLoader.getController();
                actorController.setActorData(SearchedActors.get(i));
                MovieListLayout.getChildren().add(hbox);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setDirectors() {
        ArrayList<Director> SearchedDirectors = User.SearchDirectorByName(SearchBar.getText());
        for(int i = 0 ; i < SearchedDirectors.size() ; i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Person.fxml"));

            try {
                HBox hbox = fxmlLoader.load();
                PersonController directorController = fxmlLoader.getController();
                directorController.setDirectorData(SearchedDirectors.get(i));
                MovieListLayout.getChildren().add(hbox);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


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
        pane1.setVisible(false);

        FadeTransition fadeTransition=new FadeTransition(Duration.seconds(0.25),pane1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
        TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(0.25),pane2);
        translateTransition.setByX(+600);
        translateTransition.play();

        setMovies("Any");
        setActors();
        setDirectors();

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

    public void search(ActionEvent event) {
        MovieListLayout.getChildren().clear();
        if(TypeBox.getValue().equalsIgnoreCase("actor")) {
            setActors();
            SortByBox.setDisable(true);
            GenreBox.setDisable(true);
        } else if (TypeBox.getValue().equalsIgnoreCase("director")) {
            setDirectors();
            SortByBox.setDisable(true);
            GenreBox.setDisable(true);
        } else if (TypeBox.getValue().equalsIgnoreCase("movie")){
            if(SortByBox.getValue().equalsIgnoreCase("rating")) {
                GenreBox.setDisable(true);
            }
            SortByBox.setDisable(false);
            GenreBox.setDisable(false);
            setMovies(GenreBox.getValue());
        } else {
            if(SortByBox.getValue().equalsIgnoreCase("rating")) {
                setMovies("Any");
                SortByBox.setDisable(false);
                GenreBox.setValue("Any");
                TypeBox.setValue("Movie");
                GenreBox.setDisable(true);
//                TypeBox.setDisable(true);
            } else {
                setMovies(GenreBox.getValue());
                setActors();
                setDirectors();
                SortByBox.setDisable(false);
                GenreBox.setDisable(false);
                TypeBox.setDisable(false);
            }
        }
    }
}
