<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html>
<html>
<head>
 	<meta charset="ISO-8859-1">
	<title>Nebulake - Add Permission</title>
	<link rel="stylesheet" type="text/css" href="background-container.css">
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
 	 	<div class="nav-dropdown">
 	 	    <button class="nav-button">Nebulas</button>
 	 	 	<div class="nav-dropdown-content">	 	 	 	
 	 	 		 <a href="./ViewNebulas">View Nebulas</a>
 	 	 		 <a href="./CreateNewNebula">Create New Nebula</a>
 	 	 	</div>
 	 	</div>
 	 	<div class="nav-dropdown">
 	 	    <button class="nav-button"><c:out value="${categoryName}"/> - Sub Nebulas</button>
 	 	 	<div class="nav-dropdown-content">	 	 	 	
 	 	 		 <a href="./RedirectSubnebulas">View Subnebulas</a>
 	 	 		 <a href="./CreateSubnebulaForm">Create New Subnebula</a>
 	 	 	</div>
 	 	</div>
 	 	<div class="right-nav">
	 	 	<a href="./AboutPage">About</a>
	 	 	<a href="./SupportPage">Help</a>	 	 	
 	 		<a href="/logout">Logout</a>
 	 	</div>
 	</div> 
 	
 	<div class="section">Add Permission</div>

    <div align="center">
        <c:if test="${topic == null}">
            <form action="./CreateUserPermission" method="post"></form>
        </c:if>
        <form action="./CreateUserPermission" method="post">
	        <table border="2" cellpadding="10">
	            <tr>
	                <th>User Name: </th>
	                <td>
	                    <input type="text" name="permissionname" size="45"
	                            value="<c:out value='${topic.name}' />"
	                        />
	                </td>
	            </tr>
	
	            <tr>
	                <td colspan="2" align="center">
	                    <input type="submit" value="Add Permission" />
	                </td>
	            </tr>
	        </table>
        </form>
    </div>   
 	
</body>
</html>