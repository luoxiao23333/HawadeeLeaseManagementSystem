drop database if exists hawadee;
create database hawadee;
use hawadee;
CREATE TABLE `user` (
                        `user_id` int(8) COMMENT '员工id',
                        `department_id` int(8) NULL COMMENT '部门id',
                        `position_id` int(8) NULL COMMENT '职位id',
                        `user_name` varchar(50) NULL COMMENT '员工名称',
                        `sex` varchar(10) NULL COMMENT '性别',
                        `phone` varchar(20) NULL COMMENT '手机号',
                        `email` varchar(30) NULL COMMENT '邮箱',
                        `password` varchar(30) NULL COMMENT '密码',
                        PRIMARY KEY (`user_id`)
);


CREATE TABLE `department`(
                             `department_id` int(8) PRIMARY KEY,
                             `department_name` varchar(50),
                             `chairman_number` varchar(20)
);


CREATE TABLE `POSITION` (
                            `position_id` int(8) PRIMARY key,
                            `department_id` int(8),
                            `position_name` varchar(20)
);

CREATE TABLE `contrast` (
                            `contrast_id` int(8) PRIMARY key,
                            `contrast_name` VARCHAR(20),
                            `contrast_description` VARCHAR(50),
                            `current_manager` int(8)
);


#员工和项目关系表

CREATE TABLE `contrast_info`(
                                `user_contrast_id` int PRIMARY KEY,
                                `starter` int(8),
                                `law_manager` int(8),
                                `risk_manager` int(8),
                                `ceo` int(8)
);



#添加表之间的约束

ALTER TABLE `position`
    ADD FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`) on update cascade on delete cascade;

ALTER TABLE `contrast`
    ADD FOREIGN KEY (`current_manager`) REFERENCES `user`(`user_id`);

ALTER TABLE `contrast_info`
    ADD FOREIGN KEY (`starter`) REFERENCES `user` (`user_id`) ,
    ADD FOREIGN KEY (`law_manager`) REFERENCES `user` (`user_id`),
    ADD FOREIGN KEY (`risk_manager`) REFERENCES `user` (`user_id`),
    ADD FOREIGN KEY (`ceo`) REFERENCES `user` (`user_id`);

ALTER TABLE `user`
    ADD FOREIGN KEY (`position_id`) REFERENCES `position` (`position_id`) ;


#3 创建索引
CREATE INDEX idx_usr_name ON user(user_name);

CREATE INDEX idx_dept_name ON department(department_name);

CREATE INDEX idx_contrast_name ON contrast(contrast_name);

ALTER TABLE `position`
    ADD INDEX `idx_position_name`(`position_name`) USING BTREE;

#插入数据
INSERT INTO `department` (`department_id`,  `department_name`, `chairman_number`) VALUES (1000, '管理层', '202200');
INSERT INTO `department` (`department_id`, `department_name`, `chairman_number`) VALUES (1001, '法务部', '202201');
INSERT INTO `department` (`department_id`, `department_name`, `chairman_number`) VALUES (1002, '风险管理部', '202202');
INSERT INTO `department` (`department_id`,  `department_name`, `chairman_number`) VALUES (1003, '办事部', '202203');

INSERT INTO `position` (`position_id`, `department_id`, `position_name`) VALUES (2000, 1000, '总经理');
INSERT INTO `position` (`position_id`, `department_id`, `position_name`) VALUES (2001, 1001, '法务人员');
INSERT INTO `position` (`position_id`,  `department_id`, `position_name`) VALUES (2002, 1001, '法务部经理');
INSERT INTO `position` (`position_id`, `department_id`, `position_name`) VALUES (2003, 1002, '风险管理人员');
INSERT INTO `position` (`position_id`, `department_id`, `position_name`) VALUES (2004, 1002, '风险管理部经理');
INSERT INTO `position` (`position_id`, `department_id`, `position_name`) VALUES (2005, 1003, '办事员');


INSERT INTO `user` VALUES (202200, 1000, 2000, '总经理','男','1345611', '16356437@qq.com','123');
INSERT INTO `user` VALUES (202201, 1001, 2002, '法务部经理', '男', '21343545', '23478957@qq.com','123');
INSERT INTO `user` VALUES (202202, 1002, 2004, '风险管理部经理', '女', '23475453', '23184672@qq.com','123');
INSERT INTO `user` VALUES (202204, 1003, 2005, '办事的', '男', '32313435', '12323233@qq.com','123');
