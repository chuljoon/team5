package dev.mvc.admin;

public class AdminVO {
  /** 관리자 번호 */
  private int adminno;
  /** 관리자 이름 */
  private String a_name;
  /** 관리자 닉네임 */
  private String a_nickname;
  /** 관리자 이메일 */
  private String a_email;
  /** 관리자 비밀번호 */
  private String a_passwd;
  /** 관리자 나이 */
  private String a_age;
  /** 관리자 권한 */
  private String a_act;
  /** 관리자 등록일 */
  private String rdate;
  public int getAdminno() {
    return adminno;
  }
  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }
  public String getA_name() {
    return a_name;
  }
  public void setA_name(String a_name) {
    this.a_name = a_name;
  }
  public String getA_nickname() {
    return a_nickname;
  }
  public void setA_nickname(String a_nickname) {
    this.a_nickname = a_nickname;
  }
  public String getA_email() {
    return a_email;
  }
  public void setA_email(String a_email) {
    this.a_email = a_email;
  }
  public String getA_passwd() {
    return a_passwd;
  }
  public void setA_passwd(String a_passwd) {
    this.a_passwd = a_passwd;
  }
  public String getA_age() {
    return a_age;
  }
  public void setA_age(String a_age) {
    this.a_age = a_age;
  }
  public String getA_act() {
    return a_act;
  }
  public void setA_act(String a_act) {
    this.a_act = a_act;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  
  
}
