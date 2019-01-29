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
  $(function(){ 
  
  });
</script>
</head> 
<body>
<div class="site-wrap">
  <jsp:include page="/menu/top.jsp" flush='false' />
    <div class="bg-light py-3">
      <div class="container">
        <div class="row">
          <div class="col-md-12 mb-0">
            <a href='${pageContext.request.contextPath}/index.jsp' >Home</a><span class="mx-2 mb-0">/</span>
            <strong class="text-black">Question</strong><span class="mx-2 mb-0">/</span>
            <strong class="text-black">목록</strong>
            
          </div>
        </div>
      </div>
    </div>
    
    <div class="site-section">
      <div class="container">
        <div class="row mb-5">
          <div class="col-md-12">
            <div class="border p-4 rounded" role="alert" style="text-align: center;">
              <a href="${pageContext.request.contextPath }/question/create.do">여기를 클릭하여 질문해주세요!</a>
            </div>
          </div>
        </div>
          <form class="col-md-12" method="post">
            <div class="site-blocks-table">
              <table class="table table-bordered">
                <thead>
                  <tr>
                    <th class="product-name" width=20%;>질문번호</th>
                    <th class="product-thumbnail" width=20%;>파일</th>
                    <th class="product-name" width=45%;>제목</th>
                    <th class="product-price" width=15%;>등록일</th>
<!--                     <th class="product-quantity" width=10%;>조회수</th> -->
                    <th class="product-remove" width=15%;>기타</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach var="questionVO" items="${list }">
                  <tr>
                    <td>${questionVO.questionno }</td>
                    <td class="product-thumbnail">
                    <c:choose>
                      <c:when test="${questionVO.question_thumb != ''}">
                        <IMG id='question_thumb' src='./storage/${questionVO.question_thumb }'> <!-- 이미지 파일명 출력 -->
                      </c:when>
                      <c:when test= "${questionVO.question_file1 != '' }">
                        ${questionVO.question_file1 } <!-- 파일명 출력 -->
                      </c:when>
                      <c:otherwise>
                      <IMG src='./images/no_image.png' style='width: 120px; height: 80px;'>
                      </c:otherwise>
                    </c:choose>
                    </td>
                    <td class="product-name">
                      <h2 class="h5 text-black">
                        <a href="./read.do?questionno=${questionVO.questionno}&memberno=${questionVO.memberno}">${questionVO.question_title}</a>
                      </h2>
                    </td>
                    <td>${questionVO.rdate.substring(0, 16)}</td>
<%--                     <td>${questionVO.question_cnt}</td> --%>
                    
                    <td>
                      <a href="./update.do?questionno=${questionVO.questionno}&memberno=${questionVO.memberno}"><img src="./images/update.png" title="수정" border='0'/></a>
                      <a href="./delete.do?questionno=${questionVO.questionno}&memberno=${questionVO.memberno}"><img src="./images/delete.png" title="삭제"  border='0'/></a>
                    </td>                 
                </c:forEach> 
                </tbody>
              </table>
            </div>
          </form>
        </div>
      </div>
      <jsp:include page="/menu/bottom.jsp" flush='false' />
    </div>



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
 
 
 