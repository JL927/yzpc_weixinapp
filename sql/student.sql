create table student
(
    id             bigint unsigned               not null
        primary key,
    name           varchar(30)                   not null,
    username       varchar(50)                   not null,
    password       varchar(100) default '888888' not null,
    teacher_id     int                           not null,
    class_id       int                           not null,
    major_id       tinyint                       not null,
    computer_score tinyint      default 0        not null,
    skill_score    tinyint      default 0        not null,
    english_score  tinyint      default 0        not null,
    chinese_score  tinyint      default 0        not null,
    total_score    tinyint      default 0        not null,
    satisfied      tinyint(1)   default 0        not null,
    constraint student_pk
        unique (id)
);

create index idx_username
    on student (username);

