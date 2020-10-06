<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <jsp:include page="/WEB-INF/jsp/include/lib/topLibs.jsp"></jsp:include>
    <title>하피 - 모두를 위한 경매</title>
    <link href="${pageContext.request.contextPath }/resources/bootstrap-4.0.0/docs/4.0/examples/cover/cover.css" rel="stylesheet">
    
<<<<<<< HEAD
<!--     PWA 시작! -->
    <link rel="manifest" href="${pageContext.request.contextPath}/resources/pwa/manifest.json">
    <!-- CODELAB: Add iOS meta tags and icons -->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-title" content="hafy">
<link rel="apple-touch-icon" href="/resources/img/hafy.png">
<!-- CODELAB: Add description here -->
<meta name="description" content="hana auction for you">
<!-- CODELAB: Add meta theme-color -->
<meta name="theme-color" content="#27b2a5" />

<!-- CODELAB: Add the install script here -->
 <script src="${pageContext.request.contextPath}/resources/pwa/install.js"></script>


=======
>>>>>>> 043d81d1783ccd2630b6ac8affdedf057002e7ca
    <script>
    
    setTimeout(() => {
		location.href = "${pageContext.request.contextPath }/login"    
	}, 3000);
    
    </script>
    
  </head>

  <body class="text-center" style="background: #27b2a5">

    <div class="cover-container d-flex h-100 p-3 mx-auto flex-column">
      <header class="masthead mb-auto">
      </header>

      <main role="main" class="inner cover">
        <h1 class="cover-heading" style="font-weight: bold;">H A F Y</h1>
        <p class="lead">Hana Auction For You</p>
        <p class="lead">
        </p>
      </main>

      <footer class="mastfoot mt-auto">
        <div class="inner">
          <p>Developed by <a href="https://github.com/nacastle">@nacastle</a>.</p>
        </div>
      </footer>
    </div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <jsp:include page="/WEB-INF/jsp/include/lib/botLibs.jsp"></jsp:include>
    
  </body>
</html>
