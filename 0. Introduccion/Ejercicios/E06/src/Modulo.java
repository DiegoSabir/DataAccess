public class Modulo {
    private String name;
    private int hours;
    private int unity;

    public Modulo(String name, int hours, int unity) {
        this.name = name;
        this.hours = hours;
        this.unity = unity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getUnity() {
        return unity;
    }

    public void setUnity(int unity) {
        this.unity = unity;
    }
}
