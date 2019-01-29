package dev.mvc.contents;

import java.util.HashMap;
import java.util.List;

public interface ContentsProcInter {
  
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
   * 페이징
   * @param hashMap
   * @return
   */
  public List<ContentsVO> list_by_category_paging(HashMap hashMap);
  
  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param categoryno 카테고리번호 
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage     현재 페이지
   * @param word 검색어
   * @return 페이징 생성 문자열
   */ 
  public String paging(int s_categoryno, int search_count, int nowPage, String word);
    
  /**
   * category별 검색된 레코드 개수
   * @param hashMap
   * @return
   */
  public int search_count(HashMap hashMap);

}
