<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">

  <head>
    <!-- Bootstrap meta-tags -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>
      <c:out value="${appTitle}" default="Application name not found"/>
        - <c:out value="${pageTitle}" default="Page title not found"/>
    </title>

    <%-- CSS linking --%>
    <c:forEach var="url" items="${stylesheets}">
      <link rel="stylesheet" href="<c:url value="${url}"/>">
    </c:forEach>
    
    <%-- Bootstrap fix for IE --%>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
    <!-- Container for a sticky footer -->
    <div class="container">
      <c:if test="${not empty headerView}">
        <header><jsp:include page="${headerView}" flush="true"/></header>
      </c:if>
  
      <main> 
        <%-- Sidebar --%>
        <c:if test="${not empty sidebarView}">
          <aside><jsp:include page="${sidebarView}" flush="true"/></aside>
        </c:if>
        
        <%-- Main page content --%>
        <c:choose>
          <c:when test="${not empty pageView}">
            <jsp:include page="${pageView}" flush="true"/>
          </c:when>
          <c:otherwise>Page content view is not set.</c:otherwise>
        </c:choose>
      </main>
    </div>
    
    <c:if test="${not empty footerView}">
      <footer class="sticky-footer">
        <jsp:include page="${footerView}" flush="true"/>
      </footer>
    </c:if>
    
    <%-- JavaScript initializing --%>
    <c:forEach var="url" items="${scripts}">
      <script type="text/javascript" src="<c:url value="${url}"/>"></script>
    </c:forEach>
  </body>
</html>