create database store_ms;

create table category (
id int not null primary key auto_increment,
name varchar(20),
created timestamp,
is_active boolean
);

create table product (
id int not null primary key auto_increment,
name varchar(20),
description varchar(120),
category_id int,
unit_price double,
created timestamp,
is_active boolean,
FOREIGN KEY (category_id) REFERENCES category(id)
     ON UPDATE CASCADE ON DELETE RESTRICT
);

create table city (
id int not null primary key auto_increment,
name varchar(20)
);

create table store (
id int not null primary key auto_increment,
name varchar(20),
city_id int,
address varchar(45),
FOREIGN KEY (city_id) REFERENCES city(id)
     ON UPDATE CASCADE ON DELETE RESTRICT
);

create table sales (
id int not null primary key auto_increment,
store_id int,
product_id int,
sold_on timestamp,
amount int,
total_price double,
FOREIGN KEY (store_id) REFERENCES store(id)
   ON UPDATE CASCADE ON DELETE RESTRICT,
FOREIGN KEY (product_id) REFERENCES product(id)
  ON UPDATE CASCADE ON DELETE RESTRICT
);
