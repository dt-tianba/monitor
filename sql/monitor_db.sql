/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : monitor_db

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 17/01/2020 17:45:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for m_index
-- ----------------------------
DROP TABLE IF EXISTS `m_index`;
CREATE TABLE `m_index` (
  `id` bigint(11) NOT NULL COMMENT '主键id',
  `parent_id` bigint(11) DEFAULT NULL COMMENT '父id',
  `item_name` varchar(127) NOT NULL COMMENT '品类名称',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否展示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_index
-- ----------------------------
BEGIN;
INSERT INTO `m_index` VALUES (1, NULL, '概览', 1);
INSERT INTO `m_index` VALUES (2, NULL, '全链路监控', 1);
INSERT INTO `m_index` VALUES (3, NULL, '资源监控', 1);
INSERT INTO `m_index` VALUES (4, NULL, '网络监控', 1);
INSERT INTO `m_index` VALUES (5, NULL, 'OGG监控', 1);
INSERT INTO `m_index` VALUES (6, NULL, 'ETL监控', 1);
INSERT INTO `m_index` VALUES (7, NULL, '数据供给监控', 1);
INSERT INTO `m_index` VALUES (8, NULL, '告警中心', 1);
INSERT INTO `m_index` VALUES (9, NULL, '日志中心', 1);
INSERT INTO `m_index` VALUES (10, NULL, '系统设置', 1);
COMMIT;

-- ----------------------------
-- Table structure for m_prometheus_data
-- ----------------------------
DROP TABLE IF EXISTS `m_prometheus_data`;
CREATE TABLE `m_prometheus_data` (
  `id` bigint(11) NOT NULL,
  `url_name` varchar(255) DEFAULT NULL,
  `url_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_prometheus_data
-- ----------------------------
BEGIN;
INSERT INTO `m_prometheus_data` VALUES (1, 'cpuData', 'http://192.168.8.115:9090/api/v1/query?query=node_cpu_scaling_frequency_hertz%7Bjob%3D%22linux%22%7D+%5B5m%5D');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
