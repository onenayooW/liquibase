create table kbuser_contact (id bigint not null auto_increment, created_at datetime, deleted_at datetime, sorting_order bigint, edited_at datetime, updated_at datetime, version bigint, address varchar(255), customer_type varchar(255), email varchar(255), full_name varchar(255), line_id varchar(255), mobile_no varchar(255), province varchar(255), remark json, user_hash_id varchar(255), primary key (id));
create index IDX_KBUSERCONTACT_MOBILE on kbuser_contact (mobile_no);
alter table kbuser_contact add constraint IDX_KBUSERCONTACT_FULLNAME unique (full_name);
alter table kbuser_contact add constraint IDX_KBUSERCONTACT_EMAIL unique (email);
alter table kbcard add column json_detail json;
alter table kbcard add column contact_user_id bigint;
alter table kbcard add constraint FK49tqb4ldad1akr902e1u6w13e foreign key (contact_user_id) references kbuser_contact (id);
alter table kbcard drop column contact_address;
alter table kbcard drop column contact_email;
alter table kbcard drop column contact_name;
alter table kbcard drop column contact_number;


create index IDX_KBLANE_TITLE on kblane (title);
alter table kblane modify title varchar(255) not null;

alter table kbboard add column members json;