DROP TABLE expect;

/**********************************/
/* Table Name: 기대평 */
/**********************************/

1. 테이블 생성
CREATE TABLE expect(
  expectno          NUMBER(7)                     NOT NULL,
  contentsno      NUMBER(7)                      NOT NULL,
  content           CLOB                              NOT NULL,
  imgs                 VARCHAR2(100)                      NULL,
  thumbs             VARCHAR2(100)                       NULL,
  sizes                 NUMBER(10)                           NULL,
  rdate               DATE                            NOT NULL,
  PRIMARY KEY(expectno),
  FOREIGN KEY (contentsno) REFERENCES contents (contentsno)
);

COMMENT ON TABLE expect is '기대평';
COMMENT ON COLUMN expect.expectno is '기대평 번호';
COMMENT ON COLUMN expect.contentsno is '공연정보 번호';
COMMENT ON COLUMN expect.content is '기대평 내용';
COMMENT ON COLUMN expect.imgs is '사진';
COMMENT ON COLUMN expect.thumbs is '썸네일';
COMMENT ON COLUMN expect.sizes is '사이즈';
COMMENT ON COLUMN expect.rdate is '등록일';


2. 등록
INSERT INTO expect(expectno, contentsno, content, imgs, thumbs, sizes, rdate)
VALUES(1, 1, '1234', '인피니트 너무 좋아요 암표금지', 'infilove.jpg', 'infinite.mp4', '내꺼하자.mp3', 'http://woolliment.phps.kr/artists/main_infinite.php', sysdate);

INSERT INTO expect(expectno, contentsno, passwd, content, rdate)
VALUES(2, 2, '1234', '기대평', sysdate);

INSERT INTO expect(expectno, contentsno, passwd, content, rdate)
VALUES(3, 3, '1234', '기대평2', sysdate);


3. 목록
SELECT expectno, contentsno, content, imgs, thumbs, sizes, rdate
FROM expect
ORDER BY expectno DESC;

SELECT expectno, contentsno, content, imgs, thumbs, sizes, rdate
FROM expect
WHERE contentsno=1
ORDER BY expectno DESC;

1) 페이징
-- step 1
SELECT expectno, contentsno, passwd, content, imgs, rdate
FROM expect
WHERE contentsno=1
ORDER BY expectno DESC;

-- step 2
SELECT expectno, contentsno, passwd, content, imgs, mp4, mp3, url, rdate, rownum as r
FROM(
        SELECT expectno, contentsno, passwd, content, imgs, mp4, mp3, url, rdate
        FROM expect
        WHERE contentsno=1
        ORDER BY expectno DESC
);

-- step 3
SELECT expectno, contentsno, passwd, content, imgs, mp4, mp3, url, rdate, r
FROM(
SELECT expectno, contentsno, passwd, content, imgs, mp4, mp3, url, rdate, rownum as r
FROM(
        SELECT expectno, contentsno, passwd, content, imgs, mp4, mp3, url, rdate
        FROM expect
        WHERE contentsno=1
        ORDER BY expectno DESC
        )
)
WHERE r >= 1 AND r <= 3;


4. 조회
SELECT expectno, contentsno, content, imgs, thumbs, sizes, rdate
FROM expect
WHERE expectno=1;

1) 전체 레코드 수
SELECT COUNT(expectno) as cnt
FROM expect


5. 수정
UPDATE expect
SET content='인피니트 사랑해요 꺅 암표 사라져'
WHERE expectno=1;


6. 삭제
DELETE FROM expect
WHERE expectno=1;