-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: city-db
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
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `device` (
  `id` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `hourly_consumption` float NOT NULL,
  `title` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_svnes7gltehw56j19agm781yy` (`title`),
  KEY `FKk92m2qj36vn62ctp5pgbt4982` (`user_id`),
  CONSTRAINT `FKk92m2qj36vn62ctp5pgbt4982` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` VALUES ('13290abc-2f95-4973-9525-38391ce42241','Device2','Device2',1050,'Device2',NULL),('18a1e879-57fc-4ffa-be6a-1b6659308e5c','Device11','Device11',1100,'Device11','43e38ccb-68c3-43ec-8f5f-7425183d1fb3'),('2d8ad365-3024-4a1c-9034-8ce67d66fd53','Device5','Device5',1200,'Device5','c8015684-173e-4f46-8fda-421b32e39685'),('36f0a61e-638e-4b62-a7e8-87057db065ca','Device7','Device7',1000,'Device7','c8015684-173e-4f46-8fda-421b32e39685'),('58489f9d-8d15-41e3-938d-43eae67e924f','Device10','Device10',200,'Device10','7504b4e5-b13e-4db3-bce5-a53505cc1670'),('85e564af-7907-4c1c-b411-6ad8d943bdb0','Device4','Device4',1050,'Device4','c8015684-173e-4f46-8fda-421b32e39685'),('907e966b-c111-4b96-84b6-8912f27964b8','Device3','Device3',1000,'Device3','e50762ef-1719-471e-8315-b0576da2af6f'),('9f5f6483-ed42-4e73-a873-3bd6e41f420f','Device1','Device1',1100,'Device1',NULL),('afbc9f57-7ce4-4440-83a9-f71e08477c9a','Device6','Device6',1200,'Device6','c8015684-173e-4f46-8fda-421b32e39685'),('ce8d5266-cf73-4437-8c7d-c57c27000520','Device9','Device9',1150,'Device9','7504b4e5-b13e-4db3-bce5-a53505cc1670'),('de6e6f30-f8ad-4502-b76f-877b69dbf3c4','Device8','Device8',1000,'Device8','c8015684-173e-4f46-8fda-421b32e39685');
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-12 23:15:54
