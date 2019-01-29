<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>공연 정보 사이트</title> 
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- <script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->

<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta:300,400,700"> 
<link rel="stylesheet" href="../fonts/icomoon/style.css">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/magnific-popup.css">
<link rel="stylesheet" href="../css/jquery-ui.css">
<link rel="stylesheet" href="../css/owl.carousel.min.css">
<link rel="stylesheet" href="../css/owl.theme.default.min.css">
<link rel="stylesheet" href="../css/aos.css">
<link rel="stylesheet" href="../css/style.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
    
<script type="text/javascript">

</script>

</head> 

<body>
<DIV class='container' style='width: 100%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>   
  <div class="bg-light py-3">
      <div class="container">
        <div class="row">
          <div class="col-md-12 mb-0">
            <a href='${pageContext.request.contextPath}/index.jsp' >Home</a><span class="mx-2 mb-0">/</span>
            <strong class="text-black">Q/A 삭제</strong>
          </div>
        </div>
      </div>
    </div>
      
      <FORM name='frm' method='POST' action='./delete.do'>
        <input type='hidden' name='questionno' value='${questionVO.questionno}'>
        <input type='hidden' name='memberno' value='${memberVO.memberno}'>
        <input type='hidden' name='nowPage' id='nowPage' value='${param.nowPage}'>
        <input type='hidden' name='question_word' id='question_word' value='${param.question_word}'>
      <DIV class='row'>알림</DIV>
      <DIV class='col-md-12 text-center'>
        <span class="icon-error display-3"></span>
        <h2 class="display-3 text-black"></h2>
        <p >삭제 되는글: ${questionVO.question_title }</p>
        <p >삭제하시겠습니까? 삭제하시면 복구 할 수 없습니다.</p>
        
        <p class="lead mb-5">
          <button type = "submit">삭제 진행</button>
          <button type = "button" onclick = "history.back()">취소</button>
        </p>
      </DIV>
      </FORM>

      <%-- <ASIDE style='float: left;'>
    <A href='../category/list.do'>게시판 목록</A>
    >  
    <A href='./list_by_categoryno.do?categoryno=${categoryVO.categoryno }'>${categoryVO.title }</A>
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do?categoryno=${categoryVO.categoryno }'>등록</A>
    <span class='menu_divide' >│</span> 
    <A href='./list_by_categoryno.do?categoryno=${categoryVO.categoryno }&word_find=${word_find}'>목록</A>

  </ASIDE> 
  
  <div class='menu_line'></div>

  <FORM name='frm' method='POST' action='./delete.do'>
      <input type='hidden' name='questionno' value='${questionVO.questionno}'>
      <input type="hidden" name="categoryno" value="${categoryVO.categoryno }">
      <input type="hidden" name="word" value="${param.word }">
      <input type="hidden" name="nowPage" value="${param.nowPage }">
            
      <div class="form-group">   
        <div class="col-md-12" style='text-align: center; margin: 30px;'>
          삭제 되는글: ${questionVO.question_title }<br><br>
          삭제하시겠습니까? 삭제하시면 복구 할 수 없습니다.<br>
          <button type = "submit">삭제 진행</button>
          <button type = "button" onclick = "history.back()">취소</button>
        </div>
      </div>   
  </FORM> --%>

</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>

</html> 

  