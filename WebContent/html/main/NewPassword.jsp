<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 	<title>Nebulake - Set New Password</title>
 	<link rel="stylesheet" type="text/css" href="background-container.css">
 	<link rel="shortcut icon" type="image/png" href="favicon.png"/>
</head>
<body>
 	<body>
 	<div class="title">~ NEBULAKE ~</div>
 	<div class="nav">
 	<ul>
 	 	<li><a href="/main.jsp">Main Menu</a></li>
 	 	<li><a href="/UsersForm.jsp">Create Account</a></li>
 	 	<li><a href="/AboutPage.jsp">About</a></li>
 	 	<li><a href="/HelpPage.jsp">Help</a></li>
 	 	<li class="logout"><a href="/LoginPage.jsp">Login</a></li>
 	</ul>
 	</div>
 	<div class="section">New Password</div>
 	
 	<form action = "/SetNewPassword" method="post">
 	
 	<table border="1" cellpadding="5">
  			<c:forEach var="user" items="${listEmail}">
  			 	<input type="text" value="${user.email}" name="useremail"/>
  			</c:forEach>
  			<tr>
                <th>Email: </th>
                <td>
                    <input type="text" name="useremail" size="45" />    
                </td>
            </tr>
            <tr>
                <th>New Password: </th>
                <td>
                    <input type="password" name="userpassword" size="45" />
                    
                </td>
            </tr>
            <tr>
                <th>Code: </th>
                <td>
                    <input type="text" name="authcode" size="45" />
                            
                    
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