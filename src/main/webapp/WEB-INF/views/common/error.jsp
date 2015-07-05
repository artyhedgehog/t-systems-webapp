<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p class="error">An error occurred while processing request.</p>

<code><c:out value="${exception}"/></code>
<hr>
<p class="info">See server error log for additional information.</p>
