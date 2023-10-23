import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Implementa un programa que lea el contenido de un fichero y cuente el número de líneas que tiene el fichero.
 */
public class Main {
    public static void main(String[] args) {
        try (FileInputStream fis =  new FileInputStream("C:\\Users\\a18diegorg\\Documents\\GitHub\\DataAccess\\1. Manejo de ficheros\\Ejercicios\\E04\\Documento.txt")) {
            int counterLines = 1;
            int c;
            while ((c = fis.read()) != -1) {
                if (c == 10) {
                    counterLines++;
                }
            }
            System.out.println("Existen " + counterLines + " líneas en el fichero.");
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}