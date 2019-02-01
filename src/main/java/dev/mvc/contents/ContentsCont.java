package dev.mvc.contents;

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

import dev.mvc.hall.HallProcInter;
import dev.mvc.hall.HallVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.sub_category.Categrp_CategoryVO;
import dev.mvc.sub_category.Sub_CategoryProcInter;
import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class ContentsCont {
  @Autowired
  @Qualifier("dev.mvc.sub_category.Sub_CategoryProc")
  private Sub_CategoryProcInter s_categoryProc = null;

  @Autowired
  @Qualifier("dev.mvc.contents.ContentsProc")
  private ContentsProcInter contentsProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.hall.HallProc")
  private HallProcInter hallProc = null;
  
  public ContentsCont() {
    System.out.println("--> ContentsCont created.");
  }

  /**
   * 등록 폼
   * 
   * @return
   */
  @RequestMapping(value = "/contents/create.do", method = RequestMethod.GET)
  public ModelAndView create(int s_categoryno, HttpSession session) {
    System.out.println("--> create() GET executed");
    ModelAndView mav = new ModelAndView();

    Categrp_CategoryVO categoryVO = s_categoryProc.read(s_categoryno);
    mav.addObject("categoryVO", categoryVO);
    
    int memberno = (Integer)session.getAttribute("memberno");
    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
//    System.out.println("m_act: " + memberVO.getM_act());
//    System.out.println("m_act: " + memberVO.getM_name());
    
    if (memberProc.isAdmin(session) == false) {
      mav.setViewName("/member/login_need.jsp");
    } else {
      mav.setViewName("/contents/create"); // /webapp/contents/create.jsp
    }
    return mav;
  }

  /**
   * 등록 처리
   * 
   * @param request
   * @param contentsVO
   * @return
   */
  @RequestMapping(value = "/contents/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, ContentsVO contentsVO, HttpSession session) {
    // System.out.println("--> create() POST executed");
    ModelAndView mav = new ModelAndView();
    contentsVO.setPasswd("1234"); // 개발중엔 1234로 통일

    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/contents/storage");
    MultipartFile title_imgMF = contentsVO.getTitle_imgMF();
    MultipartFile file1MF = contentsVO.getFile1MF();
    String title_img = ""; // 컬럼에 저장할 파일명
    String files = "";
    long sizes = title_imgMF.getSize();
    long file_size = file1MF.getSize();
    String thumbs = ""; // 컬럼에 저장할 파일명

    if (sizes > 0) {
      title_img = Upload.saveFileSpring(title_imgMF, upDir);

      if (Tool.isImage(title_img)) {
        thumbs = Tool.preview(upDir, title_img, 180, 254); // Thumb 이미지 생성
      }
    }
    contentsVO.setTitle_img(title_img);
    contentsVO.setSizes(sizes);
    contentsVO.setThumbs(thumbs);
    // System.out.println("title_img: "+ title_img);

    if (file_size > 0) {
      files = Upload.saveFileSpring(file1MF, upDir);
    }
    contentsVO.setFiles(files);
    contentsVO.setFile_size(file_size);
    // System.out.println("files: " + files);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------

    int count = contentsProc.create(contentsVO);

    if (count == 1) {
      mav.setViewName(
          "redirect:/contents/create_message.jsp?count=" + count + "&s_categoryno=" + contentsVO.getS_categoryno()); // /webapp/contents/create_message.jsp
    }

    return mav;
  }

  /**
   * 전체 목록
   * 
   * @return
   */
  @RequestMapping(value = "/contents/list_all_category.do", method = RequestMethod.GET)
  public ModelAndView list(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    int memberno = (Integer)session.getAttribute("memberno");
    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
    System.out.println("list_all_category에서의 memberno: " + memberVO.getMemberno());
    System.out.println("list_all_category에서의 m_act: " + memberVO.getM_act());
    
    List<ContentsVO> list = contentsProc.list_all_category();
    mav.addObject("list", list);

    mav.setViewName("/contents/list_all_category"); // /webapp/contents/list_all_category.jsp

    return mav;
  }

  /**
   * 카테고리별 목록
   * 
   * @param s_categoryno
   * @return
   */
  @RequestMapping(value = "/contents/list_by_category.do", method = RequestMethod.GET)
  public ModelAndView list_by_category(int s_categoryno) {
    ModelAndView mav = new ModelAndView();

    Categrp_CategoryVO categoryVO = s_categoryProc.read(s_categoryno);
    mav.addObject("categoryVO", categoryVO);

    List<ContentsVO> list = contentsProc.list_by_category(s_categoryno);
    mav.addObject("list", list);

    mav.setViewName("/contents/list_by_category"); // /webapp/contents/list_by_category.jsp

    return mav;
  }

  /**
   * 조회
   * 
   * @param contentsno
   * @return
   */
  @RequestMapping(value = "/contents/read.do", method = RequestMethod.GET)
  public ModelAndView read(int contentsno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/contents/read");

    ContentsVO contentsVO = contentsProc.read(contentsno);
    mav.addObject("contentsVO", contentsVO);

    Categrp_CategoryVO categoryVO = s_categoryProc.read(contentsVO.getS_categoryno()); // 카테고리
    // 정보
    // 추출
    mav.addObject("categoryVO", categoryVO);

    return mav;
  }

  /**
   * 수정
   * 
   * @param contentsno
   * @return
   */
  @RequestMapping(value = "/contents/update.do", method = RequestMethod.GET)
  public ModelAndView update(int contentsno, HttpSession session) {
    System.out.println("--> update() GET executed");
    ModelAndView mav = new ModelAndView();
    

    ContentsVO contentsVO = contentsProc.update(contentsno);
    mav.addObject("contentsVO", contentsVO);

    Categrp_CategoryVO categoryVO = s_categoryProc.read(contentsVO.getS_categoryno());
    mav.addObject("categoryVO", categoryVO);
    
    int memberno = (Integer)session.getAttribute("memberno");
    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
    if (memberProc.isAdmin(session) == false) {
      mav.setViewName("redirect:/member/login_need.jsp"); 
    } else { 
      mav.setViewName("/contents/update");
    }

    return mav;
  }

  @RequestMapping(value = "/contents/update.do", method = RequestMethod.POST)
  public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, ContentsVO contentsVO) {
    // System.out.println("--> update() POST called.");
    ModelAndView mav = new ModelAndView();

    // ---------------------------------------------------------------------------
    // 파일 전송
    // ---------------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/contents/storage");

    MultipartFile file1MF = contentsVO.getFile1MF();
    MultipartFile title_imgMF = contentsVO.getTitle_imgMF();
    String files = ""; // DBMS file1 컬럼의 값
    String title_img = "";
    long sizes = title_imgMF.getSize(); // 파일 크기
    long file_size = file1MF.getSize();
    String thumbs = ""; // DBMS thumb 컬럼의 값

    // 기존에 등록된 글 정보 로딩
    ContentsVO contentsVO_old = contentsProc.read(contentsVO.getContentsno());

    if (sizes > 0) { // 등록된 파일이 있다면
      Tool.deleteFile(upDir, contentsVO_old.getTitle_img()); // 기존 파일 삭제
      Tool.deleteFile(upDir, contentsVO_old.getThumbs()); // 기존 Thumb 파일 삭제

      title_img = Upload.saveFileSpring(title_imgMF, upDir); // 신규 파일 업로드

      if (Tool.isImage(title_img)) { // 새로운 파일 이미지인지 검사
        thumbs = Tool.preview(upDir, title_img, 180, 254); // Thumb 이미지 생성
      }
    } else {
      // 파일을 변경하지 않는 경우 기존 파일 정보 사용
      title_img = contentsVO_old.getTitle_img();
      sizes = contentsVO_old.getSizes();
      thumbs = contentsVO_old.getThumbs();
    }
    contentsVO.setTitle_img(title_img);
    contentsVO.setSizes(sizes);
    contentsVO.setThumbs(thumbs);

    if (file_size > 0) { // 등록된 파일이 있다면
      Tool.deleteFile(upDir, contentsVO_old.getFiles()); // 기존 파일 삭제

      files = Upload.saveFileSpring(file1MF, upDir); // 신규 파일 업로드

    } else {
      // 파일을 변경하지 않는 경우 기존 파일 정보 사용
      files = contentsVO_old.getFiles();
      file_size = contentsVO_old.getFile_size();
    }
    contentsVO.setFiles(files);
    contentsVO.setFile_size(file_size);
    // ---------------------------------------------------------------------------

    int count = contentsProc.update(contentsVO);

    redirectAttributes.addAttribute("count", count);

    redirectAttributes.addAttribute("contentsno", contentsVO.getContentsno());
    redirectAttributes.addAttribute("s_categoryno", contentsVO.getS_categoryno());

    mav.setViewName("redirect:/contents/update_message.jsp");

    return mav;
  }

  /**
   * 삭제
   * 
   * @param contentsno
   * @param s_categoryno
   * @return
   */
  @RequestMapping(value = "/contents/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int contentsno, int s_categoryno, HttpSession session) {
    // System.out.println("--> delete() GET executed");
    ModelAndView mav = new ModelAndView();
    

    Categrp_CategoryVO categoryVO = s_categoryProc.read(s_categoryno);
    mav.addObject("categoryVO", categoryVO);

    ContentsVO contentsVO = contentsProc.read(contentsno);
    mav.addObject("contentsVO", contentsVO);
    
    int memberno = (Integer)session.getAttribute("memberno");
    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);

    if (memberProc.isAdmin(session) == false) {
      mav.setViewName("redirect:/member/login_need.jsp"); 
    } else { 
      mav.setViewName("/contents/delete"); // /webapp/contents/delete.jsp
    }

    return mav;
  }

  @RequestMapping(value = "/contents/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(RedirectAttributes redirectAttributes, 
                                        HttpServletRequest request,
                                        int s_categoryno,
                                        int contentsno,
                                        @RequestParam(value="nowPage", defaultValue="1") int nowPage ) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/contents/delete_message"); // /webapp/contents/delete_message.jsp

    String upDir = Tool.getRealPath(request, "/contents/storage"); // 저장 폴더 절대
                                                                   // 경로

    ContentsVO contentsVO = contentsProc.read(contentsno); // 삭제할 파일 정보를 읽기 위한
                                                           // 목적

    String thumbs_old = contentsVO.getThumbs();
    String files_old = contentsVO.getFiles();
    String title_img_old = contentsVO.getTitle_img();

    StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/"); // Thumbs
    while (thumbs_st.hasMoreTokens()) { // 단어가 있는지 검사
      String fname = upDir + thumbs_st.nextToken(); // 단어 추출
      Tool.deleteFile(fname);
    }

    StringTokenizer files_st = new StringTokenizer(files_old, "/"); // files
    while (files_st.hasMoreTokens()) { // 단어가 있는지 검사
      String fname = upDir + files_st.nextToken(); // 단어 추출
      Tool.deleteFile(fname);
    }

    StringTokenizer title_img_st = new StringTokenizer(title_img_old, "/"); // files
    while (title_img_st.hasMoreTokens()) { // 단어가 있는지 검사
      String fname = upDir + title_img_st.nextToken(); // 단어 추출
      Tool.deleteFile(fname);
    }

    int count = contentsProc.delete(contentsno); // 레코드 삭제
    
