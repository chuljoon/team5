package dev.mvc.gallery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.gallery.GalleryDAO")
public class GalleryDAO implements GalleryDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public GalleryDAO() {
    System.out.println("--> GalleryDAO created.");
  }

  @Override
  public int create(GalleryVO galleryVO) {
    return sqlSessionTemplate.insert("gallery.create", galleryVO);
  }

  @Override
  public List<GalleryVO> list() {
    return sqlSessionTemplate.selectList("gallery.list");
  }

  @Override
  public int total_count() {
    return sqlSessionTemplate.selectOne("gallery.total_count");
  }

  @Override
  public GalleryVO read(int galleryno) {
    return sqlSessionTemplate.selectOne("gallery.read", galleryno);
  }

  @Override
  public int update(GalleryVO galleryVO) {
    return sqlSessionTemplate.update("gallery.update", galleryVO);
  }

  @Override
  public int delete(int galleryno) {
    return sqlSessionTemplate.update("gallery.delete", galleryno);
  }

  @Override
  public List<GalleryVO> list_search(HashMap hashMap) {
    return sqlSessionTemplate.selectList("gallery.list_search", hashMap);
  }

  @Override
  public int search_count(HashMap hashMap) {
    return sqlSessionTemplate.selectOne("gallery.search_count", hashMap);
  }


  
  
}
