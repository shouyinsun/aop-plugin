<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<meta http-equiv="content-type" content="text/html; charset=UTF-8">  
<title>server send event page</title>
</head>
<script src="fxxk/js/jquery.min.js" type="text/javascript"></script>
<body>
	<div id="resp"></div>	
</body>

<script type="text/javascript">
	if(!!window.EventSource){//新式浏览器才支持
		var source=new EventSource('push');
		s='';
		source.addEventListener('message',function(e){
			s+=e.data+"<br/>";
			$('#resp').htmt(s);
		});
		
		source.addEventListener('open',function(e){
			alert("连接打开！");
		},false);
		
		source.addEventListener('error',function(e){
			if(e.readyState==EventSource.CLOSE){
				alert("连接关闭！");
			}else{
				alert(e.readyState);
			}
		},false);
	}else{
		console.log("你的浏览器不支持SSE");
	}
</script>
</html>