<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小说检索</title>
</head>
<body>
	<h1>小说检索系统</h1>
	<div id="boolean"></div>

	<div id="dim">
		<form action="DimQueryServlet" method="POST">
			<span> 模糊检索：</span><input type="text" name="keyWord" width="50px">
			<select name="filedId">
				<option value="0">书名</option>
				<option value="1">作者，出版社，其他</option>
				<option value="2">摘要</option>
			</select> <input type="submit" value="检索" />
		</form>
	</div>
	<hr>
	<div id="prefix">
		<form action="PrefixQueryServlet" method="POST">
			<span> 前缀检索：</span><input type="text" name="keyWord" width="50px">
			<select name="filedId">
				<option value="0">书名</option>
				<option value="1">作者，出版社，其他</option>
				<option value="2">摘要</option>
			</select> <input type="submit" value="检索" />
		</form>
	</div>
	<hr>
	<div id="boolean">
		<form action="BooleanQueryServlet" method="POST">
			<span> 布尔检索：</span><input type="text" name="keyWord1" width="50px">
			<select name="condition1">
				<option value="0">MUST</option>
				<option value="1">MUST_NOT</option>
				<option value="2">SHOULD</option>
			</select> <input type="text" name="keyWord2" width="50px"> <select
				name="condition2">
				<option value="0">MUST</option>
				<option value="1">MUST_NOT</option>
				<option value="2">SHOULD</option>
			</select> <select name="filedId">
				<option value="0">书名</option>
				<option value="1">作者，出版社，其他</option>
				<option value="2">摘要</option>
			</select> <input type="submit" value="检索" />
		</form>
	</div>
	<hr>
	<div id="range">
		<form action="RangeQueryServlet" method="POST">
			<span> 范围检索：</span><input type="text" name="startQueryString"
				width="50px" placeholder="1~993"> <input type="text" name="endQueryString"
				width="50px" placeholder="1~993"> 
<!-- 				<select name="filedId">
				<option value="0">书名</option>
				<option value="1">作者，出版社，其他</option>
				<option value="2">摘要</option>
			</select>  --><input type="submit" value="检索" />
		</form>
	</div>
	<hr>
	<div id="dim">
		<form action="PhraseQueryServlet" method="POST">
			<span> 短语检索：</span><input type="text" name="keyWord" width="50px">
			<select name="filedId">
				<option value="0">书名</option>
				<option value="1">作者，出版社，其他</option>
				<option value="2">摘要</option>
			</select> <input type="submit" value="检索" />
		</form>
	</div>
</body>
</html>