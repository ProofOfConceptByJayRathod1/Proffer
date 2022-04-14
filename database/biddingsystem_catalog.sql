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
-- Table structure for table `catalog`
--

DROP TABLE IF EXISTS `catalog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `catalog` (
  `item_id` bigint NOT NULL AUTO_INCREMENT,
  `item_desc` varchar(255) DEFAULT NULL,
  `item_image` varchar(255) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `item_start_bid` int NOT NULL,
  `event_id` bigint DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `FK820lx3qlriqiy2y71qnnenobn` (`event_id`),
  CONSTRAINT `FK820lx3qlriqiy2y71qnnenobn` FOREIGN KEY (`event_id`) REFERENCES `auction` (`event_no`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalog`
--

LOCK TABLES `catalog` WRITE;
/*!40000 ALTER TABLE `catalog` DISABLE KEYS */;
INSERT INTO `catalog` VALUES (5,'Ref: ARA2440 Item no.: DAG-6/060-2444  Specification:  Gold Content: Solid 18K White Gold Weight of Item: 4.70 grams Size: 6.75 Country Made: U.S.A','21.jpg','18K Gold 0.68ct Ruby & 0.48ctw Diamond Ring',4270,3),(6,'Ref: WAG6764 Item no.: FA2056111016-BR5224AE  Specification:  Gold Content: Solid 18K White Gold Weight of Item: 3.98 grams Size: 7.25 Country Made: U.S.A','31.jpg','18K Gold 1.33ct Emerald & 0.44ctw Diamond Ring',11837,3),(7,'Ref: WAG3952 Item no.: LAI030007014-R15130  Specification:  Gold Content: Solid 14K White Gold Weight of Item: 5.00 grams Size: 7 Country Made: U.S.A','41.jpg','14K Gold 12.00ct Turquoise & 0.69ctw Diamond Ring',69160,3),(8,'Ref: WAG16000 Item No.: ARXT014  Specification:  Model: DateJust Oyster Perpetual Movement: Perpetual, Self Winding Chronometer Function: Instantaneous Date Case Size: 36mm','51.jpg','Rolex SS DateJust Diamond 36mm Wristwatch',18000,5),(9,'Ref: WAG5400 Item no.: FA2054311012-BR6400ATZ4  Gold Content: 18K White Gold Weight of Item: 5.45 grams Size: 7.5','61.jpg',' 18K White Gold 2.20ct Tanzanite & 1.05ctw Diamond',13500,3),(10,'Ref: WAG14800 Item no.: ARA8017  Specification:  Gold Content: Solid 14K White Gold Weight of Item: 1.20 grams Country Made: U.S.A','71.jpg','14K Gold 2.10ctw Diamond Stud Earrings',44230,8),(11,'One 1950 $10 Federal Reserve Note St. Louis Fr.2010-HW PMG Superb Gem Uncirculated 67EPQ. PMG Certified.','5.jpg',' March 3rd, 1863 Fourth Issue Fifty Cents Fractional Currency Note',250,4),(12,'One 1935A $1 North Africa WWII Emergency Issue Silver Certificate Note','8.jpg','1935A $1 North Africa WWII Emergency Issue Silver Certificate Note PCGS About New 58',300,4),(13,'One 1926-S $1 Peace Silver Dollar Coin.','2.jpg','1926-S $1 Peace Silver Dollar Coin',65,8),(14,'One 1844-O $5 Liberty Head Half Eagle Gold Coin.','3.jpg',' 1844-O $5 Liberty Head Half Eagle Gold Coin',1200,4),(15,'Ref: ARA2440 Item no.: DAG-6/060-2444  Specification:  Gold Content: Solid 18K White Gold Weight of Item: 4.70 grams Size: 6.75 Country Made: U.S.A','4.jpg','1833 Capped Bust Half Dollar Coin',500,4),(16,'One 1833 Capped Bust Half Dollar Coin.','6.jpg','March 3rd, 1863 Fourth Issue Fifty Cents Fractional Currency Note',110,5),(17,' One 1994 $5 American Gold Eagle Coin.','7.jpg',' 1994 $5 American Gold Eagle Coin',750,4),(18,'Ref: ARA2440 Item no.: DAG-6/060-2444  Specification:  Gold Content: Solid 18K White Gold Weight of Item: 4.70 grams Size: 6.75 Country Made: U.S.A','21.jpg','18K Gold 0.68ct Ruby & 0.48ctw Diamond Ring',4270,5),(19,'Ref: WAG5400 Item no.: FA2054311012-BR6400ATZ4  Gold Content: 18K White Gold Weight of Item: 5.45 grams Size: 7.5','31.jpg','18K White Gold 2.20ct Tanzanite & 1.05ctw Diamond',13500,5);
/*!40000 ALTER TABLE `catalog` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-14 11:15:12
