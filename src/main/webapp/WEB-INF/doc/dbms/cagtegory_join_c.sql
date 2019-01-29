-- PK 테이블 데이터 추가
CREATE TABLE main_categrp(
    m_categrpno                         NUMBER(10)     NOT NULL    PRIMARY KEY,
    name                              VARCHAR2(50)     NOT NULL,
    seqno                             NUMBER(7)    DEFAULT 0     NOT NULL,
    visible                           CHAR(1)    DEFAULT 'Y'     NOT NULL,
    rdate                             DATE     NOT NULL
);

INSERT INTO main_categrp(m_categrpno, name, seqno, visible, rdate)
VALUES((SELECT NVL(MAX(m_categrpno), 0) + 1 as m_categrpno FROM main_categrp), '콘서트정보', 1, 'Y', sysdate);

INSERT INTO main_categrp(m_categrpno, name, seqno, visible, rdate)
VALUES((SELECT NVL(MAX(m_categrpno), 0) + 1 as m_categrpno FROM main_categrp), '공지사항', 1, 'Y', sysdate);

-- FK 테이블 데이터 추가
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

INSERT INTO sub_category(s_categoryno, m_categrpno, title, seqno, visible, ids, rdate)
VALUES((SELECT NVL(MAX(s_categoryno), 0) + 1 as s_categoryno FROM sub_category), 1, '아이돌', 1 , 'Y', 'admin', sysdate);

INSERT INTO sub_category(s_categoryno, m_categrpno, title, seqno, visible, ids, rdate)
VALUES((SELECT NVL(MAX(s_categoryno), 0) + 1 as s_categoryno FROM sub_category), 1, '팬클럽/팬미팅', 1 , 'Y', 'admin', sysdate);

-- PK 테이블 데이터 확인
SELECT m_categrpno, name, seqno, visible, rdate
FROM main_categrp
ORDER BY m_categrpno ASC;

-- FK 테이블 데이터 확인
SELECT s_categoryno, m_categrpno, title, seqno, visible, ids, rdate, cnt
FROM sub_category 
ORDER BY s_categoryno ASC;


1.Cross join
- 정보로서의 가치가 매우 부족함.
- 권장하지 않음.
 
SELECT main_categrp.m_categrpno, main_categrp.name,
           sub_category.s_categoryno, sub_category.m_categrpno, sub_category.title,
           sub_category.seqno, sub_category.visible, sub_category.ids, sub_category.cnt
FROM main_categrp, sub_category
ORDER BY main_categrp.m_categrpno ASC, sub_category.seqno ASC;
 
-- 테이블 별명의 사용
-- categrp c: 테이블명이 너무 길어 categrp 테이블의 별명을 'c'로 붙임.
SELECT c.m_categrpno, c.name,
           t.s_categoryno, t.m_categrpno, t.title, t.seqno, t.visible, t.ids, t.cnt
FROM main_categrp c, sub_category t  
ORDER BY c.m_categrpno ASC, t.seqno ASC;


2. Equal JOIN시 FK 테이블을 기준으로 합니다.
- WHERE 그룹(부모) 테이블 PK = 데이터(자식) 테이블 FK
- WHERE c.categrpno = t.categrpno: 2개의 테이블에서 categrpno 컬럼이 같은
  레코드를 읽어 메모리상에서 하나의 레코드로 결합하여 메모리 테이블을
  생성합니다. (DBMS는 많은 메모리 사용)
 
SELECT c.m_categrpno, c.name,
          t.s_categoryno, t.m_categrpno, t.title, t.seqno, t.visible, t.ids, t.cnt
FROM main_categrp c, sub_category t  
WHERE c.m_categrpno = t.m_categrpno
ORDER BY c.m_categrpno ASC, t.seqno ASC;
  
-- seqno 컬럼의 중복
    SELECT c.m_categrpno, c.name, c.seqno,
               t.s_categoryno, t.m_categrpno, t.title, t.seqno, t.visible, t.ids, t.cnt
    FROM main_categrp c, sub_category t  
    WHERE c.m_categrpno = t.m_categrpno
    ORDER BY c.m_categrpno ASC, t.seqno ASC;
          
