package dev.mvc.main_categrp;

import java.util.List;

public interface Main_CategrpProcInter {
  /**
   * <Xmp>
   * ���� ī�װ� �׷� ���
   * <insert id="create" parameterType="Main_CategrpVO">
   * </Xmp>
   * @param m_categrpVO
   * @return ó���� ���ڵ� ����
   */
  public int create(Main_CategrpVO m_categrpVO);
  
  /**
   * ���
   * <xmp>
   * <select id="list" resultType="Main_CategrpVO">
   * </xmp> 
   * @return
   */
  public List<Main_CategrpVO> list();
  
  /**
   * ��ȸ
   * <xmp>
   * <select id="read" resultType="Main_CategrpVO" parameterType="int">
   * </xmp>  
   * @param m_categrpno
   * @return
   */
  public Main_CategrpVO read(int m_categrpno);
 
  /**
   * ���� ó��
   * <xmp>
   * <update id="update" parameterType="Main_CategrpVO"> 
   * </xmp>
   * @param Main_CategrpVO
   * @return ó���� ���ڵ� ����
   */
  public int update(Main_CategrpVO m_categrpVO);
  
  /**
   * ���� ó��
   * <xmp>
   *   <delete id="delete" parameterType="int">
   * </xmp> 
   * @param m_categrpno
   * @return ó���� ���ڵ� ����
   */
  public int delete(int m_categrpno);
}
