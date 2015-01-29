package edu.cs.ubbcluj.ro.servlets;

import edu.cs.ubbcluj.ro.model.Concession;
import edu.cs.ubbcluj.ro.model.Dead;
import edu.cs.ubbcluj.ro.model.Owner;
import edu.cs.ubbcluj.ro.repository.service.ConcessionService;
import edu.cs.ubbcluj.ro.repository.service.DeadService;
import edu.cs.ubbcluj.ro.repository.service.OwnerService;
import edu.cs.ubbcluj.ro.utils.Constants;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/PersonServlet")
public class PersonServlet extends HttpServlet {

    @EJB
    OwnerService ownerService;

    @EJB
    DeadService deadService;

    @EJB
    ConcessionService concessionService;


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("act");
        HttpSession session = request.getSession();
        List<Owner> owners = null;
        List<Dead> deads = null;
        List<Dead> unownedDeads = null;

        act = new String(act.getBytes("iso-8859-1"), "UTF-8");

        switch (act) {
            case Constants.OWNER_MANAGEMENT:
                owners = ownerService.getAll();
                deads = deadService.getAll();
                session.setAttribute("option", act);
                break;
            case Constants.DEAD_MANAGEMENT:
                owners = ownerService.getAll();
                deads = deadService.getAll();
                session.setAttribute("option", act);
                break;
            case Constants.VIEW_DEAD:
                deads = deadService.getAll();
                session.setAttribute("option", act);
                break;
            case Constants.VIEW_UNOWNED_DEAD:
                unownedDeads = getUnownedDeads();
                session.setAttribute("option", act);
                break;
            case Constants.SEARCH:

                break;
        }

        if (owners != null){
            session.setAttribute("owners", owners);
        }
        if (deads != null){
            session.setAttribute("deads", deads);
        }
        if (unownedDeads != null){
            session.setAttribute("unownedDeads", unownedDeads);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constants.PERSONS_PAGE);
        if (act.equals(Constants.ADD) || act.equals(Constants.EDIT)){
            requestDispatcher = request.getRequestDispatcher(Constants.PERSONS_MANAGEMENT_PAGE);
        }
        requestDispatcher.forward(request, response);

    }

    private List<Dead> getUnownedDeads(){
        List<Dead> result = new ArrayList<>();
        for (Dead dead : deadService.getAll()){
            if (!isOwned(dead)){
                result.add(dead);
            }
        }
        return result;
    }


    private boolean isOwned(Dead dead){
        for (Concession concession : concessionService.getAll()){
            for (Dead currentDead : concession.getGrave().getDeads()){
                if (dead.getId() == currentDead.getId()){
                    return true;
                }
            }        }
        return false;
    }

}