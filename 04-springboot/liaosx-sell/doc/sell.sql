/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : sell

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2019-09-05 09:53:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `hibernate_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('6');

-- ----------------------------
-- Table structure for `order_detail`
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `detail_id` varchar(64) NOT NULL COMMENT '订单详情编号',
  `order_id` varchar(64) NOT NULL COMMENT '订单编号',
  `product_id` varchar(64) NOT NULL COMMENT '商品编号',
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '商品价格',
  `product_quantity` int(11) NOT NULL COMMENT '商品数量',
  `product_icon` varchar(521) DEFAULT NULL COMMENT '商品小图',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`detail_id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单详情表';

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('46665eb3-7527-4e92-bd24-f0c0abc08893', 'd6839891-2ecb-4843-b5a9-cdb88bdbfe8a', '011d08c6-dd8c-445d-b72d-a50d98488b19', '美味蜡鸡腿', '15.00', '1', '/img/mwljt.jpg', '2019-09-04 18:21:10', '2019-09-04 18:21:10');
INSERT INTO `order_detail` VALUES ('5a716dc2-e39a-4d33-b490-5dd0efde5266', 'd6839891-2ecb-4843-b5a9-cdb88bdbfe8a', '4f6ad41d-93de-4dc3-89d9-1a76d4b07576', '红烧鸡腿', '8.00', '1', '/img/hsjt.jpg', '2019-09-04 18:04:08', '2019-09-04 18:09:01');
INSERT INTO `order_detail` VALUES ('720ef0c7-6917-4494-b1ac-93fc8d137dfa', 'd6839891-2ecb-4843-b5a9-cdb88bdbfe8a', '1a3dc37d-2086-4b66-b60b-5c0fdfdb1d41', '土豆牛肉', '26.00', '1', '/img/tdnr.jpg', '2019-09-04 18:12:39', '2019-09-04 18:12:39');
INSERT INTO `order_detail` VALUES ('949409d9-5e2d-49dc-9331-de434e7c5b12', '9a041e61-1792-4b16-8ff6-d11e28bbe104', '1a3dc37d-2086-4b66-b60b-5c0fdfdb1d41', '土豆牛肉', '26.00', '1', '/img/tdnr.jpg', '2019-09-04 18:12:39', '2019-09-05 09:41:08');
INSERT INTO `order_detail` VALUES ('ef9ad252-072f-4bf9-bae1-c65c47aace25', '9a041e61-1792-4b16-8ff6-d11e28bbe104', '4f6ad41d-93de-4dc3-89d9-1a76d4b07576', '红烧鸡腿', '8.00', '1', '/img/hsjt.jpg', '2019-09-04 18:04:08', '2019-09-05 09:41:08');

