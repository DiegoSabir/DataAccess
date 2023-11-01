/**
 * Ahora vamos a modificar el código del E01 para, manteniendo lo más inalterados posible el modelo
 * y la vista, pasemos de trabajar con ficheros binarios a hacerlo con ficheros XML a través del protocolo DOM.
 * Diseña los métodos necesarios para insertar, modificar, borrar y consultar empleados en un fichero XML
 * haciendo uso de DOM.
 */
import java.io.File;
import java.io.FileOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

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
                case 5:
                    listWorkers(workers);
                    break;
                case 6:
                    saveData(workers);
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
            System.out.println("Choose a option: ");
            System.out.println("[1] Consult employ data");
            System.out.println("[2] Add employ");
            System.out.println("[3] Modify salary");
            System.out.println("[4] Delete employ");
            System.out.println("[5] List workers");
            System.out.println("[6] Exit");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                if (choice >= 1 && choice <= 6) {
                    break;
                }
            }
            else {
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

    private static void saveData(ArrayList<Employee> workers) {
        try {
            File file = new File("EmployeesDataList.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc;

            if (file.exists()) {
                doc = db.parse(file);
            }
            else {
                doc = db.newDocument();
                Element rootElement = doc.createElement("Employees");
                doc.appendChild(rootElement);
            }

            Element rootElement = doc.getDocumentElement();

            for (Employee worker : workers) {
                Element employeeElement = doc.createElement("Employee");
                rootElement.appendChild(employeeElement);

                Element dniElement = doc.createElement("DNI");
                dniElement.appendChild(doc.createTextNode(worker.getDni()));
                employeeElement.appendChild(dniElement);

                Element nameElement = doc.createElement("Name");
                nameElement.appendChild(doc.createTextNode(worker.getName()));
                employeeElement.appendChild(nameElement);

                Element surnameElement = doc.createElement("Surname");
                surnameElement.appendChild(doc.createTextNode(worker.getSurname()));
                employeeElement.appendChild(surnameElement);

                Element salaryElement = doc.createElement("Salary");
                salaryElement.appendChild(doc.createTextNode(String.valueOf(worker.getSalary())));
                employeeElement.appendChild(salaryElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult sr = new StreamResult(file);

            transformer.transform(source, sr);

            System.out.println("Data saved successfully");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
