package dev.mvc.sub_category;

import java.util.List;

public interface Sub_CategoryDAOInter {
  /**
   * 등록
   * 
   * @param s_categoryVO
   * @return
   */
  public abstract int create(Sub_CategoryVO s_categoryVO);

  /**
   * 목록
   * 
   * @return Join 목록
   */
  public List<Categrp_CategoryVO> list();

  /**
   * 목록
   * 
   * @param m_categrpno
   *          메인 카테고리 번호
   * @return
   */
  public List<Categrp_CategoryVO> list_by_categrp(int m_categrpno);

  /**
   * 한건의 레코드 조회
   * 
   * @param s_categoryno
   * @return
   */
  public Categrp_CategoryVO read(int s_categoryno);
  
  /**
   * 레코드 수정
   * @param s_categoryVO
   * @return
   */
  public int update(Sub_CategoryVO s_categoryVO);
  
  /**
   * 한건의 레코드 삭제
   * @param s_categoryno
   * @return
   */
  public int delete(int s_categoryno);
  
  /**
   * 메인 카테고리에 따른 카운트 산출
   * @param m_categrpno
   * @return
   */
  public int count_by_categrp(int m_categrpno);
  
  /**
   * 카테고리 그룹에 따른 삭제
   * @param m_categrpno
   * @return
   */
  public int delete_by_categrp(int m_categrpno);
  
  /**
   * 출력 순서 상향
   * <xmp>
   * <update id="update_seqno_up" parameterType="int">
   * </xmp>
   * @param s_categoryno
   * @return 처리된 레코드 갯수
   */
  public int update_seqno_up(int s_categoryno);
  
  /**
   * 출력 순서 하향
   * <xmp>
   * <update id="update_seqno_down" parameterType="int">
   * </xmp>
   * @param s_categoryno
   * @return 처리된 레코드 갯수
   */
  public int update_seqno_down(int s_categoryno);

}
