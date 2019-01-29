package dev.mvc.contents;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import nation.web.tool.Tool;

@Component("dev.mvc.contents.ContentsProc")
public class ContentsProc implements ContentsProcInter {
  @Autowired
  @Qualifier("dev.mvc.contents.ContentsDAO")
  private ContentsDAOInter contentsDAO = null;

  public ContentsProc() {
    System.out.println("--> ContentsProc created.");
  }

  @Override
  public int create(ContentsVO contentsVO) {
    return contentsDAO.create(contentsVO);
  }

  @Override
  public List<ContentsVO> list_all_category() {
    return contentsDAO.list_all_category();
  }

  @Override
  public List<ContentsVO> list_by_category(int s_categoryno) {
    return contentsDAO.list_by_category(s_categoryno);
  }

  @Override
  public ContentsVO read(int contentsno) {
    return contentsDAO.read(contentsno);
  }

  @Override
  public ContentsVO update(int contentsno) {
    return contentsDAO.update(contentsno);
  }

  @Override
  public int update(ContentsVO contentsVO) {
    return contentsDAO.update(contentsVO);
  }

  @Override
  public int delete(int contentsno) {
    return contentsDAO.delete(contentsno);
  }


  @Override
  public List<ContentsVO> list_by_category_paging(HashMap hashMap) {
    /* 
    ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
    1 ������: nowPage = 1, (1 - 1) * 10 --> 0 
    2 ������: nowPage = 2, (2 - 1) * 10 --> 10
    3 ������: nowPage = 3, (3 - 1) * 10 --> 20
    */
   int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Contents.RECORD_PER_PAGE;
   
    // ���� rownum, 1 ������: 1 / 2 ������: 11 / 3 ������: 21 
   int startNum = beginOfPage + 1; 
   //  ���� rownum, 1 ������: 10 / 2 ������: 20 / 3 ������: 30
   int endNum = beginOfPage + Contents.RECORD_PER_PAGE;   
   /*
    1 ������: WHERE r >= 1 AND r <= 10
    2 ������: WHERE r >= 11 AND r <= 20
    3 ������: WHERE r >= 21 AND r <= 30
    */
   hashMap.put("startNum", startNum);
   hashMap.put("endNum", endNum);
   
   
   List<ContentsVO> list = contentsDAO.list_by_category_paging(hashMap); 
   Iterator<ContentsVO> iter = list.iterator();
   
   while(iter.hasNext() == true) {
     ContentsVO contentsVO = iter.next();
     String title = Tool.textLength(contentsVO.getTitle(), 90);
     title = Tool.convertChar(title);
     contentsVO.setTitle(title);
   }
   
   return list;
  }

  @Override
  public String paging(int s_categoryno, int search_count, int nowPage, String word) {
    int totalPage = (int)(Math.ceil((double)search_count/Contents.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Contents.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Contents.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Contents.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Contents.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
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
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("  .span_box_2{"); 
    str.append("    text-align: center;");    
    str.append("    background-color: #668db4;"); 
    str.append("    color: #FFFFFF;"); 
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>"); 
//    str.append("���� ������: " + nowPage + " / " + totalPage + "  "); 

    int _nowPage = (nowGrp-1) * Contents.PAGE_PER_BLOCK; // ���� �������� �̵� 
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./list_by_category.do?&word="+word+"&nowPage="+_nowPage+"&s_categoryno="+s_categoryno+"'>����</A></span>"); 
    } 

    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='./list_by_category_paging.do?word="+word+"&nowPage="+i+"&s_categoryno="+s_categoryno+"'>"+i+"</A></span>");   
      } 
    } 
     
    _nowPage = (nowGrp * Contents.PAGE_PER_BLOCK)+1; // 10�� ���� �������� �̵� 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./list_by_category_paging.do?&word="+word+"&nowPage="+_nowPage+"&s_categoryno="+s_categoryno+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }

  @Override
  public int search_count(HashMap hashMap) {
    return contentsDAO.search_count(hashMap);
  }

}
