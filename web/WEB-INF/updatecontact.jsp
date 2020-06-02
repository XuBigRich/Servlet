<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="common.css">
<title>修改联系人</title>
</head>
<script language="javascript" src="time.js" charset="gbk"></script>
<script language="javascript">

</script>

<body>
<center>
<h1>修改联系人</h1>
<form action="contact.jsp" method="post">
<input type=hidden name="cmd" value="updateContact">
<input type=hidden name="cid" value="${c.cid }">
<table width=300 cellspacing=1>
	<tr>
		<th>姓名</th>
		<td><input type=text name="cnam" value="${c.cnam }"></td>
	</tr>
	<tr>
		<th>性别</th>
		<td>
			<input type=radio name="sex" value="m" ${c.sex=='m'?'checked':'' }>男
			<input type=radio name="sex" value="f" ${c.sex=='f'?'checked':'' }>女
		</td>
	</tr>
	<tr>
		<th>出生日期</th>
		<td><input type=text name="birth" readonly size=10 onclick=setday(this) value="${c.birth }"></td>
	</tr>
	<tr>
		<th>联系电话</th>
		<td><input type=text name="tel" value="${c.tel }"></td>
	</tr>
	<tr>
		<th>类别</th>
		<td>
			<select name="tid">
				<c:forEach items="${ts}" var="t">
				 	<option value="${t.tid }" ${t.tid==c.tid?"selected":""}>${t.tnam }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr><th colspan=2><input type=submit value="修 改"></th></tr>
</table>	
</form>
</center>
</body>
</html>