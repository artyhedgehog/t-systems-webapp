<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%-- Bootstrap-based navigation bar --%>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <!-- Navigation menu collapse -->
      <button type="button" class="navbar-toggle collapsed"
              data-toggle="collapse" 
              data-target="#navbar-collapse"
              aria-expanded="true">
        <span class="sr-only">Toggle navigation</span> 
        <span class="icon-bar"></span>
        <span class="icon-bar"></span> 
        <span class="icon-bar"></span>
      </button>
      
      <a href="<c:url value="/"/>" class="navbar-brand"> 
        <c:out value="${appTitle}">WebApp</c:out>
      </a>    
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <c:if test="${not empty menuMap}">
      <div class="collapse navbar-collapse" id="navbar-collapse">
        <ul class="nav navbar-nav">
          <c:forEach var="entry" items="${menuMap}">
            <li>
              <a href="<c:url value="${entry.value}"/>">
                <c:out value="${entry.key}"/>
              </a>
          </c:forEach>
        </ul>
      </div>
    </c:if>
  </div>
</nav>

