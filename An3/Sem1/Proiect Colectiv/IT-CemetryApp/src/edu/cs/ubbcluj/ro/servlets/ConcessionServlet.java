package edu.cs.ubbcluj.ro.servlets;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.cs.ubbcluj.ro.model.*;
import edu.cs.ubbcluj.ro.repository.service.*;
import edu.cs.ubbcluj.ro.utils.ConcessionWriter;
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
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/ConcessionServlet")
public class ConcessionServlet extends HttpServlet {
    public static final String OWNER_MSG= "Proprietarul nu este înregistrat în sistem. Mergeți la secțiunea Persoane >" +
            " Gestiunea proprietarilor > Adaugare pentru a aduga un nou proprietar";

    @EJB
    ConcessionService concessionService;

    @EJB
    ReceiptService receiptService;

    @EJB
    OwnerService ownerService;

    @EJB
    GraveyardService graveyardService;

    @EJB
    TransactionService transactionService;

    @EJB
    UserService userService;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("act");
        HttpSession session = request.getSession();
        List<Concession> concessions = null;

        act = new String(act.getBytes("iso-8859-1"), "UTF-8");
        switch (act) {
            case Constants.DELETE :
                String[] toDelete = request.getParameterValues("selected-con");
                for (String val : toDelete)
                    deleteConcession(findConcessionByNumber(val));
                session.setAttribute("concessions", concessionService.getAll());
                session.setAttribute("option", Constants.CONCESSION_MANAGEMENT);
                response.sendRedirect(Constants.CONCESSIONS_PAGE + "?act=" + Constants.CONCESSION_MANAGEMENT.replace(' ', '+'));
                return;
            case "Save" :
                if (request.getParameter("selected-con") != null) {
                    ConcessionWriter.writeConcession(findConcessionByNumber(request.getParameter("selected-con")),
                            getServletContext().getRealPath("resources/temp/" + request.getParameter("selected-con") + ".doc"),
                            getServletContext().getRealPath("resources/concession-template.doc"));
                    session.setAttribute("saved", request.getParameter("selected-con"));
                }
                concessions = concessionService.getAll();
                session.setAttribute("option", Constants.CONCESSION_MANAGEMENT);
                break;
            case Constants.AUTOCOMPLETE :
                response.setContentType("application/json");
                response.getWriter().print(jsonOwners(request.getParameter("filter"), request.getParameter("field")));
                return;
            case Constants.ADD:
                session.setAttribute("graveyards", graveyardService.getAll());
                break;
            case Constants.EDIT:
                session.setAttribute("transactions", getTransactions(findConcessionByNumber(request.getParameter("selected-con"))));
                session.setAttribute("graveyards", graveyardService.getAll());
                session.setAttribute("concession", findConcessionByNumber(request.getParameter("selected-con")));
                break;
            case Constants.VIEW_CONCESSIONS :
                int year = Calendar.getInstance().get(Calendar.YEAR);
                if (request.getParameter("year") != null)
                    year = Integer.parseInt(request.getParameter("year"));
                concessions = filterByYear(year);
                session.setAttribute("option", act);
                break;
            case Constants.CONCESSION_MANAGEMENT :
                concessions = concessionService.getAll();
                session.setAttribute("option", act);
                break;
            case "loadGraveDetails" :
                String toLoad = request.getParameter("field");
                String res = loadGraveDetails(toLoad, request.getParameter("graveyard"), request.getParameter("parcel"));
                response.setContentType("application/json");
                response.getWriter().print(res);
                return;
            case Constants.SEARCH :
                concessions = filter(request.getParameter("filter-value"));
                session.setAttribute("option", Constants.CONCESSION_MANAGEMENT);
                break;
        }
        if (concessions != null)
            session.setAttribute("concessions", concessions);
        RequestDispatcher rd = request.getRequestDispatcher(Constants.CONCESSIONS_PAGE);
        if (act.equals(Constants.ADD) || act.equals(Constants.EDIT))
            rd = request.getRequestDispatcher(Constants.CONCESSION_MANAGEMENT_PAGE);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String act = req.getParameter("act");
        HttpSession session = req.getSession();
        String error = null;

