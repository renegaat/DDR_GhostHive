<%@ page language="java" contentType="t
ext/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ghost Manager</title>
</head>
<body>
	<h2>Ghost Manager</h2>
	<form:form method="post" action="addGhost.html" commandName="ghost">
		<table>
			<tr>
				<td><form:label path="name">
						<spring:message code="label.ghost_name" />
					</form:label></td>
				<td><form:input path="name" /></td>
			</tr>

			<tr>
				<td><form:label path="locationTo">
						<spring:message code="label.ghost_locationTo" />
					</form:label></td>


				<td><form:select id="chooseLocationTo" path="locationTo.id">
						<form:option value="" selected="selected">Select a Location</form:option>
						<form:options items="${locationListTo}" itemValue="id"
							itemLabel="id" />
					</form:select></td>
			</tr>


			<tr>
				<td><form:label path="locationFrom">
						<spring:message code="label.ghost_locationFrom" />
					</form:label></td>


				<td><form:select id="chooseLocationFrom" path="locationFrom.id">
						<form:option value="" selected="selected">Select a Location</form:option>
						<form:options items="${locationListFrom}" itemValue="id"
							itemLabel="id" />
					</form:select></td>
			</tr>
			
			<tr>
				<td><form:label path="locationAt">
						<spring:message code="label.ghost_locationAt" />
					</form:label></td>

				<td><form:select id="chooseLocationAt" path="locationAt.id">
						<form:options items="${locationListAt}" itemValue="id" itemLabel="id"/>
					</form:select></td>
			</tr>

		
			<tr>
				<td><form:label path="customer">
						<spring:message code="label.ghost_customer" />
					</form:label></td>

				<td><form:select id="chooseCustomer" path="customer.id">
						<form:options items="${customerList}" itemValue="id" itemLabel="name" />
					</form:select></td>
			</tr>
			
			<tr>
				<td><form:label path="hive">
						<spring:message code="label.ghost_hive" />
					</form:label></td>

				<td><form:select id="chooseHive" path="hive.id">
						<form:options items="${hiveList}" itemValue="id" itemLabel="name" />
					</form:select></td>
			</tr>
		
			<tr>
				<td colspan="2"><input type="submit" value="<spring:message code="label.ghost_submit"/>" /></td>
			</tr>

		</table> 
	</form:form>

	<h3>Ghosts:</h3>
	<c:if test="${!empty ghostList}">
		<table class="data">
			<tr>
				<th>ID</th>
				<th>NAME</th>
				<th>&nbsp;</th>
			</tr>
			<c:forEach items="${ghostList}" var="ghost">
				<tr>
					<td>${ghost.id}</td>
					<td>${ghost.name}</td>
					<td><a href="deleteGhost/${ghost.id}">delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>