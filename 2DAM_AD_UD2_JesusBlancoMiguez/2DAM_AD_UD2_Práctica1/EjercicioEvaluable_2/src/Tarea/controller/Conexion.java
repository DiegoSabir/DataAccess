/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tarea.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author a22jesusbm
 */
public class Conexion {

    static Connection conexion = null;
    static PreparedStatement statement = null;
    static ResultSet resultado = null;

    public static void listaEmpleados() throws SQLException {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/empleados", "root", "root");
            statement = conexion.prepareStatement("SELECT * FROM empleado");
            resultado = statement.executeQuery();

            mostrar(resultado);
            statement.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void consultarNif(String nif) {

        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empleados", "root", "root")) {
            String consulta = "SELECT * FROM empleado WHERE nif = ?";

            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                statement.setString(1, nif);

                ResultSet resultado = statement.executeQuery();

                mostrar(resultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void consultarSalarioS(int salario) {
        String consulta = "SELECT * FROM empleado WHERE salario > ?";

        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empleados", "root", "root"); PreparedStatement statement = conexion.prepareStatement(consulta)) {

            statement.setDouble(1, salario);
            ResultSet resultado = statement.executeQuery();

            mostrar(resultado);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void consultarSalarioI(int salario) {
        String consulta = "SELECT * FROM empleado WHERE salario <= ?";

        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empleados", "root", "root"); PreparedStatement statement = conexion.prepareStatement(consulta)) {

            statement.setDouble(1, salario);
            ResultSet resultado = statement.executeQuery();

            mostrar(resultado);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mostrar(ResultSet resultado) throws SQLException {
        while (resultado.next()) {
            String nombre = resultado.getString("nombre");
            System.out.println("Nombre: " + nombre);
        }
        resultado.close();
    }
}
