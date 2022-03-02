create database hawadee;
use hawadee;

create table account(
    `id` int(10) primary key auto_increment,
    `username` varchar(20) not null,
    `phone` varchar(100),
    `email` varchar(100)
)ENGINE = InnoDb, charset = utf8;