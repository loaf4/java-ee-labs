<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="style-login.css">
</head>
<body>

<div id="login-container">
    <form action="${pageContext.request.contextPath}/register" method="post" class="auth-form" id="register-form">
        <h2>Register</h2>
        <label for="email">Email:</label>
        <input type="email" name="email" required>

        <label for="password">Password:</label>
        <input type="password" name="password" required>

        <button type="submit">Sign up</button>
    </form>

    <div class="vertical-line"></div>

    <form action="${pageContext.request.contextPath}/login" method="post" class="auth-form" id="login-form">
        <h2>Login</h2>
        <label for="email">Email:</label>
        <input type="email" name="email" required>

        <label for="password">Password:</label>
        <input type="password" name="password" required>

        <button type="submit">Sign in</button>
    </form>
</div>

<% if (request.getParameter("error-auth") != null) { %>
    <script>
        alert("Invalid input")
    </script>
<% } %>
<% if (request.getParameter("error-reg") != null) { %>
    <script>
        alert("User with typed email already exists");
    </script>
<% } %>

</body>
</html>
