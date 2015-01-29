package controller;

import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterController extends HttpServlet {
    public RegisterController() {
        super();
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String age = request.getParameter("age");
        String hometown = request.getParameter("hometown");
        User user = new User(username, password, name, email, age, hometown);
        RequestDispatcher rd = null;

        DatabaseHandler databaseHandler = new DatabaseHandler();
        user = databaseHandler.registerUser(user);

        HttpSession session = request.getSession(true);
        session.setAttribute("id", user.getId());

        rd = request.getRequestDispatcher("/index.jsp");
        request.setAttribute("user", user);

        rd.forward(request, response);
    }
}
