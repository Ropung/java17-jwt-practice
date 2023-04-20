CREATE SCHEMA IF NOT EXISTS joara_basic;

CREATE TABLE IF NOT EXISTS joara_basic.user (
    id                  UUID,
    email               VARCHAR(30)                         NOT NULL        UNIQUE,
    password            VARCHAR(100)                        NOT NULL,
    nickname            VARCHAR(30)                         NOT NULL        UNIQUE,
    status              VARCHAR(30)                         NOT NULL,
    created_at          DATE                                NOT NULL,
    gender              CHAR(1)                             NOT NULL,

    CONSTRAINT          pk_user_id                          PRIMARY KEY(id)
);

CREATE UNIQUE INDEX udx_user_email ON joara_basic.user(email);
