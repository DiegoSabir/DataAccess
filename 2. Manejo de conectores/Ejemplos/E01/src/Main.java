import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {

        //1º URL DE CONEXION
        String url = "jdbc:mysql://localhost:3306/empleados";
        String usuario = "root";
        String pass = "1234";

        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;

        try{
            //Registramos el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Establecer la conexion
            conexion = DriverManager.getConnection(url,usuario,pass);
            System.out.println("Se ha establecido la conexion");

            /**
            //Codigo SQL de la consulta
            String sql = "SELECT * FROM proyecto";

            consulta = conexion.prepareStatement(sql);
            resultado = consulta.executeQuery();

            //Recorre y muestra los resultados
            while (resultado.next()){
                int numpro = resultado.getInt(1);
                String nombrep = resultado.getString(2);
                String lugarp = resultado.getString(3);
                int depnumdep = resultado.getInt(4);
                //Agrega mas columnas segun tu tabla

                System.out.println("NumProy: " + numpro + "Nombreproj:" + nombrep + "Lugarproj:" + lugarp + "departamento_Numdep:" + depnumdep);
            }
            */

            String sql = "INSERT INTO salarios(codigo, categoria)"
                    + "VALUES ('001','Programador Junior'),"
                    + "('002','Programador Senior'),"
                    + "('003','Analista Junior'),"
                    + "('004','Analista'),"
                    + "('005','Analista Senior')";

            consulta = conexion.prepareStatement(sql);
            int n = consulta.executeUpdate(sql);
            
            if (n>0){
                conexion.commit();
                System.out.println("Se ha insertado en la BBDD");
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

    //CREAR TABLA


}