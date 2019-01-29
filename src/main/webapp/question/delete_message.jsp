<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
  $(function(){ 
  
  });
</script>

</head> 
<body>
<DIV class='container'>
<c:import url="/menu/top.jsp" />
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

      <DIV class='title_line'>알림</DIV>
      <DIV class='col-md-12 text-center'>
        <c:choose>
        <c:when test="${param.count == 0}">
          <span class="icon-error display-3"></span>
          <p>글 삭제에 실패했습니다.<p>
          <p>다시한번 시도해주세요.<p>
          <p class="lead mb-5">
            <br>
            <button type='button' onclick='history.back()'>다시 시도</button>
            <button type='button' onclick="location.href='./list.do?'">목록</button>
          <p>
                     
        </c:when>
        <c:when test="${param.count == 1}">
          <span class="icon-check_circle display-3 text-success"></span>
          <p>글 삭제에 성공했습니다.<p>
          <p class="lead mb-5">
            <br>
            <button type='button' onclick="location.href='./list.do?'">목록</button>
            
          <p>          
        </c:when>
      </c:choose>
      </DIV>

      <%-- <DIV class='message'>
  <fieldset class='fieldset_basic'>
    <UL>
      <c:choose>
        <c:when test="${param.count == 0}">
          <li class='li_none'>글 삭제에 실패했습니다.</li>
          <li class='li_none'>다시한번 시도해주세요.</li>
          <li class='li_none'>
            <br>
            <button type='button' onclick='history.back()'>다시 시도</button>
            <button type='button' onclick="location.href='./list.do?'">목록</button>
          </li>
                     
        </c:when>
        <c:when test="${param.count == 1}">
          <li class='li_none'>글 삭제에 성공했습니다.</li>
          <li class='li_none'>
            <br>
            <button type='button' onclick="location.href='./list.do?'">목록</button>
          </li>          
        </c:when>
      </c:choose>    

    </UL>
  </fieldset>

</DIV> --%>

</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>

</html> 

  