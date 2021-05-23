package users;

public class Users {
	   protected int id;
	   protected String username;
	   protected String useremail;
	   protected String password;  
	   protected String salt;
	   protected String verification;
	   protected int verified;

	   public Users() {
	   }
	   // Object for getting userID
	   public Users(int id) {
		   this.id = id;
	   }

	   // Object for checking email verification
	   public Users(int verified, String useremail) {
	       this.verified = verified;
	       this.useremail = useremail;
	   }
	   // Object for making password recovery code
	   public Users(String useremail, String verification, int verified) {
		   this.useremail = useremail;
		   this.verification = verification;
		   this.verified = verified;
	   }

	   public Users(String username, String useremail, String password, String salt) {
	       this.username = username;
	       this.useremail = useremail;
	       this.password = password;
	       this.salt = salt;
	   }
	   // Object for setting new password
	   public Users(String useremail, String password, String salt, int verified) {
	       this.useremail = useremail;
	       this.password = password;
	       this.salt = salt;
	       this.verified = verified;
	   }
	   
	   // Creates Users
	   public Users(String username, String useremail, String password, String salt, int verified) {
	       this.username = username;
	       this.useremail = useremail;
	       this.password = password;
	       this.salt = salt;
	       this.verified = verified;
	   }
	   // Sends email to User
	   public Users(String username, String useremail, String verification) {
	       this.username = username;
	       this.useremail = useremail;
	       this.verification = verification;
	   }
	   
	   public Users(String username, String useremail, String password, String salt, String verification) {
	       this.username = username;
	       this.useremail = useremail;
	       this.password = password;
	       this.salt = salt;
	       this.verification = verification;
	       
	   }
	    
	   public Users(String username, String password) {
	       this.username = username;
	       this.password = password;

	   }

	   public int getId() {
	       return id;
	   }

	   public void setId(int id) {
	       this.id = id;
	   }

	   public String getUsername() {
	       return username;
	   }

	   public void setUsername(String username) {
	       this.username = username;
	   }

	   public String getEmail() {
	       return useremail;
	   }

	   public void setEmail(String useremail) {
	       this.useremail = useremail;
	   }
	   
	   public String getPassword() {
	       return password;
	   }

	   public void setPassword(String password) {
	       this.password = password;
	   }
	   
	   public String getSalt() {
	       return salt;
	   }

	   public void setSalt(String salt) {
	       this.salt = salt;
	   }
	   
	   public String getVerification() {
	       return verification;
	   }

	   public void setVerification(String verification) {
	       this.verification = verification;
	   }
	   
	   public int getVerified() {
	       return verified;
	   }

	   public void setVerified(int verified) {
	       this.verified = verified;
	   }


	}