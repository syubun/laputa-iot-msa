/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 8.0.23 : Database - laputa_iot_device
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`laputa_iot_device` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `laputa_iot_device`;

/*Table structure for table `dev_device` */

DROP TABLE IF EXISTS `dev_device`;

CREATE TABLE `dev_device` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '设备名',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '机器码',
  `type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '机器型号',
  `state` int DEFAULT '0' COMMENT '机器使用状态',
  `qrcode` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '固定二维码',
  `location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` int NOT NULL DEFAULT '0' COMMENT '是否删除',
  `version` int NOT NULL DEFAULT '1' COMMENT '版本号 乐观锁',
  `description` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `firmware` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '固件版本号',
  `alias` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '别名',
  `tenant_id` int DEFAULT NULL COMMENT '租户',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_device_code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `dev_device` */

/*Table structure for table `dev_device_param` */

DROP TABLE IF EXISTS `dev_device_param`;

CREATE TABLE `dev_device_param` (
  `id` bigint NOT NULL COMMENT '主键',
  `device_id` bigint DEFAULT NULL COMMENT '设备ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` int NOT NULL DEFAULT '0' COMMENT '是否删除',
  `version` int NOT NULL DEFAULT '1' COMMENT '版本号 乐观锁',
  `tenant_id` int DEFAULT NULL COMMENT '租户',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '参数名',
  `type` int DEFAULT '0' COMMENT '参数类型--0: GPIO --1: UART --2:I2C -3 OLED',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '参数描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `dev_device_param` */

/*Table structure for table `dev_param_gpio` */

DROP TABLE IF EXISTS `dev_param_gpio`;

CREATE TABLE `dev_param_gpio` (
  `id` bigint NOT NULL COMMENT '主键',
  `device_id` bigint DEFAULT NULL COMMENT '设备ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` int NOT NULL DEFAULT '0' COMMENT '是否删除',
  `version` int NOT NULL DEFAULT '1' COMMENT '版本号 乐观锁',
  `tenant_id` int DEFAULT NULL COMMENT '租户',
  `param_id` int DEFAULT NULL COMMENT '设备ID',
  `gpio_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'GPIO名',
  `gpio_pin` int DEFAULT NULL COMMENT 'GPIO PIN脚',
  `method` int DEFAULT NULL COMMENT '--1输出 --0输入 --2中断 --3开漏输出',
  `def_level` int DEFAULT NULL COMMENT '默认电平',
  `cur_level` int DEFAULT NULL COMMENT '当前电平',
  `alias` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '别名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `dev_param_gpio` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
