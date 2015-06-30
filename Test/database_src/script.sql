-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema test_ingegneria
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema test_ingegneria
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS test_ingegneria DEFAULT CHARACTER SET latin1 ;
USE `test_ingegneria`;

-- -----------------------------------------------------
-- Table `test_ingegneria`.`agenzie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS agenzie (
  `numero` VARCHAR(5) NOT NULL COMMENT '',
  `nome` VARCHAR(30) NOT NULL COMMENT '',
  indirizzo VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`numero`)  COMMENT '');

-- -----------------------------------------------------
-- Table `test_ingegneria`.`utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS utente (
  id_utente VARCHAR(5),
  nome VARCHAR(40) NOT NULL COMMENT '',
  cognome VARCHAR(40) NOT NULL COMMENT '',
  `num_telefono` VARCHAR(10) NOT NULL COMMENT '',
  `password` TEXT NOT NULL COMMENT '',
  `tipo` VARCHAR(3) NOT NULL COMMENT '',
  PRIMARY KEY (`id_utente`)  COMMENT '');

-- -----------------------------------------------------
-- Table `test_ingegneria`.`tipo_contratto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tipo_contratto(
  `id_tipo` INT(11) NOT NULL COMMENT '',
  `tipo_noleggio` VARCHAR(20) NOT NULL COMMENT '',
  `tipo_chilometraggio` VARCHAR(20) NOT NULL COMMENT '',
  PRIMARY KEY (`id_tipo`)  COMMENT '');
  
-- -----------------------------------------------------
-- Table `test_ingegneria`.`fascia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS fascia (
  `nome` CHAR(1) NOT NULL COMMENT '',
  `n_porte` INT(11) NOT NULL COMMENT '',
  `n_posti` INT(11) NOT NULL COMMENT '',
  `tipo_vettura` VARCHAR(20) NOT NULL COMMENT '',
  PRIMARY KEY (`nome`)  COMMENT '');

-- -----------------------------------------------------
-- Table `test_ingegneria`.`contratto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS contratto (
  `numero_ordine` VARCHAR(5) NOT NULL COMMENT '',
  `id_agenzia` VARCHAR(5) NOT NULL COMMENT '',
  `id_cliente` VARCHAR(5) NOT NULL COMMENT '',
  `data_inizio` DATE NOT NULL COMMENT '',
  `data_fine` DATE NOT NULL COMMENT '',
  `agenzia_rest` VARCHAR(5) NOT NULL COMMENT '',
  `tipo` INT(11) NOT NULL COMMENT '',
  `kmmax` INT(6) NOT NULL COMMENT '',
  PRIMARY KEY (`numero_ordine`)  COMMENT '',
  INDEX `id_agenzia` (`id_agenzia` ASC)  COMMENT '',
  INDEX `agenzia_rest` (`agenzia_rest` ASC)  COMMENT '',
  INDEX `id_cliente` (`id_cliente` ASC)  COMMENT '',
  INDEX `tipo` (`tipo` ASC)  COMMENT '',
  CONSTRAINT `contratto_ibfk_1`
    FOREIGN KEY (`id_agenzia`)
    REFERENCES `test_ingegneria`.`agenzie` (`numero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `contratto_ibfk_2`
    FOREIGN KEY (`agenzia_rest`)
    REFERENCES `test_ingegneria`.`agenzie` (`numero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `contratto_ibfk_3`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `test_ingegneria`.`utente` (`id_utente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `contratto_ibfk_4`
    FOREIGN KEY (`tipo`)
    REFERENCES `test_ingegneria`.`tipo_contratto` (`id_tipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `test_ingegneria`.`auto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS auto(
  `targa` VARCHAR(7) NOT NULL COMMENT '',
  `modello` VARCHAR(25) NOT NULL COMMENT '',
  `marca` VARCHAR(25) NOT NULL COMMENT '',
  `km` INT(10) UNSIGNED NOT NULL COMMENT '',
  `id_cliente` VARCHAR(5) NULL DEFAULT NULL COMMENT '',
  `id_contratto` VARCHAR(5) NULL DEFAULT NULL COMMENT '',
  `id_agenzia` VARCHAR(5) NOT NULL COMMENT '',
  `fascia` VARCHAR(1) NOT NULL COMMENT '',
  `disponibilita` INT(10) UNSIGNED NOT NULL COMMENT '',
  PRIMARY KEY (`targa`)  COMMENT '',
 /* INDEX `id_cliente` (`id_cliente` ASC)  COMMENT '',
  INDEX `id_agenzia` (`id_agenzia` ASC)  COMMENT '',
  INDEX `fascia` (`fascia` ASC)  COMMENT '',*/
  CONSTRAINT `auto_ibfk_1`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `test_ingegneria`.`utente` (`id_utente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `auto_ibfk_2`
    FOREIGN KEY (`id_agenzia`)
    REFERENCES `test_ingegneria`.`agenzie` (`numero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `auto_ibfk_3`
    FOREIGN KEY (`fascia`)
    REFERENCES `test_ingegneria`.`fascia` (`nome`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
	CONSTRAINT `auto_ibfk_4`
	FOREIGN KEY (id_contratto)
	REFERENCES `test_ingegneria`.`contratto` (numero_ordine)
	ON DELETE NO ACTION
    ON UPDATE NO ACTION);


/*SET SQL_MODE=@OLD_SQL_MODE;*/
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;