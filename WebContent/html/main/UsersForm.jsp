<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
 	<link rel="stylesheet" type="text/css" href="background-container.css">
 	<link rel="shortcut icon" type="image/png" href="favicon.png"/>
    <title>Nebulake - Create User</title>
</head>
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
 	<div class="section">Create Account</div>
    <div align="center">
        <c:if test="${users != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${users == null}">
            <form action="create" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <tr>
                <th>Username: </th>
                <td>
                    <input type="text" name="username" size="45"  />       
                </td>
            </tr>
            
            <tr>
                <th>Email: </th>
                <td>
                    <input type="text" name="useremail" size="45"  />       
                </td>
            </tr>
            
            <tr>
                <th>Password: </th>
                <td>
                    <input type="password" name="userpassword" size="45" />

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