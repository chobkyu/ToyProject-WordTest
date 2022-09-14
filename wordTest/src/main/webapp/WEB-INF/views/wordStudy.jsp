<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영단어 공부</title>
<link rel = "stylesheet" href = "/resources/css/wordTest.css">
</head>

<body>
<div class="header">
		<h1> 영단어 공부</h1>
		<h3> ${seq}과</h3>
	</div>

	<br><hr><br>
	<table>
		<thead>
			<tr>
				<th>영단어</th>
				<th>뜻</th>
				<th>예문</th>
				<th>예문 뜻</th>
			</tr>
		</thead>
		<tbody>
			
			<c:forEach items="${wlist}" var="dataVO">
				<tr>
					<td><c:out value="${dataVO.en}"></c:out> <p id="${dataVO.kr}"></p></td>
					<td><c:out value="${dataVO.kr}"></c:out></td>
					<td><c:out value="${dataVO.enSentence}"></c:out></td>
					<td><c:out value="${dataVO.krSentence}"></c:out></td>
				</tr>
		
		
			</c:forEach>

		</tbody>
	</table>
	<button onclick="location.href='/'">뒤로 가기</button>
</body>
</html>