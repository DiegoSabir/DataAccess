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
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Enter the name of the first person: ");
        String name1 = sc.nextLine();
        LocalDate date1 = askAge(sc, dateFormat);

        System.out.print("Enter the name of the second person: ");
        String name2 = sc.nextLine();
        LocalDate date2 = askAge(sc, dateFormat);

        int age1 = calculateAge(date1);
        int age2 = calculateAge(date2);

        String youngest, oldest;
        if (age1 < age2) {
            youngest = name1;
            oldest = name2;
        }
        else if (age2 < age1) {
            youngest = name2;
            oldest = name1;
        }
        else {
            System.out.println("Both are the same age.");
            return;
        }

        System.out.println(youngest + " is youngest");
        System.out.println(name1 + " has " + age1 + " years old and " + name2 + " has " + age2 + " years old");
    }

    private static LocalDate askAge(Scanner sc, DateTimeFormatter dateFormat) {
        LocalDate date = null;
        while (date == null) {
            System.out.print("Enter date of birth [dd/mm/aaaa]: ");
            String dateString = sc.nextLine();
            try {
                date = LocalDate.parse(dateString, dateFormat);
            }
            catch (DateTimeParseException e) {
                System.out.println("ERROR: INCORRECT FORMAT DATE");
            }
        }
        return date;
    }

    private static int calculateAge(LocalDate birthDate) {
        LocalDate actualDate = LocalDate.now();
        int age = actualDate.getYear() - birthDate.getYear();

        if (actualDate.getMonth().compareTo(birthDate.getMonth()) < 0
                || (actualDate.getMonth().compareTo(birthDate.getMonth()) == 0
                && actualDate.getDayOfMonth() < birthDate.getDayOfMonth())) {
            age--;
        }
        return age;
    }
}
