<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>단원 고르기</title>
</head>
<body>
	<h1> 단어 시험</h1>
	<h5> 해당 과를 누르면 시험이 시작됩니다</h5>
	<br><hr><br>
	<table>
		<thead>
			<th>몇 과일까</th>
			<th>몇 점을 맞을 수 있을까</th>
		</thead>
		<tbody>
			<tr>
				<td>1과</td>
				<td onclick="location.href='/goTest'">시험 보러가기</td>
			</tr>
			<tr>
				<td>2과</td>
				<td>시험 보러가기</td>
			</tr>
			<tr>
				<td>3과</td>
				<td>시험 보러가기</td>
			</tr>
		</tbody>
	</table>
	
</body>
</html>