<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/include/lib/topLibs.jsp"></jsp:include>
<title>하피 - 모두를 위한 경매</title>
    <link href="${pageContext.request.contextPath }/resources/bootstrap-4.0.0/docs/4.0/examples/navbar-fixed/navbar-top-fixed.css" rel="stylesheet">
<!--     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" /> -->
<<<<<<< HEAD
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/wow/css/libs/animate.css">
=======
>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
<style type="text/css">
a {
	text-decoration: none !important;
}

body {
	min-height: 0;
	padding-top: 3.2rem;
	padding-bottom: 4rem;
	
}

.na-nav {
	position: fixed;
	left: 0;
	top: 2.7rem;
	width: 100%;
	background: #fff;
	box-shadow: 0 0 5px rgba(0, 0, 0, .5);
}

.na-nav div {
	position: relative;
	display: flex;
	width: 100%;
	margin: 0 auto;
}

.na-nav div a {
	flex: 1;
	display: block;
	height: 40px;
	line-height: 40px;
	text-align: center;
	color: #8f8f8f;
	font-weight: bold;
}

.na-nav div a.top-on {
	color: #000;
}

.na-nav div>span {
	position: absolute;
	left: 0;
	bottom: 0;
	width: 50%;
	height: 3px;
	background: #27b2a5;
	opacity: 0;
	transition: all .5s;
}

.na-nav div a:nth-child(1).top-on ~ span {
	left: 0;
	opacity: 1;
}

.na-nav div a:nth-child(2).top-on ~ span {
	left: 50%;
	opacity: 1;
}
/* nav div a:nth-child(3).on ~ span {	left: 200px; opacity: 1;} */
/* nav div a:nth-child(4).on ~ span {	left: 300px; opacity: 1;} */
/* nav div a:nth-child(5).on ~ span {	left: 400px; opacity: 1;} */

.na-nav2 {
	height: 3rem;

}

.na-nav2 a {
	font-size: 0.7rem;
	width: 20%;
	color: black;
	text-align: center;
	padding: .3rem !important;
}

.na-nav2 i {
	margin-bottom: 0.3rem;
}


.na-nav2 a.bottom-on {
	color: white;
}

 
/* .table { */
/* 	margin-bottom: 2.9rem; */
/* } */


/*
.table td, .table th {
    padding-left: 3%;
    vertical-align: middle;
    border-top: 1px solid #dee2e6;
}

 */
 
 .na-content {
 margin: 1rem 1rem;
/*  text-align: center; */
 display: flow-root;
 
 }
 
 table {
 	font-size: 1.1rem;
 	width: 100%;
/*  	border: 1 solid rgb(158, 158, 158); */
	border-collapse: unset;
	border-top: 0.1rem solid rgb(224, 224, 224);
	margin-bottom: 1rem;
	border-collapse: collapse;
	
 
 }
 
 .ttitle {
     margin-left: 4%;
     font-weight: bold;
 }
 
 
 table th {
	width: 27%;
	height: 1.8rem;
/* 	padding-left: 3%; */
	text-align: center;
	vertical-align: middle;
 	padding: 0.1rem;
/*  	border: 0; */
	background: rgb(22, 160, 133);
	color: white;
}
 table td {
/* 	width: 73%; */
	height: 3rem;
  	padding-left: 7%;  
	text-align: left;
	vertical-align: middle;
/*  	padding: 0.1rem; */
/*  	padding-left: 0.5rem; */
 	border-bottom: 0.1rem solid rgb(224, 224, 224);
/*  	border: 0; */
/* 	background: rgb(224, 224, 224); */
}

/* 
table th img {
	max-width: 100%;
	max-height: 100%;
} */

  hr { width: 90vw  }   
 
 label { margin: 0; }
 
/*  input[type="date"]:not(.has-value):before{ */
/*   color: lightgray; */
/*   content: attr(placeholder); */
/* } */

input[type="datetime-local"]::before {
	color: #999999;
	content: attr(placeholder);
}
input[type="datetime-local"] {
	color: #ffffff;
}
input[type="datetime-local"]:focus,
input[type="datetime-local"]:valid {
	color: #666666;
}
input[type="datetime-local"]:focus::before,
input[type="datetime-local"]:valid::before {
	content: "" !important;
}

.yn {
 	width: 50%; 
/* 	border: 0.1rem solid rgb(224, 224, 224); */
	text-align: center;
	padding: 0;
	font-size: 1rem;
	height: 2.4rem;
/* 	float: left; */
}

.fa-bell-o {
    position: fixed;
    z-index: 9998;
    right: 1rem;
    top: 1.3rem;
    }
 
#noticeCnt {
    background: red;
    color: white;
    width: 1rem;
    display: inline-block;
    text-align: center;
    font-size: .8rem;
    position: relative;
    z-index: 9998;
    top: -0.6rem;
    right: -.3rem;
    font-weight: bold;

}

</style>


</head>
<body>




	<nav class="navbar fixed-top" style=" background:white; color:black; padding: .1rem 1rem; height: 3rem; border-bottom: 0.1rem solid rgb(224, 224, 224) ">
   		<div style="float: left;">
