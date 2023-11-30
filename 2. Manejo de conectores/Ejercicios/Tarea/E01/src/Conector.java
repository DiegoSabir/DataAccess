import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Conector {
    static String url = "jdbc:mysql://localhost:3306/empleados";
    static String user = "root";
    static String pass = "1234";
    static Connection conexion = null;
    static PreparedStatement consulta = null;
    static ResultSet resultado = null;

    /**
     * Establece la conexión a la base de datos utilizando la URL, usuario y contraseña proporcionados.
     * Captura cualquier excepción de tipo SQLException si hay un problema al conectar.
     */
    public static void conection() {
        try{
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexion establecida");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Realiza una consulta SQL para seleccionar todos los empleados de la tabla "empleado".
     * Itera a través del resultado y muestra el nombre de cada empleado en la consola.
     * Cierra el resultado y la consulta.
     * Maneja cualquier excepción de tipo SQLException que pueda ocurrir durante este proceso.
     */
    public static void listarEmpleados(){
        try{
            consulta = conexion.prepareStatement("SELECT * FROM empleado");
            resultado = consulta.executeQuery();

            while (resultado.next()) {
                String nombre = resultado.getString("Nombre");
                String apellido1 = resultado.getString("Apel1");
                String apellido2 = resultado.getString("Apel2");
                String dni = resultado.getString("NIF");

                System.out.println("Nombre: " + nombre);
                System.out.println("1º apellido: " + apellido1);
                System.out.println("2º apellido: " + apellido2);
                System.out.println("DNI: " + dni);
                System.out.println("-----------------------------------------");
            }

            resultado.close();
            consulta.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Prepara y ejecuta una consulta SQL para obtener empleados con un NIF específico.
     * Utiliza un PreparedStatement para evitar inyección de SQL al pasar el parámetro "nif".
     * Itera a través del resultado y muestra el nombre de cada empleado con el NIF proporcionado.
     * Cierra el resultado y la consulta.
     * Maneja cualquier excepción de tipo SQLException.
     */
    public static void consultarDNI(String nif) {
        String consulta = "SELECT * FROM empleado WHERE nif = ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ps.setString(1, nif);

            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                String nombre = resultado.getString("Nombre");
                String apellido1 = resultado.getString("Apel1");
                String apellido2 = resultado.getString("Apel2");

                System.out.println("Nombre: " + nombre);
                System.out.println("1º apellido: " + apellido1);
                System.out.println("2º apellido: " + apellido2);
            }

            resultado.close();
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prepara y ejecuta una consulta SQL para obtener empleados con un salario superior al valor proporcionado.
     * Utiliza un PreparedStatement para evitar la inyección de SQL al pasar el parámetro "salario".
     * Itera a través del resultado y muestra el nombre de cada empleado con salario superior al valor proporcionado.
     * Cierra el resultado y la consulta.
     * Maneja cualquier excepción de tipo SQLException.
     */
    public static void consultarSalarioSuperior(int salario) {
        String consulta = "SELECT * FROM empleado WHERE salario > ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);

            ps.setInt(1, salario);
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                String nombre = resultado.getString("Nombre");
                String apellido1 = resultado.getString("Apel1");
                String apellido2 = resultado.getString("Apel2");
                String dni = resultado.getString("NIF");

                System.out.println("Nombre: " + nombre);
                System.out.println("1º apellido: " + apellido1);
                System.out.println("2º apellido: " + apellido2);
                System.out.println("DNI: " + dni);
                System.out.println("-----------------------------------------");
            }

            resultado.close();
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prepara y ejecuta una consulta SQL para obtener empleados con un salario igual o inferior al valor proporcionado.
     * Utiliza un PreparedStatement para evitar la inyección de SQL al pasar el parámetro "salario".
     * Itera a través del resultado y muestra el nombre de cada empleado con salario igual o inferior al valor proporcionado.
     * Cierra el resultado y la consulta.
     * Maneja cualquier excepción de tipo SQLException.
     */
    public static void consultarSalarioIgualInferior(int salario) {
        String consulta = "SELECT * FROM empleado WHERE salario <= ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);

            ps.setInt(1, salario);
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                String nombre = resultado.getString("Nombre");
                String apellido1 = resultado.getString("Apel1");
                String apellido2 = resultado.getString("Apel2");
                String dni = resultado.getString("NIF");

                System.out.println("Nombre: " + nombre);
                System.out.println("1º apellido: " + apellido1);
                System.out.println("2º apellido: " + apellido2);
                System.out.println("DNI: " + dni);
                System.out.println("----------------------------------------");
            }

            resultado.close();
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
