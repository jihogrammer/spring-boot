<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dev.jihogrammer.member.Member" %>
<%@ page import="dev.jihogrammer.member.MemberRepository" %>
<%@ page import="dev.jihogrammer.jsp.SingletonInMemoryMembers" %>
<%
    MemberRepository repository = SingletonInMemoryMembers.getInstance();
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
                    .append("<td>").append(member.getId()).append("</td>")
                    .append("<td>").append(member.getUsername()).append("</td>")
                    .append("<td>").append(member.getAge()).append("</td>")
                    .append("</tr>");
        }
        out.write(sb.toString());
    %>
    </tbody>
</table>
