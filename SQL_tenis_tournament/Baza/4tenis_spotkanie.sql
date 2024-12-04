-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tenis
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `spotkanie`
--

DROP TABLE IF EXISTS `spotkanie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spotkanie` (
  `idSpotkanie` int NOT NULL AUTO_INCREMENT,
  `Set1` varchar(5) DEFAULT NULL,
  `Set2` varchar(5) DEFAULT NULL,
  `Set3` varchar(5) DEFAULT NULL,
  `Set4` varchar(5) DEFAULT NULL,
  `Set5` varchar(5) DEFAULT NULL,
  `idTurnieju` int NOT NULL,
  `idZawodnika1` int NOT NULL,
  `idZawodnika2` int NOT NULL,
  PRIMARY KEY (`idSpotkanie`,`idTurnieju`,`idZawodnika1`,`idZawodnika2`),
  KEY `fk_Spotkanie_Turniej1_idx` (`idTurnieju`),
  KEY `fk_Spotkanie_Zawodnik1_idx` (`idZawodnika1`),
  KEY `fk_Spotkanie_Zawodnik2_idx` (`idZawodnika2`),
  CONSTRAINT `fk_Spotkanie_Turniej1` FOREIGN KEY (`idTurnieju`) REFERENCES `turniej` (`idTurniej`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Spotkanie_Zawodnik1` FOREIGN KEY (`idZawodnika1`) REFERENCES `zawodnik` (`idZawodnik`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Spotkanie_Zawodnik2` FOREIGN KEY (`idZawodnika2`) REFERENCES `zawodnik` (`idZawodnik`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spotkanie`
--

LOCK TABLES `spotkanie` WRITE;
/*!40000 ALTER TABLE `spotkanie` DISABLE KEYS */;
INSERT INTO `spotkanie` VALUES (1,'6:3','6:4','4:6','3:7','7:6',2,1,2),(2,'6:3','6:4','6:2','','',2,1,2),(4,'6:3','6:4','6:3','','',2,1,2),(7,'6:4','6:4','7:4','','',2,1,2),(8,'6:3','6:4','6:3','','',2,1,2);
/*!40000 ALTER TABLE `spotkanie` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-12 12:55:38
