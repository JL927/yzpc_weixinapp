create table yzpc_wxapp.student
(
    id             bigint               not null
        primary key,
    name           varchar(30)          not null,
    username       varchar(50)          not null,
    password       varchar(100)         not null,
    teacher_id     int                  not null,
    class_id       int                  not null,
    major_id       tinyint              not null,
    computer_score tinyint    default 0 not null,
    skill_score    tinyint    default 0 not null,
    english_score  tinyint    default 0 not null,
    chinese_score  tinyint    default 0 not null,
    total_score    tinyint    default 0 not null,
    satisfied      tinyint(1) default 0 not null
);

create index idx_username
    on yzpc_wxapp.student (username);

