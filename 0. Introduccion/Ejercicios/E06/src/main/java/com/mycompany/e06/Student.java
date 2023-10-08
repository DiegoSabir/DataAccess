package com.mycompany.e06;
import java.util.ArrayList;

public class Student {
    private String name;
    private String town;
    private ArrayList<Subject> subjects;

    public Student(String name, String town) {
        this.name = name;
        this.town = town;
        this.subjects = new ArrayList<Subject>();
    }

    public Student(String name, String town, ArrayList<Subject> subjects) {
        this.name = name;
        this.town = town;
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }
    
    public void añadirModulo(Subject subject) {
        subjects.add(subject);
    }
    
    public String[] getNameSubject() {
        String[] names = new String[subjects.size()];
        int i = 0;
        for (Subject subject : subjects){
            names[i++] = subject.getName();
        }
        return names;
    }
    
    public double getNumberHours(){
        double totalHours = 0;
        for (Subject subject : subjects){
            totalHours += subject.getHour();
        }
        return totalHours;
    }
}
