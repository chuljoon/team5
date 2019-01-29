<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
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

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
<script type="text/javascript">
  $(function() {
    action_cancel(); // panel 출력 초기화
    
    list();  // 모든 카테고리 목록
    
  });
  
  // 모든 카테고리 목록
  function list() {
    $.ajax({
      url: "./list_by_categrp_json.do", // 요청을 보낼주소
      type: "get",  // or get
      cache: false,
      dataType: "json", // 응답 데이터 형식, or json
      data: "m_categrpno=" + ${param.m_categrpno}, 
      // Ajax 통신 성공, JSP 정상 처리
      success: function(rdata) { // callback 함수
        var panel = '';
 
        for(index=0; index < rdata.length; index++) {
          panel += "<TR>";
          panel += "<TD style='text-align: center ;'>"+(index+1)+"</TD>";
          panel += "<TD>"+rdata[index].name+"</TD>";
          panel += "<TD><A href='../contents/list_by_category.do?s_categoryno="+rdata[index].s_categoryno+"'>"+rdata[index].title+"</A></TD>";
          panel += "<TD  style='text-align: center ;'>"+rdata[index].seqno+"</TD>";
          panel += "<TD  style='text-align: center ;'>"+rdata[index].visible+"</TD>";
          panel += "<TD>"+rdata[index].ids+"</TD>";
          panel += "<TD style='text-align: center;'>"; 
          panel += "  <A href=\"javascript:update("+rdata[index].s_categoryno+")\"><IMG src='./images/update.png' title='수정'></A>";  
          panel += "  <A href=\"javascript:deleteForm("+rdata[index].s_categoryno+")\"><IMG src='./images/delete.png' title='삭제'></A>";
          panel += "  <A href=\"javascript:seqnoUp("+rdata[index].s_categoryno+")\"><IMG src='./images/up.png' title='우선 순위 높임' style='width: 20px;'></A>";
          panel += "  <A href=\"javascript:seqnoDown("+rdata[index].s_categoryno+")\"><IMG src='./images/down.png' title='우선 순위 감소' style='width: 20px;'></A>"; 
          panel += "</TD>";
          panel += "</TR>";
        }
        // alert(panel);
        // return;
        $('#tbody_panel').empty();
        $('#tbody_panel').append(panel);
      },
      // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
      error: function(request, status, error) { // callback 함수
        var panel = '';
        panel += "<DIV id='panel' class='popup1' style='heigth: 450px;'>";
        panel += '  ERROR<br><br>';
        panel += '  <strong>request.status</strong><br>'+request.status + '<hr>';
        panel += '  <strong>error</strong><br>'+error + '<hr>';
        panel += "  <br><button type='button' onclick=\"$('#main_panel').hide();\">닫기</button>";
        panel += "</DIV>";
        
        $('#main_panel').html(panel);
        $('#main_panel').show();
      }
    });
  }

  // 등록 처리
  function create() {
    $.ajax({
      url: "./create_json.do", // 요청을 보낼주소
      type: "post",  // or get
      cache: false,
      dataType: "json", // 응답 데이터 형식, or json
      data: $('#frm_create').serialize(), 
      // Ajax 통신 성공, JSP 정상 처리
      success: function(rdata) { // callback 함수
        var panel = '';
        panel += "<DIV id='panel' class='popup1' style='heigth: 250px;'>";
        panel += '  알림<br>';
        for(index=0; index < rdata.msgs.length; index++) {
          panel += rdata.msgs[index]+'<br>';
        }
        panel += "  <button type='button' onclick=\"$('#main_panel').hide();\" class='popup_button'>닫기</button>";
        panel += "</DIV>";
        
        list();  // 전체 카테고리 목록
        
        $('#main_panel').html(panel);
        $('#main_panel').show();
      },
      // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
      error: function(request, status, error) { // callback 함수
        var panel = '';
        panel += "<DIV id='panel' class='popup1' style='heigth: 450px;'>";
        panel += '  ERROR<br><br>';
        panel += '  <strong>request.status</strong><br>'+request.status + '<hr>';
        panel += '  <strong>error</strong><br>'+error + '<hr>';
        panel += "  <br><button type='button' onclick=\"$('#main_panel').hide();\">닫기</button>";
        panel += "</DIV>";
        
        $('#main_panel').html(panel);
        $('#main_panel').show();
 
      }
    });
  }
  
  // 수정폼
  function update(s_categoryno) {
    $('#panel_create').hide();
    $('#panel_update').show();
    
    $.ajax({
      url: "./update.do", // 요청을 보낼주소
      type: "get",  // or get
      cache: false,
      dataType: "json", // 응답 데이터 형식, or json
      data: 's_categoryno=' +s_categoryno,  // $('#frm').serialize(), 
      // Ajax 통신 성공, JSP 정상 처리
      success: function(rdata) { // callback 함수
        var frm_update = $('#frm_update');
        $('#m_categrpno', frm_update).val(rdata.m_categrpno);
        $('#s_categoryno', frm_update).val(rdata.s_categoryno);        
        $('#title', frm_update).val(rdata.title);
        $('#seqno', frm_update).val(rdata.seqno);
        $('#visible', frm_update).val(rdata.visible);
        $('#ids', frm_update).val(rdata.ids);        
      },
      // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
      error: function(request, status, error) { // callback 함수
        var panel = '';
        panel += "<DIV id='panel' class='popup1' style='heigth: 350px;'>";
        panel += '  ERROR<br><br>';
        panel += '  <strong>request.status</strong><br>'+request.status + '<hr>';
        panel += '  <strong>error</strong><br>'+error + '<hr>';
        panel += "  <br><button type='button' onclick=\"$('#main_panel').hide();\">닫기</button>";
        panel += "</DIV>";
        
        $('#main_panel').html(panel);
        $('#main_panel').show();

      }
    });
  } 

  // 수정 처리
  function update_submit() {
    $.ajax({
      url: "./update_json.do", // 요청을 보낼주소
      type: "post",  // or get
      cache: false,
      dataType: "json", // 응답 데이터 형식, or json
      data: $('#frm_update').serialize(), 
      // Ajax 통신 성공, JSP 정상 처리
      success: function(rdata) { // callback 함수
        var panel = '';
        panel += "<DIV id='panel' class='popup1' style='heigth: 250px;'>";
        panel += '  알림<br>';
        for(index=0; index < rdata.msgs.length; index++) {
          panel += rdata.msgs[index]+'<br>';
        }
        panel += "  <button type='button' onclick=\"$('#main_panel').hide();\" class='popup_button'>닫기</button>";
        panel += "</DIV>";
        
        list();  // 전체 카테고리 목록
        
        $('#main_panel').html(panel);
        $('#main_panel').show();
      },
      // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
      error: function(request, status, error) { // callback 함수
        var panel = '';
        panel += "<DIV id='panel' class='popup1' style='heigth: 450px;'>";
        panel += '  ERROR<br><br>';
        panel += '  <strong>request.status</strong><br>'+request.status + '<hr>';
        panel += '  <strong>error</strong><br>'+error + '<hr>';
        panel += "  <br><button type='button' onclick=\"$('#main_panel').hide();\">닫기</button>";
        panel += "</DIV>";
        
        $('#main_panel').html(panel);
        $('#main_panel').show();
 
      }
    });
  }
  
  function deleteForm(s_categoryno) {
    $('#panel_create').hide();
    $('#panel_update').hide();
    $('#panel_delete').show();
    
    $.ajax({
      url: "./delete.do", // 요청을 보낼주소
      type: "get",  // or get
      cache: false,
      dataType: "json", // 응답 데이터 형식, or json
      data: 's_categoryno=' +s_categoryno,  // $('#frm').serialize(), 
      // Ajax 통신 성공, JSP 정상 처리
      success: function(rdata) { // callback 함수
        var frm_delete = $('#frm_delete');
        $('#m_categrpno', frm_delete).val(rdata.m_categrpno); 
        $('#s_categoryno', frm_delete).val(rdata.s_categoryno);        
        $('#s_category_title').html(rdata.title); // 카테고리 이름
      },
      // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
      error: function(request, status, error) { // callback 함수
        var panel = '';
        panel += "<DIV id='panel' class='popup1' style='heigth: 350px;'>";
        panel += '  ERROR<br><br>';
        panel += '  <strong>request.status</strong><br>'+request.status + '<hr>';
        panel += '  <strong>error</strong><br>'+error + '<hr>';
        panel += "  <br><button type='button' onclick=\"$('#main_panel').hide();\">닫기</button>";
        panel += "</DIV>";
        
        $('#main_panel').html(panel);
        $('#main_panel').show();

      }
    });
  }

  // 삭제 처리
  function delete_submit() {
    $.ajax({
      url: "./delete.do", // 요청을 보낼주소
      type: "post",  // or get
      cache: false,
      dataType: "json", // 응답 데이터 형식, or json
      data: $('#frm_delete').serialize(), 
      // Ajax 통신 성공, JSP 정상 처리
      success: function(rdata) { // callback 함수
        var panel = '';
        panel += "<DIV id='panel' class='popup1' style='heigth: 250px;'>";
        panel += '  알림<br>';
        for(index=0; index < rdata.msgs.length; index++) {
          panel += rdata.msgs[index]+'<br>';
        }
        panel += "  <button type='button' onclick=\"$('#main_panel').hide();\" class='popup_button'>닫기</button>";
        panel += "</DIV>";
        
        action_cancel();
        
        list();  // 전체 카테고리 목록
        
        $('#main_panel').html(panel);
        $('#main_panel').show();
        
        // $('#frm_create')[0].reset(); // id가 frm_create인 첫번째폼을 reset
      },
      // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
      error: function(request, status, error) { // callback 함수
        var panel = '';
        panel += "<DIV id='panel' class='popup1' style='heigth: 450px;'>";
        panel += '  ERROR<br><br>';
        panel += '  <strong>request.status</strong><br>'+request.status + '<hr>';
        panel += '  <strong>error</strong><br>'+error + '<hr>';
        panel += "  <br><button type='button' onclick=\"$('#main_panel').hide();\">닫기</button>";
        panel += "</DIV>";
        
        $('#main_panel').html(panel);
        $('#main_panel').show();
 
      }
    });
  }
  
  function seqnoUp(s_categoryno) {
    var frm_seqno = $('#frm_seqno');
    frm_seqno.attr('action', './update_seqno_up.do');
    $('#s_categoryno', frm_seqno).val(s_categoryno);
    frm_seqno.submit();
  }

  function seqnoDown(s_categoryno) {
    var frm_seqno = $('#frm_seqno');
    frm_seqno.attr('action', './update_seqno_down.do');
    $('#s_categoryno', frm_seqno).val(s_categoryno);
    frm_seqno.submit();
  }
  
  function action_cancel() {
    $('#panel_update').hide();
    $('#panel_delete').hide();
    $('#panel_create').show();

    $('#frm_create')[0].reset(); // id가 frm_create인 첫번째폼을 reset
  }
  
  
