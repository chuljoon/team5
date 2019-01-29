DROP TABLE hall;

/**********************************/
/* Table Name: 공연장 */
/**********************************/

1. 테이블 제작
CREATE TABLE hall(
  hallno             NUMBER(7)                     NOT NULL,
  title                 VARCHAR2(50)                NOT NULL,
  content           CLOB                              NOT NULL,
  img                VARCHAR2(100)                       NULL,
  thumbs             VARCHAR2(100)                      NULL,
  sizes                NUMBER(10)                           NULL,
  map               VARCHAR2(2000)                       NULL,
  rdate               DATE                            NOT NULL,
  PRIMARY KEY(hallno)
);

COMMENT ON TABLE hall is '공연장 정보';
COMMENT ON COLUMN hall.hallno is '공연장 번호';
COMMENT ON COLUMN hall.title is '공연장 이름';
COMMENT ON COLUMN hall.content is '공연장 내용';
COMMENT ON COLUMN hall.img is '사진';
COMMENT ON COLUMN hall.thumbs is '썸네일';
COMMENT ON COLUMN hall.sizes is '사이즈';
COMMENT ON COLUMN hall.map is '지도';
COMMENT ON COLUMN hall.rdate is '등록일';


2. 등록
INSERT INTO hall(hallno, title, content, img, thumbs, sizes, map, rdate)
VALUES((SELECT NVL(MAX(hallno), 0) + 1 as hallno FROM hall), '고려대학교 화정체육관', '서울 성북구 안암로 145 (안암동5가, 고려대학교안암캠퍼스 자연계)', 
            'korea.jpg', 'daum map', sysdate);
            
INSERT INTO hall(hallno, contentsno, title, content, img, map, rdate)
VALUES(2, 2, '올림픽공원 SK올림픽핸드볼경기장', '서울 송파구 방이동 88', 
            'olympicpark.jpg', 'daum map', sysdate);
            
INSERT INTO hall(hallno, contentsno, title, content, img, map, rdate)
VALUES(3, 3, '경희대학교 평화의전당', '서울 동대문구 회기동 1-5', 
            'kyunghee.jpg', 'daum map', sysdate);
            
            
3. 목록
SELECT hallno, title, content, img, thumbs, sizes, map, rdate
FROM hall
ORDER BY hallno DESC;


4. 조회
SELECT hallno, title, content, img, thumbs, sizes, map, rdate
FROM hall
WHERE hallno=1;

1) 전체 레코드 수
SELECT COUNT(hallno) as cnt
FROM hall


5. 수정
UPDATE hall
SET title='잠실실내체육관', content='서울 송파구 올림픽로 25 (잠실동, 서울종합운동장)'
WHERE hallno=1;


6. 삭제
DELETE FROM hall
WHERE hallno=1;