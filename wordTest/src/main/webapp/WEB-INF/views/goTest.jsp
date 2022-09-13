<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>단어 시험</title>
</head>
<script>
	var arr = new Array;
	
	<c:forEach items="${wlist}" var="dataVO">
		arr.push({en:"${dataVO.en}",kr:"${dataVO.kr}",level:"${dataVO.level}"});
	</c:forEach>
	console.log(arr[0].en);
	
	function score(){
		var score = 0;
		for (var i =0; i<arr.length;i++){
			var id = arr[i].en
			var answer = document.getElementById(id).value;
			if(answer === arr[i].kr){
				console.log(i+"번 정답");
				score++;
			}
			else{
				console.log(i+"번 오답");
			}
		}
		alert(score/arr.length);
		
	}
</script>
<body>
	<h1> 단어 시험</h1>
	<h5> 1과 (80점 미만이면 틀린 개수만큼 5분씩 추가)</h5>
	<br><hr><br>
	
	<c:forEach items="${wlist}" var="dataVO">
		<p><c:out value="${dataVO.en}"></c:out></p>
		<input id= "${dataVO.en}" type="text" value=""> 
	</c:forEach>
		
		
	<br><br>
	<button onclick="score()" type="submit" value="제출하기">제출하기</button>
	
	
	
</body>
</html>