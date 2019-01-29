/**************************************************/
/* Table Name: ����(question) */
/**************************************************/
DROP TABLE question; 

CREATE TABLE question(
  questionno             NUMBER(7)                                 NOT NULL, -- ���� ��ȣ
  memberno             NUMBER(7)                                  NOT NULL, -- ȸ�� ��ȣ
  question_title          VARCHAR2(30)                             NOT NULL, -- ���� ����
  question_contents    VARCHAR2(3000)                          NOT NULL, -- ���� ����
  question_thumb      VARCHAR2(1000)                                 NULL,
  question_file1          VARCHAR2(1000)                                 NULL,
  question_size1         VARCHAR2(1000)                                NULL,
  question_cnt          NUMBER(7)             DEFAULT 0       NOT NULL,
  question_replycnt    NUMBER(7)             DEFAULT 0       NOT NULL,
  question_grpno       NUMBER(7)                                  NOT NULL,
  question_indent      NUMBER(2)             DEFAULT 0       NOT NULL,
  question_ansnum    NUMBER(5)             DEFAULT 0       NOT NULL,
  question_word       VARCHAR2(100)                                    NULL, 
  rdate                    DATE                                         NOT NULL,
PRIMARY KEY (questionno),
FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE question is '�����亯(����)';
COMMENT ON COLUMN question.questionno is '�����亯(����) ��ȣ';
COMMENT ON COLUMN question.memberno is 'ȸ�� ��ȣ';
COMMENT ON COLUMN question.question_title is '���� ����';
COMMENT ON COLUMN question.question_contents is '���� ����';
COMMENT ON COLUMN question.question_thumb is '���� Thumb����';
COMMENT ON COLUMN question.question_file1 is '���� ���ϵ��� �̸�';
COMMENT ON COLUMN question.question_size1 is '���� ���ϵ��� ũ��';
COMMENT ON COLUMN question.question_cnt is '���� ��ȸ��';
COMMENT ON COLUMN question.question_replycnt is '��ۼ�';
COMMENT ON COLUMN question.question_grpno is '�׷� ��ȣ';
COMMENT ON COLUMN question.question_indent is '�亯����';
COMMENT ON COLUMN question.question_ansnum is '�亯 ����';
COMMENT ON COLUMN question.question_word is '�˻���';
COMMENT ON COLUMN question.rdate is '�����';

-- ERROR: FK �÷��� ����� ���� �ٸ� ���̺� ����� �ȵǾ� �ִ� ���
    ORA-02291: integrity constraint (SOLDESK.SYS_C007131) violated - parent key not found
    ORA-02291: integrity constraint (SOLDESK.SYS_C007132) violated - parent key not found

1. ���
INSERT INTO question(questionno, memberno, question_title, question_contents,
                              question_thumb, question_file1, question_size1, question_cnt,
                              question_replycnt, question_grpno, question_indent, question_ansnum, question_word, rdate)  
VALUES((SELECT NVL(MAX(questionno), 0) + 1 as questionno FROM question),
            1, '����', '����', 'fall_m.jpg', 'fall.jpg', 0, 0, 0,
            (SELECT NVL(MAX(question_grpno), 0) + 1 as question_grpno FROM question), 
            0, 0, '������,spring,��,��,����,����,������', sysdate);
INSERT INTO question(questionno, memberno, question_title, question_contents,
                              question_thumb, question_file1, question_size1, question_cnt,
                              question_replycnt, question_grpno, question_indent, question_ansnum, question_word, rdate)  
VALUES((SELECT NVL(MAX(questionno), 0) + 1 as questionno FROM question),
            1, '����', '����', 'fall_m.jpg', 'fall.jpg', 0, 0, 0,
            (SELECT NVL(MAX(question_grpno), 0) + 1 as question_grpno FROM question), 
            0, 0, '������,spring,��,��,����,����,������', sysdate);


2. ���
-- ��ü ���
SELECT questionno, memberno, question_title, question_grpno, question_replycnt, question_indent, question_ansnum 
FROM question
ORDER BY rdate DESC, question_ansnum ASC;
-- QUESTIONNO MEMBERNO QUESTION_TITLE QUESTION_REPLYCNT QUESTION_INDENT QUESTION_ANSNUM
-- ---------- -------- -------------- ----------------- --------------- ---------------
--          5        1                �亯1                            0                          0                          0
--          4        1 �θ��1                           0               0               0
--          3        1 �亯 �׽�Ʈ                         0               0               0
--          2        1 �ٴٰǳ�                           0               0               0
--          1        1 �׽�Ʈ                            0               0               0

          
          
SELECT questionno, memberno, question_title, question_contents, 
          question_thumb, question_file1, question_size1, question_cnt, 
          question_replycnt, question_indent, question_ansnum, question_word, rdate
FROM question
ORDER BY rdate DESC, question_ansnum ASC;

-- ȸ�� ��ȣ�� ���
SELECT questionno, memberno, question_title, question_contents, 
          question_thumb, question_file1, question_size1, question_cnt, 
          question_replycnt, question_indent, question_ansnum, question_word, rdate
FROM question
WHERE memberno = 1
ORDER BY rdate DESC, question_ansnum ASC;

 QUESTIONNO MEMBERNO QUESTION_TITLE QUESTION_CONTENTS QUESTION_THUMB QUESTION_FILE1 QUESTION_SIZE1 QUESTION_CNT QUESTION_REPLYCNT QUESTION_INDENT QUESTION_ANSNUM QUESTION_WORD                RDATE
 --------------- -------------  ------------------ ------------------------  --------------------- -----------------  ------------------  ---------------- ------------------------ ---------------------- ----------------------- ---------------------------- ---------------------
          4             1                 ����                ����                    fall_m.jpg                   fall.jpg           0                         0                 0               0               0 ������,spring,��,��,����,����,������     2019-01-23 12:53:29.0
          3             1                 ����                ����                  summer_m.jpg          summer.jpg         0                         0                 0               1               1 NULL                         2019-01-23 12:50:44.0
          2             1                 test123             test123                   NULL                    NULL            0                         0                 0               0               0 ,test123                     2019-01-23 11:50:00.0
          1             1                 ����111           ����111                 fall_m.jpg                  fall.jpg           0                         0                 0               0               0 ,������,spring,��,��,����,����,������111 2019-01-23 11:30:23.0
  

3. ��ü ī��Ʈ
SELECT COUNT(*) as count
FROM question;

 COUNT
 -----
     2
     
4. ��ȸ
SELECT questionno, memberno, question_title, question_contents, 
          question_thumb, question_file1, question_size1, question_cnt, 
          question_replycnt, question_indent, question_ansnum, question_word, rdate
FROM question
WHERE questionno=1; 

5. ����
UPDATE question
SET question_title='�ܿ�', question_contents='���̽÷���...', 
      question_thumb='snow_t.jpg', question_file1='snow.jpg', question_size1=1500, question_word='�ް�'
WHERE questionno=1;

6. ����
DELETE FROM question
WHERE questionno=4

7. question�� �˻��� ���ڵ� ���
SELECT questionno, memberno, question_title, question_contents, 
          question_thumb, question_file1, question_size1, question_cnt, 
          question_replycnt, question_indent, question_ansnum, question_word, rdate
FROM question
WHERE question_word LIKE '%test%'
ORDER BY rdate DESC;

8. 'test' �˻� ���ڵ� ����
SELECT COUNT(*) as cnt
FROM question
WHERE question_word LIKE '%test%';

SELECT COUNT(*) as cnt
FROM question
WHERE question_word = null;


15) �亯
[�亯 ����]
-- 1���� ���� �亯 ��Ͽ�: grpno: 1, indent: 1, ansnum: 1
SELECT * FROM member;
SELECT * FROM categrp;
SELECT * FROM category;

