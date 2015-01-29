package edu.cs.ubbcluj.ro.servlets;


import edu.cs.ubbcluj.ro.model.Grave;
import edu.cs.ubbcluj.ro.repository.service.ConcessionService;
import edu.cs.ubbcluj.ro.repository.service.GraveService;
import edu.cs.ubbcluj.ro.repository.service.MonumentService;
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


@WebServlet("/GraveServlet")
public class GraveServlet extends HttpServlet {

    @EJB
    GraveService graveService;
    @EJB
    ConcessionService concessionService;
    @EJB
    MonumentService monumentService;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("act");
        HttpSession session = request.getSession();

        if (act == null) {
            RequestDispatcher rd = request.getRequestDispatcher("Graves.jsp");
            rd.forward(request, response);
        } else if (act.equals(Constants.GRAVES_MANAGEMENT)) {
            loadAllGraves(session);
        } else if (act.equals(Constants.VIEW_MONUMENT_GRAVES)) {
            loadAllMonumentGraves(session);
        } else if (act.equals(Constants.VIEW_GRAVES)) {
            loadGraves(session);
        }

        RequestDispatcher rd = request
                .getRequestDispatcher("Graves.jsp");
        rd.forward(request, response);

    }

    private void loadAllGraves(HttpSession session) {
        List<Grave> allGraves = graveService.getAll();

        session.setAttribute("graves", getGravesCompleteInfo(allGraves));
        session.setAttribute("option", Constants.GRAVES_MANAGEMENT);
    }

    private void loadAllMonumentGraves(HttpSession session) {
        List<Grave> allGraves = graveService.getAll();
        List<Grave> monumentsGraves = new ArrayList<>();

        for (Grave g : allGraves) {
            if (monumentService.isMonument(g.getId())) {
                monumentsGraves.add(g);
            }
        }

        session.setAttribute("graves", getGravesCompleteInfo(monumentsGraves));
        session.setAttribute("option", Constants.VIEW_MONUMENT_GRAVES);
    }

    private void loadGraves(HttpSession session) {
        List<Grave> allGraves = graveService.getAll();
        List<Grave> graves = new ArrayList<>();

        for (Grave g : allGraves) {
            if (!monumentService.isMonument(g.getId())) {
                graves.add(g);
            }
        }

        session.setAttribute("graves", getGravesCompleteInfo(graves));
        session.setAttribute("option", Constants.VIEW_GRAVES);
    }

    private List<List<String>> getGravesCompleteInfo(List<Grave> graves) {
        List<List<String>> gravesWithCompleteInfo = new ArrayList<>();
        for (Grave g : graves) {
            List<String> graveCompleteInfo = concessionService.getData(g.getId());
            if (graveCompleteInfo == null) {
                // mormantul nu a fost concesionat => "" in locul informatiei specifice mormintelor concesionate
                graveCompleteInfo = graveService.getData(g.getId());
                for (int i = 0; i < 11; i++) {
                    graveCompleteInfo.add("");
                }
            }
            gravesWithCompleteInfo.add(graveCompleteInfo);
        }
        return gravesWithCompleteInfo;
    }
}
