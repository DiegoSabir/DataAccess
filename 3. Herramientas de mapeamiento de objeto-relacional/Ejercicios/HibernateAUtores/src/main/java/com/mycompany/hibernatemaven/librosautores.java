package com.mycompany.hibernatemaven;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name="libros_autores", 
	   uniqueConstraints={@UniqueConstraint(columnNames={"DniAutor"})})

public class librosautores {
    
    @Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DniAutor", nullable=false, unique=true)
	private String DniAutor;
    @Column(name="IdLibro", nullable=false, unique=true)
	private String IdLibro;
    
    
}
