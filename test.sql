/*
 Navicat Premium Data Transfer

 Source Server         : local-mysql
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : test

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 02/07/2018 23:36:55 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL,
  `email` varchar(32) DEFAULT '123@qq.com',
  `username` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_name` (`name`),
  KEY `user_mic_index` (`name`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'zhangjl', '835397999@qq.com', 'zhangjialian'), ('2', '张三', '123456@qq.com', 'zhangsan'), ('3', '李四', '111111@qq.com', 'lisi'), ('4', '王五', '222222@qq.com', 'wangwu'), ('5', 'ceshi1', '222222@qq.com', 'ceshi1'), ('6', 'ceshi2', '333333@qq.com', '???2');
COMMIT;

-- ----------------------------
--  View structure for `user_view`
-- ----------------------------
DROP VIEW IF EXISTS `user_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `user_view` AS (select `user`.`id` AS `id`,`user`.`name` AS `name`,`user`.`email` AS `email` from `user`);

SET FOREIGN_KEY_CHECKS = 1;
