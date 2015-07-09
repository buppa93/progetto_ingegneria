-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generato il: Lug 08, 2015 alle 23:56
-- Versione del server: 5.5.43
-- Versione PHP: 5.3.10-1ubuntu3.18

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `test_ingegneria`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `agenzie`
--

CREATE TABLE IF NOT EXISTS `agenzie` (
  `numero` varchar(5) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `indirizzo` varchar(45) NOT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `agenzie`
--

INSERT INTO `agenzie` (`numero`, `nome`, `indirizzo`) VALUES
('11111', 'test', 'via afdsd'),
('11112', 'test2', 'via adlsdfdsf'),
('ag001', 'f.lli Buono', 'Gallipoli, Corso Roma 22'),
('ag002', 'Blurent', 'Gallipoli, Lungomare Marconi 15'),
('ag003', 'MotoAndAuto', 'Lecce, viale Universit? 11'),
('ag004', 'Noleggi e Vendite', ' Copertino, via Circonvallazione snc');

-- --------------------------------------------------------

--
-- Struttura della tabella `auto`
--

CREATE TABLE IF NOT EXISTS `auto` (
  `targa` varchar(7) NOT NULL,
  `modello` varchar(25) NOT NULL,
  `marca` varchar(25) NOT NULL,
  `km` int(10) unsigned NOT NULL,
  `id_cliente` varchar(5) DEFAULT NULL,
  `id_contratto` varchar(5) DEFAULT NULL,
  `id_agenzia` varchar(5) NOT NULL,
  `fascia` varchar(1) NOT NULL,
  `disponibilita` int(10) unsigned NOT NULL,
  PRIMARY KEY (`targa`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_agenzia` (`id_agenzia`),
  KEY `fascia` (`fascia`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `auto`
--

INSERT INTO `auto` (`targa`, `modello`, `marca`, `km`, `id_cliente`, `id_contratto`, `id_agenzia`, `fascia`, `disponibilita`) VALUES
('at256re', 'panda', 'fiat', 20000, NULL, NULL, 'ag002', 'A', 1),
('ba333bv', 'megane', 'renault', 150000, NULL, NULL, 'ag004', 'B', 1),
('le398hh', 'leon', 'seat', 320000, NULL, NULL, 'ag002', 'B', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `clienti`
--

CREATE TABLE IF NOT EXISTS `clienti` (
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  PRIMARY KEY (`telefono`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `clienti`
--

INSERT INTO `clienti` (`nome`, `cognome`, `telefono`) VALUES
('esempio', 'esempio', '0123456789');

-- --------------------------------------------------------

--
-- Struttura della tabella `contratto`
--

CREATE TABLE IF NOT EXISTS `contratto` (
  `numero_ordine` varchar(5) NOT NULL,
  `id_agenzia` varchar(5) NOT NULL,
  `id_cliente` varchar(5) NOT NULL,
  `data_inizio` date NOT NULL,
  `data_fine` date NOT NULL,
  `agenzia_rest` varchar(5) NOT NULL,
  `tipo` int(11) NOT NULL,
  PRIMARY KEY (`numero_ordine`),
  KEY `id_agenzia` (`id_agenzia`),
  KEY `agenzia_rest` (`agenzia_rest`),
  KEY `id_cliente` (`id_cliente`),
  KEY `tipo` (`tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `fascia`
--

CREATE TABLE IF NOT EXISTS `fascia` (
  `nome` char(1) NOT NULL,
  `n_porte` int(11) NOT NULL,
  `n_posti` int(11) NOT NULL,
  `tipo_vettura` varchar(20) NOT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `fascia`
--

INSERT INTO `fascia` (`nome`, `n_porte`, `n_posti`, `tipo_vettura`) VALUES
('A', 5, 5, 'utilitaria'),
('B', 5, 5, 'berlina');

-- --------------------------------------------------------

--
-- Struttura della tabella `tipo_contratto`
--

CREATE TABLE IF NOT EXISTS `tipo_contratto` (
  `id_tipo` int(11) NOT NULL,
  `tipo_noleggio` varchar(20) NOT NULL,
  `tipo_chilometraggio` varchar(20) NOT NULL,
  PRIMARY KEY (`id_tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id_user` varchar(5) NOT NULL,
  `nome` varchar(40) NOT NULL,
  `cognome` varchar(40) NOT NULL,
  `num_telefono` varchar(10) NOT NULL,
  `password` text NOT NULL,
  `tipo` varchar(3) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `user`
--

INSERT INTO `user` (`id_user`, `nome`, `cognome`, `num_telefono`, `password`, `tipo`) VALUES
('12345', 'prova', 'prova', '0123456789', '189bbbb00c5f1fb7fba9ad9285f193d1', 'adm'),
('34745', 'giuseppe', 'sansone', '7894561230', '353f9bfab2d01dbb1db343fdaf9ab02e', 'adm'),
('53488', 'ivan', 'scorrano', '', 'ea9ff2b1a3e3e56488bbabe32978861c', 'adm'),
('67158', 'stefano', 'carrino', '0123456789', '5ad33864521cf3d985c03d7d2818a995', 'usr'),
('85612', 'pinco', 'pallino', '1234567890', '38938c3cdc38161f1720b086575f7208', 'usr'),
('test1', 'test', 'test', '1234567890', '098f6bcd4621d373cade4e832627b4f6', 'usr');

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `auto`
--
ALTER TABLE `auto`
  ADD CONSTRAINT `auto_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `auto_ibfk_2` FOREIGN KEY (`id_agenzia`) REFERENCES `agenzie` (`numero`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `auto_ibfk_3` FOREIGN KEY (`fascia`) REFERENCES `fascia` (`nome`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `contratto`
--
ALTER TABLE `contratto`
  ADD CONSTRAINT `contratto_ibfk_1` FOREIGN KEY (`id_agenzia`) REFERENCES `agenzie` (`numero`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `contratto_ibfk_2` FOREIGN KEY (`agenzia_rest`) REFERENCES `agenzie` (`numero`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `contratto_ibfk_3` FOREIGN KEY (`id_cliente`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `contratto_ibfk_4` FOREIGN KEY (`tipo`) REFERENCES `tipo_contratto` (`id_tipo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
