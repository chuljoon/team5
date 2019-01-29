-- PK ���̺� ������ �߰�
CREATE TABLE main_categrp(
    m_categrpno                         NUMBER(10)     NOT NULL    PRIMARY KEY,
    name                              VARCHAR2(50)     NOT NULL,
    seqno                             NUMBER(7)    DEFAULT 0     NOT NULL,
    visible                           CHAR(1)    DEFAULT 'Y'     NOT NULL,
    rdate                             DATE     NOT NULL
);

INSERT INTO main_categrp(m_categrpno, name, seqno, visible, rdate)
VALUES((SELECT NVL(MAX(m_categrpno), 0) + 1 as m_categrpno FROM main_categrp), '�ܼ�Ʈ����', 1, 'Y', sysdate);

INSERT INTO main_categrp(m_categrpno, name, seqno, visible, rdate)
VALUES((SELECT NVL(MAX(m_categrpno), 0) + 1 as m_categrpno FROM main_categrp), '��������', 1, 'Y', sysdate);

-- FK ���̺� ������ �߰�
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
VALUES((SELECT NVL(MAX(s_categoryno), 0) + 1 as s_categoryno FROM sub_category), 1, '���̵�', 1 , 'Y', 'admin', sysdate);

INSERT INTO sub_category(s_categoryno, m_categrpno, title, seqno, visible, ids, rdate)
VALUES((SELECT NVL(MAX(s_categoryno), 0) + 1 as s_categoryno FROM sub_category), 1, '��Ŭ��/�ҹ���', 1 , 'Y', 'admin', sysdate);

-- PK ���̺� ������ Ȯ��
SELECT m_categrpno, name, seqno, visible, rdate
FROM main_categrp
ORDER BY m_categrpno ASC;

-- FK ���̺� ������ Ȯ��
SELECT s_categoryno, m_categrpno, title, seqno, visible, ids, rdate, cnt
FROM sub_category 
ORDER BY s_categoryno ASC;


1.Cross join
- �����μ��� ��ġ�� �ſ� ������.
- �������� ����.
 
SELECT main_categrp.m_categrpno, main_categrp.name,
           sub_category.s_categoryno, sub_category.m_categrpno, sub_category.title,
           sub_category.seqno, sub_category.visible, sub_category.ids, sub_category.cnt
FROM main_categrp, sub_category
ORDER BY main_categrp.m_categrpno ASC, sub_category.seqno ASC;
 
-- ���̺� ������ ���
-- categrp c: ���̺���� �ʹ� ��� categrp ���̺��� ������ 'c'�� ����.
SELECT c.m_categrpno, c.name,
           t.s_categoryno, t.m_categrpno, t.title, t.seqno, t.visible, t.ids, t.cnt
FROM main_categrp c, sub_category t  
ORDER BY c.m_categrpno ASC, t.seqno ASC;


2. Equal JOIN�� FK ���̺��� �������� �մϴ�.
- WHERE �׷�(�θ�) ���̺� PK = ������(�ڽ�) ���̺� FK
- WHERE c.categrpno = t.categrpno: 2���� ���̺��� categrpno �÷��� ����
  ���ڵ带 �о� �޸𸮻󿡼� �ϳ��� ���ڵ�� �����Ͽ� �޸� ���̺���
  �����մϴ�. (DBMS�� ���� �޸� ���)
 
SELECT c.m_categrpno, c.name,
          t.s_categoryno, t.m_categrpno, t.title, t.seqno, t.visible, t.ids, t.cnt
FROM main_categrp c, sub_category t  
WHERE c.m_categrpno = t.m_categrpno
ORDER BY c.m_categrpno ASC, t.seqno ASC;
  
-- seqno �÷��� �ߺ�
    SELECT c.m_categrpno, c.name, c.seqno,
               t.s_categoryno, t.m_categrpno, t.title, t.seqno, t.visible, t.ids, t.cnt
    FROM main_categrp c, sub_category t  
    WHERE c.m_categrpno = t.m_categrpno
    ORDER BY c.m_categrpno ASC, t.seqno ASC;
          
-- seqno �÷��� �ߺ�, category_seqno ó�� �÷� ������ �����Ͽ� �ذ�
    SELECT c.m_categrpno, c.name, c.seqno,
               t.s_categoryno, t.m_categrpno, t.title, t.seqno as sub_category_seqno, t.visible, t.ids, t.cnt
    FROM main_categrp c, sub_category t  
    WHERE c.m_categrpno = t.m_categrpno
    ORDER BY c.m_categrpno ASC, t.seqno ASC;
 
    
