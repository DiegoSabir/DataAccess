/**
 * Desde el departamento de recursos humanos de Umbrella Corp, nos han pedido que diseñemos
 * una pequeña aplicación que les ayude a realizar la gestión de sus empleados. Esta aplicación debe
 * almacenar la siguiente información de cada uno de los empleados.
 * - NIF →9 caracteres (8 números y 1 letra)
 * - Nombre → 10 caracteres
 * - Apellidos → 20 caracteres
 * - Salario
 *
 * La aplicación debe permitir la inserción, consulta, modificación y borrado de empleados.
 * - Consulta → Solicitará al usuario el NIF del empleado a consultar:
 *      Si existe mostrará su información, en caso contrario indicará que el empleado solicitado no existe.
 *
 * - Inserción → Solicitará por teclado la información del nuevo empleado. Antes de insertar
 *   comprobará si el nuevo NIF ya existe:
 *      Si existe se debe informar al usuario.
 *      En caso contrario se insertará.
 *
 * - Modificación → Se solicitará por teclado el NIF del empleado y el nuevo importe de su
 *   salario.
 *      En caso de no existir se informará al usuario.
 *      En caso contrario se realizará la modificación.
 *
 * - Borrado → Se solicitará el NIF del empleado a borrar por teclado. Haremos un borrado
 *   lógico situando su NIF a -1.
 *
 * - Listar → Muestra todos los empleados (No borrados) existentes en el fichero.
 *
 * Realiza una aplicación en Java que cumpla con todos los requisitos anteriores.
 */
import java.util.ArrayList;
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
}