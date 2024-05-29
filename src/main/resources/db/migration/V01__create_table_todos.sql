CREATE TABLE todos(
    id          SERIAl      NOT NULL PRIMARY KEY,
    name        VARCHAR(60) NOT NULL,
    description VARCHAR(120),
    done        BOOLEAN     DEFAULT FALSE,
    priority    INTEGER     NOT NULL
);