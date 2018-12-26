<%-- 
    Document   : access.jsp
    Created on : Sep 24, 2018, 10:08:22 AM
    Author     : aliceliang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Access DB</title>
    </head>
    <body>
        <h1>Visitor Access Log Database</h1>
        <form method="post" action="access.htm">
            Search by IP address: <input type="text" name="ip" placeholder="IP" maxlength="35" required/> 
            <input type="hidden" name="type" value="ip"/>
            <input type="submit" name="submit" value="go" /></br>
        </form>
        
        <form method="post" action="access.htm">
            Search by status code: <input type="text" name="code" placeholder="Status" maxlength="25" required/> 
            <input type="hidden" name="type" value="code"/>
            <input type="submit" name="submit" value="go" /></br>
        </form>
        
        <form method="post" action="access.htm">
            Search by HTTP method <input type="text" name="method" placeholder="Method" maxlength="25" required/>  
            <input type="hidden" name="type" value="method"/>
            <input type="submit" name="submit" value="go"/></br>
        </form> </br> </br>

        
        <div>
            <c:forEach items = "${requestScope.accessresult}" var = "access">
                ${access.ipAddress}
                ${access.datetime}
                ${access.timezone}
                ${access.httpMethod}
                ${access.uri}
                ${access.statusCode}
                ${access.responseLength}
                </br>
            </c:forEach>
        </div>
    </body>
</html>
