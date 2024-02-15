package model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "autores",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"DniAutor"})})

public class Autor {
    @Id
    @Column(name = "DniAutor", nullable = false, unique = true, length = 9)
    private String dniautor;

    @Column(name = "Nombre", nullable = false, length = 25)
    private String nombre;

    @Column(name = "Nacionalidad", nullable = false, length = 25)
    private String nacionalidad;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Libros_Autores",
            joinColumns = @JoinColumn(name = "DniAutor"),
            inverseJoinColumns = @JoinColumn(name = "IdLibro"))
    private Set<Libro> libros = new HashSet<Libro>();

    public String getDniautor() {
        return dniautor;
    }

    public void setDniautor(String dniautor) {
        this.dniautor = dniautor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Set<Libro> getLibros() {
        return libros;
    }

    public void setLibros(Set<Libro> libros) {
        this.libros = libros;
    }
}
