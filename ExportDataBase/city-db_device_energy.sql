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
-- Table structure for table `device_energy`
--

DROP TABLE IF EXISTS `device_energy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `device_energy` (
  `id` varchar(255) NOT NULL,
  `day_plus_hour_selected` datetime NOT NULL,
  `value` int NOT NULL,
  `device_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKby740n21yium52d8mj9t8po0y` (`device_id`),
  CONSTRAINT `FKby740n21yium52d8mj9t8po0y` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device_energy`
--

LOCK TABLES `device_energy` WRITE;
/*!40000 ALTER TABLE `device_energy` DISABLE KEYS */;
INSERT INTO `device_energy` VALUES ('13290abc-2f95-4973-9525-38391ce42242','2022-11-16 00:00:00',20,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42243','2022-11-16 01:00:00',45,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42244','2022-11-16 02:00:00',33,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42245','2022-11-16 03:00:00',10,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42246','2022-11-16 04:00:00',35,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42247','2022-11-16 05:00:00',67,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42248','2022-11-16 06:00:00',44,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42249','2022-11-16 07:00:00',566,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42250','2022-11-16 08:00:00',100,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42251','2022-11-16 09:00:00',300,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42252','2022-11-16 10:00:00',45,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42253','2022-11-16 11:00:00',67,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42254','2022-11-16 12:00:00',66,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42255','2022-11-16 13:00:00',10,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42256','2022-11-16 14:00:00',58,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42257','2022-11-16 15:00:00',14,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42258','2022-11-16 16:00:00',567,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42259','2022-11-16 17:00:00',13,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42260','2022-11-16 18:00:00',455,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42261','2022-11-16 19:00:00',400,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42262','2022-11-16 20:00:00',500,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42263','2022-11-16 21:00:00',377,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42264','2022-11-16 22:00:00',56,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42265','2022-11-16 23:00:00',78,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42266','2022-11-16 12:00:00',100,'2d8ad365-3024-4a1c-9034-8ce67d66fd53'),('13290abc-2f95-4973-9525-38391ce42267','2022-11-16 13:00:00',101,'2d8ad365-3024-4a1c-9034-8ce67d66fd53'),('13290abc-2f95-4973-9525-38391ce42268','2022-11-17 13:00:00',46,'36f0a61e-638e-4b62-a7e8-87057db065ca'),('13290abc-2f95-4973-9525-38391ce42269','2022-11-17 16:00:00',10,'36f0a61e-638e-4b62-a7e8-87057db065ca'),('13290abc-2f95-4973-9525-38391ce42270','2022-11-17 20:00:00',33,'36f0a61e-638e-4b62-a7e8-87057db065ca'),('13290abc-2f95-4973-9525-38391ce42271','2022-11-18 00:00:00',300,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42272','2022-11-18 01:00:00',140,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42273','2022-11-18 02:00:00',500,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42274','2022-11-18 03:00:00',400,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42275','2022-11-18 04:00:00',300,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42276','2022-11-18 05:00:00',400,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42277','2022-11-18 06:00:00',100,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42278','2022-11-18 07:00:00',200,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42279','2022-11-18 08:00:00',300,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42280','2022-11-18 09:00:00',400,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42281','2022-11-18 10:00:00',100,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42282','2022-11-18 11:00:00',200,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42283','2022-11-18 12:00:00',300,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42284','2022-11-18 13:00:00',100,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42285','2022-11-18 14:00:00',700,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42286','2022-11-18 15:00:00',800,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42287','2022-11-18 16:00:00',900,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42288','2022-11-18 17:00:00',100,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42289','2022-11-18 18:00:00',200,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42290','2022-11-18 19:00:00',700,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42291','2022-11-18 20:00:00',500,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42292','2022-11-18 21:00:00',200,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42293','2022-11-18 22:00:00',100,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42294','2022-11-18 23:00:00',150,'ce8d5266-cf73-4437-8c7d-c57c27000520'),('13290abc-2f95-4973-9525-38391ce42295','2022-11-18 10:00:00',100,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42296','2022-11-18 15:00:00',200,'58489f9d-8d15-41e3-938d-43eae67e924f'),('13290abc-2f95-4973-9525-38391ce42297','2022-11-18 20:00:00',400,'58489f9d-8d15-41e3-938d-43eae67e924f');
/*!40000 ALTER TABLE `device_energy` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-21 21:10:45
