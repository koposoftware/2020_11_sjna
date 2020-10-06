<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/include/lib/topLibs.jsp"></jsp:include>
<title>하피 - 모두를 위한 경매</title>
    <link href="${pageContext.request.contextPath }/resources/bootstrap-4.0.0/docs/4.0/examples/product/product.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/resources/bootstrap-4.0.0/docs/4.0/examples/sticky-footer/sticky-footer.css" rel="stylesheet">
    
<style type="text/css">
	.bankHover {
		background: rgba(0, 0, 0, 0.05);
	}    
    
</style>
</head>
<body>

    <nav class="site-header sticky-top py-1" style="background: #27b2a5">
      <div class="container d-flex flex-column flex-md-row justify-content-between" style="padding-left: 0;margin-left: 1rem;">
      	<h5 class="masthead-brand" style="font-weight:bold; margin-top:2%; color: white;">계좌 등록</h5>
      </div>
    </nav>
    
<<<<<<< HEAD
    <form class="needs-validation" onsubmit="return checkValid()" action="${pageContext.request.contextPath }/registerAccSuccess" name="aForm" method="post">
=======
    <form class="needs-validation" action="${pageContext.request.contextPath }/registerAccSuccess" name="aForm" method="post">
>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
     <div class="col-md-8 order-md-1">
          <h5 class="mb-3"> </h5>
            <div class="row">
<!--               <div class="col-md-6 mb-3"> -->
<!--                 <label for="firstName">아이디</label> -->
<!--                 <input type="text" class="form-control" id="firstName" placeholder="" value="" required> -->
<!--                 <div class="invalid-feedback"> -->
<!--                   Valid 아이디 is required. -->
<!--                 </div> -->
<!--               </div> -->
              <div class="col-md-6 mb-3">
                <label for="firstName">은행 선택</label>

					<table class="table table-bordered" style="text-align: center;">
						<tbody>
							<tr>
							
<!-- 							<td style="width: 6rem;"><input type="radio" class="btn btn-primary" name="bank" value="하나"></td> -->
<!-- 								<td style="width: 6rem;"><input type="radio" name="bank" value="우리"></td> -->
<!-- 								<td style="width: 6rem;"><input type="radio" name="bank" value="국민"></td> -->
								
								<td class="bank" id="hana" style="width: 6rem;"  onclick="selectBank(id)" >하나</td>
								<td class="bank" id="woori" style="width: 6rem;"  onclick="selectBank(id)">우리</td>
								<td class="bank" id="kookmin" style="width: 6rem;"  onclick="selectBank(id)">국민</td>
							</tr>
							<tr>
								<td class="bank" id="kieob" style="width: 6rem;"  onclick="selectBank(id)">기업</td>
								<td class="bank" id="shinhan" style="width: 6rem;" onclick="selectBank(id)">신한</td>
								<td class="bank" id="nonghyup" style="width: 6rem;" onclick="selectBank(id)">농협</td>
							</tr>
							<tr>
<!-- 								<td colspan="2">Larry the Bird</td> -->
								<td class="bank" id="suhyup" style="width: 6rem;"  onclick="selectBank(id)">수협</td>
								<td class="bank" id="jeju" style="font-size: 0.9rem; width: 6rem;"  onclick="selectBank(id)">제주</td>
								<td class="bank" id="blank"  ></td>
							</tr>
						</tbody>
					</table>
					
<<<<<<< HEAD
					<div id="checkBank" style="display:none; color: red;">은행을 선택해주세요.</div>
					
=======
>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
					<input type="hidden" name="bank" id="bank">

				</div>
              <div class="col-md-6 mb-3">
                <label for="lastName">계좌번호</label>
                <input type="text" class="form-control" name="accountNo" id="accountNumber" autocomplete="off" >
                <div class="invalid-feedback">
                  Valid 계좌번호 is required.
                </div>
<<<<<<< HEAD
                <div id="checkAccountNo" style="display:none; color: red;">계좌번호를 입력해주세요.</div>
=======
>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
              </div>
            </div>

        </div>
        
        <div class="footer" style="background: white;">
      		<div class="container">
<<<<<<< HEAD
            	<button class="btn btn-primary btn-lg btn-block" type="submit" style="font-weight:bold; border:#27b2a5; background: #27b2a5; color: white;">계좌등록하기</button>
=======
            	<button class="btn btn-primary btn-lg btn-block" type="submit" style="font-weight:bold; border:#27b2a5; background: #27b2a5; color: white;">인증하기</button>
>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
      		</div>
    	</div>
    	
     </form>

    <jsp:include page="/WEB-INF/jsp/include/lib/botLibs.jsp"></jsp:include>
    <script>
    
<<<<<<< HEAD
    
    function checkValid() {
    	
    	if (selectedBank == '') {
    		$("#checkBank").show()
    		return false;
		}
    	if ($("#accountNumber").val() == '') {
    		$("#checkAccountNo").show()
    		return false;
		}
    	return true;
		
	}
    
=======
>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
//    function turnGray(id) {
//   	  $("#"+id).hover(function() {
//   		  $(this).addClass('bankHover');
// 	}, function() {
//   		  $(this).removeClass('bankHover');
// 	});
	
// }
<<<<<<< HEAD


   
    let selectedBank = '';
    function selectBank(id) {
    	$("#checkBank").hide()
=======
   
    
    function selectBank(id) {
>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
//   	  	$("#"+id).removeClass('bankHover');
//   	  	$(".bank").css('background','white');
  	  	$(".bank").removeClass('bankHover');
  	  	$("#"+id).addClass('bankHover');
  	  
<<<<<<< HEAD
		selectedBank = $("#"+id).text()
=======
		let selectedBank = $("#"+id).text()
>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
  	 	$("#bank").val(selectedBank);
	}
      Holder.addTheme('thumb', {
        bg: '#55595c',
        fg: '#eceeef',
        text: 'Thumbnail'
      });
      
      
      
    </script>



</body>
</html>