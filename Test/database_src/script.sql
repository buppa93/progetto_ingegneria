CREATE DATABASE IF NOT EXISTS test_ingegneria;
USE test_ingegneria;

CREATE TABLE IF NOT EXISTS agenzie (
  numero VARCHAR(5) NOT NULL PRIMARY KEY,
  nome VARCHAR(30) NOT NULL,
  indirizzo VARCHAR(45) NOT NULL
  );

CREATE TABLE IF NOT EXISTS user (
  id_user VARCHAR(5) NOT NULL PRIMARY KEY,
  nome VARCHAR(40) NOT NULL,
  cognome VARCHAR(40) NOT NULL,
  num_teleono VARCHAR(10) NOT NULL,
  password TEXT NOT NULL,
  tipo VARCHAR(3) NOT NULL
  );

CREATE TABLE IF NOT EXISTS fascia (
  nome CHAR(1) NOT NULL PRIMARY KEY,
  n_porte INT NOT NULL,
  n_posti INT NOT NULL,
  tipo_vettura VARCHAR(20) NOT NULL
  );

CREATE TABLE IF NOT EXISTS tipo_contratto (
  id_tipo INT NOT NULL PRIMARY KEY,
  tipo_noleggio VARCHAR(20) NOT NULL,
  tipo_chilometraggio VARCHAR(20) NOT NULL
  );

CREATE TABLE IF NOT EXISTS auto (
  targa VARCHAR(7) NOT NULL PRIMARY KEY,
  modello VARCHAR(25) NOT NULL,
  marca VARCHAR(25) NOT NULL,
  km INT UNSIGNED NOT NULL,
  id_cliente VARCHAR(5) NULL,
  id_contratto VARCHAR(5) NULL,
  id_agenzia VARCHAR(5) NOT NULL,
  fascia VARCHAR(1) NOT NULL,
  disponibilita INT UNSIGNED NOT NULL,
  FOREIGN KEY (id_cliente)
    REFERENCES user(id_user)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  FOREIGN KEY (id_agenzia)
    REFERENCES agenzie(numero)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  FOREIGN KEY (fascia)
    REFERENCES fascia(nome)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS contratto 
(
  	numero_ordine VARCHAR(5) NOT NULL PRIMARY KEY,
  	id_agenzia VARCHAR(5) NOT NULL,
  	id_cliente VARCHAR(5) NOT NULL,
  	data_inizio DATE NOT NULL,
  	data_fine DATE NOT NULL,
  	agenzia_rest VARCHAR(5) NOT NULL,
  	tipo INT NOT NULL,
  	FOREIGN KEY (id_agenzia) REFERENCES agenzie(numero)
    	ON DELETE NO ACTION
    	ON UPDATE NO ACTION,
    FOREIGN KEY (agenzia_rest) REFERENCES agenzie(numero)
    	ON DELETE NO ACTION
    	ON UPDATE NO ACTION,
    FOREIGN KEY (id_cliente) REFERENCES user(id_user)
    	ON DELETE NO ACTION
    	ON UPDATE NO ACTION,
    FOREIGN KEY (tipo) REFERENCES tipo_contratto(id_tipo)
    	ON DELETE NO ACTION
    	ON UPDATE NO ACTION
    );