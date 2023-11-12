/**
 * En esta práctica vamos a modificar el código del ejercicio 1 para añadir una nueva función
 * al menú que nos permita leer el contenido del fichero de empleados usando SAX.
 * NOTA: El formato de salida debe ser idéntico al que se muestra en la lectura DOM.
 */
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Employee> workers = new ArrayList<>();
        loadData(workers);
        Scanner sc = new Scanner(System.in);

        int option;
        do {
            option = menu(sc);
            switch (option) {
                case 1:
                    consultEmployData(sc, workers);
                    break;
                case 2:
                    addEmploy(sc, workers);
                    break;
                case 3:
                    modifySalary(sc, workers);
                    break;
                case 4:
                    deleteEmploy(sc, workers);
                case 5:
                    listWorkers(workers);
                    break;
                case 6:
                    saveData(workers);
                    break;
                default:
                    System.out.println("Please enter a valid option (1-6).");
            }
        } while (option != 6);
    }

    private static int menu(Scanner sc) {
        int choice;
        while (true) {
            System.out.println("Choose a option: ");
            System.out.println("[1] Consult employ data");
            System.out.println("[2] Add employ");
            System.out.println("[3] Modify salary");
            System.out.println("[4] Delete employ");
            System.out.println("[5] List workers");
            System.out.println("[6] Save to XML");
            System.out.println("[7] Exit");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                if (choice >= 1 && choice <= 7) {
                    break;
                }
            } else {
                sc.next();
            }
        }
        return choice;
    }

    private static void consultEmployData(Scanner sc, ArrayList<Employee> workers) {
        System.out.print("Enter dni: ");
        String dni = sc.next();
        boolean dniRegistered = false;

        for (Employee worker : workers) {
            if (worker.getDni().equals(dni)) {
                dniRegistered = true;
                System.out.println("DNI: " + worker.getDni());
                System.out.println("Name: " + worker.getName());
                System.out.println("Surname: " + worker.getSurname());
                System.out.println("Salary: " + worker.getSalary());
            }
        }
        if (!dniRegistered) {
            System.out.println("The employee is not on the database");
        }
    }

    private static void addEmploy(Scanner sc, ArrayList<Employee> workers) {
        System.out.print("Enter dni: ");
        String dni = sc.next();
        boolean dniRegistered = false;

        for (Employee worker : workers) {
            if (worker.getDni().equals(dni)) {
                dniRegistered = true;
            }
        }
        if (dniRegistered) {
            System.out.println("DNI REGISTERED");
        } else {
            System.out.print("Enter name: ");
            String name = sc.next();
            System.out.print("Enter surname: ");
            String surname = sc.next();
            System.out.print("Enter salary: ");
            double salary = sc.nextDouble();
            workers.add(new Employee(dni, name, surname, salary));

            System.out.println("Employee added successfully");
        }
    }

    private static void modifySalary(Scanner sc, ArrayList<Employee> workers) {
        System.out.print("Enter dni: ");
        String dni = sc.next();
        boolean dniRegistered = false;
        for (Employee worker : workers) {
            if (worker.getDni().equals(dni)) {
                dniRegistered = true;
                System.out.print("Enter new salary: ");
                double salary = sc.nextDouble();
                worker.setSalary(salary);
                System.out.println("Salary changed successfully");
            }
        }
        if (!dniRegistered) {
            System.out.println("The employee is not on the database");
        }
    }

    private static void deleteEmploy(Scanner sc, ArrayList<Employee> workers) {
        System.out.print("Enter dni: ");
        String dni = sc.next();
        boolean dniRegistered = false;
        Iterator<Employee> it = workers.iterator();

        while (it.hasNext()) {
            Employee worker = it.next();
            if (worker.getDni().equals(dni)) {
                dniRegistered = true;
                it.remove();
                System.out.println("Employee deleted successfully");
            }
        }

        if (!dniRegistered) {
            System.out.println("The employee is not in the database");
        }
    }

    private static void listWorkers(ArrayList<Employee> workers) {
        System.out.println("Employee Data List");
        for (Employee worker : workers) {
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("DNI: " + worker.getDni());
            System.out.println("Name: " + worker.getName());
            System.out.println("Surname: " + worker.getSurname());
            System.out.println("Salary: " + worker.getSalary());
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
    }

    private static void saveData(ArrayList<Employee> workers) {
        try {

            File xmlFile = new File("EmployeesDataList.xml");

            EmployeeHandler xmlHandler = new EmployeeHandler();
            xmlHandler.EmployeeHandler(workers);

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            saxParser.parse(xmlFile, xmlHandler);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadData(ArrayList<Employee> workers) {
        try {
            File file = new File("EmployeesDataList.xml");

            EmployeeHandler handler = new EmployeeHandler();
            handler.EmployeeHandler(workers);

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            saxParser.parse(file, handler);

            System.out.println("Data loaded from EmployeesDataList.xml");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
