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

    public static void conection() {
        try{
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexion establecida");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void introducirEmpleado(Empleado empleado) {
        String consulta = "INSERT INTO empleado (NSS, Nombre, Apel1, Apel2, Sexo, Dirección, Salario, NIF) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);

            ps.setInt(1, empleado.getSalario());
            ps.setString(2, empleado.getNombre());
            ps.setString(3, empleado.getApellido1());
            ps.setString(4, empleado.getApellido2());
            ps.setString(5, empleado.getSexo());
            ps.setString(6, empleado.getDireccion());
            ps.setInt(7, empleado.getNss());
            ps.setString(8, empleado.getDni());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modificarEmpleado(Empleado empleado) {
        String consulta = "UPDATE empleado SET NSS = ?, Nombre = ?, Apel1 = ?, Apel2 = ?, Sexo = ?, Dirección = ?, Salario = ? WHERE NIF = ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);

            ps.setInt(1, empleado.getSalario());
            ps.setString(2, empleado.getNombre());
            ps.setString(3, empleado.getApellido1());
            ps.setString(4, empleado.getApellido2());
            ps.setString(5, empleado.getSexo());
            ps.setString(6, empleado.getDireccion());
            ps.setInt(7, empleado.getNss());
            ps.setString(8, empleado.getDni());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarEmpleado(String nif) {
        String consulta = "DELETE FROM empleado WHERE NIF = ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);

            ps.setString(1, nif);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public static void listarEmpleadosPorDepartamento(int numdept){
        try {
            String consulta = "SELECT * FROM empleado WHERE Numdept = ?";
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ps.setInt(1, numdept);

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

    public static void subirSueldoEmpleadosDepartamento(int numDept, int aumento) {
        String consulta = "UPDATE empleado SET Salario = Salario + ? WHERE NumDept = ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);

            ps.setInt(1, aumento);
            ps.setInt(2, numDept);

            ps.executeUpdate();
            System.out.println("Los sueldos de los empleados del departamento " + numDept + " han subido " + aumento + "€");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void introducirDepartamento(Departamento departamento) {
        String consulta = "INSERT INTO departamento (Numdep, Nombredep) VALUES (?, ?)";

        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);

            ps.setInt(1, departamento.getNumero());
            ps.setString(2, departamento.getNombre());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modificarDepartamento(Departamento departamento) {
        String consulta = "UPDATE departamento SET Nombredep = ? WHERE Numdep = ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);

            ps.setString(1, departamento.getNombre());
            ps.setInt(2, departamento.getNumero());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarDepartamento(int numero) {
        String consulta = "DELETE FROM departamento WHERE Numdep = ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);

            ps.setInt(1, numero);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listarDepartamentos(){
        try{
            consulta = conexion.prepareStatement("SELECT * FROM departamento");
            resultado = consulta.executeQuery();

            while (resultado.next()) {
                int numdep = resultado.getInt("Numdep");
                String nombredep = resultado.getString("Nombredep");
                int numempdep = resultado.getInt("Numempdep");
                String nssgerente = resultado.getString("NSSgerente");

                System.out.println("Número: " + numdep);
                System.out.println("Nombre: " + nombredep);
                System.out.println("Cantidad de empleados: " + numempdep);
                System.out.println("NSS del gerente: " + nssgerente);
                System.out.println("-----------------------------");
            }

            resultado.close();
            consulta.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void mostrarInformacionDepartamento(int numDep, String nombreDep) {
        String consulta;
        PreparedStatement ps = null;
        ResultSet resultado = null;

        try {
            // Si se proporciona el numero
            if (numDep != 0) {
                consulta = "SELECT * FROM departamento WHERE Numdep = ?";
                ps = conexion.prepareStatement(consulta);
                ps.setInt(1, numDep);
            }
            // Si se proporciona el nombre
            else if (!nombreDep.isEmpty()) {
                consulta = "SELECT * FROM departamento WHERE Nombredep = ?";
                ps = conexion.prepareStatement(consulta);
                ps.setString(1, nombreDep);
            }
            else {
                System.out.println("No se proporcionó información suficiente.");
                return;
            }

            resultado = ps.executeQuery();

            while (resultado.next()) {
                int numdep = resultado.getInt("Numdep");
                String nombredep = resultado.getString("Nombredep");
                int numempdep = resultado.getInt("Numempdep");
                String nssgerente = resultado.getString("NSSgerente");

                System.out.println("Número: " + numdep);
                System.out.println("Nombre: " + nombredep);
                System.out.println("Cantidad de empleados: " + numempdep);
                System.out.println("NSS del gerente: " + nssgerente);
                System.out.println("-----------------------------");
            }

            resultado.close();
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
