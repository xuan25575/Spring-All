-- auto-generated definition
create table sys_job
(
    job_id          int auto_increment comment '任务id'
        primary key,
    bean_name       varchar(100) null comment 'bean 名称',
    method_name     varchar(100) null comment '方法名称',
    cron_expression varchar(255) null comment 'cron 表达式',
    remark          varchar(255) null,
    job_status      int          null comment '状态（1 正常，0 暂停）',
    create_time     datetime     null comment '创建时间',
    update_time     datetime     null comment '更新时间',
    method_params   varchar(200) null comment '方法参数'
)
    comment '定时任务' charset = latin1;

