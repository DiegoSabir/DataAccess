import java.util.ArrayList;
import java.util.Scanner;

public class Student {
    private String name;
    private String city;
    private ArrayList<Modulo> subjects = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public Student(String name, String city) {
        this.name = name;
        this.city = city;
        this.subjects = new ArrayList<>();
    }

    public Student(String name, String city, ArrayList<Modulo> subjects) {
        this.name = name;
        this.city = city;
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<Modulo> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Modulo> subjects) {
        this.subjects = subjects;
    }

    public void addSubject() {
        sc = new Scanner(System.in);
        String name = "";
        while (!name.equals("exit")) {
            name = sc.nextLine();
            if(!name.equals("exit")){
                subjects.add(name);
            }
        }
    }

    public String getNameSubjects() {
        StringBuilder namesBuilder = new StringBuilder();
        for (int i = 0; i < subjects.size(); i++) {
            namesBuilder.append(subjects.get(i).getName());
            if (i < subjects.size() - 1) {
                namesBuilder.append(", "); // Agrega una coma y un espacio entre los nombres
            }
        }
        return namesBuilder.toString();
    }
    public double getNumberHours() {
        double totalHours = 0;
        for (Modulo module : subjects) {
            totalHours += module.getHoursPerWeek();
        }
        return totalHours;
    }
}
