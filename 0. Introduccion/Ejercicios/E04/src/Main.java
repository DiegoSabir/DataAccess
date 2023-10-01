/**
 * Haz un programa que pida el nombre y la fecha de nacimiento de dos personas.
 * El nombre en una línea y la fecha en la siguiente.
 * La fecha se escribirá en el formato dd/mm/aaaa, siendo dd un entero que representa al día,
 * mm un entero que representa al mes (entre 1 y 12) y aaaa el año.
 * Finalmente, el programa dirá quién es menor, y qué edad tienen en el momento de la ejecución.
 * No hay ningún método del API que calcule esto directamente.
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Pedir el nombre y la fecha de nacimiento de la primera persona
        System.out.print("Introduce primer nombre: ");
        String nombre1 = scanner.nextLine();
        LocalDate fechaNacimiento1 = pedirFecha(scanner, "Introduce primera fecha de nacimiento [dd/mm/aaaa]: ", dateFormat);

        // Pedir el nombre y la fecha de nacimiento de la segunda persona
        System.out.print("Introduce segundo nombre: ");
        String nombre2 = scanner.nextLine();
        LocalDate fechaNacimiento2 = pedirFecha(scanner, "Introduce segunda fecha de nacimiento [dd/mm/aaaa]: ", dateFormat);

        // Calcular edades
        int edad1 = calcularEdad(fechaNacimiento1);
        int edad2 = calcularEdad(fechaNacimiento2);

        // Determinar quién es menor
        String personaMenor, personaMayor;
        if (edad1 < edad2) {
            personaMenor = nombre1;
            personaMayor = nombre2;
        } else if (edad2 < edad1) {
            personaMenor = nombre2;
            personaMayor = nombre1;
        } else {
            System.out.println("Ambas personas tienen la misma edad.");
            scanner.close();
            return;
        }

        // Mostrar resultados
        System.out.println(personaMenor + " es menor");
        System.out.println(nombre1 + " tiene " + edad1 + " años y " + nombre2 + " tiene " + edad2 + " años.");

        scanner.close();
    }

    private static LocalDate pedirFecha(Scanner scanner, String mensaje, DateTimeFormatter dateFormat) {
        LocalDate fecha = null;
        while (fecha == null) {
            System.out.print(mensaje);
            String fechaStr = scanner.nextLine();
            try {
                fecha = LocalDate.parse(fechaStr, dateFormat);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. Debe ser dd/mm/aaaa.");
            }
        }
        return fecha;
    }

    private static int calcularEdad(LocalDate fechaNacimiento) {
        LocalDate fechaActual = LocalDate.now();
        int edad = fechaActual.getYear() - fechaNacimiento.getYear();

        // Ajustar la edad si el mes actual es anterior al mes de nacimiento o si es el mismo mes pero el día actual es anterior al día de nacimiento
        if (fechaActual.getMonth().compareTo(fechaNacimiento.getMonth()) < 0 ||
                (fechaActual.getMonth().compareTo(fechaNacimiento.getMonth()) == 0 &&
                        fechaActual.getDayOfMonth() < fechaNacimiento.getDayOfMonth())) {
            edad--;
        }

        return edad;
    }
}
