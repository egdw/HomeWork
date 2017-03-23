<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>
	<table>
		<th>日期</th>
		<th>操作</th>
		<c:forEach items="${requestScope.list}" var="index">
			<tr>
				<td>${index.fileName}</td>
				<td><a href="download?fileName=${index.fileName}"
					target="_blank">下载</a></td>
			</tr>
		</c:forEach>

		<h1>
			<form action="updateTopic" method="post" accept-charset="utf-8">
				请输入上传首页标题<input type="text" name="topic"
					value="${requestScope.topic }"> <input type="submit"
					name="修改">
			</form>
		</h1>
	</table>
</body>
</html>
