<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html>
<html>
<head>
 	<meta charset="ISO-8859-1">
 	<title>Nebulake - Subnebula List</title>
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
 	 	<div class="nav-dropdown">
 	 	    <button class="nav-button"><c:out value="${categoryName}"/></button>
 	 	 	<div class="nav-dropdown-content">	 	 	 	
 	 	 		 <a class="nav-button" href="./RedirectSubnebulas">View - <c:out value="${categoryName}"/> Subnebulas</a>
 	 	 		 <a class="nav-button" href="./CreateSubnebulaForm">Create New - <c:out value="${categoryName}"/> Subnebula</a>
 	 	 	</div>
 	 	</div>
 	 	<div class="right-nav">
	 	 	<a class="right-nav-button" href="./AboutPage">About</a>
	 	 	<a class="right-nav-button" href="./SupportPage">Help</a>	 	 	
 	 		<a class="right-nav-button" href="/logout">Logout</a>
 	 	</div>
 	</div>
  	<div class="section"><c:out value="${categoryName}"/></div>
	<c:if test="${commanderPanel != false}" >
		<div class="section-com-panel">Commander Panel</div>
		 	<div class="com-panel">
		 	 	<ul> 
			 	 	<li><a class="com-panel-button" href="./AddPermission">Add user to <c:out value="${categoryName}"/></a></li>
			 	 	<li><a class="com-panel-button" href="./RemovePermission">Remove user from <c:out value="${categoryName}"/></a></li>
		 	 	</ul>
		 	</div>
 	</c:if>
 	<br>
 	
 	<div class="section-list">Subnebulas</div>

    <div align="center">
        <table border="2" cellpadding="10">
            <tr>
                <th>Name</th>
                <th>Author</th>
                <th>Description</th>
                <th>View</th>
                <th>Options</th>
            </tr>
          <c:forEach var="topic" items="${listTopics}">
             <tr>
               		
                    <td><c:out value="${topic.name}" /></td>
                    <td><c:out value="${topic.author}" /></td>
                    <td><c:out value="${topic.description}" /></td>
                    
                    <form action="./ViewPosts" method="post">
	                    <td>
	                    	<input class="button" type="hidden" value="${topic.id}" name="replytopic" >
	                    	<input class="button" type="submit" value="View">
	                    </td>    
                  	</form>
                  	<c:if test="${topic.author == AUTHOR}">
                  	 	<form action="./EditSubnebulaForm" method="post"> 
	                  	 	<td>
	                  	 	 	<input class="button" type="hidden" value="${topic.id}" name="id" >
	                  	 	 	<input class="button" type="hidden" value="${topic.name}" name="name" >
	                  	 	 	<input class="button" type="hidden" value="${topic.description}" name="description" >
	                  	 	 	<input class="button" type="submit" value="Edit">
	                  	 	</td>
                  	 	</form>
                  	</c:if>
                  	<c:if test="${topic.author != AUTHOR}">
	                  	 	<td> - - - </td>
                  	</c:if>
                    
              </tr>
            </c:forEach>
                    
        </table>
        <h3><a href="./CreateSubnebulaForm">Create New Subnebula</a></h3> 	
    </div>   
</body>
</html>