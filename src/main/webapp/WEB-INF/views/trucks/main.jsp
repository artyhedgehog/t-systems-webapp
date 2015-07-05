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
    </tr>
  </thead>
  <tbody><c:forEach var="truck" items="trucks">
    <tr id="truck<c:out value="${truck.id}"/>">
      <td><c:out value="${truck.regNumber}" default="N/A"/></td>
      <td>
        <c:out value="${fn:length(truck.driversStates)}" default="-"/>
          / <c:out value="${truck.driversQuantity}" default="-"/>
      </td>
      <td><c:out value="${truck.capacityTons}" default="N/A"/></td>
      <td><c:out value="${truck.state.condition}" default="N/A"/></td>
      <td><c:out value="${truck.state.town}" default="N/A"/></td>
    </tr>
  </c:forEach></tbody>
</table>