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
<input name="rnd">
	<img id="m" src="pie"><a href="#" onclick=nextImg()>看不清楚，换一张</a>
	<img id="s" src="${png}"><a href="#" onclick=nextImg()>看不清楚，换一张</a>
</center>
</body>
</html>