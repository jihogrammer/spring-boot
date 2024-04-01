<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>Hello, ${newMember.name()}!</h1>
<table>
    <tr>
        <th>ID</th>
        <td>${newMember.id()}</td>
    </tr>
    <tr>
        <th>Username</th>
        <td>${newMember.name()}</td>
    </tr>
    <tr>
        <th>Age</th>
        <td>${newMember.age()}</td>
    </tr>
</table>

<a href="/">home</a>
