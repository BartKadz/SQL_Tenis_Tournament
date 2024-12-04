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

import beans.Turniej;
import beans.Uzytkownik;
import beans.Zawodnik;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = { "/tournaments" })
public class TournamentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    public TournamentListServlet() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String errorString = null;
    	Connection conn = MyUtils.getStoredConnection(request);
    	List<Turniej> turnieje = null;
    	List<Uzytkownik> managerowie = null;
    	try {
    		turnieje = DBUtils.listaTurniej(conn);
    		managerowie=DBUtils.listaUzytkownikow(conn);
    	}catch(SQLException e)
    	{
    		 e.printStackTrace();
             errorString = e.getMessage();
    	}
    	
    	
    	
    	
    	HttpSession session = request.getSession();
        Uzytkownik user = MyUtils.getLoginedUser(session);
        int admin=0;
        if(user!=null)
        {
        	if(user.getUprawnienia().equals("admin"))
        	{
        		admin=1;
        		for(Turniej turniej: turnieje)
        		{
        			turniej.setCzy_moj(1);
        		}
        	}
        	else
        	{
        		for(Turniej turniej: turnieje)
        		{
        			if(turniej.getIdManagera() == user.getIdUzytkownika())
        			{
        				turniej.setCzy_moj(1);
        			}
        			else
        			{
        				turniej.setCzy_moj(0);
        			}
        		}
        	}
        }
        for(Turniej turniej: turnieje)
		{
			for(Uzytkownik manager : managerowie)
			{
				if(manager.getIdUzytkownika() == turniej.getIdManagera())
				{
					turniej.setManager(manager.getImie()+" "+manager.getNazwisko());
				}
			}
		}
        
    	request.setAttribute("errorString", errorString);
        request.setAttribute("turnieje", turnieje);
    	 RequestDispatcher dispatcher = request.getServletContext()
                 .getRequestDispatcher("/WEB-INF/views/TournamentsListView.jsp");
         dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doGet(request, response);
    }
       
}
