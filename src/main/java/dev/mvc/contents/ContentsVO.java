package dev.mvc.contents;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ContentsVO {
  /** 글 번호 **/
  private int contentsno;
  /** 카테고리 번호 **/
  private int s_categoryno;
  /** 공연명 **/
  private String title;
  /** 공연 포스터 **/
  private String title_img;
  /** 게시글 비밀번호 **/
  private String passwd;
  /** 공연정보 내용 **/
  private String contents;
  /** 썸네일 **/
  private String thumbs;
  /** 작품 설명 파일 **/
  private String files;
  /** 포스터 사이즈 **/
  private long sizes;
  /** 작품설명 사이즈 **/
  private long file_size;
  /** 공연 일시 **/
  private String p_date;
  /** 검색어 **/
  private String word;
  /** 등록일 **/
  private String rdate;
  
  /** 
  Spring Framework에서 자동 주입되는 업로드 파일 객체,
  DBMS 상에 실제 컬럼은 존재하지 않고 파일 임시 저장 목적.
  여러개의 파일 업로드
  */  
  private MultipartFile file1MF;
  
  private MultipartFile title_imgMF;

  /** size1의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지 않음. */
  private String sizesLabel;

  public ContentsVO() {

  }

  public int getContentsno() {
    return contentsno;
  }

  public void setContentsno(int contentsno) {
    this.contentsno = contentsno;
  }

  public int getS_categoryno() {
    return s_categoryno;
  }

  public void setS_categoryno(int s_categoryno) {
    this.s_categoryno = s_categoryno;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle_img() {
    return title_img;
  }

  public void setTitle_img(String title_img) {
    this.title_img = title_img;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
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

  public long getSizes() {
    return sizes;
  }

  public void setSizes(long sizes) {
    this.sizes = sizes;
  }

  public long getFile_size() {
    return file_size;
  }

  public void setFile_size(long file_size) {
    this.file_size = file_size;
  }

  public String getRdate() {
    return rdate;
  }

  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
    
  public String getP_date() {
    return p_date;
  }

  public void setP_date(String p_date) {
    this.p_date = p_date;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  /**
   * @return the sizesLabel
   */
  public String getSizesLabel() {
    return sizesLabel;
  }

  /**
   * @param sizesLabel the sizesLabel to set
   */
  public void setSizesLabel(String sizesLabel) {
    this.sizesLabel = sizesLabel;
  }

  public MultipartFile getFile1MF() {
    return file1MF;
  }

  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }

  public MultipartFile getTitle_imgMF() {
    return title_imgMF;
  }

  public void setTitle_imgMF(MultipartFile title_imgMF) {
    this.title_imgMF = title_imgMF;
  }
  
}
