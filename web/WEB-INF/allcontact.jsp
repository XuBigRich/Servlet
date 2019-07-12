<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<h2>我的联系人</h2>
    <a href="com?cmd=pieservlet">统计画图</a>
	<a href="upload.jsp">上传文件</a>
	<a href="com?cmd=allfile">文件列表</a>
<a href="com?cmd=beforeAdd">添加联系人</a>
<a href="exit.jsp">退出登录</a>
<br><br>
<form action="contact.jsp" method=post>
<input type=hidden name="cmd" value="findContact">
<table width=600 cellspacing=1>
<tr>
	<th>姓名</th>
	<td><input type=text name="cnam"></td>
	<th>性别</th>
	<td>
		<input type=radio name="sex" class=nob value="a" checked>全部
		<input type=radio name="sex" class=nob value="m">男
		<input type=radio name="sex" class=nob value="f">女
	</td>
</tr>
<tr>
	<th>出生日期</th>
	<td>
		<input type=text name="birth" size=10 readonly onclick=setday(this)>
		-
		<input type=text name="birth2" size=10 readonly onclick=setday(this)>
	</td>
	<th>类别</th>
	<td>
		<select name="tid">
			<option value="0">请选择</option>
			<c:forEach items="${ts}" var="t">
				 <option value="${t.tid }">${t.tnam }</option>
			</c:forEach>
		</select>
	</td>
</tr>
<tr><th colspan=4><input type=submit value="查 询"></th></tr>
</table>
</form>
<table width=600 cellspacing=1>
<tr>
	<th>姓名</th>
	<th>性别</th>
	<th>出生日期</th>
	<th>联系电话</th>
	<th>类别</th>
	<th>操作</th>
</tr>
<c:forEach items="${pb.show}" var="c">
	<tr>
		<td>${c.cnam }</td>
		<td>${c.sex=='m'?'男':'女' }</td>
		<td>${c.birth }</td>
		<td>${c.tel }</td>
		<td>${c.tp.tnam }</td>
		<td>
			<a href="com?cmd=beforeUpdate&cid=${c.cid }">修改</a>
			<a href="com?cmd=delContact&cid=${c.cid }" onclick="return confirm('确定删除该联系人?');">删除</a>
		</td>
	</tr>
</c:forEach>
</table>
<br>
共有${pb.count }条记录，当前第${pb.p }页/共${pb.total }页
<a href="com?cmd=allContact&p=1">首页</a>
<a href="com?cmd=allContact&p=${pb.p-1 }">上一页</a>
<a href="com?cmd=allContact&p=${pb.p+1 }">下一页</a>
<a href="com?cmd=allContact&p=${pb.total }">尾页</a>
跳转到第<select>

</select>页
</center>
</body>
</html>