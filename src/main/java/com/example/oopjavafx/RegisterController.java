package com.example.oopjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RegisterController implements Initializable {

    public boolean checkInputs(String email, String password) {
        return Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$").matcher(email).find()
                &&
                Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$").matcher(password).find();
    }

    public String CodeGenerator() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder s = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            s.append(AlphaNumericString.charAt(index));
        }
        return s.toString();
    }

    Thread countdown = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 2; i > 0; i--) {
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println("error");
                }
            }
        }
    });

    public void ChangeScene(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.setResizable(false);
        stage.show();
        stage.setWidth(1045);
        stage.setHeight(645);
    }

    private Scene scene1;
    private Stage stage;
    private Parent root;

    @FXML
    TextField f_name;
    @FXML
    TextField l_name;
    @FXML
    TextField email;
    @FXML
    TextField password;
    @FXML
    Label errorText;
    @FXML
    TextField confirmedPassword;
    @FXML
    Label RandomCode;
    @FXML
    TextField Code;

    public void ChangetoLogin(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        ChangeScene(event);
    }

    public void Register(ActionEvent event) throws Exception {
        errorText.setTextFill(Color.RED);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        root = loader.load();
        LoginController loginController = loader.getController();
        if (!checkInputs(email.getText(), password.getText())) {
            errorText.setText("Incorrect format for password or email!");
            countdown.start();
            while (countdown.isAlive()) {
            }
        } else if (!confirmedPassword.getText().equals(password.getText())) {
            errorText.setText("Passwords doesn't match!");
        } else if (!Code.getText().equals(RandomCode.getText())) {
            errorText.setText("Incorrect Code!");
        } else {
            try {
                boolean found = false;
                for (User u : Main.userData) {
                    if (email.getText().equals(u.getUser_email())) {
                        errorText.setText("User already registered!");
                        return;
                    } else if (found == true) {
                        break;
                    }
                }
            } catch (Exception e) {
            }
            try {
                User u = new User();
                u.setFirst_name(f_name.getText());
                u.setLast_name(l_name.getText());
                u.setUser_email(email.getText());
                u.setUser_password(password.getText());
                Main.userData.add(u);
                
            } catch (Exception e) {
                return;
            }
            loginController.email.setText(email.getText());
            loginController.errorText.setTextFill(Color.valueOf("#4dde90"));
            loginController.errorText.setText("Registered Successfully !");
            ChangeScene(event);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RandomCode.setText(CodeGenerator());
    }
    
}
