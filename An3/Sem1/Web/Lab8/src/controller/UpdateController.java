package controller;

import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Ioana on 1/4/2015.
 */
public class UpdateController extends HttpServlet {
    public UpdateController() {
        super();
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        int id = (Integer) request.getSession().getAttribute("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String age = request.getParameter("age");
        String hometown = request.getParameter("hometown");
        User user = new User(id, username, password, name, email, age, hometown, null);
        RequestDispatcher rd = null;

        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.updateUser(user);

        rd = request.getRequestDispatcher("/index.jsp");
        request.setAttribute("user", user);

        rd.forward(request, response);
    }
}
