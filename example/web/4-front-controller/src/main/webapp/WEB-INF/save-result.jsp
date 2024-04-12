<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>Hello, ${member.name()}!</h1>
<table>
    <tr>
        <th>ID</th>
        <td>${member.id()}</td>
    </tr>
    <tr>
        <th>Username</th>
        <td>${member.name()}</td>
    </tr>
    <tr>
        <th>Age</th>
        <td>${member.age()}</td>
    </tr>
</table>

<a href="/">home</a>
