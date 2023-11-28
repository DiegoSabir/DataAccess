package Tarea.model;

public class Empleado {
    public String nIF;
    public String nombre;
    public String apel1ido;
    public String apel1ido2;
    public String sexo;
    public String direccion;
    public int nss;
    public int salario;

    public Empleado(int nss, String nombre, String apel1ido, String apel1ido2, String sexo, String direccion, int salario, String nIF) {
        this.nss = nss;
        this.nIF = nIF;
        this.nombre = nombre;
        this.nombre = nombre;
        this.apel1ido = apel1ido;
        this.apel1ido2 = apel1ido2;
        this.sexo = sexo;
        this.direccion = direccion;
        this.salario = salario;
    }

}
