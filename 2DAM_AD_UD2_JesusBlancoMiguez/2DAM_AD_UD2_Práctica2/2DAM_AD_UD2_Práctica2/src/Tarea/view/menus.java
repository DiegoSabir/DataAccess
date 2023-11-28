/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tarea.view;

import java.util.Scanner;
import Tarea.controller.Conexion;
import Tarea.model.Departamento;
import Tarea.model.Empleado;
import java.sql.SQLException;

/**
 *
 * @author a22jesusbm
 */
public class menus {
    public static void main(String[] args) throws SQLException {
        boolean exit = false;
        

        while (!exit) {
            Conexion.conexion();
            int opcion;
            System.out.println("");
            System.out.println("Menú de Consultas:");
            System.out.println("1. Lista empleados");
            System.out.println("2. Consultar NIF");
            System.out.println("3. Consultar por salario superior al introducido");
            System.out.println("4. Consultar por salario igual o inferior al introducido");
            System.out.println("5. Insert Empleado");
            System.out.println("6. Modificar Empleado");
            System.out.println("7. Eliminar Empleado");
            System.out.println("8. Insertar Departamento");
            System.out.println("9. Modificar Departamento");
            System.out.println("10. Eliminar Departamento");
            System.out.println("11. Salir");
            System.out.print("Ingrese una opcion: ");
            opcion = Integer.parseInt(new Scanner(System.in).nextLine());

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
                    Conexion.insertE(pedirEmpleado());
                }
                case 6 -> {
                    System.out.print("Introduzca nif:");
                    String nif = new Scanner(System.in).nextLine();
                    Conexion.eliminarE(nif);
                }
                case 7 -> {
                    Conexion.modifE(pedirEmpleado());
                }
                case 8 -> {
                    Conexion.insertD(pedirDepartamento());
                }
                case 9 -> {
                    Conexion.modifD(pedirDepartamento());
                }
                case 10 -> {
                    System.out.print("Numero departamento: ");
                    int numero = new Scanner(System.in).nextInt();
                    Conexion.eliminarD(numero);
                }
                case 11 -> {
                    exit = true;
                    System.out.println("Saliendo del menú...");
                }
                default ->
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    public static int pedirNumero() {
        System.out.print("Numero departamento: ");
        return new Scanner(System.in).nextInt();
    }

    public static Departamento pedirDepartamento() {
        System.out.print("Numero departamento: ");
        int numero = new Scanner(System.in).nextInt();
        System.out.print("Nombre departamento: ");
        String nombre = new Scanner(System.in).nextLine();
        return new Departamento(numero, nombre);
    }
    //poner un dni real si no deja insertar
    public static boolean validarNIF(String nif) {
        final int LONGITUD = 9;
        final String LETRAS = "TRWAGMYFPDXBNJZSQVHLCKE";

        if (nif.length() != LONGITUD) {
            return false;
        }
        char letra = Character.toUpperCase(nif.charAt(8));
        String numero = nif.substring(0, 8);

        try {
            int num = Integer.parseInt(numero);
            char letra2 = LETRAS.charAt(num % LETRAS.length());
            return letra == letra2;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validarNSS(int nss) {
        String nssString = String.valueOf(nss);
        return nssString.length() == 5;
    }

    public static boolean validarSexo(String sexo) {
        return sexo.equals("M") || sexo.equals("F");
    }

    public static Empleado pedirEmpleado() {
        Scanner scanner = new Scanner(System.in);
        String nif;
        do {
            System.out.print("NIF: ");
            nif = scanner.nextLine();
        } while (!validarNIF(nif));

        int nss;
        do {
            System.out.print("NSS: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido para NSS.");
                System.out.print("NSS: ");
                scanner.next();
            }
            nss = scanner.nextInt();
        } while (!validarNSS(nss));

        scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Apellido2: ");
        String apellido2 = scanner.nextLine();
        String sexo;
        do {
            System.out.print("Sexo: ");
            sexo = scanner.nextLine();
        } while (!validarSexo(sexo));

        System.out.print("Direccion: ");
        String direccion = scanner.nextLine();

        System.out.print("Salario: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Salario incorrecto");
            System.out.print("Salario: ");
            scanner.next();
        }
        int salario = scanner.nextInt();

        scanner.nextLine();
        scanner.close();

        return new Empleado(nss, nombre, apellido, apellido2, sexo, direccion, salario, nif);
    }
}
