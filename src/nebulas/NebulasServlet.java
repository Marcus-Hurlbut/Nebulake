package nebulas;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 

public class NebulasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NebulasDAO nebulaDAO;
 
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        nebulaDAO = new NebulasDAO(jdbcURL, jdbcUsername, jdbcPassword);
 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
 
        try {
            switch (action) {
            case "/user/ViewNebulas":
                listNebulas(request, response);
                break;
            case "/user/CreateNebula":
                createNebula(request, response);
                break;
            case "/user/CreateNewNebula":
                createNebulaForm(request, response);
                break;
            
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }    
    // Create List of Objects for all nebulas
    private void listNebulas(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	
    	List<Nebulas> listNebulas = nebulaDAO.listAllNebulas();
        request.setAttribute("listNebulas", listNebulas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CategoryList.jsp");
        dispatcher.forward(request, response);
    }
    
    // Create Object to create nebula in database
    private void createNebula(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {    
    	 HttpSession session = request.getSession(false); 
    	 String author = (String) session.getAttribute("AUTHOR"); 
    	 
    	 int protection = 0;
    	 String category = request.getParameter("categoryname");
         String description = request.getParameter("categorydescription");        
         String privacy = request.getParameter("categoryprivacy");
         
         if ("private".equals(privacy)) {
        	 protection = 1;
         }
         else {
        	 protection = 0;
         }
         Nebulas newNebula = new Nebulas(category, description, author, protection);
         
         nebulaDAO.createNebulas(newNebula);
         response.sendRedirect("./ViewNebulas");
    }
    
    // Redirect users to form that creates nebula
    private void createNebulaForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CreateCategory.jsp");
        dispatcher.forward(request, response);
    }
    
}
