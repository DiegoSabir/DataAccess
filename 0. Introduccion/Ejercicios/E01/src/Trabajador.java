public class Trabajador {
    String nombre;
    int edad;
    int categoria;
    int antiguedad;

    public static final int CAT_EMPLEADO = 0;
    public static final int CAT_ENCARGADO = 1;
    public static final int CAT_DIRECTIVO = 2;
    public static final int ANT_NOVATO = 0;
    public static final int ANT_MADURO = 1;
    public static final int ANT_EXPERTO = 2;

    public Trabajador(String nombre, int edad, int categoria, int antiguedad) {
        if (categoria < CAT_EMPLEADO || categoria > CAT_DIRECTIVO) {
            throw new CategoriaExcepcion("ERROR: Categoria incorrecta");
        }
        if (antiguedad < ANT_NOVATO || antiguedad > ANT_EXPERTO) {
            throw new AntiguedadExcepcion("ERROR: Antigüedad incorrecta");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public double calcularSueldo(){
        double sueldo = 0;
        switch (categoria) {
            case 0:
                if (antiguedad == ANT_NOVATO){
                    sueldo = 607 * 1.15 + 150;
                }
                else if (antiguedad == ANT_MADURO) {
                    sueldo = 607 * 1.15 + 300;
                }
                else if (antiguedad == ANT_EXPERTO){
                    sueldo = 607 * 1.15 + 600;
                }
                break;
            case 1:
                if (antiguedad == ANT_NOVATO){
                    sueldo = 607 * 1.35 + 150;
                }
                else if (antiguedad == ANT_MADURO) {
                    sueldo = 607 * 1.35 + 300;
                }
                else if (antiguedad == ANT_EXPERTO){
                    sueldo = 607 * 1.35 + 600;
                }
                break;
            case 2:
                if (antiguedad == ANT_NOVATO){
                    sueldo = 607 * 1.60 + 150;
                }
                else if (antiguedad == ANT_MADURO) {
                    sueldo = 607 * 1.60 + 300;
                }
                else if (antiguedad == ANT_EXPERTO){
                    sueldo = 607 * 1.60 + 600;
                }
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }

        return sueldo;
    }
}
