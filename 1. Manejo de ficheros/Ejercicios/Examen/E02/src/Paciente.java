import java.time.LocalDate;

public class Paciente {
    private String dni;
    private String nombre;
    private String apellidos;
    private String direccion;
    private LocalDate fechaUltimaVisita;
    private Boolean alergia;
    private char tipo;

    public Paciente(String dni, String nombre, String apellidos, String direccion, LocalDate fechaUltimaVisita, Boolean alergia, char tipo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.fechaUltimaVisita = fechaUltimaVisita;
        this.alergia = alergia;
        this.tipo = tipo;
    }

    public Paciente(){

    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (dni.matches("\\d{8}[a-zA-Z]")) {
            this.dni = dni;
        }
        else {
            System.out.println("Formato de Dni Incorrecto");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre.length() <= 15) {
            this.nombre = nombre;
        }
        else {
            System.out.println("Nombre superior a 15 caracteres");
        }
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaUltimaVisita() {
        return fechaUltimaVisita;
    }

    public void setFechaUltimaVisita(LocalDate fechaUltimaVisita) {
        this.fechaUltimaVisita = fechaUltimaVisita;
    }

    public Boolean getAlergia() {
        return alergia;
    }

    public void setAlergia(Boolean alergia) {
        if (alergia != null) {
            this.alergia = alergia;
        }
        else {
            System.out.println("El valor de alergia debe ser verdadero o falso");
        }
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion='" + direccion + '\'' +
                ", fechaUltimaVisita=" + fechaUltimaVisita +
                ", alergia=" + alergia +
                ", tipo=" + tipo +
                '}';
    }
}
