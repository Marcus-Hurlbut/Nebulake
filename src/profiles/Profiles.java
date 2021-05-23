package profiles;

public class Profiles {
	protected int id;
	protected int userid;
	protected String username;
	protected String email;
	protected String bio;
	protected int numOfPosts;
	protected int numOfCommanders;
	protected String image;
	protected String recentPosts;
	protected String commNames;
	protected String popularSubNebulas;
	
	
	public Profiles() {
	}

	public Profiles(int id, String username, String email, String bio, int numOfPosts, int numOfCommanders) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.bio = bio;
		this.numOfPosts = numOfPosts;
		this.numOfCommanders = numOfCommanders;
	}
	// Object for displaying user details to profile page
	public Profiles(String username, String email, String bio, int numOfPosts, int numOfCommanders) {
		this.username = username;
		this.email = email;
		this.bio = bio;
		this.numOfPosts = numOfPosts;
		this.numOfCommanders = numOfCommanders;
	}
	// Object for displaying forum details to profile page
	public Profiles(String recentPosts, String commNames, String popularSubNebulas) {
		this.recentPosts = recentPosts;
		this.commNames = commNames;
		this.popularSubNebulas = popularSubNebulas;
	}
	
	public Profiles(String commNames, String popularSubNebulas) {
		this.commNames = commNames;
		this.popularSubNebulas = popularSubNebulas;
	}
	
	public Profiles(int userid, String bio) {
		this.userid = userid;
		this.bio = bio;
	}
	
	public Profiles(String popularSubNebulas) {
		this.popularSubNebulas = popularSubNebulas;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userid;
	}
	
	public void setUserId(int userid) {
		this.userid = userid;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getBio() {
		return bio;
	}
	
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public int getNumOfPosts() {
		return numOfPosts;
	}
	
	public void setNumOfPosts(int numOfPosts) {
		this.numOfPosts = numOfPosts;
	}
	
	public int getNumOfCommanders() {
		return numOfCommanders;
	}
	
	public void setNumOfCommanders(int numOfCommanders) {
		this.numOfCommanders = numOfCommanders;
	}
	
	public String getRecentPosts() {
		return recentPosts;
	}
	
	public void setRecentPosts(String recentPosts) {
		this.recentPosts = recentPosts;
	}
	
	public String getCommNames() {
		return commNames;
	}
	
	public void setCommNames(String commNames) {
		this.commNames = commNames;
	}
	
	public String getPopularSubNebulas() {
		return popularSubNebulas;
	}
	
	public void setPopularSubNebulas(String popularSubNebulas) {
		this.popularSubNebulas = popularSubNebulas;
	}
	
	public String getImage() {
		return popularSubNebulas;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
}
