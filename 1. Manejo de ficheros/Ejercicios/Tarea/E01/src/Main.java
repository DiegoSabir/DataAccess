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
        Scanner sc = new Scanner(System.in);
        int op=0;
        do {
            System.out.println("1. Insertar");
            System.out.println("2. Consultar");
            System.out.println("3. Modificar");
            System.out.println("4. Borrar");
            System.out.println("5. Listar Todos");
            System.out.println("6. Salir");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    //Creariamos una función
                    sc.nextLine();
                    
                    Employee worker = new Employee();
                    System.out.println("Introduce NIF");
                    worker.setDni(sc.nextLine());
                    System.out.println("Introduce Nombre");
                    worker.setName(sc.nextLine());
                    System.out.println("Introduce Apellidos");
                    worker.setSurname(sc.nextLine());
                    System.out.println("Introduce Salario");
                    worker.setSalary(sc.nextDouble());

                    if(new Controller().addEmployee(worker)) {
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
                    System.out.println(new Controller().consultEmployData(sc.nextLine()));
                    break;

                case 3:
                    sc.nextLine();
                    worker = new Employee();
                    System.out.println("Introduce NIF");
                    worker.setDni(sc.nextLine());
                    System.out.println("Introduce Nombre");
                    worker.setName(sc.nextLine());
                    System.out.println("Introduce Apellidos");
                    worker.setSurname(sc.nextLine());
                    System.out.println("Introduce Salario");
                    worker.setSalary(sc.nextDouble());
                    if(new Controller().modifySalary(worker)) {
                        System.out.println("Empleado insertado");
                    }
                    else {
                        System.out.println("Empleado no insertado");
                    }
                    sc.nextLine();
                    break;

                case 4:
                    sc.nextLine();
                    worker = new Employee();
                    System.out.println("Introduce NIF");
                    worker.setDni(sc.nextLine());

                    if(new Controller().deleteEmploy(worker)) {
                        System.out.println("Empleado borrado");
                    }
                    else {
                        System.out.println("Empleado no borrado");
                    }
                    //sc.nextLine();
                    break;

                case 5:
                    ArrayList<Employee> workers = new Controller().listWorkers();
                    for (Employee empleado : workers) {
                        System.out.println(empleado);
                    }
                    break;

                default:
                    break;
            }
        }
        while(op!=6);
    }
}