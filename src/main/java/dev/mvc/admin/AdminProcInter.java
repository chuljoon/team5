package dev.mvc.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface AdminProcInter {
  /**
   * ������ ���
   * @param adminVO
   * @return ��ϵ� ������ �� 1 or 0
   */
  public int create(AdminVO adminVO);
  
  /**
   * �ߺ� �̸��� �˻�
   * @param a_email
   * @return �ߺ� �̸��� ����
   */
  public int checkEmail(String a_email);
  
  /**
   * �ߺ� �г��� �˻�
   * @param a_nickname
   * @return �ߺ� �г��� ����
   */
  public int checkNickname(String a_nickname);
  
  /**
   * �α��ε� ������ �������� �˻�
   * @param request
   * @return true: ������
   */
  public boolean isAdmin(HttpSession session); 
  
  /**
   * ������ ��ü ���
   * @return
   */
  public List<AdminVO> list();

  /**
   * ��ȸ
   * @param adminno
   * @return
   */
  public AdminVO read(int adminno);

  /**
   * ��ȸ
   * @param a_email
   * @return
   */
  public AdminVO readByEmail(String a_email);
  
  /**
   * ����
   * @param adminVO
   * @return
   */
  public int update(AdminVO adminVO);
  
  /**
   * �н����� ����
   * 
   * @param adminno ������ ��ȣ
   * @param a_passwd ������ �н����� ��
   * @return
   */
  public int passwd_update(int adminno, String a_passwd);
  
  /**
   * ���ڵ� 1�� ����
   * @param adminno ������ ������ ��ȣ
   * @return ������ ���ڵ� ����
   */
  public int delete(int adminno);
  
  /**
   * �α��� 
   * @param map
   * @return
   */
  public int login(String a_email, String a_passwd);
  
}
