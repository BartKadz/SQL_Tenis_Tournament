package servlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Spotkanie;
import beans.Uzytkownik;
import beans.Zawodnik;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = { "/createSpotkanie" })
public class CreateSpotkanieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    public CreateSpotkanieServlet() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Connection conn = MyUtils.getStoredConnection(request);
    	HttpSession session = request.getSession();
    	String code = (String) request.getParameter("code");
    	String tour = (String) request.getParameter("tour");
        String errorString = null;
        List<Zawodnik> zawodnicy = null;
        try {
        	zawodnicy= DBUtils.listaZawodnikow(conn);
        }catch(SQLException e)
        {
        	  e.printStackTrace();
              errorString = e.getMessage();
        }
        request.setAttribute("errorString", errorString);
        request.setAttribute("zawodnicy", zawodnicy);
        request.setAttribute("kod", tour);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/createSpotkanieView.jsp");
        dispatcher.forward(request, response);
    }
 
    // When the user enters the Zespol information, and click Submit.
    // This method will be called.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String errorString = null;
        Connection conn = MyUtils.getStoredConnection(request);
        String Set1 = (String) request.getParameter("set1");
        String Set2 = (String) request.getParameter("set2");
        String Set3 = (String) request.getParameter("set3");
        String Set4 = (String) request.getParameter("set4");
        String Set5 = (String) request.getParameter("set5");
        Integer id = Integer.valueOf(request.getParameter("idTurnieju"));
        Integer id1 = Integer.valueOf(request.getParameter("idZawodnika1"));
        Integer id2 = Integer.valueOf(request.getParameter("idZawodnika2"));
        Spotkanie spotkanie = null;
        List<Zawodnik> zawodnicy = null;
        try {
        	zawodnicy= DBUtils.listaZawodnikow(conn);
        }catch(SQLException e)
        {
        	  e.printStackTrace();
              errorString = e.getMessage();
        }
        if(id1!=id2)
        {
        spotkanie = new Spotkanie(0,Set1,Set2,Set3,Set4,Set5,id,id1,id2);
        }
        else
        {
         spotkanie = new Spotkanie(0,Set1,Set2,Set3,Set4,Set5,id,id1,id2);
        	errorString = "Player can not play match with himself";
        }
        if (errorString == null) {
            try {
                DBUtils.dodajSpotkanie(conn, spotkanie);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }
 
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("spotkanie", spotkanie);
        request.setAttribute("kod", id);
        request.setAttribute("zawodnicy", zawodnicy);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createSpotkanieView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the Zespol listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/games?code="+id);
        }
    }
}
