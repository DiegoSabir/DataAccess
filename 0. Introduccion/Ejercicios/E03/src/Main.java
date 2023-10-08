/**
 * Implementa dos funciones para obtener, la parte entera y la parte decimal de un número en punto flotante (double).
 * La definición de las funciones es como sigue: int getParteEntera(double numero) int getParteDecimal(double numero)
 * Internamente, estas dos funciones convierten el número double a cadena de caracteres para obtener la parte correspondiente.
 * Una vez obtenido el valor buscado, la cadena se convertirá al tipo de retorno indicado.
 * Además el programa principal (main) pedirá al usuario que introduzca un número por teclado y
 * posteriormente mostrará un menú por pantalla en el que se pregunte si desea obtener la parte entera o decimal del número introducido.
 * El programa principal main se ejecutará hasta que el usuario introduzca la opción adecuada para salir.
 */
import java.util.Scanner;

public class Main {

    public static int getParteEntera(double numero) {
        // Convierte el número double a una cadena de caracteres
        String numeroStr = Double.toString(numero);

        // Encuentra la posición del punto decimal
        int puntoPos = numeroStr.indexOf('.');

        // Extrae la parte entera como una subcadena
        String parteEnteraStr = numeroStr.substring(0, puntoPos);

        // Convierte la parte entera a un entero
        int parteEntera = Integer.parseInt(parteEnteraStr);

        return parteEntera;
    }

    // Función para obtener la parte decimal de un número double
    public static int getParteDecimal(double numero) {
        // Convierte el número double a una cadena de caracteres
        String numeroStr = Double.toString(numero);

        // Encuentra la posición del punto decimal
        int puntoPos = numeroStr.indexOf('.');

        // Extrae la parte decimal como una subcadena
        String parteDecimalStr = numeroStr.substring(puntoPos + 1);

        // Convierte la parte decimal a un entero
        int parteDecimal = Integer.parseInt(parteDecimalStr);

        return parteDecimal;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double numero;

        do {
            System.out.print("Introduce un número (o 0 para salir): ");
            numero = scanner.nextDouble();

            if (numero != 0) {
                System.out.println("1. Obtener parte entera");
                System.out.println("2. Obtener parte decimal");
                System.out.println("3. Salir");
                System.out.print("Elije una opción: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        int parteEntera = getParteEntera(numero);
                        System.out.println("Parte entera: " + parteEntera);
                        break;
                    case 2:
                        int parteDecimal = getParteDecimal(numero);
                        System.out.println("Parte decimal: " + parteDecimal);
                        break;
                    case 3:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            }
        } while (numero != 0);

        scanner.close();
    }
}
