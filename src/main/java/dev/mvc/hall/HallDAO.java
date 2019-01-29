package dev.mvc.hall;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.hall.HallDAO")
public class HallDAO implements HallDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public HallDAO() {
    System.out.println("--> HallDAO created.");
  }

  @Override
  public int create(HallVO hallVO) {
    return sqlSessionTemplate.insert("hall.create", hallVO);
  }

  @Override
  public List<HallVO> list() {
    return sqlSessionTemplate.selectList("hall.list");
  }

  @Override
  public HallVO read(int hallno) {
    return sqlSessionTemplate.selectOne("hall.read", hallno);
  }

  @Override
  public HallVO update(int hallno) {
    return sqlSessionTemplate.selectOne("hall.read", hallno);
  }

  @Override
  public int update(HallVO hallVO) {
    return sqlSessionTemplate.update("hall.update", hallVO);
  }

  @Override
  public int delete(int hallno) {
    return sqlSessionTemplate.delete("hall.delete", hallno);
  }

}
