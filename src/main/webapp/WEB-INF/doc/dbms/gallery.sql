DROP TABLE gallery;

CREATE TABLE gallery(
  galleryno     NUMBER(7)        NOT NULL        PRIMARY KEY, -- ������ ��ȣ
  memberno   NUMBER(7)                              NOT NULL, -- ȸ�� ��ȣ
  g_title         VARCHAR2(300)                        NOT NULL, -- ������ ����
  g_content    CLOB                                      NOT NULL, -- ������ ����
  g_cnt         NUMBER(7)        DEFAULT 0        NOT NULL, -- ������ ��ȸ��
  thumbs       VARCHAR2(1000)                             NULL, -- Thumb ����
  files           VARCHAR2(1000)                             NULL, -- ���ϵ��� �̸�
  sizes          VARCHAR2(1000)                             NULL, -- ���ϵ��� ũ��
  g_word       VARCHAR2(100)                                NULL, -- �˻���
  rdate         DATE                                             NULL, -- ��� ��¥
  FOREIGN KEY (memberno) REFERENCES MEMBER (memberno)
);

COMMENT ON TABLE gallery is '������ ����';
COMMENT ON COLUMN gallery.galleryno is '������ ��ȣ';
COMMENT ON COLUMN gallery.memberno is 'ȸ�� ��ȣ';
COMMENT ON COLUMN gallery.g_title is '���� ����';
COMMENT ON COLUMN gallery.g_content is '���� ����';
COMMENT ON COLUMN gallery.g_cnt is '���� ��ȸ��';
COMMENT ON COLUMN gallery.thumbs is 'Thumb ����';
COMMENT ON COLUMN gallery.files is '���ϵ��� �̸�';
COMMENT ON COLUMN gallery.sizes is '���ϵ��� ũ��';
COMMENT ON COLUMN gallery.g_word is '�˻���';
COMMENT ON COLUMN gallery.rdate is '�����';

1. ���
INSERT INTO gallery(galleryno, memberno, g_title, g_content, g_cnt, thumbs, files, sizes, g_word, rdate)  
VALUES((SELECT NVL(MAX(galleryno), 0) + 1 as galleryno FROM gallery), 
            1, '������ 10�ֳ� �ܼ�Ʈ', '������ 10�ֳ� �ܼ�Ʈ', 0, 'fall_m.jpg', 'fall.jpg', 0, '������,10�ֳ�,�ܼ�Ʈ', sysdate);

INSERT INTO gallery(galleryno, memberno, g_title, g_content, g_cnt, thumbs, files, sizes, g_word, rdate)  
VALUES((SELECT NVL(MAX(galleryno), 0) + 1 as galleryno FROM gallery), 
            1, '������ 11�ֳ� �ܼ�Ʈ', '������ 11�ֳ� �ܼ�Ʈ', 0, 'fall_m.jpg', 'fall.jpg', 0, '������,11�ֳ�,�ܼ�Ʈ', sysdate);
            
INSERT INTO gallery(galleryno, memberno, g_title, g_content, g_cnt, thumbs, files, sizes, g_word, rdate)  
VALUES((SELECT NVL(MAX(galleryno), 0) + 1 as galleryno FROM gallery), 
            1, '������ 12�ֳ� �ܼ�Ʈ', '������ 12�ֳ� �ܼ�Ʈ', 0, 'fall_m.jpg', 'fall.jpg', 0, '������,12�ֳ�,�ܼ�Ʈ', sysdate);            

2. ���
SELECT * FROM gallery;
SELECT galleryno,
          memberno, g_title, g_content, g_cnt, thumbs, files, sizes, g_word, rdate
FROM gallery
ORDER BY rdate DESC;
 GALLERYNO MEMBERNO G_TITLE      G_CONTENT    G_CNT THUMBS     FILES    SIZES G_WORD       RDATE
 --------- -------- ------------ ------------ ----- ---------- -------- ----- ------------ ---------------------
         1        1 ������ 10�ֳ� �ܼ�Ʈ ������ 10�ֳ� �ܼ�Ʈ     0 fall_m.jpg fall.jpg 0     ������,10�ֳ�,�ܼ�Ʈ 2019-01-28 09:56:25.0
         2        1 ������ 11�ֳ� �ܼ�Ʈ ������ 11�ֳ� �ܼ�Ʈ     0 fall_m.jpg fall.jpg 0     ������,11�ֳ�,�ܼ�Ʈ 2019-01-28 09:56:57.0
         3        1 ������ 12�ֳ� �ܼ�Ʈ ������ 12�ֳ� �ܼ�Ʈ     0 fall_m.jpg fall.jpg 0     ������,12�ֳ�,�ܼ�Ʈ 2019-01-28 09:56:58.0

       
3. ��ȸ
SELECT galleryno,
          memberno, g_title, g_content, g_cnt, thumbs, files, sizes, g_word, rdate
FROM gallery
WHERE galleryno=2; 
 GALLERYNO MEMBERNO G_TITLE      G_CONTENT    G_CNT THUMBS     FILES    SIZES G_WORD       RDATE
 --------- -------- ------------ ------------ ----- ---------- -------- ----- ------------ ---------------------
         2        1 ������ 11�ֳ� �ܼ�Ʈ ������ 11�ֳ� �ܼ�Ʈ     0 fall_m.jpg fall.jpg 0     ������,11�ֳ�,�ܼ�Ʈ 2019-01-28 09:56:57.0



4. ����
UPDATE gallery
SET g_title='���γ�', g_content='���γ��� �Ļ�',
     thumbs='dinner_m.jpg', files='fall.jpg', sizes= 1500, g_word='���γ�, ����'
WHERE galleryno=2;
 GALLERYNO MEMBERNO G_TITLE G_CONTENT G_CNT THUMBS       FILES    SIZES G_WORD  RDATE
 --------- -------- ------- --------- ----- ------------ -------- ----- ------- ---------------------
         2        1 ���γ�     ���γ��� �Ļ�       0 dinner_m.jpg fall.jpg 1500  ���γ�, ���� 2019-01-28 09:56:57.0


5. ����
DELETE FROM gallery
WHERE galleryno=3;


6. ��ȸ�� ����
UPDATE gallery
SET g_cnt = g_cnt + 1
WHERE galleryno=1;












