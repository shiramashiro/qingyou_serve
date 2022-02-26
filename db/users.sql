create table users(
    id int(20) primary key,
    password varchar(20) unique,
    uname varchar(15) unique,
    phone int(14) unique,
    email varchar(30) unique,
    created_date datetime,
    birthday datetime,
    signature varchar(30),
    sex varchar(5),
    location varchar(30),
    constellation varchar(8),
    age int(5),
    avatar varchar(20),
    occupation varchar(10)
);