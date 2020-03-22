/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1_3306
Source Server Version : 50562
Source Host           : 127.0.0.1:3306
Source Database       : graduation

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2020-03-22 22:17:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for applicationform
-- ----------------------------
DROP TABLE IF EXISTS `applicationform`;
CREATE TABLE `applicationform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `pname` varchar(100) DEFAULT NULL,
  `companyname` varchar(100) DEFAULT NULL,
  `cost` varchar(100) DEFAULT NULL,
  `teacher` varchar(100) DEFAULT NULL,
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of applicationform
-- ----------------------------
INSERT INTO `applicationform` VALUES ('7', '熊鹏', 'C语言实训', 'yc', '50', '李可欣老师', '2020-02-26 21:42:20');
INSERT INTO `applicationform` VALUES ('8', '熊鹏', 'linux实训', '卓应', '300', '李可欣老师', '2020-02-28 22:19:05');

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `pname` varchar(50) DEFAULT NULL,
  `level` varchar(10) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES ('1', '熊鹏', 'C语言实训', 'A', '1111');
INSERT INTO `feedback` VALUES ('2', '熊鹏', 'C语言实训', 'B', '111');
INSERT INTO `feedback` VALUES ('3', '熊鹏', 'C', 'C', '1222');
INSERT INTO `feedback` VALUES ('4', null, null, 'C', null);
INSERT INTO `feedback` VALUES ('5', null, null, 'C', null);

-- ----------------------------
-- Table structure for scoreform
-- ----------------------------
DROP TABLE IF EXISTS `scoreform`;
CREATE TABLE `scoreform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `pname` varchar(100) DEFAULT NULL,
  `companyname` varchar(100) DEFAULT NULL,
  `companyresult` varchar(100) DEFAULT NULL,
  `teacherresult` varchar(100) DEFAULT NULL,
  `teacher` varchar(100) DEFAULT NULL,
  `reportdoc` varchar(100) DEFAULT NULL,
  `companyStatus` varchar(10) DEFAULT NULL COMMENT '0 未完成 1 已完成',
  `teaStatus` varchar(10) DEFAULT NULL COMMENT '0 未完成 1 已完成',
  `status` varchar(10) DEFAULT NULL COMMENT '0 未完成 1 已完成',
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of scoreform
-- ----------------------------
INSERT INTO `scoreform` VALUES ('4', '熊鹏', 'C语言实训', 'yc', '70', '70', '李可欣老师', '817a881eb2884da28c8292eb0c3c97f4_熊鹏开题报告.docx', '0', '1', '1', '2020-03-22 22:09:20');
INSERT INTO `scoreform` VALUES ('6', '刘为', 'C语言实训', 'yc', '70', '60', '李可欣老师', '', '1', '1', '1', '2020-03-22 21:50:00');

