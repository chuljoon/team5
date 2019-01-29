package dev.mvc.main_categrp;

import java.util.List;
import java.util.Locale;

import org.json.simple.JSONObject;
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

import dev.mvc.sub_category.Sub_CategoryProcInter;


@Controller
public class Main_CategrpCont {
  @Autowired
  @Qualifier("dev.mvc.main_categrp.Main_CategrpProc")
  private Main_CategrpProcInter m_categrpProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.sub_category.Sub_CategoryProc")
  private Sub_CategoryProcInter s_categoryProc = null;
  
  public Main_CategrpCont() {
    System.out.println("--> Main_CategrpCont created.");
  }
  
  /**
   * 메인카테고리 등록
   * @return
   */
  // http://localhost:9090/info/main_categrp/create.do
  @RequestMapping(value="/main_categrp/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();

    mav.setViewName("/main_categrp/create");
   
    return mav;
  }
  
  @RequestMapping(value="/main_categrp/create.do", method=RequestMethod.POST)
  public ModelAndView create(Main_CategrpVO m_categrpVO, Locale locale) {
    ModelAndView mav = new ModelAndView();
    
    int count = m_categrpProc.create(m_categrpVO);
    
    mav.addObject("count", count);

    mav.setViewName("/main_categrp/create_message");
   
    return mav;
  }
  
  /**
   * 목록
   * @return
   */
  //http://localhost:9090/info/m_categrp/list.do
  @RequestMapping(value="/main_categrp/list.do", method=RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    
    List<Main_CategrpVO> list = m_categrpProc.list();
    mav.addObject("list", list);
    mav.setViewName("/main_categrp/list");
    
    return mav;
  }
  
  /**
   * 수정
   * @param main_categrpno
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/main_categrp/update.do", 
                             method=RequestMethod.GET,
                             produces="text/plain;charset=UTF-8")
  public ResponseEntity update(int m_categrpno) {
    HttpHeaders responseHeaders = new HttpHeaders();
    Main_CategrpVO m_categrpVO = m_categrpProc.read(m_categrpno);
    
    JSONObject json = new JSONObject();
    json.put("m_categrpno", m_categrpVO.getM_categrpno());
    json.put("name", m_categrpVO.getName());
    json.put("seqno", m_categrpVO.getSeqno());
    json.put("visible",  m_categrpVO.getVisible());
    json.put("rdate", m_categrpVO.getRdate());
    
    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
  //http://localhost:9090/info/main_categrp/update.do
  @RequestMapping(value="/main_categrp/update.do", method=RequestMethod.POST)
  public ModelAndView update(Main_CategrpVO m_categrpVO) {
    ModelAndView mav = new ModelAndView();
    
    int count = m_categrpProc.update(m_categrpVO);
    mav.setViewName("redirect:/main_categrp/list.do");
    
    return mav;
  }
  
  /**
   * 삭제
   * @param main_categrpno
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/main_categrp/delete.do", 
                             method=RequestMethod.GET,
                             produces="text/plain;charset=UTF-8")
  public ResponseEntity delete(int m_categrpno) {
    HttpHeaders responseHeaders = new HttpHeaders();
    Main_CategrpVO m_categrpVO = m_categrpProc.read(m_categrpno);
        
    JSONObject json = new JSONObject();
    json.put("m_categrpno", m_categrpVO.getM_categrpno());
    json.put("name", m_categrpVO.getName());
        
    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
  @RequestMapping(value="/main_categrp/delete.do", method=RequestMethod.POST)
  public ModelAndView delete_proc(int m_categrpno) {
    ModelAndView mav = new ModelAndView();
    
    int count = m_categrpProc.delete(m_categrpno);

    mav.setViewName("redirect:/main_categrp/list.do"); 
    
    return mav;
  }
  
  /**
   * 카테고리 테이블에서 카테고리 그룹에 소속된 레코드 모두 삭제
   * @param categrpno
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/main_categrp/delete_category_by_categrp.do", 
                             method=RequestMethod.POST,
                             produces="text/plain;charset=UTF-8")
  public ResponseEntity delete_category_by_categrp(int m_categrpno) {
    HttpHeaders responseHeaders = new HttpHeaders();
    Main_CategrpVO m_categrpVO = m_categrpProc.read(m_categrpno);
    
    int delete_by_categrp = s_categoryProc.delete_by_categrp(m_categrpno);
    
    JSONObject json = new JSONObject();
    json.put("m_categrpno", m_categrpVO.getM_categrpno());
    json.put("name", m_categrpVO.getName());
    json.put("delete_by_categrp", delete_by_categrp);
        
    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }


}
