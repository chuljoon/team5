package dev.mvc.sub_category;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.sub_category.Sub_CategoryDAO") // DBMS 저장소 접근
public class Sub_CategoryDAO implements Sub_CategoryDAOInter {
  @Autowired // 빈을 스프링이 만들어서 자동 할당, 개발자는 new 사용 안함.
  private SqlSessionTemplate sqlSessionTemplate = null;

  public Sub_CategoryDAO() {
    System.out.println("--> Sub_CategoryDAO created.");
  }

  @Override
  public int create(Sub_CategoryVO s_categoryVO) {
    return sqlSessionTemplate.insert("sub_category.create", s_categoryVO);
  }

  @Override
  public List<Categrp_CategoryVO> list() {
    return sqlSessionTemplate.selectList("sub_category.list");
  }

  @Override
  public List<Categrp_CategoryVO> list_by_categrp(int m_categrpno) {
    return sqlSessionTemplate.selectList("sub_category.list_by_categrp", m_categrpno);
  }

  @Override
  public Categrp_CategoryVO read(int s_categoryno) {
    return sqlSessionTemplate.selectOne("sub_category.read", s_categoryno);
  }

  @Override
  public int update(Sub_CategoryVO s_categoryVO) {
    return sqlSessionTemplate.update("sub_category.update", s_categoryVO);
  }

  @Override
  public int delete(int s_categoryno) {
    return sqlSessionTemplate.delete("sub_category.delete", s_categoryno);
  }

  @Override
  public int count_by_categrp(int m_categrpno) {
    return sqlSessionTemplate.selectOne("sub_category.count_by_categrp", m_categrpno);
  }

  @Override
  public int delete_by_categrp(int m_categrpno) {
    return sqlSessionTemplate.delete("sub_category.delete_by_categrp", m_categrpno);
  }

  @Override
  public int update_seqno_up(int s_categoryno) {
    return sqlSessionTemplate.update("sub_category.update_seqno_up", s_categoryno);
  }

  @Override
  public int update_seqno_down(int s_categoryno) {
    return sqlSessionTemplate.update("sub_category.update_seqno_down", s_categoryno);
  }

}
