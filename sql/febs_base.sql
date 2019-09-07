/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : febs_base

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 18/08/2019 17:56:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept`  (
                           `DEPT_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
                           `PARENT_ID` bigint(20) NOT NULL COMMENT '上级部门ID',
                           `DEPT_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
                           `ORDER_NUM` bigint(20) NULL DEFAULT NULL COMMENT '排序',
                           `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                           `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
                           PRIMARY KEY (`DEPT_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES (1, 0, '开发部', 1, '2019-06-14 20:56:41', NULL);
INSERT INTO `t_dept` VALUES (2, 1, '开发一部', 1, '2019-06-14 20:58:46', NULL);
INSERT INTO `t_dept` VALUES (3, 1, '开发二部', 2, '2019-06-14 20:58:56', NULL);
INSERT INTO `t_dept` VALUES (4, 0, '采购部', 2, '2019-06-14 20:59:56', NULL);
INSERT INTO `t_dept` VALUES (5, 0, '财务部', 3, '2019-06-14 21:00:08', NULL);
INSERT INTO `t_dept` VALUES (6, 0, '销售部', 4, '2019-06-14 21:00:15', NULL);
INSERT INTO `t_dept` VALUES (7, 0, '工程部', 5, '2019-06-14 21:00:42', NULL);
INSERT INTO `t_dept` VALUES (8, 0, '行政部', 6, '2019-06-14 21:00:49', NULL);
INSERT INTO `t_dept` VALUES (9, 0, '人力资源部', 8, '2019-06-14 21:01:14', '2019-06-14 21:01:34');
INSERT INTO `t_dept` VALUES (10, 0, '系统部', 7, '2019-06-14 21:01:31', NULL);

