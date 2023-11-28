package Tarea.model;

public class Empleado {
	private String nIF;
	private String nombre;
	private String apellido;
	private int salario;
	
	public Empleado(String nIF, String nombre, String apellido, int salario) {
		this.nIF = nIF;
		this.nombre = nombre;
		this.apellido = apellido;
		this.salario = salario;
	public String getnIF() {
		return nIF;
	}
	public void setnIF(String nIF) {
		this.nIF = nIF;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(int salario) {
		this.salario = salario;
	}
	
}
