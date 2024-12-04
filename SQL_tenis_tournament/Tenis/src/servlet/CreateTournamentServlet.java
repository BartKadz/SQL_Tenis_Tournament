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
import beans.Turniej;
import beans.Uzytkownik;
import beans.Zawodnik;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = { "/createTournament" })
public class CreateTournamentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    public CreateTournamentServlet() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Connection conn = MyUtils.getStoredConnection(request);
    	HttpSession session = request.getSession();
    	Uzytkownik user = MyUtils.getLoginedUser(session);
        String errorString = null;
        List<Uzytkownik> managerowie=null;
        if(user!=null)
        {
        	if(user.getUprawnienia().equals("admin"))
        	{
        		try {
        			managerowie = 	DBUtils.listaUzytkownikow(conn);
        		}catch(SQLException e)
        		{
        			e.printStackTrace();
                    errorString = e.getMessage();
        		}
        	}
        	else
        	{
        		Uzytkownik manager = new Uzytkownik();
        		try {
        			managerowie= new ArrayList<Uzytkownik>();
        			manager= DBUtils.ZnajdzUzytkownika(conn, user.getIdUzytkownika());
        			managerowie.add(manager);
        		}catch(SQLException e)
        		{
        			e.printStackTrace();
                    errorString = e.getMessage();
        		}
        	}
        }
        System.out.println(managerowie.size());
        System.out.println(managerowie.get(0));
        System.out.println(user.getIdUzytkownika());
        System.out.println(user.getLogin());
        System.out.println(user.getUprawnienia());
        
 
        request.setAttribute("errorString", errorString);
        request.setAttribute("managerowie", managerowie);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/createTournamentView.jsp");
        dispatcher.forward(request, response);
    }
 
    // When the user enters the Zespol information, and click Submit.
    // This method will be called.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String errorString = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Connection conn = MyUtils.getStoredConnection(request);
        Date data=null;
		try {
			data = formatter.parse(request.getParameter("data"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        String miejsce = (String) request.getParameter("miejsce");
        String Nawierzchnia = (String) request.getParameter("nawierzchnia");
        int idManagera = Integer.valueOf(request.getParameter("idUzytkownika"));
        Turniej turniej = new Turniej(0,data,miejsce, Nawierzchnia,idManagera);
        if (errorString == null) {
            try {
                DBUtils.dodajTurniej(conn, turniej);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }
 
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("turniej", turniej);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createTournamentView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the Zespol listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/tournaments");
        }
    }
}