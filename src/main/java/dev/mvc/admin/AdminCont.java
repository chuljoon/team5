package dev.mvc.admin;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminCont {
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter AdminProc = null;
  
  public AdminCont(){
    System.out.println("--> AdminCont created.."); 
  }
  
//http://localhost:9090/info/admin/create.do
 @RequestMapping(value = "/admin/create.do", method = RequestMethod.GET)
 public ModelAndView create() {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/admin/create"); // webapp/admin/create.jsp
   return mav;
 }
 
 /**
  * 중복 Email 검사
  * http://localhost:9090/info/admin/checkEmail.do?a_email=dlwlrma@gmail.com
  * 결과: {"cnt":1}
  * @param a_email
  * @return
  */
 @ResponseBody
 @RequestMapping(value="/admin/checkEmail.do", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
 public String checkEmail(String a_email) {
   JSONObject json = new JSONObject();
   int cnt = AdminProc.checkEmail(a_email);
   json.put("cnt", cnt);
   return json.toString();
 }
 
 /**
  * 중복 Nickname 검사
  * http://localhost:9090/info/admin/checkNickname.do?a_nickname=아이유짱
  * 결과: {"cnt":1}
  * @param a_nickname
  * @return
  */
 @ResponseBody
 @RequestMapping(value="/admin/checkNickname.do", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
 public String checkNickname(String a_nickname) {
   JSONObject json = new JSONObject();
   int cnt = AdminProc.checkNickname(a_nickname);  
   json.put("cnt", cnt);
   return json.toString();
 }
 
 /**
  * 관리자 가입 처리
  * @param redirectAttributes
  * @param request
  * @param adminVO
  * @return
  */
 @RequestMapping(value="/admin/create.do", method=RequestMethod.POST)
 public ModelAndView create(RedirectAttributes redirectAttributes, HttpServletRequest request, AdminVO adminVO) {
   // System.out.println("--> update() POST called.");
   ModelAndView mav = new ModelAndView();
   adminVO.setA_act("M");
   
   int count_e = AdminProc.checkEmail(adminVO.getA_email());
   int count_n = AdminProc.checkNickname(adminVO.getA_nickname());
   int count = count_e + count_n;
   // System.out.println(adminVO.getM_email()); 
   if (count_e == 1 || count_n == 1) { // ID 중복시 메시지 출력
     redirectAttributes.addAttribute("sw_e", "a_email");
     redirectAttributes.addAttribute("count_e", count_e); // 1 or 0
     redirectAttributes.addAttribute("sw_n", "a_nickname");
     redirectAttributes.addAttribute("count_n", count_n); // 1 or 0
   } else {
       
       count = AdminProc.create(adminVO); // 등록

       redirectAttributes.addAttribute("sw", "create");
       redirectAttributes.addAttribute("count", count); // 1 or 0
     
     mav.setViewName("redirect:/admin/create_message.jsp");
   }
   return mav;
 }
 
 
 /**
  * 관리자 목록 
  * @param session
  * @return
  */
 @RequestMapping(value="/admin/list.do", method=RequestMethod.GET)
 public ModelAndView list(HttpSession session){
   // System.out.println("--> create() GET called.");
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/admin/list"); // webapp/admin/list.jsp

   if (AdminProc.isAdmin(session) == false) {
     mav.setViewName("redirect:/admin/login_need.jsp"); 
   } else { 
     mav.setViewName("/admin/list"); // webapp/admin/list.jsp
   }

   List<AdminVO> list = AdminProc.list();
   mav.addObject("list", list);
 
   return mav;
 }
 /**
  * 관리자 목록 조회
  * @param adminno
  * @return
  */
 @RequestMapping(value="/admin/read.do", method=RequestMethod.GET)
 public ModelAndView read(int adminno){
   // System.out.println("--> read(int adminno) GET called.");
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/admin/read"); // webapp/admin/read.jsp
   
   AdminVO adminVO = AdminProc.read(adminno);
   mav.addObject("adminVO", adminVO);
   
   return mav;
 }  
 
 /**
  * 관리자 수정
  * @param redirectAttributes
  * @param request
  * @param adminVO
  * @return
  */
 @RequestMapping(value="/admin/update.do", method=RequestMethod.POST)
 public ModelAndView update(RedirectAttributes redirectAttributes,
                                       HttpServletRequest request, AdminVO adminVO){
   // System.out.println("--> update() POST called.");
   ModelAndView mav = new ModelAndView();
   
   int count = AdminProc.update(adminVO); // 수정

   redirectAttributes.addAttribute("count", count); // 1 or 0
   redirectAttributes.addAttribute("adminno", adminVO.getAdminno()); // 관리자 번호
   
   mav.setViewName("redirect:/admin/update_message.jsp");
  
   return mav;
 }
 
 /**
  * 패스워드 변경폼
  * @return
  */
 @RequestMapping(value="/admin/passwd_update.do", method=RequestMethod.GET)
 public ModelAndView passwd_update(){
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/admin/passwd_update"); // webapp/admin/passwd_update.jsp
   
   // mav.addObject("adminno", adminno);
   
   return mav;
 }  
 
 /**
  * 패스워드를 변경합니다.
  * @param request
  * @param a_passwd
  * @param new_passwd
  * @return
  */
 @RequestMapping(value="/admin/passwd_update.do", method=RequestMethod.POST)
 public ModelAndView passwd_update(HttpServletRequest request,
                                                   HttpSession session,
                                                   String a_passwd,
                                                   String new_passwd){
   // System.out.println("--> passwd_update() POST called.");
   ModelAndView mav = new ModelAndView();
   
   // String m_email = "test1@gmail.com";
   String a_email = (String)session.getAttribute("a_email"); // session
   // int adminno = 1;
   int adminno = (Integer)session.getAttribute("adminno"); // session
   
   // 로그인 관련 추가 영역
   int count = AdminProc.login(a_email, a_passwd); // 현재 패스워드 검사
//   int count = 1;
//   System.out.println("--> count: " + count);
   if (count == 1) { // 로그인한 관리자의 패스워드 검사
     int count_update = AdminProc.passwd_update(adminno, new_passwd);
     System.out.println("--> count_update: " + count_update);
     mav.setViewName("redirect:/admin/passwd_update_message.jsp?count=" + count_update + "&adminno=" + adminno);
   } else {
     mav.setViewName("redirect:/admin/login.do");      
   }
   
   return mav;
 } 
 
 
 /**
  * 관리자 삭제
  * @param adminno
  * @return
  */
 @RequestMapping(value="/admin/delete.do", method=RequestMethod.GET)
 public ModelAndView delete(int adminno){
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/admin/delete"); // webapp/admin/delete.jsp
   
   AdminVO adminVO = AdminProc.read(adminno);
   mav.addObject("adminVO", adminVO);
   
   return mav;
 }  
 /**
  * 관리자 삭제
  * @param redirectAttributes
  * @param request
  * @param adminno
  * @return
  */
 @RequestMapping(value="/admin/delete.do", method=RequestMethod.POST)
 public ModelAndView delete(RedirectAttributes redirectAttributes,
                                       HttpServletRequest request, int adminno){
   ModelAndView mav = new ModelAndView();
   
   int count = AdminProc.delete(adminno);

   redirectAttributes.addAttribute("count", count); // 1 or 0
   redirectAttributes.addAttribute("adminno", adminno); // 관리자 번호
   
   mav.setViewName("redirect:/admin/delete_message.jsp");
  
   return mav;
 }
 
 /**
  * 로그인 폼
  * @return
  */
 // http://localhost:9090/info/admin/login.do 
 @RequestMapping(value = "/admin/login.do", 
                            method = RequestMethod.GET)
 public ModelAndView login(HttpServletRequest request) {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/admin/login_ck_form"); // /webapp/admin/login_ck_form.jsp
   
   Cookie[] cookies = request.getCookies();
   Cookie cookie = null;

   String ck_a_email = ""; // id 저장 변수
   String ck_a_email_save = ""; // id 저장 여부를 체크하는 변수
   String ck_a_passwd = ""; // passwd 저장 변수
   String ck_a_passwd_save = ""; // passwd 저장 여부를 체크하는 변수

   if (cookies != null) {
     for (int i=0; i < cookies.length; i++){
       cookie = cookies[i]; // 쿠키 객체 추출
       
       if (cookie.getName().equals("ck_a_email")){
         ck_a_email = cookie.getValue(); 
       }else if(cookie.getName().equals("ck_a_email_save")){
         ck_a_email_save = cookie.getValue();  // Y, N
       }else if (cookie.getName().equals("ck_a_passwd")){
         ck_a_passwd = cookie.getValue();         // 1234
       }else if(cookie.getName().equals("ck_a_passwd_save")){
         ck_a_passwd_save = cookie.getValue();  // Y, N
       }
     }
   }
   
   mav.addObject("ck_a_email", ck_a_email);
   mav.addObject("ck_a_email_save", ck_a_email_save);
   mav.addObject("ck_a_passwd", ck_a_passwd);
   mav.addObject("ck_a_passwd_save", ck_a_passwd_save);
   
   return mav;
 }
 
 /**
  * 로그인 처리
  * @param request
  * @param response
  * @param session
  * @param a_email
  * @param a_email_save
  * @param a_passwd
  * @param a_passwd_save
  * @return
  */
 @RequestMapping(value="/admin/login.do", method=RequestMethod.POST)
 public ModelAndView login(HttpServletRequest request, 
                                      HttpServletResponse response,
                                      HttpSession session,
                                      String a_email, 
                                      @RequestParam(value="a_email_save", defaultValue="") String a_email_save,
                                      String a_passwd,
                                      @RequestParam(value="a_passwd_save", defaultValue="") String a_passwd_save){
   // System.out.println("--> login() POST called.");
   ModelAndView mav = new ModelAndView();
   
   if (AdminProc.login(a_email, a_passwd) != 1) { // 로그인 실패시
     mav.setViewName("redirect:/admin/login_message.jsp");
     
   } else { // 패스워드 일치하는 경우
     AdminVO old_adminVO = AdminProc.readByEmail(a_email);

     session.setAttribute("adminno",  old_adminVO.getAdminno()); // session 내부 객체
     session.setAttribute("a_email", a_email);
     session.setAttribute("a_passwd", a_passwd);
     session.setAttribute("a_name", old_adminVO.getA_name());
     
     // -------------------------------------------------------------------
     // id 관련 쿠기 저장
     // -------------------------------------------------------------------
     if (a_email_save.equals("Y")) { // id를 저장할 경우
       Cookie ck_a_email = new Cookie("ck_a_email", a_email);
       ck_a_email.setMaxAge(60 * 60 * 72 * 10); // 30 day, 초단위
       response.addCookie(ck_a_email);
     } else { // N, id를 저장하지 않는 경우
       Cookie ck_a_email = new Cookie("ck_a_email", "");
       ck_a_email.setMaxAge(0);
       response.addCookie(ck_a_email);
     }
     // a_email를 저장할지 선택하는  CheckBox 체크 여부
     Cookie ck_a_email_save = new Cookie("ck_a_email_save", a_email_save);
     ck_a_email_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
     response.addCookie(ck_a_email_save);
     // -------------------------------------------------------------------

     // -------------------------------------------------------------------
     // a_password 관련 쿠기 저장
     // -------------------------------------------------------------------
     if (a_passwd_save.equals("Y")) { // 패스워드 저장할 경우
       Cookie ck_a_passwd = new Cookie("ck_a_passwd", a_passwd);
       ck_a_passwd.setMaxAge(60 * 60 * 72 * 10); // 30 day
       response.addCookie(ck_a_passwd);
     } else { // N, 패스워드를 저장하지 않을 경우
       Cookie ck_a_passwd = new Cookie("ck_a_passwd", "");
       ck_a_passwd.setMaxAge(0);
       response.addCookie(ck_a_passwd);
     }
     // passwd를 저장할지 선택하는  CheckBox 체크 여부
     Cookie ck_a_passwd_save = new Cookie("ck_a_passwd_save", a_passwd_save);
     ck_a_passwd_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
     response.addCookie(ck_a_passwd_save);
     // -------------------------------------------------------------------
     
     mav.setViewName("redirect:/index.do"); // 확장자 명시 
     
   }
   
   return mav;
 }
 
 /**
  * 로그아웃 처리
  * @param request
  * @param session
  * @return
  */
 @RequestMapping(value="/admin/logout.do", method=RequestMethod.GET)
 public ModelAndView logout(HttpServletRequest request, 
                                        HttpSession session){
   // System.out.println("--> logout() GET called.");
   ModelAndView mav = new ModelAndView();

   session.invalidate(); // session 내부 객체의 등록된 모든 session 변수 삭제
   
   // webapp/admin/message_logout.jsp
   mav.setViewName("redirect:/admin/logout_message.jsp"); 
   
   return mav;
 }

 

}
