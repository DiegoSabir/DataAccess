/**
 * Crear un programa que muestre por defecto un listado de los ficheros y directorios que contiene el directorio desde
 * el que se ejecuta el programa. Si se le pasa la ruta de un directorio o fichero, muestra la información acerca de él,
 * y, si se trata de un directorio, muestra los ficheros y directorios que contiene.
 */

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String ruta = ".";
        if (args.length >= 1) {
            ruta = args[0];
        }
        File file = new File(ruta);
        if(!file.exists()) {
            System.out.println("No existe el fichero o directorio (" + ruta + ").");
        }
        else {
            if(file.isFile()) {
                System.out.println(ruta + " es un fichero.");
            }
            else {
                System.out.println(ruta+" es un directorio. Contenidos: ");
                File[] ficheros = file.listFiles(); // Ojo, ficheros o directorios
                for(File f : ficheros) {
                    String textoDescr = f.isDirectory() ? "/" :
                            f.isFile() ? "_" : "?";
                    System.out.println("(" + textoDescr + ") " + f.getName());
                }
            }
        }
    }
}
