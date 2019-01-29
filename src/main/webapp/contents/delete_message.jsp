<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

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

</head> 
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>

      <div class="site-section">
        <div class="container">
          <div class="row">
            <div class="col-md-12 text-center">
              <c:choose>
                <c:when test="${param.count == 0}">
                  <span class="icon-warning display-3" style="color: red;"></span>
                  <p class="lead mb-5">글 삭제에 실패했습니다.</p>
                  <p class="lead mb-5">다시한번 시도해주세요.</p>
                  <br>
                  <button type='button' onclick='history.back()'>다시 시도</button>
                  <button type='button' onclick="location.href='./list_by_category_paging.do?s_categoryno=${param.s_categoryno}&word=${param.word}&nowPage=1">목록</button>
                </c:when>
                <c:when test="${param.count == 1 }">
                  <span class="icon-check_circle display-3 text-success"></span>
                  <p class="lead mb-5">글 삭제에 성공했습니다.</p>
                  <br>
                  <button type='button' onclick="location.href='./list_by_category_paging.do?s_categoryno=${param.s_categoryno}&word=${param.word}&nowPage=1'">목록</button>
                </c:when>
              </c:choose>
            </div>
          </div>
        </div>
      </div>

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
            <button type='button' onclick="location.href='./list_by_category.do?s_categoryno=${param.s_categoryno}&word=${param.word}&nowPage=${param.nowPage}">목록</button>
          </li>
                     
        </c:when>
        <c:when test="${param.count == 1}">
          <li class='li_none'>글 삭제에 성공했습니다.</li>
          <li class='li_none'>
            <br>
            <button type='button' onclick="location.href='./list_by_category_paging.do?s_categoryno=${param.s_categoryno}&word=${param.word}&nowPage=${param.nowPage}'">목록</button>
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

  