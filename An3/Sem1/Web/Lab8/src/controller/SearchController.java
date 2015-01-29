package controller;

import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchController extends HttpServlet{
    public SearchController() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String criteria = request.getParameter("criteria");
        String searchWord = request.getParameter("searchedWord");
        DatabaseHandler handler = new DatabaseHandler();
        List<User> resultedUsers = handler.getUsersByCriteria(criteria, searchWord);

        User user = handler.getUserById(id);
        RequestDispatcher rd = null;

        rd = request.getRequestDispatcher("/index.jsp");
        if (resultedUsers != null) {
            request.setAttribute("users", resultedUsers);
        }
        request.setAttribute("user", user);
        rd.forward(request, response);
    }
}
