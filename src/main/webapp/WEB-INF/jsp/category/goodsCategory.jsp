<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/include/lib/topLibs.jsp"></jsp:include>
<title>하피 - 모두를 위한 경매</title>
    <link href="${pageContext.request.contextPath }/resources/bootstrap-4.0.0/docs/4.0/examples/navbar-fixed/navbar-top-fixed.css" rel="stylesheet">
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
	padding-top: 3rem;
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


.na-nav2 i {
	margin-bottom: 0.3rem;
}

.na-nav2 a {
	font-size: 0.7rem;
	width: 20%;
	color: black;
	text-align: center;
	padding: .3rem !important;
}

.na-nav2 a.bottom-on {
	color: white;
}


.table {
	margin-bottom: 2.9rem;
}

.table td, .table th {
	padding: 0;
    padding-left: 10%;
    vertical-align: middle;
    border-top: 1px solid #dee2e6;
<<<<<<< HEAD
    height: 3.6rem;
=======
>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
}

table th {
	width: 100%;
	height: 2.8rem;
	padding-left: 10%;
	vertical-align: middle;
	padding: 0;
}

table th img {
	max-width: 100%;
	max-height: 100%;
}
.fa-bell-o {
    position: absolute;
    z-index: 9998;
    right: 1rem;
    top: .25rem;
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

    <nav class="navbar fixed-top" style="height:3rem; background:white; color:black; padding: .1rem 1rem; border-bottom: 0.1rem solid rgb(224, 224, 224)">
      <span class="navbar-brand" style="font-weight: bold; color:black;">카테고리</span>
      
<div class="col-4 d-flex justify-content-end align-items-right" style="margin-top: 0.5rem; margin-right: -1rem;">
      			<a href="${pageContext.request.contextPath}/aucSearch" style="float: right; color:black;" ><i class="fa fa-search fa-lg material-ripple " aria-hidden="true" style="overflow:unset; position: fixed;right: 2.7rem; top: 1.2rem;"></i></a>
      			&nbsp;&nbsp;&nbsp;
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
	
		<table class="table table-hover">
			<tbody>
			<c:forEach items="${goodsCategory }" var="category">
					<tr onClick="goCategory('${category.uriName}')">
						<th class="material-ripple" scope="row" >${category.codeName }</th>
					</tr>
			</c:forEach>
			</tbody>
		</table>
	

    
	<nav class="na-nav2 navbar fixed-bottom navbar-expand-sm navbar-dark" style="background: #27b2a5; padding: .1rem 0rem; height: 3rem;">

		<a class="p-2 " href="${pageContext.request.contextPath}/hot"><div><i class="fa fa-home fa-lg"></i></div> 홈</a> 
		<a class="p-2 bottom-on" href="${pageContext.request.contextPath}/goodsCategory" ><div><i class="fa fa-bars fa-lg"></i></div> 카테고리</a>
		<a class="p-2 " href="${pageContext.request.contextPath}/myAuction" ><div><i class="fa fa-hourglass-half fa-lg" aria-hidden="true"></i></div>내 경매</a>
		<a class="p-2" href="${pageContext.request.contextPath}/displayForm" ><div><i class="fa fa-arrow-circle-up fa-lg"></i></div>출품하기</a> 
		<a class="p-2" href="${pageContext.request.contextPath}/myPage" ><div><i class="fa fa-user fa-lg"></i></div>MY</a> 

    </nav>




    <jsp:include page="/WEB-INF/jsp/include/lib/botLibs.jsp"></jsp:include>
<<<<<<< HEAD
    <script src="${pageContext.request.contextPath }/resources/wow/dist/wow.min.js"></script>
    
    
     <script type="text/javascript">
     new WOW().init();
     
  // 알림개수 불러오기
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
    
     <script type="text/javascript">
>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
     
     function goCategory(category) {
    	 location.href = "${pageContext.request.contextPath}/goodsCategory/"+category;
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