/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.7.18-log : Database - db_sunny
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_sunny` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_sunny`;

/*Table structure for table `ft_sys_dict_type` */

DROP TABLE IF EXISTS `ft_sys_dict_type`;

CREATE TABLE `ft_sys_dict_type` (
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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='字典类型表';

/*Data for the table `ft_sys_dict_type` */

insert  into `ft_sys_dict_type`(`dict_type_id`,`dict_type_key`,`dict_type_name`,`sort`,`status`,`has_values`,`create_by`,`create_time`,`last_update_by`,`last_update_time`,`del_flag`) values ('0b9ca4bdfd8e447ea1e69bdcbbfc6662','dict_clean_rule_type','数据清理规则类型',30,'1','1','admin:admin','2019-04-01 10:44:21','admin:admin','2019-04-01 10:44:21','N'),('17abc11409b04f3896a98326b7a8e444','dict_menu_type','菜单类型',4,'1','1','admin:admin','2019-02-19 11:01:47','admin:admin','2019-02-21 17:24:04','N'),('2a9baeb193ae45e7b991153699199abb','dict_tree_node','树节点',26,'1','1','admin:admin','2019-02-13 16:31:21','admin:admin','2019-02-22 16:07:18','N'),('3fd3bef3bcb04a7980aab6c023da22b7','dict_menu_visible','菜单可见性',19,'1','1','admin:admin','2019-02-20 10:20:48','admin:admin','2019-02-22 16:07:21','N'),('55bd605692fe4c4e89a57804f6498b1c','dict_is_default','是否默认值',29,'1','1','admin:admin','2019-03-28 15:25:53','admin:admin','2019-03-28 15:25:53','N'),('5810e36b06834e2abcb9be290f150400','dict_clean_rule_strategy_type','数据清理规则策略类型',31,'1','1','admin:admin','2019-04-01 10:44:49','admin:admin','2019-04-01 10:53:19','N'),('688897e11c4543258caec63be85c3598','dict_db_type','数据库类型',28,'1','1','admin:admin','2019-03-28 15:24:51','admin:admin','2019-03-28 15:24:51','N'),('68a209e233c34c2fbba784cd60b8a2de','dict_user_gender','性别',8,'1','1','admin:admin','2019-01-30 10:28:15','admin:admin','2019-02-21 17:42:24','N'),('c31c27377e8d4885aa2c5519a0c6d15b','dict_data_status','状态',1,'1','1','admin:admin','2019-01-30 14:01:02','admin:admin','2019-02-21 17:24:04','N'),('e1741a0bb1d549d3889b6ce44bf941b2','dict_user_type','用户类型',27,'1','1','admin:admin','2019-03-11 11:17:54','admin:admin','2019-03-11 11:17:54','N');

/*Table structure for table `ft_sys_dict_value` */

DROP TABLE IF EXISTS `ft_sys_dict_value`;

CREATE TABLE `ft_sys_dict_value` (
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='字典值表';

/*Data for the table `ft_sys_dict_value` */

insert  into `ft_sys_dict_value`(`dict_value_id`,`dict_label`,`dict_value`,`dict_type_id`,`dict_type_key`,`sort`,`css_class`,`is_default`,`status`,`create_by`,`create_time`,`last_update_by`,`last_update_time`,`del_flag`) values ('024170d44acd4b31a031c658eea3fbde','oralce','2','688897e11c4543258caec63be85c3598','dict_db_type',11,'css','N','1','admin:admin','2019-03-28 15:25:22','admin:admin','2019-03-28 15:25:22','N'),('076bdd5e7c4f4b658677736e0b620d72','部门','4','f3fb48c1ae2f4cd18de52f0fdcd34707','dict_org_main_categroy',1,'css','1','1','admin:admin','2019-02-13 14:29:53','','2019-02-21 14:23:44','N'),('09a9d3e8440d4c90b98e78b0bdb261b4','目录','R','17abc11409b04f3896a98326b7a8e444','dict_menu_type',1,'badge badge-success','1','1','admin:admin','2019-02-19 11:02:21','','2019-02-21 14:23:44','N'),('116bf8ba1ebe46cba12bbcaab9951257','mysql','1','688897e11c4543258caec63be85c3598','dict_db_type',10,'css','N','1','admin:admin','2019-03-28 15:25:06','admin:admin','2019-03-28 15:25:06','N'),('133fbc4773b0483da20afca0ce2e4ed9','普通用户','3','e1741a0bb1d549d3889b6ce44bf941b2','dict_user_type',9,'css','N','1','admin:admin','2019-03-11 11:18:59','admin:admin','2019-03-11 11:18:59','N'),('1bfb1b3ed9664355a9cdb11a7d8d27fd','直接删除','1','5810e36b06834e2abcb9be290f150400','dict_clean_rule_strategy_type',15,'css','N','1','admin:admin','2019-04-01 10:45:31','admin:admin','2019-04-01 10:45:31','N'),('201bbde292844ec7bf9ea1dde357aa63','团队','6','f3fb48c1ae2f4cd18de52f0fdcd34707','dict_org_main_categroy',2,'css','1','1','admin:admin','2019-02-13 14:30:18','admin:admin','2019-02-13 14:30:18','N'),('22d6e26506b44e1fb075329bfa97bdde','叶','2','2a9baeb193ae45e7b991153699199abb','dict_tree_node',2,'a','1','1','admin:admin','2019-02-13 16:34:41','','2019-02-25 17:25:42','N'),('3404e0192fd14539a8e069217e8a919d','显示','1','3fd3bef3bcb04a7980aab6c023da22b7','dict_menu_visible',1,'fa fa-circle text-success ml5','1','1','admin:admin','2019-02-20 10:21:29','','2019-02-21 17:42:34','N'),('34c69e15d98145ed9516aeac053b1ac9','按指定天数删除','1','0b9ca4bdfd8e447ea1e69bdcbbfc6662','dict_clean_rule_type',17,'css','N','1','admin:admin','2019-04-01 10:46:38','admin:admin','2019-04-01 10:46:38','N'),('3aad33a6ee514e6abfb86d12701ed1e5','事业群','2','f3fb48c1ae2f4cd18de52f0fdcd34707','dict_org_main_categroy',3,'css','1','1','admin:admin','2019-02-13 14:29:09','admin:admin','2019-02-13 14:29:09','N'),('3b0a8c8dacb84316b15d048e7f19a5d4','按失效日期删除','2','0b9ca4bdfd8e447ea1e69bdcbbfc6662','dict_clean_rule_type',18,'css','N','1','admin:admin','2019-04-01 10:46:54','admin:admin','2019-04-01 10:46:55','N'),('3bb8d2baf02540e8813f0872c7dec1c4','是','1','c31c27377e8d4885aa2c5519a0c6d15b','dict_data_status',1,'css','1','1','admin:admin','2019-01-30 14:01:27','admin:admin','2019-01-30 14:01:27','N'),('55cbb1c20935459f890d2d2604505c47','隐藏','2','3fd3bef3bcb04a7980aab6c023da22b7','dict_menu_visible',2,'fa fa-circle text-muted ml5','1','1','admin:admin','2019-02-20 10:21:42','','2019-02-21 17:42:34','N'),('5b329b1d435a45619e0ebcef548ed6ba','枝','1','2a9baeb193ae45e7b991153699199abb','dict_tree_node',1,'a','1','1','admin:admin','2019-02-13 16:34:30','','2019-02-25 17:25:42','N'),('6784164975d84ac99d40edeaf1810750','集团','1','f3fb48c1ae2f4cd18de52f0fdcd34707','dict_org_main_categroy',4,'css','1','1','admin:admin','2019-02-13 14:28:47','admin:admin','2019-02-13 14:28:47','N'),('683a202ab7f34100a90676b2b129803e','子公司','3','f3fb48c1ae2f4cd18de52f0fdcd34707','dict_org_main_categroy',5,'css','1','1','admin:admin','2019-02-13 14:29:28','admin:admin','2019-02-13 14:29:28','N'),('8589e20a5d3f4f19a35369ea87cc1ad4','未知','3','68a209e233c34c2fbba784cd60b8a2de','dict_user_gender',3,'css','0','1','admin:admin','2019-01-30 16:10:07','','2019-02-21 17:24:46','N'),('8edaf5f45c594aacaff93c4f10eb2b19','先备份，再删除','2','5810e36b06834e2abcb9be290f150400','dict_clean_rule_strategy_type',16,'css','N','1','admin:admin','2019-04-01 10:46:06','admin:admin','2019-04-01 10:46:06','N'),('93e42bb000784272b06cce52fc05445b','男','1','68a209e233c34c2fbba784cd60b8a2de','dict_user_gender',1,'css','1','1','admin:admin','2019-01-30 11:40:48','','2019-02-21 16:47:02','N'),('9e757288ed914744b0df1ab1379bbad2','管理员用户','2','e1741a0bb1d549d3889b6ce44bf941b2','dict_user_type',8,'css','N','1','admin:admin','2019-03-11 11:18:46','admin:admin','2019-03-11 11:18:46','N'),('b1a7fa51b2a54e0e8db2547245ee81f2','按钮','B','17abc11409b04f3896a98326b7a8e444','dict_menu_type',3,'badge badge-primary','1','1','admin:admin','2019-02-19 11:02:59','','2019-02-25 17:26:09','N'),('b49a212c06964a85b218376794c80acb','菜单','L','17abc11409b04f3896a98326b7a8e444','dict_menu_type',2,'badge badge-link','1','1','admin:admin','2019-02-19 11:02:40','','2019-02-25 17:26:09','N'),('ba5d9f46162c4ef3a97707e2e6e931df','db2','3','688897e11c4543258caec63be85c3598','dict_db_type',12,'css','N','1','admin:admin','2019-03-28 15:25:30','admin:admin','2019-03-28 15:25:30','N'),('c723553ed37a4f22a6dfe92428e65d6e','女','2','68a209e233c34c2fbba784cd60b8a2de','dict_user_gender',2,'css','0','1','admin:admin','2019-01-30 11:41:05','','2019-02-21 17:24:46','N'),('d3a0efb6eef04450b8ed92b1ebf1baa1','项目组','5','f3fb48c1ae2f4cd18de52f0fdcd34707','dict_org_main_categroy',6,'css','1','1','admin:admin','2019-02-13 14:30:04','admin:admin','2019-02-13 14:30:04','N'),('dd9ee797d17242888da255e880a9ec94','否','2','55bd605692fe4c4e89a57804f6498b1c','dict_is_default',14,'css','N','1','admin:admin','2019-03-28 15:26:15','admin:admin','2019-03-28 15:26:15','N'),('f186a7578344494d8b02facc8ada1637','是','1','55bd605692fe4c4e89a57804f6498b1c','dict_is_default',13,'css','N','1','admin:admin','2019-03-28 15:26:06','admin:admin','2019-03-28 15:26:06','N'),('f419821a727a464aaa612467c88f53b6','超级管理员','1','e1741a0bb1d549d3889b6ce44bf941b2','dict_user_type',7,'css','N','1','admin:admin','2019-03-11 11:18:22','admin:admin','2019-03-11 11:18:22','N'),('fae13fa84f1a47dab8322471197ab2d5','按指定字段匹配规则删除','3','0b9ca4bdfd8e447ea1e69bdcbbfc6662','dict_clean_rule_type',19,'css','N','1','admin:admin','2019-04-01 10:47:10','admin:admin','2019-04-01 10:47:10','N'),('ff2673ddbdbc4824af56ab81aef45d54','否','2','c31c27377e8d4885aa2c5519a0c6d15b','dict_data_status',2,'css','0','1','admin:admin','2019-01-30 14:01:39','admin:admin','2019-01-30 14:01:39','N');

/*Table structure for table `ft_sys_menu` */

DROP TABLE IF EXISTS `ft_sys_menu`;

CREATE TABLE `ft_sys_menu` (
  `menu_id` varchar(32) NOT NULL COMMENT '菜单id',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '上级菜单id',
  `menu_name` varchar(100) NOT NULL COMMENT '菜单名称',
  `url` varchar(255) DEFAULT NULL COMMENT '菜单url链接',
  `icon` varchar(200) DEFAULT NULL COMMENT '菜单图标',
  `menu_type` char(1) DEFAULT NULL COMMENT '菜单类型',
  `perms` varchar(200) DEFAULT NULL COMMENT '菜单权限',
  `visible` char(1) DEFAULT '1' COMMENT '菜单可见性（1可见 2不可见）',
  `sort` int(10) DEFAULT NULL COMMENT '菜单排序',
  `status` char(1) DEFAULT '1' COMMENT '状态（1正常 2停用）',
  `tree_node` char(1) DEFAULT NULL COMMENT '树节点',
  `create_by` varchar(100) DEFAULT NULL COMMENT '记录创建人（id:name）',
  `create_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  `last_update_by` varchar(100) DEFAULT NULL COMMENT '记录最后一次操作人（id:name）',
  `last_update_time` datetime DEFAULT NULL COMMENT '记录最后一次操作时间',
  `del_flag` char(1) DEFAULT 'N' COMMENT '记录删除标志（Y-逻辑删除， N-未删除）',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

/*Data for the table `ft_sys_menu` */

insert  into `ft_sys_menu`(`menu_id`,`parent_id`,`menu_name`,`url`,`icon`,`menu_type`,`perms`,`visible`,`sort`,`status`,`tree_node`,`create_by`,`create_time`,`last_update_by`,`last_update_time`,`del_flag`) values ('1','0','系统管理','#','fa fa-gear','R','admin','1',1,'1','1','admin','2018-07-18 14:47:40','admin','2018-07-18 14:47:52','N'),('1011','102','菜单查询','sys/menu/lista','#','B','sys:menu:list','1',1,'2','2','admin','2018-07-18 14:47:40','admin:admin','2019-03-04 14:57:16','N'),('1012','102','菜单新增','sys/menu/add','#','B','sys:menu:add','2',2,'2','2','admin','2018-07-18 14:47:40','admin:admin','2019-02-22 16:07:00','N'),('1013','102','菜单修改','sys/menu/edit','#','B','sys:menu:edit','1',3,'1','2','admin','2018-07-18 14:47:40','admin:admin','2019-03-04 14:57:43','N'),('1014','102','菜单删除','sys/menu/remove','#','B','sys:menu:remove','2',4,'1','2','admin','2018-07-18 14:47:40','admin:admin','2019-02-20 14:31:00','N'),('1015','102','菜单保存','sys/menu/save','#','B','sys:menu:save','1',5,'1','2','admin','2018-07-18 14:47:40','admin:admin','2019-02-20 14:31:05','N'),('102','1','菜单管理','sys/menu/list','fa fa-home','L','sys:menu:view','1',3,'1','1','admin','2018-07-18 14:47:40','admin:admin','2019-03-04 14:56:55','N'),('104','1','字典管理','sys/dict/type/list','fa fa-spinner','L','sys:dict:type:view,sys:dict:value:view','1',5,'1','1','admin','2018-07-18 14:47:40','admin:admin','2019-03-04 14:35:44','N'),('1b101138c77849b08ee62d6932f7c49b','2','代码生成','gen/list','fa fa-download','L','aa','1',NULL,'1','2','admin:admin','2019-03-05 20:48:01','admin:admin','2019-03-08 10:36:22','N'),('1ff14d0a36834527b9371fafc85b1062','9b18457575574d97a2b04cefb4e98567','清理规则管理','dataclean/rule/list','fa fa-puzzle-piece','L','clean:rule:view','1',NULL,'1','2','admin:admin','2019-03-31 16:48:38','admin:admin','2019-03-31 16:48:38','N'),('2','0','辅助组件','#','fa fa-wrench','R','c','1',2,'1','1','admin:admin','2019-03-05 14:54:22','admin:admin','2019-03-05 20:45:26','N'),('4119361ce85f46d69b2448bb532e82aa','9b169c9491844c558122ee16ed3d1c8d','数据库管理','db/dataBase/list','fa fa-database','L','db:dataBase:view','1',NULL,'1','2','admin:admin','2019-03-28 15:04:18','admin:admin','2019-03-28 15:04:18','N'),('4a4624cf6e2c4f0bbae1c5bedd010ff2','9b18457575574d97a2b04cefb4e98567','自动清理任务管理','dataclean/task/list','fa fa-history','L','clean:task:view','1',NULL,'1','2','admin:admin','2019-03-31 16:52:21','admin:admin','2019-03-31 16:52:21','N'),('5cd008ebd5374df18aaf29c68ea749db','1','角色管理','sys/role/list','fa fa-joomla','L','','1',NULL,'1','2','admin:admin','2019-03-04 18:47:22','admin:admin','2019-03-08 10:37:05','N'),('7def4d70f6954ed185b73bf5a09a491e','1','testa','testa','fa fa-book','L','aaaa','1',NULL,'1','1','admin:admin','2019-02-27 14:53:01','admin:admin','2019-02-27 14:55:23','N'),('93a69f9a95da4018841114296db7c2a7','9b169c9491844c558122ee16ed3d1c8d','数据库表管理','db/table/list','fa fa-th','L','db:table:view','1',NULL,'1','2','admin:admin','2019-03-28 15:05:08','admin:admin','2019-03-28 15:05:08','N'),('9b169c9491844c558122ee16ed3d1c8d','0','数据源管理','#','fa fa-codepen','R','admin','1',NULL,'1','1','admin:admin','2019-03-28 15:00:35','admin:admin','2019-03-28 15:00:54','N'),('9b18457575574d97a2b04cefb4e98567','0','数据清理管理','#','fa fa-trash-o','R','admin','1',NULL,'1','2','admin:admin','2019-03-31 16:40:30','admin:admin','2019-03-31 16:40:30','N'),('a16f195b58894b0bb0a6bd9e9a3ef3e0','2','RestApi','rest/apis','fa fa-pagelines','L','admin','1',NULL,'1','2','admin:admin','2019-03-07 16:47:45','admin:admin','2019-03-07 20:30:41','N'),('ed3367cb11254b7993178293cb1de1ef','7def4d70f6954ed185b73bf5a09a491e','c','','','B','c','1',NULL,'1','2','admin:admin','2019-02-27 14:54:22','admin:admin','2019-02-27 14:55:27','N'),('f2b9a3ab35624e188cbf5a7a651fd8ed','1','用户管理','sys/user/list','fa fa-user','L','sys:user:view','1',NULL,'1','2','admin:admin','2019-03-07 13:52:41','admin:admin','2019-03-07 13:52:41','N');

/*Table structure for table `ft_sys_org` */

DROP TABLE IF EXISTS `ft_sys_org`;

CREATE TABLE `ft_sys_org` (
  `org_id` varchar(32) NOT NULL COMMENT '机构id',
  `org_code` varchar(12) NOT NULL COMMENT '机构编号',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '上级机构id',
  `org_name` varchar(100) NOT NULL COMMENT '机构名称',
  `tree_node` char(1) DEFAULT NULL COMMENT '是否是叶子节点  1-树枝， 2-树叶',
  `org_main_categroy` varchar(5) DEFAULT NULL COMMENT '机构大类',
  `org_sub_categroy` varchar(10) DEFAULT NULL COMMENT '机构细类（大类再细分类）',
  `remarks` text COMMENT '机构介绍',
  `sort` int(10) DEFAULT NULL COMMENT '机构排序',
  `status` char(1) DEFAULT '1' COMMENT '状态（1正常 2停用）',
  `create_by` varchar(100) DEFAULT NULL COMMENT '记录创建人（id:name）',
  `create_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  `last_update_by` varchar(100) DEFAULT NULL COMMENT '记录最后一次操作人（id:name）',
  `last_update_time` datetime DEFAULT NULL COMMENT '记录最后一次操作时间',
  `del_flag` char(1) DEFAULT 'N' COMMENT '记录删除标志（Y-逻辑删除， N-未删除）',
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构表';

/*Data for the table `ft_sys_org` */

/*Table structure for table `ft_sys_org_post` */

DROP TABLE IF EXISTS `ft_sys_org_post`;

CREATE TABLE `ft_sys_org_post` (
  `org_id` varchar(32) DEFAULT NULL COMMENT '机构id',
  `post_id` varchar(32) DEFAULT NULL COMMENT '岗位id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构-岗位关联表';

/*Data for the table `ft_sys_org_post` */

/*Table structure for table `ft_sys_post` */

DROP TABLE IF EXISTS `ft_sys_post`;

CREATE TABLE `ft_sys_post` (
  `post_id` varchar(32) NOT NULL COMMENT '岗位id',
  `post_code` varchar(32) DEFAULT NULL COMMENT '岗位编号',
  `post_name` varchar(100) DEFAULT NULL COMMENT '岗位名称',
  `sort` int(10) DEFAULT NULL COMMENT '排序号',
  `status` char(1) DEFAULT '1' COMMENT '状态（1正常 2停用）',
  `remarks` text COMMENT '岗位描述',
  `create_by` varchar(32) DEFAULT NULL COMMENT '记录创建人（id:name）',
  `create_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  `last_update_by` varchar(32) DEFAULT NULL COMMENT '记录最后一次操作人（id:name）',
  `last_update_time` datetime DEFAULT NULL COMMENT '记录最后一次操作时间',
  `del_flag` char(1) DEFAULT 'N' COMMENT '记录删除标志（Y-逻辑删除， N-未删除）',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='岗位表';

/*Data for the table `ft_sys_post` */

/*Table structure for table `ft_sys_role` */

DROP TABLE IF EXISTS `ft_sys_role`;

CREATE TABLE `ft_sys_role` (
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `role_code` varchar(100) NOT NULL COMMENT '角色代码',
  `role_name` varchar(100) NOT NULL COMMENT '角色名称',
  `remark` text COMMENT '角色描述',
  `sort` int(10) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `status` char(1) DEFAULT '1' COMMENT '状态（1正常 2停用）',
  `create_by` varchar(100) DEFAULT NULL COMMENT '记录创建人（id:name）',
  `create_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  `last_update_by` varchar(100) DEFAULT NULL COMMENT '记录最后一次操作人（id:name）',
  `last_update_time` datetime DEFAULT NULL COMMENT '记录最后一次操作时间',
  `del_flag` char(1) DEFAULT 'N' COMMENT '记录删除标志（Y-逻辑删除， N-未删除）',
  PRIMARY KEY (`role_id`),
  KEY `sort` (`sort`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `ft_sys_role` */

insert  into `ft_sys_role`(`role_id`,`role_code`,`role_name`,`remark`,`sort`,`status`,`create_by`,`create_time`,`last_update_by`,`last_update_time`,`del_flag`) values ('r001','admin','超级管理员','拥有所有权限',1,'1',NULL,NULL,NULL,NULL,'N'),('r002','test01','测试1','测试sssssssssssssssss',2,'2',NULL,NULL,NULL,NULL,'N'),('r003','test02','测试2','aaaaaaaaaaa',3,'1',NULL,NULL,NULL,NULL,'N');

/*Table structure for table `ft_sys_role_menu` */

DROP TABLE IF EXISTS `ft_sys_role_menu`;

CREATE TABLE `ft_sys_role_menu` (
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `menu_id` varchar(32) NOT NULL COMMENT '菜单id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单关联表';

/*Data for the table `ft_sys_role_menu` */

insert  into `ft_sys_role_menu`(`role_id`,`menu_id`) values ('r001','1'),('r001','102'),('r001','104'),('r001','1011'),('r001','1012'),('r001','1013'),('r001','1014'),('r001','1015'),('r001','5cd008ebd5374df18aaf29c68ea749db'),('r001','2'),('r001','1b101138c77849b08ee62d6932f7c49b'),('r001','f2b9a3ab35624e188cbf5a7a651fd8ed'),('r001','9b169c9491844c558122ee16ed3d1c8d'),('r001','93a69f9a95da4018841114296db7c2a7'),('r001','4119361ce85f46d69b2448bb532e82aa'),('r001','9b18457575574d97a2b04cefb4e98567'),('r001','4a4624cf6e2c4f0bbae1c5bedd010ff2'),('r001','1ff14d0a36834527b9371fafc85b1062');

/*Table structure for table `ft_sys_user` */

DROP TABLE IF EXISTS `ft_sys_user`;

CREATE TABLE `ft_sys_user` (
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `login_name` varchar(100) NOT NULL COMMENT '用户登陆用户名',
  `password` varchar(100) NOT NULL COMMENT '登陆密码',
  `real_name` varchar(100) DEFAULT NULL COMMENT '用户名称',
  `salt` varchar(100) NOT NULL COMMENT '密码加密盐',
  `user_type` char(1) DEFAULT '3' COMMENT '用户类型（1-管理用户，2-运维用户，3-普通用户，4-演示用户）',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `gender` char(1) DEFAULT '3' COMMENT '性别（1-男，2-女，3-未知）',
  `head_img` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `sort` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户排序号',
  `status` char(1) DEFAULT '1' COMMENT '状态（1正常 2停用）',
  `create_by` varchar(100) DEFAULT NULL COMMENT '记录创建人（id:name）',
  `create_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  `last_update_by` varchar(100) DEFAULT NULL COMMENT '记录最后一次操作人（id:name）',
  `last_update_time` datetime DEFAULT NULL COMMENT '记录最后一次操作时间',
  `del_flag` char(1) DEFAULT 'N' COMMENT '记录删除标志（Y-逻辑删除， N-未删除）',
  PRIMARY KEY (`user_id`),
  KEY `sort` (`sort`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `ft_sys_user` */

insert  into `ft_sys_user`(`user_id`,`login_name`,`password`,`real_name`,`salt`,`user_type`,`email`,`mobile`,`gender`,`head_img`,`sort`,`status`,`create_by`,`create_time`,`last_update_by`,`last_update_time`,`del_flag`) values ('935684a9646643158b8a11d507f0efd3','fsdfaaaaaaaaaaaaaa','123456','法萨芬aa','abcd==','2','SuperC@zjtlcb.com','15810124512','2',NULL,4,'2','admin:admin','2019-03-13 09:41:15','admin:admin','2019-03-13 14:07:20','N'),('u001','admin','0f5531082dbaee9ad28d86d8da988124','admin','Cxc0624==','1','admin@sunny.com','15810124591','1',NULL,1,'1','db','2018-07-10 09:36:57','admin:admin','2019-03-11 14:19:23','N'),('u002','manager','0f5531082dbaee9ad28d86d8da988124','管理员','Cxc0624==','2','manage@sunny.com','17367147768','2',NULL,2,'1',NULL,NULL,NULL,NULL,'N'),('u003','test','0f5531082dbaee9ad28d86d8da988124','普通测试用户','Cxc0624==','3','test@sunny.com','11111111111','3',NULL,3,'1',NULL,NULL,'admin:admin','2019-03-13 09:41:30','N');

/*Table structure for table `ft_sys_user_org` */

DROP TABLE IF EXISTS `ft_sys_user_org`;

CREATE TABLE `ft_sys_user_org` (
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `org_id` varchar(32) NOT NULL COMMENT '机构id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-机构关联表';

/*Data for the table `ft_sys_user_org` */

/*Table structure for table `ft_sys_user_post` */

DROP TABLE IF EXISTS `ft_sys_user_post`;

CREATE TABLE `ft_sys_user_post` (
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `post_id` varchar(32) DEFAULT NULL COMMENT '岗位id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-岗位关联表';

/*Data for the table `ft_sys_user_post` */

/*Table structure for table `ft_sys_user_role` */

DROP TABLE IF EXISTS `ft_sys_user_role`;

CREATE TABLE `ft_sys_user_role` (
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色关联表';

/*Data for the table `ft_sys_user_role` */

insert  into `ft_sys_user_role`(`user_id`,`role_id`) values ('u001','r001');

/*Table structure for table `t_data_clean_rule` */

DROP TABLE IF EXISTS `t_data_clean_rule`;

CREATE TABLE `t_data_clean_rule` (
  `rule_id` varchar(32) NOT NULL,
  `rule_name` varchar(100) DEFAULT NULL,
  `cron` varchar(100) DEFAULT NULL,
  `rule_strategy_type` varchar(2) DEFAULT NULL,
  `rule_type` varchar(2) DEFAULT NULL,
  `days` varchar(10) DEFAULT NULL,
  `is_default` varchar(1) DEFAULT NULL,
  `remarks` text,
  `bind_count` int(10) DEFAULT NULL,
  `sort` int(10) NOT NULL AUTO_INCREMENT,
  `status` char(1) DEFAULT '1',
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_by` varchar(32) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT 'N',
  PRIMARY KEY (`rule_id`),
  KEY `sort` (`sort`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_data_clean_rule` */

insert  into `t_data_clean_rule`(`rule_id`,`rule_name`,`cron`,`rule_strategy_type`,`rule_type`,`days`,`is_default`,`remarks`,`bind_count`,`sort`,`status`,`create_by`,`create_time`,`last_update_by`,`last_update_time`,`del_flag`) values ('0978c8f01c934e08a55384af51742876','aa','aa','1','1','4','1','aa',1,1,'1','admin:admin','2019-04-01 10:55:21','admin:admin','2019-04-01 10:55:21','N'),('8149b39b60464fd8a59bbd2b2b826b6b','bb','bb','1','1','3','1','dfsfsdf',NULL,3,'1','admin:admin','2019-04-01 13:37:47','admin:admin','2019-04-01 13:37:47','N');

/*Table structure for table `t_data_clean_task` */

DROP TABLE IF EXISTS `t_data_clean_task`;

CREATE TABLE `t_data_clean_task` (
  `task_id` varchar(32) NOT NULL,
  `task_name` varchar(100) DEFAULT NULL,
  `task_group` varchar(100) DEFAULT NULL,
  `run_status` char(1) DEFAULT NULL,
  `params` varchar(1000) DEFAULT NULL,
  `task_method` varchar(100) DEFAULT NULL,
  `db_id` varchar(32) DEFAULT NULL,
  `db_name` varchar(100) DEFAULT NULL,
  `table_id` varchar(32) DEFAULT NULL,
  `table_name` varchar(100) DEFAULT NULL,
  `rule_id` varchar(32) DEFAULT NULL,
  `rule_name` varchar(100) DEFAULT NULL,
  `sort` int(10) DEFAULT NULL,
  `status` char(1) DEFAULT '1',
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_by` varchar(32) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT 'N',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_data_clean_task` */

/*Table structure for table `t_db_column` */

DROP TABLE IF EXISTS `t_db_column`;

CREATE TABLE `t_db_column` (
  `id` varchar(32) NOT NULL,
  `table_id` varchar(32) DEFAULT NULL,
  `table_name` varchar(200) DEFAULT NULL,
  `column_name` varchar(200) DEFAULT NULL,
  `column_comment` varchar(255) DEFAULT NULL,
  `data_type` varchar(200) DEFAULT NULL,
  `is_pk` char(1) DEFAULT NULL,
  `sort` int(10) NOT NULL AUTO_INCREMENT,
  `status` char(1) DEFAULT NULL,
  `del_flag` char(1) DEFAULT 'N',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_by` varchar(255) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sort` (`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_db_column` */

/*Table structure for table `t_db_data_base` */

DROP TABLE IF EXISTS `t_db_data_base`;

CREATE TABLE `t_db_data_base` (
  `db_id` varchar(32) NOT NULL,
  `db_name` varchar(100) DEFAULT NULL,
  `owner` varchar(100) DEFAULT NULL,
  `db_type` char(2) DEFAULT NULL,
  `db_ip` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `sort` int(10) DEFAULT NULL,
  `is_default` char(1) DEFAULT NULL,
  `status` char(1) DEFAULT '1',
  `create_by` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_by` varchar(100) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT 'N',
  PRIMARY KEY (`db_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_db_data_base` */

insert  into `t_db_data_base`(`db_id`,`db_name`,`owner`,`db_type`,`db_ip`,`username`,`password`,`sort`,`is_default`,`status`,`create_by`,`create_time`,`last_update_by`,`last_update_time`,`del_flag`) values ('3b508b76987c44779f3e112fe1f93562','db_dynamic002','db_dynamic002','1','127.0.0.1','root','root',NULL,'2','1','admin:admin','2019-03-29 09:13:11','admin:admin','2019-03-29 09:13:11','N'),('9708328452864639aaea81aaa2282144','db_dynamic001','db_dynamic001','1','127.0.0.1','root','root',NULL,'2','1','admin:admin','2019-03-29 09:12:10','admin:admin','2019-03-29 09:12:10','N'),('b2c2d0d8c508470ba90c8fd70debfdff','db_sunny','db_sunny','1','127.0.0.1','root','root',NULL,'1','1','admin:admin','2019-03-28 16:03:45','admin:admin','2019-03-28 16:03:45','N');

/*Table structure for table `t_db_table` */

DROP TABLE IF EXISTS `t_db_table`;

CREATE TABLE `t_db_table` (
  `id` varchar(32) NOT NULL,
  `table_name` varchar(200) DEFAULT NULL,
  `table_comment` varchar(255) DEFAULT NULL,
  `column_name` varchar(200) DEFAULT NULL,
  `column_comment` varchar(255) DEFAULT NULL,
  `data_type` varchar(100) DEFAULT NULL,
  `is_column` char(1) DEFAULT '2',
  `produced` char(1) DEFAULT NULL,
  `db_id` varchar(32) DEFAULT NULL,
  `db_name` varchar(100) DEFAULT NULL,
  `sort` int(10) NOT NULL AUTO_INCREMENT,
  `status` char(1) DEFAULT NULL,
  `del_flag` char(1) DEFAULT 'N',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_by` varchar(255) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sort` (`sort`)
) ENGINE=InnoDB AUTO_INCREMENT=1643 DEFAULT CHARSET=utf8;

/*Data for the table `t_db_table` */

insert  into `t_db_table`(`id`,`table_name`,`table_comment`,`column_name`,`column_comment`,`data_type`,`is_column`,`produced`,`db_id`,`db_name`,`sort`,`status`,`del_flag`,`create_by`,`create_time`,`last_update_by`,`last_update_time`) values ('020f22ee506c4ca2a6ecd972d93acbbd','ft_sys_role',NULL,'create_time','记录创建时间','datetime','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1564,'1','N',NULL,'2019-03-29 21:16:36',NULL,NULL),('0303bc283b2c4c65a8d0b705e56e7a9b','ft_sys_post',NULL,'post_id','岗位id','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1546,'1','N',NULL,'2019-03-29 21:16:36',NULL,NULL),('03908b08483a4f14a99c6086771d6dd2','ft_sys_post',NULL,'del_flag','记录删除标志（Y-逻辑删除， N-未删除）','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1556,'1','N',NULL,'2019-03-29 21:16:36',NULL,NULL),('05eb4fbcfaa841eab5602175ec8681c1','dynamic002_sys_dict_value',NULL,'last_update_by','记录最后一次操作人（id:name）','varchar','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1460,'1','N',NULL,'2019-03-29 21:16:32',NULL,NULL),('0904ca807bb049eba500b06f0c9fdb5e','t_db_table',NULL,'data_type','','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1613,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('092fa69aadf9432eb49a25ccba853df6','dynamic002_sys_dict_value',NULL,'create_time','记录创建时间','datetime','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1459,'1','N',NULL,'2019-03-29 21:16:32',NULL,NULL),('0a03c131eaf54e43b5d336ae09c7e724','dynamic001_sys_dict_type',NULL,'del_flag','记录删除标志（Y-逻辑删除， N-未删除）','char','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1473,'1','N',NULL,'2019-03-29 21:16:32',NULL,NULL),('0b5872588e084df3956a88e3708ea890','ft_sys_user_role',NULL,'user_id','用户id','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1591,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('0c5af6e07e364eb0ad28b0eaea9a45d7','ft_sys_post',NULL,'create_time','记录创建时间','datetime','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1553,'1','N',NULL,'2019-03-29 21:16:36',NULL,NULL),('0ccc6efa36894c76bc691e85648b0a58','ft_sys_dict_value','字典值表',NULL,NULL,NULL,'2',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1630,'1','N',NULL,'2019-03-29 21:16:39',NULL,NULL),('0d79b85389ec4d40a6f7dc03c5e7a481','ft_sys_post','岗位表',NULL,NULL,NULL,'2',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1634,'1','N',NULL,'2019-03-29 21:16:39',NULL,NULL),('0e16804a35634460b020b87b97c2e010','ft_sys_menu',NULL,'tree_node','树节点','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1523,'1','N',NULL,'2019-03-29 21:16:35',NULL,NULL),('0e239c5200d04698ba23c109672efc3b','ft_sys_menu',NULL,'create_time','记录创建时间','datetime','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1525,'1','N',NULL,'2019-03-29 21:16:35',NULL,NULL),('0e73537d679e44f09323be0392b85218','t_db_table',NULL,'id','','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1608,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('133b185b8c3845de820cceb28370521f','dynamic001_sys_dict_value',NULL,'del_flag','记录删除标志（Y-逻辑删除， N-未删除）','char','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1487,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('1407646eede5486c95fa76fb2f31ea64','ft_sys_dict_value',NULL,'last_update_time','记录最后一次操作时间','datetime','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1511,'1','N',NULL,'2019-03-29 21:16:34',NULL,NULL),('1446d443539e42a69820f5fcad1f322b','ft_sys_org','机构表',NULL,NULL,NULL,'2',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1632,'1','N',NULL,'2019-03-29 21:16:39',NULL,NULL),('1576cdad3dd3433898f968bfbf5ff817','ft_sys_user',NULL,'salt','密码加密盐','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1574,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('16b226449fd04e498baf611896f32b09','ft_sys_org',NULL,'del_flag','记录删除标志（Y-逻辑删除， N-未删除）','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1543,'1','N',NULL,'2019-03-29 21:16:35',NULL,NULL),('1c18db0489f3457ca319d3f4123fd929','dynamic002_sys_dict_value',NULL,'dict_type_key','字典类型key，冗余字段','varchar','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1453,'1','N',NULL,'2019-03-29 21:16:31',NULL,NULL),('1ca88c2fbaa34e0994496501809826f1','ft_sys_user',NULL,'login_name','用户登陆用户名','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1571,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('1f851ff42d2349beb830cb1d240caf10','ft_sys_user',NULL,'mobile','手机号','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1577,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('1fd13447f91c4402ba523c58d80d3977','t_db_table',NULL,'last_update_time','','datetime','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1624,'1','N',NULL,'2019-03-29 21:16:39',NULL,NULL),('213e476d78284db2a42c8e6e3d8c4a36','ft_sys_role',NULL,'role_id','角色id','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1557,'1','N',NULL,'2019-03-29 21:16:36',NULL,NULL),('258257485c574a6f878893957b3877e3','ft_sys_dict_type',NULL,'dict_type_key','字典类型key','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1489,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('2847c443471943009c86587f2f0fcf38','t_db_table',NULL,'create_by','','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1621,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('29d7929bd0524c358b360b8200d942a6','t_db_table',NULL,'column_name','','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1611,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('2a29d36b0e774c7290f5738f5081be08','ft_sys_dict_type','字典类型表',NULL,NULL,NULL,'2',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1629,'1','N',NULL,'2019-03-29 21:16:39',NULL,NULL),('2c8fa758bc3242d6924500c022df3fc9','t_db_table',NULL,'is_column','','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1614,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('2d08a6be12cb4c75a0604355c5a09169','dynamic002_sys_dict_value',NULL,'dict_type_id','字典类型id,外键','varchar','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1452,'1','N',NULL,'2019-03-29 21:16:31',NULL,NULL),('2d407bca9bf748748c0d8613fa624a27','ft_sys_user',NULL,'status','状态（1正常 2停用）','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1581,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('300ae377e32449f98fc475a4b761596c','t_db_data_base',NULL,'db_id','','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1593,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('3143acf69240412a9e8e09569f0a8668','ft_sys_user',NULL,'password','登陆密码','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1572,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('3189525c0a1a48719ae7a4b304c5cf31','t_db_data_base',NULL,'del_flag','','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1607,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('3303a5ff3eb0439b8ac89e50068728a4','ft_sys_dict_value',NULL,'css_class','字典标签样式','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1505,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('333c1824c99f4843af19a6dd51de4803','t_db_table',NULL,'create_time','','datetime','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1622,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('36100c57b0034690a69fb8135d78fb92','ft_sys_dict_type',NULL,'dict_type_id','字典类型主键id','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1488,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('36b260c7a0384052a3653c213b9f8cb6','ft_sys_user',NULL,'head_img','用户头像','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1579,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('36ffe01199a5453bb35c82ba4d0056b8','dynamic001_sys_dict_value',NULL,'create_by','记录创建人（id:name）','varchar','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1483,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('387c2b5c7ec8492c990d4d5d0e89b1ea','ft_sys_dict_type',NULL,'sort','字典类型排序字段','int','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1491,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('3b26cd70415e4c7fb69efdb74755fb44','dynamic001_sys_dict_value',NULL,'dict_value','字典值','varchar','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1476,'1','N',NULL,'2019-03-29 21:16:32',NULL,NULL),('3befc5f91a53499190bc756bfe24f811','dynamic002_sys_dict_type',NULL,'del_flag','记录删除标志（Y-逻辑删除， N-未删除）','char','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1448,'1','N',NULL,'2019-03-29 21:16:31',NULL,NULL),('3c3af159ca024039be208b16010b2f4b','dynamic001_sys_dict_value','字典值表',NULL,NULL,NULL,'2',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1628,'1','N',NULL,'2019-03-29 21:16:39',NULL,NULL),('3e3c05e9074347f7be3a025e80106fbd','dynamic001_sys_dict_type',NULL,'create_time','记录创建时间','datetime','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1470,'1','N',NULL,'2019-03-29 21:16:32',NULL,NULL),('3ff649bbf9cc47e1b7eba5f47514876a','dynamic002_sys_dict_value',NULL,'last_update_time','记录最后一次操作时间','datetime','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1461,'1','N',NULL,'2019-03-29 21:16:32',NULL,NULL),('431173d9a21a45b882b6c08148ce0433','ft_sys_menu',NULL,'create_by','记录创建人（id:name）','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1524,'1','N',NULL,'2019-03-29 21:16:35',NULL,NULL),('43603a03888642b4a5a5a0661aa16e37','dynamic002_sys_dict_value',NULL,'create_by','记录创建人（id:name）','varchar','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1458,'1','N',NULL,'2019-03-29 21:16:32',NULL,NULL),('43e5fb9697cd4b70b4c8f012657f42fd','dynamic001_sys_dict_type','字典类型表',NULL,NULL,NULL,'2',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1627,'1','N',NULL,'2019-03-29 21:16:39',NULL,NULL),('4409372e8c6849688ae5f55481a7c68c','ft_sys_dict_value',NULL,'last_update_by','记录最后一次操作人（id:name）','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1510,'1','N',NULL,'2019-03-29 21:16:34',NULL,NULL),('447907a3ed4f44f686c94aa8cc53c832','ft_sys_dict_value',NULL,'create_time','记录创建时间','datetime','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1509,'1','N',NULL,'2019-03-29 21:16:34',NULL,NULL),('45b8073687794b97a9d149e398b7883d','ft_sys_dict_value',NULL,'del_flag','记录删除标志（Y-逻辑删除， N-未删除）','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1512,'1','N',NULL,'2019-03-29 21:16:34',NULL,NULL),('45c46b4716ba48b2b29f7ccaeb45cb08','ft_sys_dict_type',NULL,'last_update_time','记录最后一次操作时间','datetime','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1497,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('49d3d7297bbc4b11964ee4d2158d7345','ft_sys_menu',NULL,'parent_id','上级菜单id','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1514,'1','N',NULL,'2019-03-29 21:16:34',NULL,NULL),('4e76df1c293e40179c28b8d594a9118f','ft_sys_org',NULL,'org_id','机构id','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1529,'1','N',NULL,'2019-03-29 21:16:35',NULL,NULL),('513769e2198947279ea959d14bda65bd','ft_sys_menu',NULL,'last_update_time','记录最后一次操作时间','datetime','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1527,'1','N',NULL,'2019-03-29 21:16:35',NULL,NULL),('53394c9268254053892ff95cd6ea3461','ft_sys_dict_value',NULL,'is_default','是否默认（Y-是，N-否）','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1506,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('55c6eb60a64145448b974250c45469c1','ft_sys_org_post','机构-岗位关联表',NULL,NULL,NULL,'2',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1633,'1','N',NULL,'2019-03-29 21:16:39',NULL,NULL),('55d88e9a9a1142a8836859e8fec6c5fd','ft_sys_org',NULL,'create_by','记录创建人（id:name）','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1539,'1','N',NULL,'2019-03-29 21:16:35',NULL,NULL),('55ea520a72ec43e79bb41d72971a0be3','ft_sys_menu',NULL,'del_flag','记录删除标志（Y-逻辑删除， N-未删除）','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1528,'1','N',NULL,'2019-03-29 21:16:35',NULL,NULL),('5675ad1cd179471780e17816cb9af7fb','ft_sys_dict_value',NULL,'dict_type_id','字典类型id,外键','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1502,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('583adbe5b01b460babbfe0e0b6e00a90','dynamic001_sys_dict_type',NULL,'status','状态（1正常 2停用）','char','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1467,'1','N',NULL,'2019-03-29 21:16:32',NULL,NULL),('5b28d8bc802240409ea0368604681f2c','dynamic002_sys_dict_value',NULL,'css_class','字典标签样式','varchar','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1455,'1','N',NULL,'2019-03-29 21:16:31',NULL,NULL),('5bd8784c3e5740f580bde92ee865fafe','ft_sys_dict_value',NULL,'dict_label','字典标签','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1500,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('5c2759d0bf7c4f0bb997f9d6cbd8c8b2','t_db_table',NULL,'produced','','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1615,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('5d47a121a8264bf9856273beae1e8ef4','ft_sys_user',NULL,'last_update_by','记录最后一次操作人（id:name）','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1584,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('5e8bdd2a856e42b2af6a6c481028f964','ft_sys_org',NULL,'org_name','机构名称','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1532,'1','N',NULL,'2019-03-29 21:16:35',NULL,NULL),('5fd0182c19a045649910fc232966eed5','ft_sys_role',NULL,'remark','角色描述','text','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1560,'1','N',NULL,'2019-03-29 21:16:36',NULL,NULL),('5fd267c8b9ff4189ade41e791c315408','ft_sys_org',NULL,'parent_id','上级机构id','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1531,'1','N',NULL,'2019-03-29 21:16:35',NULL,NULL),('60b289a6075e4c14b12055976d030951','ft_sys_menu',NULL,'url','菜单url链接','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1516,'1','N',NULL,'2019-03-29 21:16:34',NULL,NULL),('6410b6aab9354bd3879f31db226658ee','ft_sys_post',NULL,'create_by','记录创建人（id:name）','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1552,'1','N',NULL,'2019-03-29 21:16:36',NULL,NULL),('66863bc7fc1b4b5198cdd5a34062f6fb','ft_sys_org',NULL,'org_sub_categroy','机构细类（大类再细分类）','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1535,'1','N',NULL,'2019-03-29 21:16:35',NULL,NULL),('66d19ae4406c41df9f9e25e8682c8cad','ft_sys_role_menu',NULL,'menu_id','菜单id','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1569,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('66ffdf0637ce412da0fb74d82359da5f','ft_sys_user',NULL,'sort','用户排序号','int','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1580,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('67db4c4c42814bde9eb01cc66480514b','dynamic001_sys_dict_type',NULL,'last_update_by','记录最后一次操作人（id:name）','varchar','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1471,'1','N',NULL,'2019-03-29 21:16:32',NULL,NULL),('689a39456f15424ab4cd07d0a132f5c2','t_db_data_base',NULL,'db_ip','','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1597,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('69d47881f3874a5b9566a4849d8905c4','dynamic001_sys_dict_value',NULL,'sort','字典排序','int','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1479,'1','N',NULL,'2019-03-29 21:16:32',NULL,NULL),('6a1943ef65684720acb2961e214345c5','ft_sys_menu',NULL,'menu_id','菜单id','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1513,'1','N',NULL,'2019-03-29 21:16:34',NULL,NULL),('6bd05ddc7bf54bc4a89245734fa155d6','ft_sys_menu',NULL,'last_update_by','记录最后一次操作人（id:name）','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1526,'1','N',NULL,'2019-03-29 21:16:35',NULL,NULL),('6cd6eb27c39145eabcb42563da669099','ft_sys_role_menu','角色-菜单关联表',NULL,NULL,NULL,'2',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1636,'1','N',NULL,'2019-03-29 21:16:39',NULL,NULL),('6eaa237a1ee641ffba9d7023ed6b05db','dynamic002_sys_dict_value',NULL,'dict_value','字典值','varchar','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1451,'1','N',NULL,'2019-03-29 21:16:31',NULL,NULL),('7419f92c1c264b5aa459218fe41137b2','ft_sys_user_role','用户-角色关联表',NULL,NULL,NULL,'2',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1640,'1','N',NULL,'2019-03-29 21:16:39',NULL,NULL),('778950e8243546e8ad3d51208f14bce6','t_db_table','',NULL,NULL,NULL,'2',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1642,'1','N',NULL,'2019-03-29 21:16:39',NULL,NULL),('78c6bc9c3cf04139abc4407d2b3f8cba','ft_sys_post',NULL,'last_update_time','记录最后一次操作时间','datetime','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1555,'1','N',NULL,'2019-03-29 21:16:36',NULL,NULL),('7afa6bcd78b14f2498126f04dd21fd59','dynamic002_sys_dict_value',NULL,'is_default','是否默认（Y-是，N-否）','char','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1456,'1','N',NULL,'2019-03-29 21:16:31',NULL,NULL),('7d21e900fdd94979be3ed6c7638d50bc','ft_sys_menu',NULL,'menu_name','菜单名称','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1515,'1','N',NULL,'2019-03-29 21:16:34',NULL,NULL),('7e8690b58085472a8e1a656ae1abaef3','ft_sys_dict_value',NULL,'dict_value_id','字典主键id','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1499,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('7efb06c7840743d0ba920494e75d7261','ft_sys_user_org',NULL,'org_id','机构id','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1588,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('7f704ec478884738a8c5a71d4e9dea83','ft_sys_role',NULL,'del_flag','记录删除标志（Y-逻辑删除， N-未删除）','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1567,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('7f91e4c46f1a4052adc7230971878d1c','t_db_table',NULL,'last_update_by','','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1623,'1','N',NULL,'2019-03-29 21:16:39',NULL,NULL),('801e560c17d34b4991d77fdfb0a74dd9','ft_sys_user',NULL,'create_time','记录创建时间','datetime','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1583,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('81e0264ad58c47b6ac9abf25cc8c9f9e','dynamic002_sys_dict_value',NULL,'sort','字典排序','int','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1454,'1','N',NULL,'2019-03-29 21:16:31',NULL,NULL),('81fd7898d22741248742d95dc928133b','t_db_data_base',NULL,'password','','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1599,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('83387011d0ff488884b0432ca77b6bc7','t_db_data_base',NULL,'create_time','','datetime','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1604,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('850f42d449024b4ca2009e153c96c4dd','dynamic001_sys_dict_value',NULL,'is_default','是否默认（Y-是，N-否）','char','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1481,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('85615005df8f4fedb2a089a5c4fcdeae','t_db_data_base',NULL,'last_update_time','','datetime','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1606,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('85aa7ef79a9849d5b22273c5c2159900','dynamic001_sys_dict_value',NULL,'status','状态（1正常 2停用）','char','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1482,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('867453de965c4da2932847c6128271f8','t_db_data_base',NULL,'status','','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1602,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('8884c3aa12da4ccdb4d620c57a199ac9','ft_sys_dict_type',NULL,'last_update_by','记录最后一次操作人（id:name）','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1496,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('8b1c4d64f5394c82b3aa5cff3af27a6a','ft_sys_role',NULL,'status','状态（1正常 2停用）','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1562,'1','N',NULL,'2019-03-29 21:16:36',NULL,NULL),('8b315fa922b248b69c82d55c7eed8b86','ft_sys_dict_value',NULL,'dict_value','字典值','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1501,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('8bc3702b6fb74590879e23ba2b3651e8','ft_sys_menu',NULL,'perms','菜单权限','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1519,'1','N',NULL,'2019-03-29 21:16:34',NULL,NULL),('8c92812569d24311a53b6ff4a79fc941','t_db_table',NULL,'db_id','','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1616,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('8cc2bffc54594671a99bef7ac5a042aa','ft_sys_role','角色表',NULL,NULL,NULL,'2',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1635,'1','N',NULL,'2019-03-29 21:16:39',NULL,NULL),('8d487b6556324aec8a316599e626a07b','dynamic001_sys_dict_type',NULL,'sort','字典类型排序字段','int','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1466,'1','N',NULL,'2019-03-29 21:16:32',NULL,NULL),('8d6d400df28b449a931bc125ae1409a3','ft_sys_user',NULL,'create_by','记录创建人（id:name）','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1582,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('8d98a823ef3249ed93a9608e39f5a0e4','ft_sys_post',NULL,'sort','排序号','int','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1549,'1','N',NULL,'2019-03-29 21:16:36',NULL,NULL),('8ee72bba59f449ecb2dfb880c7a39ff4','ft_sys_user',NULL,'gender','性别（1-男，2-女，3-未知）','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1578,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('8efb57686c014502b5fa557a0fb1ef26','dynamic001_sys_dict_value',NULL,'last_update_time','记录最后一次操作时间','datetime','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1486,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('9068aa9495014efabb25ec70d2f935d8','dynamic002_sys_dict_value','字典值表',NULL,NULL,NULL,'2',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1626,'1','N',NULL,'2019-03-29 21:16:39',NULL,NULL),('90a0ee1b249d4bbc8f7a0ea29e3441eb','ft_sys_role',NULL,'sort','排序','int','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1561,'1','N',NULL,'2019-03-29 21:16:36',NULL,NULL),('9231bb8671a64c7fa77044a2f0e43ed0','ft_sys_post',NULL,'last_update_by','记录最后一次操作人（id:name）','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1554,'1','N',NULL,'2019-03-29 21:16:36',NULL,NULL),('96aed1c2d9ef4ceb970f3f635caed6f9','dynamic001_sys_dict_type',NULL,'has_values','该类型下是否有对应的字典值（1-有，2无）','char','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1468,'1','N',NULL,'2019-03-29 21:16:32',NULL,NULL),('972c556fc78d460280cd17f5e6758da1','ft_sys_org',NULL,'org_main_categroy','机构大类','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1534,'1','N',NULL,'2019-03-29 21:16:35',NULL,NULL),('9916c3e698c24707bce42b884ae85786','ft_sys_user_post',NULL,'post_id','岗位id','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1590,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('9a27fc515698454f98cf03686429bab8','ft_sys_menu','菜单表',NULL,NULL,NULL,'2',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1631,'1','N',NULL,'2019-03-29 21:16:39',NULL,NULL),('9af45de4bde94d0db6f23e6f83b5532b','dynamic002_sys_dict_type',NULL,'dict_type_key','字典类型key','varchar','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1439,'1','N',NULL,'2019-03-29 21:16:31',NULL,NULL),('9af47ed98b5245209b6b63db58657029','dynamic002_sys_dict_value',NULL,'dict_label','字典标签','varchar','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1450,'1','N',NULL,'2019-03-29 21:16:31',NULL,NULL),('9b39e0a8d3f944d7b55cc5c0129d54e8','ft_sys_dict_value',NULL,'sort','字典排序','int','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1504,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('9c5cc6e6d88245baa206c8e2aa792de8','ft_sys_dict_type',NULL,'create_time','记录创建时间','datetime','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1495,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('9dd05984086b4345a7883f4c54ecec6b','dynamic001_sys_dict_value',NULL,'dict_label','字典标签','varchar','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1475,'1','N',NULL,'2019-03-29 21:16:32',NULL,NULL),('9f3f55eb5cc540b5a72bbf2173e055c6','ft_sys_dict_type',NULL,'dict_type_name','字典类型名称','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1490,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('a19af90d3afc40dbbe2f2e394395f5ab','dynamic001_sys_dict_type',NULL,'dict_type_id','字典类型主键id','varchar','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1463,'1','N',NULL,'2019-03-29 21:16:32',NULL,NULL),('a1ebbdd320d540d3849a0eeb616795e7','t_db_table',NULL,'table_comment','','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1610,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('a2e8d36a9ae54d79b1590684a38a7c88','dynamic001_sys_dict_value',NULL,'dict_type_id','字典类型id,外键','varchar','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1477,'1','N',NULL,'2019-03-29 21:16:32',NULL,NULL),('a3f9396f07f541ad97056d7fcdf8f630','ft_sys_org_post',NULL,'post_id','岗位id','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1545,'1','N',NULL,'2019-03-29 21:16:36',NULL,NULL),('a5d4947139c149b3b783de261db92e91','t_db_data_base',NULL,'last_update_by','','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1605,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('a68bf7388dc14d73ae7b4c2cf04037ab','ft_sys_user','用户表',NULL,NULL,NULL,'2',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1637,'1','N',NULL,'2019-03-29 21:16:39',NULL,NULL),('a8f29ba4881a448bb98a3da8a46effff','dynamic001_sys_dict_type',NULL,'last_update_time','记录最后一次操作时间','datetime','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1472,'1','N',NULL,'2019-03-29 21:16:32',NULL,NULL),('a970c04fc52d45fe9a725ff455815ffe','dynamic002_sys_dict_value',NULL,'del_flag','记录删除标志（Y-逻辑删除， N-未删除）','char','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1462,'1','N',NULL,'2019-03-29 21:16:32',NULL,NULL),('aa9c75433cb8454ba9b3e04d57fbeea5','t_db_table',NULL,'column_comment','','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1612,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('aae65fe0b3844c148163f647540bc453','ft_sys_user_org','用户-机构关联表',NULL,NULL,NULL,'2',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1638,'1','N',NULL,'2019-03-29 21:16:39',NULL,NULL),('aba7a486131e48e3b41494b4a2f8331b','dynamic002_sys_dict_type',NULL,'has_values','该类型下是否有对应的字典值（1-有，2无）','char','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1443,'1','N',NULL,'2019-03-29 21:16:31',NULL,NULL),('ac610b818f77438a94f1f2b37039aecb','t_db_data_base',NULL,'create_by','','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1603,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('aea2f251076c4926b9b59b5cea2dd993','dynamic001_sys_dict_value',NULL,'dict_value_id','字典主键id','varchar','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1474,'1','N',NULL,'2019-03-29 21:16:32',NULL,NULL),('b1c22faadc6149a28d44807d33ecbf01','ft_sys_org',NULL,'create_time','记录创建时间','datetime','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1540,'1','N',NULL,'2019-03-29 21:16:35',NULL,NULL),('b2412b57098a4c479e1b6ff1cf14eee4','dynamic002_sys_dict_type',NULL,'last_update_time','记录最后一次操作时间','datetime','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1447,'1','N',NULL,'2019-03-29 21:16:31',NULL,NULL),('b570c61e6efd4074a7db5ed7c3f9fcc1','ft_sys_org',NULL,'last_update_time','记录最后一次操作时间','datetime','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1542,'1','N',NULL,'2019-03-29 21:16:35',NULL,NULL),('b6e94928fe864b3c8ae28aa3bc709f9a','ft_sys_menu',NULL,'status','状态（1正常 2停用）','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1522,'1','N',NULL,'2019-03-29 21:16:35',NULL,NULL),('b853820ecb8e439791efcb17d73eea2a','ft_sys_post',NULL,'post_code','岗位编号','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1547,'1','N',NULL,'2019-03-29 21:16:36',NULL,NULL),('ba6dfd2b44a54921a1dc8e762f6973ef','dynamic002_sys_dict_value',NULL,'dict_value_id','字典主键id','varchar','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1449,'1','N',NULL,'2019-03-29 21:16:31',NULL,NULL),('ba92c72647fe4a6b8e52ed9d83578b90','dynamic002_sys_dict_value',NULL,'status','状态（1正常 2停用）','char','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1457,'1','N',NULL,'2019-03-29 21:16:32',NULL,NULL),('bccb912c6e1d4a57a7befd0462b45f0b','t_db_data_base',NULL,'sort','','int','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1600,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('bd1af5a0a2a2432ca6801361e8c2f320','ft_sys_user',NULL,'user_id','用户id','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1570,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('be10110f6aee472bb4885d6a81c52c81','ft_sys_user',NULL,'last_update_time','记录最后一次操作时间','datetime','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1585,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('c025f825867246f4af22706f8fbebb03','ft_sys_role',NULL,'role_name','角色名称','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1559,'1','N',NULL,'2019-03-29 21:16:36',NULL,NULL),('c1b154b45b714d5f8ce2a1334c32f362','dynamic002_sys_dict_type',NULL,'dict_type_id','字典类型主键id','varchar','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1438,'1','N',NULL,'2019-03-29 21:16:31',NULL,NULL),('c2d82e388b54488792d9fb738571b581','ft_sys_org',NULL,'last_update_by','记录最后一次操作人（id:name）','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1541,'1','N',NULL,'2019-03-29 21:16:35',NULL,NULL),('c3ea286614f94302b07523f941f344f9','ft_sys_menu',NULL,'menu_type','菜单类型','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1518,'1','N',NULL,'2019-03-29 21:16:34',NULL,NULL),('c4bffeef9f8047e6bb4d0eee6eaae007','dynamic002_sys_dict_type',NULL,'status','状态（1正常 2停用）','char','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1442,'1','N',NULL,'2019-03-29 21:16:31',NULL,NULL),('c54ffd13da5546c7b54b6c16a8e18961','ft_sys_dict_type',NULL,'status','状态（1正常 2停用）','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1492,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('c556eb7e74d5480f917e7049a7a6c50d','ft_sys_user_post','用户-岗位关联表',NULL,NULL,NULL,'2',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1639,'1','N',NULL,'2019-03-29 21:16:39',NULL,NULL),('c8480a7bcf884ca1945e5172cba9ca70','ft_sys_role_menu',NULL,'role_id','角色id','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1568,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('c99d2e4a015c489a843dd5f5e080face','ft_sys_org_post',NULL,'org_id','机构id','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1544,'1','N',NULL,'2019-03-29 21:16:35',NULL,NULL),('cae3a889243f4fb8b8415bc491164b38','ft_sys_dict_type',NULL,'create_by','记录创建人（id:name）','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1494,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('cbb8cd00220a4011968e8e995a3a21f1','t_db_table',NULL,'table_name','','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1609,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('cbe8f5bb5f464701a60e6f6c492592e1','t_db_table',NULL,'del_flag','','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1620,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('cc033cb0e5d34c61aa8d8b594fe889fa','ft_sys_org',NULL,'sort','机构排序','int','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1537,'1','N',NULL,'2019-03-29 21:16:35',NULL,NULL),('ccf015994e21409e86346650cf089f99','ft_sys_dict_value',NULL,'status','状态（1正常 2停用）','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1507,'1','N',NULL,'2019-03-29 21:16:34',NULL,NULL),('cd96b727ad6f4d13974ffbd182252630','ft_sys_menu',NULL,'visible','菜单可见性（1可见 2不可见）','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1520,'1','N',NULL,'2019-03-29 21:16:34',NULL,NULL),('cd9eb6d44d7049f0808be1c91780b0df','dynamic001_sys_dict_value',NULL,'last_update_by','记录最后一次操作人（id:name）','varchar','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1485,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('d0b0e0a193d647899570a6d8e08607f4','ft_sys_org',NULL,'remarks','机构介绍','text','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1536,'1','N',NULL,'2019-03-29 21:16:35',NULL,NULL),('d119ee83ac0b4fa79082052bd8866a07','dynamic002_sys_dict_type','字典类型表',NULL,NULL,NULL,'2',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1625,'1','N',NULL,'2019-03-29 21:16:39',NULL,NULL),('d2c10391d7a94e20bc9549622c53acca','ft_sys_org',NULL,'tree_node','是否是叶子节点  1-树枝， 2-树叶','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1533,'1','N',NULL,'2019-03-29 21:16:35',NULL,NULL),('d73b978df0e2452196eed83ee8868fec','dynamic001_sys_dict_type',NULL,'dict_type_key','字典类型key','varchar','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1464,'1','N',NULL,'2019-03-29 21:16:32',NULL,NULL),('d8a4db9657c3487a88c166fb4720227d','ft_sys_user_role',NULL,'role_id','角色id','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1592,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('daed500b709640c491c85a6d6f366639','dynamic001_sys_dict_value',NULL,'css_class','字典标签样式','varchar','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1480,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('db746445ee61461099965d2a658a7039','t_db_table',NULL,'sort','','int','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1618,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('dbc1bd2f71a8471282ba7a4a5296d1df','ft_sys_role',NULL,'last_update_by','记录最后一次操作人（id:name）','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1565,'1','N',NULL,'2019-03-29 21:16:36',NULL,NULL),('dc299bc0624948b382e06ed70eacd2d4','ft_sys_post',NULL,'remarks','岗位描述','text','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1551,'1','N',NULL,'2019-03-29 21:16:36',NULL,NULL),('dc69a3453fca4811ac213cbc6c206d1f','ft_sys_menu',NULL,'sort','菜单排序','int','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1521,'1','N',NULL,'2019-03-29 21:16:34',NULL,NULL),('dc71a65317bb4c5f8ca9fe34990897fb','ft_sys_role',NULL,'last_update_time','记录最后一次操作时间','datetime','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1566,'1','N',NULL,'2019-03-29 21:16:36',NULL,NULL),('dd50e7cb6f8344c28dc23a55f65dc8e3','ft_sys_user',NULL,'email','邮箱','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1576,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('df26ef98ad914844a7b42cea01c2aace','dynamic002_sys_dict_type',NULL,'create_by','记录创建人（id:name）','varchar','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1444,'1','N',NULL,'2019-03-29 21:16:31',NULL,NULL),('df43a469b04440adb0963310e4e103bb','ft_sys_dict_type',NULL,'has_values','该类型下是否有对应的字典值（1-有，2无）','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1493,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('e1dfe9feee2649c8a0cb5550bee3d626','ft_sys_user',NULL,'user_type','用户类型（1-管理用户，2-运维用户，3-普通用户，4-演示用户）','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1575,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('e24e8a4e417e4a54bf96ed9a98e362bb','ft_sys_org',NULL,'org_code','机构编号','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1530,'1','N',NULL,'2019-03-29 21:16:35',NULL,NULL),('e3abbec9fb2443b08e3180dbc8451744','dynamic002_sys_dict_type',NULL,'dict_type_name','字典类型名称','varchar','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1440,'1','N',NULL,'2019-03-29 21:16:31',NULL,NULL),('e3c53ecd0fbb41d1809a0f24c62cb5d2','dynamic002_sys_dict_type',NULL,'sort','字典类型排序字段','int','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1441,'1','N',NULL,'2019-03-29 21:16:31',NULL,NULL),('e74d377af1bd45d8b0c0d5d62e342ed5','ft_sys_user_post',NULL,'user_id','用户id','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1589,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('e8ccffc4460c406982e66d7cb53d7da9','dynamic001_sys_dict_value',NULL,'create_time','记录创建时间','datetime','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1484,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('e94e6208cd874ef0917783e863886208','ft_sys_dict_value',NULL,'dict_type_key','字典类型key，冗余字段','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1503,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('e9bc7418c1b5413b804f13c5c5c8d01a','t_db_table',NULL,'db_name','','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1617,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('e9d94b9f2cb14c238fe885d2886854ab','t_db_data_base','',NULL,NULL,NULL,'2',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1641,'1','N',NULL,'2019-03-29 21:16:39',NULL,NULL),('eab13f071efb4dd496f1ded76539999a','ft_sys_menu',NULL,'icon','菜单图标','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1517,'1','N',NULL,'2019-03-29 21:16:34',NULL,NULL),('ed17fef5a6564fad965fa60d232d00b9','dynamic002_sys_dict_type',NULL,'last_update_by','记录最后一次操作人（id:name）','varchar','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1446,'1','N',NULL,'2019-03-29 21:16:31',NULL,NULL),('ee38cf5ec4414cf4a31c934c158dca72','t_db_data_base',NULL,'owner','','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1595,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('ef043358b08246f88e0a936e2fda5ca0','ft_sys_post',NULL,'post_name','岗位名称','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1548,'1','N',NULL,'2019-03-29 21:16:36',NULL,NULL),('ef50941a654240a0b3d487360fd7c617','ft_sys_dict_type',NULL,'del_flag','记录删除标志（Y-逻辑删除， N-未删除）','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1498,'1','N',NULL,'2019-03-29 21:16:33',NULL,NULL),('ef690e6c814142288facb054edb5a16d','dynamic001_sys_dict_value',NULL,'dict_type_key','字典类型key，冗余字段','varchar','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1478,'1','N',NULL,'2019-03-29 21:16:32',NULL,NULL),('ef6d4b17b43049ed89ba6a1e136b8218','dynamic001_sys_dict_type',NULL,'dict_type_name','字典类型名称','varchar','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1465,'1','N',NULL,'2019-03-29 21:16:32',NULL,NULL),('efdbf4ad7e074b559d493f67c896079b','dynamic002_sys_dict_type',NULL,'create_time','记录创建时间','datetime','1',NULL,'3b508b76987c44779f3e112fe1f93562','db_dynamic002',1445,'1','N',NULL,'2019-03-29 21:16:31',NULL,NULL),('f142d7c1965e478d91e29bd7eb0fd2f9','t_db_data_base',NULL,'db_name','','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1594,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('f539f29d87bb47fda45e90c2736fd5d4','t_db_data_base',NULL,'is_default','','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1601,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('f6fb1f7bcab24dc4802836bc13e5acf4','ft_sys_role',NULL,'role_code','角色代码','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1558,'1','N',NULL,'2019-03-29 21:16:36',NULL,NULL),('f730ed66c9904716b4e08aec9e98ea72','ft_sys_post',NULL,'status','状态（1正常 2停用）','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1550,'1','N',NULL,'2019-03-29 21:16:36',NULL,NULL),('f814d1205297407b8fa668ce56c1afef','ft_sys_user_org',NULL,'user_id','用户id','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1587,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('f839cd192d124f498107fd0a15eb0703','ft_sys_dict_value',NULL,'create_by','记录创建人（id:name）','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1508,'1','N',NULL,'2019-03-29 21:16:34',NULL,NULL),('f8e9feb770bb47abb0cbc2ed1fa62f44','t_db_table',NULL,'status','','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1619,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('fa16e656441b49ba90ce18f4bbc21ba4','dynamic001_sys_dict_type',NULL,'create_by','记录创建人（id:name）','varchar','1',NULL,'9708328452864639aaea81aaa2282144','db_dynamic001',1469,'1','N',NULL,'2019-03-29 21:16:32',NULL,NULL),('fa2db80121d445baa0b3625b4a7213ab','ft_sys_user',NULL,'del_flag','记录删除标志（Y-逻辑删除， N-未删除）','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1586,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('fb5d26d497314de69f8c69d7b3423c52','ft_sys_org',NULL,'status','状态（1正常 2停用）','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1538,'1','N',NULL,'2019-03-29 21:16:35',NULL,NULL),('fe2e0695d6b5425583ca11211a40e9a9','ft_sys_role',NULL,'create_by','记录创建人（id:name）','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1563,'1','N',NULL,'2019-03-29 21:16:36',NULL,NULL),('fea56d7a475e4c9fb300e5a0872df817','ft_sys_user',NULL,'real_name','用户名称','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1573,'1','N',NULL,'2019-03-29 21:16:37',NULL,NULL),('ff7a89f7c92843f0ae37d9f84e00f8ee','t_db_data_base',NULL,'username','','varchar','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1598,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL),('fffa2970f9fe4838a6d3215efa3e21f7','t_db_data_base',NULL,'db_type','','char','1',NULL,'b2c2d0d8c508470ba90c8fd70debfdff','db_sunny',1596,'1','N',NULL,'2019-03-29 21:16:38',NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
