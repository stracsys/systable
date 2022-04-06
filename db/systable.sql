-- MariaDB dump 10.19  Distrib 10.7.3-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: systable
-- ------------------------------------------------------
-- Server version	10.7.3-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classes` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_chief` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `id_chief` (`id_chief`),
  CONSTRAINT `fk_class_chief` FOREIGN KEY (`id_chief`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES
(18,'DAR',15),
(19,'ASR',28),
(20,'RT',48),
(21,'LIPMEN',43),
(22,'LIP',39);
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courses` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `start_hours` int(5) NOT NULL,
  `duration` int(5) NOT NULL,
  `end_hours` int(5) NOT NULL,
  `is_dispense` tinyint(1) NOT NULL,
  `is_modify` tinyint(1) NOT NULL,
  `is_valid` tinyint(1) NOT NULL,
  `is_payed` tinyint(1) NOT NULL,
  `content` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date` date NOT NULL,
  `id_module` int(5) NOT NULL,
  `id_timetable` int(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `start_hours` (`start_hours`,`date`),
  KEY `fk_course_module` (`id_module`),
  KEY `fk_course_timetable` (`id_timetable`),
  CONSTRAINT `fk_course_timetable` FOREIGN KEY (`id_timetable`) REFERENCES `timetables` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modules`
--

DROP TABLE IF EXISTS `modules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modules` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `hours_dispense` int(5) NOT NULL DEFAULT 0,
  `hours_remaining` int(5) NOT NULL,
  `hours_total` int(5) NOT NULL,
  `is_close` tinyint(1) NOT NULL DEFAULT 0,
  `id_class` int(5) NOT NULL,
  `id_teacher` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `fk_module_class` (`id_class`),
  KEY `fk_module_teacher` (`id_teacher`),
  CONSTRAINT `fk_module_class` FOREIGN KEY (`id_class`) REFERENCES `classes` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_module_teacher` FOREIGN KEY (`id_teacher`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modules`
--

LOCK TABLES `modules` WRITE;
/*!40000 ALTER TABLE `modules` DISABLE KEYS */;
/*!40000 ALTER TABLE `modules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timetables`
--

DROP TABLE IF EXISTS `timetables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `timetables` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `start` date NOT NULL,
  `end` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `start` (`start`),
  UNIQUE KEY `end` (`end`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timetables`
--

LOCK TABLES `timetables` WRITE;
/*!40000 ALTER TABLE `timetables` DISABLE KEYS */;
/*!40000 ALTER TABLE `timetables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `phone_code` int(5) NOT NULL,
  `phone_number` int(15) NOT NULL,
  `email` varchar(100) NOT NULL,
  `address` varchar(50) NOT NULL,
  `profile` enum('ADMIN','ASSISTANT','CHIEF','RESPONSIBLE','TEACHER','ACCOUNTANT') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone_number` (`phone_code`,`phone_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES
(1,'admin','pass','John','DOE',221,770000000,'john@exemple.com','Addr','ADMIN'),
(2,'baby','pass','Baby','DOE',0,0,'baby@example.com','Addr','RESPONSIBLE'),
(8,'bien','aze','re','rer',4554,58585,'ree@.com','erre','RESPONSIBLE'),
(15,'nuit','aqw','ajout','ajouter',245,784964,'ajout@.com','mjkgr','CHIEF'),
(18,'test10','tester','test8','TEST8',7,7,'0@email.com','addr','ACCOUNTANT'),
(19,'assistant1','123','Admin2','CRUD',213,487952,'admin2@.com','addr','ASSISTANT'),
(22,'admin5','az','azer','bzer',54874,586455,'11@.','addre','ASSISTANT'),
(24,'acc','p','546486','5468468',545848,84678864,'468@.','z','ACCOUNTANT'),
(26,'admin2','pass','Admin','Admin',546584,4898748,'489@.','uua','ADMIN'),
(28,'chef2','aqw','Chief','chef',120,58492,'@q.c','fred','CHIEF'),
(34,'hjjhc','a','hui','hui',78667,7867,'78678@.','Addr','ACCOUNTANT'),
(38,'878','a','8978','8979',78768,76867,'7686@.','addr','CHIEF'),
(39,'677656','a','78676','7678',76678,6756,'a87768@.','aadr','CHIEF'),
(40,'8979897','a','8978897','978898',9789879,897988,'9787889@.','7899689','ADMIN'),
(41,'89878','a','87897','9788789',879987,7898789,'79778089@.','0790879','ASSISTANT'),
(43,'asst','sss','asss','assss',7877,78787,'cc@.','ccccc','CHIEF'),
(44,'add','add','addd','adddd',67676,76767,'aa@:.','sssss','ADMIN'),
(46,'ddd','ddd','dddd','ddddd',9999,7777,'s@.','dddd','RESPONSIBLE'),
(47,'zeze','zzz','eeee','eeee',9999,6666,'v@.','gggg','ACCOUNTANT'),
(48,'eeee','sss','dede','deded',999,5656,'@.','ssss','CHIEF'),
(53,'ad','ddd','sdsd','sdsds',909,78685785,'@.76867','zezeze','TEACHER'),
(56,'IOUPJI','IOUIO','IOOIU','OPOI',8787,78685785,'76867@.','87987','TEACHER'),
(58,'7889789','a','897878','89797',1,0,'_èçè_ç@.','808','TEACHER'),
(59,'assist','pass','Jane','DOE',89607,8968,'798°7°@.','Addr','ASSISTANT');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-06 21:18:10
