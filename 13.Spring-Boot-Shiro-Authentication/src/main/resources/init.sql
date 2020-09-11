create table user
(
    id          int auto_increment
        primary key,
    username    varchar(100) null,
    password    varchar(100) null,
    sex         varchar(2)   null comment '性别，m男 f女',
    status      varchar(1)   null comment '0-无效，1-有效',
    create_date datetime     null comment '创建时间'
);