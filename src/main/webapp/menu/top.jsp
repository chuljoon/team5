<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <!-- 화면 상단 메뉴 -->
    <header class="site-navbar" role="banner">
      <div class="site-navbar-top">
        <div class="container">
          <div class="row align-items-center">

            <div class="col-6 col-md-4 order-2 order-md-1 site-search-icon text-left">
              <form action="" class="site-block-top-search">
                <span class="icon icon-search2"></span>
                <input type="text" class="form-control border-0" placeholder="Search">
              </form>
            </div>

            <div class="col-12 mb-3 mb-md-0 col-md-4 order-1 order-md-2 text-center">
              <div class="site-logo">
                <a href='${pageContext.request.contextPath}/index.do' class="js-logo-clone">Shoppers</a>
              </div>
            </div>

            <div class="col-6 col-md-4 order-3 order-md-3 text-right">
              <div class="site-top-icons">

                  <c:choose>
                    <c:when test="${sessionScope.m_email == NULL }">
                    <a href='${pageContext.request.contextPath}/member/login.do'><span class="icon icon-person" title='로그인'></span></a>
                    <%-- <a href='${pageContext.request.contextPath}/admin/login.do'><span class="icon icon-user-md" title='관리자 로그인'></span></a> --%>
                    </c:when>
                    <c:when test="${memberVO.m_act == M}">
                    <a href='${pageContext.request.contextPath}/member/list.do'><span class="icon icon-view_list" title='회원 리스트'></span></a>
                      ${sessionScope.m_email } <a href='${pageContext.request.contextPath}/member/logout.do' >Logout</a>
                    </c:when>
                    <c:otherwise>
                      ${sessionScope.m_email } <a href='${pageContext.request.contextPath}/member/logout.do' >Logout</a>
                      <%-- <a href='${pageContext.request.contextPath}/member/login.do'><span class="icon icon-person"></span></a> --%>
                    </c:otherwise>
                  </c:choose>

              </div> 
            </div>

          </div>
        </div>
      </div> 
      <nav class="site-navigation text-right text-md-center" role="navigation">
        <div class="container">
          <ul class="site-menu js-clone-nav d-none d-md-block">
            <li class="has-children active">
              <a href="${pageContext.request.contextPath}/index.do">Home</a>
              <ul class="dropdown">
                <li><a href="#">Menu One</a></li>
                <li><a href="#">Menu Two</a></li>
                <li><a href="#">Menu Three</a></li>
                <li class="has-children">
                  <a href="#">Sub Menu</a>
                  <ul class="dropdown">
                    <li><a href="#">Menu One</a></li>
                    <li><a href="#">Menu Two</a></li>
                    <li><a href="#">Menu Three</a></li>
                  </ul>
                </li>
              </ul>
            </li>
            <li class="has-children">
              <a href="about.html">About</a>
              <ul class="dropdown">
                <li><a href="#">Menu One</a></li>
                <li><a href="#">Menu Two</a></li>
                <li><a href="#">Menu Three</a></li>
              </ul>
            </li>
            <li><a href="shop.html">Shop</a></li>
            <li><a href="#">Catalogue</a></li>
            <li><a href='${pageContext.request.contextPath}/gallery/list.do' >갤러리</a>
            <li class= "has-children"> 
              <a href='${pageContext.request.contextPath}/question/list.do' >Q/A</a>
              <ul class= "dropdown"> 
                <li><a href='${pageContext.request.contextPath}/question/list.do' >전체 질문 목록</a></li>
                <li><a href='${pageContext.request.contextPath}/question/list_by_member_search_paging.do?memberno=${sessionScope.memberno}&nowPage=1' >1 : 1 문의</a></li>
              </ul>
            </li>
            <%-- <li><a href='${pageContext.request.contextPath}/member/list.do' >회원 리스트</a></li> --%>
          </ul>
        </div>
      </nav>
    </header>
   
   
   