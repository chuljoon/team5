package dev.mvc.sub_category;

public class Sub_CategoryVO {
  /** ���� ī�װ� ��ȣ **/
  private int s_categoryno;
  /** ���� ī�װ� �׷� ��ȣ **/
  private int m_categrpno;
  /** �Խ��� �̸� **/
  private String title;
  /** ��� ���� **/
  private int seqno;
  /** ��� ��� **/
  private String visible;
  /** ���� ���� **/
  private String ids;
  /** ��ϵ� �� �� **/
  private int cnt;
  /** ����� **/
  private String rdate;

  public Sub_CategoryVO() {

  }

  public int getS_categoryno() {
    return s_categoryno;
  }

  public void setS_categoryno(int s_categoryno) {
    this.s_categoryno = s_categoryno;
  }

  public int getM_categrpno() {
    return m_categrpno;
  }

  public void setM_categrpno(int m_categrpno) {
    this.m_categrpno = m_categrpno;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getSeqno() {
    return seqno;
  }

  public void setSeqno(int seqno) {
    this.seqno = seqno;
  }

  public String getVisible() {
    return visible;
  }

  public void setVisible(String visible) {
    this.visible = visible;
  }

  public String getIds() {
    return ids;
  }

  public void setIds(String ids) {
    this.ids = ids;
  }

  public int getCnt() {
    return cnt;
  }

  public void setCnt(int cnt) {
    this.cnt = cnt;
  }

  public String getRdate() {
    return rdate;
  }

  public void setRdate(String rdate) {
    this.rdate = rdate;
  }

}
