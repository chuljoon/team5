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
  $(function() {

  });
</script>

<script type="text/javascript">
  
</script>
</head>

<body>
  <DIV class='container' style='width: 100%;'>
    <jsp:include page="/menu/top.jsp" flush='false' />
    <DIV class='content' style='width: 100%;'>

      <ASIDE style='float: right;'>
        <A href="javascript:location.reload();">새로고침</A>
        <span class='menu_divide' >│</span> 
        <A href='./create.do?noticeno=${noticeVO.noticeno }'>등록</A>
      </ASIDE>
      <DIV class='menu_line' style='clear: both;'></DIV>

      <div style='width: 100%;'>
        <table class="table table-striped" style='width: 100%;'>
          <colgroup>
            <col style="width: 15%;"></col>
            <col style="width: 55%;"></col>
            <col style="width: 30%;"></col>

          </colgroup>
          <%-- table 컬럼 --%>
          <thead>
            <tr>
              <th>NO.</th>
              <th>제목</th>
              <th>등록일</th>
            </tr>

          </thead>

          <%-- table 내용 --%>
          <tbody>
            <c:forEach var="noticeVO" items="${list }">
              <tr>
                <td style='vertical-align: middle;'>${noticeVO.noticeno}</td>
                <td style='vertical-align: middle;'>
                <a href='./read.do?noticeno=${noticeVO.noticeno}'>${noticeVO.title}</a></td>
                <td style='vertical-align: middle;'>${noticeVO.rdate.substring(0, 10)}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
        <br>
        <br>
      </div>


    </DIV>
    <!-- content END -->
    <jsp:include page="/menu/bottom.jsp" flush='false' />
  </DIV>
  <!-- container END -->
</body>

</html>


