<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Nebulake - New Post</title>
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
 	 	    <button class="nav-button">Sub Nebulas</button>
 	 	 	<div class="nav-dropdown-content">	 	 	 	
 	 	 		 <a class="nav-button" href="./RedirectSubnebulas">View Subnebulas</a>
 	 	 		 <a class="nav-button" href="./CreateSubnebulaForm">Create New Subnebula</a>
 	 	 	</div>
 	 	</div>
 	 	<div class="nav-dropdown">
 	 	    <button class="nav-button">Discussion</button>
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
 	
 	<div class="section">Create Post</div>
    <div align="center">
        <c:if test="${topic != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${topic == null}">
            <form action="createReply" method="post">
        </c:if>
        <table border="1" cellpadding="5">

                <c:if test="${reply != null}">
                    <input type="hidden" name="topic_id" value="<c:out value='${reply.id}' />" />
                </c:if>         
                  
            <tr>
                <th>Post: </th>
                <td>
                    <textarea id="post" name="replypost"></textarea>
                </td>
            </tr>

            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>