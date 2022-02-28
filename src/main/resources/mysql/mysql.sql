create database hawadee;
use hawadee;

create table department(
    `id` int(3) primary key auto_increment,
    `name` varchar(100) not null
)ENGINE = InnoDN, charset = UTF8;

create table duty(
    `id` int(3) primary key auto_increment,
    `name` varchar(100) not null,
    `department_id` int(3) references department(`id`) on update cascade on delete cascade
)ENGINE = InnoDB, charset = UTF8;

create table user(
    `id` int(10) primary key auto_increment,
    `name` varchar(1000) not null,
    index(`name`)
)ENGINE = InnoDB, charset = UTF8;

create table duty2user(
    `duty_id` int(3) references duty(`id`) on update cascade on delete cascade,
    `user_id` int(10) references user(`id`) on update cascade on delete cascade
)ENGINE = InnoDB, charset = UTF8;

create table contract_info(
    `id` int(10) primary key auto_increment,
    `current_handler_id` int(10) references user(id) on update cascade,
    `status` int(2) not null, /* 1-processing 2-finished */
    `title` varchar(1000) not null,
    `content` blob(65536),
    `create_data` datetime default now()
)ENGINE = InnoDB, charset = UTF8;
