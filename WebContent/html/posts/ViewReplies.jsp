<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
 	<meta charset="ISO-8859-1">
 	<link rel="stylesheet" type="text/css" href="replies-background-container.css">
 	<link rel="shortcut icon" type="image/png" href="favicon.png"/>
 	<title>Nebulake - Posts</title>
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
 	 	 		 <a class="nav-button" href="./RedirectSubnebulas">View Subnebulas</a>
 	 	 		 <a class="nav-button" href="./CreateSubnebulaForm">Create New Subnebula</a>
 	 	 	</div>
 	 	</div>
 	 	<div class="nav-dropdown">
 	 	    <button class="nav-button"><c:out value="${topicName}"/></button>
 	 	 	<div class="nav-dropdown-content">	 	 	 	
 	 	 		 <a class="nav-button" href="./RedirectPosts">View Discussion</a>
 	 	 		 <a class="nav-button" href="./CreatePost">Create New Post</a>
 	 	 	</div>
 	 	</div>
 	 	<div class="right-nav">
	 	 	<a class="right-nav-button" href="./AboutPage">About</a>
	 	 	<a class="right-nav-button" href="./SupportPage">Help</a>	 	 	
 	 		<a class="right-nav-button" href="/logout">Logout</a>
 	 	</div>
 	</div>
 	<br>
 	<div class="section"><c:out value="${topicName}"/> Discussion</div>
 	<br>
   	<div align="center">
		
		<c:forEach var="reply" items="${listReplies}">
		<table cellpadding="10" cellspacing="2">
			<tr>
				<td class="usrpost">Username: 
				<c:out value="${reply.author}" />
				<input type ="hidden" name="id" value="<c:out value='${reply.id}' />" />
				</td>
				<td class="time"><c:out value="${reply.time}" /></td>

			</tr>						
			<tr>		
				<td><c:out value="${reply.reply}" /></td>
				
				<c:if test="${reply.author == reply.user}">				
					<td class="details">
 						
						<form action="./EditPostForm" method="post">
						 	<input class="edit" type="hidden" value="${reply.id}" name="id" > 
						 	<input class="edit" type="submit" value="Edit">
						 </form>
				 
						<form action="./DeletePost" method="post">
						 	<input class="delete" type="hidden" value="${reply.id}" name="id" >
						 	<input class="delete" type="submit" value="Delete">
						</form>
					 	
					</td>
				</c:if>
				<c:if test="${reply.author != reply.user}">				
					<td class="details"> - - -</td>
				</c:if>
				</a>
			</tr>
		</table>
		&nbsp;
		</c:forEach>
		<h3><a href="./showReplyForm">Create New Post</a></h3>
	</div> 
</body>
</html>