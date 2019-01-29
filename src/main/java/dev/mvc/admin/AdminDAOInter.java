package dev.mvc.admin;

import java.util.List;
import java.util.Map;

public interface AdminDAOInter {
  
  /**
   * ������ ���
   * @param adminVO
   * @return ��ϵ� ȸ�� �� 1 or 0
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
   * @param map
   * @return
   */
  public int passwd_update(Map<String, Object> map);
 
  /**
   * ���ڵ� 1�� ����
   * @param mno ������ ȸ�� ��ȣ
   * @return ������ ���ڵ� ����
   */
  public int delete(int adminno);
  
  /**
   * �α��� 
   * @param map
   * @return
   */
  public int login(Map map);
  
  
  

}