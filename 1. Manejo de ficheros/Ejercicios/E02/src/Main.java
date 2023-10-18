/**
 * Crear un programa que muestre los contenidos de un fichero de texto línea a línea, numerando las líneas.
 * (readLine de BufferedReader)
 */
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br =  new BufferedReader(new FileReader("C:\\Users\\a18diegorg\\Documents\\GitHub\\DataAccess\\1. Manejo de ficheros\\Ejercicios\\E02\\Documento.txt"))) {
            String line;
            int lineNumber = 1;

            while ((line = br.readLine()) != null) {
                System.out.println(lineNumber + ":" + line);
                lineNumber++;
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}