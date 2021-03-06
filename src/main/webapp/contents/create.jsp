<%@ page contentType="text/html; charset=UTF-8" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

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
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
 
<script type="text/JavaScript">
$(function() {
  $("#p_date1, #p_date2").datepicker({
      dateFormat: 'yy.mm.dd'
  });
});
</script>
 
</head> 
 
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>     

<div class="bg-light py-3">
      <div class="container">
        <div class="row">
          <div class="col-md-12 mb-0"><a href="../sub_category/list.do">게시판 목록</a> 
          <span class="mx-2 mb-0">/</span> 
          <strong class="text-black"><A href='./list_by_category_paging.do?s_categoryno=${categoryVO.s_categoryno }&word=${param.word}&nowPage=1'>${categoryVO.title }</A></strong></div>
        </div>
      </div>
    </div>  

    <div class="site-section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <FORM name='frm' method='POST' action='./create.do'
                       enctype="multipart/form-data" class="form-horizontal">
               
             <input type='hidden' name='s_categoryno' id='s_categoryno' value='${categoryVO.s_categoryno }'> 

                
                <div class="p-3 p-lg-5 border">
                <div class="form-group row">
                   <div class="col-md-11">
                  <label for="title" class="text-black">공연명</label>
                   <input type='text' class="form-control input-lg" name='title' id='title' value='2018 아이유 10주년 투어 콘서트 〈이 지금 dlwlrma〉－제주' required="required" style='width: 80%;'>
                  </div>
                </div>
                <div class="form-group row">
                <div class="col-md-11">
                 <label for="title_imgMF" class="text-black">공연 포스터</label>
                    <input type="file" class="form-control input-lg" name='title_imgMF' id='title_imgMF' size='40'>
                    <br>
                     Preview(미리보기) 이미지는 자동 생성됩니다.
                  </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-11">
                   <label for="p_date" class="text-black">공연 일시</label>
                     <input type="text" name='p_date' id='p_date1' size='20'> ~
                     <input type="text" name='p_date' id='p_date2' size='20'>
                  </div>
                </div>

                <div class="form-group row">
                  <div class="col-md-11">
                 <label for="contents" class="text-black">상세내용</label>
                    <textarea class="form-control input-lg" name='contents' id='contents'  rows='10'>"더 놀라운 건 '이 지금'부터야" 아이유 10주년 투어 콘서트 '이 지금 dlwlrma'의 영화같은 앙코르!</textarea>
                  </div>
                </div>
                <div class="form-group row">
                  <div class="col-md-11">
                 <label for="file1MF" class="text-black">업로드 파일</label>
                   <input type="file" class="form-control input-lg" name='file1MF' id='file1MF' size='40'>
                  </div>
                </div>
                  <div class="form-group row">
                    <div class="col-md-11">
                      <input type='text' class="form-control input-lg" name='word' id='word' value='' placeholder="검색어">
                    </div>
                  </div>
                  <div class="form-group row">
                  <div class="col-lg-4">
                    <button type="submit">등록</button>
                    <button type="button" onclick="location.href='./list_by_category_paging.do?s_categoryno=${categoryVO.s_categoryno}&word=${param.word}&nowPage=1'">취소[목록]</button>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' /> 
</DIV> <!-- container END -->
</body>
 
</html> 