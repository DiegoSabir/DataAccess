/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tarea.view;

import java.util.Scanner;
import Tarea.controller.Conexion;
import java.sql.SQLException;

/**
 *
 * @author a22jesusbm
 */
public class menus {

    public static void main(String[] args) throws SQLException {
        boolean exit = false;

        while (!exit) {
            int opcion;
            System.out.println("");
            System.out.println("Menú de Consultas:");
            System.out.println("1. Lista empleados");
            System.out.println("2. Consultar NIF");
            System.out.println("3. Consultar por salario superior al introducido");
            System.out.println("4. Consultar por salario igual o inferior al introducido");
            System.out.println("5. Salir");
            System.out.print("Ingrese una opcion: ");
            opcion = new Scanner(System.in).nextInt();

            switch (opcion) {
                case 1 ->
                    Conexion.listaEmpleados();
                case 2 -> {
                    System.out.print("Introduzca nif:");
                    String nif = new Scanner(System.in).nextLine();
                    Conexion.consultarNif(nif);
                }
                case 3 -> {
                    System.out.print("Introduzca Salario:");
                    int salsup = new Scanner(System.in).nextInt();
                    Conexion.consultarSalarioS(salsup);
                }
                case 4 -> {
                    System.out.print("Introduzca salario:");
                    int salario = new Scanner(System.in).nextInt();
                    Conexion.consultarSalarioI(salario);
                }
                case 5 -> {
                    exit = true;
                    System.out.println("Saliendo del menú...");
                }
                default ->
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

}
