<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>NEBULAKE - Create Nexus</title>
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
 	
 	<div class="section">Create Nebula</div>
    <div align="center">
        <c:if test="${category != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${category == null}">
            <form action="./CreateNebula" method="post">
        </c:if>
        <table border="1" cellpadding="5">

                <c:if test="${category != null}">
                    <input type="hidden" name="category_id" value="<c:out value='${category.id}' />" />
                </c:if>           
            <tr>
                <th>Nebula </th>
                <td>
                    <input type="text" name="categoryname" size="45"
                            value="<c:out value='${category.name}' />"
                        />
                </td>
            </tr>
            
            <tr>
                <th>Description </th>
                <td>
                    <input type="text" name="categorydescription" size="45"
                            value="<c:out value='${category.description}' />"
                    />
                </td>
            </tr>
            
            <tr>
                <th>Privacy </th>
                <td>
                    <input type="radio" id="public" name="categoryprivacy" value="public">
                    <label for="public">Public</label>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" id="private" name="categoryprivacy" value="private">
                    <label for="public">Private</label>
                </td>
            </tr>

            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Create" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>