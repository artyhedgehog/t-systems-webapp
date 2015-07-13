<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<table class="table table-striped table-hover">
  <thead>
    <tr>
      <th>Registration number</th>
      <th>Drivers quantity</th>
      <th>Capacity (tons)</th>
      <th>Condition</th>
      <th>Current town</th>
      <c:if test="${listControlsEnabled}">
        <th>
          <%-- TODO: search
          <a role="button" href="<c:url value="/trucks/find" />" 
             class="btn btn-primary btn-sm">
            Find
          </a>
          --%>
          <a role="button" href="<c:url value="/trucks/add" />"
             class="btn btn-success btn-sm pull-right">
            Add
          </a>
        </th>
      </c:if>
    </tr>
  </thead>
  <tbody><c:forEach var="truck" items="${trucks}">
    <tr id="truck<c:out value="${truck.id}" />" 
        class="<c:choose>
          <c:when test="${truck.state.condition eq 'operable'}">success</c:when>
          <c:when test="${truck.state.condition eq 'broken'}">warning</c:when>
          <c:otherwise></c:otherwise>
        </c:choose>">
      <td><c:out value="${truck.regNumber}" default="N/A" /></td>
      <td>
        <c:out value="${fn:length(truck.driversStates)}" default="-" />
          / <c:out value="${truck.driversQuantity}" default="-" />
      </td>
      <td><c:out value="${truck.capacityTons}" default="N/A" /></td>
      <td title="<c:out value="${truck.state.condition.description}" />">
        <c:out value="${truck.state.condition}" default="N/A" />
      </td>
      <td><c:out value="${truck.state.town}" default="N/A" /></td>
      <c:if test="${listControlsEnabled}">
        <td>
          <a role="button" href="<c:url value="/trucks/delete/${truck.id}" />" 
             class="btn btn-danger btn-sm pull-right">
            Delete
          </a>
          <a role="button" href="<c:url value="/trucks/edit/${truck.id}" />"
             class="btn btn-warning btn-sm pull-right">
            Edit
          </a>
        </td>
      </c:if>
    </tr>
  </c:forEach></tbody>
</table>