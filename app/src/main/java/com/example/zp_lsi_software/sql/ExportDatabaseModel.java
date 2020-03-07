package com.example.zp_lsi_software.sql;

public class ExportDatabaseModel {

    private long id;
    private String name;
    private String date;
    private String hour;
    private String user;
    private String place;

    public ExportDatabaseModel(long id, String name, String date, String hour, String user, String place) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.hour = hour;
        this.user = user;
        this.place = place;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
