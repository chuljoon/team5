package dev.mvc.notice;

public class NoticeVO {
  /** �������� ��ȣ **/
  private int noticeno;
  /** �������� ���� **/
  private String title;
  /** �������� ���� **/
  private String content;
  /** ����� **/
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
