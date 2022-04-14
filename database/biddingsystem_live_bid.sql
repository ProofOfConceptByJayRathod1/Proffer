-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: biddingsystem
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `live_bid`
--

DROP TABLE IF EXISTS `live_bid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `live_bid` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `auction_id` bigint NOT NULL,
  `bid_status` varchar(255) DEFAULT NULL,
  `bid_time` time DEFAULT NULL,
  `bidder_id` varchar(255) DEFAULT NULL,
  `current_bid_value` int NOT NULL,
  `item_id` bigint DEFAULT NULL,
  `bid_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbf8m4r4494k8fjn9tv19jyahe` (`item_id`),
  CONSTRAINT `FKbf8m4r4494k8fjn9tv19jyahe` FOREIGN KEY (`item_id`) REFERENCES `catalog` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `live_bid`
--

LOCK TABLES `live_bid` WRITE;
/*!40000 ALTER TABLE `live_bid` DISABLE KEYS */;
INSERT INTO `live_bid` VALUES (10,3,'INITIAL','11:12:00','None',4270,5,'2022-04-14'),(11,3,'INITIAL','11:12:00','None',11837,6,'2022-04-14'),(12,3,'INITIAL','11:12:00','None',69160,7,'2022-04-14'),(13,3,'INITIAL','11:12:00','None',13500,9,'2022-04-14'),(14,5,'INITIAL','11:12:00','None',18000,8,'2022-04-14'),(15,5,'INITIAL','11:12:00','None',110,16,'2022-04-14'),(16,5,'INITIAL','11:12:00','None',4270,18,'2022-04-14'),(17,5,'INITIAL','11:12:00','None',13500,19,'2022-04-14'),(18,8,'INITIAL','11:12:00','None',44230,10,'2022-04-14'),(19,8,'INITIAL','11:12:00','None',65,13,'2022-04-14');
/*!40000 ALTER TABLE `live_bid` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-14 11:15:13
