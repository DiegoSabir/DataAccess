DROP DATABASE IF EXISTS LibrosAutoresHibernate;
CREATE DATABASE LibrosAutoresHibernate
	CHARACTER SET	utf32
	COLLATE			utf32_spanish2_ci;
USE LibrosAutoresHibernate;


CREATE TABLE Libros (
	IdLibro INT NOT NULL AUTO_INCREMENT,
    Titulo CHAR ( 30 ) NOT NULL,
    Precio FLOAT NOT NULL,
	Autor CHAR(9) NOT NULL,
    
	PRIMARY KEY ( IdLibro ),
    FOREIGN KEY (Autor) REFERENCES Autores (DniAutor) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;


CREATE TABLE Autores (
	DniAutor CHAR ( 9 ) NOT NULL,
    Nombre CHAR ( 30 ) NOT NULL,
    Nacionalidad CHAR ( 20 ) NOT NULL,

	PRIMARY KEY ( DniAutor )
) ENGINE=InnoDB;


CREATE TABLE Telefonos (
	Dni CHAR ( 9 ) NOT NULL,
    NumeroTf INT DEFAULT NULL,

	PRIMARY KEY ( Dni, NumeroTf ),
    FOREIGN KEY ( Dni ) REFERENCES Autores ( DniAutor ) ON DELETE CASCADE ON UPDATE cascade
) ENGINE=InnoDB;

CREATE TABLE Libros_Autores (
    IdLibroAutor INT AUTO_INCREMENT PRIMARY KEY,
    IdLibro INT,
    DniAutor VARCHAR(20),
    FOREIGN KEY (IdLibro) REFERENCES Libros(IdLibro),
    FOREIGN KEY (DniAutor) REFERENCES Autores(DniAutor)
);
