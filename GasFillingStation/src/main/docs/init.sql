-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: gas
-- ------------------------------------------------------
-- Server version	5.6.24

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
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `car` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `carNumber` varchar(45) DEFAULT NULL,
  `typeid` int(11) DEFAULT NULL,
  `cylinderNumber` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (1,'asddswa',1,NULL),(2,'123654',1,NULL),(3,'123654',1,NULL),(4,'314561',1,NULL),(5,'123456',1,NULL),(6,'12322',1,NULL),(7,'21212121',1,NULL),(8,'21212121',1,NULL),(9,'21212121',1,NULL);
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card` (
  `id` varchar(64) NOT NULL DEFAULT '',
  `typeid` int(11) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `stateid` int(11) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES ('1197cca4-d668-4e89-a657-9cfd1b255df3',3,0,NULL,NULL),('3e54400b-c3cb-407c-80c4-ab8184c2a2b6',3,0,NULL,NULL),('418e4d59-4b57-4f24-a46d-a57c212209d5',3,0,NULL,NULL),('535aa2c8-b77f-423c-baba-9e27863da4c2',1,480,1,NULL),('66bc381f-203e-47a5-9a4f-2e99daaedc6f',3,0,NULL,NULL),('6a975a92-5cfb-43a2-b7fa-e61beb27a14d',3,0,NULL,NULL),('b40d9b08-72c5-4cae-b0b6-8c68d556ab8f',3,0,NULL,NULL),('b92d931a-9dab-4171-bb8d-ede1f2738ac9',3,0,NULL,NULL),('ce500b48-99b9-4edf-b513-bc97be2840a4',3,0,NULL,NULL),('dc23a14f-4068-4410-8b3c-77b9871e9000',3,0,NULL,NULL);
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cardhistory`
--

DROP TABLE IF EXISTS `cardhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cardhistory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cardid` int(11) DEFAULT NULL,
  `operatorid` int(11) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `remark` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cardhistory`
--

LOCK TABLES `cardhistory` WRITE;
/*!40000 ALTER TABLE `cardhistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `cardhistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cardstate`
--

DROP TABLE IF EXISTS `cardstate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cardstate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cardstate`
--

