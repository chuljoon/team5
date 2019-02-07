/**************************************************/
/* Table Name: 회원(member) */
/**************************************************/
DROP TABLE member; 

CREATE TABLE member(
  memberno    NUMBER(7)       NOT NULL,
  m_name       VARCHAR2(20)   NOT NULL, -- 성명, 한글 10자 저장 가능
  m_nickname  VARCHAR2(50)    UNIQUE NOT NULL, -- 한번 등록된 nickname은 중복 안됨
  m_email       VARCHAR2(50)    UNIQUE NOT NULL, -- 한번 등록된 email은 중복 안됨   
  m_passwd     VARCHAR2(60)    NOT NULL, -- 패스워드, 영숫자 조합 
  m_age         VARCHAR2(2)      NOT NULL, -- 나이, 99
  m_act          CHAR(1)            DEFAULT 'N' NOT NULL,
  rdate                  DATE                NOT NULL,
PRIMARY KEY (memberno)
);

COMMENT ON TABLE member is '회원';
COMMENT ON COLUMN member.memberno is '회원 번호';
COMMENT ON COLUMN member.m_name is '회원 이름';
COMMENT ON COLUMN member.m_nickname is '회원 닉네임';
COMMENT ON COLUMN member.m_email is '회원 이메일';
COMMENT ON COLUMN member.m_passwd is '회원 비밀번호';
COMMENT ON COLUMN member.m_age is '회원 나이';
COMMENT ON COLUMN member.m_act is '회원 권한';
COMMENT ON COLUMN member.rdate is '회원 등록일';


1. 등록
-- ** 이메일 중복 확인 **
SELECT COUNT(m_email) as cnt
FROM member
WHERE m_email = 'test1@gmail.com';

-- ** 닉네임 중복 확인 **
SELECT COUNT(m_nickname) as cnt
FROM member
WHERE m_nickname = '아이유짱';

-- 일반 회원 기준
-- ACT = {"G"(General) = 일반회원}, {"M"(Master) = 관리자} 
INSERT INTO member(memberno, m_name, m_nickname, m_email, m_passwd, 
                             m_age, m_act, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'관리자', '아이유짱', 'master@gmail.com', '1113', '27', 'M', sysdate);

INSERT INTO member(memberno, m_name, m_nickname, m_email, m_passwd, 
                             m_age, m_act, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'우가네', '이지은짱', 'test@gmail.com', '1113', '25', 'G', sysdate);

INSERT INTO member(memberno, m_name, m_nickname, m_email, m_passwd, 
                             m_age, m_act, m_thumbs, m_files, m_sizes, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'펭가네', '펭돌짱', 'test3@gmail.com', '1113', '5', 'G', 'test3_t.jpg', 'test3.jpg', 0, sysdate);


2. 목록
- 검색을 하지 않는 경우, 전체 목록 출력

SELECT memberno, m_name, m_nickname, m_email, m_passwd, 
          m_age, m_act, rdate
FROM member
ORDER BY memberno ASC;

 MEMBERNO M_NAME M_NICKNAME M_EMAIL         M_PASSWD M_AGE M_ACT M_THUMBS      M_FILES     M_SIZES RDATE
 -------- ------ ---------- --------------- -------- ----- ----- ------------- ----------- ------- ---------------------
        1 김가네    아이유짱       test1@gmail.com 1113     23    G     dlwlrma_t.jpg dlwlrma.jpg 0       2018-12-11 16:54:28.0
        2 우가네    이지은짱       test2@gmail.com 1113     25    G     test2_t.jpg   test2.jpg   0       2018-12-11 16:56:39.0
        3 펭가네    펭돌짱        test3@gmail.com 1113     5     G     test3_t.jpg   test3.jpg   0       2018-12-11 16:56:40.0


3. 조회
--일반 회원 정보 보기
SELECT memberno, m_name, m_nickname, m_email, m_passwd, 
          m_age, m_act, rdate
FROM member
WHERE memberno = 1;

 MEMBERNO M_NAME M_NICKNAME M_EMAIL         M_PASSWD M_AGE M_ACT M_THUMBS      M_FILES     M_SIZES RDATE
 -------- ------ ---------- --------------- -------- ----- ----- ------------- ----------- ------- ---------------------
        1 김가네    아이유짱       test1@gmail.com 1113     23    G     dlwlrma_t.jpg dlwlrma.jpg 0       2018-12-11 16:54:28.0


SELECT memberno, m_name, m_nickname, m_email, m_passwd, 
          m_age, m_act, m_thumbs, m_files, m_sizes, rdate
FROM member
WHERE m_age = '5';

 MEMBERNO M_NAME M_NICKNAME M_EMAIL         M_PASSWD M_AGE M_ACT M_THUMBS    M_FILES   M_SIZES RDATE
 -------- ------ ---------- --------------- -------- ----- ----- ----------- --------- ------- ---------------------
        3 펭가네    펭돌짱        test3@gmail.com 1113     5     G     test3_t.jpg test3.jpg 0       2018-12-11 16:56:40.0


              
4. 수정
UPDATE member 
SET m_nickname='관리자'
WHERE memberno = 1;

5. 패스워드 변경
1) 패스워드 검사
SELECT COUNT(memberno) as cnt
FROM member
WHERE memberno=1 AND m_passwd='1113';

 CNT
 ---
   1

2) 패스워드 수정
UPDATE member
SET m_passwd='0516'
WHERE memberno=1;


6. 삭제
1) 모두 삭제
DELETE FROM member;

2) 특정 회원 삭제
DELETE FROM member
WHERE memberno=4;


7. 로그인
SELECT COUNT(memberno) as cnt
FROM member
WHERE m_email='dlwlrma@gmail.com' AND m_passwd='0516';
 CNT
 ---
   1

-------------------------------------------------------------------------------------

