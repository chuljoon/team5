package dev.mvc.notice;

import java.util.HashMap;
import java.util.List;

public interface NoticeProcInter {
  /**
   * 공지사항 등록
   * 
   * @param noticeVO
   * @return
   */
  public int create(NoticeVO noticeVO);

  /**
   * 목록
   * 
   * @return
   */
  public List<NoticeVO> list();

  /**
   * 조회
   * 
   * @param noticeno
   * @return
   */
  public NoticeVO read(int noticeno);

  /**
   * 수정
   * 
   * @param noticeVO
   * @return
   */
  public NoticeVO update(int noticeno);

  public int update(NoticeVO noticeVO);

  /**
   * 삭제
   * 
   * @param noticeno
   * @return
   */
  public int delete(int noticeno);
  
  /**
   * 레코드 개수
   * @param hashMap
   * @return
   */
  public int cnt(HashMap hashMap);

  /**
   * 페이징
   * 
   * @param hashMap
   * @return
   */
  public List<NoticeVO> list_paging(HashMap hashMap);
  
  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param cnt 검색(전체) 레코드수 
   * @param nowPage     현재 페이지
   * @return 페이징 생성 문자열
   */ 
  public String paging(int cnt, int nowPage);

}
