DROP TABLE hall;

/**********************************/
/* Table Name: ������ */
/**********************************/

1. ���̺� ����
CREATE TABLE hall(
  hallno             NUMBER(7)                     NOT NULL,
  title                 VARCHAR2(50)                NOT NULL,
  content           CLOB                              NOT NULL,
  img                VARCHAR2(100)                       NULL,
  thumbs             VARCHAR2(100)                      NULL,
  sizes                NUMBER(10)                           NULL,
  map               VARCHAR2(2000)                       NULL,
  rdate               DATE                            NOT NULL,
  PRIMARY KEY(hallno)
);

COMMENT ON TABLE hall is '������ ����';
COMMENT ON COLUMN hall.hallno is '������ ��ȣ';
COMMENT ON COLUMN hall.title is '������ �̸�';
COMMENT ON COLUMN hall.content is '������ ����';
COMMENT ON COLUMN hall.img is '����';
COMMENT ON COLUMN hall.thumbs is '�����';
COMMENT ON COLUMN hall.sizes is '������';
COMMENT ON COLUMN hall.map is '����';
COMMENT ON COLUMN hall.rdate is '�����';


2. ���
INSERT INTO hall(hallno, title, content, img, thumbs, sizes, map, rdate)
VALUES((SELECT NVL(MAX(hallno), 0) + 1 as hallno FROM hall), '������б� ȭ��ü����', '���� ���ϱ� �ȾϷ� 145 (�Ⱦϵ�5��, ������б��Ⱦ�ķ�۽� �ڿ���)', 
            'korea.jpg', 'daum map', sysdate);
            
INSERT INTO hall(hallno, contentsno, title, content, img, map, rdate)
VALUES(2, 2, '�ø��Ȱ��� SK�ø����ڵ庼�����', '���� ���ı� ���̵� 88', 
            'olympicpark.jpg', 'daum map', sysdate);
            
INSERT INTO hall(hallno, contentsno, title, content, img, map, rdate)
VALUES(3, 3, '������б� ��ȭ������', '���� ���빮�� ȸ�⵿ 1-5', 
            'kyunghee.jpg', 'daum map', sysdate);
            
            
3. ���
SELECT hallno, title, content, img, thumbs, sizes, map, rdate
FROM hall
ORDER BY hallno DESC;


4. ��ȸ
SELECT hallno, title, content, img, thumbs, sizes, map, rdate
FROM hall
WHERE hallno=1;

1) ��ü ���ڵ� ��
SELECT COUNT(hallno) as cnt
FROM hall


5. ����
UPDATE hall
SET title='��ǽǳ�ü����', content='���� ���ı� �ø��ȷ� 25 (��ǵ�, �������տ��)'
WHERE hallno=1;


6. ����
DELETE FROM hall
WHERE hallno=1;