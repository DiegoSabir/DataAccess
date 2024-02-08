package com.mycompany.hibernatemaven;

import java.util.Date;
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author a22jesusbm
 */
@Entity
@Table(name = "autores",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"DniAutor"})})


public class autor1 {
    

    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "DniAutor", nullable = false, unique = true)
    private String DniAutor;

    @Column(name = "Nombre", nullable = true)
    private String Nombre;

    @Column(name = "Nacionalidad", nullable = true)
    private String Nacionalidad;
    
    @ManyToMany(cascade = CascadeType.ALL)
@JoinTable(
        name = "libros_autores",
        joinColumns = @JoinColumn(name = "DniAutor"),
        inverseJoinColumns = @JoinColumn(name = "IdLibro"))
    
    private Set<libro1> libros = new HashSet<libro1>();

    public String getDniAutor() {
        return DniAutor;
    }

    public void setDniAutor(String DniAutor) {
        this.DniAutor = DniAutor;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getNacionalidad() {
        return Nacionalidad;
    }

    public void setNacionalidad(String Nacionalidad) {
        this.Nacionalidad = Nacionalidad;
    }
    

}


