package edu.cs.ubbcluj.ro.servlets;

import edu.cs.ubbcluj.ro.model.Dead;
import edu.cs.ubbcluj.ro.model.Funeral;
import edu.cs.ubbcluj.ro.model.Grave;
import edu.cs.ubbcluj.ro.repository.service.DeadService;
import edu.cs.ubbcluj.ro.repository.service.FuneralService;
import edu.cs.ubbcluj.ro.repository.service.GraveService;
import edu.cs.ubbcluj.ro.repository.service.GraveyardService;
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
import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@WebServlet("/FuneralServlet")
public class FuneralServlet extends HttpServlet {

    @EJB
    DeadService deadService;

    @EJB
    FuneralService funeralService;

    @EJB
    GraveyardService graveyardService;

    @EJB
    GraveService graveService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String act = request.getParameter("act");
        HttpSession session = request.getSession();
        List<Funeral> funerals = null;

        act = new String(act.getBytes("iso-8859-1"), "UTF-8");
        switch (act) {
            case Constants.DELETE :
                String[] toDelete = request.getParameterValues("selected-con");
                for (String val : toDelete)
                    deleteFuneral(findFuneralById(val));
                session.setAttribute("funerals", funeralService.getAll());
                session.setAttribute("option", Constants.FUNERALS_MANAGEMENT);
                response.sendRedirect(Constants.FUNERAL_PAGE + "?act=" + Constants.FUNERALS_MANAGEMENT.replace(' ', '+'));
                return;
            case Constants.ADD :
                session.setAttribute("graveyards", graveyardService.getAll());
                break;
            case Constants.EDIT :
                session.setAttribute("graveyards", graveyardService.getAll());
                break;
            case Constants.FUNERALS_MANAGEMENT :
                funerals = funeralService.getAll();
                session.setAttribute("option", act);
                break;
        }
        if (funerals != null)
            session.setAttribute("funerals", funerals);
        RequestDispatcher rd = request.getRequestDispatcher(Constants.FUNERAL_PAGE);
        if (act.equals(Constants.ADD) || act.equals(Constants.EDIT))
            rd = request.getRequestDispatcher(Constants.FUNERAL_MANAGEMENT_PAGE);
        rd.forward(request, response);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("act");
        HttpSession session = request.getSession();

        switch (act) {
            case Constants.SAVE :
               // Funeral f = findFuneralById(request.getParameter("funeral-id"));
               // Grave g = graveService.getAll().get(Integer.parseInt(request.getParameter("parcel-number")));
                Funeral f = new Funeral();
                Integer parcel = f.getDead().getGrave().getParcel().getNumber();
                Dead d = getDead(request.getParameter("dead-name"), request.getParameter("dead-religion"));

                if (d == null)
                  addFuneral(d, parcel);
        }

        session.setAttribute("funerals", funeralService.getAll());
        session.setAttribute("option", Constants.FUNERALS_MANAGEMENT);
        response.sendRedirect(Constants.FUNERAL_PAGE + "?act=" + Constants.FUNERALS_MANAGEMENT.replace(' ', '+'));


    }

    private Funeral findFuneralById(String id) {
        List<Funeral> funerals = funeralService.getAll();
        for (Funeral f : funerals)
            if (Integer.toString(f.getId()).equals(id))
                return f;
        return null;
    }

    private Dead getDead(String name, String religion) throws UnsupportedEncodingException{
        name = new String(name.getBytes("iso-8859-1"), "UTF-8");
        religion = new String(religion.getBytes("iso-8859-1"), "UTF-8");
        List<Dead> deads = deadService.getAll();
        for (Dead d : deads) {
            String fullname = d.getFirstName() + " " + d.getLastName();
            if (fullname.equals(name) && d.getReligion().equals(religion))
                return d;

        }
        return null;
    }

    private Grave getGrave(Integer parcel) {
        List<Grave> graves = graveService.getAll();
        for (Grave g : graves) {
            if ((g.getNumber()).equals(parcel))
                return g;
        }
        return null;
    }
    private void deleteFuneral(Funeral fn) {
        // for (Dead d : fn.getDead())
        //    deadService.deleteDead(d);
        funeralService.deleteFuneral(fn);
    }

    private void addFuneral(Dead d,Integer parcel){
        Funeral f = new Funeral();
        f.setDead(d);
        f.setDate(new Date());
        Time time = new Time(System.currentTimeMillis());
        f.setTime(time);
        parcel = f.getDead().getGrave().getParcel().getNumber();
        //d.addFuneral(f);
        //f.setTime(new Time());
        funeralService.insertFuneral(f);


    }



}
