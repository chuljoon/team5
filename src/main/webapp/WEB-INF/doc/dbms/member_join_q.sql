1. 2개 이상의 테이블 연결: JOIN 
      - PK, FK 컬럼을 대상으로 합니다.

1) FK 테이블 데이터 삭제
DELETE FROM question;

2) PK 테이블 데이터 삭제
DELETE FROM member;

3) PK 테이블 데이터 추가
-- 일반 회원 기준
-- ACT = {"G"(General) = 일반회원}, {"M"(Master) = 관리자} 
INSERT INTO member(memberno, m_name, m_nickname, m_email, m_passwd, 
                             m_age, m_act, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'관리자', '아이유짱', 'master@gmail.com', '1113', '27', 'M', sysdate);

INSERT INTO member(memberno, m_name, m_nickname, m_email, m_passwd, 
                             m_age, m_act, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'테스터', '테스터', 'test@test.com', '1234', '25', 'G', sysdate);


4) FK 테이블 데이터 추가
INSERT INTO question(questionno, memberno, question_title, question_contents,
                              question_thumb, question_file1, question_size1, question_cnt,
                              question_replycnt, question_indent, question_ansnum, question_word, rdate)  
VALUES((SELECT NVL(MAX(questionno), 0) + 1 as questionno FROM question),
            1, '제목', '내용', 'fall_m.jpg', 'fall.jpg', 0, 0, 0, 0, 0, '스프링,spring,봄,春,계절,냉이,개나리', sysdate);
INSERT INTO question(questionno, memberno, question_title, question_contents,
                              question_thumb, question_file1, question_size1, question_cnt,
                              question_replycnt, question_indent, question_ansnum, question_word, rdate)  
VALUES((SELECT NVL(MAX(questionno), 0) + 1 as questionno FROM question),
            2, '제목', '내용', 'fall_m.jpg', 'fall.jpg', 0, 0, 0, 0, 0, '스프링,spring,봄,春,계절,냉이,개나리', sysdate);
            
            
2. Cross join
- 정보로서의 가치가 매우 부족함.
- 권장하지 않음.

SELECT member.memberno, member.m_name, member.m_email,
           question.questionno, question.memberno, question.question_title, question.question_contents, question.question_thumb, 
           question.question_file1, question.question_size1, question.question_cnt, question.question_replycnt,
           question.question_indent, question.question_ansnum, question.question_word, question.rdate
FROM member, question
ORDER BY member.memberno ASC, question.rdate ASC;

SELECT m.memberno, m.m_name, m.m_email,
           q.questionno, q.memberno, q.question_title, q.question_contents, q.question_thumb, 
           q.question_file1, q.question_size1, q.question_cnt, q.question_replycnt,
           q.question_indent, q.question_ansnum, q.question_word, q.rdate
FROM member m, question q
ORDER BY m.memberno ASC, q.rdate ASC;
            

3. Equal JOIN시 FK 테이블을 기준으로 합니다.
- WHERE 그룹(부모) 테이블 PK = 데이터(자식) 테이블 FK
- WHERE c.categrpno = t.categrpno: 2개의 테이블에서 categrpno 컬럼이 같은
  레코드를 읽어 메모리상에서 하나의 레코드로 결합하여 메모리 테이블을
  생성합니다. (DBMS는 많은 메모리 사용)

SELECT m.memberno, m.m_name, m.m_email,
           q.questionno, q.memberno, q.question_title, q.question_contents, q.question_thumb, 
           q.question_file1, q.question_size1, q.question_cnt, q.question_replycnt,
           q.question_indent, q.question_ansnum, q.question_word, q.rdate
FROM member m, question q 
WHERE m.memberno = q.questionno
ORDER BY m.memberno ASC, q.rdate ASC;




            
            
            
            
            
            
            