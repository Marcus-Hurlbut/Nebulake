package profiles;
 
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;

@MultipartConfig(maxFileSize = 1177215)
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProfilesDAO profileDAO;
 
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        profileDAO = new ProfilesDAO(jdbcURL, jdbcUsername, jdbcPassword);
 
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
            case "/user/MyAccount":
                showProfilePage(request, response);
                break;
            case "/user/EditProfileBio":
                editProfileBio(request, response);
                break;
            case "/user/EditProfilePicture":
                editProfilePicture(request, response);
                break;
            case "/user/EditBio":
                showEditBioPage(request, response);
                break;
            case "/user/EditPicture":
                showEditProfilePicturePage(request, response);
                break;             
 
            }
        } 
        catch (SQLException ex) {
            throw new ServletException(ex);
        }
        catch (IOException ex2) {
            throw new ServletException(ex2);
        }
    }
    
    private void showProfilePage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession(false);
    	String username = (String) session.getAttribute("AUTHOR"); 
    	int userid = profileDAO.getUserId(username);
    	
    	List<Profiles> listProfile = profileDAO.listProfilePage(username);
        request.setAttribute("listProfile", listProfile);
        
        List<Profiles> listRecentPosts = profileDAO.listProfileRecentPosts(username);
        request.setAttribute("listRecentPosts", listRecentPosts);

        List<Profiles> listCommNames = profileDAO.listProfileCommNames(username);
        request.setAttribute("listCommNames", listCommNames);
        
        List<Profiles> listPopularSubNebulas = profileDAO.listProfilePopularSubNebulas(username);
        request.setAttribute("listPopularSubNebulas", listPopularSubNebulas);
        
        List <Profiles> picture = profileDAO.listProfilePicture(userid);
        request.setAttribute("listProfilePicture", picture);

    	RequestDispatcher dispatcher = request.getRequestDispatcher("/ProfilePage.jsp");
        dispatcher.forward(request, response);
    }
    
    public void showEditBioPage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession(false);
    	String username = (String) session.getAttribute("AUTHOR"); 
    	int userid = profileDAO.getUserId(username);
    	String userbio = profileDAO.getUserBio(username);
    	
    	Profiles bio = new Profiles(userid, userbio);

    	request.setAttribute("bio", bio);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/EditBio.jsp");
        dispatcher.forward(request, response);
    }
    
    public void showEditProfilePicturePage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/EditPicture.jsp");
        dispatcher.forward(request, response);
    }
    
    public void editProfileBio(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession(false);
    	
    	String username = (String) session.getAttribute("AUTHOR");
    	int userid = profileDAO.getUserId(username);
    	String userbio = request.getParameter("bio");    	
    	
    	Profiles profile = new Profiles(userid, userbio);
    	
    	profileDAO.editProfileBio(profile);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("./MyAccount");
        dispatcher.forward(request, response);  	
    
    }
    
    public void editProfilePicture(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession(false);
    	
    	String username = (String) session.getAttribute("AUTHOR");
    	int userid = profileDAO.getUserId(username);
    	
    	InputStream inputStream = null;
    	Part picturePart = request.getPart("picture");     	
    	
    	if (picturePart != null) {
    		inputStream = picturePart.getInputStream();
    		System.out.println("not null");
    	}
    	else {
    		System.out.println("null");
    	}
    	
    	profileDAO.editProfilePicture(userid, inputStream);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("./MyAccount");
        dispatcher.forward(request, response); 
    	
    }
    	
}
