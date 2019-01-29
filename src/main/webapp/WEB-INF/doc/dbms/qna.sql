/**************************************************/
/* Table Name: 질문(question) */
/**************************************************/
DROP TABLE question; 

CREATE TABLE question(
  questionno             NUMBER(7)                                 NOT NULL, -- 질문 번호
  memberno             NUMBER(7)                                  NOT NULL, -- 회원 번호
  question_title          VARCHAR2(30)                             NOT NULL, -- 질문 제목
  question_contents    VARCHAR2(3000)                          NOT NULL, -- 질문 내용
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

COMMENT ON TABLE question is '질문답변(질문)';
COMMENT ON COLUMN question.questionno is '질문답변(질문) 번호';
COMMENT ON COLUMN question.memberno is '회원 번호';
COMMENT ON COLUMN question.question_title is '질문 제목';
COMMENT ON COLUMN question.question_contents is '질문 내용';
COMMENT ON COLUMN question.question_thumb is '질문 Thumb파일';
COMMENT ON COLUMN question.question_file1 is '질문 파일들의 이름';
COMMENT ON COLUMN question.question_size1 is '질문 파일들의 크기';
COMMENT ON COLUMN question.question_cnt is '질문 조회수';
COMMENT ON COLUMN question.question_replycnt is '댓글수';
COMMENT ON COLUMN question.question_grpno is '그룹 번호';
COMMENT ON COLUMN question.question_indent is '답변차수';
COMMENT ON COLUMN question.question_ansnum is '답변 순서';
COMMENT ON COLUMN question.question_word is '검색어';
COMMENT ON COLUMN question.rdate is '등록일';

-- ERROR: FK 컬럼에 사용할 값이 다른 테이블에 등록이 안되어 있는 경우
    ORA-02291: integrity constraint (SOLDESK.SYS_C007131) violated - parent key not found
    ORA-02291: integrity constraint (SOLDESK.SYS_C007132) violated - parent key not found

1. 등록
INSERT INTO question(questionno, memberno, question_title, question_contents,
                              question_thumb, question_file1, question_size1, question_cnt,
                              question_replycnt, question_grpno, question_indent, question_ansnum, question_word, rdate)  
VALUES((SELECT NVL(MAX(questionno), 0) + 1 as questionno FROM question),
            1, '제목', '내용', 'fall_m.jpg', 'fall.jpg', 0, 0, 0,
            (SELECT NVL(MAX(question_grpno), 0) + 1 as question_grpno FROM question), 
            0, 0, '스프링,spring,봄,春,계절,냉이,개나리', sysdate);
INSERT INTO question(questionno, memberno, question_title, question_contents,
                              question_thumb, question_file1, question_size1, question_cnt,
                              question_replycnt, question_grpno, question_indent, question_ansnum, question_word, rdate)  
VALUES((SELECT NVL(MAX(questionno), 0) + 1 as questionno FROM question),
            1, '제목', '내용', 'fall_m.jpg', 'fall.jpg', 0, 0, 0,
            (SELECT NVL(MAX(question_grpno), 0) + 1 as question_grpno FROM question), 
            0, 0, '스프링,spring,봄,春,계절,냉이,개나리', sysdate);


2. 목록
-- 전체 목록
SELECT questionno, memberno, question_title, question_grpno, question_replycnt, question_indent, question_ansnum 
FROM question
ORDER BY rdate DESC, question_ansnum ASC;
-- QUESTIONNO MEMBERNO QUESTION_TITLE QUESTION_REPLYCNT QUESTION_INDENT QUESTION_ANSNUM
-- ---------- -------- -------------- ----------------- --------------- ---------------
--          5        1                답변1                            0                          0                          0
--          4        1 부모글1                           0               0               0
--          3        1 답변 테스트                         0               0               0
--          2        1 바다건너                           0               0               0
--          1        1 테스트                            0               0               0

          
          
SELECT questionno, memberno, question_title, question_contents, 
          question_thumb, question_file1, question_size1, question_cnt, 
          question_replycnt, question_indent, question_ansnum, question_word, rdate
FROM question
ORDER BY rdate DESC, question_ansnum ASC;

-- 회원 번호별 목록
SELECT questionno, memberno, question_title, question_contents, 
          question_thumb, question_file1, question_size1, question_cnt, 
          question_replycnt, question_indent, question_ansnum, question_word, rdate
FROM question
WHERE memberno = 1
ORDER BY rdate DESC, question_ansnum ASC;

 QUESTIONNO MEMBERNO QUESTION_TITLE QUESTION_CONTENTS QUESTION_THUMB QUESTION_FILE1 QUESTION_SIZE1 QUESTION_CNT QUESTION_REPLYCNT QUESTION_INDENT QUESTION_ANSNUM QUESTION_WORD                RDATE
 --------------- -------------  ------------------ ------------------------  --------------------- -----------------  ------------------  ---------------- ------------------------ ---------------------- ----------------------- ---------------------------- ---------------------
          4             1                 제목                내용                    fall_m.jpg                   fall.jpg           0                         0                 0               0               0 스프링,spring,봄,春,계절,냉이,개나리     2019-01-23 12:53:29.0
          3             1                 제목                내용                  summer_m.jpg          summer.jpg         0                         0                 0               1               1 NULL                         2019-01-23 12:50:44.0
          2             1                 test123             test123                   NULL                    NULL            0                         0                 0               0               0 ,test123                     2019-01-23 11:50:00.0
          1             1                 제목111           내용111                 fall_m.jpg                  fall.jpg           0                         0                 0               0               0 ,스프링,spring,봄,春,계절,냉이,개나리111 2019-01-23 11:30:23.0
  

3. 전체 카운트
SELECT COUNT(*) as count
FROM question;

 COUNT
 -----
     2
     
4. 조회
SELECT questionno, memberno, question_title, question_contents, 
          question_thumb, question_file1, question_size1, question_cnt, 
          question_replycnt, question_indent, question_ansnum, question_word, rdate
FROM question
WHERE questionno=1; 

5. 수정
UPDATE question
SET question_title='겨울', question_contents='손이시려워...', 
      question_thumb='snow_t.jpg', question_file1='snow.jpg', question_size1=1500, question_word='휴가'
WHERE questionno=1;

6. 삭제
DELETE FROM question
WHERE questionno=4

7. question별 검색된 레코드 목록
SELECT questionno, memberno, question_title, question_contents, 
          question_thumb, question_file1, question_size1, question_cnt, 
          question_replycnt, question_indent, question_ansnum, question_word, rdate
FROM question
WHERE question_word LIKE '%test%'
ORDER BY rdate DESC;

8. 'test' 검색 레코드 갯수
SELECT COUNT(*) as cnt
FROM question
WHERE question_word LIKE '%test%';

SELECT COUNT(*) as cnt
FROM question
WHERE question_word = null;


15) 답변
[답변 쓰기]
-- 1번글 기준 답변 등록예: grpno: 1, indent: 1, ansnum: 1
SELECT * FROM member;
SELECT * FROM categrp;
SELECT * FROM category;

