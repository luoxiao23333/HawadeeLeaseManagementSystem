drop database if exists hawadee;
create database hawadee;
use hawadee;

create table department
(
    `id`   int(3) primary key auto_increment,
    `name` varchar(100) not null
) ENGINE = InnoDB,
  charset = UTF8;

insert into department (name)
values ("Directors"),
       ("Law"),
       ("Business");

create table duty
(
    `id`            int(3) primary key auto_increment,
    `name`          varchar(100) not null,
    `department_id` int(3) references department (`id`) on update cascade on delete cascade
) ENGINE = InnoDB,
  charset = UTF8;

insert into duty (name, department_id)
values ("general manager", 1),
       ("law director", 2),
       ("business stuff", 3);

create table user
(
    `id`       int(10) primary key auto_increment,
    `name`     varchar(1000) not null,
    unique (`name`),
    `password` varchar(20)   not null,
    `phone`    varchar(20),
    index (`name`)
) ENGINE = InnoDB,
  charset = UTF8;

insert into user (`id`, name, password, phone)
VALUES (1, "David Manager", "1234", "19981486268"),
       (2, "John Lawyer", "1234", "19981486268"),
       (3, "Bob Stuff", "1234", "19981486268");

create table duty2user
(
    `duty_id` int(3) references duty (`id`) on update cascade on delete cascade,
    `user_id` int(10) references user (`id`) on update cascade on delete cascade
) ENGINE = InnoDB,
  charset = UTF8;

insert into duty2user (duty_id, user_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);

create table contract_info
(
    `id`                 int(10) primary key auto_increment,
    `current_handler_id` int(10) references user (id) on update cascade,
    `promoter_id`        int(10) references user (id) on update cascade,
    `status`             int(2)        not null default 1, /* 1-processing 2-finished */
    `title`              varchar(1000) not null,
    `content_loc`        varchar(1000) not null,
    `file_loc`           varchar(10000),
    `create_date`        datetime               default now()
) ENGINE = InnoDB,
  charset = UTF8;

create table contract_processing_history
(
    `id`              int(10) primary key auto_increment,
    `contract_id`     int(10) references contract_info (`id`) on update cascade on delete cascade,
    `process_user_id` int(10) references user (`id`) on update cascade on delete cascade,
    `reason`          blob(65536),
    `create_date`     datetime default now(),
    `status`          int(2) not null /* 1-approved 2-denied */
) ENGINE = InnoDB,
  charset UTF8;


create table project_info
(
    `id`                 int(10) primary key auto_increment,
    `current_handler_id` int(10) references user (id) on update cascade,
    `promoter_id`        int(10) references user (id) on update cascade,
    `status`             int(2)        not null default 1, /* 1-processing 2-finished */
    `title`              varchar(1000) not null,
    `content`            blob(65536),
    `file_loc`           varchar(10000),
    `create_date`        datetime               default now()
) ENGINE = InnoDB,
  charset = UTF8;


create table project_res
(
    `id`         int(10) primary key auto_increment,
    `project_id` int(10) references project_info (`id`) on update cascade on delete cascade,
    `status`     int(2)  default 1, /* 1-processing 2-finished */
    `grade`      int(10) default 60,
    `file_loc`   varchar(10000)
) ENGINE = InnoDB,
  charset UTF8;

create table project_processing_history
(
    `id`              int(10) primary key auto_increment,
    `project_id`      int(10) references project_info (`id`) on update cascade on delete cascade,
    `process_user_id` int(10) references user (`id`) on update cascade on delete cascade,
    `reason`          blob(65536),
    `create_date`     datetime default now(),
    `status`          int(2) not null /* 1-approved 2-denied */
) ENGINE = InnoDB,
  charset UTF8;

create table comment
(
    commentID int(10) primary key auto_increment,
    nickname  varchar(1000) not null,
    content   varchar(1000)
) ENGINE = InnoDB,
  charset UTF8;

