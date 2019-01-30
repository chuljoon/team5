package dev.mvc.member;

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
public class MemberCont {
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc = null;
  
  public MemberCont(){
    System.out.println("--> MemberCont created.."); 
  }
  
//http://localhost:9090/info/member/create.do
 @RequestMapping(value = "/member/create.do", method = RequestMethod.GET)
 public ModelAndView create() {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/member/create"); // webapp/member/create.jsp
   return mav;
 }
 
 /**
  * �ߺ� Email �˻�
  * http://localhost:9090/info/member/checkEmail.do?m_email=dlwlrma@gmail.com
  * ���: {"cnt":1}
  * @param m_email
  * @return
  */
 @ResponseBody
 @RequestMapping(value="/member/checkEmail.do", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
 public String checkEmail(String m_email) {
   JSONObject json = new JSONObject();
   int cnt = memberProc.checkEmail(m_email);
   json.put("cnt", cnt);
   return json.toString();
 }
 
 /**
  * �ߺ� Nickname �˻�
  * http://localhost:9090/info/member/checkNickname.do?m_nickname=������¯
  * ���: {"cnt":1}
  * @param m_nickname
  * @return
  */
 @ResponseBody
 @RequestMapping(value="/member/checkNickname.do", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
 public String checkNickname(String m_nickname) {
   JSONObject json = new JSONObject();
   int cnt = memberProc.checkNickname(m_nickname);  
   json.put("cnt", cnt);
   return json.toString();
 }
 
 /**
  * ȸ�� ���� ó��
  * @param redirectAttributes
  * @param request
  * @param memberVO
  * @return
  */
 @RequestMapping(value="/member/create.do", method=RequestMethod.POST)
 public ModelAndView create(RedirectAttributes redirectAttributes, HttpServletRequest request, MemberVO memberVO) {
   // System.out.println("--> update() POST called.");
   ModelAndView mav = new ModelAndView();
   memberVO.setM_act("G"); /* �����߿��� ȸ������ ��� �Ϲ�ȸ�� */
   
   int count_e = memberProc.checkEmail(memberVO.getM_email());
   int count_n = memberProc.checkNickname(memberVO.getM_nickname());
   int count = count_e + count_n;
   // System.out.println(memberVO.getM_email()); 
   if (count_e == 1 || count_n == 1) { // ID �ߺ��� �޽��� ���
     redirectAttributes.addAttribute("sw_e", "m_email");
     redirectAttributes.addAttribute("count_e", count_e); // 1 or 0
     redirectAttributes.addAttribute("sw_n", "m_nickname");
     redirectAttributes.addAttribute("count_n", count_n); // 1 or 0
   } else {
       
       count = memberProc.create(memberVO); // ���

       redirectAttributes.addAttribute("sw", "create");
       redirectAttributes.addAttribute("count", count); // 1 or 0
     
     mav.setViewName("redirect:/member/create_message.jsp");
   }
   return mav;
 }
 
 
 /**
  * ȸ�� ��� 
  * @param session
  * @return
  */
 @RequestMapping(value="/member/list.do", method=RequestMethod.GET)
 public ModelAndView list(HttpSession session){
   // System.out.println("--> create() GET called.");
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/member/list"); // webapp/member/list.jsp

   if (memberProc.isAdmin(session) == false) {
     mav.setViewName("redirect:/member/login_need.jsp"); 
   } else { 
     mav.setViewName("/member/list"); // webapp/member/list.jsp
   }

   List<MemberVO> list = memberProc.list();
   mav.addObject("list", list);
 
   return mav;
 }
 /**
  * ȸ�� ��� ��ȸ
  * @param memberno
  * @return
  */
 @RequestMapping(value="/member/read.do", method=RequestMethod.GET)
 public ModelAndView read(int memberno){
   // System.out.println("--> read(int memberno) GET called.");
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/member/read"); // webapp/member/read.jsp
   
   MemberVO memberVO = memberProc.read(memberno);
   mav.addObject("memberVO", memberVO);
   
   return mav;
 }  
 
 /**
  * ȸ�� ����
  * @param redirectAttributes
  * @param request
  * @param memberVO
  * @return
  */
 @RequestMapping(value="/member/update.do", method=RequestMethod.POST)
 public ModelAndView update(RedirectAttributes redirectAttributes,
                                       HttpServletRequest request, MemberVO memberVO){
   // System.out.println("--> update() POST called.");
   ModelAndView mav = new ModelAndView();
   
   int count = memberProc.update(memberVO); // ����

   redirectAttributes.addAttribute("count", count); // 1 or 0
   redirectAttributes.addAttribute("memberno", memberVO.getMemberno()); // ȸ�� ��ȣ
   
   mav.setViewName("redirect:/member/update_message.jsp");
  
   return mav;
 }
 
 /**
  * �н����� ������
  * @return
  */
 @RequestMapping(value="/member/passwd_update.do", method=RequestMethod.GET)
 public ModelAndView passwd_update(){
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/member/passwd_update"); // webapp/member/passwd_update.jsp
   
   // mav.addObject("memberno", memberno);
   
   return mav;
 }  
 
 /**
  * �н����带 �����մϴ�.
  * @param request
  * @param passwd
  * @param new_passwd
  * @return
  */
 @RequestMapping(value="/member/passwd_update.do", method=RequestMethod.POST)
 public ModelAndView passwd_update(HttpServletRequest request,
                                                   HttpSession session,
                                                   String m_passwd,
                                                   String new_passwd){
   // System.out.println("--> passwd_update() POST called.");
   ModelAndView mav = new ModelAndView();
   
   // String m_email = "test1@gmail.com";
   String m_email = (String)session.getAttribute("m_email"); // session
   // int memberno = 1;
   int memberno = (Integer)session.getAttribute("memberno"); // session
   
   // �α��� ���� �߰� ����
   int count = memberProc.login(m_email, m_passwd); // ���� �н����� �˻�
//   int count = 1;
//   System.out.println("--> count: " + count);
   if (count == 1) { // �α����� ȸ���� �н����� �˻�
     int count_update = memberProc.passwd_update(memberno, new_passwd);
     System.out.println("--> count_update: " + count_update);
     mav.setViewName("redirect:/member/passwd_update_message.jsp?count=" + count_update + "&memberno=" + memberno);
   } else {
     mav.setViewName("redirect:/member/login.do");      
   }
   
   return mav;
 } 
 
 
 /**
  * ȸ�� ����
  * @param memberno
  * @return
  */
 @RequestMapping(value="/member/delete.do", method=RequestMethod.GET)
 public ModelAndView delete(int memberno){
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/member/delete"); // webapp/member/delete.jsp
   
   MemberVO memberVO = memberProc.read(memberno);
   mav.addObject("memberVO", memberVO);
   
   return mav;
 }  
 /**
  * ȸ�� ����
  * @param redirectAttributes
  * @param request
  * @param memberno
  * @return
  */
 @RequestMapping(value="/member/delete.do", method=RequestMethod.POST)
 public ModelAndView delete(RedirectAttributes redirectAttributes,
                                       HttpServletRequest request, int memberno){
   ModelAndView mav = new ModelAndView();
   
   int count = memberProc.delete(memberno);

   redirectAttributes.addAttribute("count", count); // 1 or 0
   redirectAttributes.addAttribute("memberno", memberno); // ȸ�� ��ȣ
   
   mav.setViewName("redirect:/member/delete_message.jsp");
  
   return mav;
 }
 
 /**
  * �α��� ��
  * @return
  */
 // http://localhost:9090/member/member/login.do 
 @RequestMapping(value = "/member/login.do", 
                            method = RequestMethod.GET)
 public ModelAndView login(HttpServletRequest request) {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/member/login_ck_form"); // /webapp/member/login_ck_form.jsp
   
   Cookie[] cookies = request.getCookies();
   Cookie cookie = null;

   String ck_m_email = ""; // id ���� ����
   String ck_m_email_save = ""; // id ���� ���θ� üũ�ϴ� ����
   String ck_m_passwd = ""; // passwd ���� ����
   String ck_m_passwd_save = ""; // passwd ���� ���θ� üũ�ϴ� ����

   if (cookies != null) {
     for (int i=0; i < cookies.length; i++){
       cookie = cookies[i]; // ��Ű ��ü ����
       
       if (cookie.getName().equals("ck_m_email")){
         ck_m_email = cookie.getValue(); 
       }else if(cookie.getName().equals("ck_m_email_save")){
         ck_m_email_save = cookie.getValue();  // Y, N
       }else if (cookie.getName().equals("ck_m_passwd")){
         ck_m_passwd = cookie.getValue();         // 1234
       }else if(cookie.getName().equals("ck_m_passwd_save")){
         ck_m_passwd_save = cookie.getValue();  // Y, N
       }
     }
   }
   
   mav.addObject("ck_m_email", ck_m_email);
   mav.addObject("ck_m_email_save", ck_m_email_save);
   mav.addObject("ck_m_passwd", ck_m_passwd);
   mav.addObject("ck_m_passwd_save", ck_m_passwd_save);
   
   return mav;
 }
 
 /**
  * �α��� ó��
  * @param request
  * @param response
  * @param session
  * @param m_email
  * @param m_email_save
  * @param m_passwd
  * @param m_passwd_save
  * @return
  */
 @RequestMapping(value="/member/login.do", method=RequestMethod.POST)
 public ModelAndView login(HttpServletRequest request, 
                                      HttpServletResponse response,
                                      HttpSession session,
                                      String m_email, 
                                      @RequestParam(value="m_email_save", defaultValue="") String m_email_save,
                                      String m_passwd,
                                      @RequestParam(value="m_passwd_save", defaultValue="") String m_passwd_save){
   // System.out.println("--> login() POST called.");
   ModelAndView mav = new ModelAndView();
   
   if (memberProc.login(m_email, m_passwd) != 1) { // �α��� ���н�
     mav.setViewName("redirect:/member/login_message.jsp");
     
   } else { // �н����� ��ġ�ϴ� ���
     MemberVO old_memberVO = memberProc.readByEmail(m_email);

     session.setAttribute("memberno",  old_memberVO.getMemberno()); // session ���� ��ü
     session.setAttribute("m_email", m_email);
     session.setAttribute("m_passwd", m_passwd);
     session.setAttribute("m_name", old_memberVO.getM_name());
     
     // -------------------------------------------------------------------
     // id ���� ��� ����
     // -------------------------------------------------------------------
     if (m_email_save.equals("Y")) { // id�� ������ ���
       Cookie ck_m_email = new Cookie("ck_m_email", m_email);
       ck_m_email.setMaxAge(60 * 60 * 72 * 10); // 30 day, �ʴ���
       response.addCookie(ck_m_email);
     } else { // N, id�� �������� �ʴ� ���
       Cookie ck_m_email = new Cookie("ck_m_email", "");
       ck_m_email.setMaxAge(0);
       response.addCookie(ck_m_email);
     }
     // id�� �������� �����ϴ�  CheckBox üũ ����
     Cookie ck_m_email_save = new Cookie("ck_m_email_save", m_email_save);
     ck_m_email_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
     response.addCookie(ck_m_email_save);
     // -------------------------------------------------------------------

     // -------------------------------------------------------------------
     // Password ���� ��� ����
     // -------------------------------------------------------------------
     if (m_passwd_save.equals("Y")) { // �н����� ������ ���
       Cookie ck_m_passwd = new Cookie("ck_m_passwd", m_passwd);
       ck_m_passwd.setMaxAge(60 * 60 * 72 * 10); // 30 day
       response.addCookie(ck_m_passwd);
     } else { // N, �н����带 �������� ���� ���
       Cookie ck_m_passwd = new Cookie("ck_m_passwd", "");
       ck_m_passwd.setMaxAge(0);
       response.addCookie(ck_m_passwd);
     }
     // passwd�� �������� �����ϴ�  CheckBox üũ ����
     Cookie ck_m_passwd_save = new Cookie("ck_m_passwd_save", m_passwd_save);
     ck_m_passwd_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
     response.addCookie(ck_m_passwd_save);
     // -------------------------------------------------------------------
     
     mav.setViewName("redirect:/index.do"); // Ȯ���� ��� 
     
   }
   
   return mav;
 }
 
 /**
  * �α׾ƿ� ó��
  * @param request
  * @param session
  * @return
  */
 @RequestMapping(value="/member/logout.do", method=RequestMethod.GET)
 public ModelAndView logout(HttpServletRequest request, 
                                        HttpSession session){
   // System.out.println("--> logout() GET called.");
   ModelAndView mav = new ModelAndView();

   session.invalidate(); // session ���� ��ü�� ��ϵ� ��� session ���� ����
   
   // webapp/member/message_logout.jsp
   mav.setViewName("redirect:/member/logout_message.jsp"); 
   
   return mav;
 }

 

}
