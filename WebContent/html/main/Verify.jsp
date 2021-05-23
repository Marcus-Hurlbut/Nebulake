<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 	<link rel="stylesheet" type="text/css" href="background-container.css">
 	<link rel="shortcut icon" type="image/png" href="favicon.png"/>
	<meta charset="UTF-8">
	<title>Nebulake - Verify Account</title>
</head>
<body>
 	<div class="title">~ NEBULAKE ~</div>
 	<div class="section">A Verification Code was sent to your email</div>
 	
 	<form action = "VerifyCode" method="post">

 	<table border="1" cellpadding="5">
            <tr>
                <th>Email: </th>
                <td>
                    <input type="text" name="useremail" size="45"  />       
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
                    <input type="submit" value="verify" />
                </td>
            </tr>
        </table>
    </form>
</body>
</html>