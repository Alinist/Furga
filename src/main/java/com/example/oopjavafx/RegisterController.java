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
                String readLine = "";
                FileReader reader = new FileReader("src\\main\\resources\\Files\\users.txt");
                int c;
                while ((c = reader.read()) != -1) {
                    readLine += (char) c;
                }
                reader.close();
                String fnameReadText = "", lnameReadText = "", emailReadText = "", pwReadText = "";
                String userStringData[] = { fnameReadText, lnameReadText, emailReadText, pwReadText };
                ArrayList<User> userData = new ArrayList<User>();
                int dataIndex = 0;
                for (int i = 0; i < readLine.length(); i++) {
                    int length = 0;
                    userStringData[dataIndex] = "";
                    String lengthSubsStr = readLine.substring(i, readLine.indexOf('#', i));
                    System.out.println("STRING: " + lengthSubsStr);
                    System.out.println(i);

                    length = Integer.parseInt(lengthSubsStr);
                    System.out.println(length);
                    i = readLine.indexOf('#', i);
                    for (int j = 1; j < length + 1; j++) {
                        userStringData[dataIndex] += readLine.charAt(i + j);
                    }
                    i += userStringData[dataIndex].length();
                    System.out.println(userStringData[dataIndex]); // For testing
                    dataIndex++;
                    if (dataIndex == 4) {
                        dataIndex = 0;
                        User u = new User();
                        u.setFirst_name(userStringData[0]);
                        u.setLast_name(userStringData[1]);
                        u.setUser_email(userStringData[2]);
                        u.setUser_password(userStringData[3]);
                        userData.add(u);
                        System.out.println( // For testing
                                "User: " + u.getFirst_name() + " " + u.getLast_name() + " " + u.getUser_email() + " "
                                        + u.getUser_password());
                    }
                }
                boolean found = false;
                for (User u : userData) {
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
                String readLine = "";
                FileReader reader = new FileReader("src\\main\\resources\\Files\\users.txt");
                int c;
                while ((c = reader.read()) != -1) {
                    readLine += (char) c;
                }
                reader.close();
                FileWriter writer = new FileWriter("src\\main\\resources\\Files\\users.txt");
                writer.write(f_name.getText().length() + "#" + f_name.getText());
                writer.write(l_name.getText().length() + "#" + l_name.getText());
                writer.write(email.getText().length() + "#" + email.getText());
                writer.write(password.getText().length() + "#" + password.getText());
                writer.write(readLine);
                writer.close();
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
