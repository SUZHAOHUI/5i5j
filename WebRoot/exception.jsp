<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<title><s:text name="exception_title"/></title>
</head>
<body style="padding:10px;background-color:#D6D3CE;">
<h2><s:text name="exception_title"/></h2>
<font color="#FF0000"><b><s:text name="exception_prompt"/></b></font><br/>
<textarea rows="22" cols="106">
	<!-- Output abnormal information content -->
	<s:property value="exception.message"/>
</textarea>
</body>
</html>