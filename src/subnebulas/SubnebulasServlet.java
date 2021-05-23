package subnebulas;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nebulas.Permissions;
 

public class SubnebulasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SubnebulasDAO subnebulaDAO;
 
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        subnebulaDAO = new SubnebulasDAO(jdbcURL, jdbcUsername, jdbcPassword);
 
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
            case "/user/ViewSubnebulas":
                listSubnebulas(request, response);
                break;
            case "/user/RedirectSubnebulas":
                redirectSubnebulas(request, response);
                break;
            case "/user/createSubnebula":
                createSubnebula(request, response);
                break;
            case "/user/CreateSubnebulaForm":
                createSubnebulaForm(request, response);
                break;
            case "/user/CreateUserPermission":
             	createPermission(request, response);
            	break;
            case "/user/AddPermission":
            	showPermissionsForm(request, response);
            	break;
            case "/user/EditSubnebula":
            	editSubnebula(request, response);
            	break;
            case "/user/EditSubnebulaForm":
            	editSubnebulaForm(request, response);
            	break;
            }
            
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    // Make objects to List Subnebulas & Nebula panel to user
    private void listSubnebulas(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {    
    	
    	HttpSession session = request.getSession(false);     	
    	int nebulaId = Integer.parseInt(request.getParameter("topiccategory"));  	
    	session.setAttribute("CATEGORYID", nebulaId);
    	String username = (String) session.getAttribute("AUTHOR");
    	
    	try {
	    	List<Subnebulas> listSubnebulas = subnebulaDAO.listAllSubnebulas(nebulaId, username);
	    	request.setAttribute("listTopics", listSubnebulas);
	    	
	    	String name = subnebulaDAO.getNebulaName(nebulaId);	        
	        session.setAttribute("categoryName", name);  
    	
	    	if (listSubnebulas != null) {    		
		                
		        boolean commanderPanel = subnebulaDAO.isCommander(nebulaId, username);
		        session.setAttribute("commanderPanel", commanderPanel);
		        
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewTopics.jsp");
		        dispatcher.forward(request, response);
	    	}   	
	    	if (listSubnebulas == null){
	    		String error = "You do NOT have access to this Nebula";
	        	session.setAttribute("ERROR", error);
	        	
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("/Error.jsp");
	            dispatcher.forward(request, response);
	    	}
    	}
    	catch (Exception e) {
    		String error = "Server Error: Contact the administrator";
        	session.setAttribute("ERROR", error);
        	
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/Error.jsp");
            dispatcher.forward(request, response);
    	}
    }
    
    // Redirect users to specific subnebula list / nebula com panel page
    private void redirectSubnebulas(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {    
    	HttpSession session = request.getSession(false); 
    	String username = (String) session.getAttribute("AUTHOR");
    	int topicCategoryId = (int) session.getAttribute("CATEGORYID");
    	
    	List<Subnebulas> listTopics = subnebulaDAO.listAllSubnebulas(topicCategoryId, username);
        request.setAttribute("listTopics", listTopics);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewTopics.jsp");
        dispatcher.forward(request, response);
    }
    
    // Create subnebula from user input / JSP page
    private void createSubnebula(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {  
    	 HttpSession session = request.getSession(false); 
    	 int userid = 0;
    	  
         String name = request.getParameter("topicname");
         String author = (String) session.getAttribute("AUTHOR");         
         int catid = (int) session.getAttribute("CATEGORYID");
         String description = request.getParameter("topicdescription");
         
         String user = author;
         userid = subnebulaDAO.getUserId(user);
  
         Subnebulas newSubnebula = new Subnebulas(catid, userid, name, author, description);
         subnebulaDAO.createSubnebulas(newSubnebula);
         response.sendRedirect("./RedirectSubnebulas");
    }
    
    // Redirect user to create subnebula JSP page
    private void createSubnebulaForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CreateTopic.jsp");
        dispatcher.forward(request, response);
    }
    
    // Create's users permission to access nebula
    private void createPermission (HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, ServletException,  IOException {
    	
    	HttpSession session = request.getSession(false); 
    	String author = (String) session.getAttribute("AUTHOR");         
        int catid = (int) session.getAttribute("CATEGORYID");
          
        String username = request.getParameter("permissionname");
        int userForPermission = subnebulaDAO.getUserId(username);               
        Permissions permission = new Permissions(userForPermission, catid);
       
        boolean isCommander = subnebulaDAO.isCommander(catid, author);

        if(isCommander != false) {
        	subnebulaDAO.createPermission(permission);
        }
        response.sendRedirect("./RedirectSubnebulas");  	
    }
    
    // Send user to permissions JSP page
    private void showPermissionsForm(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, ServletException, IOException {
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AddPermission.jsp");
        dispatcher.forward(request, response); 	
    }   
    
    // Create object to Edit Subnebula 
    private void editSubnebula(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	
    	int id = Integer.parseInt(request.getParameter("id"));
    	String name = request.getParameter("name");
    	String description = request.getParameter("description");
    	
    	Subnebulas subnebula = new Subnebulas(id, name, description);
    	subnebulaDAO.editSubnebula(subnebula);
    	response.sendRedirect("/user/RedirectSubnebulas");   	
    }
    
    // Redirect user to edit subnebula page
    private void editSubnebulaForm(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, ServletException, IOException {
    	
    	int subnebulaId = Integer.parseInt(request.getParameter("id"));
    	HttpSession session = request.getSession(false);    	
    	session.setAttribute("SUBNEBID", subnebulaId);
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/EditSubnebula.jsp");
        dispatcher.forward(request, response); 	
    } 
}
