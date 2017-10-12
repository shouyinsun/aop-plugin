<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<meta http-equiv="content-type" content="text/html; charset=UTF-8">  
<meta charset="UTF-8"> 
<title>converter page</title>
</head>
<script src="fxxk/js/jquery.min.js" type="text/javascript"></script>
<body>
	<div id="resp"></div>
	<input type="button" onclick="req()" value="请求">
	
</body>

<!--自定义的媒体类型   application/x-wisely  -->
<script type="text/javascript">
	function req(){
		$.ajax({
			url:"convert",
			data:"328831-xuchao",
			type:"POST",
			contentType:"application/x-wisely",
			success:function(data){
				$("#resp").html(data);
			}
		});
	}
</script>
</html>