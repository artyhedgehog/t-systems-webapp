<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form method="POST" role="form" class="form-horizontal">
  <c:forEach var="field" items="${form.fields}">
    <div class="form-group">
      <label for="${field.id}" class="control-label col-sm-4">
        <c:out value="${field.label}" />
      </label>
      <div class="col-sm-7"> 
        <c:choose>
          <c:when test="${field.type eq 'SELECT'}">
            <select>
              <c:forEach var="option" items="${field.options}">
                <option <c:if test="${field.value}" /> value="${option.id}">
                  <c:out value="${option}" />
                </option>
              </c:forEach>
            </select>
          </c:when>
          <c:otherwise>
            <input type="${field.type.inputType}" id="${field.id}" 
                   name="${field.name}" value="${field.value}" 
                   placeholder="${field.placeholder}" 
                   class="form-control<c:if 
                       test="${field.type eq 'SUBMIT'}"> btn btn-success</c:if>">
          </c:otherwise>
        </c:choose>
      </div>
    </div>
  </c:forEach>
</form>
