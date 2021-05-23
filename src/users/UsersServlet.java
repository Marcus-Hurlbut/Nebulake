package users;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.PasswordHash;
import main.SendRecoveryEmail;
import main.SendVerificationEmail;

import javax.servlet.annotation.WebServlet;
import java.security.*;
import java.util.Base64;


public class UsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsersDAO userDAO;
 
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        userDAO = new UsersDAO(jdbcURL, jdbcUsername, jdbcPassword);
 
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
            case "/UserForm":
                showUsersForm(request, response);
                break;
            case "/create":
                createUsers(request, response);
                break;
            case "/login":
                loginUsers(request, response);
                break;
            case "/logout":
                logoutUsers(request, response);
                break;
            case "/VerifyCode":
                verifyCode(request, response);
                break;
            case "/RecoverAccount":
                showPasswordRecovery(request, response);
                break;
            case "/SendRecoverEmail":
            	sendRecoverEmail(request, response);
            	break;
            case "/SetNewPassword":
            	setNewPassword(request, response);
            	break;
            case "/user/SupportPage":
                showHelpPage(request, response);
                break;
            case "/user/AboutPage":
                showAboutPage(request, response);
                break;           	
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
 
    private void showUsersForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("UsersForm.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showHelpPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/HelpPageUser.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showAboutPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/AboutPageUser.jsp");
        dispatcher.forward(request, response);
    }
    
    private void createUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String username = request.getParameter("username");
        username = username.trim();
        String useremail = request.getParameter("useremail");
        String password = request.getParameter("userpassword");
        int verified = 0;
        boolean validPassword = false;
        
        if (password.length() > 8 && !password.contains("abc") && !password.contains("123") && !password.contains("password")
        		&& username.length() > 1 && !username.contains("!") && !username.contains("@") && !username.contains("#") && !username.contains("$") && !username.contains("%") && !username.contains("^") && !username.contains("&") && !username.contains("*") && !username.contains(">") && !username.contains("<") && !username.contains(":")
        		&& !username.contains("(") && !username.contains(")") && !username.contains("-") && !username.contains("+") && !username.contains("=") && !username.contains("`") && !username.contains("~") && !username.contains(".") && !username.contains(",") && !username.contains(":") && !username.contains(";")
        	) {        	       	
        	validPassword = true;
        }
        
        if (validPassword != false) {
        	 try {
		        // Salt in byte form
				SecureRandom random = new SecureRandom();
				byte[] salt = new byte[64];
				random.nextBytes(salt);
				String saltString = salt.toString();
				
		        String hashPasswd = PasswordHash.setHash(password, saltString);
		        
		        Users newUsers = new Users(username, useremail, hashPasswd, saltString, verified);
		        userDAO.createUsers(newUsers);
		        
		        Users input = new Users(username, hashPasswd);
		        HttpSession oldSession = request.getSession(false);
		        if (oldSession != null) {
		            oldSession.invalidate();
		        }
		    	// create new session
		        HttpSession session = request.getSession(true);
		        session.setAttribute("USER", username);     
		        session.setAttribute("AUTHOR", username);
		 
				response.setContentType("text/html;charset=UTF-8");
        	 }  
        	 catch (SQLException ex) {
             	HttpSession session = request.getSession(false); 
            	String error = "There is an Account already associated with this username or email";
            	session.setAttribute("ERROR", error);
            	
            	RequestDispatcher dispatcher = request.getRequestDispatcher("/Error.jsp");
                dispatcher.forward(request, response); 
            }
        	 
			try (PrintWriter out = response.getWriter()) {
	
				SendVerificationEmail message = new SendVerificationEmail();
				String code = message.getRandom();		
				
				Users user = new Users(username, useremail, code);			
				boolean test = message.sendEmail(user);
				
				if(test) {
					HttpSession session = request.getSession(false);
					request.getSession();
					session.setAttribute("authcode", user);
					session.setAttribute("AUTHOR", username);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Verify.jsp");
		            dispatcher.forward(request, response); 
					
				}
				else {
					out.println("Failed to send Verification Email.");
				}
			}
        } else {
        	HttpSession session = request.getSession(false); 
        	String errorA = "Password Must contain 8 characters, a special character, and a number. ";
        	String errorB = "Username Must connot be empty or contain special characters";
        	String error = errorA + errorB;
        	session.setAttribute("ERROR", error);        	
        	
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/Error.jsp");
            dispatcher.forward(request, response);
        }
    }
 
    private void loginUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("userpassword");
        
        String usersalt = userDAO.getSalt(username);       
        
        String hashPasswd = PasswordHash.setHash(password, usersalt);
        Users input = new Users(username, hashPasswd);
                
        String validatedUser = userDAO.loginUsers(input);
        boolean verifiedEmail = userDAO.getLoginVerification(username);
        
	        if(validatedUser != null && verifiedEmail != false) {
	        	
	        	// invalidate previous session
	            HttpSession oldSession = request.getSession(false);
	            if (oldSession != null) {
	                oldSession.invalidate();
	            }
	        	// create new session
	            HttpSession session = request.getSession(true);
	            session.setAttribute("USER", validatedUser);     
	            session.setAttribute("AUTHOR", validatedUser);
	        	response.sendRedirect("/user/ViewNebulas");
	        }
	        if(validatedUser == null) {
	        	HttpSession session = request.getSession(false); 
	        	String error = "Invalid Username or Password entered";
	        	session.setAttribute("ERROR", error);
	        	
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("/Error.jsp");
	            dispatcher.forward(request, response);       	
	        }	             	
    }
    
    private void logoutUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("USER");
            session.removeAttribute("AUTHOR");
            session.invalidate();             
            response.sendRedirect("LoginPage.jsp");
            
        }   	
    }    
    
    private void verifyCode(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		
		try (PrintWriter out = response.getWriter()) {
			
			HttpSession session = request.getSession();
			Users user = (Users) session.getAttribute("authcode");
			String code = request.getParameter("authcode");
			
			Users users = (Users) session.getAttribute("useremail");
			String email = request.getParameter("useremail");
			
			if(code.equals(user.getVerification())) {
				int verified = 1;
		        Users isVerified = new Users(verified, email);
		        userDAO.verifyUser(isVerified);
	        	response.sendRedirect("/user/ViewNebulas");	        

			} else {
				response.sendRedirect("Error.jsp");
			}
		}		
    }
    
    private void showPasswordRecovery(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	
        RequestDispatcher dispatcher = request.getRequestDispatcher("PasswordRecovery.jsp");
        dispatcher.forward(request, response);
    }
    
    private void sendRecoverEmail(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession(false);
    	
    	String useremail = request.getParameter("useremail");
    	session.setAttribute("EMAIL", useremail);
    	
    	request.setAttribute("listEmail", useremail);
    	
		try (PrintWriter out = response.getWriter()) {

			SendRecoveryEmail message = new SendRecoveryEmail();
			String code = message.getRandom();		
			int verified = 1;
			
			Users user = new Users(useremail, code, verified);			
			boolean test = message.sendEmail(user);
			
			if(test) {
				request.getSession();
				session.setAttribute("authcode", user);
				response.sendRedirect("NewPassword.jsp");
			}
			else {
				out.println("Failed to send Recovery Email.");
			}
		}   	
    }
    private void setNewPassword(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession(false);
    	request.getSession();

    	Users user = (Users) session.getAttribute("authcode");
    	session.setAttribute("authcode", user);
 
    	String useremail = request.getParameter("useremail");
    	String userpassword = request.getParameter("userpassword");
    	String code = request.getParameter("authcode");
    	int verified = 0;
    	
    	// Salt in byte form
    			SecureRandom random = new SecureRandom();
    			byte[] salt = new byte[64];
    			random.nextBytes(salt);
    			
    			String saltString = salt.toString();   			
    	        String hashPasswd = PasswordHash.setHash(userpassword, saltString);   	         
    	
    	if(code.equals(user.getVerification())) {	
    		verified = 1;
    		Users editUser = new Users(useremail, hashPasswd, saltString, verified);
	        userDAO.editPassword(editUser);
        	response.sendRedirect("/LoginPage.jsp");
	        
		} else {
			response.sendRedirect("Error.jsp");
		}
    }
}
