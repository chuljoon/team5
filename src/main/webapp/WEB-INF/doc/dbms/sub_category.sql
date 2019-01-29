DROP TABLE sub_category;

/**********************************/
/* Table Name: ���� ī�װ� */
/**********************************/

1. ���̺� ���
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

COMMENT ON TABLE sub_category is '���� ī�װ�';
COMMENT ON COLUMN sub_category.s_categoryno is '���� ī�װ� ��ȣ';
COMMENT ON COLUMN sub_category.m_categrpno is '���� ī�װ� �׷� ��ȣ';
COMMENT ON COLUMN sub_category.title is '�Խ��� �̸�';
COMMENT ON COLUMN sub_category.seqno is '��� ����';
COMMENT ON COLUMN sub_category.visible is '��� ���';
COMMENT ON COLUMN sub_category.ids is '���� ����';
COMMENT ON COLUMN sub_category.cnt is '��ϵ� �� ��';
COMMENT ON COLUMN sub_category.rdate is '�����';


2. ���ڵ� �߰�
INSERT INTO sub_category(s_categoryno, m_categrpno, title, seqno, visible, ids, rdate)
VALUES(1, 1, '���̵�', 1 , 'Y', 'admin', sysdate);

INSERT INTO sub_category(s_categoryno, m_categrpno, title, seqno, visible, ids, rdate)
VALUES(2, 1, '��Ŭ��/�ҹ���', 1 , 'Y', 'admin', sysdate);

INSERT INTO sub_category(s_categoryno, m_categrpno, title, seqno, visible, ids, rdate)
VALUES(3, 1, '�߶��/R&B', 1 , 'Y', 'admin', sysdate);

INSERT INTO sub_category(s_categoryno, m_categrpno, title, seqno, visible, ids, rdate)
VALUES((SELECT NVL(MAX(s_categoryno), 0) + 1 as s_categoryno FROM sub_category), 1, '�׽�Ʈ', 1 , 'Y', 'admin', sysdate);


3. ���
SELECT s_categoryno, m_categrpno, title, seqno, visible, ids, rdate, cnt
FROM sub_category 
ORDER BY s_categoryno ASC;


4. ��ȸ
SELECT s_categoryno, m_categrpno, title, seqno, visible, ids, rdate, cnt
FROM sub_category 
WHERE s_categoryno=1;

1) ��ü ���ڵ� ��
SELECT COUNT(*) as cnt
FROM sub_category


5. ����
UPDATE sub_category
SET title='���̵�', seqno=1, visible='N', ids='admin'
WHERE s_categoryno=1;


6. ����
DELETE FROM sub_category 
WHERE s_categoryno = 3;


7. ��¼���
1) ��� �켱 ���� ����
UPDATE sub_category 
SET seqno = seqno - 1 
WHERE s_categoryno=1;

2) ��� �켱 ���� ����
UPDATE category 
SET seqno = seqno + 1 
WHERE categoryno=1;