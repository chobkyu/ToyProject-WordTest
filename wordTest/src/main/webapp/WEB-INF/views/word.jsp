<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>단어공부하기</title>
		<link rel="stylesheet" href="/resources/css/wordTest.css">
	</head>

<script>

	function wordStudy(level){
		var level = level;
		console.log(level);
		
		location.href = "/word?seq="+level+"&option=study";
	}
</script>
<body>
	<div class="header">
		<h1>단어 공부</h1>
		<h5>해당 과를 누르면 영어단어 공부를 할 수 있습니다</h5>
	</div>

	<br>
	<hr>
	<br>
	<table>
		<thead>
			<tr>
				<th>몇 과일까</th>
				<th>어떤 단어가 있을까?</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="level" begin="1" end="${count}">
				<tr>
					<td><c:out value="${level}" /></td>
					<td onclick="wordStudy(${level})">공부하러가기</td>
				</tr>

			</c:forEach>

		</tbody>
	</table>
	<button onclick="location.href='/'">뒤로 가기</button>
</body>
</html>