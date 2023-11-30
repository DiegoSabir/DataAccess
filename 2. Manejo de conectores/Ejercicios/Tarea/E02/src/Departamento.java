import java.time.LocalDateTime;

public class Departamento {
    private int numero;
    private String nombre;
    private int cantidadEmpleados;
    private String nssGerente;
    private LocalDateTime fechaInicioGerente;

    public Departamento(int numero, String nombre, int cantidadEmpleados, String nssGerente, LocalDateTime fechaInicioGerente) {
        this.numero = numero;
        this.nombre = nombre;
        this.cantidadEmpleados = cantidadEmpleados;
        this.nssGerente = nssGerente;
        this.fechaInicioGerente = fechaInicioGerente;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadEmpleados() {
        return cantidadEmpleados;
    }

    public void setCantidadEmpleados(int cantidadEmpleados) {
        this.cantidadEmpleados = cantidadEmpleados;
    }

    public String getNssGerente() {
        return nssGerente;
    }

    public void setNssGerente(String nssGerente) {
        this.nssGerente = nssGerente;
    }

    public LocalDateTime getFechaInicioGerente() {
        return fechaInicioGerente;
    }

    public void setFechaInicioGerente(LocalDateTime fechaInicioGerente) {
        this.fechaInicioGerente = fechaInicioGerente;
    }
}
