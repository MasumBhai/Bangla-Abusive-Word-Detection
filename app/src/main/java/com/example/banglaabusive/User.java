package com.example.banglaabusive;

public class User {
    String Date, Time, Speech, Location, Name;

    public User(String name,String date, String time, String speech, String location ) {
        Name = name;
        Date = date;
        Time = time;
        Speech = speech;
        Location = location;


    }



    public User(){

    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getSpeech() {
        return Speech;
    }

    public void setSpeech(String speech) {
        Speech = speech;
    }

    public String getLocation() {
        return Location;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }



    public void setLocation(String location) {
        Location = location;
    }
}
