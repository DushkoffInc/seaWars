<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Lobby</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<h1>Hello ${user.name} !!</h1>

<h2>Numbers of games: ${gamesSize}</h2>

<br/><br/>
<div>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>User Name</th>
        </tr>
        <c:forEach  items="${games}" var ="game">
            <tr>
                <td>${game.id}</td>
                <td>${game.userHost.name}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>

</html>