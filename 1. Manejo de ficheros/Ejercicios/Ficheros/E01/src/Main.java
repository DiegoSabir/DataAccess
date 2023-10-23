/**
 * Crear un programa que muestre por defecto un listado de los ficheros y directorios que contiene el directorio desde
 * el que se ejecuta el programa. Si se le pasa la ruta de un directorio o fichero, muestra la información acerca de él,
 * y, si se trata de un directorio, muestra los ficheros y directorios que contiene.
 */

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main m = new Main();
        m.listarCarpetas();
    }

    private void listarCarpetas() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce la ruta del directorio: ");
        String ruta = scanner.nextLine();
        scanner.close();

        File directorio = new File(ruta);

        if (directorio.exists() && directorio.isDirectory()) {
            for (String Directorio : Objects.requireNonNull(directorio.list())) {
                System.out.println(Directorio);
            }
        }
        else {
            System.out.println("La ruta especificada no es un directorio válido o no existe.");
        }
    }
}