① 새로운 답변을 최신으로 등록하기위해 기존 답변을 뒤로 미룹니다.
-- 모든 글의 우선 순위가 1씩 증가됨, 1등 -> 2등
UPDATE question
SET question_ansnum = question_ansnum + 1
WHERE memberno=1 AND question_grpno = 1 AND question_ansnum > 0;

-- 2등부터 우선 순위가 1씩 증가됨, 2등 -> 3등
UPDATE blog
SET ansnum = ansnum + 1
WHERE categoryno=1 AND grpno = 1 AND ansnum > 1;
 
-- 3등부터 우선 순위가 1씩 증가됨, 3등 -> 4등
UPDATE blog
SET ansnum = ansnum + 1
WHERE categoryno=1 AND grpno = 1 AND ansnum > 2;

-- 6등부터 우선 순위가 1씩 증가됨, 6등 -> 7등
UPDATE blog
SET ansnum = ansnum + 1
WHERE categoryno=1 AND grpno = 1 AND ansnum > 5;

SELECT * FROM member;

② 답변 등록
INSERT INTO question(questionno,
                          memberno, question_title, question_contents, question_thumb, question_file1, question_size1, 
                          question_cnt, question_replycnt, rdate, 
                          question_grpno, question_indent, question_ansnum, question_word)  
VALUES((SELECT NVL(MAX(questionno), 0) + 1 as questionno FROM question),
            1, '답변', '내용', 'summer_m.jpg', 'summer.jpg', 0, 0, 0, sysdate,
            1, 1, 1,'');


③ 답변에 따른 정렬 순서 변경    
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

     










