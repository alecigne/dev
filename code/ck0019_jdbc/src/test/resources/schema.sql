drop schema if exists "java-adventures-jdbc";
create schema "java-adventures-jdbc";

drop table if exists "PERSON";
create table "PERSON" (
	"ID" bigint(20) not null auto_increment,
	"NAME" varchar(255) default null,
	"AGE" int default null,
	primary key ("ID")
);

insert into "PERSON" values (1, 'Foobar', 25);
insert into "PERSON" values (2, 'Quux', 42);