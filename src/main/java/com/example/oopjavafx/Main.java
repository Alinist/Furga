package com.example.oopjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.Calendar;
import java.util.stream.Collectors;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends Application {
    public static User CurrentUser;
    public static ArrayList<Movie> moviesData = new ArrayList<Movie>();
    public static ArrayList<Actor> actorsData = new ArrayList<Actor>();
    public static ArrayList<Director> directorData = new ArrayList<Director>();
    public static ArrayList<User> userData = new ArrayList<User>();
    public static ArrayList<Watch_Record> watchRecordData = new ArrayList<Watch_Record>();
    public static HashMap<String, ArrayList<Movie>> laterLists = new HashMap<>();
    // HashMap<String, ArrayList<Movie>> historyLists = new HashMap<>();

    public void readWatchRecord() {
        try {
            String readLine = "";
            FileReader reader = new FileReader("src\\main\\resources\\Files\\watchrecord.txt");
            int c;
            while ((c = reader.read()) != -1) {
                readLine += (char) c;
            }
            reader.close();
            String currentValue = new String();

            for (char character : readLine.toCharArray()) {
                if (character != ':') {
                    currentValue += character;
                } else {
                    String[] userIdMovieIdRating = currentValue.split("/");
                    Watch_Record wr = new Watch_Record(userIdMovieIdRating[0],
                            Movie.getMovieById(userIdMovieIdRating[1]), Integer.parseInt(userIdMovieIdRating[2]),
                            Integer.parseInt(userIdMovieIdRating[3]), Integer.parseInt(userIdMovieIdRating[4]),
                            Integer.parseInt(userIdMovieIdRating[5]));
                    watchRecordData.add(wr);
                    currentValue = "";
                    System.out.println(
                            "Watch Record: " + User.getUserById(watchRecordData.get(0).getUserId()).getFirst_name()
                                    + " " + watchRecordData.get(0).getMovie().getMovieTitle() + " "
                                    + watchRecordData.get(0).getRating());
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeWatchRecord() {
        try {
            FileWriter writer = new FileWriter("src\\main\\resources\\Files\\watchrecord.txt");
            for (Watch_Record wr : watchRecordData) {
                writer.write(wr.getUserId() + "/" + wr.getMovie().getMovieID() + "/" + wr.getRating() + "/"
                        + wr.getDateString() + ":");
            }
            writer.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void readLaterLists() {
        try {
            String readLine = "";
            FileReader reader = new FileReader("src\\main\\resources\\Files\\laterlists.txt");
            int c;
            while ((c = reader.read()) != -1) {
                readLine += (char) c;
            }
            reader.close();
            String currentValue = new String();

            for (char character : readLine.toCharArray()) {
                if (character != ':') {
                    currentValue += character;
                } else {
                    String[] userIdAndMovies = currentValue.split("#");
                    ArrayList<String> movieIdList = new ArrayList<>(Arrays.asList(userIdAndMovies[1].split(",")));
                    ArrayList<Movie> laterMovies = new ArrayList<>();
                    for (String s : movieIdList) {
                        laterMovies.add(Movie.getMovieById(s));
                    }
                    laterLists.put(userIdAndMovies[0], laterMovies);
                    currentValue = "";
                    System.out.println("Later List: "
                            + Movie.getMovieById(laterLists.get(userIdAndMovies[0]).get(0).getMovieID())
                            .getMovieTitle());
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeLaterLists() {
        try {
            FileWriter writer = new FileWriter("src\\main\\resources\\Files\\laterlists.txt");
            for (String uid : laterLists.keySet()) {
                int index = 0;
                writer.write(uid + "#" + String.join(",", laterLists.get(uid).get(index).getMovieID()) + ":");
                index++;
            }
            writer.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // public void readHistoryLists() {
    // try {
    // String readLine = "";
    // FileReader reader = new
    // FileReader("src\\main\\resources\\Files\\historylists.txt");
    // int c;
    // while ((c = reader.read()) != -1) {
    // readLine += (char) c;
    // }
    // reader.close();
    // String currentValue = new String();

    // for (char character : readLine.toCharArray()) {
    // if (character != ':') {
    // currentValue += character;
    // } else {
    // String[] userIdAndMovies = currentValue.split("#");
    // ArrayList<String> movieIdList = new
    // ArrayList<>(Arrays.asList(userIdAndMovies[1].split(",")));
    // historyLists.put(userIdAndMovies[0], movieIdList);
    // currentValue = "";
    // System.out.println("History List: " + historyLists.get(userIdAndMovies[0]));
    // }
    // }

    // } catch (Exception e) {
    // System.out.println(e.getMessage());
    // }
    // }

    // public void writeHistoryLists() {
    // try {
    // FileWriter writer = new
    // FileWriter("src\\main\\resources\\Files\\historylists.txt");
    // for (String uid : historyLists.keySet()) {
    // writer.write(uid + "#" + String.join(",", historyLists.get(uid)) + ":");
    // }
    // writer.close();

    // } catch (Exception e) {
    // System.out.println(e.getMessage());
    // }
    // }

    public void readUsers() {
        try {
            String readLine = "";
            FileReader reader = new FileReader("src\\main\\resources\\Files\\users.txt");
            int c;
            while ((c = reader.read()) != -1) {
                readLine += (char) c;
            }
            reader.close();
            String userStringData[] = new String[10];
            int dataIndex = 0;
            for (int i = 0; i < readLine.length(); i++) {

                int length = 0;
                userStringData[dataIndex] = "";
                String lengthSubsStr = readLine.substring(i, readLine.indexOf('#', i));

                length = Integer.parseInt(lengthSubsStr);
                i = readLine.indexOf('#', i);

                for (int j = 1; j < length + 1; j++) {
                    userStringData[dataIndex] += readLine.charAt(i + j);
                }
                i += userStringData[dataIndex].length(); // PROBLEM HERE
                dataIndex++;
                if (dataIndex == 7) {
                    dataIndex = 0;
                    User u = new User(userStringData[4], Integer.parseInt(userStringData[5]));
                    u.setFirst_name(userStringData[0]);
                    u.setLast_name(userStringData[1]);
                    u.setUser_email(userStringData[2]);
                    u.setUser_password(userStringData[3]);
                    int day = Integer.parseInt(userStringData[6].substring(0, 2));
                    int month = Integer.parseInt(userStringData[6].substring(2, 4));
                    int year = Integer.parseInt(userStringData[6].substring(4));
                    u.subscription.plan.StartDate = Calendar.getInstance();
                    u.subscription.plan.StartDate.set(Calendar.YEAR, year);
                    u.subscription.plan.StartDate.set(Calendar.MONTH, month);
                    u.subscription.plan.StartDate.set(Calendar.DAY_OF_MONTH, day);
                    userData.add(u);
                    System.out.println( // For testing
                            "User: " + u.getFirst_name() + " " + u.getLast_name() + " " + u.getUser_email() + " "
                                    + u.getUser_password() + " Subscription: " + u.subscription.PriceOfPlan + "$ "
                                    + "     USERS NUM NOW: " + userData.size());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void readActors() {
        try {
            String readLine = "";
            FileReader reader = new FileReader("src\\main\\resources\\Files\\actors.txt");
            int c;
            while ((c = reader.read()) != -1) {
                readLine += (char) c;
            }
            reader.close();
            String actorsStringData[] = new String[5];
            int dataIndex = 0;
            for (int i = 0; i < readLine.length(); i++) {
                int length = 0;
                actorsStringData[dataIndex] = "";
                String lengthSubsStr = readLine.substring(i, readLine.indexOf('#', i));

                length = Integer.parseInt(lengthSubsStr);
                i = readLine.indexOf('#', i);
                for (int j = 1; j < length + 1; j++) {
                    actorsStringData[dataIndex] += readLine.charAt(i + j);
                }
                i += actorsStringData[dataIndex].length();
                dataIndex++;
                if (dataIndex == 5) {
                    dataIndex = 0;
                    Actor a = new Actor(actorsStringData[0], actorsStringData[1], actorsStringData[2],
                            actorsStringData[3], Integer.parseInt(actorsStringData[4]), null, null);
                    actorsData.add(a);
                    System.out.println( // For testing
                            "Actor: " + a.getFirst_name() + " " + a.getLast_name() + " " + a.getNationality() + " "
                                    + a.getGender() + " " + a.getAge() + "     ACTORS NUM NOW: " + actorsData.size());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeActors() {
        try {
            FileWriter writer = new FileWriter("src\\main\\resources\\Files\\actors.txt");
            for (Actor a : actorsData) {
                writer.write(a.getFirst_name().length() + "#" + a.getFirst_name());
                writer.write(a.getLast_name().length() + "#" + a.getLast_name());
                writer.write(a.getNationality().length() + "#" + a.getNationality());
                writer.write(a.getGender().length() + "#" + a.getGender());
                writer.write(String.valueOf(a.getAge()).length() + "#" + a.getAge());
            }
            writer.close();

        } catch (Exception e) {
            return;
        }
    }

    public void readDirectors() {
        try {
            String readLine = "";
            FileReader reader = new FileReader("src\\main\\resources\\Files\\directors.txt");
            int c;
            while ((c = reader.read()) != -1) {
                readLine += (char) c;
            }
            reader.close();
            String directorsStringData[] = new String[5];
            int dataIndex = 0;
            for (int i = 0; i < readLine.length(); i++) {
                int length = 0;
                directorsStringData[dataIndex] = "";
                String lengthSubsStr = readLine.substring(i, readLine.indexOf('#', i));

                length = Integer.parseInt(lengthSubsStr);
                i = readLine.indexOf('#', i);
                for (int j = 1; j < length + 1; j++) {
                    directorsStringData[dataIndex] += readLine.charAt(i + j);
                }
                i += directorsStringData[dataIndex].length();
                dataIndex++;
                if (dataIndex == 5) {
                    dataIndex = 0;
                    Director d = new Director(directorsStringData[0], directorsStringData[1], directorsStringData[2],
                            directorsStringData[3], Integer.parseInt(directorsStringData[4]), null, null);
                    directorData.add(d);
                    System.out.println( // For testing
                            "Director: " + d.getFirst_name() + " " + d.getLast_name() + " " + d.getNationality() + " "
                                    + d.getGender() + " " + d.getAge() + "     DIRECTORS NUM NOW: "
                                    + directorData.size());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeDirectors() {
        try {
            FileWriter writer = new FileWriter("src\\main\\resources\\Files\\directors.txt");
            for (Director d : directorData) {
                writer.write(d.getFirst_name().length() + "#" + d.getFirst_name());
                writer.write(d.getLast_name().length() + "#" + d.getLast_name());
                writer.write(d.getNationality().length() + "#" + d.getNationality());
                writer.write(d.getGender().length() + "#" + d.getGender());
                writer.write(String.valueOf(d.getAge()).length() + "#" + d.getAge());
            }
            writer.close();
        } catch (Exception e) {
            return;
        }
    }

    public void readMovies() {
        try {
            String readLine = "";
            FileReader reader = new FileReader("src\\main\\resources\\Files\\movies.txt");
            int c;
            while ((c = reader.read()) != -1) {
                readLine += (char) c;
            }
            reader.close();

            String movieStringData[] = new String[10];
            String movieCastNames[] = {};
            int dataIndex = 0;
            for (int i = 0; i < readLine.length(); i++) {
                int length = 0;
                movieStringData[dataIndex] = "";
                String lengthSubsStr = readLine.substring(i, readLine.indexOf('#', i));
                // System.out.println("STRING: " + lengthSubsStr);
                // System.out.println(i);

                length = Integer.parseInt(lengthSubsStr);
                i = readLine.indexOf('#', i);
                for (int j = 1; j < length + 1 && (i + j) < readLine.length(); j++) {
                    movieStringData[dataIndex] += readLine.charAt(i + j);
                }
                i += movieStringData[dataIndex].length();
                // System.out.println(movieStringData[dataIndex]); // For testing
                dataIndex++;
                if (dataIndex == 8) {
                    Movie m = new Movie(movieStringData[7]);
                    dataIndex++;
                    Person p = new Director("", "", "", "", 0, null, null);
                    for (Director d : directorData) {
                        if (d.getFirst_name().equals(movieStringData[2])
                                && d.getLast_name().equals(movieStringData[3])) {
                            p = d;
                            break;
                        }
                    }
                    m.setMovieTitle(movieStringData[0]);
                    m.setReleaseDate(movieStringData[1]);
                    m.setGenres(movieStringData[4].split("/"));
                    m.director = (Director) p;
                    m.setLanguage(movieStringData[5]);
                    m.setRunningTime(Integer.parseInt(movieStringData[6]));
                    length = 0;
                    movieStringData[dataIndex] = "";
                    lengthSubsStr = readLine.substring(i + 1, readLine.indexOf('$', i));
                    length = Integer.parseInt(lengthSubsStr);
                    i = readLine.indexOf('$', i);
                    for (int j = 1; j < length + 1 && (i + j) < readLine.length(); j++) {
                        movieStringData[dataIndex] += readLine.charAt(i + j);
                    }
                    i += movieStringData[dataIndex].length();
                    movieCastNames = movieStringData[dataIndex].split("-");
                    for (String actName : movieCastNames) {
                        String[] nameParts = actName.split(" ");
                        for (Actor a : actorsData) {
                            if (a.first_name.equals(nameParts[0]) && a.last_name.equals(nameParts[1])) {
                                m.getCast().add(a);
                            }
                        }
                    }
                    moviesData.add(m);
                    dataIndex = 0;
                    System.out.println( // For testing
                            "Movie: " + m.getMovieTitle() + " " + m.getReleaseDate() + " " +
                                    m.director.getFirst_name() + " " + m.director.getLast_name() + " "
                                    + m.Language + " " + m.getRunningTime() + "min. " + m.getMovieID()
                                    + "      MOVIES NUM NOW: "
                                    + moviesData.size());
                    for (Actor a : m.Cast) {
                        System.out.println(a.first_name + " " + a.last_name);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeMovies() {
        try {
            FileWriter writer = new FileWriter("src\\main\\resources\\Files\\movies.txt");
            String castString = "";
            for (Movie m : moviesData) {
                writer.write(m.getMovieTitle().length() + "#" + m.getMovieTitle());
                writer.write(m.getReleaseDate().length() + "#" + m.getReleaseDate());
                writer.write(m.director.getFirst_name().length() + "#" + m.director.getFirst_name());
                writer.write(m.director.getLast_name().length() + "#" + m.director.getLast_name());
                writer.write(Arrays.stream(m.getGenres()).collect(Collectors.joining("/")).length() + "#"
                        + Arrays.stream(m.getGenres()).collect(Collectors.joining("/")));
                writer.write(m.Language.length() + "#" + m.Language);
                writer.write(String.valueOf(m.getRunningTime()).length() + "#" + m.getRunningTime());
                writer.write(m.getMovieID().length() + "#" + m.getMovieID());
                for (Actor a : m.Cast) {
                    castString += ("-" + a.getFirst_name() + " " + a.getLast_name());
                }
                writer.write(castString.length() + "$" + castString);
                castString = "";
            }
            writer.close();

        } catch (Exception e) {
            return;
        }
    }

    public void writeUsers() {
        try {
            FileWriter writer = new FileWriter("src\\main\\resources\\Files\\users.txt");
            for (User u : userData) {
                String date = "";
                writer.write(u.getFirst_name().length() + "#" + u.getFirst_name());
                writer.write(u.getLast_name().length() + "#" + u.getLast_name());
                writer.write(u.getUser_email().length() + "#" + u.getUser_email());
                writer.write(u.getUser_password().length() + "#" + u.getUser_password());
                writer.write(u.getUser_ID().length() + "#" + u.getUser_ID());
                writer.write(String.valueOf(u.subscription.PriceOfPlan).length() + "#" + u.subscription.PriceOfPlan);
                writer.write("8#");
                if (u.subscription.plan.StartDate.get(Calendar.DAY_OF_MONTH) < 10) {
                    date += "0" + String.valueOf(u.subscription.plan.StartDate.get(Calendar.DAY_OF_MONTH));
                } else {
                    date += String.valueOf(u.subscription.plan.StartDate.get(Calendar.DAY_OF_MONTH));
                }
                if (u.subscription.plan.StartDate.get(Calendar.MONTH) < 10) {
                    date += "0" + String.valueOf(u.subscription.plan.StartDate.get(Calendar.MONTH));
                } else {
                    date += String.valueOf(u.subscription.plan.StartDate.get(Calendar.MONTH));
                }
                date += String.valueOf(u.subscription.plan.StartDate.get(Calendar.YEAR));
                writer.write(date);
            }
            writer.close();

        } catch (Exception e) {
            return;
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        readUsers();
        readActors();
        readDirectors();
        readMovies();
        readLaterLists();
        readWatchRecord();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Furga");
        Image logo = new Image("Watchit_Logo.png");
        stage.getIcons().add(logo);
        stage.setScene(scene);
        stage.show();
        scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("Register.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("WatchList.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("MovieScene.css").toExternalForm());
        stage.setOnCloseRequest(event -> {
            writeUsers();
            writeActors();
            writeDirectors();
            writeMovies();
            writeLaterLists();
            writeWatchRecord();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}