<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Member List</title>
<style>
   table  {
       margin-top: 10px;
       border: solid black 1px;
   }
   table  td {
        padding: 5px;
   }
   .message  {
        font-size:90%;
        color:blue;
        font-style:italic;
        margin-top:30px;
   }
</style>
</head>
<body>
 
 
 
<a href="${pageContext.request.contextPath}/createMember">Create Member</a>
 
<br/>
 
 
<table border="1">
 <tr>
   <th>First Name</th>
   <th>Last Name</th>
   <th>Age</th>
   <th>Email</th>
   <th>Gender</th>
 </tr>
 <c:forEach items="${memberInfos}" var="info">
 
 <tr>
   <td> ${info.firstName}  </td>
   <td> ${info.lastName}  </td>
   <td> ${info.age}  </td>
   <td> ${info.email} </td>
   <td> ${info.gender} </td>
 </tr>    
 
 </c:forEach>
</table>
<c:if test="${not empty message}">
  
   <div class="message">${message}</div>
</c:if>
 
 
 
</body>
</html>