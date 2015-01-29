<%@ page import="edu.cs.ubbcluj.ro.utils.Constants" %>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="edu.cs.ubbcluj.ro.model.Owner" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.cs.ubbcluj.ro.model.Dead" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Persoane</title>
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
  <% if (session.getAttribute("option").equals(Constants.OWNER_MANAGEMENT))
        out.print(Constants.OWNER_MANAGEMENT);
    else if (session.getAttribute("option").equals(Constants.DEAD_MANAGEMENT))
        out.print(Constants.DEAD_MANAGEMENT);
    else if (session.getAttribute("option").equals(Constants.VIEW_DEAD))
        out.print(Constants.OWNED_DEAD_REGISTER);
    else if (session.getAttribute("option").equals(Constants.VIEW_UNOWNED_DEAD))
        out.print(Constants.UNOWNED_DEAD_REGISTER);%>

  <%--<% if (session.getAttribute("option").equals(Constants.VIEW_DEAD)){ %>--%>
  <%--<div class="selection-div">--%>
    <%--<label for="year-selection">Anul</label>--%>
    <%--<select id="year-selection" name="<%out.print(StringEscapeUtils.escapeHtml3(Constants.VIEW_DEAD));%>">--%>
      <%--<%int latest = Calendar.getInstance().get(Calendar.YEAR);--%>
      <%--for (int i = latest; i >= latest - 10; i--) {--%>
      <%--%>--%>
      <%--<option value="<%out.print(i);%>"><%out.print(i);%></option>--%>
      <%--<% } %>--%>
    <%--</select>--%>
  <%--</div>--%>
  <%--<% } %>--%>
  <%--<% if (session.getAttribute("option").equals(Constants.VIEW_UNOWNED_DEAD)){ %>--%>
  <%--<div class="selection-div">--%>
    <%--<label for="year-selection">Anul</label>--%>
    <%--<select id="year-selection-copy" name="<%out.print(StringEscapeUtils.escapeHtml3(Constants.VIEW_UNOWNED_DEAD));%>">--%>
      <%--<%int latest = Calendar.getInstance().get(Calendar.YEAR);--%>
        <%--for (int i = latest; i >= latest - 10; i--) {--%>
      <%--%>--%>
      <%--<option value="<%out.print(i);%>"><%out.print(i);%></option>--%>
      <%--<% } %>--%>
    <%--</select>--%>
  <%--</div>--%>
  <%--<% } %>--%>

</div>
<% } %>

<div class="content">
  <% if (session.getAttribute("option") != null) { %>
  <form action="PersonServlet" method="GET">
    <% if (session.getAttribute("option").equals(Constants.OWNER_MANAGEMENT)
    || session.getAttribute("option").equals(Constants.DEAD_MANAGEMENT)) { %>
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
        <% if (session.getAttribute("option").equals(Constants.OWNER_MANAGEMENT)) { %>
        <th></th>
        <th>ID</th>
        <th>Nume</th>
        <th>Prenume</th>
        <th>CNP</th>
        <th>Adresa</th>
        <% } else if (session.getAttribute("option").equals(Constants.DEAD_MANAGEMENT)) { %>
        <th></th>
        <th>ID</th>
        <th>Nume</th>
        <th>Prenume</th>
        <th>Religie</th>
        <th>Mormant</th>
        <% } else if (session.getAttribute("option").equals(Constants.VIEW_DEAD)) { %>
        <th>ID</th>
        <th>Nume</th>
        <th>Prenume</th>
        <th>Religie</th>
        <th>Mormant</th>
        <% } else if (session.getAttribute("option").equals(Constants.VIEW_UNOWNED_DEAD)) { %>
        <th>ID</th>
        <th>Nume</th>
        <th>Prenume</th>
        <th>Religie</th>
        <th>Mormant</th>
        <% } %>
      </tr>

      <% List<Owner> owners = (List<Owner>)session.getAttribute("owners");
      List<Dead> deads = (List<Dead>)session.getAttribute("deads");
      if (owners.size() == 0 && session.getAttribute("option").equals(Constants.OWNER_MANAGEMENT)) { %>
      <tr>
        <td colspan="4">
          <% out.print(Constants.NO_RECORDS_MSG); %>
        </td>
      </tr>
      <% } else if (deads.size() == 0) { %>
      <tr>
        <td colspan="4">
          <% out.print(Constants.NO_RECORDS_MSG); %>
        </td>
      </tr>

      <% }
      if (session.getAttribute("option").equals(Constants.OWNER_MANAGEMENT)) {
        int i = 0;
        for (Owner owner : owners) {
          i++;%>
      <tr>
        <td><input name="selected-con" class="selected-con" type="checkbox" value="<% out.print(owner.getId());%>"></td>

        <td>
          <a href="OwnerServlet?act=Edit&selected-con=<% out.print(owner.getId()); %>">
            <% out.print(owner.getId()); %>
          </a>
        </td>
        <td><% out.print(owner.getFirstName()); %></td>
        <td><% out.print(owner.getLastName()); %></td>
        <td><% out.print(owner.getCnp()); %></td>
        <td class="address-td"><% out.print(owner.getAddress()); %></td>
      </tr>
      <% } } else if (session.getAttribute("option").equals(Constants.DEAD_MANAGEMENT)) {
      int i = 0;
      for (Dead dead : deads) {
      i++;%>
      <tr>
        <td><input name="selected-con" class="selected-con" type="checkbox" value="<% out.print(dead.getId());%>"></td>

        <td>
          <a href="DeadServlet?act=Edit&selected-con=<% out.print(dead.getId()); %>">
            <% out.print(dead.getId()); %>
          </a>
        </td>
        <td><% out.print(dead.getFirstName()); %></td>
        <td><% out.print(dead.getLastName()); %></td>
        <td><% out.print(dead.getReligion()); %></td>
        <td><% out.print(dead.getGrave().getNumber()); %></td>
      </tr>

      <% } } else  if (session.getAttribute("option").equals(Constants.VIEW_DEAD)) {
        for (Dead dead : deads) { %>
      <tr>
        <td><% out.print(dead.getId()); %></td>
        <td><% out.print(dead.getFirstName()); %></td>
        <td><% out.print(dead.getLastName()); %></td>
        <td><% out.print(dead.getReligion()); %></td>
        <td><% out.print(dead.getGrave().getNumber()); %></td>
      </tr>
      <% } } else  if (session.getAttribute("option").equals(Constants.VIEW_UNOWNED_DEAD)) {
        List<Dead> unownedDeads = (List<Dead>)session.getAttribute("unownedDeads");
        for (Dead dead : unownedDeads) { %>
      <tr>
        <td><% out.print(dead.getId()); %></td>
        <td><% out.print(dead.getFirstName()); %></td>
        <td><% out.print(dead.getLastName()); %></td>
        <td><% out.print(dead.getReligion()); %></td>
        <td><% out.print(dead.getGrave().getNumber()); %></td>
      </tr>
      <% } } %>
    </table>

  </form>
  <% session.removeAttribute("option"); %>
  <% } %>
</div>
</body>
</html>
