package dev.mvc.sub_category;

public class Sub_CategoryVO {
  /** 서브 카테고리 번호 **/
  private int s_categoryno;
  /** 메인 카테고리 그룹 번호 **/
  private int m_categrpno;
  /** 게시판 이름 **/
  private String title;
  /** 출력 순서 **/
  private int seqno;
  /** 출력 모드 **/
  private String visible;
  /** 접근 계정 **/
  private String ids;
  /** 등록된 글 수 **/
  private int cnt;
  /** 등록일 **/
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
