package dev.mvc.notice;

public class NoticeVO {
  /** 공지사항 번호 **/
  private int noticeno;
  /** 공지사항 제목 **/
  private String title;
  /** 공지사항 내용 **/
  private String content;
  /** 등록일 **/
  private String rdate;
  
  public NoticeVO() {
    
  }

  public int getNoticeno() {
    return noticeno;
  }

  public void setNoticeno(int noticeno) {
    this.noticeno = noticeno;
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

  public String getRdate() {
    return rdate;
  }

  public void setRdate(String rdate) {
    this.rdate = rdate;
  }

}
