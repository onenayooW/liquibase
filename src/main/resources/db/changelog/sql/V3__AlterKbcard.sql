alter table kbcard add column comments json;
drop table kbcomment;

alter table kbcard modify margin_price DOUBLE null;
alter table kbcard modify price DOUBLE null;

