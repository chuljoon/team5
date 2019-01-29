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

</head> 
<body>
<div class="site-wrap">
  <jsp:include page="/menu/top.jsp" flush='false' />
    <div class="bg-light py-3">
      <div class="container">
        <div class="row">
          <div class="col-md-12 mb-0">
            <a href='${pageContext.request.contextPath}/index.do' >Home</a><span class="mx-2 mb-0">/</span>
            <strong class="text-black">Login</strong>
          </div>
        </div>
      </div>
    </div>

      <DIV class='container'>
        <DIV class='title_line' style='width: 20%;'>로그인</DIV>
           <DIV style='width: 80%; margin: 0px auto;'>
              <FORM name='frm' method='POST' action='./login.do'>
                <!-- <div class="block-7">
                  <label for="email_subscribe" class="footer-heading">Subscribe</label>
                  <div class="form-group">
                    <input type="text" class="form-control py-4" id="email_subscribe" placeholder="Email">
                    <input type="submit" class="btn btn-sm btn-primary" value="Send"> 
                  </div>
                </div> -->
                <div class="block-7">
                  <label for="m_email" class="footer-heading">이메일</label>    
                  <div class="form-group">
                    <input type='text' class="form-control py-4" name='m_email' id='m_email' value='${ck_m_email }' required="required" style='width: 30%;' placeholder="이메일" autofocus="autofocus">
                    <Label>   
                    <input type='checkbox' name='m_email_save' value='Y' 
                              ${ck_m_email_save == 'Y' ? "checked='checked'" : "" }> 저장
                    </Label>
                  </div>
              </div>   
              <div class="block-7">
                <label for="m_passwd" class="footer-heading">패스워드</label>    
                <div class="form-group">
                  <input type='password' class="form-control py-4" name='m_passwd' id='m_passwd' value='${ck_m_passwd }' required="required" style='width: 30%;' placeholder="패스워드">
                  <Label>
                  <input type='checkbox' name='m_passwd_save' value='Y' 
                            ${ck_m_passwd_save == 'Y' ? "checked='checked'" : "" }> 저장
                  </Label>
                </div>
              </div>   
              <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                  <button type="submit" class="btn btn-sm btn-primary">로그인</button>
                  <button type="button" onclick="history.back()" class="btn btn-sm btn-primary">취소</button>
                  <button type="button" onclick="location.href='${pageContext.request.contextPath}/member/create.do'" class="btn btn-sm btn-primary">회원 가입</button>    
                  
                  
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
 
 
 