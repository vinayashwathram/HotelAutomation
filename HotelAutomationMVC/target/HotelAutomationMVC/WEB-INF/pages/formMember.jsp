<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Member</title>
<style>
.error-message {
	color: red;
	font-size: 90%;
	font-style: italic;
}
</style>
</head>
<body>

	<h3>${formTitle}</h3>

	<form:form action="saveMember" method="POST"
		modelAttribute="memberForm">

		<%-- <form:hidden path="userName" /> --%>

		<table>
			<tr>
				<td>User Name</td>
				<td><form:input path="userName" /></td>
				<td><form:errors path="userName" class="error-message" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><form:input type="password" path="password" /></td>
				<td><form:errors path="password" class="error-message" /></td>
			</tr>
			<tr>
				<td>First Name</td>
				<td><form:input path="firstName" /></td>
				<td><form:errors path="firstName" class="error-message" /></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><form:input path="lastName" /></td>
				<td><form:errors path="lastName" class="error-message" /></td>
			</tr>
			<tr>
				<td>Age</td>
				<td><form:input path="age" /></td>
				<td><form:errors path="age" class="error-message" /></td>
			</tr>
			<tr>
			<tr>
				<td>Email</td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" class="error-message" /></td>
			</tr>
			<td>Gender</td>
			<td><form:select path="gender">
					<form:option value="" label="- Gender -" />
					<form:option value="M" label="Male" />
					<form:option value="F" label="Female" />
				</form:select></td>
			<td><form:errors path="gender" class="error-message" /></td>
			</tr>


			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Submit" /> <a
					href="${pageContext.request.contextPath}/cancelPage">Cancel</a></td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form:form>

</body>
</html>