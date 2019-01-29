package dev.mvc.expect;

import org.springframework.web.multipart.MultipartFile;

public class ExpectVO {
  /** 기대평 번호 **/
  private int expectno;
  /** 공연정보 번호 **/
  private int contentsno;
  /** 기대평 내용 **/
  private String content;
  /** 사진 **/
  private String imgs;
  /** 썸네일 **/
  private String thumbs;
  /** 사이즈 **/
  private long sizes;
  /** 등록일 **/
  private String rdate;
  
  private MultipartFile filesMF;
  
  public ExpectVO() {
    
  }

  public int getExpectno() {
    return expectno;
  }

  public void setExpectno(int expectno) {
    this.expectno = expectno;
  }

  public int getContentsno() {
    return contentsno;
  }

  public void setContentsno(int contentsno) {
    this.contentsno = contentsno;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getImgs() {
    return imgs;
  }

  public void setImgs(String imgs) {
    this.imgs = imgs;
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

  public String getRdate() {
    return rdate;
  }

  public void setRdate(String rdate) {
    this.rdate = rdate;
  }

  public MultipartFile getFilesMF() {
    return filesMF;
  }

  public void setFilesMF(MultipartFile filesMF) {
    this.filesMF = filesMF;
  }

}
