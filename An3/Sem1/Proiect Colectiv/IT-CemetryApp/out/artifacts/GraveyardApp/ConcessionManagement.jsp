<%@ page import="edu.cs.ubbcluj.ro.utils.Constants" %>
<%@ page import="edu.cs.ubbcluj.ro.model.Graveyard" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="edu.cs.ubbcluj.ro.model.Concession" %>
<%@ page import="edu.cs.ubbcluj.ro.model.Transaction" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Evidenta contractelor de concesiune</title>
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
    <% Concession c = (Concession)session.getAttribute("concession"); %>
    <div>
        <form class="data-form" method="POST" action="ConcessionServlet">
            <fieldset>
                <p class="error-msg"><% if (session.getAttribute("error") != null) out.print(session.getAttribute("error"));%></p>
                <p class="form-header"><% out.print(Constants.HEADER0); %></p>
                <label for="concession-nr">Numar</label>
                <input type="text" id="concession-nr" name="concession-nr"
                       value="<% if(c != null) out.print(c.getNumber());%>">
            </fieldset>
            <fieldset>
                <p class="form-header"><% out.print(Constants.HEADER1); %></p>
                <p class="form-header1"><% out.print(Constants.HEADER1_1); %></p>
                <label for="concessionar-name">Nume și prenume</label>
                <ul name="concessionar-name" class="autocomplete-select" property="name"></ul>
                <input type="text" id="concessionar-name" name="concessionar-name" class="autocomplete"
                       value="<% if (c != null) out.print(c.getOwner().getLastName() + " " + c.getOwner().getFirstName());%>">
                <br>
                <label for="concessionar-cnp">CNP</label>
                <ul name="concessionar-cnp" class="autocomplete-select" property="cnp"></ul>
                <input type="text" id="concessionar-cnp" name="concessionar-cnp" class="autocomplete"
                       value="<%if (c != null) out.print(c.getOwner().getCnp());%>">
                <br>
                <label for="concessionar-address">Adresa</label>
                <ul name="concessionar-address" class="autocomplete-select" property="address"></ul>
                <input type="text" id="concessionar-address" name="concessionar-address" class="autocomplete"
                       value="<%if (c != null) out.print(c.getOwner().getAddress());%>">
                <input type="hidden" id="concessionar-id" name="concessionar-id"
                       value="<%if (c != null) out.print(c.getOwner().getId());%>">
            </fieldset>

            <fieldset>
                <p class="form-header"><% out.print(Constants.HEADER2); %></p>
                <label for="grave-name">Cimitir</label>
                <select id="grave-name" name="grave-name">
                    <% if (c == null) { %>
                    <option value="-1">Seletează...</option>
                    <% } %>
                    <% List<Graveyard> graveyards = (List<Graveyard>)session.getAttribute("graveyards");
                       int i = 0;
                       for (Graveyard g : graveyards) { %>
                    <option value="<% out.print(i++); %>"
                        <% if (c != null && c.getGrave().getParcel().getGraveyard().getName().equals(g.getName()))
                                out.print("selected");%>>
                        <% out.print(g.getName()); %>
                    </option>
                    <% } %>
                </select>
                <label for="grave-parcel">Parcela</label>
                <select id="grave-parcel" name="grave-parcel"
                        value="<% if (c != null) out.print(c.getGrave().getParcel().getNumber());%>"></select>
                <label for="grave-number">Numar</label>
                <select id="grave-number"  name="grave-number"
                        value="<% if (c != null) out.print(c.getGrave().getNumber());%>"></select>
                <label for="grave-surface">Suprafata</label>
                <input type="text" id="grave-surface" name="grave-surface" disabled
                       value="<% if (c != null) out.print(c.getGrave().getSurface() + " m2"); %>">
                <br>
            </fieldset>
            <fieldset>
                <p class="form-header"><% out.print(Constants.HEADER3); %></p>
                <label for="receipt-number">Numar chitanta</label>
                <input type="text" id="receipt-number" name="receipt-number"
                       value="<% if (c != null && c.getReceipts().size() > 0) out.print(c.getReceipts().get(0).getReceiptNumber()); %>">
                <label >Chitanta a fost achitata in baza</label>
                <input class="form-radio" type="radio" name="receipt-cause" value="1"
                        <% if (c!= null) out.print("checked"); %>>
                <p class="radio-text" value="1">Cererii numarul:<% if (c == null || c.getReceipts().size() == 0)
                            out.print("____/" + Calendar.getInstance().get(Calendar.YEAR));
                       else
                            out.print(c.getReceipts().get(0).getReceiptNumber() + "/" + Calendar.getInstance().get(Calendar.YEAR));
                    %></p>
                <input class="form-radio" type="radio" name="receipt-cause" value="2">
                <p class="radio-text" value="2">Adeverintei de inhumare numarul: ____</p>
                <input class="form-radio" type="radio" name="receipt-cause" value="3">
                <p class="radio-text" value="3">Reconcesionarii cu prezentarea fotografiei</p>
                <label for="doc-nr" class="hidden-obj">Numar</label>
                <input type="text" id="doc-nr" name="doc-nr" visible="false" class="hidden-obj">
            </fieldset>
            <fieldset>
                <input type="submit" name="act" value="Salveaza"/>
                <input type="submit" name="act" value="Anuleaza"/>
            </fieldset>
            <% if (c != null) {%>
            <fieldset>
                <img src="resources/down-arrow.png" class="more-icon">
                <p class="more-text">Mai multe detalii</p>
            </fieldset>
            <div class="history-div">
                <fieldset>
                    <p class="form-header"><% out.print(Constants.HISTORY); %></p>
                    <div>
                        <table class="data-table">
                            <tr>
                                <th>Data</th>
                                <th>Utilizator</th>
                                <th>Valori initiale</th>
                                <th>Valori dupa modificare</th>
                            </tr>
                        <% List<Transaction> transactions = (List<Transaction>)session.getAttribute("transactions");
                        if (transactions != null)
                        for (Transaction t : transactions) {
                        %>
                        <tr>
                            <td><%out.print(new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").format(t.getTransTime()));%></td>
                            <td><%out.print(t.getUser().getUsername());%></td>
                            <td class="address-td"><%out.print(t.getBeforeTrans());%></td>
                            <td class="address-td"><%out.print(t.getAfterTrans());%></td>
                        </tr>
                        <% } %>
                        </table>
                    </div>
                </fieldset>
            </div>
            <% } %>
        </form>
    </div>
</div>
<% session.removeAttribute("concession");
session.removeAttribute("error");%>

</body>
</html>
