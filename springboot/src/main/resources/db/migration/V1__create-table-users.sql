CREATE TABLE users
(
    id       TEXT                    PRIMARY KEY NOT NULL UNIQUE,
    login    TEXT                    NOT NULL UNIQUE,
    password TEXT                    NOT NULL,
    role     TEXT                    NOT NULL
);