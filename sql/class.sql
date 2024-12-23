create table class
(
    id         int unsigned auto_increment
        primary key,
    name       varchar(40) not null,
    major_id   tinyint     not null,
    teacher_id int         not null
);

