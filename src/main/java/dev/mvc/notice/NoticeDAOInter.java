package dev.mvc.notice;

import java.util.HashMap;
import java.util.List;

public interface NoticeDAOInter {
  /**
   * �������� ���
   * 
   * @param noticeVO
   * @return
   */
  public int create(NoticeVO noticeVO);

  /**
   * ���
   * 
   * @return
   */
  public List<NoticeVO> list();

  /**
   * ��ȸ
   * 
   * @param noticeno
   * @return
   */
  public NoticeVO read(int noticeno);

  /**
   * ����
   * 
   * @param noticeVO
   * @return
   */
  public NoticeVO update(int noticeno);

  public int update(NoticeVO noticeVO);

  /**
   * ����
   * 
   * @param noticeno
   * @return
   */
  public int delete(int noticeno);
  
  /**
   * ���ڵ� ����
   * @param hashMap
   * @return
   */
  public int cnt(HashMap hashMap);
  
  /**
   * ����¡
   * @param hashMap
   * @return
   */
  public List<NoticeVO> list_paging(HashMap hashMap);

}
