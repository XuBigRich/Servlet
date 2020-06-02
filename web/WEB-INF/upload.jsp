<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>上传</title>
</head>
<body>
    <filedset>
        <legend>上传单个文件</legend>
        <form action="com?cmd=uploadServlet" method="post" enctype="multipart/form-data">
            <input type="file" name="file">
            <br>
            <input type="submit" value="上传">
        </form>
    </filedset>
    <fieldset>
        <legend>上传多个文件</legend>
        <form action="com?cmd=uploadServlet" method="post" enctype="multipart/form-data">
            <input type="file" name="file">
            <br>
            <input type="file" name="file">
            <br>
            <input type="submit" value="上传">
        </form>
    </fieldset>
</body>
</html>
