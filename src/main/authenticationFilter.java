package main;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
@WebFilter("/user/*")
public class authenticationFilter implements Filter {
	
	 	private HttpServletRequest httpRequest;
	 	private static final String[] loginRequiredURLs = {
	            "CategoryList.jsp", "/createCategory", "/showCategoryForm", "/ViewTopics", "/RedirectTopics", "/createTopic", "/showTopicForm", 
	            "/enterCategoryId", "/ViewReplies", "/RedirectReplies" , "/createReply", "/showPostForm", "/showReplyForm", "/enterTopicsId", 
	            "/background-container.css", "/MyAccount", "/SupportPage", "/AboutPage", "/AddPermission", "/CreateUserPermission", "/EditPost",
	            "/DeletePost", "/EditProfileBio", "/EditProfilePicture", "/EditPicture", "/EditBio"
	    };


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession authSession = httpRequest.getSession(false);        
        
        boolean loggedIn = (authSession != null && authSession.getAttribute("USER") != null);
        
        boolean loginPage = httpRequest.getRequestURI().endsWith("LoginPage.jsp");     
        String loginURI = httpRequest.getContextPath() + "/login";
        boolean LoginRequest = httpRequest.getRequestURI().equals(loginURI);
        
        if (loggedIn && (LoginRequest || loginPage)) {
            // if user is already logged in & trying to login again ->
            // forwards user to category list
            RequestDispatcher dispatcher = request.getRequestDispatcher("/listCategories");
            dispatcher.forward(request, response);
 
        } else if (loggedIn || loginRequirement()) {
            // continues the filter chain
        	// pass the request along the filter chain
    		chain.doFilter(request, response);
 
        } else {
            // user is not logged in -> forwards to the Login page
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }		
	}
	
	private boolean loginRequirement() {
		String requestURL = httpRequest.getRequestURL().toString();
		
        for (String loginRequiredURL : loginRequiredURLs) {
            if (requestURL.contains(loginRequiredURL)) {
                return true;
            }
        }
        
        return false;
	}

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
}