        switch (act) {
            case Constants.SAVE :
                error = checkParameters(req);
                if (error == null) {
                    Concession c = findConcessionByNumber(req.getParameter("concession-nr"));
                    Owner o = getOwner(req.getParameter("concessionar-name"), req.getParameter("concessionar-cnp"),
                            req.getParameter("concessionar-address"));
                    Graveyard g = graveyardService.getAll().get(Integer.parseInt(req.getParameter("grave-name")));
                    Parcel p = g.getParcels().get(Integer.parseInt(req.getParameter("grave-parcel")));
                    Grave grave = p.getGraves().get(Integer.parseInt(req.getParameter("grave-number")));
                    if (o == null)
                        error = OWNER_MSG;
                    if (c == null)
                        createConcession(req.getParameter("concession-nr"), o, grave, req.getParameter("receipt-number"));
                    else {
                        addTransaction(c, o, grave, req.getParameter("receipt-number"));
                        editConcession(c, o, grave, req.getParameter("receipt-number"));
                    }
                }
                break;
        }

        if (error == null) {
            session.setAttribute("concessions", concessionService.getAll());
            session.setAttribute("option", Constants.CONCESSION_MANAGEMENT);
            resp.sendRedirect(Constants.CONCESSIONS_PAGE + "?act=" + Constants.CONCESSION_MANAGEMENT.replace(' ', '+'));
        } else {
            session.setAttribute("error", error);
            resp.sendRedirect(Constants.CONCESSION_MANAGEMENT_PAGE + "?act=" + act);
        }
    }


    private void addTransaction(Concession c, Owner o, Grave g, String receipt) {
        String after = "Nume concesionar: " + o.getLastName() + " " + o.getLastName()+ "\n"
                +"CNP concesionar: " + o.getCnp()  + "\n"
                +"Cimitir " + g.getParcel().getGraveyard().getName() + ", Parcela " + g.getParcel().getNumber()
                +"Nr. " + g.getNumber() + "\n"
                +"Chitanta: " + receipt;

        String before = "Nume concesionar: " + c.getOwner().getLastName() + " "
                + c.getOwner().getLastName()+ "\n"
                +"CNP concesionar: " + c.getOwner().getCnp()  + "\n"
                +"Cimitir " + c.getGrave().getParcel().getGraveyard().getName()
                +", Parcela " + c.getGrave().getParcel().getNumber()
                +"Nr. " + c.getGrave().getNumber() + "\n"
                +"Chitanta: " + c.getReceipts().get(0).getReceiptNumber();

      //  if (before.equals(after))
        //    return;

        Transaction t = new Transaction();
        t.setRecordId(c.getId());
        t.setAfterTrans(after);
        t.setBeforeTrans(before);
        t.setTableName("Concessions");
        t.setTransTime(new Date());
        t.setModificationDetails("modified");
        t.setUser(userService.getUser(26));

        transactionService.updateTransaction(t);
    }

    private List<Transaction> getTransactions(Concession c) {
        List<Transaction> all = transactionService.getAll();
        List<Transaction> res = new ArrayList<>();
        for (Transaction t : all)
            if (t.getTableName().equals("Concessions") && t.getRecordId() == c.getId())
                res.add(t);
        return res;
    }

    private String checkParameters(HttpServletRequest req) {
        if (req.getParameter("concession-nr").isEmpty())
            return ("Introduceti o valoare pentru numarul concesiunii");
        if (req.getParameter("concessionar-name").isEmpty())
            return ("Introduceti o valoare pentru numele concesionarului");
        if (req.getParameter("concessionar-cnp").isEmpty())
            return ("Introduceti o valoare pentru CNP-ul concesionarului");
        if (req.getParameter("concessionar-cnp").isEmpty())
            return ("Introduceti o valoare pentru adresa concesionarului");
        if (req.getParameter("grave-name") == null)
            return ("Selectati o valoare pentru numele cimitirului");
        if (req.getParameter("grave-parcel") == null)
            return ("Selectati o valoare pentru numarul parcelei");
        if (req.getParameter("grave-parcel") == null)
            return ("Selectati o valoare pentru numarul parcelei");
        if (req.getParameter("grave-number") == null)
            return ("Selectati o valoare pentru numarul mormatului");
        if (req.getParameter("receipt-number").isEmpty())
            return ("Introduceti numarul chitantei");
        return null;
    }

    private List<Concession> filter(String value) throws UnsupportedEncodingException {
        List<Concession> all = concessionService.getAll();
        if (value == null || value.equals(""))
            return all;
        value = new String(value.getBytes("iso-8859-1"), "UTF-8").toLowerCase();
        List<Concession> res = new ArrayList<>();
        for (Concession c : all) {
            String name = c.getOwner().getLastName() + " " + c.getOwner().getLastName();
            if (c.getNumber().toString().contains(value) || name.toLowerCase().contains(value)
                    || c.getDate().toString().contains(value) || c.getOwner().getAddress().toLowerCase().contains(value))
                res.add(c);
        }
        return res;
    }

    private void editConcession(Concession con, Owner o, Grave g, String receipt) {
        if (con.getOwner().getId() != o.getId())
            con.setOwner(o);
        if (con.getGrave().getId() != g.getId())
            con.setGrave(g);
        if (con.getReceipts().size() > 0 && !con.getReceipts().get(0).getReceiptNumber().toString().equals(receipt))
            con.getReceipts().get(0).setReceiptNumber(new BigInteger(receipt));
        concessionService.updateConcession(con);
    }

    private void createConcession(String con, Owner o, Grave g, String receipt) {
        Concession c = new Concession();
        c.setOwner(o);
        c.setGrave(g);
        c.setNumber(new BigInteger(con));
        c.setDate(new Date());
        c.setPeriod(20);
        concessionService.insertConcession(c);
        c = findConcessionByNumber(c.getNumber().toString());

        if (receipt != null && !receipt.equals("")) {
            Receipt r = new Receipt();
            r.setReceiptNumber(new BigInteger(receipt));
            r.setStartingDate(new Date());
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 20);
            r.setEndingDate(cal.getTime());
            r.setConcession(c);
            receiptService.insertReceipt(r);
        }

    }

    private Concession findConcessionByNumber(String id) {
        List<Concession> concessions = concessionService.getAll();
        for (Concession c: concessions)
            if (c.getNumber().toString().equals(id))
                return c;
        return null;
    }

    private String loadGraveDetails(String toLoad, String grNr, String prcNr) {
        String res = "";
        int graveyard = Integer.parseInt(grNr);
        if (toLoad.equals("parcel"))
            res = jsonParcels(graveyardService.getAll().get(graveyard));
        if (toLoad.equals("grave")) {
            int parcel = Integer.parseInt(prcNr);
            res = jsonGraves(graveyardService.getAll().get(graveyard).getParcels().get(parcel));
        }
        return res;
    }

    private void deleteConcession(Concession con) {
        for (Receipt r : con.getReceipts())
            receiptService.deleteReceipt(r);
        concessionService.deleteConcession(con);
    }

    private List<Concession> filterByYear(int year) {
        List<Concession> concessions = concessionService.getAll();
        List<Concession> result = new ArrayList<>();

        for (Concession c : concessions) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(c.getDate());
            if (cal.get(Calendar.YEAR) == year)
                result.add(c);
        }
        return result;
    }

    private String jsonParcels(Graveyard g) {
        List<Parcel> parcels = g.getParcels();
        JsonArray res = new JsonArray();
        for (Parcel p : parcels) {
            JsonObject obj = new JsonObject();
            obj.addProperty("number", p.getNumber());
            res.add(obj);
        }
        return res.toString();
    }

    private String jsonGraves(Parcel p) {
        List<Grave> graves = p.getGraves();
        JsonArray res = new JsonArray();
        for (Grave g : graves) {
            JsonObject obj = new JsonObject();
            obj.addProperty("number", g.getNumber());
            obj.addProperty("surface", g.getSurface());
            obj.addProperty("free", g.getConcessions().isEmpty());
            res.add(obj);
        }
        return res.toString();
    }

    private Owner getOwner(String name, String cnp, String address) throws UnsupportedEncodingException {
        name = new String(name.getBytes("iso-8859-1"), "UTF-8");
        address = new String(address.getBytes("iso-8859-1"), "UTF-8");
        List<Owner> owners = ownerService.getAll();
        for (Owner o : owners) {
            String fullname = o.getLastName() + " " + o.getFirstName();
            if (fullname.equals(name) && o.getCnp().equals(cnp) && o.getAddress().equals(address))
                return o;
        }
        return null;
     }

    private String jsonOwners(String filter, String field) throws UnsupportedEncodingException {
        filter = new String(filter.getBytes("iso-8859-1"), "UTF-8");
        JsonArray res = new JsonArray();
        List<Owner> owners = ownerService.getAll();
        for (Owner o : owners) {
            String name = o.getLastName() + " " + o.getFirstName();
            if (field.equals("name") && !name.toLowerCase().contains(filter.toLowerCase()))
                    continue;
            if (field.equals("cnp") && !((Integer)o.getId()).toString().contains(filter))
                continue;
            if (field.equals("address") && !o.getAddress().contains(filter))
                continue;
            JsonObject obj = new JsonObject();
            obj.addProperty("name", o.getLastName() + " " + o.getFirstName());
            obj.addProperty("cnp", o.getId());
            obj.addProperty("address", o.getAddress());
            res.add(obj);
        }
        System.out.print(res.size());
        return res.toString();
    }
}
