create table sys_log
(
    id          int auto_increment comment '主键'
        primary key,
    user_name   varchar(100) null comment '用户名',
    operation   varchar(50)  null comment '用户操作',
    opt_time    int          null comment '响应时间',
    method      varchar(200) null comment '操作方法',
    params      varchar(200) null comment '方法参数',
    ip          varchar(70)  null comment 'ip地址',
    create_time datetime     null comment '创建时间'
);
