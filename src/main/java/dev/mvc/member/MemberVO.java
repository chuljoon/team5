package dev.mvc.member;

import org.springframework.web.multipart.MultipartFile;

public class MemberVO {
  /** 회원 번호 */
  private int memberno;
  /** 회원 이름 */
  private String m_name;
  /** 회원 닉네임 */
  private String m_nickname;
  /** 회원 이메일 */
  private String m_email;
  /** 회원 비밀번호 */
  private String m_passwd;
  /** 회원 나이 */
  private String m_age;
  /** 회원 권한 */
  private String m_act;
  /** 회원 사진 썸네일 */
  private String m_thumbs;
  /** 회원 사진 파일 */
  private String m_files;
  /** 회원 사진 사이즈 */
  private String m_sizes;
  /** 회원 등록일 */
  private String rdate;
  /** 
  Spring Framework에서 자동 주입되는 업로드 파일 객체,
  DBMS 상에 실제 컬럼은 존재하지 않고 파일 임시 저장 목적.
  하나의 파일 업로드
*/  
 private MultipartFile filesMF;
  
  public MultipartFile getFilesMF() {
    return filesMF;
  }
  public void setFilesMF(MultipartFile filesMF) {
    this.filesMF = filesMF;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public String getM_name() {
    return m_name;
  }
  public void setM_name(String m_name) {
    System.out.println("--> MemberVO setName(\""+m_name+"\") 호출됨.");
    this.m_name = m_name;
  }
  public String getM_nickname() {
    return m_nickname;
  }
  public void setM_nickname(String m_nickname) {
    this.m_nickname = m_nickname;
  }
  public String getM_email() {
    return m_email;
  }
  public void setM_email(String m_email) {
    this.m_email = m_email;
  }
  public String getM_passwd() {
    return m_passwd;
  }
  public void setM_passwd(String m_passwd) {
    this.m_passwd = m_passwd;
  }
  public String getM_age() {
    return m_age;
  }
  public void setM_age(String m_age) {
    this.m_age = m_age;
  }
  public String getM_act() {
    return m_act;
  }
  public void setM_act(String m_act) {
    this.m_act = m_act;
  }
  public String getM_thumbs() {
    return m_thumbs;
  }
  public void setM_thumbs(String m_thumbs) {
    this.m_thumbs = m_thumbs;
  }
  public String getM_files() {
    return m_files;
  }
  public void setM_files(String m_files) {
    this.m_files = m_files;
  }
  public String getM_sizes() {
    return m_sizes;
  }
  public void setM_sizes(String m_sizes) {
    this.m_sizes = m_sizes;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
}
