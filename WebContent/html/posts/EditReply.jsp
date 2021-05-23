<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Nebulake - Edit Post</title>
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
 	 	    <button class="nav-button"><c:out value="${categoryName}"/></button>
 	 	 	<div class="nav-dropdown-content">	 	 	 	
 	 	 		 <a href="./RedirectSubnebulas">View Subnebulas</a>
 	 	 		 <a href="./CreateSubnebulaForm">Create New Subnebula</a>
 	 	 	</div>
 	 	</div>
 	 	<div class="nav-dropdown">
 	 	    <button class="nav-button"><c:out value="${topicName}"/></button>
 	 	 	<div class="nav-dropdown-content">	 	 	 	
 	 	 		 <a href="./RedirectPosts">View Discussion</a>
 	 	 		 <a href="./CreatePost">Create New Post</a>
 	 	 	</div>
 	 	</div>
 	 	<div class="right-nav">
	 	 	<a href="./AboutPage">About</a>
	 	 	<a href="./SupportPage">Help</a>	 	 	
 	 		<a href="/logout">Logout</a>
 	 	</div>
 	</div>
 	<br>
 	<div class="section">Edit Post</div>
 	<br>

      <form action="./EditPost" method="post">

        <table border="1" cellpadding="5">

                <c:if test="${reply != null}">
                    <input type="hidden" name="id" value="${reply.id}" />
                </c:if>         
                  
            <tr>
                <th>Post: </th>
                <td>
                    <textarea type="text" id="post" name="replypost" value="${reply.reply}" rows ="6" cols="45">${reply.reply}</textarea>
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