<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 	<title>Nebulake - Error</title>
 	<link rel="stylesheet" type="text/css" href="background-container.css">
 	<link rel="shortcut icon" type="image/png" href="favicon.png"/>
</head>
<body>
 <center>
  <div class="error-page">
        <div>Nebulake - Error</div>
        <div>[!] There was a problem with your request [!]</div>
        <br/>
        <div>ERROR <c:out value=': ${ERROR}' /></div>
  </div>
  </center>
</body>
</html>