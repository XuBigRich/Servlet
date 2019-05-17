<%@ page contentType="text/html;charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>显示图示</title>
	<link type="text/css" rel="stylesheet" href="common.css">
</head>
<script language="javascript" src="time.js" charset="gbk"></script>
<script language="javascript">
function nextImg(){
	document.getElementById("m").src="pie?a="+Math.random();
}
</script>
<body>
<center>
	<c:forEach items="${png}" var="c">
<input name="rnd">
	<img id="m" src="pie"><a href="#" onclick=nextImg()>看不清楚，换一张</a>
	<img id="m" src="${c}"><a href="#" onclick=nextImg()>看不清楚，换一张</a>
	</c:forEach>
</center>
</body>
</html>