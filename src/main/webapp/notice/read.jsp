<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
<meta name="viewport"
  content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet"
  href="https://fonts.googleapis.com/css?family=Mukta:300,400,700">
<link rel="stylesheet" href="../fonts/icomoon/style.css">

<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/magnific-popup.css">
<link rel="stylesheet" href="../css/jquery-ui.css">
<link rel="stylesheet" href="../css/owl.carousel.min.css">
<link rel="stylesheet" href="../css/owl.theme.default.min.css">

<link rel="stylesheet" href="../css/aos.css">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../js/jquery-ui.js"></script>
<script src="../js/popper.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/owl.carousel.min.js"></script>
<script src="../js/jquery.magnific-popup.min.js"></script>
<script src="../js/aos.js"></script>

<script src="../js/main.js"></script>

<!-- Bootstrap -->
<script
  src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">
  $(document).ready(function() { // window.onload = function() { ... }

  });

</script>
</head>

<body>
  <DIV class='container' style='width: 90%;'>
    <jsp:include page="/menu/top.jsp" flush='false' />
    <DIV class='content'>
      <c:choose>
        <c:when test="${sessionScope.m_act == 'M' }">
          <ASIDE style='float: right;'>
            <A href="javascript:location.reload();">새로고침</A> <span class='menu_divide'>│</span> 
            <A href='./create.do?noticeno=${noticeVO.noticeno }&nowPage=${param.nowPage }'>등록</A> <span class='menu_divide'>│</span> 
            <A href='./list_paging.do?nowPage=${param.nowPage }'>목록</A> <span class='menu_divide'>│</span> 
            <A href='./update.do?noticeno=${noticeVO.noticeno }&nowPage=${param.nowPage }'>수정</A> <span class='menu_divide'>│</span> 
            <A href='./delete.do?noticeno=${noticeVO.noticeno }&nowPage=${param.nowPage }'>삭제</A>
      </ASIDE>
        </c:when>
        <c:otherwise>
          <ASIDE style='float: right;'>
            <A href="javascript:location.reload();">새로고침</A> <span class='menu_divide'>│</span>  
            <A href='./list_paging.do?nowPage=${param.nowPage }'>목록</A>
           
          </ASIDE>
        </c:otherwise>
      </c:choose>
      

      <div class='menu_line'></div>

      <DIV id='main_panel'></DIV>

      <FORM name='frm' method="get" action='./update.do'>
        <input type="hidden" name="noticeno" value="${noticeVO.noticeno}">
        <div class="site-section">
          <div class="container">
            <div class="row">
              <div class="col-md-9">
                <h2 class="text-black">${noticeVO.title}</h2>
                <br>
                <p>${noticeVO.content }</p>
                <br>
                <br>
                <br>
               <p><a href="./list_paging.do?nowPage=${param.nowPage }" class="buy-now btn btn-sm btn-primary">목록</a></p>
              </div>
            </div>
          </div>
        </div>
      </FORM>


    </DIV>
    <!-- content END -->
    <jsp:include page="/menu/bottom.jsp" flush='false' />
  </DIV>
  <!-- container END -->
</body>

</html>
