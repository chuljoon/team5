package dev.mvc.notice;

import java.util.HashMap;
import java.util.List;

public interface NoticeDAOInter {
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
   * @param hashMap
   * @return
   */
  public List<NoticeVO> list_paging(HashMap hashMap);

}
