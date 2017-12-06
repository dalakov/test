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
        
	<c:remove scope="session" var="SPRING_SECURITY_LAST_EXCEPTION" />
	<h2>The ${client.clientId} server requests access to your resources </h2>
        <h2>Please select a scope and confirm access</h2>

	<form id="confirmationForm" name="confirmationForm"
            action="<%=request.getContextPath()%>/oauth/authorize" method="post">
            <input name="user_oauth_approval" value="true" type="hidden" />
            <ul class="list-unstyled">
            <c:forEach items="${scopes}" var="scope">
                <c:set var="approved">
                    <c:if test="${scope.value}"> checked</c:if>
                </c:set>
                <c:set var="denied">
                    <c:if test="${!scope.value}"> checked</c:if>
                </c:set>
                <li>
                    <div class="form-group">
                        ${scope.key}: 
                        <input type="radio" name="${scope.key}" value="true" ${approved}>Approve</input> 
                        <input type="radio" name="${scope.key}" value="false" ${denied}>Deny</input>
                    </div>
                </li>
            </c:forEach>
            </ul>
            <input type="hidden" name="${_csrf.parameterName}" 	value="${_csrf.token}" />
		<button class="btn btn-primary" type="submit">Submit</button>
	</form>
        </div>
    </div>

</body>
</html>
