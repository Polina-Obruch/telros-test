CREATE TABLE IF NOT EXISTS users
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(200) NOT NULL ,
    lastname   VARCHAR(200) NOT NULL ,
    email      VARCHAR(254) NOT NULL UNIQUE,
    phone      VARCHAR(20)  NOT NULL UNIQUE,
    patronymic VARCHAR(200),
    birthdate  DATE
);

CREATE TABLE IF NOT EXISTS images
(
    id  BIGSERIAL PRIMARY KEY,
    file_name VARCHAR(255) UNIQUE,
    mime_type VARCHAR(30),
    user_id   BIGSERIAL NOT NULL UNIQUE,
    data bytea,
    FOREIGN KEY (user_id) REFERENCES USERS (id)
);