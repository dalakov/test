<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

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
	    <h1>Authentication server Error</h1>
            <p>
		<c:out value="${message}" />
		(
            	<c:out value="${error.summary}" />
		)
            </p>
	</div>
    </div>
</body>
</html>