import java.time.LocalDateTime;
import java.util.Scanner;

public class Controlador {
    static Scanner sc = new Scanner(System.in);

    public static Empleado crearEmpleado() {
        String dni;

        do {
            System.out.print("Introduzca el DNI: ");
            dni = sc.nextLine();
        }
        while (!validacionDNI(dni));

        int nss;
        do {
            System.out.print("Introduzca el numero de la seguridad social: ");
            while (!sc.hasNextInt()) {
                System.out.println("Error: Introduzca un nss correcto");
                sc.next();
            }
            nss = sc.nextInt();
        }
        while (!validacionNSS(nss));

        sc.nextLine();
        System.out.print("Introduzca el nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Introduzca el primer apellido: ");
        String apellido1 = sc.nextLine();
        System.out.print("Introduzca el segundo apellido: ");
        String apellido2 = sc.nextLine();
        String sexo;

        do {
            System.out.print("Introduzca el genero: ");
            sexo = sc.nextLine();
        }
        while (!validacionSexo(sexo));

        System.out.print("Introduzca la direccion: ");
        String direccion = sc.nextLine();

        System.out.print("Introduzca el salario: ");
        while (!sc.hasNextInt()) {
            System.out.println("ERROR: Introduzca un salario correcto");
            sc.next();
        }
        int salario = sc.nextInt();

        sc.nextLine();

        return new Empleado(dni, nombre, apellido1, apellido2, sexo, direccion, salario, nss);
    }

    public static Departamento crearDepartamento() {
        System.out.print("Introduzca el numero de departamento: ");
        int numero = sc.nextInt();

        sc.nextLine();

        System.out.print("Introduzca el nombre de departamento: ");
        String nombre = sc.nextLine();

        System.out.print("Introduzca la cantidad de empleados: ");
        int cantidadEmpleados = sc.nextInt();

        sc.nextLine();

        System.out.print("Introduzca el NSS del gerente: ");
        String nssGerente = sc.nextLine();

        LocalDateTime fechaInicioGerente = LocalDateTime.now();

        /* EN CASO DE SI HUBIERA SIDO POR TECLADO
        System.out.println("Introduzca la fecha de inicio del gerente (año-mes-día hora:minuto:segundo): ");
        System.out.println("Por ejemplo, 2023-12-31T23:59:59");
        String fechaInicioGerenteInput = sc.nextLine();

        LocalDateTime fechaInicioGerente = null;

        try {
            // Parsear la cadena de fecha proporcionada por el usuario a LocalDateTime
            fechaInicioGerente = LocalDateTime.parse(fechaInicioGerenteInput);
        }
        catch (DateTimeParseException e) {
            System.out.println("Formato de fecha incorrecto. Se utilizará la fecha y hora actual.");
            fechaInicioGerente = LocalDateTime.now();
        }
        */

        return new Departamento(numero, nombre, cantidadEmpleados, nssGerente, fechaInicioGerente);
    }

    public static boolean validacionDNI(String dni) {
        final int LONGITUD = 9;
        final String LETRAS = "TRWAGMYFPDXBNJZSQVHLCKE";

        if (dni.length() != LONGITUD) {
            return false;
        }

        char letraDNI = Character.toUpperCase(dni.charAt(8));
        String numero = dni.substring(0, 8);

        try {
            int numeroDNI = Integer.parseInt(numero);
            char letraCalculada = LETRAS.charAt(numeroDNI % LETRAS.length());
            return letraDNI == letraCalculada ;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validacionNSS(int nss) {
        String nssString = String.valueOf(nss);

        return nssString.length() == 5;
    }

    public static boolean validacionSexo(String sexo) {
        return sexo.equals("M") || sexo.equals("F");
    }
}
