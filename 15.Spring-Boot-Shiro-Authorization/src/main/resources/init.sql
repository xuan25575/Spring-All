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
INSERT INTO h_sword.user (id, username, password, sex, status, create_date) VALUES (1, '张三', 'f817b0fb81f8676f28fa1ba354f104f6', 'M', '1', '2020-09-09 16:32:39');
INSERT INTO h_sword.user (id, username, password, sex, status, create_date) VALUES (2, '黄小2', 'eae5ea10cbcd745eedae2eabfe47c443', 'M', '1', '2020-09-07 16:32:52');
INSERT INTO h_sword.user (id, username, password, sex, status, create_date) VALUES (3, '小黑', '3244', 'M', '0', null);
INSERT INTO h_sword.user (id, username, password, sex, status, create_date) VALUES (4, 'admin', '8973cfbe14910b144c57b662ca8b92f1', 'M', '1', '2020-09-10 14:59:27');


create table user_role
(
    id      int auto_increment
        primary key,
    user_id int null comment '用户id',
    rid     int null comment '角色id'
)
    comment '用户角色关系表';

INSERT INTO h_sword.user_role (id, user_id, rid) VALUES (1, 4, 1);
INSERT INTO h_sword.user_role (id, user_id, rid) VALUES (2, 2, 2);

create table role
(
    id   int auto_increment
        primary key,
    name varchar(100) null comment '角色名称',
    memo varchar(100) null comment '角色描述'
)
    comment '角色表';
INSERT INTO h_sword.role (id, name, memo) VALUES (1, 'admin', '超级管理员');
INSERT INTO h_sword.role (id, name, memo) VALUES (2, 'huang2', '普通用户');


create table h_sword.permission
(
	id int auto_increment
		primary key,
	url varchar(100) null comment 'url路径',
	url_desc varchar(100) null comment 'url描述'
)
comment '权限表';

INSERT INTO h_sword.permission (id, url, url_desc) VALUES (1, '/user', 'user:user');
INSERT INTO h_sword.permission (id, url, url_desc) VALUES (2, '/user/add', 'user:add');
INSERT INTO h_sword.permission (id, url, url_desc) VALUES (3, '/user/delete', 'user:delete');

create table h_sword.role_permission
(
	id int auto_increment
		primary key,
	rid int null comment '角色id',
	pid int null comment '权限id'
)
comment '关系表';

INSERT INTO h_sword.role_permission (id, rid, pid) VALUES (1, 1, 1);
INSERT INTO h_sword.role_permission (id, rid, pid) VALUES (2, 1, 2);
INSERT INTO h_sword.role_permission (id, rid, pid) VALUES (3, 1, 3);
INSERT INTO h_sword.role_permission (id, rid, pid) VALUES (4, 2, 2);

















