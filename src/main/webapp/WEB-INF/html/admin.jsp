<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<body>
<h2>admin</h2>
<form name="input" action="<%=basePath%>admin2" method="post">
    随便输入: <input type="text" name="name"><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
