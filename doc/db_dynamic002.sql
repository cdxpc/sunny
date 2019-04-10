/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.7.18-log : Database - db_dynamic002
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_dynamic002` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_dynamic002`;

/*Table structure for table `dynamic002_sys_dict_type` */

DROP TABLE IF EXISTS `dynamic002_sys_dict_type`;

CREATE TABLE `dynamic002_sys_dict_type` (
  `dict_type_id` varchar(32) NOT NULL COMMENT '字典类型主键id',
  `dict_type_key` varchar(100) NOT NULL COMMENT '字典类型key',
  `dict_type_name` varchar(100) DEFAULT NULL COMMENT '字典类型名称',
  `sort` int(10) NOT NULL AUTO_INCREMENT COMMENT '字典类型排序字段',
  `status` char(1) DEFAULT '1' COMMENT '状态（1正常 2停用）',
  `has_values` char(1) DEFAULT '2' COMMENT '该类型下是否有对应的字典值（1-有，2无）',
  `create_by` varchar(100) DEFAULT NULL COMMENT '记录创建人（id:name）',
  `create_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  `last_update_by` varchar(100) DEFAULT NULL COMMENT '记录最后一次操作人（id:name）',
  `last_update_time` datetime DEFAULT NULL COMMENT '记录最后一次操作时间',
  `del_flag` char(1) DEFAULT 'N' COMMENT '记录删除标志（Y-逻辑删除， N-未删除）',
  PRIMARY KEY (`dict_type_id`),
  KEY `sort` (`sort`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='字典类型表';

/*Data for the table `dynamic002_sys_dict_type` */

insert  into `dynamic002_sys_dict_type`(`dict_type_id`,`dict_type_key`,`dict_type_name`,`sort`,`status`,`has_values`,`create_by`,`create_time`,`last_update_by`,`last_update_time`,`del_flag`) values ('17abc11409b04f3896a98326b7a8e444','dict_menu_type','菜单类型',4,'1','1','admin:admin','2019-02-19 11:01:47','admin:admin','2019-02-21 17:24:04','N'),('2a9baeb193ae45e7b991153699199abb','dict_tree_node','树节点',26,'1','1','admin:admin','2019-02-13 16:31:21','admin:admin','2019-02-22 16:07:18','N'),('3fd3bef3bcb04a7980aab6c023da22b7','dict_menu_visible','菜单可见性',19,'1','1','admin:admin','2019-02-20 10:20:48','admin:admin','2019-02-22 16:07:21','N'),('55bd605692fe4c4e89a57804f6498b1c','dict_is_default','是否默认值',29,'1','1','admin:admin','2019-03-28 15:25:53','admin:admin','2019-03-28 15:25:53','N'),('688897e11c4543258caec63be85c3598','dict_db_type','数据库类型',28,'1','1','admin:admin','2019-03-28 15:24:51','admin:admin','2019-03-28 15:24:51','N'),('68a209e233c34c2fbba784cd60b8a2de','dict_user_gender','性别',8,'1','1','admin:admin','2019-01-30 10:28:15','admin:admin','2019-02-21 17:42:24','N'),('c31c27377e8d4885aa2c5519a0c6d15b','dict_data_status','状态',1,'1','1','admin:admin','2019-01-30 14:01:02','admin:admin','2019-02-21 17:24:04','N'),('e1741a0bb1d549d3889b6ce44bf941b2','dict_user_type','用户类型',27,'1','1','admin:admin','2019-03-11 11:17:54','admin:admin','2019-03-11 11:17:54','N');

/*Table structure for table `dynamic002_sys_dict_value` */

DROP TABLE IF EXISTS `dynamic002_sys_dict_value`;

CREATE TABLE `dynamic002_sys_dict_value` (
  `dict_value_id` varchar(32) NOT NULL COMMENT '字典主键id',
  `dict_label` varchar(100) NOT NULL COMMENT '字典标签',
  `dict_value` varchar(100) NOT NULL COMMENT '字典值',
  `dict_type_id` varchar(100) DEFAULT NULL COMMENT '字典类型id,外键',
  `dict_type_key` varchar(100) DEFAULT NULL COMMENT '字典类型key，冗余字段',
  `sort` int(10) NOT NULL AUTO_INCREMENT COMMENT '字典排序',
  `css_class` varchar(100) DEFAULT NULL COMMENT '字典标签样式',
  `is_default` char(100) DEFAULT 'N' COMMENT '是否默认（Y-是，N-否）',
  `status` char(1) DEFAULT '1' COMMENT '状态（1正常 2停用）',
  `create_by` varchar(100) DEFAULT NULL COMMENT '记录创建人（id:name）',
  `create_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  `last_update_by` varchar(100) DEFAULT NULL COMMENT '记录最后一次操作人（id:name）',
  `last_update_time` datetime DEFAULT NULL COMMENT '记录最后一次操作时间',
  `del_flag` char(1) DEFAULT 'N' COMMENT '记录删除标志（Y-逻辑删除， N-未删除）',
  PRIMARY KEY (`dict_value_id`),
  KEY `sort` (`sort`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='字典值表';

/*Data for the table `dynamic002_sys_dict_value` */

insert  into `dynamic002_sys_dict_value`(`dict_value_id`,`dict_label`,`dict_value`,`dict_type_id`,`dict_type_key`,`sort`,`css_class`,`is_default`,`status`,`create_by`,`create_time`,`last_update_by`,`last_update_time`,`del_flag`) values ('024170d44acd4b31a031c658eea3fbde','oralce','2','688897e11c4543258caec63be85c3598','dict_db_type',11,'css','N','1','admin:admin','2019-03-28 15:25:22','admin:admin','2019-03-28 15:25:22','N'),('076bdd5e7c4f4b658677736e0b620d72','部门','4','f3fb48c1ae2f4cd18de52f0fdcd34707','dict_org_main_categroy',1,'css','1','1','admin:admin','2019-02-13 14:29:53','','2019-02-21 14:23:44','N'),('09a9d3e8440d4c90b98e78b0bdb261b4','目录','R','17abc11409b04f3896a98326b7a8e444','dict_menu_type',1,'badge badge-success','1','1','admin:admin','2019-02-19 11:02:21','','2019-02-21 14:23:44','N'),('116bf8ba1ebe46cba12bbcaab9951257','mysql','1','688897e11c4543258caec63be85c3598','dict_db_type',10,'css','N','1','admin:admin','2019-03-28 15:25:06','admin:admin','2019-03-28 15:25:06','N'),('133fbc4773b0483da20afca0ce2e4ed9','普通用户','3','e1741a0bb1d549d3889b6ce44bf941b2','dict_user_type',9,'css','N','1','admin:admin','2019-03-11 11:18:59','admin:admin','2019-03-11 11:18:59','N'),('201bbde292844ec7bf9ea1dde357aa63','团队','6','f3fb48c1ae2f4cd18de52f0fdcd34707','dict_org_main_categroy',2,'css','1','1','admin:admin','2019-02-13 14:30:18','admin:admin','2019-02-13 14:30:18','N'),('22d6e26506b44e1fb075329bfa97bdde','叶','2','2a9baeb193ae45e7b991153699199abb','dict_tree_node',2,'a','1','1','admin:admin','2019-02-13 16:34:41','','2019-02-25 17:25:42','N'),('3404e0192fd14539a8e069217e8a919d','显示','1','3fd3bef3bcb04a7980aab6c023da22b7','dict_menu_visible',1,'fa fa-circle text-success ml5','1','1','admin:admin','2019-02-20 10:21:29','','2019-02-21 17:42:34','N'),('3aad33a6ee514e6abfb86d12701ed1e5','事业群','2','f3fb48c1ae2f4cd18de52f0fdcd34707','dict_org_main_categroy',3,'css','1','1','admin:admin','2019-02-13 14:29:09','admin:admin','2019-02-13 14:29:09','N'),('3bb8d2baf02540e8813f0872c7dec1c4','是','1','c31c27377e8d4885aa2c5519a0c6d15b','dict_data_status',1,'css','1','1','admin:admin','2019-01-30 14:01:27','admin:admin','2019-01-30 14:01:27','N'),('55cbb1c20935459f890d2d2604505c47','隐藏','2','3fd3bef3bcb04a7980aab6c023da22b7','dict_menu_visible',2,'fa fa-circle text-muted ml5','1','1','admin:admin','2019-02-20 10:21:42','','2019-02-21 17:42:34','N'),('5b329b1d435a45619e0ebcef548ed6ba','枝','1','2a9baeb193ae45e7b991153699199abb','dict_tree_node',1,'a','1','1','admin:admin','2019-02-13 16:34:30','','2019-02-25 17:25:42','N'),('6784164975d84ac99d40edeaf1810750','集团','1','f3fb48c1ae2f4cd18de52f0fdcd34707','dict_org_main_categroy',4,'css','1','1','admin:admin','2019-02-13 14:28:47','admin:admin','2019-02-13 14:28:47','N'),('683a202ab7f34100a90676b2b129803e','子公司','3','f3fb48c1ae2f4cd18de52f0fdcd34707','dict_org_main_categroy',5,'css','1','1','admin:admin','2019-02-13 14:29:28','admin:admin','2019-02-13 14:29:28','N'),('8589e20a5d3f4f19a35369ea87cc1ad4','未知','3','68a209e233c34c2fbba784cd60b8a2de','dict_user_gender',3,'css','0','1','admin:admin','2019-01-30 16:10:07','','2019-02-21 17:24:46','N'),('93e42bb000784272b06cce52fc05445b','男','1','68a209e233c34c2fbba784cd60b8a2de','dict_user_gender',1,'css','1','1','admin:admin','2019-01-30 11:40:48','','2019-02-21 16:47:02','N'),('9e757288ed914744b0df1ab1379bbad2','管理员用户','2','e1741a0bb1d549d3889b6ce44bf941b2','dict_user_type',8,'css','N','1','admin:admin','2019-03-11 11:18:46','admin:admin','2019-03-11 11:18:46','N'),('b1a7fa51b2a54e0e8db2547245ee81f2','按钮','B','17abc11409b04f3896a98326b7a8e444','dict_menu_type',3,'badge badge-primary','1','1','admin:admin','2019-02-19 11:02:59','','2019-02-25 17:26:09','N'),('b49a212c06964a85b218376794c80acb','菜单','L','17abc11409b04f3896a98326b7a8e444','dict_menu_type',2,'badge badge-link','1','1','admin:admin','2019-02-19 11:02:40','','2019-02-25 17:26:09','N'),('ba5d9f46162c4ef3a97707e2e6e931df','db2','3','688897e11c4543258caec63be85c3598','dict_db_type',12,'css','N','1','admin:admin','2019-03-28 15:25:30','admin:admin','2019-03-28 15:25:30','N'),('c723553ed37a4f22a6dfe92428e65d6e','女','2','68a209e233c34c2fbba784cd60b8a2de','dict_user_gender',2,'css','0','1','admin:admin','2019-01-30 11:41:05','','2019-02-21 17:24:46','N'),('d3a0efb6eef04450b8ed92b1ebf1baa1','项目组','5','f3fb48c1ae2f4cd18de52f0fdcd34707','dict_org_main_categroy',6,'css','1','1','admin:admin','2019-02-13 14:30:04','admin:admin','2019-02-13 14:30:04','N'),('dd9ee797d17242888da255e880a9ec94','否','2','55bd605692fe4c4e89a57804f6498b1c','dict_is_default',14,'css','N','1','admin:admin','2019-03-28 15:26:15','admin:admin','2019-03-28 15:26:15','N'),('f186a7578344494d8b02facc8ada1637','是','1','55bd605692fe4c4e89a57804f6498b1c','dict_is_default',13,'css','N','1','admin:admin','2019-03-28 15:26:06','admin:admin','2019-03-28 15:26:06','N'),('f419821a727a464aaa612467c88f53b6','超级管理员','1','e1741a0bb1d549d3889b6ce44bf941b2','dict_user_type',7,'css','N','1','admin:admin','2019-03-11 11:18:22','admin:admin','2019-03-11 11:18:22','N'),('ff2673ddbdbc4824af56ab81aef45d54','否','2','c31c27377e8d4885aa2c5519a0c6d15b','dict_data_status',2,'css','0','1','admin:admin','2019-01-30 14:01:39','admin:admin','2019-01-30 14:01:39','N');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
