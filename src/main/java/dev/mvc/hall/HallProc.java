package dev.mvc.hall;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.hall.HallProc")
public class HallProc implements HallProcInter {
  @Autowired
  @Qualifier("dev.mvc.hall.HallDAO")
  private HallDAOInter hallDAO = null;
  
  public HallProc() {
    System.out.println("--> HallProc created.");
  }

  @Override
  public int create(HallVO hallVO) {
    return hallDAO.create(hallVO);
  }

  @Override
  public List<HallVO> list() {
    return hallDAO.list();
  }

  @Override
  public HallVO read(int hallno) {
    return hallDAO.read(hallno);
  }

  @Override
  public HallVO update(int hallno) {
    return hallDAO.update(hallno);
  }

  @Override
  public int update(HallVO hallVO) {
    return hallDAO.update(hallVO);
  }

  @Override
  public int delete(int hallno) {
    return hallDAO.delete(hallno);
  }
 
}
