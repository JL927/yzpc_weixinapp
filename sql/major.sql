create table major
(
    id             tinyint      not null
        primary key,
    name           varchar(30)  not null,
    required_score tinyint      not null,
    description    varchar(600) not null,
    constraint major_pk_2
        unique (name)
);

