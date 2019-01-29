package dev.mvc.member;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.member.MemberDAO") // DBMS 저장소 접근
public  class MemberDAO implements MemberDAOInter{
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public MemberDAO() {
    System.out.println("--> MemberDAO created.");
  }

  @Override
  public int create(MemberVO memberVO) {
    int cnt = sqlSessionTemplate.insert("member.create", memberVO);
    return cnt;
  }

  @Override
  public int checkEmail(String m_email) {
    int cnt = sqlSessionTemplate.selectOne("member.checkEmail", m_email);
    return cnt;
  }

  @Override
  public int checkNickname(String m_nickname) {
    int cnt = sqlSessionTemplate.selectOne("member.checkNickname", m_nickname);
    return cnt;
  }
  
  @Override
  public List<MemberVO> list() {
    List<MemberVO> list = sqlSessionTemplate.selectList("member.list");
    return list;
  }

  @Override
  public MemberVO read(int memberno) {
    MemberVO memberVO = sqlSessionTemplate.selectOne("member.read", memberno);
    return memberVO;
  }

  @Override
  public MemberVO readByEmail(String m_email) {
    MemberVO memberVO = sqlSessionTemplate.selectOne("member.readByEmail", m_email);
    return memberVO;
  }

  @Override
  public int update(MemberVO memberVO) {
    int count = sqlSessionTemplate.update("member.update", memberVO);
    return count;
  }

  @Override
  public int passwd_update(Map<String, Object> map) {
    int count = sqlSessionTemplate.selectOne("member.login", map);
    return count;
  }
  
  @Override
  public int delete(int memberno) {
    int count = sqlSessionTemplate.delete("member.delete", memberno);
    return count;
  }

  @Override
  public int login(Map map) {
    int count = sqlSessionTemplate.selectOne("member.login", map);
    return count;
  }


}
