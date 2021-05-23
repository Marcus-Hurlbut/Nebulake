package users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 

public class UsersDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public UsersDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
     
    public boolean createUsers(Users user) throws SQLException {
        String sql = "INSERT INTO users (username, useremail, userpassword, usersalt, userverified) VALUES (?, ?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getSalt());
        statement.setInt(5, user.getVerified());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        
        String sql2 = "SELECT user_id FROM users WHERE username = ?";
        PreparedStatement statement2 = jdbcConnection.prepareStatement(sql2);
        statement2.setString(1, user.getUsername());
        
        ResultSet resultSet2 = statement2.executeQuery();
        int userid = -1;
        
        if (resultSet2.next()) {
            userid = resultSet2.getInt("user_id");
        }
        
        String sql3 = "INSERT INTO images (image_id, profilepicture, users_user_id) VALUES (?, 1, ?)";
        PreparedStatement statement3 = jdbcConnection.prepareStatement(sql3);
        statement3.setInt(1, userid);
        statement3.setInt(2, userid);
        
        statement3.executeUpdate();
                
        statement.close();
        disconnect();
        return rowInserted;
    }
          
    public String loginUsers(Users user) throws SQLException {
    	Users login = null;
        String sql = "SELECT * FROM users WHERE username = ? AND userpassword = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            String username = resultSet.getString("username");
            String password = resultSet.getString("userpassword");            
             
            login = new Users(username, password);
            statement.close();
            disconnect();
            return username;
            
        } else {
        	String name = null;
        	String passwd = null;
        	
        	login = new Users(name, passwd);
            statement.close();
            disconnect();
            return null;
        }
    }
     
    public String getSalt(String username) throws SQLException {
    	String salt = null;
        String sql = "SELECT usersalt FROM users WHERE username = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, username);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            salt = resultSet.getString("usersalt");            
        }
         
        resultSet.close();
        statement.close();
         
        return salt;
    } 
    
    public boolean verifyUser(Users isVerified) throws SQLException {
    	String sql = "UPDATE users SET userverified = 1 WHERE useremail = (?)";
    	connect();
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, isVerified.getEmail());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    	
    }
    
    public boolean editPassword(Users editUser) throws SQLException {
    	String sql = "UPDATE users SET userpassword = ?, usersalt = ? WHERE useremail = (?)";
    	connect();
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
    	statement.setString(1, editUser.getPassword());
    	statement.setString(2, editUser.getSalt());
        statement.setString(3, editUser.getEmail());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    	
    }
    
    public boolean getLoginVerification(String username) throws SQLException {
    	String userIsVerified = null;
        String sql = "SELECT * FROM users WHERE username = ? AND userverified = 1";   
        connect();
        
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
        	return true;
        } else {
        	return false;
        }
    }
    
}