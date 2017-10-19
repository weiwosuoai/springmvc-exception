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
  `description` text DEFAULT NULL COMMENT '问题出现场景描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user_id` int NOT NULL COMMENT '提问用户id',
  `vote_up` int DEFAULT 0 COMMENT '被赞次数',
  `vote_down` int DEFAULT 0 COMMENT '被踩次数',
  `language` int(1) DEFAULT 0 COMMENT '语言类型 0：英文，1：中文',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 添加中文标题字段 title_ch
ALTER TABLE t_question ADD `title_ch` varchar(500) DEFAULT NULL COMMENT '中文问题标题';
-- 添加问题来源 id original_id
ALTER TABLE t_question ADD `original_id` int DEFAULT NULL COMMENT '问题来源id';
-- 添加问题来源 link original_link
ALTER TABLE t_question ADD `original_link` VARCHAR(500) DEFAULT NULL COMMENT '问题来源链接';
-- 添加问题被查看次数 view_num
ALTER TABLE t_question ADD `view_num` INT DEFAULT 1 COMMENT '问题被查看次数';
-- 添加问题描述信息是否翻译完成字段 title_trans_finish
ALTER TABLE t_question ADD `title_trans_finished` INT DEFAULT 0 COMMENT '问题描述信息是否翻译完成,0:未翻译,1:已翻译';
-- 添加问题答案信息是否翻译完成字段 answer_trans_finish
ALTER TABLE t_question ADD `answer_trans_finished` INT DEFAULT 0 COMMENT '问题答案信息是否翻译完成,0:未翻译,1:已翻译';
-- 添加问题状态
ALTER TABLE t_question ADD `status` INT DEFAULT 0 COMMENT '问题状态 0：机器翻译 1：人工校译';

-- question 描述表
DROP TABLE IF EXISTS `t_question_desc`;
CREATE TABLE `t_question_desc` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '问题描述id',
  `question_id` int(11) NOT NULL COMMENT '问题id',
  `description` text DEFAULT NULL COMMENT '问题出现场景描述（爬取原始英文）',
  `description_ch` text DEFAULT NULL COMMENT '问题出现场景描述(中文)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX idx_question_id (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 添加问题描述创建者 create_user_id 字段
ALTER TABLE t_question_desc ADD `create_user_id` int DEFAULT 1 COMMENT '问题描述创建者 create_user_id';

-- description
DROP TABLE IF EXISTS `t_answer`;
CREATE TABLE `t_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '答案id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  `create_user_id` int NOT NULL COMMENT '答题用户id',
  `question_id` int NOT NULL COMMENT '问题id',
  `vote_up` int DEFAULT 0 COMMENT '被赞次数',
  `vote_down` int DEFAULT 0 COMMENT '被踩次数',
  `language` int(1) DEFAULT 0 COMMENT '语言类型 0：英文，1：中文',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 添加回答的原始id
ALTER TABLE t_answer ADD `original_id` int DEFAULT NULL COMMENT '回答的原始id';
-- 添加回答是否被采纳
ALTER TABLE t_answer ADD `is_accepted` int DEFAULT 0 COMMENT '回答是否被采纳 1：采纳 0：未采纳';
-- 添加原始回答爬取的被赞或被踩次数
ALTER TABLE t_answer ADD `vote_count` int DEFAULT 0 COMMENT '回答被赞或被踩次数';

-- 回答描述表
DROP TABLE IF EXISTS `t_answer_desc`;
CREATE TABLE `t_answer_desc` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '回答描述id',
  `answer_id` int(11) NOT NULL COMMENT '回答id',
  `description` text DEFAULT NULL COMMENT '回答内容（爬取原始英文）',
  `description_ch` text DEFAULT NULL COMMENT '回答(中文)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX idx_question_id (`answer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 添加回答描述创建者 create_user_id 字段
ALTER TABLE t_answer_desc ADD `create_user_id` int DEFAULT 1 COMMENT '回答描述创建者 create_user_id';

-- tag 标签
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

-- 添加 中文描述信息字段 description_ch
ALTER TABLE t_tag ADD `description_ch` text DEFAULT NULL COMMENT '中文标签描述';
-- 添加标签目前抓取到的页码
ALTER TABLE t_tag ADD `spider_page_num` int DEFAULT 1 COMMENT '标签目前抓取到的页码';

-- question tag 关系映射
DROP TABLE IF EXISTS `t_question_tag_rel`;
CREATE TABLE `t_question_tag_rel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '问题和标签的映射id',
  `question_id` int(11) NOT NULL COMMENT '问题id',
  `tag_id` int(11) NOT NULL COMMENT '标签id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- question description map
# DROP TABLE IF EXISTS `t_question_answer_map`;
# CREATE TABLE `t_question_answer_map` (
#   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '问题和标签的映射id',
#   `question_id` int(11) NOT NULL COMMENT '问题id',
#   `answer_id` int(11) NOT NULL COMMENT '回答id',
#   `create_time` datetime NOT NULL COMMENT '创建时间',
#   `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
#   PRIMARY KEY (`id`)
# ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 索引
ALTER TABLE `t_question` ADD INDEX idx_question_vote_up (`vote_up`);
ALTER TABLE `t_question` ADD INDEX idx_question_view_num (`view_num`);
ALTER TABLE `t_question` ADD INDEX idx_title_ch_title_answer_trans_finished (`title_ch`, `title_trans_finished`, `answer_trans_finished`);

ALTER TABLE `t_question_desc` ADD INDEX idx_create_time (`create_time`);
ALTER TABLE `t_answer` ADD INDEX idx_answer_question_id (`question_id`);

ALTER TABLE `t_question_tag_map` ADD INDEX idx_question_tag_map_question_id (`question_id`);
ALTER TABLE `t_question_tag_map` ADD INDEX idx_question_tag_map_tag_id (`tag_id`);