-- ----------------------------
-- Table structure for `order_master`
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master` (
  `order_id` varchar(64) NOT NULL COMMENT '订单编号',
  `buyer_name` varchar(32) NOT NULL COMMENT '买家名字',
  `buyer_phone` varchar(32) NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) NOT NULL COMMENT '买家地址',
  `buyer_openid` varchar(64) NOT NULL COMMENT '买家微信openid',
  `order_amount` decimal(8,2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态，默认0新下单',
  `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态，默认0未支付',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`order_id`),
  KEY `idx_buyer_openid` (`buyer_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单主表';

-- ----------------------------
-- Records of order_master
-- ----------------------------
INSERT INTO `order_master` VALUES ('9a041e61-1792-4b16-8ff6-d11e28bbe104', '张三', '18868822111', '慕课网总部', 'oivFXw120Jan3DQBaLsn1wgxMsjs', '34.00', '0', '0', '2019-09-05 09:43:06', null);
INSERT INTO `order_master` VALUES ('d6839891-2ecb-4843-b5a9-cdb88bdbfe8a', '张三', '18868822111', '慕课网总部', '123', '49.00', '2', '0', '2019-09-05 09:39:16', '2019-09-05 09:41:08');

-- ----------------------------
-- Table structure for `product_category`
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `category_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) NOT NULL COMMENT '类目名字',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `uqe_category_type` (`category_type`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COMMENT='类目表';

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES ('1', '我要吃肉', '1', '2019-09-02 11:12:52', '2019-09-04 18:08:26');
INSERT INTO `product_category` VALUES ('2', '爆款小炒', '2', '2019-09-04 17:54:32', '2019-09-04 18:08:28');
INSERT INTO `product_category` VALUES ('3', '美味汤羹', '3', '2019-09-04 17:55:04', '2019-09-04 18:08:30');

-- ----------------------------
-- Table structure for `product_info`
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `product_id` varchar(64) NOT NULL COMMENT '商品编号',
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '单价',
  `product_stock` int(11) NOT NULL COMMENT '库存',
  `product_description` varchar(64) DEFAULT NULL COMMENT '描述',
  `product_icon` varchar(512) DEFAULT NULL COMMENT '小图',
  `product_status` tinyint(3) DEFAULT '0' COMMENT '商品状态，0正常1下架',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO `product_info` VALUES ('011d08c6-dd8c-445d-b72d-a50d98488b19', '美味蜡鸡腿', '15.00', '999', '', '/img/mwljt.jpg', '0', '1', '2019-09-04 18:21:10', '2019-09-05 09:41:08');
INSERT INTO `product_info` VALUES ('0e72ecc0-20b1-47cf-8bb4-2066573bfe7e', '西红柿蛋汤', '4.00', '999', '', '/img/xhsdt.jpg', '0', '3', '2019-09-04 18:23:28', '2019-09-04 18:23:28');
INSERT INTO `product_info` VALUES ('1a3dc37d-2086-4b66-b60b-5c0fdfdb1d41', '土豆牛肉', '26.00', '998', '', '/img/tdnr.jpg', '0', '1', '2019-09-04 18:12:39', '2019-09-05 09:43:06');
INSERT INTO `product_info` VALUES ('21e50e11-f527-4cba-b6ac-cda195e1b440', '紫菜蛋汤', '4.00', '999', '', '/img/zcdt.jpg', '0', '3', '2019-09-04 18:22:27', '2019-09-04 18:22:27');
INSERT INTO `product_info` VALUES ('388adc99-1f79-4ef1-8025-ee340bea0fad', '冬瓜排骨汤', '6.00', '999', '', '/img/dgpgt.jpg', '0', '3', '2019-09-04 18:24:48', '2019-09-04 18:24:48');
INSERT INTO `product_info` VALUES ('4f6ad41d-93de-4dc3-89d9-1a76d4b07576', '红烧鸡腿', '8.00', '998', '', '/img/hsjt.jpg', '0', '1', '2019-09-04 18:04:08', '2019-09-05 09:43:06');
INSERT INTO `product_info` VALUES ('51859ad7-d15c-4847-940a-418392414486', '千页豆腐', '10.00', '999', '', '/img/qydf.jpg', '0', '2', '2019-09-04 18:19:28', '2019-09-04 18:19:28');
INSERT INTO `product_info` VALUES ('6d3cb700-5b57-42b5-9c66-445b2efdd30c', '手撕包菜', '8.00', '999', '', '/img/ssbc.jpg', '0', '2', '2019-09-04 18:13:59', '2019-09-04 18:13:59');
INSERT INTO `product_info` VALUES ('7d8b7863-9bc1-4a1a-a991-8418993df0d4', '干锅花菜', '10.00', '999', '', '/img/gghc.jpg', '0', '2', '2019-09-04 18:16:29', '2019-09-04 18:26:20');
INSERT INTO `product_info` VALUES ('b6c8559a-ebae-45b9-bf4f-5efde9c89289', '番茄炒蛋', '8.00', '999', '', '/img/fqcd.jpg', '0', '2', '2019-09-04 18:00:08', '2019-09-04 18:09:12');
INSERT INTO `product_info` VALUES ('c48f2308-5a9b-441b-a95c-a6e37f671271', '炒土豆丝', '7.00', '999', '', '/img/ctds.jpg', '0', '2', '2019-09-04 18:15:13', '2019-09-04 18:26:38');
INSERT INTO `product_info` VALUES ('cfa88f55-306b-46cb-a76f-9e1ffe85e49d', '红烧大排', '17.00', '999', '', '/img/hsdp.jpg', '0', '1', '2019-09-04 18:10:56', '2019-09-04 18:10:56');
INSERT INTO `product_info` VALUES ('dc0b8250-db6c-43cc-854a-b67e2ad19fa4', '香菇鸡块', '13.00', '999', '', '/img/xgjk.jpg', '0', '1', '2019-09-04 18:02:25', '2019-09-04 18:02:25');

-- ----------------------------
-- Table structure for `seller_info`
-- ----------------------------
DROP TABLE IF EXISTS `seller_info`;
CREATE TABLE `seller_info` (
  `seller_id` varchar(64) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `openid` varchar(64) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`seller_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of seller_info
-- ----------------------------
INSERT INTO `seller_info` VALUES ('8d65609e-62f8-415d-94d1-c359d1b1bf6e', 'admin', 'admin', '123456', '2019-09-03 10:50:00', '2019-09-03 10:50:00');
