package dev.mvc.sub_category;

import java.util.List;

public interface Sub_CategoryDAOInter {
  /**
   * ���
   * 
   * @param s_categoryVO
   * @return
   */
  public abstract int create(Sub_CategoryVO s_categoryVO);

  /**
   * ���
   * 
   * @return Join ���
   */
  public List<Categrp_CategoryVO> list();

  /**
   * ���
   * 
   * @param m_categrpno
   *          ���� ī�װ� ��ȣ
   * @return
   */
  public List<Categrp_CategoryVO> list_by_categrp(int m_categrpno);

  /**
   * �Ѱ��� ���ڵ� ��ȸ
   * 
   * @param s_categoryno
   * @return
   */
  public Categrp_CategoryVO read(int s_categoryno);
  
  /**
   * ���ڵ� ����
   * @param s_categoryVO
   * @return
   */
  public int update(Sub_CategoryVO s_categoryVO);
  
  /**
   * �Ѱ��� ���ڵ� ����
   * @param s_categoryno
   * @return
   */
  public int delete(int s_categoryno);
  
  /**
   * ���� ī�װ��� ���� ī��Ʈ ����
   * @param m_categrpno
   * @return
   */
  public int count_by_categrp(int m_categrpno);
  
  /**
   * ī�װ� �׷쿡 ���� ����
   * @param m_categrpno
   * @return
   */
  public int delete_by_categrp(int m_categrpno);
  
  /**
   * ��� ���� ����
   * <xmp>
   * <update id="update_seqno_up" parameterType="int">
   * </xmp>
   * @param s_categoryno
   * @return ó���� ���ڵ� ����
   */
  public int update_seqno_up(int s_categoryno);
  
  /**
   * ��� ���� ����
   * <xmp>
   * <update id="update_seqno_down" parameterType="int">
   * </xmp>
   * @param s_categoryno
   * @return ó���� ���ڵ� ����
   */
  public int update_seqno_down(int s_categoryno);

}
