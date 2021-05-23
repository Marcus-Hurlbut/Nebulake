package posts;

public class Posts{
	   protected int id;
	   protected int catid;
	   protected int topid;
	   protected int userid;
	   protected String author;
	   protected String post;
	   protected String reply; 
	   protected String time;
	   protected String user;

	   public Posts() {
	   }

	   
	   public Posts(int topid) {
	       this.topid = topid;
	   }
	   // Creates Object for retreiving Topic & Reply ID for editing
	   public Posts(int topid, int id) {
		   this.topid = topid;
		   this.id = id;
	   }
	   
	   // Creates object that lists post content on edit form and deletes post
	   public Posts(int id, String reply) {
		   this.id = id;
		   this.reply = reply;
	   }

	   public Posts(int id, int topid, int userid, String author, String reply) {
	       this(topid, userid, author, reply);
	       this.id = id;
	   }
	   
	   public Posts(int id, String author, String reply) { 
		   this.id = id;
	       this.author = author;
	       this.reply = reply;	       	       	       
	   } 
	   // Creates object for Listing Replies
	   public Posts(int id, String author, String reply, String time, String user) { 
		   this.id = id;
	       this.author = author;
	       this.reply = reply;	
	       this.time = time;
	       this.user = user;
	   } 
	   // Creates object for getting post to edit
	   public Posts(int id, int topid, String author, String reply, String time) {
		   this.id = id;
		   this.topid = topid;
		   this.author = author;
		   this.reply = reply;
		   this.time = time;
	   }
	   
	   public Posts(int topid, int userid, String author, String reply) {
		   this.topid = topid; 
		   this.userid = userid;
	       this.author = author;
	       this.reply = reply;	       	       	       
	   }
	   // Creates object for creating a post
	   public Posts(int catid, int topid, int userid, String author, String reply, String time) {
		   this.catid = catid;
		   this.topid = topid; 
		   this.userid = userid;
	       this.author = author;
	       this.reply = reply;	    
	       this.time = time;
	   }
	   
	   public int getId() {
	       return id;
	   }

	   public void setId(int id) {
	       this.id = id;
	   }
	   
	   public int getCatId() {
	       return catid;
	   }

	   public void setCatId(int catid) {
	       this.catid = catid;
	   }
	   
	   public int getTopId() {
	       return topid;
	   }

	   public void setTopId(int topid) {
	       this.topid = topid;
	   }
	   
	   public int getUserId() {
	       return userid;
	   }

	   public void setUserId(int userid) {
	       this.userid = userid;
	   }

	   public String getAuthor() {
	       return author;
	   }

	   public void setAuthor(String author) {
	       this.author = author;
	   }

	   public String getPost() {
	       return post;
	   }

	   public void setPost(String post) {
	       this.post = post;
	   }
	   
	   public String getReply() {
	       return reply;
	   }

	   public void setReply(String reply) {
	       this.reply = reply;
	   }
	   
	   public String getTime() {
	       return time;
	   }

	   public void setTime(String time) {
	       this.time = time;
	   }
	   
	   public String getUser() {
	       return user;
	   }

	   public void setUser(String user) {
	       this.user = user;
	   }

}