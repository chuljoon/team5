package dev.mvc.sub_category;

public class Categrp_CategoryVO {
  // main_categrp table
  private String name;
  private int m_categrpno;
  private int seqno;

  // sub_category table
  private int s_categoryno;
  private String title;
  private int category_seqno;
  private String visible;
  private String ids;
  private int cnt;
  private String rdate;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getM_categrpno() {
    return m_categrpno;
  }

  public void setM_categrpno(int m_categrpno) {
    this.m_categrpno = m_categrpno;
  }

  public int getSeqno() {
    return seqno;
  }

  public void setSeqno(int seqno) {
    this.seqno = seqno;
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

  public int getCategory_seqno() {
    return category_seqno;
  }

  public void setCategory_seqno(int category_seqno) {
    this.category_seqno = category_seqno;
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
