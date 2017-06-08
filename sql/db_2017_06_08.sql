-- todo 索引待建

SET NAMES utf8;

-- user
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '用户密码，MD5加密',
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_unique` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

BEGIN;
-- 插入采集机器人用户信息
INSERT INTO `t_user` VALUES (1, 'Robot', '427338237BD929443EC5D48E24FD2B1A', '', '', NOW(), NOW());
COMMIT;

-- question
DROP TABLE IF EXISTS `t_question`;
CREATE TABLE `t_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '问题id',
  `title` varchar(500) NOT NULL COMMENT '问题标题',
  `description` text NOT NULL COMMENT '问题出现场景描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  `create_user_id` int NOT NULL COMMENT '提问用户id',
  `vote_up` int DEFAULT 0 COMMENT '被赞次数',
  `vote_down` int DEFAULT 0 COMMENT '被踩次数',
  `language` int(1) DEFAULT 0 COMMENT '语言类型 0：英文，1：中文',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- answer
DROP TABLE IF EXISTS `t_answer`;
CREATE TABLE `t_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '答案id',
  `answer` text NOT NULL COMMENT '回答的答案',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  `create_user_id` int NOT NULL COMMENT '答题用户id',
  `question_id` int NOT NULL COMMENT '问题id',
  `vote_up` int DEFAULT 0 COMMENT '被赞次数',
  `vote_down` int DEFAULT 0 COMMENT '被踩次数',
  `language` int(1) DEFAULT 0 COMMENT '语言类型 0：英文，1：中文',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- tag
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `name` VARCHAR(50) NOT NULL COMMENT '标签名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  `create_user_id` int NOT NULL COMMENT '创建者id',
  `description` text DEFAULT NULL COMMENT '标签描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- question tag mapper
DROP TABLE IF EXISTS `t_question_tag_mapper`;
CREATE TABLE `t_question_tag_mapper` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '问题和标签的映射id',
  `question_id` int(11) NOT NULL COMMENT '问题id',
  `answer_id` int(11) NOT NULL COMMENT '答案id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;




