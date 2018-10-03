/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : t_stu

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-10-03 12:47:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sign
-- ----------------------------
DROP TABLE IF EXISTS `t_sign`;
CREATE TABLE `t_sign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `todaydate` datetime NOT NULL COMMENT '当天日期',
  `signdate` datetime NOT NULL COMMENT '签到时间',
  `lateflag` int(11) NOT NULL COMMENT '迟到早退状态 1正常签到2迟到3早退',
  `amflag` int(11) NOT NULL COMMENT '上午下午 1代表上午2代表下午',
  `uno` varchar(255) DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uno` (`uno`),
  CONSTRAINT `t_sign_ibfk_1` FOREIGN KEY (`uno`) REFERENCES `t_user` (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sign
-- ----------------------------
INSERT INTO `t_sign` VALUES ('1', '2018-09-29 09:52:37', '2018-09-29 09:52:41', '1', '1', 'admin', null);
INSERT INTO `t_sign` VALUES ('3', '2018-09-29 16:43:51', '2018-09-29 16:43:51', '3', '2', 'admin', null);
INSERT INTO `t_sign` VALUES ('4', '2018-10-02 00:00:00', '2018-10-02 15:40:11', '3', '2', 'stu000003', '2018-10-02 15:58:27');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `no` varchar(255) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `sex` varchar(5) DEFAULT NULL,
  `age` int(5) DEFAULT NULL,
  `phone` varchar(11) NOT NULL,
  `email` varchar(30) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3', '123', '女', '123', '123', '123');
INSERT INTO `t_user` VALUES ('stu000001', '123', '123', '123', '123', '123', '123');
INSERT INTO `t_user` VALUES ('stu000002', '123', '张三', '男', '20', '123', '123');
INSERT INTO `t_user` VALUES ('stu000003', '202cb962ac59075b964b07152d234b70', '李四', '男', '20', '1234567', '1245');
