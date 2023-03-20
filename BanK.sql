CREATE TABLE EstadoCliente
(
    ID   int         NOT NULL AUTO_INCREMENT,
    Tipo varchar(20) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE Cliente
(
    ID            int      NOT NULL AUTO_INCREMENT,
    FechaInicio   datetime NOT NULL,
    EstadoCliente int      NOT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT EstadoCliente FOREIGN KEY (EstadoCliente) REFERENCES EstadoCliente (ID)
);

CREATE TABLE EstadoCuenta
(
    ID   int         NOT NULL AUTO_INCREMENT,
    Tipo varchar(20) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE EntidadBancaria
(
    ID     int         NOT NULL,
    Nombre varchar(45) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE CuentaBanco
(
    ID              int         NOT NULL AUTO_INCREMENT,
    Moneda          varchar(10) NOT NULL,
    IbanCuenta      varchar(24) NOT NULL UNIQUE,
    Saldo           double      NOT NULL,
    Swift           varchar(11) NOT NULL,
    Pais            varchar(45) NOT NULL,
    FechaApertura   datetime    NOT NULL,
    FechaCierre     datetime    NOT NULL,
    Titular         int         NOT NULL,
    EntidadBancaria int         NOT NULL,
    EstadoCuenta    int         NOT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT Cliente FOREIGN KEY (Titular) REFERENCES Cliente (ID),
    CONSTRAINT Entidad FOREIGN KEY (EntidadBancaria) REFERENCES EntidadBancaria (ID),
    CONSTRAINT EstadoCuenta FOREIGN KEY (EstadoCuenta) REFERENCES EstadoCuenta (ID)
);

CREATE TABLE TipoDireccion
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
    Cliente             int         NOT NULL,
    TipoDireccion       int         NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (Cliente) REFERENCES Cliente (ID),
    FOREIGN KEY (TipoDireccion) REFERENCES TipoDireccion (ID)
);

CREATE TABLE Transaccion
(
    ID               int      NOT NULL AUTO_INCREMENT,
    FechaInstruccion datetime NOT NULL,
    FechaEjecucion   datetime NOT NULL,
    Cantidad         double   NOT NULL,
    CuentaOrigen     int      NOT NULL,
    CuentaDestino    int      NOT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT CuentaOrigen FOREIGN KEY (CuentaOrigen) REFERENCES CuentaBanco (ID),
    CONSTRAINT CuentaDestino FOREIGN KEY (CuentaDestino) REFERENCES CuentaBanco (ID)
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
    ID               int         NOT NULL,
    NOMBRE           varchar(45) NOT NULL,
    APELLIDO1        varchar(45) NOT NULL,
    APELLIDO2        varchar(45),
    FECHA_NACIMIENTO datetime    NOT NULL,
    DNI              varchar(9)  NOT NULL UNIQUE,
    PRIMARY KEY (ID),
    FOREIGN KEY (ID) REFERENCES Cliente (ID)
);

CREATE TABLE Empresa
(
    ID             int         NOT NULL,
    NOMBRE         varchar(45) NOT NULL,
    FECHA_CREACION datetime    NOT NULL,
    CIF            varchar(9)  NOT NULL UNIQUE,
    PRIMARY KEY (ID),
    FOREIGN KEY (ID) REFERENCES Cliente (ID)
);

CREATE TABLE TipoUsuario
(
    ID   int NOT NULL AUTO_INCREMENT,
    Tipo varchar(20),
    PRIMARY KEY (ID)
);

CREATE TABLE Usuario
(
    ID          int         NOT NULL,
    NIF         varchar(9)  NOT NULL,
    Contrasena  varchar(45) NOT NULL,
    TipoUsuario int         NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (ID) REFERENCES Cliente (ID),
    FOREIGN KEY (TipoUsuario) REFERENCES TipoUsuario (ID)
);

CREATE TABLE Conversacion
(
    ID        int     NOT NULL,
    Emisor    int     NOT NULL,
    Receptor  int     NOT NULL,
    Terminada tinyint NOT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT Emisor FOREIGN KEY (Emisor) REFERENCES Usuario (ID),
    CONSTRAINT Receptor FOREIGN KEY (Receptor) REFERENCES Usuario (ID)
);

CREATE TABLE Mensaje
(
    ID           int          NOT NULL,
    Conversacion int          NOT NULL,
    Contenido    varchar(500) NOT NULL,
    Fecha        datetime     NOT NULL,
    Emisor       int          NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (Conversacion) REFERENCES Conversacion (ID),
    FOREIGN KEY (Emisor) REFERENCES Usuario (ID)
);

