public class Main {
    public static void main(String[] args) {

        Trabajador empleado1 = new Trabajador("Juan", 30, Trabajador.CAT_EMPLEADO, Trabajador.ANT_MADURO);
        System.out.println("Nombre: " + empleado1.getNombre());
        System.out.println("Edad: " + empleado1.getEdad());
        System.out.println("Categoría: " + empleado1.getCategoria());
        System.out.println("Antigüedad: " + empleado1.getAntiguedad());
        System.out.println("Sueldo: " + empleado1.calcularSueldo());

    }
}