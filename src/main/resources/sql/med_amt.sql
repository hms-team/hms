/*
Navicat MySQL Data Transfer

Source Server         : HospitalManagement
Source Server Version : 50633
Source Host           : localhost:3306
Source Database       : hm

Target Server Type    : MYSQL
Target Server Version : 50633
File Encoding         : 65001

Date: 2016-11-13 22:40:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for med_amt
-- ----------------------------
DROP TABLE IF EXISTS `med_amt`;
CREATE TABLE `med_amt` (
  `med_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '药品自增ID',
  `name` varchar(255) NOT NULL COMMENT '药品名称',
  `type` varchar(255) DEFAULT NULL COMMENT '药品类型，例如是药品还是器械',
  `amount_warehouse` int(11) NOT NULL DEFAULT '0' COMMENT '药库药品数量',
  `amount_storehouse` int(11) NOT NULL DEFAULT '0' COMMENT '药房药品数量',
  `unit` varchar(255) DEFAULT NULL COMMENT '数量单位',
  `expiry_date` date DEFAULT NULL COMMENT '药品有效期',
  `place` varchar(255) DEFAULT NULL COMMENT '药品位置',
  `price` decimal(10,2) unsigned zerofill DEFAULT NULL COMMENT '药品价格',
  PRIMARY KEY (`med_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of med_amt
-- ----------------------------
INSERT INTO `med_amt` VALUES ('3', '消旋山莨菪碱片', '药品', '12', '4', '盒', '2017-01-01', 'D3', '00000007.80');
INSERT INTO `med_amt` VALUES ('4', '999感冒灵颗粒', '药品', '49', '0', '盒', '2017-07-01', 'G1、D6', '00000010.60');
INSERT INTO `med_amt` VALUES ('5', '999皮炎平', '药品', '90', '4', '盒', '2018-12-01', 'C6', '00000008.85');
INSERT INTO `med_amt` VALUES ('7', '999感冒灵颗粒', 'drug', '4', '0', '盒', '2017-07-01', 'G1、D6', '00000010.65');
INSERT INTO `med_amt` VALUES ('8', '999感冒灵颗粒', 'drug', '9', '0', '盒', '2017-07-01', 'G1、D6', '00000010.65');
INSERT INTO `med_amt` VALUES ('9', '999感冒灵颗粒', 'drug', '4', '0', '盒', '2017-07-01', 'G1、D6', '00000010.65');
INSERT INTO `med_amt` VALUES ('10', '葡萄糖', '药品', '1234', '234', '盒', '2016-10-06', 'A3', '00000002.34');
INSERT INTO `med_amt` VALUES ('11', '庆大霉素', '药品', '1', '2', '盒', '2016-10-07', 'C3', '00000002.45');
INSERT INTO `med_amt` VALUES ('12', '葡萄糖', '药品', '23', '12', '支', '2016-10-08', 'A5', '00000001.34');
INSERT INTO `med_amt` VALUES ('13', '青霉素', '药品', '2', '1', '支', '2016-10-06', 'J33', '00000005.40');
INSERT INTO `med_amt` VALUES ('14', '青霉素', '药品', '23', '45', '支', '2016-10-06', '34', '00000002.30');
INSERT INTO `med_amt` VALUES ('15', '青霉素', '药品', '12', '23', '盒', '2016-10-07', '23', '00000002.30');
INSERT INTO `med_amt` VALUES ('16', '青霉素', '药品', '12', '23', '盒', '2016-10-15', 'A3', '00000012.34');
INSERT INTO `med_amt` VALUES ('17', '青霉素', '药品', '12', '34', '箱', '2016-10-13', 'A4', '00000023.45');
INSERT INTO `med_amt` VALUES ('18', '青霉素', '药品', '12', '23', '盒', '2016-10-07', 'A3', '00000002.34');
INSERT INTO `med_amt` VALUES ('19', '青霉素', '药品', '11', '12', '盒', '2016-10-07', 'A2', '00000012.00');
INSERT INTO `med_amt` VALUES ('20', '葡萄糖', '药品', '12', '12', '瓶', '2016-10-05', 'A3', '00000123.00');
INSERT INTO `med_amt` VALUES ('22', '胃得宁', '药品', '123', '12', '瓶', '2016-10-14', 'C12', '00000001.23');
INSERT INTO `med_amt` VALUES ('23', '减肥药', '药品', '5', '5', '盒', '2016-11-12', 'A4', '00000018.88');
