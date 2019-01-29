package dev.mvc.main_categrp;

public class Main_CategrpVO {
  /** 메인 카테고리 번호 */
  private int m_categrpno;
  /** 메인 카테고리 이름 */
  private String name;
  /** 출력 순서 */
  private int seqno;
  /** 출력 모드 */
  private String visible;
  /** 등록일 */
  private String rdate;
  
  public int getM_categrpno() {
    return m_categrpno;
  }
  public void setM_categrpno(int m_categrpno) {
    this.m_categrpno = m_categrpno;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
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
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }

  

}
