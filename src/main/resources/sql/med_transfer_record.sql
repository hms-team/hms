/*
Navicat MySQL Data Transfer

Source Server         : HospitalManagement
Source Server Version : 50633
Source Host           : localhost:3306
Source Database       : hm

Target Server Type    : MYSQL
Target Server Version : 50633
File Encoding         : 65001

Date: 2016-11-13 22:40:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for med_transfer_record
-- ----------------------------
DROP TABLE IF EXISTS `med_transfer_record`;
CREATE TABLE `med_transfer_record` (
  `med_id` int(11) NOT NULL COMMENT '药品ID',
  `source` varchar(255) DEFAULT NULL COMMENT '药品来源',
  `destination` varchar(255) DEFAULT NULL COMMENT '目的地',
  `amount` int(11) DEFAULT NULL COMMENT '迁移数量',
  `date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of med_transfer_record
-- ----------------------------
INSERT INTO `med_transfer_record` VALUES ('3', 'OTHERS', 'WARE_HS', '1', '2016-10-31 12:57:54');
INSERT INTO `med_transfer_record` VALUES ('3', 'OTHERS', 'WARE_HS', '5', '2016-10-31 12:59:56');
INSERT INTO `med_transfer_record` VALUES ('4', 'OTHERS', 'WARE_HS', '6', '2016-10-31 13:00:00');
INSERT INTO `med_transfer_record` VALUES ('3', 'OTHERS', 'WARE_HS', '1', '2016-10-31 13:01:40');
INSERT INTO `med_transfer_record` VALUES ('5', 'OTHERS', 'WARE_HS', '89', '2016-10-31 13:02:42');
INSERT INTO `med_transfer_record` VALUES ('3', 'OTHERS', 'WARE_HS', '1', '2016-11-01 11:39:19');
INSERT INTO `med_transfer_record` VALUES ('4', 'OTHERS', 'WARE_HS', '34', '2016-11-01 11:40:23');
INSERT INTO `med_transfer_record` VALUES ('3', 'OTHERS', 'WARE_HS', '2', '2016-11-02 12:09:40');
INSERT INTO `med_transfer_record` VALUES ('3', 'OTHERS', 'WARE_HS', '0', '2016-11-03 08:47:47');
INSERT INTO `med_transfer_record` VALUES ('3', 'OTHERS', 'WARE_HS', '1', '2016-11-12 00:56:48');
INSERT INTO `med_transfer_record` VALUES ('4', 'OTHERS', 'WARE_HS', '3', '2016-11-12 00:56:52');
INSERT INTO `med_transfer_record` VALUES ('3', 'OTHERS', 'WARE_HS', '1', '2016-11-12 02:09:33');
INSERT INTO `med_transfer_record` VALUES ('4', 'OTHERS', 'WARE_HS', '2', '2016-11-12 10:16:45');
INSERT INTO `med_transfer_record` VALUES ('8', 'OTHERS', 'WARE_HS', '5', '2016-11-12 13:22:02');
INSERT INTO `med_transfer_record` VALUES ('3', 'WARE_HS', 'STORE_HS', '2', '2016-11-12 18:55:23');
INSERT INTO `med_transfer_record` VALUES ('3', 'WARE_HS', 'STORE_HS', '3', '2016-11-12 18:55:33');
INSERT INTO `med_transfer_record` VALUES ('5', 'WARE_HS', 'STORE_HS', '4', '2016-11-12 18:57:21');
INSERT INTO `med_transfer_record` VALUES ('23', 'OTHERS', 'WARE_HS', '10', '2016-11-12 19:11:23');
INSERT INTO `med_transfer_record` VALUES ('23', 'WARE_HS', 'STORE_HS', '5', '2016-11-12 19:11:39');
INSERT INTO `med_transfer_record` VALUES ('3', 'STORE_HS', 'CONSUME', '1', '2016-11-13 21:09:18');
INSERT INTO `med_transfer_record` VALUES ('3', 'OTHERS', 'WARE_HS', '0', '2016-11-13 21:23:55');
INSERT INTO `med_transfer_record` VALUES ('3', 'OTHERS', 'WARE_HS', '2', '2016-11-13 21:25:40');
