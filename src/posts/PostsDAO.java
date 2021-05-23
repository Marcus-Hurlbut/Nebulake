package posts;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
 

public class PostsDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public PostsDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
     
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
    // Create user's post into database
    public boolean createPosts(Posts post) throws SQLException {
        
        String sql = "INSERT INTO replies (topics_topic_id, users_user_id, replyauthor, replycontent, replytime) VALUES (?, ?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        
        statement.setInt(1, post.getTopId());
        statement.setInt(2, post.getUserId());
        statement.setString(3, post.getAuthor());
        statement.setString(4, post.getReply());
        statement.setString(5, post.getTime());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    // Get posts to list / display to user from subnebula
    public List<Posts> listAllPosts(int topicCategoryId, String user) throws SQLException {
        List<Posts> listReplies = new ArrayList<>();
        
        String sql = "SELECT * FROM replies WHERE topics_topic_id = ?";        
        connect();
        
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, topicCategoryId);
        ResultSet resultSet = statement.executeQuery();

         
        while (resultSet.next()) {
        	int id = resultSet.getInt("reply_id");
            String author = resultSet.getString("replyauthor");
            String reply = resultSet.getString("replycontent");
            String time = resultSet.getString("replytime");
            
             
            Posts replies = new Posts(id, author, reply, time, user);
            listReplies.add(replies);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listReplies;
    }
    // Delete user's post from database
    public boolean deletePost(Posts reply) throws SQLException {
    	String sql = "DELETE FROM replies WHERE reply_id = ?";
    	connect();
    	
    	PreparedStatement query = jdbcConnection.prepareStatement(sql);
    	query.setInt(1,  reply.getId());
    	
    	boolean rowDeleted = query.executeUpdate() > 0;
    	query.close();
    	disconnect();
    	
    	return rowDeleted;
    }
    // Edit user's post & update database 
    public boolean editPost(Posts reply) throws SQLException {
    	String sql = "UPDATE replies SET replycontent = ? WHERE reply_id = ?";
    	connect();
    	
    	PreparedStatement query = jdbcConnection.prepareStatement(sql);
    	query.setString(1, reply.getReply());
    	query.setInt(2, reply.getId());
    	
    	boolean rowEdited = query.executeUpdate() > 0;
    	query.close();
    	disconnect();
    	
    	return rowEdited;
    }

    // Get Subnebula details from database
    public String getSubnebulaName(int topicId) throws SQLException {
    	
	   	String topicname ="";
	   	String sql = "SELECT topicname FROM topics WHERE topic_id = ?";
	   	connect();
   	 
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, topicId);       
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
        	topicname = resultSet.getString("topicname");        	
        } 
        
        resultSet.close();
        statement.close();
        disconnect();
        
        return topicname;
   }
    // Get userID for creating posts
    public int getUserId (String user) throws SQLException {
    	int userid = 0;

    	String sqlprep = "SELECT user_id AS userid FROM users WHERE username = ?" ;
    	connect();
    	PreparedStatement statement1 = jdbcConnection.prepareStatement(sqlprep);
    	statement1.setString(1, user);
    	ResultSet resultSet = statement1.executeQuery();
    	
    	if (resultSet.next()) {
    		userid = resultSet.getInt("userid");
    	}
    	
    	resultSet.close();
    	statement1.close();
        disconnect();
        return userid;
    }
    // Get Post content for editing from database
    public Posts getPostContent(int id, int topicid) throws SQLException {
    	Posts reply = null;
    	String sql = "SELECT * FROM replies WHERE reply_id = ? AND topics_topic_id = ?";
    	connect();
    	
    	PreparedStatement query = jdbcConnection.prepareStatement(sql);
    	query.setInt(1, id);
    	query.setInt(2, topicid);
    	
    	ResultSet resultSet = query.executeQuery();
    	
    	if(resultSet.next()) {
    		String replyAuthor = resultSet.getString("replyauthor");
    		String replyContent = resultSet.getString("replycontent");
    		String replyTime = resultSet.getString("replytime");
    		
    		reply = new Posts(id, topicid, replyAuthor, replyContent, replyTime);
    	}
    	resultSet.close();
    	query.close();
    	
    	return reply;
    	
    }
    
}