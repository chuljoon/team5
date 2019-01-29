package dev.mvc.main_categrp;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.main_categrp.Main_CategrpDAO")
public class Main_CategrpDAO implements Main_CategrpDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public Main_CategrpDAO() {
    System.out.println("--> Main_CategrpDAO created.");
  }

  @Override
  public int create(Main_CategrpVO m_categrpVO) {
    return sqlSessionTemplate.insert("main_categrp.create", m_categrpVO);
  }

  @Override
  public List<Main_CategrpVO> list() {
    return sqlSessionTemplate.selectList("main_categrp.list");
  }

  @Override
  public Main_CategrpVO read(int m_categrpno) {
    return sqlSessionTemplate.selectOne("main_categrp.read", m_categrpno);
  }

  @Override
  public int update(Main_CategrpVO m_categrpVO) {
    return sqlSessionTemplate.update("main_categrp.update", m_categrpVO);
  }

  @Override
  public int delete(int m_categrpno) {
    return sqlSessionTemplate.delete("main_categrp.delete", m_categrpno);
  }

}
