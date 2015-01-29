<%@ page import="edu.cs.ubbcluj.ro.utils.Constants" %>
<%@ page import="edu.cs.ubbcluj.ro.model.Concession" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Evidenta contractelor de concesiune</title>
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
    <% if (session.getAttribute("option").equals(Constants.VIEW_CONCESSIONS))
            out.print(Constants.CONCESSIONS_REGISTER);
       else
            out.print(Constants.CONCESSION_MANAGEMENT);%>

    <% if (session.getAttribute("option").equals(Constants.VIEW_CONCESSIONS)) { %>
    <div class="selection-div">
        <label for="year-selection">Anul</label>
        <select id="year-selection" name="<%out.print(StringEscapeUtils.escapeHtml3(Constants.VIEW_CONCESSIONS));%>">
            <%int latest = Calendar.getInstance().get(Calendar.YEAR);
              for (int i = latest; i >= latest - 10; i--) {
            %>
            <option value="<% out.print(i); %>"><% out.print(i); %></option>
            <% } %>
        </select>
    </div>
    <% } %>
</div>
<% } %>

<div class="content">
    <% if (session.getAttribute("option") != null) { %>
    <form action="ConcessionServlet" method="GET">
        <% if (session.getAttribute("option").equals(Constants.CONCESSION_MANAGEMENT)) { %>
        <input class="img-button" type="image" src="resources/file_add.png" value="Add" name="act" title="Adăugare"/>
        <input class="img-button" type="image" src="resources/file_edit.png" value="Edit" name="act" disabled="true" title="Modificare"/>
        <input class="img-button" type="image" src="resources/file_delete.png" value="Delete" name="act" disabled="true" title="Ștergere">
        <input class="img-button" type="image" src="resources/file_save.png" value="Save" name="act" disabled="true" title="Descărcare">
        <input type="submit" class="search-box" name="act" value="Caută">
        <input type="text" class="search-box" placeholder="Caută" name="filter-value">
        <a hidden id="download" href="resources/temp/<% if (session.getAttribute("saved") != null)
                out.print(session.getAttribute("saved") + ".doc");
                session.removeAttribute("saved"); %>"></a>
        <% } %>

        <table class="data-table">
            <tr>
                <% if (session.getAttribute("option").equals(Constants.CONCESSION_MANAGEMENT)) { %>
                    <th></th>
                <% } %>
                <th>Nr. crt</th>
                <th>Numar contract</th>
                <th>Data eliberarii</th>
                <th>Nume concesionar</th>
                <th>Adresa concesionar</th>
                <% if (session.getAttribute("option").equals(Constants.CONCESSION_MANAGEMENT)) { %>
                <th>Obiectul contractului</th>
                <% } %>
            </tr>

            <% List<Concession> concessions = (List<Concession>)session.getAttribute("concessions");
                    if (concessions.size() == 0) { %>
            <tr>
                <td colspan="<%int val = session.getAttribute("option").equals(Constants.CONCESSION_MANAGEMENT)?7:5; out.print(val);%>">
                    <% out.print(Constants.NO_RECORDS_MSG); %>
                </td>
            </tr>

            <% }
               int i = 0;
               for (Concession c : concessions) {
                    i++;%>

            <tr>
                <% if (session.getAttribute("option").equals(Constants.CONCESSION_MANAGEMENT)) { %>
                    <td><input name="selected-con" class="selected-con" type="checkbox" value="<% out.print(c.getNumber());%>"></td>
                <% } %>
                <td><% out.print(i); %></td>
                <td>
                    <a href="ConcessionServlet?act=Edit&selected-con=<% out.print(c.getNumber()); %>">
                        <% out.print(c.getNumber()); %>
                    </a>
                </td>
                <td><% out.print(new SimpleDateFormat("dd-MMM-yyyy").format(c.getDate())); %></td>
                <td><% out.print(c.getOwner().getLastName() + " " + c.getOwner().getFirstName()); %></td>
                <td class="address-td"><% out.print(c.getOwner().getAddress()); %></td>
                <% if (session.getAttribute("option").equals(Constants.CONCESSION_MANAGEMENT)) { %>
                    <td class="address-td"><% out.print("Cimitirul " + c.getGrave().getParcel().getGraveyard().getName() +
                        ", Parcela " + c.getGrave().getParcel().getNumber() + ", Numarul " + c.getGrave().getNumber()); %></td>
                <% } %>
            </tr>
            <% } %>
        </table>
    </form>
    <% session.removeAttribute("option"); %>
    <% } %>
</div>

</body>
</html>
