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
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class LoginController implements Initializable {

    public boolean checkInputs(String email, String password) {
        return Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$").matcher(email).find()
                &&
                Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$").matcher(password).find();
    }

    private Scene Register;

    private Scene scene;
    private Stage stage;
    private Parent root;

    public void ChangeScene(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    User user = new User();

    @FXML
    TextField email;
    @FXML
    TextField password;
    @FXML
    Label errorText;

    public void ChangetoRegister(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        ChangeScene(event);
        errorText.setTextFill(Color.RED);
        stage.setResizable(false);
        stage.setWidth(1076);
        stage.setHeight(818);
    }

    public void Login(ActionEvent event) throws Exception {
        if (!checkInputs(email.getText(), password.getText())) {
            errorText.setTextFill(Color.RED);
            errorText.setText("Invalid inputs !! Try again");
        }
        try {
            boolean found = false;
            for (User u : Main.userData) {
                if (u.getUser_password().equals(password.getText()) && u.getUser_email().equals(email.getText())) {
                    found = true;
                    errorText.setTextFill(Color.valueOf("#4dde90"));
                    errorText.setText("Login Successfully!");
                    root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                    ChangeScene(event);
                    stage.setResizable(true);
                    stage.setWidth(1920);
                    stage.setHeight(1080);
                    stage.setY(0);
                    stage.setX(0);
                    // Button newButton = new Button();
                    // root.
                } else if (found == true) {
                    break;
                }
            }
            if (!found) {
                errorText.setTextFill(Color.RED);
                errorText.setText("User or password does not match!");
            }
        } catch (Exception e) {
            // errorText.setText(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
