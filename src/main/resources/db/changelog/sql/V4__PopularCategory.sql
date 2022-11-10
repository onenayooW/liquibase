create table ktpopular_category (id bigint not null auto_increment, created_at datetime, deleted_at datetime, sorting_order bigint, edited_at datetime, updated_at datetime, version bigint, keyword varchar(255), slug varchar(1024), slug_type varchar(255), primary key (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;
create index IDX_KTPOPULAR_CATEGORY_SLUGTYPE on ktpopular_category (slug_type);