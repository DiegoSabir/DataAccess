package view;

import model.Autor;
import model.Libro;
import util.HibernateManager;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);;
        HibernateManager hb = new HibernateManager();
        int opcion;
        do {
            System.out.println("Opciones del menu:\n"
                    + "1. Inserción\n"
                    + "2. Borrado\n"
                    + "3. Consultar\n"
                    + "4. Salir\n" +
                    "=================");

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
}