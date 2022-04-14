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
-- Table structure for table `auction`
--

DROP TABLE IF EXISTS `auction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auction` (
  `event_no` bigint NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `duration` bigint NOT NULL,
  `event_title` varchar(255) DEFAULT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `seller_id` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `start_date_time` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `end_date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`event_no`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auction`
--

LOCK TABLES `auction` WRITE;
/*!40000 ALTER TABLE `auction` DISABLE KEYS */;
INSERT INTO `auction` VALUES (3,'Jewelry/ Gemstones & Watches','Auctionbarn Estates is a family Jewelry and watch business that is celebrating our 100th Anniversary in 2020, our online business was created in 1999 to bring rare & beautiful items to online auction',240,' Rare Rolex Certified Watches & Fine Jewelry by Auctionbarn Estates','11.jpg','sq@gmail.com','2022-04-14','15:00:00','CREATED','Apr 14,2022 15:00:00','2022-04-14 15:00:00','2022-04-14 19:00:00'),(4,'Art/ Antiques & Collectibles','BK Auctions is pleased to present this catalog which is full of luxury and collectible goods for everyone.',240,' Paper Money, Artwork, & Coin Event!','1.jpg','sq@gmail.com','2022-04-15','10:00:00','CREATED','Apr 15,2022 10:00:00','2022-04-15 10:00:00','2022-04-15 14:00:00'),(5,'Jewelry/ Gemstones & Watches','Auctionbarn Estates is a family Jewelry and watch business that is celebrating our 100th Anniversary in 2020, our online business was created in 1999 to bring rare & beautiful items to online auction',420,' Rare Rolex Certified Watches & Fine Jewelry','11.jpg','sq@gmail.com','2022-04-14','10:00:00','CREATED','Apr 14,2022 10:00:00','2022-04-14 10:00:00','2022-04-16 17:00:00'),(6,'Art/ Antiques & Collectibles','Historical rocks',120,'Rocks and Rolls','microservice architecture.png','am@gmail.com','2022-04-14','17:00:00','CREATED','Apr 14,2022 17:00:00','2022-04-14 17:00:00','2022-05-01 19:00:00'),(7,'Art/ Antiques & Collectibles','Historical rocks',180,'Rocks and Rolls','microservice architecture.png','am@gmail.com','2022-04-15','20:00:00','CREATED','Apr 12,2022 20:00:00','2022-04-15 20:00:00','2022-04-15 23:00:00'),(8,'Art/ Antiques & Collectibles','Historical rocks',780,'Rocks and Rolls','microservice architecture.png','am@gmail.com','2022-04-14','09:00:00','CREATED','Apr 14,2022 09:00:00','2022-04-14 09:00:00','2022-04-14 22:00:00');
/*!40000 ALTER TABLE `auction` ENABLE KEYS */;
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
