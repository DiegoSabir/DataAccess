import java.util.Scanner;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String dni;
        int salario, opcion;

        Conector.conection();

        do {
            System.out.println("Opciones del menu:");
            System.out.println("==========================================");
            System.out.println("1. Lista de empleados");
            System.out.println("2. Consultar NIF");
            System.out.println("3. Consultar por salario superior");
            System.out.println("4. Consultar por salario igual o inferior al introducido");
            System.out.println("==========================================");
            System.out.println("5. Salir");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    Conector.listarEmpleados();
                    break;

                case 2:
                    System.out.println("Introduzca el dni: ");
                    sc.nextLine(); // Consumir salto de línea pendiente
                    dni = sc.nextLine();
                    Conector.consultarDNI(dni);
                    break;

                case 3:
                    System.out.println("Introduzca el salario: ");
                    salario = sc.nextInt();
                    Conector.consultarSalarioSuperior(salario);
                    break;

                case 4:
                    System.out.println("Introduzca el salario: ");
                    salario = sc.nextInt();
                    Conector.consultarSalarioIgualInferior(salario);
                    break;

                case 5:
                    System.out.println("Saliendo del programa");
                    break;

                default:
                    System.out.println("ERROR: Introduzca una de las opciones disponibles");
                    break;
            }
        }
        while(opcion != 5);

        // Una vez finalizadas las operaciones, cerrar la conexión
        try {
            Conector.conexion.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
