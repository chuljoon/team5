<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>기대평 수정</title> 
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
  
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
 
</head> 
 
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>

      <div class='menu_line'></div>
      <FORM name='frm' method='POST' action='./update.do' enctype="multipart/form-data" class="form-horizontal">
        <input type='hidden' name='contentsno' id='contentsno' value='${expectVO.contentsno }'> 
        <input type='hidden' name='expectno' id='expectno' value='${expectVO.expectno }'>

        <div class="p-3 p-lg-5 border">
          <div class="form-group row">
            <div class="col-md-12">
              <textarea name='content' id='content' rows='5' style='width: 100%;' placeholder='* 게시된 글의 저작권은 글을 작성한 회원에게 있으며 게시물로 인해 발생하는 문제는 게시자 본인에게 책임이 있습니다.'>${expectVO.content }</textarea>
            </div>
          </div>
          <div id='filesPanel' class="form-group row">
            <label class="col-md-1 control-label"></label>
            <div class="col-md-11">
              <!-- 파일명을 소문자로 변경 -->
              <c:set var='imgs' value="${fn:toLowerCase(expectVO.imgs)}" />
              <!-- 소문자로 변경된 파일명이 이미지인지 검사 -->
              <c:choose>
                <c:when test="${fn:endsWith(imgs, '.jpg')}">
                  <IMG id='files' src='./storage/${expectVO.imgs}'
                    style='width: 20%;'>
                </c:when>
                <c:when test="${fn:endsWith(imgs, '.gif')}">
                  <IMG id='files' src='./storage/${expectVO.imgs}'
                    style='width: 20%;'>
                </c:when>
                <c:when test="${fn:endsWith(imgs, '.png')}">
                  <IMG id='files' src='./storage/${expectVO.imgs}'
                    style='width: 20%;'>
                </c:when>
                <c:when test="${expectVO.imgs.length() > 0}">
                ${expectVO.imgs }  <!-- 이미지가 아니면서 파일이 존재하는 경우 파일명 출력 -->
                </c:when>
              </c:choose>
            </div>
          </div>
          <div class="form-group row">
            <div class="col-md-12">
              <input type="file" class="form-control input-lg" name='filesMF' id='filesMF' size='40'>
            </div>
          </div>
          <div class="form-group row">
            <div class="col-lg-12">
              <input type="submit" class="btn btn-primary btn-lg btn-block" value="수정">
            </div>
          </div>
        </div>
      </form>

  </DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html> 