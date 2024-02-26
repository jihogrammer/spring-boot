<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dev.jihogrammer.member.Member" %>
<%@ page import="dev.jihogrammer.member.port.out.Members" %>
<%@ page import="dev.jihogrammer.member.port.out.SingletonInMemoryMemberRepository" %>
<%
    Members repository = SingletonInMemoryMemberRepository.getInstance();
    Iterable<Member> members = repository.findAll();
%>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Age</th>
        </tr>
    </thead>
    <tbody>
    <%
        StringBuilder sb = new StringBuilder();
        for (Member member : repository.findAll()) {
            sb
                    .append("<tr>")
                    .append("<td>").append(member.id().value()).append("</td>")
                    .append("<td>").append(member.name()).append("</td>")
                    .append("<td>").append(member.age()).append("</td>")
                    .append("</tr>");
        }
        out.write(sb.toString());
    %>
    </tbody>
</table>

<a href="/">home</a>
