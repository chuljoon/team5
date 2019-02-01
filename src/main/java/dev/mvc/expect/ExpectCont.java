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
   * �����
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
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/expect/storage");
    MultipartFile filesMF = expectVO.getFilesMF();
    String imgs = "";
    long sizes = filesMF.getSize();
    String thumbs = ""; // �÷��� ������ ���ϸ�

    if (sizes > 0) {
      imgs = Upload.saveFileSpring(filesMF, upDir);

      if (Tool.isImage(imgs)) {
        thumbs = Tool.preview(upDir, imgs, 180, 254); // Thumb �̹��� ����
      }
    }
    expectVO.setImgs(imgs);
    expectVO.setSizes(sizes);
    expectVO.setThumbs(thumbs);
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
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
   * ���
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
   * ��ȸ
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
   * ����
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
    // ���� ����
    // ---------------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/expect/storage");

    MultipartFile filesMF = expectVO.getFilesMF();
    String imgs = ""; // DBMS file1 �÷��� ��
    long sizes = filesMF.getSize(); // ���� ũ��
    String thumbs = ""; // DBMS thumb �÷��� ��

    // ������ ��ϵ� �� ���� �ε�
    ExpectVO expectVO_old = expectProc.read(expectVO.getExpectno());

    if (sizes > 0) { // ��ϵ� ������ �ִٸ�
      Tool.deleteFile(upDir, expectVO_old.getImgs()); // ���� ���� ����
      Tool.deleteFile(upDir, expectVO_old.getThumbs()); // ���� Thumb ���� ����

      imgs = Upload.saveFileSpring(filesMF, upDir); // �ű� ���� ���ε�

      if (Tool.isImage(imgs)) { // ���ο� ���� �̹������� �˻�
        thumbs = Tool.preview(upDir, imgs, 180, 254); // Thumb �̹��� ����
      }
    } else {
      // ������ �������� �ʴ� ��� ���� ���� ���� ���
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
   * ����
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

    String upDir = Tool.getRealPath(request, "/expect/storage"); // ���� ���� ����
                                                                   // ���

    ExpectVO expectVO = expectProc.read(expectno); // ������ ���� ������ �б� ����
                                                           // ����

    String thumbs_old = expectVO.getThumbs();
    String imgs_old = expectVO.getImgs();

/*    StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/"); // Thumbs
    while (thumbs_st.hasMoreTokens()) { // �ܾ �ִ��� �˻�
      String fname = upDir + thumbs_st.nextToken(); // �ܾ� ����
      Tool.deleteFile(fname);
    }

    StringTokenizer imgs_st = new StringTokenizer(imgs_old, "/"); // files
    while (imgs_st.hasMoreTokens()) { // �ܾ �ִ��� �˻�
      String fname = upDir + imgs_st.nextToken(); // �ܾ� ����
      Tool.deleteFile(fname);
    } */

    int count = expectProc.delete(expectno); // ���ڵ� ����
    
/*    if (count == 1) {
      // 4���� ���ڵ尡 �ϳ��� �������ΰ�� 5��° ���ڵ尡 �����Ǹ� ����������
      // 2���������� 1 �������� �ٿ����մϴ�. 
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      if (contentsProc.cnt(hashMap) % Contents.RECORD_PER_PAGE == 0){ 
        nowPage = nowPage - 1;
        if (nowPage < 1){
          nowPage = 1;
        }
      }
    }*/

    // redirect�ÿ��� request�� �����̾ȵ����� �Ʒ��� ����� �̿��Ͽ� ������ ����
    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("contentsno", expectVO.getContentsno());
    redirectAttributes.addAttribute("expectno", expectVO.getExpectno());
/*    redirectAttributes.addAttribute("nowPage", nowPage);*/

    mav.setViewName("redirect:/expect/delete_message.jsp");

    return mav;
  }

}
