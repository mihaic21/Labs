package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

public class LoginController extends HttpServlet {
 
    public LoginController() {
        super();
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        RequestDispatcher rd = null;

        DatabaseHandler databaseHandler = new DatabaseHandler();
        User user = databaseHandler.getUserIfValid(username, password);
        if (user != null) {
            rd = request.getRequestDispatcher("/index.jsp");
            request.setAttribute("user", user);
            request.getSession().setAttribute("id", user.getId());
        }
        else {
            rd = request.getRequestDispatcher("/error.jsp");
        }
        rd.forward(request, response);
    }
 
}