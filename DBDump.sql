CREATE DATABASE  IF NOT EXISTS `smarthallticket` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `smarthallticket`;
-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: smarthallticket
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email_id` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'tejasmatal@gmail.com','tejas@123'),(2,'santhilmatal@gmail.com','Santhil@123');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (2,'Mechanical Engineering'),(3,'Computer Engineering'),(4,'Information Technology'),(5,'Electronics');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classroom`
--

DROP TABLE IF EXISTS `classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classroom` (
  `id` int NOT NULL AUTO_INCREMENT,
  `number` int NOT NULL,
  `floor` int NOT NULL,
  `wing` varchar(10) NOT NULL,
  `capacity` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroom`
--

LOCK TABLES `classroom` WRITE;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
INSERT INTO `classroom` VALUES (1,4,2,'A',20),(2,1,1,'A',20),(3,1,1,'B',15),(4,2,1,'B',10),(5,3,1,'A',10),(6,1,1,'C',15),(7,1,1,'C',10),(8,2,2,'C',20),(9,1,1,'D',15),(10,1,3,'A',15),(11,4,1,'B',20),(12,3,1,'B',10),(13,1,2,'C',10),(14,2,1,'A',10),(15,1,2,'A',10);
/*!40000 ALTER TABLE `classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enroll`
--

DROP TABLE IF EXISTS `enroll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enroll` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `exam_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `exam_id` (`exam_id`),
  CONSTRAINT `enroll_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`),
  CONSTRAINT `enroll_ibfk_2` FOREIGN KEY (`exam_id`) REFERENCES `exams` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enroll`
--

LOCK TABLES `enroll` WRITE;
/*!40000 ALTER TABLE `enroll` DISABLE KEYS */;
INSERT INTO `enroll` VALUES (1,1,5),(2,1,6),(3,2,1),(4,5,10),(5,4,9),(6,3,8),(7,2,7),(8,6,7);
/*!40000 ALTER TABLE `enroll` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_subject`
--

