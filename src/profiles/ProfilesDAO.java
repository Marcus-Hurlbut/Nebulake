package profiles;
 
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

public class ProfilesDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public ProfilesDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
    
    public List<Profiles> listProfilePage(String username) throws SQLException {
    	List<Profiles> listProfile = new ArrayList<>();
    	String bio = "none";
    	String email = "";
    	int numOfPosts = 0;
    	int numOfCommanders = 0;
    	
    	// get users email
    	String sql = "SELECT useremail FROM users WHERE username = ?";
    	connect();
    	
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, username);
    	
        ResultSet resultSet = statement.executeQuery();
         	
        if (resultSet.next()) {
        	email = resultSet.getString("useremail");
        }
        
        // get users bio
    	String sql1 = "SELECT bio FROM users WHERE username = ?";
    	connect();
    	
    	PreparedStatement statement1 = jdbcConnection.prepareStatement(sql1);
        statement1.setString(1, username);
    	
        ResultSet resultSet1 = statement1.executeQuery();
         	
        if (resultSet1.next()) {
        	bio = resultSet1.getString("bio");
        }
        
        // get users number of posts
        String sql2 = "SELECT COUNT(replyauthor) AS numofposts FROM replies WHERE replyauthor = ?";
        
        PreparedStatement statement2 = jdbcConnection.prepareStatement(sql2);
        statement2.setString(1, username);
        
        ResultSet resultSet2 = statement2.executeQuery();
        
        if (resultSet2.next()) {
        	numOfPosts = resultSet2.getInt("numofposts");
        }
        
        // get users number of nebulas the user is commander of
        String sql3 = "SELECT COUNT(categoryauthor) AS numofcommanders FROM categories WHERE categoryauthor = ?";
        
        PreparedStatement statement3 = jdbcConnection.prepareStatement(sql3);
        statement3.setString(1, username);
        
        ResultSet resultSet3 = statement3.executeQuery();
        
        if (resultSet3.next()) {
        	numOfCommanders = resultSet3.getInt("numofcommanders");
        }
        Profiles profile = new Profiles(username, email, bio, numOfPosts, numOfCommanders);
        listProfile.add(profile);
        
    	return listProfile;
    }
    
    public List<Profiles> listProfileRecentPosts(String username) throws SQLException {
    	List<Profiles> listDetail = new ArrayList<>();
    	String recentPosts = "No Recent Posts";
    	String commNames = "You are not a Commander of any Nebulas";
    	String popularSubNebulas = "You are not active in any Nebulas";
    	
    	// get users 10 most recent posts
    	String sql = "SELECT replycontent AS recentposts FROM replies WHERE replyauthor = ? ORDER BY reply_id DESC LIMIT 10";
    	connect();
    	
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, username);
    	
        ResultSet resultSet = statement.executeQuery();
         	
        while (resultSet.next()) {
        	recentPosts = resultSet.getString("recentposts");
        	Profiles details = new Profiles(recentPosts, commNames, popularSubNebulas);
            listDetail.add(details);
        }
        disconnect();
        
        return listDetail;
    }
        
    public List<Profiles> listProfileCommNames(String username) throws SQLException {
    	List<Profiles> listDetail = new ArrayList<>();
    	String commNames = "You are not a Commander of any Nebulas";
    	String popularSubNebulas = "You are not active in any Nebulas";
    	
    	// get users nebulas where they are commander
        String sql2 = "SELECT categoryname AS commnames FROM categories WHERE categoryauthor = ? ORDER BY categoryname DESC";
        connect();
        
        PreparedStatement statement2 = jdbcConnection.prepareStatement(sql2);
        statement2.setString(1,  username);
        
        ResultSet resultSet2 = statement2.executeQuery();
        
        while(resultSet2.next()) {
        	commNames = resultSet2.getString("commnames");
        	Profiles details = new Profiles(commNames, popularSubNebulas);
            listDetail.add(details);
        }
        disconnect();
        
        return listDetail;
    }
    
    public List<Profiles> listProfilePopularSubNebulas(String username) throws SQLException {
    	List<Profiles> listDetail = new ArrayList<>();
    	String popularSubNebulas = "You are not active in any Nebulas";
        //get users 5 most popular nebulas where they are active
        String sql3 = "SELECT topicname AS popularsubnebulas FROM replies INNER JOIN topics ON topics.topic_id = replies.topics_topic_id WHERE replyauthor = ? GROUP BY topics_topic_id ORDER BY COUNT(topics_topic_id) DESC LIMIT 10";
        connect();
        
        PreparedStatement statement3 = jdbcConnection.prepareStatement(sql3);
        statement3.setString(1, username);
        
        ResultSet resultSet3 = statement3.executeQuery();
        
        while(resultSet3.next()) {
        	popularSubNebulas = resultSet3.getString("popularsubnebulas");
        	Profiles details = new Profiles(popularSubNebulas);
            listDetail.add(details);
        }
        disconnect();
        
    	return listDetail;
    }
    
    public List <Profiles> listProfilePicture(int userId) throws SQLException, IOException {
    	List<Profiles> listDetail = new ArrayList<>();
    	String sql = "SELECT profilepicture FROM images WHERE users_user_id = ?";
    	connect();
    	
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
    	statement.setInt(1, userId);
    	ResultSet result = statement.executeQuery();
    	
    	if (result.next()) {   		
    		Blob blob = result.getBlob("profilepicture");
    		
    		InputStream inputStream = blob.getBinaryStream();
    		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    		
    		byte[] buffer = new byte[1000000];
    		int bytesRead = -1;
    		
    		while ((bytesRead = inputStream.read(buffer)) != -1) {
    			outputStream.write(buffer, 0, bytesRead);
    		}
    		
    		byte[] imageBytes = outputStream.toByteArray();
    		String image = Base64.getEncoder().encodeToString(imageBytes);
    		
    		inputStream.close();
    		outputStream.close();
    		
    		Profiles details = new Profiles(image);
    		listDetail.add(details);
    	}
    	disconnect();
    	
    	return listDetail;
    }
    
    public boolean editProfileBio(Profiles profile) throws SQLException {
    	String sql = "UPDATE users SET bio = ? WHERE user_id = ?";
    	connect();
    	
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, profile.getBio());
        statement.setInt(2, profile.getUserId());
        
        boolean rowEdited = statement.executeUpdate() > 0;
    	statement.close();
    	disconnect();
    	
    	return rowEdited;
    }
    
    public boolean editProfilePicture(int userid, InputStream inputStream) throws SQLException {
    	String sql = "UPDATE images SET profilepicture = ? WHERE users_user_id = ?";
    	connect();
    	
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
    	
    	if (inputStream != null) {
    		statement.setBlob(1, inputStream);
    		statement.setInt(2, userid);
    	}
    	
    	
    	boolean rowEdited = statement.executeUpdate() > 0;
    	statement.close();
    	disconnect();
    	
    	return rowEdited;
    }
    
    public int getUserId(String username) throws SQLException {
    	int userid = 0;
    	String sql = "SELECT user_id FROM users WHERE username = ?";
    	connect();
    	
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
    	statement.setString(1, username);
    	ResultSet resultSet = statement.executeQuery();
    	
    	if (resultSet.next()) {
    		userid = resultSet.getInt("user_id");
    		
    	}
    	disconnect();
    	
    	return userid;
    }
    
    public String getUserBio(String username) throws SQLException {
    	String bio = "none";
    	String sql = "SELECT bio FROM users WHERE username = ?";
    	connect();
    	
    	PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, username);
    	
        ResultSet resultSet = statement.executeQuery();
         	
        if (resultSet.next()) {
        	bio = resultSet.getString("bio");
        }       
        disconnect();
        
        return bio;
    }
    
}