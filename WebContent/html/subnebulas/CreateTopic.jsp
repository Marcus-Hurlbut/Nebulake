<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>NEBULAKE - Create SubNebula</title>
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
 	 	    <button class="nav-button"><c:out value="${categoryName}"/> - Sub Nebulas</button>
 	 	 	<div class="nav-dropdown-content">	 	 	 	
 	 	 		 <a class="nav-button" href="./RedirectSubnebulas">View Subnebulas</a>
 	 	 		 <a class="nav-button" href="./CreateSubnebulaForm">Create New Subnebula</a>
 	 	 	</div>
 	 	</div>
 	 	<div class="right-nav">
	 	 	<a class="right-nav-button" href="./AboutPage">About</a>
	 	 	<a class="right-nav-button" href="./SupportPage">Help</a>	 	 	
 	 		<a class="right-nav-button" href="/logout">Logout</a>
 	 	</div>
 	</div>
 	
 	<div class="section">Create Subnebula</div>

    <div align="center">
        <c:if test="${category =! null}">
            <form action="createTopic" method="post">
            </form>
        </c:if>
        <form action="createSubnebula" method="post">
        <table border="2" cellpadding="10">

                <c:if test="${category != null}">
                    <input type="hidden" name="topic_id" value="<c:out value='${topic.id}' />" />
                    <input type="hidden" name="category_id"  value='${CATEGORYID}' />
                </c:if>         
                  
            <tr>
                <th>Subnebula Name: </th>
                <td>
                    <input type="text" name="topicname" size="45"
                            value="<c:out value='${topic.name}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Description: </th>
                <td>
                    <input type="text" name="topicdescription" size="45"
                            value="<c:out value='${topic.description}' />"
                    />
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