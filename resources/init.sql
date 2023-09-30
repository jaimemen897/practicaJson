DROP TABLE IF EXISTS POKEMON;
create table POKEMON
(
    id   INTEGER PRIMARY KEY,
    num  INTEGER,
    name VARCHAR(255),
    height DOUBLE,
    weight DOUBLE
);