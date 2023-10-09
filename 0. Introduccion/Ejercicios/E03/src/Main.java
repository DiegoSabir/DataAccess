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

    public static int getIntegerPart(double number) {
        String stringNumber = Double.toString(number);

        int decimalPointPosition  = stringNumber.indexOf('.');

        String integerPartString = stringNumber.substring(0, decimalPointPosition );

        int intergerPart = Integer.parseInt(integerPartString);

        return intergerPart;
    }

    public static int getDecimalPart(double number) {
        String stringNumber = Double.toString(number);

        int decimalPointPosition = stringNumber.indexOf('.');

        String decimalPartString  = stringNumber.substring(decimalPointPosition + 1);

        int decimalPart  = Integer.parseInt(decimalPartString);

        return decimalPart ;
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        double number;

        do {
            System.out.print("Enter a number (0 to exit): ");
            number = scanner.nextDouble();

            if (number != 0) {
                System.out.println("1. Get integer part");
                System.out.println("2. Get decimal part");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        int integerPart = getIntegerPart(number);
                        System.out.println("Integer part: " + integerPart);
                        break;
                    case 2:
                        int decimalPart = getDecimalPart(number);
                        System.out.println("Decimal part: " + decimalPart);
                        break;
                    case 3:
                        System.out.println("Exiting");
                        break;
                    default:
                        System.out.println("ERROR: INVALID OPTION.");
                        break;
                }
            }
        } while (number != 0);

        scanner.close();
    }
    public static void main(String[] args) {
        menu();
    }
}
