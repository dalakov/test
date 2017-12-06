<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" />" rel="stylesheet">
    <title> target-serv </title>
</head>

<body>
    <div class="container">
        <div class="jumbotron" style="margin-top: 30px;" >
            <h1>target server</h1>
            <h2>This is the target server. Your resources are stored here. 
                To access them, you must authenticate through the authentication server.
                Click the button to authenticate.</h2>
            <c:if test="${not empty pageContext.exception}">
                <h2>Error:</h2>       
                <h2>${pageContext.exception}</h2>
            </c:if>
            <p><a class="btn btn-lg btn-success" href="<c:url value="/auth2serv/data" />" role="button">Authenticate</a></p>
        </div>
    </div>
</body>

</html>