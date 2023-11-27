import java.sql.*;

public class Main {
    public static void main(String[] args) {

    }

    public static void conection() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/empleados";
        String user = "root";
        String pass = "1234";

        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;

        try{
            //CONEXION A LA BASE DE DATOS
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexion establecida");

            //HACER LA CONSULTA
            consulta = conexion.prepareStatement("SELECT * FROM empleado");

            //EJECUTAR LA CONSULTA
            resultado = consulta.executeQuery();

            //RECORRER EL ResultSet
            while(resultado.next()) {
                int numero = resultado.getInt("NSS");
                String nombre = resultado.getString("Nombre");
                String apellido = resultado.getString("Apel1");
                String apellido2 = resultado.getString("Apel2");
                String sexo = resultado.getString("Sexo");
                String direccion = resultado.getString("Direccion");
                String nacimiento = resultado.getString("Fechanac");
                int salario = resultado.getInt("Salario");
                int departamento = resultado.getInt("Numdept");
                String seguridadsocial = resultado.getString("NSSsup");

                System.out.println(numero + " " + nombre + " " + apellido + " " + apellido2);
            }
        }
        catch(Exception e) {
            System.out.println("La conexion no ha sido establecida");
            e.printStackTrace();
        }
        finally{
            try{
                if (resultado != null) {
                    resultado.close();
                }
                if (consulta != null) {
                    consulta.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}