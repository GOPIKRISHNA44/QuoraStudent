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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.answers: ~4 rows (approximately)
DELETE FROM `answers`;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` (`aid`, `eqid`, `doa`, `userid`, `ctype`, `updatedat`, `active`, `content`) VALUES
	(3, 3, '2022-07-27 07:50:06', 11, 'Q', '2022-07-27 07:50:06', 1, 'dont know :) how '),
	(5, 1, '2022-08-12 05:23:58', 11, 'Q', '2022-08-12 05:23:58', 1, '<p>hi</p><p><br></p>'),
	(6, 2, '2022-08-12 07:34:53', 11, 'Q', '2022-08-12 07:34:53', 1, '<p>They uplaod in the eportal <a href="https://github.com/" target="_blank">https://github.com/</a></p>'),
	(7, 10, '2022-08-12 08:39:04', 11, 'E', '2022-08-12 08:39:04', 1, '<p>GDRG</p>'),
	(8, 64, '2022-08-14 01:54:38', 21, 'Q', '2022-08-14 01:54:38', 1, '<p><span style="color: rgb(60, 60, 60);">From themain campus to throughout the city, we know that Leicester will offer you a vibrant and unique setting to spend the coming years. This blend of a&nbsp;</span><a href="https://www.theguardian.com/education/ng-interactive/2021/sep/11/the-best-uk-universities-2022-rankings" target="_blank" style="color: rgb(32, 126, 164); background-color: rgb(255, 255, 255);">Top 30 university university</a><span style="color: rgb(60, 60, 60);">&nbsp;campus life and bustling student life in Leicester city itself, makes us one of the best university campuses for growth and change.</span></p>');
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
  `userid` bigint DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.blog: ~3 rows (approximately)
DELETE FROM `blog`;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` (`bid`, `title`, `content`, `doblog`, `updatedat`, `active`, `userid`) VALUES
	(1, 'How to crabs crawl', 'e were making a sand castle on the Wonderopolis beach the other day when we saw a group of crabs gathering nearby. The group appeared to consist of an adult crab and several younger crabs. Our curiosity got the best of us, so we decided to eavesdrop to try to hear what they were talking about. Kid Crab: Mrs. Crab? What are we going to learn about in class today?Mrs. Crab: Well, Patty, I\'m so glad you asked. Today, we\'re going to learn why human beings walk frontways.Kid Crab: That\'s awesome, Mrs. Crab! I\'ve always WONDERed about that. They look so funny when they walk down the beach like that!Apparently, we stumbled upon a class of crabs with their teacher about to learn more about human beings and the way we move. From their perspective, we must look as funny to crabs as they do to us. Let\'s take a closer look at why crabs move the way they do.If you\'ve ever seen crabs moving along the shoreline, you\'ve probably noticed that they move differently than human beings. Instead of walking forward on two legs, they move quickly sideways in a flurry with their multiple legs.Crabs move sideways for the same reason humans move forward: that\'s how their bodies were built to move. Human beings have knees that bend forward. Therefore, it\'s only natural that we would move forward with each step we take.Crabs, on the other hand, have multiple legs and they\'re located on the sides of their bodies. In addition, their leg joints bend outward. Given this particular design of their bodies, it\'s most efficient and natural for crabs to move sideways.Not all crabs move sideways, however. There are a few species of crabs that can move forward, because their bodies are shaped in a way that makes them longer than they are wide. Examples of these crabs include raninids, Libinia emarginata, and Mictyris platycheles.In addition, most crabs have a front pair of legs that are mainly used for defense and grasping food. These front legs can be used to move forward, but doing so is not very efficient and doesn\'t allow the crab to move quickly. Thus, most crabs move sideways since they can move quickly and most efficiently that way.', '2022-07-27 07:32:17', '2022-07-27 07:32:17', 1, NULL),
	(2, 'How to crabs crawl', 'e were making a sand castle on the Wonderopolis beach the other day when we saw a group of crabs gathering nearby. The group appeared to consist of an adult crab and several younger crabs. Our curiosity got the best of us, so we decided to eavesdrop to try to hear what they were talking about. Kid Crab: Mrs. Crab? What are we going to learn about in class today?Mrs. Crab: Well, Patty, I\'m so glad you asked. Today, we\'re going to learn why human beings walk frontways.Kid Crab: That\'s awesome, Mrs. Crab! I\'ve always WONDERed about that. They look so funny when they walk down the beach like that!Apparently, we stumbled upon a class of crabs with their teacher about to learn more about human beings and the way we move. From their perspective, we must look as funny to crabs as they do to us. Let\'s take a closer look at why crabs move the way they do.If you\'ve ever seen crabs moving along the shoreline, you\'ve probably noticed that they move differently than human beings. Instead of walking forward on two legs, they move quickly sideways in a flurry with their multiple legs.Crabs move sideways for the same reason humans move forward: that\'s how their bodies were built to move. Human beings have knees that bend forward. Therefore, it\'s only natural that we would move forward with each step we take.Crabs, on the other hand, have multiple legs and they\'re located on the sides of their bodies. In addition, their leg joints bend outward. Given this particular design of their bodies, it\'s most efficient and natural for crabs to move sideways.Not all crabs move sideways, however. There are a few species of crabs that can move forward, because their bodies are shaped in a way that makes them longer than they are wide. Examples of these crabs include raninids, Libinia emarginata, and Mictyris platycheles.In addition, most crabs have a front pair of legs that are mainly used for defense and grasping food. These front legs can be used to move forward, but doing so is not very efficient and doesn\'t allow the crab to move quickly. Thus, most crabs move sideways since they can move quickly and most efficiently that way.', '2022-07-31 02:33:06', '2022-07-31 02:33:06', 1, NULL),
	(3, 'How to crabs crawl', 'e were making a sand castle on the Wonderopolis beach the other day when we saw a group of crabs gathering nearby. The group appeared to consist of an adult crab and several younger crabs. Our curiosity got the best of us, so we decided to eavesdrop to try to hear what they were talking about. Kid Crab: Mrs. Crab? What are we going to learn about in class today?Mrs. Crab: Well, Patty, I\'m so glad you asked. Today, we\'re going to learn why human beings walk frontways.Kid Crab: That\'s awesome, Mrs. Crab! I\'ve always WONDERed about that. They look so funny when they walk down the beach like that!Apparently, we stumbled upon a class of crabs with their teacher about to learn more about human beings and the way we move. From their perspective, we must look as funny to crabs as they do to us. Let\'s take a closer look at why crabs move the way they do.If you\'ve ever seen crabs moving along the shoreline, you\'ve probably noticed that they move differently than human beings. Instead of walking forward on two legs, they move quickly sideways in a flurry with their multiple legs.Crabs move sideways for the same reason humans move forward: that\'s how their bodies were built to move. Human beings have knees that bend forward. Therefore, it\'s only natural that we would move forward with each step we take.Crabs, on the other hand, have multiple legs and they\'re located on the sides of their bodies. In addition, their leg joints bend outward. Given this particular design of their bodies, it\'s most efficient and natural for crabs to move sideways.Not all crabs move sideways, however. There are a few species of crabs that can move forward, because their bodies are shaped in a way that makes them longer than they are wide. Examples of these crabs include raninids, Libinia emarginata, and Mictyris platycheles.In addition, most crabs have a front pair of legs that are mainly used for defense and grasping food. These front legs can be used to move forward, but doing so is not very efficient and doesn\'t allow the crab to move quickly. Thus, most crabs move sideways since they can move quickly and most efficiently that way.', '2022-07-31 02:45:35', '2022-07-31 02:45:35', 1, 2);
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.comments: ~8 rows (approximately)
DELETE FROM `comments`;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
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

