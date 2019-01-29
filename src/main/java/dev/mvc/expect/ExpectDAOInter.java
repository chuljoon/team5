package dev.mvc.expect;

import java.util.List;

public interface ExpectDAOInter {
  /**
   * ����� ���
   * @param expectVO
   * @return
   */
  public int create(ExpectVO expectVO);
  
  /**
   * ���
   * @return
   */
  public List<ExpectVO> list(int contentsno);
  
  /**
   * ��ȸ
   * @param expectno
   * @return
   */
  public ExpectVO read(int expectno);
  
  /**
   * ������
   * @param expectno
   * @return
   */
  public ExpectVO update(int expectno);
  
  /**
   * ����ó��
   * @param expectVO
   * @return
   */
  public int update(ExpectVO expectVO);
  
  /**
   * ����
   * @param expectno
   * @return
   */
  public int delete(int expectno);

}
