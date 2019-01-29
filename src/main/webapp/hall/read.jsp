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

  function panel_img(files) {
    var panel = '';
    panel += "<DIV id='panel' class='popup_img' style='width: 80%;'>";
    panel += "  <A href=\"javascript: $('#main_panel').hide();\"><IMG src='./storage/"
        + file + "' style='width: 100%;'></A>";
    panel += "</DIV>";

    $('#main_panel').html(panel);
    $('#main_panel').show();

  }
</script>
</head>

<body>
  <DIV class='container' style='width: 90%;'>
    <jsp:include page="/menu/top.jsp" flush='false' />
    <DIV class='content'>

      <ASIDE style='float: right;'>
        <A href="javascript:location.reload();">새로고침</A> <span class='menu_divide'>│</span> 
        <A href='./create.do?hallno=${hallVO.hallno }'>등록</A> 
          <span class='menu_divide'>│</span> 
          <A href='./list.do'>목록</A> 
          <span class='menu_divide'>│</span> 
          <A href='./update.do?hallno=${hallVO.hallno }'>수정</A> 
          <span class='menu_divide'>│</span> 
          <A href='./delete.do?hallno=${hallVO.hallno }'>삭제</A>
      </ASIDE>

      <div class='menu_line'></div>

      <DIV id='main_panel'></DIV>

      <FORM name='frm' method="get" action='./update.do'>
        <input type="hidden" name="hallno" value="${hallVO.hallno}">
        <div class="site-section">
          <div class="container">
            <div class="row">
              <div class="col-md-3">
                <IMG id='thumbs' src='./storage/${hallVO.thumbs}'' >
              </div>
              <div class="col-md-9">
                <h2 class="text-black">${hallVO.title}</h2>
                <br>
                <p>${hallVO.content }</p>
                <br>
                <br>
                <br>
              </div>
            </div>
          </div>
        </div>

        <div class="site-section block-3 site-blocks-2 bg-light">
          <div class="container">
            <div class="row justify-content-center">
              <div
                class="col-md-7 site-section-heading text-center pt-4">
                <h2>공연장 위치</h2>
              </div>
            </div>
            <div class="row">
              <div class="col-md-12">
                <div style='width: 520px; margin: 0px auto;'>
                  ${hallVO.map }</div>
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
