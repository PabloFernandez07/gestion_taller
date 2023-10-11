-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.30 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para taller
CREATE DATABASE IF NOT EXISTS `taller` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `taller`;

-- Volcando estructura para tabla taller.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `pkCliente` int NOT NULL,
  `nif` varchar(12) DEFAULT NULL,
  `nombre` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`pkCliente`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla taller.cliente: ~3 rows (aproximadamente)
INSERT INTO `cliente` (`pkCliente`, `nif`, `nombre`) VALUES
	(1, '12312312A', 'Cliente 1'),
	(2, '22222312W', 'Cliente 2'),
	(3, '12312500C', 'Cliente 3');

-- Volcando estructura para tabla taller.mecanico
CREATE TABLE IF NOT EXISTS `mecanico` (
  `pkMecanico` int NOT NULL,
  `nombre` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`pkMecanico`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla taller.mecanico: ~0 rows (aproximadamente)
INSERT INTO `mecanico` (`pkMecanico`, `nombre`) VALUES
	(1, 'mecanico 1');

-- Volcando estructura para tabla taller.repara
CREATE TABLE IF NOT EXISTS `repara` (
  `pkRepara` int NOT NULL,
  `fkVehiculo` int NOT NULL,
  `fechaIni` date NOT NULL,
  `fechaFin` date DEFAULT NULL,
  PRIMARY KEY (`pkRepara`) USING BTREE,
  KEY `fkvehiculo` (`fkVehiculo`) USING BTREE,
  CONSTRAINT `repara_ibfk_1` FOREIGN KEY (`fkVehiculo`) REFERENCES `vehiculo` (`pkVehiculo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla taller.repara: ~2 rows (aproximadamente)
INSERT INTO `repara` (`pkRepara`, `fkVehiculo`, `fechaIni`, `fechaFin`) VALUES
	(1, 1001, '2023-09-26', NULL),
	(2, 1002, '2023-09-29', NULL);

-- Volcando estructura para tabla taller.servicio
CREATE TABLE IF NOT EXISTS `servicio` (
  `pkServicio` int NOT NULL,
  `nombre` varchar(80) NOT NULL,
  `precio` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`pkServicio`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla taller.servicio: ~4 rows (aproximadamente)
INSERT INTO `servicio` (`pkServicio`, `nombre`, `precio`) VALUES
	(10, 'Cambio neumáticos', 35.00),
	(11, 'Alineación dirección', 55.00),
	(12, 'Sustituir frenos', 25.00),
	(13, 'PRE ITV', 25.00);

-- Volcando estructura para tabla taller.servicio_repara
CREATE TABLE IF NOT EXISTS `servicio_repara` (
  `pkSr` int NOT NULL,
  `fkRepara` int NOT NULL,
  `fkMecanico` int NOT NULL,
  `tiempo` int DEFAULT NULL,
  PRIMARY KEY (`pkSr`) USING BTREE,
  KEY `fkrepara` (`fkRepara`) USING BTREE,
  KEY `fkmecanico` (`fkMecanico`) USING BTREE,
  CONSTRAINT `servicio_repara_ibfk_1` FOREIGN KEY (`fkRepara`) REFERENCES `repara` (`pkRepara`),
  CONSTRAINT `servicio_repara_ibfk_2` FOREIGN KEY (`fkMecanico`) REFERENCES `mecanico` (`pkMecanico`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla taller.servicio_repara: ~0 rows (aproximadamente)

-- Volcando estructura para tabla taller.tipo
CREATE TABLE IF NOT EXISTS `tipo` (
  `pkTipo` int NOT NULL,
  `nombre` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`pkTipo`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla taller.tipo: ~4 rows (aproximadamente)
INSERT INTO `tipo` (`pkTipo`, `nombre`) VALUES
	(100, 'Moto'),
	(101, 'Moto eléctrica'),
	(200, 'Bicicleta'),
	(201, 'Bicicleta eléctrica');

-- Volcando estructura para tabla taller.vehiculo
CREATE TABLE IF NOT EXISTS `vehiculo` (
  `pkVehiculo` int NOT NULL,
  `fkTipo` int NOT NULL,
  `matricula` varchar(7) DEFAULT NULL,
  `modelo` varchar(80) DEFAULT NULL,
  `fkCliente` int DEFAULT NULL,
  PRIMARY KEY (`pkVehiculo`) USING BTREE,
  KEY `fktipo` (`fkTipo`) USING BTREE,
  KEY `fkcliente` (`fkCliente`) USING BTREE,
  CONSTRAINT `vehiculo_ibfk_1` FOREIGN KEY (`fkTipo`) REFERENCES `tipo` (`pkTipo`),
  CONSTRAINT `vehiculo_ibfk_2` FOREIGN KEY (`fkCliente`) REFERENCES `cliente` (`pkCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla taller.vehiculo: ~4 rows (aproximadamente)
INSERT INTO `vehiculo` (`pkVehiculo`, `fkTipo`, `matricula`, `modelo`, `fkCliente`) VALUES
	(1001, 100, '1234BBK', 'Yamaha XT', 1),
	(1002, 200, '', 'Orbea Club verde', 1),
	(1003, 101, '0002BBK', 'BMW ETR', 2),
	(1004, 200, '', 'Mérida AB azul', 3);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
