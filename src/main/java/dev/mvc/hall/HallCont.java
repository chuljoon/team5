package dev.mvc.hall;

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
import dev.mvc.sub_category.Categrp_CategoryVO;
import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class HallCont {
  @Autowired
  @Qualifier("dev.mvc.hall.HallProc")
  private HallProcInter hallProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc = null;

  public HallCont() {
    System.out.println("--> HallCont created.");
  }

  /**
   * 등록 폼
   * 
   * @return
   */
  @RequestMapping(value = "/hall/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session) {
    System.out.println("--> create() GET executed");
    ModelAndView mav = new ModelAndView();
    
    int memberno = (Integer)session.getAttribute("memberno");
    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
    if (memberProc.isAdmin(session) == false) {
      mav.setViewName("redirect:/member/login_need.jsp"); 
    } else { 
      mav.setViewName("/hall/create"); // /webapp/hall/create.jsp
    }
    

    return mav;
  }

  @RequestMapping(value = "/hall/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, HallVO hallVO) {
    System.out.println("--> create() POST executed");
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/hall/storage");
    MultipartFile file1MF = hallVO.getFile1MF();
    String img = ""; // 컬럼에 저장할 파일명
    long sizes = file1MF.getSize();
    String thumbs = ""; // 컬럼에 저장할 파일명

    if (sizes > 0) {
      img = Upload.saveFileSpring(file1MF, upDir);

      if (Tool.isImage(img)) {
        thumbs = Tool.preview(upDir, img, 178, 133); // Thumb 이미지 생성
      }
    }
    hallVO.setImg(img);
    hallVO.setSizes(sizes);
    hallVO.setThumbs(thumbs);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------

    int count = hallProc.create(hallVO);

    if (count == 1) {
      mav.setViewName("redirect:/hall/create_message.jsp?count=" + count);
    }

    return mav;
  }

  /**
   * 목록
   * @return
   */
  @RequestMapping(value = "/hall/list.do", method = RequestMethod.GET)
  public ModelAndView list_all_category() {
    System.out.println("--> list() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/hall/list");

    List<HallVO> list = hallProc.list();
    mav.addObject("list", list);

    return mav;
  }

  /**
   * 조회
   * @param hallno
   * @return
   */
  @RequestMapping(value = "/hall/read.do", method = RequestMethod.GET)
  public ModelAndView read(int hallno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/hall/read");

    HallVO hallVO = hallProc.read(hallno);
    mav.addObject("hallVO", hallVO);
    
    String map = Tool.checkNull(hallVO.getMap());
    if (map.trim().length() > 0) {
      mav.addObject("map", map);
    }

    return mav;
  }
  
  /**
   * 수정
   * @param hallno
   * @return
   */
  @RequestMapping(value = "/hall/update.do", method = RequestMethod.GET)
  public ModelAndView update(int hallno, HttpSession session) {
    System.out.println("--> update() GET executed");
    ModelAndView mav = new ModelAndView();
    int memberno = (Integer)session.getAttribute("memberno");
    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
    if (memberProc.isAdmin(session) == false) {
      mav.setViewName("redirect:/member/login_need.jsp"); 
    } else { 
      mav.setViewName("/hall/update"); // /webapp/hall/update.jsp
    }
    

    HallVO hallVO = hallProc.update(hallno);
    mav.addObject("hallVO", hallVO);
    
    return mav;
  }
  
  @RequestMapping(value="/hall/update.do", method=RequestMethod.POST)
  public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, HallVO hallVO){
    ModelAndView mav = new ModelAndView();

    // ---------------------------------------------------------------------------
    // 파일 전송
    // ---------------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/hall/storage");

    MultipartFile file1MF = hallVO.getFile1MF();
    String img = "";                    // DBMS file1 컬럼의 값
    long sizes = file1MF.getSize(); // 파일 크기
    String thumbs = "";                 // DBMS thumb 컬럼의 값
    
    // 기존에 등록된 글 정보 로딩
    HallVO hallVO_old = hallProc.read(hallVO.getHallno());
    
    if (sizes > 0) { // 등록된 파일이 있다면
      Tool.deleteFile(upDir, hallVO_old.getImg());    // 기존 파일 삭제
      Tool.deleteFile(upDir, hallVO_old.getThumbs()); // 기존 Thumb 파일 삭제
      
      img = Upload.saveFileSpring(file1MF, upDir); // 신규 파일 업로드
      
      if (Tool.isImage(img)) { // 새로운 파일 이미지인지 검사
        thumbs = Tool.preview(upDir, img, 178, 133); // Thumb 이미지 생성
      } 
    } else {
      // 파일을 변경하지 않는 경우 기존 파일 정보 사용
      img = hallVO_old.getImg();
      sizes = hallVO_old.getSizes();
      thumbs = hallVO_old.getThumbs();
    }
    hallVO.setImg(img);
    hallVO.setSizes(sizes);
    hallVO.setThumbs(thumbs);
    // ---------------------------------------------------------------------------
    
    int count = hallProc.update(hallVO);
    
    redirectAttributes.addAttribute("count", count);
    
    redirectAttributes.addAttribute("hallno", hallVO.getHallno());
    
    mav.setViewName("redirect:/hall/update_message.jsp");
   
    return mav;
  }
  
  @RequestMapping(value = "/hall/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int hallno, HttpSession session) {
    // System.out.println("--> delete() GET executed");
    ModelAndView mav = new ModelAndView();
    int memberno = (Integer)session.getAttribute("memberno");
    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
    if (memberProc.isAdmin(session) == false) {
      mav.setViewName("redirect:/member/login_need.jsp"); 
    } else { 
      mav.setViewName("/hall/delete");
    }
   

    HallVO hallVO = hallProc.read(hallno);
    mav.addObject("hallVO", hallVO);

    return mav;
  }
  
  @RequestMapping(value = "/hall/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(RedirectAttributes redirectAttributes, 
                                        HttpServletRequest request,
                                        int hallno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/hall/delete_message");

    String upDir = Tool.getRealPath(request, "/hall/storage"); // 저장 폴더 절대
                                                                   // 경로

    HallVO hallVO = hallProc.read(hallno); // 삭제할 파일 정보를 읽기 위한
                                                           // 목적

    String thumbs_old = hallVO.getThumbs();
    String img_old = hallVO.getImg();

    StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/"); // Thumbs
    while (thumbs_st.hasMoreTokens()) { // 단어가 있는지 검사
      String fname = upDir + thumbs_st.nextToken(); // 단어 추출
      Tool.deleteFile(fname);
    }

    StringTokenizer files_st = new StringTokenizer(img_old, "/"); // files
    while (files_st.hasMoreTokens()) { // 단어가 있는지 검사
      String fname = upDir + files_st.nextToken(); // 단어 추출
      Tool.deleteFile(fname);
    }

    int count = hallProc.delete(hallno); // 레코드 삭제
    
    // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("hallno", hallVO.getHallno());

    mav.setViewName("redirect:/hall/delete_message.jsp");

    return mav;
  }


}
