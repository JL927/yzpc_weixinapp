create table yzpc_wxapp.application
(
    id               bigint                                           not null
        primary key,
    student_id       bigint                                           not null,
    application_type enum ('computer', 'skill', 'english', 'chinese') not null,
    status           tinyint                                          not null,
    description      varchar(400)                                     null,
    score            tinyint                                          null,
    check (`status` in (0, 1, 2, 3, 4))
);

create index idx_student_id
    on yzpc_wxapp.application (student_id);