<!--     	  <a href="#" style="color: black; margin-right: -3rem;">출품완료</a> -->
    	  <span  style="font-size: 1.25rem; position:relative; top:0.2rem; left: 0rem; font-weight: bold; color:black;">MY</span>
  	 	</div>
  	 	
  	 	<div class="col-4 d-flex justify-content-end align-items-right" style="margin-top: 0.5rem; margin-right: -1rem;">
<!--       		<a href="#" style="float: right; color:black;" ><i class="fa fa-bell-o fa-lg" aria-hidden="true"></i></a> -->
			<a href="${pageContext.request.contextPath}/noticeContent" style="float: right; color:black;" ><i class="fa fa-bell-o fa-lg material-ripple" style="overflow:unset;" aria-hidden="true"></i>
      			
<<<<<<< HEAD
	      		<span id="noticeCnt" style="display:inline-block; background: red; color: white;"></span>
=======
      			<c:if test="${unreadNotiCnt != 0 }">
	      			<span id="noticeCnt" style="background: red; color: white;">${unreadNotiCnt }</span>
      			</c:if>
>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
      			</a>
		</div>
    </nav>



	<div class="na-content">

		<div style="float: left; font-weight: bold;">

			<i class="fa fa-user-circle fa-4x" style="float: left;"
				aria-hidden="true"></i>
			<div style="float: left; text-align: center; margin-left: 0.4rem; margin-top: 0.5rem;">
				${memberVO.name }<br> (${memberVO.nickname})
			</div>
		</div>

		<div style="float: right; text-align: center;   margin-top: 0.4rem;">
			경매지수<br> <i class="fa fa-star" aria-hidden="true"></i> 4.5 / 5.0
		</div>
<!-- 		<input type="number" id="setMin"><button id="setImmTime"></button> -->
	</div>

	<div style="margin-top: 1rem;">

		<div class="ttitle">입찰</div>
		<table class="table-hover">
			<tr onclick="goBidOnGoing()">
				<td>진행내역</td>
			</tr>
			<tr onclick="goBidWin()">
				<td>낙찰내역 (매입확정/환불)</td>
			</tr>
			<tr onclick="goLikeList()">
				<td>관심경매</td>
			</tr>
		</table>

		<div class="ttitle">출품</div>
		<table class="table-hover">
			<tr onclick="goDisplayOnGoing()">
				<td>진행내역</td>
			</tr>
<!-- 			<tr onclick="goConfirmPurchase()"> -->
<!-- 				<td>매입확정</td> -->
<!-- 			</tr> -->
			<tr onclick="goDisplayClosed()">
				<td>마감내역 (매입확정/환불)</td>
			</tr>
		</table>

		<div class="ttitle">회원</div>
		<table class="table-hover">
			<tr onclick="goMyInfo()">
				<td>회원정보</td>
			</tr>
			<tr onclick="goChangePwd()">
				<td>비밀번호 변경</td>
			</tr>
<!-- 			<tr onclick="goMyModify()"> -->
<!-- 				<td>회원정보 수정</td> -->
<!-- 			</tr> -->
			<tr onclick="goSignOut()">
				<td>회원탈퇴</td>
			</tr>
			<tr>
				<td id="openLogoutModal" data-toggle="modal" data-target="#myModal">로그아웃</td>
			</tr>
		</table>

		
<!-- 로그아웃 모달창 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
   aria-labelledby="myModalLabel" aria-hidden="true" style="margin-top: 10rem;">
   <div class="modal-dialog"><!--  큰창:<div class="modal-dialog modal-lg"> 작은창 :<div class="modal-dialog modal-sm">  -->
      <div class="modal-content">
         <div class="modal-body" style="text-align: center; margin: 1rem;">
            정말 로그아웃 하시겠습니까?
         </div>
        
         <div class="modal-footer" style="border-top:0; padding:0; ">
          		<table style="margin-bottom: 0;">
			<tr>
				<td class="yn" id="yes" onclick="yes()" data-dismiss="modal" style="border-right: 0.1rem solid rgb(224, 224, 224);">예</td>
				<td class="yn" id="no" data-dismiss="modal" onclick="no()">아니오</td>
			</tr>
		</table>
         </div>
      </div>
   </div>
