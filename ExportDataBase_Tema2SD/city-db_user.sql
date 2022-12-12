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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `age` int NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gj2fy3dcix7ph7k8684gka40c` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('4399d95f-fbc2-44e4-afa1-55e709462350','Address10',30,'Email10@Email10.com','UserZece','Passwor10-','admin'),('43e38ccb-68c3-43ec-8f5f-7425183d1fb3','Address4',24,'Email4@Email4.com','NumePatru','Passwor4-','client'),('5484cbbb-9149-4ced-bef2-b64c3435d813','Address9',29,'Email9@Email9.com','UserNoua','Passwor9-','client'),('5969e1de-b5e9-4d8d-b08d-0483c6080d88','Address8',28,'Email8@Email8.com','UserOpt','Passwor8-','client'),('6e15e38d-4efa-460e-9fec-74e84090d91c','Address6',26,'Email6@Email6.com','NumeSase','Passwor6-','client'),('7504b4e5-b13e-4db3-bce5-a53505cc1670','Address3',23,'Email3@Email3.com','NumeTrei','Passwor3-','client'),('8e5ddbc3-60b3-443f-943c-0413850fac44','Address5',25,'Email5@Email5.com','NumeCinci','Passwor5-','client'),('9b44a908-f378-4a94-9bf3-56718d271079','Address7',27,'Emal7@Email7.com','UserSapte','Passwor7-','client'),('c8015684-173e-4f46-8fda-421b32e39685','Address2',25,'Email2@Email2.com','NumeDoi','Passwor2-','client'),('e50762ef-1719-471e-8315-b0576da2af6f','Address1',21,'Email1@Email1.com','NameUnu','Passwor1-','admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
