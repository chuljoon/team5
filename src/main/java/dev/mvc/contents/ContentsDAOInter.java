package dev.mvc.contents;

import java.util.HashMap;
import java.util.List;

public interface ContentsDAOInter {
  
  /**
   * 컨텐츠 등록
   * @param contentsVO
   * @return
   */
  public int create(ContentsVO contentsVO);
  
  /**
   * 전체 목록
   * @return
   */
  public List<ContentsVO> list_all_category();
  
  /**
   * 카테고리별 목록
   * @param s_categoryno
   * @return
   */
  public List<ContentsVO> list_by_category(int s_categoryno);
  
  /**
   * 조회
   * @param contentsno
   * @return
   */
  public ContentsVO read(int contentsno);
  
  /**
   * 수정폼
   * @param contentsno
   * @return
   */
  public ContentsVO update(int contentsno);
  
  /**
   * 수정처리
   * @param contentsVO
   * @return
   */
  public int update(ContentsVO contentsVO);
  
  /**
   * 삭제
   * @param contentsno
   * @return
   */
  public int delete(int contentsno);
    
  /**
   * 검색 + 페이징
   * @param hashMap
   * @return
   */
  public List<ContentsVO> list_by_category_paging(HashMap<String, Object> hashMap);
   
  /**
   * category별 검색된 레코드 개수
   * @param hashMap
   * @return
   */
  public int search_count(HashMap hashMap);

}
