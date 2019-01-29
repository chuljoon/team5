package dev.mvc.main_categrp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.main_categrp.Main_CategrpProc")
public class Main_CategrpProc implements Main_CategrpProcInter {
  @Autowired
  @Qualifier("dev.mvc.main_categrp.Main_CategrpDAO")
  private Main_CategrpDAOInter m_categrpDAO = null;

  public Main_CategrpProc() {
    System.out.println("--> Main_CategrpProc created.");
  }

  // @Override
  public int create(Main_CategrpVO m_categrpVO) {
    return m_categrpDAO.create(m_categrpVO);
  }

  @Override
  public List<Main_CategrpVO> list() {
    return m_categrpDAO.list();
  }

  @Override
  public Main_CategrpVO read(int m_categrpno) {
    return m_categrpDAO.read(m_categrpno);
  }

  @Override
  public int update(Main_CategrpVO m_categrpVO) {
    return m_categrpDAO.update(m_categrpVO);
  }

  @Override
  public int delete(int m_categrpno) {
    return m_categrpDAO.delete(m_categrpno);
  }
}
