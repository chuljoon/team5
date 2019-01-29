<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">
$(function(){

});
</script>

<script type="text/javascript">
</script>
</head>

<body>
<DIV class='container' style='width: 100%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 100%;'>     

  <ASIDE style='float: left;'>
    <A href='./list.do'>게시판 목록</A> 
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
     <span class='menu_divide' >│</span>
    <c:choose>
      <c:when test="${param.g_word != '' }">
        <input type='text' name='g_word' id='g_word' value='${param.g_word }' style='width: 35%;'>
      </c:when>
      <c:otherwise>
        <input type='text' name='g_word' id='g_word' value='' style='width: 35%;'>
      </c:otherwise>
    </c:choose>
    <button type='submit'>검색</button>
    <button type='button' 
                 onclick="location.href='./list_search.do'">전체 보기</button>
  </ASIDE> 
  

  <DIV class='menu_line' style='clear: both;'></DIV>
  
  <div style='width: 100%;'>
    <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 15%;"></col>
        <col style="width: 60%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 15%;"></col>
        
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th style='text-align: center;'>등록일</th>
          <th>제목</th>
          <th style='text-align: center;'>조회수</th>
          <th>기타</th>
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="galleryVO" items="${list }">
          <tr> 
            <td style='vertical-align: middle; text-align: center;'>${galleryVO.rdate.substring(0, 10)}</td>
            <td style='vertical-align: middle;'>
              <a href="./read.do?galleryno=${galleryVO.galleryno}&g_word=${param.g_word}">${galleryVO.g_title}</a> 
            </td> 
            <td style='vertical-align: middle; text-align: center;'>${galleryVO.g_cnt}</td>
            <td style='vertical-align: middle;'>
              <a href="./update.do?galleryno=${galleryVO.galleryno}"><img src="./images/update.png" title="수정" border='0'/></a>
              <a href="./delete.do?galleryno=${galleryVO.galleryno}"><img src="./images/delete.png" title="삭제"  border='0'/></a>
<%--               ${contentsVO.categoryno} / ${contentsVO.mno } --%>
            </td>
          </tr>
        </c:forEach>
        
      </tbody>
    </table>
    <br><br>
  </div>


</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>

</html>
    

