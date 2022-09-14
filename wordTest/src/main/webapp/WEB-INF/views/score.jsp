<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>점수 확인</title>
	<link rel = "stylesheet" href = "/resources/css/wordTest.css">
</head>
<script>
	history.replaceState({}, null, location.pathname);

</script>
<body>
	<div class = "header">
		<h1> 점수 보기</h1>
		
		<br><hr><br>
		
	</div>
	
	<div>
		<table border="1">
			<thead>
				<tr>
					<th>점수</th>
					<th>날짜</th>
					<th title="80 이상 A 60 이상 B 40 이상 C 그 밑은 F">등급</th>
					<th>챕터</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${slist}" var="dataVO">
					<tr>
						<td><c:out value="${dataVO.score }"/></td>
						<td><c:out value="${dataVO.date }"/></td>
						<td><c:out value="${dataVO.grade }"/></td>
						<td><c:out value="${dataVO.chapter }"/>과</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</div>
	<button onclick="location.href='/'">뒤로 가기</button>
</body>
</html>