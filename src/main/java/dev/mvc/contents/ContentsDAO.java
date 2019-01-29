package dev.mvc.contents;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.contents.ContentsDAO")
public class ContentsDAO implements ContentsDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;

  public ContentsDAO() {
    System.out.println("--> ContentsDAO created.");
  }

  @Override
  public int create(ContentsVO contentsVO) {
    return sqlSessionTemplate.insert("contents.create", contentsVO);
  }

  @Override
  public List<ContentsVO> list_all_category() {
    return sqlSessionTemplate.selectList("contents.list_all_category");
  }

  @Override
  public List<ContentsVO> list_by_category(int s_categoryno) {
    return sqlSessionTemplate.selectList("contents.list_by_category", s_categoryno);
  }

  @Override
  public ContentsVO read(int contentsno) {
    return sqlSessionTemplate.selectOne("contents.read", contentsno);
  }

  @Override
  public ContentsVO update(int contentsno) {
    return sqlSessionTemplate.selectOne("contents.read", contentsno);
  }

  @Override
  public int update(ContentsVO contentsVO) {
    return sqlSessionTemplate.update("contents.update", contentsVO);
  }

  @Override
  public int delete(int contentsno) {
    return sqlSessionTemplate.delete("contents.delete", contentsno);
  }

  @Override
  public List<ContentsVO> list_by_category_paging(HashMap hashMap) {
    return sqlSessionTemplate.selectList("contents.list_by_category_paging", hashMap);
  }

  @Override
  public int search_count(HashMap hashMap) {
    return sqlSessionTemplate.selectOne("contents.search_count", hashMap);
  }

}
