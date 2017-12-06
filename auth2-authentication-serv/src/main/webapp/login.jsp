<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" />" rel="stylesheet">
    <title> Auth-serv </title>
</head>

<body>
    <div class="container">
        <div class="jumbotron" style="margin-top: 30px;" >

	<h1>Authentication server</h1>

	<c:if test="${not empty param.authentication_error}">
            <p class="error">Your login attempt was not successful.</p>
	</c:if>
	<c:if test="${not empty param.authorization_error}">
   	    <p class="error">You are not permitted to access that resource.</p>
	</c:if>

	<div class="form-horizontal">
	<p>For test use username: "test" password: "qwerty".</p>
	<form action="<c:url value="/login"/>" method="post" role="form">
            <fieldset>
                <div class="form-group">
                    <label for="username">Username:</label> <input id="username"
                	class="form-control" type='text' name='username'
			value="test" />
                </div>
                <div class="form-group">
                    <label for="password">Password:</label> <input id="password"
                	class="form-control" type='text' name='password' value="qwerty" />
        	</div>
		<button class="btn btn-primary" type="submit">Login</button>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </fieldset>
	</form>
        </div>
        </div>
    </div>

</body>
</html>