LOCK TABLES `cardstate` WRITE;
/*!40000 ALTER TABLE `cardstate` DISABLE KEYS */;
INSERT INTO `cardstate` VALUES (1,'激活',NULL),(2,'挂失',NULL),(3,'注销',NULL);
/*!40000 ALTER TABLE `cardstate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cardtype`
--

DROP TABLE IF EXISTS `cardtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cardtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `desc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cardtype`
--

LOCK TABLES `cardtype` WRITE;
/*!40000 ALTER TABLE `cardtype` DISABLE KEYS */;
INSERT INTO `cardtype` VALUES (1,'管理卡',NULL),(2,'员工卡',NULL),(3,'普通卡',NULL);
/*!40000 ALTER TABLE `cardtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cartype`
--

DROP TABLE IF EXISTS `cartype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cartype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `desc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartype`
--

LOCK TABLES `cartype` WRITE;
/*!40000 ALTER TABLE `cartype` DISABLE KEYS */;
INSERT INTO `cartype` VALUES (1,'出租车',NULL),(2,'私家车',NULL);
/*!40000 ALTER TABLE `cartype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `leadingUser` int(11) DEFAULT NULL,
  `location` varchar(200) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `freeGasNumber` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'母站',1,'上海','123321232321',100000000),(2,'上海分公司',2,'上海','2332123',552931),(3,'北京分公司',3,'北京','3212321',45123),(4,'太原分公司',4,'太原','3321',12341),(8,'123',2,'321','1233212321',20000);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `costhistory`
--

DROP TABLE IF EXISTS `costhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `costhistory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `operatorid` int(11) DEFAULT NULL,
  `gasAmount` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `companyid` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `costhistory`
--

LOCK TABLES `costhistory` WRITE;
/*!40000 ALTER TABLE `costhistory` DISABLE KEYS */;
INSERT INTO `costhistory` VALUES (1,1,1,2,15,1,30,240,'2015-12-21 17:37:38'),(2,1,1,2,30,1,60,480,'2015-12-21 17:38:03');
/*!40000 ALTER TABLE `costhistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gashistory`
--

DROP TABLE IF EXISTS `gashistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gashistory` (
  `id` int(11) NOT NULL,
  `companyid` int(11) DEFAULT NULL,
  `gasAmount` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gashistory`
--

LOCK TABLES `gashistory` WRITE;
/*!40000 ALTER TABLE `gashistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `gashistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `remark` varchar(64) DEFAULT NULL,
  `url` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'员工管理','员工管理','/users'),(2,'子站管理','子站管理','/substation'),(3,'业务处理','业务处理','/business'),(4,'数据查询','数据查询','/data'),(5,'权限管理','权限管理','/permission'),(6,'系统设置','系统设置','/setting');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rechargehistory`
--

DROP TABLE IF EXISTS `rechargehistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rechargehistory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cardid` varchar(64) DEFAULT NULL,
  `operatorid` int(11) DEFAULT NULL,
  `money` double DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rechargehistory`
--

LOCK TABLES `rechargehistory` WRITE;
/*!40000 ALTER TABLE `rechargehistory` DISABLE KEYS */;
INSERT INTO `rechargehistory` VALUES (1,'535aa2c8-b77f-423c-baba-9e27863da4c2',1,100,270,'2015-12-21 17:37:19'),(2,'535aa2c8-b77f-423c-baba-9e27863da4c2',1,300,540,'2015-12-21 17:37:51');
/*!40000 ALTER TABLE `rechargehistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registerhistory`
--

DROP TABLE IF EXISTS `registerhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registerhistory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cardid` int(11) DEFAULT NULL,
  `operatorid` int(11) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registerhistory`
--

LOCK TABLES `registerhistory` WRITE;
/*!40000 ALTER TABLE `registerhistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `registerhistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin',NULL),(2,'employee',NULL),(3,'customer',NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rolemenu`
--

DROP TABLE IF EXISTS `rolemenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rolemenu` (
  `roleid` int(11) NOT NULL,
  `menuid` int(11) NOT NULL,
  PRIMARY KEY (`roleid`,`menuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rolemenu`
--

LOCK TABLES `rolemenu` WRITE;
/*!40000 ALTER TABLE `rolemenu` DISABLE KEYS */;
INSERT INTO `rolemenu` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(2,3),(2,4);
/*!40000 ALTER TABLE `rolemenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setting`
--

DROP TABLE IF EXISTS `setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `setting` (
  `key` varchar(45) NOT NULL,
  `value` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setting`
--

LOCK TABLES `setting` WRITE;
/*!40000 ALTER TABLE `setting` DISABLE KEYS */;
INSERT INTO `setting` VALUES ('agentid','20'),('corpid','wx75a40ad206394845'),('domain','http://zjy187156814.6655.la:10328'),('EncodingAESKey','vMpk78HxIOg9sItzl5QeRhVPYnZfiuAXB8NOlcjBeWC'),('secret','mZnEl0LWLRwj30fqicGcXvbOwbLjB4760ckBIsHNjXwjeUACRATtngR-zVhNnmcr'),('Token','LjLEnZTyM24YWQVcMM5peVxaG0p2J9d');
/*!40000 ALTER TABLE `setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `cardid` varchar(100) DEFAULT NULL,
  `companyid` int(11) DEFAULT NULL,
  `number` varchar(45) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  `carid` int(11) DEFAULT NULL,
  `lastLoginDate` date DEFAULT NULL,
  `idcard` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin','18234134166','535aa2c8-b77f-423c-baba-9e27863da4c2',1,'yt00522',1,NULL,NULL,'140602199205079013'),(2,'shanghai','shanghai','1233212321','12312312312321321',2,'iyi23321',2,NULL,NULL,NULL),(3,'beijing','beijing','1231231233','213123123213213123',3,'3213321',2,NULL,NULL,NULL),(4,'taiyuan','taiyuan','12332134213','321321312412312312',4,'3213412',2,NULL,NULL,NULL),(11,'memeda',NULL,'13761764041','418e4d59-4b57-4f24-a46d-a57c212209d5',NULL,'13761764041',3,7,NULL,NULL),(12,'memeda',NULL,'13761764041','3e54400b-c3cb-407c-80c4-ab8184c2a2b6',NULL,'13761764041',3,8,NULL,NULL),(13,'memeda',NULL,'13761764041','dc23a14f-4068-4410-8b3c-77b9871e9000',NULL,'13761764041',3,9,NULL,NULL);
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

-- Dump completed on 2015-12-21 19:50:54
