package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "autores",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"DniAutor"})})

public class Autor implements Serializable {
    @Id
    @Column(name = "DniAutor", nullable = false, unique = true, length = 9)
    public String dniautor;

    @Column(name = "Nombre", nullable = false, length = 25)
    private String nombre;

    @Column(name = "Nacionalidad", nullable = false, length = 25)
    private String nacionalidad;

    public Autor(String dniautor, String nombre, String nacionalidad) {
        this.dniautor = dniautor;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public Autor() {

    }

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
}
