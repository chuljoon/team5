DROP TABLE notice;

/**********************************/
/* Table Name: 공지사항 */
/**********************************/

1. 테이블 생성
CREATE TABLE notice(
  noticeno          NUMBER(7)                    NOT NULL,
  title                 VARCHAR2(100)               NOT NULL,
  content            CLOB                           NOT NULL,
  rdate               DATE                            NOT NULL,
  PRIMARY KEY(noticeno)
);

COMMENT ON TABLE notice is '공지사항';
COMMENT ON COLUMN notice.noticeno is '공지사항 번호';
COMMENT ON COLUMN notice.title is '공지사항 제목';
COMMENT ON COLUMN notice.content is '공지사항 내용';
COMMENT ON COLUMN notice.rdate is '등록일';


2. 등록
INSERT INTO notice(noticeno, title, content, rdate)
VALUES(1, '공지사항', '공지사항입니다.', sysdate);

INSERT INTO notice(noticeno, title, content, rdate)
VALUES(2, '공지사항2', '공지사항2입니다.', sysdate);

INSERT INTO notice(noticeno, title, content, rdate)
VALUES(3, '공지사항3', '공지사항3입니다.', sysdate);

INSERT INTO notice(noticeno, title, content, rdate)
VALUES((SELECT NVL(MAX(noticeno), 0) + 1 as noticeno FROM notice), '공지사항4', '공지사항4입니다.', sysdate);


3. 목록
SELECT noticeno, title, content, rdate
FROM notice
ORDER BY noticeno DESC;

1) 페이징
-- step 1
SELECT noticeno, title, content, rdate
FROM notice
WHERE noticeno=1
ORDER BY noticeno DESC;

-- step 2
SELECT noticeno, title, content, rdate, rownum as r
FROM (
          SELECT noticeno, title, content, rdate
          FROM notice
          WHERE noticeno=1
          ORDER BY noticeno DESC
);

-- step 3
SELECT noticeno, title, content, rdate, r
FROM (
SELECT noticeno, title, content, rdate, rownum as r
FROM (
          SELECT noticeno, title, content, rdate
          FROM notice
          WHERE noticeno=1
          ORDER BY noticeno DESC
          )
)
WHERE r >= 1 AND r<= 3;


4. 조회
SELECT noticeno, title, content, rdate
FROM notice
WHERE noticeno=1;

1) 전체 레코드 수
SELECT COUNT(noticeno) as cnt
FROM notice


5. 수정
UPDATE notice
SET title='공지사항4', content='공지사항4 입니다.'
WHERE noticeno=1;


6. 삭제
DELETE FROM notice
WHERE noticeno=1;