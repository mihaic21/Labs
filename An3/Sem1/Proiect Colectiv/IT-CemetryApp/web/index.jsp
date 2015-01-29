<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login</title>
</head>
<body>

<div id="wrapper">
    <div id="firstPart">
        <h1>Gestiunea Cimitirelor</h1>
    </div>

    <div id="secondPart">
        <form action="LoginServlet" method="post">
            <p id="name"> Nume utilizator </p>
            <input id="user" type="text" placeholder="Utilizator" name="username"/>

            <p id="pass"> Parola </p>
            <div>
                <input id="parola" type="password" placeholder="Parola" name="password"/>
            </div>

            <input type="submit" name="act" value="Autentificare" class="button" />
        </form>
    </div>
    </div>
</body>
</html>