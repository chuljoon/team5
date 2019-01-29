package dev.mvc.sub_category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.sub_category.Sub_CategoryProc")
public class Sub_CategoryProc implements Sub_CategoryProcInter {
  @Autowired
  @Qualifier("dev.mvc.sub_category.Sub_CategoryDAO")
  private Sub_CategoryDAOInter s_categoryDAO = null;

  public Sub_CategoryProc() {
    System.out.println("--> Sub_CategoryProc created.");
  }

  @Override
  public int create(Sub_CategoryVO s_categoryVO) {
    return s_categoryDAO.create(s_categoryVO);
  }

  @Override
  public List<Categrp_CategoryVO> list() {
    return s_categoryDAO.list();
  }

  @Override
  public List<Categrp_CategoryVO> list_by_categrp(int m_categrpno) {
    return s_categoryDAO.list_by_categrp(m_categrpno);
  }

  @Override
  public Categrp_CategoryVO read(int s_categoryno) {
    return s_categoryDAO.read(s_categoryno);
  }

  @Override
  public int update(Sub_CategoryVO s_categoryVO) {
    return s_categoryDAO.update(s_categoryVO);
  }

  @Override
  public int delete(int s_categoryno) {
    return s_categoryDAO.delete(s_categoryno);
  }

  @Override
  public int count_by_categrp(int m_categrpno) {
    return s_categoryDAO.count_by_categrp(m_categrpno);
  }

  @Override
  public int delete_by_categrp(int m_categrpno) {
    return s_categoryDAO.delete_by_categrp(m_categrpno);
  }

  @Override
  public int update_seqno_up(int s_categoryno) {
    return s_categoryDAO.update_seqno_up(s_categoryno);
  }

  @Override
  public int update_seqno_down(int s_categoryno) {
    return s_categoryDAO.update_seqno_down(s_categoryno);
  }

}
