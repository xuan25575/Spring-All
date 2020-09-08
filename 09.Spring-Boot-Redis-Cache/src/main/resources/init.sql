create table user
(
    id       int auto_increment
        primary key,
    username varchar(100) null,
    password varchar(100) null,
    sex      varchar(2)   null comment '性别，m男 f女'
);