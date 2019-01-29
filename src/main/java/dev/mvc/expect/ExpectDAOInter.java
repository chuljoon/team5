package dev.mvc.expect;

import java.util.List;

public interface ExpectDAOInter {
  /**
   * 기대평 등록
   * @param expectVO
   * @return
   */
  public int create(ExpectVO expectVO);
  
  /**
   * 목록
   * @return
   */
  public List<ExpectVO> list(int contentsno);
  
  /**
   * 조회
   * @param expectno
   * @return
   */
  public ExpectVO read(int expectno);
  
  /**
   * 수정폼
   * @param expectno
   * @return
   */
  public ExpectVO update(int expectno);
  
  /**
   * 수정처리
   * @param expectVO
   * @return
   */
  public int update(ExpectVO expectVO);
  
  /**
   * 삭제
   * @param expectno
   * @return
   */
  public int delete(int expectno);

}
