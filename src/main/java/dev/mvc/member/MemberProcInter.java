package dev.mvc.member;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface MemberProcInter {
  /**
   * ȸ�� ���
   * @param memberVO
   * @return ��ϵ� ȸ�� �� 1 or 0
   */
  public int create(MemberVO memberVO);
  
  /**
   * �ߺ� �̸��� �˻�
   * @param id
   * @return �ߺ� �̸��� ����
   */
  public int checkEmail(String m_email);
  
  /**
   * �ߺ� �г��� �˻�
   * @param id
   * @return �ߺ� �г��� ����
   */
  public int checkNickname(String m_nickname);
  
  /**
   * �α��ε� ȸ�� �������� �˻�
   * @param request
   * @return true: ������
   */
  public boolean isMember(HttpSession session); 
  
  /**
   * ȸ�� ��ü ���
   * @return
   */
  public List<MemberVO> list();

  /**
   * ��ȸ
   * @param memberno
   * @return
   */
  public MemberVO read(int memberno);

  /**
   * ��ȸ
   * @param m_email
   * @return
   */
  public MemberVO readByEmail(String m_email);
  
  /**
   * ����
   * @param memberVO
   * @return
   */
  public int update(MemberVO memberVO);
  
  /**
   * �н����� ����
   * 
   * @param mno ȸ�� ��ȣ
   * @param passwd ������ �н����� ��
   * @return
   */
  public int passwd_update(int memberno, String m_passwd);
  
  /**
   * ���ڵ� 1�� ����
   * @param mno ������ ȸ�� ��ȣ
   * @return ������ ���ڵ� ����
   */
  public int delete(int memberno);
  
  /**
   * �α��� 
   * @param map
   * @return
   */
  public int login(String m_email, String m_passwd);
  
}
