package dev.mvc.admin;

import java.util.List;
import java.util.Map;

public interface AdminDAOInter {
  
  /**
   * 관리자 등록
   * @param adminVO
   * @return 등록된 회원 수 1 or 0
   */
  public int create(AdminVO adminVO);
  
  /**
   * 중복 이메일 검사
   * @param a_email
   * @return 중복 이메일 개수
   */
  public int checkEmail(String a_email);
  
  /**
   * 중복 닉네임 검사
   * @param a_nickname
   * @return 중복 닉네임 개수
   */
  public int checkNickname(String a_nickname);
  
  /**
   * 관리자 전체 목록
   * @return
   */
  public List<AdminVO> list();
  
  /**
   * 조회
   * @param adminno
   * @return
   */
  public AdminVO read(int adminno);

  /**
   * 조회
   * @param a_email
   * @return
   */
  public AdminVO readByEmail(String a_email);
  
  /**
   * 변경
   * @param adminVO
   * @return
   */
  public int update(AdminVO adminVO);
  
  /**
   * 패스워드 변경 
   * @param map
   * @return
   */
  public int passwd_update(Map<String, Object> map);
 
  /**
   * 레코드 1건 삭제
   * @param mno 삭제할 회원 번호
   * @return 삭제된 레코드 갯수
   */
  public int delete(int adminno);
  
  /**
   * 로그인 
   * @param map
   * @return
   */
  public int login(Map map);
  
  
  

}