CREATE TABLE Cliente (
  ID int NOT NULL,
  NumeroIdentificacion varchar(9) NOT NULL UNIQUE,
  Estado enum('Activo','Inactivo','Bloqueado','Cerrado') NOT NULL,
  FechaInicio datetime NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE CuentaBanco (
  Moneda varchar(10) NOT NULL,
  IbanCuenta varchar(24) NOT NULL UNIQUE,
  Swift varchar(11) NOT NULL,
  Pais varchar(45) NOT NULL,
  FechaApertura datetime NOT NULL,
  FechaCierre datetime NOT NULL,
  EstadoCuenta enum('Activa','Bloqueada','Cerrada') NOT NULL,
  PRIMARY KEY (IbanCuenta)
);

CREATE TABLE Direccion (
  ID int NOT NULL,
  Tipo enum('Casa','Oficina','CodigoPostal') NOT NULL,
  Calle varchar(45) NOT NULL,
  Numero int NOT NULL,
  PlantaPuertaOficina varchar(45) DEFAULT NULL,
  Ciudad varchar(45) NOT NULL,
  Region varchar(45) NOT NULL,
  CodigoPostal int NOT NULL,
  Pais varchar(45) NOT NULL,
  Valida tinyint DEFAULT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (ID) REFERENCES Cliente (ID)
);

CREATE TABLE EntidadBancaria (
  Nombre varchar(45) NOT NULL,
  PRIMARY KEY (Nombre)
);

CREATE TABLE Transaccion (
  ID int NOT NULL,
  FechaInstruccion datetime NOT NULL,
  FechaEjecucion datetime NOT NULL,
  Cantidad int NOT NULL,
  CuentaOrigen varchar(24) NOT NULL,
  CuentaDestino varchar(24) NOT NULL,
  CONSTRAINT CuentaDestino FOREIGN KEY (CuentaDestino) REFERENCES CuentaBanco (IbanCuenta),
  CONSTRAINT CuentaOrigen FOREIGN KEY (CuentaOrigen) REFERENCES CuentaBanco (IbanCuenta),
  PRIMARY KEY (ID)
);

CREATE TABLE Divisa (
  ID varchar(3) NOT NULL,
  NOMBRE varchar(40) NOT NULL,
  EQUIVALENCIA double NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE Persona (
  NOMBRE varchar(45) NOT NULL,
  APELLIDO1 varchar(45) NOT NULL,
  APELLIDO2 varchar(45) NOT NULL,
  FECHA_NACIMIENTO datetime NOT NULL,
  DNI varchar(9) NOT NULL,
  CONSTRAINT DNI FOREIGN KEY (DNI) REFERENCES Cliente (NumeroIdentificacion)
);

CREATE TABLE Empresa (
  NOMBRE varchar(45) NOT NULL,
  FECHA_CREACION datetime NOT NULL,
  NIF varchar(9) NOT NULL,
  CONSTRAINT NIF FOREIGN KEY (NIF) REFERENCES Cliente (NumeroIdentificacion)
);
