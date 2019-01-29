package dev.mvc.notice;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.notice.NoticeDAO")
public class NoticeDAO implements NoticeDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public NoticeDAO() {
    System.out.println("--> NoticeDAO created.");
  }

  @Override
  public int create(NoticeVO noticeVO) {
    return sqlSessionTemplate.insert("notice.create", noticeVO);
  }

  @Override
  public List<NoticeVO> list() {
    return sqlSessionTemplate.selectList("notice.list");
  }

  @Override
  public NoticeVO read(int noticeno) {
    return sqlSessionTemplate.selectOne("notice.read", noticeno);
  }

  @Override
  public NoticeVO update(int noticeno) {
    return sqlSessionTemplate.selectOne("notice.read", noticeno);
  }
  
  @Override
  public int update(NoticeVO noticeVO) {
    return sqlSessionTemplate.update("notice.update", noticeVO);
  }

  @Override
  public int delete(int noticeno) {
    return sqlSessionTemplate.delete("notice.delete", noticeno);
  }

  @Override
  public int cnt(HashMap hashMap) {
    return sqlSessionTemplate.selectOne("notice.cnt", hashMap);
  }
  
  @Override
  public List<NoticeVO> list_paging(HashMap hashMap) {
    return sqlSessionTemplate.selectList("notice.list_paging", hashMap);
  }

}
