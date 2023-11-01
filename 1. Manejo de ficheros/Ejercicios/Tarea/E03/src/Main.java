/**
 * En esta práctica vamos a modificar el código del ejercicio 1 para añadir una nueva función
 * al menú que nos permita leer el contenido del fichero de empleados usando SAX.
 * NOTA: El formato de salida debe ser idéntico al que se muestra en la lectura DOM.
 */
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        ArrayList<Employee> workers = new ArrayList<>();
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
                    break;
                case 5:
                    listWorkers(workers);
                    break;
                case 6:
                    saveDataSAX(workers, "EmployeesDataList.xml");
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Please enter a valid option (1-6).");
            }
        } while (option != 6);
    }

    private static int menu(Scanner sc) {
        int choice;
        while (true) {
            System.out.println("Choose an option: ");
            System.out.println("[1] Consult employee data");
            System.out.println("[2] Add employee");
            System.out.println("[3] Modify salary");
            System.out.println("[4] Delete employee");
            System.out.println("[5] List employees");
            System.out.println("[6] Save and Exit");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                if (choice >= 1 && choice <= 6) {
                    break;
                }
            } else {
                sc.next();
            }
        }
        return choice;
    }

    private static void consultEmployData(Scanner sc, ArrayList<Employee> workers){
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

    private static void addEmploy(Scanner sc, ArrayList<Employee> workers){
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
        }
        else {
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

    private static void modifySalary(Scanner sc, ArrayList<Employee> workers){
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

    private static void deleteEmploy(Scanner sc, ArrayList<Employee> workers){
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

    private static void listWorkers(ArrayList<Employee> workers){
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

    private static void saveDataSAX(ArrayList<Employee> workers, String filename) {
        try {
            File file = new File(filename);
            EmployeeHandler handler = new EmployeeHandler();

            if (file.exists()) {
                XMLReader xmlReader = XMLReaderFactory.createXMLReader();
                xmlReader.setContentHandler(handler);
                xmlReader.parse(new InputSource(new FileInputStream(filename)));
            }

            ArrayList<Employee> existingEmployees = handler.getEmployees();
            existingEmployees.addAll(workers);

            OutputStream os = new FileOutputStream(filename);
            XMLStreamWriter writer = XMLOutputFactory.newInstance().createXMLStreamWriter(os);
            writer.writeStartDocument();
            writer.writeStartElement("Employees");

            for (Employee employee : existingEmployees) {
                writer.writeStartElement("Employee");
                writer.writeStartElement("DNI");
                writer.writeCharacters(employee.getDni());
                writer.writeEndElement();
                writer.writeStartElement("Name");
                writer.writeCharacters(employee.getName());
                writer.writeEndElement();
                writer.writeStartElement("Surname");
                writer.writeCharacters(employee.getSurname());
                writer.writeEndElement();
                writer.writeStartElement("Salary");
                writer.writeCharacters(String.valueOf(employee.getSalary()));
                writer.writeEndElement();
                writer.writeEndElement();
            }

            writer.writeEndElement();
            writer.writeEndDocument();
            writer.close();

            System.out.println("Data saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

