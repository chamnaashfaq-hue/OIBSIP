<%@ page import="java.util.List, Model.User" %>
<html>
<body>
    <h2>Registered Members</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Role</th>
        </tr>
        <%
            List<User> users = (List<User>) request.getAttribute("users");
            for (User u : users) {
        %>
        <tr>
            <td><%= u.getId() %></td>
            <td><%= u.getName() %></td>
            <td><%= u.getEmail() %></td>
            <td><%= u.getRole() %></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>