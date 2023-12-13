<%@ page import="org.example.AdModel" %>
<%@ page import="org.example.AdComment" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style-light.css"
          id="theme">
</head>

<button onclick="toggleTheme()">Change theme</button>

<body>
<h1>Ad board</h1>

<% String username = (String) session.getAttribute("username"); %>
<% if (username != null) { %>
<h2><%= username %> </h2>
<a href="${pageContext.request.contextPath}/logout">Logout</a><br>
<form action="${pageContext.request.contextPath}/addAd" method="post">
    <label for="adTitle">title:</label>
    <input type="text" name="adTitle" id="adTitle" required><br>

    <label for="adText">text:</label>
    <textarea name="adText" id="adText" required></textarea><br>

    <button type="submit">Add ad</button>
</form>
<% } else { %>
<a href="${pageContext.request.contextPath}/login">Login</a><br>
<% } %>

<h2>AdBoard</h2>
<div class="ad">
<% ArrayList<AdModel> ads = (ArrayList<AdModel>) request.getServletContext().getAttribute("ads"); %>
<% if (ads != null) {
    for (AdModel ad : ads) { %>
<h3 class="ad-title"><%= ad.getTitle() %>
</h3>
<div class="ad-text"><%= ad.getText() %>
</div>
<div class="ad-username"><%= ad.getUsername() %>
</div>
<div class="ad-time"><%= ad.getDate() %>
</div>
<br>
<div class="comment">
<% if (username != null) { %>
<form action="${pageContext.request.contextPath}/addComment" method="post">
    <input type="hidden" name="adId" id="adId" value="<%=ad.getId()%>">

    <label for="commentText">text:</label>
    <textarea name="commentText" id="commentText" required></textarea><br>

    <button type="submit">Add comment</button>
</form>
<%}%>
<% for (AdComment comment : ad.getComments()) { %>
<br>
<div class="ad-text"><%= comment.getText() %>
</div>
<div class="ad-username"><%= comment.getUsername() %>
</div>
<div class="ad-time"><%= comment.getDate() %>
</div>
<%}%>
</div>
<%}%>
<%}%>
</div>

<script>
  function toggleTheme() {
      var themeLink = document.getElementById('theme');
      if (themeLink.getAttribute('href') === 'style-light.css') {
          themeLink.setAttribute('href', 'style-dark.css');
      } else {
          themeLink.setAttribute('href', 'style-light.css');
      }
  }
</script>

</body>
</html>
