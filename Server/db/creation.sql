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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.answers: ~2 rows (approximately)
DELETE FROM `answers`;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` (`aid`, `eqid`, `doa`, `userid`, `ctype`, `updatedat`, `active`, `content`) VALUES
	(3, 3, '2022-07-27 07:50:06', 11, 'Q', '2022-07-27 07:50:06', 1, 'dont know :) how '),
	(5, 1, '2022-08-12 05:23:58', 11, 'Q', '2022-08-12 05:23:58', 1, '<p>hi</p><p><br></p>'),
	(6, 2, '2022-08-12 07:34:53', 11, 'Q', '2022-08-12 07:34:53', 1, '<p>They uplaod in the eportal <a href="https://github.com/" target="_blank">https://github.com/</a></p>'),
	(7, 10, '2022-08-12 08:39:04', 11, 'E', '2022-08-12 08:39:04', 1, '<p>GDRG</p>');
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

-- Dumping data for table quorastudent.comments: ~1 rows (approximately)
DELETE FROM `comments`;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` (`cid`, `cpid`, `userid`, `parentid`, `ctype`, `comment`, `doc`, `updatedat`, `active`) VALUES
	(5, -1, 11, 1, 'Q', 'through portal !!! ', '2022-07-15 03:19:35', '2022-07-15 03:19:35', 1),
	(6, -1, 11, 13, 'Q', 'This is the vamsi comment', '2022-08-12 08:47:09', '2022-08-12 08:47:09', 1),
	(7, -1, 11, 6, 'A', 'hi', '2022-08-12 08:19:52', '2022-08-12 08:19:52', 1),
	(8, -1, 11, 6, 'A', 'Hello', '2022-08-12 08:20:01', '2022-08-12 08:20:01', 1),
	(9, -1, 11, 5, 'A', 'khdfx', '2022-08-12 08:34:43', '2022-08-12 08:34:43', 1),
	(10, -1, 11, 5, 'A', 'bdf', '2022-08-12 08:34:45', '2022-08-12 08:34:45', 1),
	(11, -1, 11, 1, 'Q', 'jtfyuj', '2022-08-12 08:37:57', '2022-08-12 08:37:57', 1),
	(12, -1, 11, 1, 'Q', 'jfgyj', '2022-08-12 08:38:01', '2022-08-12 08:38:01', 1);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.emailactivator: ~0 rows (approximately)
DELETE FROM `emailactivator`;
/*!40000 ALTER TABLE `emailactivator` DISABLE KEYS */;
/*!40000 ALTER TABLE `emailactivator` ENABLE KEYS */;

-- Dumping structure for table quorastudent.events
DROP TABLE IF EXISTS `events`;
CREATE TABLE IF NOT EXISTS `events` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `eid` bigint NOT NULL,
  `fromdate` datetime DEFAULT NULL,
  `todate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.events: ~3 rows (approximately)
DELETE FROM `events`;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` (`id`, `eid`, `fromdate`, `todate`) VALUES
	(2, 10, '2020-08-07 19:00:00', '2020-09-06 19:00:00'),
	(3, 14, '2022-08-10 20:45:27', '2022-08-17 00:00:00'),
	(4, 16, '2022-08-10 20:47:53', '2022-08-22 00:00:00');
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

-- Dumping data for table quorastudent.likedislike: ~6 rows (approximately)
DELETE FROM `likedislike`;
/*!40000 ALTER TABLE `likedislike` DISABLE KEYS */;
INSERT INTO `likedislike` (`id`, `parentid`, `updwnvt`, `userid`, `ctype`, `updatedon`) VALUES
	(1, 3, 1, 6, 'Q', '2022-07-13 06:42:41'),
	(2, 3, 1, 5, 'Q', '2022-07-15 01:51:43'),
	(3, 1, 0, 11, 'Q', '2022-08-12 08:56:33'),
	(4, 2, 1, 6, 'A', '2022-07-15 01:51:43'),
	(5, 3, 1, 6, 'C', '2022-07-15 01:51:43'),
	(6, 1, 0, 6, 'Q', '2022-08-12 09:24:51'),
	(7, 13, 1, 11, 'Q', '2022-08-12 09:24:36'),
	(8, 13, 1, 6, 'Q', '2022-08-12 08:46:13'),
	(9, 5, 0, 11, 'Q', '2022-08-12 04:47:06'),
	(10, 2, 1, 6, 'Q', NULL),
	(15, 2, 1, 11, 'Q', '2022-07-15 01:51:43'),
	(16, 4, 1, 11, 'A', '2022-08-12 07:30:30'),
	(17, 5, 1, 11, 'A', '2022-08-12 08:46:14'),
	(18, 10, 1, 11, 'Q', '2022-08-12 08:39:42'),
	(19, 5, 1, 6, 'A', '2022-08-12 09:24:54'),
	(20, 12, 1, 13, 'Q', '2022-08-12 09:25:40');
