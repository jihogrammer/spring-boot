<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>New Form</title>
</head>
<body>
<form action="/members/save.jsp" method="post">
    <table>
        <tr>
            <th>username</th>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <th>age</th>
            <td><input type="number" name="age"></td>
        </tr>
    </table>
    <input type="submit" />
</form>
</body>
</html>

<a href="/">home</a>
