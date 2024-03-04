<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table>
  <thead>
    <tr>
      <th>ID</th>
      <th>Username</th>
      <th>Age</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="member" items="${members}">
    <tr>
      <td>${member.id}</td>
      <td>${member.name}</td>
      <td>${member.age}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<h5><a href="/">HOME</a></h5>
