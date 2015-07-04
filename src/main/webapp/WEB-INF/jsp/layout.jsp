<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> 
<%--          isELIgnored="false"%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
      <c:out value="${appTitle}" default="Application name not found"/>
        - <c:out value="${pageTitle}" default="Page title not found"/>
    </title>
    <c:forEach var="url" items="${scripts}">
      <script type="text/javascript" src="${url}"></script>
    </c:forEach>
    <c:forEach var="url" items="${stylesheets}">
      <link rel="stylesheet" href="${url}">
    </c:forEach>
  </head>
  <body>
    <c:if test="${not empty headerView}">
      <header><jsp:include page="${headerView}" flush="true"/></header>
    </c:if>
    <main> 
      <c:if test="${not empty sidebarView}">
        <aside><jsp:include page="${sidebarView}" flush="true"/></aside>
      </c:if>
      <c:choose>
        <c:when test="${not empty pageView}">
          <jsp:include page="${pageView}" flush="true"/>
        </c:when>
        <c:otherwise>Page content view is not set.</c:otherwise>
      </c:choose>
    </main>
    <footer>
      <c:if test="${not empty footerView}">
        <jsp:include page="${footerView}" flush="true"/>
      </c:if>
    </footer>
  </body>
</html>