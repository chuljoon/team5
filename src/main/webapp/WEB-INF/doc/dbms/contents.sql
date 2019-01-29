DROP TABLE contents;
DROP TABLE expect;

/**********************************/
/* Table Name: 공연정보 */
/**********************************/

1. 테이블 생성
CREATE TABLE contents(
  contentsno        NUMBER(7)                    NOT NULL,
  s_categoryno      NUMBER(7)                    NOT NULL,
  title                 VARCHAR2(100)               NOT NULL,
  title_img           VARCHAR2(100)               NOT NULL,  
  thumbs              VARCHAR2(100)                     NULL,
  sizes                 NUMBER(10)                         NULL,
  passwd             VARCHAR2(100)                  NOT NULL,
  contents            CLOB                             NOT NULL,
  files                  VARCHAR2(100)                     NULL,
  file_size             NUMBER(10)                          NULL,
  p_date              VARCHAR2(100)              NOT NULL,
  word                VARCHAR2(100)                     NULL,
  rdate               DATE                            NOT NULL,
  PRIMARY KEY(contentsno),
  FOREIGN KEY (s_categoryno) REFERENCES sub_category (s_categoryno)
);

COMMENT ON TABLE contents is '공연정보';
COMMENT ON COLUMN contents.contentsno is '공연정보 번호';
COMMENT ON COLUMN contents.s_categoryno is '서브 카테고리 번호';
COMMENT ON COLUMN contents.hallno is '공연장 번호';
COMMENT ON COLUMN contents.title is '공연명';
COMMENT ON COLUMN contents.title_img is '공연포스터';
COMMENT ON COLUMN contents.thumbs is '썸네일';
COMMENT ON COLUMN contents.sizes is '사진 사이즈';
COMMENT ON COLUMN contents.passwd is '패스워드';
COMMENT ON COLUMN contents.contents is '내용';
COMMENT ON COLUMN contents.files is '작품설명사진';
COMMENT ON COLUMN contents.file_size is '작품설명 사이즈';
COMMENT ON COLUMN contents.p_date is '공연 일시';
COMMENT ON COLUMN contents.word is '검색어';
COMMENT ON COLUMN contents.rdate is '등록일';


2. 등록
INSERT INTO contents(contentsno, s_categoryno, title, title_img, thumbs,
                             sizes, passwd, contents, files, file_size,p_date, rdate)
VALUES((SELECT NVL(MAX(contentsno), 0) + 1 as contentsno FROM contents),
            1, '인피니트', 'infinite.jpg', 'infinite_t.jpg', 0,  '1234', '인피니트팬미팅', 'fanmeeting.jpg', 0, '2019-01-12', sysdate);

INSERT INTO contents(contentsno, s_categoryno, title, title_img, 
                             passwd, contents, thumbs, files, sizes, rdate)
VALUES(2, 1, '제목', '01.jpg', '1234', '내용', '01_t.jpg', 'f01.jpg', 0, sysdate);

INSERT INTO contents(contentsno, s_categoryno, title, title_img, 
                             passwd, contents, thumbs, files, sizes, rdate)
VALUES(3, 1, '제목2', '02.jpg', '1234', '내용2', '02_t.jpg', 'f02.jpg', 0, sysdate);


3. 목록
SELECT contentsno, s_categoryno, title, title_img, thumbs, sizes, passwd, contents, files, file_size, p_date, rdate
FROM contents
ORDER BY contentsno DESC;

1) 검색
SELECT contentsno, s_categoryno, title, title_img, thumbs, sizes, passwd, contents, files, file_size, p_date, rdate
FROM contents
WHERE title='제목';

-- '제목' 컬럼으로 검색
SELECT contentsno, s_categoryno, title, title_img, passwd, contents, thumbs, files, sizes, rdate
FROM contents
WHERE title LIKE '%아이유%'
ORDER BY contentsno DESC;

2) 페이징
-- step 1
SELECT contentsno, s_categoryno, title, title_img, thumbs, sizes, passwd, contents, files, file_size, p_date, rdate
FROM contents
WHERE s_categoryno=1
ORDER BY contentsno DESC;

-- step 2
SELECT contentsno, s_categoryno, title, title_img, thumbs, sizes, passwd, contents, files, file_size, p_date, rdate, rownum as r
FROM(
        SELECT contentsno, s_categoryno, title, title_img, thumbs, sizes, passwd, contents, files, file_size, p_date, rdate
        FROM contents
        WHERE s_categoryno=1
        ORDER BY contentsno DESC
);

-- step 3
SELECT contentsno, s_categoryno, title, title_img, thumbs, sizes, passwd, contents, files, file_size, p_date, rdate, r
FROM(
SELECT contentsno, s_categoryno, title, title_img, thumbs, sizes, passwd, contents, files, file_size, p_date, rdate, rownum as r
FROM(
        SELECT contentsno, s_categoryno, title, title_img, thumbs, sizes, passwd, contents, files, file_size, p_date, rdate
        FROM contents
        WHERE s_categoryno=1
        ORDER BY contentsno DESC
        )
)
WHERE r >= 1 AND r <= 3;

-- 검색
SELECT contentsno, s_categoryno, title, title_img, passwd, contents, thumbs, files, sizes, rdate, r
FROM(
SELECT contentsno, s_categoryno, title, title_img, passwd, contents, thumbs, files, sizes, rdate, rownum as r
FROM(
        SELECT contentsno, s_categoryno, title, title_img, passwd, contents, thumbs, files, sizes, rdate
        FROM contents
        WHERE s_categoryno=1 AND title LIKE '%인피니트%'
        ORDER BY contentsno DESC
        )
)
WHERE r >= 1 AND r <= 3;


4. 조회
SELECT contentsno, s_categoryno, title, title_img, passwd, contents, thumbs, files, sizes, rdate
FROM contents
WHERE contentsno=1;

1) 전체 레코드 수
SELECT COUNT(contentsno) as cnt
FROM contents


5. 수정
UPDATE contents
SET title='인피니트', contents='인피니트팬미팅최고', thumbs='infinite_t.jpg', files='infifan.jpg', sizes=1500
WHERE contentsno=1;


6. 삭제
DELETE FROM contents
WHERE contentsno=4;