-- ----------------------------
-- Table structure for training_schedule
-- ----------------------------
DROP TABLE IF EXISTS `training_schedule`;
CREATE TABLE `training_schedule` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `pcode` varchar(50) DEFAULT NULL COMMENT '项目编号',
  `pname` varchar(50) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL COMMENT '0 校内 1 校外',
  `companyname` varchar(50) DEFAULT NULL,
  `starttime` varchar(200) DEFAULT NULL,
  `endtime` varchar(200) DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL COMMENT '实训内容简介',
  `doc` varchar(255) DEFAULT NULL COMMENT '文件上传',
  `cost` varchar(50) DEFAULT NULL COMMENT '费用',
  `pattr1` varchar(200) DEFAULT NULL COMMENT '备用属性1   0 为审批中 1为通过',
  `pattr2` varchar(200) DEFAULT NULL COMMENT '备用属性2',
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of training_schedule
-- ----------------------------
INSERT INTO `training_schedule` VALUES ('8', 'P202011815031761', 'C语言实训', '计信学院', '0', 'yc', '2020-10-5', '2020-11-10', 'yc JAVA语言实训1111111', '9701f0c279a749e0805442a69b560454_c语言实训.txt', '5198', '0', null, '2020-03-22 16:57:37');
INSERT INTO `training_schedule` VALUES ('9', 'P202011815211319', 'linux实训', '学校南门', '1', '卓应', '2020-10-5', '2020-11-10', '', '7f611004d640443092b76bb4ddb283c7_linux实训.txt', '502', '1', null, '2020-03-22 16:16:56');
INSERT INTO `training_schedule` VALUES ('10', 'P2020224222512626', '嵌入式实习', '长沙麓谷企业广场', '1', '粤嵌', '2012-2-1', '2012-3-4', null, 'c52992964aa94251a95274e7ae986dc0_想法.txt', '50', '1', null, '2020-03-22 16:16:54');
INSERT INTO `training_schedule` VALUES ('11', 'P2020321231917201', '大数据学习', '计信学院2栋2520', '0', 'yc', '2018-12-29 ', '2019-12-20 ', '大数据培训', '2fc7e79d992c48e7a94f5d6db64bbfb8_想法.txt', '15888', '1', null, '2020-03-22 16:36:28');
INSERT INTO `training_schedule` VALUES ('13', 'P2020322214737555', 'c#', '计信学院2栋2514', '0', 'atguigu', '2018-12-29', '2018-12-31', 'c#', '9f0b14d296d342e6bc723cce00849829_功能.txt', '5000', '1', null, '2020-03-22 21:49:08');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `gender` varchar(10) DEFAULT '1' COMMENT '0 女 1 男',
  `powerlevel` varchar(20) DEFAULT '3' COMMENT '0 管理员 1 企业 2 老师 3 学生',
  `phone` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1', '0', '13244441111');
INSERT INTO `user` VALUES ('2', '李可欣老师', 'e10adc3949ba59abbe56e057f20f883e', '0', '2', '13255556666');
INSERT INTO `user` VALUES ('3', '熊鹏', '202cb962ac59075b964b07152d234b70', '1', '3', '13266669999');
INSERT INTO `user` VALUES ('4', '任长安老师', 'e10adc3949ba59abbe56e057f20f883e', '1', '2', '13255556666');
INSERT INTO `user` VALUES ('5', '王新祥老师', 'e10adc3949ba59abbe56e057f20f883e', '1', '2', '12312321312');
INSERT INTO `user` VALUES ('6', '熊鹏1', 'e10adc3949ba59abbe56e057f20f883e', '1', '3', '13255556666');
INSERT INTO `user` VALUES ('8', 'yc', 'e10adc3949ba59abbe56e057f20f883e', null, '1', '777777777777');
INSERT INTO `user` VALUES ('9', '卓应', 'e10adc3949ba59abbe56e057f20f883e', '1', '1', '13255556666');
INSERT INTO `user` VALUES ('10', '管理员', 'e10adc3949ba59abbe56e057f20f883e', '1', '3', '15285374069');
INSERT INTO `user` VALUES ('13', '黑马', 'e10adc3949ba59abbe56e057f20f883e', '1', '1', '15285374069');
INSERT INTO `user` VALUES ('14', '小芬', 'e10adc3949ba59abbe56e057f20f883e', '1', '3', '18385616483');
INSERT INTO `user` VALUES ('15', '粤嵌', 'e10adc3949ba59abbe56e057f20f883e', '1', '1', '15285374069');
INSERT INTO `user` VALUES ('16', '陈利平老师', 'e10adc3949ba59abbe56e057f20f883e', '0', '2', '15285374069');
INSERT INTO `user` VALUES ('18', '彭彩虹老师', 'e10adc3949ba59abbe56e057f20f883e', '1', '2', '13365452');
INSERT INTO `user` VALUES ('19', '熊鹏2', 'e10adc3949ba59abbe56e057f20f883e', '1', '3', '13255556666');
INSERT INTO `user` VALUES ('20', '熊鹏3', 'e10adc3949ba59abbe56e057f20f883e', '1', '3', '13255556666');
INSERT INTO `user` VALUES ('27', 'atguigu', 'e10adc3949ba59abbe56e057f20f883e', '1', '1', '15285374069');
