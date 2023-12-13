<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="style-login.css">
</head>
<body>

<div id="login-container">
    <form action="/login" class="auth-form" id="register-form">
        <h2>Register</h2>
        <label for="register-email">Email:</label>
        <input type="email" id="register-email" name="register-email" required>

        <label for="register-password">Password:</label>
        <input type="password" id="register-password" name="register-password" required>

        <button type="submit">Register</button>
    </form>

    <div class="vertical-line"></div>

    <form action="/register" class="auth-form" id="login-form">
        <h2>Login</h2>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">Login</button>
    </form>
</div>

<% if (request.getParameter("error-auth") != null) { %>
    <script>
        alert("Введены неверные данные")
    </script>
<% } %>
<% if (request.getParameter("error-reg") != null) { %>
    <script>
        alert("Пользователь с таким email уже существует");
    </script>
<% } %>

</body>
</html>
