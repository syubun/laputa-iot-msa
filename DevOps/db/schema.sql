
DROP database IF EXISTS `laputa_iot_upms`;
DROP database IF EXISTS `laputa_iot_ac`;
DROP database IF EXISTS `laputa_iot_job`;
DROP database IF EXISTS `laputa_iot_mp`;
DROP database IF EXISTS `laputa_iot_config`;
DROP database IF EXISTS `laputa_iot_pay`;
DROP database IF EXISTS `laputa_iot_codegen`;



-- laputa_iot 核心表
create database `laputa_iot_upms` default character set utf8mb4 collate utf8mb4_general_ci;

-- laputa_iot 工作流相关库
create database `laputa_iot_ac` default character set utf8mb4 collate utf8mb4_general_ci;

-- laputa_iot 任务相关库
create database `laputa_iot_job` default character set utf8mb4 collate utf8mb4_general_ci;

-- laputa_iot 公众号管理相关库
create database `laputa_iot_mp` default character set utf8mb4 collate utf8mb4_general_ci;

-- laputa_iot nacos配置相关库
create database `laputa_iot_config` default character set utf8mb4 collate utf8mb4_general_ci;

-- laputa_iot pay配置相关库
create database `laputa_iot_pay` default character set utf8mb4 collate utf8mb4_general_ci;

-- laputa_iot codegen相关库
create database `laputa_iot_codegen` default character set utf8mb4 collate utf8mb4_general_ci;
-- laputa_iot device相关库
create database `laputa_iot_device` default character set utf8mb4 collate utf8mb4_general_ci;