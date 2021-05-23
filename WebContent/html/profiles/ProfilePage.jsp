<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
 	<meta charset="ISO-8859-1">
 	<title>Nebulake - My Account</title>
 	<link rel="stylesheet" type="text/css" href="profile-container.css">
 	<link rel="shortcut icon" type="image/png" href="favicon.png"/>
</head>
<body>
 	<div class="title">~ NEBULAKE ~</div>
 	
 	<div class="nav">
 	 	<div class="nav-dropdown">
 	 	    <button class="nav-button">My Account</button>
 	 	 	<div class="nav-dropdown-content">	 	 	 	
 	 	 		 <a class="nav-button" href="./MyAccount">Profile</a>
 	 	 		 <a class="nav-button" href="./EditBio">Edit bio</a>
 	 	 		 <a class="nav-button" href="./EditPicture">New profile picture</a>
 	 	 	</div>
 	 	</div>
 	 	
 	 	<a class="nav-button" href="./ViewNebulas">Nebulake Forum</a>
 	 	<div class="right-nav">
	 	 	<a class="right-nav-button" href="./AboutPage">About</a>
	 	 	<a class="right-nav-button" href="./SupportPage">Help</a>	 	 	
 	 		<a class="right-nav-button" href="/logout">Logout</a>
 	 	</div>
 	</div>
 	
 	
 	<c:forEach var="profile" items="${listProfile}">
 	
 	<div class="section">My Profile ~ <c:out value="${AUTHOR}" /></div>
 
        <div class="grid-container">  
        
           <div class="grid-item user"><c:out value="${profile.username}" />
            	<div class="email"><c:out value="${profile.email}" /></div>
           </div>
            	
            <c:forEach var="picture" items="${listProfilePicture}">						   
           		<div class="grid-item picture">
           		 		<img src="data:image/jpg;base64, ${picture.image}" />
           		</div>
           	</c:forEach>
           	
           <div class="grid-item numposts">- Number of Posts Made -<br><c:out value="${profile.numOfPosts}" /></div>
           <div class="grid-item numcomm">- Number of Nebulas Made -<br><c:out value="${profile.numOfCommanders}" /></div>
           <div class="grid-item bio"><c:out value="${profile.bio}" />

           </div>
        </div>    
        <br>
        <br>
        </c:forEach>
        
        
	  <div class="row">
	   	<div class="column"> 
		     <table border = "1" cellpadding="10">
		            <tr>
		                <th class="list1">Recent Posts</th>
		            </tr>   
		             		    
		 		<c:forEach var="post" items="${listRecentPosts}">	
		 	 		<tr class="tree-container">
		 	 		  	<td class="detail1"><c:out value="${post.recentPosts}" /></td>
		            </tr>
		        </c:forEach>
	     	</table> 
	    </div>
	    <br>
	     <div class="column">
	      	<table border = "1" cellpadding="10">
	      		    <tr>
		                <th class="list3">Popular Subnebulas</th>
		            </tr>   
			            
		        <c:forEach var="pop" items="${listPopularSubNebulas}">
		            <tr class="tree-container">
		                 <td class="detail3"><c:out value="${pop.popularSubNebulas}" /></td>
	            	</tr>
	        	</c:forEach>
	       </table>
		 </div>  
		 <div class="column">
	      	<table border = "1" cellpadding="10">
	      		    <tr>
		                <th class="list2">Commander of Nebulas</th>
		            </tr> 
	      
		        <c:forEach var="comm" items="${listCommNames}">	
		 	 		<tr class="tree-container">                
		                 <td class="detail2"><c:out value="${comm.commNames}" /></td>
		            </tr>     
		        </c:forEach>
		    </table>
		 </div>   
	   </div>            	
</body>
</html>