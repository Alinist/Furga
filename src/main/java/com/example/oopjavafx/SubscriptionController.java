package com.example.oopjavafx;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SubscriptionController implements Initializable {
    public void ChangeScene(ActionEvent event) {
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();
    }
    private boolean menuOpened = false;

    private boolean BlockingPaneExists = false;

    @FXML
    private Button Furga;

    @FXML
    private Button Later;

    @FXML
    private Button Logout;

    @FXML
    private TextField SearchBar;

    @FXML
    private Label UserName;

    @FXML
    private ImageView menu;

    @FXML
    private AnchorPane pane1;

    @FXML
    private AnchorPane pane2;

    @FXML
    private Label MaxNoOfMovies;

    @FXML
    private Label NoOfWatchedMovies;
    @FXML
    private Label fName;
    @FXML
    private Label lName;
    @FXML
    private Label subscription;

    @FXML
    private Button renewSubscibtionButton;

    private Scene scene1;
    private Stage stage;
    private Parent root;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WatchListController watchListController = new WatchListController();
        fName.setText(Main.CurrentUser.getFirst_name());
        lName.setText(Main.CurrentUser.getLast_name());
        subscription.setText(Main.CurrentUser.subscription.plan.typeOfPlan);
        NoOfWatchedMovies.setText(String.valueOf(Main.CurrentUser.subscription.plan.numberOfMovies));
        if(Main.CurrentUser.subscription.plan.typeOfPlan.equalsIgnoreCase("basic")) {
            MaxNoOfMovies.setText("5");
        }
        else if (Main.CurrentUser.subscription.plan.typeOfPlan.equalsIgnoreCase("standard")){
            MaxNoOfMovies.setText("10");
        }
        else if (Main.CurrentUser.subscription.plan.typeOfPlan.equalsIgnoreCase("premium")){
            MaxNoOfMovies.setText("30");
        }

        UserName.setText(Main.CurrentUser.getFirst_name() + " " + Main.CurrentUser.getLast_name());
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

    public void Search(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Search.fxml"));
        root = loader.load();
        SearchController searchController = loader.getController();
        searchController.SearchBar.setText(SearchBar.getText());
        ChangeScene(event);
        stage.setResizable(true);
        stage.setResizable(true);
    }

    @FXML
    void subscribeToBasic(ActionEvent event) throws IOException {
        Main.CurrentUser.subscription.setPriceOfPlan(10);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("My_Subscription.fxml"));
        root = loader.load();
        ChangeScene(event);
    }
    @FXML
    void subscribeToStandard(ActionEvent event) throws IOException {
        Main.CurrentUser.subscription.setPriceOfPlan(20);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("My_Subscription.fxml"));
        root = loader.load();
        ChangeScene(event);
    }
    @FXML
    void subscribeToPremium(ActionEvent event) throws IOException {
        Main.CurrentUser.subscription.setPriceOfPlan(30);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("My_Subscription.fxml"));
        root = loader.load();
        ChangeScene(event);
    }

    @FXML
    void cancelSubscirption(ActionEvent event) throws IOException {
        Main.CurrentUser.Cancel_Subscription();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Pricing_plan.fxml"));
        root = loader.load();
        ChangeScene(event);
    }

    @FXML
    void renewSubscription(ActionEvent event) {
        Main.CurrentUser.EditSubscription(Main.CurrentUser.subscription.PriceOfPlan);
        renewSubscibtionButton.setDisable(true);
    }
}
