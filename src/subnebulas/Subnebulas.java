package subnebulas;

public class Subnebulas{
	   protected int id;
	   protected int catid;
	   protected String categoryname;
	   protected int userid;
	   protected String name;
	   protected String author;
	   protected String description;  

	   public Subnebulas() {
	   }
	   
	  
	   // Object for listing nebula name to subnebula page
	   public Subnebulas(String categoryname) {
		   this.categoryname = categoryname;
	   }
	   // Object for editing subnebula
	   public Subnebulas(int id, String name, String description) {
		   this.id = id;
		   this.name = name;
		   this.description = description;
	   }
	   
	   public Subnebulas(int id, int catid, int userid, String name, String author, String description) {
	       this(catid, userid, name, author, description);
	       this.id = id;
	   }
	   
	   public Subnebulas( String name, String author, String description) { 
	       this.name = name;
	       this.author = author;
	       this.description = description;	       	       	       
	   } 
	   
	   public Subnebulas(int id, String name, String author, String description) { 
		   this.id = id;
	       this.name = name;
	       this.author = author;
	       this.description = description;	       	       	       
	   } 
	   
	   public Subnebulas(int catid, int userid, String name, String author, String description) {
		   this.catid = catid; 
		   this.userid = userid;
	       this.name = name;
	       this.author = author;
	       this.description = description;	       	       	       
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
	   
	   public String getCategoryName() {
	       return categoryname;
	   }

	   public void setCategoryName(String categoryname) {
	       this.categoryname = categoryname;
	   }
	   
	   public int getUserId() {
	       return userid;
	   }

	   public void setUserId(int userid) {
	       this.userid = userid;
	   }

	   public String getName() {
	       return name;
	   }

	   public void setName(String name) {
	       this.name = name;
	   }
	   public String getAuthor() {
	       return author;
	   }

	   public void setAuthor(String author) {
	       this.author = author;
	   }

	   public String getDescription() {
	       return description;
	   }

	   public void setDescription(String description) {
	       this.description = description;
	   }

}