package dev.mvc.member;

import org.springframework.web.multipart.MultipartFile;

public class MemberVO {
  /** ȸ�� ��ȣ */
  private int memberno;
  /** ȸ�� �̸� */
  private String m_name;
  /** ȸ�� �г��� */
  private String m_nickname;
  /** ȸ�� �̸��� */
  private String m_email;
  /** ȸ�� ��й�ȣ */
  private String m_passwd;
  /** ȸ�� ���� */
  private String m_age;
  /** ȸ�� ���� */
  private String m_act;
  /** ȸ�� ���� ����� */
  private String m_thumbs;
  /** ȸ�� ���� ���� */
  private String m_files;
  /** ȸ�� ���� ������ */
  private String m_sizes;
  /** ȸ�� ����� */
  private String rdate;
  /** 
  Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü,
  DBMS �� ���� �÷��� �������� �ʰ� ���� �ӽ� ���� ����.
  �ϳ��� ���� ���ε�
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
    System.out.println("--> MemberVO setName(\""+m_name+"\") ȣ���.");
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
