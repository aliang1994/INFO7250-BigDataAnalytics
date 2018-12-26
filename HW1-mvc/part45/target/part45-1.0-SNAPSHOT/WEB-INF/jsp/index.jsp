<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to the Database!</title>
    </head>

    <body>
        <h1>Welcome to the Database</h1>
        <form method="get" action="select.htm">
            <select name="select">
                <option value="movie">Movie Database</option>
                <option value="access">Visiting Log Database</option>
            </select>
            <input type="submit" value="GO TO PAGE"/>
        </form>
        
    </body>
</html>
