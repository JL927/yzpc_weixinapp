create table yzpc_wxapp.teacher
(
    id       int auto_increment
        primary key,
    name     varchar(30)                                       not null,
    username varchar(50)                                       not null,
    password varchar(100)                                      not null,
    role     enum ('dean', 'department_head', 'class_teacher') not null
);

