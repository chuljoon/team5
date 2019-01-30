package dev.mvc.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.member.MemberProc")
public class MemberProc implements MemberProcInter {
  @Autowired
  @Qualifier("dev.mvc.member.MemberDAO")
  private MemberDAOInter memberDAO = null;
  
  public MemberProc() {
    System.out.println("--> MemberProc created..");
  }

  @Override
  public int create(MemberVO memberVO) {
    int cnt = memberDAO.create(memberVO);
    return cnt;
  }
  
  @Override
  public int checkEmail(String m_email) {
    int cnt = memberDAO.checkEmail(m_email);
    return cnt;
  }

  @Override
  public int checkNickname(String m_nickname) {
    int cnt = memberDAO.checkNickname(m_nickname);
    return cnt;
  }
  
  @Override
  public boolean isMember(HttpSession session) {
    boolean sw = false;
    
    String m_email = (String)session.getAttribute("m_email");
    
    if (m_email != null) {
      sw = true;
    }
    return sw;
  }
  
  @Override
  public boolean isAdmin(HttpSession session) {
    boolean sw = false;
    String m_email = (String)session.getAttribute("m_email");
    String m_act = (String)session.getAttribute("m_act");
    
    if (m_email != null && m_act == "M") {
      sw = true;
    }
    return sw;
  }

  @Override
  public List<MemberVO> list() {
    List<MemberVO> list = memberDAO.list();
    return list;
  }

  @Override
  public MemberVO read(int memberno) {
    MemberVO memberVO = memberDAO.read(memberno);
    
    return memberVO;
  }

  @Override
  public MemberVO readByEmail(String m_email) {
    MemberVO memberVO = memberDAO.readByEmail(m_email);
    
    return memberVO;
  }

  @Override
  public int update(MemberVO memberVO) {
    int count = memberDAO.update(memberVO);
    return count;
  }

  @Override
  public int passwd_update(int memberno, String m_passwd) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("memberno", memberno);
    map.put("m_passwd", m_passwd);
    
    int count = memberDAO.passwd_update(map);
    
    return count;
  }

  @Override
  public int delete(int memberno) {
    int count = memberDAO.delete(memberno);
    return count;
  }

  @Override
  public int login(String m_email, String m_passwd) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("m_email", m_email);
    map.put("m_passwd", m_passwd);
    int count = memberDAO.login(map);
    
    return count;
  }

  



}
