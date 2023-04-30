

--작품(BookGenre)장르 테이블
CREATE TABLE IF NOT EXISTS joara_basic.book_genre (
    id                  UUID                                PRIMARY KEY,
    genre_name          VARCHAR(30)                         NOT NULL
);

--작품(Book)테이블,  SEQUENCE 추가
CREATE TABLE IF NOT EXISTS joara_basic.book (
    id                  UUID                                PRIMARY KEY,
    member_id           UUID                                ,
    genre_id            UUID                                ,
    isbn                VARCHAR(50)                         ,
    cip                 VARCHAR(50)                         ,
    title               VARCHAR(30)                         NOT NULL,
    description         TEXT                                NOT NULL,
    url                 VARCHAR(255)                        ,
    avg_score           DOUBLE PRECISION                    ,
    create_at           DATE                                NOT NULL,
    update_at           DATE                                ,
    delete_at           DATE
);

--작품태그 목록(BookTag)테이블
CREATE TABLE IF NOT EXISTS joara_basic.book_tag (
    id                  UUID                                PRIMARY KEY,
    book_id             UUID                                NOT NULL,
    book_free_tag_id    UUID                                NOT NULL
);
--작품태그(BookFreeTag)테이블
--CREATE TABLE IF NOT EXISTS joara_basic.book_tag (
--    id                  UUID                                PRIMARY KEY,
--    book_tag_name       VARCHAR(30)                         NOT NULL
--);

----작품(책) 디폴트 생성
--INSERT INTO joara_basic.book(id,member_id, book_num,book_title, book_description)
--VALUES ('ebce6e70-4768-48dc-9ee6-0867af44b2a1','62e9d74f-9524-4c14-92cb-0d2e47b86c01',nextval('seq_book'),'book_title1','book_description1');
--INSERT INTO joara_basic.book(id,member_id, book_num,book_title, book_description)
--VALUES ('ebce6e70-4768-48dc-9ee6-0867af44b2a2','62e9d74f-9524-4c14-92cb-0d2e47b86c02',nextval('seq_book'),'book_title1','book_description1');