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
import javax.servlet.http.HttpSession;

import beans.Spotkanie;
import beans.Turniej;
import beans.Uzytkownik;
import utils.DBUtils;
import utils.MyUtils;
 
@WebServlet(urlPatterns = { "/editTournament" })
public class EditTournamentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public EditTournamentServlet() {
        super();
    }
 
    // Show Trener edit page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        HttpSession session = request.getSession();
        Uzytkownik user = MyUtils.getLoginedUser(session);
        String code = (String) request.getParameter("code");
 
        Turniej turniej = null;
 
        String errorString = null;
        List<Uzytkownik> managerowie=null;
        try {
        	int id = Integer.valueOf(code);
        	turniej = DBUtils.znajdzTurniej(conn, id);
        	managerowie = DBUtils.listaUzytkownikow(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        if (errorString != null && turniej == null) {
            response.sendRedirect(request.getServletPath() + "/tournaments");
            return;
        }
        int admin =0;
        if(user!=null)
        {
        	if(user.getUprawnienia().equals("admin"))
        	{
        		admin=1;
        	}
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("turniej", turniej);
        request.setAttribute("managerowie", managerowie);
        request.setAttribute("admin", admin);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/editTournamentView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    // After the user modifies the dyski information, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        int kod = Integer.valueOf(request.getParameter("idTurnieju"));
        Date data=null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
			data = formatter.parse(request.getParameter("data"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        String miejsce = (String) request.getParameter("miejsce");
        String wynik = (String) request.getParameter("nawierzchnia");
        Turniej turniej = new Turniej(kod,data,miejsce,wynik,0);
        HttpSession session = request.getSession();
    	Uzytkownik user = MyUtils.getLoginedUser(session);
    	 List<Uzytkownik> managerowie=null;
    	 int admin=0;
    	if(user!=null)
    	{
    		if(user.getUprawnienia().equals("admin"))
    		{
    			turniej.setIdManagera(Integer.valueOf(request.getParameter("idUzytkownika")));
    			System.out.println(turniej.getIdManagera()+" "+turniej.getIdTurnieju());
    			admin=1;
    		}
    	}
        String errorString = null;
        try {
            if(turniej.getIdManagera()!=0)
            {
            	System.out.println("TU!");
            	DBUtils.aktualizujTurniej2(conn, turniej);
            	managerowie = DBUtils.listaUzytkownikow(conn);
            }
            else
            {
            	DBUtils.aktualizujTurniej(conn, turniej);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("turniej", turniej);
        request.setAttribute("managerowie", managerowie);
        request.setAttribute("admin", admin);
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editTournamentView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the Dyski listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/tournaments");
        }
    }
 
}
