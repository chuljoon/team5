package dev.mvc.gallery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface GalleryDAOInter {
  
  /**
   * ������ ���
   * @param galleryVO
   * @return
   */
  public int create(GalleryVO galleryVO);
  
  /**
   * ������ ��ü ���
   * @return
   */
  public List<GalleryVO> list();
  
  /**
   * ��ü �ۼ�
   * @return
   */
  public int total_count();
  
  /**
   * ������ ��ȸ
   * @param galleryno
   * @return
   */
  public GalleryVO read(int galleryno);

  /**
   * ������ ����
   * @param galleryVO
   * @return
   */
  public int update(GalleryVO galleryVO);
  
  /**
   * ������ ����
   * @param galleryno
   * @return
   */
  public int delete(int galleryno);
  
  /**
   * �˻� ���
   * @param hashMap
   * @return
   */
  public List<GalleryVO> list_search(HashMap hashMap);
  
  
  /**
   * �˻��� ���ڵ� ����
   * @return
   */
  public int search_count(HashMap hashMap);
}
