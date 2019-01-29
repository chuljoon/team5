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

<!-- Bootstrap -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">
$(function(){

});
</script>

<script type="text/javascript">
</script>
</head>

<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>     

  <div class='menu_line'></div>
  <DIV class='content' style='width: 100%;'>
    <FORM name='frm' method='POST' action='./create.do'
               enctype="multipart/form-data" class="form-horizontal">
               
      <input type='hidden' name='contentsno' id='contentsno' value='${param.contentsno }'>
      
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
              <li class="nav-item"><a class="nav-link" href="../contents/read.do?contentsno=${param.contentsno }&s_categoryno=${param.s_categoryno}"> 상세보기 <span class="sr-only">
                    (current) </span></a></li>
              <li class="nav-item active"><a class="nav-link" href="../expect/list.do?contentsno=${param.contentsno }"> 기대평 </a></li>
              <li class="nav-item"><a class="nav-link" href="../hall/list.do"> 공연장 </a></li>
            </ul>
          </div>
        </nav>
      
              <div class="p-3 p-lg-5 border">
                <div class="form-group row">
                  <div class="col-md-12">
                <textarea name='content' id='content' rows='5' style='width: 100%;' placeholder='* 게시된 글의 저작권은 글을 작성한 회원에게 있으며 게시물로 인해 발생하는 문제는 게시자 본인에게 책임이 있습니다.'></textarea>
                  </div>
                </div>
                <div class="form-group row">
                  <div class="col-md-12">
                    <input type="file" class="form-control input-lg" name='filesMF' id='filesMF' size='40'>
                  </div>
                </div>
                <div class="form-group row">
                  <div class="col-lg-12">
                    <input type="submit" class="btn btn-primary btn-lg btn-block" value="등록">
                  </div>
                </div>
              </div>
            </form>
          </div>
         
  <div style='width: 100%;'>
    <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 25%;"></col>
        <col style="width: 60%;"></col>
        <col style="width: 15%;"></col>
        
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th>이름</th>
          <th>내용</th>
          <th>등록일</th>
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="expectVO" items="${list }">
          <tr> 
          <td style='vertical-align: middle;'>이름</td>
            <td style='vertical-align: middle;'>
              <c:choose>
                <c:when test="${expectVO.thumbs != ''}">
                  <IMG id='thumbs' src='./storage/${expectVO.thumbs }'> <!-- 이미지 파일명 출력 -->
                  ${expectVO.content}
                </c:when>
                <c:otherwise>
                  <!-- 파일이 존재하지 않는 경우 -->
                  <IMG id='thumbs' src='./images/white.jpg' style='width: 10px; height: 10px;'>
                  ${expectVO.content}
                </c:otherwise>
              </c:choose>
            </td>
            <td style='vertical-align: middle;'>${expectVO.rdate.substring(0, 16)}</td>
<%--             <td style='vertical-align: middle;'>
              <a href="./update.do?expectno=${expectVO.expectno}&contentsno=${expectVO.contentsno}"><img src="./images/update.png" title="수정" border='0'/></a>
              <a href="./delete.do?expectno=${expectVO.expectno}&contentxno=${expectVO.contentsno}"><img src="./images/delete.png" title="삭제"  border='0'/></a>
            </td> --%>
          </tr>
        </c:forEach>
        
      </tbody>
    </table>
    <br><br>
  </div>


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

  <script src="../js/main.js"></script>

</html>

