create table user
(
    `id`       bigint       not null primary key,
    `username` varchar(255) not null default '',
    `password` varchar(255) not null default ''
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;