create table division(id int primary key, name varchar(60) not null);

create table district(id int primary key,division_id int, name varchar(60) not null, foreign key(division_id) references division(id));

create table township(id int primary key,district_id int, name varchar(60) not null, foreign key(district_id) references district(id));