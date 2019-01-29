package dev.mvc.hall;

import java.util.List;

public interface HallProcInter {
  
  /**
   * 공연장 등록
   * @param hallVO
   * @return
   */
  public int create(HallVO hallVO);
  
  /**
   * 목록
   * @return
   */
  public List<HallVO> list();
  
  /**
   * 조회
   * @param hallno
   * @return
   */
  public HallVO read(int hallno);
  
  /**
   * 수정폼
   * @param hallno
   * @return
   */
  public HallVO update(int hallno);
  
  /**
   * 수정처리
   * @param hallVO
   * @return
   */
  public int update(HallVO hallVO);
  
  /**
   * 삭제
   * @param hallno
   * @return
   */
  public int delete(int hallno);

}
