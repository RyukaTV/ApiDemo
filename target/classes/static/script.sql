drop database if exists ApiDemo;
create database ApiDemo;
use ApiDemo;

create or replace table `user`(
	id int auto_increment,
	name varchar(64),
	email varchar(255),
	primary key (id)
);