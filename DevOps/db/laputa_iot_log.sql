/*
 Navicat Premium Data Transfer

 Source Server         : 百度云
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : 106.13.63.126:3306
 Source Schema         : laputa_iot_log

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 19/09/2021 09:15:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `service_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remote_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_agent` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `request_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `time` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '执行时间',
  `exception` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `tenant_id` int(0) NULL DEFAULT 0 COMMENT '所属租户',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0正常 1删除',
  `version` int(0) NOT NULL DEFAULT 1 COMMENT '乐观锁 版本号',
  `create_user` bigint(0) NULL DEFAULT 1 COMMENT '创建者',
  `update_user` bigint(0) NULL DEFAULT 1 COMMENT '更新者',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_log_create_by`(`create_by`) USING BTREE,
  INDEX `sys_log_request_uri`(`request_uri`) USING BTREE,
  INDEX `sys_log_type`(`type`) USING BTREE,
  INDEX `sys_log_create_date`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 888095867347664962 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (888787508232454177, '0', 'admin用户登录', 'test', 'admin', '2021-09-18 14:04:15', '2021-09-18 14:04:15', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, NULL, 1, 0, 1, 1, 1);
INSERT INTO `sys_log` VALUES (888787509155201089, '0', '得到登录用户的权鉴表', 'test', 'admin', '2021-09-18 14:04:15', '2021-09-18 14:04:15', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36', '/acl/getPermCode', 'GET', '_t=%5B1631945054216%5D', '3', NULL, 1, 0, 1, 1, 1);
INSERT INTO `sys_log` VALUES (888790852535582817, '0', '得到登录用户的权鉴表', 'test', 'admin', '2021-09-18 14:17:32', '2021-09-18 14:17:32', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36', '/acl/getPermCode', 'GET', '_t=%5B1631945852029%5D', '0', NULL, 1, 0, 1, 1, 1);
INSERT INTO `sys_log` VALUES (888791154856820865, '0', '得到登录用户的权鉴表', 'test', 'admin', '2021-09-18 14:18:44', '2021-09-18 14:18:44', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36', '/acl/getPermCode', 'GET', '_t=%5B1631945921552%5D', '0', NULL, 1, 0, 1, 1, 1);
INSERT INTO `sys_log` VALUES (888795608440111265, '0', '得到登录用户的权鉴表', 'test', 'admin', '2021-09-18 14:36:26', '2021-09-18 14:36:26', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36', '/acl/getPermCode', 'GET', '_t=%5B1631946985121%5D', '0', NULL, 1, 0, 1, 1, 1);
INSERT INTO `sys_log` VALUES (888795863164387521, '0', '删除终端', 'test', 'admin', '2021-09-18 14:37:27', '2021-09-18 14:37:27', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36', '/client/5', 'DELETE', NULL, '166', NULL, 1, 0, 1, 1, 1);
INSERT INTO `sys_log` VALUES (888795969741652193, '0', '得到登录用户的权鉴表', 'test', 'admin', '2021-09-18 14:37:52', '2021-09-18 14:37:52', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36', '/acl/getPermCode', 'GET', '_t=%5B1631947072358%5D', '0', NULL, 1, 0, 1, 1, 1);
INSERT INTO `sys_log` VALUES (888796018961809665, '0', '删除终端', 'test', 'admin', '2021-09-18 14:38:04', '2021-09-18 14:38:04', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36', '/client/5', 'DELETE', NULL, '57', NULL, 1, 0, 1, 1, 1);
INSERT INTO `sys_log` VALUES (888799821379404065, '0', '删除终端', 'test', 'admin', '2021-09-18 14:53:11', '2021-09-18 14:53:11', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36', '/client/5', 'DELETE', NULL, '418', NULL, 1, 0, 1, 1, 1);
INSERT INTO `sys_log` VALUES (888801717624242209, '0', '删除终端', 'test', 'admin', '2021-09-18 15:00:43', '2021-09-18 15:00:43', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36', '/client/5', 'DELETE', NULL, '361', NULL, 1, 0, 1, 1, 1);
INSERT INTO `sys_log` VALUES (888803660992086081, '0', '删除终端', 'test', 'admin', '2021-09-18 15:08:26', '2021-09-18 15:08:26', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36', '/client/5', 'DELETE', NULL, '18114', NULL, 1, 0, 1, 1, 1);
INSERT INTO `sys_log` VALUES (888803674158006369, '0', '删除终端', 'test', 'admin', '2021-09-18 15:08:29', '2021-09-18 15:08:29', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36', '/client/5', 'DELETE', NULL, '73', NULL, 1, 0, 1, 1, 1);
INSERT INTO `sys_log` VALUES (888803702398255233, '0', '删除终端', 'test', 'admin', '2021-09-18 15:08:36', '2021-09-18 15:08:36', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36', '/client/5', 'DELETE', NULL, '85', NULL, 1, 0, 1, 1, 1);
INSERT INTO `sys_log` VALUES (888803989871657121, '0', '得到登录用户的权鉴表', 'test', 'admin', '2021-09-18 15:09:45', '2021-09-18 15:09:45', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36', '/acl/getPermCode', 'GET', '_t=%5B1631948984202%5D', '3', NULL, 1, 0, 1, 1, 1);
INSERT INTO `sys_log` VALUES (888804008397897921, '0', '删除终端', 'test', 'admin', '2021-09-18 15:09:49', '2021-09-18 15:09:49', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36', '/client/5', 'DELETE', NULL, '79', NULL, 1, 0, 1, 1, 1);
INSERT INTO `sys_log` VALUES (888804021123416289, '0', '删除终端', 'test', 'admin', '2021-09-18 15:09:52', '2021-09-18 15:09:52', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36', '/client/5', 'DELETE', NULL, '59', NULL, 1, 0, 1, 1, 1);
INSERT INTO `sys_log` VALUES (888804101901517057, '0', '得到登录用户的权鉴表', 'test', 'admin', '2021-09-18 15:10:11', '2021-09-18 15:10:11', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36', '/acl/getPermCode', 'GET', '_t=%5B1631949011215%5D', '0', NULL, 1, 0, 1, 1, 1);
INSERT INTO `sys_log` VALUES (888804118922002721, '0', '删除终端', 'test', 'admin', '2021-09-18 15:10:15', '2021-09-18 15:10:15', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36', '/client/pig', 'DELETE', NULL, '65', NULL, 1, 0, 1, 1, 1);
INSERT INTO `sys_log` VALUES (888816538059014177, '0', '得到登录用户的权鉴表', 'test', 'admin', '2021-09-18 15:59:36', '2021-09-18 15:59:36', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36', '/acl/getPermCode', 'GET', '_t=%5B1631951975505%5D', '2', NULL, 1, 0, 1, 1, 1);
INSERT INTO `sys_log` VALUES (888816538910457921, '0', 'admin用户登录', 'test', 'admin', '2021-09-18 15:59:36', '2021-09-18 15:59:36', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, NULL, 1, 0, 1, 1, 1);
INSERT INTO `sys_log` VALUES (889069830190137377, '0', '得到登录用户的权鉴表', 'test', 'admin', '2021-09-19 08:46:06', '2021-09-19 08:46:06', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36', '/acl/getPermCode', 'GET', '_t=%5B1632012365430%5D', '3', NULL, 1, 0, 1, 1, 1);
INSERT INTO `sys_log` VALUES (889069831532314689, '0', 'admin用户登录', 'test', 'admin', '2021-09-19 08:46:06', '2021-09-19 08:46:06', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, NULL, 1, 0, 1, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
