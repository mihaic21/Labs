<%@ page import="edu.cs.ubbcluj.ro.model.Funeral" %>
<%@ page import="edu.cs.ubbcluj.ro.model.Graveyard" %>
<%@ page import="edu.cs.ubbcluj.ro.utils.Constants" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.cs.ubbcluj.ro.model.Grave" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Programare inmormantari</title>
    <link href="css/styles.css" rel="stylesheet" type="text/css">
    <script src="jquery-1.11.1.js"></script>
    <script src="jquery.js"></script>
    <script src="concessions.js"></script>
</head>
<body>

<div class="menu">
  <div>
    <img class="logo" src="resources/logo.gif" alt="logo">
  </div>
</div>

<div class="content">
  <% Funeral f = (Funeral)session.getAttribute("funeral"); %>
  <div>
    <form class="data-form" method="POST" action="FuneralServlet">
      <fieldset>
        <p class="error-msg"><% if (session.getAttribute("error") != null) out.print(session.getAttribute("error")); %></p>
        <p class="form-header"><% out.print(Constants.HEADERF0); %></p>

        <label for="name-dead"> Nume </label>
        <input type="text" id="name-dead" name="name-dead"
               value="<% if (f != null) out.print(f.getDead().getFirstName());%>"/>
        <br />

          <label for="Prenume-dead"> Nume </label>
          <input type="text" id="Prenume-dead" name="Prenume-dead"
                 value="<% if (f != null) out.print(f.getDead().getLastName());%>"/>
          <br />

        <label for="religion-dead"> Religie </label>
        <input type="text" id="religion-dead" name="religion-dead"
               value="<% if (f != null) out.print(f.getDead().getReligion());%>"/>
      </fieldset>
      <fieldset>
        <p class="form-header"><% out.print(Constants.HEADERF1); %></p>
        <label for="dateF"> Data </label>
        <input type="text" id="dateF" name="dateF"
               value="<% if (f != null) out.print(f.getDate());%>"/>
        <label for="timeF"> Ora </label>
        <input type="text" id="timeF" name="timeF"
               value="<% if (f != null) out.print(f.getTime());%>"/>
        <label for="grave-name">Cimitir</label>
        <select id="grave-name" name="grave-name">
          <% if (f == null) { %>
          <option value="-1">Selecteaza...</option>
          <% } %>
          <% List<Graveyard> graveyards = (List<Graveyard>)session.getAttribute("graveyards");
             int i = 0;
             for (Graveyard g : graveyards) { %>
          <option value="<% out.print(i++); %>"
                  <% if (f != null)
                    out.print("selected"); %>>
                  <% out.print(g.getName()); %>
          </option>
          <% } %>
        </select>
        <label for="grave-parcel">Parcela</label>
        <select id="grave-parcel" name="grave-parcel"
                value="<% if (f != null) out.print(f.getDead().getGrave().getParcel().getNumber());%>"></select>
        <label for="grave-number">Numar</label>
        <select id="grave-number" name="grave-number"
                value="<% if (f != null) out.print(f.getDead().getGrave().getNumber());%>"> </select>
        <label for="grave-surface">Suprafata</label>
        <input type="text" id="grave-surface" name="grave-surface"
                value="<% if (f != null) out.print(f.getDead().getGrave().getSurface() + " m2"); %>"> </input>
      </fieldset>
      <fieldset>
        <input type="submit" name="act" value="Salveaza" />
        <input type="submit" name="act" value="Anuleaza"/>
      </fieldset>

      </fieldset>

    </form>
  </div>


</div>
<% session.removeAttribute("funeral");
  session.removeAttribute("error"); %>
</body>
</html>
