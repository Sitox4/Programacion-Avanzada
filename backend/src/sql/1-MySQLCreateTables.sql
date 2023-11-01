DROP TABLE Compra;
DROP TABLE Sesion;
DROP TABLE Sala;
DROP TABLE Pelicula;
DROP TABLE User;

CREATE TABLE User (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      userName VARCHAR(60) COLLATE latin1_bin NOT NULL,
                      password VARCHAR(60) NOT NULL,
                      firstName VARCHAR(60) NOT NULL,
                      lastName VARCHAR(60) NOT NULL,
                      email VARCHAR(60) NOT NULL,
                      role TINYINT NOT NULL,
                      CONSTRAINT UserPK PRIMARY KEY (id),
                      CONSTRAINT UserNameUniqueKey UNIQUE (userName)
) ENGINE = InnoDB;

CREATE INDEX UserIndexByUserName ON User (userName);

CREATE TABLE Pelicula(
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         titulo VARCHAR(60) COLLATE latin1_bin NOT NULL,
                         resumen VARCHAR(1000) COLLATE latin1_bin NOT NULL,
                         duracion SMALLINT NOT NULL,
                         CONSTRAINT PeliculaPK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE Sala(
                     id BIGINT NOT NULL AUTO_INCREMENT,
                     nombre VARCHAR(60) COLLATE latin1_bin NOT NULL,
                     capacidad SMALLINT NOT NULL,
                     CONSTRAINT SalaPK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE Sesion(
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       fecha DATETIME NOT NULL,
                       precio FLOAT NOT NULL,
                       peliculaId BIGINT NOT NULL,
                       salaId BIGINT NOT NULL,
                       ticketsLibres SMALLINT NOT NULL,
                       version BIGINT NOT NULL,
                       CONSTRAINT SesionPK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE Compra(
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       cantidad SMALLINT NOT NULL,
                       tarjeta VARCHAR(32) COLLATE latin1_bin NOT NULL,
                       userId BIGINT NOT NULL,
                       sesionId BIGINT NOT NULL,
                       fecha DATETIME NOT NULL,
                       entregada BIT NOT NULL,
                       CONSTRAINT CompraPK PRIMARY KEY (id)
) ENGINE = InnoDB;