DROP TABLE contents;
DROP TABLE expect;

/**********************************/
/* Table Name: �������� */
/**********************************/

1. ���̺� ����
CREATE TABLE contents(
  contentsno        NUMBER(7)                    NOT NULL,
  s_categoryno      NUMBER(7)                    NOT NULL,
  title                 VARCHAR2(100)               NOT NULL,
  title_img           VARCHAR2(100)               NOT NULL,  
  thumbs              VARCHAR2(100)                     NULL,
  sizes                 NUMBER(10)                         NULL,
  passwd             VARCHAR2(100)                  NOT NULL,
  contents            CLOB                             NOT NULL,
  files                  VARCHAR2(100)                     NULL,
  file_size             NUMBER(10)                          NULL,
  p_date              VARCHAR2(100)              NOT NULL,
  word                VARCHAR2(100)                     NULL,
  rdate               DATE                            NOT NULL,
  PRIMARY KEY(contentsno),
  FOREIGN KEY (s_categoryno) REFERENCES sub_category (s_categoryno)
);

COMMENT ON TABLE contents is '��������';
COMMENT ON COLUMN contents.contentsno is '�������� ��ȣ';
COMMENT ON COLUMN contents.s_categoryno is '���� ī�װ� ��ȣ';
COMMENT ON COLUMN contents.hallno is '������ ��ȣ';
COMMENT ON COLUMN contents.title is '������';
COMMENT ON COLUMN contents.title_img is '����������';
COMMENT ON COLUMN contents.thumbs is '�����';
COMMENT ON COLUMN contents.sizes is '���� ������';
COMMENT ON COLUMN contents.passwd is '�н�����';
COMMENT ON COLUMN contents.contents is '����';
COMMENT ON COLUMN contents.files is '��ǰ�������';
COMMENT ON COLUMN contents.file_size is '��ǰ���� ������';
COMMENT ON COLUMN contents.p_date is '���� �Ͻ�';
COMMENT ON COLUMN contents.word is '�˻���';
COMMENT ON COLUMN contents.rdate is '�����';


2. ���
INSERT INTO contents(contentsno, s_categoryno, title, title_img, thumbs,
                             sizes, passwd, contents, files, file_size,p_date, rdate)
VALUES((SELECT NVL(MAX(contentsno), 0) + 1 as contentsno FROM contents),
            1, '���Ǵ�Ʈ', 'infinite.jpg', 'infinite_t.jpg', 0,  '1234', '���Ǵ�Ʈ�ҹ���', 'fanmeeting.jpg', 0, '2019-01-12', sysdate);

INSERT INTO contents(contentsno, s_categoryno, title, title_img, 
                             passwd, contents, thumbs, files, sizes, rdate)
VALUES(2, 1, '����', '01.jpg', '1234', '����', '01_t.jpg', 'f01.jpg', 0, sysdate);

INSERT INTO contents(contentsno, s_categoryno, title, title_img, 
                             passwd, contents, thumbs, files, sizes, rdate)
VALUES(3, 1, '����2', '02.jpg', '1234', '����2', '02_t.jpg', 'f02.jpg', 0, sysdate);


3. ���
SELECT contentsno, s_categoryno, title, title_img, thumbs, sizes, passwd, contents, files, file_size, p_date, rdate
FROM contents
ORDER BY contentsno DESC;

1) �˻�
SELECT contentsno, s_categoryno, title, title_img, thumbs, sizes, passwd, contents, files, file_size, p_date, rdate
FROM contents
WHERE title='����';

-- '����' �÷����� �˻�
SELECT contentsno, s_categoryno, title, title_img, passwd, contents, thumbs, files, sizes, rdate
FROM contents
WHERE title LIKE '%������%'
ORDER BY contentsno DESC;

2) ����¡
-- step 1
SELECT contentsno, s_categoryno, title, title_img, thumbs, sizes, passwd, contents, files, file_size, p_date, rdate
FROM contents
WHERE s_categoryno=1
ORDER BY contentsno DESC;

-- step 2
SELECT contentsno, s_categoryno, title, title_img, thumbs, sizes, passwd, contents, files, file_size, p_date, rdate, rownum as r
FROM(
        SELECT contentsno, s_categoryno, title, title_img, thumbs, sizes, passwd, contents, files, file_size, p_date, rdate
        FROM contents
        WHERE s_categoryno=1
        ORDER BY contentsno DESC
);

-- step 3
SELECT contentsno, s_categoryno, title, title_img, thumbs, sizes, passwd, contents, files, file_size, p_date, rdate, r
FROM(
SELECT contentsno, s_categoryno, title, title_img, thumbs, sizes, passwd, contents, files, file_size, p_date, rdate, rownum as r
FROM(
        SELECT contentsno, s_categoryno, title, title_img, thumbs, sizes, passwd, contents, files, file_size, p_date, rdate
        FROM contents
        WHERE s_categoryno=1
        ORDER BY contentsno DESC
        )
)
WHERE r >= 1 AND r <= 3;

-- �˻�
SELECT contentsno, s_categoryno, title, title_img, passwd, contents, thumbs, files, sizes, rdate, r
FROM(
SELECT contentsno, s_categoryno, title, title_img, passwd, contents, thumbs, files, sizes, rdate, rownum as r
FROM(
        SELECT contentsno, s_categoryno, title, title_img, passwd, contents, thumbs, files, sizes, rdate
        FROM contents
        WHERE s_categoryno=1 AND title LIKE '%���Ǵ�Ʈ%'
        ORDER BY contentsno DESC
        )
)
WHERE r >= 1 AND r <= 3;


4. ��ȸ
SELECT contentsno, s_categoryno, title, title_img, passwd, contents, thumbs, files, sizes, rdate
FROM contents
WHERE contentsno=1;

1) ��ü ���ڵ� ��
SELECT COUNT(contentsno) as cnt
FROM contents


5. ����
UPDATE contents
SET title='���Ǵ�Ʈ', contents='���Ǵ�Ʈ�ҹ����ְ�', thumbs='infinite_t.jpg', files='infifan.jpg', sizes=1500
WHERE contentsno=1;


6. ����
DELETE FROM contents
WHERE contentsno=4;