DROP TABLE IF EXISTS `exam_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam_subject` (
  `id` int NOT NULL AUTO_INCREMENT,
  `exam_id` int DEFAULT NULL,
  `subject_id` int DEFAULT NULL,
  `date_of_exam` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `exam_id` (`exam_id`),
  KEY `subject_id` (`subject_id`),
  CONSTRAINT `exam_subject_ibfk_1` FOREIGN KEY (`exam_id`) REFERENCES `exams` (`id`),
  CONSTRAINT `exam_subject_ibfk_2` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_subject`
--

LOCK TABLES `exam_subject` WRITE;
/*!40000 ALTER TABLE `exam_subject` DISABLE KEYS */;
INSERT INTO `exam_subject` VALUES (1,1,1,'2017-08-10 00:00:00'),(2,1,2,'2017-08-10 00:00:00'),(3,1,3,'2023-03-20 01:16:00'),(4,1,4,'2023-03-20 01:21:00'),(5,1,5,'2023-03-20 01:24:00'),(6,1,6,'2023-03-20 01:26:00'),(7,3,1,'2023-03-14 15:37:00'),(8,1,7,'2023-03-18 22:47:00'),(9,5,1,'2023-03-26 16:30:00'),(10,6,2,'2023-03-27 02:15:00'),(11,6,3,'2023-03-28 16:16:00'),(12,7,1,'2023-03-28 19:43:00'),(13,7,2,'2023-03-29 20:44:00'),(14,7,4,'2023-03-30 20:44:00'),(15,7,3,'2023-03-31 20:44:00'),(16,7,6,'2023-04-01 20:45:00'),(17,8,1,'2023-03-28 19:30:00'),(18,8,2,'2023-03-29 19:47:00'),(19,8,4,'2023-03-30 19:47:00'),(20,8,3,'2023-03-31 19:47:00'),(21,8,6,'2023-04-01 19:48:00'),(22,9,1,'2023-03-28 19:48:00'),(23,9,2,'2023-03-29 19:49:00'),(24,9,4,'2023-03-30 19:49:00'),(25,9,3,'2023-03-31 17:49:00'),(26,9,6,'2023-04-01 19:54:00'),(27,10,1,'2023-03-28 19:50:00'),(28,10,2,'2023-03-29 19:50:00'),(29,10,4,'2023-03-30 19:51:00'),(30,10,3,'2023-03-31 19:51:00'),(31,10,6,'2023-04-01 19:51:00');
/*!40000 ALTER TABLE `exam_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exams`
--

DROP TABLE IF EXISTS `exams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exams` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `held_year` varchar(4) DEFAULT NULL,
  `branch_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `branch_id` (`branch_id`),
  CONSTRAINT `exams_ibfk_1` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exams`
--

LOCK TABLES `exams` WRITE;
/*!40000 ALTER TABLE `exams` DISABLE KEYS */;
INSERT INTO `exams` VALUES (1,'winter','2023',5),(3,'winter','2023',2),(4,'winter','2023',3),(5,'winter','2023',4),(6,'Summer','2023',4),(7,'B.E (Electronics) SEM 8','2023',5),(8,'B.E (Mechanical) SEM 7','2023',2),(9,'B.E (Computer) SEM 8','2023',3),(10,'B.E (Information Technology) SEM 7','2023',4);
/*!40000 ALTER TABLE `exams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_classroom`
--

DROP TABLE IF EXISTS `student_classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_classroom` (
  `id` int NOT NULL AUTO_INCREMENT,
  `classroom_id` int NOT NULL,
  `student_id` int NOT NULL,
  `exam_subject_id` int NOT NULL,
  `date_of_exam` datetime DEFAULT NULL,
  `seat_number` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `exam_subject_id` (`exam_subject_id`),
  KEY `classroom_id` (`classroom_id`),
  CONSTRAINT `student_classroom_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`),
  CONSTRAINT `student_classroom_ibfk_2` FOREIGN KEY (`exam_subject_id`) REFERENCES `exam_subject` (`id`),
  CONSTRAINT `student_classroom_ibfk_3` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_classroom`
--

LOCK TABLES `student_classroom` WRITE;
/*!40000 ALTER TABLE `student_classroom` DISABLE KEYS */;
INSERT INTO `student_classroom` VALUES (1,2,2,12,'2023-03-28 19:43:00',1),(2,2,6,12,'2023-03-28 19:43:00',2),(3,2,2,13,'2023-03-29 20:44:00',1),(4,2,6,13,'2023-03-29 20:44:00',2),(5,2,2,14,'2023-03-30 20:44:00',1),(6,2,6,14,'2023-03-30 20:44:00',2),(7,2,2,15,'2023-03-31 20:44:00',1),(8,2,6,15,'2023-03-31 20:44:00',2),(9,2,2,16,'2023-04-01 20:45:00',1),(10,2,6,16,'2023-04-01 20:45:00',2);
/*!40000 ALTER TABLE `student_classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fname` varchar(30) NOT NULL,
  `lname` varchar(30) NOT NULL,
  `aadhar_number` decimal(12,0) NOT NULL,
  `DOB` date NOT NULL,
  `Branch_id` int NOT NULL,
  `Contact_no` decimal(10,0) NOT NULL,
  `Email_id` varchar(50) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `aadhar_number` (`aadhar_number`,`Email_id`,`Contact_no`),
  KEY `Branch_id` (`Branch_id`),
  CONSTRAINT `students_ibfk_1` FOREIGN KEY (`Branch_id`) REFERENCES `branch` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,'Rahul','Chavan',121213131414,'2023-03-24',4,7045815501,'rahultest@gmail.com','Test Address','Rahul@123'),(2,'santhil','matal',151516161717,'2001-10-30',5,7045588389,'santhilmatal@gmail.com','gavanipda','Santhil@123'),(3,'Pratik','kir',123443211234,'2001-06-27',2,7900072492,'pratikkir@gmail.com','airoli','Pratik@123'),(4,'Priyam','Adam',456776541234,'2001-06-15',3,8433849125,'priyamadam@gmail.com','airoli','Priyam@123'),(5,'Swaraj','lonandkar',987667891234,'2001-06-12',4,7738819317,'swarajloni@gmail.com','thane','Swaraj@123'),(6,'prasanna','kolte',345634563456,'2023-03-31',5,9561592591,'prasannakolte@gmail.com','badlapur','Kolte@123');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjects`
--

DROP TABLE IF EXISTS `subjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subjects` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`code`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjects`
--

LOCK TABLES `subjects` WRITE;
/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
INSERT INTO `subjects` VALUES (4,'Applied Chemistry - I','ELC104'),(1,'Applied Mathematics - I ','ELC101'),(2,'Applied Physics - I','ELC102'),(6,'BEE','ELC106'),(5,'Data Science','ELC105'),(3,'Engineering Mechanics','ELC103'),(7,'ME','IT101');
/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-01  9:54:23
