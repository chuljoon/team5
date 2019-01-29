package dev.mvc.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.admin.AdminProc")
public class AdminProc implements AdminProcInter {
  @Autowired
  @Qualifier("dev.mvc.admin.AdminDAO")
  private AdminDAOInter adminDAO = null;
  
  public AdminProc() {
    System.out.println("--> AdminProc created..");
  }

  @Override
  public int create(AdminVO adminVO) {
    int cnt = adminDAO.create(adminVO);
    return cnt;
  }
  
  @Override
  public int checkEmail(String a_email) {
    int cnt = adminDAO.checkEmail(a_email);
    return cnt;
  }

  @Override
  public int checkNickname(String a_nickname) {
    int cnt = adminDAO.checkNickname(a_nickname);
    return cnt;
  }
  
  @Override
  public boolean isAdmin(HttpSession session) {
    boolean sw = false;
    
    String a_email = (String)session.getAttribute("a_email");
    
    if (a_email != null){
      sw = true;
    }
    return sw;
  }

  @Override
  public List<AdminVO> list() {
    List<AdminVO> list = adminDAO.list();
    return list;
  }

  @Override
  public AdminVO read(int adminno) {
    AdminVO adminVO = adminDAO.read(adminno);
    
    return adminVO;
  }

  @Override
  public AdminVO readByEmail(String a_email) {
    AdminVO adminVO = adminDAO.readByEmail(a_email);
    
    return adminVO;
  }

  @Override
  public int update(AdminVO adminVO) {
    int count = adminDAO.update(adminVO);
    return count;
  }

  @Override
  public int passwd_update(int adminno, String a_passwd) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("adminno", adminno);
    map.put("a_passwd", a_passwd);
    
    int count = adminDAO.passwd_update(map);
    
    return count;
  }

  @Override
  public int delete(int adminno) {
    int count = adminDAO.delete(adminno);
    return count;
  }

  @Override
  public int login(String a_email, String a_passwd) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("a_email", a_email);
    map.put("a_passwd", a_passwd);
    int count = adminDAO.login(map);
    
    return count;
  }

  



}
