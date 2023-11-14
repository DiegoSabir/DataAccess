/**
 * Crear un programa que muestre los contenidos de un fichero de texto línea a línea, numerando las líneas.
 * (readLine de BufferedReader)
 */
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce el nombre del fichero: ");
        String file = sc.nextLine();

        try (BufferedReader fbr = new BufferedReader(new FileReader(file))) {
            int i = 0;
            String linea = fbr.readLine();
            while (linea != null) {
                System.out.format("[%5d] %s", i++, linea);
                System.out.println();
                linea = fbr.readLine();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("No existe fichero " + file);
        }
        catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}