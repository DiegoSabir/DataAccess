package com.mycompany.e06;

public class Subject {
    private String name;
    private int hour;
    private int lesson;

    public Subject(String name, int hour, int lesson) {
        this.name = name;
        this.hour = hour;
        this.lesson = lesson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getLesson() {
        return lesson;
    }

    public void setLesson(int lesson) {
        this.lesson = lesson;
    }
    
}
