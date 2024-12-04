package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Uzytkownik;
import beans.Zawodnik;
import utils.DBUtils;
import utils.MyUtils;
 
@WebServlet(urlPatterns = { "/editPlayer" })
public class EditPlayerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public EditPlayerServlet() {
        super();
    }
 
    // Show Trener edit page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String code = (String) request.getParameter("code");
 
        Zawodnik zawodnik = null;
 
        String errorString = null;
       
        try {
        	int id = Integer.valueOf(code);
            zawodnik = DBUtils.znajdzZawodnika(conn, id);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        if (errorString != null && zawodnik == null) {
            response.sendRedirect(request.getServletPath() + "/players");
            return;
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("zawodnik", zawodnik);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/editPlayerView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    // After the user modifies the dyski information, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        int kod = Integer.valueOf(request.getParameter("idZawodnika"));
        String imie = (String) request.getParameter("imie");
        String nazwisko = (String) request.getParameter("nazwisko");
        Zawodnik zawodnik = new Zawodnik(kod, imie, nazwisko);
 
        String errorString = null;
       
        try {
            DBUtils.aktualizujZawodnika(conn, zawodnik);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("zawodnik", zawodnik);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editPlayerView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the Players listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/players");
        }
    }
 
}
