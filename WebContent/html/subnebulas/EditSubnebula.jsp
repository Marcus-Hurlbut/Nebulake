<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>NEBULAKE - Edit SubNebula</title>
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
 	
 	<div class="section">Edit Subnebula</div>

    <div align="center">
    
        <form action="./EditSubnebula" method="post">
        <table border="1" cellpadding="10">
        
                  
            <tr>
                <th>Subnebula Name: </th>
                <td>
                    <input type="text" name="name" size="45"
                            value="<c:out value="${topic.name}" />"
                        />
                </td>
            </tr>
            <tr>
                <th>Description: </th>
                <td>
                    <input type="text" name="description" size="45"
                            value="<c:out value="${topic.description}" />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                 	<input type="hidden" name="id" value="${SUBNEBID}" /> 
                    <input type="hidden" name="catid"  value="${CATEGORYID}" />
                    <input type="submit" value="Edit" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>