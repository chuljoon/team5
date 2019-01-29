<%@ page contentType="text/html; charset=UTF-8" %>

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
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>     

<%--   <ASIDE style='float: left;'>
    <A href='../category/list.do'>게시판 목록</A>
    <img src='./images/bu5.gif'>  
    <A href='./list_by_categoryno.do?categoryno=${categoryVO.categoryno }'>${categoryVO.title }</A>
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do?categoryno=${categoryVO.categoryno }'>등록</A>
    <span class='menu_divide' >│</span> 
    <A href='javascript: history.back()'>답변 등록 취소</A>
  </ASIDE>  --%>

  <div class='menu_line'></div>
  <DIV style='width: 100%;'>
    <FORM name='frm' method='POST' action='./reply.do' enctype="multipart/form-data" class="form-horizontal">
      <input type='hidden' name='memberno' id='memberno' value='${sessionScope.memberno }'>
      <input type="hidden" name="questionno" value="${questionVO.questionno}">
      <input type="hidden" name="nowPage" value="${param.nowPage}">
      
      <div class="form-group">   
        <label for="title" class="col-md-2 control-label">글 제목</label>
        <div class="col-md-10">
          <input type='text' class="form-control input-lg" name='question_title' id='question_title' value='바다건너' required="required" style='width: 80%;'>
        </div>
      </div>   
      <div class="form-group">   
        <label for="content" class="col-md-2 control-label">내용</label>
        <div class="col-md-10">
          <textarea class="form-control input-lg" name='question_contents' id='question_contents'  rows='10'>크리스마스에 갔던곳</textarea>
        </div>
      </div>
         
      <div class="form-group">   
        <label for="file1MF" class="col-md-2 control-label">업로드 파일</label>
        <div class="col-md-10">
          <input type="file" class="form-control input-lg" name='file1MF' id='file1MF' size='40' >
          <br>
          Preview(미리보기) 이미지는 자동 생성됩니다.
        </div>
      </div>   

      <div class="form-group">   
        <label for="word" class="col-md-2 control-label">검색어</label>
        <div class="col-md-10">
          <input type='text' class="form-control input-lg" name='question_word' id='question_word' value='' style='width: 80%;'>
        </div>
      </div>  
      
      <DIV style='text-align: right;'>
        <button type="submit">답변 등록</button>
        <button type="button" onclick="location.href='./list.do'">취소[목록]</button>
      </DIV>
    </FORM>
  </DIV>

</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>

</html> 


