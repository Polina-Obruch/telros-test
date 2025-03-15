CREATE TABLE IF NOT EXISTS users
(
    id         BIGSERIAL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name       VARCHAR(200) NOT NULL ,
    lastname   VARCHAR(200) NOT NULL ,
    email      VARCHAR(254) NOT NULL UNIQUE,
    phone      VARCHAR(20)  NOT NULL UNIQUE,
    patronymic VARCHAR(200),
    birthdate  DATE
);