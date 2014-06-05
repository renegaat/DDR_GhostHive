<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Location</title>
</head>
<body>

<h2>Location Manager</h2>

<form:form method="post" action="addLocation.html" commandName="location">

	<table>
	<tr>
		<td><form:label path="name"><spring:message code="label.location_name"/></form:label></td>
		<td><form:input path="name" /></td> 
	</tr>
	<tr>
		<td><form:label path="latitude"><spring:message code="label.location_latitude"/></form:label></td>
		<td><form:input path="latitude" /></td>
	</tr>
	<tr>
		<td><form:label path="longitude"><spring:message code="label.location_longitude"/></form:label></td>
		<td><form:input path="longitude" /></td>
	</tr>
	
	<tr>
		<td>
			<form:select path="locationType">
            	   <form:options/>
			</form:select>
		</td>
	</tr>
	
	<tr>
		<td colspan="2">
			<input type="submit" value="<spring:message code="label.location_submit"/>"/>
		</td>
	</tr>
	
</table>	
</form:form>

<h3>Locations:</h3>
<c:if  test="${!empty locationList}">
<table class="data">
<tr>
    <th>ID</th>
    <th>NAME</th>
    <th>TYPE</th>
    <th>&nbsp;</th>
</tr>
<c:forEach items="${locationList}" var="location">
   	<tr>
        <td>${location.id}</td>
        <td>${location.name}</td>
        <td>${location.locationType}</td>
		<td>
			<c:if  test="${!empty location.locationInfo}">
				<c:forEach items="${location.locationInfo}" var = "locationInfo">
					${locationInfo.name},
				</c:forEach>
			</c:if>
			
		</td>
        <td><a href="delete/${location.id}">delete</a></td>
	</tr>
</c:forEach>
</table>
</c:if>
</body>
</html>