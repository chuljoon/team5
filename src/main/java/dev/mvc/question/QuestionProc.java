package dev.mvc.question;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import nation.web.tool.Tool;

@Component("dev.mvc.question.QuestionProc")
public class QuestionProc implements QuestionProcInter {
  @Autowired
  @Qualifier("dev.mvc.question.QuestionDAO")
  private QuestionDAOInter questionDAO = null;
  
  public QuestionProc() {
    System.out.println("--> QuestionProc created.."); 
  }

  @Override
  public int create(QuestionVO questionVO) {
    int count = 0;
    count = questionDAO.create(questionVO);
    return count;
  }

  @Override
  public List<QuestionVO> list() {
    List<QuestionVO> list = questionDAO.list();
    
    Iterator<QuestionVO> iter = list.iterator();
    
    while(iter.hasNext()) {
      QuestionVO questionVO = iter.next();
      String title = Tool.textLength(questionVO.getQuestion_title(), 90);
      questionVO.setQuestion_title(title);
    }
    
    return list;
  }

  @Override
  public List<QuestionVO> list_by_member(int memberno) {
    List<QuestionVO> list = questionDAO.list_by_member(memberno);
    
    Iterator<QuestionVO> iter = list.iterator();
    
    while(iter.hasNext()) {
      QuestionVO questionVO = iter.next();
      String title = Tool.textLength(questionVO.getQuestion_title(), 90);
      questionVO.setQuestion_title(title);
    }
    
    return list;
  }
  
  @Override
  public int total_count() {
    int count = questionDAO.total_count();
    return count;
  }

  @Override
  public QuestionVO read(int questionno) {
    QuestionVO questionVO = questionDAO.read(questionno);
    
    long question_size1 = questionVO.getQuestion_size1();
    
    if (question_size1 > 0) {
      String question_size1Label = Tool.unit(question_size1);
      questionVO.setSize1Label(question_size1Label);
    }
    
    String question_title = questionVO.getQuestion_title();
    question_title = Tool.convertChar(question_title);
    questionVO.setQuestion_title(question_title);
   
    /* 특수 문자 변환 코드 시작 */
    String question_contents = questionVO.getQuestion_contents();
    question_contents = Tool.convertChar(question_contents);
    questionVO.setQuestion_contents(question_contents);
    
    return questionVO;
  }

  @Override
  public QuestionVO upeate(int questionno) {
    QuestionVO questionVO = questionDAO.upeate(questionno);
    return questionVO;
  }
 
  @Override
  public int update(QuestionVO questionVO) {
    return questionDAO.update(questionVO);
  }

  @Override
  public int delete(int questionno) {
    return questionDAO.delete(questionno);
  }

  @Override
  public List<QuestionVO> list_by_member_search(HashMap hashMap) {
    List<QuestionVO> list = questionDAO.list_by_member_search(hashMap);
    
    return list;
  }

  @Override
  public int search_count(HashMap hashMap) {
    int count = questionDAO.search_count(hashMap);
    return count;
  }

  @Override
  public List<QuestionVO> list_by_member_search_paging(HashMap<String, Object> hashMap) {
    /* 
    페이지에서 출력할 시작 레코드 번호 계산 기준값, nowPage는 1부터 시작
    1 페이지: nowPage = 1, (1 - 1) * 10 --> 0 
    2 페이지: nowPage = 2, (2 - 1) * 10 --> 10
    3 페이지: nowPage = 3, (3 - 1) * 10 --> 20
    */
   int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Question.RECORD_PER_PAGE;
   
    // 시작 rownum, 1 페이지: 1 / 2 페이지: 11 / 3 페이지: 21 
   int startNum = beginOfPage + 1; 
   //  종료 rownum, 1 페이지: 10 / 2 페이지: 20 / 3 페이지: 30
   int endNum = beginOfPage + Question.RECORD_PER_PAGE;   
   /*
    1 페이지: WHERE r >= 1 AND r <= 10
    2 페이지: WHERE r >= 11 AND r <= 20
    3 페이지: WHERE r >= 21 AND r <= 30
    */
   hashMap.put("startNum", startNum);
   hashMap.put("endNum", endNum);
   
   List<QuestionVO> list = questionDAO.list_by_member_search_paging(hashMap); 
   Iterator<QuestionVO> iter = list.iterator();
   
   while(iter.hasNext() == true) {
     QuestionVO questionVO = iter.next();
     String question_title = Tool.textLength(questionVO.getQuestion_title(), 90);
     question_title = Tool.convertChar(question_title); // 태그 처리
     questionVO.setQuestion_title(question_title);
   }
   
   return list;
  }

  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param memberno 회원번호 
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage     현재 페이지
   * @param word 검색어
   * @return 페이징 생성 문자열
   */ 
  @Override
  public String paging(int memberno, int search_count, int nowPage, String question_word) {
    int totalPage = (int)(Math.ceil((double)search_count/Question.RECORD_PER_PAGE)); // 전체 페이지  
    int totalGrp = (int)(Math.ceil((double)totalPage/Question.PAGE_PER_BLOCK));// 전체 그룹 
    int nowGrp = (int)(Math.ceil((double)nowPage/Question.PAGE_PER_BLOCK));    // 현재 그룹 
    int startPage = ((nowGrp - 1) * Question.PAGE_PER_BLOCK) + 1; // 특정 그룹의 페이지 목록 시작  
    int endPage = (nowGrp * Question.PAGE_PER_BLOCK);             // 특정 그룹의 페이지 목록 종료   
     
    StringBuffer str = new StringBuffer(); 
     
    str.append("<style type='text/css'>"); 
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}"); 
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
    str.append("  .span_box_1{"); 
    str.append("    text-align: center;");    
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("  }"); 
    str.append("  .span_box_2{"); 
    str.append("    text-align: center;");    
    str.append("    background-color: #668db4;"); 
    str.append("    color: #FFFFFF;"); 
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>"); 
//    str.append("현재 페이지: " + nowPage + " / " + totalPage + "  "); 

    // 이전 10개 페이지로 이동
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // 현재 2그룹일 경우: (2 - 1) * 10 = 1그룹의 10
    // 현재 3그룹일 경우: (3 - 1) * 10 = 2그룹의 20
    int _nowPage = (nowGrp-1) * Question.PAGE_PER_BLOCK;  
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./list_by_member_search_paging.do?&question_word="+question_word+"&nowPage="+_nowPage+"&memberno="+memberno+"'>이전</A></span>"); 
    } 

    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
      }else{
        // 현재 페이지가 아닌 페이지
        str.append("<span class='span_box_1'><A href='./list_by_member_search_paging.do?question_word="+question_word+"&nowPage="+i+"&memberno="+memberno+"'>"+i+"</A></span>");   
      } 
    } 

    // 10개 다음 페이지로 이동
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // 현재 1그룹일 경우: (1 * 10) + 1 = 2그룹의 11
    // 현재 2그룹일 경우: (2 * 10) + 1 = 3그룹의 21
    _nowPage = (nowGrp * Question.PAGE_PER_BLOCK)+1;  
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./list_by_member_search_paging.do?&question_word="+question_word+"&nowPage="+_nowPage+"&memberno="+memberno+"'>다음</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }

  @Override
  public int update_question_Ansnum(QuestionVO questionVO) {
    int count = 0;
    count = questionDAO.update_question_Ansnum(questionVO);
    return count;
  }

  @Override
  public int reply(QuestionVO questionVO) {
    int count = 0;
    count = questionDAO.reply(questionVO);
    return count;
  }

  
}
