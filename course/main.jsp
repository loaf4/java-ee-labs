<%@ page import="org.example.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>To-Do List</title>
    <link rel="stylesheet" type="text/css" href="style-main.css">
</head>
<body>

<% User user = (User) session.getAttribute("user"); %>
<% Map<String, TaskList> lists = (HashMap<String, TaskList>) request.getServletContext().getAttribute("lists"); %>

<div id="sidebar">
    <% if (user != null) { %>
        <div>
            <p><%= user.getEmail() %></p>
            <p><a href="${pageContext.request.contextPath}/logout">Выйти</a></p>
        </div>

        <form action="/add-list" method="post" class="add-task-group-form">
            <label for="title">Добавить группу заданий:</label>
            <input type="text" name="title">
            <button type="button">Добавить</button>
        </form>

        <% for (Map.Entry<String, TaskList> list : lists.entrySet()) { %>
            <ul id="task-groups" class="task-groups">
                <form class="task-group-form">
                    <label><%= list.getKey() %></label>
                    <button type="button" onclick="toggleTaskGroup(this)">Открыть</button>
                </form>
            </ul>
        <% } %>
    <% } %>
</div>

<% if (lists != null) { %>
    <div id="main-content">
        <% for (Map.Entry<String, TaskList> tl : lists.entrySet()) { %>
            <div id="current-task-group" class="task-group">
                <h2>
                    <%= tl.getKey() %>
                    <form action="/delete-task" method="post" class="delete-task-form">
                        <input type="hidden" name="list" value="<%= tl.getKey() %>">
                        <button type="button">Удалить</button>
                    </form>
                </h2>
                <form action="/add-task" method="post" class="add-task-form">
                    <input type="hidden" name="list" value="<%= tl.getKey() %>">
                    <label for="task">Добавить задачу:</label>
                    <input type="text" class="task" name="task">
                    <button type="button">Добавить</button>
                </form>
                <ul id="task-list" class="task-list">
                    <% for (Map.Entry<String, Task> t : tl.getValue().getTasks().entrySet()) { %>
                        <li class="task">
                            <span><%= t.getKey() %></span>
                            <div class="task-actions">
                                <form action="/delete-task" method="post" class="delete-task-form">
                                    <input type="hidden" name="list" value="<%= tl.getKey() %>">
                                    <input type="hidden" class="task" name="task" value="<%= t.getKey() %>">
                                    <button type="button">Удалить</button>
                                </form>
                                <form action="/refactor-task" method="post" class="refactor-task-form">
                                    <input type="hidden" name="list" value="<%= tl.getKey() %>">
                                    <input type="hidden" class="task" name="task" value="<%= t.getKey() %>">
                                    <button type="button">Редактировать</button>
                                </form>
                                <form action="/complete-task" method="post" class="complete-task-form">
                                    <input type="hidden" name="list" value="<%= tl.getKey() %>">
                                    <input type="hidden" class="task" name="task" value="<%= t.getKey() %>">
                                    <button type="button">Выполнено</button>
                                </form>
                            </div>
                        </li>

                        <form action="/add-sub-task" method="post" class="add-sub-task-form">
                            <input type="hidden" name="list" value="<%= tl.getKey() %>">
                            <input type="hidden" name="task" value="<%= t.getKey() %>">
                            <label for="sub-task">Добавить подзадачу:</label>
                            <input type="text" class="sub-task" name="sub-task">
                            <button type="button">Добавить</button>
                        </form>

                        <% for (Map.Entry<String, SubTask> st : t.getValue().getSubTasks().entrySet()) { %>
                            <ul class="sub-tasks">
                                <li class="task">
                                    <span><%= st.getKey() %></span>
                                    <div class="task-actions">
                                        <form action="/delete-sub-task" method="post" class="delete-sub-task-form">
                                            <input type="hidden" name="list"value="<%= tl.getKey() %>">
                                            <input type="hidden" name="task" value="<%= t.getKey() %>">
                                            <input type="hidden" name="sub-task" value="<%= st.getKey() %>">
                                            <button type="button">Удалить</button>
                                        </form>
                                        <form action="/refactor-sub-task" method="post" class="delete-sub-task-form">
                                            <input type="hidden" name="list"value="<%= tl.getKey() %>">
                                            <input type="hidden" name="task" value="<%= t.getKey() %>">
                                            <input type="hidden" name="sub-task" value="<%= st.getKey() %>">
                                            <button type="button">Редактировать</button>
                                        </form>
                                        <form action="/complete-sub-task" method="post" class="delete-sub-task-form">
                                            <input type="hidden" name="list"value="<%= tl.getKey() %>">
                                            <input type="hidden" name="task" value="<%= t.getKey() %>">
                                            <input type="hidden" name="sub-task" value="<%= st.getKey() %>">
                                            <button type="button">Выполнено</button>
                                        </form>
                                    </div>
                                </li>
                            </ul>
                        <% } %>
                    <% } %>
                </ul>
            </div>

            <div id="bottom-right-section">
                <input type="text" id="share" name="share">
                <button action="/share" method="post" type="button">Поделиться</button>
            </div>
        <% } %>
    </div>
<% } %>

</body>
</html>
