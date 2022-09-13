<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>단원 고르기</title>
	<link rel = "stylesheet" href = "/resources/css/wordTest.css">
</head>

<script>
	function wordTest(level){
		var level = level;
		console.log(level);
		
		location.href = "/goTest?seq="+level;
	}
</script>
<body>
	<div class="header">
		<h1> 단어 시험</h1>
		<h5> 해당 과를 누르면 시험이 시작됩니다</h5>
	</div>

	<br><hr><br>
	<table>
		<thead>
			<tr>
				<th>몇 과일까</th>
				<th>몇 점을 맞을 수 있을까</th>
			</tr>
		</thead>
		<tbody>
			
			<c:forEach var="level" begin="1" end="12">
				<tr>
					<td><c:out value="${level}"/></td>
					<td onclick="wordTest(${level})">시험 보러가기</td>
				</tr>
			
			</c:forEach>

		</tbody>
	</table>
	<button onclick="location.href='/'">뒤로 가기</button>
</body>
</html>