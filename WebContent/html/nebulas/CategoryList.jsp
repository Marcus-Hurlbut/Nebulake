<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
 	<meta charset="ISO-8859-1">
 	<title>Nebulake - Nebula List</title>
 	<link rel="stylesheet" type="text/css" href="background-container.css">
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
 	 	<div class="nav-dropdown">
 	 	    <button class="nav-button">Nebulas</button>
 	 	 	<div class="nav-dropdown-content">	 	 	 	
 	 	 		 <a class="nav-button" href="./ViewNebulas">View Nebulas</a>
 	 	 		 <a class="nav-button" href="./CreateNewNebula">Create New Nebula</a>
 	 	 	</div>
 	 	</div>
 	 	<div class="right-nav">
	 	 	<a class="right-nav-button" href="./AboutPage">About</a>
	 	 	<a class="right-nav-button" href="./SupportPage">Help</a>	 	 	
 	 		<a class="right-nav-button" href="/logout">Logout</a>
 	 	</div>
 	</div>
 		
 	<div class="section">Nebulas</div>
    <div align="center">
            
        <table border="2" cellpadding="10">
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Commander</th>
                <th>Privacy</th>
                <th>Nebula</th>
            </tr>
            <c:forEach var="nebula" items="${listNebulas}">
                <tr>
                    <td><c:out value="${nebula.name}" /></td>
                    <td><c:out value="${nebula.description}" /></td>
                    <td><c:out value="${nebula.author}" /></td>
                    <td><c:out value="${nebula.privacy}" /></td>
                    
                    <form action="./ViewSubnebulas" method="post">
                    <td>
                     	<input class="button" type="hidden" value="${nebula.id}" name="topiccategory" >
                     	<input class="button" type="hidden" value="${nebula.name}" name="categoryname" >
                    	<input class="button" type="submit" value="Enter">
                    </td>    
                  	</form>
                </tr>
            </c:forEach>
        </table>    
        <h3><a href="./CreateNewNebula">Create New Nebula</a></h3> 	  
    </div>   
</body>
</html>