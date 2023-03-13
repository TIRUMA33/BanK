CREATE TABLE Cliente
(
    ID                   int        NOT NULL AUTO_INCREMENT,
    NumeroIdentificacion varchar(9) NOT NULL UNIQUE,
    FechaInicio          datetime   NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE EstadoCliente
(
    ID   int NOT NULL AUTO_INCREMENT,
    Tipo varchar(20),
    PRIMARY KEY (ID)
);

CREATE TABLE CuentaBanco
(
    ID            int         NOT NULL AUTO_INCREMENT,
    Moneda        varchar(10) NOT NULL,
    IbanCuenta    varchar(24) NOT NULL UNIQUE,
    Swift         varchar(11) NOT NULL,
    Pais          varchar(45) NOT NULL,
    FechaApertura datetime    NOT NULL,
    FechaCierre   datetime    NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE EstadoCuenta
(
    ID   int NOT NULL AUTO_INCREMENT,
    Tipo varchar(20),
    PRIMARY KEY (ID)
);

CREATE TABLE Direccion
(
    ID                  int         NOT NULL AUTO_INCREMENT,
    Calle               varchar(45) NOT NULL,
    Numero              int         NOT NULL,
    PlantaPuertaOficina varchar(45) DEFAULT NULL,
    Ciudad              varchar(45) NOT NULL,
    Region              varchar(45) NOT NULL,
    CodigoPostal        int         NOT NULL,
    Pais                varchar(45) NOT NULL,
    Valida              tinyint     DEFAULT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (ID) REFERENCES Cliente (ID)
);

CREATE TABLE TipoDireccion
(
    ID   int NOT NULL AUTO_INCREMENT,
    Tipo varchar(20),
    PRIMARY KEY (ID)
);

CREATE TABLE EntidadBancaria
(
    Nombre varchar(45) NOT NULL,
    PRIMARY KEY (Nombre)
);

CREATE TABLE Transaccion
(
    ID               int         NOT NULL AUTO_INCREMENT,
    FechaInstruccion datetime    NOT NULL,
    FechaEjecucion   datetime    NOT NULL,
    Cantidad         int         NOT NULL,
    CuentaOrigen     varchar(24) NOT NULL,
    CuentaDestino    varchar(24) NOT NULL,
    CONSTRAINT CuentaDestino FOREIGN KEY (CuentaDestino) REFERENCES CuentaBanco (IbanCuenta),
    CONSTRAINT CuentaOrigen FOREIGN KEY (CuentaOrigen) REFERENCES CuentaBanco (IbanCuenta),
    PRIMARY KEY (ID)
);

CREATE TABLE Divisa
(
    ID           varchar(3)  NOT NULL,
    NOMBRE       varchar(40) NOT NULL,
    EQUIVALENCIA double      NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE Persona
(
    NOMBRE           varchar(45) NOT NULL,
    APELLIDO1        varchar(45) NOT NULL,
    APELLIDO2        varchar(45) NOT NULL,
    FECHA_NACIMIENTO datetime    NOT NULL,
    DNI              varchar(9)  NOT NULL,
    CONSTRAINT DNI FOREIGN KEY (DNI) REFERENCES Cliente (NumeroIdentificacion)
);

CREATE TABLE Empresa
(
    NOMBRE         varchar(45) NOT NULL,
    FECHA_CREACION datetime    NOT NULL,
    NIF            varchar(9)  NOT NULL,
    CONSTRAINT NIF FOREIGN KEY (NIF) REFERENCES Cliente (NumeroIdentificacion)
);

CREATE TABLE Usuario
(
    NIF        varchar(9)  NOT NULL,
    Contrasena varchar(45) NOT NULL,
    CONSTRAINT Usuario FOREIGN KEY (NIF) REFERENCES Cliente (NumeroIdentificacion)
);

CREATE TABLE Asistente
(
    ID      int          NOT NULL AUTO_INCREMENT,
    NIF     varchar(9)   NOT NULL,
    Mensaje varchar(500) NOT NULL,
    Fecha   datetime     NOT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT Cliente FOREIGN KEY (NIF) REFERENCES Cliente (NumeroIdentificacion)
);
