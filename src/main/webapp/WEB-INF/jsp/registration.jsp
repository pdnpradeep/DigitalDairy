<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: Pradeep.P
  Date: 06-04-2015
  Time: 08:55
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<html>
<head>
    <title><tiles:getAsString name="title"/></title>
</head>
<body>

<form action="demo_form.asp">
    First name: <input type="text" name="fname" ><br>
    Last name: <input type="text" name="lname" ><br>
    Email: <input type="text" name="email"><br>
    phone no: <input type="text" name="phno" ><br>
    password: <input type="password" name="password"><br>
   ReEnter-password: <input type="password" name="repassword"><br>

    <input type="submit" value="Submit">
</form>

<br><br>

<center>
    <tiles:insertAttribute name="footer"/>
</center>


</body>
</html>