import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        //1º URL DE CONEXION
        String url = "\"jdbc:mysql://localhost/empresa";
        String usuario = "root";
        String pass = "";

        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;

        try{
            //Registramos el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Establecer la conexion
            conexion = DriverManager.getConnection(url,usuario,pass);
            System.out.println("Se ha establecido la conexion");

            //Codigo SQL de la consulta
            String sql = "SELECT * FROM projectos";

            consulta = conexion.prepareStatement(sql);
            resultado = consulta.executeQuery();

            //Recorre y muestra los resultados
            while (resultado.next()){
                int numpro = resultado.getInt("Numproy");
                String nombrep = resultado.getString("Nombreproj");
                String lugarp = resultado.getString("Lugarproj");
                int depnumdep = resultado.getInt("departamento_Numdep");
                //Agrega mas columnas segun tu tabla

                System.out.println("NumProy: " + numpro + "Nombreproj:" + nombrep + "Lugarproj:" + lugarp + "departamento_Numdep:" + depnumdep;
            }

        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try{
                if (resultado != null){
                    resultado.close();
                }
                if (consulta != null){
                    consulta.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}