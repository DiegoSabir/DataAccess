package view;

import model.Autor;
import model.Libro;
import util.Actuator;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    static Scanner sc = new Scanner(System.in);;
    static Actuator actuator;
    public static void main(String[] args) {
        actuator = new Actuator();
        int opcion;
        do {
            System.out.println("Opciones del menu:\n"
                    + "1. Inserción\n"
                    + "2. Borrado\n"
                    + "3. Consultar\n"
                    + "4. Salir\n");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    insercion();
                    break;

                case 2:
                    borrado();
                    break;

                case 3:
                    consultar();
                    break;

                case 4:
                    System.out.println("Saliendo del programa");
                    break;

                default:
                    System.out.println("ERROR: Introduzca una de las opciones disponibles");
            }
        }
        while (opcion != 5);
    }

    private static void insercion() {
        int opcion;
        do {
            System.out.println("Introduzca una opcion:\n"
                    + "1. Añadir autor\n"
                    + "2. Añadir libro\n");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    try{
                        sc = new Scanner(System.in);
                        System.out.println("Introduzca dni:");
                        String dni = sc.nextLine();

                        System.out.println("Introduzca nombre:");
                        String nombre = sc.nextLine();

                        System.out.println("Introduzca nacionalidad:");
                        String nacionalidad = sc.nextLine();

                        actuator.insert(new Autor(dni,nombre,nacionalidad));
                    }
                    catch(Exception e){
                        throw new InputMismatchException("ERROR: Introduzca los datos correctos" + e.getLocalizedMessage());
                    }
                    break;

                case 2:
                    try{
                        sc = new Scanner(System.in);
                        System.out.println("Introduzca titulo:");
                        String titulo = sc.nextLine();

                        System.out.println("Introduzca precio");
                        float precio = sc.nextFloat();
                        sc.nextLine();

                        System.out.println("Introduzca dni del autor");
                        String dniAutor = sc.nextLine();
                        Autor autor = actuator.getDni(dniAutor);

                        if(autor != null) {
                            actuator.insert(new Libro(titulo, precio, autor));
                        }
                        else{
                            throw new InputMismatchException("ERROR: Autor no existente en la base de datos");
                        }
                    }
                    catch(Exception ex){
                        throw new InputMismatchException("ERROR: Introduzca los datos correctos" + ex.getLocalizedMessage());
                    }
                    break;

                default:
                    System.out.println("ERROR: Introduzca una de las opciones disponibles");
            }
        }
        while (opcion != 2);
    }

    private static void borrado() {
        int opcion;
        do {
            System.out.println("Introduzca una opcion:\n"
                    + "1. Borrado autor\n"
                    + "2. Borrado libro\n");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    try{
                        sc = new Scanner(System.in);
                        System.out.println("Introduzca dni del autor");
                        String dniAutor = sc.nextLine();
                        Autor author = actuator.getDni(dniAutor);
                        actuator.delete(author);
                    }
                    catch(InputMismatchException ex){
                        System.out.println(ex.getLocalizedMessage());
                    }
                    break;

                case 2:
                    try{
                        sc = new Scanner(System.in);
                        System.out.println("Introduzca identicacion del libro");
                        int idLibro = sc.nextInt(); sc.nextLine();

                        Libro libro = actuator.getIdLibro(idLibro);
                        actuator.delete(libro);
                    }
                    catch(InputMismatchException e){
                        System.out.println(e.getLocalizedMessage());
                    }
                    break;

                default:
                    System.out.println("ERROR: Introduzca una de las opciones disponibles");
            }
        }
        while (opcion != 2);
    }

    private static void consultar() {
        int opcion;
        do {
            System.out.println("Introduzca una opcion:\n"
                    + "1. Consulta por titulo\n"
                    + "2. Consulta por autor\n"
                    + "3. Consultar todos los libros\n"
                    + "4. Consultar todos los autores\n");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    try{
                        sc = new Scanner(System.in);
                        System.out.println("Introduzca el titulo");
                        String titulo = sc.nextLine();
                        Libro libro = actuator.getLibroByTitulo(titulo);
                        System.out.println(libro);
                    }
                    catch(InputMismatchException ex){
                        System.out.println(ex.getLocalizedMessage());
                    }
                    break;

                case 2:
                    try{
                        sc = new Scanner(System.in);
                        System.out.println("Introduzca el nombre del autor");
                        String nombre = sc.nextLine();
                        List libros = actuator.getLibroByAutor(nombre);
                        System.out.println(libros);
                    }
                    catch(InputMismatchException ex){
                        System.out.println(ex.getLocalizedMessage());
                    }
                    break;

                case 3:
                    try{
                        List libros = actuator.getAllLibros();
                        System.out.println(libros);
                    }
                    catch(InputMismatchException ex){
                        System.out.println(ex.getLocalizedMessage());
                    }
                    break;

                case 4:

                    break;

                default:
                    System.out.println("ERROR: Introduzca una de las opciones disponibles");
            }
        }
        while (opcion != 4);
    }
}
