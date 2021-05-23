<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
 	<meta charset="ISO-8859-1">
 	<title>Nebulake - Edit Picture</title>
 	<link rel="stylesheet" type="text/css" href="profile-container.css">
 	<link rel="shortcut icon" type="image/png" href="favicon.png"/>
</head>
<body>
 	<div class="title">~ NEBULAKE ~</div>
 	
 	<div class="nav">
 	 	<div class="nav-dropdown">
 	 	    <button class="nav-button">My Account</button>
 	 	 	<div class="nav-dropdown-content">	 	 	 	
 	 	 		 <a href="./MyAccount">Profile</a>
 	 	 		 <a href="./EditBio">Edit bio</a>
 	 	 		 <a href="./EditPicture">New profile picture</a>
 	 	 	</div>
 	 	</div>
 	 	
 	 	<a href="./ViewNebulas">Nebulake Forum</a>
 	 	<a href="./AboutPage">About</a>
 	 	<a href="./SupportPage">Help</a>
 	 	<a class="logout" href="/logout">Logout</a>
 	</div>
 	
 	<div class="section">Edit Profile Picture</div>
      
    <form action="./EditProfilePicture" method="post" enctype="multipart/form-data">  
	    <table class="edit" border="1" cellpadding="5">

	            <tr>
	                <th>Picture: </th>
	                <td>
	                    <input type="file" name="picture" />
	                </td>
	            </tr>
	
	            <tr>
	                <td colspan="2" align="center">
	                    <input type="submit" value="Save" />
	                </td>
	            </tr>

	     </table>     
     </form>     	
</body>
</html>