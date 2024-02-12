DROP DATABASE IF EXISTS LibrosAutoresHibernate;
CREATE DATABASE LibrosAutoresHibernate CHARACTER SET utf32 COLLATE utf32_spanish2_ci;
USE LibrosAutoresHibernate;

CREATE TABLE Autores (
    DniAutor CHAR(9) NOT NULL,
    Nombre CHAR(30) NOT NULL,
    Nacionalidad CHAR(20) NOT NULL,
    PRIMARY KEY (DniAutor)
) ENGINE=InnoDB;

CREATE TABLE Libros (
    IdLibro INT NOT NULL AUTO_INCREMENT,
    Titulo CHAR(30) NOT NULL,
    Precio DOUBLE NOT NULL,
    Autor CHAR(9) NOT NULL,
    PRIMARY KEY (IdLibro),
    FOREIGN KEY (Autor) REFERENCES Autores (DniAutor) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE Telefonos (
    Dni CHAR(9) NOT NULL,
    NumeroTf INT NOT NULL,
    PRIMARY KEY (Dni, NumeroTf),
    FOREIGN KEY (Dni) REFERENCES Autores (DniAutor) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;


CREATE TABLE Libros_Autores (
    IdLibroAutor INT AUTO_INCREMENT PRIMARY KEY,
    IdLibro INT,
    DniAutor VARCHAR(20),
    FOREIGN KEY (IdLibro) REFERENCES Libros(IdLibro),
    FOREIGN KEY (DniAutor) REFERENCES Autores(DniAutor)
);

INSERT INTO Autores (DniAutor, Nombre, Nacionalidad) VALUES
('96904033L', 'Natsu Hyuga', 'Japones'),
('25019104A', 'Negi Haruba', 'Japones'),
('71322682G', 'Reiji Miyajima', 'Japones'),
('19433518J', 'Aka Akasaka', 'Japones'),
('14385448Y', 'Koyoharu Gotouge', 'Japones'),
('45264078D', 'Hajime Isayama', 'Japones'),
('88093003J', 'Hajime Kamoshida', 'Japones'),
('21393035E', 'Ichiei Ishibumi', 'Japones'),
('69569159R', 'Kanehito Yamada', 'Japones');

INSERT INTO Libros (Titulo, Precio, Autor) VALUES
('The Apothecary Diaries', 19.99, '96904033L'),
('The Quintessential Quintuplets', 15.50, '25019104A'),
('Rent a Girlfriend', 12.99, '71322682G'),
('My Favourite Idol', 18.75, '19433518J'),
('Kaguya Sama Love Is War', 17.25, '19433518J'),
('Demon Slayer', 14.99, '14385448Y'),
('Attack On Titan', 16.50, '45264078D'),
('Rascal Does Not Dream', 10.25, '88093003J'),
('High School DxD', 11.99, '21393035E'),
('Frieren Beyond Journey End', 13.75, '69569159R');
