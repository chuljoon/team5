DROP TABLE gallery;

CREATE TABLE gallery(
  galleryno     NUMBER(7)        NOT NULL        PRIMARY KEY, -- 갤러리 번호
  memberno   NUMBER(7)                              NOT NULL, -- 회원 번호
  g_title         VARCHAR2(300)                        NOT NULL, -- 갤러리 제목
  g_content    CLOB                                      NOT NULL, -- 갤러리 내용
  g_cnt         NUMBER(7)        DEFAULT 0        NOT NULL, -- 갤러리 조회수
  thumbs       VARCHAR2(1000)                             NULL, -- Thumb 파일
  files           VARCHAR2(1000)                             NULL, -- 파일들의 이름
  sizes          VARCHAR2(1000)                             NULL, -- 파일들의 크기
  g_word       VARCHAR2(100)                                NULL, -- 검색어
  rdate         DATE                                             NULL, -- 등록 날짜
  FOREIGN KEY (memberno) REFERENCES MEMBER (memberno)
);

COMMENT ON TABLE gallery is '갤러리 영상';
COMMENT ON COLUMN gallery.galleryno is '갤러리 번호';
COMMENT ON COLUMN gallery.memberno is '회원 번호';
COMMENT ON COLUMN gallery.g_title is '영상 제목';
COMMENT ON COLUMN gallery.g_content is '영상 내용';
COMMENT ON COLUMN gallery.g_cnt is '영상 조회수';
COMMENT ON COLUMN gallery.thumbs is 'Thumb 파일';
COMMENT ON COLUMN gallery.files is '파일들의 이름';
COMMENT ON COLUMN gallery.sizes is '파일들의 크기';
COMMENT ON COLUMN gallery.g_word is '검색어';
COMMENT ON COLUMN gallery.rdate is '등록일';

1. 등록
INSERT INTO gallery(galleryno, memberno, g_title, g_content, g_cnt, thumbs, files, sizes, g_word, rdate)  
VALUES((SELECT NVL(MAX(galleryno), 0) + 1 as galleryno FROM gallery), 
            1, '아이유 10주년 콘서트', '아이유 10주년 콘서트', 0, 'fall_m.jpg', 'fall.jpg', 0, '아이유,10주년,콘서트', sysdate);

INSERT INTO gallery(galleryno, memberno, g_title, g_content, g_cnt, thumbs, files, sizes, g_word, rdate)  
VALUES((SELECT NVL(MAX(galleryno), 0) + 1 as galleryno FROM gallery), 
            1, '아이유 11주년 콘서트', '아이유 11주년 콘서트', 0, 'fall_m.jpg', 'fall.jpg', 0, '아이유,11주년,콘서트', sysdate);
            
INSERT INTO gallery(galleryno, memberno, g_title, g_content, g_cnt, thumbs, files, sizes, g_word, rdate)  
VALUES((SELECT NVL(MAX(galleryno), 0) + 1 as galleryno FROM gallery), 
            1, '아이유 12주년 콘서트', '아이유 12주년 콘서트', 0, 'fall_m.jpg', 'fall.jpg', 0, '아이유,12주년,콘서트', sysdate);            

2. 목록
SELECT * FROM gallery;
SELECT galleryno,
          memberno, g_title, g_content, g_cnt, thumbs, files, sizes, g_word, rdate
FROM gallery
ORDER BY rdate DESC;
 GALLERYNO MEMBERNO G_TITLE      G_CONTENT    G_CNT THUMBS     FILES    SIZES G_WORD       RDATE
 --------- -------- ------------ ------------ ----- ---------- -------- ----- ------------ ---------------------
         1        1 아이유 10주년 콘서트 아이유 10주년 콘서트     0 fall_m.jpg fall.jpg 0     아이유,10주년,콘서트 2019-01-28 09:56:25.0
         2        1 아이유 11주년 콘서트 아이유 11주년 콘서트     0 fall_m.jpg fall.jpg 0     아이유,11주년,콘서트 2019-01-28 09:56:57.0
         3        1 아이유 12주년 콘서트 아이유 12주년 콘서트     0 fall_m.jpg fall.jpg 0     아이유,12주년,콘서트 2019-01-28 09:56:58.0

       
3. 조회
SELECT galleryno,
          memberno, g_title, g_content, g_cnt, thumbs, files, sizes, g_word, rdate
FROM gallery
WHERE galleryno=2; 
 GALLERYNO MEMBERNO G_TITLE      G_CONTENT    G_CNT THUMBS     FILES    SIZES G_WORD       RDATE
 --------- -------- ------------ ------------ ----- ---------- -------- ----- ------------ ---------------------
         2        1 아이유 11주년 콘서트 아이유 11주년 콘서트     0 fall_m.jpg fall.jpg 0     아이유,11주년,콘서트 2019-01-28 09:56:57.0



4. 수정
UPDATE gallery
SET g_title='유인나', g_content='유인나와 식사',
     thumbs='dinner_m.jpg', files='fall.jpg', sizes= 1500, g_word='유인나, 저녁'
WHERE galleryno=2;
 GALLERYNO MEMBERNO G_TITLE G_CONTENT G_CNT THUMBS       FILES    SIZES G_WORD  RDATE
 --------- -------- ------- --------- ----- ------------ -------- ----- ------- ---------------------
         2        1 유인나     유인나와 식사       0 dinner_m.jpg fall.jpg 1500  유인나, 저녁 2019-01-28 09:56:57.0


5. 삭제
DELETE FROM gallery
WHERE galleryno=3;


6. 조회수 증가
UPDATE gallery
SET g_cnt = g_cnt + 1
WHERE galleryno=1;












