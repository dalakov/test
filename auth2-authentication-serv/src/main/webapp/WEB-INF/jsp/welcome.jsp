<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" />" rel="stylesheet">
    <title>Auth-serv</title>
</head>

<body>
    <div class="container">
        <div class="jumbotron" style="margin-top: 30px;" >
            <h1>Authentication server is running</h1>
            <h2>Click the button to run Data Viewer and execute sql(select * from users) to view users downloaded to the database</h2>
            <p><a class="btn btn-lg btn-primary" href="<c:url value="/viewdbusers" />" role="button">View Data</a></p>
        </div>
    </div>
</body>
</html>
