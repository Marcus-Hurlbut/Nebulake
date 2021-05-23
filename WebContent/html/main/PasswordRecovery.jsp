<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 	<title>Nebulake - Recover Account</title>
 	<link rel="stylesheet" type="text/css" href="background-container.css">
 	<link rel="shortcut icon" type="image/png" href="favicon.png"/>
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
 	<div class="section">Recover Account</div>
 	<form action="/SendRecoverEmail" method="post">
 	<table border="1" cellpadding="5">
 
            <tr>
                <th>Email: </th>
                <td>
                    <input type="text" name="useremail" size="45" />
                    
                </td>
            </tr>
            <tr>

                <td colspan="2" align="center">
                    <input type="submit" value="Send Email" />
                </td>
            </tr>
        </table>
        </form>
</body>
</html>