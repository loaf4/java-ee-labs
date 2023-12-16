<%@ page import="org.example.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>To-Do List</title>
    <link rel="stylesheet" type="text/css" href="style-main.css">
</head>
<body>

<% User user = (User) session.getAttribute("user"); %>
<% Map<String, TaskList> lists = (HashMap<String, TaskList>) session.getAttribute("lists"); %>

<div id="sidebar">
    <% if (user != null) { %>
        <div>
            <p><%= user.getEmail() %></p>
            <p><a href="${pageContext.request.contextPath}/logout">Loguot</a></p>
        </div>

        <form action="${pageContext.request.contextPath}/add-list" method="post" class="add-task-group-form">
            <label for="title">Add new task list:</label>
            <input type="text" name="title">
            <button type="submit">Add</button>
        </form>

        <% for (Map.Entry<String, TaskList> list : lists.entrySet()) { %>
            <div id="<%= list.getKey().replaceAll(" ", "") %>" class="task-groups" onclick="showList(this)">
                <div>
                    <%= list.getKey() %>
                </div>
            </div>
        <% } %>
    <% } else { %>
        <p><a href="${pageContext.request.contextPath}/login">Login</a></p>
    <% } %>
</div>

<% if (lists != null) { %>
    <% for (Map.Entry<String, TaskList> tl : lists.entrySet()) { %>
        <div class="main-content <%= tl.getKey().replaceAll(" ", "") %>" style="display: none;">
            <div id="current-task-group" class="task-group">
                <h2>
                    <%= tl.getKey() %>
                    <form action="${pageContext.request.contextPath}/delete-list" method="post" class="delete-task-form">
                        <input type="hidden" name="list" value="<%= tl.getKey() %>">
                        <button type="submit">Delete</button>
                    </form>
                </h2>
                <br>
                <form action="${pageContext.request.contextPath}/add-task" method="post" class="add-task-form">
                    <input type="hidden" name="list" value="<%= tl.getKey() %>">
                    <label for="task">Add task:</label>
                    <input type="text" class="task-input" name="task">
                    <button type="submit">Add</button>
                </form>
                <ul id="task-list" class="task-list">
                    <% for (Map.Entry<String, Task> t : tl.getValue().getTasks().entrySet()) { %>
                        <li>
                            <div class="task">
                                <span><%= t.getKey() %></span>
                                <div class="task-actions">
                                    <form action="${pageContext.request.contextPath}/delete-task" method="post" class="delete-task-form">
                                        <input type="hidden" name="list" value="<%= tl.getKey() %>">
                                        <input type="hidden" name="task" value="<%= t.getKey() %>">
                                        <button type="submit">Delete</button>
                                    </form>
                                    <form class="refactor-task-form">
                                        <button type="button" onclick="editTask(this)">Edit</button>
                                    </form>
                                    <form action="${pageContext.request.contextPath}/complete-task" method="post" class="complete-task-form">
                                        <input type="hidden" name="list" value="<%= tl.getKey() %>">
                                        <input type="hidden" name="task" value="<%= t.getKey() %>">
                                        <button type="submit">Complete</button>
                                    </form>
                                </div>
                            </div>

                            <div class="edit-task-form" style="display: none;">
                                <form action="${pageContext.request.contextPath}/refactor-task" method="post" class="refactor-task-form">
                                    <input type="hidden" name="list" value="<%= tl.getKey() %>">
                                    <input type="hidden" name="older-task" value="<%= t.getKey() %>">
                                    <label for="task">Edit task:</label>
                                    <input type="text" class="task-input" name="task" value="<%= t.getKey() %>">
                                    <button type="submit">Confirm</button>
                                </form>
                            </div>
                        </li>

                        <form action="${pageContext.request.contextPath}/add-sub-task" method="post" class="add-sub-task-form">
                            <input type="hidden" name="list" value="<%= tl.getKey() %>">
                            <input type="hidden" name="task" value="<%= t.getKey() %>">
                            <label for="sub-task">Add sub task:</label>
                            <input type="text" class="task-input" name="sub-task">
                            <button type="submit">Add</button>
                        </form>

                        <ul class="sub-tasks">
                            <% for (Map.Entry<String, SubTask> st : t.getValue().getSubTasks().entrySet()) { %>
                                <li class="task">
                                    <span><%= st.getKey() %></span>
                                    <div class="task-actions">
                                        <form action="${pageContext.request.contextPath}/delete-sub-task" method="post" class="delete-sub-task-form">
                                            <input type="hidden" name="list"value="<%= tl.getKey() %>">
                                            <input type="hidden" name="task" value="<%= t.getKey() %>">
                                            <input type="hidden" name="sub-task" value="<%= st.getKey() %>">
                                            <button type="submit">Delete</button>
                                        </form>
                                        <form class="delete-sub-task-form">
                                            <button type="button" onclick="editTask(this)">Edit</button>
                                        </form>
                                        <form action="${pageContext.request.contextPath}/complete-sub-task" method="post" class="delete-sub-task-form">
                                            <input type="hidden" name="list"value="<%= tl.getKey() %>">
                                            <input type="hidden" name="task" value="<%= t.getKey() %>">
                                            <input type="hidden" name="sub-task" value="<%= st.getKey() %>">
                                            <button type="submit">Complete</button>
                                        </form>
                                    </div>
                                </li>

                                <div class="edit-task-form" style="display: none;">
                                    <form action="${pageContext.request.contextPath}/refactor-sub-task" method="post" class="refactor-task-form">
                                        <input type="hidden" name="list" value="<%= tl.getKey() %>">
                                        <input type="hidden" name="task" value="<%= t.getKey() %>">
                                        <input type="hidden" name="older-sub-task" value="<%= st.getKey() %>">
                                        <label for="sub-task">Edit sub task:</label>
                                        <input type="text" class="task-input" name="sub-task" value="<%= st.getKey() %>">
                                        <button type="submit">Confirm</button>
                                    </form>
                                </div>
                            <% } %>
                        </ul>
                    <% } %>
                </ul>
            </div>

            <div id="bottom-right-section">
                <form action="${pageContext.request.contextPath}/share" method="post" class="share-form">
                    <input type="hidden" name="list" value="<%= tl.getKey() %>">
                    <label for="share">Email:</label>
                    <input type="text" id="share" name="share">
                    <button type="submit">Share</button>
                </form>
            </div>
        </div>
    <% } %>
<% } %>

<script>
    function editTask(button) {
        const forms = document.getElementsByClassName("edit-task-form");
        for (var i = 0; i < forms.length; ++i) {
            forms[i].style.display = "none";
        }
        const form = button.parentElement.parentElement.parentElement.parentElement.getElementsByClassName("edit-task-form");
        form[0].style.display = form[0].style.display === "none" ? "block" : "none";
    }

    function showList(task) {
        const containers = document.getElementsByClassName("main-content");
        const container = document.getElementsByClassName(task.id);
        const forms = document.getElementsByClassName("edit-task-form");
        for (var i = 0; i < forms.length; ++i) {
            forms[i].style.display = "none";
        }
        for (var i = 0; i < containers.length; ++i) {
            containers[i].style.display = "none";
        }
        container[0].style.display = "block";
    }
</script>

</body>
</html>
