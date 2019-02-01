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
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/question/storage");
    MultipartFile file1MF = questionVO.getFile1MF();
    String question_file1 = ""; // 컬럼에 저장할 파일명
    long question_size1 = file1MF.getSize();
    String question_thumb = ""; // 컬럼에 저장할 파일명

    if (question_size1 > 0) {
      question_file1 = Upload.saveFileSpring(file1MF, upDir);

      if (Tool.isImage(question_file1)) {
        question_thumb = Tool.preview(upDir, question_file1, 120, 80); // Thumb 이미지 생성
      }
    }
    questionVO.setQuestion_file1(question_file1);
    questionVO.setQuestion_size1(question_size1); 
    questionVO.setQuestion_thumb(question_thumb);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------

    if (questionProc.create(questionVO) == 1) {
      msgs.add("[질문 등록 성공]");
      msgs.add("질문을 등록했습니다.");
    } else {
      msgs.add("[질문 등록 실패]");
      msgs.add("질문 등록에 실패했습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요. ☏ 전산 운영팀: 000-0000-0000");
      links.add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
    }
    // links.add("<button type='button' onclick=\"location.href='./list_by_categoryno.do?categoryno="+blogVO.getCategoryno()+"'\">목록</button>");

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
   // 파일 전송
   // ---------------------------------------------------------------------------
   String upDir = Tool.getRealPath(request, "/question/storage");
   MultipartFile file1MF = questionVO.getFile1MF();
   String question_file1 = ""; // 컬럼에 저장할 파일명
   long question_size1 = file1MF.getSize();
   String question_thumb = ""; // 컬럼에 저장할 파일명
   
   // 기존에 등록된 글 정보 로딩
   QuestionVO questionVO_old = questionProc.read(questionVO.getQuestionno());
   
   if (question_size1 > 0) { // 등록된 파일이 있다면
     Tool.deleteFile(upDir, questionVO_old.getQuestion_file1());    // 기존 파일 삭제
     Tool.deleteFile(upDir, questionVO_old.getQuestion_thumb()); // 기존 Thumb 파일 삭제
     
     question_file1 = Upload.saveFileSpring(file1MF, upDir); // 신규 파일 업로드
     
     if (Tool.isImage(question_file1)) { // 새로운 파일 이미지인지 검사
       question_thumb = Tool.preview(upDir, question_file1, 120, 80); // Thumb 이미지 생성
     } 
   } else {
     // 파일을 변경하지 않는 경우 기존 파일 정보 사용
     question_file1 = questionVO_old.getQuestion_file1();
     question_size1 = questionVO_old.getQuestion_size1();
     question_thumb = questionVO_old.getQuestion_thumb();
   }
   questionVO.setQuestion_file1(question_file1);
   questionVO.setQuestion_size1(question_size1);
   questionVO.setQuestion_thumb(question_thumb);
   // ---------------------------------------------------------------------------
   
   // 회원 개발 후 session 으로변경
   int memberno = (Integer)session.getAttribute("memberno");
   questionVO.setMemberno(memberno); 

   int count = questionProc.update(questionVO);

   redirectAttributes.addAttribute("count", count);

   // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
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

   String upDir = Tool.getRealPath(request, "/question/storage"); // 저장 폴더 절대
                                                                  // 경로

   QuestionVO questionVO = questionProc.read(questionno); // 삭제할 파일 정보를 읽기 위한
                                                          // 목적

   String question_thumb_old = questionVO.getQuestion_thumb();
   String question_file1_old = questionVO.getQuestion_file1();

   StringTokenizer question_thumb_st = new StringTokenizer(question_thumb_old, "/"); // Thumbs
   while (question_thumb_st.hasMoreTokens()) { // 단어가 있는지 검사
     String fname = upDir + question_thumb_st.nextToken(); // 단어 추출
     Tool.deleteFile(fname);
   }

   StringTokenizer question_file1_st = new StringTokenizer(question_file1_old, "/"); // files
   while (question_file1_st.hasMoreTokens()) { // 단어가 있는지 검사
     String fname = upDir + question_file1_st.nextToken(); // 단어 추출
     Tool.deleteFile(fname);
   }

   int count = questionProc.delete(questionno); // 레코드 삭제

   /*if (count == 1) {
     categoryProc.decreaseCnt(categoryno); // 등록된 글수의 감소
     
     // 4개의 레코드가 하나의 페이지인경우 5번째 레코드가 삭제되면 페이지수도
     // 2페이지에서 1 페이지로 줄여야합니다. 
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

   // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
   redirectAttributes.addAttribute("count", count); // 1 or 0
   redirectAttributes.addAttribute("questionno", questionVO.getQuestionno());
//   redirectAttributes.addAttribute("categoryno", contentsVO.getCategoryno());
//   redirectAttributes.addAttribute("question_word", question_word);
//   redirectAttributes.addAttribute("nowPage", nowPage);

   mav.setViewName("redirect:/question/delete_message.jsp");

   return mav;
 }
  
 /**
  * 검색 목록
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

   // 검색 기능 추가, webapp/question/list_by_member_search.jsp
   memberno = (Integer)session.getAttribute("memberno");
   MemberVO memberVO = memberProc.read(memberno);
   mav.addObject("memberVO", memberVO);
   if (memberProc.isMember(session) == false) {
     mav.setViewName("redirect:/member/login_need_g.jsp"); 
   } else { 
     mav.setViewName("/question/list_by_member_search");
   }
   

   // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
   HashMap<String, Object> hashMap = new HashMap<String, Object>();
   hashMap.put("memberno", memberno); // #{memberno}
   hashMap.put("question_word", question_word); // #{question_word}

    System.out.println("memberno: " + memberno);
    System.out.println("question_word: " + question_word);

   // 검색 목록
   List<QuestionVO> list = questionProc.list_by_member_search(hashMap);
   mav.addObject("list", list);

   // 검색된 레코드 갯수
   int search_count = questionProc.search_count(hashMap);
   mav.addObject("search_count", search_count);


   // mav.addObject("question_word", question_word);

   return mav;
 }
 
 /**
  * 목록 + 검색 + 페이징 지원
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
   
   // 검색 기능 추가,  /webapp/question/list_by_member_search_paging.jsp
   memberno = (Integer)session.getAttribute("memberno");
   MemberVO memberVO = memberProc.read(memberno);
   mav.addObject("memberVO", memberVO);
   if (memberProc.isMember(session) == false) {
     mav.setViewName("redirect:/member/login_need_g.jsp"); 
   } else { 
     mav.setViewName("/question/list_by_member_search_paging"); // webapp/question/create.jsp 
   }
   

   // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
   HashMap<String, Object> hashMap = new HashMap<String, Object>();
   hashMap.put("memberno", memberno); // #{memberno}
   hashMap.put("question_word", question_word);  // #{question_word}
   hashMap.put("nowPage", nowPage);       
   
   // 검색 목록
   List<QuestionVO> list = questionProc.list_by_member_search_paging(hashMap);
   
   if (memberProc.isMember(session) == false) {
     mav.setViewName("redirect:/member/login_need_g.jsp"); 
   } else { 
     mav.addObject("list", list);
   }
   
   // 검색된 레코드 갯수
   int search_count = questionProc.search_count(hashMap);
   mav.addObject("search_count", search_count);
   
   mav.addObject("question_word", question_word);
 
   /*
    * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
    * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
    *
    * @param categoryno 카테고리번호 
    * @param search_count 검색(전체) 레코드수 
    * @param nowPage     현재 페이지
    * @param word 검색어
    * @return 페이징 생성 문자열
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
   // 파일 전송 코드 시작
   // -------------------------------------------------------------------
   String upDir = Tool.getRealPath(request, "/question/storage");
   /*
   <input type="file" class="form-control input-lg" name='file1MF' id='file1MF' size='40' >
   ↓
    name='file1MF'에 해당하는 필드를 찾아서 File 객체를 자동으로 할당
   ↓
   BlogVO.java: private MultipartFile file1MF;
   ↓
    파일 객체 사용: MultipartFile file1MF = blogVO.getFile1MF();          
    */
   MultipartFile file1MF = questionVO.getFile1MF();
   String question_file1 = "";
   long question_size1 = file1MF.getSize();
   String question_thumb = "";
   
   if (question_size1 > 0) {
     question_file1 = Upload.saveFileSpring(file1MF, upDir);
     
     if (Tool.isImage(question_file1)) {
       question_thumb = Tool.preview(upDir, question_file1, 120, 80); // Thumb 이미지 생성
     } 
   }
   questionVO.setQuestion_file1(question_file1);
   questionVO.setQuestion_size1(question_size1);
   questionVO.setQuestion_thumb(question_thumb);
   // ---------------------------------------------------------------------------
   
   // 회원 개발 후 session 으로변경
   int memberno = (Integer)session.getAttribute("memberno");
   
   String root = request.getContextPath();
   
   // --------------------------- 답변 관련 코드 시작 --------------------------
   QuestionVO parentVO = questionProc.read(questionVO.getQuestionno()); // 부모글 정보 추출
   
   questionVO.setQuestion_grpno(parentVO.getQuestion_grpno());     // 그룹 번호
   questionVO.setQuestion_ansnum(parentVO.getQuestion_ansnum()); // 답변 순서

   questionProc.update_question_Ansnum(questionVO); // 현재 등록된 답변 뒤로 +1 처리함.

   questionVO.setQuestion_indent(parentVO.getQuestion_indent() + 1); // 답변 차수 증가
   questionVO.setQuestion_ansnum(parentVO.getQuestion_ansnum() + 1); // 부모 바로 아래 등록
   // --------------------------- 답변 관련 코드 종료 --------------------------
   
   if (questionProc.reply(questionVO) == 1) {
     
/*     categoryProc.increaseCnt(categoryno); // 글 수 증가
*/     
     msgs.add("답변을 등록했습니다.");
     links.add("<button type='button' onclick=\"location.href='./create.do?memberno="+memberno+">계속 등록</button>");
     
   } else {
     msgs.add("답변 등록에 실패했습니다.");
     msgs.add("죄송하지만 다시한번 시도해주세요. ☏ 000-0000-0000");
     links.add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
     
   }
   links.add("<button type='button' onclick=\"location.href='./list_by_member.do?nowPage="+nowPage+"'\">목록</button>");
   links.add("<button type='button' onclick=\"location.href='"+root+"/home.do'\">홈페이지</button>");
   
   mav.addObject("msgs", msgs);
   mav.addObject("links", links);
   
   return mav;
 }

  
}
