<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/latest/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta:300,400,700"> 
    <link rel="stylesheet" href="../fonts/icomoon/style.css">

    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/magnific-popup.css">
    <link rel="stylesheet" href="../css/jquery-ui.css">
    <link rel="stylesheet" href="../css/owl.carousel.min.css">
    <link rel="stylesheet" href="../css/owl.theme.default.min.css">

    <link rel="stylesheet" href="../css/aos.css">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
  <script src="../js/jquery-3.3.1.min.js"></script>
  <script src="../js/jquery-ui.js"></script>
  <script src="../js/popper.min.js"></script>
  <script src="../js/bootstrap.min.js"></script>
  <script src="../js/owl.carousel.min.js"></script>
  <script src="../js/jquery.magnific-popup.min.js"></script>
  <script src="../js/aos.js"></script>

  <script src="../js/main.js"></script>

<!-- Bootstrap -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
 
<script type="text/javascript">
$(function(){
 
});
</script>
 
<script type="text/javascript">
</script>
</head>
 
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 100%;'>
      <form name='frm' id='frm' method="get"
        action="./list_by_category_paging.do">
        <ASIDE style='float: left;'>
          <A href='../sub_category/list.do'>게시판 목록</A> > <A
            href='./list_by_category_paging.do?s_categoryno=${categoryVO.s_categoryno }&word=${param.word}&nowPage=1'>${categoryVO.title }</A>

        </ASIDE>
        <ASIDE style='float: right;'>
          <A href="javascript:location.reload();">새로고침</A> <span
            class='menu_divide'>│</span> <A
            href='./create.do?s_categoryno=${categoryVO.s_categoryno }'>등록</A>

          <input type='hidden' name='s_categoryno' id='s_categoryno' value='${categoryVO.s_categoryno }'>
           <span class='menu_divide'>│</span>
          <c:choose>
            <c:when test="${param.word != '' }">
              <input type='text' name='word' id='word' value='${param.word }' style='width: 35%;'>
            </c:when>
            <c:otherwise>
              <input type='text' name='word' id='word' value='' style='width: 35%;'>
            </c:otherwise>
          </c:choose>
          <button type='submit'>검색</button>
          <button type='button' onclick="location.href='./list_by_category_paging.do?s_categoryno=${categoryVO.s_categoryno }&nowPage=1'">전체보기</button>
        </ASIDE>
      </form>

      <div class='menu_line' style='clear: both;'></div>  
      
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
              <li class="nav-item"><a class="nav-link" href="./list_all_category.do"> 전체 <span class="sr-only">
                    (current) </span></a></li>
              <li class="nav-item"><a class="nav-link" href="./list_by_category_paging.do?s_categoryno=1&word=${param.word}&nowPage=1"> 아이돌 </a></li>
              <li class="nav-item"><a class="nav-link" href="./list_by_category_paging.do?s_categoryno=2&word=${param.word}&nowPage=1"> 팬클럽/팬미팅 </a></li>
              <li class="nav-item"><a class="nav-link" href="./list_by_category_paging.do?s_categoryno=3&word=${param.word}&nowPage=1"> 발라드/R&B </a></li>
              <li class="nav-item"><a class="nav-link" href="./list_by_category_paging.do?s_categoryno=4&word=${param.word}&nowPage=1"> 힙합/EDM </a></li>
              <li class="nav-item"><a class="nav-link" href="./list_by_category_paging.do?s_categoryno=5&word=${param.word}&nowPage=1"> 페스티벌 </a></li>
              <li class="nav-item"><a class="nav-link" href="./list_by_category_paging.do?s_categoryno=6&word=${param.word}&nowPage=1"> 인디/록 </a></li>
              <li class="nav-item"><a class="nav-link" href="./list_by_category_paging.do?s_categoryno=7&word=${param.word}&nowPage=1"> 내한 </a></li>
              <li class="nav-item"><a class="nav-link" href="./list_by_category_paging.do?s_categoryno=8&word=${param.word}&nowPage=1"> 그 외 장르 </a></li>
            </ul>
          </div>
        </nav>
 
  <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 65%;"></col>
        <col style="width: 20%;"></col>
        <col style="width: 15%;"></col>
        
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th>제목</th>
          <th>공연 일시</th>
          <th>기타</th>
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="contentsVO" items="${list }">
          <tr>
            <td style='vertical-align: middle;'>
            <c:choose>
              <c:when test="${contentsVO.thumbs != ''}">
                <IMG id='thumbs' src='./storage/${contentsVO.thumbs}' style='width: 90px; height: 125px;'> <!-- 이미지 파일명 출력 -->
              </c:when>
              <c:otherwise>
                <IMG id='thumbs' src='./images/none.jpg' style='width: 90px; height: 125px;'> <!-- 파일이 존재하지 않는 경우 -->
              </c:otherwise>
            </c:choose>       
              <a href="./read.do?contentsno=${contentsVO.contentsno}&s_categoryno=${contentsVO.s_categoryno}&word=${param.word}&nowPage=${param.nowPage}">${contentsVO.title}</a> 
            </td> 
            <td style='vertical-align: middle;'>${contentsVO.p_date}</td>
            <td style='vertical-align: middle;'>
              <a href="./update.do?contentsno=${contentsVO.contentsno}&s_categoryno=${contentsVO.s_categoryno}&word=${param.word}&nowPage=${param.nowPage}"><img src="./images/update.png" title="수정" border='0'/></a>
              <a href="./delete.do?contentsno=${contentsVO.contentsno}&s_categoryno=${contentsVO.s_categoryno}&word=${param.word}&nowPage=${param.nowPage}"><img src="./images/delete.png" title="삭제"  border='0'/></a>
            </td>
          </tr>
        </c:forEach>
        
      </tbody>
  </table>
  <DIV class='bottom_menu'>${paging }</DIV>
  <br><br>
 
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html>