package dev.mvc.hall;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class HallVO {
  /** 공연장 번호 **/
  private int hallno;
  /** 공연장 이름 **/
  private String title;
  /** 공연장 내용 **/
  private String content;
  /** 사진 **/
  private String img;
  /** 썸네일 **/
  private String thumbs;
  /** 사이즈 **/
  private long sizes;
  /** 지도 **/
  private String map;
  /** 등록일 **/
  private String rdate;
  
  /** 
  Spring Framework에서 자동 주입되는 업로드 파일 객체,
  DBMS 상에 실제 컬럼은 존재하지 않고 파일 임시 저장 목적.
  여러개의 파일 업로드
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
