package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="Libros", uniqueConstraints={@UniqueConstraint(columnNames={"IdLibro"})})
public class Libro {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IdLibro", nullable=false, unique=true)
    private int idLibro;

    @Column(name="Titulo", length=30, nullable=true)
    private String titulo;

    @Column(name="Precio", nullable=true)
    private Float precio;

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
}

