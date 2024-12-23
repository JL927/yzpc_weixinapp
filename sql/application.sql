create table application
(
    id               bigint unsigned                                  not null
        primary key,
    student_id       bigint                                           not null,
    application_type enum ('computer', 'skill', 'english', 'chinese') not null,
    status           tinyint     default 1                            not null comment '申请状态中，0表示失败，1为提交等待班主任审核，2为等待主任审核，3为等待院长审核，4为通过，在通过后会有最终得分，如果失败则可以添加描述',
    description      varchar(400)                                     null,
    score            tinyint     default 0                            null,
    name             varchar(50) default '未命名'                     not null,
    check (`status` in (0, 1, 2, 3, 4))
);

create index idx_student_id
    on application (student_id);

