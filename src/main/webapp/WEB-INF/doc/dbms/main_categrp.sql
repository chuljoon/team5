DROP TABLE main_categrp;

/**********************************/
/* Table Name: 메인 카테고리 */
/**********************************/

1. 테이블 생성
CREATE TABLE main_categrp(
    m_categrpno                         NUMBER(10)     NOT NULL    PRIMARY KEY,
--  classification                    CHAR(1)    DEFAULT 1     NOT NULL,
    name                              VARCHAR2(50)     NOT NULL,
    seqno                             NUMBER(7)    DEFAULT 0     NOT NULL,
    visible                           CHAR(1)    DEFAULT 'Y'     NOT NULL,
    rdate                             DATE     NOT NULL
);

COMMENT ON TABLE main_categrp is '메인 카테고리 그룹';
COMMENT ON COLUMN main_categrp.m_categrpno is '메인 카테고리 그룹 번호';
-- COMMENT ON COLUMN main_categrp.classification is '메인 카테고리 그룹 분류';
COMMENT ON COLUMN main_categrp.name is '이름';
COMMENT ON COLUMN main_categrp.seqno is '출력 순서';
COMMENT ON COLUMN main_categrp.visible is '출력 모드';
COMMENT ON COLUMN main_categrp.rdate is '그룹 생성일';


2. 레코드 등록
INSERT INTO main_categrp(m_categrpno, name, seqno, visible, rdate)
VALUES(1, '콘서트정보', 1, 'Y', sysdate);

INSERT INTO main_categrp(m_categrpno, name, seqno, visible, rdate)
VALUES(2, '공지사항', 1, 'Y', sysdate);

INSERT INTO main_categrp(m_categrpno, name, seqno, visible, rdate)
VALUES((SELECT NVL(MAX(m_categrpno), 0) + 1 as m_categrpno FROM main_categrp), '테스트', 1, 'Y', sysdate);


3. 목록
SELECT m_categrpno, name, seqno, visible, rdate
FROM main_categrp
ORDER BY m_categrpno ASC;

4. 조회
SELECT m_categrpno, name, seqno, visible, rdate
FROM main_categrp
WHERE m_categrpno =1;

5. 전체 레코드 수
SELECT COUNT(*) as cnt
FROM main_categrp

6. 수정
UPDATE main_categrp
SET name='콘서트정보',
WHERE m_categrpno = 1;

7. 삭제
DELETE FROM main_categrp
WHERE m_categrpno = 8;