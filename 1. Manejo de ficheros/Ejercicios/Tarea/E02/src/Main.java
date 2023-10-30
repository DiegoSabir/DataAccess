/**
 * Ahora vamos a modificar el código del E01 para, manteniendo lo más inalterados posible el modelo
 * y la vista, pasemos de trabajar con ficheros binarios a hacerlo con ficheros XML a través del protocolo DOM.
 * Diseña los métodos necesarios para insertar, modificar, borrar y consultar empleados en un fichero XML
 * haciendo uso de DOM.
 */

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String XML_FILE = "DataEmployee2.xml";
    public static void main(String[] args) {
        ArrayList<Employee> workers = loadEmployeesFromXML();
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
                    saveEmployeesToXML(workers);
                    break;
                case 3:
                    modifySalary(sc, workers);
                    saveEmployeesToXML(workers);
                    break;
                case 4:
                    deleteEmploy(sc, workers);
                    saveEmployeesToXML(workers);
                    break;
                case 5:
                    listWorkers(workers);
                    break;
                case 6:
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
        for (Employee worker : workers) {
            if (worker.getDni().equals(dni)) {
                dniRegistered = true;
                workers.remove(worker);
                System.out.println("Employee deleted successfully");
            }
        }
        if (!dniRegistered) {
            System.out.println("The employee is not on the database");
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

    private static void saveEmployeesToXML(ArrayList<Employee> workers) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element root = doc.createElement("Employees");

            for (Employee worker : workers) {
                Element employeeElement = doc.createElement("Employee");

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
                salaryElement.appendChild(doc.createTextNode(Double.toString(worker.getSalary())));
                employeeElement.appendChild(salaryElement);

                root.appendChild(employeeElement);
            }

            doc.appendChild(root);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(XML_FILE));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Employee> loadEmployeesFromXML() {
        ArrayList<Employee> workers = new ArrayList<>();

        try {
            File file = new File(XML_FILE);
            if (file.exists()) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(file);

                Element root = doc.getDocumentElement();
                NodeList employeeNodes = root.getElementsByTagName("Employee");

                for (int i = 0; i < employeeNodes.getLength(); i++) {
                    Element employeeElement = (Element) employeeNodes.item(i);

                    String dni = employeeElement.getElementsByTagName("DNI").item(0).getTextContent();
                    String name = employeeElement.getElementsByTagName("Name").item(0).getTextContent();
                    String surname = employeeElement.getElementsByTagName("Surname").item(0).getTextContent();
                    double salary = Double.parseDouble(employeeElement.getElementsByTagName("Salary").item(0).getTextContent());

                    workers.add(new Employee(dni, name, surname, salary));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return workers;
    }
}