</script>
 
</head> 
 
<body>
<DIV class='container' style='width: 90%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style='width: 100%;'>
  
  <DIV id='main_panel'></DIV>
  
  <DIV class='title_line'>${categrpVO.name } 카테고리</DIV>
 
  <DIV id='panel_delete' style='padding: 10px 0px 10px 0px; background-color: #FFAAAA; width: 100%; text-align: center;'>
    <FORM name='frm_delete' id='frm_delete'>
      <input type='hidden' name='m_categrpno' id='m_categrpno' value=''>
      <input type='hidden' name='s_categoryno' id='s_categoryno' value=''>
      
      <span id='s_category_title'></span> 카테고리를 삭제하시겠습니까?
      삭제하면 복구 할 수 없습니다.
      <button type="button" id='submit' onclick="delete_submit()">삭제</button>
      <button type="button" onclick="action_cancel()">취소</button>
    </FORM>
  </DIV>
  
  <!-- 우선 순위 증가 감소 폼 -->
  <FORM name='frm_seqno' id='frm_seqno' method='post' action=''>
    <input type='hidden' name='m_categrpno' id='m_categrpno' value='${categoryVO.m_categrpno }'>
    <input type='hidden' name='s_categoryno' id='s_categoryno' value=''>
  </FORM>
  
  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F5F5F5; width: 100%; text-align: center;'>
    <FORM name='frm_create' id='frm_create' method='POST' action='./create.do'>
       
      <label for='title'>메인 카테고리</label>
       <select name='m_categrpno' id='m_categrpno'>
         <c:forEach var="categrpVO" items="${categrp_list}">
           <option value='${categrpVO.m_categrpno }'>${categrpVO.name}</option>
         </c:forEach>
       </select>
             
      <label for='title'>카테고리 이름</label>
      <input type='text' name='title' id='title' size='10' value='' required="required" style='width: 10%;'>
 
      <label for='seqno'>출력 순서</label>
      <input type='number' name='seqno' id='seqno' value='' required="required" style='width: 5%;'>
  
      <label for='visible'>출력 형식</label>
       <select name='visible'>
         <option value='Y' selected="selected">Y</option>
         <option value='N'>N</option>
       </select>
       
      <label for='ids'>접근 계정</label>
      <input type='text' name='ids' id='ids' value='admin' required="required" style='width: 10%;'>
 
      <button type="button" id='submit' onclick="create()">등록</button>
      <button type="button" onclick="create_update_cancel()">취소</button>
    </FORM>
  </DIV>
  
  <!--  수정폼은 항상 PK 전달한다. -->
  <DIV id='panel_update' style='padding: 10px 0px 10px 0px; background-color: #DDDDDD; width: 100%; text-align: center;'>  
    <FORM name='frm_update' id='frm_update' method='POST' action='./update.do'>
      <input type='hidden' name='s_categoryno' id='s_categoryno' value=''> 
 
      <label for='title'>메인 카테고리 번호</label>
      <input type='number' name='m_categrpno' id='m_categrpno' size='7' value='' required="required" style='width: 3%;'>
        
      <label for='name'>카테고리 이름</label>
      <input type='text' name='title' id='title' size='15' value='' required="required" style='width: 20%;'>
 
      <label for='seqno'>출력 순서</label>
      <input type='number' name='seqno' id='seqno' value='' required="required" style='width: 5%;'>
  
      <label for='visible'>출력 형식</label>
       <select name='visible'>
         <option value='Y' selected="selected">Y</option>
         <option value='N'>N</option>
       </select>
 
      <label for='ids'>접근 계정</label>
      <input type='text' name='ids' id='ids' value='admin' required="required" style='width: 10%;'>
 
      <button type="button" id='submit' onclick="update_submit()">저장</button>
      <button type="button" onclick="create_update_cancel()">취소</button>
    </FORM>
  </DIV>
  
<TABLE class='table table-striped'>
  <colgroup>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 25%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 15%;'/>
 
  </colgroup>
  <thead>  
  <TR>

    <TH style='text-align: center ;'>번호</TH>
    <TH style='text-align: center ;'>그룹</TH>
    <TH style='text-align: center ;'>카테고리 이름</TH>
    <TH style='text-align: center ;'>순서</TH>
    <TH style='text-align: center ;'>출력</TH>
    <TH style='text-align: center ;'>접근계정</TH>
    <TH style='text-align: center ;'>기타</TH>
    
  </TR>
  </thead>
 
  <tbody id='tbody_panel' data-nowPage='0' data-endPage='0'>
  </tbody>
  
</TABLE>
 
 
</DIV> <!-- content END -->
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
 