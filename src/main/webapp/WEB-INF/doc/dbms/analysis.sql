-- 분석 결과 등록 테이블
DROP TABLE analysis


CREATE TABLE analysis (
  analysisno NUMBER(7) NOT NULL PRIMARY KEY,
  title VARCHAR2(300) NOT NULL,
  content CLOB NOT NULL,
  file1 VARCHAR2(1000) NULL,
  thumb1 VARCHAR2(50) NULL,
  rdate DATE NOT NULL,
  cnt NUMBER(7) DEFAULT 0 NOT NULL,
  adminno NUMBER(7) NOT NULL,
  FOREIGN KEY (adminno) REFERENCES ADMIN(adminno)
);

-- Upgrade
-- 











-- 