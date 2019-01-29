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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
    
</head> 
<body>
<DIV class='container' style='width: 100%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>

      <div class="site-section">
        <div class="container">
          <div class="row">
            <div class="col-md-12 text-center">
              <c:choose>
                <c:when test="${param.count == 1 }">
                <span class="icon-check_circle display-3 text-success"></span>
                  <p class="lead mb-5">새로운 컨텐츠를 등록했습니다.</p>
                </c:when>
                <c:otherwise>
                <span class="icon-warning display-3" style="color: red;"></span>
                  <p class="lead mb-5">새로운 컨텐츠 등록에 실패했습니다.</p>
                </c:otherwise>
              </c:choose>
              <button type='button' onclick="location.href='./create.do?s_categoryno=${param.s_categoryno}&word=${param.word}&nowPage=${param.nowPage}'">새로운 컨텐츠 등록</button>
              <button type='button' onclick="location.href='./list_by_category_paging.do?s_categoryno=${param.s_categoryno}&word=${param.word}&nowPage=1'">목록</button>
            </div>
          </div>
        </div>
      </div>

<%--       <DIV class='message'>
  <fieldset class='fieldset_basic'>
    <UL>
      <c:choose>
        <c:when test="${param.count == 1 }">
          <LI class='li_none'>새로운 컨텐츠를 등록했습니다.</LI>
        </c:when>
        <c:otherwise>
          <LI class='li_none'>새로운 컨텐츠 등록에 실패했습니다.</LI>
        </c:otherwise>
      </c:choose>
      <LI class='li_none'>
        <br>
        <button type='button' onclick="location.href='./create.do?s_categoryno=${param.s_categoryno}&word=${param.word}&nowPage=${param.nowPage}'">새로운 컨텐츠 등록</button>
        <button type='button' onclick="location.href='./list_by_category_paging.do?s_categoryno=${param.s_categoryno}&word=${param.word}&nowPage=${param.nowPage}'">목록</button>
      </LI>
     </UL>
  </fieldset>

</DIV> --%>

</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' /> 
</DIV> <!-- container END -->
</body>

</html> 

   