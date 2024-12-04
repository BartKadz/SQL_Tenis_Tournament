package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Uzytkownik;
import utils.DBUtils;
import utils.MyUtils;
 
@WebServlet(urlPatterns = { "/register" })
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public RegisterServlet() {
        super();
    }
 
    // Show Login page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // Forward to /WEB-INF/views/loginView.jsp
        // (Users can not access directly into JSP pages placed in WEB-INF)
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/register.jsp");
 
        dispatcher.forward(request, response);
 
    }
 
    // When the user enters userName & password, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Uzytkownik user = new Uzytkownik();
        String userName = request.getParameter("login");
        String password = request.getParameter("haslo");
        String imie = request.getParameter("imie");
        String nazwisko = request.getParameter("nazwisko");
        String uprawnienia = "manager";
 
        boolean hasError = false;
        String errorString = null;
 
        if (userName == null || password == null || userName.length() == 0 || password.length() == 0 
        		|| imie==null || imie.length()==0 || nazwisko==null || nazwisko.length()==0) {
            hasError = true;
            errorString = "Required username,password, name, surname";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            user.setLogin(userName);
            user.setHaslo(password);
            user.setImie(imie);
            user.setNazwisko(nazwisko);
            user.setUprawnienia(uprawnienia);
            try {
                // Create user in DB
                DBUtils.DodajUzytkownika(conn, user);
                user = DBUtils.ZnajdzUzytkownika(conn, userName, password);
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
            if (!hasError) {
                user = new Uzytkownik();
                user.setLogin(userName);
                user.setHaslo(password);
     
                // Store information in request attribute, before forward.
                request.setAttribute("errorString", errorString);
                request.setAttribute("user", user);
     
                // Forward to /WEB-INF/views/login.jsp
                RequestDispatcher dispatcher //
                        = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
     
                dispatcher.forward(request, response);
            }
            // If no error
            // Store user information in Session
            // And redirect to userInfo page.
            else {
                HttpSession session = request.getSession();
                MyUtils.storeLoginedUser(session, user);     
                // Redirect to userInfo page.
                response.sendRedirect(request.getContextPath() + "/home");
        }
        }
    }
}
