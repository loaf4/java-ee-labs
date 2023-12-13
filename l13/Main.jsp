<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.lang.String"%>
<%@ page import="java.util.Objects"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Телефонная книжка</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }

        #container {
            width: 600px;
            margin: 20px auto;
            background-color: #fff;
            border: 1px solid #ccc;
            padding: 10px;
            border-radius: 5px;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
        }

        form {
            text-align: center;
            padding: 10px;
        }

        label, input {
            display: block;
            margin: 5px 0;
        }

        input[type="text"] {
            width: 98%;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        button {
            display: block;
            margin: 10px 0;
            padding: 5px 10px;
            background-color: #007BFF;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        ul {
            list-style: none;
            padding: 0;
        }

        li {
            display: flex;
            justify-content: space-between;
            border-bottom: 1px solid #ccc;
            padding: 5px 0;
        }
    </style>
</head>
<body>
    <div id="container">
        <h1>Телефонная книжка</h1>
        <h2>Добавление пользователя</h2>
        <form method="POST" action="${pageContext.request.contextPath}/phonebook/add">
            <label for="name">Имя:</label>
            <input type="text" name="name" placeholder="Введите имя">
            <label for="phone">Телефон:</label>
            <input type="text" name="phone" placeholder="Введите телефон">
            <input type="submit" value="Add">
        </form>
        <br>
        <h2>Удаление пользователя</h2>
        <form method="POST" action="${pageContext.request.contextPath}/phonebook/delete-user">
            <label for="name">Имя:</label>
            <input type="text" name="name" placeholder="Введите имя">
            <input type="submit" value="Delete user">
        </form>
        <br>
        <h2>Удаление номера пользователя</h2>
        <form method="POST" action="${pageContext.request.contextPath}/phonebook/delete-phone">
            <label for="name">Имя:</label>
            <input type="text" name="name" placeholder="Введите имя">
            <label for="phone">Телефон:</label>
            <input type="text" name="phone" placeholder="Введите телефон">
            <input type="submit" value="Delete phone">
        </form>
        <ul>
            <%Map<String, ArrayList<String>> users = (Map<String, ArrayList<String>>) request.getAttribute("notes");%>
            <%for (Map.Entry<String, ArrayList<String>> u : users.entrySet()) {%>
            <ul>
                <li><strong>Имя: <%= u.getKey()%></strong></li>
                <ul>
                    <%for (String p : u.getValue()) {%>
                    <li><%= p%></li>
                    <%}%>
                </ul>
            </ul>
            <%}%>
        </ul>
    </div>
</body>
</html>
