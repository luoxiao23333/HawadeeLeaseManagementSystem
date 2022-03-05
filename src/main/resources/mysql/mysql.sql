drop database if exists hawadee;
create database hawadee;
use hawadee;

create table department(
    `id` int(3) primary key auto_increment,
    `name` varchar(100) not null
)ENGINE = InnoDB, charset = UTF8;

insert into department (name)
values ("Directors"),("Law"),("Business");

create table duty(
    `id` int(3) primary key auto_increment,
    `name` varchar(100) not null,
    `department_id` int(3) references department(`id`) on update cascade on delete cascade
)ENGINE = InnoDB, charset = UTF8;

insert into duty (name, department_id)
values ("general manager",1),("law director",2),("business stuff",3);

create table user(
    `id` int(10) primary key auto_increment,
    `name` varchar(1000) not null,
    unique(`name`),
    `password` varchar(20) not null,
    `phone` varchar(20),
    index(`name`)
)ENGINE = InnoDB, charset = UTF8;

insert into user (`id`,name, password, phone) VALUES
(1,"David Manager","1234", "19981486268"),(2,"John Lawyer","1234", "19981486268"),(3,"Bob Stuff","1234", "19981486268");

create table duty2user(
    `duty_id` int(3) references duty(`id`) on update cascade on delete cascade,
    `user_id` int(10) references user(`id`) on update cascade on delete cascade
)ENGINE = InnoDB, charset = UTF8;

insert into duty2user  (duty_id, user_id) VALUES
(1,1),(2,2),(3,3);

create table contract_info(
    `id` int(10) primary key auto_increment,
    `current_handler_id` int(10) references user(id) on update cascade,
    `promoter_id` int(10) references user(id) on update cascade,
    `status` int(2) not null default 1, /* 1-processing 2-finished */
    `title` varchar(1000) not null,
    `content` blob(65536),
    `file_loc` varchar(10000),
    `create_date` datetime default now()
)ENGINE = InnoDB, charset = UTF8;

create table contract_processing_history(
    `id` int(10) primary key auto_increment,
    `contract_id` int(10) references contract_info(`id`) on update cascade on delete cascade,
    `process_user_id` int(10) references user(`id`) on update cascade on delete cascade,
    `reason` blob(65536),
    `create_date` datetime default now(),
    `status` int(2) not null /* 1-approved 2-denied */
)ENGINE = InnoDB, charset UTF8;

CREATE TABLE `reimbursement_info`  (
    `id` int NOT NULL AUTO_INCREMENT,
    `current_handler_id` int NULL DEFAULT NULL,
    `promoter_id` int NULL DEFAULT NULL,
    `status` int NOT NULL DEFAULT 1,
    `title` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `content` mediumblob NULL,
    `file_loc` varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `reimbursement_processing_history`  (
    `id` int NOT NULL AUTO_INCREMENT,
    `reimbursement_id` int NULL DEFAULT NULL,
    `process_user_id` int NULL DEFAULT NULL,
    `reason` mediumblob NULL,
    `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP,
    `status` int NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;