�� ���ο� �亯�� �ֽ����� ����ϱ����� ���� �亯�� �ڷ� �̷�ϴ�.
-- ��� ���� �켱 ������ 1�� ������, 1�� -> 2��
UPDATE question
SET question_ansnum = question_ansnum + 1
WHERE memberno=1 AND question_grpno = 1 AND question_ansnum > 0;

-- 2����� �켱 ������ 1�� ������, 2�� -> 3��
UPDATE blog
SET ansnum = ansnum + 1
WHERE categoryno=1 AND grpno = 1 AND ansnum > 1;
 
-- 3����� �켱 ������ 1�� ������, 3�� -> 4��
UPDATE blog
SET ansnum = ansnum + 1
WHERE categoryno=1 AND grpno = 1 AND ansnum > 2;

-- 6����� �켱 ������ 1�� ������, 6�� -> 7��
UPDATE blog
SET ansnum = ansnum + 1
WHERE categoryno=1 AND grpno = 1 AND ansnum > 5;

SELECT * FROM member;

�� �亯 ���
INSERT INTO question(questionno,
                          memberno, question_title, question_contents, question_thumb, question_file1, question_size1, 
                          question_cnt, question_replycnt, rdate, 
                          question_grpno, question_indent, question_ansnum, question_word)  
VALUES((SELECT NVL(MAX(questionno), 0) + 1 as questionno FROM question),
            1, '�亯', '����', 'summer_m.jpg', 'summer.jpg', 0, 0, 0, sysdate,
            1, 1, 1,'');


�� �亯�� ���� ���� ���� ����    
SELECT questionno, question_title, question_contents, 
          question_thumb, question_file1, question_size1, question_cnt, question_replycnt, rdate, 
          question_grpno, question_indent, question_ansnum, question_word, r
FROM(
         SELECT questionno, question_title, question_contents, 
                   question_thumb, question_file1, question_size1, question_cnt, question_replycnt, rdate, 
                   question_grpno, question_indent, question_ansnum, question_word, rownum as r
         FROM(
                  SELECT questionno, question_title, question_contents, 
                            question_thumb, question_file1, question_size1, question_cnt, question_replycnt, rdate, 
                            question_grpno, question_indent, question_ansnum, question_word
                  FROM question
                  ORDER BY question_grpno DESC, question_ansnum ASC
         )
)
WHERE r >=1 AND r <= 3;

     










