<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>공연 정보 사이트</title> 
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

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
   var loading = 0;
  
  $(document).ready(function(){ // window.onload = function() { ... }
    imgResize()
  });

  //<div id='file1Panel'> 태그의 width에 맞추어 자동 축소
  function imgResize() {
    loading = loading + 1;
    var question_file1 = $('#question_file1');
    var width = question_file1.width();
    // alert(width);
    
    if (question_file1 != null) {
      // 이미지 width가 화면의 70%보다 크다면
      if (width > screen.width-(screen.width * 0.3)) {  
        // file1.width(600); // 이미지 축소
        $('#file1Panel').attr('width', '100%');
        question_file1.css('width', '100%'); // <div id='file1Panel'> 태그의 width에 맞추어 자동 축소
      } else {
        // 작은 이미지는 그대로 출력
      }
    }
    
    var timer = setInterval(imgResize, 100); // 0.1 초
    
    if (loading == 2) {
      clearInterval(timer) // 타이머 종료, 함수 실행 종료
    }

  } 
</script>
</head>

<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>

      <div class="site-section" id='file1Panel'>
        <FORM name='frm' method="get" action='./update.do'>
        <input type="hidden" name="questionno" value="${questionVO.questionno}">
          <div class="container">
            <div class="row">
              <div class="col-md-6">
                <c:set var='question_file1' value="${fn:toLowerCase(questionVO.question_file1)}" />
                <c:choose>
                  <c:when test="${fn:endsWith(question_file1, '.jpg')}">
                    <IMG id='question_file1' src='./storage/${questionVO.question_file1}'>
                  </c:when>
                  <c:when test="${fn:endsWith(question_file1, '.gif')}">
                    <IMG id='question_file1' src='./storage/${questionVO.question_file1}'>
                  </c:when>
                  <c:when test="${fn:endsWith(question_file1, '.png')}">
                    <IMG id='question_file1' src='./storage/${questionVO.question_file1}'>
                  </c:when>
                </c:choose>
              </div>
              <div class="col-md-6">
                <h2 class="text-black">${questionVO.question_title}</h2>
                <p>질문 내용: ${questionVO.question_contents }</p>
                <p class="mb-4">작성 날짜: ${questionVO.rdate.substring(0, 16)}</p>
                <p class="mb-4">
                  <c:if test="${questionVO.question_size1 > 0}">
                    첨부 파일: <A href='${pageContext.request.contextPath}/download?dir=/blog/storage&filename=${questionVO.question_file1}'>${questionVO.question_file1}</A> (${questionVO.size1Label})
                  </c:if>
                </p>
                <DIV style='text-decoration: underline;'>검색어:(키워드) ${questionVO.question_word }</DIV>
              </div>
            </div>
          </div>
        </FORM>
      </div>

      

</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
<script src="../js/jquery-3.3.1.min.js"></script>
    <script src="../js/jquery-ui.js"></script>
    <script src="../js/popper.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/owl.carousel.min.js"></script>
    <script src="../js/jquery.magnific-popup.min.js"></script>
    <script src="../js/aos.js"></script>
    <script src="../js/main.js"></script>
</body>

</html>

