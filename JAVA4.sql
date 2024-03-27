CREATE DATABASE JAVA4
use java4
CREATE TABLE Users(
    id varchar(100) PRIMARY KEY,
    password varchar(100),
    fullname nvarchar(100),
    email nvarchar(100),
    admin tinyint
);	

CREATE TABLE Videos(
    id varchar(100) PRIMARY KEY,
    title nvarchar(200) ,
    poster nvarchar(200),
    description nvarchar(200),
    views int,
    active tinyint
);

CREATE TABLE Favorites(
    id BIGINT PRIMARY KEY IDENTITY (1, 1),
    userid varchar(100),
    videoid varchar(100),
    likedate DATE,
    FOREIGN KEY (userid) REFERENCES Users(id),
    FOREIGN KEY (videoid) REFERENCES Videos(id)
);