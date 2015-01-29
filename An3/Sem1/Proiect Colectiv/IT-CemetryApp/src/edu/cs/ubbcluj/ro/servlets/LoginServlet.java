package edu.cs.ubbcluj.ro.servlets;


import edu.cs.ubbcluj.ro.repository.service.UserService;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    UserService userService;

    /**
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse
     *      response)
     *
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("act");

        if (act == null) {
            RequestDispatcher rd = request
                    .getRequestDispatcher("Funerals.jsp");
            rd.forward(request, response);
        } else if (act.equals("Autentificare")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            boolean loggedIn = userService.login(username, password);
            if (loggedIn) {
                RequestDispatcher rd = request
                        .getRequestDispatcher("Funerals.jsp");
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = request
                        .getRequestDispatcher("LoginFailed.jsp");
                rd.forward(request, response);

            }

        }
    }

}