-- ----------------------------
-- Table structure for t_eximport
-- ----------------------------
DROP TABLE IF EXISTS `t_eximport`;
CREATE TABLE `t_eximport`  (
                               `FIELD1` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字段1',
                               `FIELD2` int(11) NOT NULL COMMENT '字段2',
                               `FIELD3` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字段3',
                               `CREATE_TIME` datetime(0) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Excel导入导出测试' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_eximport
-- ----------------------------
INSERT INTO `t_eximport` VALUES ('字段1', 1, 'mrbird0@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 2, 'mrbird1@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 3, 'mrbird2@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 4, 'mrbird3@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 5, 'mrbird4@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 6, 'mrbird5@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 7, 'mrbird6@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 8, 'mrbird7@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 9, 'mrbird8@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 10, 'mrbird9@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 11, 'mrbird10@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 12, 'mrbird11@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 13, 'mrbird12@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 14, 'mrbird13@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 15, 'mrbird14@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 16, 'mrbird15@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 17, 'mrbird16@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 18, 'mrbird17@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 19, 'mrbird18@gmail.com', '2019-06-13 03:14:06');
INSERT INTO `t_eximport` VALUES ('字段1', 20, 'mrbird19@gmail.com', '2019-06-13 03:14:06');

-- ----------------------------
-- Table structure for t_generator_config
-- ----------------------------
DROP TABLE IF EXISTS `t_generator_config`;
CREATE TABLE `t_generator_config`  (
                                       `id` int(11) NOT NULL COMMENT '主键',
                                       `author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
                                       `base_package` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '基础包名',
                                       `entity_package` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'entity文件存放路径',
                                       `mapper_package` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'mapper文件存放路径',
                                       `mapper_xml_package` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'mapper xml文件存放路径',
                                       `service_package` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'servcie文件存放路径',
                                       `service_impl_package` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'serviceImpl文件存放路径',
                                       `controller_package` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'controller文件存放路径',
                                       `is_trim` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否去除前缀 1是 0否',
                                       `trim_value` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前缀内容',
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_generator_config
-- ----------------------------
INSERT INTO `t_generator_config` VALUES (1, 'MrBird', 'cc.mrbird.febs.gen', 'entity', 'mapper', 'mapper', 'service', 'service.impl', 'controller', '1', 't_');

-- ----------------------------
-- Table structure for t_job
-- ----------------------------
DROP TABLE IF EXISTS `t_job`;
CREATE TABLE `t_job`  (
                          `JOB_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
                          `BEAN_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'spring bean名称',
                          `METHOD_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '方法名',
                          `PARAMS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
                          `CRON_EXPRESSION` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'cron表达式',
                          `STATUS` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务状态  0：正常  1：暂停',
                          `REMARK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
                          `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                          PRIMARY KEY (`JOB_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_job
-- ----------------------------
INSERT INTO `t_job` VALUES (1, 'testTask', 'test', 'mrbird', '0/1 * * * * ?', '1', '有参任务调度测试~~', '2018-02-24 16:26:14');
INSERT INTO `t_job` VALUES (2, 'testTask', 'test1', NULL, '0/10 * * * * ?', '1', '无参任务调度测试', '2018-02-24 17:06:23');
INSERT INTO `t_job` VALUES (3, 'testTask', 'test', 'hello world', '0/1 * * * * ?', '1', '有参任务调度测试,每隔一秒触发', '2018-02-26 09:28:26');
INSERT INTO `t_job` VALUES (11, 'testTask', 'test2', NULL, '0/5 * * * * ?', '1', '测试异常', '2018-02-26 11:15:30');

-- ----------------------------
-- Table structure for t_job_log
-- ----------------------------
DROP TABLE IF EXISTS `t_job_log`;
CREATE TABLE `t_job_log`  (
                              `LOG_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
                              `JOB_ID` bigint(20) NOT NULL COMMENT '任务id',
                              `BEAN_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'spring bean名称',
                              `METHOD_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '方法名',
                              `PARAMS` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
                              `STATUS` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务状态    0：成功    1：失败',
                              `ERROR` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '失败信息',
                              `TIMES` decimal(11, 0) NULL DEFAULT NULL COMMENT '耗时(单位：毫秒)',
                              `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                              PRIMARY KEY (`LOG_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2562 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '调度日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_job_log
-- ----------------------------
INSERT INTO `t_job_log` VALUES (2472, 2, 'testTask', 'test1', NULL, '0', NULL, 14, '2018-04-02 17:29:50');
INSERT INTO `t_job_log` VALUES (2473, 2, 'testTask', 'test1', NULL, '0', NULL, 1, '2018-04-02 17:30:00');
INSERT INTO `t_job_log` VALUES (2474, 2, 'testTask', 'test1', NULL, '0', NULL, 0, '2018-04-02 17:30:10');
INSERT INTO `t_job_log` VALUES (2475, 2, 'testTask', 'test1', NULL, '0', NULL, 1, '2018-04-02 17:30:20');
INSERT INTO `t_job_log` VALUES (2476, 1, 'testTask', 'test', 'mrbird', '0', NULL, 2, '2019-06-11 08:49:24');
INSERT INTO `t_job_log` VALUES (2477, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 08:49:25');
INSERT INTO `t_job_log` VALUES (2478, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 08:49:26');
INSERT INTO `t_job_log` VALUES (2479, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 08:49:27');
INSERT INTO `t_job_log` VALUES (2480, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 08:49:28');
INSERT INTO `t_job_log` VALUES (2481, 1, 'testTask', 'test', 'mrbird', '0', NULL, 2, '2019-06-11 08:49:29');
INSERT INTO `t_job_log` VALUES (2482, 1, 'testTask', 'test', 'mrbird', '0', NULL, 2, '2019-06-11 08:49:30');
INSERT INTO `t_job_log` VALUES (2483, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 08:49:31');
INSERT INTO `t_job_log` VALUES (2484, 1, 'testTask', 'test', 'mrbird', '0', NULL, 4, '2019-06-11 10:43:36');
INSERT INTO `t_job_log` VALUES (2485, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:43:37');
INSERT INTO `t_job_log` VALUES (2486, 1, 'testTask', 'test', 'mrbird', '0', NULL, 15, '2019-06-11 10:43:38');
INSERT INTO `t_job_log` VALUES (2487, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:43:39');
INSERT INTO `t_job_log` VALUES (2488, 1, 'testTask', 'test', 'mrbird', '0', NULL, 53, '2019-06-11 10:43:40');
INSERT INTO `t_job_log` VALUES (2489, 1, 'testTask', 'test', 'mrbird', '0', NULL, 3, '2019-06-11 10:43:41');
INSERT INTO `t_job_log` VALUES (2490, 1, 'testTask', 'test', 'mrbird', '0', NULL, 2, '2019-06-11 10:43:42');
INSERT INTO `t_job_log` VALUES (2491, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:43:43');
INSERT INTO `t_job_log` VALUES (2492, 1, 'testTask', 'test', 'mrbird', '0', NULL, 0, '2019-06-11 10:43:44');
INSERT INTO `t_job_log` VALUES (2493, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:43:45');
INSERT INTO `t_job_log` VALUES (2494, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:43:46');
INSERT INTO `t_job_log` VALUES (2495, 1, 'testTask', 'test', 'mrbird', '0', NULL, 13, '2019-06-11 10:43:47');
INSERT INTO `t_job_log` VALUES (2496, 1, 'testTask', 'test', 'mrbird', '0', NULL, 35, '2019-06-11 10:43:48');
INSERT INTO `t_job_log` VALUES (2497, 1, 'testTask', 'test', 'mrbird', '0', NULL, 3, '2019-06-11 10:43:49');
INSERT INTO `t_job_log` VALUES (2498, 1, 'testTask', 'test', 'mrbird', '0', NULL, 7, '2019-06-11 10:43:50');
INSERT INTO `t_job_log` VALUES (2499, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:43:51');
INSERT INTO `t_job_log` VALUES (2500, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:43:52');
INSERT INTO `t_job_log` VALUES (2501, 1, 'testTask', 'test', 'mrbird', '0', NULL, 2, '2019-06-11 10:43:53');
INSERT INTO `t_job_log` VALUES (2502, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:43:54');
INSERT INTO `t_job_log` VALUES (2503, 1, 'testTask', 'test', 'mrbird', '0', NULL, 14, '2019-06-11 10:43:55');
INSERT INTO `t_job_log` VALUES (2504, 1, 'testTask', 'test', 'mrbird', '0', NULL, 2, '2019-06-11 10:43:56');
INSERT INTO `t_job_log` VALUES (2505, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:43:57');
INSERT INTO `t_job_log` VALUES (2506, 1, 'testTask', 'test', 'mrbird', '0', NULL, 2, '2019-06-11 10:43:58');
INSERT INTO `t_job_log` VALUES (2507, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:43:59');
INSERT INTO `t_job_log` VALUES (2508, 1, 'testTask', 'test', 'mrbird', '0', NULL, 2, '2019-06-11 10:44:00');
INSERT INTO `t_job_log` VALUES (2509, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:44:01');
INSERT INTO `t_job_log` VALUES (2510, 1, 'testTask', 'test', 'mrbird', '0', NULL, 37, '2019-06-11 10:44:02');
INSERT INTO `t_job_log` VALUES (2511, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:44:03');
INSERT INTO `t_job_log` VALUES (2512, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:44:04');
INSERT INTO `t_job_log` VALUES (2513, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:44:05');
INSERT INTO `t_job_log` VALUES (2514, 1, 'testTask', 'test', 'mrbird', '0', NULL, 0, '2019-06-11 10:44:06');
INSERT INTO `t_job_log` VALUES (2515, 1, 'testTask', 'test', 'mrbird', '0', NULL, 2, '2019-06-11 10:44:07');
INSERT INTO `t_job_log` VALUES (2516, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:44:08');
INSERT INTO `t_job_log` VALUES (2517, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:44:09');
INSERT INTO `t_job_log` VALUES (2518, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:44:10');
INSERT INTO `t_job_log` VALUES (2519, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:44:11');
INSERT INTO `t_job_log` VALUES (2520, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:44:12');
INSERT INTO `t_job_log` VALUES (2521, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:44:13');
INSERT INTO `t_job_log` VALUES (2522, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:44:16');
INSERT INTO `t_job_log` VALUES (2523, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:44:16');
INSERT INTO `t_job_log` VALUES (2524, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:44:16');
INSERT INTO `t_job_log` VALUES (2525, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:44:17');
INSERT INTO `t_job_log` VALUES (2526, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:44:18');
INSERT INTO `t_job_log` VALUES (2527, 1, 'testTask', 'test', 'mrbird', '0', NULL, 145, '2019-06-11 10:44:19');
INSERT INTO `t_job_log` VALUES (2528, 1, 'testTask', 'test', 'mrbird', '0', NULL, 11, '2019-06-11 10:44:20');
INSERT INTO `t_job_log` VALUES (2529, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:44:21');
INSERT INTO `t_job_log` VALUES (2530, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:44:22');
INSERT INTO `t_job_log` VALUES (2531, 1, 'testTask', 'test', 'mrbird', '0', NULL, 206, '2019-06-11 10:44:23');
INSERT INTO `t_job_log` VALUES (2532, 1, 'testTask', 'test', 'mrbird', '0', NULL, 0, '2019-06-11 10:44:24');
INSERT INTO `t_job_log` VALUES (2533, 1, 'testTask', 'test', 'mrbird', '0', NULL, 0, '2019-06-11 10:44:25');
INSERT INTO `t_job_log` VALUES (2534, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:44:26');
INSERT INTO `t_job_log` VALUES (2535, 1, 'testTask', 'test', 'mrbird', '0', NULL, 0, '2019-06-11 10:44:27');
INSERT INTO `t_job_log` VALUES (2536, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:44:28');
INSERT INTO `t_job_log` VALUES (2537, 1, 'testTask', 'test', 'mrbird', '0', NULL, 2, '2019-06-11 10:45:54');
INSERT INTO `t_job_log` VALUES (2538, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:45:55');
INSERT INTO `t_job_log` VALUES (2539, 1, 'testTask', 'test', 'mrbird', '0', NULL, 2, '2019-06-11 10:45:56');
INSERT INTO `t_job_log` VALUES (2540, 1, 'testTask', 'test', 'mrbird', '0', NULL, 4, '2019-06-11 10:45:57');
INSERT INTO `t_job_log` VALUES (2541, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:45:58');
INSERT INTO `t_job_log` VALUES (2542, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:45:59');
INSERT INTO `t_job_log` VALUES (2543, 1, 'testTask', 'test', 'mrbird', '0', NULL, 0, '2019-06-11 10:46:00');
INSERT INTO `t_job_log` VALUES (2544, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:46:01');
INSERT INTO `t_job_log` VALUES (2545, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:46:02');
INSERT INTO `t_job_log` VALUES (2546, 1, 'testTask', 'test', 'mrbird', '0', NULL, 0, '2019-06-11 10:46:03');
INSERT INTO `t_job_log` VALUES (2547, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:46:04');
INSERT INTO `t_job_log` VALUES (2548, 1, 'testTask', 'test', 'mrbird', '0', NULL, 0, '2019-06-11 10:46:05');
INSERT INTO `t_job_log` VALUES (2549, 1, 'testTask', 'test', 'mrbird', '0', NULL, 2, '2019-06-11 10:46:06');
INSERT INTO `t_job_log` VALUES (2550, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:46:07');
INSERT INTO `t_job_log` VALUES (2551, 1, 'testTask', 'test', 'mrbird', '0', NULL, 2, '2019-06-11 10:46:45');
INSERT INTO `t_job_log` VALUES (2552, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:46:46');
INSERT INTO `t_job_log` VALUES (2553, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:46:47');
INSERT INTO `t_job_log` VALUES (2554, 1, 'testTask', 'test', 'mrbird', '0', NULL, 2, '2019-06-11 10:46:48');
INSERT INTO `t_job_log` VALUES (2555, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:46:49');
INSERT INTO `t_job_log` VALUES (2556, 1, 'testTask', 'test', 'mrbird', '0', NULL, 10, '2019-06-11 10:46:50');
INSERT INTO `t_job_log` VALUES (2557, 1, 'testTask', 'test', 'mrbird', '0', NULL, 2, '2019-06-11 10:46:51');
INSERT INTO `t_job_log` VALUES (2558, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:46:52');
INSERT INTO `t_job_log` VALUES (2559, 1, 'testTask', 'test', 'mrbird', '0', NULL, 2, '2019-06-11 10:46:53');
INSERT INTO `t_job_log` VALUES (2560, 1, 'testTask', 'test', 'mrbird', '0', NULL, 0, '2019-06-11 10:46:54');
INSERT INTO `t_job_log` VALUES (2561, 1, 'testTask', 'test', 'mrbird', '0', NULL, 1, '2019-06-11 10:46:55');

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log`  (
                          `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
                          `USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户',
                          `OPERATION` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '操作内容',
                          `TIME` decimal(11, 0) NULL DEFAULT NULL COMMENT '耗时',
                          `METHOD` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '操作方法',
                          `PARAMS` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '方法参数',
                          `IP` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作者IP',
                          `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                          `location` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作地点',
                          PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1008 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES (890, 'MrBird', '修改角色', 1144, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=管理员, remark=管理员，拥有所有操作权限, createTime=null, modifyTime=Thu Jun 13 16:40:57 CST 2019, menuIds=1,3,11,12,13,160,161,4,14,15,16,5,17,18,19,6,20,21,22,2,8,23,10,24,136,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,109,110,137,138,139,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125)\"', '127.0.0.1', '2019-06-13 08:40:59', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (891, 'MrBird', '修改角色', 267, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=75, roleName=可怜, remark=, createTime=null, modifyTime=Thu Jun 13 16:47:00 CST 2019, menuIds=115)\"', '127.0.0.1', '2019-06-13 08:47:01', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (892, 'MrBird', '修改角色', 478, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=管理员, remark=管理员，拥有所有操作权限, createTime=null, modifyTime=Thu Jun 13 16:47:15 CST 2019, menuIds=1,3,11,12,13,160,161,4,14,15,16,5,17,18,19,6,20,21,22,2,8,23,10,24,136,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,109,110,137,138,139,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125)\"', '127.0.0.1', '2019-06-13 08:47:16', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (893, 'MrBird', '删除角色', 233, 'cc.mrbird.febs.system.controller.RoleController.deleteRoles()', ' roleIds: \"75\"', '127.0.0.1', '2019-06-13 08:47:50', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (894, 'MrBird', '修改用户', 128, 'cc.mrbird.febs.system.controller.UserController.updateUser()', ' user: \"User(userId=6, username=tester, password=null, deptId=6, email=tester@qq.com, mobile=13888888888, status=1, createTime=null, modifyTime=Thu Jun 13 16:56:29 CST 2019, lastLoginTime=null, sex=1, avatar=null, theme=null, isTab=null, description=我是测试用户, deptName=null, createTimeFrom=null, createTimeTo=null, roleId=2, roleName=null)\"', '127.0.0.1', '2019-06-13 08:56:29', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (895, 'MrBird', '修改用户', 323, 'cc.mrbird.febs.system.controller.UserController.updateUser()', ' user: \"User(userId=6, username=tester, password=null, deptId=6, email=tester@qq.com, mobile=13888888888, status=1, createTime=null, modifyTime=Thu Jun 13 16:56:36 CST 2019, lastLoginTime=null, sex=0, avatar=null, theme=null, isTab=null, description=我是测试用户, deptName=null, createTimeFrom=null, createTimeTo=null, roleId=2, roleName=null)\"', '127.0.0.1', '2019-06-13 08:56:37', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (896, 'MrBird', '修改菜单/按钮', 172, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=3, parentId=1, menuName=用户管理, url=/system/user, perms=user:view, icon=, type=0, orderNum=1, createTime=null, modifyTime=Thu Jun 13 16:57:10 CST 2019)\"', '127.0.0.1', '2019-06-13 08:57:11', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (897, 'MrBird', '修改菜单/按钮', 164, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=4, parentId=1, menuName=角色管理, url=/system/role, perms=role:view, icon=, type=0, orderNum=2, createTime=null, modifyTime=Thu Jun 13 16:57:18 CST 2019)\"', '127.0.0.1', '2019-06-13 08:57:19', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (898, 'MrBird', '修改菜单/按钮', 81, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=5, parentId=1, menuName=菜单管理, url=/system/menu, perms=menu:view, icon=, type=0, orderNum=3, createTime=null, modifyTime=Thu Jun 13 16:57:33 CST 2019)\"', '127.0.0.1', '2019-06-13 08:57:34', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (899, 'MrBird', '修改角色', 436, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=管理员, remark=管理员，拥有所有操作权限, createTime=null, modifyTime=Thu Jun 13 16:59:24 CST 2019, menuIds=1,3,161,4,14,15,16,5,17,18,19,6,20,21,22,2,8,23,10,24,136,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,109,110,137,138,139,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125)\"', '127.0.0.1', '2019-06-13 08:59:25', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (900, 'MrBird', '修改角色', 329, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=管理员, remark=管理员，拥有所有操作权限, createTime=null, modifyTime=Thu Jun 13 16:59:55 CST 2019, menuIds=1,3,11,161,4,14,15,16,5,17,18,19,6,20,21,22,2,8,23,10,24,136,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,109,110,137,138,139,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125)\"', '127.0.0.1', '2019-06-13 08:59:56', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (901, 'MrBird', '修改角色', 270, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=管理员, remark=管理员，拥有所有操作权限, createTime=null, modifyTime=Thu Jun 13 17:00:39 CST 2019, menuIds=1,3,11,12,161,4,14,15,16,5,17,18,19,6,20,21,22,2,8,23,10,24,136,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,109,110,137,138,139,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125)\"', '127.0.0.1', '2019-06-13 09:00:39', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (902, 'MrBird', '修改角色', 238, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=管理员, remark=管理员，拥有所有操作权限, createTime=null, modifyTime=Thu Jun 13 17:01:47 CST 2019, menuIds=1,3,11,12,13,160,161,4,14,15,16,5,17,18,19,6,20,21,22,2,8,23,10,24,136,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,109,110,137,138,139,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125)\"', '127.0.0.1', '2019-06-13 09:01:48', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (903, 'MrBird', '修改角色', 202, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=管理员, remark=管理员，拥有所有操作权限, createTime=null, modifyTime=Thu Jun 13 17:02:28 CST 2019, menuIds=1,3,11,13,160,161,4,14,15,16,5,17,18,19,6,20,21,22,2,8,23,10,24,136,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,109,110,137,138,139,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125)\"', '127.0.0.1', '2019-06-13 09:02:28', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (904, 'MrBird', '修改角色', 446, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=管理员, remark=管理员，拥有所有操作权限, createTime=null, modifyTime=Thu Jun 13 17:14:28 CST 2019, menuIds=1,3,161,4,14,15,16,5,17,18,19,6,20,21,22,2,8,23,10,24,136,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,109,110,137,138,139,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125)\"', '127.0.0.1', '2019-06-13 09:14:29', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (905, 'MrBird', '修改角色', 150, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=管理员, remark=管理员，拥有所有操作权限, createTime=null, modifyTime=Thu Jun 13 17:14:47 CST 2019, menuIds=1,3,11,12,13,160,161,4,14,15,16,5,17,18,19,6,20,21,22,2,8,23,10,24,136,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,109,110,137,138,139,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125)\"', '127.0.0.1', '2019-06-13 09:14:48', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (906, 'MrBird', '修改角色', 88, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=管理员, remark=管理员，拥有所有操作权限, createTime=null, modifyTime=Thu Jun 13 17:14:55 CST 2019, menuIds=1,3,4,14,15,16,5,17,18,19,6,20,21,22,2,8,23,10,24,136,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,109,110,137,138,139,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125)\"', '127.0.0.1', '2019-06-13 09:14:56', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (907, 'MrBird', '修改角色', 177, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=管理员, remark=管理员，拥有所有操作权限, createTime=null, modifyTime=Thu Jun 13 17:18:58 CST 2019, menuIds=1,3,11,4,14,15,16,5,17,18,19,6,20,21,22,2,8,23,10,24,136,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,109,110,137,138,139,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125)\"', '127.0.0.1', '2019-06-13 09:18:58', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (908, 'MrBird', '修改角色', 286, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=管理员, remark=管理员，拥有所有操作权限, createTime=null, modifyTime=Thu Jun 13 17:22:52 CST 2019, menuIds=1,3,4,14,15,16,5,17,18,19,6,20,21,22,2,8,23,10,24,136,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,109,110,137,138,139,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125)\"', '127.0.0.1', '2019-06-13 09:22:53', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (909, 'MrBird', '修改角色', 105, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=管理员, remark=管理员，拥有所有操作权限, createTime=null, modifyTime=Thu Jun 13 17:23:08 CST 2019, menuIds=1,3,11,4,14,15,16,5,17,18,19,6,20,21,22,2,8,23,10,24,136,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,109,110,137,138,139,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125)\"', '127.0.0.1', '2019-06-13 09:23:09', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (910, 'MrBird', '修改角色', 275, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=管理员, remark=管理员，拥有所有操作权限, createTime=null, modifyTime=Thu Jun 13 17:32:55 CST 2019, menuIds=1,3,11,13,4,14,15,16,5,17,18,19,6,20,21,22,2,8,23,10,24,136,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,109,110,137,138,139,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125)\"', '127.0.0.1', '2019-06-13 09:32:56', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (911, 'MrBird', '修改角色', 348, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=管理员, remark=管理员，拥有所有操作权限, createTime=null, modifyTime=Thu Jun 13 18:53:31 CST 2019, menuIds=1,3,4,14,15,16,5,17,18,19,6,20,21,22,2,8,23,10,24,136,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,109,110,137,138,139,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125)\"', '127.0.0.1', '2019-06-13 10:53:32', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (912, 'MrBird', '修改角色', 114, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=管理员, remark=管理员，拥有所有操作权限, createTime=null, modifyTime=Thu Jun 13 18:53:49 CST 2019, menuIds=1,4,14,15,16,5,17,18,19,6,20,21,22,2,8,23,10,24,136,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,109,110,137,138,139,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125)\"', '127.0.0.1', '2019-06-13 10:53:50', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (913, 'MrBird', '修改角色', 255, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=管理员, remark=管理员，拥有所有操作权限, createTime=null, modifyTime=Thu Jun 13 18:54:08 CST 2019, menuIds=1,3,4,14,15,16,5,17,18,19,6,20,21,22,2,8,23,10,24,136,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,109,110,137,138,139,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125)\"', '127.0.0.1', '2019-06-13 10:54:09', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (914, 'MrBird', '修改菜单/按钮', 51, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=3, parentId=1, menuName=用户管理, url=/system/user, perms=user:view啊, icon=, type=0, orderNum=1, createTime=null, modifyTime=Thu Jun 13 18:54:28 CST 2019)\"', '127.0.0.1', '2019-06-13 10:54:28', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (915, 'MrBird', '修改菜单/按钮', 150, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=3, parentId=1, menuName=用户管理, url=/system/user, perms=user:view, icon=, type=0, orderNum=1, createTime=null, modifyTime=Thu Jun 13 18:54:46 CST 2019)\"', '127.0.0.1', '2019-06-13 10:54:46', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (916, 'MrBird', '修改角色', 259, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=管理员, remark=管理员，拥有所有操作权限, createTime=null, modifyTime=Thu Jun 13 18:55:01 CST 2019, menuIds=1,3,11,4,14,15,16,5,17,18,19,6,20,21,22,2,8,23,10,24,136,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,109,110,137,138,139,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125)\"', '127.0.0.1', '2019-06-13 10:55:02', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (917, 'MrBird', '修改菜单/按钮', 118, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=3, parentId=1, menuName=用户管理, url=/system/user, perms=user:viewa, icon=, type=0, orderNum=1, createTime=null, modifyTime=Thu Jun 13 18:57:57 CST 2019)\"', '127.0.0.1', '2019-06-13 10:57:58', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (918, 'MrBird', '修改角色', 431, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=管理员, remark=管理员，拥有所有操作权限, createTime=null, modifyTime=Thu Jun 13 19:13:30 CST 2019, menuIds=1,3,11,12,13,160,161,4,14,15,16,5,17,18,19,6,20,21,22,2,8,23,10,24,136,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,109,110,137,138,139,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125)\"', '127.0.0.1', '2019-06-13 11:13:31', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (919, 'MrBird', '修改菜单/按钮', 157, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=3, parentId=1, menuName=用户管理, url=/system/user, perms=user:view, icon=, type=0, orderNum=1, createTime=null, modifyTime=Thu Jun 13 19:13:44 CST 2019)\"', '127.0.0.1', '2019-06-13 11:13:45', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (920, 'MrBird', '修改菜单/按钮', 149, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=3, parentId=1, menuName=用户管理, url=/system/user, perms=user:view, icon=layui-icon-meh, type=0, orderNum=1, createTime=null, modifyTime=Thu Jun 13 19:13:55 CST 2019)\"', '127.0.0.1', '2019-06-13 11:13:56', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (921, 'MrBird', '修改菜单/按钮', 163, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=2, parentId=0, menuName=系统监控, url=, perms=, icon=, type=0, orderNum=2, createTime=null, modifyTime=Thu Jun 13 19:14:34 CST 2019)\"', '127.0.0.1', '2019-06-13 11:14:34', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (922, 'MrBird', '修改菜单/按钮', 146, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=2, parentId=0, menuName=系统监控, url=, perms=, icon=layui-icon-alert, type=0, orderNum=2, createTime=null, modifyTime=Thu Jun 13 19:20:40 CST 2019)\"', '127.0.0.1', '2019-06-13 11:20:40', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (923, 'MrBird', '修改角色', 70, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=管理员, remark=管理员，拥有所有操作权限, createTime=null, modifyTime=Thu Jun 13 21:24:53 CST 2019, menuIds=1,3,12,13,160,161,4,14,15,16,5,17,18,19,6,20,21,22,2,8,23,10,24,136,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,109,110,137,138,139,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125)\"', '172.18.96.1', '2019-06-13 13:24:54', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (924, 'MrBird', '修改角色', 22, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=管理员, remark=管理员，拥有所有操作权限, createTime=null, modifyTime=Thu Jun 13 21:25:06 CST 2019, menuIds=1,3,11,12,13,160,161,4,14,15,16,5,17,18,19,6,20,21,22,2,8,23,10,24,136,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,109,110,137,138,139,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125)\"', '172.18.96.1', '2019-06-13 13:25:07', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (925, 'MrBird', '修改角色', 60, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=2, roleName=注册账户, remark=注册账户，只可查看，不可操作, createTime=null, modifyTime=Thu Jun 13 22:24:09 CST 2019, menuIds=1,3,4,14,5,6,2,8,10,136,113,114,127,128,129,130,131,101,102,109,137,138,139,115,132,133,135,134,126,116,117,119,120,121,122,123,118,125)\"', '172.18.96.1', '2019-06-13 14:24:10', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (926, 'MrBird', '修改角色', 69, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=2, roleName=注册账户, remark=注册账户，只可查看，不可操作, createTime=null, modifyTime=Thu Jun 13 22:24:58 CST 2019, menuIds=1,3,4,5,6,2,8,10,136,113,114,127,128,129,130,131,101,102,109,137,138,139,115,132,133,135,134,126,116,117,119,120,121,122,123,118,125)\"', '172.18.96.1', '2019-06-13 14:24:59', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (927, 'scott', '新增角色', 5, 'cc.mrbird.febs.system.controller.RoleController.addRole()', ' role: \"Role(roleId=76, roleName=1341234, remark=, createTime=Thu Jun 13 22:25:21 CST 2019, modifyTime=null, menuIds=)\"', '127.0.0.1', '2019-06-13 14:25:21', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (928, 'MrBird', '新增菜单/按钮', 7, 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=162, parentId=4, menuName=导出角色, url=null, perms=role:export, icon=null, type=1, orderNum=null, createTime=Thu Jun 13 22:29:00 CST 2019, modifyTime=null)\"', '172.18.96.1', '2019-06-13 14:29:00', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (929, 'MrBird', '修改菜单/按钮', 9, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=162, parentId=4, menuName=导出Excel, url=null, perms=role:export, icon=null, type=1, orderNum=null, createTime=null, modifyTime=Thu Jun 13 22:29:11 CST 2019)\"', '172.18.96.1', '2019-06-13 14:29:11', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (930, 'MrBird', '新增菜单/按钮', 14, 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=163, parentId=5, menuName=导出Excel, url=null, perms=menu:export, icon=null, type=1, orderNum=null, createTime=Thu Jun 13 22:29:32 CST 2019, modifyTime=null)\"', '172.18.96.1', '2019-06-13 14:29:32', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (931, 'MrBird', '新增菜单/按钮', 13, 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=164, parentId=6, menuName=导出Excel, url=null, perms=dept:export, icon=null, type=1, orderNum=null, createTime=Thu Jun 13 22:29:58 CST 2019, modifyTime=null)\"', '172.18.96.1', '2019-06-13 14:29:59', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (932, 'MrBird', '修改菜单/按钮', 6, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=8, parentId=2, menuName=在线用户, url=/monitor/online, perms=online:view, icon=, type=0, orderNum=1, createTime=null, modifyTime=Thu Jun 13 22:30:30 CST 2019)\"', '172.18.96.1', '2019-06-13 14:30:31', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (933, 'MrBird', '修改菜单/按钮', 13, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=10, parentId=2, menuName=系统日志, url=/monitor/log, perms=log:view, icon=, type=0, orderNum=2, createTime=null, modifyTime=Thu Jun 13 22:30:37 CST 2019)\"', '172.18.96.1', '2019-06-13 14:30:37', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (934, 'MrBird', '修改菜单/按钮', 17, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=113, parentId=2, menuName=Redis监控, url=/monitor/redis/info, perms=redis:view, icon=, type=0, orderNum=4, createTime=null, modifyTime=Thu Jun 13 22:30:44 CST 2019)\"', '172.18.96.1', '2019-06-13 14:30:45', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (935, 'MrBird', '修改菜单/按钮', 13, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=114, parentId=2, menuName=Redis终端, url=/monitor/redis/terminal, perms=redis:terminal:view, icon=, type=0, orderNum=5, createTime=null, modifyTime=Thu Jun 13 22:30:53 CST 2019)\"', '172.18.96.1', '2019-06-13 14:30:54', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (936, 'MrBird', '修改菜单/按钮', 13, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=139, parentId=137, menuName=代码生成, url=/generator/generator, perms=generator:view, icon=, type=0, orderNum=2, createTime=null, modifyTime=Thu Jun 13 22:31:38 CST 2019)\"', '172.18.96.1', '2019-06-13 14:31:38', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (937, 'MrBird', '新增菜单/按钮', 15, 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=165, parentId=138, menuName=修改配置, url=null, perms=configure:config:update, icon=null, type=1, orderNum=null, createTime=Thu Jun 13 22:32:08 CST 2019, modifyTime=null)\"', '172.18.96.1', '2019-06-13 14:32:09', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (938, 'MrBird', '修改菜单/按钮', 13, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=165, parentId=138, menuName=修改配置, url=null, perms=generator:configure:update, icon=null, type=1, orderNum=null, createTime=null, modifyTime=Thu Jun 13 22:32:19 CST 2019)\"', '172.18.96.1', '2019-06-13 14:32:20', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (939, 'MrBird', '新增菜单/按钮', 6, 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=166, parentId=139, menuName=生成代码, url=null, perms=generator:generate, icon=null, type=1, orderNum=null, createTime=Thu Jun 13 22:32:50 CST 2019, modifyTime=null)\"', '172.18.96.1', '2019-06-13 14:32:51', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (940, 'MrBird', '新增菜单/按钮', 13, 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=167, parentId=125, menuName=模板下载, url=null, perms=eximport:template, icon=null, type=1, orderNum=null, createTime=Thu Jun 13 22:33:36 CST 2019, modifyTime=null)\"', '172.18.96.1', '2019-06-13 14:33:37', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (941, 'MrBird', '新增菜单/按钮', 16, 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=168, parentId=125, menuName=导出Excel, url=null, perms=eximport:export, icon=null, type=1, orderNum=null, createTime=Thu Jun 13 22:33:56 CST 2019, modifyTime=null)\"', '172.18.96.1', '2019-06-13 14:33:57', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (942, 'MrBird', '新增菜单/按钮', 4, 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=169, parentId=125, menuName=导入Excel, url=null, perms=eximport:import, icon=null, type=1, orderNum=null, createTime=Thu Jun 13 22:34:19 CST 2019, modifyTime=null)\"', '172.18.96.1', '2019-06-13 14:34:19', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (943, 'MrBird', '新增菜单/按钮', 13, 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=170, parentId=10, menuName=导出Excel, url=null, perms=log:export, icon=null, type=1, orderNum=null, createTime=Thu Jun 13 22:34:55 CST 2019, modifyTime=null)\"', '172.18.96.1', '2019-06-13 14:34:55', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (944, 'MrBird', '新增菜单/按钮', 11, 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=171, parentId=136, menuName=删除日志, url=null, perms=loginLog:delete, icon=null, type=1, orderNum=null, createTime=Thu Jun 13 22:35:26 CST 2019, modifyTime=null)\"', '172.18.96.1', '2019-06-13 14:35:27', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (945, 'MrBird', '修改菜单/按钮', 15, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=136, parentId=2, menuName=登录日志, url=/monitor/loginlog, perms=loginlog:view, icon=, type=0, orderNum=3, createTime=null, modifyTime=Thu Jun 13 22:35:56 CST 2019)\"', '172.18.96.1', '2019-06-13 14:35:56', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (946, 'MrBird', '修改菜单/按钮', 13, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=171, parentId=136, menuName=删除日志, url=null, perms=loginlog:delete, icon=null, type=1, orderNum=null, createTime=null, modifyTime=Thu Jun 13 22:36:07 CST 2019)\"', '172.18.96.1', '2019-06-13 14:36:08', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (947, 'MrBird', '新增菜单/按钮', 11, 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=172, parentId=136, menuName=导出Excel, url=null, perms=loginlog:export, icon=null, type=1, orderNum=null, createTime=Thu Jun 13 22:36:25 CST 2019, modifyTime=null)\"', '172.18.96.1', '2019-06-13 14:36:26', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (948, 'MrBird', '修改菜单/按钮', 14, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=127, parentId=2, menuName=请求追踪, url=/monitor/httptrace, perms=httptrace:view, icon=, type=0, orderNum=6, createTime=null, modifyTime=Thu Jun 13 22:36:43 CST 2019)\"', '172.18.96.1', '2019-06-13 14:36:43', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (949, 'MrBird', '修改菜单/按钮', 5, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=129, parentId=128, menuName=JVM信息, url=/monitor/jvm, perms=jvm:view, icon=, type=0, orderNum=1, createTime=null, modifyTime=Thu Jun 13 22:36:50 CST 2019)\"', '172.18.96.1', '2019-06-13 14:36:51', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (950, 'MrBird', '修改菜单/按钮', 5, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=130, parentId=128, menuName=Tomcat信息, url=/monitor/tomcat, perms=tomcat:view, icon=, type=0, orderNum=2, createTime=null, modifyTime=Thu Jun 13 22:36:57 CST 2019)\"', '172.18.96.1', '2019-06-13 14:36:57', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (951, 'MrBird', '修改菜单/按钮', 13, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=131, parentId=128, menuName=服务器信息, url=/monitor/server, perms=server:view, icon=, type=0, orderNum=3, createTime=null, modifyTime=Thu Jun 13 22:37:03 CST 2019)\"', '172.18.96.1', '2019-06-13 14:37:04', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (952, 'MrBird', '新增菜单/按钮', 4, 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=173, parentId=102, menuName=导出Excel, url=null, perms=job:export, icon=null, type=1, orderNum=null, createTime=Thu Jun 13 22:37:24 CST 2019, modifyTime=null)\"', '172.18.96.1', '2019-06-13 14:37:25', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (953, 'MrBird', '新增菜单/按钮', 5, 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=174, parentId=109, menuName=导出Excel, url=null, perms=joblog:export, icon=null, type=1, orderNum=null, createTime=Thu Jun 13 22:37:46 CST 2019, modifyTime=null)\"', '172.18.96.1', '2019-06-13 14:37:46', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (954, 'MrBird', '修改菜单/按钮', 7, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=174, parentId=109, menuName=导出Excel, url=null, perms=job:log:export, icon=null, type=1, orderNum=null, createTime=null, modifyTime=Thu Jun 13 22:38:02 CST 2019)\"', '172.18.96.1', '2019-06-13 14:38:02', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (955, 'MrBird', '修改角色', 75, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=2, roleName=注册账户, remark=注册账户，只可查看，不可操作, createTime=null, modifyTime=Thu Jun 13 22:39:57 CST 2019, menuIds=1,3,4,5,163,6,2,8,10,136,113,114,127,128,129,130,131,101,102,109,137,138,139,115,132,133,135,134,126,116,117,119,120,121,122,123,118,125)\"', '172.18.96.1', '2019-06-13 14:39:57', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (956, 'MrBird', '修改角色', 67, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=2, roleName=注册账户, remark=注册账户，只可查看，不可操作, createTime=null, modifyTime=Thu Jun 13 22:42:14 CST 2019, menuIds=1,3,4,5,163,6,164,2,8,10,136,113,114,127,128,129,130,131,101,102,109,137,138,139,115,132,133,135,134,126,116,117,119,120,121,122,123,118,125)\"', '172.18.96.1', '2019-06-13 14:42:15', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (957, 'MrBird', '删除角色', 129, 'cc.mrbird.febs.system.controller.RoleController.deleteRoles()', ' roleIds: \"76\"', '192.168.33.1', '2019-06-14 05:58:39', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (958, 'MrBird', '修改角色', 242, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=管理员, remark=管理员，拥有所有操作权限, createTime=null, modifyTime=Fri Jun 14 14:38:42 CST 2019, menuIds=1,3,11,12,13,160,161,4,14,15,16,162,5,17,18,19,163,6,20,21,22,164,2,8,23,10,24,170,136,171,172,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,173,109,110,174,137,138,165,139,166,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125,167,168,169)\"', '192.168.33.1', '2019-06-14 06:38:43', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (959, 'MrBird', '修改用户', 112, 'cc.mrbird.febs.system.controller.UserController.updateUser()', ' user: \"User(userId=6, username=tester, password=null, deptId=6, email=tester@qq.com, mobile=13888888888, status=1, createTime=null, modifyTime=Fri Jun 14 15:04:29 CST 2019, lastLoginTime=null, sex=0, avatar=null, theme=null, isTab=null, description=我是测试用户, deptName=null, createTimeFrom=null, createTimeTo=null, roleId=68, roleName=null)\"', '192.168.33.1', '2019-06-14 07:04:30', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (960, 'MrBird', '修改角色', 768, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=68, roleName=系统监控员, remark=负责系统监控模块, createTime=null, modifyTime=Fri Jun 14 15:05:10 CST 2019, menuIds=2,8,23,10,24,136,113,114,127,128,129,130,131)\"', '192.168.33.1', '2019-06-14 07:05:11', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (961, 'MrBird', '修改菜单/按钮', 57, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=6, parentId=1, menuName=部门管理, url=/system/dept, perms=dept:view, icon=, type=0, orderNum=4, createTime=null, modifyTime=Fri Jun 14 19:55:59 CST 2019)\"', '172.18.96.1', '2019-06-14 19:56:00', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (962, 'MrBird', '修改角色', 77, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=2, roleName=注册账户, remark=注册账户，拥有查看，新增权限（新增用户除外）和导出Excel权限, createTime=null, modifyTime=Fri Jun 14 20:47:47 CST 2019, menuIds=1,3,161,4,14,162,5,17,163,6,20,164,2,8,10,170,136,172,113,114,127,128,129,130,131,101,102,173,109,174,137,138,139,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125,167,168,169)\"', '172.18.96.1', '2019-06-14 20:47:47', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (963, 'MrBird', '新增角色', 61, 'cc.mrbird.febs.system.controller.RoleController.addRole()', ' role: \"Role(roleId=77, roleName=Redis监控员, remark=负责Redis模块, createTime=Fri Jun 14 20:49:21 CST 2019, modifyTime=null, menuIds=2,113,114)\"', '172.18.96.1', '2019-06-14 20:49:22', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (964, 'MrBird', '新增角色', 66, 'cc.mrbird.febs.system.controller.RoleController.addRole()', ' role: \"Role(roleId=78, roleName=系统监控员, remark=负责整个系统监控模块, createTime=Fri Jun 14 20:50:06 CST 2019, modifyTime=null, menuIds=2,8,23,10,24,170,136,171,172,113,114,127,128,129,130,131)\"', '172.18.96.1', '2019-06-14 20:50:07', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (965, 'MrBird', '新增角色', 62, 'cc.mrbird.febs.system.controller.RoleController.addRole()', ' role: \"Role(roleId=79, roleName=跑批人员, remark=负责任务调度跑批模块, createTime=Fri Jun 14 20:51:01 CST 2019, modifyTime=null, menuIds=101,102,103,104,105,106,107,108,173,109,110,174)\"', '172.18.96.1', '2019-06-14 20:51:02', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (966, 'MrBird', '新增角色', 58, 'cc.mrbird.febs.system.controller.RoleController.addRole()', ' role: \"Role(roleId=80, roleName=开发人员, remark=拥有代码生成模块的权限, createTime=Fri Jun 14 20:51:25 CST 2019, modifyTime=null, menuIds=137,138,165,139,166)\"', '172.18.96.1', '2019-06-14 20:51:26', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (967, 'MrBird', '修改角色', 72, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=系统管理员, remark=系统管理员，拥有所有操作权限(●\'◡\'●), createTime=null, modifyTime=Fri Jun 14 20:51:57 CST 2019, menuIds=1,3,11,12,13,160,161,4,14,15,16,162,5,17,18,19,163,6,20,21,22,164,2,8,23,10,24,170,136,171,172,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,173,109,110,174,137,138,165,139,166,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125,167,168,169)\"', '172.18.96.1', '2019-06-14 20:51:58', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (968, 'MrBird', '修改角色', 27, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=系统管理员, remark=系统管理员，拥有所有操作权限 ^_^, createTime=null, modifyTime=Fri Jun 14 20:52:14 CST 2019, menuIds=1,3,11,12,13,160,161,4,14,15,16,162,5,17,18,19,163,6,20,21,22,164,2,8,23,10,24,170,136,171,172,113,114,127,128,129,130,131,101,102,103,104,105,106,107,108,173,109,110,174,137,138,165,139,166,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125,167,168,169)\"', '172.18.96.1', '2019-06-14 20:52:14', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (969, 'MrBird', '新增用户', 20, 'cc.mrbird.febs.system.controller.UserController.addUser()', ' user: \"User(userId=45, username=Scott, password=1d685729d113cfd03872f154939bee1c, deptId=null, email=scott@gmail.com, mobile=17722222222, status=1, createTime=Fri Jun 14 20:55:52 CST 2019, modifyTime=null, lastLoginTime=null, sex=0, avatar=default.jpg, theme=black, isTab=1, description=我是scott。, deptName=null, createTimeFrom=null, createTimeTo=null, roleId=2, roleName=null)\"', '172.18.96.1', '2019-06-14 20:55:53', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (970, 'MrBird', '新增部门', 14, 'cc.mrbird.febs.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=7, parentId=0, deptName=开发部, orderNum=1, createTime=Fri Jun 14 20:56:40 CST 2019, modifyTime=null)\"', '172.18.96.1', '2019-06-14 20:56:41', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (971, 'MrBird', '新增部门', 17, 'cc.mrbird.febs.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=2, parentId=1, deptName=开发一部, orderNum=1, createTime=Fri Jun 14 20:58:46 CST 2019, modifyTime=null)\"', '172.18.96.1', '2019-06-14 20:58:46', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (972, 'MrBird', '新增部门', 5, 'cc.mrbird.febs.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=3, parentId=1, deptName=开发二部, orderNum=2, createTime=Fri Jun 14 20:58:55 CST 2019, modifyTime=null)\"', '172.18.96.1', '2019-06-14 20:58:56', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (973, 'MrBird', '新增部门', 6, 'cc.mrbird.febs.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=4, parentId=0, deptName=采购部, orderNum=2, createTime=Fri Jun 14 20:59:55 CST 2019, modifyTime=null)\"', '172.18.96.1', '2019-06-14 20:59:56', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (974, 'MrBird', '新增部门', 13, 'cc.mrbird.febs.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=5, parentId=0, deptName=财务部, orderNum=3, createTime=Fri Jun 14 21:00:08 CST 2019, modifyTime=null)\"', '172.18.96.1', '2019-06-14 21:00:08', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (975, 'MrBird', '新增部门', 5, 'cc.mrbird.febs.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=6, parentId=0, deptName=销售部, orderNum=4, createTime=Fri Jun 14 21:00:15 CST 2019, modifyTime=null)\"', '172.18.96.1', '2019-06-14 21:00:15', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (976, 'MrBird', '新增部门', 5, 'cc.mrbird.febs.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=7, parentId=0, deptName=工程部, orderNum=5, createTime=Fri Jun 14 21:00:41 CST 2019, modifyTime=null)\"', '172.18.96.1', '2019-06-14 21:00:42', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (977, 'MrBird', '新增部门', 12, 'cc.mrbird.febs.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=8, parentId=0, deptName=行政部, orderNum=6, createTime=Fri Jun 14 21:00:48 CST 2019, modifyTime=null)\"', '172.18.96.1', '2019-06-14 21:00:49', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (978, 'MrBird', '新增部门', 4, 'cc.mrbird.febs.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=9, parentId=0, deptName=人力资源部, orderNum=7, createTime=Fri Jun 14 21:01:14 CST 2019, modifyTime=null)\"', '172.18.96.1', '2019-06-14 21:01:14', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (979, 'MrBird', '新增部门', 12, 'cc.mrbird.febs.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=10, parentId=0, deptName=系统部, orderNum=7, createTime=Fri Jun 14 21:01:30 CST 2019, modifyTime=null)\"', '172.18.96.1', '2019-06-14 21:01:31', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (980, 'MrBird', '修改部门', 15, 'cc.mrbird.febs.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=9, parentId=0, deptName=人力资源部, orderNum=8, createTime=null, modifyTime=Fri Jun 14 21:01:34 CST 2019)\"', '172.18.96.1', '2019-06-14 21:01:34', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (981, 'MrBird', '修改用户', 26, 'cc.mrbird.febs.system.controller.UserController.updateUser()', ' user: \"User(userId=2, username=Scott, password=null, deptId=10, email=scott@gmail.com, mobile=17722222222, status=1, createTime=null, modifyTime=Fri Jun 14 21:02:10 CST 2019, lastLoginTime=null, sex=0, avatar=null, theme=null, isTab=null, description=我是scott。, deptName=null, createTimeFrom=null, createTimeTo=null, roleId=2, roleName=null)\"', '172.18.96.1', '2019-06-14 21:02:11', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (982, 'MrBird', '新增用户', 15, 'cc.mrbird.febs.system.controller.UserController.addUser()', ' user: \"User(userId=3, username=Reina, password=1461afff857c02afbfb768aa3771503d, deptId=4, email=Reina@hotmail.com, mobile=17711111111, status=1, createTime=Fri Jun 14 21:07:37 CST 2019, modifyTime=null, lastLoginTime=null, sex=1, avatar=default.jpg, theme=black, isTab=1, description=由于公款私用，已被封禁。, deptName=null, createTimeFrom=null, createTimeTo=null, roleId=77, roleName=null)\"', '172.18.96.1', '2019-06-14 21:07:38', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (983, 'MrBird', '修改用户', 15, 'cc.mrbird.febs.system.controller.UserController.updateUser()', ' user: \"User(userId=3, username=Reina, password=null, deptId=4, email=Reina@hotmail.com, mobile=17711111111, status=0, createTime=null, modifyTime=Fri Jun 14 21:08:48 CST 2019, lastLoginTime=null, sex=1, avatar=null, theme=null, isTab=null, description=由于公款私用，已被封禁。, deptName=null, createTimeFrom=null, createTimeTo=null, roleId=77, roleName=null)\"', '172.18.96.1', '2019-06-14 21:08:49', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (984, 'MrBird', '新增用户', 14, 'cc.mrbird.febs.system.controller.UserController.addUser()', ' user: \"User(userId=4, username=Micaela, password=9f2daa2c7bed6870fcbb5b9a51d6300e, deptId=10, email=Micaela@163.com, mobile=17733333333, status=1, createTime=Fri Jun 14 21:10:13 CST 2019, modifyTime=null, lastLoginTime=null, sex=0, avatar=default.jpg, theme=black, isTab=1, description=我叫米克拉, deptName=null, createTimeFrom=null, createTimeTo=null, roleId=78, roleName=null)\"', '172.18.96.1', '2019-06-14 21:10:13', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (985, 'MrBird', '新增用户', 6, 'cc.mrbird.febs.system.controller.UserController.addUser()', ' user: \"User(userId=5, username=Jana, password=176679b77b3c3e352bd3b30ddc81083e, deptId=8, email=Jana@126.com, mobile=17744444444, status=1, createTime=Fri Jun 14 21:12:16 CST 2019, modifyTime=null, lastLoginTime=null, sex=1, avatar=default.jpg, theme=black, isTab=1, description=大家好，我叫简娜, deptName=null, createTimeFrom=null, createTimeTo=null, roleId=79, roleName=null)\"', '172.18.96.1', '2019-06-14 21:12:16', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (986, 'MrBird', '新增用户', 63, 'cc.mrbird.febs.system.controller.UserController.addUser()', ' user: \"User(userId=6, username=Georgie, password=dffc683378cdaa015a0ee9554c532225, deptId=3, email=Georgie@qq.com, mobile=17766666666, status=1, createTime=Fri Jun 14 21:15:09 CST 2019, modifyTime=null, lastLoginTime=null, sex=2, avatar=default.jpg, theme=black, isTab=1, description=生产环境执行rm -rf *，账号封禁中T T, deptName=null, createTimeFrom=null, createTimeTo=null, roleId=80, roleName=null)\"', '172.18.96.1', '2019-06-14 21:15:09', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (987, 'MrBird', '修改用户', 54, 'cc.mrbird.febs.system.controller.UserController.updateUser()', ' user: \"User(userId=6, username=Georgie, password=null, deptId=3, email=Georgie@qq.com, mobile=17766666666, status=1, createTime=null, modifyTime=Fri Jun 14 21:15:44 CST 2019, lastLoginTime=null, sex=2, avatar=null, theme=null, isTab=null, description=生产执行rm -rf *，账号封禁T T, deptName=null, createTimeFrom=null, createTimeTo=null, roleId=80, roleName=null)\"', '172.18.96.1', '2019-06-14 21:15:44', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (988, 'MrBird', '新增用户', 24, 'cc.mrbird.febs.system.controller.UserController.addUser()', ' user: \"User(userId=7, username=Margot, password=31379841b9f4bfde22b8b40471e9a6ce, deptId=9, email=Margot@qq.com, mobile=13444444444, status=1, createTime=Fri Jun 14 21:17:52 CST 2019, modifyTime=null, lastLoginTime=null, sex=1, avatar=default.jpg, theme=black, isTab=1, description=大家好我叫玛戈, deptName=null, createTimeFrom=null, createTimeTo=null, roleId=77,78, roleName=null)\"', '172.18.96.1', '2019-06-14 21:17:53', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (989, 'MrBird', '修改用户', 67, 'cc.mrbird.febs.system.controller.UserController.updateUser()', ' user: \"User(userId=7, username=Margot, password=null, deptId=9, email=Margot@qq.com, mobile=13444444444, status=1, createTime=null, modifyTime=Fri Jun 14 21:18:59 CST 2019, lastLoginTime=null, sex=1, avatar=null, theme=null, isTab=null, description=大家好我叫玛戈, deptName=null, createTimeFrom=null, createTimeTo=null, roleId=78,79,80, roleName=null)\"', '172.18.96.1', '2019-06-14 21:18:59', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES (990, 'MrBird', '新增菜单/按钮', 12, 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=176, parentId=0, menuName=测试bug, url=, perms=, icon=, type=0, orderNum=null, createTime=Sun Aug 11 20:12:50 CST 2019, modifyTime=null)\"', '192.168.56.1', '2019-08-11 20:12:51', '');
INSERT INTO `t_log` VALUES (991, 'MrBird', '新增菜单/按钮', 11, 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=175, parentId=0, menuName=测试bug, url=, perms=, icon=, type=0, orderNum=null, createTime=Sun Aug 11 20:12:50 CST 2019, modifyTime=null)\"', '192.168.56.1', '2019-08-11 20:12:51', '');
INSERT INTO `t_log` VALUES (992, 'MrBird', '新增部门', 14, 'cc.mrbird.febs.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=11, parentId=0, deptName=测试, orderNum=null, createTime=Sun Aug 11 20:13:01 CST 2019, modifyTime=null)\"', '192.168.56.1', '2019-08-11 20:13:02', '');
INSERT INTO `t_log` VALUES (993, 'MrBird', '新增部门', 15, 'cc.mrbird.febs.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=12, parentId=0, deptName=测试, orderNum=null, createTime=Sun Aug 11 20:13:01 CST 2019, modifyTime=null)\"', '192.168.56.1', '2019-08-11 20:13:02', '');
INSERT INTO `t_log` VALUES (994, 'MrBird', '删除部门', 20, 'cc.mrbird.febs.system.controller.DeptController.deleteDepts()', ' deptIds: \"11,12\"', '192.168.56.1', '2019-08-11 20:13:07', '');
INSERT INTO `t_log` VALUES (995, 'MrBird', '删除菜单/按钮', 19, 'cc.mrbird.febs.system.controller.MenuController.deleteMenus()', ' menuIds: \"175,176\"', '192.168.56.1', '2019-08-11 20:13:13', '');
INSERT INTO `t_log` VALUES (996, 'MrBird', '新增菜单/按钮', 6, 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=177, parentId=0, menuName=21341, url=, perms=, icon=, type=0, orderNum=null, createTime=Sun Aug 11 20:16:08 CST 2019, modifyTime=null)\"', '192.168.56.1', '2019-08-11 20:16:08', '');
INSERT INTO `t_log` VALUES (997, 'MrBird', '删除菜单/按钮', 9, 'cc.mrbird.febs.system.controller.MenuController.deleteMenus()', ' menuIds: \"177\"', '192.168.56.1', '2019-08-11 20:16:46', '');
INSERT INTO `t_log` VALUES (998, 'MrBird', '新增菜单/按钮', 7, 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=178, parentId=0, menuName=我却认为人, url=, perms=, icon=, type=0, orderNum=null, createTime=Sun Aug 11 20:16:50 CST 2019, modifyTime=null)\"', '192.168.56.1', '2019-08-11 20:16:50', '');
INSERT INTO `t_log` VALUES (999, 'MrBird', '新增部门', 7, 'cc.mrbird.febs.system.controller.DeptController.addDept()', ' dept: \"Dept(deptId=13, parentId=0, deptName=发士大夫, orderNum=null, createTime=Sun Aug 11 20:16:55 CST 2019, modifyTime=null)\"', '192.168.56.1', '2019-08-11 20:16:56', '');
INSERT INTO `t_log` VALUES (1000, 'MrBird', '删除部门', 15, 'cc.mrbird.febs.system.controller.DeptController.deleteDepts()', ' deptIds: \"13\"', '192.168.56.1', '2019-08-11 20:17:00', '');
INSERT INTO `t_log` VALUES (1001, 'MrBird', '删除菜单/按钮', 15, 'cc.mrbird.febs.system.controller.MenuController.deleteMenus()', ' menuIds: \"178\"', '192.168.56.1', '2019-08-11 20:17:04', '');
INSERT INTO `t_log` VALUES (1002, 'MrBird', '新增菜单/按钮', 17, 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=175, parentId=2, menuName=Swagger文档, url=, perms=, icon=, type=0, orderNum=8, createTime=Sun Aug 18 17:25:35 CST 2019, modifyTime=null)\"', '127.0.0.1', '2019-08-18 17:25:36', '');
INSERT INTO `t_log` VALUES (1003, 'MrBird', '修改菜单/按钮', 16, 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=175, parentId=2, menuName=Swagger文档, url=/monitor/swagger, perms=swagger:view, icon=, type=0, orderNum=8, createTime=null, modifyTime=Sun Aug 18 17:25:58 CST 2019)\"', '127.0.0.1', '2019-08-18 17:25:59', '');
INSERT INTO `t_log` VALUES (1004, 'MrBird', '修改角色', 44, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=系统管理员, remark=系统管理员，拥有所有操作权限 ^_^, createTime=null, modifyTime=Sun Aug 18 17:26:19 CST 2019, menuIds=1,3,11,12,13,160,161,4,14,15,16,162,5,17,18,19,163,6,20,21,22,164,2,8,23,10,24,170,136,171,172,113,114,127,128,129,130,131,175,101,102,103,104,105,106,107,108,173,109,110,174,137,138,165,139,166,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125,167,168,169)\"', '127.0.0.1', '2019-08-18 17:26:19', '');
INSERT INTO `t_log` VALUES (1005, 'MrBird', '修改角色', 17, 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=2, roleName=注册账户, remark=注册账户，拥有查看，新增权限（新增用户除外）和导出Excel权限, createTime=null, modifyTime=Sun Aug 18 17:36:01 CST 2019, menuIds=1,3,161,4,14,162,5,17,163,6,20,164,2,8,10,170,136,172,113,114,127,128,129,130,131,175,101,102,173,109,174,137,138,139,115,132,133,135,134,126,159,116,117,119,120,121,122,123,118,125,167,168,169)\"', '127.0.0.1', '2019-08-18 17:36:02', '');
INSERT INTO `t_log` VALUES (1006, 'Scott', '新增菜单/按钮', 13, 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=176, parentId=0, menuName=1234, url=null, perms=null, icon=null, type=1, orderNum=null, createTime=Sun Aug 18 17:37:09 CST 2019, modifyTime=null)\"', '127.0.0.1', '2019-08-18 17:37:10', '');
INSERT INTO `t_log` VALUES (1007, 'Scott', '新增菜单/按钮', 14, 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=177, parentId=0, menuName=513241, url=, perms=, icon=, type=0, orderNum=null, createTime=Sun Aug 18 17:38:30 CST 2019, modifyTime=null)\"', '127.0.0.1', '2019-08-18 17:38:30', '');

-- ----------------------------
-- Table structure for t_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_login_log`;
CREATE TABLE `t_login_log`  (
                                `ID` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                `USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
                                `LOGIN_TIME` datetime(0) NOT NULL COMMENT '登录时间',
                                `LOCATION` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录地点',
                                `IP` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
                                `SYSTEM` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统',
                                `BROWSER` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器',
                                PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 70 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_login_log
-- ----------------------------
INSERT INTO `t_login_log` VALUES (64, 'mrbird', '2019-08-11 20:12:31', '', '192.168.56.1', 'Windows 10', 'Chrome 75');
INSERT INTO `t_login_log` VALUES (65, 'mrbird', '2019-08-18 17:10:27', '', '127.0.0.1', 'Windows 10', 'Chrome 75');
INSERT INTO `t_login_log` VALUES (66, 'scott', '2019-08-18 17:35:32', '', '127.0.0.1', 'Windows 10', 'Chrome 75');
INSERT INTO `t_login_log` VALUES (67, 'mrbird', '2019-08-18 17:35:53', '', '127.0.0.1', 'Windows 10', 'Chrome 75');
INSERT INTO `t_login_log` VALUES (68, 'scott', '2019-08-18 17:36:17', '', '127.0.0.1', 'Windows 10', 'Chrome 75');
INSERT INTO `t_login_log` VALUES (69, 'mrbird', '2019-08-18 17:39:24', '', '127.0.0.1', 'Windows 10', 'Chrome 75');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
                           `MENU_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单/按钮ID',
                           `PARENT_ID` bigint(20) NOT NULL COMMENT '上级菜单ID',
                           `MENU_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单/按钮名称',
                           `URL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
                           `PERMS` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '权限标识',
                           `ICON` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
                           `TYPE` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型 0菜单 1按钮',
                           `ORDER_NUM` bigint(20) NULL DEFAULT NULL COMMENT '排序',
                           `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
                           `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
                           PRIMARY KEY (`MENU_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 178 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, 0, '系统管理', NULL, NULL, 'layui-icon-setting', '0', 1, '2017-12-27 16:39:07', NULL);
INSERT INTO `t_menu` VALUES (2, 0, '系统监控', '', '', 'layui-icon-alert', '0', 2, '2017-12-27 16:45:51', '2019-06-13 11:20:40');
INSERT INTO `t_menu` VALUES (3, 1, '用户管理', '/system/user', 'user:view', 'layui-icon-meh', '0', 1, '2017-12-27 16:47:13', '2019-06-13 11:13:55');
INSERT INTO `t_menu` VALUES (4, 1, '角色管理', '/system/role', 'role:view', '', '0', 2, '2017-12-27 16:48:09', '2019-06-13 08:57:19');
INSERT INTO `t_menu` VALUES (5, 1, '菜单管理', '/system/menu', 'menu:view', '', '0', 3, '2017-12-27 16:48:57', '2019-06-13 08:57:34');
INSERT INTO `t_menu` VALUES (6, 1, '部门管理', '/system/dept', 'dept:view', '', '0', 4, '2017-12-27 16:57:33', '2019-06-14 19:56:00');
INSERT INTO `t_menu` VALUES (8, 2, '在线用户', '/monitor/online', 'online:view', '', '0', 1, '2017-12-27 16:59:33', '2019-06-13 14:30:31');
INSERT INTO `t_menu` VALUES (10, 2, '系统日志', '/monitor/log', 'log:view', '', '0', 2, '2017-12-27 17:00:50', '2019-06-13 14:30:37');
INSERT INTO `t_menu` VALUES (11, 3, '新增用户', NULL, 'user:add', NULL, '1', NULL, '2017-12-27 17:02:58', NULL);
INSERT INTO `t_menu` VALUES (12, 3, '修改用户', NULL, 'user:update', NULL, '1', NULL, '2017-12-27 17:04:07', NULL);
INSERT INTO `t_menu` VALUES (13, 3, '删除用户', NULL, 'user:delete', NULL, '1', NULL, '2017-12-27 17:04:58', NULL);
INSERT INTO `t_menu` VALUES (14, 4, '新增角色', NULL, 'role:add', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `t_menu` VALUES (15, 4, '修改角色', NULL, 'role:update', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `t_menu` VALUES (16, 4, '删除角色', NULL, 'role:delete', NULL, '1', NULL, '2017-12-27 17:06:38', NULL);
INSERT INTO `t_menu` VALUES (17, 5, '新增菜单', NULL, 'menu:add', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `t_menu` VALUES (18, 5, '修改菜单', NULL, 'menu:update', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `t_menu` VALUES (19, 5, '删除菜单', NULL, 'menu:delete', NULL, '1', NULL, '2017-12-27 17:08:02', NULL);
INSERT INTO `t_menu` VALUES (20, 6, '新增部门', NULL, 'dept:add', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `t_menu` VALUES (21, 6, '修改部门', NULL, 'dept:update', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `t_menu` VALUES (22, 6, '删除部门', NULL, 'dept:delete', NULL, '1', NULL, '2017-12-27 17:09:24', NULL);
INSERT INTO `t_menu` VALUES (23, 8, '踢出用户', NULL, 'user:kickout', NULL, '1', NULL, '2017-12-27 17:11:13', NULL);
INSERT INTO `t_menu` VALUES (24, 10, '删除日志', NULL, 'log:delete', NULL, '1', NULL, '2017-12-27 17:11:45', '2019-06-06 05:56:40');
INSERT INTO `t_menu` VALUES (101, 0, '任务调度', NULL, NULL, 'layui-icon-time-circle', '0', 3, '2018-02-24 15:52:57', NULL);
INSERT INTO `t_menu` VALUES (102, 101, '定时任务', '/job/job', 'job:view', '', '0', 1, '2018-02-24 15:53:53', '2018-04-25 09:05:12');
INSERT INTO `t_menu` VALUES (103, 102, '新增任务', NULL, 'job:add', NULL, '1', NULL, '2018-02-24 15:55:10', NULL);
INSERT INTO `t_menu` VALUES (104, 102, '修改任务', NULL, 'job:update', NULL, '1', NULL, '2018-02-24 15:55:53', NULL);
INSERT INTO `t_menu` VALUES (105, 102, '删除任务', NULL, 'job:delete', NULL, '1', NULL, '2018-02-24 15:56:18', NULL);
INSERT INTO `t_menu` VALUES (106, 102, '暂停任务', NULL, 'job:pause', NULL, '1', NULL, '2018-02-24 15:57:08', NULL);
INSERT INTO `t_menu` VALUES (107, 102, '恢复任务', NULL, 'job:resume', NULL, '1', NULL, '2018-02-24 15:58:21', NULL);
INSERT INTO `t_menu` VALUES (108, 102, '立即执行任务', NULL, 'job:run', NULL, '1', NULL, '2018-02-24 15:59:45', NULL);
INSERT INTO `t_menu` VALUES (109, 101, '调度日志', '/job/log', 'job:log:view', '', '0', 2, '2018-02-24 16:00:45', '2019-06-09 02:48:19');
INSERT INTO `t_menu` VALUES (110, 109, '删除日志', NULL, 'job:log:delete', NULL, '1', NULL, '2018-02-24 16:01:21', NULL);
INSERT INTO `t_menu` VALUES (113, 2, 'Redis监控', '/monitor/redis/info', 'redis:view', '', '0', 4, '2018-06-28 14:29:42', '2019-06-13 14:30:45');
INSERT INTO `t_menu` VALUES (114, 2, 'Redis终端', '/monitor/redis/terminal', 'redis:terminal:view', '', '0', 5, '2018-06-28 15:35:21', '2019-06-13 14:30:54');
INSERT INTO `t_menu` VALUES (115, 0, '其他模块', NULL, NULL, 'layui-icon-gift', '0', 5, '2019-05-27 10:18:07', NULL);
INSERT INTO `t_menu` VALUES (116, 115, 'Apex图表', '', '', NULL, '0', 2, '2019-05-27 10:21:35', NULL);
INSERT INTO `t_menu` VALUES (117, 116, '线性图表', '/others/apex/line', 'apex:line:view', NULL, '0', 1, '2019-05-27 10:24:49', NULL);
INSERT INTO `t_menu` VALUES (118, 115, '高德地图', '/others/map', 'map:view', '', '0', 3, '2019-05-27 17:13:12', '2019-06-12 15:33:00');
INSERT INTO `t_menu` VALUES (119, 116, '面积图表', '/others/apex/area', 'apex:area:view', NULL, '0', 2, '2019-05-27 18:49:22', NULL);
INSERT INTO `t_menu` VALUES (120, 116, '柱形图表', '/others/apex/column', 'apex:column:view', NULL, '0', 3, '2019-05-27 18:51:33', NULL);
INSERT INTO `t_menu` VALUES (121, 116, '雷达图表', '/others/apex/radar', 'apex:radar:view', NULL, '0', 4, '2019-05-27 18:56:05', NULL);
INSERT INTO `t_menu` VALUES (122, 116, '条形图表', '/others/apex/bar', 'apex:bar:view', NULL, '0', 5, '2019-05-27 18:57:02', NULL);
INSERT INTO `t_menu` VALUES (123, 116, '混合图表', '/others/apex/mix', 'apex:mix:view', '', '0', 6, '2019-05-27 18:58:04', '2019-06-06 02:55:23');
INSERT INTO `t_menu` VALUES (125, 115, '导入导出', '/others/eximport', 'others:eximport:view', '', '0', 4, '2019-05-27 19:01:57', '2019-06-13 01:20:14');
INSERT INTO `t_menu` VALUES (126, 132, '系统图标', '/others/febs/icon', 'febs:icons:view', '', '0', 4, '2019-05-27 19:03:18', '2019-06-06 03:05:26');
INSERT INTO `t_menu` VALUES (127, 2, '请求追踪', '/monitor/httptrace', 'httptrace:view', '', '0', 6, '2019-05-27 19:06:38', '2019-06-13 14:36:43');
INSERT INTO `t_menu` VALUES (128, 2, '系统信息', NULL, NULL, NULL, '0', 7, '2019-05-27 19:08:23', NULL);
INSERT INTO `t_menu` VALUES (129, 128, 'JVM信息', '/monitor/jvm', 'jvm:view', '', '0', 1, '2019-05-27 19:08:50', '2019-06-13 14:36:51');
INSERT INTO `t_menu` VALUES (130, 128, 'Tomcat信息', '/monitor/tomcat', 'tomcat:view', '', '0', 2, '2019-05-27 19:09:26', '2019-06-13 14:36:57');
INSERT INTO `t_menu` VALUES (131, 128, '服务器信息', '/monitor/server', 'server:view', '', '0', 3, '2019-05-27 19:10:07', '2019-06-13 14:37:04');
INSERT INTO `t_menu` VALUES (132, 115, 'FEBS组件', '', '', NULL, '0', 1, '2019-05-27 19:13:54', NULL);
INSERT INTO `t_menu` VALUES (133, 132, '表单组件', '/others/febs/form', 'febs:form:view', NULL, '0', 1, '2019-05-27 19:14:45', NULL);
INSERT INTO `t_menu` VALUES (134, 132, 'FEBS工具', '/others/febs/tools', 'febs:tools:view', '', '0', 3, '2019-05-29 10:11:22', '2019-06-12 13:21:27');
INSERT INTO `t_menu` VALUES (135, 132, '表单组合', '/others/febs/form/group', 'febs:formgroup:view', NULL, '0', 2, '2019-05-29 10:16:19', NULL);
INSERT INTO `t_menu` VALUES (136, 2, '登录日志', '/monitor/loginlog', 'loginlog:view', '', '0', 3, '2019-05-29 15:56:15', '2019-06-13 14:35:56');
INSERT INTO `t_menu` VALUES (137, 0, '代码生成', '', NULL, 'layui-icon-verticalright', '0', 4, '2019-06-03 15:35:58', NULL);
INSERT INTO `t_menu` VALUES (138, 137, '生成配置', '/generator/configure', 'generator:configure:view', NULL, '0', 1, '2019-06-03 15:38:36', NULL);
INSERT INTO `t_menu` VALUES (139, 137, '代码生成', '/generator/generator', 'generator:view', '', '0', 2, '2019-06-03 15:39:15', '2019-06-13 14:31:38');
INSERT INTO `t_menu` VALUES (159, 132, '其他组件', '/others/febs/others', 'others:febs:others', '', '0', 5, '2019-06-12 07:51:08', '2019-06-12 07:51:40');
INSERT INTO `t_menu` VALUES (160, 3, '密码重置', NULL, 'user:password:reset', NULL, '1', NULL, '2019-06-13 08:40:13', NULL);
INSERT INTO `t_menu` VALUES (161, 3, '导出Excel', NULL, 'user:export', NULL, '1', NULL, '2019-06-13 08:40:34', NULL);
INSERT INTO `t_menu` VALUES (162, 4, '导出Excel', NULL, 'role:export', NULL, '1', NULL, '2019-06-13 14:29:00', '2019-06-13 14:29:11');
INSERT INTO `t_menu` VALUES (163, 5, '导出Excel', NULL, 'menu:export', NULL, '1', NULL, '2019-06-13 14:29:32', NULL);
INSERT INTO `t_menu` VALUES (164, 6, '导出Excel', NULL, 'dept:export', NULL, '1', NULL, '2019-06-13 14:29:59', NULL);
INSERT INTO `t_menu` VALUES (165, 138, '修改配置', NULL, 'generator:configure:update', NULL, '1', NULL, '2019-06-13 14:32:09', '2019-06-13 14:32:20');
INSERT INTO `t_menu` VALUES (166, 139, '生成代码', NULL, 'generator:generate', NULL, '1', NULL, '2019-06-13 14:32:51', NULL);
INSERT INTO `t_menu` VALUES (167, 125, '模板下载', NULL, 'eximport:template', NULL, '1', NULL, '2019-06-13 14:33:37', NULL);
INSERT INTO `t_menu` VALUES (168, 125, '导出Excel', NULL, 'eximport:export', NULL, '1', NULL, '2019-06-13 14:33:57', NULL);
INSERT INTO `t_menu` VALUES (169, 125, '导入Excel', NULL, 'eximport:import', NULL, '1', NULL, '2019-06-13 14:34:19', NULL);
INSERT INTO `t_menu` VALUES (170, 10, '导出Excel', NULL, 'log:export', NULL, '1', NULL, '2019-06-13 14:34:55', NULL);
INSERT INTO `t_menu` VALUES (171, 136, '删除日志', NULL, 'loginlog:delete', NULL, '1', NULL, '2019-06-13 14:35:27', '2019-06-13 14:36:08');
INSERT INTO `t_menu` VALUES (172, 136, '导出Excel', NULL, 'loginlog:export', NULL, '1', NULL, '2019-06-13 14:36:26', NULL);
INSERT INTO `t_menu` VALUES (173, 102, '导出Excel', NULL, 'job:export', NULL, '1', NULL, '2019-06-13 14:37:25', NULL);
INSERT INTO `t_menu` VALUES (174, 109, '导出Excel', NULL, 'job:log:export', NULL, '1', NULL, '2019-06-13 14:37:46', '2019-06-13 14:38:02');
INSERT INTO `t_menu` VALUES (175, 2, 'Swagger文档', '/monitor/swagger', 'swagger:view', '', '0', 8, '2019-08-18 17:25:36', '2019-08-18 17:25:59');
INSERT INTO `t_menu` VALUES (176, 0, '1234', NULL, NULL, NULL, '1', NULL, '2019-08-18 17:37:10', NULL);
INSERT INTO `t_menu` VALUES (177, 0, '513241', '', '', '', '0', NULL, '2019-08-18 17:38:30', NULL);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
                           `ROLE_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
                           `ROLE_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
                           `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
                           `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
                           `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
                           PRIMARY KEY (`ROLE_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 81 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '系统管理员', '系统管理员，拥有所有操作权限 ^_^', '2019-06-14 16:23:11', '2019-08-18 17:26:19');
INSERT INTO `t_role` VALUES (2, '注册账户', '注册账户，拥有查看，新增权限（新增用户除外）和导出Excel权限', '2019-06-14 16:00:15', '2019-08-18 17:36:02');
INSERT INTO `t_role` VALUES (77, 'Redis监控员', '负责Redis模块', '2019-06-14 20:49:22', NULL);
INSERT INTO `t_role` VALUES (78, '系统监控员', '负责整个系统监控模块', '2019-06-14 20:50:07', NULL);
INSERT INTO `t_role` VALUES (79, '跑批人员', '负责任务调度跑批模块', '2019-06-14 20:51:02', NULL);
INSERT INTO `t_role` VALUES (80, '开发人员', '拥有代码生成模块的权限', '2019-06-14 20:51:26', NULL);

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
                                `ROLE_ID` bigint(20) NOT NULL COMMENT '角色ID',
                                `MENU_ID` bigint(20) NOT NULL COMMENT '菜单/按钮ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES (77, 2);
INSERT INTO `t_role_menu` VALUES (77, 113);
INSERT INTO `t_role_menu` VALUES (77, 114);
INSERT INTO `t_role_menu` VALUES (78, 2);
INSERT INTO `t_role_menu` VALUES (78, 8);
INSERT INTO `t_role_menu` VALUES (78, 23);
INSERT INTO `t_role_menu` VALUES (78, 10);
INSERT INTO `t_role_menu` VALUES (78, 24);
INSERT INTO `t_role_menu` VALUES (78, 170);
INSERT INTO `t_role_menu` VALUES (78, 136);
INSERT INTO `t_role_menu` VALUES (78, 171);
INSERT INTO `t_role_menu` VALUES (78, 172);
INSERT INTO `t_role_menu` VALUES (78, 113);
INSERT INTO `t_role_menu` VALUES (78, 114);
INSERT INTO `t_role_menu` VALUES (78, 127);
INSERT INTO `t_role_menu` VALUES (78, 128);
INSERT INTO `t_role_menu` VALUES (78, 129);
INSERT INTO `t_role_menu` VALUES (78, 130);
INSERT INTO `t_role_menu` VALUES (78, 131);
INSERT INTO `t_role_menu` VALUES (79, 101);
INSERT INTO `t_role_menu` VALUES (79, 102);
INSERT INTO `t_role_menu` VALUES (79, 103);
INSERT INTO `t_role_menu` VALUES (79, 104);
INSERT INTO `t_role_menu` VALUES (79, 105);
INSERT INTO `t_role_menu` VALUES (79, 106);
INSERT INTO `t_role_menu` VALUES (79, 107);
INSERT INTO `t_role_menu` VALUES (79, 108);
INSERT INTO `t_role_menu` VALUES (79, 173);
INSERT INTO `t_role_menu` VALUES (79, 109);
INSERT INTO `t_role_menu` VALUES (79, 110);
INSERT INTO `t_role_menu` VALUES (79, 174);
INSERT INTO `t_role_menu` VALUES (80, 137);
INSERT INTO `t_role_menu` VALUES (80, 138);
INSERT INTO `t_role_menu` VALUES (80, 165);
INSERT INTO `t_role_menu` VALUES (80, 139);
INSERT INTO `t_role_menu` VALUES (80, 166);
INSERT INTO `t_role_menu` VALUES (1, 1);
INSERT INTO `t_role_menu` VALUES (1, 3);
INSERT INTO `t_role_menu` VALUES (1, 11);
INSERT INTO `t_role_menu` VALUES (1, 12);
INSERT INTO `t_role_menu` VALUES (1, 13);
INSERT INTO `t_role_menu` VALUES (1, 160);
INSERT INTO `t_role_menu` VALUES (1, 161);
INSERT INTO `t_role_menu` VALUES (1, 4);
INSERT INTO `t_role_menu` VALUES (1, 14);
INSERT INTO `t_role_menu` VALUES (1, 15);
INSERT INTO `t_role_menu` VALUES (1, 16);
INSERT INTO `t_role_menu` VALUES (1, 162);
INSERT INTO `t_role_menu` VALUES (1, 5);
INSERT INTO `t_role_menu` VALUES (1, 17);
INSERT INTO `t_role_menu` VALUES (1, 18);
INSERT INTO `t_role_menu` VALUES (1, 19);
INSERT INTO `t_role_menu` VALUES (1, 163);
INSERT INTO `t_role_menu` VALUES (1, 6);
INSERT INTO `t_role_menu` VALUES (1, 20);
INSERT INTO `t_role_menu` VALUES (1, 21);
INSERT INTO `t_role_menu` VALUES (1, 22);
INSERT INTO `t_role_menu` VALUES (1, 164);
INSERT INTO `t_role_menu` VALUES (1, 2);
INSERT INTO `t_role_menu` VALUES (1, 8);
INSERT INTO `t_role_menu` VALUES (1, 23);
INSERT INTO `t_role_menu` VALUES (1, 10);
INSERT INTO `t_role_menu` VALUES (1, 24);
INSERT INTO `t_role_menu` VALUES (1, 170);
INSERT INTO `t_role_menu` VALUES (1, 136);
INSERT INTO `t_role_menu` VALUES (1, 171);
INSERT INTO `t_role_menu` VALUES (1, 172);
INSERT INTO `t_role_menu` VALUES (1, 113);
INSERT INTO `t_role_menu` VALUES (1, 114);
INSERT INTO `t_role_menu` VALUES (1, 127);
INSERT INTO `t_role_menu` VALUES (1, 128);
INSERT INTO `t_role_menu` VALUES (1, 129);
INSERT INTO `t_role_menu` VALUES (1, 130);
INSERT INTO `t_role_menu` VALUES (1, 131);
INSERT INTO `t_role_menu` VALUES (1, 175);
INSERT INTO `t_role_menu` VALUES (1, 101);
INSERT INTO `t_role_menu` VALUES (1, 102);
INSERT INTO `t_role_menu` VALUES (1, 103);
INSERT INTO `t_role_menu` VALUES (1, 104);
INSERT INTO `t_role_menu` VALUES (1, 105);
INSERT INTO `t_role_menu` VALUES (1, 106);
INSERT INTO `t_role_menu` VALUES (1, 107);
INSERT INTO `t_role_menu` VALUES (1, 108);
INSERT INTO `t_role_menu` VALUES (1, 173);
INSERT INTO `t_role_menu` VALUES (1, 109);
INSERT INTO `t_role_menu` VALUES (1, 110);
INSERT INTO `t_role_menu` VALUES (1, 174);
INSERT INTO `t_role_menu` VALUES (1, 137);
INSERT INTO `t_role_menu` VALUES (1, 138);
INSERT INTO `t_role_menu` VALUES (1, 165);
INSERT INTO `t_role_menu` VALUES (1, 139);
INSERT INTO `t_role_menu` VALUES (1, 166);
INSERT INTO `t_role_menu` VALUES (1, 115);
INSERT INTO `t_role_menu` VALUES (1, 132);
INSERT INTO `t_role_menu` VALUES (1, 133);
INSERT INTO `t_role_menu` VALUES (1, 135);
INSERT INTO `t_role_menu` VALUES (1, 134);
INSERT INTO `t_role_menu` VALUES (1, 126);
INSERT INTO `t_role_menu` VALUES (1, 159);
INSERT INTO `t_role_menu` VALUES (1, 116);
INSERT INTO `t_role_menu` VALUES (1, 117);
INSERT INTO `t_role_menu` VALUES (1, 119);
INSERT INTO `t_role_menu` VALUES (1, 120);
INSERT INTO `t_role_menu` VALUES (1, 121);
INSERT INTO `t_role_menu` VALUES (1, 122);
INSERT INTO `t_role_menu` VALUES (1, 123);
INSERT INTO `t_role_menu` VALUES (1, 118);
INSERT INTO `t_role_menu` VALUES (1, 125);
INSERT INTO `t_role_menu` VALUES (1, 167);
INSERT INTO `t_role_menu` VALUES (1, 168);
INSERT INTO `t_role_menu` VALUES (1, 169);
INSERT INTO `t_role_menu` VALUES (2, 1);
INSERT INTO `t_role_menu` VALUES (2, 3);
INSERT INTO `t_role_menu` VALUES (2, 161);
INSERT INTO `t_role_menu` VALUES (2, 4);
INSERT INTO `t_role_menu` VALUES (2, 14);
INSERT INTO `t_role_menu` VALUES (2, 162);
INSERT INTO `t_role_menu` VALUES (2, 5);
INSERT INTO `t_role_menu` VALUES (2, 17);
INSERT INTO `t_role_menu` VALUES (2, 163);
INSERT INTO `t_role_menu` VALUES (2, 6);
INSERT INTO `t_role_menu` VALUES (2, 20);
INSERT INTO `t_role_menu` VALUES (2, 164);
INSERT INTO `t_role_menu` VALUES (2, 2);
INSERT INTO `t_role_menu` VALUES (2, 8);
INSERT INTO `t_role_menu` VALUES (2, 10);
INSERT INTO `t_role_menu` VALUES (2, 170);
INSERT INTO `t_role_menu` VALUES (2, 136);
INSERT INTO `t_role_menu` VALUES (2, 172);
INSERT INTO `t_role_menu` VALUES (2, 113);
INSERT INTO `t_role_menu` VALUES (2, 114);
INSERT INTO `t_role_menu` VALUES (2, 127);
INSERT INTO `t_role_menu` VALUES (2, 128);
INSERT INTO `t_role_menu` VALUES (2, 129);
INSERT INTO `t_role_menu` VALUES (2, 130);
INSERT INTO `t_role_menu` VALUES (2, 131);
INSERT INTO `t_role_menu` VALUES (2, 175);
INSERT INTO `t_role_menu` VALUES (2, 101);
INSERT INTO `t_role_menu` VALUES (2, 102);
INSERT INTO `t_role_menu` VALUES (2, 173);
INSERT INTO `t_role_menu` VALUES (2, 109);
INSERT INTO `t_role_menu` VALUES (2, 174);
INSERT INTO `t_role_menu` VALUES (2, 137);
INSERT INTO `t_role_menu` VALUES (2, 138);
INSERT INTO `t_role_menu` VALUES (2, 139);
INSERT INTO `t_role_menu` VALUES (2, 115);
INSERT INTO `t_role_menu` VALUES (2, 132);
INSERT INTO `t_role_menu` VALUES (2, 133);
INSERT INTO `t_role_menu` VALUES (2, 135);
INSERT INTO `t_role_menu` VALUES (2, 134);
INSERT INTO `t_role_menu` VALUES (2, 126);
INSERT INTO `t_role_menu` VALUES (2, 159);
INSERT INTO `t_role_menu` VALUES (2, 116);
INSERT INTO `t_role_menu` VALUES (2, 117);
INSERT INTO `t_role_menu` VALUES (2, 119);
INSERT INTO `t_role_menu` VALUES (2, 120);
INSERT INTO `t_role_menu` VALUES (2, 121);
INSERT INTO `t_role_menu` VALUES (2, 122);
INSERT INTO `t_role_menu` VALUES (2, 123);
INSERT INTO `t_role_menu` VALUES (2, 118);
INSERT INTO `t_role_menu` VALUES (2, 125);
INSERT INTO `t_role_menu` VALUES (2, 167);
INSERT INTO `t_role_menu` VALUES (2, 168);
INSERT INTO `t_role_menu` VALUES (2, 169);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
                           `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                           `USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
                           `PASSWORD` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
                           `DEPT_ID` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
                           `EMAIL` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
                           `MOBILE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
                           `STATUS` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态 0锁定 1有效',
                           `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
                           `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
                           `LAST_LOGIN_TIME` datetime(0) NULL DEFAULT NULL COMMENT '最近访问时间',
                           `SSEX` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别 0男 1女 2保密',
                           `IS_TAB` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否开启tab，0关闭 1开启',
                           `THEME` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题',
                           `AVATAR` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
                           `DESCRIPTION` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
                           PRIMARY KEY (`USER_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'MrBird', 'cb62ad1497597283961545d608f80241', 1, 'mrbird@qq.com', '17788888888', '1', '2019-06-14 20:39:22', '2019-06-14 20:44:42', '2019-08-18 17:39:25', '0', '1', 'white', 'cnrhVkzwxjPwAaCfPbdc.png', '我是帅比作者。');
INSERT INTO `t_user` VALUES (2, 'Scott', '1d685729d113cfd03872f154939bee1c', 10, 'scott@gmail.com', '17722222222', '1', '2019-06-14 20:55:53', '2019-06-14 21:05:43', '2019-08-18 17:36:18', '0', '1', 'black', 'gaOngJwsRYRaVAuXXcmB.png', '我是scott。');
INSERT INTO `t_user` VALUES (3, 'Reina', '1461afff857c02afbfb768aa3771503d', 4, 'Reina@hotmail.com', '17711111111', '0', '2019-06-14 21:07:38', '2019-06-14 21:09:06', '2019-06-14 21:08:26', '1', '1', 'black', '5997fedcc7bd4cffbd350b40d1b5b987.jpg', '由于公款私用，已被封禁。');
INSERT INTO `t_user` VALUES (4, 'Micaela', '9f2daa2c7bed6870fcbb5b9a51d6300e', 10, 'Micaela@163.com', '17733333333', '1', '2019-06-14 21:10:13', '2019-06-14 21:11:26', '2019-06-14 21:10:37', '0', '0', 'white', '20180414165909.jpg', '我叫米克拉');
INSERT INTO `t_user` VALUES (5, 'Jana', '176679b77b3c3e352bd3b30ddc81083e', 8, 'Jana@126.com', '17744444444', '1', '2019-06-14 21:12:16', '2019-06-14 21:12:52', '2019-06-14 21:12:32', '1', '1', 'white', '20180414165821.jpg', '大家好，我叫简娜');
INSERT INTO `t_user` VALUES (6, 'Georgie', 'dffc683378cdaa015a0ee9554c532225', 3, 'Georgie@qq.com', '17766666666', '0', '2019-06-14 21:15:09', '2019-06-14 21:16:25', '2019-06-14 21:16:11', '2', '0', 'black', 'BiazfanxmamNRoxxVxka.png', '生产执行rm -rf *，账号封禁T T');
INSERT INTO `t_user` VALUES (7, 'Margot', '31379841b9f4bfde22b8b40471e9a6ce', 9, 'Margot@qq.com', '13444444444', '1', '2019-06-14 21:17:53', '2019-06-14 21:18:59', '2019-06-14 21:18:07', '1', '1', 'white', '20180414165834.jpg', '大家好我叫玛戈');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
                                `USER_ID` bigint(20) NOT NULL COMMENT '用户ID',
                                `ROLE_ID` bigint(20) NOT NULL COMMENT '角色ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1);
INSERT INTO `t_user_role` VALUES (2, 2);
INSERT INTO `t_user_role` VALUES (3, 77);
INSERT INTO `t_user_role` VALUES (4, 78);
INSERT INTO `t_user_role` VALUES (5, 79);
INSERT INTO `t_user_role` VALUES (6, 80);
INSERT INTO `t_user_role` VALUES (7, 78);
INSERT INTO `t_user_role` VALUES (7, 79);
INSERT INTO `t_user_role` VALUES (7, 80);

SET FOREIGN_KEY_CHECKS = 1;
