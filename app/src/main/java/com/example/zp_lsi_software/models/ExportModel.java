package com.example.zp_lsi_software.models;

public class ExportModel {

    private final String name;
    private final String date;
    private final String hour;
    private final String user;
    private final String place;

    public ExportModel(String name, String date, String hour, String user, String place) {
        this.name = name;
        this.date = date;
        this.hour = hour;
        this.user = user;
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public String getUser() {
        return user;
    }

    public String getPlace() {
        return place;
    }
}
