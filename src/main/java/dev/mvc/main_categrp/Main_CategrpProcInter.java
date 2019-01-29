package dev.mvc.main_categrp;

import java.util.List;

public interface Main_CategrpProcInter {
  /**
   * <Xmp>
   * 메인 카테고리 그룹 등록
   * <insert id="create" parameterType="Main_CategrpVO">
   * </Xmp>
   * @param m_categrpVO
   * @return 처리된 레코드 개수
   */
  public int create(Main_CategrpVO m_categrpVO);
  
  /**
   * 목록
   * <xmp>
   * <select id="list" resultType="Main_CategrpVO">
   * </xmp> 
   * @return
   */
  public List<Main_CategrpVO> list();
  
  /**
   * 조회
   * <xmp>
   * <select id="read" resultType="Main_CategrpVO" parameterType="int">
   * </xmp>  
   * @param m_categrpno
   * @return
   */
  public Main_CategrpVO read(int m_categrpno);
 
  /**
   * 수정 처리
   * <xmp>
   * <update id="update" parameterType="Main_CategrpVO"> 
   * </xmp>
   * @param Main_CategrpVO
   * @return 처리된 레코드 갯수
   */
  public int update(Main_CategrpVO m_categrpVO);
  
  /**
   * 삭제 처리
   * <xmp>
   *   <delete id="delete" parameterType="int">
   * </xmp> 
   * @param m_categrpno
   * @return 처리된 레코드 갯수
   */
  public int delete(int m_categrpno);
}
