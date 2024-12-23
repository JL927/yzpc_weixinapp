create table images
(
    id             bigint unsigned not null
        primary key,
    application_id bigint          not null,
    url            varchar(2048)   not null
);

create index idx_application_id
    on images (application_id);

