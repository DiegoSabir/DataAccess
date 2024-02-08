package model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import model.Autor;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="libros",
        uniqueConstraints={@UniqueConstraint(columnNames={"IdLibro"})})

public class Libro{
    @ManyToMany(cascade = {CascadeType.ALL},mappedBy = "libros")
    private Set<Autor> autores = new HashSet<Autor>();

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IdLibro", nullable=false, unique=true, length=11)
    private int IdLibro;

    @Column(name="Titulo", length=20, nullable=true)
    private String Titulo;

    @Column(name="Precio", length=20, nullable=true)
    private double Precio;

    public int getIdLibro() {
        return IdLibro;
    }

    public void setIdLibro(int IdLibro) {
        this.IdLibro = IdLibro;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }
}
