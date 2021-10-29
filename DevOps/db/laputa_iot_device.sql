/*
 Navicat Premium Data Transfer

 Source Server         : 百度云
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : 106.13.63.126:3306
 Source Schema         : laputa_iot_device

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 19/09/2021 09:13:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dev_device
-- ----------------------------
DROP TABLE IF EXISTS `dev_device`;
CREATE TABLE `dev_device`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备名',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机器码',
  `type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机器型号',
  `state` int(0) NULL DEFAULT 0 COMMENT '机器使用状态',
  `qrcode` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '固定二维码',
  `location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `deleted` int(0) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `version` int(0) NOT NULL DEFAULT 1 COMMENT '版本号 乐观锁',
  `description` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `firmware` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '固件版本号',
  `alias` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '别名',
  `tenant_id` int(0) NULL DEFAULT NULL COMMENT '租户',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_device_code`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dev_device_param
-- ----------------------------
DROP TABLE IF EXISTS `dev_device_param`;
CREATE TABLE `dev_device_param`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `device_id` bigint(0) NULL DEFAULT NULL COMMENT '设备ID',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `deleted` int(0) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `version` int(0) NOT NULL DEFAULT 1 COMMENT '版本号 乐观锁',
  `tenant_id` int(0) NULL DEFAULT NULL COMMENT '租户',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数名',
  `type` int(0) NULL DEFAULT 0 COMMENT '参数类型--0: GPIO --1: UART --2:I2C -3 OLED',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dev_param_gpio
-- ----------------------------
DROP TABLE IF EXISTS `dev_param_gpio`;
CREATE TABLE `dev_param_gpio`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `device_id` bigint(0) NULL DEFAULT NULL COMMENT '设备ID',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `deleted` int(0) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `version` int(0) NOT NULL DEFAULT 1 COMMENT '版本号 乐观锁',
  `tenant_id` int(0) NULL DEFAULT NULL COMMENT '租户',
  `param_id` int(0) NULL DEFAULT NULL COMMENT '设备ID',
  `gpio_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'GPIO名',
  `gpio_pin` int(0) NULL DEFAULT NULL COMMENT 'GPIO PIN脚',
  `method` int(0) NULL DEFAULT NULL COMMENT '--1输出 --0输入 --2中断 --3开漏输出',
  `def_level` int(0) NULL DEFAULT NULL COMMENT '默认电平',
  `cur_level` int(0) NULL DEFAULT NULL COMMENT '当前电平',
  `alias` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '别名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
