package dev.mvc.contents;

import java.util.HashMap;
import java.util.List;

public interface ContentsDAOInter {
  
  /**
   * ������ ���
   * @param contentsVO
   * @return
   */
  public int create(ContentsVO contentsVO);
  
  /**
   * ��ü ���
   * @return
   */
  public List<ContentsVO> list_all_category();
  
  /**
   * ī�װ��� ���
   * @param s_categoryno
   * @return
   */
  public List<ContentsVO> list_by_category(int s_categoryno);
  
  /**
   * ��ȸ
   * @param contentsno
   * @return
   */
  public ContentsVO read(int contentsno);
  
  /**
   * ������
   * @param contentsno
   * @return
   */
  public ContentsVO update(int contentsno);
  
  /**
   * ����ó��
   * @param contentsVO
   * @return
   */
  public int update(ContentsVO contentsVO);
  
  /**
   * ����
   * @param contentsno
   * @return
   */
  public int delete(int contentsno);
    
  /**
   * �˻� + ����¡
   * @param hashMap
   * @return
   */
  public List<ContentsVO> list_by_category_paging(HashMap<String, Object> hashMap);
   
  /**
   * category�� �˻��� ���ڵ� ����
   * @param hashMap
   * @return
   */
  public int search_count(HashMap hashMap);

}
