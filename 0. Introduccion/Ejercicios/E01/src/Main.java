public class Main {
    public static void main(String[] args) {
        Worker worker1 = new Worker("Diego", 24, Worker.CAT_EMPLOYEE, Worker.SEN_JUNIOR);

        System.out.println("Name: " + worker1.getName());
        System.out.println("Age: " + worker1.getAge());
        System.out.println("Category: " + worker1.getCategory());
        System.out.println("Seniority: " + worker1.getSeniority());
        System.out.println("Salary: " + worker1.calculateSalary());
    }
}