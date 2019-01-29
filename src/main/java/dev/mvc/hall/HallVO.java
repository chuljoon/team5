package dev.mvc.hall;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class HallVO {
  /** ������ ��ȣ **/
  private int hallno;
  /** ������ �̸� **/
  private String title;
  /** ������ ���� **/
  private String content;
  /** ���� **/
  private String img;
  /** ����� **/
  private String thumbs;
  /** ������ **/
  private long sizes;
  /** ���� **/
  private String map;
  /** ����� **/
  private String rdate;
  
  /** 
  Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü,
  DBMS �� ���� �÷��� �������� �ʰ� ���� �ӽ� ���� ����.
  �������� ���� ���ε�
  */  
  private MultipartFile file1MF;
  
  public HallVO() {
    
  }

  public int getHallno() {
    return hallno;
  }

  public void setHallno(int hallno) {
    this.hallno = hallno;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public String getThumbs() {
    return thumbs;
  }

  public void setThumbs(String thumbs) {
    this.thumbs = thumbs;
  }

  public long getSizes() {
    return sizes;
  }

  public void setSizes(long sizes) {
    this.sizes = sizes;
  }

  public String getMap() {
    return map;
  }

  public void setMap(String map) {
    this.map = map;
  }

  public String getRdate() {
    return rdate;
  }

  public void setRdate(String rdate) {
    this.rdate = rdate;
  }

  public MultipartFile getFile1MF() {
    return file1MF;
  }

  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }

}
