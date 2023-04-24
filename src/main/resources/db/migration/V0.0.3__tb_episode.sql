
--회차좋아요(EpiLove)
CREATE TABLE IF NOT EXISTS joara_basic.epi_love (
    id                  UUID                                PRIMARY KEY,
    member_id           UUID                                ,
    epi_id              UUID                                NOT NULL,
    is_epi              BOOLEAN                             NOT NULL DEFAULT false
);



--회차(Epi)테이블,  SEQUENCE 추가
CREATE TABLE IF NOT EXISTS joara_basic.epi (
    id                  UUID                                PRIMARY KEY,
    book_id             UUID                                NOT NULL,
    epi_title           VARCHAR(30)                         NOT NULL,
    epi_content         TEXT                                NOT NULL,
    epi_review          VARCHAR(30)                         ,
    epi_cover           VARCHAR(255)                        ,
    epi_view_count      BIGINT                              DEFAULT 0,
    epi_status          VARCHAR(30)                         DEFAULT 'PENDING',
    create_at           DATE                                NOT NULL,
    update_at           DATE                                ,
    delete_at           DATE
);


--회차(EpiComment)댓글 테이블,  SEQUENCE 추가

CREATE TABLE IF NOT EXISTS joara_basic.epi_reply (
    id                  UUID                                PRIMARY KEY,
    epi_id              UUID                                NOT NULL,
    epi_reply_content   VARCHAR(255)                        NOT NULL,
    create_at           DATE                                NOT NULL,
    update_at           DATE                                ,
    delete_at           DATE
);

--회차(EpiReply)댓글 테이블,  SEQUENCE 추가
CREATE TABLE IF NOT EXISTS joara_basic.epi_sub_reply (
    id                  UUID                                PRIMARY KEY,
    epi_sub_reply_id    UUID                                NOT NULL,
    epi_sub_reply_content   VARCHAR(255)                    NOT NULL,
    create_at           DATE                                NOT NULL,
    update_at           DATE                                ,
    delete_at           DATE
);