3. LEFT Outer JOIN
- �θ����̺��� �ְ� �ڽ� ���̺��� �����ϴ� ���ڵ尡 ���� ����� ���
- category FK ���̺� '+' �������ϸ� ���ڵ� ������ ��� 
  NULL ������ �����Ͽ� ���
 
INSERT INTO main_categrp(m_categrpno, name, seqno, visible, rdate)
VALUES((SELECT NVL(MAX(m_categrpno), 0) + 1 as m_categrpno FROM main_categrp), '�׽�Ʈ', 1, 'Y', sysdate);
 
SELECT * FROM main_categrp ORDER BY m_categrpno ASC;
 
-- FK ���̺� '+' ��ȣ�� �����ϸ� PK ���̺��� ��� ��µ� 
SELECT c.m_categrpno, c.name,  
          t.s_categoryno, t.m_categrpno, t.title, t.seqno, t.visible, t.ids, t.cnt
FROM main_categrp c, sub_category t  
WHERE c.m_categrpno = t.m_categrpno(+)
ORDER BY c.m_categrpno ASC, t.seqno ASC;

 M_CATEGRPNO NAME  S_CATEGORYNO M_CATEGRPNO TITLE   SEQNO VISIBLE IDS   CNT
 ----------- ----- ------------ ----------- ------- ----- ------- ----- ----
           1 �ܼ�Ʈ����            1           1 ���̵�         1 Y       admin    0
           1 �ܼ�Ʈ����            2           1 ��Ŭ��/�ҹ���     1 Y       admin    0
           2 ��������          NULL        NULL NULL     NULL NULL    NULL  NULL
           3 �׽�Ʈ           NULL        NULL NULL     NULL NULL    NULL  NULL
 
-- ANSI Left Outer Join: ���� ���̺� ��� ���
SELECT c.m_categrpno, c.name,  
          t.s_categoryno, t.m_categrpno, t.title, t.seqno, t.visible, t.ids, t.cnt
FROM main_categrp c LEFT OUTER JOIN sub_category t
ON c.m_categrpno = t.m_categrpno
ORDER BY c.m_categrpno ASC, t.seqno ASC;
 
 M_CATEGRPNO NAME  S_CATEGORYNO M_CATEGRPNO TITLE   SEQNO VISIBLE IDS   CNT
 ----------- ----- ------------ ----------- ------- ----- ------- ----- ----
           1 �ܼ�Ʈ����            1           1 ���̵�         1 Y       admin    0
           1 �ܼ�Ʈ����            2           1 ��Ŭ��/�ҹ���     1 Y       admin    0
           2 ��������          NULL        NULL NULL     NULL NULL    NULL  NULL
           3 �׽�Ʈ           NULL        NULL NULL     NULL NULL    NULL  NULL
 
-- ANSI Right Outer Join, 
-- ���Ἲ ���������� �ջ����� PK ���� FK�� ��� �Ұ��Ͽ� Equal(����)�� ���� �����
-- �����, �����μ��� ��ġ�� ����. 
SELECT c.m_categrpno, c.name,  
          t.s_categoryno, t.m_categrpno, t.title, t.seqno, t.visible, t.ids, t.cnt
FROM main_categrp c RIGHT OUTER JOIN sub_category t
ON c.m_categrpno = t.m_categrpno
ORDER BY c.m_categrpno ASC, t.seqno ASC;
 
 M_CATEGRPNO NAME  S_CATEGORYNO M_CATEGRPNO TITLE   SEQNO VISIBLE IDS   CNT
 ----------- ----- ------------ ----------- ------- ----- ------- ----- ---
           1 �ܼ�Ʈ����            1           1 ���̵�         1 Y       admin   0
           1 �ܼ�Ʈ����            2           1 ��Ŭ��/�ҹ���     1 Y       admin   0
          
-- from���� ���̺��� ��ġ�� ������.
SELECT c.m_categrpno, c.name,  
          t.s_categoryno, t.m_categrpno, t.title, t.seqno, t.visible, t.ids, t.cnt
FROM sub_category t RIGHT OUTER JOIN main_categrp c 
ON c.m_categrpno = t.m_categrpno
ORDER BY c.m_categrpno ASC, t.seqno ASC;