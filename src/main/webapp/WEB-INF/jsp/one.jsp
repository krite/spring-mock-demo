<%@ include file="/WEB-INF/jsp/includes/includes.jsp" %>

<html>
<head>
	<title><fmt:message key="title"/></title>
</head>
<body>
<form:form action="controllerone" method="post" commandName="command">
	<table>
		<tr>
			<td>
				Text to encrypt:
			</td>
			<td>
				<form:input path="text" size="80" maxlength="20"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit">Encrypt</button>
			</td>
		</tr>
	</table>
</form:form>
</body>
</html>
