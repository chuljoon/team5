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
  <script src="../js/jquery-3.3.1.min.js"></script>
  <script src="../js/jquery-ui.js"></script>
  <script src="../js/popper.min.js"></script>
  <script src="../js/bootstrap.min.js"></script>
  <script src="../js/owl.carousel.min.js"></script>
  <script src="../js/jquery.magnific-popup.min.js"></script>
  <script src="../js/aos.js"></script>

  <script src="../js/main.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
<script type="text/javascript">
  $(function() {
    
  });
  
  function update(m_categrpno) {
    $('#panel_create').hide();
    $('#panel_update').show();
    
    $.ajax({
      url: "./update.do", 
      type: "get", // or get
      cache: false,
      async: true, // true: 비동기
      dataType: "json", // 응답 형식, html, xml...
      // data: $('#frm').serialize(),  // 보내는 데이터
      data: 'm_categrpno='+m_categrpno,
      success: function(rdata) {
        // alert(rdata);
        var frm_update = $('#frm_update');
        $('#m_categrpno', frm_update).val(rdata.m_categrpno);
        $('#name', frm_update).val(rdata.name);
        $('#seqno', frm_update).val(rdata.seqno);
        $('#visible', frm_update).val(rdata.visible);
      },
      error: function(request, status, error) { // 응답 결과, 상태, 에러 내용
        var msg = 'ERROR<br><br>';
        msg += '<strong>request.status</strong><br>'+request.status + '<hr>';
        msg += '<strong>request.responseText</strong><br>'+request.responseText + '<hr>';
        msg += '<strong>status</strong><br>'+status + '<hr>';
        msg += '<strong>error</strong><br>'+error + '<hr>';

/*         var msg = '알림<br><br>';
        msg += '<strong>현재 시스템 정비중입니다.</strong><br>조속히 처리하겠습니다.<hr>';
        msg += '예상 종료 시간: 16:00'; */
          
        $('#main_panel').html(msg);
        $('#main_panel').show();
      }
     });

  }
  
  function create_update_cancel() {
    $('#panel_update').hide();
    $('#panel_delete').hide();
    $('#panel_create').show();
  }
  
  // 삭제 폼
  function deleteOne(m_categrpno) {
    $('#panel_create').hide();
    $('#panel_update').hide();
    $('#panel_delete').show();
    
    $.ajax({
      url: "./delete.do", 
      type: "get", // or get
      cache: false,
      async: true, // true: 비동기
      dataType: "json", // 응답 형식, html, xml...
      // data: $('#frm').serialize(),  // 보내는 데이터
      data: 'm_categrpno='+m_categrpno,
      success: function(rdata) {
        // alert(rdata);
        var frm_delete = $('#frm_delete');
        $('#m_categrpno', frm_delete).val(m_categrpno);
        
        var str = '';        
        // 소속된 카테고리 갯수를 출력 예정
        if (rdata.count_by_categrp > 0) {
          str = '<span style="color: #FF0000;">&apos;'+ rdata.name + '&apos; 카테고리에 [' + rdata.count_by_categrp + '] 건의 데이터가 등록되어있습니다.</span><br>';
          str += '카테고리에 등록된 데이터를 삭제해야 카테고리 그룹 삭제가 가능합니다.<br>';
          str += '<button type="button" onclick="delete_category_by_categrp('+m_categrpno+')">카테고리 삭제</button>';
          str += '&nbsp;<button type="button" onclick="create_update_cancel();">취소</button>';
        } else {
          str = '[' + rdata.name + "] 카테고리 그룹을 삭제하시겠습니까?<br>";
          str += "삭제하면 복구 할 수 없습니다.<br>"
          str += '<button type="submit" id="submit">삭제</button>';
          str += '&nbsp;<button type="button" onclick="create_update_cancel();">취소</button>'; 
        }
        $('#msg_delete').html(str);
      },
      error: function(request, status, error) { // 응답 결과, 상태, 에러 내용
        var msg = 'ERROR<br><br>';
        msg += '<strong>request.status</strong><br>'+request.status + '<hr>';
        msg += '<strong>request.responseText</strong><br>'+request.responseText + '<hr>';
        msg += '<strong>status</strong><br>'+status + '<hr>';
        msg += '<strong>error</strong><br>'+error + '<hr>';

/*         var msg = '알림<br><br>';
        msg += '<strong>현재 시스템 정비중입니다.</strong><br>조속히 처리하겠습니다.<hr>';
        msg += '예상 종료 시간: 16:00'; */
          
        $('#main_panel').html(msg);
        $('#main_panel').show();
      }
     });

  }
  
 
  // 카테고리 그룹에 등록된 카테고리 모두 삭제
  function delete_category_by_categrp(m_categrpno) {
    $.ajax({
      url: "./delete_category_by_categrp.do", 
      type: "post", // or get
      cache: false,
      async: true, // true: 비동기
      dataType: "json", // 응답 형식, html, xml...
      // data: $('#frm').serialize(),  // 보내는 데이터
      data: 'm_categrpno='+m_categrpno,
      success: function(rdata) {
        // alert(rdata);
        var frm_delete = $('#frm_delete');
        $('#m_categrpno', frm_delete).val(m_categrpno);
        
        var str = '';        
        // 소속된 카테고리 갯수를 출력 예정
        if (rdata.delete_by_categrp > 0) {
          str = '<span style="color: #FF0000;">&apos;'+ rdata.name + '&apos; 카테고리에서 [' + rdata.delete_by_categrp + '] 건의 데이터를 삭제했습니다.</span><br>';
          str += '카테고리 그룹 삭제를 계속 진행하시겠습니까?<br>';
          str += '<button type="button" onclick="deleteOne('+m_categrpno+')">카테고리 그룹 삭제</button>';
          str += '&nbsp;<button type="button" onclick="create_update_cancel();">취소</button>';
        } else {
          str = '[' + rdata.name + "] 카테고리 그룹 관련 카테고리 삭제에 실패했습니다. 다시 시도하시겠습니까?<br>";
          str += "삭제하면 복구 할 수 없습니다.<br>"
          str += '<button type="button" onclick="delete_category_by_categrp('+m_categrpno+')">카테고리 삭제</button>';
          str += '&nbsp;<button type="button" onclick="create_update_cancel();">취소</button>'; 
        }
        $('#msg_delete').html(str);
      },
      error: function(request, status, error) { // 응답 결과, 상태, 에러 내용
        var msg = 'ERROR<br><br>';
        msg += '<strong>request.status</strong><br>'+request.status + '<hr>';
        msg += '<strong>request.responseText</strong><br>'+request.responseText + '<hr>';
        msg += '<strong>status</strong><br>'+status + '<hr>';
        msg += '<strong>error</strong><br>'+error + '<hr>';

/*         var msg = '알림<br><br>';
        msg += '<strong>현재 시스템 정비중입니다.</strong><br>조속히 처리하겠습니다.<hr>';
        msg += '예상 종료 시간: 16:00'; */
          
        $('#main_panel').html(msg);
        $('#main_panel').show();
      }
     });

  }
