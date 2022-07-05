-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.23 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for quorastudent
DROP DATABASE IF EXISTS `quorastudent`;
CREATE DATABASE IF NOT EXISTS `quorastudent` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `quorastudent`;

-- Dumping structure for table quorastudent.hibernate_sequence
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.hibernate_sequence: ~1 rows (approximately)
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(7);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Dumping structure for table quorastudent.interests
DROP TABLE IF EXISTS `interests`;
CREATE TABLE IF NOT EXISTS `interests` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.interests: ~3 rows (approximately)
/*!40000 ALTER TABLE `interests` DISABLE KEYS */;
INSERT INTO `interests` (`id`, `name`) VALUES
	(1, 'sports'),
	(2, 'maths'),
	(3, 'education');
/*!40000 ALTER TABLE `interests` ENABLE KEYS */;

-- Dumping structure for table quorastudent.sessiondetails
DROP TABLE IF EXISTS `sessiondetails`;
CREATE TABLE IF NOT EXISTS `sessiondetails` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userid` int DEFAULT NULL,
  `sessionkey` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `loggedinat` datetime DEFAULT NULL,
  `loggedoutat` datetime DEFAULT NULL,
  `active` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.sessiondetails: ~10 rows (approximately)
/*!40000 ALTER TABLE `sessiondetails` DISABLE KEYS */;
INSERT INTO `sessiondetails` (`id`, `userid`, `sessionkey`, `loggedinat`, `loggedoutat`, `active`) VALUES
	(7, 5, 'CaewSLVQXk+AB7I634sn7IW/DA4DAoPldw4PmrjKHRftyvbzYzMB/daYaNqENpTW', '2022-06-28 11:46:26', '2022-06-29 12:15:12', 0),
	(8, 6, 'UBv4i4Hpq+fgbW9Duzz5HnX1gtMWExUV5wXaaYmNIPfOz7lxUGzguGSd4QvMY7ja', '2022-06-28 00:17:43', '2022-06-29 00:15:04', 0),
	(9, 6, '1Wzq3Qjj/pSG7HTQMkkhY54LeB/ClSd39sp9Ru9S9SvvJjpauqdjsR4tiJGzjIVR', '2022-07-01 00:45:20', NULL, 0),
	(10, 6, 'MxD1Huz6b72YhzLwAg6YeD0k7zQow7F6rY2TE8mP5M58O2YRRuPzGvPWxKYoIQ/E', '2022-07-01 00:46:19', NULL, 0),
	(11, 6, 'WVXbeWHsQgU1RxNA5JtT4Des6oSVNFbylXMCw8I4NT1WfsidV4Tz2Cp04b8MRYrC', '2022-07-04 03:34:47', NULL, 0),
	(12, 6, 'pVM73qEMtJWHNtMPm0vVz4/LhcqKVoyt21Y6bagEME8NNoSIdNqq35x9+q5ZbagA', '2022-07-04 03:35:18', NULL, 0),
	(13, 6, 'f8QMSwlOcIEFqnTtKnRRXk8xAkCQv7OVMXN050+pkIqYvURvk3TJP3sC0X959qsc', '2022-07-04 06:51:05', NULL, 0),
	(14, 6, 'Nip98Z0Yz1YUKHlPKcH6OvyP+MLun7ha3yaaR1+KvDjzqzylhA0WgYFGwKwpED4b', '2022-07-04 07:15:46', NULL, 0),
	(15, 6, '9o92FfL9IIpQxX52JibSUcgmini7aFrDxuBhSNexUbUKNIi536wEsYHArFX3Wy7u', '2022-07-04 07:18:18', NULL, 0),
	(16, 6, 'WTkpzI+TVwMKJpI0K5cQkqL6Fq19db0yQBnDoOa3xCyL7kozw6jYoNhs7KrGhQB+', '2022-07-04 07:28:24', NULL, 0);
/*!40000 ALTER TABLE `sessiondetails` ENABLE KEYS */;

-- Dumping structure for table quorastudent.universities
DROP TABLE IF EXISTS `universities`;
CREATE TABLE IF NOT EXISTS `universities` (
  `unvcode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '00',
  `unvname` varchar(200) DEFAULT NULL,
  `unvaddress` varchar(1000) DEFAULT NULL,
  `unvmobile` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`unvcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.universities: ~1 rows (approximately)
/*!40000 ALTER TABLE `universities` DISABLE KEYS */;
INSERT INTO `universities` (`unvcode`, `unvname`, `unvaddress`, `unvmobile`) VALUES
	('92', 'Abertay University', 'Abertay University', '1234');
/*!40000 ALTER TABLE `universities` ENABLE KEYS */;

-- Dumping structure for table quorastudent.userdetails
DROP TABLE IF EXISTS `userdetails`;
CREATE TABLE IF NOT EXISTS `userdetails` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(200) DEFAULT NULL,
  `emailid` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(1000) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `universitycode` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `interestspopup` int DEFAULT '0',
  PRIMARY KEY (`userid`) USING BTREE,
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `emailId` (`emailid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.userdetails: ~3 rows (approximately)
/*!40000 ALTER TABLE `userdetails` DISABLE KEYS */;
INSERT INTO `userdetails` (`userid`, `username`, `emailid`, `password`, `dob`, `universitycode`, `interestspopup`) VALUES
	(5, 'goppp', 'aisd@gm.com', 'rYl+saNY1QvRu/HK65BIaQ==', '1997-07-24', '01', NULL),
	(6, 'vamsi', 'vamsi@gmail.com', 'zEhq/Nf9IuuDN3XWL+o54A==', '1997-07-22', '92', NULL),
	(7, 'vamsoi', NULL, 'T4s7sw3gkHMtN8n4Z5hxOA==', '1997-07-22', NULL, NULL);
/*!40000 ALTER TABLE `userdetails` ENABLE KEYS */;

-- Dumping structure for table quorastudent.userinterests
DROP TABLE IF EXISTS `userinterests`;
CREATE TABLE IF NOT EXISTS `userinterests` (
  `userid` int NOT NULL,
  `interests` longtext,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.userinterests: ~1 rows (approximately)
/*!40000 ALTER TABLE `userinterests` DISABLE KEYS */;
INSERT INTO `userinterests` (`userid`, `interests`) VALUES
	(6, '1;3;');
/*!40000 ALTER TABLE `userinterests` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
