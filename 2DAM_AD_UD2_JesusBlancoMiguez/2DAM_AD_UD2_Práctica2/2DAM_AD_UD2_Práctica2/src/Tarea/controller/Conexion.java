/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tarea.controller;

import Tarea.model.Departamento;
import Tarea.model.Empleado;
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

    static Connection conexion;
    static PreparedStatement statement = null;
    static ResultSet resultado = null;

    public static void conexion() throws SQLException {
        conexion = DriverManager.getConnection("jdbc:mysql://localhost/empleados", "root", "root");
    }

    public static void listaEmpleados() {
        try {

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

        String consulta = "SELECT * FROM empleado WHERE nif = ?";

        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
            statement.setString(1, nif);

            ResultSet resultado = statement.executeQuery();

            mostrar(resultado);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static void consultarSalarioS(int salario) {
        String consulta = "SELECT * FROM empleado WHERE salario > ?";

        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {

            statement.setDouble(1, salario);
            ResultSet resultado = statement.executeQuery();

            mostrar(resultado);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void consultarSalarioI(int salario) {
        String consulta = "SELECT * FROM empleado WHERE salario <= ?";

        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {

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

    public static void eliminarD(int numero) throws SQLException {
        String consulta = "DELETE FROM departamento WHERE Numdep = ?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
            preparedStatement.setInt(1, numero);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void insertD(Departamento depart) throws SQLException {
        String consulta = "INSERT INTO departamento (Numdep, Nombredep) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
            preparedStatement.setInt(1, depart.numero);
            preparedStatement.setString(2, depart.nombre);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void modifD(Departamento depart) throws SQLException {
        String consulta = "UPDATE departamento SET Nombredep = ? WHERE Numdep = ?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
            preparedStatement.setString(1, depart.nombre);
            preparedStatement.setInt(2, depart.numero);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void insertE(Empleado emp) throws SQLException {
        String consulta = "INSERT INTO empleado (NSS, Nombre, Apel1, Apel2, Sexo, Dirección, Salario, NIF) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
            preparedStatement.setInt(1, emp.nss);
            preparedStatement.setString(2, emp.nombre);
            preparedStatement.setString(3, emp.apel1ido);
            preparedStatement.setString(4, emp.apel1ido2);
            preparedStatement.setString(5, emp.sexo);
            preparedStatement.setString(6, emp.direccion);
            preparedStatement.setInt(7, emp.salario);
            preparedStatement.setString(8, emp.nIF);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void modifE(Empleado emp) throws SQLException {
        String consulta = "UPDATE empleado SET NSS = ?, Nombre = ?, Apel1 = ?, Apel2 = ?, Sexo = ?, Dirección = ?, Salario = ? WHERE NIF = ?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
            preparedStatement.setInt(1, emp.nss);
            preparedStatement.setString(2, emp.nombre);
            preparedStatement.setString(3, emp.apel1ido);
            preparedStatement.setString(4, emp.apel1ido2);
            preparedStatement.setString(5, emp.sexo);
            preparedStatement.setString(6, emp.direccion);
            preparedStatement.setInt(7, emp.salario);
            preparedStatement.setString(8, emp.nIF);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void eliminarE(String nif) throws SQLException {
        String consulta = "DELETE FROM empleado WHERE NIF = ?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(consulta)) {
            preparedStatement.setString(1, nif);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
