<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Lixiao
  Date: 8/28/2015
  Time: 9:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商家登陆界面</title>
</head>
<body>
    <s:url value="/seller/login.action" var="loginPage"></s:url>
    <s:actionerror></s:actionerror>
    <s:fielderror fieldName="username"></s:fielderror>
    <s:fielderror fieldName="password"></s:fielderror>

    <s:form action="login" namespace="seller"  theme="simple">
    <s:textfield name="username"></s:textfield>
    <s:textfield name="password"></s:textfield>
        <s:submit value="提交"></s:submit>
    </s:form>
</body>
</html>
