<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Logiweb - <c:out value="${pageTitle}">Page title not found</c:out></title>
  <c:forEach var="url" items="${scripts}">
    <script type="text/javascript" src="${url}"></script>
  </c:forEach>
  <c:forEach var="url" items="${stylesheets}">
    <link rel="stylesheet" href="${url}"> 
  </c:forEach>
</head>
<body>

</body>
</html>