/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.9 : Database - bridge
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bridge` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `bridge`;

/*Table structure for table `banker` */

DROP TABLE IF EXISTS `banker`;

CREATE TABLE `banker` (
  `banker_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `fname` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`banker_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `banker` */

insert  into `banker`(`banker_id`,`login_id`,`fname`,`place`,`phone`,`email`) values 
(1,2,'user qwerty','kerala','2345678907','student@gmail.com');

/*Table structure for table `business` */

DROP TABLE IF EXISTS `business`;

CREATE TABLE `business` (
  `business_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`business_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `business` */

insert  into `business`(`business_id`,`name`,`details`,`date`) values 
(1,'user','qwertyui','2023-03-31');

/*Table structure for table `enqyryteam` */

DROP TABLE IF EXISTS `enqyryteam`;

CREATE TABLE `enqyryteam` (
  `enqury_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`enqury_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `enqyryteam` */

insert  into `enqyryteam`(`enqury_id`,`login_id`,`name`,`place`,`phone`) values 
(1,1,'user','kerala','1234567890');

/*Table structure for table `idea` */

DROP TABLE IF EXISTS `idea`;

CREATE TABLE `idea` (
  `idea_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `experence` varchar(100) DEFAULT NULL,
  `skill` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idea_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `idea` */

insert  into `idea`(`idea_id`,`user_id`,`name`,`category`,`experence`,`skill`,`date`,`status`) values 
(1,1,'1sdfdfd','fdafa','fafaf','fafa','343234','ffsf'),
(2,3,'bdhsh','hdhdh','hdhdh','bdhsb','2023-03-13','pending'),
(3,NULL,'asdfg','asdd','adfg','afg','2023-03-14','pending');

/*Table structure for table `investor` */

DROP TABLE IF EXISTS `investor`;

CREATE TABLE `investor` (
  `investor_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `fname` varchar(100) DEFAULT NULL,
  `lname` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`investor_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `investor` */

insert  into `investor`(`investor_id`,`login_id`,`fname`,`lname`,`place`,`phone`,`email`) values 
(1,4,'user','qwerty','kerala','2345678907','student@gmail.com'),
(2,7,'user','qwerty','karanakodam','2345678907','student@gmail.com');

/*Table structure for table `loan` */

DROP TABLE IF EXISTS `loan`;

CREATE TABLE `loan` (
  `loan_id` int(11) NOT NULL AUTO_INCREMENT,
  `banker_id` int(11) DEFAULT NULL,
  `loandetails` varchar(100) DEFAULT NULL,
  `noofdays` varchar(100) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `monthlyamount` varchar(100) DEFAULT NULL,
  `papers` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`loan_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `loan` */

insert  into `loan`(`loan_id`,`banker_id`,`loandetails`,`noofdays`,`amount`,`monthlyamount`,`papers`) values 
(1,1,'qwertyui','dhsdjs','5889','saj,dsak','static/image9d3d92cb-9905-4eec-812a-2767953d35admotorbike-front-side-bike-logo-fast-ride-symbol-2J54CKY.jpg');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `usertype` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`username`,`password`,`usertype`) values 
(1,'bus','bus','business'),
(2,'bank','bank','bank'),
(3,'admin','admin','admin'),
(4,'in','in','investor'),
(5,'user','user','user'),
(6,'hai','hai','user'),
(7,'investor','investor','investor');

/*Table structure for table `news` */

DROP TABLE IF EXISTS `news`;

CREATE TABLE `news` (
  `news_id` int(11) NOT NULL AUTO_INCREMENT,
  `news` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`news_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `news` */

insert  into `news`(`news_id`,`news`) values 
(3,'tomorrow 15-03-2023');

/*Table structure for table `offers` */

DROP TABLE IF EXISTS `offers`;

CREATE TABLE `offers` (
  `offers_id` int(11) NOT NULL AUTO_INCREMENT,
  `banker_id` varchar(100) DEFAULT NULL,
  `offerss` varchar(100) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`offers_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `offers` */

insert  into `offers`(`offers_id`,`banker_id`,`offerss`,`details`) values 
(1,'1','qwertyui','dhsdjs');

/*Table structure for table `proposal` */

DROP TABLE IF EXISTS `proposal`;

CREATE TABLE `proposal` (
  `proposal_id` int(11) NOT NULL AUTO_INCREMENT,
  `idea_id` int(11) DEFAULT NULL,
  `investor_id` int(11) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`proposal_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `proposal` */

insert  into `proposal`(`proposal_id`,`idea_id`,`investor_id`,`amount`,`date`,`status`) values 
(1,1,1,'5889','2023-03-12','Accept'),
(2,1,1,'627','2023-03-14','Accept');

/*Table structure for table `query` */

DROP TABLE IF EXISTS `query`;

CREATE TABLE `query` (
  `query_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `query` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`query_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `query` */

insert  into `query`(`query_id`,`user_id`,`query`,`date`) values 
(1,1,'erghj','1234567'),
(2,3,'aa','2023-03-13'),
(3,NULL,'asdd','2023-03-14');

/*Table structure for table `solution` */

DROP TABLE IF EXISTS `solution`;

CREATE TABLE `solution` (
  `solution_id` int(11) DEFAULT NULL,
  `query_id` int(11) DEFAULT NULL,
  `solution` varchar(100) DEFAULT NULL,
  `enqury_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `solution` */

insert  into `solution`(`solution_id`,`query_id`,`solution`,`enqury_id`) values 
(1,1,'user',1);

/*Table structure for table `stories` */

DROP TABLE IF EXISTS `stories`;

CREATE TABLE `stories` (
  `stories_id` int(11) NOT NULL AUTO_INCREMENT,
  `business_id` int(11) DEFAULT NULL,
  `image` varchar(1000) DEFAULT NULL,
  `details` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`stories_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `stories` */

insert  into `stories`(`stories_id`,`business_id`,`image`,`details`) values 
(3,1,'static/image79b52100-7d47-40d6-b81a-e6fd74350124motorbike-front-side-bike-logo-fast-ride-symbol-2J54CKY.jpg',' at today morning  9.30 am');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `fname` varchar(100) DEFAULT NULL,
  `lname` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`user_id`,`login_id`,`fname`,`lname`,`place`,`phone`,`email`) values 
(1,1,'czfdsfs','fsdfsd','fdsfsd','5452325332','ssd'),
(2,NULL,NULL,NULL,NULL,NULL,NULL),
(3,6,'bdhdh','hxhdh','bchdhhd','8764664653','bchrhrj@gmail.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
