package dev.mvc.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class QuestionCont {
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.question.QuestionProc")
  private QuestionProcInter questionProc = null;
  
  public QuestionCont() {
    System.out.println("--> QuestionCont created.."); 
  }
  
  @RequestMapping(value = "/question/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session) {
    // System.out.println("--> create() GET executed");
    ModelAndView mav = new ModelAndView();
    
    int memberno = (Integer)session.getAttribute("memberno");
    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
    if (memberProc.isMember(session) == false) {
      mav.setViewName("redirect:/member/login_need_g.jsp"); 
    } else { 
      mav.setViewName("/question/create"); // /webapp/notice/delete.jsp
    }

    return mav;
  }
  
  @RequestMapping(value = "/question/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, QuestionVO questionVO) {
    // System.out.println("--> create() POST executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/question/message"); // /webapp/question/message.jsp

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/question/storage");
    MultipartFile file1MF = questionVO.getFile1MF();
    String question_file1 = ""; // �÷��� ������ ���ϸ�
    long question_size1 = file1MF.getSize();
    String question_thumb = ""; // �÷��� ������ ���ϸ�

    if (question_size1 > 0) {
      question_file1 = Upload.saveFileSpring(file1MF, upDir);

      if (Tool.isImage(question_file1)) {
        question_thumb = Tool.preview(upDir, question_file1, 120, 80); // Thumb �̹��� ����
      }
    }
    questionVO.setQuestion_file1(question_file1);
    questionVO.setQuestion_size1(question_size1); 
    questionVO.setQuestion_thumb(question_thumb);
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------

    if (questionProc.create(questionVO) == 1) {
      msgs.add("[���� ��� ����]");
      msgs.add("������ ����߽��ϴ�.");
    } else {
      msgs.add("[���� ��� ����]");
      msgs.add("���� ��Ͽ� �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���. �� ���� ���: 000-0000-0000");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
    }
    // links.add("<button type='button' onclick=\"location.href='./list_by_categoryno.do?categoryno="+blogVO.getCategoryno()+"'\">���</button>");

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }
  
  @RequestMapping(value = "/question/list.do", method = RequestMethod.GET)
  public ModelAndView list(HttpSession session) {
    System.out.println("--> list() GET called.");
    ModelAndView mav = new ModelAndView();
    
    int memberno = (Integer)session.getAttribute("memberno");
    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
    if (memberProc.isMember(session) == false) {
      mav.setViewName("redirect:/member/login_need_g.jsp"); 
    } else { 
      mav.setViewName("/question/list"); // /webapp/question/list.jsp
    }

    List<QuestionVO> list = questionProc.list();
    mav.addObject("list", list);

    return mav;
  }
  
//http://localhost:9090/info/question/list_by_member.do?memberno=1
 @RequestMapping(value = "/question/list_by_member.do", method = RequestMethod.GET)
 public ModelAndView list_by_member(int memberno, HttpSession session) {
   ModelAndView mav = new ModelAndView();
   
   MemberVO memberVO = memberProc.read(memberno);
   mav.addObject("memberVO", memberVO);

   List<QuestionVO> list = questionProc.list_by_member(memberno);
   mav.addObject("list", list);
   
   if (memberProc.isMember(session) == false) {
     mav.setViewName("redirect:/member/login_need_g.jsp"); 
   } else { 
     mav.setViewName("/question/list_by_member"); // /webapp/question/list_by_member.jsp
   }

   return mav;
 }
  
  @RequestMapping(value = "/question/read.do", method = RequestMethod.GET)
  public ModelAndView read(int questionno, HttpSession session) {
    System.out.println("--> read() GET executed");
    ModelAndView mav = new ModelAndView();
    
    int memberno = (Integer)session.getAttribute("memberno");
    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
    if (memberProc.isMember(session) == false) {
      mav.setViewName("redirect:/member/login_need_g.jsp"); 
    } else { 
      mav.setViewName("/question/read"); // /webapp/blog/read.jsp
    }

    QuestionVO questionVO = questionProc.read(questionno);
    mav.addObject("questionVO", questionVO);
    
    return mav;
  }
  
//http://localhost:9090/info/question/update.do
 @RequestMapping(value = "/question/update.do", method = RequestMethod.GET)
 public ModelAndView update(int questionno, HttpSession session) {
   System.out.println("--> update() GET executed");
   ModelAndView mav = new ModelAndView();
   
   int memberno = (Integer)session.getAttribute("memberno");
   MemberVO memberVO = memberProc.read(memberno);
   mav.addObject("memberVO", memberVO);
   if (memberProc.isAdmin(session) == false) {
     mav.setViewName("redirect:/member/login_need.jsp"); 
   } else { 
     mav.setViewName("/question/update"); // /webapp/question/update.jsp
   }
   

   QuestionVO questionVO = questionProc.upeate(questionno);
   mav.addObject("questionVO", questionVO);

   return mav;
 }
 
 @RequestMapping(value="/question/update.do", method=RequestMethod.POST)
 public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, QuestionVO questionVO, HttpSession session){
   System.out.println("--> update() POST called.");
   ModelAndView mav = new ModelAndView();
   
   // int categoryno = blogVO.getCategoryno();
   // ---------------------------------------------------------------------------
   // ���� ����
   // ---------------------------------------------------------------------------
   String upDir = Tool.getRealPath(request, "/question/storage");
   MultipartFile file1MF = questionVO.getFile1MF();
   String question_file1 = ""; // �÷��� ������ ���ϸ�
   long question_size1 = file1MF.getSize();
   String question_thumb = ""; // �÷��� ������ ���ϸ�
   
   // ������ ��ϵ� �� ���� �ε�
   QuestionVO questionVO_old = questionProc.read(questionVO.getQuestionno());
   
   if (question_size1 > 0) { // ��ϵ� ������ �ִٸ�
     Tool.deleteFile(upDir, questionVO_old.getQuestion_file1());    // ���� ���� ����
     Tool.deleteFile(upDir, questionVO_old.getQuestion_thumb()); // ���� Thumb ���� ����
     
     question_file1 = Upload.saveFileSpring(file1MF, upDir); // �ű� ���� ���ε�
     
     if (Tool.isImage(question_file1)) { // ���ο� ���� �̹������� �˻�
       question_thumb = Tool.preview(upDir, question_file1, 120, 80); // Thumb �̹��� ����
     } 
   } else {
     // ������ �������� �ʴ� ��� ���� ���� ���� ���
     question_file1 = questionVO_old.getQuestion_file1();
     question_size1 = questionVO_old.getQuestion_size1();
     question_thumb = questionVO_old.getQuestion_thumb();
   }
   questionVO.setQuestion_file1(question_file1);
   questionVO.setQuestion_size1(question_size1);
   questionVO.setQuestion_thumb(question_thumb);
   // ---------------------------------------------------------------------------
   
   // ȸ�� ���� �� session ���κ���
   int memberno = (Integer)session.getAttribute("memberno");
   questionVO.setMemberno(memberno); 

   int count = questionProc.update(questionVO);

   redirectAttributes.addAttribute("count", count);

   // redirect�ÿ��� request�� �����̾ȵ����� �Ʒ��� ����� �̿��Ͽ� ������ ����
   redirectAttributes.addAttribute("questionno", questionVO.getQuestionno());
   redirectAttributes.addAttribute("memberno", questionVO.getMemberno());

   mav.setViewName("redirect:/question/update_message.jsp");
   
   return mav;
 }
 
 @RequestMapping(value = "/question/delete.do", method = RequestMethod.GET)
 public ModelAndView delete(int questionno, HttpSession session) {
   // System.out.println("--> delete() GET executed");
   ModelAndView mav = new ModelAndView();
   
   int memberno = (Integer)session.getAttribute("memberno");
   MemberVO memberVO = memberProc.read(memberno);
   mav.addObject("memberVO", memberVO);
   if (memberProc.isAdmin(session) == false) {
     mav.setViewName("redirect:/member/login_need.jsp"); 
   } else { 
     mav.setViewName("/question/delete"); // /webapp/question/delete.jsp
   }
   

   QuestionVO questionVO = questionProc.read(questionno);
   mav.addObject("questionVO", questionVO);

   return mav;
 }
  
 @RequestMapping(value = "/question/delete.do", method = RequestMethod.POST)
 public ModelAndView delete(RedirectAttributes redirectAttributes, 
                                       HttpServletRequest request, 
                                       int questionno, 
                                       @RequestParam(value="question_word", defaultValue="") String question_word,
                                       @RequestParam(value="nowPage", defaultValue="1") int nowPage 
     ) {
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/question/delete_message"); // /webapp/contents/delete_message.jsp

   String upDir = Tool.getRealPath(request, "/question/storage"); // ���� ���� ����
                                                                  // ���

   QuestionVO questionVO = questionProc.read(questionno); // ������ ���� ������ �б� ����
                                                          // ����

   String question_thumb_old = questionVO.getQuestion_thumb();
   String question_file1_old = questionVO.getQuestion_file1();

   StringTokenizer question_thumb_st = new StringTokenizer(question_thumb_old, "/"); // Thumbs
   while (question_thumb_st.hasMoreTokens()) { // �ܾ �ִ��� �˻�
     String fname = upDir + question_thumb_st.nextToken(); // �ܾ� ����
     Tool.deleteFile(fname);
   }

   StringTokenizer question_file1_st = new StringTokenizer(question_file1_old, "/"); // files
   while (question_file1_st.hasMoreTokens()) { // �ܾ �ִ��� �˻�
     String fname = upDir + question_file1_st.nextToken(); // �ܾ� ����
     Tool.deleteFile(fname);
   }

   int count = questionProc.delete(questionno); // ���ڵ� ����

   /*if (count == 1) {
     categoryProc.decreaseCnt(categoryno); // ��ϵ� �ۼ��� ����
     
     // 4���� ���ڵ尡 �ϳ��� �������ΰ�� 5��° ���ڵ尡 �����Ǹ� ����������
     // 2���������� 1 �������� �ٿ����մϴ�. 
     HashMap<String, Object> hashMap = new HashMap<String, Object>();
     hashMap.put("categoryno", categoryno); // #{categoryno}
     hashMap.put("question_word", question_word);                  // #{question_word}
     if (contentsProc.search_count(hashMap) % Contents.RECORD_PER_PAGE == 0){ 
       nowPage = nowPage - 1;
       if (nowPage < 1){
         nowPage = 1;
       }
     }
     
   }*/

   // redirect�ÿ��� request�� �����̾ȵ����� �Ʒ��� ����� �̿��Ͽ� ������ ����
   redirectAttributes.addAttribute("count", count); // 1 or 0
   redirectAttributes.addAttribute("questionno", questionVO.getQuestionno());
//   redirectAttributes.addAttribute("categoryno", contentsVO.getCategoryno());
//   redirectAttributes.addAttribute("question_word", question_word);
//   redirectAttributes.addAttribute("nowPage", nowPage);

   mav.setViewName("redirect:/question/delete_message.jsp");

   return mav;
 }
  
 /**
  * �˻� ���
  * 
  * @param questionno
  * @param question_word
  * @return
  */
 @RequestMapping(value = "/question/list_by_member_search.do", method = RequestMethod.GET)
 public ModelAndView list_by_category_search(int memberno, String question_word, HttpSession session) {
   // System.out.println("--> list_by_member(int memberno, String question_word) GET called.");
   ModelAndView mav = new ModelAndView();
   // mav.setViewName("/question/list_by_memberno"); //
   // webapp/question/list_by_memberno.jsp

   // �˻� ��� �߰�, webapp/question/list_by_member_search.jsp
   memberno = (Integer)session.getAttribute("memberno");
   MemberVO memberVO = memberProc.read(memberno);
   mav.addObject("memberVO", memberVO);
   if (memberProc.isMember(session) == false) {
     mav.setViewName("redirect:/member/login_need_g.jsp"); 
   } else { 
     mav.setViewName("/question/list_by_member_search");
   }
   

   // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
   HashMap<String, Object> hashMap = new HashMap<String, Object>();
   hashMap.put("memberno", memberno); // #{memberno}
   hashMap.put("question_word", question_word); // #{question_word}

    System.out.println("memberno: " + memberno);
    System.out.println("question_word: " + question_word);

   // �˻� ���
   List<QuestionVO> list = questionProc.list_by_member_search(hashMap);
   mav.addObject("list", list);

   // �˻��� ���ڵ� ����
   int search_count = questionProc.search_count(hashMap);
   mav.addObject("search_count", search_count);


   // mav.addObject("question_word", question_word);

   return mav;
 }
 
 /**
  * ��� + �˻� + ����¡ ����
  * @param memberno
  * @param question_word
  * @param nowPage
  * @return
  */
 @RequestMapping(value = "/question/list_by_member_search_paging.do", 
                                      method = RequestMethod.GET)
 public ModelAndView list_by_member_search_paging(HttpSession session,
     @RequestParam(value="memberno") int memberno,
     @RequestParam(value="question_word", defaultValue="") String question_word,
     @RequestParam(value="nowPage", defaultValue="1") int nowPage
     ) { 
   // System.out.println("--> list_by_category() GET called.");
   System.out.println("--> nowPage: " + nowPage);
   
   ModelAndView mav = new ModelAndView();
   
   // �˻� ��� �߰�,  /webapp/question/list_by_member_search_paging.jsp
   memberno = (Integer)session.getAttribute("memberno");
   MemberVO memberVO = memberProc.read(memberno);
   mav.addObject("memberVO", memberVO);
   if (memberProc.isMember(session) == false) {
     mav.setViewName("redirect:/member/login_need_g.jsp"); 
   } else { 
     mav.setViewName("/question/list_by_member_search_paging"); // webapp/question/create.jsp 
   }
   

   // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
   HashMap<String, Object> hashMap = new HashMap<String, Object>();
   hashMap.put("memberno", memberno); // #{memberno}
   hashMap.put("question_word", question_word);  // #{question_word}
   hashMap.put("nowPage", nowPage);       
   
   // �˻� ���
   List<QuestionVO> list = questionProc.list_by_member_search_paging(hashMap);
   
   if (memberProc.isMember(session) == false) {
     mav.setViewName("redirect:/member/login_need_g.jsp"); 
   } else { 
     mav.addObject("list", list);
   }
   
   // �˻��� ���ڵ� ����
   int search_count = questionProc.search_count(hashMap);
   mav.addObject("search_count", search_count);
   
   mav.addObject("question_word", question_word);
 
   /*
    * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
    * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
    *
    * @param categoryno ī�װ���ȣ 
    * @param search_count �˻�(��ü) ���ڵ�� 
    * @param nowPage     ���� ������
    * @param word �˻���
    * @return ����¡ ���� ���ڿ�
    */ 
   String paging = questionProc.paging(memberno, search_count, nowPage, question_word);
   mav.addObject("paging", paging);
 
   mav.addObject("nowPage", nowPage);
   
   return mav;
 }
 
 @RequestMapping(value="/question/reply.do", method=RequestMethod.GET)
 public ModelAndView reply(QuestionVO questionVO, HttpSession session){
   // System.out.println("--> reply() GET called.");
   ModelAndView mav = new ModelAndView();
   
   int memberno = (Integer)session.getAttribute("memberno");
   MemberVO memberVO = memberProc.read(memberno);
   mav.addObject("memberVO", memberVO);
   if (memberProc.isAdmin(session) == false) {
     mav.setViewName("redirect:/member/login_need.jsp"); 
   } else { 
     mav.setViewName("/question/reply"); // webapp/contents/reply.js
   }
   

   memberVO = memberProc.read(questionVO.getMemberno());
   mav.addObject("memberVO", memberVO);
   
   questionVO = questionProc.read(questionVO.getQuestionno());
   mav.addObject("questionVO", questionVO);

   return mav;
 }

 @RequestMapping(value="/question/reply.do", method=RequestMethod.POST)
 public ModelAndView reply(HttpServletRequest request, QuestionVO questionVO, int nowPage, HttpSession session){
   // System.out.println("--> create() POST called.");
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/question/message"); // webapp/blog/message.jsp
   
   ArrayList<String> msgs = new ArrayList<String>();
   ArrayList<String> links = new ArrayList<String>();

   // -------------------------------------------------------------------
   // ���� ���� �ڵ� ����
   // -------------------------------------------------------------------
   String upDir = Tool.getRealPath(request, "/question/storage");
   /*
   <input type="file" class="form-control input-lg" name='file1MF' id='file1MF' size='40' >
   ��
    name='file1MF'�� �ش��ϴ� �ʵ带 ã�Ƽ� File ��ü�� �ڵ����� �Ҵ�
   ��
   BlogVO.java: private MultipartFile file1MF;
   ��
    ���� ��ü ���: MultipartFile file1MF = blogVO.getFile1MF();          
    */
   MultipartFile file1MF = questionVO.getFile1MF();
   String question_file1 = "";
   long question_size1 = file1MF.getSize();
   String question_thumb = "";
   
   if (question_size1 > 0) {
     question_file1 = Upload.saveFileSpring(file1MF, upDir);
     
     if (Tool.isImage(question_file1)) {
       question_thumb = Tool.preview(upDir, question_file1, 120, 80); // Thumb �̹��� ����
     } 
   }
   questionVO.setQuestion_file1(question_file1);
   questionVO.setQuestion_size1(question_size1);
   questionVO.setQuestion_thumb(question_thumb);
   // ---------------------------------------------------------------------------
   
   // ȸ�� ���� �� session ���κ���
   int memberno = (Integer)session.getAttribute("memberno");
   
   String root = request.getContextPath();
   
   // --------------------------- �亯 ���� �ڵ� ���� --------------------------
   QuestionVO parentVO = questionProc.read(questionVO.getQuestionno()); // �θ�� ���� ����
   
   questionVO.setQuestion_grpno(parentVO.getQuestion_grpno());     // �׷� ��ȣ
   questionVO.setQuestion_ansnum(parentVO.getQuestion_ansnum()); // �亯 ����

   questionProc.update_question_Ansnum(questionVO); // ���� ��ϵ� �亯 �ڷ� +1 ó����.

   questionVO.setQuestion_indent(parentVO.getQuestion_indent() + 1); // �亯 ���� ����
   questionVO.setQuestion_ansnum(parentVO.getQuestion_ansnum() + 1); // �θ� �ٷ� �Ʒ� ���
   // --------------------------- �亯 ���� �ڵ� ���� --------------------------
   
   if (questionProc.reply(questionVO) == 1) {
     
/*     categoryProc.increaseCnt(categoryno); // �� �� ����
*/     
     msgs.add("�亯�� ����߽��ϴ�.");
     links.add("<button type='button' onclick=\"location.href='./create.do?memberno="+memberno+">��� ���</button>");
     
   } else {
     msgs.add("�亯 ��Ͽ� �����߽��ϴ�.");
     msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���. �� 000-0000-0000");
     links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
     
   }
   links.add("<button type='button' onclick=\"location.href='./list_by_member.do?nowPage="+nowPage+"'\">���</button>");
   links.add("<button type='button' onclick=\"location.href='"+root+"/home.do'\">Ȩ������</button>");
   
   mav.addObject("msgs", msgs);
   mav.addObject("links", links);
   
   return mav;
 }

  
}
