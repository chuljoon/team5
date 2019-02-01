package dev.mvc.expect;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.contents.ContentsProcInter;
import dev.mvc.contents.ContentsVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.sub_category.Categrp_CategoryVO;
import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class ExpectCont {
  @Autowired
  @Qualifier("dev.mvc.contents.ContentsProc")
  private ContentsProcInter contentsProc = null;

  @Autowired
  @Qualifier("dev.mvc.expect.ExpectProc")
  private ExpectProc expectProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc = null;

  public ExpectCont() {
    System.out.println("--> ExpectCont created.");
  }

  /**
   * 등록폼
   * 
   * @param contentsno
   * @return
   */
  @RequestMapping(value = "/expect/create.do", method = RequestMethod.GET)
  public ModelAndView create(int contentsno, HttpSession session) {
    System.out.println("--> create() GET executed");
    ModelAndView mav = new ModelAndView();

    ContentsVO contentsVO = contentsProc.read(contentsno);
    mav.addObject("contentsVO", contentsVO);
    
    int memberno = (Integer)session.getAttribute("memberno");
    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
    if (memberProc.isMember(session) == false) {
      mav.setViewName("redirect:/member/login_need_g.jsp"); 
    } else { 
      mav.setViewName("/expect/create"); // /webapp/contents/create.jsp
    }

    

    return mav;
  }

  @RequestMapping(value = "/expect/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, ExpectVO expectVO, HttpSession session) {
    // System.out.println("--> create() POST executed");
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/expect/storage");
    MultipartFile filesMF = expectVO.getFilesMF();
    String imgs = "";
    long sizes = filesMF.getSize();
    String thumbs = ""; // 컬럼에 저장할 파일명

    if (sizes > 0) {
      imgs = Upload.saveFileSpring(filesMF, upDir);

      if (Tool.isImage(imgs)) {
        thumbs = Tool.preview(upDir, imgs, 180, 254); // Thumb 이미지 생성
      }
    }
    expectVO.setImgs(imgs);
    expectVO.setSizes(sizes);
    expectVO.setThumbs(thumbs);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // ------------------------------------------------------------------- 

    int memberno = (Integer)session.getAttribute("memberno");
    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
    if (memberProc.isMember(session) == false) {
      mav.setViewName("redirect:/member/login_need_g.jsp"); 
    } else { 
      int count = expectProc.create(expectVO);
      mav.setViewName("redirect:/expect/create_message.jsp?count=" + count + "&contentsno=" + expectVO.getContentsno());
    }
    
    return mav;
  }

  /**
   * 목록
   * @return
   */
  @RequestMapping(value = "/expect/list.do", method = RequestMethod.GET)
  public ModelAndView list(int contentsno) {
    ModelAndView mav = new ModelAndView();
    
    ContentsVO contentsVO = contentsProc.read(contentsno);
    mav.addObject("contentsVO", contentsVO);

    List<ExpectVO> list = expectProc.list(contentsno);
    mav.addObject("list", list);

    mav.setViewName("/expect/list");

    return mav;
  }
  
  /**
   * 조회
   * @param expectno
   * @return
   */
  @RequestMapping(value = "/expect/read.do", method = RequestMethod.GET)
  public ModelAndView read(int expectno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/expect/read");
    
    ExpectVO expectVO = expectProc.read(expectno);
    mav.addObject("expectVO", expectVO);

    return mav;
  }
  
  /**
   * 수정
   * @param expectno
   * @return
   */
  @RequestMapping(value = "/expect/update.do", method = RequestMethod.GET)
  public ModelAndView update(int expectno) {
    System.out.println("--> update() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/expect/update");

    ExpectVO expectVO = expectProc.update(expectno);
    mav.addObject("expectVO", expectVO);
    
    ContentsVO contentsVO = contentsProc.read(expectVO.getContentsno());
    mav.addObject("contentsVO", contentsVO);

    return mav;
  }
  
  @RequestMapping(value = "/expect/update.do", method = RequestMethod.POST)
  public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, ExpectVO expectVO) {
    // System.out.println("--> update() POST called.");
    ModelAndView mav = new ModelAndView();

    // ---------------------------------------------------------------------------
    // 파일 전송
    // ---------------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/expect/storage");

    MultipartFile filesMF = expectVO.getFilesMF();
    String imgs = ""; // DBMS file1 컬럼의 값
    long sizes = filesMF.getSize(); // 파일 크기
    String thumbs = ""; // DBMS thumb 컬럼의 값

    // 기존에 등록된 글 정보 로딩
    ExpectVO expectVO_old = expectProc.read(expectVO.getExpectno());

    if (sizes > 0) { // 등록된 파일이 있다면
      Tool.deleteFile(upDir, expectVO_old.getImgs()); // 기존 파일 삭제
      Tool.deleteFile(upDir, expectVO_old.getThumbs()); // 기존 Thumb 파일 삭제

      imgs = Upload.saveFileSpring(filesMF, upDir); // 신규 파일 업로드

      if (Tool.isImage(imgs)) { // 새로운 파일 이미지인지 검사
        thumbs = Tool.preview(upDir, imgs, 180, 254); // Thumb 이미지 생성
      }
    } else {
      // 파일을 변경하지 않는 경우 기존 파일 정보 사용
      imgs = expectVO_old.getImgs();
      sizes = expectVO_old.getSizes();
      thumbs = expectVO_old.getThumbs();
    }
    expectVO.setImgs(imgs);
    expectVO.setSizes(sizes);
    expectVO.setThumbs(thumbs);

    // ---------------------------------------------------------------------------

    int count = expectProc.update(expectVO);

    redirectAttributes.addAttribute("count", count);

    redirectAttributes.addAttribute("expectno", expectVO.getExpectno());
    redirectAttributes.addAttribute("contentsno", expectVO.getContentsno());

    mav.setViewName("redirect:/expect/update_message.jsp");

    return mav;
  }

  /**
   * 삭제
   * @param expectno
   * @param contentsno
   * @return
   */
  @RequestMapping(value = "/expect/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int expectno, int contentsno) {
    // System.out.println("--> delete() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/expect/delete"); // /webapp/contents/delete.jsp

    ExpectVO expectVO = expectProc.read(expectno);
    mav.addObject("expectVO", expectVO);

    ContentsVO contentsVO = contentsProc.read(contentsno);
    mav.addObject("contentsVO", contentsVO);

    return mav;
  }
  
  @RequestMapping(value = "/expect/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(RedirectAttributes redirectAttributes, 
                                        HttpServletRequest request,
                                        int expectno,
                                        int contentsno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/expect/delete_message"); // /webapp/contents/delete_message.jsp

    String upDir = Tool.getRealPath(request, "/expect/storage"); // 저장 폴더 절대
                                                                   // 경로

    ExpectVO expectVO = expectProc.read(expectno); // 삭제할 파일 정보를 읽기 위한
                                                           // 목적

    String thumbs_old = expectVO.getThumbs();
    String imgs_old = expectVO.getImgs();

/*    StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/"); // Thumbs
    while (thumbs_st.hasMoreTokens()) { // 단어가 있는지 검사
      String fname = upDir + thumbs_st.nextToken(); // 단어 추출
      Tool.deleteFile(fname);
    }

    StringTokenizer imgs_st = new StringTokenizer(imgs_old, "/"); // files
    while (imgs_st.hasMoreTokens()) { // 단어가 있는지 검사
      String fname = upDir + imgs_st.nextToken(); // 단어 추출
      Tool.deleteFile(fname);
    } */

    int count = expectProc.delete(expectno); // 레코드 삭제
    
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
    redirectAttributes.addAttribute("contentsno", expectVO.getContentsno());
    redirectAttributes.addAttribute("expectno", expectVO.getExpectno());
/*    redirectAttributes.addAttribute("nowPage", nowPage);*/

    mav.setViewName("redirect:/expect/delete_message.jsp");

    return mav;
  }

}
