/**
 * Ahora vamos a modificar el código del E01 para, manteniendo lo más inalterados posible el modelo
 * y la vista, pasemos de trabajar con ficheros binarios a hacerlo con ficheros XML a través del protocolo DOM.
 * Diseña los métodos necesarios para insertar, modificar, borrar y consultar empleados en un fichero XML
 * haciendo uso de DOM.
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("1. Insertar");
            System.out.println("2. Consultar");
            System.out.println("3. Modificar");
            System.out.println("4. Borrar");
            System.out.println("5. Listar Todos");
            System.out.println("6. Salir");
            option=sc.nextInt();

            switch (option) {
                case 1:
                    //Creariamos una función
                    sc.nextLine();
                    Employee e = new Employee();
                    System.out.println("Introduce NIF");
                    e.setDni(sc.nextLine());
                    System.out.println("Introduce Nombre");
                    e.setName(sc.nextLine());
                    System.out.println("Introduce Apellidos");
                    e.setSurname(sc.nextLine());
                    System.out.println("Introduce Salario");
                    e.setSalary(sc.nextDouble());
                    if(new Controller().addEmployee(e)) {
                        System.out.println("Empleado insertado");
                    }
                    else {
                        System.out.println("Empleado no insertado");
                    }
                    sc.nextLine();
                    break;

                case 2:
                    sc.nextLine();
                    System.out.println("Introduce NIF");
                    System.out.println(new Controller().consultEmployeeData(sc.nextLine()));
                    break;

                case 3:
                    sc.nextLine();
                    e = new Employee();
                    System.out.println("Introduce NIF");
                    e.setDni(sc.nextLine());
                    System.out.println("Introduce Nombre");
                    e.setName(sc.nextLine());
                    System.out.println("Introduce Apellidos");
                    e.setSurname(sc.nextLine());
                    System.out.println("Introduce Salario");
                    e.setSalary(sc.nextDouble());

                    if(new Controller().modifyEmployee(e)) {
                        System.out.println("Empleado insertado");
                    }
                    else {
                        System.out.println("Empleado no insertado");
                    }
                    sc.nextLine();
                    break;

                case 4:
                    sc.nextLine();
                    e = new Employee();
                    System.out.println("Introduce NIF");
                    e.setDni(sc.nextLine());

                    if(new Controller().deleteEmployee(e)) {
                        System.out.println("Empleado borrado");
                    }
                    else {
                        System.out.println("Empleado no borrado");
                    }
                    sc.nextLine();
                    break;

                case 5:
                    ArrayList<Employee> workers = new Controller().listEmployees();
                    for (Employee empleado : workers) {
                        System.out.println(empleado);
                    }
                    break;

                default:
                    break;
            }
        }
        while(option != 6);
    }
}
