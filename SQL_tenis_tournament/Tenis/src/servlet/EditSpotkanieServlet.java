package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Spotkanie;
import beans.Uzytkownik;
import beans.Zawodnik;
import utils.DBUtils;
import utils.MyUtils;
 
@WebServlet(urlPatterns = { "/editSpotkanie" })
public class EditSpotkanieServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public EditSpotkanieServlet() {
        super();
    }
 
    // Show Trener edit page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String code = (String) request.getParameter("code");
        String tour = (String) request.getParameter("tour");
 
        Spotkanie spotkanie = null;
        List<Zawodnik> zawodnicy= null;
        String errorString = null;
        try {
        	int id = Integer.valueOf(code);
            spotkanie = DBUtils.znajdzSpotkanie(conn, id);
            zawodnicy = DBUtils.listaZawodnikow(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        for(Zawodnik zawodnik : zawodnicy)
        {
        	if(spotkanie.getIdZawodnika1()==zawodnik.getIdZawodnika())
        	{
        		spotkanie.setZawodnik1(zawodnik.getImie()+" " + zawodnik.getNazwisko());
        	}
        	else if (spotkanie.getIdZawodnika2()==zawodnik.getIdZawodnika())
        	{
        		spotkanie.setZawodnik2(zawodnik.getImie()+" " + zawodnik.getNazwisko());
        	}
        }
 
        if (errorString != null && spotkanie == null) {
            response.sendRedirect(request.getServletPath() + "/games?code="+tour);
            return;
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("spotkanie", spotkanie);
        request.setAttribute("zawodnicy", zawodnicy);
        request.setAttribute("kod", tour);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/editSpotkanieView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    // After the user modifies the dyski information, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        String errorString = null;
        int kod = Integer.valueOf(request.getParameter("idSpotkania"));
        String set1 = (String) request.getParameter("set1");
        String set2 = (String) request.getParameter("set2");
        String set3 = (String) request.getParameter("set3");
        String set4 = (String) request.getParameter("set4");
        String set5 = (String) request.getParameter("set5");
        int  id = Integer.valueOf(request.getParameter("idTurnieju"));
        int  id1 = Integer.valueOf(request.getParameter("idZawodnika1"));
        int  id2 = Integer.valueOf(request.getParameter("idZawodnika2"));	
        Spotkanie spotkanie = new Spotkanie(kod,set1,set2,set3,set4,set5,id,id1,id2);
        List<Zawodnik> zawodnicy = null;
        try {
			zawodnicy = DBUtils.listaZawodnikow(conn);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        if(id1==id2)
        {
        	errorString = "Player can not play match with himself!";
        }
        if(errorString==null)
        {
        try {
            DBUtils.aktualizujSpotkanie(conn, spotkanie);
            
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        }
        for(Zawodnik zawodnik : zawodnicy)
        {
        	if(spotkanie.getIdZawodnika1()==zawodnik.getIdZawodnika())
        	{
        		spotkanie.setZawodnik1(zawodnik.getImie()+" " + zawodnik.getNazwisko());
        	}
        	else if (spotkanie.getIdZawodnika2()==zawodnik.getIdZawodnika())
        	{
        		spotkanie.setZawodnik2(zawodnik.getImie()+" " + zawodnik.getNazwisko());
        	}
        }
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("spotkanie", spotkanie);
        request.setAttribute("zawodnicy", zawodnicy);
        request.setAttribute("kod", id);
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editSpotkanieView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the Dyski listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/games?code="+id);
        }
    }
 
}
