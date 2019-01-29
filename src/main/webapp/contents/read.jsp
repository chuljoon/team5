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

  <script src="../js/main.js"></script>

<!-- Bootstrap -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
 
<script type="text/javascript">
  $(document).ready(function(){ // window.onload = function() { ... }

  });
  
  function panel_img(files){
    var panel = '';
    panel += "<DIV id='panel' class='popup_img' style='width: 80%;'>";
    panel += "  <A href=\"javascript: $('#main_panel').hide();\"><IMG src='./storage/"+file+"' style='width: 100%;'></A>";
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
 
  <ASIDE style='float: left;'>
    <A href='../sub_category/list.do?m_categrpno=${categoryVO.m_categrpno }'>카테고리</A>
    <span style='font-size: 1.2em;'>></span>  
    <A href='./list_by_category_paging.do?s_categoryno=${categoryVO.s_categoryno }&word=${param.word}&nowPage=1'>${categoryVO.title }</A>
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do?s_categoryno=${categoryVO.s_categoryno }'>등록</A>
    <span class='menu_divide' >│</span> 
    <A href='./list_by_category_paging.do?s_categoryno=${categoryVO.s_categoryno }&word=${param.word}&nowPage=${param.nowPage }'>목록</A>
    <span class='menu_divide' >│</span> 
    <A href='./update.do?contentsno=${contentsVO.contentsno }&s_categoryno=${categoryVO.s_categoryno }&word=${param.word}&nowPage=${param.nowPage }'>수정</A>
    <span class='menu_divide' >│</span> 
    <A href='./delete.do?contentsno=${contentsVO.contentsno }&s_categoryno=${categoryVO.s_categoryno }&word=${param.word}&nowPage=${param.nowPage}'>삭제</A>
  </ASIDE> 
  
  <div class='menu_line'></div>
 
  <DIV id='main_panel'></DIV>
  
  <FORM name='frm' method="get" action='./update.do'>
      <input type="hidden" name="contentsno" value="${contentsVO.contentsno}">
      <div class="site-section">
      <div class="container">
        <div class="row">
          <div class="col-md-3">
            <IMG id='thumbs'  src='./storage/${contentsVO.thumbs}'' >
          </div>
          <div class="col-md-9">
            <h2 class="text-black">${contentsVO.title}</h2> <br>
            <p>${contentsVO.contents }</p> <br><br><br>
            <p><strong class="text-primary h4">공연기간: ${contentsVO.p_date }</strong></p>
          </div>
        </div>
      </div>
    </div>

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
              <li class="nav-item active"><a class="nav-link" href="#"> 상세보기 <span class="sr-only">
                    (current) </span></a></li>
              <li class="nav-item"><a class="nav-link" href="../expect/list.do?contentsno=${param.contentsno }"> 기대평 </a></li>
              <li class="nav-item"><a class="nav-link" href="../hall/list.do"> 공연장 </a></li>
            </ul>
          </div>
        </nav>

        <div class="site-section block-3 site-blocks-2 bg-light">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-md-7 site-section-heading text-center pt-4">
            <h2>상세정보</h2>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
             <div id='panel_img' style="padding: center;">
              <!-- EL 값을 JSTL 변수에 할당 -->
              <c:set var='files' value="${fn:toLowerCase(contentsVO.files)}" />
              
              <c:choose>
                <c:when test="${fn:endsWith(files, '.jpg')}">
                  <IMG id='files' src='./storage/${contentsVO.files}' >
                </c:when>
                <c:when test="${fn:endsWith(files, '.gif')}">
                  <IMG id='files'  src='./storage/${contentsVO.files}' >
                </c:when>
                <c:when test="${fn:endsWith(files, '.png')}">
                  <IMG id='files'  src='./storage/${contentsVO.files}'' >
                </c:when>
              </c:choose>
              </div>
          </div>
        </div>
      </div>
    </div>
    
 </FORM>
 
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' /> 
</DIV> <!-- container END -->
</body>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
  <script src="../js/jquery-3.3.1.min.js"></script>
  <script src="../js/jquery-ui.js"></script>
  <script src="../js/popper.min.js"></script>
  <script src="../js/bootstrap.min.js"></script>
  <script src="../js/owl.carousel.min.js"></script>
  <script src="../js/jquery.magnific-popup.min.js"></script>
  <script src="../js/aos.js"></script>
 
</html>
 