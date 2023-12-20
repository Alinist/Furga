package com.example.oopjavafx;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;

public class Admin extends User {

//    public User getUser(String userID) {
//        ArrayList<User> userList = new ArrayList<>();
//        for (User user : userList) {
//            if (user.getUser_ID().equals(userID)) {
//                return user;
//            }
//        }
//        return null;
//    }
    public static void addActorToActorList(Actor actor) {
    boolean Is_Added_Before_InList = false;
    for (int i = 0; i < ActorList.size(); i++) {
        if (ActorList.get(i).getFirst_name().equalsIgnoreCase(actor.getFirst_name()) && ActorList.get(i).getLast_name().equalsIgnoreCase(actor.getLast_name()) ){
            Is_Added_Before_InList = true;
            break;
        }
    }
    if (!Is_Added_Before_InList){
       ActorList.add(actor);
    }
}

    public static void removeActorFromActorList(Actor actor) {
        ActorList.remove(actor);
    }

    public static void addDirectorToDirectorList(Director director) {
        boolean Is_Added_Before_InList = false;
        for (int i = 0; i < DirectorList.size(); i++) {
            if (DirectorList.get(i).getFirst_name().equalsIgnoreCase(director.getFirst_name()) && DirectorList.get(i).getLast_name().equalsIgnoreCase(director.getLast_name()) ){
                Is_Added_Before_InList = true;
                break;
            }
        }
        if (!Is_Added_Before_InList){
            DirectorList.add(director);
        }
    }

    public static void removeDirectorFromDirectorList(Director director) {
        DirectorList.remove(director);
    }

    public static void addMovieToMovieList(Movie movie) {

        boolean Is_Added_Before_InMovieList = false;
        for (int i = 0; i < MovieList.size(); i++) {
            if (MovieList.get(i).getMovieTitle().toLowerCase().equals(movie.getMovieTitle().toLowerCase())){
                Is_Added_Before_InMovieList = true;
                break;
            }
        }
        if (!Is_Added_Before_InMovieList){
            MovieList.add(movie);
        }
    }

    public static void removeMovieFromMovieList(Movie movie) {
        MovieList.remove(movie);
    }

//    public void resetRating(Movie movie) {
//        for (Float rating : movie.Ratings) {
//            rating = 0f;
//        }
//    }
//    public void removeRating(Movie movie, User user) {
//        HashMap ratings = user.getUserRatingsForMovie();
//        if (ratings.containsKey(movie)) {
//            ratings.remove(movie);
//        }
//    }
    public void suspendAccount(User user) {
        if (suspended) {
            System.out.println("User already suspended.");
        }
        else {
            user.suspended = true;
            System.out.println("User successfully suspended.");
        }
    }
    public void unsuspendAccount(User user) {
        if (suspended) {
            user.suspended = false;
            System.out.println("User already suspended.");
        }
        else {
            System.out.println("User already not suspended.");
        }
    }
    public String getMostRevMonth() {
        int max = 0;
        int index = 0;
        for (int i = 0; i < 12; i++) {
            if (subscription.monthlyRevenue[i] > max) {
                max = subscription.monthlyRevenue[i];
                index = i;
            }
        }
        return Month.of(index + 1).toString();
    }
    public String getMostSubbedPlan() {
        HashMap<String, Integer> plansCount = new HashMap<>();
        plansCount.put("Basic", Subscription.countBasic);
        plansCount.put("Standard", Subscription.countStandard);
        plansCount.put("Premium", Subscription.countPremium);

        HashMap.Entry<String, Integer> max = null;

        for (HashMap.Entry<String, Integer> entry : plansCount.entrySet()) {
            if (max == null || entry.getValue().compareTo(max.getValue()) > 0) {
                max = entry;
            }
        }

        return max.getKey();
    }

}
