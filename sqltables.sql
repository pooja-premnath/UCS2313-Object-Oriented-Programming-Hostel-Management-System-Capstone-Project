-- MySQL dump 10.13  Distrib 8.0.31, for macos13.0 (arm64)
--
-- Host: localhost    Database: java
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin1','password1'),(2,'admin2','password2');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hostelrooms`
--

DROP TABLE IF EXISTS `hostelrooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hostelrooms` (
  `OccupantName` varchar(255) NOT NULL,
  `RegisterNumber` varchar(100) NOT NULL,
  `OccupancyStatus` char(1) DEFAULT 'E',
  `RoomType` varchar(100) NOT NULL,
  `RoomNumber` int NOT NULL,
  `BlockNumber` varchar(5) NOT NULL,
  PRIMARY KEY (`RoomNumber`,`BlockNumber`,`RegisterNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hostelrooms`
--

LOCK TABLES `hostelrooms` WRITE;
/*!40000 ALTER TABLE `hostelrooms` DISABLE KEYS */;
INSERT INTO `hostelrooms` VALUES ('Rita Verma','2110567','O','Shared_AttachedBathroom',14,'5C'),('Pooja Premnath','2110152','O','Single_SharedBathroom',30,'3B'),('Ojuice','2110272','O','Single_AttachedBathroom',42,'LH7');
/*!40000 ALTER TABLE `hostelrooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `outpasslogs`
--

DROP TABLE IF EXISTS `outpasslogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `outpasslogs` (
  `OutpassNumber` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `RegisterNumber` varchar(255) NOT NULL,
  `BlockNumber` varchar(5) NOT NULL,
  `RoomNumber` int NOT NULL,
  `Purpose` varchar(500) NOT NULL,
  `Year` int NOT NULL,
  `OutDate` date NOT NULL,
  `InDate` date NOT NULL,
  `OutTime` time NOT NULL,
  `InTime` time NOT NULL,
  `WardenValidation` char(1) NOT NULL DEFAULT 'P',
  `ParentValidation` char(1) NOT NULL DEFAULT 'P',
  PRIMARY KEY (`OutpassNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `outpasslogs`
--

LOCK TABLES `outpasslogs` WRITE;
/*!40000 ALTER TABLE `outpasslogs` DISABLE KEYS */;
INSERT INTO `outpasslogs` VALUES (3,'Pooja Premnath','2110152','5A',19,'Going home',2,'2023-09-01','2023-10-01','11:30:00','10:30:00','A','A');
/*!40000 ALTER TABLE `outpasslogs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parent`
--

DROP TABLE IF EXISTS `parent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parent` (
  `ParentID` varchar(100) NOT NULL,
  `ParentName` varchar(255) NOT NULL,
  `ParentPhoneNumber` mediumtext NOT NULL,
  `ParentEmail` varchar(100) DEFAULT NULL,
  `RegisterNumber` varchar(100) NOT NULL,
  `ParentPassword` varchar(100) NOT NULL,
  PRIMARY KEY (`ParentID`),
  KEY `RegisterNumber` (`RegisterNumber`),
  CONSTRAINT `parent_ibfk_1` FOREIGN KEY (`RegisterNumber`) REFERENCES `student` (`RegisterNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parent`
--

LOCK TABLES `parent` WRITE;
/*!40000 ALTER TABLE `parent` DISABLE KEYS */;
INSERT INTO `parent` VALUES ('minu@2110567','Minu Verma','9876543211','minu@gmail.com','2110567','2110567@minu'),('preetha@2110152','Preetha Premnath','9003166462','tpreetha@gmail.com','2110152','2110152@preetha'),('tejuice','Tejuice','0987645321','tejuicedadofojuice1969@gmail.com','2110272','Tejuice@2110272');
/*!40000 ALTER TABLE `parent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `RegisterNumber` varchar(100) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `PhoneNo` mediumtext NOT NULL,
  `EmailID` varchar(200) DEFAULT NULL,
  `Year` int DEFAULT NULL,
  `Department` varchar(255) NOT NULL,
  `RoomNo` int NOT NULL,
  `RoomType` varchar(255) NOT NULL,
  `ParentName` varchar(255) NOT NULL,
  `ParentEmail` varchar(255) NOT NULL,
  `StudentPassword` varchar(100) NOT NULL,
  `BlockNo` varchar(3) NOT NULL,
  PRIMARY KEY (`RegisterNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('2110152','Pooja Premnath','2244668800','pooja2110152@ssn.edu.in',2,'Computer Science and Engineering',19,'Single_AttachedBathroom','Preetha Premnath','tpreetha@gmail.com','pooja@2110152','5A'),('2110272','Ojuice','9876512340','ojuice2110272@ssn.edu.in',2,'Computer Science and Engineering',5,'Single_AttachedBathroom','Tejuice','tejuicedadofojuice1969@gmail.com','ojuice@2110272','LH8'),('2110567','Rita Verma','1234567899','rita2110567@ssn.edu.in',1,'Mechanical Engineering',14,'Shared_AttachedBathroom','Minu Verma','minu@gmail.com','rita@2110567','5C');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visitor`
--

DROP TABLE IF EXISTS `visitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visitor` (
  `VisitorID` int NOT NULL AUTO_INCREMENT,
  `VisitorName` varchar(255) NOT NULL,
  `PhoneNo` mediumtext NOT NULL,
  `DateofVisit` date DEFAULT NULL,
  `InTime` time DEFAULT NULL,
  `OutTime` time DEFAULT NULL,
  `VisitorType` varchar(255) NOT NULL,
  `NatureOfVisit` varchar(255) NOT NULL,
  PRIMARY KEY (`VisitorID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitor`
--

LOCK TABLES `visitor` WRITE;
/*!40000 ALTER TABLE `visitor` DISABLE KEYS */;
INSERT INTO `visitor` VALUES (1,'Latha','1122334455','2022-01-03','11:30:00','12:30:00','Parent','Visit Ward'),(2,'John','9988776655','2022-01-04','09:00:00','10:00:00','Contractor','Maintenance');
/*!40000 ALTER TABLE `visitor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-10 23:36:45
