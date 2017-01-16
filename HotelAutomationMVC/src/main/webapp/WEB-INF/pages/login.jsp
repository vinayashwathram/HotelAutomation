<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>${title}</title>
</head>
<body>

	<h1>Message : ${message}</h1>

	<form:form action="checkLogin" method="POST"
		modelAttribute="memberForm">

		

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
				<td>&nbsp;</td>
				<td><input type="submit" value="Submit" />
				<td>&nbsp;</td>
			</tr>
		</table>
	</form:form>

</body>
</html>