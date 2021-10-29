/*
 Navicat Premium Data Transfer

 Source Server         : 百度云
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : 106.13.63.126:3306
 Source Schema         : laputa_iot_upms

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 07/08/2021 14:16:14
*/
CREATE DATABASE /*!32312 IF NOT EXISTS*/`laputa_iot_upms` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `laputa_iot_upms`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  `parent_id` int(0) NULL DEFAULT NULL,
  `tenant_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, '江苏', 1, '2018-01-22 19:00:23', '2019-05-18 14:56:06', '0', 0, 1);
INSERT INTO `sys_dept` VALUES (2, '无锡', 2, '2018-01-22 19:00:38', '2019-05-18 14:12:07', '0', 0, 1);
INSERT INTO `sys_dept` VALUES (3, '新吴区', 3, '2018-01-22 19:00:44', '2019-05-18 14:56:11', '0', 1, 1);
INSERT INTO `sys_dept` VALUES (4, '王庄街道', 4, '2018-01-22 19:00:52', '2019-05-18 14:56:09', '0', 3, 1);
INSERT INTO `sys_dept` VALUES (5, '院校', 5, '2018-01-22 19:00:57', '2019-05-18 14:56:13', '0', 4, 1);
INSERT INTO `sys_dept` VALUES (6, '潍院', 6, '2018-01-22 19:01:06', '2019-05-18 14:56:16', '1', 5, 1);
INSERT INTO `sys_dept` VALUES (7, '1沙县', 7, '2018-01-22 19:01:57', '2019-05-18 14:12:17', '0', 2, 1);
INSERT INTO `sys_dept` VALUES (8, '2沙县', 8, '2018-01-22 19:02:03', '2019-05-18 14:12:19', '0', 7, 1);
INSERT INTO `sys_dept` VALUES (9, '租户默认部门', 0, '2021-07-06 09:02:07', NULL, '0', 0, 2);

-- ----------------------------
-- Table structure for sys_dept_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_relation`;
CREATE TABLE `sys_dept_relation`  (
  `ancestor` int(0) NOT NULL COMMENT '祖先节点',
  `descendant` int(0) NOT NULL COMMENT '后代节点',
  PRIMARY KEY (`ancestor`, `descendant`) USING BTREE,
  INDEX `idx1`(`ancestor`) USING BTREE,
  INDEX `idx2`(`descendant`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept_relation
-- ----------------------------
INSERT INTO `sys_dept_relation` VALUES (1, 1);
INSERT INTO `sys_dept_relation` VALUES (1, 3);
INSERT INTO `sys_dept_relation` VALUES (1, 4);
INSERT INTO `sys_dept_relation` VALUES (1, 5);
INSERT INTO `sys_dept_relation` VALUES (2, 2);
INSERT INTO `sys_dept_relation` VALUES (2, 7);
INSERT INTO `sys_dept_relation` VALUES (2, 8);
INSERT INTO `sys_dept_relation` VALUES (2, 11);
INSERT INTO `sys_dept_relation` VALUES (2, 12);
INSERT INTO `sys_dept_relation` VALUES (3, 3);
INSERT INTO `sys_dept_relation` VALUES (3, 4);
INSERT INTO `sys_dept_relation` VALUES (3, 5);
INSERT INTO `sys_dept_relation` VALUES (4, 4);
INSERT INTO `sys_dept_relation` VALUES (4, 5);
INSERT INTO `sys_dept_relation` VALUES (5, 5);
INSERT INTO `sys_dept_relation` VALUES (7, 7);
INSERT INTO `sys_dept_relation` VALUES (7, 8);
INSERT INTO `sys_dept_relation` VALUES (7, 11);
INSERT INTO `sys_dept_relation` VALUES (7, 12);
INSERT INTO `sys_dept_relation` VALUES (8, 8);
INSERT INTO `sys_dept_relation` VALUES (9, 9);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `system` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  `tenant_id` int(0) NOT NULL DEFAULT 0 COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_dict_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 120 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, 'log_type', '日志类型', '2019-03-19 11:06:44', '2019-03-19 11:06:44', '异常、正常', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (2, 'social_type', '社交登录', '2019-03-19 11:09:44', '2019-03-19 11:09:44', '微信、QQ', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (3, 'leave_status', '请假状态', '2019-03-19 11:09:44', '2019-03-19 11:09:44', '未提交、审批中、完成、驳回', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (4, 'job_type', '定时任务类型', '2019-03-19 11:22:21', '2019-03-19 11:22:21', 'quartz', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (5, 'job_status', '定时任务状态', '2019-03-19 11:24:57', '2019-03-19 11:24:57', '发布状态、运行状态', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (6, 'job_execute_status', '定时任务执行状态', '2019-03-19 11:26:15', '2019-03-19 11:26:15', '正常、异常', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (7, 'misfire_policy', '定时任务错失执行策略', '2019-03-19 11:27:19', '2019-03-19 11:27:19', '周期', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (8, 'gender', '性别', '2019-03-27 13:44:06', '2019-03-27 13:44:06', '微信用户性别', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (9, 'subscribe', '订阅状态', '2019-03-27 13:48:33', '2019-03-27 13:48:33', '公众号订阅状态', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (10, 'response_type', '回复', '2019-03-28 21:29:21', '2019-03-28 21:29:21', '微信消息是否已回复', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (11, 'param_type', '参数配置', '2019-04-29 18:20:47', '2019-04-29 18:20:47', '检索、原文、报表、安全、文档、消息、其他', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (12, 'status_type', '租户状态', '2019-05-15 16:31:08', '2019-05-15 16:31:08', '租户状态', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (13, 'dict_type', '字典类型', '2019-05-16 14:16:20', '2019-05-16 14:20:16', '系统类不能修改', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (14, 'channel_status', '支付渠道状态', '2019-05-30 16:14:43', '2019-05-30 16:14:43', '支付渠道状态（0-正常，1-冻结）', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (15, 'channel_id', '渠道编码ID', '2019-05-30 18:59:12', '2019-05-30 18:59:12', '不同的支付方式', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (16, 'order_status', '订单状态', '2019-06-27 08:17:40', '2019-06-27 08:17:40', '支付订单状态', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (17, 'grant_types', '授权类型', '2019-08-13 07:34:10', '2019-08-13 07:34:10', NULL, '1', '0', 1);
INSERT INTO `sys_dict` VALUES (18, 'style_type', '前端风格', '2020-02-07 03:49:28', '2020-02-07 03:50:40', '0-Avue 1-element', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (19, 'captcha_flag_types', '验证码开关', '2020-11-18 06:53:25', '2020-11-18 06:53:25', '是否校验验证码', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (20, 'enc_flag_types', '前端密码加密', '2020-11-18 06:54:44', '2020-11-18 06:54:44', '前端密码是否加密传输', '1', '0', 1);
INSERT INTO `sys_dict` VALUES (100, 'log_type', '日志类型', '2019-03-19 11:06:44', '2019-03-19 11:06:44', '异常、正常', '1', '0', 2);
INSERT INTO `sys_dict` VALUES (101, 'social_type', '社交登录', '2019-03-19 11:09:44', '2019-03-19 11:09:44', '微信、QQ', '1', '0', 2);
INSERT INTO `sys_dict` VALUES (102, 'leave_status', '请假状态', '2019-03-19 11:09:44', '2019-03-19 11:09:44', '未提交、审批中、完成、驳回', '1', '0', 2);
INSERT INTO `sys_dict` VALUES (103, 'job_type', '定时任务类型', '2019-03-19 11:22:21', '2019-03-19 11:22:21', 'quartz', '1', '0', 2);
INSERT INTO `sys_dict` VALUES (104, 'job_status', '定时任务状态', '2019-03-19 11:24:57', '2019-03-19 11:24:57', '发布状态、运行状态', '1', '0', 2);
INSERT INTO `sys_dict` VALUES (105, 'job_execute_status', '定时任务执行状态', '2019-03-19 11:26:15', '2019-03-19 11:26:15', '正常、异常', '1', '0', 2);
INSERT INTO `sys_dict` VALUES (106, 'misfire_policy', '定时任务错失执行策略', '2019-03-19 11:27:19', '2019-03-19 11:27:19', '周期', '1', '0', 2);
INSERT INTO `sys_dict` VALUES (107, 'gender', '性别', '2019-03-27 13:44:06', '2019-03-27 13:44:06', '微信用户性别', '1', '0', 2);
INSERT INTO `sys_dict` VALUES (108, 'subscribe', '订阅状态', '2019-03-27 13:48:33', '2019-03-27 13:48:33', '公众号订阅状态', '1', '0', 2);
INSERT INTO `sys_dict` VALUES (109, 'response_type', '回复', '2019-03-28 21:29:21', '2019-03-28 21:29:21', '微信消息是否已回复', '1', '0', 2);
INSERT INTO `sys_dict` VALUES (110, 'param_type', '参数配置', '2019-04-29 18:20:47', '2019-04-29 18:20:47', '检索、原文、报表、安全、文档、消息、其他', '1', '0', 2);
INSERT INTO `sys_dict` VALUES (111, 'status_type', '租户状态', '2019-05-15 16:31:08', '2019-05-15 16:31:08', '租户状态', '1', '0', 2);
INSERT INTO `sys_dict` VALUES (112, 'dict_type', '字典类型', '2019-05-16 14:16:20', '2019-05-16 14:20:16', '系统类不能修改', '1', '0', 2);
INSERT INTO `sys_dict` VALUES (113, 'channel_status', '支付渠道状态', '2019-05-30 16:14:43', '2019-05-30 16:14:43', '支付渠道状态（0-正常，1-冻结）', '1', '0', 2);
INSERT INTO `sys_dict` VALUES (114, 'channel_id', '渠道编码ID', '2019-05-30 18:59:12', '2019-05-30 18:59:12', '不同的支付方式', '1', '0', 2);
INSERT INTO `sys_dict` VALUES (115, 'order_status', '订单状态', '2019-06-27 08:17:40', '2019-06-27 08:17:40', '支付订单状态', '1', '0', 2);
INSERT INTO `sys_dict` VALUES (116, 'grant_types', '授权类型', '2019-08-13 07:34:10', '2019-08-13 07:34:10', NULL, '1', '0', 2);
INSERT INTO `sys_dict` VALUES (117, 'style_type', '前端风格', '2020-02-07 03:49:28', '2020-02-07 03:50:40', '0-Avue 1-element', '1', '0', 2);
INSERT INTO `sys_dict` VALUES (118, 'captcha_flag_types', '验证码开关', '2020-11-18 06:53:25', '2020-11-18 06:53:25', '是否校验验证码', '1', '0', 2);
INSERT INTO `sys_dict` VALUES (119, 'enc_flag_types', '前端密码加密', '2020-11-18 06:54:44', '2020-11-18 06:54:44', '前端密码是否加密传输', '1', '0', 2);

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `dict_id` int(0) NOT NULL,
  `value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序（升序）',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  `tenant_id` int(0) NOT NULL DEFAULT 0 COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_dict_value`(`value`) USING BTREE,
  INDEX `sys_dict_label`(`label`) USING BTREE,
  INDEX `sys_dict_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1061 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES (1, 1, '9', '异常', 'log_type', '日志异常', 1, '2019-03-19 11:08:59', '2019-03-25 12:49:13', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (2, 1, '0', '正常', 'log_type', '日志正常', 0, '2019-03-19 11:09:17', '2019-03-25 12:49:18', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (3, 2, 'WX', '微信', 'social_type', '微信登录', 0, '2019-03-19 11:10:02', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (4, 2, 'QQ', 'QQ', 'social_type', 'QQ登录', 1, '2019-03-19 11:10:14', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (5, 3, '0', '未提交', 'leave_status', '未提交', 0, '2019-03-19 11:18:34', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (6, 3, '1', '审批中', 'leave_status', '审批中', 1, '2019-03-19 11:18:45', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (7, 3, '2', '完成', 'leave_status', '完成', 2, '2019-03-19 11:19:02', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (8, 3, '9', '驳回', 'leave_status', '驳回', 9, '2019-03-19 11:19:20', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (9, 4, '1', 'java类', 'job_type', 'java类', 1, '2019-03-19 11:22:37', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (10, 4, '2', 'spring bean', 'job_type', 'spring bean容器实例', 2, '2019-03-19 11:23:05', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (11, 4, '9', '其他', 'job_type', '其他类型', 9, '2019-03-19 11:23:31', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (12, 4, '3', 'Rest 调用', 'job_type', 'Rest 调用', 3, '2019-03-19 11:23:57', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (13, 4, '4', 'jar', 'job_type', 'jar类型', 4, '2019-03-19 11:24:20', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (14, 5, '1', '未发布', 'job_status', '未发布', 1, '2019-03-19 11:25:18', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (15, 5, '2', '运行中', 'job_status', '运行中', 2, '2019-03-19 11:25:31', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (16, 5, '3', '暂停', 'job_status', '暂停', 3, '2019-03-19 11:25:42', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (17, 6, '0', '正常', 'job_execute_status', '正常', 0, '2019-03-19 11:26:27', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (18, 6, '1', '异常', 'job_execute_status', '异常', 1, '2019-03-19 11:26:41', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (19, 7, '1', '错失周期立即执行', 'misfire_policy', '错失周期立即执行', 1, '2019-03-19 11:27:45', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (20, 7, '2', '错失周期执行一次', 'misfire_policy', '错失周期执行一次', 2, '2019-03-19 11:27:57', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (21, 7, '3', '下周期执行', 'misfire_policy', '下周期执行', 3, '2019-03-19 11:28:08', '2019-03-25 12:49:36', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (22, 8, '1', '男', 'gender', '微信-男', 0, '2019-03-27 13:45:13', '2019-03-27 13:45:13', '微信-男', '0', 1);
INSERT INTO `sys_dict_item` VALUES (23, 8, '2', '女', 'gender', '女-微信', 1, '2019-03-27 13:45:34', '2019-03-27 13:45:34', '女-微信', '0', 1);
INSERT INTO `sys_dict_item` VALUES (24, 8, '0', '未知', 'gender', 'x性别未知', 3, '2019-03-27 13:45:57', '2019-03-27 13:45:57', 'x性别未知', '0', 1);
INSERT INTO `sys_dict_item` VALUES (25, 9, '0', '未关注', 'subscribe', '公众号-未关注', 0, '2019-03-27 13:49:07', '2019-03-27 13:49:07', '公众号-未关注', '0', 1);
INSERT INTO `sys_dict_item` VALUES (26, 9, '1', '已关注', 'subscribe', '公众号-已关注', 1, '2019-03-27 13:49:26', '2019-03-27 13:49:26', '公众号-已关注', '0', 1);
INSERT INTO `sys_dict_item` VALUES (27, 10, '0', '未回复', 'response_type', '微信消息-未回复', 0, '2019-03-28 21:29:47', '2019-03-28 21:29:47', '微信消息-未回复', '0', 1);
INSERT INTO `sys_dict_item` VALUES (28, 10, '1', '已回复', 'response_type', '微信消息-已回复', 1, '2019-03-28 21:30:08', '2019-03-28 21:30:08', '微信消息-已回复', '0', 1);
INSERT INTO `sys_dict_item` VALUES (29, 11, '1', '检索', 'param_type', '检索', 0, '2019-04-29 18:22:17', '2019-04-29 18:22:17', '检索', '0', 1);
INSERT INTO `sys_dict_item` VALUES (30, 11, '2', '原文', 'param_type', '原文', 0, '2019-04-29 18:22:27', '2019-04-29 18:22:27', '原文', '0', 1);
INSERT INTO `sys_dict_item` VALUES (31, 11, '3', '报表', 'param_type', '报表', 0, '2019-04-29 18:22:36', '2019-04-29 18:22:36', '报表', '0', 1);
INSERT INTO `sys_dict_item` VALUES (32, 11, '4', '安全', 'param_type', '安全', 0, '2019-04-29 18:22:46', '2019-04-29 18:22:46', '安全', '0', 1);
INSERT INTO `sys_dict_item` VALUES (33, 11, '5', '文档', 'param_type', '文档', 0, '2019-04-29 18:22:56', '2019-04-29 18:22:56', '文档', '0', 1);
INSERT INTO `sys_dict_item` VALUES (34, 11, '6', '消息', 'param_type', '消息', 0, '2019-04-29 18:23:05', '2019-04-29 18:23:05', '消息', '0', 1);
INSERT INTO `sys_dict_item` VALUES (35, 11, '9', '其他', 'param_type', '其他', 0, '2019-04-29 18:23:16', '2019-04-29 18:23:16', '其他', '0', 1);
INSERT INTO `sys_dict_item` VALUES (36, 11, '0', '默认', 'param_type', '默认', 0, '2019-04-29 18:23:30', '2019-04-29 18:23:30', '默认', '0', 1);
INSERT INTO `sys_dict_item` VALUES (37, 12, '0', '正常', 'status_type', '状态正常', 0, '2019-05-15 16:31:34', '2019-05-16 22:30:46', '状态正常', '0', 1);
INSERT INTO `sys_dict_item` VALUES (38, 12, '9', '冻结', 'status_type', '状态冻结', 1, '2019-05-15 16:31:56', '2019-05-16 22:30:50', '状态冻结', '0', 1);
INSERT INTO `sys_dict_item` VALUES (39, 13, '1', '系统类', 'dict_type', '系统类字典', 0, '2019-05-16 14:20:40', '2019-05-16 14:20:40', '不能修改删除', '0', 1);
INSERT INTO `sys_dict_item` VALUES (40, 13, '0', '业务类', 'dict_type', '业务类字典', 0, '2019-05-16 14:20:59', '2019-05-16 14:20:59', '可以修改', '0', 1);
INSERT INTO `sys_dict_item` VALUES (41, 14, '0', '正常', 'channel_status', '支付渠道状态正常', 0, '2019-05-30 16:16:51', '2019-05-30 16:16:51', NULL, '0', 1);
INSERT INTO `sys_dict_item` VALUES (42, 14, '1', '冻结', 'channel_status', '支付渠道冻结', 0, '2019-05-30 16:17:08', '2019-05-30 16:17:08', NULL, '0', 1);
INSERT INTO `sys_dict_item` VALUES (43, 15, 'ALIPAY_WAP', '支付宝wap支付', 'channel_id', '支付宝扫码支付', 0, '2019-05-30 19:03:16', '2019-06-18 13:51:42', '支付宝wap支付', '0', 1);
INSERT INTO `sys_dict_item` VALUES (44, 15, 'WEIXIN_MP', '微信公众号支付', 'channel_id', '微信公众号支付', 1, '2019-05-30 19:08:08', '2019-06-18 13:51:53', '微信公众号支付', '0', 1);
INSERT INTO `sys_dict_item` VALUES (45, 16, '1', '支付成功', 'order_status', '支付成功', 1, '2019-06-27 08:18:26', '2019-06-27 08:18:26', '订单支付成功', '0', 1);
INSERT INTO `sys_dict_item` VALUES (46, 16, '2', '支付完成', 'order_status', '订单支付完成', 2, '2019-06-27 08:18:44', '2019-06-27 08:18:44', '订单支付完成', '0', 1);
INSERT INTO `sys_dict_item` VALUES (47, 16, '0', '待支付', 'order_status', '订单待支付', 0, '2019-06-27 08:19:02', '2019-06-27 08:19:02', '订单待支付', '0', 1);
INSERT INTO `sys_dict_item` VALUES (48, 16, '-1', '支付失败', 'order_status', '订单支付失败', 3, '2019-06-27 08:19:37', '2019-06-27 08:19:37', '订单支付失败', '0', 1);
INSERT INTO `sys_dict_item` VALUES (49, 2, 'GITEE', '码云', 'social_type', '码云', 2, '2019-06-28 09:59:12', '2019-06-28 09:59:12', '码云', '0', 1);
INSERT INTO `sys_dict_item` VALUES (50, 2, 'OSC', '开源中国', 'social_type', '开源中国登录', 0, '2019-06-28 10:04:32', '2019-06-28 10:04:32', 'http://gitee.huaxiadaowei.com/#/authredirect', '0', 1);
INSERT INTO `sys_dict_item` VALUES (51, 17, 'password', '密码模式', 'grant_types', '支持oauth密码模式', 0, '2019-08-13 07:35:28', '2019-08-13 07:35:28', NULL, '0', 1);
INSERT INTO `sys_dict_item` VALUES (52, 17, 'authorization_code', '授权码模式', 'grant_types', 'oauth2 授权码模式', 1, '2019-08-13 07:36:07', '2019-08-13 07:36:07', NULL, '0', 1);
INSERT INTO `sys_dict_item` VALUES (53, 17, 'client_credentials', '客户端模式', 'grant_types', 'oauth2 客户端模式', 2, '2019-08-13 07:36:30', '2019-08-13 07:36:30', NULL, '0', 1);
INSERT INTO `sys_dict_item` VALUES (54, 17, 'refresh_token', '刷新模式', 'grant_types', 'oauth2 刷新token', 3, '2019-08-13 07:36:54', '2019-08-13 07:36:54', NULL, '0', 1);
INSERT INTO `sys_dict_item` VALUES (55, 17, 'implicit', '简化模式', 'grant_types', 'oauth2 简化模式', 4, '2019-08-13 07:39:32', '2019-08-13 07:39:32', NULL, '0', 1);
INSERT INTO `sys_dict_item` VALUES (56, 18, '0', 'Avue', 'style_type', 'Avue风格', 0, '2020-02-07 03:52:52', '2020-02-07 03:52:52', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (57, 18, '1', 'element', 'style_type', 'element-ui', 1, '2020-02-07 03:53:12', '2020-02-07 03:53:12', '', '0', 1);
INSERT INTO `sys_dict_item` VALUES (58, 19, '0', '关', 'captcha_flag_types', '不校验验证码', 0, '2020-11-18 06:53:58', '2020-11-18 06:53:58', '不校验验证码 -0', '0', 1);
INSERT INTO `sys_dict_item` VALUES (59, 19, '1', '开', 'captcha_flag_types', '校验验证码', 1, '2020-11-18 06:54:15', '2020-11-18 06:54:15', '不校验验证码-1', '0', 1);
INSERT INTO `sys_dict_item` VALUES (60, 20, '0', '否', 'enc_flag_types', '不加密', 0, '2020-11-18 06:55:31', '2020-11-18 06:55:31', '不加密-0', '0', 1);
INSERT INTO `sys_dict_item` VALUES (61, 20, '1', '是', 'enc_flag_types', '加密', 1, '2020-11-18 06:55:51', '2020-11-18 06:55:51', '加密-1', '0', 1);
INSERT INTO `sys_dict_item` VALUES (1000, 100, '9', '异常', 'log_type', '日志异常', 1, '2019-03-19 11:08:59', '2019-03-25 12:49:13', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1001, 100, '0', '正常', 'log_type', '日志正常', 0, '2019-03-19 11:09:17', '2019-03-25 12:49:18', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1002, 101, 'WX', '微信', 'social_type', '微信登录', 0, '2019-03-19 11:10:02', '2019-03-25 12:49:36', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1003, 101, 'QQ', 'QQ', 'social_type', 'QQ登录', 1, '2019-03-19 11:10:14', '2019-03-25 12:49:36', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1004, 101, 'GITEE', '码云', 'social_type', '码云', 2, '2019-06-28 09:59:12', '2019-06-28 09:59:12', '码云', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1005, 101, 'OSC', '开源中国', 'social_type', '开源中国登录', 0, '2019-06-28 10:04:32', '2019-06-28 10:04:32', 'http://gitee.huaxiadaowei.com/#/authredirect', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1006, 102, '0', '未提交', 'leave_status', '未提交', 0, '2019-03-19 11:18:34', '2019-03-25 12:49:36', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1007, 102, '1', '审批中', 'leave_status', '审批中', 1, '2019-03-19 11:18:45', '2019-03-25 12:49:36', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1008, 102, '2', '完成', 'leave_status', '完成', 2, '2019-03-19 11:19:02', '2019-03-25 12:49:36', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1009, 102, '9', '驳回', 'leave_status', '驳回', 9, '2019-03-19 11:19:20', '2019-03-25 12:49:36', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1010, 103, '1', 'java类', 'job_type', 'java类', 1, '2019-03-19 11:22:37', '2019-03-25 12:49:36', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1011, 103, '2', 'spring bean', 'job_type', 'spring bean容器实例', 2, '2019-03-19 11:23:05', '2019-03-25 12:49:36', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1012, 103, '9', '其他', 'job_type', '其他类型', 9, '2019-03-19 11:23:31', '2019-03-25 12:49:36', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1013, 103, '3', 'Rest 调用', 'job_type', 'Rest 调用', 3, '2019-03-19 11:23:57', '2019-03-25 12:49:36', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1014, 103, '4', 'jar', 'job_type', 'jar类型', 4, '2019-03-19 11:24:20', '2019-03-25 12:49:36', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1015, 104, '1', '未发布', 'job_status', '未发布', 1, '2019-03-19 11:25:18', '2019-03-25 12:49:36', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1016, 104, '2', '运行中', 'job_status', '运行中', 2, '2019-03-19 11:25:31', '2019-03-25 12:49:36', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1017, 104, '3', '暂停', 'job_status', '暂停', 3, '2019-03-19 11:25:42', '2019-03-25 12:49:36', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1018, 105, '0', '正常', 'job_execute_status', '正常', 0, '2019-03-19 11:26:27', '2019-03-25 12:49:36', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1019, 105, '1', '异常', 'job_execute_status', '异常', 1, '2019-03-19 11:26:41', '2019-03-25 12:49:36', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1020, 106, '1', '错失周期立即执行', 'misfire_policy', '错失周期立即执行', 1, '2019-03-19 11:27:45', '2019-03-25 12:49:36', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1021, 106, '2', '错失周期执行一次', 'misfire_policy', '错失周期执行一次', 2, '2019-03-19 11:27:57', '2019-03-25 12:49:36', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1022, 106, '3', '下周期执行', 'misfire_policy', '下周期执行', 3, '2019-03-19 11:28:08', '2019-03-25 12:49:36', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1023, 107, '1', '男', 'gender', '微信-男', 0, '2019-03-27 13:45:13', '2019-03-27 13:45:13', '微信-男', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1024, 107, '2', '女', 'gender', '女-微信', 1, '2019-03-27 13:45:34', '2019-03-27 13:45:34', '女-微信', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1025, 107, '0', '未知', 'gender', 'x性别未知', 3, '2019-03-27 13:45:57', '2019-03-27 13:45:57', 'x性别未知', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1026, 108, '0', '未关注', 'subscribe', '公众号-未关注', 0, '2019-03-27 13:49:07', '2019-03-27 13:49:07', '公众号-未关注', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1027, 108, '1', '已关注', 'subscribe', '公众号-已关注', 1, '2019-03-27 13:49:26', '2019-03-27 13:49:26', '公众号-已关注', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1028, 109, '0', '未回复', 'response_type', '微信消息-未回复', 0, '2019-03-28 21:29:47', '2019-03-28 21:29:47', '微信消息-未回复', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1029, 109, '1', '已回复', 'response_type', '微信消息-已回复', 1, '2019-03-28 21:30:08', '2019-03-28 21:30:08', '微信消息-已回复', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1030, 110, '1', '检索', 'param_type', '检索', 0, '2019-04-29 18:22:17', '2019-04-29 18:22:17', '检索', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1031, 110, '2', '原文', 'param_type', '原文', 0, '2019-04-29 18:22:27', '2019-04-29 18:22:27', '原文', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1032, 110, '3', '报表', 'param_type', '报表', 0, '2019-04-29 18:22:36', '2019-04-29 18:22:36', '报表', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1033, 110, '4', '安全', 'param_type', '安全', 0, '2019-04-29 18:22:46', '2019-04-29 18:22:46', '安全', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1034, 110, '5', '文档', 'param_type', '文档', 0, '2019-04-29 18:22:56', '2019-04-29 18:22:56', '文档', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1035, 110, '6', '消息', 'param_type', '消息', 0, '2019-04-29 18:23:05', '2019-04-29 18:23:05', '消息', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1036, 110, '9', '其他', 'param_type', '其他', 0, '2019-04-29 18:23:16', '2019-04-29 18:23:16', '其他', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1037, 110, '0', '默认', 'param_type', '默认', 0, '2019-04-29 18:23:30', '2019-04-29 18:23:30', '默认', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1038, 111, '0', '正常', 'status_type', '状态正常', 0, '2019-05-15 16:31:34', '2019-05-16 22:30:46', '状态正常', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1039, 111, '9', '冻结', 'status_type', '状态冻结', 1, '2019-05-15 16:31:56', '2019-05-16 22:30:50', '状态冻结', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1040, 112, '1', '系统类', 'dict_type', '系统类字典', 0, '2019-05-16 14:20:40', '2019-05-16 14:20:40', '不能修改删除', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1041, 112, '0', '业务类', 'dict_type', '业务类字典', 0, '2019-05-16 14:20:59', '2019-05-16 14:20:59', '可以修改', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1042, 113, '0', '正常', 'channel_status', '支付渠道状态正常', 0, '2019-05-30 16:16:51', '2019-05-30 16:16:51', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1043, 113, '1', '冻结', 'channel_status', '支付渠道冻结', 0, '2019-05-30 16:17:08', '2019-05-30 16:17:08', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1044, 114, 'ALIPAY_WAP', '支付宝wap支付', 'channel_id', '支付宝扫码支付', 0, '2019-05-30 19:03:16', '2019-06-18 13:51:42', '支付宝wap支付', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1045, 114, 'WEIXIN_MP', '微信公众号支付', 'channel_id', '微信公众号支付', 1, '2019-05-30 19:08:08', '2019-06-18 13:51:53', '微信公众号支付', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1046, 115, '1', '支付成功', 'order_status', '支付成功', 1, '2019-06-27 08:18:26', '2019-06-27 08:18:26', '订单支付成功', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1047, 115, '2', '支付完成', 'order_status', '订单支付完成', 2, '2019-06-27 08:18:44', '2019-06-27 08:18:44', '订单支付完成', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1048, 115, '0', '待支付', 'order_status', '订单待支付', 0, '2019-06-27 08:19:02', '2019-06-27 08:19:02', '订单待支付', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1049, 115, '-1', '支付失败', 'order_status', '订单支付失败', 3, '2019-06-27 08:19:37', '2019-06-27 08:19:37', '订单支付失败', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1050, 116, 'password', '密码模式', 'grant_types', '支持oauth密码模式', 0, '2019-08-13 07:35:28', '2019-08-13 07:35:28', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1051, 116, 'authorization_code', '授权码模式', 'grant_types', 'oauth2 授权码模式', 1, '2019-08-13 07:36:07', '2019-08-13 07:36:07', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1052, 116, 'client_credentials', '客户端模式', 'grant_types', 'oauth2 客户端模式', 2, '2019-08-13 07:36:30', '2019-08-13 07:36:30', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1053, 116, 'refresh_token', '刷新模式', 'grant_types', 'oauth2 刷新token', 3, '2019-08-13 07:36:54', '2019-08-13 07:36:54', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1054, 116, 'implicit', '简化模式', 'grant_types', 'oauth2 简化模式', 4, '2019-08-13 07:39:32', '2019-08-13 07:39:32', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1055, 117, '0', 'Avue', 'style_type', 'Avue风格', 0, '2020-02-07 03:52:52', '2020-02-07 03:52:52', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1056, 117, '1', 'element', 'style_type', 'element-ui', 1, '2020-02-07 03:53:12', '2020-02-07 03:53:12', NULL, '0', 2);
INSERT INTO `sys_dict_item` VALUES (1057, 118, '0', '关', 'captcha_flag_types', '不校验验证码', 0, '2020-11-18 06:53:58', '2020-11-18 06:53:58', '不校验验证码 -0', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1058, 118, '1', '开', 'captcha_flag_types', '校验验证码', 1, '2020-11-18 06:54:15', '2020-11-18 06:54:15', '不校验验证码-1', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1059, 119, '0', '否', 'enc_flag_types', '不加密', 0, '2020-11-18 06:55:31', '2020-11-18 06:55:31', '不加密-0', '0', 2);
INSERT INTO `sys_dict_item` VALUES (1060, 119, '1', '是', 'enc_flag_types', '加密', 1, '2020-11-18 06:55:51', '2020-11-18 06:55:51', '加密-1', '0', 2);

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` bigint(0) NOT NULL COMMENT '编号',
  `file_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bucket_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `original` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `file_size` bigint(0) NULL DEFAULT NULL COMMENT '文件大小',
  `create_user` bigint(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '上传时间',
  `update_user` bigint(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `tenant_id` int(0) NULL DEFAULT NULL COMMENT '所属租户',
  `version` int(0) NULL DEFAULT NULL,
  `url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `meta_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (870308810948870177, 'ace557a5f5ed4cafa7744b5020cf06e8.png', 'laputa', 'logo.png', 'png', 10873, 868140350533795873, '2021-07-29 14:16:30', 0, '2021-07-29 14:16:30', 0, 1, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (870315488142426177, '5b3f34669bb342f4bee9003d17f7d684.jpg', 'laputa', '微信图片_20210709152431.jpg', 'jpg', 1040049, 868140350533795873, '2021-07-29 14:43:02', 0, '2021-07-29 14:43:02', 0, 1, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (870315908512350305, '9b32d966072e4fe0a59299961583adf3.png', 'laputa', 'logo.png', 'png', 10873, 868140350533795873, '2021-07-29 14:44:42', 0, '2021-07-29 14:44:42', 0, 1, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (870345023252594817, 'bbd5545a9f16410c9fe180d321d5cf9b.png', 'laputa', 'logo.png', 'png', 18038, 868140350533795873, '2021-07-29 16:40:24', 0, '2021-07-29 16:40:24', 0, 1, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (870345292996673697, 'ddd5d696aaac4b37b09be73965e9c5d4.jpg', 'laputa', '微信图片_20210709152431.jpg', 'jpg', 1039752, 868140350533795873, '2021-07-29 16:41:28', 0, '2021-07-29 16:41:28', 0, 1, NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (870347382414376993, '98345e090f1946eca863aae9468fae41.jpg', 'laputa', '微信图片_20210709152431.jpg', 'jpg', 758736, 868140350533795873, '2021-07-29 16:49:46', 0, '2021-07-29 16:49:46', 0, 1, 1, 'http://laputa-minio:9000/laputa/98345e090f1946eca863aae9468fae41.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210729%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210729T084946Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=e475ca486a1ac43b17c23cfe090e8f8134538e6b6a54ab30e6c21bced2fad08d', 'image/png');
INSERT INTO `sys_file` VALUES (870389935243264033, '753baa8355c64a94bf38294ad1bbd7d7.jpg', 'laputa', '006fmUQjly1gqmjf7qwc1j30qo11c77l.jpg', 'jpg', 390265, 868140350533795873, '2021-07-29 19:38:52', 0, '2021-07-29 19:38:52', 0, 1, 1, 'http://laputa-minio:9000/laputa/753baa8355c64a94bf38294ad1bbd7d7.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210729%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210729T113851Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=b95d4f997dbe1ee67a0ae9d0e2d3dc099896157d14b632fba67c227571ed4629', 'image/png');
INSERT INTO `sys_file` VALUES (870395623470268481, '4a8756a2b0684926b614de557a05e687.jpg', 'laputa', '006fmUQjly1gqml1jhub3j30u010n41q.jpg', 'jpg', 350711, 868140350533795873, '2021-07-29 20:01:28', 0, '2021-07-29 20:01:28', 0, 1, 1, 'http://laputa-minio:9000/laputa/4a8756a2b0684926b614de557a05e687.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210729%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210729T120128Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=f0d3957777dcc2858efa0e39c10cb141c4ae365f36b905eab1c6ec9f7baddadb', 'image/png');
INSERT INTO `sys_file` VALUES (870396330059497569, 'dfacf7c4f43d4b91ac6484dfa3e6f104.jpg', 'laputa', '006fmUQjly1gqmjf89xmyj30qo11cwiw.jpg', 'jpg', 406120, 868140350533795873, '2021-07-29 20:04:16', 0, '2021-07-29 20:04:16', 0, 1, 1, 'http://laputa-minio:9000/laputa/dfacf7c4f43d4b91ac6484dfa3e6f104.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210729%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210729T120416Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=3f19c16c5dfb789035e840a0bbe0fd71e3ce0dd4413483d5788f15ef074c91bf', 'image/png');
INSERT INTO `sys_file` VALUES (870397095352205441, '9c3eafb27d284ec7bc4bf1342c8a7e8b.jpg', 'laputa', '006fmUQjly1gqmjf89xmyj30qo11cwiw.jpg', 'jpg', 406120, 868140350533795873, '2021-07-29 20:07:19', 0, '2021-07-29 20:07:19', 0, 1, 1, 'http://laputa-minio:9000/laputa/9c3eafb27d284ec7bc4bf1342c8a7e8b.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210729%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210729T120718Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=5d141ca4c2398426120d58e8ac536ac056bc59af407e2d6486ddcb64800bd220', 'image/png');
INSERT INTO `sys_file` VALUES (870397368992792737, 'faa5cc779413498d8e113730dd0c0423.jpg', 'laputa', '006fmUQjly1gqml1jhub3j30u010n41q.jpg', 'jpg', 350711, 868140350533795873, '2021-07-29 20:08:24', 0, '2021-07-29 20:08:24', 0, 1, 1, 'http://laputa-minio:9000/laputa/faa5cc779413498d8e113730dd0c0423.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210729%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210729T120824Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=0ca36449e409282f3a741eb3fa69c5f02479efd949bac54343f936dd9eb1656a', 'image/png');
INSERT INTO `sys_file` VALUES (870397557602255041, '7385263d46094ef18307dedca6731768.jpg', 'laputa', '006fmUQjly1gqml1jhub3j30u010n41q.jpg', 'jpg', 350711, 868140350533795873, '2021-07-29 20:09:09', 0, '2021-07-29 20:09:09', 0, 1, 1, 'http://laputa-minio:9000/laputa/7385263d46094ef18307dedca6731768.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210729%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210729T120909Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=68ef5a175f498a9aae69527f02762a1c67325235f750dab01ea99b61f4bfad84', 'image/png');
INSERT INTO `sys_file` VALUES (870397757662167265, 'c05f66a3610d4773b8980cd922a2051b.jpg', 'laputa', '006fmUQjly1gqmjf89xmyj30qo11cwiw.jpg', 'jpg', 406120, 868140350533795873, '2021-07-29 20:09:57', 0, '2021-07-29 20:09:57', 0, 1, 1, 'http://laputa-minio:9000/laputa/c05f66a3610d4773b8980cd922a2051b.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210729%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210729T120956Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=5701bd49f0abe6e13c9cb3fdc90619f444f4cf561723841cbdac40e565f77e95', 'image/png');
INSERT INTO `sys_file` VALUES (870398090182394113, '590bb048fbb94c5cb9aab32e187adfb2.jpg', 'laputa', '006fmUQjly1gqmjf89xmyj30qo11cwiw.jpg', 'jpg', 406120, 868140350533795873, '2021-07-29 20:11:16', 0, '2021-07-29 20:11:16', 0, 1, 1, 'http://laputa-minio:9000/laputa/590bb048fbb94c5cb9aab32e187adfb2.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210729%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210729T121116Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=eeb4bac1ca4998143b414c70889c1aef703a9925a96f4be0440d0193cb9cf91e', 'image/png');
INSERT INTO `sys_file` VALUES (870398284001181985, '1c0d3e615d1e4007a4e6f0357b46f56d.jpg', 'laputa', '006fmUQjly1gqmjf89xmyj30qo11cwiw.jpg', 'jpg', 406120, 868140350533795873, '2021-07-29 20:12:02', 0, '2021-07-29 20:12:02', 0, 1, 1, 'http://laputa-minio:9000/laputa/1c0d3e615d1e4007a4e6f0357b46f56d.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210729%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210729T121202Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=e4daefb262d8df86183073e594e7b660cc5c9aa45472e124b2baf7ceb5f35fcc', 'image/png');
INSERT INTO `sys_file` VALUES (870399113986834753, '051822198d0343cf8cc559dad5d1bcc1.jpg', 'laputa', '006fmUQjly1gqmjf89xmyj30qo11cwiw.jpg', 'jpg', 406120, 868140350533795873, '2021-07-29 20:15:20', 0, '2021-07-29 20:15:20', 0, 1, 1, 'http://laputa-minio:9000/laputa/051822198d0343cf8cc559dad5d1bcc1.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210729%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210729T121520Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=d0aa338f525b6b129187bf4ca2517b0f6198865df00670779f47c7e41fb625bf', 'image/png');
INSERT INTO `sys_file` VALUES (870588558895743009, 'f89eed9e2dec4760bf9cbc13ffb7e749.jpg', 'laputa', '微信图片_20210709152431.jpg', 'jpg', 758736, 868140350533795873, '2021-07-30 08:48:07', 0, '2021-07-30 08:48:07', 0, 1, 1, 'http://laputa-minio:9000/laputa/f89eed9e2dec4760bf9cbc13ffb7e749.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210730%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210730T004807Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=b384e94dba6a31728000f600f00319d8a8a019a271aabd52aa84de294e344c16', 'image/png');
INSERT INTO `sys_file` VALUES (870593752748523553, '50685205dddc4952ac16f773658931da.jpg', 'laputa', '微信图片_20210709152522.jpg', 'jpg', 838721, 868140350533795873, '2021-07-30 09:08:46', 0, '2021-07-30 09:08:46', 0, 1, 1, 'http://laputa-minio:9000/laputa/50685205dddc4952ac16f773658931da.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210730%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210730T010845Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=2710253ffc6387eea00177e11f82fc79b271d32f8d6a69bf30e21cc39b582301', 'image/png');
INSERT INTO `sys_file` VALUES (870594480493821985, '94ccf3cbf6374a2eb4392aa0d53fdacd.jpg', 'laputa', '微信图片_20210709152522.jpg', 'jpg', 838721, 868140350533795873, '2021-07-30 09:11:39', 0, '2021-07-30 09:11:39', 0, 1, 1, 'http://laputa-minio:9000/laputa/94ccf3cbf6374a2eb4392aa0d53fdacd.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210730%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210730T011139Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=3a8e8ca77d954c287abf71146fa28af049a60034213e32e5bc8ed0605cc8971e', 'image/png');
INSERT INTO `sys_file` VALUES (870595608069537857, 'c29fc8136c904372847de24e6a4c81c1.jpg', 'laputa', '微信图片_20210709152411.jpg', 'jpg', 63755, 868140350533795873, '2021-07-30 09:16:08', 0, '2021-07-30 09:16:08', 0, 1, 1, 'http://laputa-minio:9000/laputa/c29fc8136c904372847de24e6a4c81c1.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210730%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210730T011608Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=5251aeb510b10c9357cae5841b1a49d06ecc464084db55b1420d3a025169af86', 'image/png');
INSERT INTO `sys_file` VALUES (870609263939027041, '2b66207241a2439f9314f6038911034f.jpg', 'laputa', 'header.jpg', 'jpg', 63755, 868140350533795873, '2021-07-30 10:10:24', 0, '2021-07-30 10:10:24', 0, 1, 1, 'http://laputa-minio:9000/laputa/2b66207241a2439f9314f6038911034f.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210730%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210730T021023Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=921d45785b38a615dff2522e7cc8e08acb30997605c63a95b8d1db6d1dbd29f3', 'image/png');
INSERT INTO `sys_file` VALUES (870668331487592481, '1853b39a6e7a4c34975f292a1cf679b2.jpg', 'laputa', '微信图片_20210709152431.jpg', 'jpg', 758736, 868140350533795873, '2021-07-30 14:05:07', 0, '2021-07-30 14:05:07', 0, 1, 1, 'http://laputa-minio:9000/laputa/1853b39a6e7a4c34975f292a1cf679b2.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210730%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210730T060506Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=e1792c53cfaa1fec3794571e250b96343d8fb7fcd8d4918277a186f834272878', 'image/png');

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
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  `exception` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `tenant_id` int(0) NULL DEFAULT 0 COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_log_create_by`(`create_by`) USING BTREE,
  INDEX `sys_log_request_uri`(`request_uri`) USING BTREE,
  INDEX `sys_log_type`(`type`) USING BTREE,
  INDEX `sys_log_create_date`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 682 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (407, '0', 'admin用户登录', 'test', 'admin', '2021-07-28 07:31:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (408, '0', 'admin用户登录', 'test', 'admin', '2021-07-28 07:31:30', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (409, '0', 'admin用户登录', 'test', 'admin', '2021-07-28 07:31:56', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (410, '0', 'admin用户登录', 'test', 'admin', '2021-07-28 07:32:46', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (411, '0', 'admin用户登录', 'test', 'admin', '2021-07-28 07:35:00', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (412, '0', 'admin用户登录', 'test', 'admin', '2021-07-28 07:36:51', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (413, '0', 'admin用户登录', 'test', 'admin', '2021-07-28 07:38:53', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (414, '0', 'admin用户登录', 'test', 'admin', '2021-07-28 07:41:54', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (415, '0', 'admin用户登录', 'test', 'admin', '2021-07-28 07:44:00', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (416, '0', 'admin用户登录', 'test', 'admin', '2021-07-28 07:45:55', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (417, '0', 'admin用户登录', 'test', 'admin', '2021-07-28 08:06:00', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (418, '0', 'admin用户登录', 'test', 'admin', '2021-07-28 08:09:50', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (419, '0', 'admin用户登录', 'test', 'admin', '2021-07-28 08:11:34', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (420, '0', 'admin用户登录', 'test', 'admin', '2021-07-28 08:16:19', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (421, '0', 'admin用户登录', 'test', 'admin', '2021-07-28 08:16:43', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (422, '0', 'admin用户登录', 'test', 'admin', '2021-07-28 08:22:51', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (423, '0', 'admin用户登录', 'test', 'admin', '2021-07-28 08:27:10', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (424, '0', 'admin用户登录', 'test', 'admin', '2021-07-29 01:36:44', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (425, '0', 'admin用户退出', 'test', 'admin', '2021-07-29 01:59:12', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer c91d472c-7d94-47ff-9b07-5cfecb774827', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (426, '0', 'admin用户登录', 'test', 'admin', '2021-07-29 01:59:14', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (427, '0', 'admin用户退出', 'test', 'admin', '2021-07-29 02:05:02', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer c37ba5ba-f279-46f1-aabd-c3da8f67b863', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (428, '0', 'admin用户登录', 'test', 'admin', '2021-07-29 02:05:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (429, '0', 'admin用户退出', 'test', 'admin', '2021-07-29 06:13:09', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer c6e03540-4f0a-492c-ba81-4d967846f27f', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (430, '0', 'admin用户登录', 'test', 'admin', '2021-07-29 06:13:14', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (431, '0', 'admin用户退出', 'test', 'admin', '2021-07-29 06:29:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer fff916b8-5e25-4449-9bca-265ff15d4926', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (432, '0', 'admin用户登录', 'test', 'admin', '2021-07-29 06:29:45', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (433, '0', 'admin用户退出', 'test', 'admin', '2021-07-29 06:41:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer f3ca8d39-49d8-467f-a6d8-60e039c8dc5c', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (434, '0', 'admin用户登录', 'test', 'admin', '2021-07-29 06:41:14', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (435, '0', 'admin用户退出', 'test', 'admin', '2021-07-29 06:48:24', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 9ec08c8d-ecdb-428a-809d-6ce89808b56c', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (436, '0', 'admin用户登录', 'test', 'admin', '2021-07-29 06:48:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (437, '0', 'admin用户退出', 'test', 'admin', '2021-07-29 06:48:31', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer f07c35b7-421f-45bf-9a15-d177b3062874', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (438, '0', 'admin用户登录', 'test', 'admin', '2021-07-29 06:48:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (439, '0', 'admin用户登录', 'test', 'admin', '2021-07-29 10:30:48', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (440, '0', 'admin用户登录', 'test', 'admin', '2021-07-29 11:37:35', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (441, '0', 'admin用户登录', 'test', 'admin', '2021-07-29 12:01:13', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (442, '0', 'admin用户登录', 'test', 'admin', '2021-07-29 14:36:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (443, '0', 'admin用户登录', 'test', 'admin', '2021-07-29 14:36:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (444, '0', 'admin用户登录', 'test', 'admin', '2021-07-29 14:37:35', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (445, '0', 'admin用户登录', 'test', 'admin', '2021-07-29 14:39:18', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (446, '0', 'admin用户退出', 'test', 'admin', '2021-07-29 14:41:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 2ef76202-5a52-4cd6-a081-082c0340b858', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (447, '0', 'admin用户登录', 'test', 'admin', '2021-07-29 14:41:29', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (448, '0', 'admin用户登录', 'test', 'admin', '2021-07-29 14:48:51', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (449, '0', 'admin用户登录', 'test', 'admin', '2021-07-29 14:57:39', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (450, '0', 'admin用户登录', 'test', 'admin', '2021-07-29 15:09:44', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (451, '0', 'admin用户登录', 'test', 'admin', '2021-07-30 00:47:47', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (452, '0', 'admin用户退出', 'test', 'admin', '2021-07-30 01:23:07', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 9b2d682b-368f-4233-99a5-6f59fe51c70a', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (453, '0', 'admin用户登录', 'test', 'admin', '2021-07-30 01:23:12', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (454, '0', 'admin用户退出', 'test', 'admin', '2021-07-30 06:44:13', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 78ba4ed7-7ab7-4aee-a5d3-f9b69715dd1f', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (455, '0', 'admin用户登录', 'test', 'admin', '2021-07-30 06:44:45', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (456, '0', '新增菜单', 'test', 'admin', '2021-07-30 07:00:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '411', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (457, '0', '新增菜单', 'test', 'admin', '2021-07-30 07:01:21', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '113', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (458, '0', 'admin用户退出', 'test', 'admin', '2021-07-30 07:03:14', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer ed85b7dd-2b10-47a8-a5c8-07dcaac3fcf5', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (459, '0', 'admin用户登录', 'test', 'admin', '2021-07-30 07:03:31', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (460, '0', 'admin用户登录', 'test', 'admin', '2021-07-31 00:36:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (461, '0', '新增菜单', 'test', 'admin', '2021-07-31 01:29:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '496', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (462, '0', '新增菜单', 'test', 'admin', '2021-07-31 01:32:40', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '376', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (463, '0', '新增菜单', 'test', 'admin', '2021-07-31 01:33:39', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '86', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (464, '0', '修改角色', 'test', 'admin', '2021-07-31 01:34:47', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/role', 'PUT', NULL, '519', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (465, '0', '新增菜单', 'test', 'admin', '2021-07-31 01:50:46', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '410', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (466, '0', '新增菜单', 'test', 'admin', '2021-07-31 02:01:28', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '487', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (467, '0', '新增菜单', 'test', 'admin', '2021-07-31 02:02:07', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '92', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (468, '0', '新增菜单', 'test', 'admin', '2021-07-31 02:07:44', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '388', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (469, '0', '新增菜单', 'test', 'admin', '2021-07-31 02:08:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '95', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (470, '0', '新增菜单', 'test', 'admin', '2021-07-31 02:09:04', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '83', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (471, '0', '新增菜单', 'test', 'admin', '2021-07-31 02:12:48', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '473', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (472, '0', '修改角色', 'test', 'admin', '2021-07-31 02:20:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/role', 'PUT', NULL, '430', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (473, '0', 'admin用户退出', 'test', 'admin', '2021-07-31 02:20:24', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer c4592771-c490-482c-8745-101f893c6308', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (474, '0', 'admin用户登录', 'test', 'admin', '2021-07-31 02:20:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (475, '0', '更新菜单', 'test', 'admin', '2021-07-31 02:22:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'PUT', NULL, '393', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (476, '0', '新增菜单', 'test', 'admin', '2021-07-31 02:27:44', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '106', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (477, '0', '新增菜单', 'test', 'admin', '2021-07-31 02:28:32', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '82', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (478, '0', '新增菜单', 'test', 'admin', '2021-07-31 02:29:16', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '100', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (479, '0', '更新菜单', 'test', 'admin', '2021-07-31 02:29:52', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'PUT', NULL, '76', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (480, '0', '更新菜单', 'test', 'admin', '2021-07-31 02:39:13', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'PUT', NULL, '114', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (481, '9', '更新菜单', 'test', 'admin', '2021-07-31 02:49:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'PUT', NULL, '46', '0', 'nested exception is org.apache.ibatis.exceptions.PersistenceException: \r\n### Error updating database.  Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu    \n WHERE  name=?\n\n AND component=?\n\n AND path=?\n\n AND parent_id=?\n AND sort=?\n AND type=?\n\n AND title=?\n\n\n\n\n AND ignore_keep_alive=?\n AND icon=?\n\n\n AND show_menu=?\n\n\n\n\n\n\n\n\n AND is_link=?\n\n\n\n\n\n\n\n\n AND deleted=0\r\n### The error may exist in com/laputa/iot/admin/mapper/SysMenuMapper.java (best guess)\r\n### The error may involve com.laputa.iot.upms.mapper.SysMenuMapper.update\r\n### The error occurred while executing an update\r\n### Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu    \n WHERE  name=?\n\n AND component=?\n\n AND path=?\n\n AND parent_id=?\n AND sort=?\n AND type=?\n\n AND title=?\n\n\n\n\n AND ignore_keep_alive=?\n AND icon=?\n\n\n AND show_menu=?\n\n\n\n\n\n\n\n\n AND is_link=?\n\n\n\n\n\n\n\n\n AND deleted=0', 1);
INSERT INTO `sys_log` VALUES (482, '9', '更新菜单', 'test', 'admin', '2021-07-31 02:50:31', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'PUT', NULL, '5918', '0', 'nested exception is org.apache.ibatis.exceptions.PersistenceException: \r\n### Error updating database.  Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu    \n WHERE  name=?\n\n AND component=?\n\n AND path=?\n\n AND parent_id=?\n AND sort=?\n AND type=?\n\n AND title=?\n\n\n\n\n AND ignore_keep_alive=?\n AND icon=?\n\n\n AND show_menu=?\n\n\n\n\n\n\n\n\n AND is_link=?\n\n\n\n\n\n\n\n\n AND deleted=0\r\n### The error may exist in com/laputa/iot/admin/mapper/SysMenuMapper.java (best guess)\r\n### The error may involve com.laputa.iot.upms.mapper.SysMenuMapper.update\r\n### The error occurred while executing an update\r\n### Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Failed to process, Error SQL: UPDATE sys_menu    \n WHERE  name=?\n\n AND component=?\n\n AND path=?\n\n AND parent_id=?\n AND sort=?\n AND type=?\n\n AND title=?\n\n\n\n\n AND ignore_keep_alive=?\n AND icon=?\n\n\n AND show_menu=?\n\n\n\n\n\n\n\n\n AND is_link=?\n\n\n\n\n\n\n\n\n AND deleted=0', 1);
INSERT INTO `sys_log` VALUES (483, '0', '更新菜单', 'test', 'admin', '2021-07-31 03:37:31', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'PUT', NULL, '7073', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (484, '0', '新增菜单', 'test', 'admin', '2021-07-31 05:25:02', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '501', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (485, '0', '更新菜单', 'test', 'admin', '2021-07-31 05:27:53', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'PUT', NULL, '100', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (486, '0', '修改角色', 'test', 'admin', '2021-07-31 05:39:53', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/role', 'PUT', NULL, '438', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (487, '0', 'admin用户退出', 'test', 'admin', '2021-07-31 05:40:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 33838db0-6fe7-47d4-9823-2abf89934163', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (488, '0', 'admin用户登录', 'test', 'admin', '2021-07-31 05:40:24', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (489, '0', '修改角色', 'test', 'admin', '2021-07-31 05:45:54', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/role', 'PUT', NULL, '389', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (490, '0', 'admin用户退出', 'test', 'admin', '2021-07-31 05:46:06', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 9ad514e9-e190-423b-b9e1-d6ce98d879e9', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (491, '0', 'admin用户登录', 'test', 'admin', '2021-07-31 05:46:09', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (492, '0', '新增菜单', 'test', 'admin', '2021-07-31 05:50:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '120', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (493, '0', '新增菜单', 'test', 'admin', '2021-07-31 05:53:56', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '481', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (494, '0', '修改角色', 'test', 'admin', '2021-07-31 05:56:10', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/role', 'PUT', NULL, '399', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (495, '0', 'admin用户退出', 'test', 'admin', '2021-07-31 05:56:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer fef62cd3-2442-4154-bf02-a6a111c81880', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (496, '0', 'admin用户登录', 'test', 'admin', '2021-07-31 05:56:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (497, '0', 'admin用户退出', 'test', 'admin', '2021-07-31 06:00:29', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 6d71e56c-7ee1-484c-9268-d53a9c208c71', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (498, '0', 'admin用户登录', 'test', 'admin', '2021-07-31 06:00:31', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (499, '0', '新增菜单', 'test', 'admin', '2021-07-31 06:04:16', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '410', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (500, '0', 'admin用户退出', 'test', 'admin', '2021-07-31 06:04:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer dcf62a07-f339-46d7-a114-6264e2376e37', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (501, '0', 'admin用户登录', 'test', 'admin', '2021-07-31 06:04:29', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (502, '0', 'admin用户退出', 'test', 'admin', '2021-07-31 06:07:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 46f45b61-c965-4caf-8775-e6cf4a235fb8', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (503, '0', 'admin用户登录', 'test', 'admin', '2021-07-31 06:07:06', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (504, '0', 'admin用户登录', 'test', 'admin', '2021-07-31 06:10:48', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (505, '0', '修改角色', 'test', 'admin', '2021-07-31 06:17:10', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/role', 'PUT', NULL, '729', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (506, '0', 'admin用户退出', 'test', 'admin', '2021-07-31 06:17:30', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 27f6b8ce-9a87-4b92-8295-d442690e6efb', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (507, '0', 'admin用户登录', 'test', 'admin', '2021-07-31 06:17:32', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (508, '0', '更新菜单', 'test', 'admin', '2021-07-31 06:26:55', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'PUT', NULL, '112', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (509, '0', '更新菜单', 'test', 'admin', '2021-07-31 06:29:33', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'PUT', NULL, '120', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (510, '0', '更新菜单', 'test', 'admin', '2021-07-31 06:31:52', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'PUT', NULL, '93', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (511, '0', 'admin用户退出', 'test', 'admin', '2021-07-31 06:32:00', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 330022ce-817f-4155-8a43-f180b21470be', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (512, '0', 'admin用户登录', 'test', 'admin', '2021-07-31 06:32:02', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (513, '0', 'admin用户登录', 'test', 'admin', '2021-08-01 05:23:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (514, '0', 'admin用户退出', 'test', 'admin', '2021-08-01 05:29:16', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 6c5d4a52-d848-43ce-8361-f0619a36ffa1', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (515, '0', 'admin用户登录', 'test', 'admin', '2021-08-01 05:38:59', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (516, '0', 'admin用户退出', 'test', 'admin', '2021-08-01 05:41:16', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 5563627d-ece4-41e1-add6-d5259db29772', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (517, '0', 'admin用户登录', 'test', 'admin', '2021-08-01 06:07:37', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (518, '0', '新增菜单', 'test', 'admin', '2021-08-01 06:39:18', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '126', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (519, '0', '新增菜单', 'test', 'admin', '2021-08-01 06:39:57', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '103', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (520, '0', '新增菜单', 'test', 'admin', '2021-08-01 06:40:33', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '88', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (521, '0', '新增菜单', 'test', 'admin', '2021-08-01 06:43:18', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '420', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (522, '0', '新增菜单', 'test', 'admin', '2021-08-01 06:44:19', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '94', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (523, '0', '新增菜单', 'test', 'admin', '2021-08-01 06:45:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '96', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (524, '0', '新增菜单', 'test', 'admin', '2021-08-01 06:45:51', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '94', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (525, '0', '新增菜单', 'test', 'admin', '2021-08-01 06:47:43', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '713', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (526, '0', '新增菜单', 'test', 'admin', '2021-08-01 06:49:44', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '91', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (527, '0', '新增菜单', 'test', 'admin', '2021-08-01 06:50:18', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '91', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (528, '0', '新增菜单', 'test', 'admin', '2021-08-01 06:50:48', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '95', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (529, '0', '新增菜单', 'test', 'admin', '2021-08-01 06:52:32', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '367', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (530, '0', '更新菜单', 'test', 'admin', '2021-08-01 06:53:46', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'PUT', NULL, '457', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (531, '0', '新增菜单', 'test', 'admin', '2021-08-01 06:54:23', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '93', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (532, '0', '新增菜单', 'test', 'admin', '2021-08-01 06:54:54', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '94', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (533, '0', '新增菜单', 'test', 'admin', '2021-08-01 06:55:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '83', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (534, '0', '新增菜单', 'test', 'admin', '2021-08-01 06:56:59', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '403', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (535, '0', '新增菜单', 'test', 'admin', '2021-08-01 06:57:32', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '91', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (536, '0', '新增菜单', 'test', 'admin', '2021-08-01 06:58:42', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '364', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (537, '0', '新增菜单', 'test', 'admin', '2021-08-01 07:02:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '375', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (538, '0', '更新菜单', 'test', 'admin', '2021-08-01 07:02:49', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'PUT', NULL, '88', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (539, '0', '新增菜单', 'test', 'admin', '2021-08-01 07:06:00', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '464', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (540, '0', '新增菜单', 'test', 'admin', '2021-08-01 08:53:54', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'POST', NULL, '422', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (541, '0', '修改角色', 'test', 'admin', '2021-08-01 08:54:18', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/role', 'PUT', NULL, '424', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (542, '0', 'admin用户退出', 'test', 'admin', '2021-08-01 08:54:23', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer ae1ed7cb-c45e-443c-b3c2-2770bfb93cb3', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (543, '0', 'admin用户登录', 'test', 'admin', '2021-08-01 08:54:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (544, '0', '更新菜单', 'test', 'admin', '2021-08-01 09:00:14', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'PUT', NULL, '118', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (545, '0', 'admin用户退出', 'test', 'admin', '2021-08-01 09:00:18', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer b301aab8-065a-4c1d-bd58-53ec3c9a1204', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (546, '0', 'admin用户登录', 'test', 'admin', '2021-08-01 09:00:20', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (547, '0', '更新菜单', 'test', 'admin', '2021-08-01 09:01:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'PUT', NULL, '119', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (548, '0', 'admin用户退出', 'test', 'admin', '2021-08-01 09:01:43', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 7c26f060-dd24-4437-9dd3-41f65687e3c1', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (549, '0', 'admin用户登录', 'test', 'admin', '2021-08-01 09:01:44', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (550, '0', 'admin用户退出', 'test', 'admin', '2021-08-01 09:03:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 6dddc9da-49cc-4136-aadb-ee98b61f3127', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (551, '0', 'admin用户登录', 'test', 'admin', '2021-08-01 09:03:19', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (552, '0', '更新菜单', 'test', 'admin', '2021-08-01 09:04:56', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'PUT', NULL, '121', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (553, '0', 'admin用户退出', 'test', 'admin', '2021-08-01 09:05:01', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer b67bb0b7-d8f2-4fda-b745-34ca596b836f', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (554, '0', 'admin用户登录', 'test', 'admin', '2021-08-01 09:05:03', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (555, '0', 'admin用户登录', 'test', 'admin', '2021-08-03 02:21:40', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (556, '0', '更新菜单', 'test', 'admin', '2021-08-03 03:09:19', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/menu', 'PUT', NULL, '140', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (557, '0', 'admin用户登录', 'test', 'admin', '2021-08-03 14:05:30', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (558, '0', 'admin用户登录', 'test', 'admin', '2021-08-03 14:05:59', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (559, '0', 'admin用户登录', 'test', 'admin', '2021-08-03 14:21:09', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (560, '0', 'admin用户登录', 'test', 'admin', '2021-08-03 14:28:35', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (561, '0', 'admin用户登录', 'test', 'admin', '2021-08-03 14:34:58', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (562, '0', 'admin用户登录', 'test', 'admin', '2021-08-03 14:34:59', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (563, '0', 'admin用户登录', 'test', 'admin', '2021-08-03 14:37:47', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (564, '0', 'admin用户登录', 'test', 'admin', '2021-08-03 14:39:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (565, '0', 'admin用户登录', 'test', 'admin', '2021-08-03 14:40:50', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (566, '0', 'admin用户登录', 'test', 'admin', '2021-08-03 14:56:13', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (567, '0', 'admin用户登录', 'test', 'admin', '2021-08-04 01:30:09', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (568, '0', 'admin用户登录', 'test', 'admin', '2021-08-04 02:51:53', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (569, '0', 'admin用户登录', 'test', 'admin', '2021-08-04 02:52:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (570, '0', 'admin用户登录', 'test', 'admin', '2021-08-04 05:03:12', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (571, '0', 'admin用户登录', 'test', 'admin', '2021-08-05 12:38:28', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (572, '0', 'admin用户登录', 'test', 'admin', '2021-08-05 12:38:55', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (573, '0', 'admin用户登录', 'test', 'admin', '2021-08-05 12:58:52', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (574, '0', 'admin用户登录', 'test', 'admin', '2021-08-05 13:09:16', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (575, '0', 'admin用户登录', 'test', 'admin', '2021-08-05 13:17:29', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (576, '0', '更新菜单', 'test', 'admin', '2021-08-05 22:12:31', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '452', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (577, '0', '更新菜单', 'test', 'admin', '2021-08-05 22:12:43', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '264', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (578, '0', 'admin用户登录', 'test', 'admin', '2021-08-06 00:41:19', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (579, '0', '新增菜单', 'test', 'admin', '2021-08-06 01:02:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '916', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (580, '0', '更新菜单', 'test', 'admin', '2021-08-06 01:03:00', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '404', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (581, '0', '新增菜单', 'test', 'admin', '2021-08-06 01:03:55', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '103', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (582, '0', '新增菜单', 'test', 'admin', '2021-08-06 01:04:25', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '137', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (583, '0', '新增菜单', 'test', 'admin', '2021-08-06 01:05:02', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '113', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (584, '0', '新增菜单', 'test', 'admin', '2021-08-06 01:05:54', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '99', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (585, '0', '新增菜单', 'test', 'admin', '2021-08-06 01:06:40', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '100', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (586, '0', '新增菜单', 'test', 'admin', '2021-08-06 01:07:16', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '100', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (587, '0', '新增菜单', 'test', 'admin', '2021-08-06 01:07:59', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '101', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (588, '0', '新增菜单', 'test', 'admin', '2021-08-06 01:18:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '473', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (589, '0', '修改角色', 'test', 'admin', '2021-08-06 01:18:55', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/role', 'PUT', NULL, '483', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (590, '0', 'admin用户登录', 'test', 'admin', '2021-08-06 01:19:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (591, '0', '更新菜单', 'test', 'admin', '2021-08-06 01:21:37', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '138', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (592, '0', 'admin用户退出', 'test', 'admin', '2021-08-06 01:22:10', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 05008f1d-ba3e-4f1a-82e3-9b0f6e2a3800', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (593, '0', 'admin用户登录', 'test', 'admin', '2021-08-06 01:22:32', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (594, '0', 'admin用户退出', 'test', 'admin', '2021-08-06 01:22:43', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/token/logout', 'DELETE', 'Bearer f45d83d4-c867-405b-84d6-56406bb8c880', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (595, '0', 'admin用户登录', 'test', 'admin', '2021-08-06 01:29:40', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (596, '0', '更新菜单', 'test', 'admin', '2021-08-06 01:31:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '139', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (597, '0', 'admin用户退出', 'test', 'admin', '2021-08-06 01:31:21', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 0e81c90b-c411-4033-ab01-aa780a848ad2', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (598, '0', 'admin用户登录', 'test', 'admin', '2021-08-06 01:31:23', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (599, '0', '更新菜单', 'test', 'admin', '2021-08-06 01:32:29', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '142', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (600, '0', 'admin用户退出', 'test', 'admin', '2021-08-06 01:32:34', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 1105b8c0-9f3d-4ce0-9aea-52506c82b28b', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (601, '0', 'admin用户登录', 'test', 'admin', '2021-08-06 01:34:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (602, '0', '更新菜单', 'test', 'admin', '2021-08-06 06:14:47', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '184', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (603, '0', '更新菜单', 'test', 'admin', '2021-08-06 06:19:09', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '119', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (604, '0', '更新菜单', 'test', 'admin', '2021-08-06 06:19:30', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '123', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (605, '0', '更新菜单', 'test', 'admin', '2021-08-06 06:19:55', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '120', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (606, '0', '更新菜单', 'test', 'admin', '2021-08-06 06:20:45', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '363', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (607, '0', '更新菜单', 'test', 'admin', '2021-08-06 06:21:18', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '119', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (608, '0', '新增菜单', 'test', 'admin', '2021-08-06 06:26:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '632', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (609, '0', '新增菜单', 'test', 'admin', '2021-08-06 06:28:23', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '564', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (610, '0', '新增菜单', 'test', 'admin', '2021-08-06 06:31:37', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '806', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (611, '0', '新增菜单', 'test', 'admin', '2021-08-06 06:32:49', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '1800', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (612, '0', 'admin用户退出', 'test', 'admin', '2021-08-06 06:34:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/token/logout', 'DELETE', 'Bearer a430f43b-7d52-4bab-8bcf-80818ae9bb67', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (613, '0', 'admin用户登录', 'test', 'admin', '2021-08-06 06:49:28', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (614, '0', '更新菜单', 'test', 'admin', '2021-08-06 06:56:46', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '154', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (615, '0', '更新菜单', 'test', 'admin', '2021-08-06 06:58:33', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '130', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (616, '0', 'admin用户退出', 'test', 'admin', '2021-08-06 06:58:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/token/logout', 'DELETE', 'Bearer fdd423c9-dce3-4dfb-a0b7-4eb9c987e2f2', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (617, '0', 'admin用户登录', 'test', 'admin', '2021-08-06 06:58:40', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (618, '0', '新增菜单', 'test', 'admin', '2021-08-06 07:45:49', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '642', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (619, '0', 'admin用户退出', 'test', 'admin', '2021-08-06 07:45:56', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 292da983-7af3-4c60-a2a0-aa3c6e528d8d', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (620, '0', 'admin用户登录', 'test', 'admin', '2021-08-06 07:45:59', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (621, '0', '新增菜单', 'test', 'admin', '2021-08-06 07:53:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '552', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (622, '0', 'admin用户退出', 'test', 'admin', '2021-08-06 07:53:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/token/logout', 'DELETE', 'Bearer e43c3cc9-dd69-4511-83b2-bfc6ce0df864', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (623, '0', 'admin用户登录', 'test', 'admin', '2021-08-06 07:53:19', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (624, '0', '更新菜单', 'test', 'admin', '2021-08-06 08:34:04', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '143', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (625, '0', 'admin用户退出', 'test', 'admin', '2021-08-06 08:34:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 44f99213-98e9-4348-888c-e9538bd3581a', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (626, '0', 'admin用户登录', 'test', 'admin', '2021-08-06 08:34:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (627, '0', 'admin用户登录', 'test', 'admin', '2021-08-06 23:47:50', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (628, '0', '新增菜单', 'test', 'admin', '2021-08-07 00:30:24', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '503', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (629, '0', '新增菜单', 'test', 'admin', '2021-08-07 00:32:12', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '146', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (630, '0', '修改角色', 'test', 'admin', '2021-08-07 00:32:45', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/role', 'PUT', NULL, '546', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (631, '0', 'admin用户退出', 'test', 'admin', '2021-08-07 00:32:50', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 28010e71-300f-42aa-b83a-92309c05a29b', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (632, '0', 'admin用户登录', 'test', 'admin', '2021-08-07 00:32:52', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (633, '0', '更新菜单', 'test', 'admin', '2021-08-07 00:34:01', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '148', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (634, '0', 'admin用户退出', 'test', 'admin', '2021-08-07 00:34:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 26da80cb-8289-4457-9dca-553663e67933', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (635, '0', 'admin用户登录', 'test', 'admin', '2021-08-07 00:34:19', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (636, '0', '更新菜单', 'test', 'admin', '2021-08-07 03:11:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '147', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (637, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:13:28', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '565', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (638, '0', '更新菜单', 'test', 'admin', '2021-08-07 05:13:48', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '114', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (639, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:18:54', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '474', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (640, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:21:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '490', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (641, '0', '更新菜单', 'test', 'admin', '2021-08-07 05:21:21', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '118', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (642, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:23:00', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '1701', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (643, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:24:52', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '496', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (644, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:26:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '440', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (645, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:26:48', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '103', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (646, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:28:47', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '531', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (647, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:33:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '532', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (648, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:36:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '536', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (649, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:37:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '116', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (650, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:37:59', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '118', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (651, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:38:42', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '116', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (652, '0', 'admin用户退出', 'test', 'admin', '2021-08-07 05:38:56', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 62779470-01de-4dc6-9935-0b39e5d86216', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (653, '0', 'admin用户登录', 'test', 'admin', '2021-08-07 05:38:58', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (654, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:41:37', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '545', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (655, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:43:40', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '474', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (656, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:44:28', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '111', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (657, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:44:55', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '117', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (658, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:46:03', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '114', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (659, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:48:09', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '558', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (660, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:48:37', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '114', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (661, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:51:37', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '109', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (662, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:52:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '118', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (663, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:53:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '510', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (664, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:54:23', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '115', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (665, '0', '新增菜单', 'test', 'admin', '2021-08-07 05:57:33', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '508', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (666, '0', '新增菜单', 'test', 'admin', '2021-08-07 06:00:21', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '447', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (667, '0', '新增菜单', 'test', 'admin', '2021-08-07 06:01:00', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '102', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (668, '0', '新增菜单', 'test', 'admin', '2021-08-07 06:01:32', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '105', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (669, '0', '新增菜单', 'test', 'admin', '2021-08-07 06:02:25', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '102', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (670, '0', '新增菜单', 'test', 'admin', '2021-08-07 06:04:43', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '517', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (671, '0', '新增菜单', 'test', 'admin', '2021-08-07 06:05:56', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'POST', NULL, '462', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (672, '0', '更新菜单', 'test', 'admin', '2021-08-07 06:06:31', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '112', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (673, '0', '更新菜单', 'test', 'admin', '2021-08-07 06:06:43', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '107', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (674, '0', '更新菜单', 'test', 'admin', '2021-08-07 06:06:50', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '106', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (675, '0', '更新菜单', 'test', 'admin', '2021-08-07 06:06:56', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '107', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (676, '0', '更新菜单', 'test', 'admin', '2021-08-07 06:07:02', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '107', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (677, '0', '更新菜单', 'test', 'admin', '2021-08-07 06:07:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '107', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (678, '0', '更新菜单', 'test', 'admin', '2021-08-07 06:07:37', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/menu', 'PUT', NULL, '106', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (679, '0', '修改角色', 'test', 'admin', '2021-08-07 06:08:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/role', 'PUT', NULL, '452', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (680, '0', 'admin用户退出', 'test', 'admin', '2021-08-07 06:08:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 06152b40-bba5-4b40-94d2-547cbd37c42d', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (681, '0', 'admin用户登录', 'test', 'admin', '2021-08-07 06:08:19', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(0) NOT NULL COMMENT '主键ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `permission` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单权限标识',
  `component` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端路由组件',
  `alias` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端路由参数',
  `path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端路由标识路径',
  `paramPath` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路径参数',
  `parent_id` bigint(0) NULL DEFAULT -1 COMMENT '父菜单ID',
  `sort` int(0) NULL DEFAULT 1 COMMENT '排序值',
  `type` tinyint(1) NULL DEFAULT 0 COMMENT '菜单类型 0目录 1菜单 2按钮',
  `redirect` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单重定向',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `disabled` tinyint(1) NULL DEFAULT 0 COMMENT '是否可用状态 0启用 1禁用',
  `ignore_auth` tinyint(1) NULL DEFAULT 0 COMMENT '是否忽略权限 0鉴权 1忽略',
  `affix` tinyint(1) NULL DEFAULT 0 COMMENT '是否固定头部 0不固定 1固定',
  `tag` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签',
  `ignore_keep_alive` tinyint(1) NULL DEFAULT 0 COMMENT '路由缓存',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `hide_menu` tinyint(1) NULL DEFAULT 0 COMMENT '隐藏菜单',
  `hide_tab` tinyint(1) NULL DEFAULT 0 COMMENT '隐藏Tab标签',
  `show_menu` tinyint(1) NULL DEFAULT 1 COMMENT '显示菜单',
  `current_active_menu` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '当前激活菜单',
  `frame_src` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'Iframe的源链接',
  `transition_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '动画类型',
  `hide_breadcrumb` tinyint(1) NULL DEFAULT 0 COMMENT '显示在头部指引中 0显示 1隐藏',
  `hide_children_in_menu` tinyint(1) NULL DEFAULT 0 COMMENT '显示子菜单 0显示 1隐藏',
  `hide_children_in_path` tinyint(1) NULL DEFAULT 0 COMMENT '隐藏子菜单路径 0显示 1隐藏',
  `carry_param` tinyint(1) NULL DEFAULT 0 COMMENT '是否带参数 0不带 1带',
  `single` tinyint(1) NULL DEFAULT 0 COMMENT '是否带子菜单',
  `is_link` tinyint(1) NULL DEFAULT 0 COMMENT '是否是链接',
  `ignore_route` tinyint(1) NULL DEFAULT 0 COMMENT '是否纯菜单 不带路由, 0带路由 1不带路由',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '启用状态 0启用 1禁用',
  `version` int(0) NULL DEFAULT 1 COMMENT '乐观锁',
  `create_user` bigint(0) NULL DEFAULT 0 COMMENT '创建人id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` bigint(0) NULL DEFAULT 0 COMMENT '更新人id',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `tenant_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (869184907492982817, '权限管理', NULL, 'LAYOUT', NULL, '/system', NULL, -1, 2, 0, '/system/account', 'routes.system.authority', 0, 0, 0, NULL, 0, 'ion:settings-outline', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-26 11:50:31', 0, '2021-07-31 01:36:56', 1);
INSERT INTO `sys_menu` VALUES (869199450768146465, '欢迎页面', NULL, '/dashboard/workbench/index', NULL, '/dashboard', NULL, -1, 1, 1, NULL, 'routes.dashboard.index', 0, 0, 1, NULL, 0, 'bx:bx-home', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, '2021-07-26 12:48:18', 0, '2021-08-06 14:58:32', 1);
INSERT INTO `sys_menu` VALUES (869203162005766177, '账户管理', NULL, '/system/account/index', NULL, 'account', NULL, 869184907492982817, 201, 1, NULL, 'routes.demo.system.account', 0, 0, 0, NULL, 1, 'ion:person-sharp', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-26 13:03:03', 0, '2021-07-31 02:42:29', 1);
INSERT INTO `sys_menu` VALUES (869204862980587553, '账户详情', NULL, '/system/account/AccountDetail', NULL, 'account_detail/:id', NULL, 869184907492982817, 202, 1, NULL, 'routes.demo.system.account_detail', 0, 0, 0, NULL, 1, 'ion:people-sharp', 1, 0, 0, '/system/account', NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-26 13:09:49', 0, '2021-07-31 11:37:31', 1);
INSERT INTO `sys_menu` VALUES (869206211482877985, '角色管理', NULL, '/system/role/index', NULL, 'role', NULL, 869184907492982817, 203, 1, NULL, 'routes.system.role.role', 0, 0, 0, NULL, 1, 'eos-icons:role-binding', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-26 13:15:10', 0, '2021-07-31 03:40:12', 1);
INSERT INTO `sys_menu` VALUES (869206521244811297, '菜单管理', NULL, '/system/menu/index', NULL, 'menu', NULL, 869184907492982817, 204, 1, NULL, 'routes.system.menu.menu', 0, 0, 0, NULL, 1, 'gg:menu-boxed', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-26 13:16:24', 0, '2021-07-31 03:41:07', 1);
INSERT INTO `sys_menu` VALUES (869206747531706401, '部门管理', NULL, '/system/dept/index', NULL, 'dept', NULL, 869184907492982817, 205, 1, NULL, 'routes.system.dept.dept', 0, 0, 0, NULL, 1, 'department|svg', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-26 13:17:18', 0, '2021-08-06 07:50:33', 1);
INSERT INTO `sys_menu` VALUES (869575493588877345, '系统管理', NULL, 'LAYOUT', NULL, '/manage', NULL, -1, 3, 0, '/manage/log', 'routes.system.system', 0, 0, 0, NULL, 0, 'ant-design:aliyun-outlined', 0, 0, 0, '/manage/log', NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-27 13:42:34', 0, '2021-07-31 06:00:03', 1);
INSERT INTO `sys_menu` VALUES (869581522607276097, '菜单新增', 'sys_menu_add', NULL, NULL, NULL, NULL, 869206521244811297, 20401, 2, NULL, 'routes.system.menu.add', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-27 14:06:31', 0, '2021-07-31 01:36:44', 1);
INSERT INTO `sys_menu` VALUES (870682177593933889, '用户新增', 'sys_user_add', NULL, NULL, NULL, NULL, 869203162005766177, 20101, 2, NULL, 'routes.system.user.add', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-30 15:00:08', 0, '2021-07-31 05:33:41', 1);
INSERT INTO `sys_menu` VALUES (870682484231110753, '用户编辑', 'sys_user_edit', NULL, NULL, NULL, NULL, 869203162005766177, 20102, 2, NULL, 'routes.system.user.edit', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-30 15:01:21', 0, '2021-07-31 05:33:41', 1);
INSERT INTO `sys_menu` VALUES (870961295355019297, '用户删除', 'sys_user_del', NULL, NULL, NULL, NULL, 869203162005766177, 20103, 2, NULL, 'routes.system.user.del', 0, 0, 0, NULL, 0, 'ion:ios-monitor-outline', 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-31 09:29:15', 0, '2021-07-31 05:33:41', 1);
INSERT INTO `sys_menu` VALUES (870962156940558401, '菜单编辑', 'sys_menu_edit', NULL, NULL, NULL, NULL, 869206521244811297, 20402, 2, NULL, 'routes.system.menu.edit', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-31 09:32:40', 0, '2021-07-31 05:33:41', 1);
INSERT INTO `sys_menu` VALUES (870962406287736929, '菜单删除', 'sys_menu_del', NULL, NULL, NULL, NULL, 869206521244811297, 20403, 2, NULL, 'routes.system.menu.del', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-31 09:33:40', 0, '2021-07-31 05:33:42', 1);
INSERT INTO `sys_menu` VALUES (870966712663539841, '角色新增', 'sys_role_add', NULL, NULL, NULL, NULL, 869206211482877985, 20201, 2, NULL, 'routes.system.role.add', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-31 09:50:46', 0, '2021-07-31 05:33:42', 1);
INSERT INTO `sys_menu` VALUES (870969404744007841, '角色修改', 'sys_role_edit', NULL, NULL, NULL, NULL, 869206211482877985, 20202, 2, NULL, 'routes.system.role.edit', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-31 10:01:28', 0, '2021-07-31 05:33:42', 1);
INSERT INTO `sys_menu` VALUES (870969566736416961, '角色删除', 'sys_role_del', NULL, NULL, NULL, NULL, 869206211482877985, 20203, 2, NULL, 'routes.system.role.del', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-31 10:02:07', 0, '2021-07-31 05:33:42', 1);
INSERT INTO `sys_menu` VALUES (870970979159900385, '部门新增', 'sys_dept_add', NULL, NULL, NULL, NULL, 869206747531706401, 20301, 2, NULL, 'routes.system.dept.add', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-31 10:07:44', 0, '2021-07-31 05:33:42', 1);
INSERT INTO `sys_menu` VALUES (870971142062473473, '部门修改', 'sys_dept_edit', NULL, NULL, NULL, NULL, 869206747531706401, 20302, 2, NULL, 'routes.system.dept.edit', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-31 10:08:22', 0, '2021-07-31 05:33:42', 1);
INSERT INTO `sys_menu` VALUES (870971316352581921, '部门删除', 'sys_dept_del', NULL, NULL, NULL, NULL, 869206747531706401, 20303, 2, NULL, 'routes.system.dept.del', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-31 10:09:04', 0, '2021-07-31 05:33:42', 1);
INSERT INTO `sys_menu` VALUES (870972257264992577, '租户管理', NULL, '/system/rent/index', NULL, 'rent', NULL, 869184907492982817, 206, 1, NULL, 'routes.system.rent.rent', 0, 0, 0, NULL, 1, 'ion:apps-sharp', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-31 10:12:48', 0, '2021-07-31 05:33:42', 1);
INSERT INTO `sys_menu` VALUES (870976015768224097, '租户新增', 'admin_systenant_add', NULL, NULL, NULL, NULL, 870972257264992577, 20501, 2, NULL, 'routes.system.rent.add', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-31 10:27:44', 0, '2021-07-31 05:33:42', 1);
INSERT INTO `sys_menu` VALUES (870976217421971841, '租户修改', 'admin_systenant_edit', NULL, NULL, NULL, NULL, 870972257264992577, 20502, 2, NULL, 'routes.system.rent.edit', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-31 10:28:32', 0, '2021-07-31 05:33:42', 1);
INSERT INTO `sys_menu` VALUES (870976399429599649, '租户删除', 'admin_systenant_del', NULL, NULL, NULL, NULL, 870972257264992577, 20503, 2, NULL, 'routes.system.dept.del', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-31 10:29:16', 0, '2021-07-31 05:33:42', 1);
INSERT INTO `sys_menu` VALUES (871020629682290721, '日志管理', NULL, '/manage/log/index', NULL, 'log', NULL, 869575493588877345, 301, 1, NULL, 'routes.manage.log', 0, 0, 0, NULL, 0, 'ion:logo-instagram', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-31 13:25:01', 0, '2021-07-31 05:45:01', 1);
INSERT INTO `sys_menu` VALUES (871027069025255489, '日志删除', 'sys_log_del', NULL, NULL, NULL, NULL, 871020629682290721, 30101, 2, NULL, 'routes.manage.logdel', 0, 0, 0, NULL, 0, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-31 13:50:36', 0, '2021-07-31 06:33:22', 1);
INSERT INTO `sys_menu` VALUES (871027903658197089, '字典管理', NULL, '/manage/dict', NULL, 'dict', NULL, 869575493588877345, 302, 1, NULL, 'routes.manage.dict.manage', 0, 0, 0, NULL, 1, 'ion:dice-outline', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-31 13:53:55', 0, '2021-07-31 06:33:22', 1);
INSERT INTO `sys_menu` VALUES (871030503799521409, '用户信息', NULL, '/account/center/index', NULL, '/account', NULL, -1, 10000, 1, NULL, 'routes.account.user', 0, 0, 0, NULL, 1, 'ion:skull-outline', 1, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-31 14:04:15', 0, '2021-08-06 16:34:04', 1);
INSERT INTO `sys_menu` VALUES (871401705575546913, '字典新增', 'sys_dict_add', NULL, NULL, NULL, NULL, 871027903658197089, 30201, 2, NULL, 'routes.manage.dict.add', 0, 0, 0, NULL, 0, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-01 14:39:17', 0, '2021-08-01 14:39:17', 1);
INSERT INTO `sys_menu` VALUES (871401867974803521, '字典修改', 'sys_dict_edit', NULL, NULL, NULL, NULL, 871027903658197089, 30202, 2, NULL, 'routes.manage.dict.edit', 0, 0, 0, NULL, 0, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-01 14:39:55', 0, '2021-08-01 14:39:55', 1);
INSERT INTO `sys_menu` VALUES (871402021096259681, '字典删除', 'sys_dict_del', NULL, NULL, NULL, NULL, 871027903658197089, 30203, 2, NULL, 'routes.manage.dict.del', 0, 0, 0, NULL, 0, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-01 14:40:32', 0, '2021-08-01 14:40:32', 1);
INSERT INTO `sys_menu` VALUES (871402711369646209, '参数管理', NULL, '/manager/param/index', NULL, 'param', NULL, 869575493588877345, 303, 1, NULL, 'routes.manage.param.manage', 0, 0, 0, NULL, 1, 'ion:cog', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-01 14:43:16', 0, '2021-08-01 14:43:16', 1);
INSERT INTO `sys_menu` VALUES (871402966945366177, '参数新增', 'admin_syspublicparam_add', NULL, NULL, NULL, NULL, 871402711369646209, 303, 2, NULL, 'routes.manage.param.add', 0, 0, 0, NULL, 0, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-01 14:44:17', 0, '2021-08-01 14:44:17', 1);
INSERT INTO `sys_menu` VALUES (871403184327753921, '参数编辑', 'admin_syspublicparam_edit', NULL, NULL, NULL, NULL, 871402711369646209, 30302, 2, NULL, 'routes.manage.param.edit', 0, 0, 0, NULL, 0, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-01 14:45:09', 0, '2021-08-01 14:45:09', 1);
INSERT INTO `sys_menu` VALUES (871403354469695713, '参数删除', 'admin_syspublicparam_del', NULL, NULL, NULL, NULL, 871402711369646209, 30303, 2, NULL, 'routes.manage.param.del', 0, 0, 0, NULL, 0, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-01 14:45:50', 0, '2021-08-01 14:45:50', 1);
INSERT INTO `sys_menu` VALUES (871403818963697921, '终端管理', NULL, '/manage/client', NULL, 'client', NULL, 869575493588877345, 304, 1, NULL, 'routes.manage.client.manager', 0, 0, 0, NULL, 1, 'ion:layers-outline', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-01 14:47:41', 0, '2021-08-01 14:47:41', 1);
INSERT INTO `sys_menu` VALUES (871404332103237921, '终端新增', 'sys_client_add', NULL, NULL, NULL, NULL, 871403818963697921, 30401, 2, NULL, 'routes.manage.client.add', 0, 0, 0, NULL, 0, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-01 14:49:43', 0, '2021-08-01 14:49:43', 1);
INSERT INTO `sys_menu` VALUES (871404473640026433, '终端修改', 'sys_client_edit', NULL, NULL, NULL, NULL, 871403818963697921, 30402, 2, NULL, 'routes.manage.client.edit', 0, 0, 0, NULL, 0, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-01 14:50:17', 0, '2021-08-01 14:50:17', 1);
INSERT INTO `sys_menu` VALUES (871404597615264097, '终端删除', 'sys_client_del', NULL, NULL, NULL, NULL, 871403818963697921, 30403, 2, NULL, 'routes.manage.client.del', 0, 0, 0, NULL, 0, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-01 14:50:46', 0, '2021-08-01 14:50:46', 1);
INSERT INTO `sys_menu` VALUES (871405035261526401, '密钥管理', NULL, '/manage/social', NULL, 'social', NULL, 869575493588877345, 305, 1, NULL, 'routes.manage.social.manage', 0, 0, 0, NULL, 0, 'ion:ios-attach', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-01 14:52:31', 0, '2021-08-01 14:53:44', 1);
INSERT INTO `sys_menu` VALUES (871405499373846945, '密钥新增', 'sys_social_details_add', NULL, NULL, NULL, NULL, 871405035261526401, 30501, 2, NULL, 'routes.manage.social.add', 0, 0, 0, NULL, 0, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-01 14:54:21', 0, '2021-08-01 14:54:21', 1);
INSERT INTO `sys_menu` VALUES (871405632945652161, '密钥修改', 'sys_social_details_edit', NULL, NULL, NULL, NULL, 871405035261526401, 30502, 2, NULL, 'routes.manage.social.edit', 0, 0, 0, NULL, 0, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-01 14:54:53', 0, '2021-08-01 14:54:53', 1);
INSERT INTO `sys_menu` VALUES (871405747479511521, '密钥删除', 'sys_social_details_del', NULL, NULL, NULL, NULL, 871405035261526401, 30503, 2, NULL, 'routes.manage.social.del', 0, 0, 0, NULL, 0, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-01 14:55:20', 0, '2021-08-01 14:55:20', 1);
INSERT INTO `sys_menu` VALUES (871406153270034945, '令牌管理', NULL, '/manage/token', NULL, 'token', NULL, 869575493588877345, 306, 1, NULL, 'routes.manage.token.manage', 0, 0, 0, NULL, 0, 'ion:md-book', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-01 14:56:57', 0, '2021-08-01 14:56:57', 1);
INSERT INTO `sys_menu` VALUES (871406293401731617, '令牌删除', 'sys_token_del', NULL, NULL, NULL, NULL, 871406153270034945, 30601, 2, NULL, 'routes.manage.token.del', 0, 0, 0, NULL, 0, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-01 14:57:31', 0, '2021-08-01 14:57:31', 1);
INSERT INTO `sys_menu` VALUES (871406584394154561, '动态路由', NULL, '/manage/router', NULL, 'router', NULL, 869575493588877345, 307, 1, NULL, 'routes.manage.router', 0, 0, 0, NULL, 0, 'ion:md-git-pull-request', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-01 14:58:40', 0, '2021-08-01 14:58:40', 1);
INSERT INTO `sys_menu` VALUES (871407461074993761, '服务治理', NULL, 'LAYOUT', NULL, '/monitor', NULL, -1, 4, 0, '/governance/cache', 'routes.governance.governance', 0, 0, 0, NULL, 0, 'ion:ios-monitor-outline', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-01 15:02:09', 0, '2021-08-06 14:21:17', 1);
INSERT INTO `sys_menu` VALUES (871408422384304769, '缓存监控', NULL, '/governance/cache', NULL, 'cache', NULL, 871407461074993761, 402, 1, NULL, 'routes.governance.cache', 0, 0, 0, NULL, 1, 'ion:newspaper-sharp', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-01 15:05:58', 0, '2021-08-06 14:20:44', 1);
INSERT INTO `sys_menu` VALUES (871435584181109409, 'WS通话', NULL, '/monitor/ws/index', NULL, 'ws', NULL, 871407461074993761, 10002, 1, NULL, 'routes.monitor.ws', 0, 0, 0, NULL, 0, 'ion:link-outline', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-01 16:53:54', 0, '2021-08-07 13:13:48', 1);
INSERT INTO `sys_menu` VALUES (873128912970842145, '任务管理', NULL, '/manager/job-manage/index', NULL, '/job-manager', NULL, 869575493588877345, 308, 1, NULL, 'routes.manage.job.manage', 0, 0, 0, NULL, 1, 'ion:invert-mode', 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-06 09:02:35', 0, '2021-08-06 09:02:59', 1);
INSERT INTO `sys_menu` VALUES (873129249983168577, '任务新增', 'job_sys_job_add', NULL, NULL, NULL, NULL, 873128912970842145, 30801, 2, NULL, 'routes.manage.job.add', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-06 09:03:55', 0, '2021-08-06 09:03:55', 1);
INSERT INTO `sys_menu` VALUES (873129375329943649, '任务修改', 'job_sys_job_edit', NULL, NULL, NULL, NULL, 873128912970842145, 30802, 2, NULL, 'routes.manage.job.edit', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-06 09:04:25', 0, '2021-08-06 09:04:25', 1);
INSERT INTO `sys_menu` VALUES (873129530112344193, '任务删除', 'job_sys_job_del', NULL, NULL, NULL, NULL, 873128912970842145, 30803, 2, NULL, 'routes.manage.job.del', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-06 09:05:02', 0, '2021-08-06 09:05:02', 1);
INSERT INTO `sys_menu` VALUES (873129747301793953, '任务暂停', 'job_sys_job_shutdown_job', NULL, NULL, NULL, NULL, 873128912970842145, 30804, 2, NULL, 'routes.manage.job.shutdown', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-06 09:05:54', 0, '2021-08-06 09:05:54', 1);
INSERT INTO `sys_menu` VALUES (873129940969586881, '任务开始', 'job_sys_job_start_job', NULL, NULL, NULL, NULL, 873128912970842145, 30805, 2, NULL, 'routes.manage.job.start', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-06 09:06:40', 0, '2021-08-06 09:06:40', 1);
INSERT INTO `sys_menu` VALUES (873130092551733473, '任务刷新', 'job_sys_job_refresh_job', NULL, NULL, NULL, NULL, 873128912970842145, 30806, 2, NULL, 'routes.manage.job.refresh', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-06 09:07:16', 0, '2021-08-06 09:07:16', 1);
INSERT INTO `sys_menu` VALUES (873130271401050369, '执行任务', 'job_sys_job_run_job', NULL, NULL, NULL, NULL, 873128912970842145, 30807, 2, NULL, 'routes.manage.job.run', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-06 09:07:59', 0, '2021-08-06 09:07:59', 1);
INSERT INTO `sys_menu` VALUES (873132883487752481, '服务监控', NULL, 'IFrame', NULL, 'http://laputa-monitor:5001', NULL, 871407461074993761, 403, 1, NULL, 'routes.governance.monitor', 0, 0, 0, NULL, 0, 'ion:cog-sharp', 0, 0, 1, NULL, 'http://laputa-monitor:5001', NULL, 0, 0, 0, 0, 0, 1, 0, 0, NULL, 0, '2021-08-06 09:18:22', 0, '2021-08-06 09:32:29', 1);
INSERT INTO `sys_menu` VALUES (873210319982297121, '流量监控', NULL, '/governance/', NULL, 'http://laputa-sentinel:5020', NULL, 871407461074993761, 404, 1, NULL, 'routes.governance.flow', 0, 0, 0, NULL, 0, 'ion:link-outline', 0, 0, 1, NULL, 'http://laputa-sentinel:5020', NULL, 0, 0, 0, 0, 0, 1, 0, 0, NULL, 0, '2021-08-06 14:26:04', 0, '2021-08-06 14:26:04', 1);
INSERT INTO `sys_menu` VALUES (873210902348824641, '接口文档', NULL, 'IFrame', NULL, 'laputa-gateway:9999/swagger-ui/index.html', NULL, 871407461074993761, 405, 1, NULL, 'routes.governance.swagger', 0, 0, 0, NULL, 0, 'ion:expand-sharp', 0, 0, 0, NULL, 'laputa-gateway:9999/swagger-ui/index.html', NULL, 0, 0, 0, 0, 0, 1, 0, 0, NULL, 0, '2021-08-06 14:28:23', 0, '2021-08-06 14:28:23', 1);
INSERT INTO `sys_menu` VALUES (873211711472009313, '事务监控', NULL, '/governance/transaction/index', NULL, 'transaction', NULL, 871407461074993761, 406, 1, '/governance/transaction', 'routes.governance.transaction', 0, 0, 0, NULL, 0, 'ion:qr-code', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-06 14:31:36', 0, '2021-08-06 14:31:36', 1);
INSERT INTO `sys_menu` VALUES (873212011104698497, '文档扩展', NULL, 'IFrame', NULL, 'http://laputa-gateway:9999/doc.html', NULL, 871407461074993761, 407, 1, NULL, '文档扩展', 0, 0, 0, NULL, 0, 'ion:mail-open', 0, 0, 1, NULL, 'http://laputa-gateway:9999/doc.html', NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-06 14:32:47', 0, '2021-08-06 14:32:47', 1);
INSERT INTO `sys_menu` VALUES (873230387868860577, '在线事务', NULL, '/governance/tx/model', NULL, 'tx/model', NULL, 873211711472009313, 40801, 1, NULL, 'routes.governance.ts.monitor', 0, 0, 0, NULL, 1, 'ion:person-remove-sharp', 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-06 15:45:49', 0, '2021-08-06 15:45:49', 1);
INSERT INTO `sys_menu` VALUES (873232239243034817, '任务日志', NULL, '/governance/job-log/index', NULL, '/job-log', NULL, 873128912970842145, 40608, 1, NULL, 'routes.governance.job-log', 0, 0, 0, NULL, 1, 'ion:help-buoy-outline', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-06 15:53:10', 0, '2021-08-06 15:53:10', 1);
INSERT INTO `sys_menu` VALUES (873483202340061217, '图标演示', NULL, '/demo/feat/icon', NULL, '/icon', NULL, 869575493588877345, 10001, 1, NULL, 'routes.system.rent.rent', 0, 0, 0, NULL, 1, 'download-count', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 1, 0, 1, NULL, 0, '2021-08-07 08:30:24', 0, '2021-08-07 08:30:24', 1);
INSERT INTO `sys_menu` VALUES (873483654829965377, '图标演示', NULL, '/demo/feat/icon/index', NULL, '/demo/feat/icon', NULL, 869575493588877345, 10001, 1, NULL, 'routes.demo.feat.icon', 0, 0, 0, NULL, 0, 'test|svg', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 08:32:12', 0, '2021-08-07 11:11:26', 1);
INSERT INTO `sys_menu` VALUES (873554436364959841, '协同管理', NULL, 'LAYOUT', NULL, '/flowwork', NULL, -1, 6, 0, '/flowwork/manage', 'routes.workflow.manage', 0, 0, 0, NULL, 0, 'total-sales|svg', 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:13:28', 0, '2021-08-07 13:21:22', 1);
INSERT INTO `sys_menu` VALUES (873555802890174593, '模型管理', NULL, '/workflow/model/index', NULL, '/model', NULL, 873554436364959841, 601, 1, NULL, 'routes.workflow.model', 0, 0, 0, NULL, 1, 'transaction|svg', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:18:54', 0, '2021-08-07 13:18:54', 1);
INSERT INTO `sys_menu` VALUES (873556352776011937, '流程管理', NULL, '/workflow/process/index', NULL, '/process', NULL, 873554436364959841, 602, 1, NULL, 'routes.workflow.process', 0, 0, 0, NULL, 1, 'ion:social-facebook-outline', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:21:05', 0, '2021-08-07 13:21:05', 1);
INSERT INTO `sys_menu` VALUES (873556829743874241, '请假管理', NULL, '/workflow/holiday/index', NULL, '/holiday', NULL, 873554436364959841, 603, 1, NULL, 'routes.workflow.holiday.manage', 0, 0, 0, NULL, 1, 'sun|svg', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:22:58', 0, '2021-08-07 13:22:58', 1);
INSERT INTO `sys_menu` VALUES (873557306212614369, '请假新增', 'act_leavebill_add', NULL, NULL, NULL, NULL, 873556829743874241, 60301, 2, NULL, 'routes.workflow.holiday.add', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:24:52', 0, '2021-08-07 13:24:52', 1);
INSERT INTO `sys_menu` VALUES (873557626175095041, '请假修改', 'act_leavebill_edit', NULL, NULL, NULL, NULL, 873556829743874241, 60302, 2, NULL, 'routes.workflow.holiday.edit', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:26:08', 0, '2021-08-07 13:26:08', 1);
INSERT INTO `sys_menu` VALUES (873557794379268385, '请假删除', 'act_leavebill_del', NULL, NULL, NULL, NULL, 873556829743874241, 60303, 2, NULL, 'routes.workflow.holiday.del', 0, 0, 0, NULL, 0, NULL, 0, 0, 0, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:26:48', 0, '2021-08-07 13:26:48', 1);
INSERT INTO `sys_menu` VALUES (873558291597230401, '待办任务', NULL, '/workflow/task/index', NULL, '/task', NULL, 873554436364959841, 604, 1, NULL, 'routes.workflow.task', 0, 0, 0, NULL, 1, 'ion:grid-outline', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:28:47', 0, '2021-08-07 13:28:47', 1);
INSERT INTO `sys_menu` VALUES (873559397698109793, '支付管理', NULL, 'LAYOUT', NULL, '/pay', NULL, -1, 7, 0, '/pay/manager', 'routes.pay.manage', 0, 0, 0, NULL, 1, 'ion:logo-alipay', 0, 0, 1, '/pay/manager', NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:33:11', 0, '2021-08-07 13:33:11', 1);
INSERT INTO `sys_menu` VALUES (873560168468578689, '渠道管理', NULL, '/pay/channel/index', NULL, '/channel', NULL, 873559397698109793, 701, 1, NULL, 'routes.pay.channel.manage', 0, 0, 0, NULL, 1, 'ion:stats-chart-outline', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:36:14', 0, '2021-08-07 13:36:14', 1);
INSERT INTO `sys_menu` VALUES (873560395871158689, '增加渠道', 'pay_paychannel_add', NULL, NULL, NULL, NULL, 873560168468578689, 70101, 2, NULL, 'routes.pay.channel.add', 0, 0, 0, NULL, 1, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:37:09', 0, '2021-08-07 13:37:09', 1);
INSERT INTO `sys_menu` VALUES (873560609117962689, '编辑渠道', 'pay_paychannel_edit', NULL, NULL, NULL, NULL, 873560168468578689, 70102, 2, NULL, 'routes.pay.channel.edit', 0, 0, 0, NULL, 1, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:37:59', 0, '2021-08-07 13:37:59', 1);
INSERT INTO `sys_menu` VALUES (873560788403487201, '删除渠道', 'pay_paychannel_del', NULL, NULL, NULL, NULL, 873560168468578689, 70103, 2, NULL, 'routes.pay.channel.del', 0, 0, 0, NULL, 1, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:38:42', 0, '2021-08-07 13:38:42', 1);
INSERT INTO `sys_menu` VALUES (873561519357428225, '收银台', NULL, '/pay/cashier/index', NULL, '/cashier', NULL, 873559397698109793, 702, 1, NULL, 'routes.pay.cashier', 0, 0, 0, NULL, 1, 'download-count|svg', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:41:36', 0, '2021-08-07 13:41:36', 1);
INSERT INTO `sys_menu` VALUES (873562036179567137, '商品订单', NULL, '/pay/goods/index', NULL, '/goods', NULL, 873559397698109793, 703, 1, NULL, 'routes.pay.goods.manage', 0, 0, 0, NULL, 1, 'ion:logo-instagram', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:43:40', 0, '2021-08-07 13:43:40', 1);
INSERT INTO `sys_menu` VALUES (873562239133549121, '商品订单新增', 'generator_paygoodsorder_add', NULL, NULL, NULL, NULL, 873562036179567137, 70301, 2, NULL, 'routes.pay.goods.add', 0, 0, 0, NULL, 1, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:44:28', 0, '2021-08-07 13:44:28', 1);
INSERT INTO `sys_menu` VALUES (873562354242028129, '商品订单修改', 'generator_paygoodsorder_edit', NULL, NULL, NULL, NULL, 873562036179567137, 70302, 2, NULL, 'routes.pay.goods.edit', 0, 0, 0, NULL, 1, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:44:55', 0, '2021-08-07 13:44:55', 1);
INSERT INTO `sys_menu` VALUES (873562637848281729, '商品订单删除', 'generator_paygoodsorder_del', NULL, NULL, NULL, NULL, 873562036179567137, 70303, 2, NULL, 'routes.pay.goods.del', 0, 0, 0, NULL, 1, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:46:03', 0, '2021-08-07 13:46:03', 1);
INSERT INTO `sys_menu` VALUES (873563166842290849, '支付订单', NULL, '/pay/orders/index', NULL, '/orders', NULL, 873559397698109793, 704, 1, NULL, 'routes.pay.orders.manage', 0, 0, 0, NULL, 1, 'ion:logo-closed-captioning', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:48:09', 0, '2021-08-07 13:48:09', 1);
INSERT INTO `sys_menu` VALUES (873563283007734465, '支付订单新增', 'generator_paytradeorder_add', NULL, NULL, NULL, NULL, 873563166842290849, 70401, 2, NULL, 'routes.pay.orders.add', 0, 0, 0, NULL, 1, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:48:37', 0, '2021-08-07 13:48:37', 1);
INSERT INTO `sys_menu` VALUES (873564038246695649, '支付订单修改', 'generator_paytradeorder_edit', NULL, NULL, NULL, NULL, 873563166842290849, 70402, 2, NULL, 'routes.pay.orders.edit', 0, 0, 0, NULL, 1, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:51:37', 0, '2021-08-07 13:51:37', 1);
INSERT INTO `sys_menu` VALUES (873564228349330177, '支付订单删除', 'generator_paytradeorder_del', NULL, NULL, NULL, NULL, 873563166842290849, 70403, 2, NULL, 'routes.pay.orders.del', 0, 0, 0, NULL, 1, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:52:22', 0, '2021-08-07 13:52:22', 1);
INSERT INTO `sys_menu` VALUES (873564538656523041, '回调记录', NULL, '/pay/notify/index', NULL, '/notify', NULL, 873559397698109793, 705, 1, NULL, 'routes.pay.notify.manage', 0, 0, 0, NULL, 1, 'ion:barcode', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:53:36', 0, '2021-08-07 13:53:36', 1);
INSERT INTO `sys_menu` VALUES (873564735285494593, '记录删除', 'generator_paynotifyrecord_del', NULL, NULL, NULL, NULL, 873564538656523041, 70501, 2, NULL, 'routes.pay.notify.del', 0, 0, 0, NULL, 1, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:54:23', 0, '2021-08-07 13:54:23', 1);
INSERT INTO `sys_menu` VALUES (873565530261291873, '开发平台', NULL, 'LAYOUT', NULL, '/develop', NULL, -1, 8, 0, '/develop/form', 'routes.develop.platform', 0, 0, 0, NULL, 1, 'ion:code-download', 0, 0, 1, '/develop/form', NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 13:57:33', 0, '2021-08-07 14:06:31', 1);
INSERT INTO `sys_menu` VALUES (873566236695331713, '表单管理', NULL, '/develop/form/index', NULL, '/form', NULL, 873565530261291873, 801, 1, NULL, 'routes.develop.form.manage', 0, 0, 0, NULL, 1, 'ion:cloud-done', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 14:00:21', 0, '2021-08-07 14:06:44', 1);
INSERT INTO `sys_menu` VALUES (873566402588443553, '表单新增', 'gen_form_add', NULL, NULL, NULL, NULL, 873566236695331713, 701, 2, NULL, 'routes.develop.form.add', 0, 0, 0, NULL, 1, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 14:01:01', 0, '2021-08-07 14:06:50', 1);
INSERT INTO `sys_menu` VALUES (873566533261984705, '表单修改', 'gen_form_edit', NULL, NULL, NULL, NULL, 873566236695331713, 80102, 2, NULL, 'routes.develop.form.edit', 0, 0, 0, NULL, 1, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 14:01:32', 0, '2021-08-07 14:06:56', 1);
INSERT INTO `sys_menu` VALUES (873566756264739809, '表单删除', 'gen_form_del', NULL, NULL, NULL, NULL, 873566236695331713, 80103, 2, NULL, 'routes.develop.form.del', 0, 0, 0, NULL, 1, NULL, 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 14:02:25', 0, '2021-08-07 14:07:03', 1);
INSERT INTO `sys_menu` VALUES (873567335372293121, '数据源管理', '/gen/datasource/index', '/gen/datasource/index', NULL, '/datasource', NULL, 873565530261291873, 802, 1, NULL, 'routes.develop.datasource', 0, 0, 0, NULL, 1, 'ion:md-paper', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 14:04:43', 0, '2021-08-07 14:07:09', 1);
INSERT INTO `sys_menu` VALUES (873567638939239457, '代码生成', NULL, '/develop/code/index', NULL, '/code', NULL, 873565530261291873, 803, 1, NULL, 'routes.develop.gen', 0, 0, 0, NULL, 1, 'ion:calendar-outline', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-08-07 14:05:55', 0, '2021-08-07 14:07:37', 1);

-- ----------------------------
-- Table structure for sys_oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client_details`;
CREATE TABLE `sys_oauth_client_details`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `client_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `access_token_validity` int(0) NULL DEFAULT NULL,
  `refresh_token_validity` int(0) NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  `tenant_id` int(0) NOT NULL DEFAULT 0 COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '终端信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oauth_client_details
-- ----------------------------
INSERT INTO `sys_oauth_client_details` VALUES (1, 'app', NULL, 'app', 'server', 'password,refresh_token,authorization_code,client_credentials,implicit', NULL, NULL, 43200, 2592001, '{\"enc_flag\":\"1\",\"captcha_flag\":\"1\"}', 'true', '0', 1);
INSERT INTO `sys_oauth_client_details` VALUES (2, 'daemon', NULL, 'daemon', 'server', 'password,refresh_token', NULL, NULL, NULL, NULL, '{\"enc_flag\":\"1\",\"captcha_flag\":\"1\"}', 'true', '0', 1);
INSERT INTO `sys_oauth_client_details` VALUES (3, 'gen', NULL, 'gen', 'server', 'password,refresh_token', NULL, NULL, NULL, NULL, '{\"enc_flag\":\"1\",\"captcha_flag\":\"1\"}', 'true', '0', 1);
INSERT INTO `sys_oauth_client_details` VALUES (4, 'mp', NULL, 'mp', 'server', 'password,refresh_token', NULL, NULL, NULL, NULL, '{\"enc_flag\":\"1\",\"captcha_flag\":\"1\"}', 'true', '0', 1);
INSERT INTO `sys_oauth_client_details` VALUES (5, 'pig', NULL, 'pig', 'server', 'password,refresh_token,authorization_code,client_credentials', 'http://localhost:4040/sso1/login,http://localhost:4041/sso1/login,http://localhost:8080/renren-admin/sys/oauth2-sso,http://localhost:8090/sys/oauth2-sso', NULL, NULL, NULL, '{\"enc_flag\":\"1\",\"captcha_flag\":\"1\"}', 'false', '0', 1);
INSERT INTO `sys_oauth_client_details` VALUES (6, 'test', NULL, 'test', 'server', 'password,refresh_token', NULL, NULL, NULL, NULL, '{ \"enc_flag\":\"1\",\"captcha_flag\":\"0\"}', 'true', '0', 1);
INSERT INTO `sys_oauth_client_details` VALUES (10, 'app', NULL, 'app', 'server', 'password,refresh_token,authorization_code,client_credentials,implicit', NULL, NULL, 43200, 2592001, '{\"enc_flag\":\"1\",\"captcha_flag\":\"1\"}', 'true', '0', 2);
INSERT INTO `sys_oauth_client_details` VALUES (11, 'daemon', NULL, 'daemon', 'server', 'password,refresh_token', NULL, NULL, NULL, NULL, '{\"enc_flag\":\"1\",\"captcha_flag\":\"1\"}', 'true', '0', 2);
INSERT INTO `sys_oauth_client_details` VALUES (12, 'gen', NULL, 'gen', 'server', 'password,refresh_token', NULL, NULL, NULL, NULL, '{\"enc_flag\":\"1\",\"captcha_flag\":\"1\"}', 'true', '0', 2);
INSERT INTO `sys_oauth_client_details` VALUES (13, 'mp', NULL, 'mp', 'server', 'password,refresh_token', NULL, NULL, NULL, NULL, '{\"enc_flag\":\"1\",\"captcha_flag\":\"1\"}', 'true', '0', 2);
INSERT INTO `sys_oauth_client_details` VALUES (14, 'pig', NULL, 'pig', 'server', 'password,refresh_token,authorization_code,client_credentials', 'http://localhost:4040/sso1/login,http://localhost:4041/sso1/login,http://localhost:8080/renren-admin/sys/oauth2-sso,http://localhost:8090/sys/oauth2-sso', NULL, NULL, NULL, '{\"enc_flag\":\"1\",\"captcha_flag\":\"1\"}', 'false', '0', 2);
INSERT INTO `sys_oauth_client_details` VALUES (15, 'test', NULL, 'test', 'server', 'password,refresh_token', NULL, NULL, NULL, NULL, '{ \"enc_flag\":\"1\",\"captcha_flag\":\"0\"}', 'true', '0', 2);
INSERT INTO `sys_oauth_client_details` VALUES (16, 'laputa', NULL, 'laputa', 'server', 'password,authorization_code,client_credentials,refresh_token', NULL, NULL, 0, 0, '{\"enc_flag\":\"1\",\"captcha_flag\":\"1\"}', 'false', '0', 1);

-- ----------------------------
-- Table structure for sys_public_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_public_param`;
CREATE TABLE `sys_public_param`  (
  `public_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `public_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `public_key` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `public_value` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  `validate_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `public_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  `system` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  `tenant_id` int(0) NULL DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`public_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '公共参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_public_param
-- ----------------------------
INSERT INTO `sys_public_param` VALUES (1, '租户默认来源', 'TENANT_DEFAULT_ID', '1', '0', '', '2020-05-12 04:03:46', '2020-06-20 08:56:30', '2', '0', '1', 1);
INSERT INTO `sys_public_param` VALUES (2, '租户默认部门名称', 'TENANT_DEFAULT_DEPTNAME', '租户默认部门', '0', '', '2020-05-12 03:36:32', NULL, '2', '1', '0', 1);
INSERT INTO `sys_public_param` VALUES (3, '租户默认账户', 'TENANT_DEFAULT_USERNAME', 'admin', '0', '', '2020-05-12 04:05:04', NULL, '2', '1', '0', 1);
INSERT INTO `sys_public_param` VALUES (4, '租户默认密码', 'TENANT_DEFAULT_PASSWORD', '123456', '0', '', '2020-05-12 04:05:24', NULL, '2', '1', '0', 1);
INSERT INTO `sys_public_param` VALUES (5, '租户默认角色编码', 'TENANT_DEFAULT_ROLECODE', 'ROLE_ADMIN', '0', '', '2020-05-12 04:05:57', NULL, '2', '1', '0', 1);
INSERT INTO `sys_public_param` VALUES (6, '租户默认角色名称', 'TENANT_DEFAULT_ROLENAME', '租户默认角色', '0', '', '2020-05-12 04:06:19', NULL, '2', '1', '0', 1);
INSERT INTO `sys_public_param` VALUES (7, '表前缀', 'GEN_TABLE_PREFIX', 'tb_', '0', '', '2020-05-12 04:23:04', NULL, '9', '1', '0', 1);
INSERT INTO `sys_public_param` VALUES (8, '接口文档不显示的字段', 'GEN_HIDDEN_COLUMNS', 'tenant_id', '0', '', '2020-05-12 04:25:19', NULL, '9', '1', '0', 1);
INSERT INTO `sys_public_param` VALUES (9, '租户默认部门名称', 'TENANT_DEFAULT_DEPTNAME', '租户默认部门', '0', NULL, '2020-05-12 03:36:32', NULL, '2', '1', '0', 2);
INSERT INTO `sys_public_param` VALUES (10, '租户默认账户', 'TENANT_DEFAULT_USERNAME', 'admin', '0', NULL, '2020-05-12 04:05:04', NULL, '2', '1', '0', 2);
INSERT INTO `sys_public_param` VALUES (11, '租户默认密码', 'TENANT_DEFAULT_PASSWORD', '123456', '0', NULL, '2020-05-12 04:05:24', NULL, '2', '1', '0', 2);
INSERT INTO `sys_public_param` VALUES (12, '租户默认角色编码', 'TENANT_DEFAULT_ROLECODE', 'ROLE_ADMIN', '0', NULL, '2020-05-12 04:05:57', NULL, '2', '1', '0', 2);
INSERT INTO `sys_public_param` VALUES (13, '租户默认角色名称', 'TENANT_DEFAULT_ROLENAME', '租户默认角色', '0', NULL, '2020-05-12 04:06:19', NULL, '2', '1', '0', 2);
INSERT INTO `sys_public_param` VALUES (14, '表前缀', 'GEN_TABLE_PREFIX', 'tb_', '0', NULL, '2020-05-12 04:23:04', NULL, '9', '1', '0', 2);
INSERT INTO `sys_public_param` VALUES (15, '接口文档不显示的字段', 'GEN_HIDDEN_COLUMNS', 'tenant_id', '0', NULL, '2020-05-12 04:25:19', NULL, '9', '1', '0', 2);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(0) NOT NULL COMMENT '主键ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色标识',
  `role_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色描述',
  `ds_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '2' COMMENT '数据权限类型',
  `ds_scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据权限作用范围',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '启用状态 0启用 1禁用',
  `version` int(0) NULL DEFAULT 1 COMMENT '乐观锁',
  `create_user` bigint(0) NULL DEFAULT 0 COMMENT '创建人id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` bigint(0) NULL DEFAULT 0 COMMENT '更新人id',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `tenant_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_idx1_role_code`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (868497241403293729, '普通用户', 'COMMON_USER', '普通用户角色', '0', '1', 0, 1, 0, '2021-07-24 14:17:58', 0, '2021-08-07 14:08:11', 1);
INSERT INTO `sys_role` VALUES (868497478360498209, '租户用户', 'TENANT_USER', '租户默认角色', '0', '1,3', 0, 1, 0, '2021-07-24 14:18:55', 0, '2021-07-29 01:28:08', 1);
INSERT INTO `sys_role` VALUES (869934528154566689, '开发者', 'DEVELOPER', '开发者 有用的开发者', '0', '0', 0, NULL, 0, '2021-07-28 13:29:14', 0, '2021-07-29 01:28:10', 1);
INSERT INTO `sys_role` VALUES (869935288674156609, '管理者', 'ADMIN', '系统管理者', '0', '0', 0, NULL, 0, '2021-07-28 13:32:16', 0, '2021-07-29 01:28:11', 1);
INSERT INTO `sys_role` VALUES (869938094852276257, '通用者', 'COMMON', '通用用户', '0', '1，3', 0, NULL, 0, '2021-07-28 13:43:25', 0, '2021-07-28 13:48:54', 1);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(0) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (868494377528328225, 869184907492982817);
INSERT INTO `sys_role_menu` VALUES (868494377528328225, 869199450768146465);
INSERT INTO `sys_role_menu` VALUES (868494377528328225, 869203162005766177);
INSERT INTO `sys_role_menu` VALUES (868494377528328225, 869204862980587553);
INSERT INTO `sys_role_menu` VALUES (868494377528328225, 869206211482877985);
INSERT INTO `sys_role_menu` VALUES (868494377528328225, 869206521244811297);
INSERT INTO `sys_role_menu` VALUES (868494377528328225, 869206747531706401);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 869184907492982817);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 869199450768146465);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 869203162005766177);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 869204862980587553);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 869206211482877985);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 869206521244811297);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 869206747531706401);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 869575493588877345);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 869581522607276097);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 870682177593933889);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 870682484231110753);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 870961295355019297);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 870962156940558401);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 870962406287736929);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 870966712663539841);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 870969404744007841);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 870969566736416961);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 870970979159900385);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 870971142062473473);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 870971316352581921);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 870972257264992577);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 870976015768224097);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 870976217421971841);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 870976399429599649);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871020629682290721);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871027069025255489);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871027903658197089);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871030503799521409);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871401705575546913);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871401867974803521);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871402021096259681);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871402711369646209);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871402966945366177);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871403184327753921);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871403354469695713);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871403818963697921);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871404332103237921);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871404473640026433);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871404597615264097);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871405035261526401);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871405499373846945);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871405632945652161);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871405747479511521);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871406153270034945);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871406293401731617);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871406584394154561);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871407461074993761);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871408422384304769);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 871435584181109409);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873128912970842145);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873129249983168577);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873129375329943649);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873129530112344193);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873129747301793953);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873129940969586881);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873130092551733473);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873130271401050369);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873132883487752481);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873210319982297121);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873210902348824641);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873211711472009313);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873212011104698497);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873230387868860577);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873232239243034817);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873483654829965377);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873554436364959841);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873555802890174593);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873556352776011937);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873556829743874241);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873557306212614369);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873557626175095041);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873557794379268385);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873558291597230401);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873559397698109793);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873560168468578689);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873560395871158689);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873560609117962689);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873560788403487201);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873561519357428225);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873562036179567137);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873562239133549121);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873562354242028129);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873562637848281729);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873563166842290849);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873563283007734465);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873564038246695649);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873564228349330177);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873564538656523041);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873564735285494593);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873565530261291873);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873566236695331713);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873566402588443553);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873566533261984705);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873566756264739809);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873567335372293121);
INSERT INTO `sys_role_menu` VALUES (868497241403293729, 873567638939239457);
INSERT INTO `sys_role_menu` VALUES (868497478360498209, 869184907492982817);
INSERT INTO `sys_role_menu` VALUES (868497478360498209, 869199450768146465);
INSERT INTO `sys_role_menu` VALUES (868497478360498209, 869203162005766177);
INSERT INTO `sys_role_menu` VALUES (868497478360498209, 869204862980587553);
INSERT INTO `sys_role_menu` VALUES (868497478360498209, 869206211482877985);
INSERT INTO `sys_role_menu` VALUES (868497478360498209, 869206521244811297);
INSERT INTO `sys_role_menu` VALUES (868497478360498209, 869206747531706401);
INSERT INTO `sys_role_menu` VALUES (869934528154566689, 869184907492982817);
INSERT INTO `sys_role_menu` VALUES (869934528154566689, 869199450768146465);
INSERT INTO `sys_role_menu` VALUES (869934528154566689, 869203162005766177);
INSERT INTO `sys_role_menu` VALUES (869934528154566689, 869204862980587553);
INSERT INTO `sys_role_menu` VALUES (869934528154566689, 869206211482877985);
INSERT INTO `sys_role_menu` VALUES (869934528154566689, 869206521244811297);
INSERT INTO `sys_role_menu` VALUES (869934528154566689, 869206747531706401);
INSERT INTO `sys_role_menu` VALUES (869934528154566689, 869575493588877345);
INSERT INTO `sys_role_menu` VALUES (869934528154566689, 869581522607276097);
INSERT INTO `sys_role_menu` VALUES (869935288674156609, 869184907492982817);
INSERT INTO `sys_role_menu` VALUES (869935288674156609, 869199450768146465);
INSERT INTO `sys_role_menu` VALUES (869935288674156609, 869203162005766177);
INSERT INTO `sys_role_menu` VALUES (869935288674156609, 869204862980587553);
INSERT INTO `sys_role_menu` VALUES (869935288674156609, 869206211482877985);
INSERT INTO `sys_role_menu` VALUES (869935288674156609, 869206521244811297);
INSERT INTO `sys_role_menu` VALUES (869935288674156609, 869206747531706401);
INSERT INTO `sys_role_menu` VALUES (869935288674156609, 869575493588877345);
INSERT INTO `sys_role_menu` VALUES (869935288674156609, 869581522607276097);

-- ----------------------------
-- Table structure for sys_route_conf
-- ----------------------------
DROP TABLE IF EXISTS `sys_route_conf`;
CREATE TABLE `sys_route_conf`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `route_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `route_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `predicates` json NULL COMMENT '断言',
  `filters` json NULL COMMENT '过滤器',
  `uri` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `order` int(0) NULL DEFAULT 0 COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 120 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '路由配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_route_conf
-- ----------------------------
INSERT INTO `sys_route_conf` VALUES (1, '工作流管理模块', 'pigx-oa-platform', '[{\"args\": {\"_genkey_0\": \"/act/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-oa-platform', 0, '2019-10-16 16:44:41', '2021-07-06 06:46:21', '1');
INSERT INTO `sys_route_conf` VALUES (2, '认证中心', 'pigx-auth', '[{\"args\": {\"_genkey_0\": \"/auth/**\"}, \"name\": \"Path\"}]', '[{\"args\": {}, \"name\": \"ValidateCodeGatewayFilter\"}, {\"args\": {}, \"name\": \"PasswordDecoderFilter\"}]', 'lb://pigx-auth', 0, '2019-10-16 16:44:41', '2021-07-06 06:46:21', '1');
INSERT INTO `sys_route_conf` VALUES (3, '代码生成模块', 'pigx-codegen', '[{\"args\": {\"_genkey_0\": \"/gen/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-codegen', 0, '2019-10-16 16:44:41', '2021-07-06 06:46:21', '1');
INSERT INTO `sys_route_conf` VALUES (4, 'elastic-job定时任务模块', 'pigx-daemon-elastic-job', '[{\"args\": {\"_genkey_0\": \"/daemon/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-daemon-elastic-job', 0, '2019-10-16 16:44:41', '2021-07-06 06:46:21', '1');
INSERT INTO `sys_route_conf` VALUES (5, 'quartz定时任务模块', 'pigx-daemon-quartz', '[{\"args\": {\"_genkey_0\": \"/job/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-daemon-quartz', 0, '2019-10-16 16:44:41', '2021-07-06 06:46:21', '1');
INSERT INTO `sys_route_conf` VALUES (6, '分布式事务模块', 'pigx-tx-manager', '[{\"args\": {\"_genkey_0\": \"/tx/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-tx-manager', 0, '2019-10-16 16:44:41', '2021-07-06 06:46:21', '1');
INSERT INTO `sys_route_conf` VALUES (7, '通用权限模块', 'laputa-upms-service', '[{\"args\": {\"_genkey_0\": \"/admin/**\"}, \"name\": \"Path\"}]', '[{\"args\": {\"key-resolver\": \"#{@remoteAddrKeyResolver}\", \"redis-rate-limiter.burstCapacity\": \"1000\", \"redis-rate-limiter.replenishRate\": \"1000\"}, \"name\": \"RequestRateLimiter\"}]', 'lb://laputa-upms-service', 0, '2019-10-16 16:44:41', '2021-07-06 06:46:21', '1');
INSERT INTO `sys_route_conf` VALUES (8, '工作流长链接支持1', 'pigx-oa-platform-ws-1', '[{\"args\": {\"_genkey_0\": \"/act/ws/info/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-oa-platform', 0, '2019-10-16 16:44:41', '2021-07-06 06:46:21', '1');
INSERT INTO `sys_route_conf` VALUES (9, '工作流长链接支持2', 'pigx-oa-platform-ws-2', '[{\"args\": {\"_genkey_0\": \"/act/ws/**\"}, \"name\": \"Path\"}]', '[]', 'lb:ws://pigx-oa-platform', 100, '2019-10-16 16:44:41', '2021-07-06 06:46:21', '1');
INSERT INTO `sys_route_conf` VALUES (10, '微信公众号管理', 'pigx-mp-platform', '[{\"args\": {\"_genkey_0\": \"/mp/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-mp-platform', 0, '2019-10-16 16:44:41', '2021-07-06 06:46:21', '1');
INSERT INTO `sys_route_conf` VALUES (11, '支付管理', 'pigx-pay-platform', '[{\"args\": {\"_genkey_0\": \"/pay/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-pay-platform', 0, '2019-10-16 16:44:41', '2021-07-06 06:46:21', '1');
INSERT INTO `sys_route_conf` VALUES (12, '监控管理', 'pigx-monitor', '[{\"args\": {\"_genkey_0\": \"/monitor/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-monitor', 0, '2019-10-16 16:44:41', '2021-07-06 06:46:21', '1');
INSERT INTO `sys_route_conf` VALUES (13, '工作流管理模块', 'pigx-oa-platform', '[{\"args\": {\"_genkey_0\": \"/act/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-oa-platform', 0, '2021-07-06 06:46:21', '2021-07-07 00:58:27', '1');
INSERT INTO `sys_route_conf` VALUES (14, '认证中心', 'laputa-auth', '[{\"args\": {\"_genkey_0\": \"/auth/**\"}, \"name\": \"Path\"}]', '[{\"args\": {}, \"name\": \"ValidateCodeGatewayFilter\"}, {\"args\": {}, \"name\": \"PasswordDecoderFilter\"}]', 'lb://laputa-auth', 0, '2021-07-06 06:46:21', '2021-07-07 00:58:27', '1');
INSERT INTO `sys_route_conf` VALUES (15, '代码生成模块', 'pigx-codegen', '[{\"args\": {\"_genkey_0\": \"/gen/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-codegen', 0, '2021-07-06 06:46:21', '2021-07-07 00:58:27', '1');
INSERT INTO `sys_route_conf` VALUES (16, 'elastic-job定时任务模块', 'pigx-daemon-elastic-job', '[{\"args\": {\"_genkey_0\": \"/daemon/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-daemon-elastic-job', 0, '2021-07-06 06:46:21', '2021-07-07 00:58:27', '1');
INSERT INTO `sys_route_conf` VALUES (17, 'quartz定时任务模块', 'pigx-daemon-quartz', '[{\"args\": {\"_genkey_0\": \"/job/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-daemon-quartz', 0, '2021-07-06 06:46:21', '2021-07-07 00:58:27', '1');
INSERT INTO `sys_route_conf` VALUES (18, '分布式事务模块', 'pigx-tx-manager', '[{\"args\": {\"_genkey_0\": \"/tx/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-tx-manager', 0, '2021-07-06 06:46:21', '2021-07-07 00:58:27', '1');
INSERT INTO `sys_route_conf` VALUES (19, '通用权限模块', 'laputa-upms-service', '[{\"args\": {\"_genkey_0\": \"/admin/**\"}, \"name\": \"Path\"}]', '[{\"args\": {\"key-resolver\": \"#{@remoteAddrKeyResolver}\", \"redis-rate-limiter.burstCapacity\": \"1000\", \"redis-rate-limiter.replenishRate\": \"1000\"}, \"name\": \"RequestRateLimiter\"}]', 'lb://laputa-upms-service', 0, '2021-07-06 06:46:21', '2021-07-07 00:58:27', '1');
INSERT INTO `sys_route_conf` VALUES (20, '工作流长链接支持1', 'pigx-oa-platform-ws-1', '[{\"args\": {\"_genkey_0\": \"/act/ws/info/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-oa-platform', 0, '2021-07-06 06:46:21', '2021-07-07 00:58:27', '1');
INSERT INTO `sys_route_conf` VALUES (21, '工作流长链接支持2', 'pigx-oa-platform-ws-2', '[{\"args\": {\"_genkey_0\": \"/act/ws/**\"}, \"name\": \"Path\"}]', '[]', 'lb:ws://pigx-oa-platform', 100, '2021-07-06 06:46:21', '2021-07-07 00:58:27', '1');
INSERT INTO `sys_route_conf` VALUES (22, '微信公众号管理', 'pigx-mp-platform', '[{\"args\": {\"_genkey_0\": \"/mp/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-mp-platform', 0, '2021-07-06 06:46:21', '2021-07-07 00:58:27', '1');
INSERT INTO `sys_route_conf` VALUES (23, '支付管理', 'pigx-pay-platform', '[{\"args\": {\"_genkey_0\": \"/pay/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-pay-platform', 0, '2021-07-06 06:46:21', '2021-07-07 00:58:27', '1');
INSERT INTO `sys_route_conf` VALUES (24, '监控管理', 'pigx-monitor', '[{\"args\": {\"_genkey_0\": \"/monitor/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-monitor', 0, '2021-07-06 06:46:21', '2021-07-07 00:58:27', '1');
INSERT INTO `sys_route_conf` VALUES (25, '工作流管理模块', 'pigx-oa-platform', '[{\"args\": {\"_genkey_0\": \"/act/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-oa-platform', 0, '2021-07-07 00:58:27', '2021-07-07 01:06:11', '1');
INSERT INTO `sys_route_conf` VALUES (26, '认证中心', 'laputa-auth', '[{\"args\": {\"_genkey_0\": \"/auth/**\"}, \"name\": \"Path\"}]', '[{\"args\": {}, \"name\": \"ValidateCodeGatewayFilter\"}, {\"args\": {}, \"name\": \"PasswordDecoderFilter\"}]', 'lb://laputa-auth', 0, '2021-07-07 00:58:27', '2021-07-07 01:06:11', '1');
INSERT INTO `sys_route_conf` VALUES (27, '代码生成模块', 'laputa-codegen', '[{\"args\": {\"_genkey_0\": \"/gen/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-codegen', 0, '2021-07-07 00:58:27', '2021-07-07 01:06:11', '1');
INSERT INTO `sys_route_conf` VALUES (28, 'elastic-job定时任务模块', 'pigx-daemon-elastic-job', '[{\"args\": {\"_genkey_0\": \"/daemon/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-daemon-elastic-job', 0, '2021-07-07 00:58:27', '2021-07-07 01:06:11', '1');
INSERT INTO `sys_route_conf` VALUES (29, 'quartz定时任务模块', 'pigx-daemon-quartz', '[{\"args\": {\"_genkey_0\": \"/job/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-daemon-quartz', 0, '2021-07-07 00:58:27', '2021-07-07 01:06:11', '1');
INSERT INTO `sys_route_conf` VALUES (30, '分布式事务模块', 'pigx-tx-manager', '[{\"args\": {\"_genkey_0\": \"/tx/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-tx-manager', 0, '2021-07-07 00:58:27', '2021-07-07 01:06:11', '1');
INSERT INTO `sys_route_conf` VALUES (31, '通用权限模块', 'laputa-upms-service', '[{\"args\": {\"_genkey_0\": \"/admin/**\"}, \"name\": \"Path\"}]', '[{\"args\": {\"key-resolver\": \"#{@remoteAddrKeyResolver}\", \"redis-rate-limiter.burstCapacity\": \"1000\", \"redis-rate-limiter.replenishRate\": \"1000\"}, \"name\": \"RequestRateLimiter\"}]', 'lb://laputa-upms-service', 0, '2021-07-07 00:58:27', '2021-07-07 01:06:11', '1');
INSERT INTO `sys_route_conf` VALUES (32, '工作流长链接支持1', 'pigx-oa-platform-ws-1', '[{\"args\": {\"_genkey_0\": \"/act/ws/info/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-oa-platform', 0, '2021-07-07 00:58:27', '2021-07-07 01:06:11', '1');
INSERT INTO `sys_route_conf` VALUES (33, '工作流长链接支持2', 'pigx-oa-platform-ws-2', '[{\"args\": {\"_genkey_0\": \"/act/ws/**\"}, \"name\": \"Path\"}]', '[]', 'lb:ws://pigx-oa-platform', 100, '2021-07-07 00:58:27', '2021-07-07 01:06:11', '1');
INSERT INTO `sys_route_conf` VALUES (34, '微信公众号管理', 'pigx-mp-platform', '[{\"args\": {\"_genkey_0\": \"/mp/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-mp-platform', 0, '2021-07-07 00:58:27', '2021-07-07 01:06:11', '1');
INSERT INTO `sys_route_conf` VALUES (35, '支付管理', 'pigx-pay-platform', '[{\"args\": {\"_genkey_0\": \"/pay/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-pay-platform', 0, '2021-07-07 00:58:27', '2021-07-07 01:06:11', '1');
INSERT INTO `sys_route_conf` VALUES (36, '监控管理', 'laputa-monitor', '[{\"args\": {\"_genkey_0\": \"/monitor/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-monitor', 0, '2021-07-07 00:58:27', '2021-07-07 01:06:11', '1');
INSERT INTO `sys_route_conf` VALUES (37, '工作流管理模块', 'laputa-oa-platform', '[{\"args\": {\"_genkey_0\": \"/act/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-oa-platform', 0, '2021-07-07 01:06:11', '2021-07-07 01:27:14', '1');
INSERT INTO `sys_route_conf` VALUES (38, '认证中心', 'laputa-auth', '[{\"args\": {\"_genkey_0\": \"/auth/**\"}, \"name\": \"Path\"}]', '[{\"args\": {}, \"name\": \"ValidateCodeGatewayFilter\"}, {\"args\": {}, \"name\": \"PasswordDecoderFilter\"}]', 'lb://laputa-auth', 0, '2021-07-07 01:06:11', '2021-07-07 01:27:14', '1');
INSERT INTO `sys_route_conf` VALUES (39, '代码生成模块', 'laputa-codegen', '[{\"args\": {\"_genkey_0\": \"/gen/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-codegen', 0, '2021-07-07 01:06:11', '2021-07-07 01:27:14', '1');
INSERT INTO `sys_route_conf` VALUES (40, 'elastic-job定时任务模块', 'pigx-daemon-elastic-job', '[{\"args\": {\"_genkey_0\": \"/daemon/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-daemon-elastic-job', 0, '2021-07-07 01:06:11', '2021-07-07 01:27:14', '1');
INSERT INTO `sys_route_conf` VALUES (41, 'quartz定时任务模块', 'pigx-daemon-quartz', '[{\"args\": {\"_genkey_0\": \"/job/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-daemon-quartz', 0, '2021-07-07 01:06:11', '2021-07-07 01:27:14', '1');
INSERT INTO `sys_route_conf` VALUES (42, '分布式事务模块', 'pigx-tx-manager', '[{\"args\": {\"_genkey_0\": \"/tx/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-tx-manager', 0, '2021-07-07 01:06:11', '2021-07-07 01:27:14', '1');
INSERT INTO `sys_route_conf` VALUES (43, '通用权限模块', 'laputa-upms-service', '[{\"args\": {\"_genkey_0\": \"/admin/**\"}, \"name\": \"Path\"}]', '[{\"args\": {\"key-resolver\": \"#{@remoteAddrKeyResolver}\", \"redis-rate-limiter.burstCapacity\": \"1000\", \"redis-rate-limiter.replenishRate\": \"1000\"}, \"name\": \"RequestRateLimiter\"}]', 'lb://laputa-upms-service', 0, '2021-07-07 01:06:11', '2021-07-07 01:27:14', '1');
INSERT INTO `sys_route_conf` VALUES (44, '工作流长链接支持1', 'pigx-oa-platform-ws-1', '[{\"args\": {\"_genkey_0\": \"/act/ws/info/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-oa-platform', 0, '2021-07-07 01:06:11', '2021-07-07 01:27:14', '1');
INSERT INTO `sys_route_conf` VALUES (45, '工作流长链接支持2', 'laputa-oa-platform-ws-2', '[{\"args\": {\"_genkey_0\": \"/act/ws/**\"}, \"name\": \"Path\"}]', '[]', 'lb:ws://pigx-oa-platform', 100, '2021-07-07 01:06:11', '2021-07-07 01:27:14', '1');
INSERT INTO `sys_route_conf` VALUES (46, '微信公众号管理', 'pigx-mp-platform', '[{\"args\": {\"_genkey_0\": \"/mp/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-mp-platform', 0, '2021-07-07 01:06:11', '2021-07-07 01:27:14', '1');
INSERT INTO `sys_route_conf` VALUES (47, '支付管理', 'pigx-pay-platform', '[{\"args\": {\"_genkey_0\": \"/pay/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-pay-platform', 0, '2021-07-07 01:06:11', '2021-07-07 01:27:14', '1');
INSERT INTO `sys_route_conf` VALUES (48, '监控管理', 'laputa-monitor', '[{\"args\": {\"_genkey_0\": \"/monitor/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-monitor', 0, '2021-07-07 01:06:11', '2021-07-07 01:27:14', '1');
INSERT INTO `sys_route_conf` VALUES (49, '工作流管理模块', 'laputa-oa-platform', '[{\"args\": {\"_genkey_0\": \"/act/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-oa-platform', 0, '2021-07-07 01:27:14', '2021-07-07 01:31:40', '1');
INSERT INTO `sys_route_conf` VALUES (50, '认证中心', 'laputa-auth', '[{\"args\": {\"_genkey_0\": \"/auth/**\"}, \"name\": \"Path\"}]', '[{\"args\": {}, \"name\": \"ValidateCodeGatewayFilter\"}, {\"args\": {}, \"name\": \"PasswordDecoderFilter\"}]', 'lb://laputa-auth', 0, '2021-07-07 01:27:14', '2021-07-07 01:31:40', '1');
INSERT INTO `sys_route_conf` VALUES (51, '代码生成模块', 'laputa-codegen', '[{\"args\": {\"_genkey_0\": \"/gen/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-codegen', 0, '2021-07-07 01:27:14', '2021-07-07 01:31:40', '1');
INSERT INTO `sys_route_conf` VALUES (52, 'elastic-job定时任务模块', 'pigx-daemon-elastic-job', '[{\"args\": {\"_genkey_0\": \"/daemon/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-daemon-elastic-job', 0, '2021-07-07 01:27:14', '2021-07-07 01:31:40', '1');
INSERT INTO `sys_route_conf` VALUES (53, 'quartz定时任务模块', 'pigx-daemon-quartz', '[{\"args\": {\"_genkey_0\": \"/job/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-daemon-quartz', 0, '2021-07-07 01:27:14', '2021-07-07 01:31:40', '1');
INSERT INTO `sys_route_conf` VALUES (54, '分布式事务模块', 'pigx-tx-manager', '[{\"args\": {\"_genkey_0\": \"/tx/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-tx-manager', 0, '2021-07-07 01:27:14', '2021-07-07 01:31:40', '1');
INSERT INTO `sys_route_conf` VALUES (55, '通用权限模块', 'laputa-upms-service', '[{\"args\": {\"_genkey_0\": \"/admin/**\"}, \"name\": \"Path\"}]', '[{\"args\": {\"key-resolver\": \"#{@remoteAddrKeyResolver}\", \"redis-rate-limiter.burstCapacity\": \"1000\", \"redis-rate-limiter.replenishRate\": \"1000\"}, \"name\": \"RequestRateLimiter\"}]', 'lb://laputa-upms-service', 0, '2021-07-07 01:27:14', '2021-07-07 01:31:40', '1');
INSERT INTO `sys_route_conf` VALUES (56, '工作流长链接支持1', 'laputa-oa-platform-ws-1', '[{\"args\": {\"_genkey_0\": \"/act/ws/info/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-oa-platform', 0, '2021-07-07 01:27:14', '2021-07-07 01:31:40', '1');
INSERT INTO `sys_route_conf` VALUES (57, '工作流长链接支持2', 'laputa-oa-platform-ws-2', '[{\"args\": {\"_genkey_0\": \"/act/ws/**\"}, \"name\": \"Path\"}]', '[]', 'lb:ws://laputa-oa-platform', 100, '2021-07-07 01:27:14', '2021-07-07 01:31:40', '1');
INSERT INTO `sys_route_conf` VALUES (58, '微信公众号管理', 'pigx-mp-platform', '[{\"args\": {\"_genkey_0\": \"/mp/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-mp-platform', 0, '2021-07-07 01:27:14', '2021-07-07 01:31:40', '1');
INSERT INTO `sys_route_conf` VALUES (59, '支付管理', 'pigx-pay-platform', '[{\"args\": {\"_genkey_0\": \"/pay/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-pay-platform', 0, '2021-07-07 01:27:14', '2021-07-07 01:31:40', '1');
INSERT INTO `sys_route_conf` VALUES (60, '监控管理', 'laputa-monitor', '[{\"args\": {\"_genkey_0\": \"/monitor/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-monitor', 0, '2021-07-07 01:27:14', '2021-07-07 01:31:40', '1');
INSERT INTO `sys_route_conf` VALUES (61, '工作流管理模块', 'laputa-oa-platform', '[{\"args\": {\"_genkey_0\": \"/act/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-oa-platform', 0, '2021-07-07 01:31:40', '2021-07-07 02:05:51', '1');
INSERT INTO `sys_route_conf` VALUES (62, '认证中心', 'laputa-auth', '[{\"args\": {\"_genkey_0\": \"/auth/**\"}, \"name\": \"Path\"}]', '[{\"args\": {}, \"name\": \"ValidateCodeGatewayFilter\"}, {\"args\": {}, \"name\": \"PasswordDecoderFilter\"}]', 'lb://laputa-auth', 0, '2021-07-07 01:31:40', '2021-07-07 02:05:51', '1');
INSERT INTO `sys_route_conf` VALUES (63, '代码生成模块', 'laputa-codegen', '[{\"args\": {\"_genkey_0\": \"/gen/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-codegen', 0, '2021-07-07 01:31:40', '2021-07-07 02:05:51', '1');
INSERT INTO `sys_route_conf` VALUES (64, 'elastic-job定时任务模块', 'pigx-daemon-elastic-job', '[{\"args\": {\"_genkey_0\": \"/daemon/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-daemon-elastic-job', 0, '2021-07-07 01:31:40', '2021-07-07 02:05:51', '1');
INSERT INTO `sys_route_conf` VALUES (65, 'quartz定时任务模块', 'pigx-daemon-quartz', '[{\"args\": {\"_genkey_0\": \"/job/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-daemon-quartz', 0, '2021-07-07 01:31:40', '2021-07-07 02:05:51', '1');
INSERT INTO `sys_route_conf` VALUES (66, '分布式事务模块', 'pigx-tx-manager', '[{\"args\": {\"_genkey_0\": \"/tx/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-tx-manager', 0, '2021-07-07 01:31:40', '2021-07-07 02:05:51', '1');
INSERT INTO `sys_route_conf` VALUES (67, '通用权限模块', 'laputa-upms-service', '[{\"args\": {\"_genkey_0\": \"/admin/**\"}, \"name\": \"Path\"}]', '[{\"args\": {\"key-resolver\": \"#{@remoteAddrKeyResolver}\", \"redis-rate-limiter.burstCapacity\": \"1000\", \"redis-rate-limiter.replenishRate\": \"1000\"}, \"name\": \"RequestRateLimiter\"}]', 'lb://laputa-upms-service', 0, '2021-07-07 01:31:40', '2021-07-07 02:05:51', '1');
INSERT INTO `sys_route_conf` VALUES (68, '工作流长链接支持1', 'laputa-oa-platform-ws-1', '[{\"args\": {\"_genkey_0\": \"/act/ws/info/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-oa-platform', 0, '2021-07-07 01:31:40', '2021-07-07 02:05:51', '1');
INSERT INTO `sys_route_conf` VALUES (69, '工作流长链接支持2', 'laputa-oa-platform-ws-2', '[{\"args\": {\"_genkey_0\": \"/act/ws/**\"}, \"name\": \"Path\"}]', '[]', 'lb:ws://laputa-oa-platform', 100, '2021-07-07 01:31:40', '2021-07-07 02:05:51', '1');
INSERT INTO `sys_route_conf` VALUES (70, '微信公众号管理', 'laputa-mp-platform', '[{\"args\": {\"_genkey_0\": \"/mp/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-mp-platform', 0, '2021-07-07 01:31:40', '2021-07-07 02:05:51', '1');
INSERT INTO `sys_route_conf` VALUES (71, '支付管理', 'pigx-pay-platform', '[{\"args\": {\"_genkey_0\": \"/pay/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-pay-platform', 0, '2021-07-07 01:31:40', '2021-07-07 02:05:51', '1');
INSERT INTO `sys_route_conf` VALUES (72, '监控管理', 'laputa-monitor', '[{\"args\": {\"_genkey_0\": \"/monitor/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-monitor', 0, '2021-07-07 01:31:40', '2021-07-07 02:05:51', '1');
INSERT INTO `sys_route_conf` VALUES (73, '工作流管理模块', 'laputa-oa-platform', '[{\"args\": {\"_genkey_0\": \"/act/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-oa-platform', 0, '2021-07-07 02:05:51', '2021-07-07 02:13:01', '1');
INSERT INTO `sys_route_conf` VALUES (74, '认证中心', 'laputa-auth', '[{\"args\": {\"_genkey_0\": \"/auth/**\"}, \"name\": \"Path\"}]', '[{\"args\": {}, \"name\": \"ValidateCodeGatewayFilter\"}, {\"args\": {}, \"name\": \"PasswordDecoderFilter\"}]', 'lb://laputa-auth', 0, '2021-07-07 02:05:51', '2021-07-07 02:13:01', '1');
INSERT INTO `sys_route_conf` VALUES (75, '代码生成模块', 'laputa-codegen', '[{\"args\": {\"_genkey_0\": \"/gen/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-codegen', 0, '2021-07-07 02:05:51', '2021-07-07 02:13:01', '1');
INSERT INTO `sys_route_conf` VALUES (76, 'elastic-job定时任务模块', 'pigx-daemon-elastic-job', '[{\"args\": {\"_genkey_0\": \"/daemon/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-daemon-elastic-job', 0, '2021-07-07 02:05:51', '2021-07-07 02:13:01', '1');
INSERT INTO `sys_route_conf` VALUES (77, 'quartz定时任务模块', 'pigx-daemon-quartz', '[{\"args\": {\"_genkey_0\": \"/job/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-daemon-quartz', 0, '2021-07-07 02:05:51', '2021-07-07 02:13:01', '1');
INSERT INTO `sys_route_conf` VALUES (78, '分布式事务模块', 'pigx-tx-manager', '[{\"args\": {\"_genkey_0\": \"/tx/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-tx-manager', 0, '2021-07-07 02:05:51', '2021-07-07 02:13:01', '1');
INSERT INTO `sys_route_conf` VALUES (79, '通用权限模块', 'laputa-upms-service', '[{\"args\": {\"_genkey_0\": \"/admin/**\"}, \"name\": \"Path\"}]', '[{\"args\": {\"key-resolver\": \"#{@remoteAddrKeyResolver}\", \"redis-rate-limiter.burstCapacity\": \"1000\", \"redis-rate-limiter.replenishRate\": \"1000\"}, \"name\": \"RequestRateLimiter\"}]', 'lb://laputa-upms-service', 0, '2021-07-07 02:05:51', '2021-07-07 02:13:01', '1');
INSERT INTO `sys_route_conf` VALUES (80, '工作流长链接支持1', 'laputa-oa-platform-ws-1', '[{\"args\": {\"_genkey_0\": \"/act/ws/info/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-oa-platform', 0, '2021-07-07 02:05:51', '2021-07-07 02:13:01', '1');
INSERT INTO `sys_route_conf` VALUES (81, '工作流长链接支持2', 'laputa-oa-platform-ws-2', '[{\"args\": {\"_genkey_0\": \"/act/ws/**\"}, \"name\": \"Path\"}]', '[]', 'lb:ws://laputa-oa-platform', 100, '2021-07-07 02:05:51', '2021-07-07 02:13:01', '1');
INSERT INTO `sys_route_conf` VALUES (82, '微信公众号管理', 'laputa-mp-platform', '[{\"args\": {\"_genkey_0\": \"/mp/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-mp-platform', 0, '2021-07-07 02:05:51', '2021-07-07 02:13:01', '1');
INSERT INTO `sys_route_conf` VALUES (83, '支付管理', 'laputa-pay-platform', '[{\"args\": {\"_genkey_0\": \"/pay/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-pay-platform', 0, '2021-07-07 02:05:51', '2021-07-07 02:13:01', '1');
INSERT INTO `sys_route_conf` VALUES (84, '监控管理', 'laputa-monitor', '[{\"args\": {\"_genkey_0\": \"/monitor/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-monitor', 0, '2021-07-07 02:05:51', '2021-07-07 02:13:01', '1');
INSERT INTO `sys_route_conf` VALUES (85, '工作流管理模块', 'laputa-oa-platform', '[{\"args\": {\"_genkey_0\": \"/act/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-oa-platform', 0, '2021-07-07 02:13:01', '2021-07-07 02:32:15', '1');
INSERT INTO `sys_route_conf` VALUES (86, '认证中心', 'laputa-auth', '[{\"args\": {\"_genkey_0\": \"/auth/**\"}, \"name\": \"Path\"}]', '[{\"args\": {}, \"name\": \"ValidateCodeGatewayFilter\"}, {\"args\": {}, \"name\": \"PasswordDecoderFilter\"}]', 'lb://laputa-auth', 0, '2021-07-07 02:13:01', '2021-07-07 02:32:15', '1');
INSERT INTO `sys_route_conf` VALUES (87, '代码生成模块', 'laputa-codegen', '[{\"args\": {\"_genkey_0\": \"/gen/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-codegen', 0, '2021-07-07 02:13:01', '2021-07-07 02:32:15', '1');
INSERT INTO `sys_route_conf` VALUES (88, 'elastic-job定时任务模块', 'pigx-daemon-elastic-job', '[{\"args\": {\"_genkey_0\": \"/daemon/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-daemon-elastic-job', 0, '2021-07-07 02:13:01', '2021-07-07 02:32:15', '1');
INSERT INTO `sys_route_conf` VALUES (89, 'quartz定时任务模块', 'pigx-daemon-quartz', '[{\"args\": {\"_genkey_0\": \"/job/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-daemon-quartz', 0, '2021-07-07 02:13:01', '2021-07-07 02:32:15', '1');
INSERT INTO `sys_route_conf` VALUES (90, '分布式事务模块', 'laputa-tx-manager', '[{\"args\": {\"_genkey_0\": \"/tx/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-tx-manager', 0, '2021-07-07 02:13:01', '2021-07-07 02:32:15', '1');
INSERT INTO `sys_route_conf` VALUES (91, '通用权限模块', 'laputa-upms-service', '[{\"args\": {\"_genkey_0\": \"/admin/**\"}, \"name\": \"Path\"}]', '[{\"args\": {\"key-resolver\": \"#{@remoteAddrKeyResolver}\", \"redis-rate-limiter.burstCapacity\": \"1000\", \"redis-rate-limiter.replenishRate\": \"1000\"}, \"name\": \"RequestRateLimiter\"}]', 'lb://laputa-upms-service', 0, '2021-07-07 02:13:01', '2021-07-07 02:32:15', '1');
INSERT INTO `sys_route_conf` VALUES (92, '工作流长链接支持1', 'laputa-oa-platform-ws-1', '[{\"args\": {\"_genkey_0\": \"/act/ws/info/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-oa-platform', 0, '2021-07-07 02:13:01', '2021-07-07 02:32:15', '1');
INSERT INTO `sys_route_conf` VALUES (93, '工作流长链接支持2', 'laputa-oa-platform-ws-2', '[{\"args\": {\"_genkey_0\": \"/act/ws/**\"}, \"name\": \"Path\"}]', '[]', 'lb:ws://laputa-oa-platform', 100, '2021-07-07 02:13:01', '2021-07-07 02:32:15', '1');
INSERT INTO `sys_route_conf` VALUES (94, '微信公众号管理', 'laputa-mp-platform', '[{\"args\": {\"_genkey_0\": \"/mp/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-mp-platform', 0, '2021-07-07 02:13:01', '2021-07-07 02:32:15', '1');
INSERT INTO `sys_route_conf` VALUES (95, '支付管理', 'laputa-pay-platform', '[{\"args\": {\"_genkey_0\": \"/pay/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-pay-platform', 0, '2021-07-07 02:13:01', '2021-07-07 02:32:15', '1');
INSERT INTO `sys_route_conf` VALUES (96, '监控管理', 'laputa-monitor', '[{\"args\": {\"_genkey_0\": \"/monitor/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-monitor', 0, '2021-07-07 02:13:01', '2021-07-07 02:32:15', '1');
INSERT INTO `sys_route_conf` VALUES (97, '工作流管理模块', 'laputa-oa-platform', '[{\"args\": {\"_genkey_0\": \"/act/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-oa-platform', 0, '2021-07-07 02:32:15', '2021-07-07 03:08:38', '1');
INSERT INTO `sys_route_conf` VALUES (98, '认证中心', 'laputa-auth', '[{\"args\": {\"_genkey_0\": \"/auth/**\"}, \"name\": \"Path\"}]', '[{\"args\": {}, \"name\": \"ValidateCodeGatewayFilter\"}, {\"args\": {}, \"name\": \"PasswordDecoderFilter\"}]', 'lb://laputa-auth', 0, '2021-07-07 02:32:15', '2021-07-07 03:08:38', '1');
INSERT INTO `sys_route_conf` VALUES (99, '代码生成模块', 'laputa-codegen', '[{\"args\": {\"_genkey_0\": \"/gen/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-codegen', 0, '2021-07-07 02:32:15', '2021-07-07 03:08:38', '1');
INSERT INTO `sys_route_conf` VALUES (100, 'elastic-job定时任务模块', 'pigx-daemon-elastic-job', '[{\"args\": {\"_genkey_0\": \"/daemon/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pigx-daemon-elastic-job', 0, '2021-07-07 02:32:15', '2021-07-07 03:08:38', '1');
INSERT INTO `sys_route_conf` VALUES (101, 'quartz定时任务模块', 'laputa-daemon-quartz', '[{\"args\": {\"_genkey_0\": \"/job/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-daemon-quartz', 0, '2021-07-07 02:32:15', '2021-07-07 03:08:38', '1');
INSERT INTO `sys_route_conf` VALUES (102, '分布式事务模块', 'laputa-tx-manager', '[{\"args\": {\"_genkey_0\": \"/tx/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-tx-manager', 0, '2021-07-07 02:32:15', '2021-07-07 03:08:38', '1');
INSERT INTO `sys_route_conf` VALUES (103, '通用权限模块', 'laputa-upms-service', '[{\"args\": {\"_genkey_0\": \"/admin/**\"}, \"name\": \"Path\"}]', '[{\"args\": {\"key-resolver\": \"#{@remoteAddrKeyResolver}\", \"redis-rate-limiter.burstCapacity\": \"1000\", \"redis-rate-limiter.replenishRate\": \"1000\"}, \"name\": \"RequestRateLimiter\"}]', 'lb://laputa-upms-service', 0, '2021-07-07 02:32:15', '2021-07-07 03:08:38', '1');
INSERT INTO `sys_route_conf` VALUES (104, '工作流长链接支持1', 'laputa-oa-platform-ws-1', '[{\"args\": {\"_genkey_0\": \"/act/ws/info/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-oa-platform', 0, '2021-07-07 02:32:15', '2021-07-07 03:08:38', '1');
INSERT INTO `sys_route_conf` VALUES (105, '工作流长链接支持2', 'laputa-oa-platform-ws-2', '[{\"args\": {\"_genkey_0\": \"/act/ws/**\"}, \"name\": \"Path\"}]', '[]', 'lb:ws://laputa-oa-platform', 100, '2021-07-07 02:32:15', '2021-07-07 03:08:38', '1');
INSERT INTO `sys_route_conf` VALUES (106, '微信公众号管理', 'laputa-mp-platform', '[{\"args\": {\"_genkey_0\": \"/mp/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-mp-platform', 0, '2021-07-07 02:32:15', '2021-07-07 03:08:38', '1');
INSERT INTO `sys_route_conf` VALUES (107, '支付管理', 'laputa-pay-platform', '[{\"args\": {\"_genkey_0\": \"/pay/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-pay-platform', 0, '2021-07-07 02:32:15', '2021-07-07 03:08:38', '1');
INSERT INTO `sys_route_conf` VALUES (108, '监控管理', 'laputa-monitor', '[{\"args\": {\"_genkey_0\": \"/monitor/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-monitor', 0, '2021-07-07 02:32:15', '2021-07-07 03:08:38', '1');
INSERT INTO `sys_route_conf` VALUES (109, '工作流管理模块', 'laputa-oa-platform', '[{\"args\": {\"_genkey_0\": \"/act/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-oa-platform', 0, '2021-07-07 03:08:38', NULL, '0');
INSERT INTO `sys_route_conf` VALUES (110, '认证中心', 'laputa-auth', '[{\"args\": {\"_genkey_0\": \"/auth/**\"}, \"name\": \"Path\"}]', '[{\"args\": {}, \"name\": \"ValidateCodeGatewayFilter\"}, {\"args\": {}, \"name\": \"PasswordDecoderFilter\"}]', 'lb://laputa-auth', 0, '2021-07-07 03:08:38', NULL, '0');
INSERT INTO `sys_route_conf` VALUES (111, '代码生成模块', 'laputa-codegen', '[{\"args\": {\"_genkey_0\": \"/gen/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-codegen', 0, '2021-07-07 03:08:38', NULL, '0');
INSERT INTO `sys_route_conf` VALUES (112, 'quartz定时任务模块', 'laputa-daemon-quartz', '[{\"args\": {\"_genkey_0\": \"/job/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-daemon-quartz', 0, '2021-07-07 03:08:38', NULL, '0');
INSERT INTO `sys_route_conf` VALUES (113, '分布式事务模块', 'laputa-tx-manager', '[{\"args\": {\"_genkey_0\": \"/tx/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-tx-manager', 0, '2021-07-07 03:08:38', NULL, '0');
INSERT INTO `sys_route_conf` VALUES (114, '通用权限模块', 'laputa-upms-service', '[{\"args\": {\"_genkey_0\": \"/admin/**\"}, \"name\": \"Path\"}]', '[{\"args\": {\"key-resolver\": \"#{@remoteAddrKeyResolver}\", \"redis-rate-limiter.burstCapacity\": \"1000\", \"redis-rate-limiter.replenishRate\": \"1000\"}, \"name\": \"RequestRateLimiter\"}]', 'lb://laputa-upms-service', 0, '2021-07-07 03:08:38', NULL, '0');
INSERT INTO `sys_route_conf` VALUES (115, '工作流长链接支持1', 'laputa-oa-platform-ws-1', '[{\"args\": {\"_genkey_0\": \"/act/ws/info/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-oa-platform', 0, '2021-07-07 03:08:38', NULL, '0');
INSERT INTO `sys_route_conf` VALUES (116, '工作流长链接支持2', 'laputa-oa-platform-ws-2', '[{\"args\": {\"_genkey_0\": \"/act/ws/**\"}, \"name\": \"Path\"}]', '[]', 'lb:ws://laputa-oa-platform', 100, '2021-07-07 03:08:38', NULL, '0');
INSERT INTO `sys_route_conf` VALUES (117, '微信公众号管理', 'laputa-mp-platform', '[{\"args\": {\"_genkey_0\": \"/mp/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-mp-platform', 0, '2021-07-07 03:08:38', NULL, '0');
INSERT INTO `sys_route_conf` VALUES (118, '支付管理', 'laputa-pay-platform', '[{\"args\": {\"_genkey_0\": \"/pay/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-pay-platform', 0, '2021-07-07 03:08:38', NULL, '0');
INSERT INTO `sys_route_conf` VALUES (119, '监控管理', 'laputa-monitor', '[{\"args\": {\"_genkey_0\": \"/monitor/**\"}, \"name\": \"Path\"}]', '[]', 'lb://laputa-monitor', 0, '2021-07-07 03:08:38', NULL, '0');

-- ----------------------------
-- Table structure for sys_social_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_social_details`;
CREATE TABLE `sys_social_details`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主鍵',
  `type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remark` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `app_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `app_secret` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `redirect_url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  `tenant_id` int(0) NOT NULL DEFAULT 0 COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统社交登录账号表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_social_details
-- ----------------------------
INSERT INTO `sys_social_details` VALUES (1, 'WX', '微信互联参数', 'wxd1678d3f83b1d83a', '6ddb043f94da5d2172926abe8533504f', 'daoweicloud.com', '2018-08-16 14:24:25', '2019-03-02 09:43:13', '0', 1);
INSERT INTO `sys_social_details` VALUES (2, 'GITEE', '码云登录', '8fc54e0e76e7842cf767c3ae3b9fdc48c03cefed27aa565ff7b2a39d142d9892', 'c544469ce78a67d9fcf9b28cd9f310b73f5cbc46a1b993e0802ad61517deb221', 'http://gitee.huaxiadaowei.com/#/authredirect', '2019-06-28 09:59:55', '2019-06-28 09:59:55', '0', 1);
INSERT INTO `sys_social_details` VALUES (3, 'OSC', '开源中国', 'neIIqlwGsjsfsA6uxNqD', 'aOPhRuOOJNXV1x7JrTJ9qIyRCAPXoO0l', 'http://gitee.huaxiadaowei.com/#/authredirect', '2019-06-28 10:05:37', '2019-06-28 10:05:37', '0', 1);
INSERT INTO `sys_social_details` VALUES (4, 'MINI', '小程序', 'wx6832be859d0e1cf5', '08036aef810dcb2f8ae31510910ba631', NULL, '2019-11-02 22:08:03', '2019-11-02 22:10:53', '0', 1);

-- ----------------------------
-- Table structure for sys_tenant
-- ----------------------------
DROP TABLE IF EXISTS `sys_tenant`;
CREATE TABLE `sys_tenant`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '租户id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '租户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_tenant
-- ----------------------------
INSERT INTO `sys_tenant` VALUES (1, '龙猫宝宝', '10', '2019-05-15 00:00:00', '2029-05-15 00:00:00', '0', '0', '2019-05-15 15:44:57', '2019-05-18 14:47:30');
INSERT INTO `sys_tenant` VALUES (2, '大宝贝', '11', '2021-07-06 12:00:00', '2022-08-01 12:00:00', '0', '0', '2021-07-06 09:02:06', '2021-07-06 09:02:06');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(0) NOT NULL COMMENT '主键ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户账号',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '加密盐',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '手机',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '头像',
  `social_uid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社交登录UID',
  `access_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社交登录TOKEN',
  `expires_in` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社交登录过期时间',
  `lock_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '是否被锁 0启用 1禁用',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '启用状态 0启用 1禁用',
  `version` int(0) NULL DEFAULT 1 COMMENT '乐观锁',
  `create_user` bigint(0) NULL DEFAULT 0 COMMENT '创建人id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` bigint(0) NULL DEFAULT 0 COMMENT '更新人id',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `tenant_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_idx1_username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (868139489657094177, 'longmao', '龙猫', '$2a$10$3S95f5UAsrgI.mJvPJhxn.qwNWQVDMlP3bR5zdzA1BBz8/cDLSoiS', NULL, '13800002501', 'longmao@163.com', '', NULL, NULL, NULL, '0', 0, 1, 0, '2021-07-23 14:36:24', 0, '2021-07-23 14:36:24', 1);
INSERT INTO `sys_user` VALUES (868140350533795873, 'admin', '管理员', '$2a$10$slarZlKZzSFAyY6LWahFsuNu5HnlOqrocL9/ZJlGVJCLFiKfrIPpq', NULL, '13800000001', 'sommer_jiang@163.com', '', NULL, NULL, NULL, '0', 0, 1, 0, '2021-07-23 14:39:49', 0, '2021-07-23 14:39:49', 1);

-- ----------------------------
-- Table structure for sys_user_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_dept`;
CREATE TABLE `sys_user_dept`  (
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `dept_id` int(0) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`user_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户部门关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_dept
-- ----------------------------
INSERT INTO `sys_user_dept` VALUES (868139489657094177, 1);
INSERT INTO `sys_user_dept` VALUES (868140350533795873, 2);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (868139489657094177, 868494377528328225);
INSERT INTO `sys_user_role` VALUES (868139489657094177, 868497241403293729);
INSERT INTO `sys_user_role` VALUES (868139489657094177, 868497478360498209);
INSERT INTO `sys_user_role` VALUES (868140350533795873, 868497241403293729);

-- ----------------------------
-- Table structure for sys_user_social
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_social`;
CREATE TABLE `sys_user_social`  (
  `id` bigint(0) NOT NULL COMMENT '主键ID',
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号类型：wx微信登录Id mini小程序Id，qqQQ openId gitee_id码云 osc_id 开源中国 ',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '头像',
  `social_uid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社交登录UID',
  `access_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社交登录TOKEN',
  `expires_in` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社交登录过期时间',
  `deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '启用状态 0启用 1禁用',
  `version` int(0) NULL DEFAULT 1 COMMENT '乐观锁',
  `create_user` bigint(0) NULL DEFAULT 0 COMMENT '创建人id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` bigint(0) NULL DEFAULT 0 COMMENT '更新人id',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `tenant_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_idx1_social_uid`(`avatar`, `social_uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
