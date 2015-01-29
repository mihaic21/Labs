<%@ page import="java.util.List" %>
<%@ page import="edu.cs.ubbcluj.ro.model.Grave" %>
<%@ page import="edu.cs.ubbcluj.ro.utils.Constants" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">

    <link href="css/styles.css" rel="stylesheet" type="text/css">

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="jquery.js"></script>
    <title>Morminte</title>
</head>
<body>
<div class="menu">
    <div>
        <img class="logo" src="resources/logo.gif" alt="logo">
    </div>
</div>

<% if (session.getAttribute("option") != null) { %>
<div class="title-div">
    <% if (session.getAttribute("option").equals(Constants.GRAVES_MANAGEMENT))
        out.print(Constants.GRAVES_MANAGEMENT);
      else if (session.getAttribute("option").equals(Constants.VIEW_MONUMENT_GRAVES))
        out.print(Constants.MONUMENTS_REGISTER);
      else if (session.getAttribute("option").equals(Constants.VIEW_GRAVES))
        out.print(Constants.GRAVES_REGISTER);
    %>
</div>
<% } %>

<div class="content">
    <% if (session.getAttribute("option") != null) { %>
    <form action="GraveServlet" method="GET">
        <% if (session.getAttribute("option").equals(Constants.GRAVES_MANAGEMENT)) { %>
        <input class="img-button" type="image" src="resources/file_add.png" value="Add" name="act" title="Ad?ugare"/>
        <input class="img-button" type="image" src="resources/file_edit.png" value="Edit" name="act" disabled="true"
               title="Modificare"/>
        <input class="img-button" type="image" src="resources/file_delete.png" value="Delete" name="act" disabled="true"
               title="?tergere">
        <input class="img-button" type="image" src="resources/file_save.png" value="Save" name="act" disabled="true"
               title="Desc?rcare">
        <input type="submit" class="search-box" name="act" value="Caut?">
        <input type="text" class="search-box" placeholder="Caut?" name="filter-value">
        <a hidden id="download" href="resources/temp/<% if (session.getAttribute("saved") != null)
                out.print(session.getAttribute("saved") + ".doc");
                session.removeAttribute("saved"); %>"></a>
        <% } %>

        <table class="data-table">
            <tr>
                <% if (session.getAttribute("option").equals(Constants.GRAVES_MANAGEMENT)) { %>
                <th></th>
                <% } %>
                <th>Nr. crt</th>
                <th>Cimir</th>
                <th>Parcela</th>
                <th>Numar mormant</th>
                <th>Nume detinator</th>
                <th>Domiciliu detinator</th>
                <th>Numar chitanta</th>
                <th>Nume mort</th>
                <th>Data inmormantare</th>
                <th>Suprafata mormant</th>
                <th>Observatii</th>
                <th>Numar act de modificare</th>
                <th>Fotografie mormant</th>
            </tr>

            <% List<List<String>> graves = (List<List<String>>) session.getAttribute("graves");
                if (graves.size() == 0) { %>
            <tr>
                <td colspan="<%int val = session.getAttribute("option").equals(Constants.GRAVES_MANAGEMENT)?7:5; out.print(val);%>">
                    <% out.print(Constants.NO_RECORDS_MSG); %>
                </td>
            </tr>

            <% }
                int i = 0;
                for (List<String> grave : graves) {
                    i++;%>

            <tr>
                <% if (session.getAttribute("option").equals(Constants.GRAVES_MANAGEMENT)) { %>
                <td><input name="selected-con" class="selected-con" type="checkbox" value="<% out.print(grave.get(2));%>"></td>
                <% } %>
                <td class="address-td"><% out.print(i); %></td>
                <td class="address-td">
                    <a href="ConcessionServlet?act=Edit&selected-con=<%= grave.get(2) %>">
                        <%= grave.get(2) %>
                    </a>
                </td>
                <td class="address-td"><%= grave.get(0) %></td>
                <td class="address-td"><%= grave.get(1) %></td>
                <td class="address-td"><%= grave.get(3) + " " + grave.get(4) %></td>
                <td class="address-td"><%= grave.get(5) %></td>
                <td class="address-td"><%= grave.get(6) %></td>
                <td class="address-td"><%= grave.get(7) + " " + grave.get(8) %></td>
                <td class="address-td"><%= grave.get(9) %></td>
                <td class="address-td"><%= grave.get(10) %></td>
                <td class="address-td"><%= grave.get(11) %></td>
                <td class="address-td"><%= grave.get(12) %></td>
                <td class="address-td"><img src="resources/grave.jpg" width="25px" height="25px"/></td>
            </tr>
            <% } %>
        </table>
    </form>
    <% session.removeAttribute("option"); %>
    <% } %>
</div>

</body>
</html>