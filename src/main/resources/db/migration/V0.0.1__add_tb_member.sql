CREATE TABLE IF NOT EXISTS study_basic.member (
    id              UUID,
    email           VARCHAR(30)         NOT NULL    UNIQUE,
    password        VARCHAR(100)        NOT NULL,
    nickname        VARCHAR(30)         NOT NULL    UNIQUE,
    status          VARCHAR(30)         NOT NULL,
    created_at      DATE                NOT NULL,
    gender          CHAR(1)             NOT NULL,
    CONSTRAINT      pk_member_id        PRIMARY KEY(id)
);

CREATE UNIQUE INDEX udx_member_email ON study_basic.member(email);
