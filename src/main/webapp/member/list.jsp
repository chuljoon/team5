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
            <strong class="text-black">Member</strong><span class="mx-2 mb-0">/</span>
            <strong class="text-black">목록</strong>
          </div>
        </div>
      </div>
    </div>
  </div>
  
  <div class="site-section">
      <div class="container">
        <div class="row mb-5">
          <form class="col-md-12" method="post">
            <div class="site-blocks-table">
              <table class="table table-bordered">
                <thead>
                  <tr>
                    <th class="product-name">회원번호</th>
                    <th class="product-name">Email</th>
                    <th class="product-name">성명</th>
                    <th class="product-name">닉네임</th>
                    <th class="product-name">나이</th>
                    <th class="product-name">권한</th>
                    <th class="product-name">등록일</th>
                    <th class="product-name">기타</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach var="memberVO" items="${list }">
                  <c:set var="memberno" value ="${memberVO.memberno }" /> 
                  <tr>
                    <td>${memberno }</td>
                    <td class="product-name">${memberVO.m_email}</td>
                    <td class="product-name"><A href="./read.do?memberno=${memberno}">${memberVO.m_name}</A></td>
                    <td class="product-name">${memberVO.m_nickname}</td>
                    <td class="product-name">${memberVO.m_age}</td>
                    <td class="product-name"><A href="./read.do?memberno=${memberno}">${memberVO.m_act}</A></td>
                    <td class="product-name">${memberVO.rdate.substring(0, 10)}</td>
                    <td class="product-name">
                      <A href="./passwd_update.do?memberno=${memberno}"><span class="icon icon-cog" title='비밀번호 수정'></span></A>
                      <A href="./read.do?memberno=${memberno}"><span class="icon icon-edit" title='상세정보 및 수정'></span></A>
                      <A href="./delete.do?memberno=${memberno}"><span class="icon icon-delete" title='삭제'></span></A>
                    </td>
                  </tr>                 
                </c:forEach> 
                </tbody>
              </table>
            </div>
          </form>
        </div>
      </div>
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
  