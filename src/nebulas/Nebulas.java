package nebulas;

public class Nebulas {
	   protected int id;
	   protected String name;
	   protected String description;  
	   protected String author;
	   protected int protection;
	   protected String privacy;

	   public Nebulas() {
	   }
	   // Creates object for getting category id & redirects
	   public Nebulas(int id) {
	       this.id = id;
	   }
	   // Creates Object for getting categories by id
	   public Nebulas(int id, String name, String description) {
		   this.id = id;
		   this.name = name;
		   this.description = description;
	   }
	   // Creates Object for listing / displaying categories
	   public Nebulas(int id, String name, String description, String author, String privacy) {
		   this.id = id;
	       this.name = name;
	       this.description = description;      
	       this.author = author;
	       this.privacy = privacy;
	   }
	   // Creates Object for creating new categories
	   public Nebulas(String name, String description, String author, int protection) {
	       this.name = name;
	       this.description = description;
	       this.author = author;
	       this.protection = protection;
	   }

	   public int getId() {
	       return id;
	   }

	   public void setId(int id) {
	       this.id = id;
	   }

	   public String getName() {
	       return name;
	   }

	   public void setName(String name) {
	       this.name = name;
	   }

	   public String getDescription() {
	       return description;
	   }

	   public void setDescription(String description) {
	       this.description = description;
	   }
	   
	   public String getAuthor() {
		   return author;
	   }
	   
	   public void setAuthor(String author) {
		   this.author = author;
	   }
	   
	   public int getProtection() {
		   return protection;
	   }
	   
	   public void setProtection(int protection) {
		   this.protection = protection;
	   }
	   
	   public String getPrivacy() {
		   return privacy;
	   }
	   
	   public void setPrivacy(String privacy) {
		   this.privacy = privacy;
	   }

}