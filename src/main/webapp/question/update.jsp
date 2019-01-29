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


<script type="text/JavaScript">

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
            <strong class="text-black">Question</strong>
          </div>
        </div>
      </div>
    </div>

      <DIV class='container'>
        <DIV class='title_line' style='width: 50%;'>컨텐츠에서 받아올 내용을 표시합니다.</DIV>
           <DIV style='width: 80%; margin: 0px auto;'>
              <FORM name='frm' method='POST' action='./update.do' enctype="multipart/form-data" class="form-horizontal">
                <input type='hidden' name='questionno' id='questionno' value= '${param.questionno }'> 
                <input type='hidden' name='memberno' id='memberno' value='${param.memberno }'>
                <input type='hidden' name='nowPage' id='nowPage' value='${param.nowPage }'>
                <input type='hidden' name='question_word' id='question_word' value='${param.question_word }'>    
                
                <div class="block-7">
                  <label for="question_title" class="footer-heading">제목</label>    
                  <div class="form-group">
                    <input type='text' class="form-control py-4" name='question_title' id='question_title' value='${questionVO.question_title }' required="required" style='width: 80%;' placeholder="제목" >
                  </div>
                </div>   
                <div class="block-7">
                  <label for="question_contents" class="footer-heading">내용</label>    
                  <div class="form-group">
                    <textarea class="form-control py-4" name='question_contents' id='question_contents' rows='10' placeholder="내용">${questionVO.question_contents}</textarea>
                  </div>
                </div>
                <div class="block-7">
                  <div class="form-group">
                    <input type="text" class="form-control py-4" name='question_word' id='question_word' value='${questionVO.question_word }' placeholder="검색어"> <br>
                  </div>
                </div>
          <div id='file1Panel' class="form-group">
            <label class="col-md-1 control-label"></label>
            <div class="col-md-11">
              <!-- 파일명을 소문자로 변경 -->
              <c:set var='question_file1' value="${fn:toLowerCase(questionVO.question_file1)}" />
              <!-- 소문자로 변경된 파일명이 이미지인지 검사 -->
              <c:choose>
                <c:when test="${fn:endsWith(question_file1, '.jpg')}">
                  <IMG id='question_file1' src='./storage/${questionVO.question_file1}' style='width: 20%;'>
                </c:when>
                <c:when test="${fn:endsWith(question_file1, '.gif')}">
                  <IMG id='question_file1' src='./storage/${questionVO.question_file1}' style='width: 20%;'>
                </c:when>
                <c:when test="${fn:endsWith(question_file1, '.png')}">
                  <IMG id='question_file1' src='./storage/${questionVO.question_file1}' style='width: 20%;'>
                </c:when>
                <c:when test="${questionVO.question_file1.length() > 0}"> ${questionVO.question_file1 }  <!-- 이미지가 아니면서 파일이 존재하는 경우 파일명 출력 -->
                </c:when>
              </c:choose>
            </div>
          </div>
          <div class="form-group">
            <label for="file1MF" class="col-md-1 control-label">파일</label>
            <div class="col-md-11">
              <input type="file" class="form-control input-md" name='file1MF' id='file1MF' size='40'> <br>
              Preview(미리보기) 이미지는 자동 생성됩니다.
            </div>
          </div>
          <DIV style='text-align: right;'>
            <button type="submit">저장</button>
            <button type="button" onclick="location.href='./list.do?'">취소[목록]</button>
          </DIV>
        </FORM>
          </DIV>


      


</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>

</html> 

