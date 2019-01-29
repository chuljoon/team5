DROP TABLE main_categrp;

/**********************************/
/* Table Name: ���� ī�װ� */
/**********************************/

1. ���̺� ����
CREATE TABLE main_categrp(
    m_categrpno                         NUMBER(10)     NOT NULL    PRIMARY KEY,
--  classification                    CHAR(1)    DEFAULT 1     NOT NULL,
    name                              VARCHAR2(50)     NOT NULL,
    seqno                             NUMBER(7)    DEFAULT 0     NOT NULL,
    visible                           CHAR(1)    DEFAULT 'Y'     NOT NULL,
    rdate                             DATE     NOT NULL
);

COMMENT ON TABLE main_categrp is '���� ī�װ� �׷�';
COMMENT ON COLUMN main_categrp.m_categrpno is '���� ī�װ� �׷� ��ȣ';
-- COMMENT ON COLUMN main_categrp.classification is '���� ī�װ� �׷� �з�';
COMMENT ON COLUMN main_categrp.name is '�̸�';
COMMENT ON COLUMN main_categrp.seqno is '��� ����';
COMMENT ON COLUMN main_categrp.visible is '��� ���';
COMMENT ON COLUMN main_categrp.rdate is '�׷� ������';


2. ���ڵ� ���
INSERT INTO main_categrp(m_categrpno, name, seqno, visible, rdate)
VALUES(1, '�ܼ�Ʈ����', 1, 'Y', sysdate);

INSERT INTO main_categrp(m_categrpno, name, seqno, visible, rdate)
VALUES(2, '��������', 1, 'Y', sysdate);

INSERT INTO main_categrp(m_categrpno, name, seqno, visible, rdate)
VALUES((SELECT NVL(MAX(m_categrpno), 0) + 1 as m_categrpno FROM main_categrp), '�׽�Ʈ', 1, 'Y', sysdate);


3. ���
SELECT m_categrpno, name, seqno, visible, rdate
FROM main_categrp
ORDER BY m_categrpno ASC;

4. ��ȸ
SELECT m_categrpno, name, seqno, visible, rdate
FROM main_categrp
WHERE m_categrpno =1;

5. ��ü ���ڵ� ��
SELECT COUNT(*) as cnt
FROM main_categrp

6. ����
UPDATE main_categrp
SET name='�ܼ�Ʈ����',
WHERE m_categrpno = 1;

7. ����
DELETE FROM main_categrp
WHERE m_categrpno = 8;