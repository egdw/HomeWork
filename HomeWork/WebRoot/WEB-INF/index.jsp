<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>作业上传</title>
<style type="text/css">
#block2 {
	height: 200px;
	width: 100%;
	border: #ccc 1px solid;
	font-size: 12px;
	text-align: center;
}

#block2 ul {
	list-style: outside none none;
	padding: 3px 0;
	overflow: hidden;
	margin: 0;
}

#block2 ul li {
	color: #F00;
	display: block;
	width: 100%;
}
</style>
<link
	href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<script src="http://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"
	type="text/javascript"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="alert alert-info" role="alert"
				style="text-align: center;">${requestScope.topic}</div>
		</div>
	</nav>
	<div class="gonggao" style="width=" 100%"; weight="600px">
		<hr>
		<center>
			<div id="block2">
				<center>
					<ul id="rolltxt">

						<li><h2>
								<div class="alert alert-danger" role="alert">注意事项</div>
							</h2></li>
						<li><div class="alert alert-danger" role="alert">只能上传ZIP和RAR</div></li>
						<li><div class="alert alert-danger" role="alert">格式：姓名+学号</div></li>

						<li><h3></h3></li>
					</ul>
				</center>
			</div>
		</center>
	</div>
	<center>
		<div class="container kv-main" style="width=100%;margin-top: 50px" ; weight="600px">
			<div class="list-group">
				<a href="#" class="list-group-item">
					<form action="uploadFile" method="post"
						enctype="multipart/form-data" float="left">
						<div style="width=100% weight="600px" float:block">
							<font size="4"><b>请选择要上传的文件:</b></font>
						</div>
						<input type="file" name="files"> <input type="submit"
							value="提交">
					</form>
				</a>
			</div>
		</div>
	</center>
	</div>

</body>
</html>