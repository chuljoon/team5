<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>공연 정보 사이트</title>
<meta name="viewport"
  content="width=device-width, initial-scale=1, shrink-to-fit=no">

<script type="text/JavaScript"
  src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script
  src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<!-- <link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"> 
<link rel="stylesheet"
  href="https://fonts.googleapis.com/css?family=Mukta:300,400,700">
<link rel="stylesheet" href="../fonts/icomoon/style.css">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/magnific-popup.css">
<link rel="stylesheet" href="../css/jquery-ui.css">
<link rel="stylesheet" href="../css/owl.carousel.min.css">
<link rel="stylesheet" href="../css/owl.theme.default.min.css">
<link rel="stylesheet" href="../css/aos.css">
<link rel="stylesheet" href="../css/style.css">


<script
  src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>

<script type="text/javascript">
  $(function() {
 
  });
 
  function send() {
    if ($('#new_passwd').val() != $('#new_passwd2').val()) {
      var msg = "입력된 패스워드가 일치하지 않습니다.<br>";
      msg += "패스워드를 다시 입력해주세요.<br>";
 
      $('#modal_title').html('패스워드 일치여부 확인');
      $('#modal_content').attr('class', 'alert alert-danger');
      $('#modal_content').html(msg);
      $('#modal_panel').modal(); // 다이얼로그 출력   
      
      return false; // submit 중지
    }
    
    return true; 
  }
</script>
 
</head> 
 
 
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>
  <DIV id='main_panel'></DIV>
  <!-- Modal -->
        <div class="modal fade" id="modal_panel" role="dialog">
          <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h4 class="modal-title" id='modal_title'></h4>
              </div>
              <div class="modal-body">
                <p id='modal_content'></p>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default"
                  data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
        <!-- Modal END -->
    <div class="bg-light py-3">
      <div class="container">
        <div class="row">
          <div class="col-md-12 mb-0">
            <a href='${pageContext.request.contextPath}/index.jsp'>Home</a><span class="mx-2 mb-0">/</span>
            <a href='${pageContext.request.contextPath}/member/list.do' >Member</a><span class="mx-2 mb-0">/</span> 
            <strong class="text-black">패스워드 변경</strong>
          </div>
        </div>
      </div>
    </div>
 
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do'>회원 가입</A>
    <span class='menu_divide' >│</span> 
    <A href='./list.do'>목록</A>
  </ASIDE> 
 
  <div class='menu_line'></div>
    
  <DIV class='title_line'>패스워드 변경</DIV>
  <FORM name='frm' method='POST' action='./passwd_update.do' onsubmit="return send();" class="form-horizontal">
 
    <div class="form-group">
      <label for="m_passwd" class="col-md-6 control-label">현재 패스워드</label>    
      <div class="col-md-6">
        <input type='password' class="form-control input-md" name='m_passwd' id='m_passwd' value='' required="required" style='width: 50%;' placeholder="패스워드">
      </div>
    </div>   
                    
    <div class="form-group">
      <label for="m_passwd" class="col-md-6 control-label">새로운 패스워드</label>    
      <div class="col-md-6">
        <input type='password' class="form-control input-md" name='new_passwd' id='new_passwd' value='' required="required" style='width: 50%;' placeholder="새로운 패스워드">
      </div>
    </div>   
 
    <div class="form-group">
      <label for="m_passwd2" class="col-md-6 control-label">새로운 패스워드 확인</label>    
      <div class="col-md-6">
        <input type='password' class="form-control input-md" name='new_passwd2' id='new_passwd2' value='' required="required" style='width: 50%;' placeholder="새로운 패스워드 확인">
      </div>
    </div>   
    
    <div class="form-group">
      <div class="col-md-offset-6 col-md-6">
        <button type="submit" class="btn btn-primary btn-md">변경</button>
        <button type="button" onclick="location.href='./list.do'" class="btn btn-primary btn-md">취소</button>
 
      </div>
    </div>   
</FORM>
 
</DIV>
      <jsp:include page="/menu/bottom.jsp" flush='false' />
      </DIV>
    <!-- container END -->

    <!-- <script src="../js/jquery-3.3.1.min.js"></script> -->
    <script src="../js/jquery-ui.js"></script>
    <script src="../js/popper.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/owl.carousel.min.js"></script>
    <script src="../js/jquery.magnific-popup.min.js"></script>
    <script src="../js/aos.js"></script>
    <script src="../js/main.js"></script>
</body>

</html>



 
 
 