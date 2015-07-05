<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
          <c:forEach var="entry" items="menuMap">
            <c:choose>
              <c:when test="${empty entry.value.entrySet}">
                <%-- Just a link then --%>
                <li>
                  <a href="<c:url value="${entry.value}"/>">
                    <c:out value="${entry.key}"/>
                  </a>
              </c:when>
              <c:otherwise>
                <%-- Got to be submenu map then --%>
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" 
                     role="button" aria-haspopup="true" aria-expanded="false">
                    <c:out value="${entry.key}"/>
                    <span class="caret"></span>
                  </a>
                  <ul class="dropdown-menu">
                    <c:forEach var="subEntry" items="entry.value">
                      <li>
                        <a href="<c:url value="${subEntry.value}"/>">
                          <c:out value="${subEntry.key}"/>
                        </a>
                    </c:forEach>
                  </ul>
                </c:otherwise>
              </c:choose>
            </c:forEach>
          </ul>
        </div>
      <!-- /.navbar-collapse -->
    </c:if>
  </div>
  <!-- /.container-fluid -->
</nav>