-- seqno 컬럼의 중복, category_seqno 처럼 컬럼 별명을 지정하여 해결
    SELECT c.m_categrpno, c.name, c.seqno,
               t.s_categoryno, t.m_categrpno, t.title, t.seqno as sub_category_seqno, t.visible, t.ids, t.cnt
    FROM main_categrp c, sub_category t  
    WHERE c.m_categrpno = t.m_categrpno
    ORDER BY c.m_categrpno ASC, t.seqno ASC;
 
    
3. LEFT Outer JOIN
- 부모테이블에만 있고 자식 테이블에는 대응하는 레코드가 없는 경우의 출력
- category FK 테이블에 '+' 선언을하면 레코드 대응이 없어도 
  NULL 값으로 대응하여 출력
 
INSERT INTO main_categrp(m_categrpno, name, seqno, visible, rdate)
VALUES((SELECT NVL(MAX(m_categrpno), 0) + 1 as m_categrpno FROM main_categrp), '테스트', 1, 'Y', sysdate);
 
SELECT * FROM main_categrp ORDER BY m_categrpno ASC;
 
-- FK 테이블에 '+' 기호를 선언하면 PK 테이블이 모두 출력됨 
SELECT c.m_categrpno, c.name,  
          t.s_categoryno, t.m_categrpno, t.title, t.seqno, t.visible, t.ids, t.cnt
FROM main_categrp c, sub_category t  
WHERE c.m_categrpno = t.m_categrpno(+)
ORDER BY c.m_categrpno ASC, t.seqno ASC;

 M_CATEGRPNO NAME  S_CATEGORYNO M_CATEGRPNO TITLE   SEQNO VISIBLE IDS   CNT
 ----------- ----- ------------ ----------- ------- ----- ------- ----- ----
           1 콘서트정보            1           1 아이돌         1 Y       admin    0
           1 콘서트정보            2           1 팬클럽/팬미팅     1 Y       admin    0
           2 공지사항          NULL        NULL NULL     NULL NULL    NULL  NULL
           3 테스트           NULL        NULL NULL     NULL NULL    NULL  NULL
 
-- ANSI Left Outer Join: 왼쪽 테이블 모두 출력
SELECT c.m_categrpno, c.name,  
          t.s_categoryno, t.m_categrpno, t.title, t.seqno, t.visible, t.ids, t.cnt
FROM main_categrp c LEFT OUTER JOIN sub_category t
ON c.m_categrpno = t.m_categrpno
ORDER BY c.m_categrpno ASC, t.seqno ASC;
 
 M_CATEGRPNO NAME  S_CATEGORYNO M_CATEGRPNO TITLE   SEQNO VISIBLE IDS   CNT
 ----------- ----- ------------ ----------- ------- ----- ------- ----- ----
           1 콘서트정보            1           1 아이돌         1 Y       admin    0
           1 콘서트정보            2           1 팬클럽/팬미팅     1 Y       admin    0
           2 공지사항          NULL        NULL NULL     NULL NULL    NULL  NULL
           3 테스트           NULL        NULL NULL     NULL NULL    NULL  NULL
 
-- ANSI Right Outer Join, 
-- 무결성 제약조건의 손상으로 PK 없는 FK는 등록 불가하여 Equal(동등)과 같은 결과를
-- 출력함, 정보로서의 가치는 없음. 
SELECT c.m_categrpno, c.name,  
          t.s_categoryno, t.m_categrpno, t.title, t.seqno, t.visible, t.ids, t.cnt
FROM main_categrp c RIGHT OUTER JOIN sub_category t
ON c.m_categrpno = t.m_categrpno
ORDER BY c.m_categrpno ASC, t.seqno ASC;
 
 M_CATEGRPNO NAME  S_CATEGORYNO M_CATEGRPNO TITLE   SEQNO VISIBLE IDS   CNT
 ----------- ----- ------------ ----------- ------- ----- ------- ----- ---
           1 콘서트정보            1           1 아이돌         1 Y       admin   0
           1 콘서트정보            2           1 팬클럽/팬미팅     1 Y       admin   0
          
-- from절의 테이블의 위치를 변경함.
SELECT c.m_categrpno, c.name,  
          t.s_categoryno, t.m_categrpno, t.title, t.seqno, t.visible, t.ids, t.cnt
FROM sub_category t RIGHT OUTER JOIN main_categrp c 
ON c.m_categrpno = t.m_categrpno
ORDER BY c.m_categrpno ASC, t.seqno ASC;