<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
  <head>
  <meta charset="utf-8">
    <title> 공연 정보 사이트 </title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta:300,400,700"> 
    <link rel="stylesheet" href="fonts/icomoon/style.css">

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">
    <link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">


    <link rel="stylesheet" href="css/aos.css">
    
    <link rel="stylesheet" href="css/style.css">
    
  </head>
  <body>
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/jquery.magnific-popup.min.js"></script>
    <script src="js/aos.js"></script>
    <script src="js/main.js"></script>
  
  <div class="site-wrap">
  <jsp:include page="/menu/top.jsp" flush='false' />
  
  
    <div class="site-section block-3 site-blocks-2 bg-light">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-md-7 site-section-heading text-center pt-4">
            <h2>현재 예매 진행중인 콘서트</h2>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <div class="nonloop-block-3 owl-carousel">
              <div class="item">
                <div class="block-4 text-center">
                  <figure class="block-4-image">
                    <a href="${pageContext.request.contextPath}/contents/read.do?contentsno=1&s_categoryno=1"><img src="images/1.jpg" alt="Image placeholder" class="img-fluid" style="height: 400px; width: 300px;"></a>
                  </figure>
                  <div class="block-4-text p-4">
                    <h3><a href="${pageContext.request.contextPath}/contents/read.do?contentsno=1&s_categoryno=1">2018 아이유 10주년 투어 콘서트 〈이 지금 dlwlrma〉－제주</a></h3>
                    <p class="text-primary font-weight-bold">2019.01.05 ~ 2019.01.05</p>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="block-4 text-center">
                  <figure class="block-4-image">
                    <a href="${pageContext.request.contextPath}/contents/read.do?contentsno=5&s_categoryno=7"><img src="images/2.jpg" alt="Image placeholder" class="img-fluid" style="height: 400px; width: 300px;"></a>
                  </figure>
                  <div class="block-4-text p-4">
                    <h3><a href="${pageContext.request.contextPath}/contents/read.do?contentsno=5&s_categoryno=7">앤마리 첫 단독 내한공연（ANNE-MARIE 1ST LIVE IN SEOUL）</a></h3>
                    <p class="text-primary font-weight-bold">2019.04.13 ~ 2019.04.13</p>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="block-4 text-center">
                  <figure class="block-4-image">
                    <a href="${pageContext.request.contextPath}/contents/read.do?contentsno=2&s_categoryno=2"><img src="images/3.jpg" alt="Image placeholder" class="img-fluid" style="height: 400px; width: 300px;"></a>
                  </figure>
                  <div class="block-4-text p-4">
                    <h3><a href="${pageContext.request.contextPath}/contents/read.do?contentsno=2&s_categoryno=2">VIXX N 팬미팅 2019 〈아！차학연〉</a></h3>
                    <p class="text-primary font-weight-bold">2019.02.16 ~ 2019.02.17</p>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="block-4 text-center">
                  <figure class="block-4-image">
                    <a href="${pageContext.request.contextPath}/contents/read.do?contentsno=6&s_categoryno=5"><img src="images/7.jpg" alt="Image placeholder" class="img-fluid" style="height: 400px; width: 300px;"></a>
                  </figure>
                  <div class="block-4-text p-4">
                    <h3><a href="${pageContext.request.contextPath}/contents/read.do?contentsno=6&s_categoryno=5">2019 WORLD DJ FESTIVAL（2019 월드 디제이 페스티벌）</a></h3>
                    <p class="text-primary font-weight-bold">2019.06.01 ~ 2019.06.01</p>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="block-4 text-center">
                  <figure class="block-4-image">
                    <a href="${pageContext.request.contextPath}/contents/read.do?contentsno=7&s_categoryno=3"><img src="images/6.jpg" alt="Image placeholder" class="img-fluid" style="height: 400px; width: 300px;"></a>
                  </figure>
                  <div class="block-4-text p-4">
                    <h3><a href="${pageContext.request.contextPath}/contents/read.do?contentsno=7&s_categoryno=3">플라네타리움 레코드（PLT）1st Tour Concert ‘WE GO HOLIC in Seoul’</a></h3>
                    <p class="text-primary font-weight-bold">2019.03.09 ~ 2019.03.09</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="site-section block-8">
      <div class="container">
        <div class="row justify-content-center  mb-5">
          <div class="col-md-7 site-section-heading text-center pt-4">
            <h2>가장 기대되는 콘서트</h2>
          </div>
        </div>
        <div class="row align-items-center">
          <div class="col-md-12 col-lg-7 mb-5">
            <img src="images/test.jpg" alt="Image placeholder" class="img-fluid rounded" style="height: 400px; width: 600px;">
          </div>
          <div class="col-md-12 col-lg-5 text-center pl-md-5">
            <h2>2019 LOVELYZ CONCERT 〈겨울나라의 러블리즈3〉</h2>
            <p class="post-meta mb-4"><span class="block-8-sep">&bullet;</span> 2019.02.14 ~ 2019.02.17</p>
            <p>첫눈과 함께 했던 그날을 기억하나요 우리가 함께 맞이할 세 번째 겨울은 또 어떤 모습일까요？ 러블리너스와 러블리즈가 다시 쓰는 2019년 겨울 동화 2019 겨울나라의 러블리즈3</p>
            <p><a href="${pageContext.request.contextPath}/contents/read.do?contentsno=3&s_categoryno=1" class="btn btn-primary btn-sm">바로가기</a></p>
          </div>
        </div>
      </div>
    </div>

  <jsp:include page="/menu/bottom.jsp" flush='false' /> 
  </div>

  </body>
</html>