/*
 Navicat Premium Data Transfer

 Source Server         : 百度云
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : 106.13.63.126:3306
 Source Schema         : laputa_iot_file

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 19/09/2021 09:13:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_catalog
-- ----------------------------
DROP TABLE IF EXISTS `sys_catalog`;
CREATE TABLE `sys_catalog`  (
  `id` bigint(0) NOT NULL COMMENT '编号ID',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名',
  `alias` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '别名',
  `parent_id` bigint(0) NULL DEFAULT -1 COMMENT '父文件夹Id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `tenant_id` bigint(0) NULL DEFAULT NULL COMMENT '所属租户',
  `version` int(0) NULL DEFAULT 1 COMMENT '版本号',
  `create_user` bigint(0) NULL DEFAULT 1 COMMENT '创建者',
  `update_user` bigint(0) NULL DEFAULT 1 COMMENT '更新者',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件夹' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` bigint(0) NOT NULL COMMENT '编号',
  `file_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名',
  `bucket_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '桶名',
  `original` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '原始名',
  `file_exten` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '后缀名',
  `file_size` bigint(0) NULL DEFAULT NULL COMMENT '文件大小',
  `url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '快速链接',
  `content_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型',
  `is_img` tinyint(1) NOT NULL COMMENT '是否图片',
  `source` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '来源 local minio s3 qiniu aliyun',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '上传时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `tenant_id` bigint(0) NULL DEFAULT NULL COMMENT '所属租户',
  `version` int(0) NULL DEFAULT 1 COMMENT '版本号',
  `create_user` bigint(0) NULL DEFAULT 1 COMMENT '创建者',
  `update_user` bigint(0) NULL DEFAULT 1 COMMENT '更新者',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_file_name`(`file_name`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (880036378727940129, '09ec386a96284c49a0f4fb9d1951f21a.jpg', 'laputa', '微信图片_20210709152522.jpg', 'jpg', 99572, 'http://laputa-minio:9000/laputa/09ec386a96284c49a0f4fb9d1951f21a.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210825%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210825T023023Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=0d5638196d7b6a6bacc0d9ac7d4a34f97a960595e12ac04cdae7be9db47c1062', 'image/jpeg', 0, 'minio', '2021-08-25 10:30:23', '2021-08-25 03:13:37', 1, 1, 1, 868140350533795873, 0);
INSERT INTO `sys_file` VALUES (880071905166491681, '8de97190fd3c4f0cb21b9fe71c65c1ff.jpg', 'laputa', '微信图片_20210709152431.jpg', 'jpg', 81788, 'http://laputa-minio:9000/laputa/8de97190fd3c4f0cb21b9fe71c65c1ff.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210825%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210825T045133Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=51232aa96e6e842c377a9d3a04a4fb90dc3e0c16e0b0ff4fe424fb9a51251667', 'image/jpeg', 1, 'minio', '2021-08-25 12:51:33', '2021-08-25 12:51:33', 0, 1, 1, 868140350533795873, 0);
INSERT INTO `sys_file` VALUES (880187017822994465, 'edd704b711c249538a86363df7a6a74f.jpg', 'laputa', '006fmUQjly1gqmjf7qwc1j30qo11c77l.jpg', 'jpg', 79640, 'http://laputa-minio:9000/laputa/edd704b711c249538a86363df7a6a74f.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210825%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210825T122858Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=457886e35ab8afd5dd7165957cb311520345bfe6abde49e356b0d2329361fa67', 'image/jpeg', 1, 'minio', '2021-08-25 20:28:58', '2021-08-25 20:28:58', 0, 1, 1, 868140350533795873, 0);
INSERT INTO `sys_file` VALUES (880189345481359425, 'e48ddacd6ff348d3b5a58526e6dc1ea4.jpg', 'laputa', '006fmUQjly1gqp5oy33w8j30u0141wqi.jpg', 'jpg', 194756, 'http://laputa-minio:9000/laputa/e48ddacd6ff348d3b5a58526e6dc1ea4.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210825%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210825T123813Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=a1793adc70986d366634da6b23fde527d30c0205bdb4129101e8670c4b619f2e', 'image/jpeg', 1, 'minio', '2021-08-25 20:38:13', '2021-08-25 20:38:13', 0, 1, 1, 868140350533795873, 0);
INSERT INTO `sys_file` VALUES (880190084060545121, '0c7d97f663a6421cbac12a8a3855b3b7.jpg', 'laputa', '006fmUQjly1gsgf5os8tyj30hb0s9dha.jpg', 'jpg', 64717, 'http://laputa-minio:9000/laputa/0c7d97f663a6421cbac12a8a3855b3b7.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210825%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210825T124109Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=91bcb89670b19889b978e7a301a0b7f62ab93177b9cfa5b7cb727155cf00119f', 'image/jpeg', 1, 'minio', '2021-08-25 20:41:09', '2021-08-25 20:41:09', 0, 1, 1, 868140350533795873, 0);
INSERT INTO `sys_file` VALUES (880339305396961313, '50230b9c8b9a4bb68e92dc895b3c6fc0.m4a', 'laputa', '拜登.m4a', 'm4a', 65380111, 'http://laputa-minio:9000/laputa/50230b9c8b9a4bb68e92dc895b3c6fc0.m4a?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210825%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210825T223406Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=4fd3b3749efef8210ff7c440050b993d1b8da1e4b81e04ee061b4b13a2a9a99e', 'audio/x-m4a', 0, 'minio', '2021-08-26 06:34:07', '2021-08-26 00:53:36', 1, 1, 1, 868140350533795873, 0);
INSERT INTO `sys_file` VALUES (880339491896688705, '490b0d60970d4e1bb4feb738f18ba655.m4a', 'laputa', '拜登.m4a', 'm4a', 65380111, 'http://laputa-minio:9000/laputa/490b0d60970d4e1bb4feb738f18ba655.m4a?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210825%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210825T223450Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=0ed9cb4f1fe40c847afc2cde8efb7257be668213f57481a0d4b2e2ef80236219', 'audio/x-m4a', 0, 'minio', '2021-08-26 06:34:51', '2021-08-26 06:34:51', 0, 1, 1, 868140350533795873, 0);
INSERT INTO `sys_file` VALUES (880459154538364961, 'a2f9c077b78940c996aec59436e68a6e.sql', 'laputa', 'sys_menu.sql', 'sql', 13655, 'http://laputa-minio:9000/laputa/a2f9c077b78940c996aec59436e68a6e.sql?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210826%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210826T063020Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=df72167b91c5e59431a507ce09d32ecc35d23ae0412e528ed4aa2c7ba692d8f3', 'application/octet-stream', 0, 'minio', '2021-08-26 14:30:21', '2021-08-26 14:30:21', 0, 1, 1, 868140350533795873, 0);
INSERT INTO `sys_file` VALUES (880459323019362369, 'ab5cd548041b4a35b4e7d3cef3ed8271.mp4', 'laputa', '02.圈子功能实现之动态信息点赞（编写dubbo服务）_高清 720P.mp4', 'mp4', 34301967, 'http://laputa-minio:9000/laputa/ab5cd548041b4a35b4e7d3cef3ed8271.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210826%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210826T063100Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=1d859521949fa275ca6897afe1ba79fa7fbb8167a9d337434c6b18877e3e7102', 'video/mp4', 0, 'minio', '2021-08-26 14:31:01', '2021-08-26 14:31:01', 0, 1, 1, 868140350533795873, 0);
INSERT INTO `sys_file` VALUES (880532464483696673, '706462bdf41f49cb99076d9b2af3d278.jpg', 'laputa', 'QQ图片20210501101702.jpg', 'jpg', 211432, 'http://laputa-minio:9000/laputa/706462bdf41f49cb99076d9b2af3d278.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210826%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210826T112139Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=3798974e17a8b7a3216fd6654b7ce67faeaedb6748b7e5910447859f93ae5cc5', 'image/jpeg', 1, 'minio', '2021-08-26 19:21:39', '2021-08-26 19:21:39', 0, 1, 1, 868140350533795873, 0);
INSERT INTO `sys_file` VALUES (886563496651653153, 'b9338df3dde24aba8369ef36211cf48e.jpg', 'laputa', 'header.jpg', 'jpg', 63755, 'http://laputa-minio:9000/laputa/b9338df3dde24aba8369ef36211cf48e.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210912%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210912T024649Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=555b5846fe6d8223296ec575b23c96aa06fa57457b7a9a3afbb946a6b26321e6', 'image/png', 1, 'minio', '2021-09-12 10:46:49', '2021-09-12 10:46:49', 0, 1, 1, 868140350533795873, 868140350533795873);
INSERT INTO `sys_file` VALUES (886608596811382817, '87a3b0d2ea744827bba80374c5bcbeba.jpg', 'laputa', 'header.jpg', 'jpg', 63755, 'http://laputa-minio:9000/laputa/87a3b0d2ea744827bba80374c5bcbeba.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210912%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210912T054602Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=b7a841662df5ec7d5bd4ce6a45c92af7e68c5788703a8d14b46b7ea18e5d6a27', 'image/png', 1, 'minio', '2021-09-12 13:46:02', '2021-09-12 13:46:02', 0, 1, 1, 868140350533795873, 868140350533795873);
INSERT INTO `sys_file` VALUES (886608788063256641, 'ae6769b737974202bad1367011582fc9.jpg', 'laputa', 'header.jpg', 'jpg', 63755, 'http://laputa-minio:9000/laputa/ae6769b737974202bad1367011582fc9.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210912%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210912T054647Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=cea6197e79ae5eaf5e58a5e7e3ce588b3510e5d3bd64fc418fdfc76ddeedc972', 'image/png', 1, 'minio', '2021-09-12 13:46:48', '2021-09-12 13:46:48', 0, 1, 1, 868140350533795873, 868140350533795873);
INSERT INTO `sys_file` VALUES (886609304394661985, '3cc6c856b45c441281c89792148c66a2.png', 'laputa', 'logo.png', 'png', 10799, 'http://laputa-minio:9000/laputa/3cc6c856b45c441281c89792148c66a2.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210912%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210912T054850Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=bf61a237936a80e2dfecc4c6081c60604169ce48e76287b5c60722d3b9185d8f', 'image/png', 1, 'minio', '2021-09-12 13:48:51', '2021-09-12 13:48:51', 0, 1, 1, 868140350533795873, 868140350533795873);
INSERT INTO `sys_file` VALUES (886612714795827329, '2dade482370b4de6a7820a9db6737ed1.jpg', 'laputa', '微信图片_20210709152431.jpg', 'jpg', 1043123, 'http://laputa-minio:9000/laputa/2dade482370b4de6a7820a9db6737ed1.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210912%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210912T060223Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=a692d23b130931f54e756ae148a0a4514af1c38445facd55ec4bbfd98e67ab0d', 'image/png', 1, 'minio', '2021-09-12 14:02:24', '2021-09-12 14:02:24', 0, 1, 1, 868140350533795873, 868140350533795873);
INSERT INTO `sys_file` VALUES (886616037611536545, 'b3d796d4606e48fb9a9409ab92bf38a1.jpg', 'laputa', '微信图片_20210709152522.jpg', 'jpg', 838721, 'http://laputa-minio:9000/laputa/b3d796d4606e48fb9a9409ab92bf38a1.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210912%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210912T061536Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=f9325cdf8bbdd74cb5b1d500dd14ca7c894103432d761a593a9c0777bba43693', 'image/png', 1, 'minio', '2021-09-12 14:15:36', '2021-09-12 14:15:36', 0, 1, 1, 868140350533795873, 868140350533795873);
INSERT INTO `sys_file` VALUES (886617083830337729, '3b1b3e0ab42740fcbaa02c97c7ce2bb9.jpg', 'laputa', '微信图片_20210709152522.jpg', 'jpg', 838721, 'http://laputa-minio:9000/laputa/3b1b3e0ab42740fcbaa02c97c7ce2bb9.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210912%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210912T061945Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=38e7bfbbe1498c7c38e77cfaa2f668ab39ede0687adf982e0cbfe80944ad5e56', 'image/png', 1, 'minio', '2021-09-12 14:19:46', '2021-09-12 14:19:46', 0, 1, 1, 868140350533795873, 868140350533795873);
INSERT INTO `sys_file` VALUES (886618891696996577, 'a015f6eff0ce4abda23f1bc0d90bf1f6.jpg', 'laputa', '微信图片_20210709152522.jpg', 'jpg', 838721, 'http://laputa-minio:9000/laputa/a015f6eff0ce4abda23f1bc0d90bf1f6.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210912%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210912T062656Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=cff51d00a9c170efeae9915d72153bcd0102e3835a90d6629cd5e5de45f8ab1a', 'image/png', 1, 'minio', '2021-09-12 14:26:57', '2021-09-12 14:26:57', 0, 1, 1, 868140350533795873, 868140350533795873);
INSERT INTO `sys_file` VALUES (886619399379747073, '80c0ed8f6f584368b8f06c67506fb479.jpg', 'laputa', '微信图片_20210709152522.jpg', 'jpg', 838721, 'http://laputa-minio:9000/laputa/80c0ed8f6f584368b8f06c67506fb479.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210912%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210912T062857Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=d32cb800ee017065fbeeb43869cd150d1d261bac3e56b124853d6f563b6d2edf', 'image/png', 1, 'minio', '2021-09-12 14:28:58', '2021-09-12 14:28:58', 0, 1, 1, 868140350533795873, 868140350533795873);
INSERT INTO `sys_file` VALUES (886619925718761761, '5d6ed85671f94f149b83b1159fd6c468.jpg', 'laputa', '微信图片_20210709152522.jpg', 'jpg', 838721, 'http://laputa-minio:9000/laputa/5d6ed85671f94f149b83b1159fd6c468.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210912%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210912T063103Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=8b3e4f79ebe84997afa36fc9f6f99e37a3604474cf3c982f30fb9d42becab1c5', 'image/png', 1, 'minio', '2021-09-12 14:31:03', '2021-09-12 14:31:03', 0, 1, 1, 868140350533795873, 868140350533795873);
INSERT INTO `sys_file` VALUES (886620501890302273, 'b00b971d910e4ed893481be10d30c604.jpg', 'laputa', '微信图片_20210709152522.jpg', 'jpg', 838721, 'http://laputa-minio:9000/laputa/b00b971d910e4ed893481be10d30c604.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210912%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210912T063320Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=ff6b6c4586e47150910ae58599ba11dc480ef41cacfa5df4a209f9583b80b2b8', 'image/png', 1, 'minio', '2021-09-12 14:33:20', '2021-09-12 14:33:20', 0, 1, 1, 868140350533795873, 868140350533795873);
INSERT INTO `sys_file` VALUES (886622152801911137, 'ee855bc68ed34d1ea375be36293f6d30.jpg', 'laputa', '微信图片_20210709152522.jpg', 'jpg', 838721, 'http://laputa-minio:9000/laputa/ee855bc68ed34d1ea375be36293f6d30.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210912%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210912T063954Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=d7b64e2b50b8a7746a16903b7b478215f87b268572f389068d320ab49763425b', 'image/png', 1, 'minio', '2021-09-12 14:39:54', '2021-09-12 14:39:54', 0, 1, 1, 868140350533795873, 868140350533795873);
INSERT INTO `sys_file` VALUES (886623323029504385, '2ee6a8425c4444e387d8c9302042bbf7.jpg', 'laputa', '微信图片_20210709152522.jpg', 'jpg', 838721, 'http://laputa-minio:9000/laputa/2ee6a8425c4444e387d8c9302042bbf7.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210912%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210912T064433Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=13371d6ae6d041f342ab6df6f0357fca9373160d4683f259bb802b53e99ca782', 'image/png', 1, 'minio', '2021-09-12 14:44:33', '2021-09-12 14:44:33', 0, 1, 1, 868140350533795873, 868140350533795873);
INSERT INTO `sys_file` VALUES (886625607666893217, 'ec98881eb88a407ab0edf26349ca5406.jpg', 'laputa', '微信图片_20210709152522.jpg', 'jpg', 838721, 'http://laputa-minio:9000/laputa/ec98881eb88a407ab0edf26349ca5406.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210912%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210912T065337Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=c1a3816d572601dbdbff1d6e1da3057f33a52e09cd59689c17f93777e9d1f533', 'image/png', 1, 'minio', '2021-09-12 14:53:38', '2021-09-12 14:53:38', 0, 1, 1, 868140350533795873, 868140350533795873);
INSERT INTO `sys_file` VALUES (886626510188839361, 'f61c944288a74e9ab38d09df1f76154e.jpg', 'laputa', '微信图片_20210709152431.jpg', 'jpg', 758736, 'http://laputa-minio:9000/laputa/f61c944288a74e9ab38d09df1f76154e.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210912%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210912T065712Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=2e8217ebfce0ad7232c767c9372235b938383b94462a4ff2878236a323e8120a', 'image/png', 1, 'minio', '2021-09-12 14:57:13', '2021-09-12 14:57:13', 0, 1, 1, 868140350533795873, 868140350533795873);
INSERT INTO `sys_file` VALUES (886629868085707233, '14912d996f7a4afca2b90d60791d3b52.jpg', 'laputa', '微信图片_20210709152431.jpg', 'jpg', 758736, 'http://laputa-minio:9000/laputa/14912d996f7a4afca2b90d60791d3b52.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210912%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210912T071033Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=2d9f95c7b073148136d5f2585c97bbfccdb14094a46c26285c75d57c77c0e4bd', 'image/png', 1, 'minio', '2021-09-12 15:10:34', '2021-09-12 15:10:34', 0, 1, 1, 868140350533795873, 868140350533795873);
INSERT INTO `sys_file` VALUES (886630044338749953, 'bae3ddfe937c41c2ad9f306eebedb00f.jpg', 'laputa', '微信图片_20210709152431.jpg', 'jpg', 758736, 'http://laputa-minio:9000/laputa/bae3ddfe937c41c2ad9f306eebedb00f.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=laputa%2F20210912%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210912T071115Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=fa550e58afd4fb62679d3babd4d9089ce3b96ff92006db52a3b4c992737c3bfc', 'image/png', 1, 'minio', '2021-09-12 15:11:16', '2021-09-12 15:11:16', 0, 1, 1, 868140350533795873, 868140350533795873);

SET FOREIGN_KEY_CHECKS = 1;
