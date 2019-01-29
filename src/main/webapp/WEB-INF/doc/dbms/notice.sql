DROP TABLE notice;

/**********************************/
/* Table Name: �������� */
/**********************************/

1. ���̺� ����
CREATE TABLE notice(
  noticeno          NUMBER(7)                    NOT NULL,
  title                 VARCHAR2(100)               NOT NULL,
  content            CLOB                           NOT NULL,
  rdate               DATE                            NOT NULL,
  PRIMARY KEY(noticeno)
);

COMMENT ON TABLE notice is '��������';
COMMENT ON COLUMN notice.noticeno is '�������� ��ȣ';
COMMENT ON COLUMN notice.title is '�������� ����';
COMMENT ON COLUMN notice.content is '�������� ����';
COMMENT ON COLUMN notice.rdate is '�����';


2. ���
INSERT INTO notice(noticeno, title, content, rdate)
VALUES(1, '��������', '���������Դϴ�.', sysdate);

INSERT INTO notice(noticeno, title, content, rdate)
VALUES(2, '��������2', '��������2�Դϴ�.', sysdate);

INSERT INTO notice(noticeno, title, content, rdate)
VALUES(3, '��������3', '��������3�Դϴ�.', sysdate);

INSERT INTO notice(noticeno, title, content, rdate)
VALUES((SELECT NVL(MAX(noticeno), 0) + 1 as noticeno FROM notice), '��������4', '��������4�Դϴ�.', sysdate);


3. ���
SELECT noticeno, title, content, rdate
FROM notice
ORDER BY noticeno DESC;

1) ����¡
-- step 1
SELECT noticeno, title, content, rdate
FROM notice
WHERE noticeno=1
ORDER BY noticeno DESC;

-- step 2
SELECT noticeno, title, content, rdate, rownum as r
FROM (
          SELECT noticeno, title, content, rdate
          FROM notice
          WHERE noticeno=1
          ORDER BY noticeno DESC
);

-- step 3
SELECT noticeno, title, content, rdate, r
FROM (
SELECT noticeno, title, content, rdate, rownum as r
FROM (
          SELECT noticeno, title, content, rdate
          FROM notice
          WHERE noticeno=1
          ORDER BY noticeno DESC
          )
)
WHERE r >= 1 AND r<= 3;


4. ��ȸ
SELECT noticeno, title, content, rdate
FROM notice
WHERE noticeno=1;

1) ��ü ���ڵ� ��
SELECT COUNT(noticeno) as cnt
FROM notice


5. ����
UPDATE notice
SET title='��������4', content='��������4 �Դϴ�.'
WHERE noticeno=1;


6. ����
DELETE FROM notice
WHERE noticeno=1;