CREATE TABLE `reimbursement_info`
(
    `id`                 int                                                       NOT NULL AUTO_INCREMENT,
    `current_handler_id` int                                                       NULL     DEFAULT NULL,
    `promoter_id`        int                                                       NULL     DEFAULT NULL,
    `status`             int                                                       NOT NULL DEFAULT 1,
    `amount`             bigint                                                    NOT NULL,
    `content`            mediumblob                                                NULL,
    `file_loc`           varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    `create_date`        datetime                                                  NULL     DEFAULT CURRENT_TIMESTAMP,
    `approval_file_loc`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL     DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

CREATE TABLE `reimbursement_processing_history`
(
    `id`               int        NOT NULL AUTO_INCREMENT,
    `reimbursement_id` int        NULL DEFAULT NULL,
    `process_user_id`  int        NULL DEFAULT NULL,
    `reason`           mediumblob NULL,
    `create_date`      datetime   NULL DEFAULT CURRENT_TIMESTAMP,
    `status`           int        NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

CREATE TABLE `userRel`
(
    `id`          int references `user` (id),
    `colleague`   int references `user` (id),
    `leader`      int references `user` (id),
    `subordinate` int references `user` (id),
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `id2` (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

INSERT INTO `userRel`
VALUES (1, 2, 3, 4);
INSERT INTO `userRel`
VALUES (2, 1, 3, 4);
INSERT INTO `userRel`
VALUES (3, 2, 1, 4);

CREATE TABLE `userInfo`
(
    `id`         int references `user` (id),
    `school`     varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci,
    `age`        int,
    `level`      int,
    `self_intro` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `id2` (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

INSERT INTO `userInfo`
VALUES (1, 'scu', 20, 14, 'i am a pig and i love scu');
INSERT INTO `userInfo`
VALUES (2, 'sjtu', 20, 18, 'work hard and you will get promoted');
INSERT INTO `userInfo`
VALUES (3, 'pku', 20, 20, 'i am really good at pua');
INSERT INTO `userInfo`
VALUES (4, 'jhu', 20, 20, 'I am Data');

/*
客户管理表,小龙部分
 */
create table client
(
    id    int(10) auto_increment primary key,
    name  varchar(1000) not null,
    phone varchar(200)  null,
    email varchar(500)  null
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;
insert into client (name, phone, email) values ("杜小龙","15282505597","1635564377@qq.com"),
                                               ("樊杨","15282505597","642782632@qq.com"),
                                               ("罗霄","15282505597","2362844711@qq.com"),
                                               ("汤智妍","1282505597","1295884532@qq.com"),
                                               ("李政霖","15282505597","1125806272@qq.com"),
                                               ("刘君昊","15282505597","992364620@qq.com");

/* 新的表格放在这行上面 */

/* 测试数据面板功能插入的合同 */
insert into hawadee.user (hawadee.user.name, hawadee.user.password, hawadee.user.id)
values ("Data", "1234", 4);
insert into hawadee.contract_info(current_handler_id, status, title, content_loc, id, promoter_id)
VALUES (4, 1, "Data Visualization", "Data vis test", 1, 4);

insert into contract_processing_history(contract_id, process_user_id, reason, create_date, status)
VALUES (1, 4, "good", "2000-01-01 00:00:00", 1),
       (1, 4, "good", "2000-02-01 00:00:00", 1),
       (1, 4, "good", "2000-03-01 00:00:00", 1),
       (1, 4, "good", "2000-04-01 00:00:00", 1),
       (1, 4, "good", "2000-05-01 00:00:00", 1),
       (1, 4, "good", "2000-06-01 00:00:00", 1),
       (1, 4, "good", "2000-01-01 00:00:00", 1),
       (1, 4, "good", "2000-03-01 00:00:00", 1),
       (1, 4, "good", "2000-04-01 00:00:00", 1),
       (1, 4, "good", "2000-05-01 00:00:00", 1),
       (1, 4, "good", "2000-06-01 00:00:00", 1),
       (1, 4, "good", "2000-01-01 00:00:00", 1),
       (1, 4, "good", "2000-01-01 00:00:00", 1),
       (1, 4, "good", "2000-01-01 00:00:00", 1),
       (1, 4, "good", "2000-01-01 00:00:00", 1),
       (1, 4, "good", "2000-02-01 00:00:00", 1),
       (1, 4, "good", "2000-02-01 00:00:00", 1),
       (1, 4, "good", "2000-02-01 00:00:00", 1),
       (1, 4, "good", "2000-01-01 00:00:00", 1),
       (1, 4, "good", "2000-01-01 00:00:00", 1),
       (1, 4, "good", "2000-04-01 00:00:00", 1),
       (1, 4, "good", "2000-01-01 00:00:00", 1),
       (1, 4, "good", "2000-04-01 00:00:00", 1),
       (1, 4, "good", "2000-01-01 00:00:00", 1),
       (1, 4, "good", "2000-01-01 00:00:00", 1),
       (1, 4, "good", "2000-04-01 00:00:00", 1),
       (1, 4, "good", "2000-06-01 00:00:00", 2),
       (1, 4, "good", "2000-01-01 00:00:00", 2),
       (1, 4, "good", "2000-02-01 00:00:00", 2),
       (1, 4, "good", "2000-03-01 00:00:00", 2),
       (1, 4, "good", "2000-04-01 00:00:00", 2),
       (1, 4, "good", "2000-05-01 00:00:00", 2),
       (1, 4, "good", "2000-06-01 00:00:00", 2),
       (1, 4, "good", "2000-06-01 00:00:00", 2),
       (1, 4, "good", "2000-06-01 00:00:00", 2),
       (1, 4, "good", "2000-07-01 00:00:00", 2),
       (1, 4, "good", "2000-08-01 00:00:00", 2),
       (1, 4, "good", "2000-01-01 00:00:00", 2),
       (1, 4, "good", "2000-03-01 00:00:00", 2);

insert into reimbursement_info (current_handler_id, promoter_id, status, amount, content, file_loc,
                                approval_file_loc, create_date)
values (4, 4, 2, 100, "For Data Test", "For Data Test", "For Data Test", "2000-01-01 00:00:00"),
       (4, 4, 2, 204, "For Data Test", "For Data Test", "For Data Test", "2000-01-01 00:00:00"),
       (4, 4, 2, 909, "For Data Test", "For Data Test", "For Data Test", "2000-01-01 00:00:00"),
       (4, 4, 2, 38, "For Data Test", "For Data Test", "For Data Test", "2000-01-01 00:00:00"),
       (4, 4, 2, 398, "For Data Test", "For Data Test", "For Data Test", "2000-01-01 00:00:00"),
       (4, 4, 2, 289, "For Data Test", "For Data Test", "For Data Test", "2000-01-01 00:00:00"),
       (4, 4, 2, 334, "For Data Test", "For Data Test", "For Data Test", "2000-01-01 00:00:00"),
       (4, 4, 2, 44, "For Data Test", "For Data Test", "For Data Test", "2000-01-01 00:00:00"),
       (4, 4, 2, 78, "For Data Test", "For Data Test", "For Data Test", "2000-01-01 00:00:00"),
       (4, 4, 2, 908, "For Data Test", "For Data Test", "For Data Test", "2000-01-01 00:00:00"),
       (4, 4, 2, 567, "For Data Test", "For Data Test", "For Data Test", "2000-02-01 00:00:00"),
       (4, 4, 2, 876, "For Data Test", "For Data Test", "For Data Test", "2000-02-01 00:00:00"),
       (4, 4, 2, 346, "For Data Test", "For Data Test", "For Data Test", "2000-02-01 00:00:00"),
       (4, 4, 2, 784, "For Data Test", "For Data Test", "For Data Test", "2000-02-01 00:00:00"),
       (4, 4, 2, 789, "For Data Test", "For Data Test", "For Data Test", "2000-02-01 00:00:00"),
       (4, 4, 2, 459, "For Data Test", "For Data Test", "For Data Test", "2000-02-01 00:00:00"),
       (4, 4, 2, 752, "For Data Test", "For Data Test", "For Data Test", "2000-02-01 00:00:00"),
       (4, 4, 2, 145, "For Data Test", "For Data Test", "For Data Test", "2000-02-01 00:00:00"),
       (4, 4, 2, 785, "For Data Test", "For Data Test", "For Data Test", "2000-02-01 00:00:00"),
       (4, 4, 2, 478, "For Data Test", "For Data Test", "For Data Test", "2000-02-01 00:00:00"),
       (4, 4, 2, 235, "For Data Test", "For Data Test", "For Data Test", "2000-02-01 00:00:00"),
       (4, 4, 2, 20, "For Data Test", "For Data Test", "For Data Test", "2000-02-01 00:00:00"),
       (4, 4, 2, 521, "For Data Test", "For Data Test", "For Data Test", "2000-02-01 00:00:00"),
       (4, 4, 2, 205, "For Data Test", "For Data Test", "For Data Test", "2000-02-01 00:00:00"),
       (4, 4, 2, 789, "For Data Test", "For Data Test", "For Data Test", "2000-02-01 00:00:00"),
       (4, 4, 2, 852, "For Data Test", "For Data Test", "For Data Test", "2000-02-01 00:00:00"),
       (4, 4, 2, 145, "For Data Test", "For Data Test", "For Data Test", "2000-02-01 00:00:00"),
       (4, 4, 2, 630, "For Data Test", "For Data Test", "For Data Test", "2000-02-01 00:00:00"),
       (4, 4, 2, 458, "For Data Test", "For Data Test", "For Data Test", "2000-02-01 00:00:00"),
       (4, 4, 2, 962, "For Data Test", "For Data Test", "For Data Test", "2000-02-01 00:00:00"),
       (4, 4, 2, 365, "For Data Test", "For Data Test", "For Data Test", "2000-02-01 00:00:00"),
       (4, 4, 2, 896, "For Data Test", "For Data Test", "For Data Test", "2000-03-01 00:00:00"),
       (4, 4, 2, 325, "For Data Test", "For Data Test", "For Data Test", "2000-03-01 00:00:00"),
       (4, 4, 2, 412, "For Data Test", "For Data Test", "For Data Test", "2000-03-01 00:00:00"),
       (4, 4, 2, 47, "For Data Test", "For Data Test", "For Data Test", "2000-03-01 00:00:00"),
       (4, 4, 2, 89, "For Data Test", "For Data Test", "For Data Test", "2000-03-01 00:00:00"),
       (4, 4, 2, 5, "For Data Test", "For Data Test", "For Data Test", "2000-03-01 00:00:00"),
       (4, 4, 2, 44, "For Data Test", "For Data Test", "For Data Test", "2000-03-01 00:00:00"),
       (4, 4, 2, 789, "For Data Test", "For Data Test", "For Data Test", "2000-03-01 00:00:00"),
       (4, 4, 2, 632, "For Data Test", "For Data Test", "For Data Test", "2000-03-01 00:00:00"),
       (4, 4, 2, 458, "For Data Test", "For Data Test", "For Data Test", "2000-03-01 00:00:00"),
       (4, 4, 2, 952, "For Data Test", "For Data Test", "For Data Test", "2000-03-01 00:00:00"),
       (4, 4, 2, 145, "For Data Test", "For Data Test", "For Data Test", "2000-03-01 00:00:00"),
       (4, 4, 2, 785, "For Data Test", "For Data Test", "For Data Test", "2000-03-01 00:00:00"),
       (4, 4, 2, 487, "For Data Test", "For Data Test", "For Data Test", "2000-03-01 00:00:00"),
       (4, 4, 2, 521, "For Data Test", "For Data Test", "For Data Test", "2000-03-01 00:00:00"),
       (4, 4, 2, 450, "For Data Test", "For Data Test", "For Data Test", "2000-03-01 00:00:00"),
       (4, 4, 2, 214, "For Data Test", "For Data Test", "For Data Test", "2000-03-01 00:00:00"),
       (4, 4, 2, 547, "For Data Test", "For Data Test", "For Data Test", "2000-03-01 00:00:00"),
       (4, 4, 2, 896, "For Data Test", "For Data Test", "For Data Test", "2000-03-01 00:00:00"),
       (4, 4, 2, 854, "For Data Test", "For Data Test", "For Data Test", "2000-03-01 00:00:00"),
       (4, 4, 2, 785, "For Data Test", "For Data Test", "For Data Test", "2000-03-01 00:00:00"),
       (4, 4, 2, 478, "For Data Test", "For Data Test", "For Data Test", "2000-03-01 00:00:00"),
       (4, 4, 2, 965, "For Data Test", "For Data Test", "For Data Test", "2000-04-01 00:00:00"),
       (4, 4, 2, 874, "For Data Test", "For Data Test", "For Data Test", "2000-04-01 00:00:00"),
       (4, 4, 2, 521, "For Data Test", "For Data Test", "For Data Test", "2000-04-01 00:00:00"),
       (4, 4, 2, 748, "For Data Test", "For Data Test", "For Data Test", "2000-04-01 00:00:00"),
       (4, 4, 2, 521, "For Data Test", "For Data Test", "For Data Test", "2000-04-01 00:00:00"),
       (4, 4, 2, 456, "For Data Test", "For Data Test", "For Data Test", "2000-04-01 00:00:00"),
       (4, 4, 2, 963, "For Data Test", "For Data Test", "For Data Test", "2000-04-01 00:00:00"),
       (4, 4, 2, 85, "For Data Test", "For Data Test", "For Data Test", "2000-04-01 00:00:00"),
       (4, 4, 2, 48, "For Data Test", "For Data Test", "For Data Test", "2000-04-01 00:00:00"),
       (4, 4, 2, 789, "For Data Test", "For Data Test", "For Data Test", "2000-05-01 00:00:00"),
       (4, 4, 2, 652, "For Data Test", "For Data Test", "For Data Test", "2000-05-01 00:00:00"),
       (4, 4, 2, 145, "For Data Test", "For Data Test", "For Data Test", "2000-05-01 00:00:00"),
       (4, 4, 2, 124, "For Data Test", "For Data Test", "For Data Test", "2000-05-01 00:00:00"),
       (4, 4, 2, 548, "For Data Test", "For Data Test", "For Data Test", "2000-05-01 00:00:00"),
       (4, 4, 2, 789, "For Data Test", "For Data Test", "For Data Test", "2000-06-01 00:00:00"),
       (4, 4, 2, 457, "For Data Test", "For Data Test", "For Data Test", "2000-06-01 00:00:00"),
       (4, 4, 2, 896, "For Data Test", "For Data Test", "For Data Test", "2000-06-01 00:00:00"),
       (4, 4, 2, 789, "For Data Test", "For Data Test", "For Data Test", "2000-06-01 00:00:00");

