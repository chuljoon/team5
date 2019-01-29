package dev.mvc.gallery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface GalleryDAOInter {
  
  /**
   * 갤러리 등록
   * @param galleryVO
   * @return
   */
  public int create(GalleryVO galleryVO);
  
  /**
   * 갤러리 전체 목록
   * @return
   */
  public List<GalleryVO> list();
  
  /**
   * 전체 글수
   * @return
   */
  public int total_count();
  
  /**
   * 갤러리 조회
   * @param galleryno
   * @return
   */
  public GalleryVO read(int galleryno);

  /**
   * 갤러리 수정
   * @param galleryVO
   * @return
   */
  public int update(GalleryVO galleryVO);
  
  /**
   * 갤러리 삭제
   * @param galleryno
   * @return
   */
  public int delete(int galleryno);
  
  /**
   * 검색 목록
   * @param hashMap
   * @return
   */
  public List<GalleryVO> list_search(HashMap hashMap);
  
  
  /**
   * 검색된 레코드 갯수
   * @return
   */
  public int search_count(HashMap hashMap);
}
