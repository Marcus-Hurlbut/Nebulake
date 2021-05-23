<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
 	<meta charset="ISO-8859-1">
 	<link rel="stylesheet" type="text/css" href="background-container.css">
 	<link rel="shortcut icon" type="image/png" href="favicon.png"/>
 	<title>Nebulake - Login</title>
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
 	<div class="section">Login</div>
    <div align="center">
    
        <form action="login" method="post">

	        <table border="1" cellpadding="5">	 
	            <tr>
	                <th>Username: </th>
	                <td>
	                    <input type="text" name="username" size="45" />                    
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
	                    <input type="submit" value="Login" />
	                </td>
	            </tr>
	        </table>
	        
        </form>
        <p><a href="/RecoverAccount">Forgot Password?</a> &nbsp;&nbsp;
           <a href="/UsersForm.jsp">Don't have an account?</a>
        </p>
    </div>   
</body>
</body>
</html>