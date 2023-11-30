import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String dni, nombreDep;
        int opcion, salario, numDep;

        Conector.conection();

        do {
            System.out.println("Opciones de menu:");
            System.out.println("====================================================");
            System.out.println("1. Introducir un empleado");
            System.out.println("2. Modificar un empleado");
            System.out.println("3. Eliminar un empleado");
            System.out.println("4. Listar la lista de empleados");
            System.out.println("5. Listar la lista de empleados segun departamento");
            System.out.println("6. Consultar por DNI");
            System.out.println("7. Consultar por salario superior");
            System.out.println("8. Consultar por salario igual o inferior");
            System.out.println("9. Subir sueldo a los empleados de un departamento");
            System.out.println("====================================================");
            System.out.println("Opciones de departamento disponibles:");
            System.out.println("10. Introducir un departamento");
            System.out.println("11. Modificar un departamento");
            System.out.println("12. Eliminar un departamento");
            System.out.println("13. Listar todos los departamentos");
            System.out.println("14. Mostrar informacion de un departamento");
            System.out.println("====================================================");
            System.out.println("15. Salir");

            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    Conector.introducirEmpleado(Controlador.crearEmpleado());
                    break;

                case 2:
                    Conector.modificarEmpleado(Controlador.crearEmpleado());
                    break;

                case 3:
                    System.out.println("Introduzca el dni: ");
                    sc.nextLine();
                    dni = sc.nextLine();
                    Conector.eliminarEmpleado(dni);
                    break;

                case 4:
                    Conector.listarEmpleados();
                    break;

                case 5:
                    System.out.println("Introduzca el numero de departamento: ");
                    numDep = sc.nextInt();
                    sc.nextLine();
                    Conector.listarEmpleadosPorDepartamento(numDep);
                    break;

                case 6:
                    System.out.println("Introduzca el dni: ");
                    sc.nextLine();
                    dni = sc.nextLine();
                    Conector.consultarDNI(dni);
                    break;

                case 7:
                    System.out.println("Introduzca el salario: ");
                    salario = sc.nextInt();
                    Conector.consultarSalarioSuperior(salario);
                    break;

                case 8:
                    System.out.println("Introduzca el salario: ");
                    salario = sc.nextInt();
                    Conector.consultarSalarioIgualInferior(salario);
                    break;

                case 9:
                    System.out.print("Introduzca el número de departamento: ");
                    numDep = sc.nextInt();
                    System.out.print("Introduzca el aumento del salario: ");
                    int aumentoSalario = sc.nextInt();
                    Conector.subirSueldoEmpleadosDepartamento(numDep, aumentoSalario);
                    break;

                case 10:
                    Conector.introducirDepartamento(Controlador.crearDepartamento());
                    break;

                case 11:
                    Conector.modificarDepartamento(Controlador.crearDepartamento());
                    break;

                case 12:
                    System.out.print("Introduzca el numero de departamento: ");
                    numDep = sc.nextInt();
                    sc.nextLine();
                    Conector.eliminarDepartamento(numDep);
                    break;

                case 13:
                    Conector.listarDepartamentos();
                    break;

                case 14:
                    System.out.println("Introduzca una de las posibles busquedas:");
                    System.out.println("1. Mostrar por ID");
                    System.out.println("2. Mostrar por nombre");
                    int opcionMostrar = sc.nextInt();
                    sc.nextLine();

                    if (opcionMostrar == 1) {
                        System.out.print("Introduzca el número de departamento: ");
                        numDep = sc.nextInt();
                        Conector.mostrarInformacionDepartamento(numDep, "");
                    }
                    else if (opcionMostrar == 2) {
                        System.out.print("Introduzca el nombre de departamento: ");
                        nombreDep = sc.nextLine();
                        Conector.mostrarInformacionDepartamento(0, nombreDep);
                    }
                    else {
                        System.out.println("Opción no válida");
                    }
                    break;

                case 15:
                    System.out.println("Saliendo del programa");
                    break;

                default:
                    System.out.println("ERROR: Introduzca una de las opciones disponibles");
                    break;
            }
        }
        while(opcion != 15);

        // Una vez finalizadas las operaciones, cerrar la conexión
        try {
            Conector.conexion.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
