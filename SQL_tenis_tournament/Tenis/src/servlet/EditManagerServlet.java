package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Uzytkownik;
import utils.DBUtils;
import utils.MyUtils;
 
@WebServlet(urlPatterns = { "/editManager" })
public class EditManagerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public EditManagerServlet() {
        super();
    }
 
    // Show Manager edit page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String code = (String) request.getParameter("code");
 
        Uzytkownik manager = null;
 
        String errorString = null;
        try {
        	int id = Integer.valueOf(code);
        	manager = DBUtils.ZnajdzUzytkownika(conn, id);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        if (errorString != null && manager == null) {
            response.sendRedirect(request.getServletPath() + "/managers");
            return;
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("manager", manager);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/editManagerView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    // After the user modifies the dyski information, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        int kod = Integer.valueOf(request.getParameter("idUzytkownika"));
        String imie = (String) request.getParameter("imie");
        String nazwisko = (String) request.getParameter("nazwisko");
        Uzytkownik manager = new Uzytkownik(kod,"","",imie,nazwisko,"manager");
 
        String errorString = null;
        try {
            DBUtils.aktualizujUzytkownika(conn, manager);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("manager", manager);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editManagerView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the Dyski listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/managers");
        }
    }
 
}
