drop table if exists countries;

CREATE TABLE countries 
(
    id int NOT NULL,
    name varchar(255),
    capital varchar(255),
    PRIMARY KEY (id)
);