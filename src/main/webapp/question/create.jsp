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
              <FORM name='frm' method='POST' action='./create.do' enctype="multipart/form-data" class="form-horizontal">
                <input type='hidden' name='memberno' id='memberno' value='${sessionScope.memberno }'>
                
                <div class="block-7">
                  <label for="question_title" class="footer-heading">제목</label>    
                  <div class="form-group">
                    <input type='text' class="form-control py-4" name='question_title' id='question_title' value='' required="required" style='width: 80%;' placeholder="제목" >
                  </div>
                </div>   
                <div class="block-7">
                  <label for="question_contents" class="footer-heading">내용</label>    
                  <div class="form-group">
                    <textarea class="form-control py-4" name='question_contents' id='question_contents' rows='10' placeholder="내용"></textarea>
                  </div>
                </div>
                <div class="block-7">
                  <div class="form-group">
                    <input type="file" class="form-control py-4" name='file1MF' id='file1MF' size='40' > <br>
                    Preview(미리보기) 이미지는 자동 생성됩니다.
                  </div>
                </div>
                <div class="block-7">
                  <div class="form-group">
                    <input type="text" class="form-control py-4" name='question_word' id='question_word' value='' placeholder="검색어"> <br>
                  </div>
                </div>
              <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                  <button type="submit" class="btn btn-sm btn-primary">등록</button>
                  <button type="button" onclick="history.back()" class="btn btn-sm btn-primary">취소</button>  
                </div> 
              </div>    
            </FORM>
          </DIV>
        </DIV>
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
 
 
 