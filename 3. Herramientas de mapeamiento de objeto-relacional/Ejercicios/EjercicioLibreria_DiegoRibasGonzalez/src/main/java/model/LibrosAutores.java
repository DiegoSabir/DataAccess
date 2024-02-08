package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="libros_autores",
        uniqueConstraints={@UniqueConstraint(columnNames={"DniAutor"})})
public class LibrosAutores {
    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="DniAutor", nullable=false, unique=true)
    private String DniAutor;
    @Column(name="IdLibro", nullable=false, unique=true)
    private String IdLibro;
}
