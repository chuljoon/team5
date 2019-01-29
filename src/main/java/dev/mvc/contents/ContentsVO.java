package dev.mvc.contents;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ContentsVO {
  /** �� ��ȣ **/
  private int contentsno;
  /** ī�װ� ��ȣ **/
  private int s_categoryno;
  /** ������ **/
  private String title;
  /** ���� ������ **/
  private String title_img;
  /** �Խñ� ��й�ȣ **/
  private String passwd;
  /** �������� ���� **/
  private String contents;
  /** ����� **/
  private String thumbs;
  /** ��ǰ ���� ���� **/
  private String files;
  /** ������ ������ **/
  private long sizes;
  /** ��ǰ���� ������ **/
  private long file_size;
  /** ���� �Ͻ� **/
  private String p_date;
  /** �˻��� **/
  private String word;
  /** ����� **/
  private String rdate;
  
  /** 
  Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü,
  DBMS �� ���� �÷��� �������� �ʰ� ���� �ӽ� ���� ����.
  �������� ���� ���ε�
  */  
  private MultipartFile file1MF;
  
  private MultipartFile title_imgMF;

  /** size1�� �ĸ� ���� ��¿� ����, ���� �÷��� �������� ����. */
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
