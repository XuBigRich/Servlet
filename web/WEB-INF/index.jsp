<%@ page contentType="text/html;charset=utf-8"%>
<html>
<head>
	<title>通讯录</title>
	<link type="text/css" rel="stylesheet" href="common.css">
</head>
<script language="javascript" src="time.js" charset="gbk"></script>
<script language="javascript">

</script>
<body>
<center>
<form name="frm" action="com" method="post">
<input type=hidden name="cmd" value="login">
<table width=350 cellspacing=1>
	<tr><th colspan=2>通讯录</th></tr>
	<tr>
		<th>用户名</th>
		<td><input type=text name="nam"></td>
	</tr>
	<tr>
		<th>密码</th>
		<td><input type=password name="pwd"></td>
	</tr>
	<tr>
		<th colspan=2>
			<input type=submit value="登 录">
		</th>
	</tr>
</table>
</form>
</center>
</body>
</html>