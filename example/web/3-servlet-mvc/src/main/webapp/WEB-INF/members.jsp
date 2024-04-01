<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Hello, Servlet MVC!</h1>

<ul>
  <li><a href="/members/sign-up">sign-up</a></li>
</ul>

<c:if test="${!members.isEmpty()}">
  <h2>Members</h2>
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
</c:if>
