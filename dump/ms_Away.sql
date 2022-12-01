-- MySQL dump 10.13  Distrib 8.0.30, for macos12 (x86_64)
--
-- Host: 127.0.0.1    Database: ms
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `Away`
--

DROP TABLE IF EXISTS `Away`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Away` (
  `match_id` int NOT NULL AUTO_INCREMENT,
  `team_id` int NOT NULL,
  `away_possesion` varchar(5) DEFAULT NULL,
  `away_shoot` varchar(5) DEFAULT NULL,
  `away_target` varchar(5) DEFAULT NULL,
  `away_corner` varchar(5) DEFAULT NULL,
  `away_foul` varchar(5) DEFAULT NULL,
  `away_offside` varchar(5) DEFAULT NULL,
  `away_yellow` varchar(5) DEFAULT NULL,
  `away_red` varchar(5) DEFAULT NULL,
  `away_bench` varchar(5) DEFAULT NULL,
  `away_formation` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`match_id`),
  KEY `FK_Team_TO_Away_1` (`team_id`),
  CONSTRAINT `FK_Match_TO_Away_1` FOREIGN KEY (`match_id`) REFERENCES `Match` (`match_id`),
  CONSTRAINT `FK_Team_TO_Away_1` FOREIGN KEY (`team_id`) REFERENCES `Team` (`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Away`
--

LOCK TABLES `Away` WRITE;
/*!40000 ALTER TABLE `Away` DISABLE KEYS */;
/*!40000 ALTER TABLE `Away` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-01 14:06:08
