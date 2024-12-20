create table yzpc_wxapp.images
(
    id             bigint        not null
        primary key,
    application_id bigint        not null,
    url            varchar(2048) not null
);

create index idx_application_id
    on yzpc_wxapp.images (application_id);

