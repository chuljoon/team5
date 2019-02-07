/**************************************************/
/* Table Name: ȸ��(member) */
/**************************************************/
DROP TABLE member; 

CREATE TABLE member(
  memberno    NUMBER(7)       NOT NULL,
  m_name       VARCHAR2(20)   NOT NULL, -- ����, �ѱ� 10�� ���� ����
  m_nickname  VARCHAR2(50)    UNIQUE NOT NULL, -- �ѹ� ��ϵ� nickname�� �ߺ� �ȵ�
  m_email       VARCHAR2(50)    UNIQUE NOT NULL, -- �ѹ� ��ϵ� email�� �ߺ� �ȵ�   
  m_passwd     VARCHAR2(60)    NOT NULL, -- �н�����, ������ ���� 
  m_age         VARCHAR2(2)      NOT NULL, -- ����, 99
  m_act          CHAR(1)            DEFAULT 'N' NOT NULL,
  rdate                  DATE                NOT NULL,
PRIMARY KEY (memberno)
);

COMMENT ON TABLE member is 'ȸ��';
COMMENT ON COLUMN member.memberno is 'ȸ�� ��ȣ';
COMMENT ON COLUMN member.m_name is 'ȸ�� �̸�';
COMMENT ON COLUMN member.m_nickname is 'ȸ�� �г���';
COMMENT ON COLUMN member.m_email is 'ȸ�� �̸���';
COMMENT ON COLUMN member.m_passwd is 'ȸ�� ��й�ȣ';
COMMENT ON COLUMN member.m_age is 'ȸ�� ����';
COMMENT ON COLUMN member.m_act is 'ȸ�� ����';
COMMENT ON COLUMN member.rdate is 'ȸ�� �����';


1. ���
-- ** �̸��� �ߺ� Ȯ�� **
SELECT COUNT(m_email) as cnt
FROM member
WHERE m_email = 'test1@gmail.com';

-- ** �г��� �ߺ� Ȯ�� **
SELECT COUNT(m_nickname) as cnt
FROM member
WHERE m_nickname = '������¯';

-- �Ϲ� ȸ�� ����
-- ACT = {"G"(General) = �Ϲ�ȸ��}, {"M"(Master) = ������} 
INSERT INTO member(memberno, m_name, m_nickname, m_email, m_passwd, 
                             m_age, m_act, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'������', '������¯', 'master@gmail.com', '1113', '27', 'M', sysdate);

INSERT INTO member(memberno, m_name, m_nickname, m_email, m_passwd, 
                             m_age, m_act, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'�찡��', '������¯', 'test@gmail.com', '1113', '25', 'G', sysdate);

INSERT INTO member(memberno, m_name, m_nickname, m_email, m_passwd, 
                             m_age, m_act, m_thumbs, m_files, m_sizes, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'�밡��', '�뵹¯', 'test3@gmail.com', '1113', '5', 'G', 'test3_t.jpg', 'test3.jpg', 0, sysdate);


2. ���
- �˻��� ���� �ʴ� ���, ��ü ��� ���

SELECT memberno, m_name, m_nickname, m_email, m_passwd, 
          m_age, m_act, rdate
FROM member
ORDER BY memberno ASC;

 MEMBERNO M_NAME M_NICKNAME M_EMAIL         M_PASSWD M_AGE M_ACT M_THUMBS      M_FILES     M_SIZES RDATE
 -------- ------ ---------- --------------- -------- ----- ----- ------------- ----------- ------- ---------------------
        1 �谡��    ������¯       test1@gmail.com 1113     23    G     dlwlrma_t.jpg dlwlrma.jpg 0       2018-12-11 16:54:28.0
        2 �찡��    ������¯       test2@gmail.com 1113     25    G     test2_t.jpg   test2.jpg   0       2018-12-11 16:56:39.0
        3 �밡��    �뵹¯        test3@gmail.com 1113     5     G     test3_t.jpg   test3.jpg   0       2018-12-11 16:56:40.0


3. ��ȸ
--�Ϲ� ȸ�� ���� ����
SELECT memberno, m_name, m_nickname, m_email, m_passwd, 
          m_age, m_act, rdate
FROM member
WHERE memberno = 1;

 MEMBERNO M_NAME M_NICKNAME M_EMAIL         M_PASSWD M_AGE M_ACT M_THUMBS      M_FILES     M_SIZES RDATE
 -------- ------ ---------- --------------- -------- ----- ----- ------------- ----------- ------- ---------------------
        1 �谡��    ������¯       test1@gmail.com 1113     23    G     dlwlrma_t.jpg dlwlrma.jpg 0       2018-12-11 16:54:28.0


SELECT memberno, m_name, m_nickname, m_email, m_passwd, 
          m_age, m_act, m_thumbs, m_files, m_sizes, rdate
FROM member
WHERE m_age = '5';

 MEMBERNO M_NAME M_NICKNAME M_EMAIL         M_PASSWD M_AGE M_ACT M_THUMBS    M_FILES   M_SIZES RDATE
 -------- ------ ---------- --------------- -------- ----- ----- ----------- --------- ------- ---------------------
        3 �밡��    �뵹¯        test3@gmail.com 1113     5     G     test3_t.jpg test3.jpg 0       2018-12-11 16:56:40.0


              
4. ����
UPDATE member 
SET m_nickname='������'
WHERE memberno = 1;

5. �н����� ����
1) �н����� �˻�
SELECT COUNT(memberno) as cnt
FROM member
WHERE memberno=1 AND m_passwd='1113';

 CNT
 ---
   1

2) �н����� ����
UPDATE member
SET m_passwd='0516'
WHERE memberno=1;


6. ����
1) ��� ����
DELETE FROM member;

2) Ư�� ȸ�� ����
DELETE FROM member
WHERE memberno=4;


7. �α���
SELECT COUNT(memberno) as cnt
FROM member
WHERE m_email='dlwlrma@gmail.com' AND m_passwd='0516';
 CNT
 ---
   1

-------------------------------------------------------------------------------------

