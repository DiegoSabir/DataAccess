/**
 * En esta práctica vamos a modificar el código del ejercicio 1 para añadir una nueva función
 * al menú que nos permita leer el contenido del fichero de empleados usando SAX.
 * NOTA: El formato de salida debe ser idéntico al que se muestra en la lectura DOM.
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
            System.out.println("6. Leer empleados con SAX");
            System.out.println("7. Salir");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    //Creariamos una función
                    sc.nextLine();
                    Employee e= new Employee();
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
                    e= new Employee();
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
                    e= new Employee();
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
                    ArrayList<Employee> empleados = new Controller().listEmployees();
                    for (Employee empleado : empleados) {
                        System.out.println(empleado);
                    }
                    break;

                case 6:
                    ArrayList<Employee> empleadosSAX = new Controller().EmployeeHandler();
                    for (Employee empleado : empleadosSAX) {
                        System.out.println(empleado);
                    }
                    break;

                case 7:
                    //opcion para salir
                    break;

                default:
                    break;
            }
        }
        while(option != 7);
    }
}