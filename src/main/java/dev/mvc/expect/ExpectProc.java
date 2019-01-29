package dev.mvc.expect;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.expect.ExpectProc")
public class ExpectProc implements ExpectProcInter {
  @Autowired
  @Qualifier("dev.mvc.expect.ExpectDAO")
  private ExpectDAOInter expectDAO = null;

  public ExpectProc() {
    System.out.println("--> ExpectProc created.");
  }

  @Override
  public int create(ExpectVO expectVO) {
    return expectDAO.create(expectVO);
  }

  @Override
  public List<ExpectVO> list(int contentsno) {
    return expectDAO.list(contentsno);
  }

  @Override
  public ExpectVO read(int expectno) {
    return expectDAO.read(expectno);
  }

  @Override
  public ExpectVO update(int expectno) {
    return expectDAO.update(expectno);
  }

  @Override
  public int update(ExpectVO expectVO) {
    return expectDAO.update(expectVO);
  }

  @Override
  public int delete(int expectno) {
    return expectDAO.delete(expectno);
  }

}
