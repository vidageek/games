<%@ page language="java" contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>User-Agent: *
<c:forEach items="${games}" var="game">Disallow: /play/${game}/task
</c:forEach>
<c:forEach items="${inactiveGames}" var="game">Disallow: /play/${game}
</c:forEach>