/*    if (count == 1) {
      // 4개의 레코드가 하나의 페이지인경우 5번째 레코드가 삭제되면 페이지수도
      // 2페이지에서 1 페이지로 줄여야합니다. 
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      if (contentsProc.cnt(hashMap) % Contents.RECORD_PER_PAGE == 0){ 
        nowPage = nowPage - 1;
        if (nowPage < 1){
          nowPage = 1;
        }
      }
    }*/

    // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("contentsno", contentsVO.getContentsno());
    redirectAttributes.addAttribute("s_categoryno", contentsVO.getS_categoryno());
/*    redirectAttributes.addAttribute("nowPage", nowPage);*/

    mav.setViewName("redirect:/contents/delete_message.jsp");

    return mav;
  }

  /**
   * 목록 + 검색 + 페이징 지원
   * 
   * @param categoryno
   * @param nowPage
   * @return
   */
  @RequestMapping(value = "/contents/list_by_category_paging.do", method = RequestMethod.GET)
  public ModelAndView list_by_category_paging(HttpSession session,
      @RequestParam(value = "s_categoryno") int s_categoryno,
      @RequestParam(value="word", defaultValue="") String word,
      @RequestParam(value = "nowPage", defaultValue = "1") int nowPage) {
    // System.out.println("--> list_by_category() GET called.");
    System.out.println("--> nowPage: " + nowPage);

    ModelAndView mav = new ModelAndView();

    mav.setViewName("/contents/list_by_category_paging");

    // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("s_categoryno", s_categoryno);
    hashMap.put("word", word);                  // #{word}
    hashMap.put("nowPage", nowPage);

    // 목록
    List<ContentsVO> list = contentsProc.list_by_category_paging(hashMap);
    mav.addObject("list", list);

    // 레코드 갯수
    int search_count = contentsProc.search_count(hashMap);
    mav.addObject("search_count", search_count);
    
    int memberno = (Integer)session.getAttribute("memberno");
    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
    
    Categrp_CategoryVO categoryVO = s_categoryProc.read(s_categoryno);
    mav.addObject("categoryVO", categoryVO);

    String paging = contentsProc.paging(s_categoryno, search_count, nowPage, word);
    mav.addObject("paging", paging);

    mav.addObject("nowPage", nowPage);

    return mav;
  }

}
