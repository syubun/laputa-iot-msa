/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : laputa_iot_job

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 07/07/2021 13:09:58
*/
CREATE DATABASE /*!32312 IF NOT EXISTS*/`laputa_iot_job` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `laputa_iot_job`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `blob_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`, `calendar_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cron_expression` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `time_zone_id` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('clusteredScheduler', 'demo', 'demo', '* 3/5 * * * ? *', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('clusteredScheduler', '定时任务', '定时', '* 1/5 * * * ? *', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('clusteredScheduler', '测试任务', 'demo', '0 * * * * ? *', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `entry_id` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fired_time` bigint(0) NOT NULL,
  `sched_time` bigint(0) NOT NULL,
  `priority` int(0) NOT NULL,
  `state` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `entry_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job_class_name` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_durable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_update_data` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('clusteredScheduler', 'demo', 'demo', NULL, 'PigxQuartzFactory', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000B7363686564756C654A6F627372002E636F6D2E70696734636C6F75642E706967782E6461656D6F6E2E71756172747A2E656E746974792E5379734A6F6200000000000000010200174C0009636C6173734E616D657400124C6A6176612F6C616E672F537472696E673B4C0008637265617465427971007E00094C000A63726561746554696D657400194C6A6176612F74696D652F4C6F63616C4461746554696D653B4C000E63726F6E45787072657373696F6E71007E00094C000B657865637574655061746871007E00094C00106A6F624578656375746553746174757371007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400134C6A6176612F6C616E672F496E74656765723B4C00076A6F624E616D6571007E00094C00086A6F624F7264657271007E00094C00096A6F6253746174757371007E00094C000D6A6F6254656E616E745479706571007E00094C00076A6F625479706571007E00094C000A6D6574686F644E616D6571007E00094C00116D6574686F64506172616D7356616C756571007E00094C000D6D697366697265506F6C69637971007E00094C00086E65787454696D6571007E000A4C000C70726576696F757354696D6571007E000A4C000672656D61726B71007E00094C0009737461727454696D6571007E000A4C000874656E616E74496471007E000B4C0008757064617465427971007E00094C000A75706461746554696D6571007E000A78720035636F6D2E62616F6D69646F752E6D796261746973706C75732E657874656E73696F6E2E6163746976657265636F72642E4D6F64656C0000000000000001020000787074000464656D6F74000561646D696E7372000D6A6176612E74696D652E536572955D84BA1B2248B20C00007870770A05000007E50706090FFD7874000F2A20332F35202A202A202A203F202A707400013074000464656D6F737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000274000464656D6F7400013174000131740001317400013274000A64656D6F4D6574686F64740006313233343536740001327070740000707371007E0015000000017400007371007E0010770A05000007E50706090FFD787800);
INSERT INTO `qrtz_job_details` VALUES ('clusteredScheduler', '定时任务', '定时', NULL, 'PigxQuartzFactory', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000B7363686564756C654A6F627372002E636F6D2E70696734636C6F75642E706967782E6461656D6F6E2E71756172747A2E656E746974792E5379734A6F6200000000000000010200174C0009636C6173734E616D657400124C6A6176612F6C616E672F537472696E673B4C0008637265617465427971007E00094C000A63726561746554696D657400194C6A6176612F74696D652F4C6F63616C4461746554696D653B4C000E63726F6E45787072657373696F6E71007E00094C000B657865637574655061746871007E00094C00106A6F624578656375746553746174757371007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400134C6A6176612F6C616E672F496E74656765723B4C00076A6F624E616D6571007E00094C00086A6F624F7264657271007E00094C00096A6F6253746174757371007E00094C000D6A6F6254656E616E745479706571007E00094C00076A6F625479706571007E00094C000A6D6574686F644E616D6571007E00094C00116D6574686F64506172616D7356616C756571007E00094C000D6D697366697265506F6C69637971007E00094C00086E65787454696D6571007E000A4C000C70726576696F757354696D6571007E000A4C000672656D61726B71007E00094C0009737461727454696D6571007E000A4C000874656E616E74496471007E000B4C0008757064617465427971007E00094C000A75706461746554696D6571007E000A78720035636F6D2E62616F6D69646F752E6D796261746973706C75732E657874656E73696F6E2E6163746976657265636F72642E4D6F64656C00000000000000010200007870740035636F6D2E70696734636C6F75642E706967782E61646D696E2E636F6E6669672E4C61707574614E61636F732E696E6974436C6F756474000561646D696E7372000D6A6176612E74696D652E536572955D84BA1B2248B20C00007870770A05000007E507060906EA7874000F2A20312F35202A202A202A203F202A7074000130740006E5AE9AE697B6737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000174000CE5AE9AE697B6E4BBBBE58AA1740001317400013174000131740001317070740001327070740009696E6974436C6F75647071007E00177400007371007E0010770A05000007E507060906EA787800);
INSERT INTO `qrtz_job_details` VALUES ('clusteredScheduler', '测试任务', 'demo', NULL, 'PigxQuartzFactory', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000B7363686564756C654A6F627372002E636F6D2E70696734636C6F75642E706967782E6461656D6F6E2E71756172747A2E656E746974792E5379734A6F6200000000000000010200174C0009636C6173734E616D657400124C6A6176612F6C616E672F537472696E673B4C0008637265617465427971007E00094C000A63726561746554696D657400194C6A6176612F74696D652F4C6F63616C4461746554696D653B4C000E63726F6E45787072657373696F6E71007E00094C000B657865637574655061746871007E00094C00106A6F624578656375746553746174757371007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400134C6A6176612F6C616E672F496E74656765723B4C00076A6F624E616D6571007E00094C00086A6F624F7264657271007E00094C00096A6F6253746174757371007E00094C000D6A6F6254656E616E745479706571007E00094C00076A6F625479706571007E00094C000A6D6574686F644E616D6571007E00094C00116D6574686F64506172616D7356616C756571007E00094C000D6D697366697265506F6C69637971007E00094C00086E65787454696D6571007E000A4C000C70726576696F757354696D6571007E000A4C000672656D61726B71007E00094C0009737461727454696D6571007E000A4C000874656E616E74496471007E000B4C0008757064617465427971007E00094C000A75706461746554696D6571007E000A78720035636F6D2E62616F6D69646F752E6D796261746973706C75732E657874656E73696F6E2E6163746976657265636F72642E4D6F64656C0000000000000001020000787074000464656D6F74000561646D696E7372000D6A6176612E74696D652E536572955D84BA1B2248B20C00007870770A05000007E303190E00F17874000D30202A202A202A202A203F202A7400007400013074000464656D6F737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000174000CE6B58BE8AF95E4BBBBE58AA17400013574000133740001317400013274000A64656D6F4D6574686F6474000970696734636C6F7564740001337371007E0010770905000007E3031916E2787371007E0010770A05000007E30319161CCD78740020E6BC94E7A4BA537072696E67204265616EE79A84E5AE9AE697B6E4BBBBE58AA17371007E0010770A05000007E303191600DE7871007E001874000561646D696E7371007E0010770A05000007E303190E00F1787800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `lock_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('clusteredScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('clusteredScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_checkin_time` bigint(0) NOT NULL,
  `checkin_interval` bigint(0) NOT NULL,
  PRIMARY KEY (`sched_name`, `instance_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('clusteredScheduler', 'GVEX60Q4ARFITS01625625072631', 1625633333820, 10000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `repeat_count` bigint(0) NOT NULL,
  `repeat_interval` bigint(0) NOT NULL,
  `times_triggered` bigint(0) NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_IBFK_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `str_prop_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `str_prop_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `str_prop_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `int_prop_1` int(0) NULL DEFAULT NULL,
  `int_prop_2` int(0) NULL DEFAULT NULL,
  `long_prop_1` bigint(0) NULL DEFAULT NULL,
  `long_prop_2` bigint(0) NULL DEFAULT NULL,
  `dec_prop_1` decimal(13, 4) NULL DEFAULT NULL,
  `dec_prop_2` decimal(13, 4) NULL DEFAULT NULL,
  `bool_prop_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bool_prop_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_IBFK_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `next_fire_time` bigint(0) NULL DEFAULT NULL,
  `prev_fire_time` bigint(0) NULL DEFAULT NULL,
  `priority` int(0) NULL DEFAULT NULL,
  `trigger_state` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_type` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `start_time` bigint(0) NOT NULL,
  `end_time` bigint(0) NULL DEFAULT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `misfire_instr` smallint(0) NULL DEFAULT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  INDEX `sched_name`(`sched_name`, `job_name`, `job_group`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('clusteredScheduler', 'demo', 'demo', 'demo', 'demo', NULL, 1625633580000, 1625633339000, 5, 'WAITING', 'CRON', 1625562909000, 0, NULL, 2, 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000B7363686564756C654A6F627372002E636F6D2E70696734636C6F75642E706967782E6461656D6F6E2E71756172747A2E656E746974792E5379734A6F6200000000000000010200174C0009636C6173734E616D657400124C6A6176612F6C616E672F537472696E673B4C0008637265617465427971007E00094C000A63726561746554696D657400194C6A6176612F74696D652F4C6F63616C4461746554696D653B4C000E63726F6E45787072657373696F6E71007E00094C000B657865637574655061746871007E00094C00106A6F624578656375746553746174757371007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400134C6A6176612F6C616E672F496E74656765723B4C00076A6F624E616D6571007E00094C00086A6F624F7264657271007E00094C00096A6F6253746174757371007E00094C000D6A6F6254656E616E745479706571007E00094C00076A6F625479706571007E00094C000A6D6574686F644E616D6571007E00094C00116D6574686F64506172616D7356616C756571007E00094C000D6D697366697265506F6C69637971007E00094C00086E65787454696D6571007E000A4C000C70726576696F757354696D6571007E000A4C000672656D61726B71007E00094C0009737461727454696D6571007E000A4C000874656E616E74496471007E000B4C0008757064617465427971007E00094C000A75706461746554696D6571007E000A78720035636F6D2E62616F6D69646F752E6D796261746973706C75732E657874656E73696F6E2E6163746976657265636F72642E4D6F64656C0000000000000001020000787074000464656D6F74000561646D696E7372000D6A6176612E74696D652E536572955D84BA1B2248B20C00007870770A05000007E50706090FFD7874000F2A20332F35202A202A202A203F202A707400013074000464656D6F737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000274000464656D6F7400013174000133740001317400013274000A64656D6F4D6574686F64740006313233343536740001337371007E0010770A05000007E507061113D8787371007E0010770A05000007E507061113D9787400007371007E0010770A05000007E50706110FF6787371007E00150000000174000561646D696E7371007E0010770A05000007E50706090FFD787800);
INSERT INTO `qrtz_triggers` VALUES ('clusteredScheduler', '定时任务', '定时', '定时任务', '定时', NULL, 1625562660000, 1625562419000, 5, 'PAUSED', 'CRON', 1625562387000, 0, NULL, 1, '');
INSERT INTO `qrtz_triggers` VALUES ('clusteredScheduler', '测试任务', 'demo', '测试任务', 'demo', NULL, 1566205080000, 1566205020000, 5, 'PAUSED', 'CRON', 1553564598000, 0, NULL, 2, '');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
  `job_order` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '组内执行顺利，值越大执行优先级越高，最大值9，最小值1',
  `job_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '1、java类;2、spring bean名称;3、rest调用;4、jar调用;9其他',
  `execute_path` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'job_type=3时，rest调用地址，仅支持rest get协议,需要增加String返回值，0成功，1失败;job_type=4时，jar路径;其它值为空',
  `class_name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'job_type=1时，类完整路径;job_type=2时，spring bean名称;其它值为空',
  `method_name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务方法',
  `method_params_value` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数值',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '3' COMMENT '错失执行策略（1错失周期立即执行 2错失周期执行一次 3下周期执行）',
  `job_tenant_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '1、多租户任务;2、非多租户任务',
  `job_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（1、未发布;2、运行中;3、暂停;4、删除;）',
  `job_execute_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1异常）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `start_time` timestamp(0) NULL DEFAULT NULL COMMENT '初次执行时间',
  `previous_time` timestamp(0) NULL DEFAULT NULL COMMENT '上次执行时间',
  `next_time` timestamp(0) NULL DEFAULT NULL COMMENT '下次执行时间',
  `tenant_id` int(0) NULL DEFAULT 1 COMMENT '租户',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, '定时任务', '定时', '1', '1', NULL, 'LaputaNacos.initCloud', NULL, NULL, '* 1/5 * * * ? *', '2', '1', '3', '1', 'admin', '2021-07-06 09:06:21', '', '2021-07-06 09:06:21', '2021-07-06 17:06:27', '2021-07-06 17:06:59', '2021-07-06 17:11:00', 1, 'initCloud');
INSERT INTO `sys_job` VALUES (2, 'demo', 'demo', '1', '2', NULL, 'demo', 'demoMethod', '123456', '* 3/5 * * * ? *', '3', '1', '2', '0', 'admin', '2021-07-06 09:15:02', 'admin', '2021-07-06 09:15:02', '2021-07-06 17:15:09', '2021-07-07 12:48:59', '2021-07-07 12:53:00', 1, '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `job_log_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_id` int(0) NOT NULL COMMENT '任务id',
  `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务组名',
  `job_order` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组内执行顺利，值越大执行优先级越高，最大值9，最小值1',
  `job_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '1、java类;2、spring bean名称;3、rest调用;4、jar调用;9其他',
  `execute_path` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'job_type=3时，rest调用地址，仅支持post协议;job_type=4时，jar路径;其它值为空',
  `class_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'job_type=1时，类完整路径;job_type=2时，spring bean名称;其它值为空',
  `method_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务方法',
  `method_params_value` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数值',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron执行表达式',
  `job_message` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `job_log_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `execute_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行时间',
  `exception_info` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '异常信息',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `tenant_id` int(0) NOT NULL DEFAULT 1 COMMENT '租户id',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 116 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务执行日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------



-- ----------------------------
-- Table structure for xxl_job_group
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_group`;
CREATE TABLE `xxl_job_group`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '执行器AppName',
  `title` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '执行器名称',
  `address_type` tinyint(0) NOT NULL DEFAULT 0 COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '执行器地址列表，多地址逗号分隔',
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_info
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_info`;
CREATE TABLE `xxl_job_info`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `job_group` int(0) NOT NULL COMMENT '执行器主键ID',
  `job_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `author` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '报警邮件',
  `schedule_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'NONE' COMMENT '调度类型',
  `schedule_conf` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调度配置，值含义取决于调度类型',
  `misfire_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'DO_NOTHING' COMMENT '调度过期策略',
  `executor_route_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int(0) NOT NULL DEFAULT 0 COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int(0) NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `glue_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime(0) NULL DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  `trigger_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '调度状态：0-停止，1-运行',
  `trigger_last_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '上次调度时间',
  `trigger_next_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '下次调度时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_lock
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_lock`;
CREATE TABLE `xxl_job_lock`  (
  `lock_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '锁名称',
  PRIMARY KEY (`lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_log
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log`;
CREATE TABLE `xxl_job_log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `job_group` int(0) NOT NULL COMMENT '执行器主键ID',
  `job_id` int(0) NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int(0) NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `trigger_time` datetime(0) NULL DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int(0) NOT NULL COMMENT '调度-结果',
  `trigger_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '调度-日志',
  `handle_time` datetime(0) NULL DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int(0) NOT NULL COMMENT '执行-状态',
  `handle_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '执行-日志',
  `alarm_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `I_trigger_time`(`trigger_time`) USING BTREE,
  INDEX `I_handle_code`(`handle_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_log_report
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log_report`;
CREATE TABLE `xxl_job_log_report`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `trigger_day` datetime(0) NULL DEFAULT NULL COMMENT '调度-时间',
  `running_count` int(0) NOT NULL DEFAULT 0 COMMENT '运行中-日志数量',
  `suc_count` int(0) NOT NULL DEFAULT 0 COMMENT '执行成功-日志数量',
  `fail_count` int(0) NOT NULL DEFAULT 0 COMMENT '执行失败-日志数量',
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `i_trigger_day`(`trigger_day`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_logglue
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_logglue`;
CREATE TABLE `xxl_job_logglue`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `job_id` int(0) NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'GLUE备注',
  `add_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_registry
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_registry`;
CREATE TABLE `xxl_job_registry`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `registry_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `registry_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `i_g_k_v`(`registry_group`, `registry_key`, `registry_value`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_user
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_user`;
CREATE TABLE `xxl_job_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `role` tinyint(0) NOT NULL COMMENT '角色：0-普通用户、1-管理员',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `i_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_user
-- ----------------------------
INSERT INTO `xxl_job_user` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 1, NULL);

SET FOREIGN_KEY_CHECKS = 1;