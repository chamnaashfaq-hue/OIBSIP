<%@ page import="java.util.List, Model.IssuedBook" %>
<html>
<body>
    <h2>Unpaid Fines</h2>
    <table border="1">
        <tr>
            <th>Issued Book ID</th>
            <th>Book</th>
            <th>User</th>
            <th>Fine Amount</th>
        </tr>
        <%
            List<IssuedBook> fines = (List<IssuedBook>) request.getAttribute("fines");
            for (IssuedBook ib : fines) {
        %>
        <tr>
            <td><%= ib.getId() %></td>
            <td><%= ib.getBookTitle() %></td>
            <td><%= ib.getUserName() %></td>
            <td><%= ib.getFineAmount() %></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>