-- Dumping structure for table quorastudent.emailactivator
DROP TABLE IF EXISTS `emailactivator`;
CREATE TABLE IF NOT EXISTS `emailactivator` (
  `emailid` varchar(50) DEFAULT NULL,
  `emailactivatingtext` longtext,
  `id` int NOT NULL AUTO_INCREMENT,
  `createdat` datetime DEFAULT NULL,
  `userid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.emailactivator: ~1 rows (approximately)
DELETE FROM `emailactivator`;
/*!40000 ALTER TABLE `emailactivator` DISABLE KEYS */;
INSERT INTO `emailactivator` (`emailid`, `emailactivatingtext`, `id`, `createdat`, `userid`) VALUES
	('akhill@g.com', 'X2iwT3FeX2XewLN1KBwb79stG28tiQkzeTIuifhWgVgiDFGcfktegKkH3VciLf6iSAhCfhtHugtRwffLXezSLHVKefYSEgTNLgYe6YtuMeggHu67sA7JszNifFetLbXd5eO7iXdheL9i3MHigugwAfUFu6dzgfww6GTfbufgsu431SsdfWLhRfk8LYtd25UeatYdKYUU', 6, '2022-08-14 00:16:00', 19),
	('liger@test.com', 'HBSgsObdwf5iCACwHg2QeDugfFuKwfiLFFcuiibffbHPBf3gL56KAFSauAG8Qeu8whKewfAZeQtMbTDA4XiIiEWfQVTZ16Oikf638eDIiYhuGwXfuNh5kV9gfQ8WfHCCUELAWPEQ3Fd3kg9e66e7WSuIkiCiiRJtUAeB3fButfaABbkwROisiRwgIBtWNcwzNGgWa6e1', 7, '2022-08-14 01:05:18', 20),
	('anveshnani40@gmail.com', 'g9O9cuRbf4wicHtEBZU6AaSzLeuiCfUz1u5VK4seia5IGgfcwNGAAHCege3eDIeQW2UegW3e1DzKKiceiMRaViu9BMRZ6fQKAu9uVeeEgf7ieeWVt3SwZLYbfUQs4w43EiG3YIfgeVzJRFgHWJXt95uzfT1ze8S2gTgikNwfs8kiQsSP5ga5ukeYZYVikIw1LgBegCiw', 8, '2022-08-14 01:49:00', 21),
	('Prerana@gmail.com', 'EMu4KkRQgEuugWwHuuzubDgLw4wiKEDeNgQGkbTKO42aw36Ni45XPGYQICY2Wzw6iei2tgctfVtgVeTtNfkeigd7wZtK6DXBFFCS3VRwdDK2u5gz2IJ7zKJQEYwtCeeFwEQHciW9IHDFRfEfNg7A3Ji9YM8Ed4eeANaR2igRGJH5Xeftg5eB4eC66t6I8RJ9zCYaiZGc', 9, '2022-08-14 01:50:34', 22);
/*!40000 ALTER TABLE `emailactivator` ENABLE KEYS */;

-- Dumping structure for table quorastudent.events
DROP TABLE IF EXISTS `events`;
CREATE TABLE IF NOT EXISTS `events` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `eid` bigint NOT NULL,
  `fromdate` datetime DEFAULT NULL,
  `todate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.events: ~3 rows (approximately)
DELETE FROM `events`;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` (`id`, `eid`, `fromdate`, `todate`) VALUES
	(2, 10, '2020-08-07 19:00:00', '2020-09-06 19:00:00'),
	(3, 14, '2022-08-10 20:45:27', '2022-08-17 00:00:00'),
	(4, 16, '2022-08-10 20:47:53', '2022-08-22 00:00:00'),
	(5, 43, '2022-08-14 11:22:04', NULL);
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

-- Dumping data for table quorastudent.interests: ~8 rows (approximately)
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='parentid can be of question,answer,blog,comment,entity';

-- Dumping data for table quorastudent.likedislike: ~16 rows (approximately)
DELETE FROM `likedislike`;
/*!40000 ALTER TABLE `likedislike` DISABLE KEYS */;
/*!40000 ALTER TABLE `likedislike` ENABLE KEYS */;

-- Dumping structure for table quorastudent.notifications
DROP TABLE IF EXISTS `notifications`;
CREATE TABLE IF NOT EXISTS `notifications` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `notifhtmltext` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `userid` bigint DEFAULT NULL,
  `createdat` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.notifications: ~0 rows (approximately)
DELETE FROM `notifications`;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;

-- Dumping structure for table quorastudent.passwordreset
DROP TABLE IF EXISTS `passwordreset`;
CREATE TABLE IF NOT EXISTS `passwordreset` (
  `id` int NOT NULL AUTO_INCREMENT,
  `emailid` varchar(50) DEFAULT NULL,
  `passwordresetlink` longtext,
  `createdat` datetime DEFAULT NULL,
  `active` int DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.passwordreset: ~1 rows (approximately)
DELETE FROM `passwordreset`;
/*!40000 ALTER TABLE `passwordreset` DISABLE KEYS */;
INSERT INTO `passwordreset` (`id`, `emailid`, `passwordresetlink`, `createdat`, `active`) VALUES
	(4, 'bhargav.gandham44@gmail.com', 'gKWbfDk5bzIgeeAcBuLA2f7uGfXeWKViutX5KBiVRLR5LV1IsDIQ9ize5i2cuiwVbMcCu8UfUYhFPZQFBNiu5ePeitI9EQigee1uUOuueWUOiCUL29ifeNQQYHgeu4DaUeu6I5KuCBHXJeCYCIGg5Sts2WzKei8iFwWURdEIDJdWgZ6Zgf7eYIKddiSieFaeeeWW1iz7', '2022-08-13 10:11:35', 1);
/*!40000 ALTER TABLE `passwordreset` ENABLE KEYS */;

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
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.questions: ~11 rows (approximately)
DELETE FROM `questions`;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` (`eqid`, `userid`, `question`, `doq`, `updatedat`, `ctype`, `active`, `tags`) VALUES
	(64, 22, '<p>What is the campus culture and environment at <a href="https://le.ac.uk/" target="_blank">University of Leicester</a>?</p><p><br></p>', '2022-08-14 01:52:01', '2022-08-14 01:52:01', 'Q', 1, ';;');
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
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.sessiondetails: ~36 rows (approximately)
DELETE FROM `sessiondetails`;
/*!40000 ALTER TABLE `sessiondetails` DISABLE KEYS */;
INSERT INTO `sessiondetails` (`id`, `userid`, `sessionkey`, `loggedinat`, `loggedoutat`, `active`) VALUES
	(127, 19, 'pXu59qe8UMc8RdwsX6T9IjJG+sb9e/TcpFq5enyU30Yb+R4xwR1JMLcSjR+Hj2qo', '2022-08-14 00:03:03', '2022-08-14 00:24:58', 0),
	(128, 20, '1devcqOIV0msCc+WOjB4sCzogpqRX51cHEEEDm/ZpFRhkgfrf2HTwEIw3kuRmRrr', '2022-08-14 01:05:41', '2022-08-14 01:05:53', 0),
	(129, 20, 'N2uuRFo209gkiTyKY5ETcTWtBCqhID02ramK0+j1gok0NA7r3eMdLFUYlASaxLu7', '2022-08-14 01:21:03', '2022-08-14 01:21:05', 0),
	(130, 20, 'zjDt88sxjgq3Ac6eMa98FO/KCr63drK9AOtH7q7/HuaMISh1q8TH8/5HWDYleAy3', '2022-08-14 01:43:02', '2022-08-14 01:48:20', 0),
	(131, 21, 'k3lP7jAZMEAAGQLA8NkcQvbL/y9zff0xfbKdnqhC1uGsN3zBro0mxqFkXhaymwM8', '2022-08-14 01:49:10', NULL, 1),
	(132, 22, 'r3CAc8zTL1v5e/Am9oNWG2jhr9uWRSSMCHOUs/DmbSW2+VDpxG5e6VqtT3+PHzrJ', '2022-08-14 01:50:38', NULL, 1);
/*!40000 ALTER TABLE `sessiondetails` ENABLE KEYS */;

-- Dumping structure for table quorastudent.universities
DROP TABLE IF EXISTS `universities`;
CREATE TABLE IF NOT EXISTS `universities` (
  `unvcode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '00',
  `unvname` varchar(200) DEFAULT NULL,
  `unvaddress` varchar(1000) DEFAULT NULL,
  `unvmobile` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`unvcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.universities: ~4 rows (approximately)
DELETE FROM `universities`;
/*!40000 ALTER TABLE `universities` DISABLE KEYS */;
INSERT INTO `universities` (`unvcode`, `unvname`, `unvaddress`, `unvmobile`) VALUES
	('1', 'University of Oxford', 'Oxford OX1 2JD, United Kingdom', '+44 1865 270000\r\n'),
	('10', 'Liverpool John Moores University', '70 Mount Pleasant, Merseyside L3 5UX, United Kingdom', '+44 151 231 2121\r\n'),
	('11', 'University of Hull', 'Cottingham Rd, Hull HU6 7RX, United Kingdom', '+44 1482 346311\r\n'),
	('12', 'University of the West of England', 'UWE Bristol - Frenchay Campus, Coldharbour Ln, Bristol BS16 1QY, United Kingdom', '+44 117 965 6261\r\n'),
	('13', 'Edge Hill University', ' St Helens Rd, Ormskirk L39 4QP, United Kingdom', '+44 1695 575171\r\n'),
	('14', 'University Of Sheffield', 'Sheffield S10 2TN, United Kingdom', '+44 114 222 2000\r\n'),
	('15', 'University of Essex', 'Wivenhoe Park, Colchester CO4 3SQ, United Kingdom', ' +44 1206 873333\r\n'),
	('16', 'Aston University', 'Aston St, Birmingham B4 7ET, United Kingdom', '+44 121 204 3000\r\n'),
	('17', 'De Montfort University', 'Gateway House, Leicester LE1 9BH, United Kingdom', '+44 116 255 1551\r\n'),
	('18', 'University of Kent', 'Giles Ln, Canterbury CT2 7NZ, United Kingdom', '+44 1227 764000\r\n'),
	('19', 'University of York', 'Heslington, York YO10 5DD, United Kingdom', '+44 1904 320000\r\n'),
	('2', 'University of Cambridge', 'The Old Schools, Trinity Ln, Cambridge CB2 1TN, United Kingdom', '+44 1223 337733\r\n'),
	('20', 'University of Edinburgh', 'Old College, South Bridge, Edinburgh EH8 9YL, United Kingdom', '+44 131 650 1000\r\n'),
	('21', 'University of Leicester', 'University Rd, Leicester LE1 7RH, United Kingdom', ' +44 116 252 2522'),
	('3', 'University of Birmingham', 'Birmingham, United Kingdom', '+44 121 414 3344\r\n'),
	('4', 'University of Bristol', 'Bristol BS8 1TH, United Kingdom', '+44 120 414 3344\r\n'),
	('5', 'Imperial College London', 'Exhibition Rd, South Kensington, London SW7 2BX, United Kingdom', '+44 20 7589 5111\r\n'),
	('6', 'University of Manchester', 'Oxford Rd, Manchester M13 9PL, United Kingdom', '+44 161 306 6000\r\n'),
	('7', 'University of Bristol', 'Bristol BS8 1TH, United Kingdom', '+44 117 928 9000\r\n'),
	('8', 'Durham University', 'Durham, United Kingdom', '+44 191 334 2000\r\n'),
	('9', 'University of Leeds', 'Woodhouse, Leeds LS2 9JT, United Kingdom', '+44 113 243 1751\r\n');
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
  `emailidactivated` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`userid`) USING BTREE,
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `emailId` (`emailid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.userdetails: ~5 rows (approximately)
DELETE FROM `userdetails`;
/*!40000 ALTER TABLE `userdetails` DISABLE KEYS */;
INSERT INTO `userdetails` (`userid`, `username`, `emailid`, `password`, `dob`, `universitycode`, `interestspopup`, `avatarid`, `emailidactivated`) VALUES
	(6, 'vamsi', 'vamsi@gmail.com', 'zEhq/Nf9IuuDN3XWL+o54A==', '1997-07-22', '2', 1, 1, 0),
	(11, 'gopik', 'vamsoir9rr@gmail.com', 'zEhq/Nf9IuuDN3XWL+o54A==', '1997-07-22', '2', 1, 5, 0),
	(12, 'Gopikrishna', 'gopi@gmail.com', '1HLV5GltQFw8y2Gj3So4aA==', '2022-08-08', '1', 1, 1, 0),
	(13, 'test', 'test@gmail.com', '3zqM0lfh/hQPAsCX6L4M4w==', '2022-08-17', '2', 1, 1, 0),
	(18, 'bhargav', 'bhargav.gandham44@gmail.com', '3zqM0lfh/hQPAsCX6L4M4w==', '2022-08-11', '2', 1, 1, 0),
	(19, 'akhil', 'akhill@g.com', '3zqM0lfh/hQPAsCX6L4M4w==', '2022-08-09', '2', 1, 1, 0),
	(20, 'liger', 'liger@test.com', '3zqM0lfh/hQPAsCX6L4M4w==', '2022-08-24', '4', 1, 1, 0),
	(21, 'anvesh', 'anveshnani40@gmail.com', '3zqM0lfh/hQPAsCX6L4M4w==', '2022-08-07', '21', 1, 1, 0),
	(22, 'Prerana', 'Prerana@gmail.com', '3zqM0lfh/hQPAsCX6L4M4w==', '2022-08-02', '21', 1, 1, 0);
/*!40000 ALTER TABLE `userdetails` ENABLE KEYS */;

-- Dumping structure for table quorastudent.userinterests
DROP TABLE IF EXISTS `userinterests`;
CREATE TABLE IF NOT EXISTS `userinterests` (
  `userid` int NOT NULL,
  `interests` longtext,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.userinterests: ~4 rows (approximately)
DELETE FROM `userinterests`;
/*!40000 ALTER TABLE `userinterests` DISABLE KEYS */;
INSERT INTO `userinterests` (`userid`, `interests`) VALUES
	(6, NULL),
	(11, NULL),
	(12, NULL),
	(13, NULL),
	(18, ';3;5;'),
	(19, NULL),
	(20, NULL),
	(21, NULL),
	(22, NULL);
/*!40000 ALTER TABLE `userinterests` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
