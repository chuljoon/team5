/**************************************************/
/* Table Name: 관리자(admin) */
/**************************************************/
DROP TABLE admin; 

CREATE TABLE admin(
  adminno    NUMBER(7)       NOT NULL,
  a_name       VARCHAR2(20)   NOT NULL, -- 성명, 한글 10자 저장 가능
  a_nickname  VARCHAR2(50)    UNIQUE NOT NULL, -- 한번 등록된 nickname은 중복 안됨
  a_email       VARCHAR2(50)    UNIQUE NOT NULL, -- 한번 등록된 email은 중복 안됨   
  a_passwd     VARCHAR2(60)    NOT NULL, -- 패스워드, 영숫자 조합 
  a_age         VARCHAR2(2)      NOT NULL, -- 나이, 99
  a_act          CHAR(1)            DEFAULT 'N' NOT NULL,
  rdate                  DATE                NOT NULL,
PRIMARY KEY (adminno)
);

COMMENT ON TABLE admin is '관리자';
COMMENT ON COLUMN admin.adminno is '관리자 번호';
COMMENT ON COLUMN admin.a_name is '관리자 이름';
COMMENT ON COLUMN admin.a_nickname is '관리자 닉네임';
COMMENT ON COLUMN admin.a_email is '관리자 이메일';
COMMENT ON COLUMN admin.a_passwd is '관리자 비밀번호';
COMMENT ON COLUMN admin.a_age is '관리자 나이';
COMMENT ON COLUMN admin.a_act is '관리자 권한';
COMMENT ON COLUMN admin.rdate is '관리자 등록일';


1. 등록
-- ** 이메일 중복 확인 **
SELECT COUNT(a_email) as cnt
FROM admin
WHERE a_email = 'admin1@gmail.com';

-- ** 닉네임 중복 확인 **
SELECT COUNT(a_nickname) as cnt
FROM admin
WHERE a_nickname = '관리자';

-- 일반 관리자 기준
-- ACT = {"G"(General) = 일반관리자}, {"M"(Master) = 관리자} 
INSERT INTO admin(adminno, a_name, a_nickname, a_email, a_passwd, 
                             a_age, a_act, rdate)
VALUES ((SELECT NVL(MAX(adminno), 0)+1 as adminno FROM admin),
'관리자', '관리자', 'admin1@gmail.com', '1113', '27', 'M', sysdate);


2. 목록
- 검색을 하지 않는 경우, 전체 목록 출력

SELECT adminno, a_name, a_nickname, a_email, a_passwd, 
          a_age, a_act, rdate
FROM admin
ORDER BY adminno ASC;

 adminNO a_name a_nickname a_email         a_passwd a_age a_act M_THUMBS      M_FILES     M_SIZES RDATE
 -------- ------ ---------- --------------- -------- ----- ----- ------------- ----------- ------- ---------------------
        1 김가네    아이유짱       test1@gmail.com 1113     23    G     dlwlrma_t.jpg dlwlrma.jpg 0       2018-12-11 16:54:28.0
        2 우가네    이지은짱       test2@gmail.com 1113     25    G     test2_t.jpg   test2.jpg   0       2018-12-11 16:56:39.0
        3 펭가네    펭돌짱        test3@gmail.com 1113     5     G     test3_t.jpg   test3.jpg   0       2018-12-11 16:56:40.0


3. 조회
--일반 관리자 정보 보기
SELECT adminno, a_name, a_nickname, a_email, a_passwd, 
          a_age, a_act, rdate
FROM admin
WHERE adminno = 1;

 adminNO a_name a_nickname a_email         a_passwd a_age a_act M_THUMBS      M_FILES     M_SIZES RDATE
 -------- ------ ---------- --------------- -------- ----- ----- ------------- ----------- ------- ---------------------
        1 김가네    아이유짱       test1@gmail.com 1113     23    G     dlwlrma_t.jpg dlwlrma.jpg 0       2018-12-11 16:54:28.0


SELECT adminno, a_name, a_nickname, a_email, a_passwd, 
          a_age, a_act, m_thumbs, m_files, m_sizes, rdate
FROM admin
WHERE a_age = '5';

 adminNO a_name a_nickname a_email         a_passwd a_age a_act M_THUMBS    M_FILES   M_SIZES RDATE
 -------- ------ ---------- --------------- -------- ----- ----- ----------- --------- ------- ---------------------
        3 펭가네    펭돌짱        test3@gmail.com 1113     5     G     test3_t.jpg test3.jpg 0       2018-12-11 16:56:40.0


              
4. 수정
UPDATE admin 
SET a_name='이지금', a_email='dlwlrma@gmail.com', a_age='26'
WHERE adminno = 1;

5. 패스워드 변경
1) 패스워드 검사
SELECT COUNT(adminno) as cnt
FROM admin
WHERE adminno=1 AND a_passwd='1113';

 CNT
 ---
   1

2) 패스워드 수정
UPDATE admin
SET a_passwd='0516'
WHERE adminno=1;


6. 삭제
1) 모두 삭제
DELETE FROM admin;

2) 특정 관리자 삭제
DELETE FROM admin
WHERE adminno=4;


7. 로그인
SELECT COUNT(adminno) as cnt
FROM admin
WHERE a_email='dlwlrma@gmail.com' AND a_passwd='0516';
 CNT
 ---
   1

-------------------------------------------------------------------------------------

