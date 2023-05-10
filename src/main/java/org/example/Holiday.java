package org.example;

public class Holiday {
    private String date;
    private String day;
    private String holiday;
    private String state;
    private String id;

    public Holiday(String date, String day, String holiday, String state, String id) {
        this.date = date;
        this.day = day;
        this.holiday = holiday;
        this.state = state;
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getHoliday() {
        return holiday;
    }

    public String getState() {
        return state;
    }

    public String getId() {
        return id;
    }
}

