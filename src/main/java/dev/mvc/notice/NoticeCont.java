package dev.mvc.notice;

import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.notice.NoticeVO;

@Controller
public class NoticeCont {
  @Autowired
  @Qualifier("dev.mvc.notice.NoticeProc")
  private NoticeProcInter noticeProc = null;

  public NoticeCont() {
    System.out.println("--> NoticeCont created.");
  }

  /**
   * 등록
   * 
   * @return
   */
  @RequestMapping(value = "/notice/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("--> create() GET executed");
    ModelAndView mav = new ModelAndView();

    mav.setViewName("/notice/create");

    return mav;
  }

  @RequestMapping(value = "/notice/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, NoticeVO noticeVO) {
    // System.out.println("--> create() POST executed");
    ModelAndView mav = new ModelAndView();

    int count = noticeProc.create(noticeVO);

    if (count == 1) {
      mav.setViewName("redirect:/notice/create_message.jsp?count=" + count);
    }

    return mav;
  }

  /**
   * 목록
   * 
   * @return
   */
  @RequestMapping(value = "/notice/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();

    List<NoticeVO> list = noticeProc.list();
    mav.addObject("list", list);

    mav.setViewName("/notice/list");

    return mav;
  }

  /**
   * 조회
   * 
   * @param noticeno
   * @return
   */
  @RequestMapping(value = "/notice/read.do", method = RequestMethod.GET)
  public ModelAndView read(int noticeno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/notice/read");

    NoticeVO noticeVO = noticeProc.read(noticeno);
    mav.addObject("noticeVO", noticeVO);

    return mav;
  }

  /**
   * 수정
   * 
   * @param noticeno
   * @return
   */
  @RequestMapping(value = "/notice/update.do", method = RequestMethod.GET)
  public ModelAndView update(int noticeno) {
    System.out.println("--> update() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/notice/update");

    NoticeVO noticeVO = noticeProc.update(noticeno);
    mav.addObject("noticeVO", noticeVO);

    return mav;
  }

  @RequestMapping(value = "/notice/update.do", method = RequestMethod.POST)
  public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, NoticeVO noticeVO) {
    // System.out.println("--> update() POST called.");
    ModelAndView mav = new ModelAndView();

    int count = noticeProc.update(noticeVO);

    redirectAttributes.addAttribute("count", count);

    redirectAttributes.addAttribute("noticeno", noticeVO.getNoticeno());

    mav.setViewName("redirect:/notice/update_message.jsp");

    return mav;
  }

  /**
   * 삭제
   * 
   * @param noticeno
   * @return
   */
  @RequestMapping(value = "/notice/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int noticeno) {
    // System.out.println("--> delete() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/notice/delete"); // /webapp/notice/delete.jsp

    NoticeVO noticeVO = noticeProc.read(noticeno);
    mav.addObject("noticeVO", noticeVO);

    return mav;
  }

  @RequestMapping(value = "/notice/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(RedirectAttributes redirectAttributes, HttpServletRequest request, int noticeno) {
    ModelAndView mav = new ModelAndView();

    NoticeVO noticeVO = noticeProc.read(noticeno); // 삭제할 파일 정보를 읽기 위한
                                                   // 목적

    int count = noticeProc.delete(noticeno); // 레코드 삭제

    // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("noticeno", noticeVO.getNoticeno());

    mav.setViewName("redirect:/notice/delete_message.jsp");

    return mav;
  }

  /**
   * 목록 + 페이징 지원
   * @param nowPage
   * @return
   */
  @RequestMapping(value = "/notice/list_paging.do", method = RequestMethod.GET)
  public ModelAndView list_paging(
      @RequestParam(value = "nowPage", defaultValue = "1") int nowPage) {

    System.out.println("--> nowPage: " + nowPage);

    ModelAndView mav = new ModelAndView();

    mav.setViewName("/notice/list_paging");

    // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
    HashMap hashMap = new HashMap();
    hashMap.put("nowPage", nowPage);

    // 목록
    List<NoticeVO> list = noticeProc.list_paging(hashMap);
    mav.addObject("list", list);

    // 레코드 갯수
    int cnt = noticeProc.cnt(hashMap);
    mav.addObject("cnt", cnt);

    String paging = noticeProc.paging(cnt, nowPage);
    mav.addObject("paging", paging);

    mav.addObject("nowPage", nowPage);

    return mav;
  }

}
