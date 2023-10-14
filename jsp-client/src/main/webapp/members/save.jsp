<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dev.jihogrammer.member.Member" %>
<%@ page import="dev.jihogrammer.member.MemberRepository" %>
<%@ page import="dev.jihogrammer.jsp.SingletonInMemoryMembers" %>
<%
    MemberRepository repository = SingletonInMemoryMembers.getInstance();

    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member newMember = repository.save(new Member(username, age));
%>

<h1>Hello, <%= newMember.getUsername() %>!</h1>
<table>
    <tr>
        <th>ID</th>
        <td><%= newMember.getId() %></td>
    </tr>
    <tr>
        <th>Username</th>
        <td><%= newMember.getUsername() %></td>
    </tr>
    <tr>
        <th>Age</th>
        <td><%= newMember.getAge() %></td>
    </tr>
</table>