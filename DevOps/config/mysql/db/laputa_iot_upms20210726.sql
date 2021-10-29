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

 Date: 26/07/2021 16:40:18
*/

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
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `file_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bucket_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `original` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `file_size` bigint(0) NULL DEFAULT NULL COMMENT '文件大小',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '上传时间',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  `tenant_id` int(0) NULL DEFAULT NULL COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (1, '5954f80795294958b80d0b00a0cb9c4d.jpeg', 'laputa', '1-1.jpeg', 'jpeg', 127173, 'admin', '2021-07-06 08:52:17', NULL, '2021-07-16 05:18:00', '1', 1);
INSERT INTO `sys_file` VALUES (2, '6209cf7d41c14f579e9a76a72e2da75f.jpeg', 'laputa', '1-1.jpeg', 'jpeg', 127173, 'admin', '2021-07-16 02:31:08', NULL, '2021-07-16 02:31:08', '0', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 283 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, '0', 'admin用户登录', 'pig', 'admin', '2021-07-06 06:33:06', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (2, '0', 'admin用户登录', 'pig', 'admin', '2021-07-06 06:34:45', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (3, '0', '修改租户', 'pig', 'admin', '2021-07-06 06:36:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/tenant', 'PUT', NULL, '63', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (4, '0', '修改路由', 'pig', 'admin', '2021-07-06 06:46:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/route', 'PUT', NULL, '173', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (5, '0', 'admin用户退出', 'pig', 'admin', '2021-07-06 06:46:40', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 019920b3-c3fe-4872-913a-6af7ebaec9ab', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (6, '0', 'admin用户登录', 'pig', 'admin', '2021-07-06 06:46:47', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (7, '0', 'admin用户登录', 'pig', 'admin', '2021-07-06 06:59:45', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (8, '0', 'admin用户退出', 'pig', 'admin', '2021-07-06 07:08:21', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 302e610a-10e4-4306-85e1-f8a126021bff', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (9, '0', 'admin用户登录', 'pig', 'admin', '2021-07-06 07:08:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (10, '0', 'admin用户退出', 'pig', 'admin', '2021-07-06 07:39:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 5148831b-18b1-4f3a-be6d-9d0d4329c03d', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (11, '0', 'admin用户登录', 'pig', 'admin', '2021-07-06 07:39:33', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (12, '0', '添加用户', 'pig', 'admin', '2021-07-06 07:40:06', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/user', 'POST', NULL, '192', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (13, '0', 'admin用户登录', 'pig', 'admin', '2021-07-06 08:35:35', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (14, '0', 'admin用户登录', 'pig', 'admin', '2021-07-06 08:48:02', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (15, '0', '修改个人信息', 'pig', 'admin', '2021-07-06 08:49:40', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/user/edit', 'PUT', NULL, '245', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (16, '0', 'admin用户退出', 'pig', 'admin', '2021-07-06 08:49:40', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 7818d8ce-79b6-4cea-8e33-7a610e6aba78', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (17, '0', 'admin用户登录', 'pig', 'admin', '2021-07-06 08:49:47', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (18, '0', 'admin用户退出', 'pig', 'admin', '2021-07-06 08:52:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/token/logout', 'DELETE', 'Bearer a82c3f05-f7ed-412a-867d-c8b2cabf114d', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (19, '0', '修改个人信息', 'pig', 'admin', '2021-07-06 08:52:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/user/edit', 'PUT', NULL, '246', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (20, '0', 'admin用户登录', 'pig', 'admin', '2021-07-06 08:52:37', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (21, '0', '新增租户', 'pig', 'admin', '2021-07-06 09:02:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/tenant', 'POST', NULL, '1654', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (22, '0', '新增定时任务', 'pig', 'admin', '2021-07-06 09:06:21', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/sys-job', 'POST', NULL, '62', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (23, '0', '启动定时任务', 'pig', 'admin', '2021-07-06 09:06:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/sys-job/start-job/1', 'POST', NULL, '477', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (24, '0', '立刻执行定时任务', 'pig', 'admin', '2021-07-06 09:07:12', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/sys-job/run-job/1', 'POST', NULL, '209', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (25, '0', '暂停定时任务', 'pig', 'admin', '2021-07-06 09:07:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/sys-job/shutdown-job/1', 'POST', NULL, '147', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (26, '0', '新增定时任务', 'pig', 'admin', '2021-07-06 09:15:02', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/sys-job', 'POST', NULL, '103', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (27, '0', '启动定时任务', 'pig', 'admin', '2021-07-06 09:15:07', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/sys-job/start-job/2', 'POST', NULL, '391', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (28, '0', '立刻执行定时任务', 'pig', 'admin', '2021-07-06 09:15:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/sys-job/run-job/2', 'POST', NULL, '122', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (29, '0', '暂停定时任务', 'pig', 'admin', '2021-07-06 09:18:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/sys-job/shutdown-job/2', 'POST', NULL, '104', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (30, '0', '修改定时任务', 'pig', 'admin', '2021-07-06 09:18:51', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/sys-job', 'PUT', NULL, '412', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (31, '0', '启动定时任务', 'pig', 'admin', '2021-07-06 09:18:55', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/sys-job/start-job/2', 'POST', NULL, '142', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (32, '0', '暂停定时任务', 'pig', 'admin', '2021-07-06 09:19:37', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/sys-job/shutdown-job/2', 'POST', NULL, '142', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (33, '0', 'admin用户退出', 'pig', 'admin', '2021-07-06 10:29:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/token/logout', 'DELETE', 'Bearer d2d95be4-ed90-41c5-a5fa-36da47b42bef', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (34, '0', 'admin用户登录', 'pig', 'admin', '2021-07-06 10:29:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 2);
INSERT INTO `sys_log` VALUES (35, '0', 'admin用户退出', 'pig', 'admin', '2021-07-06 10:30:03', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 361b75b3-ae09-49fc-986f-f7c62efd8125', NULL, '0', NULL, 2);
INSERT INTO `sys_log` VALUES (36, '0', 'admin用户登录', 'pig', 'admin', '2021-07-06 10:30:18', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (37, '9', 'admin用户登录', 'test', 'admin', '2021-07-06 10:35:32', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (38, '9', 'admin用户登录', 'test', 'admin', '2021-07-06 10:36:02', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (39, '9', 'admin用户登录', 'test', 'admin', '2021-07-06 10:36:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (40, '9', 'admin用户登录', 'test', 'admin', '2021-07-06 10:39:25', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (41, '0', 'admin用户退出', 'pig', 'admin', '2021-07-06 10:43:40', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 69e9f38e-eacc-4aa4-acdc-9775869b8f3d', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (42, '0', 'admin用户登录', 'pig', 'admin', '2021-07-06 10:43:47', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (43, '0', 'admin用户退出', 'pig', 'admin', '2021-07-06 10:44:18', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 19f4746b-13bc-4e14-bfaf-2b6f783efd8b', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (44, '0', 'admin用户登录', 'pig', 'admin', '2021-07-06 10:46:14', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (45, '0', 'admin用户登录', 'pig', 'admin', '2021-07-06 10:46:28', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (46, '9', 'admin用户登录', 'test', 'admin', '2021-07-06 10:50:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (47, '0', 'admin用户登录', 'pig', 'admin', '2021-07-07 00:43:28', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (48, '0', '更新菜单', 'pig', 'admin', '2021-07-07 00:47:20', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/menu', 'PUT', NULL, '95', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (49, '0', 'admin用户登录', 'test', 'admin', '2021-07-07 00:47:45', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (50, '0', '更新菜单', 'pig', 'admin', '2021-07-07 00:48:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/menu', 'PUT', NULL, '64', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (51, '0', 'admin用户登录', 'pig', 'admin', '2021-07-07 00:57:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (52, '0', '修改路由', 'pig', 'admin', '2021-07-07 00:58:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/route', 'PUT', NULL, '168', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (53, '0', '修改路由', 'pig', 'admin', '2021-07-07 01:06:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/route', 'PUT', NULL, '136', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (54, '0', 'admin用户退出', 'pig', 'admin', '2021-07-07 01:06:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/token/logout', 'DELETE', 'Bearer c432dec4-437f-4ff8-9349-2e2cd9294342', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (55, '0', 'admin用户登录', 'pig', 'admin', '2021-07-07 01:06:42', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (56, '0', 'admin用户登录', 'pig', 'admin', '2021-07-07 01:10:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (57, '0', '修改路由', 'pig', 'admin', '2021-07-07 01:27:14', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/route', 'PUT', NULL, '139', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (58, '0', 'admin用户退出', 'pig', 'admin', '2021-07-07 01:27:23', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/token/logout', 'DELETE', 'Bearer e4d499ca-db7a-457b-8ad3-2d837e46b058', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (59, '0', 'admin用户登录', 'pig', 'admin', '2021-07-07 01:27:28', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (60, '0', '修改路由', 'pig', 'admin', '2021-07-07 01:31:40', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/route', 'PUT', NULL, '163', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (61, '0', 'admin用户退出', 'pig', 'admin', '2021-07-07 01:37:18', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 5a46413b-0b98-4595-88a9-02058ce824a2', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (62, '0', 'admin用户登录', 'pig', 'admin', '2021-07-07 01:37:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (63, '0', '修改路由', 'pig', 'admin', '2021-07-07 02:05:51', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/route', 'PUT', NULL, '212', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (64, '9', '聚合支付购买商品', NULL, 'anonymousUser', '2021-07-07 02:06:04', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/goods/merge/buy', 'GET', 'amount=%5B1%5D', '209', '0', '聚合支付渠道配置为空', 1);
INSERT INTO `sys_log` VALUES (65, '0', '修改路由', 'pig', 'admin', '2021-07-07 02:13:01', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/route', 'PUT', NULL, '127', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (66, '0', '修改路由', 'pig', 'admin', '2021-07-07 02:32:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/route', 'PUT', NULL, '120', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (67, '0', '修改定时任务', 'pig', 'admin', '2021-07-07 02:45:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/sys-job', 'PUT', NULL, '569', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (68, '0', '启动定时任务', 'pig', 'admin', '2021-07-07 02:45:21', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/sys-job/start-job/2', 'POST', NULL, '136', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (69, '0', '修改路由', 'pig', 'admin', '2021-07-07 03:08:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/route', 'PUT', NULL, '190', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (70, '0', 'admin用户登录', 'pig', 'admin', '2021-07-07 05:10:34', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (71, '0', 'admin用户登录', 'pig', 'admin', '2021-07-07 05:13:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (72, '0', 'admin用户登录', 'pig', 'admin', '2021-07-07 07:56:34', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (73, '0', 'admin用户登录', 'pig', 'admin', '2021-07-08 00:35:33', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (74, '0', '添加终端', 'pig', 'admin', '2021-07-08 01:41:43', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/client', 'POST', NULL, '72', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (75, '0', '编辑终端', 'pig', 'admin', '2021-07-08 01:43:03', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/client', 'PUT', NULL, '88', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (76, '0', 'admin用户退出', 'pig', 'admin', '2021-07-08 01:54:24', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 4a75b9c5-aae1-4c21-92ea-941d05bd63ce', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (77, '0', 'admin用户登录', 'pig', 'admin', '2021-07-08 01:54:31', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (78, '0', '新增数据源表', 'pig', 'admin', '2021-07-08 02:14:48', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/dsconf', 'POST', NULL, '83', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (79, '0', '暂停定时任务', 'pig', 'admin', '2021-07-08 05:01:47', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/sys-job/shutdown-job/2', 'POST', NULL, '68', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (80, '0', '删除定时任务', 'pig', 'admin', '2021-07-08 05:02:51', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/sys-job/2', 'DELETE', NULL, '661', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (81, '0', '删除定时任务', 'pig', 'admin', '2021-07-08 05:02:55', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/sys-job/1', 'DELETE', NULL, '454', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (82, '0', '新增定时任务', 'pig', 'admin', '2021-07-08 05:09:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/sys-job', 'POST', NULL, '67', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (83, '0', '启动定时任务', 'pig', 'admin', '2021-07-08 05:09:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/sys-job/start-job/3', 'POST', NULL, '329', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (84, '0', 'admin用户登录', 'pig', 'admin', '2021-07-12 01:16:53', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (85, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-16 02:30:58', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (86, '0', 'admin用户退出', 'laputa', 'admin', '2021-07-16 02:32:28', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 8b44ce56-a8cc-42a1-872b-ec2171d754fd', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (87, '0', '修改个人信息', 'laputa', 'admin', '2021-07-16 02:32:28', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/user/edit', 'PUT', NULL, '237', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (88, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-16 02:32:34', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (89, '0', 'admin用户退出', 'laputa', 'admin', '2021-07-16 02:49:21', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 06bb9e1e-39f9-4fe2-b607-91d0a6eea05c', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (90, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-16 02:49:35', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (91, '0', '添加角色', 'laputa', 'admin', '2021-07-16 02:52:48', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/role', 'POST', NULL, '78', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (92, '0', '更新角色菜单', 'laputa', 'admin', '2021-07-16 02:53:10', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/role/menu', 'PUT', NULL, '117', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (93, '0', 'admin用户退出', 'laputa', 'admin', '2021-07-16 03:31:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/token/logout', 'DELETE', 'Bearer b7fa5da1-cc5d-4e11-8fef-8c6036beeb7a', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (94, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-16 04:43:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (95, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-16 05:12:00', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (96, '0', 'admin用户退出', 'laputa', 'admin', '2021-07-16 05:12:42', '2021-07-16 05:15:33', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 445d2687-f52a-4879-bd68-16058622f69d', NULL, '1', NULL, 1);
INSERT INTO `sys_log` VALUES (97, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-16 05:12:51', '2021-07-16 05:15:30', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '1', NULL, 1);
INSERT INTO `sys_log` VALUES (98, '0', 'admin用户退出', 'laputa', 'admin', '2021-07-16 05:16:34', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 4efbd082-34be-4563-81ce-d30715489dbf', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (99, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-16 05:16:42', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (100, '0', '删除文件管理', 'laputa', 'admin', '2021-07-16 05:18:00', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/sys-file/1', 'DELETE', NULL, '121', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (101, '9', 'test用户登录', 'test', 'test', '2021-07-16 07:30:19', NULL, '127.0.0.1', 'PostmanRuntime/7.28.1', '/oauth/token', 'POST', 'test', NULL, '0', 'Bad credentials', 1);
INSERT INTO `sys_log` VALUES (102, '9', 'admin用户登录', 'test', 'admin', '2021-07-16 07:32:03', NULL, '127.0.0.1', 'PostmanRuntime/7.28.1', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (103, '9', 'admin用户登录', 'test', 'admin', '2021-07-16 07:32:29', NULL, '127.0.0.1', 'PostmanRuntime/7.28.1', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (104, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-16 07:33:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (105, '0', 'admin用户退出', 'laputa', 'admin', '2021-07-16 07:34:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/token/logout', 'DELETE', 'Bearer fc80634f-0e37-4e2e-9bd5-9671858eedbc', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (106, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-16 07:34:46', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (107, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-16 07:38:40', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (108, '0', 'admin用户退出', 'laputa', 'admin', '2021-07-16 07:39:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/token/logout', 'DELETE', 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsImF1dGhvcml0aWVzIjpbImpvYl9zeXNfam9iX2FkZCIsInBheV9wYXljaGFubmVsX2VkaXQiLCJzeXNfZGVwdF9kZWwiLCJhZG1pbl9zeXNwdWJsaWNwYXJhbV9lZGl0IiwibXBfd3hhdXRvcmVwbHlfZWRpdCIsInN5c191c2VyX2VkaXQiLCJzeXNfZmlsZV9kZWwiLCJzeXNfbWVudV9kZWwiLCJnZW5lcmF0b3JfcGF5Z29vZHNvcmRlcl9hZGQiLCJtcF93eG1hdGVyaWFsX2RlbCIsInN5c19zb2NpYWxfZGV0YWlsc19kZWwiLCJnZW5lcmF0b3JfcGF5bm90aWZ5cmVjb3JkX2VkaXQiLCJtcF93eGFjY291bnRmYW5zX3N5bmMiLCJwYXlfcGF5Y2hhbm5lbF9kZWwiLCJzeXNfcm9sZV9lZGl0IiwiL2FjdGl2aXRpL2xlYXZlIiwibXBfd3htc2dfZWRpdCIsInN5c190b2tlbl9kZWwiLCJnZW5lcmF0b3JfcGF5dHJhZGVvcmRlcl9kZWwiLCJzeXNfZGVwdF9hZGQiLCJnZW5lcmF0b3JfcGF5Z29vZHNvcmRlcl9kZWwiLCJzeXNfcm9sZV9kZWwiLCJqb2Jfc3lzX2pvYl9yZWZyZXNoX2pvYiIsIm1wX3d4bWVudV9hZGQiLCJzeXNfZGljdF9kZWwiLCJzeXNfdXNlcl9kZWwiLCJzeXNfbWVudV9hZGQiLCJnZW5lcmF0b3JfcGF5bm90aWZ5cmVjb3JkX2RlbCIsInN5c19jbGllbnRfYWRkIiwic3lzX2RlcHRfZWRpdCIsImFjdF9wcm9jZXNzX21hbmFnZSIsIm1wX3d4YWNjb3VudGZhbnNfZWRpdCIsIm1wX3d4bXNnX2FkZCIsInN5c19tZW51X2VkaXQiLCJzeXNfdXNlcl9hZGQiLCJnZW5lcmF0b3JfcGF5Z29vZHNvcmRlcl9lZGl0IiwicGF5X3BheWNoYW5uZWxfYWRkIiwiYWRtaW5fc3lzdGVuYW50X2VkaXQiLCJqb2Jfc3lzX2pvYl9kZWwiLCJzeXNfbG9nX2RlbCIsImdlbmVyYXRvcl9wYXlub3RpZnlyZWNvcmRfYWRkIiwic3lzX2NsaWVudF9kZWwiLCJzeXNfY2xpZW50X2VkaXQiLCJST0xFXzEiLCJtcF93eGFjY291bnRfZWRpdCIsImpvYl9zeXNfam9iX3J1bl9qb2IiLCIvYWN0aXZpdGkvdGFzayIsImpvYl9zeXNfam9iX3N0YXJ0X2pvYiIsIm1wX3d4YWNjb3VudF9hZGQiLCJhZG1pbl9zeXN0ZW5hbnRfYWRkIiwiYWN0X3Rhc2tfbWFuYWdlIiwiam9iX3N5c19qb2JfZWRpdCIsInN5c19kaWN0X2VkaXQiLCJhZG1pbl9zeXNwdWJsaWNwYXJhbV9hZGQiLCJhY3RfbGVhdmViaWxsX2FkZCIsInN5c19zb2NpYWxfZGV0YWlsc19lZGl0IiwibXBfd3hhY2NvdW50ZmFuc19kZWwiLCJtcF93eG1zZ19kZWwiLCJnZW5fZm9ybV9hZGQiLCJnZW5fZm9ybV9lZGl0IiwiZ2VuZXJhdG9yX3BheXRyYWRlb3JkZXJfZWRpdCIsIm1wX3d4YXV0b3JlcGx5X2FkZCIsImpvYl9zeXNfam9iX3NodXRkb3duX2pvYiIsInN5c19kaWN0X2FkZCIsImdlbmVyYXRvcl9wYXl0cmFkZW9yZGVyX2FkZCIsInN5c19yb2xlX2FkZCIsIm1wX3d4YWNjb3VudF9kZWwiLCJhY3RfbGVhdmViaWxsX2VkaXQiLCJzeXNfcm9sZV9wZXJtIiwiYWRtaW5fc3lzdGVuYW50X2RlbCIsImFkbWluX3N5c3B1YmxpY3BhcmFtX2RlbCIsIm1wX3d4YXV0b3JlcGx5X2RlbCIsIm1wX3d4YWNjb3VudGZhbnNfYWRkIiwibXBfd3htZW51X3B1c2giLCIvYWN0aXZpdGkvcHJvY2VzcyIsImFjdF9sZWF2ZWJpbGxfZGVsIiwiZ2VuX2Zvcm1fZGVsIiwibXBfd3htYXRlcmlhbF9hZGQiLCJzeXNfc29jaWFsX2RldGFpbHNfYWRkIiwiYWN0X21vZGVsX21hbmFnZSJdLCJqdGkiOiIwYjZlMGY4YS1kZDUwLTRkMDItYTRmNy1kZmU1ZWQ1YmY2ZjYiLCJ1c2VyaW5mbyI6IntcInVzZXJGbGFnXCI6XCIxXCIsXCJpZFwiOlwiMVwiLFwicXFudW1cIjpcIjQzODk0NDIwOVwiLFwidXNlcm5hbWVcIjpcIkxpYW9YaWFuZ1wifSIsImNsaWVudF9pZCI6ImxhcHV0YSIsInNjb3BlIjpbInNlcnZlciJdfQ.6dD0fJncR7im0-RBlDSYofFu0FhqiGxGim3wbp7jUls', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (109, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-16 07:39:44', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (110, '9', 'admin用户登录', 'test', 'admin', '2021-07-16 07:57:29', NULL, '127.0.0.1', 'PostmanRuntime/7.28.1', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (111, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-16 07:58:52', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (112, '0', 'admin用户登录', 'test', 'admin', '2021-07-16 07:59:27', NULL, '127.0.0.1', 'PostmanRuntime/7.28.1', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (113, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-17 00:44:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (114, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-17 02:14:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (115, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-19 04:42:25', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (116, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-19 06:31:25', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (117, '0', 'admin用户登录', 'test', 'admin', '2021-07-19 07:17:28', NULL, '127.0.0.1', 'PostmanRuntime/7.28.1', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (118, '0', 'admin用户退出', 'laputa', 'admin', '2021-07-19 07:22:56', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 82483fcc-75f8-4275-9fb2-ae77e28cec85', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (119, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-19 07:23:06', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (120, '0', 'admin用户退出', 'laputa', 'admin', '2021-07-19 07:23:30', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/token/logout', 'DELETE', 'Bearer f96e041a-2f9c-433d-a4cd-7a59e3bd2895', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (121, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-19 07:23:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (122, '0', 'admin用户退出', 'laputa', 'admin', '2021-07-19 07:31:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 9dbc5ab4-5441-4f99-9cda-eeaf66f8cbc7', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (123, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-19 07:31:18', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (124, '9', '用户登录', 'laputa', NULL, '2021-07-19 07:39:52', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (125, '9', '用户登录', 'laputa', NULL, '2021-07-19 07:40:00', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (126, '9', '用户登录', 'laputa', NULL, '2021-07-19 07:40:09', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (127, '9', '用户登录', 'laputa', NULL, '2021-07-19 07:40:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (128, '9', '用户登录', 'laputa', NULL, '2021-07-19 07:45:02', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (129, '9', '用户登录', 'laputa', NULL, '2021-07-19 07:45:04', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (130, '9', '用户登录', 'laputa', NULL, '2021-07-19 07:45:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (131, '9', '用户登录', 'laputa', NULL, '2021-07-19 07:45:19', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (132, '9', '用户登录', 'laputa', NULL, '2021-07-19 07:45:21', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (133, '9', '用户登录', 'laputa', NULL, '2021-07-19 07:45:51', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (134, '9', '用户登录', 'laputa', NULL, '2021-07-19 07:46:58', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (135, '9', '用户登录', 'laputa', NULL, '2021-07-19 07:47:49', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (136, '9', '用户登录', 'laputa', NULL, '2021-07-19 07:48:29', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (137, '0', 'admin用户退出', 'laputa', 'admin', '2021-07-19 07:48:43', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 91a5588c-ebf2-418e-a59c-5d46c1410a60', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (138, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-19 07:48:58', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (139, '9', '用户登录', 'laputa', NULL, '2021-07-19 07:49:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (140, '9', '用户登录', 'laputa', NULL, '2021-07-19 07:54:21', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (141, '0', 'admin用户退出', 'laputa', 'admin', '2021-07-19 07:55:04', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/token/logout', 'DELETE', 'Bearer c6ab4bf8-c666-4c35-8028-877cc01959b4', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (142, '9', '用户登录', 'laputa', NULL, '2021-07-19 07:57:40', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (143, '9', '用户登录', 'laputa', NULL, '2021-07-19 08:02:00', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (144, '9', '用户登录', 'laputa', NULL, '2021-07-19 08:04:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (145, '9', '用户登录', 'laputa', NULL, '2021-07-19 08:04:43', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (146, '0', 'admin用户登录', 'test', 'admin', '2021-07-19 08:07:21', NULL, '127.0.0.1', 'PostmanRuntime/7.28.1', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (147, '9', '用户登录', 'test', NULL, '2021-07-19 08:13:51', NULL, '127.0.0.1', 'PostmanRuntime/7.28.1', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (148, '9', 'admin用户登录', 'test', 'admin', '2021-07-19 08:14:19', NULL, '127.0.0.1', 'PostmanRuntime/7.28.1', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (149, '9', 'admin用户登录', 'test', 'admin', '2021-07-19 08:14:29', NULL, '127.0.0.1', 'PostmanRuntime/7.28.1', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (150, '9', 'admin用户登录', 'test', 'admin', '2021-07-19 08:14:30', NULL, '127.0.0.1', 'PostmanRuntime/7.28.1', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (151, '9', 'admin用户登录', 'test', 'admin', '2021-07-19 08:14:42', NULL, '127.0.0.1', 'PostmanRuntime/7.28.1', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (152, '0', 'admin用户登录', 'test', 'admin', '2021-07-19 08:14:51', NULL, '127.0.0.1', 'PostmanRuntime/7.28.1', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (153, '0', 'admin用户登录', 'test', 'admin', '2021-07-19 08:17:05', NULL, '127.0.0.1', 'PostmanRuntime/7.28.1', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (154, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-19 08:18:48', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (155, '9', '用户登录', 'test', NULL, '2021-07-19 08:24:18', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (156, '9', '用户登录', 'test', NULL, '2021-07-19 08:24:44', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (157, '0', 'admin用户登录', 'test', 'admin', '2021-07-19 08:25:09', NULL, '127.0.0.1', 'PostmanRuntime/7.28.1', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (158, '9', '用户登录', 'test', NULL, '2021-07-19 08:25:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (159, '0', 'admin用户退出', 'laputa', 'admin', '2021-07-19 08:26:32', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 74c3358a-f9f8-463c-a1ce-0d78994a7ee0', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (160, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-19 08:26:48', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (161, '0', 'admin用户退出', 'laputa', 'admin', '2021-07-19 08:27:25', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/token/logout', 'DELETE', 'Bearer bd5d5c8f-f19a-473b-99c2-56f5dcafbac7', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (162, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-19 08:27:39', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (163, '0', 'admin用户登录', 'laputa', 'admin', '2021-07-20 00:34:49', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (164, '9', '用户登录', 'test', NULL, '2021-07-20 00:37:19', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (165, '9', '用户登录', 'test', NULL, '2021-07-20 00:37:23', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (166, '9', '用户登录', 'test', NULL, '2021-07-20 00:38:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (167, '9', '用户登录', 'test', NULL, '2021-07-20 00:45:30', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (168, '9', '用户登录', 'test', NULL, '2021-07-20 01:08:46', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (169, '9', '用户登录', 'test', NULL, '2021-07-20 01:11:40', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (170, '9', '用户登录', 'test', NULL, '2021-07-20 01:11:47', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (171, '9', '用户登录', 'test', NULL, '2021-07-20 01:13:13', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (172, '9', '用户登录', 'test', NULL, '2021-07-20 01:14:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (173, '9', '用户登录', 'test', NULL, '2021-07-20 01:16:40', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (174, '9', '用户登录', 'test', NULL, '2021-07-20 01:17:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', NULL, NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (175, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:19:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (176, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:19:28', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (177, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:20:34', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (178, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:22:23', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (179, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:24:00', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (180, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:29:30', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (181, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:35:43', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (182, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:35:52', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (183, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:36:30', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (184, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:38:50', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (185, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:41:06', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (186, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:42:18', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (187, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:42:23', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (188, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:45:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (189, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:45:29', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (190, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:45:41', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (191, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:45:45', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (192, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:46:24', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (193, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:48:09', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (194, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:48:21', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (195, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:48:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (196, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:48:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (197, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:48:24', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (198, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:48:25', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (199, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:48:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (200, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:48:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (201, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:53:13', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (202, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 01:54:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (203, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 02:02:48', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (204, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 02:02:49', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (205, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 02:02:50', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (206, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 02:07:33', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (207, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 02:09:04', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (208, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 02:14:50', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (209, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 02:18:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (210, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 02:23:51', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (211, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 02:23:53', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (212, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 02:34:54', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (213, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 02:42:07', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (214, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 02:45:13', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (215, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 02:54:50', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (216, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 03:14:45', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (217, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 04:43:52', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (218, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 04:47:43', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (219, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 05:00:35', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (220, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 05:00:54', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (221, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 05:52:03', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (222, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 05:56:37', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (223, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 06:13:50', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (224, '0', 'admin用户退出', 'test', 'admin', '2021-07-20 06:13:57', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/token/logout', 'DELETE', '96c52920-9e87-42c1-a17e-60b33d0ba8a6', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (225, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 06:15:19', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (226, '0', 'admin用户退出', 'test', 'admin', '2021-07-20 06:16:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/token/logout', 'DELETE', '98f7210f-56cd-4e8b-8af7-1bdd7056b2b7', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (227, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 06:16:40', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (228, '0', 'admin用户退出', 'test', 'admin', '2021-07-20 06:25:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/token/logout', 'DELETE', 'c30966dd-037d-4fa9-9c92-fc19fc656c82', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (229, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 06:25:19', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (230, '0', 'admin用户退出', 'test', 'admin', '2021-07-20 06:52:20', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/token/logout', 'DELETE', 'b006092f-e458-469e-9dc2-e0550c9b4b42', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (231, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 07:47:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (232, '0', 'admin用户退出', 'test', 'admin', '2021-07-20 07:48:45', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/token/logout', 'DELETE', '5181de66-3747-4200-9150-979ad0351cfd', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (233, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 08:19:25', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (234, '0', 'admin用户退出', 'test', 'admin', '2021-07-20 08:21:39', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/token/logout', 'DELETE', '6a09d079-c43a-4719-b6dc-2124353c5078', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (235, '0', 'admin用户登录', 'test', 'admin', '2021-07-20 08:32:19', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (236, '0', 'admin用户退出', 'test', 'admin', '2021-07-20 08:32:29', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36', '/token/logout', 'DELETE', 'a88eafcc-dcea-4366-8031-f6cc61313794', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (237, '0', 'admin用户登录', 'test', 'admin', '2021-07-22 05:29:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (238, '0', 'admin用户退出', 'test', 'admin', '2021-07-22 05:32:59', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 53273227-f4d7-461c-b9ac-b419866fa183', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (239, '0', 'admin用户登录', 'test', 'admin', '2021-07-22 05:56:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (240, '0', 'admin用户登录', 'test', 'admin', '2021-07-22 05:57:29', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (241, '0', 'admin用户退出', 'test', 'admin', '2021-07-22 05:59:28', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 61a50b4a-640b-49d0-9ba0-ab8d7aec63f6', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (242, '0', 'admin用户登录', 'test', 'admin', '2021-07-22 06:12:55', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (243, '0', 'admin用户登录', 'test', 'admin', '2021-07-22 06:14:55', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (244, '0', 'admin用户退出', 'test', 'admin', '2021-07-22 06:15:06', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 356a2f84-5b01-4766-bc29-b35490d56caf', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (245, '0', 'admin用户登录', 'test', 'admin', '2021-07-22 06:15:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (246, '0', 'admin用户登录', 'test', 'admin', '2021-07-22 06:19:09', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (247, '0', 'admin用户登录', 'test', 'admin', '2021-07-22 06:24:21', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (248, '0', 'admin用户退出', 'test', 'admin', '2021-07-22 06:51:43', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 92208eca-c0bb-4640-bddc-857e74e9d0f6', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (249, '0', 'admin用户登录', 'test', 'admin', '2021-07-22 06:51:46', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (250, '0', 'admin用户退出', 'test', 'admin', '2021-07-22 06:51:54', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/token/logout', 'DELETE', 'Bearer f7c9d533-20b7-4d1c-933c-7b3fbd6b71b0', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (251, '0', 'admin用户登录', 'test', 'admin', '2021-07-22 06:51:59', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (252, '0', 'admin用户登录', 'test', 'admin', '2021-07-22 06:54:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (253, '0', 'admin用户退出', 'test', 'admin', '2021-07-22 06:54:31', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 296ef43c-7264-4117-9145-de1943be41c4', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (254, '0', 'admin用户登录', 'test', 'admin', '2021-07-22 06:54:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (255, '9', 'admin用户登录', 'test', 'admin', '2021-07-22 07:22:49', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (256, '9', 'admin用户登录', 'test', 'admin', '2021-07-22 07:22:56', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (257, '9', 'admin用户登录', 'test', 'admin', '2021-07-22 07:22:59', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (258, '9', 'admin用户登录', 'test', 'admin', '2021-07-22 07:23:09', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (259, '0', 'admin用户登录', 'test', 'admin', '2021-07-22 07:24:03', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (260, '0', 'admin用户退出', 'test', 'admin', '2021-07-22 07:25:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 09cb217b-c8ca-4913-828f-f2d536798a9a', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (261, '0', 'admin用户登录', 'test', 'admin', '2021-07-22 07:25:18', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (262, '0', 'admin用户登录', 'test', 'admin', '2021-07-22 07:25:26', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (263, '0', 'admin用户退出', 'test', 'admin', '2021-07-22 07:27:21', NULL, '192.168.4.51', 'okhttp/3.14.9', '/token/0447d072-1a2f-489d-942d-5238fb98a7d4', 'DELETE', NULL, NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (264, '0', '删除用户token', 'test', 'admin', '2021-07-22 07:27:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/token/0447d072-1a2f-489d-942d-5238fb98a7d4', 'DELETE', NULL, '362', '0', NULL, 1);
INSERT INTO `sys_log` VALUES (265, '0', 'admin用户登录', 'test', 'admin', '2021-07-22 07:28:24', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (266, '0', 'admin用户登录', 'test', 'admin', '2021-07-22 11:29:32', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (267, '0', 'admin用户登录', 'test', 'admin', '2021-07-22 11:31:14', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (268, '0', 'admin用户登录', 'test', 'admin', '2021-07-23 00:57:43', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (269, '0', 'admin用户登录', 'test', 'admin', '2021-07-23 07:17:34', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (270, '0', 'admin用户退出', 'test', 'admin', '2021-07-23 07:19:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 90d54734-87e2-49fa-a222-5ca6d09e881b', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (271, '0', 'admin用户登录', 'test', 'admin', '2021-07-23 07:19:13', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (272, '0', 'admin用户登录', 'test', 'admin', '2021-07-23 07:29:18', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (273, '0', 'admin用户退出', 'test', 'admin', '2021-07-23 07:32:51', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer a8c0ff1f-79ce-434d-97ca-b583e89d6206', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (274, '0', 'admin用户登录', 'test', 'admin', '2021-07-24 02:38:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (275, '0', 'admin用户登录', 'test', 'admin', '2021-07-24 02:43:56', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (276, '0', 'admin用户登录', 'test', 'admin', '2021-07-24 02:50:13', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (277, '0', 'admin用户登录', 'test', 'admin', '2021-07-24 02:56:10', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (278, '0', 'admin用户退出', 'test', 'admin', '2021-07-24 07:07:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer a38eb720-5f97-4b27-8e89-6c436db28895', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (279, '9', 'admin用户登录', 'test', 'admin', '2021-07-24 08:17:29', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (280, '9', 'admin用户登录', 'test', 'admin', '2021-07-24 08:17:34', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (281, '9', 'admin用户登录', 'test', 'admin', '2021-07-24 08:18:53', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (282, '9', 'admin用户登录', 'test', 'admin', '2021-07-24 08:22:54', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (283, '9', 'admin用户登录', 'test', 'admin', '2021-07-26 01:00:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (284, '9', 'admin用户登录', 'test', 'admin', '2021-07-26 01:00:37', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (285, '9', 'admin用户登录', 'test', 'admin', '2021-07-26 01:00:52', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (286, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 01:02:47', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (287, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 01:05:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (288, '0', 'admin用户退出', 'test', 'admin', '2021-07-26 01:05:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 4c1922d4-7a99-42ac-96dc-421ba3aa3083', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (289, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 01:07:07', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (290, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 01:08:06', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (291, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 01:09:39', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (292, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 01:11:31', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (293, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 01:12:42', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (294, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 01:13:33', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (295, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 01:40:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (296, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 01:41:29', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (297, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 05:25:51', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (298, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 05:34:23', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (299, '0', 'admin用户退出', 'test', 'admin', '2021-07-26 05:34:46', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer c4adaf78-0b5f-4304-8cfe-2b680443c118', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (300, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 05:34:51', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (301, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 05:40:10', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (302, '0', 'admin用户退出', 'test', 'admin', '2021-07-26 05:40:18', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer a810f6e8-21f6-403c-a19e-8a8112656df2', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (303, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 05:40:37', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (304, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 05:44:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (305, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 05:45:21', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (306, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 05:46:58', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (307, '0', 'admin用户退出', 'test', 'admin', '2021-07-26 05:47:03', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer a380412d-f069-4c7a-9720-a0c538d648dc', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (308, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 05:47:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (309, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 05:48:27', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (310, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 05:50:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (311, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 05:51:44', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (312, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 06:03:14', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (313, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 06:04:58', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (314, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 06:07:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (315, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 06:09:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (316, '0', 'admin用户退出', 'test', 'admin', '2021-07-26 06:14:23', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 77d8d186-60ec-4f79-87f6-cee666b2ad96', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (317, '9', 'admin用户登录', 'test', 'admin', '2021-07-26 06:22:09', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (318, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 06:23:44', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (319, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 06:24:07', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (320, '0', 'admin用户退出', 'test', 'admin', '2021-07-26 06:24:14', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 0f215d63-8860-421d-aca4-6123c2bcfe72', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (321, '9', 'admin用户登录', 'test', 'admin', '2021-07-26 06:24:52', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', '用户名或密码错误', 1);
INSERT INTO `sys_log` VALUES (322, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 06:45:57', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (323, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 06:47:18', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (324, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 06:48:06', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (325, '0', 'admin用户退出', 'test', 'admin', '2021-07-26 06:48:12', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 3492ec16-339a-48ee-bf51-0cb4d06f166b', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (326, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 06:48:24', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (327, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 06:48:33', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (328, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 06:50:45', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (329, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 06:58:19', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (330, '0', 'admin用户退出', 'test', 'admin', '2021-07-26 06:58:25', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 0a1f5a66-440c-4caa-b1e5-7b918d5e6399', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (331, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 06:58:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (332, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 06:59:11', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (333, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 07:05:13', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (334, '0', 'admin用户退出', 'test', 'admin', '2021-07-26 07:05:19', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 0c792f29-56b5-4fe8-a785-79267baadef0', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (335, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 07:05:30', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (336, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 07:24:50', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (337, '0', 'admin用户退出', 'test', 'admin', '2021-07-26 07:24:59', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer d82b7cb8-6ca6-4e86-9066-a059ca6af30b', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (338, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 07:25:09', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (339, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 07:25:46', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (340, '0', 'admin用户退出', 'test', 'admin', '2021-07-26 07:26:45', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer e272b9a3-15dc-4c5e-a553-9e631aea2e7b', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (341, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 07:26:49', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (342, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 07:53:52', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (343, '0', 'admin用户退出', 'test', 'admin', '2021-07-26 08:03:02', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer c9bc8552-bb65-4302-8987-2d99da9a2352', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (344, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 08:03:04', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (345, '0', 'admin用户退出', 'test', 'admin', '2021-07-26 08:06:08', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer feae7fa8-0c7b-421f-89cc-46e75c074115', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (346, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 08:06:10', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (347, '0', 'admin用户退出', 'test', 'admin', '2021-07-26 08:22:36', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer c1ecd088-906f-4c22-aa2e-411387484c26', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (348, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 08:22:38', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (349, '0', 'admin用户退出', 'test', 'admin', '2021-07-26 08:22:43', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 23c01bfc-07ce-446c-a375-c42c7b7b7211', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (350, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 08:22:48', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (351, '0', 'admin用户退出', 'test', 'admin', '2021-07-26 08:24:10', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 0ed6a04d-07c2-48ec-b466-a8ad49a89055', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (352, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 08:24:15', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (353, '0', 'admin用户退出', 'test', 'admin', '2021-07-26 08:32:00', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 1cc9b829-60be-4a0c-ba41-b1566b6fef1b', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (354, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 08:32:05', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (355, '0', 'admin用户退出', 'test', 'admin', '2021-07-26 08:35:37', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 323f4e46-2027-499a-8767-648d9ab2ee8f', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (356, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 08:35:41', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (357, '0', 'admin用户退出', 'test', 'admin', '2021-07-26 08:37:19', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer c5310304-0e2d-4884-ad0d-a89762798b3b', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (358, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 08:37:21', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (359, '0', 'admin用户退出', 'test', 'admin', '2021-07-26 08:38:17', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 1f91c6f1-1513-4bbf-81f6-759ddc197433', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (360, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 08:38:22', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (361, '0', 'admin用户退出', 'test', 'admin', '2021-07-26 08:39:01', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/token/logout', 'DELETE', 'Bearer 9158690c-cf4e-46c4-9243-1cd9b3bd9c06', NULL, '0', NULL, 1);
INSERT INTO `sys_log` VALUES (362, '0', 'admin用户登录', 'test', 'admin', '2021-07-26 08:39:03', NULL, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36', '/oauth/token', 'POST', 'admin', NULL, '0', NULL, 1);

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
  `parent_id` bigint(0) NULL DEFAULT NULL COMMENT '父菜单ID',
  `sort` int(0) NULL DEFAULT 1 COMMENT '排序值',
  `type` tinyint(1) NULL DEFAULT 0 COMMENT '菜单图标',
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
  `hide_breadcrumb` tinyint(1) NULL DEFAULT 1 COMMENT '显示在头部指引中 0显示 1隐藏',
  `hide_children_in_menu` tinyint(1) NULL DEFAULT 1 COMMENT '显示子菜单 0显示 1隐藏',
  `hide_children_in_path` tinyint(1) NULL DEFAULT 1 COMMENT '隐藏子菜单路径 0显示 1隐藏',
  `carry_param` tinyint(1) NULL DEFAULT 1 COMMENT '是否带参数 0不带 1带',
  `single` tinyint(1) NULL DEFAULT 1 COMMENT '是否带但菜单',
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
INSERT INTO `sys_menu` VALUES (869184907492982817, 'System', NULL, 'LAYOUT', NULL, '/system', NULL, -1, 2, 0, '/system/account', 'routes.demo.system.moduleName', 0, 0, 0, NULL, 0, 'ion:settings-outline', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, '2021-07-26 11:50:31', 0, '2021-07-26 08:37:11', 1);
INSERT INTO `sys_menu` VALUES (869199450768146465, 'Welcome', NULL, '/dashboard/analysis/index', NULL, '/dashboard', NULL, -1, 1, 0, NULL, 'routes.dashboard.analysis', 0, 0, 1, NULL, 0, 'bx:bx-home', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 1, 0, 0, 0, NULL, 0, '2021-07-26 12:48:18', 0, '2021-07-26 08:37:11', 1);
INSERT INTO `sys_menu` VALUES (869203162005766177, 'AccountManagement', NULL, '/system/account/index', NULL, 'account', NULL, 869184907492982817, 201, 0, NULL, 'routes.demo.system.account', 0, 0, 0, NULL, 1, 'bx:bx-home', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-07-26 13:03:03', 0, '2021-07-26 08:37:11', 1);
INSERT INTO `sys_menu` VALUES (869204862980587553, 'AccountDetail', NULL, '/system/account/AccountDetail', NULL, 'account_detail/:id', NULL, 869184907492982817, 202, 0, NULL, 'routes.demo.system.account_detail', 0, 0, 0, NULL, 1, 'bx:bx-home', 1, 0, 0, '/system/account', NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-07-26 13:09:49', 0, '2021-07-26 08:37:11', 1);
INSERT INTO `sys_menu` VALUES (869206211482877985, 'RoleManagement', NULL, '/system/role/index', NULL, 'role', NULL, 869184907492982817, 203, 0, NULL, 'routes.demo.system.role', 0, 0, 0, NULL, 1, 'bx:bx-home', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-07-26 13:15:10', 0, '2021-07-26 08:37:11', 1);
INSERT INTO `sys_menu` VALUES (869206521244811297, 'MenuManagement', NULL, '/system/menu/index', NULL, 'menu', NULL, 869184907492982817, 204, 0, NULL, 'routes.demo.system.menu', 0, 0, 0, NULL, 1, 'bx:bx-home', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-07-26 13:16:24', 0, '2021-07-26 08:37:11', 1);
INSERT INTO `sys_menu` VALUES (869206747531706401, 'DeptManagement', NULL, '/system/dept/index', NULL, 'dept', NULL, 869184907492982817, 205, 0, NULL, 'routes.demo.system.dept', 0, 0, 0, NULL, 1, 'bx:bx-home', 0, 0, 1, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, '2021-07-26 13:17:18', 0, '2021-07-26 08:37:11', 1);

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
INSERT INTO `sys_role` VALUES (868494377528328225, '管理员', 'ADMIN', '日常维护管理员', '0', 'master', 0, 1, 0, '2021-07-24 14:06:36', 0, '2021-07-24 14:06:36', 0);
INSERT INTO `sys_role` VALUES (868497241403293729, '普通用户', 'COMMON_USER', '普通用户角色', '0', 'master', 0, 1, 0, '2021-07-24 14:17:58', 0, '2021-07-24 14:17:58', 1);
INSERT INTO `sys_role` VALUES (868497478360498209, '租户用户', 'TENANT_USER', '租户默认角色', '0', 'master', 0, 1, 0, '2021-07-24 14:18:55', 0, '2021-07-24 14:18:55', 1);

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
INSERT INTO `sys_role_menu` VALUES (868497478360498209, 869184907492982817);
INSERT INTO `sys_role_menu` VALUES (868497478360498209, 869199450768146465);
INSERT INTO `sys_role_menu` VALUES (868497478360498209, 869203162005766177);
INSERT INTO `sys_role_menu` VALUES (868497478360498209, 869204862980587553);
INSERT INTO `sys_role_menu` VALUES (868497478360498209, 869206211482877985);
INSERT INTO `sys_role_menu` VALUES (868497478360498209, 869206521244811297);
INSERT INTO `sys_role_menu` VALUES (868497478360498209, 869206747531706401);

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
INSERT INTO `sys_user_role` VALUES (868140350533795873, 868494377528328225);
INSERT INTO `sys_user_role` VALUES (868140350533795873, 868497241403293729);
INSERT INTO `sys_user_role` VALUES (868140350533795873, 868497478360498209);

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
