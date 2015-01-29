<%@ page import="edu.cs.ubbcluj.ro.utils.Constants" %>
<%@ page import="edu.cs.ubbcluj.ro.model.Funeral" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Programare inmormantari</title>

    <link href="css/styles.css" rel="stylesheet" type="text/css">

    <script src="jquery-1.11.1.js"></script>
    <script src="jquery.js"></script>
</head>
<body>
<div class="menu">
    <div>
        <img class="logo" src="resources/logo.gif" alt="logo">
    </div>
</div>

<% if (session.getAttribute("option") != null) { %>
<div class="title-div">
    <% if (session.getAttribute("option").equals(Constants.FUNERALS_MANAGEMENT))
        out.print(Constants.FUNERALS_MANAGEMENT); %>
</div>
<% } %>
<div class="content">
    <% if (session.getAttribute("option") != null) {  %>
        <form action="FuneralServlet" method="GET">
            <% if (session.getAttribute("option").equals(Constants.FUNERALS_MANAGEMENT)) { %>
            <input class="img-button" type="image" src="resources/file_add.png" value="Add" name="act" title="Ad?ugare"/>
            <input class="img-button" type="image" src="resources/file_edit.png" value="Edit" name="act" disabled="true" title="Modificare"/>
            <input class="img-button" type="image" src="resources/file_delete.png" value="Delete" name="act" disabled="true" title="Stergere">
            <input type="submit" class="search-box" name="act" value="Cauta">
            <input type="text" class="search-box" placeholder="Cauta" name="filter-value">
            <% } %>

            <table class="data-table">
                <tr>
                    <% if (session.getAttribute("option").equals(Constants.FUNERALS_MANAGEMENT)) { %>
                        <th></th>
                    <% } %>
                    <th>Nume si prenume decedat</th>
                    <th>Religia</th>
                    <th>Locul de inmormantare</th>
                </tr>

                <% List<Funeral> funerals = (List<Funeral>)session.getAttribute("funerals");
                    if (funerals.size() == 0) { %>
                <tr>
                    <td colspan="<% int val = session.getAttribute("option").equals(Constants.FUNERALS_MANAGEMENT)?4:4; out.println(val); %>">
                        <% out.print(Constants.NO_RECORDS_MSG); %>
                    </td>
                </tr>

                <% }
                int i = 0;
                for (Funeral f : funerals) {
                    i++; %>

                <tr>
                    <% if (session.getAttribute("option").equals(Constants.FUNERALS_MANAGEMENT)){ %>
                        <td><input name="selected-con" class="selected-con" type="checkbox" value="<% out.print(f.getId());%>"></td>
                    <% } %>
                    <td><% out.print(f.getDead().getFirstName() + " " + f.getDead().getLastName()); %></td>
                    <td><% out.print(f.getDead().getReligion()); %></td>
                    <td><% out.print(f.getTime() + ", " + f.getDate() + ", Parcel " + f.getDead().getGrave().getNumber()); %></td>
                </tr>

                <% } %>
            </table>


        </form>
        <% session.removeAttribute("option"); %>
        <% } %>

</div>

</body>
</html>