/*!40000 ALTER TABLE `likedislike` ENABLE KEYS */;

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

-- Dumping data for table quorastudent.passwordreset: ~0 rows (approximately)
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.questions: ~11 rows (approximately)
DELETE FROM `questions`;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` (`eqid`, `userid`, `question`, `doq`, `updatedat`, `ctype`, `active`, `tags`) VALUES
	(1, 11, 'How do the college manage marks ', '2022-07-06 06:44:18', '2022-07-06 06:44:18', 'Q', 1, ';1;2;'),
	(2, 6, '<h1>How do the college manage marks</h1> ', '2022-08-01 05:46:32', '2022-08-01 05:46:32', 'Q', 1, ';1;2;3;'),
	(4, 6, '<p>Hello how </p>', '2022-07-09 11:05:58', '2022-07-09 11:05:58', 'Q', 1, ';1;2;3;'),
	(6, 6, '<h1>How do the college manage marks</h1> ', '2022-08-08 01:33:14', '2022-08-08 01:33:14', 'Q', 1, ';1;2;3;'),
	(10, 6, '<h1>eVENT TEST</h1> ', '2022-08-08 01:42:23', '2022-08-08 01:42:23', 'E', 1, ';1;2;3;'),
	(11, 6, '<h1>How do the college manage marks</h1> ', '2022-08-08 03:54:43', '2022-08-08 03:54:43', 'Q', 1, ';1;2;3;'),
	(12, 12, '<p>Hello this is a test case for 89 unv</p>', '2022-08-09 00:28:21', '2022-08-09 00:28:21', 'Q', 1, ';1;2;3;'),
	(13, 6, '<h1>tHIS SHOULD BE THE FIRST</h1> ', '2022-08-10 07:27:20', '2022-08-10 07:27:20', 'Q', 1, ';1;2;3;'),
	(14, 6, '<p>bff</p>', '2022-08-10 08:46:21', '2022-08-10 08:46:21', 'E', 1, ';6;4;5;3;'),
	(15, 6, '<p>fwe</p>', '2022-08-10 08:47:47', '2022-08-10 08:47:47', 'Q', 1, ';;'),
	(16, 6, '<p>uyru</p>', '2022-08-10 08:48:24', '2022-08-10 08:48:24', 'E', 1, ';;');
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
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.sessiondetails: ~2 rows (approximately)
DELETE FROM `sessiondetails`;
/*!40000 ALTER TABLE `sessiondetails` DISABLE KEYS */;
INSERT INTO `sessiondetails` (`id`, `userid`, `sessionkey`, `loggedinat`, `loggedoutat`, `active`) VALUES
	(76, 6, 'D5geDjA9tH/1Kf34gBwQhqiOZfKE0DcXbBY3E/aUg2urOiza65d9B4z0DiibpeHl', '2022-08-10 09:19:43', '2022-08-10 09:39:19', 0),
	(77, 6, '20NN9xwGPcl/qP6wVdNsiudYg9QY3gvLtajPjc16yOoO/8lwORat3FKUbsbvzoBu', '2022-08-12 08:43:01', '2022-08-12 08:44:47', 0),
	(78, 11, 'Yuj3nZvqFVbF+3xzjphnTOiyexTvzR0LJ6hQmSXGGAUMIQXYYAIDrhSIISPlc/AE', '2022-08-12 08:45:01', '2022-08-12 08:45:51', 0),
	(79, 6, 'lbcT8OhrLfYM11pyE5oOLQx1MjV6l1yhx95uY5pfaz3tZ41ojtdlN+w7G54TK77m', '2022-08-12 08:45:54', '2022-08-12 08:46:25', 0),
	(80, 11, 'aQxwntujFG0wHdZSwtXz1gH14sAK9owsDR1dWAuxvWU4mmZ9zUq2C7zIpHxbxPxi', '2022-08-12 08:46:29', '2022-08-12 08:46:54', 0),
	(81, 11, 'rEbOdMuHulolk0dDIEkDcsIjScFC3KPpOr4Agz1KzeOBAit6D/b+DsPgHfJ4s6sV', '2022-08-12 08:46:56', '2022-08-12 08:47:25', 0),
	(82, 6, 'h6QW3KA0PhkVVAirTmvxmOFt5Er3BkDCzqs48vw8awlxqV2ECKYaaGQVfcKLhPXx', '2022-08-12 08:47:28', '2022-08-12 08:47:32', 0),
	(83, 11, 'j5ZKQE2seUDfrIib5WVmSYEY/50LA+Ogi0IxbNing0WpV8B+9E46ezEbwPJ46c/D', '2022-08-12 08:47:36', '2022-08-12 06:09:52', 0),
	(84, 11, 'Ej/uXnoqX5qWfTaWEmpqIw+4GMrATtRCkND+iO4m2v2IZViptKYfu5J2Bn/EG/81', '2022-08-12 06:10:21', '2022-08-12 06:13:00', 0),
	(85, 11, 'OsrkjFfLafSXtujnVHM9OXaVaZ6NRSwWV/77IAAymXzBi3Y8+dMdc3rS5001URoZ', '2022-08-12 06:28:44', NULL, 1),
	(86, 11, '/tNMwr5WNLVaB5RepwHNJ4UpnqoqwAWNHhU9GW9W0nsKlOXmqOynFk3yj50S+0fs', '2022-08-12 06:29:45', '2022-08-12 06:55:36', 0),
	(87, 11, '3o45OsKKxkxeThh+LrHP2ol5O5igVKiL60GR9gfGpsziqK3WPNJYwIJEggO00UQ9', '2022-08-12 06:55:37', '2022-08-12 06:55:56', 0),
	(88, 11, 'AQrAQO5it4Z4bL77fkoc01VaKpAZ22/2wD3c2VzSS9FqSDiVrh6GedVapQGi7FtE', '2022-08-12 06:56:01', '2022-08-12 06:56:20', 0),
	(89, 11, 'UNyMRYMn13Uzr9k2gAWRNTNHDbLK3NnCE2S/a0jhELlr5iEOSj+YZ1N3MUAUyhnE', '2022-08-12 06:56:22', '2022-08-12 08:58:25', 0),
	(90, 11, 'wc9WpNaGmSYCidW1nqzlvhbIMRPxQdSl04RfoO/NkSEnhJjZKgtTzzOlj8UjaI/M', '2022-08-12 08:59:10', '2022-08-12 09:24:18', 0),
	(91, 11, 'kh39iF8Kp53f/gXN9eJk52vFPdCJc4+Qf5hfSE0poBpbHOln7I0Mzx/zdgwZO/YB', '2022-08-12 09:24:20', '2022-08-12 09:24:39', 0),
	(92, 6, 'U967CFpO/xYJmZEmCk1z94tkYCOf+suspdHnDUbqEFWNHZDzl2bNQnM1RlW/OIsb', '2022-08-12 09:24:43', '2022-08-12 09:24:57', 0),
	(93, 13, 'SI4NKT8dWhzUPN9waNimKUv78h7pbFeXSPhCq8iguwERczBQ2Yo7cBm1XiR2YtaV', '2022-08-12 09:25:29', '2022-08-12 09:28:27', 0),
	(94, 13, '8WhMJgqcbFNDmRpaE8RJJvHfQwAde7Bn8C8C3AL8p3/5L3A+qV9jcRJ2hrA1YaUH', '2022-08-12 09:28:28', '2022-08-12 11:43:16', 0),
	(95, 13, 'rGoggcJu2fwxsGRF9yauCjRjEB7nwZe0aeXB25JnnJgCn7NzGqJYO1a+daCKoNXh', '2022-08-12 11:43:28', '2022-08-12 11:43:37', 0),
	(96, 13, 'zZTOo5BYwPKrhz/XWi0ritKafchsfu3u8s8Fcc/bTxUVYd1gMAgFL8GsOAZY+OSD', '2022-08-12 11:44:48', '2022-08-12 11:44:51', 0),
	(97, 13, 'kM9FKCY+//bGM9jqwD/zY+VqLJSw2IVhlLa93ErbFWnsiGRy6TyQtmA+zSLd3PNI', '2022-08-12 11:46:31', '2022-08-12 11:46:34', 0),
	(98, 13, '2vDN4/rPxAfsHf8ImgEEjD9qb8qVl+oclQoPGAOlaxNtjr41Xv6WdcHXvPzJDUQN', '2022-08-12 11:48:19', '2022-08-12 11:48:22', 0),
	(99, 13, 'kQv4LmWhNS+MYagKh1B2PRGFHLSO2oKJZJTKIwRNtbxLqvrwr6BVME8RyREBTZhu', '2022-08-12 11:49:30', '2022-08-12 11:49:32', 0),
	(100, 13, 'f2UqEnZoC3DUb5MoO+HKwi8co9YzEh/R1CpsjIUwV8WptQpXit+c5ZP1kquUBJom', '2022-08-12 11:49:38', '2022-08-13 00:15:33', 0),
	(101, 13, 'o++QbrvxMznthNV0HsVX1/RdTzMthhBmbxWYSq/eHJqX7JO4RB/hA/xmVicLAdLz', '2022-08-13 00:15:37', NULL, 1),
	(102, 13, 'WhJ5rm1Bn+Se1BuDx3FIGjyQMTHVMNKTZaYwUOi7LzVgkIQc+8aOg+Z2EYBWNUMk', '2022-08-13 00:18:24', NULL, 1),
	(103, 13, 'Y9oJd+aYxgLQ9mkgbzTa/7CryDftnW67r4d37fjrDKMo9NQ1Bkzu82uG9v/kBphD', '2022-08-13 00:26:57', '2022-08-13 00:29:45', 0),
	(104, 13, 'PXXDmAv+2AVAMg0nbrgEsH3PMNUfImXuiI3YfQL/lk+DgWwg+kqAcJrAm091HHm5', '2022-08-13 00:29:48', '2022-08-13 00:30:27', 0),
	(105, 13, 'NveNbaN82aDAMc6/fuIfQyNA/JH9a54Gzp+yOeHT8dsetYe0r5M91Kb8GCVD8Rrd', '2022-08-13 00:30:30', '2022-08-13 00:32:46', 0),
	(106, 13, 'lq43EV+4ZHVj455iPzZYY6JKrHAbxIRc+FWTLI9fz1FWpsj7Puhg6Pa/pjInUYMt', '2022-08-13 00:32:49', '2022-08-13 00:34:48', 0),
	(107, 13, 'ZsQS0q5YGrEcDjlR6ij7PFWk/dt4Z5jbsTX0uEc6JXRP4GVW9NzxbK19N1t3wNPh', '2022-08-13 00:34:50', '2022-08-13 01:15:32', 0),
	(108, 15, 'A2yjC3XThD3STZ8HnyzVz1ux16/Eu2ClIL9KNct27Klz4rTffePNB+8omRfibNA8', '2022-08-13 08:48:10', '2022-08-13 10:09:26', 0),
	(109, 18, '83cEeQKCiC8gqhpJ9P0VoPrvKjXQFFRos/vAaKplTQj9QNoYu8eUa134Yzehqi+U', '2022-08-13 11:22:50', '2022-08-13 11:24:22', 0),
	(110, 18, 'shFG6DHwta4O17vvYBUDR2s2e3ECZzLxk7BbdQSLUgVVXG7Jqpop/rbOM4f0uDN6', '2022-08-13 11:24:30', '2022-08-13 00:09:32', 0),
	(111, 18, '1v62PlSi7lk4pHBeS9w52a4RlHPw3uhsXP4p7bkdtB2hiY9+yqWy12FW+SOK/kBJ', '2022-08-13 00:09:41', NULL, 1);
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
	('1', 'Abertay University', 'Abertay University', '1234'),
	('2', 'Cardiff University', 'Cardiff University', '7890'),
	('3', 'Lecis University', 'Lecis University', '3456'),
	('4', 'University of Kentucky', 'University of Kentucky', '6789');
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quorastudent.userdetails: ~3 rows (approximately)
DELETE FROM `userdetails`;
/*!40000 ALTER TABLE `userdetails` DISABLE KEYS */;
INSERT INTO `userdetails` (`userid`, `username`, `emailid`, `password`, `dob`, `universitycode`, `interestspopup`, `avatarid`, `emailidactivated`) VALUES
	(6, 'vamsi', 'vamsi@gmail.com', 'zEhq/Nf9IuuDN3XWL+o54A==', '1997-07-22', '2', 1, 1, 0),
	(11, 'gopik', 'vamsoir9rr@gmail.com', 'zEhq/Nf9IuuDN3XWL+o54A==', '1997-07-22', '2', 1, 5, 0),
	(12, 'Gopikrishna', 'gopi@gmail.com', '1HLV5GltQFw8y2Gj3So4aA==', '2022-08-08', '1', 1, 1, 0),
	(13, 'test', 'test@gmail.com', '3zqM0lfh/hQPAsCX6L4M4w==', '2022-08-17', '1', 0, 1, 0),
	(18, 'bhargav', 'bhargav.gandham44@gmail.com', '3zqM0lfh/hQPAsCX6L4M4w==', '2022-08-11', '1', 1, 1, 0);
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
	(6, NULL),
	(11, NULL),
	(12, NULL),
	(18, ';3;5;');
/*!40000 ALTER TABLE `userinterests` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
