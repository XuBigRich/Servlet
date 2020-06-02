<%@ page contentType="text/html;charset=utf-8"%>
<jsp:useBean id="c" class="vo.Contact" scope="request"/>
<jsp:setProperty name="c" property="*"/>
<jsp:forward page="com"/>