/**************************************************/
/* Table Name: ������(admin) */
/**************************************************/
DROP TABLE admin; 

CREATE TABLE admin(
  adminno    NUMBER(7)       NOT NULL,
  a_name       VARCHAR2(20)   NOT NULL, -- ����, �ѱ� 10�� ���� ����
  a_nickname  VARCHAR2(50)    UNIQUE NOT NULL, -- �ѹ� ��ϵ� nickname�� �ߺ� �ȵ�
  a_email       VARCHAR2(50)    UNIQUE NOT NULL, -- �ѹ� ��ϵ� email�� �ߺ� �ȵ�   
  a_passwd     VARCHAR2(60)    NOT NULL, -- �н�����, ������ ���� 
  a_age         VARCHAR2(2)      NOT NULL, -- ����, 99
  a_act          CHAR(1)            DEFAULT 'N' NOT NULL,
  rdate                  DATE                NOT NULL,
PRIMARY KEY (adminno)
);

COMMENT ON TABLE admin is '������';
COMMENT ON COLUMN admin.adminno is '������ ��ȣ';
COMMENT ON COLUMN admin.a_name is '������ �̸�';
COMMENT ON COLUMN admin.a_nickname is '������ �г���';
COMMENT ON COLUMN admin.a_email is '������ �̸���';
COMMENT ON COLUMN admin.a_passwd is '������ ��й�ȣ';
COMMENT ON COLUMN admin.a_age is '������ ����';
COMMENT ON COLUMN admin.a_act is '������ ����';
COMMENT ON COLUMN admin.rdate is '������ �����';


1. ���
-- ** �̸��� �ߺ� Ȯ�� **
SELECT COUNT(a_email) as cnt
FROM admin
WHERE a_email = 'admin1@gmail.com';

-- ** �г��� �ߺ� Ȯ�� **
SELECT COUNT(a_nickname) as cnt
FROM admin
WHERE a_nickname = '������';

-- �Ϲ� ������ ����
-- ACT = {"G"(General) = �Ϲݰ�����}, {"M"(Master) = ������} 
INSERT INTO admin(adminno, a_name, a_nickname, a_email, a_passwd, 
                             a_age, a_act, rdate)
VALUES ((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin),
'������', '������', 'admin1@gmail.com', '1113', '27', 'M', sysdate);


2. ���
- �˻��� ���� �ʴ� ���, ��ü ��� ���

SELECT adminno, a_name, a_nickname, a_email, a_passwd, 
          a_age, a_act, rdate
FROM admin
ORDER BY adminno ASC;

 adminNO a_name a_nickname a_email         a_passwd a_age a_act M_THUMBS      M_FILES     M_SIZES RDATE
 -------- ------ ---------- --------------- -------- ----- ----- ------------- ----------- ------- ---------------------
        1 �谡��    ������¯       test1@gmail.com 1113     23    G     dlwlrma_t.jpg dlwlrma.jpg 0       2018-12-11 16:54:28.0
        2 �찡��    ������¯       test2@gmail.com 1113     25    G     test2_t.jpg   test2.jpg   0       2018-12-11 16:56:39.0
        3 �밡��    �뵹¯        test3@gmail.com 1113     5     G     test3_t.jpg   test3.jpg   0       2018-12-11 16:56:40.0


3. ��ȸ
--�Ϲ� ������ ���� ����
SELECT adminno, a_name, a_nickname, a_email, a_passwd, 
          a_age, a_act, rdate
FROM admin
WHERE adminno = 1;

 adminNO a_name a_nickname a_email         a_passwd a_age a_act M_THUMBS      M_FILES     M_SIZES RDATE
 -------- ------ ---------- --------------- -------- ----- ----- ------------- ----------- ------- ---------------------
        1 �谡��    ������¯       test1@gmail.com 1113     23    G     dlwlrma_t.jpg dlwlrma.jpg 0       2018-12-11 16:54:28.0


SELECT adminno, a_name, a_nickname, a_email, a_passwd, 
          a_age, a_act, m_thumbs, m_files, m_sizes, rdate
FROM admin
WHERE a_age = '5';

 adminNO a_name a_nickname a_email         a_passwd a_age a_act M_THUMBS    M_FILES   M_SIZES RDATE
 -------- ------ ---------- --------------- -------- ----- ----- ----------- --------- ------- ---------------------
        3 �밡��    �뵹¯        test3@gmail.com 1113     5     G     test3_t.jpg test3.jpg 0       2018-12-11 16:56:40.0


              
4. ����
UPDATE admin 
SET a_name='������', a_email='dlwlrma@gmail.com', a_age='26'
WHERE adminno = 1;

5. �н����� ����
1) �н����� �˻�
SELECT COUNT(adminno) as cnt
FROM admin
WHERE adminno=1 AND a_passwd='1113';

 CNT
 ---
   1

2) �н����� ����
UPDATE admin
SET a_passwd='0516'
WHERE adminno=1;


6. ����
1) ��� ����
DELETE FROM admin;

2) Ư�� ������ ����
DELETE FROM admin
WHERE adminno=4;


7. �α���
SELECT COUNT(adminno) as cnt
FROM admin
WHERE a_email='dlwlrma@gmail.com' AND a_passwd='0516';
 CNT
 ---
   1

-------------------------------------------------------------------------------------

