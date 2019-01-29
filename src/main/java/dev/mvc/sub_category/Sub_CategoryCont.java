package dev.mvc.sub_category;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.main_categrp.Main_CategrpProcInter;
import dev.mvc.main_categrp.Main_CategrpVO;

@Controller
public class Sub_CategoryCont {
  @Autowired
  @Qualifier("dev.mvc.sub_category.Sub_CategoryProc")
  private Sub_CategoryProcInter s_categoryProc = null;

  @Autowired
  @Qualifier("dev.mvc.main_categrp.Main_CategrpProc")
  private Main_CategrpProcInter m_categrpProc = null;

  public Sub_CategoryCont() {
    System.out.println("--> Sub_CategoryCont created.");
  }

  /**
   * ���
   * 
   * @return
   */
  @RequestMapping(value = "/sub_category/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/sub_category/create"); // /webapp/category/create.jsp

    return mav;
  }

  @RequestMapping(value = "/sub_category/create.do", method = RequestMethod.POST)
  public ModelAndView create(Sub_CategoryVO s_categoryVO) {
    ModelAndView mav = new ModelAndView();

    int count = s_categoryProc.create(s_categoryVO);
    mav.setViewName("redirect:/sub_category/create_message.jsp?count=" + count);

    return mav;
  }

  @ResponseBody
  @RequestMapping(value = "/sub_category/create_json.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public ResponseEntity create_json(Sub_CategoryVO s_categoryVO) {
    HttpHeaders responseHeaders = new HttpHeaders();

    JSONObject json = new JSONObject();
    JSONArray msgs = new JSONArray();

    if (s_categoryProc.create(s_categoryVO) == 1) {
      msgs.put("ī�װ��� ����߽��ϴ�.");
      msgs.put("��ϵ� ī�װ� " + s_categoryVO.getTitle());
    } else {
      msgs.put("ī�װ� ��Ͽ� �����߽��ϴ�.");
      msgs.put("�ٽ��ѹ� �õ����ּ���. �� ����: 000-0000-0000");
    }
    json.put("msgs", msgs);

    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }

  /**
   * ��ü ���
   * 
   * @return
   */

  @RequestMapping(value = "/sub_category/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();

    List<Main_CategrpVO> categrp_list = m_categrpProc.list();
    mav.addObject("categrp_list", categrp_list);
    
    mav.setViewName("/sub_category/list"); // /webapp/category/list.jsp

    return mav;
  }

  /**
   * JSON ��� ��ü ���
   * 
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/sub_category/list_json.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public ResponseEntity list_json() {
    HttpHeaders responseHeaders = new HttpHeaders();
    List<Categrp_CategoryVO> list = s_categoryProc.list();

    JSONArray json = new JSONArray(list);

    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }

  /**
   * ī�װ� �׷캰 ��ü ���
   * 
   * @param m_categrpno
   * @return
   */
  @RequestMapping(value = "/sub_category/list_by_categrp.do", method = RequestMethod.GET)
  public ModelAndView list_by_categrp(int m_categrpno) {
    ModelAndView mav = new ModelAndView();
    
    List<Main_CategrpVO> categrp_list = m_categrpProc.list();
    mav.addObject("categrp_list", categrp_list);

    Main_CategrpVO categrpVO = m_categrpProc.read(m_categrpno);
    mav.addObject("categrpVO", categrpVO);

    mav.setViewName("/sub_category/list_by_categrp"); // /webapp/category/list_by_categrp.jsp

    return mav;
  }

  @ResponseBody
  @RequestMapping(value = "/sub_category/list_by_categrp_json.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public ResponseEntity list_by_categrp_json(int m_categrpno) {
    HttpHeaders responseHeaders = new HttpHeaders();
    
    List<Categrp_CategoryVO> list = s_categoryProc.list_by_categrp(m_categrpno);

    JSONArray json = new JSONArray(list);

    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }

  /**
   * ī�װ� �׷� �� ī�װ� ��� ���� ���
   * 
   * @param request
   *          ���� ��ο� ��ü
   * @return
   */
  @RequestMapping(value = "/sub_category/list_index.do", method = RequestMethod.GET)
  public ModelAndView list_index(HttpServletRequest request) {
    // System.out.println("--> list_index() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/sub_category/list_index"); // webapp/category/list_index.jsp

    List<Main_CategrpVO> categrp_list = m_categrpProc.list(); // ī�װ� �׷� ���

    // Categrp: name, Category: title ���� ���
    ArrayList<String> name_title_list = new ArrayList<String>();

    StringBuffer url = new StringBuffer(); // ī�װ� ���� ��ũ ����

    // ī�װ� �׷� ������ŭ ��ȯ
    for (int index = 0; index < categrp_list.size(); index++) {
      Main_CategrpVO m_categrpVO = categrp_list.get(index);

      name_title_list.add("<LI class='categrp_name'>" + m_categrpVO.getName() + "</LI>");

      // ī�װ� �׷캰 ī�װ� Join ���
      List<Categrp_CategoryVO> category_list = s_categoryProc.list_by_categrp(m_categrpVO.getM_categrpno());

      // ī�װ� ������ŭ ��ȯ
      for (int j = 0; j < category_list.size(); j++) {
        Categrp_CategoryVO categrp_CategoryVO = category_list.get(j);
        String title = categrp_CategoryVO.getTitle(); // ī�װ� �̸�
        int cnt = categrp_CategoryVO.getCnt();

        url.append("<LI class='category_title'>");
        url.append("  <A href='" + request.getContextPath() + "/contents/list_by_category.do?categoryno="
            + categrp_CategoryVO.getS_categoryno() + "'>");
        url.append(title);
        url.append("  </A>");
        url.append("  <span style='font-size: 0.9em; color: #555555;'>(" + cnt + ")</span>");
        url.append("</LI>");
        name_title_list.add(url.toString()); // ��� ��Ͽ� �ϳ��� category �߰�

        url.delete(0, url.toString().length()); // StringBuffer ���ڿ� ����

      }
    }

    mav.addObject("name_title_list", name_title_list);
    // mav.addObject("total_count", contentsProc.total_count());

    return mav;
  }

  /**
   * ����
   * 
   * @param categoryno
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/sub_category/update.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public String update(int s_categoryno) {

    Categrp_CategoryVO categoryVO = s_categoryProc.read(s_categoryno);
    JSONObject obj = new JSONObject(categoryVO);

    return obj.toString();
  }

  // http://localhost:9090/ojt/category/update_json.do?categrpno=1&categoryno=1&title=�״�����&seqno=1&visible=Y&ids=admin
  // {"msgs":["ī�װ��� ����߽��ϴ�.","��ϵ� ī�װ� ���"]}
  @ResponseBody
  @RequestMapping(value = "/sub_category/update_json.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public ResponseEntity update_json(Sub_CategoryVO s_categoryVO) {
    HttpHeaders responseHeaders = new HttpHeaders();

    JSONObject json = new JSONObject();
    JSONArray msgs = new JSONArray();

    if (s_categoryProc.update(s_categoryVO) == 1) {
      msgs.put("ī�װ��� �����߽��ϴ�.");
      msgs.put("������ ī�װ� " + s_categoryVO.getTitle());
    } else {
      msgs.put("ī�װ� ������ �����߽��ϴ�.");
      msgs.put("�ٽ��ѹ� �õ����ּ���. �� ����: 000-0000-0000");
    }
    json.put("msgs", msgs);

    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }

  /**
   * ����
   * 
   * @param s_categoryno
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/sub_category/delete.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public String delete(int s_categoryno) {
    Categrp_CategoryVO categoryVO = s_categoryProc.read(s_categoryno);
    JSONObject obj = new JSONObject(categoryVO);

    return obj.toString();
  }

  @ResponseBody
  @RequestMapping(value = "/sub_category/delete.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public ResponseEntity delete_proc(int s_categoryno) {
    HttpHeaders responseHeaders = new HttpHeaders();

    JSONObject json = new JSONObject();
    JSONArray msgs = new JSONArray();

    String title = s_categoryProc.read(s_categoryno).getTitle();

    if (s_categoryProc.delete(s_categoryno) == 1) {
      msgs.put("ī�װ��� �����߽��ϴ�.");
      msgs.put("������ ī�װ� [" + title + "]");
    } else {
      msgs.put("[" + title + "] ī�װ� ������ �����߽��ϴ�.");
      msgs.put("�ٽ��ѹ� �õ����ּ���. �� ����: 000-0000-0000");
    }
    json.put("msgs", msgs);

    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
  /**
   * ��¼��� ����
   * @param s_categoryno
   * @return
   */
  @RequestMapping(value="/sub_category/update_seqno_up.do", method=RequestMethod.POST)
  public ModelAndView update_seqno_up(int s_categoryno) {
    ModelAndView mav = new ModelAndView();
    
    int count = s_categoryProc.update_seqno_up(s_categoryno);
    if (count == 1) {
      mav.setViewName("redirect:/sub_category/list_by_categrp.do");
    }
    
    return mav;
  }
  
  @RequestMapping(value="/sub_category/update_seqno_down.do", method=RequestMethod.POST)
  public ModelAndView update_seqno_down(int s_categoryno) {
    ModelAndView mav = new ModelAndView();
    
    int count = s_categoryProc.update_seqno_down(s_categoryno);
    if (count == 1) {
      mav.setViewName("redirect:/sub_category/list_by_categrp.do");
    }
    
    return mav;
  }

}