</script>

</head> 

<body>
<DIV class='container' style='width: 100%;'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>
  
  <DIV id='main_panel'></DIV>
  
  <DIV class='title_line'>카테고리 그룹</DIV>

  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <FORM name='frm_create' id='frm_create' method='POST' action='./create.do'>
      <input type='hidden' name='lang' id='lang' value=''>
                 
      <label for='name'>그룹 이름</label>
      <input type='text' name='name' id='name' value='' required="required" style='width: 25%;'>

      <label for='seqno'>순서</label>
      <input type='number' name='seqno' id='seqno' value='' required="required" 
                min='1' max='1000' step='1' style='width: 5%;'>
  
      <label for='visible'>형식</label>
      <select name='visible' id='visible'>
        <option value='Y' selected="selected">Y</option>
        <option value='N'>N</option>
      </select>
       
      <button type="submit" id='submit'>등록</button>
      <button type="button" onclick="create_update_cancel();">취소</button>
    </FORM>
  </DIV>

  <DIV id='panel_update' style='display: none; padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <FORM name='frm_update' id='frm_update' method='POST' 
                action='./update.do'>
      <input type='hidden' name='m_categrpno' id='m_categrpno' value=''>
           
      <label for='name'>그룹 이름</label>
      <input type='text' name='name' id='name' size='15' value='' required="required" style='width: 30%;'>

      <label for='seqno'>순서</label>
      <input type='number' name='seqno' id='seqno' value='' required="required" 
                min='1' max='1000' step='1' style='width: 5%;'>
  
      <label for='visible'>형식</label>
      <select name='visible' id='visible'>
        <option value='Y' selected="selected">Y</option>
        <option value='N'>N</option>
      </select>

      <button type="submit" id='submit'>저장</button>
      <button type="button" onclick="create_update_cancel();">취소</button>
    </FORM>
  </DIV>

  <DIV id='panel_delete' style='display: none; padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <FORM name='frm_delete' id='frm_delete' method='POST' 
                action='./delete.do'> 
      <input type='hidden' name='m_categrpno' id='m_categrpno' value=''>

      <DIV id='msg_delete' style='margin: 20px auto;'></DIV>
    </FORM>
  </DIV>

  
<TABLE class='table table-striped'>
  <colgroup>
    <col style='width: 20%;'/>
    <col style='width: 40%;'/>
    <col style='width: 20%;'/>
    <col style='width: 20%;'/>
  </colgroup>

  <thead>  
  <TR>
    <TH style='text-align: center ;'>순서</TH>
    <TH>대분류명</TH>
    <TH style='text-align: center ;'>등록일</TH>
    <TH style='text-align: center ;'>기타</TH>
  </TR>
  </thead>
  <c:forEach var="m_categrpVO" items="${list }">
  <TR>
    <TD style='text-align: center ;'>${m_categrpVO.seqno }</TD>
    <TD><A href="../sub_category/list_by_categrp.do?m_categrpno=${m_categrpVO.m_categrpno }">${m_categrpVO.name }</A></TD>
    <TD style='text-align: center ;'>${m_categrpVO.rdate.substring(0, 10) }</TD>
    <TD>
      <A href="javascript:update(${m_categrpVO.m_categrpno })"><IMG src='./images/update.png' title='수정'></A>
      <A href="javascript:deleteOne(${m_categrpVO.m_categrpno })"><IMG src='./images/delete.png' title='삭제'></A>
    </TD>
  </TR>
  </c:forEach> 

</TABLE>

</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' /> 
</DIV> <!-- container END -->
</body>

</html> 
 
 