1. 2�� �̻��� ���̺� ����: JOIN 
      - PK, FK �÷��� ������� �մϴ�.

1) FK ���̺� ������ ����
DELETE FROM question;

2) PK ���̺� ������ ����
DELETE FROM member;

3) PK ���̺� ������ �߰�
-- �Ϲ� ȸ�� ����
-- ACT = {"G"(General) = �Ϲ�ȸ��}, {"M"(Master) = ������} 
INSERT INTO member(memberno, m_name, m_nickname, m_email, m_passwd, 
                             m_age, m_act, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'������', '������¯', 'master@gmail.com', '1113', '27', 'M', sysdate);

INSERT INTO member(memberno, m_name, m_nickname, m_email, m_passwd, 
                             m_age, m_act, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'�׽���', '�׽���', 'test@test.com', '1234', '25', 'G', sysdate);


4) FK ���̺� ������ �߰�
INSERT INTO question(questionno, memberno, question_title, question_contents,
                              question_thumb, question_file1, question_size1, question_cnt,
                              question_replycnt, question_indent, question_ansnum, question_word, rdate)  
VALUES((SELECT NVL(MAX(questionno), 0) + 1 as questionno FROM question),
            1, '����', '����', 'fall_m.jpg', 'fall.jpg', 0, 0, 0, 0, 0, '������,spring,��,��,����,����,������', sysdate);
INSERT INTO question(questionno, memberno, question_title, question_contents,
                              question_thumb, question_file1, question_size1, question_cnt,
                              question_replycnt, question_indent, question_ansnum, question_word, rdate)  
VALUES((SELECT NVL(MAX(questionno), 0) + 1 as questionno FROM question),
            2, '����', '����', 'fall_m.jpg', 'fall.jpg', 0, 0, 0, 0, 0, '������,spring,��,��,����,����,������', sysdate);
            
            
2. Cross join
- �����μ��� ��ġ�� �ſ� ������.
- �������� ����.

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
            

3. Equal JOIN�� FK ���̺��� �������� �մϴ�.
- WHERE �׷�(�θ�) ���̺� PK = ������(�ڽ�) ���̺� FK
- WHERE c.categrpno = t.categrpno: 2���� ���̺��� categrpno �÷��� ����
  ���ڵ带 �о� �޸𸮻󿡼� �ϳ��� ���ڵ�� �����Ͽ� �޸� ���̺���
  �����մϴ�. (DBMS�� ���� �޸� ���)

SELECT m.memberno, m.m_name, m.m_email,
           q.questionno, q.memberno, q.question_title, q.question_contents, q.question_thumb, 
           q.question_file1, q.question_size1, q.question_cnt, q.question_replycnt,
           q.question_indent, q.question_ansnum, q.question_word, q.rdate
FROM member m, question q 
WHERE m.memberno = q.questionno
ORDER BY m.memberno ASC, q.rdate ASC;




            
            
            
            
            
            
            