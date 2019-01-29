package dev.mvc.question;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.question.QuestionDAO")
public class QuestionDAO implements QuestionDAOInter{
  @Autowired
  private SqlSessionTemplate mybatis;
  
  public QuestionDAO() {
    System.out.println("--> QuestionDAO created..");  
  }

  @Override
  public int create(QuestionVO questionVO) {
    return mybatis.insert("question.create", questionVO);
  }

  @Override
  public List<QuestionVO> list() {
    return mybatis.selectList("question.list");
  }

  @Override
  public List<QuestionVO> list_by_member(int memberno) {
    return mybatis.selectList("question.list_by_member", memberno); 
  }

  @Override
  public int total_count() {
    return mybatis.selectOne("question.total_count");
  }

  @Override
  public QuestionVO read(int questionno) {
    QuestionVO questionVO = mybatis.selectOne("question.read", questionno);
    return questionVO;
  }

  @Override
  public QuestionVO upeate(int questionno) {
    QuestionVO questionVO = mybatis.selectOne("question.read", questionno);
    return questionVO;
  }

  @Override
  public int update(QuestionVO questionVO) {
    return mybatis.update("question.update", questionVO);
  }

  @Override
  public int delete(int questionno) {
    return mybatis.delete("question.delete", questionno);
  }

  @Override
  public List<QuestionVO> list_by_member_search(HashMap hashMap) {
    return mybatis.selectList("question.list_by_member_search", hashMap);
  }

  @Override
  public int search_count(HashMap hashMap) {
    return mybatis.selectOne("question.search_count", hashMap);
  }

  @Override
  public List<QuestionVO> list_by_member_search_paging(HashMap<String, Object> hashMap) {
    return mybatis.selectList("question.list_by_member_search_paging", hashMap);
  }

  @Override
  public int update_question_Ansnum(QuestionVO questionVO) {
    return mybatis.insert("question.update_question_Ansnum", questionVO);
  }

  @Override
  public int reply(QuestionVO questionVO) {
    return mybatis.insert("question.reply", questionVO);
  }

  
  
}

