-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: shape
-- ------------------------------------------------------
-- Server version	5.7.9-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `measure`
--

DROP TABLE IF EXISTS `measure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `measure` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `nqf_id` varchar(255) DEFAULT NULL,
  `cms_id` varchar(255) DEFAULT NULL,
  `numerator_description` varchar(255) DEFAULT NULL,
  `denominator_description` varchar(255) DEFAULT NULL,
  `exclusions_description` varchar(255) DEFAULT NULL,
  `well_controlled_numerator` tinyint(1) DEFAULT NULL,
  `selected` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `measure`
--

LOCK TABLES `measure` WRITE;
/*!40000 ALTER TABLE `measure` DISABLE KEYS */;
/*!40000 ALTER TABLE `measure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization`
--

DROP TABLE IF EXISTS `organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organization` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `address_street` varchar(255) DEFAULT NULL,
  `address_state` varchar(45) DEFAULT NULL,
  `address_city` varchar(45) DEFAULT NULL,
  `address_zip` int(11) DEFAULT NULL,
  `phone` int(15) DEFAULT NULL,
  `primary_contact_name` varchar(45) DEFAULT NULL,
  `primary_contact_email` varchar(45) DEFAULT NULL,
  `primary_contact_role` varchar(45) DEFAULT NULL,
  `primary_contact_phone` int(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization`
--

LOCK TABLES `organization` WRITE;
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization_measure`
--

DROP TABLE IF EXISTS `organization_measure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organization_measure` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `organization_id` INT NOT NULL,
  `numerator_value` int(11) DEFAULT NULL,
  `denominator_value` int(11) DEFAULT NULL,
  `RP_date` varchar(255) DEFAULT NULL,
  `gender_male` int(11) DEFAULT NULL,
  `gender_female` int(11) DEFAULT NULL,
  `gender_other` int(11) DEFAULT NULL,
  `age_under_17` int(11) DEFAULT NULL,
  `age_18_44` int(11) DEFAULT NULL,
  `age_45_64` int(11) DEFAULT NULL,
  `age_over_65` int(11) DEFAULT NULL,
  `ethnicity_hispanic_latino` int(11) DEFAULT NULL,
  `ethnicity_not_hispanic_latino` int(11) DEFAULT NULL,
  `race_african_american` int(11) DEFAULT NULL,
  `race_american_indian` int(11) DEFAULT NULL,
  `race_asian` int(11) DEFAULT NULL,
  `race_native_hawaiian` int(11) DEFAULT NULL,
  `race_white` int(11) DEFAULT NULL,
  `race_other` int(11) DEFAULT NULL,
  `measure_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `organization_measure_organization_idx` (`organization_id`),
  KEY `organization_measure_measure_idx` (`measure_id`),
  CONSTRAINT `organization_measure_measure` FOREIGN KEY (`measure_id`) REFERENCES `measure` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `organization_measure_organization` FOREIGN KEY (`organization_id`) REFERENCES `organization` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization_measure`
--

LOCK TABLES `organization_measure` WRITE;
/*!40000 ALTER TABLE `organization_measure` DISABLE KEYS */;
/*!40000 ALTER TABLE `organization_measure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization_stratification`
--

DROP TABLE IF EXISTS `organization_stratification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organization_stratification` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `organization_id` INT NOT NULL,
  `gender_male` int(11) DEFAULT NULL,
  `gender_female` int(11) DEFAULT NULL,
  `gender_other` int(11) DEFAULT NULL,
  `age_under_17` int(11) DEFAULT NULL,
  `age_18-44` int(11) DEFAULT NULL,
  `age_45-64` int(11) DEFAULT NULL,
  `age_over_65` int(11) DEFAULT NULL,
  `ethnicity_hispanic_latino` int(11) DEFAULT NULL,
  `ethnicity_not_hispanic_latino` int(11) DEFAULT NULL,
  `race_african_american` int(11) DEFAULT NULL,
  `race_american_indian` int(11) DEFAULT NULL,
  `race_asian` int(11) DEFAULT NULL,
  `race_native_hawaiian` int(11) DEFAULT NULL,
  `race_white` int(11) DEFAULT NULL,
  `race_other` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `organization_stratification_organization_idx` (`organization_id`),
  CONSTRAINT `organization_stratification_organization` FOREIGN KEY (`organization_id`) REFERENCES `organization` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization_stratification`
--

LOCK TABLES `organization_stratification` WRITE;
/*!40000 ALTER TABLE `organization_stratification` DISABLE KEYS */;
/*!40000 ALTER TABLE `organization_stratification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provider`
--

DROP TABLE IF EXISTS `provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provider` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `npi` int(15) DEFAULT NULL,
  `organization_id` INT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider`
--

LOCK TABLES `provider` WRITE;
/*!40000 ALTER TABLE `provider` DISABLE KEYS */;
/*!40000 ALTER TABLE `provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provider_measure`
--

DROP TABLE IF EXISTS `provider_measure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provider_measure` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `provider_id` INT NOT NULL,
  `measure_id` INT NOT NULL,
  `numerator_value` int(11) DEFAULT NULL,
  `denominator_value` int(11) DEFAULT NULL,
  `RP_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `provider_measure_provider_idx` (`provider_id`),
  KEY `provider_measure_measure_idx` (`measure_id`),
  CONSTRAINT `provider_measure_measure` FOREIGN KEY (`measure_id`) REFERENCES `measure` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `provider_measure_provider` FOREIGN KEY (`provider_id`) REFERENCES `user_organization` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider_measure`
--

LOCK TABLES `provider_measure` WRITE;
/*!40000 ALTER TABLE `provider_measure` DISABLE KEYS */;
/*!40000 ALTER TABLE `provider_measure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `role` varchar(45) DEFAULT NULL,
  `admin` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_organization`
--

DROP TABLE IF EXISTS `user_organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_organization` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL,
  `organization_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `organization_id1_idx` (`organization_id`),
  KEY `user_organziation_user_idx` (`user_id`),
  CONSTRAINT `user_organization_organization` FOREIGN KEY (`organization_id`) REFERENCES `organization` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_organziation_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_organization`
--

LOCK TABLES `user_organization` WRITE;
/*!40000 ALTER TABLE `user_organization` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_provider`
--

DROP TABLE IF EXISTS `user_provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_provider` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL,
  `provider_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_provider_provider_idx` (`provider_id`),
  KEY `user_provider_user_idx` (`user_id`),
  CONSTRAINT `user_provider_provider` FOREIGN KEY (`provider_id`) REFERENCES `provider` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_provider_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_provider`
--

LOCK TABLES `user_provider` WRITE;
/*!40000 ALTER TABLE `user_provider` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_provider` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-28 11:22:06
