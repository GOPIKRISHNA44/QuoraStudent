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

-- Dumping structure for table quorastudent.answers
DROP TABLE IF EXISTS `answers`;
CREATE TABLE IF NOT EXISTS `answers` (
  `aid` bigint NOT NULL AUTO_INCREMENT,
  `eqid` bigint DEFAULT NULL,
  `doa` datetime DEFAULT NULL,
  `userid` bigint DEFAULT NULL,
  `ctype` varchar(50) DEFAULT NULL,
  `updatedat` datetime DEFAULT NULL,
  `active` int DEFAULT NULL,
  `content` longtext,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.answers: ~0 rows (approximately)
DELETE FROM `answers`;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;

-- Dumping structure for table quorastudent.blog
DROP TABLE IF EXISTS `blog`;
CREATE TABLE IF NOT EXISTS `blog` (
  `bid` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `content` longtext,
  `doblog` datetime DEFAULT NULL,
  `updatedat` datetime DEFAULT NULL,
  `active` int DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.blog: ~0 rows (approximately)
DELETE FROM `blog`;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;

-- Dumping structure for table quorastudent.comments
DROP TABLE IF EXISTS `comments`;
CREATE TABLE IF NOT EXISTS `comments` (
  `cid` bigint NOT NULL AUTO_INCREMENT,
  `cpid` bigint DEFAULT NULL,
  `userid` int DEFAULT NULL,
  `parentid` int NOT NULL DEFAULT '0',
  `ctype` varchar(50) DEFAULT NULL,
  `comment` longtext,
  `doc` datetime DEFAULT NULL,
  `updatedat` datetime DEFAULT NULL,
  `active` int DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.comments: ~1 rows (approximately)
DELETE FROM `comments`;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` (`cid`, `cpid`, `userid`, `parentid`, `ctype`, `comment`, `doc`, `updatedat`, `active`) VALUES
	(3, -1, 11, 2, 'Q', 'through portal !!! ', '2022-07-15 03:19:35', '2022-07-15 03:19:35', 1);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;

-- Dumping structure for table quorastudent.ctype
DROP TABLE IF EXISTS `ctype`;
CREATE TABLE IF NOT EXISTS `ctype` (
  `id` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.ctype: ~4 rows (approximately)
DELETE FROM `ctype`;
/*!40000 ALTER TABLE `ctype` DISABLE KEYS */;
INSERT INTO `ctype` (`id`) VALUES
	('A'),
	('B'),
	('Q'),
	('E');
/*!40000 ALTER TABLE `ctype` ENABLE KEYS */;

-- Dumping structure for table quorastudent.events
DROP TABLE IF EXISTS `events`;
CREATE TABLE IF NOT EXISTS `events` (
  `eid` bigint NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `from` datetime DEFAULT NULL,
  `to` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.events: ~0 rows (approximately)
DELETE FROM `events`;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
/*!40000 ALTER TABLE `events` ENABLE KEYS */;

-- Dumping structure for procedure quorastudent.feed
DROP PROCEDURE IF EXISTS `feed`;
DELIMITER //
CREATE PROCEDURE `feed`(
	IN `unvcode` INT
)
BEGIN

SELECT q.*, u.username,u.avatarid,
 COALESCE(SUM(ld.updwnvt = 1), 0) AS likes,
       COALESCE(SUM(ld.updwnvt = 0), 0) AS dislikes
from questions q 
INNER JOIN userdetails u ON u.userid=q.userid AND u.universitycode =  @unvcode
left JOIN likedislike ld ON ld.ctype = 'q' AND ld.parentid = q.eqid
WHERE q.active=1 AND q.etype = 'q'  GROUP BY q.eqid  ;


END//
DELIMITER ;

-- Dumping structure for table quorastudent.interests
DROP TABLE IF EXISTS `interests`;
CREATE TABLE IF NOT EXISTS `interests` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.interests: ~9 rows (approximately)
DELETE FROM `interests`;
/*!40000 ALTER TABLE `interests` DISABLE KEYS */;
INSERT INTO `interests` (`id`, `name`) VALUES
	(1, 'Sports'),
	(2, 'Maths'),
	(3, 'Education'),
	(4, 'Any'),
	(5, 'Festivals'),
	(6, 'Museums'),
	(7, 'Anthropology'),
	(8, 'Business Administration'),
	(9, 'Pharmacy');
/*!40000 ALTER TABLE `interests` ENABLE KEYS */;

-- Dumping structure for table quorastudent.likedislike
DROP TABLE IF EXISTS `likedislike`;
CREATE TABLE IF NOT EXISTS `likedislike` (
  `id` int NOT NULL AUTO_INCREMENT,
  `parentid` int DEFAULT NULL,
  `updwnvt` int DEFAULT NULL,
  `userid` int DEFAULT NULL,
  `ctype` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `updatedon` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.likedislike: ~3 rows (approximately)
DELETE FROM `likedislike`;
/*!40000 ALTER TABLE `likedislike` DISABLE KEYS */;
INSERT INTO `likedislike` (`id`, `parentid`, `updwnvt`, `userid`, `ctype`, `updatedon`) VALUES
	(1, 3, 1, 6, 'Q', '2022-07-13 06:42:41'),
	(2, 3, 1, 5, 'Q', '2022-07-15 01:51:43'),
	(3, 1, 0, 6, 'Q', NULL);
/*!40000 ALTER TABLE `likedislike` ENABLE KEYS */;

-- Dumping structure for table quorastudent.questions
DROP TABLE IF EXISTS `questions`;
CREATE TABLE IF NOT EXISTS `questions` (
  `eqid` int NOT NULL AUTO_INCREMENT,
  `userid` int DEFAULT NULL,
  `question` longtext,
  `doq` datetime DEFAULT NULL,
  `updatedat` datetime DEFAULT NULL,
  `ctype` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `active` int DEFAULT NULL,
  `tags` longtext,
  PRIMARY KEY (`eqid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.questions: ~4 rows (approximately)
DELETE FROM `questions`;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` (`eqid`, `userid`, `question`, `doq`, `updatedat`, `ctype`, `active`, `tags`) VALUES
	(1, 6, 'How do the college manage marks ', '2022-07-06 06:44:18', '2022-07-06 06:44:18', 'Q', 1, ';1;2;'),
	(2, 6, '<h1>How do the college manage marks</h1> ', '2022-07-06 06:44:55', '2022-07-06 06:44:55', 'Q', 1, ';1;2;3;'),
	(3, 6, '<h1>How do the college manage marks</h1> ', '2022-07-06 06:45:51', '2022-07-06 06:45:51', 'Q', 1, ';1;2;3;'),
	(4, 6, '<p>Hello how </p>', '2022-07-09 11:05:58', '2022-07-09 11:05:58', 'Q', 1, ';1;2;3;');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;

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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.sessiondetails: ~25 rows (approximately)
DELETE FROM `sessiondetails`;
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
	(16, 6, 'WTkpzI+TVwMKJpI0K5cQkqL6Fq19db0yQBnDoOa3xCyL7kozw6jYoNhs7KrGhQB+', '2022-07-04 07:28:24', NULL, 0),
	(17, 6, 'jB8bbldcRqKGoV6v+aBs3maXI1mUwTrV0KX5QYXYPwihOHJ32pJtqIZEef2Fx5gq', '2022-07-06 07:28:23', NULL, 0),
	(18, 9, 'yxGKhZ7jpn7/n/LKljIE/FrmJ1AJWhu63CckpEjFW9zJ/P6Kcl6oeN40SWtd6aY3', '2022-07-06 07:30:38', NULL, 0),
	(19, 10, 'orBNszFz/4XFvhj1aqYzZN+aUzw844gzLBvvixUkLhcSnzEFaAFMdox/9CYdCCSt', '2022-07-06 07:33:13', NULL, 0),
	(20, 11, 'kmMH514ginJdb2Ph92/HQCZ3ZxPLiBwbs0EM2wxxPwITDic8r99r0OLs87ut4rH7', '2022-07-06 07:37:00', NULL, 0),
	(21, 11, 'fYqE/SnWkbeAb2M3NzeHGs+QF6Kdf/xeUcH5wHPt02KF6xbLORZSbRld55OmXn4W', '2022-07-06 07:37:37', NULL, 0),
	(22, 6, '0AQ1qCQfaqeAO9TpdtU5f0H1sTIVqfuBrmKBIUzJxFxem96KPKeV1I/MotGQTmjg', '2022-07-08 01:22:07', NULL, 0),
	(23, 6, 't636Bi/D2GQKqI4ZZw83dyyTEjeKvZxwGYuvmHOO58b+xqovBxi1DYuWRLv8VgST', '2022-07-08 04:03:34', NULL, 0),
	(24, 6, '9Xw6f/dpH6DRAsx7XdKNKE5Ciqx4QlGVCAjr3iPJ+3Qnvynz8nmQlPoGlscjsWRu', '2022-07-08 04:04:00', NULL, 0),
	(25, 6, 'RolMI9fShMxPeHKOvrOb7ZExa98ZPdM8gNrj4rLN5BGfLWDyqsBOoFnJpzqbT5ZQ', '2022-07-09 11:05:27', NULL, 0),
	(26, 6, 't3qnIx67/ujUjMxj7jhOTVPuD+y30OOwhtMxpWb0bQY7ZxfkTMgFG9mDhUZoGgdf', '2022-07-09 11:07:59', NULL, 0),
	(27, 6, '174Zuhn/uao1fTMiQfDv7636g749dSjb+BAWdHPGwfNctVzNke6pJW/od6wJSKGO', '2022-07-11 00:22:03', NULL, 0),
	(28, 6, 'ZRSjekOm5X3OlbPP3BW5fL7DSd0xKuWVF0Uiwx1c+Rb/99smWQqyj+ptZNz4yRvZ', '2022-07-11 00:22:03', NULL, 0),
	(29, 6, '8KQTqvssBqN8c01WXdAKKzsQHSza37AWq0CZ4xa1dZrjyVOyZZpL32XPNPdy5W/x', '2022-07-16 11:29:23', NULL, 0),
	(30, 6, 'lN3Z9cDQ290PsyDFW8mwo6OCZFVZDQHkPrU+0Ajm93wbZi7PAsdx4flTgRtKcicE', '2022-07-18 09:29:28', NULL, 0),
	(31, 6, 'WQ1RCbFI0DtQACB280KnGHDHtSw1R01ayKVj+aL/LHoXanpHDri5vECWVT8X7aCt', '2022-07-18 09:29:42', NULL, 0);
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

-- Dumping data for table quorastudent.universities: ~4 rows (approximately)
DELETE FROM `universities`;
/*!40000 ALTER TABLE `universities` DISABLE KEYS */;
INSERT INTO `universities` (`unvcode`, `unvname`, `unvaddress`, `unvmobile`) VALUES
	('89', 'Cardiff University', 'Cardiff University', '7890'),
	('92', 'Abertay University', 'Abertay University', '1234'),
	('93', 'Lecis University', 'Lecis University', '3456'),
	('94', 'University of Kentucky', 'University of Kentucky', '6789');
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
  `avatarid` int DEFAULT '1',
  PRIMARY KEY (`userid`) USING BTREE,
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `emailId` (`emailid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.userdetails: ~2 rows (approximately)
DELETE FROM `userdetails`;
/*!40000 ALTER TABLE `userdetails` DISABLE KEYS */;
INSERT INTO `userdetails` (`userid`, `username`, `emailid`, `password`, `dob`, `universitycode`, `interestspopup`, `avatarid`) VALUES
	(6, 'vamsi', 'vamsi@gmail.com', 'zEhq/Nf9IuuDN3XWL+o54A==', '1997-07-22', '92', 1, 1),
	(11, 'vamsiirr', 'vamsoir9rr@gmail.com', 'kC8y7t0ahpecEzfu7i351w==', '1997-07-22', '92', 0, 1);
/*!40000 ALTER TABLE `userdetails` ENABLE KEYS */;

-- Dumping structure for table quorastudent.userinterests
DROP TABLE IF EXISTS `userinterests`;
CREATE TABLE IF NOT EXISTS `userinterests` (
  `userid` int NOT NULL,
  `interests` longtext,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.userinterests: ~1 rows (approximately)
DELETE FROM `userinterests`;
/*!40000 ALTER TABLE `userinterests` DISABLE KEYS */;
INSERT INTO `userinterests` (`userid`, `interests`) VALUES
	(6, NULL);
/*!40000 ALTER TABLE `userinterests` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
