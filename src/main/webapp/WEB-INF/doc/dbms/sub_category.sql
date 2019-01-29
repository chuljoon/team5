DROP TABLE sub_category;

/**********************************/
/* Table Name: 서브 카테고리 */
/**********************************/

1. 테이블 등록
CREATE TABLE sub_category(
  s_categoryno      NUMBER(7)                                NOT NULL,
  m_categrpno       NUMBER(10)                               NOT NULL ,
  title                VARCHAR2(50)                            NOT NULL,
  seqno             NUMBER(3)        DEFAULT 1         NOT NULL,
  visible             CHAR(1)            DEFAULT 'Y'        NOT NULL,
  ids                  VARCHAR2(100)                                NULL,
  cnt                  NUMBER(6)       DEFAULT 0          NOT NULL,
  rdate               DATE                 NOT NULL,
  PRIMARY KEY(s_categoryno),
  FOREIGN KEY (m_categrpno) REFERENCES main_categrp (m_categrpno)
);

COMMENT ON TABLE sub_category is '서브 카테고리';
COMMENT ON COLUMN sub_category.s_categoryno is '서브 카테고리 번호';
COMMENT ON COLUMN sub_category.m_categrpno is '메인 카테고리 그룹 번호';
COMMENT ON COLUMN sub_category.title is '게시판 이름';
COMMENT ON COLUMN sub_category.seqno is '출력 순서';
COMMENT ON COLUMN sub_category.visible is '출력 모드';
COMMENT ON COLUMN sub_category.ids is '접근 계정';
COMMENT ON COLUMN sub_category.cnt is '등록된 글 수';
COMMENT ON COLUMN sub_category.rdate is '등록일';


2. 레코드 추가
INSERT INTO sub_category(s_categoryno, m_categrpno, title, seqno, visible, ids, rdate)
VALUES(1, 1, '아이돌', 1 , 'Y', 'admin', sysdate);

INSERT INTO sub_category(s_categoryno, m_categrpno, title, seqno, visible, ids, rdate)
VALUES(2, 1, '팬클럽/팬미팅', 1 , 'Y', 'admin', sysdate);

INSERT INTO sub_category(s_categoryno, m_categrpno, title, seqno, visible, ids, rdate)
VALUES(3, 1, '발라드/R&B', 1 , 'Y', 'admin', sysdate);

INSERT INTO sub_category(s_categoryno, m_categrpno, title, seqno, visible, ids, rdate)
VALUES((SELECT NVL(MAX(s_categoryno), 0) + 1 as s_categoryno FROM sub_category), 1, '테스트', 1 , 'Y', 'admin', sysdate);


3. 목록
SELECT s_categoryno, m_categrpno, title, seqno, visible, ids, rdate, cnt
FROM sub_category 
ORDER BY s_categoryno ASC;


4. 조회
SELECT s_categoryno, m_categrpno, title, seqno, visible, ids, rdate, cnt
FROM sub_category 
WHERE s_categoryno=1;

1) 전체 레코드 수
SELECT COUNT(*) as cnt
FROM sub_category


5. 수정
UPDATE sub_category
SET title='아이돌', seqno=1, visible='N', ids='admin'
WHERE s_categoryno=1;


6. 삭제
DELETE FROM sub_category 
WHERE s_categoryno = 3;


7. 출력순서
1) 출력 우선 순위 높임
UPDATE sub_category 
SET seqno = seqno - 1 
WHERE s_categoryno=1;

2) 출력 우선 순서 낮춤
UPDATE category 
SET seqno = seqno + 1 
WHERE categoryno=1;