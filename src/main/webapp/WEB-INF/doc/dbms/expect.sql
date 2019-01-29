DROP TABLE expect;

/**********************************/
/* Table Name: ����� */
/**********************************/

1. ���̺� ����
CREATE TABLE expect(
  expectno          NUMBER(7)                     NOT NULL,
  contentsno      NUMBER(7)                      NOT NULL,
  content           CLOB                              NOT NULL,
  imgs                 VARCHAR2(100)                      NULL,
  thumbs             VARCHAR2(100)                       NULL,
  sizes                 NUMBER(10)                           NULL,
  rdate               DATE                            NOT NULL,
  PRIMARY KEY(expectno),
  FOREIGN KEY (contentsno) REFERENCES contents (contentsno)
);

COMMENT ON TABLE expect is '�����';
COMMENT ON COLUMN expect.expectno is '����� ��ȣ';
COMMENT ON COLUMN expect.contentsno is '�������� ��ȣ';
COMMENT ON COLUMN expect.content is '����� ����';
COMMENT ON COLUMN expect.imgs is '����';
COMMENT ON COLUMN expect.thumbs is '�����';
COMMENT ON COLUMN expect.sizes is '������';
COMMENT ON COLUMN expect.rdate is '�����';


2. ���
INSERT INTO expect(expectno, contentsno, content, imgs, thumbs, sizes, rdate)
VALUES(1, 1, '1234', '���Ǵ�Ʈ �ʹ� ���ƿ� ��ǥ����', 'infilove.jpg', 'infinite.mp4', '��������.mp3', 'http://woolliment.phps.kr/artists/main_infinite.php', sysdate);

INSERT INTO expect(expectno, contentsno, passwd, content, rdate)
VALUES(2, 2, '1234', '�����', sysdate);

INSERT INTO expect(expectno, contentsno, passwd, content, rdate)
VALUES(3, 3, '1234', '�����2', sysdate);


3. ���
SELECT expectno, contentsno, content, imgs, thumbs, sizes, rdate
FROM expect
ORDER BY expectno DESC;

SELECT expectno, contentsno, content, imgs, thumbs, sizes, rdate
FROM expect
WHERE contentsno=1
ORDER BY expectno DESC;

1) ����¡
-- step 1
SELECT expectno, contentsno, passwd, content, imgs, rdate
FROM expect
WHERE contentsno=1
ORDER BY expectno DESC;

-- step 2
SELECT expectno, contentsno, passwd, content, imgs, mp4, mp3, url, rdate, rownum as r
FROM(
        SELECT expectno, contentsno, passwd, content, imgs, mp4, mp3, url, rdate
        FROM expect
        WHERE contentsno=1
        ORDER BY expectno DESC
);

-- step 3
SELECT expectno, contentsno, passwd, content, imgs, mp4, mp3, url, rdate, r
FROM(
SELECT expectno, contentsno, passwd, content, imgs, mp4, mp3, url, rdate, rownum as r
FROM(
        SELECT expectno, contentsno, passwd, content, imgs, mp4, mp3, url, rdate
        FROM expect
        WHERE contentsno=1
        ORDER BY expectno DESC
        )
)
WHERE r >= 1 AND r <= 3;


4. ��ȸ
SELECT expectno, contentsno, content, imgs, thumbs, sizes, rdate
FROM expect
WHERE expectno=1;

1) ��ü ���ڵ� ��
SELECT COUNT(expectno) as cnt
FROM expect


5. ����
UPDATE expect
SET content='���Ǵ�Ʈ ����ؿ� �� ��ǥ �����'
WHERE expectno=1;


6. ����
DELETE FROM expect
WHERE expectno=1;