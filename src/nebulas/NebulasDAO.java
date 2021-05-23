package nebulas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 

public class NebulasDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public NebulasDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
    // Create nebula in the database with object
    public boolean createNebulas(Nebulas nebula) throws SQLException {
        String sql = "INSERT INTO categories (categoryname, categorydescription, categoryauthor, categoryprotection) VALUES (?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, nebula.getName());
        statement.setString(2, nebula.getDescription());
        statement.setString(3, nebula.getAuthor());
        statement.setInt(4, nebula.getProtection());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        
        // Insert permission into database for commander to view nebula
        if(nebula.getProtection() != 0) {
        	
        	String name = nebula.getAuthor();
        	int userId = getUserId(name);
        		       	    
        	String nebulaName = nebula.getName();
        	int catId = getNebulaId(nebulaName);      	
	        	
	        String sql_commander_permission = "INSERT INTO permissions (users_user_id, categories_category_id) VALUES (?, ?)";
	        	
	        PreparedStatement permission = jdbcConnection.prepareStatement(sql_commander_permission);
	        permission.setInt(1, userId);
	        permission.setInt(2, catId);
	            
	        permission.executeUpdate();
	        permission.close();
	        
        }
        statement.close();
        disconnect();
        return rowInserted;
    }
    // Get nebulas to display to user from database
    public List<Nebulas> listAllNebulas() throws SQLException {
        List<Nebulas> listNebulas = new ArrayList<>();
        String privacy = "";
        String sql = "SELECT * FROM categories";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("category_id");
            String name = resultSet.getString("categoryname");
            String description = resultSet.getString("categorydescription");
            String author = resultSet.getString("categoryauthor");
            int protection = resultSet.getInt("categoryprotection");
 
            if (protection == 1) {
            	privacy = "Private";
            }
            else {
            	privacy = "Public";
            }            
            Nebulas nebula = new Nebulas(id, name, description, author, privacy);
            listNebulas.add(nebula);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listNebulas;
    }
	// Get userID from database
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
	// Get nebulaID from database
    public int getNebulaId (String name) throws SQLException {
    	int nebid = 0;

    	String sql_nebid = "SELECT category_id FROM categories WHERE categoryname = ?";
        PreparedStatement statement = jdbcConnection.prepareStatement(sql_nebid);
        statement.setString(1, name);
        
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {	       	
        	nebid = resultSet.getInt("category_id");
        }
    	
    	resultSet.close();
    	statement.close();
        disconnect();
        return nebid;
    }
     
}