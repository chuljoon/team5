package dev.mvc.expect;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.expect.ExpectDAO")
public class ExpectDAO implements ExpectDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public ExpectDAO() {
    System.out.println("--> ExpectDAO created.");
  }

  @Override
  public int create(ExpectVO expectVO) {
    return sqlSessionTemplate.insert("expect.create", expectVO);
  }

  @Override
  public List<ExpectVO> list(int contentsno) {
    return sqlSessionTemplate.selectList("expect.list", contentsno);
  }

  @Override
  public ExpectVO read(int expectno) {
    return sqlSessionTemplate.selectOne("expect.read", expectno);
  }
  
  @Override
  public ExpectVO update(int expectno) {
    return sqlSessionTemplate.selectOne("expect.read", expectno);
  }

  @Override
  public int update(ExpectVO expectVO) {
    return sqlSessionTemplate.update("expect.update", expectVO);
  }

  @Override
  public int delete(int expectno) {
    return sqlSessionTemplate.delete("expect.delete", expectno);
  }

}
