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

@WebServlet(urlPatterns = { "/createPlayer" })
public class CreatePlayerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    public CreatePlayerServlet() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
        
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/createPlayerView.jsp");
        dispatcher.forward(request, response);
    }
 
    // When the user enters the Zespol information, and click Submit.
    // This method will be called.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String errorString = null;
    
        Connection conn = MyUtils.getStoredConnection(request);
        String imie = (String) request.getParameter("imie");
        String nazwisko = (String) request.getParameter("nazwisko");
        Zawodnik zawodnik = new Zawodnik(0,imie,nazwisko);
        
        if (errorString == null) {
            try {
                DBUtils.dodajZawodnika(conn, zawodnik);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }
 
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("zawodnik", zawodnik);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createPlayerView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the Zespol listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/players");
        }
    }
}
