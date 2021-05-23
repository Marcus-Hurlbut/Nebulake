package subnebulas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nebulas.Permissions;
import posts.Posts;
 

public class SubnebulasDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public SubnebulasDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
    // Create Subnebula into database
    public boolean createSubnebulas(Subnebulas topic) throws SQLException {
        
        String sql = "INSERT INTO topics (categories_category_id, users_user_id, topicname, topicauthor, topicdescription) VALUES (?, ?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, topic.getCatId());
        statement.setInt(2, topic.getUserId());
        statement.setString(3, topic.getName());
        statement.setString(4, topic.getAuthor());
        statement.setString(5, topic.getDescription());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    // List Subnebulas from database to user 
    public List<Subnebulas> listAllSubnebulas(int topicCategoryId, String username) throws SQLException {
        List<Subnebulas> listTopics = new ArrayList<>();
        int protection = 0;
        boolean isVerified = false;
        
        // Get Nebula permission status
        String presql = "SELECT categoryprotection FROM categories WHERE category_id = ?";
        connect();
        
        PreparedStatement prestatement = jdbcConnection.prepareStatement(presql);
        prestatement.setInt(1, topicCategoryId);
        ResultSet result = prestatement.executeQuery();
        
        if (result.next()) {
        	protection = result.getInt("categoryprotection");
        	
        	// Get userID from database
        	if(protection == 1) { 
        		String sqlUserId = "SELECT user_id FROM users WHERE username = ?";
        		PreparedStatement prestatement2 = jdbcConnection.prepareStatement(sqlUserId);
        		prestatement2.setString(1, username);
        		
        		ResultSet result2 = prestatement2.executeQuery();
        		// Check user ID against permissions
        		if(result2.next()) {
        			int userid = result2.getInt("user_id");        	
        			String sqlPermission = "SELECT * FROM permissions WHERE categories_category_id = ? AND users_user_id = ?";
        			
        			PreparedStatement sqlstatement = jdbcConnection.prepareStatement(sqlPermission);
        	        sqlstatement.setInt(1, topicCategoryId);
        	        sqlstatement.setInt(2, userid);
        	        ResultSet result3 = sqlstatement.executeQuery();
        	        
        	        if(result3.next()) {
        	        	isVerified = true;
        	        }
        	        else {
        	        	isVerified = false;
        	        }
        		}
        	} else {
        		isVerified = true;
        	}
        }
        // List Subnebulas content to user
        String sql = "SELECT * FROM topics WHERE categories_category_id = ?";        
        
        if(isVerified != false) {
        
	        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
	        statement.setInt(1, topicCategoryId);
	        ResultSet resultSet = statement.executeQuery();
	         
	        while (resultSet.next()) {
	        	int id = resultSet.getInt("topic_id");
	            String name = resultSet.getString("topicname");
	            String author = resultSet.getString("topicauthor");
	            String description = resultSet.getString("topicdescription");            
	             
	            Subnebulas topic = new Subnebulas(id, name, author, description);
	            listTopics.add(topic);
	        }         
	        resultSet.close();
	        statement.close();
        } 
        else {
        	listTopics = null;
        }
        
	    disconnect();
         
        return listTopics;
    }
    
    // Get verification from database if user is the commander of that nebula
    public boolean isCommander(int categoryid, String username) throws SQLException {
    	boolean isCommander	= false;
    	
    	String sql = "SELECT * FROM categories WHERE category_id = ? AND categoryauthor = ?";
    	connect();
    	
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
    	statement.setInt(1, categoryid);
    	statement.setString(2, username);
    	
    	ResultSet result = statement.executeQuery();
		
		if(result.next()) {
			isCommander = true;
		}
    	
    	return isCommander;
    }
    
    // Create permission into database for allowing user nebula access 
    public boolean createPermission(Permissions permission) throws SQLException {
    	String sql = "INSERT INTO permissions (categories_category_id, users_user_id) VALUES (?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, permission.getCategoryId());
        statement.setInt(2, permission.getUserId());        
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    
    // Get Nebula name from database to display to user section
    public String getNebulaName(int topicCategoryId) throws SQLException {
    	 String categoryname ="";
    	 Subnebulas name = new Subnebulas();
    	 String sql = "SELECT categoryname FROM categories WHERE category_id = ?";
    	 connect();
    	 
         PreparedStatement statement = jdbcConnection.prepareStatement(sql);
         statement.setInt(1, topicCategoryId);
         
         ResultSet resultSet = statement.executeQuery();
         if (resultSet.next()) {
         	categoryname = resultSet.getString("categoryname");
         	System.out.println(categoryname);
         	
         } 
         name = new Subnebulas(categoryname);
         resultSet.close();
         statement.close();
         disconnect();
         
         return categoryname;
    }
    
    // Get userID from database for creating subnebula / permissions
    public int getUserId (String user) throws SQLException {
    	int userid = 0;

    	String sqlprep = "SELECT user_id AS userid FROM users WHERE username = ?" ;
    	connect();
    	PreparedStatement statement = jdbcConnection.prepareStatement(sqlprep);
    	statement.setString(1, user);
    	ResultSet resultSet = statement.executeQuery();
    	
    	if (resultSet.next()) {
    		userid = resultSet.getInt("userid");
    	}
    	
    	resultSet.close();
    	statement.close();
        disconnect();
        return userid;
    }
    
    public boolean editSubnebula(Subnebulas subnebula) throws SQLException {
    	String sql = "UPDATE topics SET topicname = ?, topicdescription = ? WHERE topic_id = ?";
    	connect();
    	
    	PreparedStatement query = jdbcConnection.prepareStatement(sql);
    	query.setString(1, subnebula.getName());
    	query.setString(2, subnebula.getDescription());
    	query.setInt(3, subnebula.getId());
    	
    	boolean rowEdited = query.executeUpdate() > 0;
    	query.close();
    	disconnect();
    	
    	return rowEdited;
    }
    
    public boolean deleteSubnebula(Subnebulas subnebula) throws SQLException {
    	String sql = "DELETE FROM topics WHERE topic_id = ?";
    	connect();
    	
    	PreparedStatement query = jdbcConnection.prepareStatement(sql);
    	query.setInt(1,  subnebula.getId());
    	
    	boolean rowDeleted = query.executeUpdate() > 0;
    	query.close();
    	disconnect();
    	
    	return rowDeleted;
    }
    
}