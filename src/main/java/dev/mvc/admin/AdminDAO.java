package dev.mvc.admin;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.admin.AdminDAO") // DBMS 저장소 접근
public  class AdminDAO implements AdminDAOInter{
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public AdminDAO() {
    System.out.println("--> AdminDAO created.");
  }

  @Override
  public int create(AdminVO adminVO) {
    int cnt = sqlSessionTemplate.insert("admin.create", adminVO);
    return cnt;
  }

  @Override
  public int checkEmail(String a_email) {
    int cnt = sqlSessionTemplate.selectOne("admin.checkEmail", a_email);
    return cnt;
  }

  @Override
  public int checkNickname(String a_nickname) {
    int cnt = sqlSessionTemplate.selectOne("admin.checkNickname", a_nickname);
    return cnt;
  }
  
  @Override
  public List<AdminVO> list() {
    List<AdminVO> list = sqlSessionTemplate.selectList("admin.list");
    return list;
  }

  @Override
  public AdminVO read(int adminno) {
    AdminVO adminVO = sqlSessionTemplate.selectOne("admin.read", adminno);
    return adminVO;
  }

  @Override
  public AdminVO readByEmail(String a_email) {
    AdminVO adminVO = sqlSessionTemplate.selectOne("admin.readByEmail", a_email);
    return adminVO;
  }

  @Override
  public int update(AdminVO adminVO) {
    int count = sqlSessionTemplate.update("admin.update", adminVO);
    return count;
  }

  @Override
  public int passwd_update(Map<String, Object> map) {
    int count = sqlSessionTemplate.selectOne("admin.login", map);
    return count;
  }
  
  @Override
  public int delete(int adminno) {
    int count = sqlSessionTemplate.delete("admin.delete", adminno);
    return count;
  }

  @Override
  public int login(Map map) {
    int count = sqlSessionTemplate.selectOne("admin.login", map);
    return count;
  }


}
