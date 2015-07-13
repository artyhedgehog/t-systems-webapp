<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="main.jsp" %>

<form method="POST" role="form" class="form-inline">
  <div class="form-group">
    <input role="button" type="submit" class="btn btn-danger" value="Delete">
    <a role="button" href="<c:url value="${cancelUrl}" />" 
       class="btn btn-lg btn-primary">
      Cancel
    </a>
  </div>
</form>
