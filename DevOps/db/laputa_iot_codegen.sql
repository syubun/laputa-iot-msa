/*
 Navicat Premium Data Transfer

 Source Server         : 百度云
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : 106.13.63.126:3306
 Source Schema         : laputa_iot_codegen

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 19/09/2021 09:12:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_datasource_conf
-- ----------------------------
DROP TABLE IF EXISTS `gen_datasource_conf`;
CREATE TABLE `gen_datasource_conf`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '别名',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'jdbcurl',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `create_date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标记',
  `tenant_id` int(0) NULL DEFAULT NULL COMMENT '租户ID',
  `ds_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据库类型',
  `conf_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置类型',
  `ds_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据库名称',
  `instance` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '实例',
  `port` int(0) NULL DEFAULT NULL COMMENT '端口',
  `host` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主机',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_datasource_conf
-- ----------------------------
INSERT INTO `gen_datasource_conf` VALUES (9, 'l_2', 'jdbc:mysql://laputa-mysql:3306/laputa_iot_upms?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&allowMultiQueries=true&allowPublicKeyRetrieval=true', 'root', 'B47kAOva4yQEOHdBMCYC12oBYwY9iL3o', '2021-07-06 05:05:11', '2021-08-09 08:11:13', '0', 1, 'mysql', '0', 'laputa_iot_upms', NULL, 3306, 'laputa-mysql');
INSERT INTO `gen_datasource_conf` VALUES (10, 'laputa_device', 'jdbc:mysql://laputa-mysql:3306/laputa_iot_device?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&allowMultiQueries=true&allowPublicKeyRetrieval=true', 'root', '4affoSijDsRcTGvvq3OQMY3vFy3RVGRV', '2021-08-08 09:05:23', '2021-08-13 06:15:35', '1', 1, 'mysql', '0', 'laputa_iot_device', NULL, 3306, 'laputa-mysql');
INSERT INTO `gen_datasource_conf` VALUES (11, 'laputa_codegen', 'jdbc:mysql://laputa-mysql:3306/laputa_iot_codegen?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&allowMultiQueries=true&allowPublicKeyRetrieval=true', 'root', 'a3GE49OKTY/f/UAo8SCzqOEOIcPZNDfx', '2021-08-09 08:06:12', '2021-08-13 06:14:20', '1', 1, 'mysql', '0', 'laputa_iot_codegen', NULL, 3306, 'laputa-mysql');
INSERT INTO `gen_datasource_conf` VALUES (12, 'laputa_pay', 'jdbc:mysql://laputa-mysql:3306/laputa_iot_pay?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&allowMultiQueries=true&allowPublicKeyRetrieval=true', 'root', 'kOTHa1PEJCBhnvDr9xBZzcBkfSy47l85', '2021-08-13 06:16:17', '2021-08-13 06:16:17', '0', 1, 'mysql', '0', 'laputa_iot_pay', NULL, 3306, 'laputa-mysql');
INSERT INTO `gen_datasource_conf` VALUES (13, 'laputa_org', 'jdbc:mysql://laputa-mysql:3306/laputa_iot_org?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&allowMultiQueries=true&allowPublicKeyRetrieval=true', 'root', 'zTUPQSdQ+xFMkzLowz4/Wa1arkssxG4i', '2021-08-16 06:44:12', '2021-08-16 06:44:12', '0', 1, 'mysql', '0', 'laputa_iot_org', NULL, 3306, 'laputa-mysql');
INSERT INTO `gen_datasource_conf` VALUES (14, 'laputa_file', 'jdbc:mysql://laputa-mysql:3306/laputa_iot_file?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&allowMultiQueries=true&allowPublicKeyRetrieval=true', 'root', 't4CiS/cn1DegN43IMZLZ6J2apyhAK9K4', '2021-08-25 01:25:38', '2021-08-25 01:25:38', '0', 1, 'mysql', '0', 'laputa_iot_file', NULL, 3306, 'laputa-mysql');
INSERT INTO `gen_datasource_conf` VALUES (15, 'laputa_flow', 'jdbc:mysql://laputa-mysql:3306/laputa_iot_flow?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&allowMultiQueries=true&allowPublicKeyRetrieval=true', 'root', 'ccjiE8VUQ4jJoKA9PtrJnto5qYk6YfEZ', '2021-09-08 04:58:33', '2021-09-08 04:58:33', '0', 1, 'mysql', '0', 'laputa_iot_flow', NULL, 3306, 'laputa-mysql');

-- ----------------------------
-- Table structure for gen_form_conf
-- ----------------------------
DROP TABLE IF EXISTS `gen_form_conf`;
CREATE TABLE `gen_form_conf`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `form_info` json NOT NULL COMMENT '表单信息',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0',
  `tenant_id` int(0) NULL DEFAULT NULL COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `table_name`(`table_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '表单配置' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