</div>
		<div class="ttitle">계좌</div>
		<table class="table-hover">
			<tr onclick="goMyAccount()">
				<td>내 계좌</td>
			</tr>
			<tr onclick="goRegisterMAccount()">
				<td>계좌 등록</td>
			</tr>
			<tr onclick="goMngAccount()">
				<td>내 계좌 삭제</td>
			</tr>
		</table>
		
		<div class="ttitle">설정</div>
		<table class="table-hover">
			<tr onclick="goSetNotice()">
				<td>알림 설정</td>
			</tr>
		</table>

	</div>

    
	<nav class="na-nav2 navbar fixed-bottom navbar-expand-sm navbar-dark" style="background: #27b2a5; padding: .1rem 0rem; height: 3rem;">

		<a class="p-2 " href="${pageContext.request.contextPath}/hot"><div><i class="fa fa-home fa-lg"></i></div> 홈</a> 
		<a class="p-2" href="${pageContext.request.contextPath}/goodsCategory" ><div><i class="fa fa-bars fa-lg"></i></div> 카테고리</a>
		<a class="p-2 " href="${pageContext.request.contextPath}/myAuction" ><div><i class="fa fa-hourglass-half fa-lg" aria-hidden="true"></i></div>내 경매</a>
		<a class="p-2" href="${pageContext.request.contextPath}/displayForm" ><div><i class="fa fa-arrow-circle-up fa-lg"></i></div>출품하기</a> 
		<a class="p-2 bottom-on" href="${pageContext.request.contextPath}/myPage" ><div><i class="fa fa-user fa-lg"></i></div>MY</a> 

    </nav>

    <jsp:include page="/WEB-INF/jsp/include/lib/botLibs.jsp"></jsp:include>
<<<<<<< HEAD
    <script src="${pageContext.request.contextPath }/resources/wow/dist/wow.min.js"></script>
=======
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script> -->
>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
      <script type="text/javascript">
      
//       $("table").removeClass("table-hover")
//       $("tr").addClass("material-ripple")
<<<<<<< HEAD

// 알림개수 불러오기
new WOW().init();

		function getNotiCnt() {
			let notiCnt;
		$.ajax({
			url : "${pageContext.request.contextPath}/getNotiCnt",
			type : "get",
			async : false,
			success : function(data) {
				console.log("data" + data)
				notiCnt = data;
			}, 
			error : function() {
				console.log("noticeCnt 불러오기 실패")
			}
		})
		return notiCnt;
		
		}
		// 바로 부른 후 n초마다 알림메세지 새로 로드하기
		if (getNotiCnt() != 0) {
			$("#noticeCnt").text(getNotiCnt())
			$("#noticeCnt").show()
			
		}
		
		setNotiCnt = setInterval(function() {
			let newCnt = getNotiCnt();
			let oldCnt = $("#noticeCnt").text();
			
			if (newCnt != 0 && newCnt != oldCnt) {
					console.log("갱신")
					$("#noticeCnt").hide()
					$("#noticeCnt").show("wow bounceInDown")
					$("#noticeCnt").text(newCnt)
				} else {
					console.log("갱신 x")
				}
			
			}, 3000);

=======
>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
      
      function goDisplayClosed() {
    	  location.href = "${pageContext.request.contextPath}/displayClosed"
	}
      function goDisplayOnGoing() {
    	  location.href = "${pageContext.request.contextPath}/displayOnGoing"
	}
      function goBidOnGoing() {
    	  location.href = "${pageContext.request.contextPath}/bidOnGoing"
	}
      function goBidWin() {
    	  location.href = "${pageContext.request.contextPath}/bidWin"
	}
      function goSetNotice() {
    	  location.href = "${pageContext.request.contextPath}/noticeSetting"
	}
      function goConfirmPurchase() {
    	  location.href = "${pageContext.request.contextPath}/confirmPurchaseForm"
		
	}
      function goLikeList() {
    	  location.href = "${pageContext.request.contextPath}/likeAuction"
		
	}
      function goRegisterMAccount() {
    	  location.href = "${pageContext.request.contextPath}/addRegisterAccForm"
		
	}
      function goMngAccount() {
    	  location.href = "${pageContext.request.contextPath}/myAccountMng"
		
	}
      function goMyAccount() {
    	  location.href = "${pageContext.request.contextPath}/myAccount"
		
	}
      function goSignOut() {
    	  location.href = "${pageContext.request.contextPath}/signOut"
		
	}
      function goChangePwd() {
    	  location.href = "${pageContext.request.contextPath}/changePwd"
		
	}
      
      $("#setImmTime").click(function() {
		let setTime = $("#setMin").val()
		console.log(setTime)
	})
      
   $('#openLogoutModal').click(function(){
	  	$("#yes").css("background","white")
	  	$("#no").css("background","white")
});
   
   function yes() {
	  	$("#yes").css("background","rgb(224, 224, 224)")
		$.ajax({
			url: "${pageContext.request.contextPath}/logout",
			type: 'post',
			success: function() {
				location.href= "${pageContext.request.contextPath}/login"
				
			},
			error: function() {
			}
		})
}
   
   function no() {
	  	$("#no").css("background","rgb(224, 224, 224)")
}
      
      
      function goMyInfo() {
    	  location.href = "${pageContext.request.contextPath}/myInfo"
	}
      function goMyModify() {
    	  location.href = "${pageContext.request.contextPath}/myModify"
	}
      
	  $('.na-nav a').on('click',function(){
		 $(this).addClass('top-on');
		 $(this).siblings().removeClass('top-on');
		});
	  $('.na-nav2 a').on('click',function(){
		 $(this).addClass('bottom-on');
		 $(this).siblings().removeClass('bottom-on');
		});
  
  </script>    
</body>
</html>