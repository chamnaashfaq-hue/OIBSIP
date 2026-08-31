<%@ page import="java.util.List, Model.Book" %>
<html>
<body>
    <h2>All Books</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>ISBN</th>
            <th>Category</th>
            <th>Total Quantity</th>
            <th>Available Quantity</th>
        </tr>
        <%
            List<Book> books = (List<Book>) request.getAttribute("books");
            for (Book b : books) {
        %>
        <tr>
            <td><%= b.getId() %></td>
            <td><%= b.getTitle() %></td>
            <td><%= b.getAuthor() %></td>
            <td><%= b.getIsbn() %></td>
            <td><%= b.getCategory() %></td>
            <td><%= b.getTotalQuantity() %></td>
            <td><%= b.getAvailableQuantity() %></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>