CREATE DATABASE  IF NOT EXISTS `sigem_db` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sigem_db`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: sigem_db
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alunos`
--

DROP TABLE IF EXISTS `alunos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alunos` (
  `idAluno` int NOT NULL AUTO_INCREMENT,
  `nomeAluno` varchar(50) NOT NULL,
  `dataNascimento` date NOT NULL,
  `nivel` varchar(1) NOT NULL COMMENT 'I - Iniciante\\nM - Médio\\nA - Avançado',
  `contatoAluno` varchar(16) NOT NULL,
  `nomeResponsavel` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idAluno`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alunos`
--

LOCK TABLES `alunos` WRITE;
/*!40000 ALTER TABLE `alunos` DISABLE KEYS */;
INSERT INTO `alunos` VALUES (1,'Rafinha da Silva','2012-02-26','M','(44) 96543-7895','Dona Mãe da Rafinha'),(9,'Eduardo Pereira','2010-06-08','M','(44) 99874-1254','Mãe do Eduardo'),(10,'Raphael Irmão do Eduardo','2014-06-30','M','(44) 99878-1254','Mãe do Eduardo e do Raphael'),(11,'Flavio Andrade','2002-08-14','I','(44) 98745-5632',NULL),(12,'Adriano Alves','2000-09-16','A','(44) 99865-1234',NULL),(13,'Cristian Machado','1999-12-08','I','(44) 99665-8521',NULL),(14,'Roberta Vizufino','2006-10-20','A','(44) 96325-7845',NULL),(15,'Paulinho Gogó da Silva','1988-10-17','A','(11) 99632-8745',NULL);
/*!40000 ALTER TABLE `alunos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aulas`
--

DROP TABLE IF EXISTS `aulas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aulas` (
  `idAula` int NOT NULL AUTO_INCREMENT,
  `idAluno` int NOT NULL,
  `idProfessor` int NOT NULL,
  `idCurso` int NOT NULL,
  `idModalidade` int NOT NULL,
  `idSala` int NOT NULL,
  `idDiaSemana` int NOT NULL,
  `dataInicio` date NOT NULL,
  PRIMARY KEY (`idAula`),
  KEY `fk_aulas_alunos_idx` (`idAluno`),
  KEY `fk_aulas_professores_idx` (`idProfessor`),
  KEY `fk_aulas_cursos_idx` (`idCurso`),
  KEY `fk_aulas_modalidades_idx` (`idModalidade`),
  KEY `fk_aulas_salas_idx` (`idSala`),
  KEY `fk_aulas_diasemanas_idx` (`idDiaSemana`),
  CONSTRAINT `fk_aulas_alunos` FOREIGN KEY (`idAluno`) REFERENCES `alunos` (`idAluno`),
  CONSTRAINT `fk_aulas_cursos` FOREIGN KEY (`idCurso`) REFERENCES `curso` (`idCurso`),
  CONSTRAINT `fk_aulas_diasemanas` FOREIGN KEY (`idDiaSemana`) REFERENCES `diasemana` (`idDiasemana`),
  CONSTRAINT `fk_aulas_modalidades` FOREIGN KEY (`idModalidade`) REFERENCES `modalidades` (`idModalidade`),
  CONSTRAINT `fk_aulas_professores` FOREIGN KEY (`idProfessor`) REFERENCES `professores` (`idProfessor`),
  CONSTRAINT `fk_aulas_salas` FOREIGN KEY (`idSala`) REFERENCES `salas` (`idSala`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aulas`
--

LOCK TABLES `aulas` WRITE;
/*!40000 ALTER TABLE `aulas` DISABLE KEYS */;
INSERT INTO `aulas` VALUES (5,1,1,3,1,1,5,'2026-04-01'),(6,9,1,2,2,3,3,'2026-04-01'),(7,10,1,2,2,3,3,'2026-04-01'),(8,13,2,3,1,1,4,'2026-03-30'),(9,14,1,1,1,3,2,'2026-04-02');
/*!40000 ALTER TABLE `aulas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso` (
  `idCurso` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(15) NOT NULL,
  PRIMARY KEY (`idCurso`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` VALUES (1,'Piano'),(2,'Teclado'),(3,'Violão'),(4,'Musicalização');
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diasemana`
--

DROP TABLE IF EXISTS `diasemana`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diasemana` (
  `idDiasemana` int NOT NULL AUTO_INCREMENT,
  `dia` varchar(7) NOT NULL,
  PRIMARY KEY (`idDiasemana`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diasemana`
--

LOCK TABLES `diasemana` WRITE;
/*!40000 ALTER TABLE `diasemana` DISABLE KEYS */;
INSERT INTO `diasemana` VALUES (2,'Segunda'),(3,'Terça'),(4,'Quarta'),(5,'Quinta'),(6,'Sexta');
/*!40000 ALTER TABLE `diasemana` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `financeiro`
--

DROP TABLE IF EXISTS `financeiro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `financeiro` (
  `idFinanceiro` int NOT NULL AUTO_INCREMENT,
  `valor` decimal(8,2) NOT NULL,
  `dataVencimento` date NOT NULL,
  `dataPagamento` date DEFAULT NULL,
  `idAluno` int NOT NULL,
  `status` varchar(8) DEFAULT NULL COMMENT 'Pendente\nPago',
  PRIMARY KEY (`idFinanceiro`),
  KEY `fk_aulas_alunos_idx` (`idAluno`),
  CONSTRAINT `fk_financeiro_alunos` FOREIGN KEY (`idAluno`) REFERENCES `alunos` (`idAluno`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `financeiro`
--

LOCK TABLES `financeiro` WRITE;
/*!40000 ALTER TABLE `financeiro` DISABLE KEYS */;
/*!40000 ALTER TABLE `financeiro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (3);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modalidades`
--

DROP TABLE IF EXISTS `modalidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modalidades` (
  `idModalidade` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(15) NOT NULL COMMENT 'Individual\\\\nGrupo',
  PRIMARY KEY (`idModalidade`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modalidades`
--

LOCK TABLES `modalidades` WRITE;
/*!40000 ALTER TABLE `modalidades` DISABLE KEYS */;
INSERT INTO `modalidades` VALUES (1,'Individual'),(2,'Grupo');
/*!40000 ALTER TABLE `modalidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presencas`
--

DROP TABLE IF EXISTS `presencas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `presencas` (
  `idPresenca` int NOT NULL AUTO_INCREMENT,
  `data` date NOT NULL,
  `idAluno` int NOT NULL,
  `conteudo` varchar(100) NOT NULL,
  `status` varchar(2) NOT NULL,
  PRIMARY KEY (`idPresenca`),
  KEY `fk_presencas_alunos_idx` (`idAluno`),
  CONSTRAINT `fk_presencas_alunos` FOREIGN KEY (`idAluno`) REFERENCES `alunos` (`idAluno`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presencas`
--

LOCK TABLES `presencas` WRITE;
/*!40000 ALTER TABLE `presencas` DISABLE KEYS */;
INSERT INTO `presencas` VALUES (1,'2026-04-03',11,'Claves de sol e fa','P'),(2,'2026-04-02',14,'Armadura de Clave','P');
/*!40000 ALTER TABLE `presencas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professores`
--

DROP TABLE IF EXISTS `professores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professores` (
  `idProfessor` int NOT NULL AUTO_INCREMENT,
  `nomeProfessor` varchar(50) NOT NULL,
  `contatoProfessor` varchar(16) NOT NULL,
  `dataNascProfessor` date NOT NULL,
  PRIMARY KEY (`idProfessor`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professores`
--

LOCK TABLES `professores` WRITE;
/*!40000 ALTER TABLE `professores` DISABLE KEYS */;
INSERT INTO `professores` VALUES (1,'Dilber Gonçalves','(44) 99807-7553','1996-03-27'),(2,'Juliane Valle Cordeiro','(44) 99838-6917','1999-05-24');
/*!40000 ALTER TABLE `professores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salas`
--

DROP TABLE IF EXISTS `salas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salas` (
  `idSala` int NOT NULL AUTO_INCREMENT,
  `nomeSala` varchar(15) NOT NULL,
  PRIMARY KEY (`idSala`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salas`
--

LOCK TABLES `salas` WRITE;
/*!40000 ALTER TABLE `salas` DISABLE KEYS */;
INSERT INTO `salas` VALUES (1,'Guitarra'),(3,'Piano'),(4,'Estudio'),(7,'Musicalizacao');
/*!40000 ALTER TABLE `salas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sigem_db'
--

--
-- Dumping routines for database 'sigem_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-06 14:05:19
