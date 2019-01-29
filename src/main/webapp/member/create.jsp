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
    $.cookie('checkEmail', 'FALSE'); // Cookie 생성
    $('#popup').hide();
  });

  function checkEmail() {
    var frm = $('#frm');
    var params = 'm_email=' + $('#m_email', frm).val(); // #: m_email
    // alert('params: ' + params);

    $.ajax({
      url : "./checkEmail.do",
      type : "GET",
      cache : false,
      dataType : "json", // or html
      data : params,
      success : function(data) {
        var msg = "";

        if (data.cnt > 0) {
          $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap
          msg = "이미 사용중인 『Email』 입니다.";
        } else {
          $('#modal_content').attr('class', 'alert alert-success'); // Bootstrap
          msg = "사용 가능한 『Email』 입니다.";

          $.cookie('checkEmail', 'TRUE'); // Cookie 값 변경
        }

        $('#modal_title').html('Email 중복 확인');
        $('#modal_content').html(msg);
        $('#modal_panel').modal(); // 다이얼로그 출력   
      },
      // 통신 에러, 요청 실패, 200 아닌 경우, dataType이 다른경우
      error : function(request, status, error) {
        var msg = "에러가 발생했습니다. <br><br>";
        msg += "다시 시도해주세요.<br><br>";
        msg += "request.status: " + request.status + "<br>";
        msg += "request.responseText: " + request.responseText + "<br>";
        msg += "status: " + status + "<br>";
        msg += "error: " + error;

        // var panel = "";
        // panel += "<DIV id='panel' class='popup1' style='height: 350px;'>";
        // panel += msg;
        // panel += "<br>[<A href=\"javascript: $('#main_panel').hide()\">CLOSE</A>]";
        // panel += "</DIV>";

        // $('#main_panel').html(panel);      
        // $('#main_panel').show();
        // id_span.html(msg);
        $('#modal_title').html('Email 중복 확인');
        $('#modal_content').attr('class', 'alert alert-danger');
        $('#modal_content').html(msg);
        $('#modal_panel').modal(); // 다이얼로그 출력   
      }
    });

  }

  function checkNickname() {
    var frm = $('#frm');
    var params = 'm_nickname=' + $('#m_nickname', frm).val(); // #: m_nickname
    // alert('params: ' + params);

    $.ajax({
      url : "./checkNickname.do",
      type : "GET",
      cache : false,
      dataType : "json", // or html
      data : params,
      success : function(data) {
        var msg = "";

        if (data.cnt > 0) {
          $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap
          msg = "이미 사용중인 『닉네임』 입니다.";
        } else {
          $('#modal_content').attr('class', 'alert alert-success'); // Bootstrap
          msg = "사용 가능한 『닉네임』 입니다.";

          $.cookie('checkNickname', 'TRUE'); // Cookie 값 변경
        }

        $('#modal_title').html('닉네임 중복 확인');
        $('#modal_content').html(msg);
        $('#modal_panel').modal(); // 다이얼로그 출력   
      },
      // 통신 에러, 요청 실패, 200 아닌 경우, dataType이 다른경우
      error : function(request, status, error) {
        var msg = "에러가 발생했습니다. <br><br>";
        msg += "다시 시도해주세요.<br><br>";
        msg += "request.status: " + request.status + "<br>";
        msg += "request.responseText: " + request.responseText + "<br>";
        msg += "status: " + status + "<br>";
        msg += "error: " + error;

        // var panel = "";
        // panel += "<DIV id='panel' class='popup1' style='height: 350px;'>";
        // panel += msg;
        // panel += "<br>[<A href=\"javascript: $('#main_panel').hide()\">CLOSE</A>]";
        // panel += "</DIV>";

        // $('#main_panel').html(panel);      
        // $('#main_panel').show();
        // id_span.html(msg);
        $('#modal_title').html('닉네임 중복 확인');
        $('#modal_content').attr('class', 'alert alert-danger');
        $('#modal_content').html(msg);
        $('#modal_panel').modal(); // 다이얼로그 출력   
      }
    });

  }

  function send() {
    var check_e = $.cookie('checkEmail'); // 쿠키값
    var check_n = $.cookie('checkNickname');

    if (check_e != 'TRUE' || check_n != 'TRUE') {
      var msg = "Email or 닉네임 중복확인이 되지 않았습니다.<br>";
      msg += "Email or 닉네임 [중복확인] 버튼을 클릭하세요.<br>";

      $('#modal_title').html('Email 체크 확인');
      $('#modal_title').html('닉네임 체크 확인');
      $('#modal_content').attr('class', 'alert alert-danger');
      $('#modal_content').html(msg);
      $('#modal_panel').modal(); // 다이얼로그 출력   

      return false; // submit 중지
    }

    if ($('#m_passwd').val() != $('#m_passwd2').val()) {
      var msg = "입력된 패스워드가 일치하지 않습니다.<br>";
      msg += "패스워드를 다시 입력해주세요.<br>";

      $('#modal_title').html('패스워드 일치여부 확인');
      $('#modal_content').attr('class', 'alert alert-danger');
      $('#modal_content').html(msg);
      $('#modal_panel').modal(); // 다이얼로그 출력   

      return false; // submit 중지
    }

    return true; // submit 진행
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
            <a href='${pageContext.request.contextPath}/index.jsp'>Home</a><span
              class="mx-2 mb-0">/</span> <strong class="text-black">Login</strong>
          </div>
        </div>
      </div>
    </div>

    <div class="site-section">
      <DIV class='container'>
        <div class="row mb-5">
          <div class="col-md-12">
            <div class="border p-4 rounded" role="alert">
              이미 회원이시다면 <a
                href="${pageContext.request.contextPath }/member/login.do">여기</a>
              를 눌러 로그인해주세요!
            </div>
          </div>
        </div>
      

        

        <DIV class='title_line'>회원가입</DIV>
        <FORM name='frm' id='frm' method='POST' action='./create.do'
          onsubmit="return send();" class="form-horizontal">
          <div class="row">
            <div class="col-md-6 mb-5 mb-md-0">
              <h2 class="h3 mb-3 text-black">회원 가입</h2>
              <div class="p-3 p-lg-5 border">
                <div class="form-group">
                  <label for="m_name" class="text-black">성명 <span
                    class="text-danger">*</span></label> <input type='text'
                    class="form-control" name='m_name' id='m_name'
                    value='홍길동' required="required" placeholder="성명">
                </div>

                <div class="form-group row">
                  <div class="col-md-6">
                    <label for="m_email" class="text-black">이메일(Email)
                    </label> <input type='text' class="form-control"
                      name='m_email' id='m_email' value='abc@abc.com'
                      required="required" placeholder="이메일(아이디)"
                      autofocus="autofocus">
                    <button type='button' onclick="checkEmail()"
                      class="">중복확인</button>
                    <SPAN id='id_span'></SPAN>
                    <!-- ID 중복 관련 메시지 -->
                  </div>
                  <div class="col-md-6">로그인 시 필요합니다.</div>
                </div>

                <div class="form-group row">
                  <div class="col-md-12">
                    <label for="m_nickname" class="text-black">닉네임
                      <span class="text-danger">*</span>
                    </label> <input type='text' class="form-control"
                      name='m_nickname' id='m_nickname' value='멋쟁이'
                      required="required" placeholder="닉네임"
                      autofocus="autofocus">
                    <button type='button' onclick="checkNickname()"
                      class="">중복확인</button>
                  </div>
                </div>

                <div class="form-group row">
                  <div class="col-md-12">
                    <label for="m_passwd" class="text-black">패스워드
                      / 패스워드 확인 <span class="text-danger">*</span>
                    </label> <input type='password' class="form-control"
                      name='m_passwd' id='m_passwd' value='1234'
                      required="required" style='width: 50%;'
                      placeholder="패스워드"> <input type="password"
                      class="form-control" name='m_passwd2'
                      id='m_passwd2' value='1234' required="required"
                      style='width: 50%;' placeholder="패스워드 확인">
                  </div>
                </div>
              </div>
            </div>

            <div class="col-md-6">
              <div class="row mb-5">
                <div class="col-md-12">
                  <h2 class="h3 mb-3 text-black"></h2>
                  <div class="p-3 p-lg-5 border">
                    <div class="form-group row">
                      <div class="col-md-12">
                        <label for="m_age" class="text-black">나이
                          <span class="text-danger">*</span>
                        </label> <input type='text' class="form-control"
                          name='m_age' id='m_age' value='20'
                          required="required" style='width: 30%;'
                          placeholder="나이">
                      </div>
                    </div>
                    <div class="form-group">
                      <div class="col-md-offset-2 col-md-10">
                        <button type="submit"
                          class="btn btn-primary btn-md">가입</button>
                        <button type="button"
                          onclick="location.href='../index.jsp'"
                          class="btn btn-primary btn-md">취소</button>

                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
    </FORM>
    </div>
    </div>
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


