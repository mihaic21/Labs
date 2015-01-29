<%@ page import="model.User" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile page</title>

    <link href="resources/twitter-bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="resources/style.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <h3>
        <%
            model.User user = (model.User) request.getAttribute("user");
            out.println("Welcome " + user.getUsername());
        %>
    </h3>

    <form action="UpdateController" method="post" class="col-lg-4 top-buffer">
        <div class="panel panel-info ">
            <div class="panel-heading">
                <h5>Your profile</h5>
            </div>

            <div class="panel-body">
                <div class="input-group">
                    <span class="input-group-addon addon">Username</span>
                    <input type="text" class="form-control" value="<% out.println(user.getUsername()); %>"
                           name="username">
                </div>
                <div class="input-group">
                    <span class="input-group-addon addon">Password</span>
                    <input type="password" class="form-control" value="<% out.println(user.getPassword()); %>"
                           name="password">
                </div>
                <div class="input-group top-buffer">
                    <span class="input-group-addon addon">Name</span>
                    <input type="text" class="form-control" value="<% out.println(user.getName()); %>"
                           name="name">
                </div>
                <div class="input-group">
                    <span class="input-group-addon addon">E-mail</span>
                    <input type="text" class="form-control" value="<% out.println(user.getEmail()); %>" name="email">
                </div>
                <div class="input-group">
                    <span class="input-group-addon addon">Age</span>
                    <input type="text" class="form-control" value="<% out.println(user.getAge()); %>" name="age">
                </div>
                <div class="input-group">
                    <span class="input-group-addon addon">Hometown</span>
                    <input type="text" class="form-control" value="<% out.println(user.getHometown()); %>"
                           name="hometown">
                </div>

                <img src="<%=user.getPhotoPath() %>"/>

                <input type="submit" value="Update" class="btn btn-info top-buffer"/>
            </div>

        </div>
    </form>

    <form action="UploadController" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="<% out.print(user.getId()); %>">
        <input name="photoPath" type="file" size="50">
        <input name="submit" type="submit" value="Submit">
    </form>

    <div class="container col-md-8">
        <h2>Search</h2>

        <div class="btn-group col-md-3">
            <button class="btn btn-info" type="button">Choose a filed</button>
            <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                <span class="caret"></span>
                <span class="sr-only">Toggle Dropdown</span>
            </button>
            <ul class="dropdown-menu" role="menu">
                <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Username</a></li>
                <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Name</a></li>
                <li role="presentation"><a role="menuitem" tabindex="-1" href="#">E-mail</a></li>
                <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Age</a></li>
                <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Hometown</a></li>
            </ul>
        </div>


        <form action="SearchController" method="get">
            <div class="col-lg-3">
                <input type="text" class="form-control" placeholder="Search for.." name="searchedWord">
            </div>

            <input type="hidden" name="id" value="<% out.print(user.getId()); %>">
            <input id="criteria" type="hidden" name="criteria">
            <button type="submit" class="btn btn-info">Search</button>
        </form>
        <div class="panel panel-default top-buffer">
            <div class="panel-heading">Results</div>

            <table class="table">
                <tr>
                    <td><strong>Username</strong></td>
                    <td><strong>Name</strong></td>
                    <td><strong>E-mail</strong></td>
                    <td><strong>Age</strong></td>
                    <td><strong>Hometown</strong></td>
                </tr>
                <% List<User> resultedUsers = (List<User>) request.getAttribute("users");
                    if (resultedUsers != null) {
                        for (User resultedUser : resultedUsers) { %>
                <tr>
                    <td>
                        <% out.println(resultedUser.getUsername()); %>
                    </td>
                    <td>
                        <% out.println(resultedUser.getName()); %>
                    </td>
                    <td>
                        <% out.println(resultedUser.getEmail()); %>
                    </td>
                    <td>
                        <% out.println(resultedUser.getAge()); %>
                    </td>
                    <td>
                        <% out.println(resultedUser.getHometown()); %>
                    </td>
                </tr>
                <% }
                } %>
            </table>
        </div>
    </div>
</div>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="resources/twitter-bootstrap/js/bootstrap.js"></script>
<script>
    $(function () {

        $(".dropdown-menu li a").click(function () {

            var selectedCriteria = $(this).text();
            $(".btn:first-child").text(selectedCriteria);
            $(".btn:first-child").val(selectedCriteria);
            $("#criteria").val(selectedCriteria)
        });

    });
</script>
</body>
</html>
