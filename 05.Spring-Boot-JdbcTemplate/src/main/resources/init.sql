create table student(
	id int auto_increment comment '主键',
	name varchar(20) null comment '名字',
	sex varchar(1) null comment '性别,1-男，0-女',
	score int null comment '分数',
	constraint student_pk
		primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO STUDENT VALUES ('001', '黄小2', 'M',33);
INSERT INTO STUDENT VALUES ('002', 'jack', 'M',44);
INSERT INTO STUDENT VALUES ('003', 'lisa', 'F',46);