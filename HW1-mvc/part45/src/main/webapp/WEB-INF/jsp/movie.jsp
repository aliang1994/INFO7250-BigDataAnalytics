<%-- 
    Document   : movie.jsp
    Created on : Sep 24, 2018, 10:08:14 AM
    Author     : aliceliang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movie DB</title>
    </head>
    <body>
        <h1>Movie Database</h1>
        <form method="post" action="movie.htm">
            Search by name: <input type="text" name="name" placeholder="Name" maxlength="25" required/> 
            <input type="hidden" name="type" value="name"/>
            <input type="submit" name="submit" value="go" /></br>
        </form>
        
        <form method="post" action="movie.htm">
            Search by genre: <input type="text" name="genre" placeholder="Genre" maxlength="25" required/>  
            <input type="hidden" name="type" value="genre"/>
            <input type="submit" name="submit" value="go"/></br>
        </form> </br> </br>

        
        <h2>New Search</h2>
        <form method="post" action="movie.htm">
            Keyword: <input type="text" name="keyword" placeholder="Keyword" maxlength="25" required/> 
            <input type="hidden" name="type" value="kw"/>
            <input type="submit" name="submit" value="go" /></br>
        </form>
        
        <div>
            <c:forEach items = "${requestScope.result}" var = "movie">
                ${movie.movieid}
                ${movie.title}
                ${movie.genres}</br>
            </c:forEach>
        </div>
    </body>
</html>
