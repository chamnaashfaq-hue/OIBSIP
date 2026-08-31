<%@ page import="java.util.List, Model.IssuedBook" %>
<html>
<body>
    <h2>Currently Issued Books</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Book</th>
            <th>User</th>
            <th>Issue Date</th>
            <th>Due Date</th>
        </tr>
        <%
            List<IssuedBook> list = (List<IssuedBook>) request.getAttribute("issuedBooks");
            for (IssuedBook ib : list) {
        %>
        <tr>
            <td><%= ib.getId() %></td>
            <td><%= ib.getBookTitle() %></td>
            <td><%= ib.getUserName() %></td>
            <td><%= ib.getIssueDate() %></td>
            <td><%= ib.getDueDate() %></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>