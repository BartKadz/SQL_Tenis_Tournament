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

@WebServlet(urlPatterns = { "/players" })
public class PlayerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    public PlayerListServlet() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String errorString = null;
    	Connection conn = MyUtils.getStoredConnection(request);
    	List<Zawodnik> zawodnicy = null;
    	try {
    		zawodnicy = DBUtils.listaZawodnikow(conn);
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
        	}
        	else
        	{
        		admin=0;
        	}
        }
    	request.setAttribute("errorString", errorString);
        request.setAttribute("zawodnicy", zawodnicy);
        request.setAttribute("admin", admin);
    	 RequestDispatcher dispatcher = request.getServletContext()
                 .getRequestDispatcher("/WEB-INF/views/PlayersListView.jsp");
         dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doGet(request, response);
    }
       
}
