package dev.mvc.gallery;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class GalleryVO {
  private int galleryno;
  private int memberno;
  private String m_nickname;
  private String g_title;
  private String g_content;
  private int g_cnt;
  private String thumbs ="";
  private String files ="";
  private String sizes ="";
  private String g_word;
  private String rdate;
  
  /** 
  Spring Framework에서 자동 주입되는 업로드 파일 객체,
  DBMS 상에 실제 컬럼은 존재하지 않고 파일 임시 저장 목적.
  여러개의 파일 업로드
  */  
  private List<MultipartFile> filesMF;

  /** size1의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지 않음. */
  private String sizesLabel;
  
  
  
  public String getM_nickname() {
    return m_nickname;
  }
  public void setM_nickname(String m_nickname) {
    this.m_nickname = m_nickname;
  }
  public List<MultipartFile> getFilesMF() {
    return filesMF;
  }
  public void setFilesMF(List<MultipartFile> filesMF) {
    this.filesMF = filesMF;
  }
  public String getSizesLabel() {
    return sizesLabel;
  }
  public void setSizesLabel(String sizesLabel) {
    this.sizesLabel = sizesLabel;
  }
  public int getGalleryno() {
    return galleryno;
  }
  public void setGalleryno(int galleryno) {
    this.galleryno = galleryno;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public String getG_title() {
    return g_title;
  }
  public void setG_title(String g_title) {
    this.g_title = g_title;
  }
  public String getG_content() {
    return g_content;
  }
  public void setG_content(String g_content) {
    this.g_content = g_content;
  }
  public int getG_cnt() {
    return g_cnt;
  }
  public void setG_cnt(int g_cnt) {
    this.g_cnt = g_cnt;
  }
  public String getThumbs() {
    return thumbs;
  }
  public void setThumbs(String thumbs) {
    this.thumbs = thumbs;
  }
  public String getFiles() {
    return files;
  }
  public void setFiles(String files) {
    this.files = files;
  }
  public String getSizes() {
    return sizes;
  }
  public void setSizes(String sizes) {
    this.sizes = sizes;
  }
  public String getG_word() {
    return g_word;
  }
  public void setG_word(String g_word) {
    this.g_word = g_word;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }

  
}
