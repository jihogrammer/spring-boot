<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dev.jihogrammer.member.Member" %>
<%@ page import="dev.jihogrammer.member.port.in.MemberRegisterCommand" %>
<%@ page import="dev.jihogrammer.member.port.out.Members" %>
<%@ page import="dev.jihogrammer.member.port.out.SingletonInMemoryMemberRepository" %>
<%
    var members = SingletonInMemoryMemberRepository.getInstance();

    var command = MemberRegisterCommand.builder()
            .name(request.getParameter("name"))
            .age(Integer.parseInt(request.getParameter("age")))
            .build();
    var newMember = members.register(command);
%>

<h1>Hello, <%= newMember.name() %>!</h1>
<table>
    <tr>
        <th>ID</th>
        <td><%= newMember.id().value() %></td>
    </tr>
    <tr>
        <th>Username</th>
        <td><%= newMember.name() %></td>
    </tr>
    <tr>
        <th>Age</th>
        <td><%= newMember.age() %></td>
    </tr>
</table>

<a href="/">home</a>
