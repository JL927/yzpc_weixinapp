-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: yzpc_wxapp
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application` (
  `id` bigint unsigned NOT NULL,
  `student_id` bigint NOT NULL,
  `application_type` enum('computer','skill','english','chinese') NOT NULL,
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '申请状态中，0表示失败，1为提交等待班主任审核，2为等待主任审核，3为等待院长审核，4为通过，在通过后会有最终得分，如果失败则可以添加描述',
  `description` varchar(400) DEFAULT NULL,
  `score` tinyint DEFAULT '0',
  `name` varchar(50) NOT NULL DEFAULT '未命名',
  PRIMARY KEY (`id`),
  KEY `idx_student_id` (`student_id`),
  CONSTRAINT `application_chk_1` CHECK ((`status` in (0,1,2,3,4)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (1870758399127314434,1,'chinese',4,'',2,'未命名');
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `major_id` tinyint NOT NULL,
  `teacher_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (1,'旅游一班',1,8);
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images` (
  `id` bigint unsigned NOT NULL,
  `application_id` bigint NOT NULL,
  `url` varchar(2048) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_application_id` (`application_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `major` (
  `id` tinyint NOT NULL,
  `name` varchar(30) NOT NULL,
  `required_score` tinyint NOT NULL,
  `description` varchar(600) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `major_pk_2` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
INSERT INTO `major` VALUES (1,'旅游管理（高中）',4,'计算机类证书（获得国家一级及以上证书或通过信息技术校测认定）。\n技能类证书（获得导游资格证书、人社部门技能鉴定证书、学校技能认定证书、参加省级比赛获得三等奖及以上成绩，满足其中之一即可）。\n英语类证书（获得大学英语应用能力等级考试A级或B级证书、CET-4、CET-6，满足其中之一即可）。\n以上三类证书，每个证书2个学分，计算机类和英语类证书至少获得2个学分，技能类证书至少获得2个学分。'),(2,'旅游管理（中职）',4,'计算机类证书（获得国家一级及以上证书或通过信息技术校测认定）。\n技能类证书（获得导游资格证书、人社部门技能鉴定证书、学校技能认定证书、参加省级比赛获得三等奖及以上成绩，满足其中之一即可）。\n英语类证书（获得大学英语应用能力等级考试A级或B级证书、CET-4、CET-6，满足其中之一即可）。\n以上三类证书，每个证书2个学分，计算机类和英语类证书至少获得2个学分，技能类证书至少获得2个学分。'),(3,'旅游管理（3+2）',6,'计算机类证书（获得国家一级及以上证书或通过信息技术校测认定）。\n技能类证书（获得导游资格证书、人社部门技能鉴定证书、学校技能认定证书、参加省级比赛获得三等奖及以上成绩，满足其中之一即可）。\n英语类证书（大学英语四级考试分数须达到300分及以上，小语种参加相应等级考试达到同等水平）。\n以上三类证书，每类证书2个学分，共需获得6个学分。'),(4,'导游',4,'计算机类证书（获得国家一级及以上证书或通过信息技术校测认定）。\n英语类证书（获得大学英语应用能力等级考试A级或B级证书、CET-4、CET-6，满足其中之一即可）。\n技能类证书（获得导游资格证书、人社部门技能鉴定证书、学校技能认定证书、参加省级比赛获得三等奖及以上成绩，满足其中之一即可）。\n以上三类证书，每个证书2个学分，计算机类和英语类证书至少获得2个学分，技能类证书至少获得2个学分。'),(5,'酒店管理与数字化运营（高中）',4,'计算机类证书（获得国家一级及以上证书或通过信息技术校测认定）。\n英语类证书（获得大学英语应用能力等级考试A级或B级证书、CET-4、CET-6，满足其中之一即可）。\n技能类证书（获得人社部门技能鉴定证书、参加省级比赛获得三等奖及以上成绩、酒店相关行业证书之一）。\n以上三类证书，每个证书2个学分，计算机类和英语类证书至少获得2个学分，技能类证书至少获得2个学分。'),(6,'酒店管理与数字化运营（中职）',4,'计算机类证书（获得国家一级及以上证书或通过信息技术校测认定）。\n英语类证书（获得大学英语应用能力等级考试A级或B级证书、CET-4、CET-6，满足其中之一即可）。\n技能类证书（获得人社部门技能鉴定证书、参加省级比赛获得三等奖及以上成绩或酒店相关行业证书之一）。\n以上三类证书，每个证书2个学分，计算机类和英语类证书至少获得2个学分，技能类证书至少获得2个学分。'),(7,'高速铁路客运服务',4,'计算机类证书（获得国家一级及以上证书或通过信息技术校测认定）。\n技能类证书（获得人社部门技能鉴定证书、学校技能认定证书、参加省级比赛获得三等奖及以上成绩、高速铁路客运服务行业证书，满足其中之一即可）。\n英语类证书（获得大学英语应用能力等级考试A级或B级证书、CET-4、CET-6，满足其中之一即可）。\n以上三类证书，每个证书2个学分，计算机类和英语类证书至少获得2个学分，技能类证书至少获得2个学分。'),(8,'空中乘务',4,'计算机类证书（获得国家一级及以上证书或通过信息技术校测认定）。 \n英语类证书（获得大学英语应用能力等级考试B级证书、CET-4、CET-6，满足其中之一即可）。\n技能类证书（获得人社部门技能鉴定证书、普通话证书、学校技能认定证书、参加校级比赛获得一等奖成绩、空中乘务行业证书，满足其中之一即可）。\n以上三类证书，每个证书2个学分，计算机和英语类证书至少获得2个学分，技能类证书至少获得2个学分。'),(9,'人物形象设计（高中）',4,'计算机类证书（获得国家一级及以上证书或通过信息技术校测认定）。\n鼓励学生考取普通话等级证书。\n技能类证书（获得人社部门技能鉴定证书、学校技能认定证书、参加省级比赛获得三等奖及以上成绩、美发美容行业证书，满足其中之一即可）。\n以上三类证书，计算机证书2个学分，普通话等级证书1个学分，技能类证书每个1个学分。'),(10,'人物形象设计（中职）',4,'计算机类证书（获得国家一级及以上证书或通过信息技术校测认定）。\n鼓励学生考取普通话等级证书。\n技能类证书（获得人社部门技能鉴定证书、学校技能认定证书、参加省级比赛获得三等奖及以上成绩、美发美容行业证书，满足其中之一即可）。\n以上三类证书，计算机证书2个学分，普通话等级证书1个学分，技能类证书每个1个学分。');
/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` bigint unsigned NOT NULL,
  `name` varchar(30) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL DEFAULT '888888',
  `teacher_id` int NOT NULL,
  `class_id` int NOT NULL,
  `major_id` tinyint NOT NULL,
  `computer_score` tinyint NOT NULL DEFAULT '0',
  `skill_score` tinyint NOT NULL DEFAULT '0',
  `english_score` tinyint NOT NULL DEFAULT '0',
  `chinese_score` tinyint NOT NULL DEFAULT '0',
  `total_score` tinyint NOT NULL DEFAULT '0',
  `satisfied` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_pk` (`id`),
  UNIQUE KEY `student_pk_2` (`username`),
  KEY `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'gzy','gzy','888888',1,1,1,0,0,0,2,2,0),(2,'yjj','yjj','888888',1,1,1,0,0,0,0,0,0);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` enum('dean','department_head','class_teacher') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'hhp','root','root','dean'),(7,'ghc','ghc','123','department_head'),(8,'yhb','yhb','123','class_teacher');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-11 15:22:08
