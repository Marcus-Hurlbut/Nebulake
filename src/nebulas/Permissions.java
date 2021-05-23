package nebulas;

public class Permissions {
	protected int id;
	protected int userid;
	protected int categoryid;
	protected String username;

	public Permissions() {	
	}
	// Creates Object for getting permissions for specific user and getting username
	public Permissions(int userid) {
		this.userid = userid;
	}
	// Creates Object for inserting permission
	public Permissions(int userid, int categoryid) {
		this.userid = userid;
		this.categoryid = categoryid;
	}
	// Creates Object for holding username content
	public Permissions(String username) {
		this.username = username;
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
	
	public int getCategoryId() {
		return categoryid;
	}
	
	public void setCategoryId(int categoryid) {
		this.categoryid = categoryid;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
}
