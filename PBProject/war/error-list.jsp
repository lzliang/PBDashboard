<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach var="error" items="${errors}">
        <div style="font-size: medium; color: red; margin-bottom: 10px;" align="center">
                ${error}</div>
</c:forEach>