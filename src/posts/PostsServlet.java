package posts;
 
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.RequestDispatcher; 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 

public class PostsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PostsDAO postDAO;
 
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        postDAO = new PostsDAO(jdbcURL, jdbcUsername, jdbcPassword);
 
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
            case "/user/ViewPosts":
                listPosts(request, response);
                break;
            case "/user/RedirectPosts":
                redirectPosts(request, response);
                break;
            case "/user/createReply":
                createPost(request, response);
                break;         
            case "/user/CreatePost":
                postForm(request, response);
                break;
            case "/user/EditPostForm":
                editForm(request, response);
                break;
            case "/user/EditPost":
                editPost(request, response);
                break;
            case "/user/DeletePost":
                deletePost(request, response);
                break;

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    } 
    private void listPosts(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {    
    	int replyTopicId = Integer.parseInt(request.getParameter("replytopic"));
    	HttpSession session = request.getSession(false); 
    	
    	String user = (String) session.getAttribute("AUTHOR"); 
    	session.setAttribute("TOPICID", replyTopicId);

    	String name = postDAO.getSubnebulaName(replyTopicId);	        
        session.setAttribute("topicName", name); 
    	
    	List<Posts> listReplies = postDAO.listAllPosts(replyTopicId, user);
        request.setAttribute("listReplies", listReplies);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewReplies.jsp");
        dispatcher.forward(request, response);
    }
    
    private void redirectPosts(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {    
    	
    	HttpSession session = request.getSession(false); 
    	String user = (String) session.getAttribute("AUTHOR"); 
    	int replyTopicId = (int) session.getAttribute("TOPICID");    	  	
    	
    	List<Posts> listReplies = postDAO.listAllPosts(replyTopicId, user);
        request.setAttribute("listReplies", listReplies);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewReplies.jsp");
        dispatcher.forward(request, response);
    }
    
    private void createPost(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {  
    	 HttpSession session = request.getSession(false); 
    	 int userid = 0;
    	 
    	 int categoryid = (int) session.getAttribute("CATEGORYID");
    	 int topicid = (int) session.getAttribute("TOPICID");
    	 String author = (String) session.getAttribute("AUTHOR");         
         String reply = request.getParameter("replypost");
         
         Date date = new Date();
         SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy ':' HH:mm z");
         String time = dateFormat.format(date);
                  
         userid = postDAO.getUserId(author);           
  
         Posts newReply = new Posts(categoryid, topicid, userid, author, reply, time);
         postDAO.createPosts(newReply);
         response.sendRedirect("./RedirectPosts");
    }
    
    private void postForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CreateReply.jsp");
        dispatcher.forward(request, response);
    }
    
    private void editForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
    	HttpSession session = request.getSession(false); 
        int replyId = Integer.parseInt(request.getParameter("id"));
        int topicId = (int) session.getAttribute("TOPICID");
            	
        Posts replyInfo = postDAO.getPostContent(replyId, topicId);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/EditReply.jsp");
        request.setAttribute("reply", replyInfo);
        dispatcher.forward(request, response);
    } 
    
    private void editPost(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	String replyContent = request.getParameter("replypost");
    	
    	Posts reply = new Posts(id, replyContent);
    	postDAO.editPost(reply);
    	response.sendRedirect("/user/RedirectPosts");
    }
    
    private void deletePost(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session = request.getSession(false); 
    	int id = Integer.parseInt(request.getParameter("id"));
    	String replyContent = request.getParameter("reply");
    	
    	Posts reply = new Posts(id, replyContent);
    	postDAO.deletePost(reply);
    	response.sendRedirect("/user/RedirectPosts");
    }  
   
    
}